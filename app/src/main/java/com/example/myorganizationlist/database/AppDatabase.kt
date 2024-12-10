
package com.example.myorganizationlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myorganizationlist.database.controller.ProdutoDao
import com.example.myorganizationlist.database.controller.UserDao
import com.example.myorganizationlist.model.Product
import com.example.myorganizationlist.model.User

@Database(entities = [User::class, Product::class], version = 1, exportSchema = true)
@TypeConverters(ConvertorTypes::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao
    abstract fun UserDao(): UserDao

    companion object {
        @Volatile private var db: AppDatabase? = null
        fun instancia(context: Context): AppDatabase {
            return db?: Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "orgs.db"
            ).build()
                .also {
                    db = it
                }
        }
    }
}
