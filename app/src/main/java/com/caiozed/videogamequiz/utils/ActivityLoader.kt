package com.caiozed.videogamequiz.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AlertDialog
import com.caiozed.videogamequiz.R

fun openLoading(activity: Context) : AlertDialog{
    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    builder.setCancelable(false) // if you want user to wait for some process to finish,

    builder.setView(R.layout.loading_dialog_layout)
    val dialog: AlertDialog = builder.create()
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    return dialog
}


fun openModal(activity: Context) : AlertDialog{
    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    builder.setCancelable(false) // if you want user to wait for some process to finish,

    builder.setView(R.layout.gameover_dialog)
    val dialog: AlertDialog = builder.create()
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    return dialog
}
