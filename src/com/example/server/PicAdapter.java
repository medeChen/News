package com.example.server;

import java.util.List;

import com.example.daliynews.R;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class PicAdapter<T> extends CommonAdapter<T> {

	public PicAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		super(context, mDatas, itemLayoutId);
		ImageLoader.getInstance().init(
				ImageLoaderConfiguration.createDefault(context));
	}

	@Override
	public void convert(ViewHolder helper, T item, int position) {
		News row = (News) item;
		helper.setText(R.id.tv1, row.getTitle());
		Log.w("title", row.getTitle());
		helper.setText(R.id.tv2, row.getDes());
		ImageView imageView = helper.getView(R.id.iv_show);
		ImageLoader.getInstance().displayImage("http://avatar.csdn.net/D/8/2/1_u011072139.jpg", imageView);
	}

}
