package com.stealth.manager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Insert
    suspend fun insert(profile: ProfileEntity)

    @Query("SELECT * FROM profiles")
    fun getAllProfiles(): Flow<List<ProfileEntity>>

    @Delete
    suspend fun delete(profile: ProfileEntity)
}
