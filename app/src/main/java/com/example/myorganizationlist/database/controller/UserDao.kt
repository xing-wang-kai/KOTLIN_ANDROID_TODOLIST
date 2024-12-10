package com.example.myorganizationlist.database.controller

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myorganizationlist.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): Flow<List<User>>

    @Query("""
        SELECT * FROM User
        ORDER BY 
        CASE 
            WHEN :campo = 'email' AND :ordem = 'asc' THEN email END ASC,
        CASE 
            WHEN :campo = 'email' AND :ordem = 'desc' THEN email END DESC,
        CASE 
            WHEN :campo = 'name' AND :ordem = 'asc' THEN name END ASC,
        CASE 
            WHEN :campo = 'name' AND :ordem = 'desc' THEN name END DESC,
        CASE 
            WHEN :campo = 'password' AND :ordem = 'asc' THEN password END ASC,
        CASE 
            WHEN :campo = 'password' AND :ordem = 'desc' THEN password END DESC
    """)
    fun getAllSorted(campo: String, ordem: String): Flow<List<User>>

    @Query("SELECT * FROM User WHERE email LIKE :email LIMIT 1")
    suspend fun findByEmail(email: String): User?

    @Query("SELECT * FROM User WHERE id LIKE :id LIMIT 1")
    suspend fun findByid(id: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User)

    @Update
    suspend fun update(vararg user: User)

    @Delete
    suspend fun delete(user: User)
}