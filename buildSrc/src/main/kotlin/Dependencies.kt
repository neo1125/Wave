object Versions {
    const val kotlinVersion = "1.5.21"
    const val composeVersion = "1.0.1"

    const val compileSdk = 31
    const val minSdk = 23
    const val targetSdk = 31

    const val libraryVersion = "1.0.0"
}

object Kotlin {
    const val stdlibVersion = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
}

object Compose {
    const val uiVersion = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val materialVersion = "androidx.compose.material:material:${Versions.composeVersion}"
    const val previewVersion = "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val ActivityVersion = "androidx.activity:activity-compose:1.3.1"
}

object AndroidX {
    const val coreKtxVersion = "androidx.core:core-ktx:1.6.0"
    const val appcompatVersion = "androidx.appcompat:appcompat:1.3.1"
    const val lifecycleKtxVersion = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
}

object Material {
    const val materialVersion = "com.google.android.material:material:1.4.0"
}

object Test {
    const val junitVersion = "junit:junit:4.13.2"
    const val androidxJunitVersion = "androidx.test.ext:junit:1.1.3"
    const val espressoVersion = "androidx.test.espresso:espresso-core:3.4.0"
    const val composeJunitVersion = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
    const val composeToolingVersion = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
}
