package com.nurisis.seemyclothappp

import android.util.Log
import java.lang.Exception

class Calculator<T: Number>{

    fun add(num1:T, num2:T) : Double{
        return (num1.toDouble() + num2.toDouble())
    }

    fun remainder(num1:T, num2:T) : Int{
        if(num2.toDouble() == 0.toDouble()) return 0

        return  (num1.toDouble()/num2.toDouble()).toInt()
    }
}