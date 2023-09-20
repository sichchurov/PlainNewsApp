buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.2")
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}

