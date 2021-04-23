package com.example.bigwalk_test

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toFile
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.http.Url
import java.io.InputStream
import java.net.URL
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.coroutineContext

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        lateinit var hdataAdapter: HListAdapter
        hdataAdapter = HListAdapter(context)

        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item, null)

        val mimagelay = view.findViewById<LinearLayout>(R.id.lst_item_img_lay)
        val mimage = view.findViewById<ImageView>(R.id.lst_item_img)
        val mtitle = view.findViewById<TextView>(R.id.lst_item_mtitle)
        val stitle = view.findViewById<TextView>(R.id.lst_item_stitle)
        val prgsbar = view.findViewById<ProgressBar>(R.id.prgssbar)
        val persent = view.findViewById<TextView>(R.id.lst_item_persent)
        val organization = view.findViewById<TextView>(R.id.lst_item_organi)
        val organiround = view.findViewById<LinearLayout>(R.id.lst_item_typeround)
        val btn_donate = view.findViewById<Button>(R.id.btn_donate)
        val process = view.findViewById<TextView>(R.id.lst_item_process)
        val data = dataList[position]

        var sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var date = sf.parse(data.endDateD)

        var today = Calendar.getInstance()
        var calcuDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)

        if (calcuDate > 0) {
            if (data.story == true) {
                process.text = "기부완료"
                process.setTextColor(0xFF8B00FF.toInt())
                mimagelay.setBackgroundResource(R.drawable.resultpost)
            } else {
                process.text = "종료"
                process.setTextColor(Color.RED)
            }
        } else {
            process.text = "진행중"
            process.setTextColor(0xFF4379E6.toInt())
        }

        if (data.status == "END") {
            mimage.alpha = 0.3F
            mtitle.alpha = 0.3F
            stitle.alpha = 0.3F
            btn_donate.visibility = View.GONE
        }
        if (data.organization.toString().equals("[]")) {
            organization.text = "공개형"
            organiround.background = R.drawable.public_round.toDrawable()
            organiround.setBackgroundColor(0xFF01AB9E.toInt())//Drawable로 강제변환해서 그런지 색상이 깨짐 그래서 넣음
        } else {
            organization.text = "기업형"
            organiround.background = R.drawable.company_round.toDrawable()
            organiround.setBackgroundColor(0xFFFCCC4E.toInt())
        }
        Glide.with(context).load(data.mainImage).into(mimage)
        mtitle.text = data.mTitle
        stitle.text = data.sTitle
        persent.text = data.ratio.toString() + "%"
        prgsbar.progress = data.ratio

        btn_donate.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val dialogView: View = LayoutInflater.from(context).inflate(R.layout.dialog_check, null)
            val ok = dialogView.findViewById<LinearLayout>(R.id.btn_ok)
            builder.setView(dialogView)
            ok.setOnClickListener {
                val intent = Intent(context,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)//
                intent.putExtra("mImage",data.mainImage)
                intent.putExtra("mTitle",data.mTitle)
                context.startActivity(intent)
            }
            builder.show()
        }

        return view
    }

}