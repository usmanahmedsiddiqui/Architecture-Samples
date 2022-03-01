plugins {
    id(Plugin.androidApplication)
    kotlin(KotlinPlugin.android)
    id(Plugin.navigation)
    kotlin(KotlinPlugin.kapt)
    id(Plugin.hilt)
    kotlin(KotlinPlugin.serialization) version Kotlin.kotlinVersion
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }

        kotlinOptions.freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.OptIn"
        )
    }
}

dependencies {

    implementation(project(":core"))

    //Android Views
    implementation(AndroidView.swipeRefreshLayout)
    implementation(AndroidView.constraintLayout)

    //Android AppCompat
    implementation(AndroidX.appCompat)

    //Android Ktx
    implementation(AndroidX.fragmentKtx)
    implementation(AndroidX.coreKtx)

    //Kotlin
    implementation(AndroidX.serializationJson)

    //Android Navigation
    implementation(Navigation.navigationFragmentKtx)
    implementation(Navigation.navigationUiKtx)

    //Android Lifecycle
    implementation(AndroidLifecycle.viewModeLifecycle)
    implementation(AndroidLifecycle.liveDataLifecycle)
    implementation(AndroidLifecycle.ktxLifecycle)
    implementation(AndroidLifecycle.java8Lifecycle)

    //Google Materials
    implementation(AndroidView.material)

    //Hilt
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltNavigation)
    kapt(Hilt.hiltCompiler)

    //Kotlin Coroutines
    implementation(Coroutine.coroutine)

    //Coil
    implementation(Coil.coil)


    //Retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.squareup)
    implementation(Retrofit.okttpLoggingInterceptor)
    implementation(Retrofit.okhttp3)

}