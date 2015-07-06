package com.fishpan.lightning;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private ViewPager viewpager;
	private List<View> imageViews = new ArrayList<View>();
	private int[] guidies = {R.drawable.guide_map1,R.drawable.guide_map2,R.drawable.guide_map3,R.drawable.guide_map4};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewpager = (ViewPager) findViewById(R.id.guid_viewpager);
		//	显示界面
		initView();
	}
	 
	/**
	 * 初始化界面
	 */
	private void initView(){
		viewpager.setAdapter(new PagerAdapter() {
			@Override
			public boolean isViewFromObject(View view, Object object) { 
				return view == object;
			}
			
			@Override
			public int getCount() { 
				return guidies.length;
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) { 
				container.removeView(imageViews.get(position)); 
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				if(position >= imageViews.size()){
					ImageView imageView = new ImageView(MainActivity.this);
					imageView.setImageDrawable(getResources().getDrawable(guidies[position]));
					imageViews.add(imageView);
					container.addView(imageView);
				}else{
					container.addView(imageViews.get(position));
				}
				return imageViews.get(position);
			} 
		});
		
		
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			private LinearLayout guideIndexLinearLayout = (LinearLayout) findViewById(R.id.guid_index_line);
			@Override
			public void onPageSelected(int position) { 
				Log.i("tran_tag", "select page:" + position);
				int count = guideIndexLinearLayout.getChildCount();
				ImageView temp = null;
				for(int i = 0; i < count; i ++){
					temp = (ImageView) guideIndexLinearLayout.getChildAt(i);
					temp.setImageDrawable(getResources().getDrawable(R.drawable.pagers_index_bg));
				}
				
				ImageView selectedImg = (ImageView) guideIndexLinearLayout.getChildAt(position);
				selectedImg.setImageDrawable(getResources().getDrawable(R.drawable.pagers_index_selected));
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) { }
			
			@Override
			public void onPageScrollStateChanged(int arg0) { }
		});
	}
}
