package com.example.myorganizationlist.database.controller

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myorganizationlist.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {
    @Query("SELECT * FROM Product")
    fun getAll(): Flow<List<Product>>

    @Query("""
        SELECT * FROM Product
        ORDER BY 
        CASE 
            WHEN :campo = 'title' AND :ordem = 'asc' THEN title END ASC,
        CASE 
            WHEN :campo = 'title' AND :ordem = 'desc' THEN title END DESC,
        CASE 
            WHEN :campo = 'description' AND :ordem = 'asc' THEN description END ASC,
        CASE 
            WHEN :campo = 'description' AND :ordem = 'desc' THEN description END DESC,
        CASE 
            WHEN :campo = 'price' AND :ordem = 'asc' THEN price END ASC,
        CASE 
            WHEN :campo = 'price' AND :ordem = 'desc' THEN price END DESC
    """)
    fun getAllSorted(campo: String, ordem: String): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE title LIKE :frist LIMIT 1")
    suspend fun findByName(frist: String): Product

    @Query("SELECT * FROM Product WHERE id LIKE :id LIMIT 1")
    suspend fun findByid(id: Long): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg product: Product)

    @Update
    suspend fun update(vararg product: Product)

    @Delete
    suspend fun delete(product:Product)
}