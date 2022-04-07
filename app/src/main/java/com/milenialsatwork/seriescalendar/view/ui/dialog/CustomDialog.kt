package com.milenialsatwork.seriescalendar.view.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import com.milenialsatwork.seriescalendar.R

class CustomDialog
    (var c: Activity) : Dialog(c), View.OnClickListener {
    var d: Dialog? = null
    var yes: Button? = null
    var no: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)
        yes = findViewById(R.id.positive) as Button?
        no = findViewById(R.id.negative) as Button?
        yes?.setOnClickListener(this)
        no?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.positive -> c.finish()
            R.id.negative -> dismiss()
            else -> {
            }
        }
        dismiss()
    }
}