package com.example.contacttracingproject.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contacttracingproject.models.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_table")
    fun getAll(): Flow<List<History>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(history: History)
}
