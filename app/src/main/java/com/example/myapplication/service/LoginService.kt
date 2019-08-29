package com.example.myapplication.service

import com.example.myapplication.models.ResponseModel
import com.example.myapplication.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    /*
    @GET("users/{user}/repos")
  Call<List<Repo>> listRepos(@Path("user") String user);
     */

    @POST("api/login")
    fun login(@Body user: User): Call<ResponseModel>
}