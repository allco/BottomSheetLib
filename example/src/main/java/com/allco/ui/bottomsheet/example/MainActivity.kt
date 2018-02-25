package com.allco.ui.bottomsheet.example

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.allco.ui.bottomsheet.bottomSheet
import com.allco.ui.bottomsheet.example.databinding.ActivityMainBinding
import com.allco.ui.bottomsheet.example.databinding.CustomLayoutBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.btRunLinear.setOnClickListener { runLinearBottomSheet() }
    }

    private fun runLinearBottomSheet() {

        bottomSheet {

            maxInitialHeightInPercents = 100

            // trivial items
            title { title = "Title item 1" }
            clickableItem {
                title = "Clickable item 1"
                onClicked = {
                    Log.d(TAG, "Clicked: " + title)
                    false
                }
            }
            clickableItem {
                title = "Clickable item 2"
                onClicked = {
                    Log.d(TAG, "Clicked: " + title)
                    false
                }
            }
            title { title = "Title item 2" }
            clickableItem {
                title = "Clickable item 3"
                onClicked = {
                    Log.d(TAG, "Clicked: " + title)
                    false
                }
            }
            divider {}
            clickableItem {
                title = "Clickable item 4"
                onClicked = {
                    Log.d(TAG, "Clicked: " + title)
                    false
                }
            }

            // a bit more complicated items
            divider {
                leftOffset = resources.getDimensionPixelOffset(R.dimen.dividerLeftOffset)
                rightOffset = resources.getDimensionPixelOffset(R.dimen.dividerRightOffset)
            }

            clickableItem {
                title = "Magenta car. Tinted vector icon."
                iconRes = R.drawable.ic_directions_car_black_24dp
                iconResTintColor = R.color.colorAccent
                onClicked = {
                    Log.d(TAG, "Clicked " + title)
                    false
                }
            }

            divider {}

            clickableItem {
                title = "Photo camera. Drawable."
                iconDrawable = ResourcesCompat.getDrawable(resources, R.drawable.photo_icon, null)
                onClicked = {
                    Log.d(TAG, "Clicked " + title)
                    false
                }
            }

            clickableItem {
                title = "Item without icon"
            }

            custom {
                layoutRes = R.layout.custom_layout
                onBind = { binding, dialogInterface ->
                    (binding as CustomLayoutBinding).apply {
                        button.setOnClickListener {
                            dialogInterface.dismiss()
                        }
                    }
                }
            }

            onCanceled = {
                Log.d(TAG, "Bottomsheet was canceled")
            }
        }.show()

    }
}
