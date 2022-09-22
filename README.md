<a href="https://www.mindinventory.com/?utm_source=gthb&utm_medium=repo&utm_campaign=overlapImageGalleryView"><img src="https://github.com/Sammindinventory/MindInventory/blob/main/Banner.png"></a>
# OverlapImageGalleryView [![](https://jitpack.io/v/Mindinventory/OverlapImageGalleryView.svg)](https://jitpack.io/#Mindinventory/OverlapImageGalleryView) ![](https://img.shields.io/github/languages/top/Mindinventory/OverlapImageGalleryView) ![](https://img.shields.io/github/license/mindinventory/OverlapImageGalleryView)

--
OverlapImageGalleryView is an android library which provides circular image horizontal list with multiple animations and customization.

### Preview
![image](/media/OverlapImageView.gif)

### Key features

* Easy way to integrate it with your recyclerview adapter.
* Overlapping space as you want.
* Number of items to show in gallery as overlapped.
* Different scroll animations.
* Orientation.
* Supported androidx

## Usage
### Dependencies
- **Step 1: Add the JitPack repository in your project build.gradle file**
```bash
allprojects {
	    repositories {
		    ...
		    maven { url 'https://jitpack.io' }
	    }
    }
```
**or**

**If Android studio version is Arctic Fox or higher then add it in your settings.gradle**

```bash
dependencyResolutionManagement {
  		repositories {
       		...
       		maven { url 'https://jitpack.io' }
   		}
   }
``` 
- **Step 2: Add the dependency in your app module build.gradle file**
```bash
dependencies {
		    ...
	        implementation 'com.github.Mindinventory:OverlapImageGalleryView:x.x.x'
	}
```
### Implementation
```Fragment/Activity
    //------limit number of items to be overlapped     
    private val overlapLimit = 5     
  
    //------set value of item overlapping in percentage between 0 to 100
    private val overlapWidthInPercentage = -50
  
    //------set item decoration for item overlapping
    recyclerView.addItemDecoration(OverlapRecyclerViewDecoration(overlapLimit, overlapWidth))
    recyclerView.adapter = mAdapter         
    mAdapter.setImageList(setDummyArrayList())
    
    
    //------ Implement OverlapRecyclerViewClickListener interface to get callback of items click.
    override fun onNormalItemClicked(adapterPosition: Int) {
        toast(this,"Normal item clicked >> $adapterPosition")
    }

    override fun onNumberedItemClick(adapterPosition: Int) {
        toast(this,"Numbered item clicked >> $adapterPosition")
        // Here you can add remaining items in list or open seperate screen.
    }
```

### Library used
* Glide -> implementation 'com.github.bumptech.glide:glide:4.8.0'

### Dribble
https://dribbble.com/shots/5790365-Magnetic-Swipe-Animation-code

## LICENSE!

OverlapImageGalleryView is [MIT-licensed](/LICENSE).

## Let us know!
If you use our open-source libraries in your project, please make sure to credit us and Give a star to www.mindinventorycom

<p><h4>Please feel free to use this component and Let us know if you are interested to building Apps or Designing Products.</h4>
<a href="https://www.mindinventory.com/contact-us.php?utm_source=gthb&utm_medium=repo&utm_campaign=overlapImageGalleryView">
<img src="https://github.com/Sammindinventory/MindInventory/blob/main/hirebutton.png" width="203" height="43"  alt="app development">
</a>
