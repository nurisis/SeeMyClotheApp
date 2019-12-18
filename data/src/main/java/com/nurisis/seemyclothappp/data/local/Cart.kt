package com.nurisis.seemyclothappp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true) var cart_id:Int = 0,
    val cart_url :String = "",
    val cart_img_url:String = "",
    var cart_img_local_path:String = "",
    val cart_title:String = "",
    val cart_created:String = "",
    var cart_updated:String = ""
)