package work.project.rgr_kitpo

import MyFloat
import MyInt
import MyString
import Point
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun WellnessScreen(modifier: Modifier = Modifier,
                   androidListViewModel: AndroidListViewModel = viewModel()
){
    Column(modifier =  modifier
        .padding(16.dp)) {
        LazyColumn(modifier = modifier, state = rememberLazyListState()){
            item{

                var selectedItem = rememberSaveable { mutableStateOf("Int") }
                var addedValue = rememberSaveable { mutableStateOf("") }
                var idx = rememberSaveable { mutableStateOf("") }


                Column(modifier = modifier.padding(16.dp)) {

                    MyDropdownMenu(selectedItem,androidListViewModel)
                    NumTextFieldWithSaveableState(addedValue)
                    Button(onClick = {
                        if(selectedItem.value.equals("Int")){
                            var num = addedValue.value.toInt()
                            androidListViewModel.addEnd(MyInt(num))
                        }
                        if(selectedItem.value.equals("Float")){
                            var num = addedValue.value.toFloat()
                            androidListViewModel.addEnd(MyFloat(num))
                        }
                        if(selectedItem.value.equals("String")){
                            var num = addedValue.value
                            androidListViewModel.addEnd(MyString(num))
                        }
                        if(selectedItem.value.equals("Point")){
                            var num = addedValue.value
                            androidListViewModel.addEnd(Point().parseValue(num))
                        }
                    }) {
                        Text(text = "Добавить в конец")
                    }
                    IdxTextFieldWithSaveableState(idx)
                    Row(){
                        Button(onClick = {
                            if(selectedItem.value.equals("Int")){
                                var num = addedValue.value.toInt()
                                var id = idx.value.toInt()
                                androidListViewModel.addIndex(MyInt(num),id)
                            }
                            if(selectedItem.value.equals("Float")){
                                var num = addedValue.value.toFloat()
                                var id = idx.value.toInt()
                                androidListViewModel.addIndex(MyFloat(num),id)
                            }
                            if(selectedItem.value.equals("String")){
                                var num = addedValue.value
                                var id = idx.value.toInt()
                                androidListViewModel.addIndex(MyString(num),id)
                            }
                            if(selectedItem.value.equals("Point")){
                                var num = addedValue.value
                                var id = idx.value.toInt()
                                androidListViewModel.addIndex(Point().parseValue(num),id)
                            }
                        }){
                            Text(text = "Добавить по индексу")
                        }
                        Button(onClick = {
                            androidListViewModel.remove(idx.value.toInt())
                        }){
                            Text(text = "Удалить значение по индексу")
                        }
                    }
                    Button(onClick = {
                        if(selectedItem.value.equals("Int")) {
                            androidListViewModel.changeIndex(MyInt(addedValue.value.toInt()),idx.value.toInt())
                        }
                        if(selectedItem.value.equals("Float")) {
                            androidListViewModel.changeIndex(MyFloat(addedValue.value.toFloat()),idx.value.toInt())
                        }
                        if(selectedItem.value.equals("String")) {
                            androidListViewModel.changeIndex(MyString(addedValue.value),idx.value.toInt())
                        }
                        if(selectedItem.value.equals("Point")) {
                            androidListViewModel.changeIndex(Point().parseValue(addedValue.value),idx.value.toInt())
                        }

                    }){
                        Text(text = "Заменить значение по индексу")
                    }
                    Button(onClick = {
                        androidListViewModel.sortMyLinkList(selectedItem.value)
                    }){
                        Text(text = "Отсортировать список")
                    }

                }
            }
            items(items = androidListViewModel.items){ item ->
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu(selectedItem: MutableState<String>, androidListViewModel: AndroidListViewModel) {
    // Состояние для управления раскрытием меню
    var expanded by rememberSaveable { mutableStateOf(false) }

    // Список опций для выпадающего меню
    val items = listOf("Int", "Float", "String", "Point")

    Column {
        // Текстовое поле для отображения текущего выбранного элемента
        TextField(
            readOnly = true,
            value = TextFieldValue(selectedItem.value),
            onValueChange = {androidListViewModel.changeList()},
            label = { Text("Select Type") },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // Выпадающее меню
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        androidListViewModel.changeList()
                        selectedItem.value = item
                        expanded = false
                    }
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NumTextFieldWithSaveableState(str: MutableState<String>) {

    Column(modifier = Modifier.padding(16.dp)) {
        // Отображение текущего текста
        Text(text = "Поле ввода для значений:", style = MaterialTheme.typography.bodyLarge)
        // Текстовое поле
        TextField(
            value = str.value,
            onValueChange = { str.value = it },
            label = { Text("Введите текст") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdxTextFieldWithSaveableState(idx: MutableState<String>) {

    Column(modifier = Modifier.padding(16.dp)) {
        // Отображение текущего текста
        Text(text = "Поле ввода для индексов:", style = MaterialTheme.typography.bodyLarge)
        // Текстовое поле
        TextField(
            value = idx.value,
            onValueChange = { idx.value = it },
            label = { Text("Введите индекс") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewWellnessScreen(){
    WellnessScreen()
}