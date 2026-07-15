plugins {
    id("idea")
    id("net.neoforged.moddev") version "2.0.141"
    id("neoforge-mutex")
    id("com.gradleup.shadow") version "9.5.1"
}

version = "${property("mod.version")}+${sc.current.version}"
base.archivesName = "${property("mod.id") as String}-neoforge"

val shadowGroup: String = property("mod.group") as String

configurations {
    shadow
}

sourceSets.main {
    resources.srcDir("src/main/generated/resources")
    resources.srcDir("src/main/resources")
}

repositories {
    /**
     * Restricts dependency search of the given [groups] to the [maven URL][url], improving the setup speed.
     */
    fun strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
        forRepository { maven(url) { name = alias } }
        filter { groups.forEach(::includeGroup) }
    }
    strictMaven("https://www.cursemaven.com", "CurseForge", "curse.maven")
    strictMaven("https://api.modrinth.com/maven", "Modrinth", "maven.modrinth")
}

dependencies {
    shadow("com.github.ben-manes.caffeine:caffeine:${property("deps.caffeine")}")
    implementation("com.github.ben-manes.caffeine:caffeine:${property("deps.caffeine")}")
}

neoForge {
    version = property("deps.neoforge_loader") as String

    mods {
        // Define mod <-> source bindings
        // These are used to tell the game which sources are for which mod
        // Multi mod projects should define one per mod
        register("${property("mod.id")}") {
            sourceSet(sourceSets.main.get())
        }
    }

    // Default run configurations.
    // These can be tweaked, removed, or duplicated as needed.
    runs {
        register("client") {
            gameDirectory = file("../../run/")
            client()
            // List of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", property("mod.id") as String)
        }

        register("server") {
            gameDirectory = file("../../run/")
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", property("mod.id") as String)
        }

        // This run config launches GameTestServer and runs all registered gametests, then exits.
        // By default, the server will crash when no gametests are provided.
        // The gametest system is also enabled by default for other run configs under the /test command.
        register("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", property("mod.id") as String)
        }

        if (sc.current.parsed >= "1.21.4") {
            register("clientData") {
                clientData()

                gameDirectory = file("../../run")

                programArguments.addAll(
                    "--mod", property("mod.id") as String,
                    "--all",
                    "--output", file("src/main/generated/resources").absolutePath,
                    "--existing", file("src/main/resources").absolutePath
                )
            }
        } else {
            register("data") {
                data()

                gameDirectory = file("../../run")

                programArguments.addAll(
                    "--mod", property("mod.id") as String,
                    "--all",
                    "--output", file("src/main/generated/resources").absolutePath,
                    "--existing", file("src/main/resources").absolutePath
                )
            }
        }

        // Applies to all the run configs above
        configureEach {
            // Recommended logging data for a userdev environment
            // The markers can be added/remove as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            systemProperty("forge.logging.markers", "REGISTRIES")

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }
}

val requiredJava = when {
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
    jar {
        enabled = false
    }

    assemble {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveClassifier.set("")

        configurations = listOf(project.configurations.shadow.get())

        relocate("com.github.benmanes.caffeine", "${shadowGroup}.shaded.caffeine")
        relocate("com.google.errorprone", "${shadowGroup}.shaded.errorprone")
        relocate("org.jspecify", "${shadowGroup}.shaded.jspecify")

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
            register("neoforge_loader", "deps.neoforge_loader")
        }

        filesMatching("META-INF/neoforge.mods.toml") { expand(props) }

        val mixinJava = "JAVA_${requiredJava.majorVersion}"
        filesMatching("*.mixins.json") { expand("java" to mixinJava) }

        exclude("fabric.mod.json", "*.ct", "*.classtweaker")
    }

    named("createMinecraftArtifacts") {
        dependsOn("stonecutterGenerate")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        description = "Builds mod jars and copies results to `build/libs/{mod version}/`"

        dependsOn(build)

        inputs.property("version", project.property("mod.version"))

        from(
            jar.flatMap { it.archiveFile }
        )

        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
    }
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
