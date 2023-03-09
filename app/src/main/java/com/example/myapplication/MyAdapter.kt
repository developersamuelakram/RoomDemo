package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.db.Subscriber


class MyAdapter(private val subscriberList: List<Subscriber>,  private val clickListener : (Subscriber)->Unit) : RecyclerView.Adapter<MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val inflate = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(inflate, R.layout.list_item, parent, false)
        return MyHolder(binding)


    }

    override fun getItemCount(): Int {
      return  subscriberList.size

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.bindTheView(subscriberList[position], clickListener)
    }


}

class MyHolder(val binding: ListItemBinding) : ViewHolder(binding.root){


      fun bindTheView(subscriber: Subscriber, clickListener : (Subscriber)->Unit){

          binding.subnametv.text = subscriber.name

          binding.subemailtv.text = subscriber.email

          binding.listItemLayout.setOnClickListener{

              clickListener(subscriber)

          }

      }


}