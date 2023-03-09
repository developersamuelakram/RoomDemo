package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.Subscriber
import com.example.myapplication.db.SubscriberDataBase
import com.example.myapplication.mvvm.SubscriberFactoryViewModel
import com.example.myapplication.mvvm.SubscriberRepositry
import com.example.myapplication.mvvm.SubscriberViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var myAdapter: MyAdapter
    lateinit var viewModel : SubscriberViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDataBase.getInstance(applicationContext).subscriberDao
        val repositry = SubscriberRepositry(dao)
        val factory = SubscriberFactoryViewModel(repositry)
        viewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initRecyclerView()


    }

    fun initRecyclerView(){

        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel.subscriberslist.observe(this, Observer {

            myAdapter = MyAdapter(it, {selectedItem: Subscriber->onClickedSubscriber(selectedItem)})
            binding.recyclerView.adapter = myAdapter


        })

    }

    private fun onClickedSubscriber(subscriber: Subscriber){

        viewModel.selectedSubscriber(subscriber)
        Toast.makeText(this, " Subscriber name is ${subscriber.name} \n Email is ${subscriber.email}", Toast.LENGTH_SHORT).show()

    }
}