package com.dan.felnorforest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu);
    }

    fun onStartButtonPressed(view: View){
        val newIntent = Intent(this,MainActivity::class.java)

        startActivity(newIntent)
    }
}
