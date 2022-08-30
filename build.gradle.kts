plugins {
    java
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
    withJavadocJar()
    withSourcesJar()
}

nexusPublishing {
    repositories {
        sonatype {
            // https://central.sonatype.org/news/20210223_new-users-on-s01/
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

publishing {
    publications.create<MavenPublication>("mavenJava") {
        artifactId = project.name
        from(components["java"])

        pom {
            name.set("Gradle Enterprise Testing Annotations")
            description.set("Common annotations for Gradle Enterprise Testing.")
            url.set("https://github.com/gradle/gradle-enterprise-testing-annotations/")
            licenses {
                license {
                    name.set("Apache-2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0")
                }
            }
            developers {
                developer {
                    name.set("The Gradle team")
                    organization.set("Gradle Inc.")
                    organizationUrl.set("https://gradle.com")
                }
            }
            scm {
                connection.set("scm:git:git://github.com/gradle/gradle-enterprise-testing-annotations.git")
                developerConnection.set("scm:git:ssh://git@github.com:gradle/gradle-enterprise-testing-annotations.git")
                url.set("https://github.com/gradle/gradle-enterprise-testing-annotations/")
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
    useInMemoryPgpKeys(System.getenv("PGP_SIGNING_KEY"), System.getenv("PGP_SIGNING_KEY_PASSPHRASE"))
}
