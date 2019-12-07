package com.example.bookmystay.presenter.hoteldetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmystay.R
import com.example.bookmystay.data.hoteldetail.CommentModel
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(var commentList:List<CommentModel>) : RecyclerView.Adapter<CommentAdapter.Companion.MyViewHolder>() {

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val txtUser = view.txtName
            val txtComment = view.txtComments
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val commentModel = commentList.get(position)
        holder.txtUser.text = commentModel.user
        holder.txtComment.text = commentModel.comment
    }
}