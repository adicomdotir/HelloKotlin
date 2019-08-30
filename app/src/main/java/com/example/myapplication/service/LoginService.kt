package com.example.myapplication.service

import com.example.myapplication.models.ResponseModel
import com.example.myapplication.models.User
import com.example.myapplication.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {
    /*
    @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);
     */

    @POST("/oauth/token")
    fun login(@Body user: User): Call<ResponseModel>

    @GET("/api/user")
    fun getUser(): Call<UserModel>
}