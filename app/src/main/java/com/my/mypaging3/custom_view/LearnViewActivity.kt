package com.my.mypaging3.custom_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.my.mypaging3.R

class LearnViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_view)

        val simpleView = findViewById<EditText>(R.id.input_field)
        simpleView.addTextChangedListener{

        }
    }
}