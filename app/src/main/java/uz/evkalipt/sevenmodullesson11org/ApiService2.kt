package uz.evkalipt.sevenmodullesson11org

import retrofit2.Call
import retrofit2.http.GET
import uz.evkalipt.sevenmodullesson11org.model.Valyut
import uz.evkalipt.sevenmodullesson11org.model.ValyutApiStore

interface ApiService2 {

    @GET("json")
    fun getValyut(): Call<List<ValyutApiStore>>

}