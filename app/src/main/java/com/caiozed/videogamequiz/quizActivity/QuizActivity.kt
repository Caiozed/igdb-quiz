package com.caiozed.videogamequiz.quizActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.caiozed.videogamequiz.BR
import com.caiozed.videogamequiz.R
import com.caiozed.videogamequiz.databinding.ActivityQuizBinding
import com.caiozed.videogamequiz.models.Game
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val game = intent.extras?.get("game") as Game

        val binding = DataBindingUtil
            .setContentView<ActivityQuizBinding>(this,
                R.layout.activity_quiz
            )

        var viewModel = QuizActivityViewModel()
        viewModel.game = game

        viewModel.createQuestion(arrayOf(option1.id, option2.id, option3.id, option4.id))
        binding.setVariable(BR.viewModel, viewModel)
    }
}
