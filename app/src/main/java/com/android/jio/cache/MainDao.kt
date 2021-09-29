package com.android.jio.cache

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveData(responseEntity: ResponseEntity): Completable

    @Query("SELECT * FROM response_table")
    abstract fun getData(): Single<ResponseEntity>

    @Query("DELETE FROM response_table")
    abstract fun clearData(): Completable

}