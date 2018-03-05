package com.allco.ui.bottomsheet

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.databinding.ViewDataBinding
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes

class BottomSheetSettings {

    var onCanceled: (() -> Unit)? = null
    var maxInitialHeightInPercents: Int = 100
        set(value) {
            field = value.coerceIn(0, 100)
        }

    internal interface Item

    data class TitleItem(override var title: String? = null) : Item, TitleViewModel

    data class DividerItem(override var leftOffset: Int? = null,
                           override var rightOffset: Int? = null) : Item, DividerViewModel

    data class CustomItem(var layoutRes: Int? = null,
                          var onBind: ((ViewDataBinding, DialogInterface) -> Unit)? = null) : Item

    data class ClickableItem(override var title: String? = null,
                             override var iconUrl: String? = null,
                             override var iconDrawable: Drawable? = null,
                             override var onClicked: (() -> Unit)? = null,
                             @DrawableRes override var iconRes: Int? = null,
                             @ColorRes override var iconResTintColor: Int? = null,
                             var dismissOnClick: Boolean = true) : Item, ClickableViewModel

    internal val listItems = mutableListOf<Item>()

    fun title(action: TitleItem.() -> Unit) {
        listItems.add(TitleItem().apply(action))
    }

    fun clickableItem(action: ClickableItem.() -> Unit) {
        listItems.add(ClickableItem().apply(action))
    }

    fun divider(action: DividerItem.() -> Unit) {
        listItems.add(DividerItem().apply(action))
    }

    fun custom(action: CustomItem.() -> Unit) {
        listItems.add(CustomItem().apply(action))
    }
}

class BottomSheetBuilder(private val context: Context, private var settings: BottomSheetSettings) {
    fun show(): DialogInterface {
        return BottomSheetDialog(context).apply {
            init(settings)
            show()
        }
    }
}

fun Activity.bottomSheet(init: BottomSheetSettings.() -> Unit): BottomSheetBuilder {
    return BottomSheetBuilder(this, BottomSheetSettings().apply { init() })
}


