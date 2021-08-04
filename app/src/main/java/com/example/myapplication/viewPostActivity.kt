package com.example.myapplication

import android.icu.text.CaseMap
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class viewPostActivity: AppCompatActivity() {
    var postId = 0
    lateinit var tvPostTitle : TextView
    lateinit var tvPostBody : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_post)
        postId = intent.getIntExtra("POST_ID",0)

        var tvPostBody=findViewById<TextView>(R.id.tvPostBody)
        var tvPostTitle=findViewById<TextView>(R.id.tvPostTitle)
    }
    fun fetchPostsById(){
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.getPostById(postId)
            request.enqueue(object : Callback<Posts>{
                override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                    if (response.isSuccessful) {
                        var posts = response.body()!!
                        tvPostBody.text = posts?.body
                        tvPostTitle.text = posts?.title
                    }
                }
                override fun onFailure(call: Call<Posts>, t: Throwable) {
                    Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG)
                }
            })
    }

}