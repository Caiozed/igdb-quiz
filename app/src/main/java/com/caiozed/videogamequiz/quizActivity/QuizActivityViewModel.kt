package com.caiozed.videogamequiz.quizActivity

import android.R
import android.util.Log.d
import android.widget.RadioGroup
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.caiozed.videogamequiz.BR
import com.caiozed.videogamequiz.IGDBService
import com.caiozed.videogamequiz.gamestart.GameStartActivity
import com.caiozed.videogamequiz.gamestart.GameStartActivityViewModel
import com.caiozed.videogamequiz.models.Game
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlin.math.round
import kotlin.random.Random


class QuizActivityViewModel : BaseObservable() {
    var igdbService = IGDBService()

    @get:Bindable
    var game: Game? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.game)
        }

    @get:Bindable
    var question: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.question)
        }

    @get:Bindable
    var answer: Int? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.answer)
        }


    @get:Bindable
    var options: Array<Pair<Int,String>>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.options)
        }

    fun onRadioChanged(radioGroup: RadioGroup?, id: Int) {
        if (radioGroup!!.checkedRadioButtonId == answer!!){
            GameStartActivityViewModel.instance.points++
        }
        GameStartActivityViewModel.instance.startSearch(GameStartActivityViewModel.instance.view)
        (radioGroup.context as QuizActivity).finish()
    }

    fun createQuestion(optionsIds: Array<Int>){
        question = "What's the original rating for this game ?"
        options = arrayOf(Pair<Int, String>(optionsIds[0], (Random.nextInt(0,100)).toString()),
            Pair<Int, String>(optionsIds[1],(Random.nextInt(0,100)).toString()),
            Pair<Int, String>(optionsIds[2],(Random.nextInt(0,100)).toString()),
            Pair<Int, String>(optionsIds[3],(Random.nextInt(0,100)).toString()))

        var selectedIndex = (Random.nextInt(0,3))
        options!![selectedIndex] = Pair<Int, String>(optionsIds[selectedIndex], game!!.rating.toInt().toString())
        answer =  options!![selectedIndex].first
    }

}