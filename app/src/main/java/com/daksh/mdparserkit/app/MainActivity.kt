package com.daksh.mdparserkit.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daksh.mdparserkit.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAppcompose.setOnClickListener {
            val intent = Intent(it.context, ComposeActivity::class.java)
            startActivity(intent)
        }
    }
}
