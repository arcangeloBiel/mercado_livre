package com.example.mercadolivre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastroActivity : AppCompatActivity() {

    lateinit var editEmail: EditText
    lateinit var editSenha: EditText
    lateinit var autenticacao: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        inicializaComponentesInterface()
    }

    fun criarConta(view: View) {
        if (validarCampos()) {
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()
            autenticacao.createUserWithEmailAndPassword(
                email, senha
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show()
                    finish()
                    startActivity(
                        Intent(
                            this, PrincipalActivity::class.java
                        )
                    )
                } else {
                    Toast.makeText(this, "Erro ao cadastrar ao usuário", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun validarCampos(): Boolean {
        if (editEmail.text.isNotEmpty() && editSenha.text.isNotEmpty()) {
            return true
        }
        return false
    }

    fun inicializaComponentesInterface() {
        editEmail = findViewById(R.id.idEditEmail)
        editSenha = findViewById(R.id.idEditSenha)
        autenticacao = Firebase.auth
    }

}