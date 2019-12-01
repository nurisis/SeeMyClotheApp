package com.nurisis.seemyclothappp.data

data class NaverSearchResult(
    var total:Long,
    var start:Int,
    var display:Int,

    var items:List<NaverShopItem>
)