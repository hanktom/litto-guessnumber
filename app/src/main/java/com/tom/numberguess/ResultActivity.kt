package com.tom.numberguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tom.numberguess.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //get result
        val times = intent.getIntExtra("TIMES", -1)
        binding.result.text = times.toString()
        binding.button.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}