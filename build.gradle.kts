import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
    id("org.jetbrains.intellij.platform") version "2.2.1"
    id("org.jetbrains.kotlin.jvm") version "2.1.20-RC"
}

group = "de.cubbossa"
version = "1.1.0"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
    maven("https://nexus.leonardbausenwein.de/repository/maven-public/")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    intellijPlatform {
        bundledPlugin("com.intellij.java")
        bundledPlugin("org.intellij.intelliLang")
        bundledPlugin("org.jetbrains.plugins.yaml")
        bundledPlugin("com.intellij.properties")
        intellijIdeaCommunity("2024.3")
    }
}

sourceSets.main {
    java.srcDirs("src/main/gen", "src/main/kotlin")
}

tasks {
    generateParser {
        sourceFile.set(file("src/main/kotlin/org/intellij/sdk/language/legacy/common/parser/LegacyParser.bnf"))
        pathToParser.set("/org/intellij/sdk/language/legacy/common/parser")
        pathToPsiRoot.set("/org/intellij/sdk/language/legacy/common/parser")
        targetRootOutputDir.set(file("src/main/gen"))
        purgeOldFiles.set(true)
    }
}

tasks.register("generateLegacyLexer", org.jetbrains.grammarkit.tasks.GenerateLexerTask::class.java) {
    sourceFile.set(file("src/main/kotlin/org/intellij/sdk/language/legacy/common/lexer/LegacyLexer.flex"))
    targetOutputDir.set(file("src/main/gen/org/intellij/sdk/language/legacy/common/lexer"))
    targetFile("LegacyLexer")
}

tasks.register("generateMiniMessageLexer", org.jetbrains.grammarkit.tasks.GenerateLexerTask::class.java) {
    sourceFile.set(file("src/main/kotlin/org/intellij/sdk/language/minimessage/lexer/MiniMessageLexer.flex"))
    targetOutputDir.set(file("src/main/gen/org/intellij/sdk/language/minimessage/lexer"))
    targetFile("MiniMessageLexer")
}

tasks.register("generateNanoMessageLexer", org.jetbrains.grammarkit.tasks.GenerateLexerTask::class.java) {
    sourceFile.set(file("src/main/kotlin/org/intellij/sdk/language/nanomessage/lexer/NanoMessageLexer.flex"))
    targetOutputDir.set(file("src/main/gen/org/intellij/sdk/language/nanomessage/lexer"))
    targetFile("NanoMessageLexer")
}


kotlin.compilerOptions {
    jvmTarget.set(JvmTarget.JVM_21)
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks.compileJava {
    options.release.set(21)
}

tasks.patchPluginXml {
    sinceBuild.set("243")
    untilBuild.set("243.*")
}

// tasks.signPlugin {
//     certificateChainFile.set(file("C:/Users/leona/chain.crt"))
//     privateKeyFile.set(file("C:/Users/leona/private_encrypted.pem"))
//     password.set(providers.environmentVariable("PRIVATE_KEY_PASSWORD"))
// }

// tasks.publishPlugin {
//     token.set(System.getenv("PUBLISH_TOKEN"))
// }