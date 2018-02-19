package com.allco.ui.bottomsheet

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import com.allco.ui.recyclerView.ObserverAdapter


fun Activity.bottomSheet(init: BottomSheetSettings.() -> Unit): BottomSheetBuilder {
    return BottomSheetBuilder(this, BottomSheetSettings().apply { init() })
}

class BottomSheetSettings {

    data class TitleItem(var title: String? = null) : ObserverAdapter.Item {
        override val layout = R.layout.bottom_sheet_list_item_title
    }

    data class DividerItem(
            var leftOffset: Int? = null,
            var rightOffset: Int? = null) : ObserverAdapter.Item {
        override val layout = R.layout.bottom_sheet_list_item_divider
    }

    class ClickableItem : ObserverAdapter.Item {

        var title: String? = null
        var onClicked: (() -> Boolean)? = null

        @ColorRes
        var iconResTintColor: Int? = null
        @DrawableRes
        var iconRes: Int? = null
            set(value) {
                field = value
                iconUrl = null
                iconDrawable = null
            }

        var iconDrawable: Drawable? = null
            set(value) {
                field = value
                iconUrl = null
                iconRes = null
                iconResTintColor = null
            }
        var iconUrl: String? = null
            set(value) {
                field = value
                iconRes = null
                iconResTintColor = null
                iconDrawable = null
            }

        override val layout = R.layout.bottom_sheet_list_item
    }

    val listItems = ObserverAdapter.ItemList()

    var onCanceled: (() -> Unit)? = null

    fun title(action: TitleItem.() -> Unit) {
        listItems.add(TitleItem().apply(action))
    }

    fun clickableItem(action: ClickableItem.() -> Unit) {
        listItems.add(ClickableItem().apply(action))
    }

    fun divider(action: DividerItem.() -> Unit) {
        listItems.add(DividerItem().apply(action))
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

