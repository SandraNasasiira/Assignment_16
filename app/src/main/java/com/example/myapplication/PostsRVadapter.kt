package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PostsRVadapter(var PostsList: List<Posts>, var context: Context):RecyclerView.Adapter<PostsRVadapter.ViewHolderPost>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPost {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.cardposts,parent,false)
        return ViewHolderPost(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolderPost, position: Int) {
        var currentPost=PostsList.get(position)
        holder.tvUserId.text=currentPost.userId.toString()
        holder.tvId.text=currentPost.Id.toString()
        holder.tvtTitle.text=currentPost.title
        holder.tvBody.text=currentPost.body
        holder.cvPosts.setOnClickListener {
            var intent = Intent(context, viewPostActivity::class.java)
            intent.putExtra("POST_ID",currentPost.Id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return PostsList.size
    }
class ViewHolderPost(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvUserId=itemView.findViewById<TextView>(R.id.tvUserId)
    var tvId=itemView.findViewById<TextView>(R.id.tvId)
    var tvtTitle=itemView.findViewById<TextView>(R.id.tvTitle)
    var tvBody=itemView.findViewById<TextView>(R.id.tvBody)
    var cvPosts=itemView.findViewById<CardView>(R.id.cvPosts)
}}
