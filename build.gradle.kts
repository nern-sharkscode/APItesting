plugins {
    java
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allure {
    version.set("2.27.0")
}
val aspectjweaver: Configuration by configurations.creating
dependencies {
    // Playwright для виконання запитів
    implementation("io.rest-assured:rest-assured:5.4.0")

    // TestNG для запуску тестів
    testImplementation("org.testng:testng:7.9.0")

    // Allure для звітів
    implementation("io.qameta.allure:allure-testng:2.27.0")

    // Jackson для перетворення Java-об'єктів (POJO) у JSON і навпаки
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")

    // Бібліотеки Log4j2
    implementation("org.apache.logging.log4j:log4j-api:2.23.1")
    implementation("org.apache.logging.log4j:log4j-core:2.23.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.23.1")
    aspectjweaver("org.aspectj:aspectjweaver:1.9.22")
}

tasks.test {
    useTestNG {
//        suites("src/test/resources/testng.xml")
    }
    testLogging {
        events("passed", "skipped", "failed")
    }
    doFirst {
        jvmArgs("-javaagent:${aspectjweaver.asPath}")
    }
}

configurations.all {
    resolutionStrategy {
        force("org.aspectj:aspectjweaver:1.9.22")
    }
}