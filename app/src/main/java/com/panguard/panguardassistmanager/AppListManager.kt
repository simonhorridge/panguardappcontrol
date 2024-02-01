package com.panguard.panguardassistmanager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

class AppListManager(private val context: Context) {

    fun getInstalledApps(): Array<AppInfo> {
        val packageManager: PackageManager = context.packageManager

        // Get a list of all installed applications
        val installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

        // Convert ApplicationInfo objects to custom AppInfo objects
        return installedApps.map { AppInfo(it.loadLabel(packageManager).toString(), it.packageName) }
            .toTypedArray()
    }

    data class AppInfo(val appName: String, val packageName: String)
}
