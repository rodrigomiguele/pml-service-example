import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "rmiguele"
version = "1.0.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.21"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

subprojects {
    repositories {
        mavenCentral()
    }
}