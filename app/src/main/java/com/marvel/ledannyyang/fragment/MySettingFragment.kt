package com.marvel.ledannyyang.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.room.MyRoomDatabase
import org.jetbrains.anko.toast

class MySettingFragment : PreferenceFragmentCompat(){
    private lateinit var clearList: Preference
    private lateinit var deleteCache: Preference
    private lateinit var sendFeedback: Preference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_setting, rootKey)
        clearList  = findPreference("comicList") as Preference
        deleteCache = findPreference("cache") as Preference
        sendFeedback = findPreference("feedback") as Preference

        clearList.setOnPreferenceClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(getString(R.string.delete_watchlist))
            builder.setMessage(getString(R.string.delete_sure))

            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, which ->
                clearList.context.toast(getString(R.string.list_clear))
                MyRoomDatabase.getMyRoomDatabase(clearList.context)?.clearFavourites()
                true
            })

            builder.setNegativeButton("Cancel", null)
            val dialog = builder.create()
            dialog.show()

            true
        }

        deleteCache.setOnPreferenceClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle(getString(R.string.delete_cache))
            builder.setMessage(getString(R.string.delete_sure_cache))

            builder.setPositiveButton("Continue", DialogInterface.OnClickListener { dialog, which ->
                deleteCache.context.cacheDir.deleteRecursively()
                deleteCache.context.toast(getString(R.string.deleteCache))
                true
            })

            builder.setNegativeButton("Cancel", null)
            val dialog = builder.create()
            dialog.show()

            true
        }

        sendFeedback.setOnPreferenceClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.owner_email), null))
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.marvelapp_feedback))
            startActivity(Intent.createChooser(intent, getString(R.string.send_email)))
            true
        }
    }
}