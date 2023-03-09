package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDataBase : RoomDatabase() {
    // declaring abstract reference for the interface

    abstract  val subscriberDao: SubscriberDao

    // singleton instances

    companion object{
        // THIS MAKES THE FIELD IMMEDIATELY VISIBILE TO OTHER THREAD
        @Volatile
        private var INSTANCE : SubscriberDataBase? = null
        fun getInstance (context: Context) : SubscriberDataBase{
            synchronized(this){
                var instance  = INSTANCE

                if (instance == null){

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDataBase::class.java,
                        "subscriber_data_database"


                    ).build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }


}