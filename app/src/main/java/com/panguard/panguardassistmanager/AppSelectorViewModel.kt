package com.panguard.panguardassistmanager

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AppSelectionUiState(
    val selectedItems: MutableMap<String, Boolean> = mutableMapOf()
)

class AppSelectorViewModel(private val repository: SettingsRepository) : ViewModel() {
    private var _uiState: AppSelectionUiState = repository.loadState()
    private val uiState = MutableStateFlow(_uiState)

    fun isSelected(appInfo: AppInfo): Boolean {
        return uiState.value.selectedItems[appInfo.packageName] ?: false
    }

    fun calculateBorderColorForSelected(appInfo: AppInfo): Color {
        return if (isSelected(appInfo))
            Color.Red else
            Color.Transparent
    }

    fun toggleSelected(appInfo: AppInfo): Boolean {

        val newVal = !isSelected(appInfo)
        uiState.update {
            it.selectedItems[appInfo.packageName] = newVal
            saveState()
            return newVal
        }
        return newVal
    }

    private fun saveState() {
        viewModelScope.launch {
            repository.saveState(_uiState)
        }
    }
}