package com.dan.felnorforest

import android.view.View
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

/**
 */
abstract class RecyclerItem: Serializable{}

class Message(messageJSON: JSONObject, clickHandler: (View?,Int) -> Unit): RecyclerItem() {

    public lateinit var message: String
    public var decision: Decision? = null
    public var destId: String? = null
    public var timeDelay: String? =null

    init {
        try {
            message = messageJSON.getString("message")
            if (!messageJSON.isNull("decision")) {
                decision = Decision(messageJSON.getJSONObject("decision"), clickHandler)
            }
            destId = messageJSON.getString("destId")
            timeDelay = messageJSON.getString("timeDelay")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    override fun toString(): String {
        return message
    }
}

class Decision(decisionJSON: JSONObject, clickHandler: (View?,Int)->Unit): RecyclerItem() {

    lateinit var choice1: Choice
    lateinit var choice2: Choice

    init {
        try{
            choice1 = Choice(decisionJSON.getJSONObject("choice1"),clickHandler)
            choice2 = Choice(decisionJSON.getJSONObject("choice2"),clickHandler)
        } catch (e: JSONException){
            e.printStackTrace()
        }
    }

    override fun toString(): String {
        return choice1.message+":"+choice2.message

    }

}

class Choice(choiceJSON: JSONObject, handlerFunction: (View?,Int) -> Unit): Serializable {

    lateinit var message:String
    lateinit var destId:String
    lateinit var clickHandler: ChoiceClickListener

    init {
        try {
            message = choiceJSON.getString("message")
            destId = choiceJSON.getString("destId")
            clickHandler = ChoiceClickListener(destId.toInt(), handlerFunction)
        } catch (e:JSONException){
            e.printStackTrace()
        }
    }

}

class ChoiceClickListener(var destId: Int, var body: (View?,Int)->Unit) : View.OnClickListener{

    override fun onClick(v: View?) {
        body(v,destId)
    }

}