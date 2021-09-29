package com.android.jio.cache

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveData(responseEntity: ResponseEntity): Completable

    @Query("SELECT * FROM response_table LIMIT 1")
    fun getData(): Single<ResponseEntity>

    @Query("DELETE FROM response_table")
    fun clearData(): Completable

}