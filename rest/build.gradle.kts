dependencies {
    implementation(project(":usecases"))
    implementation(project(":integrations"))

    testFixturesImplementation(testFixtures(project(":usecases")))

    constraints {
        implementation("com.fasterxml.jackson.module:jackson-databind:2.13.2.2") {
            because("2.13.2 version has CVE-2020-36518")
        }
    }
}