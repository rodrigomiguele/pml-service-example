import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.mongodb:mongo-java-driver:2.12.3")
    implementation("dev.morphia.morphia:core:1.5.3")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation ("org.mockito:mockito-core:3.3.3")
    testImplementation ("org.mockito:mockito-junit-jupiter:3.3.3")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}