package com.example.myorganizationlist.ui.activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myorganizationlist.database.AppDatabase
import com.example.myorganizationlist.databinding.ActivityLoginAccessBinding
import kotlinx.coroutines.launch

class LoginUserActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginAccessBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDatabase.instancia(this).UserDao()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title=" Olá faça seu login "
        val loginButton = binding.contentLoginUserEmailAcessButtom
        val registerButton = binding.contentLoginUserEmailNewuserButtom

        loginButton.setOnClickListener{

            lifecycleScope.launch {
                val email = binding.contentLoginUserEmailImput.text.toString()
                val password = binding.contentLoginUserEmailPasswordImput.text.toString()

                Log.i("USER_ACCESS", "EMAIL - $email")
                Log.i("USER_ACCESS", "PASSWORD - $password")
                dao.findByEmail(email).let {
                    if(it!=null){
                        Log.i("USER_ACCESS", "USUARIO EXISTE")
                    }else{
                        Log.i("USER_ACCESS", "USUARIO NÃO EXISTE")
                    }
                }


            }
        }
        registerButton.setOnClickListener{
            this.sendToRegisterActivity()
        }
    }

    private fun sendToRegisterActivity(){
        val intent = Intent(this, RegistarNewUserActivity::class.java)
        startActivity(intent)
    }

}