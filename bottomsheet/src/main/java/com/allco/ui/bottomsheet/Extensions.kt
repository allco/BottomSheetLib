package com.allco.ui.bottomsheet

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v7.content.res.AppCompatResources

fun Dialog.getDrawable(@DrawableRes drawableRes: Int?): Drawable? =
    drawableRes?.let { AppCompatResources.getDrawable(context, drawableRes) }
