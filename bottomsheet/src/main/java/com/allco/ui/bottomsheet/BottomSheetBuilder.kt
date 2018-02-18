package com.allco.ui.bottomsheet

import android.app.Activity
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes


fun Activity.bottomSheet(init: BottomSheetSettings.() -> Unit): BottomSheetBuilder {
    return BottomSheetBuilder(BottomSheetSettings().apply { init() })
}

class BottomSheetSettings {
    interface Item

    data class DividerItemBuilder(
            var leftOffset: Int? = Int.MIN_VALUE,
            var rightOffset: Int? = Int.MIN_VALUE) : Item

    data class ItemBuilder(var title: String? = null,
                           @DrawableRes var iconRes: Int = Int.MIN_VALUE,
                           @ColorInt var iconTint: Int = Int.MIN_VALUE,
                           var iconDrawable: Drawable? = null,
                           var iconUrl: String? = null,
                           var clickable: Boolean = false,
                           var onClicked: (() -> Boolean)? = null) : Item

    private var listItems = mutableListOf<Item>()

    var onCanceled: (() -> Unit)? = null

    fun item(action: ItemBuilder.() -> Unit) {
        listItems.add(ItemBuilder().apply(action))
    }

    fun divider(action: DividerItemBuilder.() -> Unit) {
        listItems.add(DividerItemBuilder().apply(action))
    }
}

class BottomSheetBuilder(var settings: BottomSheetSettings) {
    fun show() {

    }
}

