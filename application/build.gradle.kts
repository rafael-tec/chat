plugins {
    application
}

application {
    mainClass.set("br.com.github.chat.application.BootKt")

    applicationDefaultJvmArgs = listOf(
        "-server",
        "-XX:+UseNUMA",
        "-XX:+UseG1GC",
        "-XX:+UseStringDeduplication",
        "-Duser.timezone=Etc/UTC"
    )
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.6.4")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.5")
}