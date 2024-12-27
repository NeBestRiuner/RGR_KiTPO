package work.project.rgr_kitpo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask,Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit
    ){
    LazyColumn(modifier = modifier, state = rememberLazyListState()){
        items(items = list, key = {task -> task.id}){
            task ->
            WellnessTaskItem(taskName = task.label, checked = task.checked, onCheckedChange = {checked -> onCheckedTask(task, checked)}, onClose = {onCloseTask(task)})
        }
    }

}