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
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.6.6")

    implementation(project(":rest"))
    implementation(project(":persistence"))
    implementation(project(":usecases"))
}