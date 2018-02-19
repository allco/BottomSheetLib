package com.allco.ui.bottomsheet.example

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
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
            title {
                title = "Title 1"
            }

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
                onBind = { binding ->

                }
            }

            onCanceled = {
                Log.d(TAG, "Bottomsheet was canceled")
            }
        }
                .show()
    }
}
