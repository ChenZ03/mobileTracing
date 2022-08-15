package com.example.contacttracingproject.dao

import androidx.room.*
import com.example.contacttracingproject.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user_table")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user_table WHERE icNumber = :icNumber")
    suspend fun getUser(icNumber : String): User

    //login
    @Query("SELECT * FROM user_table WHERE icNumber = :icNumber AND password = :password")
    suspend fun loginUser(icNumber : String, password : String): User
}