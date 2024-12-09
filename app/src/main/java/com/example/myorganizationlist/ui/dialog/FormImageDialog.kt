package com.example.myorganizationlist.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.myorganizationlist.databinding.ImageFormBinding
import com.example.myorganizationlist.extensions.tryToLoadImage

class FormImageDialog(private val context: Context) {

    fun show(currentUrl: String? = null, whenImageLoaded: (imagem: String)-> Unit ){

        val binding = ImageFormBinding.inflate(LayoutInflater.from(this.context))

        currentUrl?.let{
            binding.imageFormImageView.tryToLoadImage(it)
            binding.imageFormImputImgUrl.setText(it)
        }

        binding.imageFormButtomLoad.setOnClickListener{
            val url = binding.imageFormImputImgUrl.text.toString()
            binding.imageFormImageView.tryToLoadImage(url)
        }

            AlertDialog.Builder(this.context)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = binding.imageFormImputImgUrl.text.toString()
                    whenImageLoaded(url)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()


    }
}