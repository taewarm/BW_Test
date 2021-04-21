package com.example.bigwalk_test

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toFile
import com.bumptech.glide.Glide
import retrofit2.http.Url
import java.io.InputStream
import java.net.URL

class ListAdapter (val context: Context, val dataList: ArrayList<LstData>) : BaseAdapter(){
    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item,null)

        val mimage = view.findViewById<ImageView>(R.id.lst_item_img)
        val mtitle = view.findViewById<TextView>(R.id.lst_item_mtitle)
        val stitle = view.findViewById<TextView>(R.id.lst_item_stitle)
        val prgsbar = view.findViewById<ProgressBar>(R.id.prgssbar)
        val persent = view.findViewById<TextView>(R.id.lst_item_persent)
        val data = dataList[position]

//            var uri:Uri = Uri.parse("https://bigwalk-dev.s3.ap-northeast-2.amazonaws.com/campaign/242/thumbnail/8D3q1-detail.jpg")
//            val source = ImageDecoder.createSource(context.contentResolver,uri)
//            val bit:Bitmap = ImageDecoder.decodeBitmap(source)
//            mimage.setImageBitmap(bit)
//            Log.i("여기",source.toString())


//        mimage.setImageURI(uri)

        Glide.with(context).load(data.mainImage).into(mimage)
        mtitle.text = data.mTitle
        stitle.text = data.sTitle
        persent.text = data.ratio.toString()
        prgsbar.progress = data.ratio
        return view
    }
}