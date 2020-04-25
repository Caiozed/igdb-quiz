package com.caiozed.videogamequiz.mainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.caiozed.videogamequiz.R
import com.caiozed.videogamequiz.BR
import com.caiozed.videogamequiz.databinding.ActivityMainBinding
import com.caiozed.videogamequiz.gamestart.GameStartActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this,
                R.layout.activity_main
            )

        var viewModel = MainActivityViewModel()
        binding.setVariable(BR.viewModel, viewModel)

        start_btn.setOnClickListener {
            startActivity(Intent(this, GameStartActivity::class.java))
        }

    }
}
