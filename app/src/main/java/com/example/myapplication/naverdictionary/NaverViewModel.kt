package com.example.myapplication.naverdictionary

import android.util.Log
import com.example.myapplication.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 나중에 연결하기 편하라고 따로 만들어둠. 나중에 완성하면 지워도 됨
// 그래서 일단은 전부 주석처리
//class NaverViewModel {
//    private suspend fun fenchNaverResult(query: String) {
//        NaverRetrofit.naverApiService.naverDic(Constants.NAVER_CLIENT_ID, Constants.NAVER_CLIENT_SECRET, query)
//            ?.enqueue(object: Callback<NaverData> {
//                override fun onResponse(call: Call<NaverData>, response: Response<NaverData>) {
//                    val body = response.body()
//
//                    body?.let {
//                        response.body()!!.{
//                            // 여기에 Adapter 연결하면 될듯
//                        }
//                    }
//
//                    adapter.item
//                    adapter.notifyDataSetChanged()
//                    Log.d("네이버api 검사", "응답")
//                }
//
//                override fun onFailure(call: Call<NaverData>, t: Throwable) {
//                    Log.d("네이버api 검사", "응답실패")
//                }
//            })
//    }
//}