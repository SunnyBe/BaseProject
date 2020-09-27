package com.zistus.basemvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zistus.basemvi.home.ui.HomeFragment
import com.zistus.core.utils.SplitHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var testString: String

    val splitHelper = SplitHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
//        daggerComponent(this, AppModuleDependencies::class.java)
//            .build()
//            .inject(this)
        super.onCreate(savedInstanceState)
        testLabel?.text = testString
        showMainFragment()
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

    private fun showMainFragment(){
        if(supportFragmentManager.fragments.size == 0){
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_home_container,
                    HomeFragment(),
                    "HomeFragment"
                )
                .commit()
        }
    }
}
