package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rvPosts:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPosts = findViewById(R.id.rvPosts)
        rvPosts.layoutManager = LinearLayoutManager(baseContext)
        fetchPosts()
    }
    fun fetchPosts(){
        var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
        var request = retrofit.getPosts()
        request.enqueue(object :Callback<List<Posts>> {

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (response.isSuccessful) {
                    var postsList = response.body()!!
                    if (postsList != null) {
                        var postsadaptor = PostsRVadapter(postsList, baseContext)
                        rvPosts = findViewById(R.id.rvPosts)
                        rvPosts.adapter = postsadaptor

//                    Toast.makeText(baseContext, postList.size.toString(), Toast.LENGTH_LONG).show()
//                    var postsDate = mutableListOf<Posts>()
//                    for (x in 1..postsList.size){
//                        postsDate.add(Posts(""))
//                }
                    }
                }
            }
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
            })
        }
    }