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

tasks.compileJava {
    options.isIncremental = true
    options.isFork = true
    options.isFailOnError = false
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
println("${versionCatalog.libraryAliases}")

dependencies {
    implementation(libs.bundles.log4j)

    implementation("com.typesafe", "config","1.4.2")
    implementation("io.netty", "netty-all", "4.1.69.Final")
    implementation("com.google.inject", "guice", "5.1.0")
    implementation("io.vavr", "vavr","0.10.4")

    val junitVersion = "5.8.1"
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)
    testImplementation("org.assertj", "assertj-core", "3.22.0")

    implementation(project(":db-common"))
}