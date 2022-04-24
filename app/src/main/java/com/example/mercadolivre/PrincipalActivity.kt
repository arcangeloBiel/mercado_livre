package com.example.mercadolivre

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.mercadolivre.data.model.Produto
import com.example.mercadolivre.presentation.adapter.ProdutoAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class PrincipalActivity : AppCompatActivity() {

    lateinit var slider: ImageSlider
    private val db = Firebase.firestore
    lateinit var recycler: RecyclerView

    private val produtoAdapter by lazy { ProdutoAdapter(mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        inicializarslider()
        getProduto()
//        val recCharacters by lazy { findViewById<RecyclerView>(R.id.recyclerViewListaProdutos) }
//        recCharacters.adapter = produtoAdapter
    }

    fun getProduto() {
        try {
            db.collection("produto_teste")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val myMap = document.data.map {
                            it
                        }.toMutableList()
                        initRecyclerView(myMap as MutableList<Produto>)

                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("teste", "Error getting documents.", exception)
                }

        } catch (ex: Exception) {
            Log.w("teste", "Error getting documents.", ex)
        }
    }

    fun initRecyclerView(prod: MutableList<Produto>) {
        val recycler by lazy { findViewById<RecyclerView>(R.id.recyclerViewListaProdutos) }
        recycler.adapter = produtoAdapter
        produtoAdapter.setDataSet(prod)
        recycler.layoutManager = LinearLayoutManager(this)
        produtoAdapter.notifyDataSetChanged()

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

