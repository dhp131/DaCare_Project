plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.prm392.dacare"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.prm392.dacare"
        minSdk = 27
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.legacy.support.v4)
    implementation(libs.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    implementation(libs.lifecycle.viewmodel.ktx.v262)
    implementation(libs.lifecycle.livedata.ktx.v262)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //===New dependencies here===
    implementation(libs.paging.runtime)

    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    implementation(libs.picasso)

}