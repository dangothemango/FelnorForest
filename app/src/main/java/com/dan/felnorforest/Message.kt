package com.dan.felnorforest

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

/**
 */
class Message(messageJSON: JSONObject): Serializable {

    public lateinit var message: String
    public var decision: Decision? = null
    public var destId: String? = null
    public var timeDelay: String? =null

    init {
        try {
            message = messageJSON.getString("message")
            if (!messageJSON.isNull("decision")) {
                decision = Decision(messageJSON.getJSONObject("decision"))
            }
            destId = messageJSON.getString("destId")
            timeDelay = messageJSON.getString("timeDelay")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
}

class Decision(decisionJSON: JSONObject): Serializable {

    public lateinit var choice1: Choice
    public lateinit var choice2: Choice

    init {
        try{
            choice1 = Choice(decisionJSON.getJSONObject("choice1"))
            choice2 = Choice(decisionJSON.getJSONObject("choice2"))
        } catch (e: JSONException){
            e.printStackTrace()
        }
    }

}

class Choice(choiceJSON: JSONObject): Serializable {

    public lateinit var message:String
    public lateinit var destId:String

    init {
        try {
            message = choiceJSON.getString("message")
            destId = choiceJSON.getString("destId")
        } catch (e:JSONException){
            e.printStackTrace()
        }
    }

}