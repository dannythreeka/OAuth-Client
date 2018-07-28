import org.gradle.jvm.tasks.Jar
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    `build-scan`
    `maven-publish`
    kotlin("jvm") version "1.2.41"
    id("org.jetbrains.dokka") version "0.9.16"
}

group = "com.danny"
version = "0.0.1"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib", "1.2.31"))
    testImplementation("junit:junit:4.12")
}

// Configure existing Dokka task to output HTML to typical Javadoc directory
val dokka by tasks.getting(DokkaTask::class) {
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"
}

// Create dokka Jar task from dokka task output
val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    classifier = "javadoc"
    // dependsOn(dokka) not needed; dependency automatically inferred by from(dokka)
    from(dokka)
}

// Create sources Jar from main kotlin sources
val sourcesJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles sources JAR"
    classifier = "sources"
    from(java.sourceSets["main"].allSource)
}

publishing {
    publications {
        create("default", MavenPublication::class.java) {
            from(components["java"])
            artifact(sourcesJar)
            artifact(dokkaJar)
        }
    }
    repositories {
        maven {
            url = uri("$buildDir/repository")
        }
    }
}