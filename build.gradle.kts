plugins {
    kotlin("jvm") version "1.8.22"
    checkstyle
}

checkstyle {
    configFile = rootProject.file("checkstyle.xml")
}

group = "ru.job4j"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.apache.commons:commons-dbcp2:2.7.0")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.testcontainers:junit-jupiter:1.18.3")
    testImplementation("org.testcontainers:postgresql:1.17.3")
}

tasks.test {
    useJUnitPlatform()
}