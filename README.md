[![](https://jitpack.io/v/allco/BottomSheetLib.svg)](https://jitpack.io/#allco/BottomSheetLib)

# BottomSheetLib
A tiny library which helps to use a BottomSheet defined by Material Design.

## How to use
### Include
TBD


## Examples
The following reproduces some Examples [provided by Google](https://material.io/guidelines/components/bottom-sheets.html#bottom-sheets-specs).  

### #1
<img src="doc/example1.png" alt="Example #1 image." width=400 /> 

```kotlin
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
```

### #2
<img src="doc/example2.png" alt="Example #1 image." width=400 />

```kotlin
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

``` 
