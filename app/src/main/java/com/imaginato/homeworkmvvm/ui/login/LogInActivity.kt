package com.imaginato.homeworkmvvm.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.imaginato.homeworkmvvm.R
import com.imaginato.homeworkmvvm.databinding.ActivityLogInBinding
import com.imaginato.homeworkmvvm.ui.demo.MainActivity
import com.imaginato.homeworkmvvm.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LogInActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoginActivityViewModel>()
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initObserve()
    }

    private fun clickListeners() {
        binding.button.setOnClickListener {
            viewModel.getLogIn()
        }
    }

    private fun initObserve() {
        viewModel.allUsers.observe(this, Observer { users ->
            if (users.isNotEmpty()) {
                startActivity(Intent(this,HomeActivity::class.java).putExtra("user", "Username: "+users[0].userName+" \nUser ID :"+users[0].userId))
                finish()
            }
        })
        viewModel.progress.observe(this, Observer {
            binding.pbLoading.isVisible = it
        })
        clickListeners()
    }
}