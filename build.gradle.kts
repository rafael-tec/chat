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

    dependencies {
        implementation("org.springframework.boot:spring-boot-gradle-plugin:2.6.4")

        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}
