package com.allco.ui.bottomsheet

import android.app.Dialog
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

fun Dialog.getDrawable(@DrawableRes drawableRes: Int?): Drawable? =
    drawableRes?.let { AppCompatResources.getDrawable(context, drawableRes) }
