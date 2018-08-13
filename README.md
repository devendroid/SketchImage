# SketchImage
[![](https://jitpack.io/v/devsideal/SketchImage.svg)](https://jitpack.io/#devsideal/SketchImage)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-ReadMoreOption-green.svg?style=flat )]( https://android-arsenal.com/details/1/7058)
[![GitHub license](https://img.shields.io/github/license/dcendents/android-maven-gradle-plugin.svg )]( http://www.apache.org/licenses/LICENSE-2.0.html)

Convert image in pencil sketch, gray scale, blur effect and inverted color.
## Demo
![ReadMoreOption](/assets/sketchimage1.0.1.gif)

## Dependency
- Add the dependencies to your gradle files:

#### Step 1. Add it in your root build.gradle at the end of repositories
```gradle
   allprojects {
       repositories {
    	...
    	maven { url 'https://jitpack.io' }
    	}
    }
```

#### Step 2. Add the dependency
```gradle
    dependencies {
        implementation 'com.github.devsideal:SketchImage:1.0.1'
     }

```

## Usage
```java

  Bitmap bmOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.your_image);
  SketchImage sketchImage = new SketchImage.Builder(this, bmOriginal).build();
  
  Bitmap bmProcessed = sketchImage.getImageAs(
            SketchImage.ORIGINAL_TO_SKETCH, 100 // value 0 - 100
            // Other options
            // SketchImage.ORIGINAL_TO_GRAY
            // SketchImage.ORIGINAL_TO_COLORED_SKETCH
            // SketchImage.ORIGINAL_TO_SOFT_SKETCH
            // And many more.....
    );
  imageView.setImageBitmap(bmProcessed);

```

## License
```
Copyright 2018 Deven Singh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```