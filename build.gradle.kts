plugins {
    `java-library`
    `maven-publish`
    `application`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

    if (JavaVersion.current() != JavaVersion.VERSION_11) {
        throw GradleException("The java version used ${JavaVersion.current()} is not the expected version ${JavaVersion.VERSION_11}.")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.db.learn"
            artifactId = "gawds"
            version = "0.1"

            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
}

configure<JavaApplication> {
    mainClass.set("com.db.learn.gawds.MainApp")
}

tasks.named<Test>("test") {
    testLogging.showStackTraces = true
    useJUnitPlatform()
}

dependencies {
    implementation("org.apache.logging.log4j", "log4j-api", "2.11.2")
    implementation("org.apache.logging.log4j", "log4j-core", "2.11.2")

    implementation("io.netty", "netty-all", "4.1.69.Final")

    implementation("com.google.inject", "guice", "5.0.1")
}
