plugins {
    `maven-publish`
    signing
}

// --------------- Sources JAR ---------------

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    // Include standard source directories (works for both JVM and Android)
    from(project.layout.projectDirectory.dir("src/main/kotlin"))
    from(project.layout.projectDirectory.dir("src/main/java"))
    // Only add if directory exists
    duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.EXCLUDE
}

// --------------- Dokka / Javadoc JAR ---------------

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    // Empty JAR for now — Dokka can be added later
}

// --------------- Publication ---------------

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                artifactId = project.name

                // Include sources and javadoc
                artifact(sourcesJar.get())
                artifact(javadocJar.get())

                // Include the main component
                val androidComponent = project.components.findByName("release")
                if (androidComponent != null) {
                    from(androidComponent)
                } else {
                    from(project.components.getByName("java"))
                }

                // POM metadata
                pom {
                    name.set("FlexiScale — ${project.name.removePrefix("flexiscale-")}")
                    description.set(
                        when (project.name) {
                            "flexiscale-runtime" -> "Core responsive UI scaling engine for Android."
                            "flexiscale-tokens" -> "Design system tokens for FlexiScale."
                            "flexiscale-compose" -> "Jetpack Compose integration for FlexiScale."
                            "flexiscale-resources" -> "Pre-generated XML dimension resources for FlexiScale."
                            else -> "FlexiScale module"
                        }
                    )
                    url.set("https://github.com/hardik-vaghani/flexiscale")

                    licenses {
                        license {
                            name.set("Apache License, Version 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    developers {
                        developer {
                            id.set("hardik-vaghani")
                            name.set("Hardik Vaghani")
                            email.set("hardik.devtools@gmail.com")
                        }
                    }

                    scm {
                        connection.set("scm:git:github.com/hardik-vaghani/flexiscale.git")
                        developerConnection.set("scm:git:ssh://github.com/hardik-vaghani/flexiscale.git")
                        url.set("https://github.com/hardik-vaghani/flexiscale/tree/main")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("sonatypeUser") as? String ?: ""
                password = findProperty("sonatypePassword") as? String ?: ""
            }
        }
    }
}

// --------------- Signing ---------------

afterEvaluate {
    signing {
        setRequired {
            gradle.taskGraph.hasTask(":${project.name}:publish") ||
                gradle.taskGraph.hasTask(":${project.name}:publishReleasePublicationToSonatypeRepository")
        }

        val signingKey: String? by project
        val signingPassword: String? by project

        if (signingKey != null) {
            useInMemoryPgpKeys(signingKey, signingPassword ?: "")
            sign(publishing.publications["release"])
        } else {
            val signingKeyId: String? by project
            val secretKeyRingFile: String? by project
            if (signingKeyId != null && secretKeyRingFile != null) {
                sign(publishing.publications["release"])
            }
        }
    }
}
