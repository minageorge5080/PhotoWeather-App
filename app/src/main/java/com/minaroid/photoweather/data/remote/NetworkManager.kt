package com.minaroid.photoweather.data.remote

import com.google.gson.GsonBuilder
import io.reactivex.Single
import java.util.HashMap
import javax.inject.Inject

class NetworkManager @Inject constructor(private val apiService: ApiService) {

    private val headers: HashMap<String, String> = HashMap()
    private val gson = GsonBuilder().serializeNulls().create()

    init {
        headers["Accept"] = "application/json"
        headers["Content-Type"] = "application/json"
    }

    fun <V> getRequest(api: String, headers: HashMap<String, String> = HashMap(), params: HashMap<String, Any> = HashMap(), parseClass: Class<V>): Single<V> {
        this.headers.putAll(headers)
        return apiService.getRequest(api, this.headers, params).map {
            return@map gson.fromJson(it, parseClass)
        }
    }
}