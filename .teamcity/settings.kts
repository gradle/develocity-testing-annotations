import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle

version = "2022.04"

project {
    buildType{
        name = "Publish Common Gradle Testing Annotations"
        id = RelativeId(name.toId())
        description = "Publish Common Gradle Testing Annotations to Maven Central staging repository"

        vcs {
            root(AbsoluteId("OpenSourceProjects_GradleEnterpriseTestingAnnotations_GradleGradleEnterpriseTestingAnnotations"))
            checkoutMode = CheckoutMode.ON_AGENT
            cleanCheckout = true
        }

        requirements {
            contains("teamcity.agent.jvm.os.name", "Linux")
        }

        steps {
            gradle {
                useGradleWrapper = true
                tasks = "clean publishMavenJavaPublicationToSonatypeRepository"
                gradleParams = "--build-cache"
            }
        }
        params {
            param("env.ORG_GRADLE_PROJECT_sonatypeUsername", "%mavenCentralStagingRepoUser%")
            password("env.ORG_GRADLE_PROJECT_sonatypePassword", "%mavenCentralStagingRepoPassword%")
            password("env.PGP_SIGNING_KEY", "%pgpSigningKey%")
            password("env.PGP_SIGNING_KEY_PASSPHRASE", "%pgpSigningPassphrase%")
        }
    }
    params {
        param("env.GRADLE_ENTERPRISE_ACCESS_KEY", "%ge.gradle.org.access.key%")
        param("env.GRADLE_CACHE_REMOTE_URL", "%gradle.cache.remote.url%")
        param("env.GRADLE_CACHE_REMOTE_USERNAME", "%gradle.cache.remote.username%")
        password("env.GRADLE_CACHE_REMOTE_PASSWORD", "%gradle.cache.remote.password%")
    }
}
