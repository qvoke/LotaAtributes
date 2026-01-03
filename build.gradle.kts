plugins {
    id("java")
}

group = "com.lota.LotaAtributes"
version = "1.0-SNAPSHOT"
base.archivesName.set("lotaatributes")

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}