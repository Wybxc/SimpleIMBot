plugins {
    id("java")
    id("application")
}

group = "cc.wybxc"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("cc.wybxc.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
