package com.dan.felnorforest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.*


class MainActivity : AppCompatActivity() {

    private var visibleMessagesList: ArrayList<Message> = ArrayList()
    private var messagesList: ArrayList<Message> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: MainViewRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager=linearLayoutManager
        adapter = MainViewRecyclerAdapter(visibleMessagesList)
        recyclerView.adapter = adapter

        var json: JSONObject = parseJson(R.raw.narration)
        var i: Int = 1
        while (json.has(i.toString())){
            var message:JSONObject = json.getJSONObject(i.toString())
            messagesList.add(Message(message))
            Log.d("MainActivity", messagesList.get(i-1).message)
            i++
        }
    }

    override fun onStart() {
        super.onStart()
        visibleMessagesList.addAll(messagesList)
        adapter.notifyDataSetChanged()
    }

    fun parseJson(resource: Int): JSONObject {
        var inStream: InputStream = resources.openRawResource(resource)
        var writer: Writer = StringWriter()
        var buffer: CharArray = kotlin.CharArray(1024)
        try {
            var reader: Reader = BufferedReader(InputStreamReader(inStream, "UTF-8"))
            var n: Int = reader.read(buffer);
            while (n != -1) {
                writer.write(buffer, 0, n);
                n = reader.read(buffer)
            }
        } finally {
            inStream.close();
        }

        var jsonString : String  = writer.toString();

        return JSONObject(jsonString)
    }

}
