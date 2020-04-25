package com.caiozed.videogamequiz.gamestart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.caiozed.videogamequiz.BR
import com.caiozed.videogamequiz.R
import com.caiozed.videogamequiz.databinding.ActivityGameStartBinding
import com.caiozed.videogamequiz.utils.openModal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class GameStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding  = DataBindingUtil
            .setContentView<ActivityGameStartBinding>(this, R.layout.activity_game_start)
        var viewModel = GameStartActivityViewModel(this)

        binding.setVariable(BR.viewModel, viewModel)
        viewModel.startSearch(this)

        GlobalScope.launch {
            while (viewModel!!.timeLeft!! > 0){
                viewModel!!.timeLeft = viewModel!!.timeLeft?.minus(1);
                Thread.sleep(1_000)
            }

            viewModel.view.runOnUiThread{
                var dialog = openModal(viewModel.view)
                dialog.show()

                dialog.findViewById<TextView>(R.id.points_txt)?.text = "${viewModel.points} points!"

                dialog.findViewById<Button>(R.id.restart_btn)?.setOnClickListener {
                    var intent = Intent(viewModel.view, GameStartActivity::class.java);
                    viewModel.view.startActivity(intent);
                    viewModel.view.finishAffinity();
                }
            }
        }
    }
}



