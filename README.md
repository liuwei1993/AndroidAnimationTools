# Android动画工具箱
### 预览
<img src="https://raw.githubusercontent.com/liuwei1993/AndroidAnimationTools/master/screen_record.gif" width="300px"/>


### 使用方法



##### 示例：

- [WavingBars](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/WavingBars.java)
- [WanderingCubes](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/WanderingCubes.java)
- [FoldingCube](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/FoldingCube.java)
- [CubeGrid](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/CubeGrid.java)
- [RotatingPlane](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/RotatingPlane.java)
- [RotatingDots](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/RotatingDots.java)
- [RotatingDots2](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/RotatingDots2.java)
- [DoubleCircleBounce](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/DoubleCircleBounce.java)
- [MultiplePulse](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/MultiplePulse.java)
- [ThreeBounce](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/ThreeBounce.java)
- [ChasingDots](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/container/ChasingDots.java)

##### 示例使用：
java中：
```
ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
AnimationDrawable drawable = new WavingBars();
imageView.setImageDrawable(drawable);
drawable.start();
```
layout中：
```
<!-- 如果动画需要超出View边界，则需要将ImageView的父View的 android:clipChildren属性设置为false -->
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:clipChildren="false">

    <ImageView
        android:id="@+id/image"
        android:layout_width="160dp"
        android:layout_height="160dp"
        android:clipChildren="false"
        android:layout_gravity="center"/>

</FrameLayout>
```
效果：

<img src="https://raw.githubusercontent.com/liuwei1993/AndroidAnimationTools/master/wavingbars_sample.gif" width="300px"/>

### 实现原理

##### 核心类：

该库主要有两个重要部分组成一个是drawable系列，用于绘制图形，一个是animator系列，用于方便的构造Animator.

drawable包下
- [AnimationDrawable](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/AnimationDrawable.java)
- [AnimDrawableContainer](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/drawable/AnimDrawableContainer.java)

animator包下
- [DrawableAnimatorBuilder](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/animator/DrawableAnimatorBuilder.java)
- [ViewAnimatorBuilder](https://github.com/liuwei1993/AndroidAnimationTools/blob/master/library/src/main/java/com/simon/core/animationtools/animator/ViewAnimatorBuilder.java)

> 继承AnimatonDrawable类后使用DrawableAnimatorBuilder可以使Drawable具备动画效果，而AnimDrawableContainer自身继承于AnimationDrawable并且可以管理多个AnimatonDrawable从而实现复杂动画效果
> 而interpolator包下的工具类可以帮助我们高效的创建复杂的Interpolator.
> 使用者可参考示例代码来自定义各种复杂动画效果.
