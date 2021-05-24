version = "0.1.0"

plugins {
    id("org.jetbrains.kotlin.jvm").version("1.4.31")
    "maven-publish"
}

repositories {

    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.mockito:mockito-core:3.3.0")
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-inline:2.13.0")
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
                         "Implementation-Version" to project.version))
    }
}