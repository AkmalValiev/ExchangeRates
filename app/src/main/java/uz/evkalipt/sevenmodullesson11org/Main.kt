package uz.evkalipt.sevenmodullesson11org

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:url")
fun loadImage(imageView:ImageView, url:String){
        Picasso.get().load(url).into(imageView)
}