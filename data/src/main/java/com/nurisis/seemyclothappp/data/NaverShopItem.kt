package com.nurisis.seemyclothappp.data

data class NaverShopItem(
    var title:String,
    var link:String,
    var image:String,
    var mallName:String,
    var productId:Long,
    var hprice:Long, // Highest price
    var lprice:Long // Lowest price
)