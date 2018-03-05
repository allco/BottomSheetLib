[![](https://jitpack.io/v/allco/BottomSheetLib.svg)](https://jitpack.io/#allco/BottomSheetLib)

# BottomSheetLib
A tiny library which helps to use a BottomSheet defined by Material Design.

## How to use
### Include
TBD
### Example
```

     getActivity().bottomSheet {

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

```


# Motivation
TBD
