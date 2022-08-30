pluginManagement {
    plugins {
        id("com.gradle.enterprise") version "3.11.1"
        id("com.gradle.enterprise.gradle-enterprise-conventions-plugin") version "0.7.4"
        id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
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

rootProject.name = "gradle-enterprise-testing-annotations"
