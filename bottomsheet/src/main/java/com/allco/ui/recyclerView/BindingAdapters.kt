package com.allco.ui.recyclerView

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["listItems"])
fun setRecyclerViewItems(recyclerView: RecyclerView, listItems: ObserverBasedAdapter.ItemList) {
    recyclerView.adapter = ObserverBasedAdapter(listItems)
}

@BindingAdapter(value = ["marginLeft", "marginRight"], requireAll = false)
fun setParentLayoutLeftMargins(view: View, leftMargin: Int, rightMargin: Int) {
    (view.layoutParams as? ViewGroup.MarginLayoutParams)?.also { lp ->
        leftMargin.also { lp.leftMargin = it }
        rightMargin.also { lp.rightMargin = it }
        view.parent?.requestLayout()
    }
}

@BindingAdapter(value = ["compatLeftDrawableRes", "compatLeftDrawableResTintColor"], requireAll = false)
fun setCompoundDrawableRes(view: TextView, @DrawableRes drawableLeftRes: Int?, @ColorRes colorTintLeft: Int?) {
    modifyDrawable(view, drawableLeftRes, colorTintLeft)?.also {
        view.setCompoundDrawablesWithIntrinsicBounds(it, null, null, null)
    }
}

@BindingAdapter(value = ["compatLeftDrawable"], requireAll = false)
fun setCompoundDrawable(view: TextView, drawableLeftRes: Drawable?) {
    drawableLeftRes?.also {
        view.setCompoundDrawablesWithIntrinsicBounds(it, null, null, null)
    }
}

private fun modifyDrawable(view: TextView, @DrawableRes drawableRes: Int?, @ColorRes colorRes: Int?): Drawable? {
    return drawableRes?.let { res ->
        val context = view.context
        return VectorDrawableCompat.create(context.resources, res, context.theme)?.also { drawable ->
            colorRes?.also { colorRes ->
                DrawableCompat.setTint(drawable, ContextCompat.getColor(context, colorRes))
            }
        }
    }
}

@BindingAdapter(value = ["compatLeftDrawableUrl", "compatLeftDrawableUrlSize"], requireAll = false)
fun loadImageUrlDrawableLeft(textView: TextView, imageUrlDrawableLeft: String?, floatImageUrlSize: Float?) {

    if (isEmpty(imageUrlDrawableLeft)) {
        return
    }

    try {
        Class.forName("com.squareup.picasso.Picasso")
    } catch (e: ClassNotFoundException) {
        Log.w("BindingAdapters", "com.squareup.picasso.Picasso is required for loading icons from URL", e)
        return
    }

    val tag = textView.tag
    val context = textView.context
    val imageUrlSize = floatImageUrlSize?.let { Math.round(it) }
    val target: TextViewPicassoTarget = when (tag) {
        null -> TextViewPicassoTarget(textView)
        is TextViewPicassoTarget -> {
            Picasso.with(context).cancelRequest(tag)
            tag
        }
        else -> throw IllegalStateException("TextView with \"compatLeftDrawableUrl\"-attribute should never use setTag(..)/getTag()")
    }

    var drawableLeft: Drawable? = textView.compoundDrawables[0]
    // setup transparent placeholder
    if (drawableLeft == null) {
        drawableLeft = ColorDrawable(Color.TRANSPARENT)
        imageUrlSize?.also {
            drawableLeft.setBounds(0, 0, it, it)
        }

        textView.setCompoundDrawables(drawableLeft, null, null, null)
    }

    Picasso.with(context).load(imageUrlDrawableLeft).apply {
        imageUrlSize?.also { resize(it, it) }
        centerCrop()
        into(target)
    }
}

private class TextViewPicassoTarget internal constructor(private val textView: TextView) : com.squareup.picasso.Target {
    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
        val drawable = BitmapDrawable(textView.resources, bitmap)
        drawable.setBounds(0, 0, bitmap.width, bitmap.height)
        textView.setCompoundDrawables(drawable, null, null, null)
    }

    override fun onBitmapFailed(errorDrawable: Drawable) {}
    override fun onPrepareLoad(placeHolderDrawable: Drawable) {}
}
