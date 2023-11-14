//buildscript {
//    repositories {
//        google()
//    }
//    dependencies {
//        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
//        classpath ("com.google.android.gms:oss-licenses-plugin:0.10.4")
//        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.1")
//    }
//}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "8.1.0" apply false
    id ("com.android.library") version "8.1.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id ("com.google.dagger.hilt.android") version "2.44" apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.5.1" apply false
}

