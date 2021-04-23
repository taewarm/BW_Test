package com.example.bigwalk_test

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class HListAdapter(val context: Context) : RecyclerView.Adapter<HListAdapter.ViewHolder>() {
    var datas = arrayListOf<HLstData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.h_list_item,parent,false)
        Log.i("여기2","Tt")
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mimage = view.findViewById<ImageView>(R.id.h_lst_img)
        val mtitle = view.findViewById<TextView>(R.id.h_lst_txt)

        fun bind(item: HLstData) {
            Glide.with(itemView).load(item.mImage).into(mimage)
            mtitle.text = item.mTitle
        }
    }

}