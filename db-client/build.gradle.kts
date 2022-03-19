plugins {
    `maven-publish`
    application
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
    mainClass.set("com.gawds.client.MainApp")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<JavaExec> {
    mainClass.set("com.gawds.client.MainApp")

    args("localhost", "8082")
}

dependencies {
    implementation(libs.bundles.log4j)

    implementation("io.netty", "netty-all", "4.1.69.Final")

    implementation("com.google.inject", "guice", "5.0.1")

    val junitVersion = "5.8.1"
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    implementation(project(":db-common"))
}
