package com.nurisis.seemyclothappp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyImageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        Toast.makeText(context, "Image is saved!!", Toast.LENGTH_LONG).show()

        Log.d(TAG,"Action : $action")

        intent.data?.let {
            val cursor = context.contentResolver.query(
                it, null, null, null, null
            )
            cursor?.moveToFirst()
            val image_path = cursor?.getString(cursor.getColumnIndex("_data"))
            Log.d(TAG,"Image path : $image_path")
        } ?: Log.e(TAG, "Intent.data is null ...")
    }

    companion object{ const val TAG = "MyImageReceiver>>"}
}
