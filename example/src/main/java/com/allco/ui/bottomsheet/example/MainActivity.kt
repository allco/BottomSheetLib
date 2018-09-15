package com.allco.ui.bottomsheet.example

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.allco.ui.bottomsheet.bottomSheet
import com.allco.ui.bottomsheet.example.databinding.ActivityMainBinding
import com.allco.ui.bottomsheet.example.databinding.CustomLayoutBinding
import org.jetbrains.anko.toast
import org.jetbrains.anko.withAlpha

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    @Suppress("UNUSED_PARAMETER")
    fun runExampleYesNo(view: View) {
        bottomSheet {
            clickableItem {
                titleRes = R.string.yes
                onClicked = { toast(title.toString()) }
            }
            clickableItem {
                titleRes = R.string.no
                onClicked = { toast(title.toString()) }
            }
        }.show()
    }

    @Suppress("UNUSED_PARAMETER")
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

    @Suppress("UNUSED_PARAMETER")
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

    @Suppress("UNUSED_PARAMETER")
    fun runExample3(view: View) {
        bottomSheet {
            maxInitialHeightInPercents = 100
            onCanceled = { toast("Bottomsheet was canceled") }

            title { title = "Custom items" }

            divider {
                // shortened divider
                leftOffset = resources.getDimensionPixelOffset(R.dimen.dividerLeftOffset)
                rightOffset = resources.getDimensionPixelOffset(R.dimen.dividerRightOffset)
            }

            clickableItem {
                title = "Item with `Drawable` as icon."
                iconDrawable = ResourcesCompat.getDrawable(resources, R.drawable.photo_icon, null)
            }

            divider {} // full-length divider

            custom {
                layoutRes = R.layout.custom_layout
                onBind = { binding, _, dialogInterface ->
                    (binding as CustomLayoutBinding).apply {
                        // model = setup data accordingly `position`
                        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                            binding.root.setBackgroundColor(
                                when {
                                    rating < 1 -> Color.RED.withAlpha(0x88)
                                    rating < 2 -> Color.MAGENTA.withAlpha(0x88)
                                    rating < 3 -> Color.YELLOW.withAlpha(0x88)
                                    rating < 4 -> Color.CYAN.withAlpha(0x88)
                                    else -> Color.GREEN.withAlpha(0x88)
                                }
                            )
                        }
                        button.setOnClickListener {
                            dialogInterface.dismiss()
                        }
                    }
                }
            }
        }.show()
    }
}
