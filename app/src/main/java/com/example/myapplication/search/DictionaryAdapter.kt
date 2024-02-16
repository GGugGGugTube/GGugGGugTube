package com.example.myapplication.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.DictionaryItemBinding
import com.example.myapplication.model.NaverModel
interface OnNaverClickedListener {
    fun onNaverClicked (item: NaverModel)
}
class DictionaryAdapter(private val mContext: Context) : RecyclerView.Adapter<DictionaryAdapter.Holder>() {
    var items = ArrayList<NaverModel>()

    var onNaverClickedListener : OnNaverClickedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            DictionaryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(mContext)
            .load(items[position].thumbnail)
            .into(holder.ivimgge)

        holder.itemView.setOnClickListener {
            onNaverClickedListener?.onNaverClicked(items[position])
        }

        holder.tvtitle.text = items[position].title.replace("<b>", "").replace("</b>", "")
        holder.tvdesc.text = items[position].description.replace("<b>", "").replace("</b>", "")
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class Holder(binding: DictionaryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var tvtitle = binding.tvDictitle
        var ivimgge = binding.imgDicimage
        var tvdesc = binding.tvDicword
    }

}