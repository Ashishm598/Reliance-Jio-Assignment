package com.android.jio.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveData(responseEntity: ResponseEntity): Completable

    @Query("SELECT * FROM response_table")
    fun getData(): Single<ResponseEntity>

    @Query("DELETE FROM response_table")
    fun clearData(): Completable

}