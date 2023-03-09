package com.example.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {


    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber)

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)


    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)


    @Query("DELETE FROM Subscriber_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM Subscriber_table")
    fun getAllSubscribers(): LiveData<List<Subscriber>>




}