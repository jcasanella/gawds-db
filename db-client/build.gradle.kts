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
    implementation(libs.netty)
    implementation(libs.guice)

    // Test libs
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.assertj)

    implementation(project(":db-common"))
}
