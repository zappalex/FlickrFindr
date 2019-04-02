package com.example.aashworth.flickrfindr.presentation

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import com.example.aashworth.flickrfindr.InjectorUtils
import com.example.aashworth.flickrfindr.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val viewModel = PhotoSearchViewModel(InjectorUtils.getRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUi()
    }

    private fun initializeUi() {
        setSearchTermListener()
    }

    private fun setSearchTermListener() {
        search_term.setOnEditorActionListener() { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.getPhotosForSearchTerm(v.text.toString()).observe(this@MainActivity, Observer { photos ->
                    Log.d("TEsting", "Twerking")
                    // TODO: Set Grid View Here
                    photos?.forEach { photo ->
                        Log.d("Path", photo.fullPhotoUrl)
                    }
                })
            }
            false
        }
    }

}
