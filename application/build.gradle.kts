plugins {
    java
    id("io.quarkus") version "1.3.2.Final"
    kotlin("jvm")
    kotlin("plugin.allopen")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":core"))
    implementation("org.mongodb:mongo-java-driver:2.12.3")
    implementation("dev.morphia.morphia:core:1.5.3")
    implementation(enforcedPlatform("io.quarkus:quarkus-bom:1.3.2.Final"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-vertx")
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}