plugins {
    `java-library`
    `maven-publish`
    application
}

repositories {
    mavenCentral()
}

configure<JavaApplication> {
    mainClass.set("com.db.learn.gawds.MainApp")
}

dependencies {
    implementation("org.apache.logging.log4j", "log4j-api", "2.11.2")
    implementation("org.apache.logging.log4j", "log4j-core", "2.11.2")

    implementation("io.netty", "netty-all", "4.1.69.Final")

    implementation("com.google.inject", "guice", "5.0.1")
}