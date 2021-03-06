package com.milenialsatwork.seriescalendar.view.ui.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.milenialsatwork.seriescalendar.R
import com.milenialsatwork.seriescalendar.model.utils.SCLog

class InputDialog(
    private var activity: Activity) : Dialog(activity), View.OnClickListener {
    companion object {
        private var TAG = "InputDialog"
    }

    private lateinit var textView: EditText
    private lateinit var titleTextView: TextView
    private lateinit var positive: MaterialButton
    private lateinit var negative: MaterialButton
    private lateinit var callbackFunction: (value: Any) -> Unit
    private var title = ""

    fun setCallbackFunction(callbackFunction: (value: Any) -> Unit): InputDialog {
        this.callbackFunction = callbackFunction
        return this
    }

    fun setTitle(title: String): InputDialog {
        this.title = title
        return this
    }

    override fun onClick(view: View?) {
        when (view?.id)  {
            R.id.positive -> {
                callbackFunction(textView.text)
            }
            R.id.negative -> {
                dismiss()
            }
            R.id.name_input_field -> {
                SCLog.d(TAG, "Editing Name")
                textView.text.clear()
            }
            else -> {
                SCLog.d(TAG, "onClick: Unknown view.id")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        SCLog.d(TAG, "onCreate: creating dialog")
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.input_dialog)
        textView = findViewById(R.id.name_input_field)
        titleTextView = findViewById(R.id.input_name_title)
        titleTextView.text = title
        textView.text.clear()
        textView.setHint(R.string.series_name_title)
        positive = findViewById(R.id.positive)
        negative = findViewById(R.id.negative)

        positive.setOnClickListener(this)
        negative.setOnClickListener(this)
    }
}