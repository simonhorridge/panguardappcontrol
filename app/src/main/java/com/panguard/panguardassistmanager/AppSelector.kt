package com.panguard.panguardassistmanager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp

class AppSelector {

    @Composable()
    fun AppList(appList: AppList){
        val apps = appList.getApps()

        Column(modifier = Modifier.fillMaxSize()) {
            for( app in apps) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp)){

                    Text(app.name);
                    Checkbox(checked = false, onCheckedChange = {})

                }


            }
        }
    }
}

class AppList{

    public fun getApps(): MutableList<App> {
        var id = 0;
        var names = arrayOf("App1", "App2", "App3")
        var rtn = mutableListOf<App>()
        for (name in names) {
            rtn.add(App(name = name, id=id++))
        }

        return rtn
    }

}

 data class App(public val name: String,val id: Int){


}