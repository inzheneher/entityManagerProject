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
    testImplementation(
            "org.junit.jupiter",
            "junit-jupiter-api",
            "5.4.2"
    )
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}