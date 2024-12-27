package work.project.rgr_kitpo

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun WellnessScreen(modifier: Modifier = Modifier,
                   wellnessViewModel: WellnessViewModel = viewModel()
){
    Column(modifier = modifier) {
        WaterCounter(modifier)

        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = {task, checked ->
                wellnessViewModel.changeTaskChecked(task,checked)
            },
            onCloseTask = {task -> wellnessViewModel.remove(task)}
        )
    }
}



@Preview
@Composable
fun PreviewWellnessScreen(){
    WaterCounter()
}