package uz.evkalipt.sevenmodullesson11org.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.evkalipt.sevenmodullesson11org.dao.ValyutApiStore2Dao
import uz.evkalipt.sevenmodullesson11org.dao.ValyutApiStoreDao
import uz.evkalipt.sevenmodullesson11org.model.ValyutApiStore
import uz.evkalipt.sevenmodullesson11org.model.ValyutApiStore2

@Database(entities = [ValyutApiStore::class, ValyutApiStore2::class], version = 1)
abstract class MyDb:RoomDatabase() {

    abstract fun valyutApiStoreDao(): ValyutApiStoreDao
    abstract fun valyutApiStore2Dao(): ValyutApiStore2Dao

    companion object{
        private var instance:MyDb? = null

        fun getInstance(context: Context):MyDb{
            if (instance == null){
                instance = Room.databaseBuilder(context, MyDb::class.java, "pdp")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}