plugins {
    id("com.android.library")
    id("maven-publish")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode(1)
        versionName = "1.0"
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        renderscriptTargetApi = Versions.compileSdk
        renderscriptSupportModeEnabled = true
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

apply(from = "publish.gradle")
apply(from = "ktlint.gradle")

dependencies {
    /* Android X */
    api(dependencyNotation = "androidx.annotation:annotation:${Versions.annotation}")
    api(dependencyNotation = "androidx.appcompat:appcompat:${Versions.appcompat}")
    api(dependencyNotation = "androidx.biometric:biometric:${Versions.biometricPrompt}")
    api(dependencyNotation = "androidx.browser:browser:${Versions.browser}")
    api(dependencyNotation = "androidx.constraintlayout:constraintlayout:${Versions.constraint}")
    api(dependencyNotation = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}")
    api(dependencyNotation = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}")
    api(dependencyNotation = "androidx.media:media:${Versions.media}")
    api(dependencyNotation = "androidx.multidex:multidex:${Versions.multidex}")
    api(dependencyNotation = "androidx.recyclerview:recyclerview:${Versions.recyclerView}")
    api(dependencyNotation = "com.google.android.material:material:${Versions.material}")

    /* Preferences */
    api(dependencyNotation = "androidx.preference:preference-ktx:${Versions.preferences}")

    /* GLIDE */
    api(dependencyNotation = "com.github.bumptech.glide:glide:${Versions.glide}") {
        exclude(group = "com.android.support")
    }
    api(dependencyNotation = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}") {
        exclude(group = "glide-parent")
    }
    kapt(dependencyNotation = "com.github.bumptech.glide:compiler:${Versions.glide}")

    /* Play Services */
    api(dependencyNotation = "com.google.android.gms:play-services-location:${Versions.location}") {
        exclude(group = "com.android.support")
    }
    api(dependencyNotation = "com.google.android.gms:play-services-maps:${Versions.maps}") {
        exclude(group = "com.android.support")
    }
    api(dependencyNotation = "com.google.android.gms:play-services-vision:${Versions.vision}") {
        exclude(group = "com.android.support")
    }

    implementation(dependencyNotation = "androidx.core:core-ktx:1.3.2")
    implementation(dependencyNotation = "androidx.appcompat:appcompat:1.2.0")

}