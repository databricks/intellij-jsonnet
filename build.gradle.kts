plugins {
    id("java")
    // https://github.com/JetBrains/Grammar-Kit/releases
    id("org.jetbrains.grammarkit") version "2022.3"
    // https://github.com/jetbrains/gradle-intellij-plugin/releases
    id("org.jetbrains.intellij") version "1.13.2"
}

group = "com.databricks"
version = "2.0-SNAPSHOT"

repositories {
    mavenCentral()
}

intellij {
    version.set("2022.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

sourceSets {
    main {
        java.srcDirs("src/main/gen")
    }
}

apply(plugin = "org.jetbrains.grammarkit")

tasks {
    patchPluginXml {
        sinceBuild.set("223")
        untilBuild.set("")
    }

    generateLexer {
        source.set("src/main/grammars/Jsonnet.flex")
        targetDir.set("src/main/gen/com/jsonnetplugin")
        targetClass.set("JsonnetLexer")
        purgeOldFiles.set(true)
    }

    generateParser {
        source.set("src/main/grammars/Jsonnet.bnf")
        targetRoot.set("src/main/gen")
        pathToParser.set("com/jsonnetplugin/parser/JsonnetParser.java")
        pathToPsiRoot.set("com/jsonnetplugin/psi")
        purgeOldFiles.set(true)
    }

    compileJava {
        dependsOn(generateLexer, generateParser)
    }
}


