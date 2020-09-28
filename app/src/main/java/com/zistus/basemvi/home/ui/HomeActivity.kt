package com.zistus.basemvi.home.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zistus.basemvi.R
import com.zistus.basemvi.utils.DataStateListener
import com.zistus.core.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(R.layout.activity_home), DataStateListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMainFragment()
    }

    override fun onDataStateChange(dataState: DataState<*>?) {
        dataState?.loading?.let { loading ->
            if (loading) {
                Toast.makeText(this, "loading ...", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showMainFragment() {
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_home_container, HomeFragment(), "HomeFragment")
                .commit()
        }
    }

//    private fun daggerComponent(
//        activity: Activity,
//        kClass: Class<AppModuleDependencies>
//    ): AppComponent.Builder {
//        return DaggerAppComponent.builder()
//            .context(activity)
//            .appDependencies(
//                EntryPointAccessors.fromApplication(
//                    applicationContext,
//                    kClass
//                )
//            )
//    }
}