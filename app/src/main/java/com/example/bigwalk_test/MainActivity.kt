package com.example.bigwalk_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var data = arrayListOf<LstData>()
    val dataAdapter = ListAdapter(this,data)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var nanumlist = findViewById<ListView>(R.id.lst_nanum)
        nanumlist.adapter = dataAdapter
        ListApi()
        Scrollmenu()
    }

    fun ListApi() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://app-dev.bigwalk.co.kr:10000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(BigwalkAPI::class.java)
        val callTest = api.getData(0,60)

        callTest.enqueue(object : Callback<List<APIData>>{
            override fun onResponse(call: Call<List<APIData>>, response: Response<List<APIData>>) {
                Log.i("여기",response.body()?.get(0).toString())
                Log.i("여기",response.body()?.size.toString())
                var sTitleList:List<String>
                var myList:List<String>
                var storyy:String
                for (i in 0..response.body()!!.size-1){
                    Log.i("여기",response.body()?.get(i).toString())
                    var lst_image =response.body()?.get(i)?.smallListThumbnailImagePath!!
                    var mTitle:String = response.body()?.get(i)?.name!!
                    var sTitle:String = response.body()?.get(i)?.campaignPromoter.toString()
                    var ratio:Int = response.body()?.get(i)?.ratio!!
                    var status:String = response.body()?.get(i)?.status!!
                    var organi:Object = response.body()?.get(i)?.organizations!!
                    var stroy:Object = response.body()?.get(i)?.my!!
                    var endDate: String = response.body()?.get(i)?.endDate!!
                    endDate = endDate.replace("T"," ")
                    sTitleList = sTitle.split(",")
                    myList = stroy.toString().split(", ")
                    storyy = myList[3].replace("story","")
                    storyy = storyy.replace("}","")
                    var sstory:Boolean = storyy.toBoolean()
                    Log.i("log",myList[3])

                    //object데이터형식을 가져올때 이렇게 밖에 안되나 방안 찾기
                    data.add(LstData(mTitle,sTitleList[0].substring(6,sTitleList[0].length),lst_image,ratio,status,organi,endDate,sstory))
                    var lst_main = findViewById<LinearLayout>(R.id.lst_item_main)
                    lst_main?.setBackgroundColor(Color.TRANSPARENT)
                }
                data.sortBy { it.ratio }
                dataAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<APIData>>, t: Throwable) {
                Log.i("여기","실패 : $t")
            }
        })
    }

    fun Scrollmenu(){

        var scl_all = findViewById<LinearLayout>(R.id.scl_all)
        var scl_env = findViewById<LinearLayout>(R.id.scl_env)
        var scl_old = findViewById<LinearLayout>(R.id.scl_old)
        var scl_amal = findViewById<LinearLayout>(R.id.scl_amal)
        var scl_cild = findViewById<LinearLayout>(R.id.scl_cild)
        var scl_dsab = findViewById<LinearLayout>(R.id.scl_dsab)
        var scl_eath = findViewById<LinearLayout>(R.id.scl_eath)
        var txt_all = findViewById<TextView>(R.id.txt_all)
        var txt_env = findViewById<TextView>(R.id.txt_env)
        var txt_old = findViewById<TextView>(R.id.txt_old)
        var txt_amal = findViewById<TextView>(R.id.txt_amal)
        var txt_cild = findViewById<TextView>(R.id.txt_cild)
        var txt_dsab = findViewById<TextView>(R.id.txt_dsab)
        var txt_eath = findViewById<TextView>(R.id.txt_eath)

        scl_all.setOnClickListener {
            it.setBackgroundResource(R.drawable.horizon_round)
            txt_all.setTextColor(Color.WHITE)
            data.clear()
            ListApi()
            dataAdapter.notifyDataSetChanged()

            scl_env.background = null
            txt_env.setTextColor(Color.GRAY)
            scl_old.background = null
            txt_old.setTextColor(Color.GRAY)
            scl_amal.background = null
            txt_amal.setTextColor(Color.GRAY)
            scl_cild.background = null
            txt_cild.setTextColor(Color.GRAY)
            scl_dsab.background = null
            txt_dsab.setTextColor(Color.GRAY)
            scl_eath.background = null
            txt_eath.setTextColor(Color.GRAY)
        }

        scl_env.setOnClickListener {
            it.setBackgroundResource(R.drawable.horizon_round)
            txt_env.setTextColor(Color.WHITE)

            data.clear()
            dataAdapter.notifyDataSetChanged()

            scl_all.background = null
            txt_all.setTextColor(Color.GRAY)
            scl_old.background = null
            txt_old.setTextColor(Color.GRAY)
            scl_amal.background = null
            txt_amal.setTextColor(Color.GRAY)
            scl_cild.background = null
            txt_cild.setTextColor(Color.GRAY)
            scl_dsab.background = null
            txt_dsab.setTextColor(Color.GRAY)
            scl_eath.background = null
            txt_eath.setTextColor(Color.GRAY)
        }

        scl_old.setOnClickListener {
            it.setBackgroundResource(R.drawable.horizon_round)
            txt_old.setTextColor(Color.WHITE)

            data.clear()
            dataAdapter.notifyDataSetChanged()

            scl_all.background = null
            txt_all.setTextColor(Color.GRAY)
            scl_env.background = null
            txt_env.setTextColor(Color.GRAY)
            scl_amal.background = null
            txt_amal.setTextColor(Color.GRAY)
            scl_cild.background = null
            txt_cild.setTextColor(Color.GRAY)
            scl_dsab.background = null
            txt_dsab.setTextColor(Color.GRAY)
            scl_eath.background = null
            txt_eath.setTextColor(Color.GRAY)
        }

        scl_amal.setOnClickListener {
            it.setBackgroundResource(R.drawable.horizon_round)
            txt_amal.setTextColor(Color.WHITE)

            data.clear()
            dataAdapter.notifyDataSetChanged()

            scl_all.background = null
            txt_all.setTextColor(Color.GRAY)
            scl_old.background = null
            txt_old.setTextColor(Color.GRAY)
            scl_env.background = null
            txt_env.setTextColor(Color.GRAY)
            scl_cild.background = null
            txt_cild.setTextColor(Color.GRAY)
            scl_dsab.background = null
            txt_dsab.setTextColor(Color.GRAY)
            scl_eath.background = null
            txt_eath.setTextColor(Color.GRAY)
        }

        scl_cild.setOnClickListener {
            it.setBackgroundResource(R.drawable.horizon_round)
            txt_cild.setTextColor(Color.WHITE)

            data.clear()
            dataAdapter.notifyDataSetChanged()

            scl_all.background = null
            txt_all.setTextColor(Color.GRAY)
            scl_old.background = null
            txt_old.setTextColor(Color.GRAY)
            scl_amal.background = null
            txt_amal.setTextColor(Color.GRAY)
            scl_env.background = null
            txt_env.setTextColor(Color.GRAY)
            scl_dsab.background = null
            txt_dsab.setTextColor(Color.GRAY)
            scl_eath.background = null
            txt_eath.setTextColor(Color.GRAY)
        }

        scl_dsab.setOnClickListener {
            it.setBackgroundResource(R.drawable.horizon_round)
            txt_dsab.setTextColor(Color.WHITE)

            data.clear()
            dataAdapter.notifyDataSetChanged()

            scl_all.background = null
            txt_all.setTextColor(Color.GRAY)
            scl_old.background = null
            txt_old.setTextColor(Color.GRAY)
            scl_amal.background = null
            txt_amal.setTextColor(Color.GRAY)
            scl_cild.background = null
            txt_cild.setTextColor(Color.GRAY)
            scl_env.background = null
            txt_env.setTextColor(Color.GRAY)
            scl_eath.background = null
            txt_eath.setTextColor(Color.GRAY)
        }

        scl_eath.setOnClickListener {
            it.setBackgroundResource(R.drawable.horizon_round)
            txt_eath.setTextColor(Color.WHITE)

            data.clear()
            dataAdapter.notifyDataSetChanged()

            scl_all.background = null
            txt_all.setTextColor(Color.GRAY)
            scl_old.background = null
            txt_old.setTextColor(Color.GRAY)
            scl_amal.background = null
            txt_amal.setTextColor(Color.GRAY)
            scl_cild.background = null
            txt_cild.setTextColor(Color.GRAY)
            scl_dsab.background = null
            txt_dsab.setTextColor(Color.GRAY)
            scl_env.background = null
            txt_env.setTextColor(Color.GRAY)
        }
    }
}