package com.marvel.ledannyyang.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.fragment.ComicFragment
import com.marvel.ledannyyang.fragment.SavedFragment

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    val comicFragment = ComicFragment.newInstance()
    val savedFragment = SavedFragment.newInstance()

    var activeFragment: Fragment = comicFragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.now_playing_item -> {
                toolbar.title = getString(R.string.comic_title)
                openFragment(comicFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.upcoming_item -> {
                toolbar.title = getString(R.string.saved)
                openFragment(savedFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.main_toolbar)
        toolbar.title = getString(R.string.comic_title)

        setSupportActionBar(toolbar)
        val navigationBar = findViewById<BottomNavigationView>(R.id.navigationBar)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction().add(R.id.container, savedFragment, "2").hide(savedFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.container, comicFragment, "1").commit()
    }

    override fun onBackPressed() {
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId){
        R.id.preview -> {
            if(item.icon.constantState == getDrawable(R.drawable.grid).constantState){
                item.icon = getDrawable(R.drawable.list)
            }else{
                item.icon = getDrawable(R.drawable.grid)
            }

            ComicFragment.changeSpanCount()
            SavedFragment.changeSpanCount()
            true
        }

        R.id.settings -> {
            startActivity(Intent(this, SettingActivity::class.java))
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}
