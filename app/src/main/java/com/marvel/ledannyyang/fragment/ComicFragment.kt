package com.marvel.ledannyyang.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.divider.HorizontalDivider
import com.marvel.ledannyyang.listadapter.ComicAdapter
import com.marvel.ledannyyang.model.Comic
import com.marvel.ledannyyang.room.MyRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicFragment : Fragment(){
    private val comics = mutableListOf<Comic>()
    private var roomDatabase: MyRoomDatabase? = null

    private lateinit var noitemLayout: LinearLayout

    companion object{
        var gridLayoutManager: GridLayoutManager? = null
        lateinit var comicList : RecyclerView
        lateinit var viewAdapter: ComicAdapter
        lateinit var decorator: HorizontalDivider

        fun newInstance(): ComicFragment = ComicFragment()

        fun changeSpanCount(){
            if(gridLayoutManager?.spanCount == 1){
                gridLayoutManager?.spanCount = 3
                comicList.removeItemDecoration(decorator)
            }else{
                gridLayoutManager?.spanCount = 1
                comicList.addItemDecoration(decorator)
            }

            viewAdapter.notifyItemRangeChanged(0, viewAdapter.itemCount)
        }

        fun updateList(comics: MutableList<Comic>){
            viewAdapter.updateList(comics)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_comic, container, false)

        noitemLayout = view.findViewById(R.id.comic_layout)
        gridLayoutManager = GridLayoutManager(activity, 1)
        viewAdapter = ComicAdapter(gridLayoutManager, comics)
        decorator = HorizontalDivider(activity?.applicationContext!!)

        comicList = view.findViewById<RecyclerView>(R.id.comic_list).apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addItemDecoration(decorator)
            adapter = viewAdapter
        }

        CoroutineScope(Dispatchers.IO).launch {
            roomDatabase = MyRoomDatabase.getMyRoomDatabase(activity?.applicationContext!!)
            val comics = roomDatabase?.getAllComicPreview()

            withContext(Dispatchers.Main){
                if(comics?.isEmpty()!!){
                    noitemLayout.visibility = View.VISIBLE
                }
            }

            updateList(comics!!)
        }

        return view
    }
}