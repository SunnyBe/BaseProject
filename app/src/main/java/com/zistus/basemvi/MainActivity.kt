package com.zistus.basemvi

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zistus.basemvi.di.AppComponent
import com.zistus.basemvi.di.DaggerAppComponent
import com.zistus.core.di.module.AppModuleDependencies
import com.zistus.core.utils.SplitHelper
import dagger.hilt.android.EntryPointAccessors
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var testString: String

    val splitHelper = SplitHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        daggerComponent(this, AppModuleDependencies::class.java)
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testLabel?.text = testString
    }

    private fun daggerComponent(
        activity: Activity,
        kClass: Class<AppModuleDependencies>
    ): AppComponent.Builder {
        return DaggerAppComponent.builder()
            .context(activity)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    kClass
                )
            )
    }
}
