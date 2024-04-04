package com.panguard.panguardassistmanager

import android.app.usage.UsageStatsManager
import android.content.Context

class AppUsageStatisticsHelper {
     fun getAppUsageStats(context: Context): Map<String, AggregatedUsageStats> {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val startTime = endTime - (24 * 60 * 60 * 1000 * 7) // 7 Days ago

        val usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime)

        // Aggregate usage stats by package name
        val aggregatedStats = mutableMapOf<String, AggregatedUsageStats>()
        for (usageStats in usageStatsList) {
            val existingStats = aggregatedStats[usageStats.packageName]
            if (existingStats == null) {
                aggregatedStats[usageStats.packageName] = AggregatedUsageStats(usageStats.packageName, usageStats.totalTimeInForeground)
            } else {
                existingStats.totalTimeInForeground += usageStats.totalTimeInForeground
            }
        }

        return aggregatedStats
    }
}
data class AggregatedUsageStats(
    val packageName: String,
    var totalTimeInForeground: Long
)

