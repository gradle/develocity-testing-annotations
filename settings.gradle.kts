pluginManagement {
    plugins {
        id("com.gradle.enterprise") version "3.17.5"
        id("com.gradle.enterprise.gradle-enterprise-conventions-plugin") version "0.7.5"
        id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    }
}


plugins {
    id("com.gradle.enterprise")
    id("com.gradle.enterprise.gradle-enterprise-conventions-plugin")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "develocity-testing-annotations"
