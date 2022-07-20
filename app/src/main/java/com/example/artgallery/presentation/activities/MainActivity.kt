package com.example.artgallery.presentation.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.artgallery.presentation.adapters.GalleryImageAdapter
import com.example.artgallery.R
import com.example.artgallery.presentation.adapters.GalleryImageClickListener
import com.example.artgallery.presentation.adapters.Image
import com.example.artgallery.presentation.fragment.GalleryFullscreenFragment
import kotlinx.android.synthetic.main.recyclerview.*
import java.util.*

@Suppress("PrivatePropertyName", "MemberVisibilityCanBePrivate", "UsePropertyAccessSyntax")
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
        imageList.add(Image("https://i.postimg.cc/x8DZmMMm/Surf.jpg", "Первый день в Surf","Сегодня был мой первый день в студии Surf..."))
        imageList.add(Image("https://i.postimg.cc/rsNftb86/image.jpg", "Самый милый корги",""))
        imageList.add(Image("https://i.postimg.cc/cC0mZmfF/image.jpg", "Печенья которые приг... ",""))
        imageList.add(Image("https://i.postimg.cc/76G6h8n6/image.jpg", "Чашечка свежего кофе","Для бариста и посетителей кофеен специальные кружки для кофе — это ещё один способ проконтролировать вкус напитка и приготовить его именно так, как нравится вам.\n" +
                "\n" +
                "Теперь, кроме регулировки экстракции, настройки помола, времени заваривания и многого что помогает выделять нужные характеристики кофе, вы сможете выбрать и кружку для кофе в зависимости от сорта."))
        imageList.add(Image("https://i.postimg.cc/c4NHxzgx/image.jpg", "Карта в поезде",""))
        imageList.add(Image("https://i.postimg.cc/253j9RJw/image.jpg", "Карта навигатор",""))

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