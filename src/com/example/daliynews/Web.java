package com.example.daliynews;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Web extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		Bundle bundle=this.getIntent().getExtras();
		WebView web=(WebView) findViewById(R.id.web);
		web.loadUrl(bundle.get("url").toString());
	}

}
