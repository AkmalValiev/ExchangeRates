package uz.evkalipt.sevenmodullesson11org.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.evkalipt.sevenmodullesson11org.model.ValyutApiStore2

@Dao
interface ValyutApiStore2Dao {

    @Insert
    fun addStore2(valyutApiStore2: ValyutApiStore2)

    @Query("select * from valyutapistore2")
    fun getAll():List<ValyutApiStore2>

}