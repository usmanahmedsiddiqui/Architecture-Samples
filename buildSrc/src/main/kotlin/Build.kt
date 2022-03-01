object Build {
    private const val androidBuildToolsVersion = "7.1.0"

    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.kotlinVersion}"
    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${Navigation.navigationVersion}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"
}