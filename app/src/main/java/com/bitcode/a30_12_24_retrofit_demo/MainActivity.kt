package com.bitcode.a30_12_24_retrofit_demo

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bitcode.a30_12_24_retrofit_demo.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(){
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var txtViewHello: TextView
    private lateinit var layoutInflater: LayoutInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater = getLayoutInflater()
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        txtViewHello = activityMainBinding.txtViewHello

        val usersService = UsersService.getInstance()

        activityMainBinding.btnFetchData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var response = usersService.fetchAllUsers(1)
                for(eachUser in response.users) {
                    Log.e("tag",eachUser.toString())
                }

                var user = usersService.fetchUser(3)
                Log.e("tag", user.toString() )
                Log.e("tag", "Message: " + user.user.email)
                
                withContext(Dispatchers.Main){
                    activityMainBinding.txtViewEmail.text = user.user.email

                    Glide.with(this@MainActivity)
                        .load(user.user.avatar)
                        .into(activityMainBinding.imgView1)
                }
            }
        }
    }
}