plugins {
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.navigationSafeArgs)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvmToolchain(18)
}

android {
    namespace = "com.seeho.stopbadhabit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.seeho.stopbadhabit"
        minSdk = 26
        targetSdk = 34
        versionCode = 3
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_18.toString()
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
}

dependencies {

    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.appcompat)
    implementation (libs.material)
    implementation (libs.androidx.constraintlayout)
    implementation (libs.androidx.legacy.support.v4)
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.junit)
    androidTestImplementation (libs.androidx.espresso.core)


    // gson
    implementation (libs.gson)

    // Glide
    implementation (libs.github.glide)
    annotationProcessor (libs.compiler)
    ksp (libs.github.glide)

    //Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling)


    // Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.runtime.ktx)
    // LiveData
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.lifecycle.viewmodel.compose)

    // Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)

    // optional - Test helpers
    testImplementation(libs.androidx.room.testing)

    //navigation component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Lottie
    implementation (libs.lottie)
    implementation(libs.lottie.compose)

    //Material Design
    implementation (libs.material.v1100)

    //Hilt
    implementation (libs.hilt.android)
    ksp (libs.hilt.compiler)

    //Work
    implementation (libs.androidx.work.runtime.ktx)

    //Activity, Fragment
    implementation (libs.androidx.fragment.ktx)
    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.room.ktx)

    //OSS
    implementation (libs.play.services.oss.licenses)

    //Balloon
    implementation (libs.balloon)
    implementation(libs.balloon.compose)

    //Splash
    implementation (libs.androidx.core.splashscreen)
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}