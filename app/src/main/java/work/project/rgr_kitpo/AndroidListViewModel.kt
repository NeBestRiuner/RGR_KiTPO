package work.project.rgr_kitpo

import MyLinkedList
import Point
import UserType
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class AndroidListViewModel : ViewModel(){
    private val _items = mutableStateListOf<String>()
    val items: List<String> get() = _items

    var elems = MyLinkedList()

    fun remove(idx: Int){
        elems.remove(idx)
        updateList()
    }
    fun addEnd(value: UserType){
        elems.add(value)
        updateList()
    }
    fun addIndex(value: UserType, idx: Int){
        elems.addNum(value,idx)
        updateList()
    }
    fun changeIndex(value: UserType,idx: Int){
        elems.set(value, idx)
        updateList()
    }
    fun getMyLL(): MyLinkedList{
        return elems;
    }
    fun sortMyLinkList(str: String){
        if(str.equals("Int")){
            elems = elems.mergeSort(elems,MyInt.tc)
        }
        if(str.equals("Float")){
            elems = elems.mergeSort(elems,MyFloat.tc)
        }
        if(str.equals("String")){
            elems = elems.mergeSort(elems,MyString.tc)
        }
        if(str.equals("Point")){
            elems = elems.mergeSort(elems,Point().typeComparator)
        }
        updateList()
    }
    private fun updateList() {
        println("Вызвана перерисовка массива: ")
        println(elems.print())
        _items.clear()
        _items.addAll(elems.toList())
    }
    public fun changeList(){
        elems = MyLinkedList()
        updateList()
    }

}

