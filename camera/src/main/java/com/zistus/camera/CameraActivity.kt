package com.zistus.camera

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zistus.camera.di.DaggerCameraComponent
import com.zistus.core.di.module.CameraModuleDependencies
import dagger.hilt.android.EntryPointAccessors

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val cameraComponent = DaggerCameraComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    CameraModuleDependencies::class.java
                )
            )
        cameraComponent.build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }
}