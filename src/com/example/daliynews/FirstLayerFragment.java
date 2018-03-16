package com.example.daliynews;

import java.util.HashMap;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pages.View1;
import com.example.pages.View10;
import com.example.pages.View11;
import com.example.pages.View12;
import com.example.pages.View2;
import com.example.pages.View3;
import com.example.pages.View4;
import com.example.pages.View5;
import com.example.pages.View6;
import com.example.pages.View7;
import com.example.pages.View8;
import com.example.pages.View9;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.IndicatorViewPager.IndicatorFragmentPagerAdapter;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

public class FirstLayerFragment extends LazyFragment {
	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	public static final String INTENT_STRING_TABNAME = "intent_String_tabname";
	public static final String INTENT_INT_INDEX = "intent_int_index";
	private String tabName;
	private int index, page, x = 4;
	private String[] tag;
	Map<Integer, Map<Integer, Fragment>> pages = new HashMap<Integer, Map<Integer, Fragment>>();
	Map<Integer, Fragment> views;
	Fragment[] a = { new View1(), new View2(), new View3(), new View4() };
	Fragment[] b = { new View5(), new View6(), new View7(), new View8() };
	Fragment[] c = { new View9(), new View10(), new View11(), new View12() };

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_tabmain);
		Resources res = getResources();

		Bundle bundle = getArguments();
		index = bundle.getInt(INTENT_INT_INDEX);
		tag = bundle.getStringArray("tag");
		page = bundle.getInt("position");
		Log.w("hello",Integer.toString(page));
		views = new HashMap<Integer, Fragment>();
		for (int i = 0; i < a.length; i++) {
			views.put(i, a[i]);
		}
		pages.put(0, views);
		views = new HashMap<Integer, Fragment>();
		for (int i = 0; i < b.length; i++) {
			views.put(i, b[i]);
		}
		pages.put(1, views);
		views = new HashMap<Integer, Fragment>();
		for (int i = 0; i < c.length; i++) {
			views.put(i, c[i]);
		}
		pages.put(2, views);

		ViewPager viewPager = (ViewPager) findViewById(R.id.fragment_tabmain_viewPager);
		Indicator indicator = (Indicator) findViewById(R.id.fragment_tabmain_indicator);
		indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED,
				5));
		float unSelectSize = 16;
		float selectSize = unSelectSize * 1.2f;

		int selectColor = res.getColor(R.color.tab_top_text_2);
		int unSelectColor = res.getColor(R.color.tab_top_text_1);
		indicator.setOnTransitionListener(new OnTransitionTextListener()
				.setColor(selectColor, unSelectColor).setSize(selectSize,
						unSelectSize));

		viewPager.setOffscreenPageLimit(4);

		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getApplicationContext());

		indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

	}

	@Override
	protected void onResumeLazy() {
		super.onResumeLazy();
	}

	@Override
	protected void onFragmentStartLazy() {
		super.onFragmentStartLazy();
	}

	@Override
	protected void onFragmentStopLazy() {
		super.onFragmentStopLazy();
	}

	@Override
	protected void onPauseLazy() {
		super.onPauseLazy();
	}

	@Override
	protected void onDestroyViewLazy() {
		super.onDestroyViewLazy();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private class MyAdapter extends IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return x;
		}

		@Override
		public View getViewForTab(int position, View convertView,
				ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_top, container,
						false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(tag[position]);
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {
			int a = position;
			Log.w("page",Integer.toString(a));
			return pages.get(page).get(a);
		}
	}

}
