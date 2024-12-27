package work.project.rgr_kitpo

import MyLinkedList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ListViewModel:ViewModel() {
    private val _myLinkedList = getMyIntLinkedList()
    val myLinkedList: MyLinkedList
        get() = _myLinkedList

    fun remove(id: Int){
        _myLinkedList.remove(id)
    }
    private fun getMyIntLinkedList() = MyLinkedList()
}