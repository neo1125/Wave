buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.4.20")
        classpath("org.jetbrains.dokka:dokka-core:1.4.20")
    }
}

task<Delete>( "clean") {
    delete(rootProject.buildDir)
}