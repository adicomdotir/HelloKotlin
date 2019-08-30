package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.ResponseModel
import com.example.myapplication.models.UserModel
import com.example.myapplication.service.LoginService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)

        val intent: Intent = intent
        val message = intent.getStringExtra("Key")
        val gson = Gson()
        val res = gson.fromJson(message, ResponseModel::class.java)
        val tvMessage: TextView = findViewById(R.id.tv_message)
        tvMessage.text = "در حال دریافت اطلاعات"

        val loginService = initRetrofit(res.access_token)
        val response: Call<UserModel> = loginService.getUser()
        response.enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {}

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                val userModel: UserModel? = response.body()
                tvMessage.text = userModel?.name

                val loadingTask = LoadingTask()
                loadingTask.execute()
            }
        })
    }

    private fun initRetrofit(accessToken: String): LoginService {
        val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $accessToken")
                .build()
            chain.proceed(request)
        }.build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://moviesapi.ir/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(LoginService::class.java)
    }

    companion object {
        fun start(context: Context, value: String) {
            val intent = Intent(context, TargetActivity::class.java)
            intent.putExtra("Key", value)
            context.startActivity(intent)
        }
    }
}

