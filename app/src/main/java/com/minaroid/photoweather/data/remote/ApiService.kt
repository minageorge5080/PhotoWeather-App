package com.minaroid.photoweather.data.remote

import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET
    fun getRequest(
        @Url api: String,
        @HeaderMap headers: HashMap<String, String> = HashMap(),
        @QueryMap params: HashMap<String, Any> = HashMap()
    ): Single<JsonElement>

}