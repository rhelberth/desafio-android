package com.picpay.desafio.android.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.local.DbConstants
import com.picpay.desafio.android.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<UserEntity>)

    @Query("SELECT * FROM ${DbConstants.USER_TABLE_NAME}")
   suspend fun getAll(): List<UserEntity>

    @Query("DELETE FROM ${DbConstants.USER_TABLE_NAME}")
    suspend fun clearAll()
}