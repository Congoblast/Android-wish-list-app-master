package com.example.wishinglist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.wishinglist.data.ItemDatabase
import com.example.wishinglist.repository.ItemRepository
import com.example.wishinglist.model.Items
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application): AndroidViewModel(application) {

     val readAllData: LiveData<List<Items>>
    private val repository: ItemRepository
    init {
        val itemDao = ItemDatabase.getDatabase(
            application
        ).itemDao()
        repository = ItemRepository(itemDao)
        readAllData = repository.readAllData
    }
    fun addItem(items: Items){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(items)
        }
    }
    fun updateItems(items:Items){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateItems(items)
        }

    }
    fun deleteItem(items:Items){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(items)
        }
    }

}