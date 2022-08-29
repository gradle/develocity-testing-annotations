plugins {
    java
    id("io.github.gradle-nexus.publish-plugin")
}


nexusPublishing {
    packageGroup.set("org.gradle")
    repositories {
        sonatype {
            // https://central.sonatype.org/news/20210223_new-users-on-s01/
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
