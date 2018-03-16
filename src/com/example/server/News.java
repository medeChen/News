package com.example.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class News {
//	private int NavigationId;
//
//	private int Id;
//
//	private String Navigation;
//
//	public void setNavigationId(int NavigationId) {
//		this.NavigationId = NavigationId;
//	}
//
//	public int getNavigationId() {
//		return this.NavigationId;
//	}
//
//	public void setId(int Id) {
//		this.Id = Id;
//	}
//
//	public int getId() {
//		return this.Id;
//	}
//
//	public void setNavigation(String Navigation) {
//		this.Navigation = Navigation;
//	}
//
//	public String getNavigation() {
//		return this.Navigation;
//	}
	private String des;

	private String title;

	private String url;

	public void setDes(String des){
	this.des = des;
	}
	public String getDes(){
	return this.des;
	}
	public void setTitle(String title){
	this.title = title;
	}
	public String getTitle(){
	return this.title;
	}
	public void setUrl(String url){
	this.url = url;
	}
	public String getUrl(){
	return this.url;
	}



}