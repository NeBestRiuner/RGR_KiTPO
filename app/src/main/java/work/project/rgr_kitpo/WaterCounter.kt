package work.project.rgr_kitpo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier){

    Column(modifier = modifier.padding(16.dp)) {
        var count by remember{mutableStateOf(0)}
        if(count>0){
            Text(
                text = "You've had $count glasses.",
                modifier = modifier.padding(16.dp)
            )
        }
        Row(Modifier.padding(top = 8.dp)){
            Button(onClick = {count++}
                , enabled = count < 10) {
                Text("Add one")
            }
            Button(
                onClick = {count = 0},
                Modifier.padding(start = 8.dp)
            ){
                Text("Clear water count")
            }
        }
    }
}