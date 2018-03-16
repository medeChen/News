package com.example.pages;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.daliynews.R;
import com.example.daliynews.Web;
import com.example.server.JsonParser;
import com.example.server.News;
import com.example.server.NewsAdapter;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.shizhefei.fragment.LazyFragment;

public class View12 extends LazyFragment  {
	List<News> ne;
	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.view12);
		getWebData();
	}
	

	private void getWebData() {
		// TODO 自动生成的方法存根
//		RequestParams params = new RequestParams();
//		params.addQueryStringParameter("name", "1");
//		params.addQueryStringParameter("name2", "1");
//
//		
//		HttpUtils http = new HttpUtils();
//		http.configHttpCacheSize(0);
//		http.send(HttpRequest.HttpMethod.POST,
//				"http://172.29.1.5:8080/axis2/services/WangJieService/getNews",
//				params, new RequestCallBack<String>() {
		
		HttpUtils http = new HttpUtils();
		http.configHttpCacheSize(0);
		http.send(HttpRequest.HttpMethod.GET,
				"http://10.158.56.89:8080/axis2/services/MyService/getNewslist12",
				 new RequestCallBack<String>() {

					@Override
					public void onStart() {
						findViewById(R.id.pb_show).setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
					}
					

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						findViewById(R.id.pb_show).setVisibility(View.GONE);
						String json = responseInfo.result;
						json = json.replace("<ns:getNewslist12Response xmlns:ns=\"http://aishang.ypf.com\"><ns:return>", "");
						json = json.replace("</ns:return></ns:getNewslist12Response>", "");
						List<News> listNews = new JsonParser<News>().parserJsonList(json, News.class);
						ne=listNews;
						Log.w("hello", ne.get(0).getUrl());
						ListView listView = (ListView)findViewById(R.id.lv_data);
						NewsAdapter<News> adapter = new NewsAdapter<News>(View12.this.getActivity(), listNews, R.layout.news1);
						listView.setAdapter(adapter);
						listView.setOnItemClickListener(new OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent,View view,int position,long id){
								Intent intent = new Intent(View12.this.getActivity(), Web.class);
								Bundle bundle = new Bundle();
								bundle.putString("url", ne.get(position).getUrl());
								intent.putExtras(bundle);
								startActivity(intent);
							}
						});
						
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						findViewById(R.id.pb_show).setVisibility(View.GONE);
					}
				});
	}
}
