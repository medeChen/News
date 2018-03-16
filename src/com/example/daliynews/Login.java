package com.example.daliynews;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.db.Dbuser;
import com.shizhefei.fragment.LazyFragment;

public class Login extends LazyFragment {
	com.example.db.Dbuser dbhelper;

	@Override
	protected void onCreateViewLazy(final Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.about);
		ImageView iv = (ImageView) findViewById(R.id.imageView4);
		iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this.getActivity(), Fav.class);
				startActivity(intent);
			}
		});

		ImageView user = (ImageView) findViewById(R.id.imageView3);
		user.setOnClickListener(new OnClickListener() {

			private Dialog dialog;

			@Override
			public void onClick(View v) {
				final View layout = View.inflate(getActivity(), R.layout.log,
						null);
				dialog = new AlertDialog.Builder(Login.this.getActivity())
						.setTitle("登陆")
						.setView(layout)
						.setNegativeButton("注册",
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialoginterface,
											int i) {
										dialog.dismiss();
										// 按钮事件
										Toast.makeText(
												Login.this.getActivity(),
												"注册成功", Toast.LENGTH_LONG)
												.show();
										dbhelper = new com.example.db.Dbuser(
												Login.this.getActivity());
										String[] args = {
												((EditText) layout
														.findViewById(R.id.user))
														.getText().toString(),
												((EditText) layout
														.findViewById(R.id.psd))
														.getText().toString() };
										String[] column = { "[Username]",
												"[Password]" };

										ContentValues c = new ContentValues();
										for (int j = 0; j < args.length; j++) {
											c.put(column[j], args[j]);
										}
										SQLiteDatabase dbs = dbhelper
												.getWritableDatabase();
										dbs.insert("user", null, c);
										TextView user = (TextView) findViewById(R.id.user);
										user.setText(((EditText) layout
												.findViewById(R.id.user))
												.getText().toString());
									}
								})
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialoginterface,
											int i) {
										dialog.dismiss();
										// 按钮事件
										dbhelper = new Dbuser(Login.this
												.getActivity());
										String se = ((EditText) layout
												.findViewById(R.id.user))
												.getText().toString();
										SQLiteDatabase dbs = dbhelper
												.getWritableDatabase();
										Cursor cursor = dbs
												.rawQuery(
														"select * from user where Username like ? ",
														new String[] { "%" + se
																+ "%" });
										if (cursor.moveToNext()) {
											if (((EditText) layout
													.findViewById(R.id.psd))
													.getText().toString().equals(cursor
															.getString(1))) {
												TextView user = (TextView) findViewById(R.id.user);
												user.setText(cursor
														.getString(0));
												Toast.makeText(
														Login.this
																.getActivity(),
														"登陆成功",
														Toast.LENGTH_LONG)
														.show();
											} else {
												Toast.makeText(
														Login.this
																.getActivity(),
														"密码错误",
														Toast.LENGTH_LONG)
														.show();
											}
										} else {
											Toast.makeText(
													Login.this.getActivity(),
													"error", Toast.LENGTH_LONG)
													.show();
										}
									}
								}).create();
				dialog.show();

			}
		});

	}
}
