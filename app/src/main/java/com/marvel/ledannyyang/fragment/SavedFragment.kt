package com.marvel.ledannyyang.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.divider.HorizontalDivider
import com.marvel.ledannyyang.listadapter.ComicAdapter
import com.marvel.ledannyyang.model.Comic
import com.marvel.ledannyyang.room.MyRoomDatabase
import kotlinx.coroutines.*

class SavedFragment : Fragment(), CoroutineScope{
    override val coroutineContext = Dispatchers.IO + Job()
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var noitemLayout: LinearLayout
    private val savedComics = mutableListOf<Comic>()
    private var roomDatabase: MyRoomDatabase? = null

    companion object{
        var gridLayoutManager: GridLayoutManager? = null
        lateinit var comicList : RecyclerView
        lateinit var viewAdapter: ComicAdapter
        lateinit var decorator: HorizontalDivider

        fun newInstance(): SavedFragment = SavedFragment()

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
        val view =  inflater.inflate(R.layout.fragment_saved, container, false)

        swipeRefresh = view.findViewById(R.id.saved_swiperefresh)
        noitemLayout = view.findViewById(R.id.favourite_layout)
        gridLayoutManager = GridLayoutManager(activity, 1)
        viewAdapter = ComicAdapter(gridLayoutManager, savedComics)
        decorator = HorizontalDivider(activity?.applicationContext!!)

        comicList = view.findViewById<RecyclerView>(R.id.saved_list).apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addItemDecoration(decorator)
            adapter = viewAdapter
        }

        swipeRefresh.setOnRefreshListener {
            launch {
                val favComics = roomDatabase?.getFavouriteComics()

                withContext(Dispatchers.Main){
                    if(favComics?.isNotEmpty()!!){
                        noitemLayout.visibility = View.GONE
                    }else{
                        noitemLayout.visibility = View.VISIBLE
                    }
                }

                viewAdapter.updateList(favComics!!)
            }
            swipeRefresh.isRefreshing = false
        }

        launch {
            roomDatabase =  MyRoomDatabase.getMyRoomDatabase(activity?.applicationContext!!)
            val favComics = roomDatabase?.getFavouriteComics()

            if(favComics.isNullOrEmpty()){
                withContext(Dispatchers.Main){
                    noitemLayout.visibility = View.VISIBLE
                }
            }

            viewAdapter.updateList(favComics!!)
        }

        return view
    }
}