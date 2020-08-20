package com.zistus.camera.di

import android.content.Context
import com.zistus.camera.CameraActivity
import com.zistus.core.di.module.CameraModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [CameraModuleDependencies::class]
)
interface CameraComponent {

    fun inject(activity: CameraActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(loginModuleDependencies: CameraModuleDependencies): Builder
        fun build(): CameraComponent
    }
}