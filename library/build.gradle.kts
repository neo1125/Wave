plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk

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