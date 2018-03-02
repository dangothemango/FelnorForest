package com.dan.felnorforest

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.message_recycler_row.view.*

/**
 *
 */

class MainViewRecyclerAdapter(private val messages: ArrayList<Message>): RecyclerView.Adapter<MainViewRecyclerAdapter.MessageHolder>() {

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder:MainViewRecyclerAdapter.MessageHolder, position: Int) {
        val itemMessage = messages[position]
        holder.bindMessage(itemMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewRecyclerAdapter.MessageHolder{
        val inflatedView = parent.inflate(R.layout.message_recycler_row, false)
        return MessageHolder(inflatedView)
    }

    class MessageHolder(v:View):RecyclerView.ViewHolder(v),View.OnClickListener{
        private var view: View = v
        private var message: Message? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindMessage(message: Message){
            this.message = message

            view.message.text = message.message
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView","CLICK!")
        }

        companion object {
            private val MESSAGE_KEY = "MESSAGE"
        }
    }
}