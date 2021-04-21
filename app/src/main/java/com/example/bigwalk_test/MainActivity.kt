package com.example.bigwalk_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var data = arrayListOf<LstData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataAdapter = ListAdapter(this,data)
        var nanumlist = findViewById<ListView>(R.id.lst_nanum)
        nanumlist.adapter = dataAdapter

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
                for (i in 0..response.body()!!.size-1){
                    Log.i("여기",response.body()?.get(i).toString())
                    var lst_image =response.body()?.get(i)?.detailThumbnailImagePath!!
                    var mTitle:String = response.body()?.get(i)?.name.toString()
                    var sTitle:String = response.body()?.get(i)?.campaignPromoter.toString()
                    var ratio:Int = response.body()?.get(i)?.ratio!!
                    sTitleList = sTitle.split(",")
                    //object데이터형식을 가져올때 이렇게 밖에 안되나 방안 찾기
                    data.add(LstData(mTitle,sTitleList[0].substring(6,sTitleList[0].length),lst_image,ratio,true))
                    Log.i("ratio,name",response.body()?.get(i)?.ratio.toString()+","+response.body()?.get(i)?.name.toString())
                }
                data.sortBy { it.ratio }
                dataAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<APIData>>, t: Throwable) {
                Log.i("여기","실패 : $t")
            }
        })
    }
}