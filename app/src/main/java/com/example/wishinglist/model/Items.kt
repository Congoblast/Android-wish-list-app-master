package com.example.wishinglist.model

import android.os.Parcelable
import android.text.Html
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "item_table")
data class Items (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    val location:String,
    val price: Int,
    val image: String,
    var checkmark: Boolean
): Parcelable