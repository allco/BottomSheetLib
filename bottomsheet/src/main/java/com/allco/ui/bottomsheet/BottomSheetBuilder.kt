package com.allco.ui.bottomsheet

import android.content.Context
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import java.util.*
import javax.inject.Inject

class BottomSheetBuilder @Inject constructor(context: Context) {

    private var instanceId: String? = null

    private val itemsList = ArrayList<ItemData>()
    private var cancelable = true

    fun addTitle(title: String): BottomSheetBuilder {
        itemsList.add(ItemData(title = title))
        return this
    }

    /**
     * Adds a clickable item to the BottomSheet
     *
     * @param drawableRes - drawable resource id (VectorDrawable supported) which will be used as the icon. Preferable size is 24dp x 24dp.
     * @param title       - string which will be use as the title
     * @param payload     - a Bundle which when the item clicked will be delivered to [BottomSheetListener.onBottomSheetItemClicked]
     */
    fun addItem(@DrawableRes drawableRes: Int, title: String, payload: Bundle?): BottomSheetBuilder {
        itemsList.add(ItemData(title = title, iconRes = drawableRes, payload = payload, clickable = true))
        return this
    }

    /**
     * Adds a clickable item to the BottomSheet
     *
     * @param drawableRes  - drawable resource id (VectorDrawable supported) which will be used as the icon. Preferable size is 24dp x 24dp.
     * @param drawableTint - tint color of the drawable resource (default is 0 = no tint)
     * @param title        - string which will be use as the title
     * @param payload      - a Bundle which when the item clicked will be delivered to [BottomSheetListener.onBottomSheetItemClicked]
     */
    fun addItem(@DrawableRes drawableRes: Int, @ColorInt drawableTint: Int, title: String, payload: Bundle?): BottomSheetBuilder {
        itemsList.add(ItemData(title = title, iconRes = drawableRes, iconTint = drawableTint, payload = payload, clickable = true))
        return this
    }

    /**
     * Convenience method. Clone of [.addItem]
     * which helps to wrap integer id to the Bundle payload.
     *
     * @param id - integer id which will be available after click with [.extractId]
     */
    fun addItem(@DrawableRes drawableRes: Int, title: String, id: Int): BottomSheetBuilder {
        val bundle = Bundle()
        bundle.putInt(BOTTOM_SHEET_PAYLOAD_ITEM_ID, id)
        return addItem(drawableRes, title, bundle)
    }

    /**
     * Adds a divider to the BottomSheet
     */
    fun addDivider(): BottomSheetBuilder {
        itemsList.add(ItemData(dividerLayout = 0))
        return this
    }

    /**
     * Adds a divider to the BottomSheet
     *
     * @param dimensionResourceId a resource id describing the layout_marginStart size of the divider
     */
    fun addDivider(@LayoutRes layoutRes: Int): BottomSheetBuilder {
        itemsList.add(ItemData(dividerLayout = layoutRes))
        return this
    }

    /**
     * @param instanceId an optional instanceId, which will be delivered
     * to [BottomSheetListenerProvider.getBottomSheetListener]
     */
    fun setInstanceId(instanceId: String): BottomSheetBuilder {
        this.instanceId = instanceId
        return this
    }

    /**
     * Makes the BottomSheet cancelable (if Back button pressed for example).
     * By default the BottomSheet is cancelable;
     */
    fun setCancelable(cancelable: Boolean): BottomSheetBuilder {
        this.cancelable = cancelable
        return this
    }


    companion object {

        private const val BOTTOM_SHEET_PAYLOAD_ITEM_ID = "BOTTOM_SHEET_PAYLOAD_ITEM_ID"

        /**
         * Supposed to be used in [BottomSheetListener.onBottomSheetItemClicked]
         * in order to extract id if it was delivered previously with [BottomSheetBuilder.addItem]
         *
         * @param payload a Bundle which contain id under [BottomSheetBuilder.BOTTOM_SHEET_PAYLOAD_ITEM_ID] key
         * @return extracted id or [Integer.MIN_VALUE] if it was not found.
         */
        fun extractId(payload: Bundle): Int {
            return payload.getInt(BOTTOM_SHEET_PAYLOAD_ITEM_ID, Integer.MIN_VALUE)
        }
    }
}
