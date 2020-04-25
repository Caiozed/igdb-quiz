package com.caiozed.videogamequiz.gamestart

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.GridLayoutManager
import com.caiozed.videogamequiz.models.adapters.GameAdapter
//import com.caiozed.videogamequiz.models.adapters.GridSpacingItemDecoration
import com.caiozed.videogamequiz.utils.openLoading
import kotlinx.android.synthetic.main.activity_game_start.*
import kotlinx.coroutines.runBlocking


public class GameRequest (var activity: GameStartActivity, var viewModel: GameStartActivityViewModel): AsyncTask<String, String, String>() {
    var dialog = openLoading(activity)

    override fun onPreExecute() {
        super.onPreExecute();
        dialog.show()
    }

    override fun doInBackground(vararg params: String?): String {
        runBlocking {
            viewModel.searchForGames()
        }

        Handler(Looper.getMainLooper()).post(kotlinx.coroutines.Runnable {
            val layoutManager = GridLayoutManager(activity, 2)
//            activity.gridView.addItemDecoration(GridSpacingItemDecoration(2, 20, true, 0))
            activity.gridView.layoutManager = layoutManager

            activity.gridView.adapter = GameAdapter(viewModel.games)
            dialog.dismiss()
        })
        return  ""
    }
}