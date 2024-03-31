plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.realmKotlin)
    alias(libs.plugins.navigstionSafeArgs)
    alias(libs.plugins.daggerHilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.promsvyazbanktest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.promsvyazbanktest"
        minSdk = 24
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

//  Realm
    implementation(libs.realm.kotlin)

//  Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

//  Retrofit
    implementation(libs.retrofit.base)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

//  Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

//  Coroutines
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.core)

//  LifeCycle
    implementation(libs.viewmodel)
    implementation(libs.viewmodel.savedstate)
    implementation(libs.livedata)

//  Gson
    implementation(libs.gson)

//  Utils
    debugImplementation(libs.timber)
}

kapt {
    correctErrorTypes = true
}