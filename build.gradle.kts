plugins {
    `java-library`
    `maven-publish`
    application
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

tasks.named<Test>("test") {
    testLogging.showStackTraces = true
    useJUnitPlatform()
}

