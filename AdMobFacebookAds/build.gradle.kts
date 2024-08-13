plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.compose.ads.admobfacebookads"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishing {
    publications {
        // Creates a Maven publication called "release".
       register<MavenPublication>("release"){
           afterEvaluate {
               from(components["release"])
           }
       }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.material3)

    implementation (libs.play.services.ads)
    implementation (libs.accompanist.drawablepainter)
    implementation(libs.coil.compose)

    implementation(libs.androidx.annotation)
    implementation (libs.audience.network.sdk)
    implementation(libs.androidx.lifecycle.process)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}