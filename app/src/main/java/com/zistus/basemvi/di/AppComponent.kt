package com.zistus.basemvi.di

import android.content.Context
import com.zistus.basemvi.MainActivity
import com.zistus.basemvi.home.ui.HomeActivity
import com.zistus.basemvi.home.ui.HomeFragment
import com.zistus.core.di.module.AppModuleDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppModuleDependencies::class],
    modules = [AppModule::class, FactoryModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: HomeActivity)
    fun inject(homeFragment: HomeFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(appModuleDependencies: AppModuleDependencies): Builder
        fun build(): AppComponent
    }
}