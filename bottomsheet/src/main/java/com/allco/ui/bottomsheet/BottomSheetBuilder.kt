package com.allco.ui.bottomsheet

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.databinding.ViewDataBinding
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment

/**
 * Creates [BottomSheetBuilder]
 * @param action an configuration action for the [BottomSheetSettings]
 */
fun Activity.bottomSheet(action: BottomSheetSettings.() -> Unit): BottomSheetBuilder {
    return BottomSheetBuilder(this, BottomSheetSettings().apply { action() })
}

/**
 * Creates [BottomSheetBuilder]
 * @param action an configuration action for the [BottomSheetSettings]
 */
fun Fragment.bottomSheet(action: BottomSheetSettings.() -> Unit): BottomSheetBuilder {
    return BottomSheetBuilder(this.context!!, BottomSheetSettings().apply { action() })
}

/** Represents the configured BottomSheet  */
class BottomSheetBuilder internal constructor(private val context: Context, private var settings: BottomSheetSettings) {
    /** Actually shows the BottomSheet */
    fun show(): DialogInterface {
        return BottomSheetDialog(context).apply {
            init(settings)
            show()
        }
    }
}

/**
 * Represents settings for [BottomSheetBuilder]
 */
class BottomSheetSettings {

    /**
     * Creates a title item
     *  @param action an configuration action for the [TitleItem]
     */
    fun title(action: TitleItem.() -> Unit) {
        listItems.add(TitleItem().apply(action))
    }

    /**
     * Creates a clickable item
     *  @param action an configuration action for the [ClickableItem]
     */
    fun clickableItem(action: ClickableItem.() -> Unit) {
        listItems.add(ClickableItem().apply(action))
    }

    /**
     * Creates a divider items
     *  @param action an configuration action for the [DividerItem]
     */
    fun divider(action: DividerItem.() -> Unit) {
        listItems.add(DividerItem().apply(action))
    }

    /**
     * Creates an item with custom layout
     *  @param action an configuration action for the [CustomItem]
     */
    fun custom(action: CustomItem.() -> Unit) {
        listItems.add(CustomItem().apply(action))
    }

    /** an action, which will be invoked in case if the BottomSheet is canceled (user pushed "backButton" or tapped beside) */
    var onCanceled: (() -> Unit)? = null
    /** indicates the maximum initial height of the BottomSheet in percents of available screen height */
    var maxInitialHeightInPercents: Int = 100
        set(value) {
            field = value.coerceIn(0, 100)
        }

    internal interface Item

    /**
     * Represents unclickable item with title
     * @property title the text which will be shown as a title
     */
    data class TitleItem(
        override var title: String? = null,
        @StringRes var titleRes: Int? = null
    ) : Item, TitleViewModel

    /**
     * Represents the horizontal line item aka divider
     * @property leftOffset blank gap from left side in pixels
     * @property rightOffset blank gap from right side in pixels
     */
    data class DividerItem(
        override var leftOffset: Int? = null,
        override var rightOffset: Int? = null
    ) : Item, DividerViewModel

    /**
     * Represents an item with custom layout
     * @property layoutRes id of layout from resource
     * @property onBind action which will be called every time when the [layoutRes] is supposed to be populated with actual data.
     */
    data class CustomItem(
        var layoutRes: Int? = null,
        var onBind: ((binding: ViewDataBinding, position: Int, dialog: DialogInterface) -> Unit)? = null
    ) : Item

    /**
     * Represents an clickable item which can optionally can have a title and an icon.
     * The icon will be fitted in the 24dp x 24dp square.
     * @property title the text of the item
     * @property iconRes resource id of a drawable which is supposed to be used as as an icon
     * @property iconResTintColor resource id of a color which will be used fot tinting [iconRes]
     * @property iconDrawable a [Drawable] which will be used as an icon overrides [iconRes]
     * @property onClicked an action which will be invoked is the user tapped the item
     * @property dismissOnClick if `false` then the BottomSheet will not be dismissed automatically if the user tapped the item
     */
    data class ClickableItem(
        override var title: String? = null,
        @StringRes var titleRes: Int? = null,
        override var iconUrl: String? = null,
        override var iconDrawable: Drawable? = null,
        override var onClicked: (() -> Unit)? = null,
        @DrawableRes override var iconRes: Int? = null,
        @ColorRes override var iconResTintColor: Int = R.color.bottom_sheet_item_text_title,
        var dismissOnClick: Boolean = true
    ) : Item, ClickableViewModel

    internal val listItems = mutableListOf<Item>()
}

