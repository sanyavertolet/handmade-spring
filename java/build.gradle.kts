plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

//    implementation("cglib:cglib:3.3.0")
    implementation("org.reflections:reflections:0.9.12")
    implementation("javax.annotation:jsr250-api:1.0")
}

tasks.test {
    useJUnitPlatform()
}

java {
    version = 17
}
