package com.example.daliynews;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorPagerAdapter;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorViewPagerAdapter;

public class GuideActivity extends FragmentActivity {
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	Intent it;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_guide);
		ViewPager viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
		Indicator indicator = (Indicator) findViewById(R.id.guide_indicator);
		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getApplicationContext());
		indicatorViewPager.setAdapter(adapter);
	}

	private IndicatorPagerAdapter adapter = new IndicatorViewPagerAdapter() {
		private int[] images = { R.drawable.p1, R.drawable.p2, R.drawable.p3,
				R.drawable.p4 };

		@Override
		public View getViewForTab(int position, View convertView,
				ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_guide, container,
						false);
			}
			return convertView;
		}

		@Override
		public View getViewForPage(int position, View convertView,
				ViewGroup container) {
			if (convertView == null) {
				convertView = new View(getApplicationContext());
				convertView.setLayoutParams(new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			}
			if (position == 0) {
				it = new Intent(GuideActivity.this, Fragment1.class);
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						startActivity(it);
						finish();
					}
				};
				timer.schedule(task, 1000 * 3);
			}
			convertView.setBackgroundResource(images[position]);
			return convertView;
		}

		@Override
		public int getCount() {
			return images.length;
		}
	};
}
