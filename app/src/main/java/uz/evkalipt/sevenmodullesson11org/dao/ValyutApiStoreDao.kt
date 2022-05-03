package uz.evkalipt.sevenmodullesson11org.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import uz.evkalipt.sevenmodullesson11org.model.ValyutApiStore

@Dao
interface ValyutApiStoreDao {

    @Insert
    fun addStore(valyutApiStore: ValyutApiStore)

    @Query("select * from valyutapistore")
    fun getAll():List<ValyutApiStore>

    @Delete
    fun delete(valyutApiStore: ValyutApiStore)

}