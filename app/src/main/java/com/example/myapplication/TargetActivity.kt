package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)

        val intent: Intent = intent
        val message = intent.getStringExtra("Key")
        val tvMessage: TextView = findViewById(R.id.tv_message)
        tvMessage.text = message
    }

    companion object {
        fun start(context: Context, value: String) {
            val intent = Intent(context, TargetActivity::class.java)
            intent.putExtra("Key", value)
            context.startActivity(intent)
        }
    }
}
