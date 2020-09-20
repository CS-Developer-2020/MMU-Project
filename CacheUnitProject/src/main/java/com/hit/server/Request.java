package com.hit.server;

import java.util.Dictionary;
import java.util.Hashtable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class Request<T>
extends java.lang.Object
implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Hashtable<String, String> headers;
	private T body;
	
	public Request(Hashtable<String, String> headers, T body) {
		this.setAction(headers);
		this.setBody(body);
	}

	public Dictionary<String, String> getHeader() {
		return headers;
	}

	public void setAction(Hashtable<String, String> headers) {
		this.headers = headers;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	@Override
	public String toString() 
	{
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(this);
	}
}
