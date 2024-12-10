package com.example.myorganizationlist.ui.activitys

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myorganizationlist.database.AppDatabase
import com.example.myorganizationlist.databinding.ActivityRegistarNewUserBinding
import com.example.myorganizationlist.model.User
import kotlinx.coroutines.launch

class RegistarNewUserActivity : AppCompatActivity() {

    private val dao by lazy {
        AppDatabase.instancia(this).UserDao()
    }
    private val binding by lazy {
        ActivityRegistarNewUserBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "Cadastre-se"

        val registerButton = binding.activityRegistarNewUserRegisterButtom

        registerButton.setOnClickListener {
            criarUsuarioIfNotExist()
        }

    }

    private fun criarUsuarioIfNotExist(){

        val email = binding.activityRegistarNewUserEmailImput.text.toString()
        val name = binding.activityRegistarNewUserNameImput.text.toString()
        val password = binding.activityRegistarNewUserPasswordImput.text.toString()

        lifecycleScope.launch {
            dao.findByEmail(email).let {
                if(it == null){
                    dao.insertAll(User(name=name, email=email, password=password))
                    Log.i("USER_ACCESS", "CADASTRADO")
                    finish()
                }else{
                    Toast.makeText(
                        this@RegistarNewUserActivity,
                        "Usuário Já existe!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.i("USER_ACCESS", "USUARIO EXISTE")
                }
            }
        }
    }
}