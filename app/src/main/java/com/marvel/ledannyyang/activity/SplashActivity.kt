package com.marvel.ledannyyang.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.retrofit.RetrofitClient
import com.marvel.ledannyyang.room.MyRoomDatabase
import com.marvel.ledannyyang.util.ConnectionUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext = Dispatchers.IO + Job()

    companion object{
        fun launchMainActivity(context: Context){
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
            val activity = context as Activity
            activity.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(ConnectionUtils.isOnline(this)){
            launch {
                RetrofitClient.getComicPreview(this@SplashActivity, 50)
            }
        }else{
            Handler().postDelayed({launchMainActivity(this)}, 2000)
        }
    }
}
