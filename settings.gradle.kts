pluginManagement {
    plugins {
        id("com.gradle.develocity") version "3.17.6"
        id("com.gradle.common-custom-user-data-gradle-plugin") version "2.0.2"
        id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    }
}


plugins {
    id("com.gradle.develocity")
    id("com.gradle.common-custom-user-data-gradle-plugin")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

val isCI = providers.environmentVariable("CI")

develocity {
    server = "https://ge.gradle.org"
    buildScan {
        uploadInBackground = isCI.map { false }.orElse(true)
        publishing.onlyIf { it.isAuthenticated }
        obfuscation {
            ipAddresses { addresses -> addresses.map { "0.0.0.0" } }
        }
    }
}

buildCache {
    local {
        isEnabled = true
    }

    remote(develocity.buildCache) {
        server = "https://eu-build-cache.gradle.org"
        isEnabled = true
        val accessKey = providers.environmentVariable("DEVELOCITY_ACCESS_KEY").orNull
        isPush = providers.environmentVariable("DEVELOCITY_ACCESS_KEY").zip(isCi) { key, ci ->
    		key.isNotBlank()
	}.orElse(false)
    }
}

rootProject.name = "develocity-testing-annotations"
