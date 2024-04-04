pluginManagement {
    plugins {
        id("com.gradle.enterprise") version "3.16.2"
        id("com.gradle.common-custom-user-data-gradle-plugin") version "1.13"
        id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    }
}


plugins {
    id("com.gradle.enterprise")
    id("com.gradle.common-custom-user-data-gradle-plugin")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "develocity-testing-annotations"
