plugins {
    `maven-publish`
    application
    `java-library`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
}

configure<JavaApplication> {
    mainClass.set("com.gawds.db.MainApp")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

dependencies {
    val log4jVersion = "2.17.1"
    implementation("org.apache.logging.log4j", "log4j-api", log4jVersion)
    implementation("org.apache.logging.log4j", "log4j-core", log4jVersion)
    implementation("org.apache.logging.log4j", "log4j-slf4j-impl", log4jVersion)

    implementation("com.typesafe", "config","1.4.2")
    implementation("io.netty", "netty-all", "4.1.69.Final")
    implementation("com.google.inject", "guice", "5.0.1")
    implementation("io.vavr", "vavr","0.10.4")

    val junitVersion = "5.8.1"
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)
    testImplementation("org.assertj", "assertj-core", "3.22.0")

    implementation(project(":db-common"))
}