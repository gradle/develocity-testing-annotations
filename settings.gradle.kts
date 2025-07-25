pluginManagement {
    plugins {
        id("com.gradle.develocity") version "4.1"
        id("com.gradle.common-custom-user-data-gradle-plugin") version "2.3"
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

val isCI = providers.environmentVariable("CI").presence()

develocity {
    server = "https://ge.gradle.org"
    buildScan {
        uploadInBackground = isCI.not()
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
        val hasAccessKey = providers.environmentVariable("DEVELOCITY_ACCESS_KEY").map { it.isNotBlank() }.orElse(false)
        isPush = hasAccessKey.zip(isCI) { accessKey, ci -> ci && accessKey }.get()
    }
}

rootProject.name = "develocity-testing-annotations"

fun Provider<*>.presence(): Provider<Boolean> = map { true }.orElse(false)
fun Provider<Boolean>.not(): Provider<Boolean> = map { !it }
