package com.allco.ui.bottomsheet.example

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.allco.ui.bottomsheet.bottomSheet

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomSheet {
            item {
                title = "Title 1"
                clickable = false
            }

            divider {
                leftOffset = resources.getDimensionPixelOffset(R.dimen.dividerLeftOffset)
                rightOffset = resources.getDimensionPixelOffset(R.dimen.dividerRightOffset)
            }

            item {
                title = "Magenta car. Tinted vector icon."
                iconRes = R.drawable.ic_directions_car_black_24dp
                iconTint = Color.MAGENTA
                onClicked = {
                    Log.d(TAG, "Clicked " + title)
                    false
                }
            }

            item {
                title = "Photo camera. Drawable."
                iconDrawable = resources.getDrawable(R.drawable.photo_icon, theme)
                onClicked = {
                    Log.d(TAG, "Clicked " + title)
                    false
                }
            }

            onCanceled = {
                Log.d(TAG, "Bottomsheet was canceled")
            }
        }
                .show()
    }
}
