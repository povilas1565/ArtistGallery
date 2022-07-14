package com.example.artgallery.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.artgallery.adapters.GalleryImageAdapter
import com.example.artgallery.R

import com.example.artgallery.adapters.GalleryImageClickListener
import com.example.artgallery.adapters.Image
import com.example.artgallery.fragments.GalleryFullscreenFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), GalleryImageClickListener {

    // gallery column count
    private val SPAN_COUNT = 2

    private val imageList = ArrayList<Image>()
    lateinit var galleryAdapter: GalleryImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init adapter
        galleryAdapter = GalleryImageAdapter(imageList)
        galleryAdapter.listener = this

        // init recyclerview
        recyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        recyclerView.adapter = galleryAdapter

        // load images
        loadImages()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadImages() {


        galleryAdapter.notifyDataSetChanged()
    }

    override fun onClick(position: Int) {
        // handle click of image

        val bundle = Bundle()
        bundle.putSerializable("images", imageList)
        bundle.putInt("position", position)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val galleryFragment = GalleryFullscreenFragment()
        galleryFragment.setArguments(bundle)
        galleryFragment.show(fragmentTransaction, "gallery")
    }
}