package com.dan.felnorforest

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.message_recycler_row.view.*
import kotlinx.android.synthetic.main.decision_recycler_row.view.*

/**
 *
 */

class MainViewRecyclerAdapter(private val messages: ArrayList<RecyclerItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        if (messages[position] is Message){
            return 0
        } else {
            return 1
        }
    }

    override fun onBindViewHolder(holder:RecyclerView.ViewHolder, position: Int) {
        val itemMessage = messages[position]
        if (itemMessage is Decision){
            val castedHolder: DecisionHolder = holder as DecisionHolder
            castedHolder.bindDecision(itemMessage)
        } else {
            val castedHolder: MessageHolder = holder as MessageHolder
            castedHolder.bindMessage(itemMessage as Message)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        when (viewType){
            0->{
                val inflatedView = parent.inflate(R.layout.message_recycler_row, false)
                return MessageHolder(inflatedView)
            }
            1->{
                val inflatedView = parent.inflate(R.layout.decision_recycler_row, false)
                return DecisionHolder(inflatedView)
            }
            else->{
                throw Exception("Item is not of a valid type")
            }
        }
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

    class DecisionHolder(v: View):RecyclerView.ViewHolder(v),View.OnClickListener{

        private var view: View = v
        private var choice1: Choice? = null
        private var choice2: Choice? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindDecision(decision: Decision){
                view.choice1Button.text = decision.choice1.message
                view.choice2Button.text = decision.choice2.message
        }

        override fun onClick(v: View?) {
            Log.d("RecyclerView","CLICK!")
        }

        companion object {
            private val DECISION_KEY = "DECISION"
        }
    }
}