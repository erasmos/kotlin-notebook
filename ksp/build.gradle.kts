plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:2.0.0-1.0.21")
}

tasks.test {
    useJUnitPlatform()
}