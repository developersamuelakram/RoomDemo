package com.example.myapplication.mvvm

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.db.Subscriber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepositry) : ViewModel() {


    // list of subscribers

    val subscriberslist = repository.getAllsubscribers

   private var isUpdated : Boolean = false
    private var updateId : Int = 0
    private  var selectedName : String = ""
    private  var selectedEmail : String = ""



    // storing inputs of edittexts

    val inputName  = MutableLiveData<String>()
    val inputEmail  = MutableLiveData<String>()

    // storing the values of button texts

    val saveOrUpdate = MutableLiveData<String>()
    val clearAllButton = MutableLiveData<String>()


    init {
        // dynaamically changing the values of buttons

        saveOrUpdate.value = "SAVE"
        clearAllButton.value = "Clear All"


    }


    fun insertOrUpdateSubscriber(){


        if (isUpdated == true){


            updateFunction()
        } else {


            val name = inputName.value
            val email = inputEmail.value

            insert(Subscriber(0, name!!, email!!))



            inputName.value = ""
            inputEmail.value = ""

        }

    }

    fun updateFunction(){



            // isUpdated = true

            val name = inputName.value
            val email = inputEmail.value

            update(Subscriber(updateId, name!!, email!!))

            inputName.value = ""
            inputEmail.value = ""

           saveOrUpdate.value = "SAVE"
           clearAllButton.value = "CLEAR ALL"




    }


    // must be done on background thread
    fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertSubscriber(subscriber)
    }

    // must be done on background thread
    fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateSubscriber(subscriber)
    }

    // must be done on background thread
    fun clear(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteSubscriber(subscriber)
    }

    fun clearAllOrDelete(){

        // frugally checking if button text is clear means
        // it is not a new text but an old text being udpated,
        if (clearAllButton.value == "Clear") {

            clear(Subscriber(updateId, selectedName, selectedEmail ))
            inputName.value = ""
            inputEmail.value = ""

        } else {

            clearAll()

        }



    }

    //  for deleting all
    fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllSubscriber()
    }

    fun selectedSubscriber(subscriber: Subscriber) {

        saveOrUpdate.value = "Update"
        clearAllButton.value = "Clear"

        inputName.value = subscriber.name
        inputEmail.value = subscriber.email


// setting the boolean that yes it has to be updated

        isUpdated = true

        // saving Id, name and email of selected user

        updateId = subscriber.id
         selectedName = subscriber.name
         selectedEmail = subscriber.email



    }

}