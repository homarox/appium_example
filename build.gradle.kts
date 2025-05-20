plugins {
    kotlin("jvm") version "1.9.0"
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.appium:java-client:8.5.1")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("org.seleniumhq.selenium:selenium-java:4.9.1")
    implementation(kotlin("stdlib"))
}

application {
    mainClass.set("AppiumTestKt")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
