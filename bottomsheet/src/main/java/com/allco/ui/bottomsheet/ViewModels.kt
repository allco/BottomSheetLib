package com.allco.ui.bottomsheet

import android.content.DialogInterface
import android.databinding.ViewDataBinding
import android.graphics.drawable.Drawable
import com.allco.ui.recyclerView.ObserverBasedAdapter

interface TitleViewModel {
    var title: String?
    var textAppearanceRes: Int?
}

interface DividerViewModel {
    var leftOffset: Int?
    var rightOffset: Int?
}

interface ClickableViewModel {
    var title: String?
    var iconUrl: String?
    var iconDrawable: Drawable?
    var iconResTintColor: Int
    var iconRes: Int?
    var textAppearanceRes: Int?
    var onClicked: (() -> Unit)?
}

class TitleViewModelImpl(val data: BottomSheetSettings.TitleItem) : TitleViewModel by data, ObserverBasedAdapter.Item {
    override val layout = R.layout.bottom_sheet_list_item_title
}

class DividerViewModelImpl(data: BottomSheetSettings.DividerItem) : DividerViewModel by data, ObserverBasedAdapter.Item {
    override val layout = R.layout.bottom_sheet_list_item_divider
}

class CustomItemViewModel(private val data: BottomSheetSettings.CustomItem, private val dialog: DialogInterface) : ObserverBasedAdapter.Item {
    override val layout: Int
        get() = data.layoutRes
                ?: throw IllegalStateException("`layout` property is required for 'custom{}' item")

    override val binder: ((ViewDataBinding, Int) -> Unit)
        get() = { binding, position ->
            data.onBind?.invoke(binding, position, dialog) ?: super.binder
        }
}

class ClickableViewModelImpl(
    private val data: BottomSheetSettings.ClickableItem,
    private val dialog: DialogInterface
)
    : ClickableViewModel by data, ObserverBasedAdapter.Item {

    override val layout = R.layout.bottom_sheet_list_item

    override var onClicked: (() -> Unit)? = {
        data.onClicked?.invoke()
        if (data.dismissOnClick) dialog.dismiss()
    }
}