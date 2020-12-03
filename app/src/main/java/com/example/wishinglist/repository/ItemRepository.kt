package com.example.wishinglist.repository

import androidx.lifecycle.LiveData
import com.example.wishinglist.data.ItemDao
import com.example.wishinglist.model.Items

class ItemRepository(private val itemDao: ItemDao) {
    val readAllData: LiveData<List<Items>> = itemDao.readAllData()

    suspend fun addItem(items: Items){
        itemDao.addItem(items)

    }

    suspend fun updateItems(items:Items){
        itemDao.updateItems(items)
    }

    suspend fun deleteItem(items:Items){
        itemDao.deleteitem(items)
    }
}
