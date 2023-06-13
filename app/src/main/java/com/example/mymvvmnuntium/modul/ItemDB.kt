package com.example.mymvvmnuntium.modul

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class  ItemDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val name: String?=null
):java.io.Serializable{

}