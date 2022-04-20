package com.example.mercadolivre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PrincipalActivity : AppCompatActivity() {

    lateinit var slider: ImageSlider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        inicializarslider()
    }

    fun inicializarslider() {
        slider = findViewById(R.id.image_slider)
        val listaImagens = ArrayList<SlideModel>()
        listaImagens.add(SlideModel(R.drawable.promocao01, ScaleTypes.CENTER_CROP))
        listaImagens.add(SlideModel(R.drawable.promocao02, ScaleTypes.CENTER_CROP))
        listaImagens.add(SlideModel(R.drawable.promocao03, ScaleTypes.CENTER_CROP))
        slider.setImageList(listaImagens)
    }

    fun deslogar(view: View) {
        val autenticacao = Firebase.auth
        autenticacao.signOut()
        finish()
        startActivity(
            Intent(
                this, MainActivity::class.java
            )
        )
    }
}