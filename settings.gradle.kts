dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("log4jVersion", "2.17.1")
            library("log4j-api", "org.apache.logging.log4j", "log4j-api").versionRef("log4jVersion")
            library("log4j-core", "org.apache.logging.log4j", "log4j-core").versionRef("log4jVersion")
            library("log4j-slf4j-impl", "org.apache.logging.log4j", "log4j-slf4j-impl").versionRef("log4jVersion")
            bundle("log4j", listOf("log4j-api", "log4j-core", "log4j-slf4j-impl"))

            version("junitVersion", "5.8.1")
            library("junit-api", "org.junit.jupiter", "junit-jupiter-api").versionRef("junitVersion")
            library("junit-engine", "org.junit.jupiter", "junit-jupiter-engine").versionRef("junitVersion")
            library("junit-params", "org.junit.jupiter", "junit-jupiter-params").versionRef("junitVersion")


            library("assertj", "org.assertj", "assertj-core").version("3.22.0")
            library("netty", "io.netty", "netty-all").version("4.1.69.Final")
            library("guice", "com.google.inject", "guice").version("5.1.0")
        }
    }
}

rootProject.name = "gawds-db"
include("db-server", "db-client", "db-common")
