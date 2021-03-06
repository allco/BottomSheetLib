[![](https://jitpack.io/v/allco/BottomSheetLib.svg)](https://jitpack.io/#allco/BottomSheetLib)

# BottomSheetLib
A tiny library which helps to use a [BottomSheet defined by Material Design](https://material.io/guidelines/components/bottom-sheets.html).   

## Motivation
There is an authentic implementation [provided by Goolge](https://materialdoc.com/components/bottom-sheets/) for **permanent BottomSheet** 
which is inarguably useful but there is no any simple solution for **modal BottomSheets** especially if it is only needed to show something trivial (like yes/no buttons).  

This library is supposed to simplify the creation of general purpose **modal BottomSheets** by providing configuration DSL based on Kotlin-extensions. 

## Requirements
*DataBinding should be enabled* at your project. Add it in your module's build.gradle:

		dataBinding {enabled = true}

## Include
Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.allco:BottomSheetLib:0.3'
	}

## Examples
The following examples was inspired by [Google's definition of BottomSheet](https://material.io/guidelines/components/bottom-sheets.html#bottom-sheets-specs).  

### #1
Yes/No chooser

<img src="doc/example1.png" alt="Example #1 image." width=400 /> 

```kotlin
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
```


### #2
<img src="doc/example2.png" alt="Example #1 image." width=400 /> 

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

### #3
<img src="doc/example3.png" alt="Example #2 image." width=400 />

```kotlin
    fun runExample2(view: View) {
        bottomSheet {
            clickableItem {
                title = "Document"
                tintColorRes = R.color.icon_document
                iconRes = R.drawable.ic_insert_chart_black
                onClicked = { toast(title.toString()) }
            }
            clickableItem {
                title = "Spreadsheet"
                tintColorRes = R.color.icon_spreadsheet
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

### #4
Custom items

<img src="doc/example4.png" alt="Example #4 image." width=400 />

```kotlin
    fun runExample3(view: View) {
        bottomSheet {
            maxInitialHeightInPercents = 100
            onCanceled = { toast("Bottomsheet was canceled") }

            title { title = "Custom items" }

            divider { // shortened divider
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
                onBind = { binding, position, dialogInterface ->
                    (binding as CustomLayoutBinding).apply {
                       binding.model = ... // setup data accordingly `position`
                    }
                }
            }
        }.show()
    }
```

### #5
Custom background

<img src="doc/example5.png" alt="Custom backgroung image." width=400 />

```kotlin
    fun runExampleYesNo(view: View) {
        bottomSheet {
            backgroundRes = R.drawable.custom_bg    
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
```

# How to style with style attributes

The library exposes some UI parameters as style attributes.

There is a default style named `BottomSheetLib` which tries to follow [Material design](https://material.io) as much as possible.
However, at your app it can be fully or partially overridden.
    
Here how you can do it:

1. A new style inherited from `BottomSheetLib` should be defined.

```xml
    <style name="MyStyle" parent="BottomSheetLib">
        <item name="bslPaneBackground">@drawable/custom_bg</item>
        <item name="bslPanePaddingTop">0dp</item>
        <item name="bslPanePaddingBottom">0dp</item>
        <item name="bslItemClickableTextAppearance">?android:textAppearanceMedium</item>
        ...
    </style>

```

**For a full list of all the available attributes check [this](https://github.com/allco/BottomSheetLib/blob/master/bottomsheet/src/main/res/values/styles.xml)**


2. Then the style needs to be hooked up with `bottomSheetLibStyle` attribute in the App's theme.

```xml
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        ...
        <item name="bottomSheetLibStyle">@style/BottomSheetLib.MyStyle</item>
    </style>
```

Check [an example](https://github.com/allco/BottomSheetLib/blob/master/example/src/main/res/values/styles.xml) in order to see how it could be done.

# How to use

1. Call `bottomSheet(...)` on an Activity or Fragment in order to get the `BottomSheetBuilder`.
2. Provide `bottomSheet(...)` with an action which supposed to configure the BottomSheet.
3. Call `bottomSheetBuilder.show()` to actually show the BottomSheet.  

For more information about available options check KDoc [here](https://github.com/allco/BottomSheetLib/blob/master/bottomsheet/src/main/java/com/allco/ui/bottomsheet/BottomSheetBuilder.kt)  
