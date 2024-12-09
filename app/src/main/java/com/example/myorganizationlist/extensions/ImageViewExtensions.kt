package com.example.myorganizationlist.extensions

import android.widget.ImageView
import coil.load

fun ImageView.tryToLoadImage(url: String? = null){
    load(url){
        fallback(com.example.myorganizationlist.R.drawable.erro)
        error(com.example.myorganizationlist.R.drawable.erro)
        placeholder(com.example.myorganizationlist.R.drawable.loading_img_placeholder)
    }
}
