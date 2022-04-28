plugins {
    java
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.allopen") version "1.6.10"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = "br.com.github"
    version = "1.0-SNAPSHOT"

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "java-test-fixtures")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        implementation("org.springframework.boot:spring-boot-starter-web:2.6.6")
        implementation("org.springframework.boot:spring-boot-starter-validation:2.6.6")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.2")

        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.0")
        testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.6")
        testImplementation("io.mockk:mockk:1.12.3")
        testImplementation("com.ninja-squad:springmockk:3.1.1")

        testImplementation("io.kotest:kotest-assertions-core-jvm:5.2.3")
        testImplementation("io.kotest:kotest-property:5.2.3")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:5.2.3")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}