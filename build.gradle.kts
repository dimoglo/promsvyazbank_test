buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.realmKotlin) apply false
    alias(libs.plugins.daggerHilt) apply false
    alias(libs.plugins.navigstionSafeArgs) apply false
}