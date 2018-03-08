package com.allco.ui.bottomsheet.example

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.allco.ui.bottomsheet.bottomSheet
import com.allco.ui.bottomsheet.example.databinding.ActivityMainBinding
import com.allco.ui.bottomsheet.example.databinding.CustomLayoutBinding
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    fun runExample1(view: View) {
        bottomSheet {
            clickableItem {
                title = "Share"
                iconRes = R.drawable.ic_share_black
                onClicked = { toast(title.toString()) }
            }
            clickableItem {
                title = "Upload"
                iconRes = R.drawable.ic_cloud_upload_black
                onClicked = { toast(title.toString()) }
            }
            clickableItem {
                title = "Copy"
                iconRes = R.drawable.ic_content_copy_black
                onClicked = { toast(title.toString()) }
            }
            clickableItem {
                title = "Print this page"
                iconRes = R.drawable.ic_print_black
                onClicked = { toast(title.toString()) }
            }
        }.show()
    }

    fun runExample2(view: View) {
        bottomSheet {
            clickableItem {
                title = "Document"
                iconResTintColor = R.color.icon_document
                iconRes = R.drawable.ic_insert_chart_black
                onClicked = { toast(title.toString()) }
            }
            clickableItem {
                title = "Spreadsheet"
                iconResTintColor = R.color.icon_spreadsheet
                iconRes = R.drawable.ic_insert_photo
                onClicked = { toast(title.toString()) }
            }
            clickableItem {
                title = "Folder"
                iconRes = R.drawable.ic_folder_black
                onClicked = { toast(title.toString()) }
            }

            divider { }

            clickableItem {
                title = "Upload photos or videos"
                iconRes = R.drawable.ic_cloud_upload_black
                onClicked = { toast(title.toString()) }
            }

            clickableItem {
                title = "Use camera"
                iconRes = R.drawable.ic_photo_camera_black
                onClicked = { toast(title.toString()) }
            }
        }.show()
    }


    fun runExampleN(view: View) {

        bottomSheet {

            maxInitialHeightInPercents = 100

            // trivial items
            title { title = "Title item 1" }
            clickableItem {
                title = "Clickable item 1"
                onClicked = { Log.d(TAG, "Clicked: " + title) }
            }
            clickableItem {
                title = "Clickable item 2"
                onClicked = { Log.d(TAG, "Clicked: " + title) }
            }
            title { title = "Title item 2" }
            clickableItem {
                title = "Clickable item 3"
                onClicked = { Log.d(TAG, "Clicked: " + title) }
            }
            divider {}
            clickableItem {
                title = "Clickable item but non dismissible."
                onClicked = { Log.d(TAG, "Clicked: " + title) }
                dismissOnClick = false
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
                }
            }

            divider {}

            clickableItem {
                title = "Item with `Drawable` as icon."
                iconDrawable = ResourcesCompat.getDrawable(resources, R.drawable.photo_icon, null)
                onClicked = {
                    Log.d(TAG, "Clicked " + title)
                }
            }

            custom {
                layoutRes = R.layout.custom_layout
                onBind = { binding, position, dialogInterface ->
                    (binding as CustomLayoutBinding).apply {
                        //model = setup data accordingly `position`
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
