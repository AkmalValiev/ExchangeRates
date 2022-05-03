package uz.evkalipt.sevenmodullesson11org

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.evkalipt.sevenmodullesson11org.db.MyDb
import uz.evkalipt.sevenmodullesson11org.model.ValyutApiStore
import uz.evkalipt.sevenmodullesson11org.model.ValyutApiStore2

class MyWorkService(var context: Context, var workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    lateinit var myDb: MyDb

    override fun doWork(): Result {
        myDb = MyDb.getInstance(context)
        ApiClient.getRetrofit().create(ApiService2::class.java).getValyut()
            .enqueue(object : Callback<List<ValyutApiStore>> {
                override fun onResponse(
                    call: Call<List<ValyutApiStore>>,
                    response: Response<List<ValyutApiStore>>
                ) {
                    if (myDb.valyutApiStoreDao().getAll().isEmpty()) {
                        response.body()?.forEach {
                            myDb.valyutApiStoreDao().addStore(it)
                        }
                    } else {
                        response.body()?.forEach {
                            for (valyutApiStore in myDb.valyutApiStoreDao().getAll()) {
                                if (valyutApiStore.code == it.code) {
                                    if (valyutApiStore.nbu_buy_price != it.nbu_buy_price || valyutApiStore.nbu_cell_price != it.nbu_cell_price) {
                                        myDb.valyutApiStoreDao().delete(valyutApiStore)
                                        myDb.valyutApiStoreDao().addStore(it)
                                        var valyutApiStore2 = ValyutApiStore2(
                                            it.cb_price,
                                            it.code,
                                            it.date,
                                            it.nbu_buy_price,
                                            it.nbu_cell_price,
                                            it.title
                                        )
                                        myDb.valyutApiStore2Dao().addStore2(valyutApiStore2)
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<ValyutApiStore>>, t: Throwable) {

                }

            })
        return Result.success()
    }

}