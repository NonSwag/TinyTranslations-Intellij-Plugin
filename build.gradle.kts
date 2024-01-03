plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    id("org.jetbrains.intellij") version "1.13.3"
}

group = "de.cubbossa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://nexus.leonardbausenwein.de/repository/maven-public/")
}

dependencies {
    implementation("de.cubbossa:Translations:4.0.0")
    implementation("net.kyori:adventure-api:4.14.0")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.3.1")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf("org.intellij.intelliLang", "org.jetbrains.plugins.yaml", "com.intellij.properties"))
}

sourceSets["main"].java.srcDirs("src/main/gen", "src/main/kotlin")

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("233.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
