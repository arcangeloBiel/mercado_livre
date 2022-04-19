package com.example.mercadolivre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
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