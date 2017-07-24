package com.example.FurnitureShare.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;


import com.example.FurnitureShare.R;
import com.example.FurnitureShare.base.BaseActivity;
import com.example.FurnitureShare.ui.HomeActivity;

import java.util.ArrayList;

/**引导页*/
public class Navigation extends BaseActivity {

	/**Viewpager对象*/
	private ViewPager viewPager;
	private ImageView imageView;
	/**创建一个数组，用来存放每个页面要显示的view*/
	private ArrayList<View> pageViews;
	/**创建一个imageView类型的数组，用来表示导航小圆点*/
	private ImageView[] imageViews;
	/**装显示图片的viewGroup*/
	private ViewGroup viewPictures;
	/**导航小圆点的viewGroup*/
	private ViewGroup viewPoints;

//	private PowerManager.WakeLock wakeLock;
//	private PlayerView player;
//
//	private boolean mark = true;
//	private boolean mark1 = true;
//	private boolean mark2 = true;
//	private boolean mark3 = true;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		//LayoutInflater作用类似于findViewbyId（）
		//LayoutInflater是用来找res/layout/下的xml文件，并且实例化
		LayoutInflater inflater = getLayoutInflater();
		pageViews = new ArrayList<View>();
		pageViews.add(inflater.inflate(R.layout.viewpager04, null));
		pageViews.add(inflater.inflate(R.layout.viewpager05, null));
		pageViews.add(inflater.inflate(R.layout.viewpager06, null));
//		pageViews.add(inflater.inflate(R.layout.viewpager07, null));
		
		//小圆点数组，大小是图片的个数
		imageViews = new ImageView[pageViews.size()];
		//从指定xml文件中加载视图
		viewPictures = (ViewGroup) inflater.inflate(R.layout.viewpagers, null);
		viewPager = (ViewPager) viewPictures.findViewById(R.id.guidePagers);
		viewPoints = (ViewGroup) viewPictures.findViewById(R.id.viewPoints);


		
		//添加小圆点导航的图片
//		for (int i = 0; i < pageViews.size(); i++) {
//			imageView = new ImageView(Navigation.this);
//			//setLayoutParams重置控件的布局（LayoutParams重新设置坐标）
//			imageView.setLayoutParams(new ViewGroup.LayoutParams(60, 20));
//			//设置内边框
//			imageView.setPadding(5, 0, 5, 0);
//			// 把小圆点放进数组中
//			imageViews[i] = imageView;
//			//m默认选中的是第一张图片，此时第一张图片是选中状态
//			if (i == 0)
//				imageViews[i].setImageDrawable(getResources().getDrawable(
//						R.drawable.page_indicator_focused));
//			else
//				imageViews[i].setImageDrawable(getResources().getDrawable(
//						R.drawable.page_indicator_unfocused));
//			// 将imageviews添加到小圆点视图组
//			viewPoints.addView(imageViews[i]);
//		}
		setContentView(viewPictures);
		//设置viewPager的适配器
		viewPager.setAdapter(new NavigationPageAdapter());
		// 为viewpager添加监听，当view发生变化时的响应
		viewPager.setOnPageChangeListener(new NavigationPageChangeListener());


	}



	// 设置要显示的pageradapter类
	// 导航图片view的适配器，必须要实现的是下面四个方法
	class NavigationPageAdapter extends PagerAdapter {
		//获取当前窗体界面数
		@Override
		public int getCount() {
			return pageViews.size();
		}
		//该方法声明了返回值不一定是view，可以是任意对象
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		//初始化每个Item（初始化position位置的界面）
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager)container).addView(pageViews.get(position));
			return pageViews.get(position);
		}
		// 销毁每个Item（销毁position位置的界面）
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager)container).removeView(pageViews.get(position));
		}
	}
	// viewpager的监听器，主要是onPageSelected要实现
	// (设置viewpager滑动的事件，实现导航点的滚动)
	class NavigationPageChangeListener implements ViewPager.OnPageChangeListener {

		//表示当前滑动的状态，arg0有三个状态(0， 1， 2)
		//arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。
		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
		//当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用
        //arg0表示目标(当前页面，及你点击滑动的页面)，
		//arg1表示页面偏移的百分比，
		//arg2表示页面偏移的像素位置
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
        //指的是当前选择的是哪个页面
		@Override
		public void onPageSelected(int position) {
			// 循环主要是控制导航中每个小圆点的状态
//			for (int i = 0; i < imageViews.length; i++) {
//				// 当前view下设置小圆点为选中状态
//				imageViews[i].setImageDrawable(getResources().getDrawable(
//						R.drawable.page_indicator_focused));
//				// 其余设置为飞选中状态
//				if (position != i)
//					imageViews[i].setImageDrawable(getResources().getDrawable(
//							R.drawable.page_indicator_unfocused));
//			}

//			AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
//			alphaAnimation.setDuration(2000);
//			alphaAnimation.setStartOffset(1000);
//
//			AlphaAnimation alphaAnimation1 = new AlphaAnimation(0,1);
//			alphaAnimation1.setDuration(2000);
//			alphaAnimation1.setStartOffset(2000);
//
//			AlphaAnimation alphaAnimation2 = new AlphaAnimation(0,1);
//			alphaAnimation2.setDuration(2000);
//			alphaAnimation2.setStartOffset(3000);
//
//			AlphaAnimation alphaAnimation3 = new AlphaAnimation(0,1);
//			alphaAnimation3.setDuration(2000);
//			alphaAnimation3.setStartOffset(4000);
//
//			AlphaAnimation alphaAnimation4 = new AlphaAnimation(0,1);
//			alphaAnimation4.setDuration(2000);
//			alphaAnimation4.setStartOffset(5000);
//
//			AlphaAnimation alphaAnimation5 = new AlphaAnimation(0,1);
//			alphaAnimation5.setDuration(2000);
//			alphaAnimation5.setStartOffset(6000);
//
//
//			if (position == 1){
//
//				if (mark1){
//					RelativeLayout rl_two1 = (RelativeLayout)pageViews.get(1).findViewById(R.id.rl_two1);
//					rl_two1.setVisibility(View.VISIBLE);
//					RelativeLayout rl_two2 = (RelativeLayout)pageViews.get(1).findViewById(R.id.rl_two2);
//					rl_two2.setVisibility(View.VISIBLE);
//					ImageView iv_two1 = (ImageView)pageViews.get(1).findViewById(R.id.iv_two1);
//					iv_two1.startAnimation(alphaAnimation);
//					ImageView iv_two2 = (ImageView)pageViews.get(1).findViewById(R.id.iv_two2);
//					iv_two2.startAnimation(alphaAnimation1);
//					ImageView iv_two3 = (ImageView)pageViews.get(1).findViewById(R.id.iv_two3);
//					iv_two3.startAnimation(alphaAnimation2);
//					ImageView iv_two4 = (ImageView)pageViews.get(1).findViewById(R.id.iv_two4);
//					iv_two4.startAnimation(alphaAnimation3);
//					ImageView iv_two5 = (ImageView)pageViews.get(1).findViewById(R.id.iv_two5);
//					iv_two5.startAnimation(alphaAnimation4);
//					ImageView iv_two6 = (ImageView)pageViews.get(1).findViewById(R.id.iv_two6);
//					iv_two6.startAnimation(alphaAnimation5);
//					mark1 = false;
//				}
//
//			}else if (position == 2){
//
//				if (mark2){
//					RelativeLayout rl_three1 = (RelativeLayout)pageViews.get(2).findViewById(R.id.rl_three1);
//					rl_three1.setVisibility(View.VISIBLE);
//					RelativeLayout rl_three2 = (RelativeLayout)pageViews.get(2).findViewById(R.id.rl_three2);
//					rl_three2.setVisibility(View.VISIBLE);
//					ImageView iv_three1 = (ImageView)pageViews.get(2).findViewById(R.id.iv_three1);
//					iv_three1.startAnimation(alphaAnimation);
//					ImageView iv_three2 = (ImageView)pageViews.get(2).findViewById(R.id.iv_three2);
//					iv_three2.startAnimation(alphaAnimation1);
//					ImageView iv_three3 = (ImageView)pageViews.get(2).findViewById(R.id.iv_three3);
//					iv_three3.startAnimation(alphaAnimation2);
//					ImageView iv_three4 = (ImageView)pageViews.get(2).findViewById(R.id.iv_three4);
//					iv_three4.startAnimation(alphaAnimation3);
//					ImageView iv_three5 = (ImageView)pageViews.get(2).findViewById(R.id.iv_three5);
//					iv_three5.startAnimation(alphaAnimation4);
//					ImageView iv_three6 = (ImageView)pageViews.get(2).findViewById(R.id.iv_three6);
//					iv_three6.startAnimation(alphaAnimation5);
//					mark2 = false;
//				}
//			}else if (position == 3){
//
//				if (mark3){
//					ImageView iv_four = (ImageView)pageViews.get(3).findViewById(R.id.iv_four);
//					iv_four.setVisibility(View.VISIBLE);
//					iv_four.startAnimation(alphaAnimation);
//					LinearLayout ll_four = (LinearLayout)pageViews.get(3).findViewById(R.id.ll_four);
//					ll_four.setVisibility(View.VISIBLE);
//					ImageView iv_four1 = (ImageView)pageViews.get(3).findViewById(R.id.iv_four1);
//					iv_four1.startAnimation(alphaAnimation1);
//					ImageView iv_four2 = (ImageView)pageViews.get(3).findViewById(R.id.iv_four2);
//					iv_four2.startAnimation(alphaAnimation2);
//					ImageView imageView1 = (ImageView)pageViews.get(3).findViewById(R.id.imageView1);
//					imageView1.setVisibility(View.VISIBLE);
//					imageView1.startAnimation(alphaAnimation3);
//
//					mark3 = false;
//				}
//			}

		}

	}
	// 开始按钮方法，开始按钮在XML文件中onClick属性设置；
		// 我试图把按钮在本activity中实例化并设置点击监听，但总是报错，使用这个方法后没有报错，原因没找到
		public void startbutton(View v) {
//			if (player != null) {
//				player.onDestroy();
//			}
			//Intent intent = new Intent(WhatsnewPagesA.this, WhatsnewAnimationA.class);
			Intent intent = new Intent();
			intent.setClass(Navigation.this, HomeActivity.class);
			startActivity(intent);
			Navigation.this.finish();
		}
	
}
