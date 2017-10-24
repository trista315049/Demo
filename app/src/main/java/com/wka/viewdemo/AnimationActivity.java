package com.wka.viewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * Created by  trista on 2017/10/6.
 */

public class AnimationActivity extends AppCompatActivity {
    private ImageView head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        head = (ImageView) findViewById(R.id.head);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation mScaleAnimation = new ScaleAnimation(1.0f, 0.5f, 1.0f,
                        0.5f,// 整个屏幕就0.0到1.0的大小//缩放
                        Animation.INFINITE, 0.5f,
                        Animation.INFINITE, 0.5f);
                mScaleAnimation.setDuration(1000);
                mScaleAnimation.setFillAfter(true);
                Log.e("head.getLeft()----"+head.getLeft(),"----head.getLeft()+head.getWidth()/4---"+head.getLeft()+head.getWidth()/4+"----head.getTop()"+(-head.getTop()));

                Animation mTranslateAnimation = new TranslateAnimation(0,head.getWidth()/4,0,-head.getTop());// 移动
                mTranslateAnimation.setDuration(1000);

                AnimationSet mAnimationSet=new AnimationSet(false);
                mAnimationSet.addAnimation(mScaleAnimation);
                mAnimationSet.setFillAfter(true);
                mAnimationSet.addAnimation(mTranslateAnimation);
                head.startAnimation(mAnimationSet);
            }
        });

    }
}
