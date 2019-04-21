plugins {
    java
}

group = "org.mav"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(
            "org.hibernate",
            "hibernate-core",
            "5.4.2.Final"
    )
    implementation(
            "com.h2database",
            "h2",
            "1.4.199"

    )
    implementation(
            "org.springframework",
            "spring-context",
            "5.1.6.RELEASE"
    )
    implementation(
            "org.springframework",
            "spring-orm",
            "5.1.6.RELEASE"
    )
    implementation(
            "commons-dbcp",
            "commons-dbcp",
            "1.4"
    )
    testImplementation(
            "org.junit.jupiter",
            "junit-jupiter-api",
            "5.4.2"
    )
    testImplementation(
            "org.springframework", 
            "spring-test", 
            "5.1.6.RELEASE"
    )
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<Test> {
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    setForkEvery(100)
    reports.html.isEnabled = false
    reports.junitXml.isEnabled = false
}

tasks.withType<JavaCompile> {
    options.isFork = true
}