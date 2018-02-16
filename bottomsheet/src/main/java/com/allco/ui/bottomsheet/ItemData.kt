package com.allco.ui.bottomsheet

import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes

data class ItemData(val title: String? = null,
                    @DrawableRes
                    val iconRes: Int = Int.MIN_VALUE,
                    @ColorInt
                    val iconTint: Int = Int.MIN_VALUE,
                    val imageUrl: String? = null,
                    val payload: Bundle? = null,
                    val clickable: Boolean = false,
                    @LayoutRes
                    val dividerLayout: Int = Int.MIN_VALUE)