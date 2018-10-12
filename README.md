# OverlapImageGalleyView


## Overview

OverlapImageGalleyView is a flexible library which helps you to create overlapping images gallery in your android Application. You can easily integrate it with the most popular image loading libraries such as Picasso, Glide and Fresco.


### Screenshot

![image](/media/OverlapImageGalleyView.gif)


### Key features

* Easy way to integrate it with your recyclerview adapter.
* Overlapping space as you want.
* Number of items to show in gallery as overlapped.
* Different scroll animations.
* Orientation.


# Usage


```java
    //------limit number of items to be overlapped     
    private val overlapLimit = 5     
  
    //------set value of item overlapping     
    private val overlapWidth = -50
  
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
#### Other lib which we have used here
* CircleImageView -> implementation 'de.hdodenhof:circleimageview:2.2.0'
* Glide -> implementation 'com.github.bumptech.glide:glide:4.8.0'


# LICENSE!

Map interaction prototype is [MIT-licensed](/LICENSE).

# Let us know!
We’d be really happy if you send us links to your projects where you use our component. Just send an email to sales@mindinventory.com And do let us know if you have any questions or suggestion regarding our work.
