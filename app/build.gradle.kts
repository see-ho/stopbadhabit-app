kotlin {
    jvmToolchain(18)
}
plugins {
    //id "3on"
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    //id ("dagger.hilt.android.plugin")
    //id "com.google.android.gms.oss-licenses-plugin"
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.seeho.stopbadhabit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.seeho.stopbadhabit"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.0"

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

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")


    // gson
    implementation ("com.google.code.gson:gson:2.9.1")

    // Glide
    val glide_version="4.16.0"
    implementation ("com.github.bumptech.glide:glide:$glide_version")
    annotationProcessor ("com.github.bumptech.glide:compiler:$glide_version")
    kapt ("com.github.bumptech.glide:compiler:$glide_version")

    //Compose
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Coroutines
    val  coroutines_version = "1.6.1"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    // Coroutine Lifecycle Scopes
    val  lifecycle_version = "2.6.2"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha01")

    // Room
    val roomVersion = "2.6.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$roomVersion")

    //navigation component
    val  nav_version = "2.7.3"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Lottie
    val lottieVersion = "6.1.0"
    implementation ("com.airbnb.android:lottie:$lottieVersion")

    //Material Design
    val materialVersion = "1.10.0"
    implementation ("com.google.android.material:material:$materialVersion")

    //Hilt
    implementation ("com.google.dagger:hilt-android:2.46.1")
    kapt ("com.google.dagger:hilt-compiler:2.44")

    //Work
    implementation ("androidx.work:work-runtime-ktx:2.7.1")

    //Activity, Fragment
    implementation ("androidx.fragment:fragment-ktx:1.5.2")
    implementation ("androidx.activity:activity-ktx:1.5.1")
    implementation ("androidx.room:room-ktx:2.4.3")

    //OSS
    implementation ("com.google.android.gms:play-services-oss-licenses:17.0.1")

    //Balloon
    implementation ("com.github.skydoves:balloon:1.4.7")

    //Splash
    implementation ("androidx.core:core-splashscreen:1.0.0")
}

kapt {
    correctErrorTypes = true
}