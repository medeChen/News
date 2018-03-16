package com.example.daliynews;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.shizhefei.view.viewpager.SViewPager;

public class Fragment1 extends FragmentActivity {
	private IndicatorViewPager indicatorViewPager;
	private String[] tag1 ={"政治","世界","本地","军事"};
	private String[] tag2 ={"金融","教育","美食","收藏"};
	private String[] tag3 ={"科技","健康","汽车","航空"};

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_tabmain);
		if (android.os.Build.VERSION.SDK_INT > 9) {  
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();  
		    StrictMode.setThreadPolicy(policy);  
		}  
		SViewPager viewPager = (SViewPager) findViewById(R.id.tabmain_viewPager);
		Indicator indicator = (Indicator) findViewById(R.id.tabmain_indicator);
		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		indicatorViewPager
				.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setCanScroll(false);
		viewPager.setOffscreenPageLimit(4);
	}

	private class MyAdapter extends IndicatorFragmentPagerAdapter {
		private String[] tabNames = { "时事", "生活", "科技", "我" };
		private LayoutInflater inflater;

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
			inflater = LayoutInflater.from(getApplicationContext());
		}

		@Override
		public int getCount() {
			return tabNames.length;
		}

		@Override
		public View getViewForTab(int position, View convertView,
				ViewGroup container) {
			if (convertView == null) {
				convertView = (TextView) inflater.inflate(R.layout.tab_main,
						container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(tabNames[position]);
			textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			return textView;
		}

		// 每个页面显示的内容
		@Override
		public Fragment getFragmentForPage(int position) {
			FirstLayerFragment mainFragment = new FirstLayerFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("position", position);
			if (position == 3) {
				return new Login();
			} else if (position == 0) {
				bundle.putStringArray("tag", tag1);
				bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX, position);
				mainFragment.setArguments(bundle);
				return mainFragment;
			} else if(position == 1){
				bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME,
						tabNames[position]);
				bundle.putStringArray("tag", tag2);
				bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX, position);
				mainFragment.setArguments(bundle);
				return mainFragment;
			}else{
				bundle.putString(FirstLayerFragment.INTENT_STRING_TABNAME,
						tabNames[position]);
				bundle.putStringArray("tag", tag3);
				bundle.putInt(FirstLayerFragment.INTENT_INT_INDEX, position);
				mainFragment.setArguments(bundle);
				return mainFragment;
			}
		}
	}
}
