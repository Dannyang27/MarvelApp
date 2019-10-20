package com.marvel.ledannyyang.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.retrofit.RetrofitClient
import com.marvel.ledannyyang.util.ConnectionUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(ConnectionUtils.isOnline(this)){
            CoroutineScope(Dispatchers.IO).launch {
                RetrofitClient.getLatestComicsIfModified(this@SplashActivity)
                launchMainActivity(this@SplashActivity)
            }
        }else{
            Handler().postDelayed({
                launchMainActivity(this)
            }, 2000)
        }
    }

    private fun launchMainActivity(context: Context){
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish()
    }
}
