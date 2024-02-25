package com.panguard.panguardassistmanager

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap

class AppSelector {

    @Composable()
    fun AppList(appList: AppListManager, usageStats: Map<String, AggregatedUsageStats>) {






        val apps = appList.getInstalledApps().sortedBy { it.appName }

        Column(modifier = Modifier.fillMaxSize()) {

            Box {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    val stats = usageStats.values.toList();

                    items(stats) { appInfo ->
                        UsageStats(appInfo)
                    }

                    items(apps) { appInfo ->
                        AppListItem(appInfo, usageStats[appInfo.appName])
                    }
                }
//
//                VerticalScrollbar(
//                    modifier =Modifier. fillMaxHeight().align( Alignment.CenterEnd),
//                    adapter = rememberScrollbarAdapter(rememberScrollState()),
//                )
            }


        }
    }

    @Composable
    fun AppListItem(appInfo: AppListManager.AppInfo, aggregatedUsageStats: AggregatedUsageStats?) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            AppIcon(appInfo.icon)
            Spacer(modifier = Modifier.width(16.dp))
            //Text(text = appInfo.appName, modifier=Modifier.align(CenterVertically))
            Text(text = appInfo.packageName, modifier=Modifier.align(CenterVertically))



            Spacer(Modifier.weight(1f))
            Checkbox(
                modifier=Modifier.align(CenterVertically),
                checked = false, // Set initial state as needed
                onCheckedChange = { /* Handle checkbox state change */ }
            )
        }
        Row(){
            if (aggregatedUsageStats != null) {
                UsageStats( aggregatedUsageStats)
            }
        }

    }

    @Composable
    fun UsageStats(stat: AggregatedUsageStats){
        Text(text = stat.packageName)
        Text(text = stat.totalTimeInForeground.toString())
    }

    @Composable
    fun AppIcon(icon: Drawable) {
        val bitmap = icon.toBitmap()

        Image(
            painter = remember { BitmapPainter(bitmap.asImageBitmap()) },
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
        )
    }
}

