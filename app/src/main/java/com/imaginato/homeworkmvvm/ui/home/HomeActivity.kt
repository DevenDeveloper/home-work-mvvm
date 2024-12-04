package com.imaginato.homeworkmvvm.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.imaginato.homeworkmvvm.data.local.login.LogInDatabase
import com.imaginato.homeworkmvvm.databinding.ActivityHomeBinding
import com.imaginato.homeworkmvvm.ui.login.LogInActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewUserName.text = intent.getStringExtra("user")

        binding.button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val database: LogInDatabase by inject()
                database.clearAllTables()
                startActivity(Intent(this@HomeActivity, LogInActivity::class.java))
                finish()
            }
        }
    }
}