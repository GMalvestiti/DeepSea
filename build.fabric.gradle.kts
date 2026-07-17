plugins {
    id("build.common")
    id("dev.kikugie.loom-back-compat")
    id("com.gradleup.shadow") version "9.5.1"
}

// DO NOT set group = ...!
version = "${property("mod.version")}+${sc.current.version}"
base.archivesName = "${property("mod.id") as String}-fabric"

sourceSets.main {
    resources.exclude("**/.cache")
}

val shadowGroup: String = property("mod.group") as String

configurations {
    shadow
}

dependencies {
    minecraft("com.mojang:minecraft:${sc.current.version}")
    loomx.applyMojangMappings()

    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")

    implementation("com.github.ben-manes.caffeine:caffeine:${property("deps.caffeine")}")
    shadow("com.github.ben-manes.caffeine:caffeine:${property("deps.caffeine")}")
}

loom {
    fabricModJsonPath = rootProject.file("src/main/resources/fabric.mod.json") // Useful for interface injection
    accessWidenerPath = sc.process(
        rootProject.file("src/main/resources/${property("mod.id")}.ct"),
        "build/${property("mod.id")}.ct"
    )

    decompilerOptions.named("vineflower") {
        options.put("mark-corresponding-synthetics", "1") // Adds names to lambdas - useful for mixins
    }

    runConfigs.all {
        preferGradleTask = true
        generateRunConfig = true
        runDirectory = rootProject.file("run") // Shares the run directory between versions
        jvmArguments.add("-Dmixin.debug.export=true") // Exports transformed classes for debugging
    }
}

fabricApi {
    configureDataGeneration {
        client = true
    }
}

val requiredJava: JavaVersion = when {
    sc.current.parsed >= "26.1" -> JavaVersion.VERSION_25
    sc.current.parsed >= "1.20.5" -> JavaVersion.VERSION_21
    sc.current.parsed >= "1.18" -> JavaVersion.VERSION_17
    sc.current.parsed >= "1.17" -> JavaVersion.VERSION_16
    else -> JavaVersion.VERSION_1_8
}

java {
    withSourcesJar()
    targetCompatibility = requiredJava
    sourceCompatibility = requiredJava

    toolchain {
        vendor = JvmVendorSpec.ADOPTIUM
        languageVersion = JavaLanguageVersion.of(requiredJava.majorVersion)
    }
}

tasks {
    if (sc.current.parsed < "26.1") {
        named<net.fabricmc.loom.task.RemapJarTask>("remapJar") {
            dependsOn(shadowJar)
            inputFile.set(shadowJar.flatMap { it.archiveFile })
        }
    } else {
        jar {
            enabled = false
        }

        assemble {
            dependsOn(shadowJar)
        }
    }

    shadowJar {
        if (sc.current.parsed < "26.1") {
            archiveClassifier.set("shadow")
        } else {
            archiveClassifier.set("")
        }

        configurations = listOf(project.configurations.shadow.get())

        relocate("com.github.benmanes.caffeine", "${shadowGroup}.shadow.caffeine")
        relocate("com.google.errorprone", "${shadowGroup}.shadow.errorprone")
        relocate("org.jspecify", "${shadowGroup}.shadow.jspecify")

        mergeServiceFiles()
    }

    processResources {
        fun MutableMap<String, String>.register(key: String, property: String) {
            val value: String = sc.properties[property]
            inputs.property(key, value)
            set(key, value)
        }

        val props = buildMap {
            register("id", "mod.id")
            register("name", "mod.name")
            register("version", "mod.version")
            register("minecraft", "mod.mc_compat")
            register("description", "mod.description")
            register("author", "mod.author")
            register("contact_homepage", "mod.contact_homepage")
            register("contact_sources", "mod.contact_sources")
            register("contact_issues", "mod.contact_issues")
            register("license", "mod.license")
            register("fabric_loader", "deps.fabric_loader")
            register("fabric_api", "deps.fabric_api")
        }

        filesMatching("fabric.mod.json") { expand(props) }

        val mixinJava = "JAVA_${requiredJava.majorVersion}"
        filesMatching("*.mixins.json") { expand("java" to mixinJava) }

        exclude("META-INF/neoforge.mods.toml")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        description = "Builds mod jars and copies results to `build/libs/{mod version}/`"

        dependsOn(build)

        inputs.property("version", project.property("mod.version"))

        from(
            loomx.modJar.flatMap { it.archiveFile }
        )

        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
    }
}
