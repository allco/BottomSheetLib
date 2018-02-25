package com.allco.ui.bottomsheet

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.R
import android.support.design.widget.BottomSheetBehavior
import android.view.WindowManager
import android.widget.FrameLayout
import com.allco.ui.bottomsheet.databinding.BottomSheetListBinding
import com.allco.ui.recyclerView.ObserverBasedAdapter
import kotlin.math.min
import kotlin.math.roundToInt

class BottomSheetDialog(context: Context) : android.support.design.widget.BottomSheetDialog(context) {

    internal fun init(settings: BottomSheetSettings) {
        val binding = BottomSheetListBinding.inflate(layoutInflater)
        binding.rvBottomSheet.adapter = ObserverBasedAdapter(settings.listItems, this)
        setContentView(binding.root)

        @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        binding.root.addOnLayoutChangeListener { view, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            val bottomSheet = findViewById<FrameLayout>(R.id.design_bottom_sheet)
                    ?: throw IllegalStateException("R.id.design_bottom_sheet is not found")
            bottomSheet.setBackgroundColor(Color.TRANSPARENT)
            val maxInitialHeight = (getScreenHeight() * settings.maxInitialHeightInPercents / 100f).roundToInt()
            BottomSheetBehavior.from(bottomSheet).apply { peekHeight = min(bottomSheet.height, maxInitialHeight) }
        }
    }

    private fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // make statusBar translucent
        val window = window
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        super.onCreate(savedInstanceState)
    }
}
