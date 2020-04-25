package com.caiozed.videogamequiz.gamestart

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.caiozed.videogamequiz.IGDBService
import com.caiozed.videogamequiz.BR
import com.caiozed.videogamequiz.databinding.ActivityGameStartBinding
import com.caiozed.videogamequiz.models.Game
import kotlin.random.Random

class GameStartActivityViewModel(var view: GameStartActivity) : BaseObservable() {
    var igdbService = IGDBService()
    companion object {
        lateinit var instance: GameStartActivityViewModel
    }

    init {
        instance = this
    }

    @get:Bindable
    var games:  Array<Game>? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.games)
        }

    @get:Bindable
    var selectedGame: Game? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.selectedGame)
        }

    @get:Bindable
    var timeLeft: Int = 120
        set(value) {
            field = value
            notifyPropertyChanged(BR.timeLeft)
        }

    @get:Bindable
    var points: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.points)
        }

    suspend fun searchForGames(){
        var lastGameAdded = igdbService.getRandomGames("""fields id;
        sort id desc;
        limit 1;""")?.get(0)

        var randomID: MutableList<Int> = mutableListOf<Int>()

        (1..4).forEach {
            randomID.add(Random.nextInt(0, lastGameAdded.id))
        }

        games = igdbService.getRandomGames("""fields name, id, cover, rating;
        where id = (${randomID?.joinToString(",")});""")

        var covers = igdbService.getCovers(games?.map { it.cover })
        covers.forEach {
            var gameId = it.game
            var game = games?.filter { it.id == gameId}?.get(0)
            if(game != null) {
                game!!.coverData = it
            }
        }
    }

    fun startSearch(context: GameStartActivity){
        GameRequest(context, this).execute()
    }

    fun startSearch(context: Context){
        GameRequest(context as GameStartActivity, this).execute()
    }
}
