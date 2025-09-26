plugins {
    id("java")
    id("application")
}

application {
    mainClass = "org.example.Main"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.squareup.okhttp3:okhttp:5.1.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    implementation("org.eclipse.jetty:jetty-server:11.0.24")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.24")
    runtimeOnly("com.h2database:h2:2.2.224")

    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    implementation("com.github.pengrad:java-telegram-bot-api:9.2.0")
}

tasks.test {
    useJUnitPlatform()
}