package com.zistus.camera

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zistus.camera.di.DaggerCameraComponent
import com.zistus.core.di.module.CameraModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class CameraActivity : AppCompatActivity() {

    @Inject
    lateinit var testText: String

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

        Toast.makeText(this, testText, Toast.LENGTH_LONG).show()
    }

}