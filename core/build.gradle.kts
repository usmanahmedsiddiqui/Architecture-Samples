plugins {
    id(Plugin.androidLibrary)
    kotlin(KotlinPlugin.android)
    kotlin(KotlinPlugin.kapt)
    kotlin(KotlinPlugin.serialization) version Kotlin.kotlinVersion
    id(Plugin.hilt)
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    //Kotlin
    implementation(AndroidX.serializationJson)

    //Hilt
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltNavigation)
    kapt(Hilt.hiltCompiler)

    //Kotlin Coroutines
    implementation(Coroutine.coroutine)

    //Retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.squareup)
    implementation(Retrofit.okttpLoggingInterceptor)
    implementation(Retrofit.okhttp3)

    //Room
    implementation(Room.roomRunTime)
    implementation(Room.roomKtx)
    kapt(Room.roomCompiler)

    //Testing
    testImplementation(Testing.junit4)
    testImplementation(Testing.testRunner)
    testImplementation(Testing.androidArchCore)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.truth)
    testImplementation(Testing.turbine)
}