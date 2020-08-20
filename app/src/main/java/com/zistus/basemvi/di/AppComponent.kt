package com.zistus.basemvi.di

import android.content.Context
import com.zistus.basemvi.MainActivity
import com.zistus.core.di.module.AppModuleDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppModuleDependencies::class], modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(appModuleDependencies: AppModuleDependencies): Builder
        fun build(): AppComponent
    }
}