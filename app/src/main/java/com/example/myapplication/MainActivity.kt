package com.example.myapplication

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var number: Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val typeface =  Typeface.createFromAsset(this.baseContext.assets, "fonts/samim-en.ttf")

        val tvResult: TextView = findViewById(R.id.tv_result)
        tvResult.text = printWithFormat()
        tvResult.typeface = typeface

        val btnResult: Button = findViewById(R.id.button)
        btnResult.setOnClickListener {
            if (number < 1024) {
                tvResult.text = printWithFormat()
            } else {
                TargetActivity.start(this, "Target Activity")
            }
        }
    }

    private fun printWithFormat(): String {
        return String.format("مقدار: %d", sum())
    }

    fun sum(): Long {
        number *= 2
        return number
    }
}
