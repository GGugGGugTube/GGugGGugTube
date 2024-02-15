package com.example.myapplication.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CommentItemBinding

class CommentAdapter() :
    RecyclerView.Adapter<CommentAdapter.Holder>() {
    private val TAG = "CommentAdapter"

    private var commentItems:List<Comment> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentAdapter.Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CommentItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(commentItems[position])
    }

    override fun getItemCount(): Int {
        return commentItems.size
    }

    fun updateItems(newCommentItems:List<Comment>){
        commentItems = newCommentItems
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Comment) {
            binding.tvComment.text = item.comment
        }
    }
}