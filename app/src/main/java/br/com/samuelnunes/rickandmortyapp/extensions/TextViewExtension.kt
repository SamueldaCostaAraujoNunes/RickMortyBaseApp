package br.com.samuelnunes.rickandmortyapp.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 04/08/2021 at 16:08
 */

@BindingAdapter("visibleIf")
fun View.visibleIf(condition: Boolean) {
    visibility = if (condition) {
        VISIBLE
    } else {
        GONE
    }
}