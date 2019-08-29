package com.example.myapplication

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.ResponseModel
import com.example.myapplication.models.User
import com.example.myapplication.service.LoginService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
                btnResult.isEnabled = false
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://reqres.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val loginService = retrofit.create(LoginService::class.java)
                val res: Call<ResponseModel> = loginService.login(User("eve.holt@reqres.in", "cityslicka"))
//                TargetActivity.start(this, "Target Activity")
                res.enqueue(object: Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        Log.e("TAG", response.toString())
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        t.printStackTrace()
                    }

                })
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
