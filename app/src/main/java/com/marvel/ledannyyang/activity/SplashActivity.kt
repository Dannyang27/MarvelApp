package com.marvel.ledannyyang.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.retrofit.RetrofitClient

class SplashActivity : AppCompatActivity() {

    companion object{
        fun launchMainActivity(context: Context){
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        RetrofitClient.getComicPreview(this)
    }
}
