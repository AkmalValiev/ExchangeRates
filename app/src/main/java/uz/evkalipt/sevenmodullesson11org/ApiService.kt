package uz.evkalipt.sevenmodullesson11org

import retrofit2.Call
import retrofit2.http.GET
import uz.evkalipt.sevenmodullesson11org.model.Valyut

interface ApiService {

    @GET("json")
    fun getValyut():Call<List<Valyut>>

}