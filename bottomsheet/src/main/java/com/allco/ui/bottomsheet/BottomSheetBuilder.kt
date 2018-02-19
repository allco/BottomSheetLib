package com.allco.ui.bottomsheet

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.databinding.ViewDataBinding
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import com.allco.ui.recyclerView.ObserverBasedAdapter


fun Activity.bottomSheet(init: BottomSheetSettings.() -> Unit): BottomSheetBuilder {
    return BottomSheetBuilder(this, BottomSheetSettings().apply { init() })
}

class BottomSheetSettings {

    data class TitleItem(var title: String? = null) : ObserverBasedAdapter.Item {
        override val layout = R.layout.bottom_sheet_list_item_title
    }

    data class DividerItem(
            var leftOffset: Int? = null,
            var rightOffset: Int? = null) : ObserverBasedAdapter.Item {
        override val layout = R.layout.bottom_sheet_list_item_divider
    }

    class CustomItem : ObserverBasedAdapter.Item {
        var layoutRes: Int? = null
        var onBind: ((binding: ViewDataBinding) -> Unit)? = null

        override val layout: Int
            get() = layoutRes
                    ?: throw IllegalStateException("layout is required for 'custom{}' item")

        override val binder: ((binding: ViewDataBinding) -> Unit)
            get() = onBind ?: super.binder

    }

    class ClickableItem : ObserverBasedAdapter.Item {
        var title: String? = null
        var iconUrl: String? = null
        var iconDrawable: Drawable? = null
        @ColorRes
        var iconResTintColor: Int? = null
        @DrawableRes
        var iconRes: Int? = null
        var onClicked: (() -> Boolean)? = null

        override val layout = R.layout.bottom_sheet_list_item
    }

    val listItems = ObserverBasedAdapter.ItemList()

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

