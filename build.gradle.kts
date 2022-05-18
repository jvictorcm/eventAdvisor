plugins {
    java
    `maven-publish`
    application
    id("org.springframework.boot") version ("2.6.7")
    id("io.spring.dependency-management") version ("1.0.11.RELEASE")
    jacoco
}

group = "de.victor"
version = "0.0.1-SNAPSHOT"
description = "EventAdvisor"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    runtimeOnly("io.micrometer:micrometer-registry-new-relic")
    implementation("com.newrelic.agent.java:newrelic-api:7.6.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.8")
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("mysql:mysql-connector-java:8.0.29")
    testImplementation("junit:junit:4.12")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
    finalizedBy("jacocoTestReport")
    doLast {
        println("View code coverage at:")
        println("file://$buildDir/reports/jacoco/test/html/index.html")
    }
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
}