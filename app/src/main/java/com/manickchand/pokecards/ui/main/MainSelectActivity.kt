package com.manickchand.pokecards.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.manickchand.pokecards.databinding.ActivitySelectMainBinding
import com.manickchand.pokecards.ui.main.bygenericviewstate.HomeByGenericViewStateActivity
import com.manickchand.pokecards.ui.main.bylivedata.HomeByLiveDataActivity

class MainSelectActivity : AppCompatActivity() {

    private val binding: ActivitySelectMainBinding by lazy {
        ActivitySelectMainBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goToLiveDataMain.setOnClickListener {
            startActivity(Intent(this, HomeByLiveDataActivity::class.java))
        }

        binding.goToViewStateMain.setOnClickListener {
            //TODO
        }

        binding.goToGenericViewStateMain.setOnClickListener {
            startActivity(Intent(this, HomeByGenericViewStateActivity::class.java))
        }

        binding.goToInterfaceMain.setOnClickListener {
            //TODO
        }

    }
}