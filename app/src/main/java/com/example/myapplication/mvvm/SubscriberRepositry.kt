package com.example.myapplication.mvvm

import com.example.myapplication.db.Subscriber
import com.example.myapplication.db.SubscriberDao

class SubscriberRepositry(private val subscriberdao : SubscriberDao) {

    val getAllsubscribers = subscriberdao.getAllSubscribers()


    suspend fun insertSubscriber (subscriber: Subscriber){
        subscriberdao.insertSubscriber(subscriber)

    }

    suspend fun updateSubscriber (subscriber: Subscriber){
        subscriberdao.updateSubscriber(subscriber)

    }

    suspend fun deleteSubscriber (subscriber: Subscriber){
        subscriberdao.deleteSubscriber(subscriber)

    }

    suspend fun deleteAllSubscriber(){
        subscriberdao.deleteAll()
    }
}