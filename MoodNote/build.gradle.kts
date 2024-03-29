// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
}

ext {
    appCompatVersion = '1.6.1'
    constraintLayoutVersion = '2.1.4'
    coreTestingVersion = '2.1.0'
    lifecycleVersion = '2.3.1'
    materialVersion = '1.7.0'
    roomVersion = '2.4.3'
    // testing
    junitVersion = '4.13.2'
    espressoVersion = '3.5.0'
    androidxJunitVersion = '1.1.2'
}
