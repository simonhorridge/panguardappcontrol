package com.panguard.panguardassistmanager

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SettingsRepository(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "app_selector_prefs",
        Context.MODE_PRIVATE
    )

    private val gson = Gson()
    fun saveState(uiState: AppSelectionUiState) {
        val stateJson = gson.toJson(uiState)
        sharedPreferences.edit().putString("ui_state", stateJson).apply()
    }

    fun loadState(): AppSelectionUiState {
        val savedState = sharedPreferences.getString("ui_state", null)
        return savedState?.let {
            gson.fromJson(it, AppSelectionUiState::class.java)
        } ?: AppSelectionUiState()
    }
}
