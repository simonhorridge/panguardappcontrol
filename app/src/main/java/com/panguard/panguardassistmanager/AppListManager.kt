package com.panguard.panguardassistmanager

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

class AppListManager(private val context: Context) {

    fun getInstalledApps(): List<AppInfo> {
        val packageManager: PackageManager = context.packageManager

        // Create an Intent with MAIN action and LAUNCHER category to query the launcher for the list of apps
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)


        // Retrieve a list of ResolveInfo objects that match the intent
        val resolveInfoList = packageManager.queryIntentActivities(mainIntent, 0)


        // Create a list of AppInfo objects from the ResolveInfo list
        return resolveInfoList.map { resolveInfo ->
            val appName = resolveInfo.loadLabel(packageManager).toString()
            val packageName = resolveInfo.activityInfo.packageName
            val icon = resolveInfo.loadIcon(packageManager)
            val iconUrl = "android.resource://${packageName}/${resolveInfo.activityInfo.icon}"
            AppInfo(appName, packageName, icon, iconUrl)
        }
    }
}

