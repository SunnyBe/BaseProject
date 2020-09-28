package dependencies

import Versions

object TestDependencies {

    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    val jupiter_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit_jupiter_version}"
    val jupiter_params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit_jupiter_version}"
    val jupiter_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit_jupiter_version}"
    val junit4 = "junit:junit:${Versions.junit_4_version}"
    val junit_extension = "androidx.test.ext:junit:${Versions.junit_ext_version}"
    val mockk = "io.mockk:mockk:${Versions.mockk_version}"
    val dagger_hilt = "com.google.dagger:hilt-android-testing:2.28-alpha"
    val room = "androidx.room:room-testing:${Versions.room}"
}