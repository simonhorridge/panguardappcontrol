package com.panguard.panguardassistmanager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.content.Context
class AppSelector {

    @Composable()
    fun AppList(appList: AppListManager){
        val apps = appList.getInstalledApps()

        Column(modifier = Modifier.fillMaxSize()) {
            for( app in apps) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp)){

                    Text(app.appName);
                    Checkbox(checked = false, onCheckedChange = {})
                }
            }
        }
    }
}

