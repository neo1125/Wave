plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.neo.wave.sample"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    implementation(project(":library"))

    implementation(Kotlin.stdlibVersion)
    implementation(AndroidX.coreKtxVersion)
    implementation(AndroidX.appcompatVersion)
    implementation(AndroidX.lifecycleKtxVersion)

    implementation(Compose.uiVersion)
    implementation(Compose.materialVersion)
    implementation(Compose.previewVersion)
    implementation(Compose.ActivityVersion)

    implementation(Material.materialVersion)

    testImplementation(Test.junitVersion)
    androidTestImplementation(Test.androidxJunitVersion)
    androidTestImplementation(Test.espressoVersion)
    androidTestImplementation(Test.composeJunitVersion)
    debugImplementation(Test.composeToolingVersion)
}