package com.antoinecrettenand.itunesstoresearch.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesItem
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesItemType
import com.antoinecrettenand.itunesstoresearch.data.model.ItunesResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItunesSearchRepository {
    private val TAG: String = javaClass.simpleName

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val itunesService: ItunesNetwork = retrofit.create(ItunesNetwork::class.java)
    val itemList = MutableLiveData<List<ItunesItem>>()

    fun search(query: String, itemType: ItunesItemType, countryCode: String) {
        itunesService.getMedia(query, itemType.apiName, countryCode).enqueue(object : Callback<ItunesResult> {
            override fun onResponse(call: Call<ItunesResult>, response: Response<ItunesResult>) {
                if (response.isSuccessful) {
                    val results: List<ItunesItem> = response.body()?.results ?: emptyList()

                    itemList.postValue(results)
                    Log.d(TAG, "onResponse: Successful with ${results.size} items returned.")
                } else {
                    Log.d(TAG, "onResponse: Failed with code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ItunesResult>, t: Throwable) {
                itemList.postValue(emptyList())
                Log.d(TAG, "Request failed")
            }
        })
    }

}
