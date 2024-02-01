package com.panguard.panguardassistmanager
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap

class AppSelector {

    @Composable()
    fun AppList(appList: AppListManager) {
        val apps = appList.getInstalledApps().sortedBy { it.appName }

        Column(modifier = Modifier.fillMaxSize()) {

            Box{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    items(apps) { appInfo ->
                        AppListItem(appInfo)
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
    fun AppListItem(appInfo: AppListManager.AppInfo) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            AppIcon(appInfo.icon)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = appInfo.appName)
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(
                checked = false, // Set initial state as needed
                onCheckedChange = { /* Handle checkbox state change */ }
            )

        }
    }

    @Composable
    fun AppIcon(icon: Drawable) {
        val bitmap =icon.toBitmap()

        Image(
            painter = remember { BitmapPainter(bitmap.asImageBitmap()) },
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
    }
}

