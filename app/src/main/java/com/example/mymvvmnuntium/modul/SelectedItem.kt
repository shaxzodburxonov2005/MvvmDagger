package com.example.mymvvmnuntium.modul

import androidx.room.Entity
import androidx.room.PrimaryKey


data class SelectedItem(
    val nameSd:String?=null,
    val img:Int,
    val id:Int?=null,

):java.io.Serializable {
    fun getSelected():ItemDB=
        ItemDB(this.id,this.nameSd)
}