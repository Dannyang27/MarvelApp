package com.marvel.ledannyyang.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.marvel.ledannyyang.R
import com.marvel.ledannyyang.divider.HorizontalDivider
import com.marvel.ledannyyang.listadapter.ComicAdapter
import com.marvel.ledannyyang.util.ConnectionUtils
import org.jetbrains.anko.toast

class ComicFragment : Fragment(){
    private lateinit var swipeRefresh: SwipeRefreshLayout

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_comic, container, false)

        swipeRefresh = view.findViewById(R.id.comic_swiperefresh)
        gridLayoutManager = GridLayoutManager(activity, 1)
        viewAdapter = ComicAdapter(gridLayoutManager, mutableListOf("1,","2","3","1,","2","3","1,","2","3"))
        decorator = HorizontalDivider(activity?.applicationContext!!)

        comicList = view.findViewById<RecyclerView>(R.id.comic_list).apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addItemDecoration(decorator)
            adapter = viewAdapter

            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val isBottomReached = !recyclerView.canScrollVertically(1)
                    if (isBottomReached){
                        context.toast("Bottom reached")
                    }
                }
            })
        }

        swipeRefresh.setOnRefreshListener {
            if(ConnectionUtils.isConnectedToNetwork(activity?.applicationContext!!)){
                context?.toast("Connected to Internet")
            }else{
                context?.toast("Not connected to Internet")
            }
            swipeRefresh.isRefreshing = false
        }

        return view
    }
}