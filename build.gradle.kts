plugins {
    kotlin("jvm") version "1.6.10"
    application
}

application {
    mainClass.set("pw.derise.demo.MainKt")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.camunda:zeebe-client-java:1.2.7")
    implementation("org.slf4j:slf4j-nop:2.0.0-alpha5")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}