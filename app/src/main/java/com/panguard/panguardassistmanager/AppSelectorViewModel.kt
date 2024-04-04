package com.panguard.panguardassistmanager

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class AppSelectionUiState(
    val selectedItems: MutableMap<String, Boolean> = mutableMapOf()
)

class AppSelectorViewModel : ViewModel() {
    private val uiState = MutableStateFlow(AppSelectionUiState())

    fun isSelected(appInfo: AppInfo): Boolean {
        return uiState.value.selectedItems[appInfo.packageName] ?: false
    }

    fun toggleSelected(appInfo: AppInfo): Boolean {

        val newVal = !isSelected(appInfo)
        uiState.update {
            it.selectedItems[appInfo.packageName] = newVal
            return newVal
        }
        return newVal
    }
}