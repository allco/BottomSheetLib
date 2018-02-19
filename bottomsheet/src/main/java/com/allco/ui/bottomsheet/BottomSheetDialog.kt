package com.allco.ui.bottomsheet

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.R
import android.support.design.widget.BottomSheetBehavior
import android.view.WindowManager
import android.widget.FrameLayout
import com.allco.ui.bottomsheet.databinding.BottomSheetListBinding

class BottomSheetDialog(context: Context) : android.support.design.widget.BottomSheetDialog(context) {

    internal fun init(settings: BottomSheetSettings) {
        val binding = BottomSheetListBinding.inflate(layoutInflater)
        binding.items = settings.listItems
        setContentView(binding.root)

        @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        binding.root.addOnLayoutChangeListener { view, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            val bottomSheet = findViewById<FrameLayout>(R.id.design_bottom_sheet)!!
            bottomSheet.setBackgroundColor(Color.TRANSPARENT)
            BottomSheetBehavior.from(bottomSheet).apply { peekHeight = bottomSheet.height }
        }
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
