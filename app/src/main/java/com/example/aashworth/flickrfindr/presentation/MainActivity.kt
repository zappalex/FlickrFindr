package com.example.aashworth.flickrfindr.presentation

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.example.aashworth.flickrfindr.InjectorUtils
import com.example.aashworth.flickrfindr.R
import com.example.aashworth.flickrfindr.data.models.Photo
import com.example.aashworth.flickrfindr.presentation.adapters.PhotoSearchAdapter
import com.example.aashworth.flickrfindr.presentation.viewmodels.PhotoSearchViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), PhotoSearchAdapter.PhotoSearchAdapterOnClickHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: PhotoSearchAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var viewModel: PhotoSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUi()
    }

    private fun initializeUi() {
        viewModel = PhotoSearchViewModel(InjectorUtils.getRepository())
        viewAdapter = PhotoSearchAdapter(this)
        layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.photos_recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = viewAdapter
        setSearchTermListener()
    }

    private fun setSearchTermListener() {
        search_term_edittext.setOnEditorActionListener() { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addPhotosToViewAdapter(v)
            }
            // we return false each time to ensure keyboard is removed
            false
        }
    }

    private fun addPhotosToViewAdapter(view: TextView) {
        viewModel.getPhotosForSearchTerm(view.text.toString()).observe(this@MainActivity, Observer { photos ->
            viewAdapter.setPhotoList(photos)
            recyclerView.scrollToPosition(0)
        })
    }

    override fun onClick(photo: Photo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
