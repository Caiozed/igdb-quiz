package com.caiozed.videogamequiz.models.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.caiozed.videogamequiz.R
import com.caiozed.videogamequiz.models.Game
import com.caiozed.videogamequiz.quizActivity.QuizActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_quiz.view.*
import kotlinx.android.synthetic.main.game_layout.view.*
import java.io.Serializable
import android.util.Pair as UtilsPair


class GameAdapter(private val games: Array<Game>?) :
    RecyclerView.Adapter<GameAdapter.GameHolder>() {

    class GameHolder(val textView: View) : RecyclerView.ViewHolder(textView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.GameHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_layout, parent, false) as View

        return GameHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        val game = this.games!![position]
        Picasso.get()
            .load("https://images.igdb.com/igdb/image/upload/t_720p/${game.coverData?.image_id}.jpg")
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .into(holder.itemView.gameImage)
        holder.itemView.gameText.text = game?.name


        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context, QuizActivity::class.java)
            var textStr = holder.itemView.context.getString(R.string.text_transition)
            var imageStr = holder.itemView.context.getString(R.string.image_transition)

            var options = ActivityOptions.makeSceneTransitionAnimation(holder.itemView.context as Activity,
                UtilsPair.create<View, String>(holder.itemView.gameImage, imageStr),
                UtilsPair.create<View, String>(holder.itemView.gameText, textStr))

            intent.putExtra("game", game as Serializable)
            holder.itemView.context.startActivity(intent, options.toBundle())
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = this.games?.size ?: 0
}
//
//class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean, private val headerNum: Int) : RecyclerView.ItemDecoration() {
//
//    override fun getItemOffsets(
//        outRect: Rect,
//        view: View,
//        parent: RecyclerView,
//        state: RecyclerView.State
//    ) {
//        val position = parent.getChildAdapterPosition(view) - headerNum // item position
//
//        if (position >= 0) {
//            val column = position % spanCount // item column
//
//            if (includeEdge) {
//                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
//                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
//
//                if (position < spanCount) { // top edge
//                    outRect.top = spacing
//                }
//                outRect.bottom = spacing // item bottom
//            } else {
//                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
//                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
//                if (position >= spanCount) {
//                    outRect.top = spacing // item top
//                }
//            }
//        } else {
//            outRect.left = 0
//            outRect.right = 0
//            outRect.top = 0
//            outRect.bottom = 0
//        }
//    }
//}