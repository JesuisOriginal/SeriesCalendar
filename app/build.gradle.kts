plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    kotlin("android")
}

android {
    compileSdkVersion = 31.toString()
    buildToolsVersion = "30.0.3"


    defaultConfig {
        applicationId = "com.milenialsatwork.seriescalendar"
        minSdk = 30
        targetSdk = 31
        versionCode = 1
        versionName ="1.0.0"

        // testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true",
                    "room.expandProjection" to "true"
                )
            }
        }
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val roomVersion = "2.4.2"

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")

    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")
    implementation("androidx.cardview:cardview:1.0.0")



    // ROOM START
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // material
    implementation("com.google.android.material:material:1.5.0")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")

    implementation("androidx.recyclerview:recyclerview:1.3.0-alpha02")

    //tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

}

