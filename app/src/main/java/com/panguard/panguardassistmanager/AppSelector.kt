package com.panguard.panguardassistmanager

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap


@Composable
fun AppList(
    appList: AppListManager,
    usageStats: Map<String, AggregatedUsageStats>,
    context: Context,

    ) {
    val settingsRepository = SettingsRepository(context)
    val vm = AppSelectorViewModel(settingsRepository)

    val apps = appList.getInstalledApps().sortedBy { it.appName }
    Column(modifier = Modifier.fillMaxSize()) {

        Box {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                val stats = usageStats.values.toList()

                items(stats) { appInfo ->
                    UsageStats(appInfo)
                }

                items(apps) { appInfo ->
                    AppListItem(
                        appInfo,
                        usageStats[appInfo.packageName],
                        vm
                    )
                }
            }
        }
    }
}

@Composable
fun AppListItem(
    appInfo: AppInfo,
    aggregatedUsageStats: AggregatedUsageStats?,
    vm: AppSelectorViewModel
) {

    var isSelected by remember { mutableStateOf(vm.isSelected(appInfo)) }
    // Calculate border color based on isSelected
    val borderColor = if (isSelected) {
        vm.calculateBorderColorForSelected(appInfo)
    } else {
        Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .border(width = 2.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
            .clickable(
                onClick = {
                    isSelected = vm.toggleSelected(appInfo)
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )

    ) {

        AppIcon(appInfo.icon)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = appInfo.appName, modifier = Modifier.align(CenterVertically))

        Spacer(Modifier.weight(1f))

//        Checkbox(
//            modifier = Modifier.align(CenterVertically),
//            checked = isSelected,
//            onCheckedChange = {
//                isSelected = vm.toggleSelected(appInfo)
//            }
//        )
    }
    Row {
        if (aggregatedUsageStats != null) {
            UsageStats(aggregatedUsageStats)
        }
    }
}

@Composable
fun UsageStats(stat: AggregatedUsageStats) {
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
