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
    testImplementation("org.projectlombok:lombok:1.18.40")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.squareup.okhttp3:okhttp:5.1.0")
    implementation("com.google.apis:google-api-services-calendar:v3-rev20220715-2.0.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    implementation("org.eclipse.jetty:jetty-server:11.0.24")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.24")
    runtimeOnly("com.h2database:h2:2.2.224")
}

tasks.test {
    useJUnitPlatform()
}