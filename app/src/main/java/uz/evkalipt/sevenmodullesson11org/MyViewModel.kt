package uz.evkalipt.sevenmodullesson11org

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.evkalipt.sevenmodullesson11org.model.Valyut
import uz.evkalipt.sevenmodullesson11org.model.Valyut1

class MyViewModel:ViewModel() {

    private val liveData = MutableLiveData<List<Valyut1>>()

    fun getValyut(): LiveData<List<Valyut1>> {
        ApiClient.getRetrofit().create(ApiService::class.java).getValyut().enqueue(object :
            Callback<List<Valyut>> {
            override fun onResponse(call: Call<List<Valyut>>, response: Response<List<Valyut>>) {
                if (response.isSuccessful){
                    var list = ArrayList<Valyut1>()
                    response.body()?.forEach {
                        var str = ObservableField<String>("https://nbu.uz/local/templates/nbu/images/flags/${it.code}.png")
                        list.add(Valyut1(str, it))
                    }
                    liveData.value = list
                }
            }

            override fun onFailure(call: Call<List<Valyut>>, t: Throwable) {

            }

        })

        return liveData
    }


}