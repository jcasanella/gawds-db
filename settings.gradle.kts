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
        }
    }
}

rootProject.name = "gawds-db"
include("db-server", "db-client", "db-common")
