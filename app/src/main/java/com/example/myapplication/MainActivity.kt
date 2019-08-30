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
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {
    private var number: Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val typeface = Typeface.createFromAsset(this.baseContext.assets, "fonts/samim-en.ttf")

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
                    .baseUrl("http://moviesapi.ir/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val loginService = retrofit.create(LoginService::class.java)
                val user = User("password", "adicom@gmail.com", "123456")
                val res: Call<ResponseModel> = loginService.login(user)
                res.enqueue(object : Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        val responseModel: ResponseModel? = response.body()
                        changeActivity(responseModel)
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        Log.e("TAG", t.message)
                    }

                })
            }
        }
    }

    private fun changeActivity(responseModel: ResponseModel?) {
        val gson = Gson()
        val myJson = gson.toJson(responseModel)
        TargetActivity.start(this, myJson)
    }

    private fun printWithFormat(): String {
        return String.format("مقدار: %d", sum())
    }

    fun sum(): Long {
        number *= 2
        return number
    }
}
