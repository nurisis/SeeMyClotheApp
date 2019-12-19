package com.nurisis.seemyclothappp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CartDao{

    @Query("SELECT * FROM cart")
    fun getAllCart() : LiveData<List<Cart>>

    @Insert(onConflict = REPLACE)
    fun insertCart(cart: Cart)

}