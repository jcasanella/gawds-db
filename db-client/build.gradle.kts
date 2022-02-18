plugins {
    `maven-publish`
    application
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
    val log4jVersion = "2.17.1"
    implementation("org.apache.logging.log4j", "log4j-api", log4jVersion)
    implementation("org.apache.logging.log4j", "log4j-core", log4jVersion)

    implementation("io.netty", "netty-all", "4.1.69.Final")

    implementation("com.google.inject", "guice", "5.0.1")

    val junitVersion = "5.8.1"
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)

    implementation(project(":db-common"))
}
