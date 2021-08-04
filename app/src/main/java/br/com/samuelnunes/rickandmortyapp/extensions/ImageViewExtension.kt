package br.com.samuelnunes.rickandmortyapp.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import br.com.samuelnunes.rickandmortyapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 * @author Samuel da Costa Araujo Nunes
 * Created 03/08/2021 at 16:05
 */

@BindingAdapter("url")
fun ImageView.url(url: String?){
    val cornerRadius = context.resources.getDimensionPixelSize(R.dimen.corner_radius)
    Glide.with(context)
        .asBitmap()
        .load(url)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(cornerRadius)))
        .into(this)
}