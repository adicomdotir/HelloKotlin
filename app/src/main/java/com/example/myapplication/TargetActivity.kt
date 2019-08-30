package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.ResponseModel
import com.example.myapplication.models.User
import com.example.myapplication.models.UserModel
import com.example.myapplication.service.LoginService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.io.IOException
import okhttp3.Interceptor
import okhttp3.Request


class TargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)

        val intent: Intent = intent
        val message = intent.getStringExtra("Key")
        val gson = Gson()
        val res = gson.fromJson(message, ResponseModel::class.java)
        val tvMessage: TextView = findViewById(R.id.tv_message)

        val okHttpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + res.access_token)
                    .build()
                return chain.proceed(request)
            }
        }).build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://moviesapi.ir/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val loginService = retrofit.create(LoginService::class.java)
        val response: Call<UserModel> = loginService.getUser()
        response.enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {}

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                val userModel: UserModel? = response.body()
                tvMessage.text = userModel?.name
            }
        })
    }

    companion object {
        fun start(context: Context, value: String) {
            val intent = Intent(context, TargetActivity::class.java)
            intent.putExtra("Key", value)
            context.startActivity(intent)
        }
    }
}
