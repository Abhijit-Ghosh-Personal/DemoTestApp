package com.demotestapplication.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager

class ProgressDialog(internal var context: Context, layout: Int) : Dialog(context) {

    init {
        // This is the fragment_search_details XML file that describes your Dialog fragment_search_details
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(layout)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lWindowParams = WindowManager.LayoutParams()
        lWindowParams.copyFrom(this.window!!.attributes)
        lWindowParams.width = WindowManager.LayoutParams.MATCH_PARENT
        lWindowParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        this.window!!.attributes = lWindowParams
        setCancelable(false)
    }

    fun showDialog() {
        try {
            this.show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun dismissDialog() {
        try {
            this.dismiss()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}