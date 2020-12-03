package com.example.wishinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wishinglist.model.Items

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //Add ignore, so more items of the same name can be added,
    suspend fun addItem(items: Items)

    @Query("SELECT * FROM ITEM_TABLE ORDER BY id ASC")
    //think about this later. It sorts by id,
    fun readAllData():LiveData<List<Items>>

    @Update
    suspend fun updateItems (items:Items)

    @Delete
    suspend fun deleteitem(items:Items)



}