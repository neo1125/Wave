import com.android.build.gradle.LibraryExtension
import org.gradle.api.tasks.bundling.Jar

plugins {
    id("com.android.library")
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka")
    kotlin("android")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        setProperty("archivesBaseName", "wave-${Versions.libraryVersion}")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
}

dependencies {
    implementation(Kotlin.stdlibVersion)
    implementation(Compose.uiVersion)
    implementation(Compose.materialVersion)
    implementation(Compose.previewVersion)
    implementation(Material.materialVersion)
    testImplementation(Test.junitVersion)
    androidTestImplementation(Test.androidxJunitVersion)
    androidTestImplementation(Test.espressoVersion)
    androidTestImplementation(Test.composeJunitVersion)
    debugImplementation(Test.composeToolingVersion)
}

val sourcesJar = tasks.register<Jar>("sourcesJar") {
    group = "build"
    description = "Assembles Source jar for publishing"
    archiveClassifier.set("sources")
    if (plugins.hasPlugin("com.android.library")) {
        from((project.extensions.getByName("android") as LibraryExtension).sourceSets.named("main").get().java.srcDirs)
    } else {
        from((project.extensions.getByName("sourceSets") as SourceSetContainer).named("main").get().allSource)
    }
}

val dokkaJar = tasks.create<Jar>("dokkaJar") {
    group = "build"
    description = "Assembles Javadoc jar from Dokka API docs"
    archiveClassifier.set("javadoc")
    from(tasks.dokkaJavadoc)
}

tasks.dokkaJavadoc.configure {
    outputDirectory.set(buildDir.resolve("javadoc"))
    dokkaSourceSets {
        configureEach {
            sourceRoot(file("src"))
        }
    }
}

afterEvaluate {
    publishing {

        publications {
            create<MavenPublication>("release") {
                groupId = "io.github.neo1125"
                artifactId = "wave"
                version = Versions.libraryVersion

                from(components["release"])
                //artifact(dokkaJar)
                artifact(sourcesJar)

                pom {

                    name.set("Wave")
                    description.set("Wave Progress View for android compose")
                    url.set("https://github.com/neo1125/Wave")

                    developers {
                        developer {
                            id.set("NEO25")
                            name.set("Yoon HunHee")
                        }
                    }

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }

                    scm {
                        connection.set("scm:git:git://github.com/neo1125/Wave.git")
                        developerConnection.set("scm:git:ssh://github.com/neo1125/Wave.git")
                        url.set("https://github.com/neo1125/Wave/")
                    }
                }
            }
        }

        repositories {
            maven {
                credentials {
                    username = System.getenv("ossrhUsername")
                    password = System.getenv("ossrhPassword")
                }
                val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                url = if (project.hasProperty("snapshots")) snapshotsRepoUrl else releasesRepoUrl
            }
        }
    }

    signing {
        sign(publishing.publications)
    }
}