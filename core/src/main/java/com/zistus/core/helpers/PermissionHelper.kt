package com.zistus.core.helpers

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment

class PermissionHelper {
    companion object {
        private fun useRunTimePermissions(): Boolean {
            return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
        }

        fun hasPermission(activity: Activity, permission: String?): Boolean {
            return if (useRunTimePermissions() && permission != null)
                activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            else false
        }

        fun requestPermissions(activity: Activity, permission: Array<String>, requestCode: Int) {
            if (useRunTimePermissions() && permission.isNotEmpty()) {
                activity.requestPermissions(permission, requestCode)
            }
        }

        fun requestPermissions(fragment: Fragment, permission: Array<String?>?, requestCode: Int) {
            if (useRunTimePermissions() && permission != null) {
                fragment.requestPermissions(permission, requestCode)
            }
        }

        fun shouldShowRational(activity: Activity, permission: String?): Boolean {
            return if (useRunTimePermissions() && permission != null) {
                activity.shouldShowRequestPermissionRationale(permission)
            } else false
        }

        fun shouldAskForPermission(activity: Activity?, permission: String?): Boolean {
            return if (useRunTimePermissions() && permission != null) {
                !hasPermission(activity!!, permission) &&
                        (!hasAskedForPermission(activity, permission) ||
                                shouldShowRational(activity, permission))
            } else false
        }

        private fun hasAskedForPermission(activity: Activity?, permission: String?): Boolean {
            return PreferenceManager
                .getDefaultSharedPreferences(activity)
                .getBoolean(permission, false)
        }

    }
}