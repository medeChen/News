package com.example.server;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.daliynews.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class NewsAdapter<T> extends CommonAdapter<T> {
	com.example.db.Dbservice dbhelper;
	Context context1;
	News row;

	public NewsAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		super(context, mDatas, itemLayoutId);
		context1=context;
		ImageLoader.getInstance().init(
				ImageLoaderConfiguration.createDefault(context));
	}

	@Override
	public void convert(ViewHolder helper, T item, int position) {
		
		row = (News) item;
		helper.setText(R.id.tv1, row.getTitle());
		helper.setText(R.id.tv2, row.getDes());
		ImageView imageView = helper.getView(R.id.iv_show);
		ImageLoader.getInstance().displayImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1995477228,4272631840&fm=26&gp=0.jpg", imageView);
		Button btn=helper.getView(R.id.fav);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbhelper = new com.example.db.Dbservice(context1);
				String[] args = {
						row.getTitle(),
						row.getUrl()};
				String[] column = { "[Title]", "[Url]" };

				ContentValues c = new ContentValues();
				for (int i = 0; i < args.length; i++) {
					c.put(column[i], args[i]);
				}
				SQLiteDatabase dbs = dbhelper.getWritableDatabase();
				dbs.insert("fav", null, c);
			}
		});
	
	
	}

}
