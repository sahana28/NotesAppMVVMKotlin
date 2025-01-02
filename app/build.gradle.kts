import com.android.build.gradle.internal.utils.isKotlinKaptPluginApplied

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.androidx.navigation.safe.args)
}

android {
    namespace = "com.example.notesappmvvmkotlin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.notesappmvvmkotlin"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Navigation Components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Room Database
    implementation(libs.androidx.room.runtime)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation(libs.androidx.room.ktx)
    androidTestImplementation(libs.androidx.room.testing)


    // Lifecycle components
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.common.java8)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.ktx)

    // Kotlin components
    implementation(libs.kotlin.stdlib.jdk7)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
}