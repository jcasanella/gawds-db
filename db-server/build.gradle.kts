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

// This task will print the catalog alias
abstract class PrintCatalogAlias @Inject constructor(private val r : Project) :  DefaultTask() {
    @TaskAction
    fun catalogAlias() {
        val versionCatalog = r.rootProject.project.extensions.getByType<VersionCatalogsExtension>().named("libs")
        println("${versionCatalog.libraryAliases}")
    }
}

tasks.register<PrintCatalogAlias>("catalog")

dependencies {
    implementation(libs.bundles.log4j)
    implementation(libs.netty)
    implementation(libs.guice)

    implementation("com.typesafe", "config","1.4.2")
    implementation("io.vavr", "vavr","0.10.4")

    // Test libs
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.assertj)

    implementation(project(":db-common"))
}