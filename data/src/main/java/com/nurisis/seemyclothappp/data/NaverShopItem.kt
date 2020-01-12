package com.nurisis.seemyclothappp.data

data class NaverShopItem(
    var title:String = "",
    var link:String = "",
    var image:String = "",
    var mallName:String = "",
    var productId:Long = 0,
    var hprice:Long = 0, // Highest price
    var lprice:Long= 0 // Lowest price
)