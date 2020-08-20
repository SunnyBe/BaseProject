package com.zistus.core.utils

import android.app.Activity
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.tasks.Task

class SplitHelper(private val activity: Activity) {

    private val manager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(activity)
    }

    /**
     * Request to add module. Download module if module has not been downloaded yet.
     * @param moduleName Name of the module to add to module list
     * @return Installation Task<Int> to be able to listen for the progress of the task
     */
    suspend fun addModule(moduleName: String): Task<Int> {
        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()
        return manager.startInstall(request)
    }

    suspend fun installedModules(): Set<String> {
        return manager.installedModules
    }
}