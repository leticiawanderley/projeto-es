package br.edu.ufcg.maonamassa.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
 
public class HttpURLConnection {
 
	private final static String USER_AGENT = "Mozilla/5.0";
 
	
	// HTTP GET request
	public static String sendGet(String urlWithParameters) throws Exception {
 
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(urlWithParameters);
 
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
 
		HttpResponse response = client.execute(request);
 
		System.out.println("Response Code : " + 
                       response.getStatusLine().getStatusCode());
 
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		return result.toString();
 
	}
 
	// HTTP POST request
	public static String sendPost(List<NameValuePair> parameters) throws Exception {
 
		String url = "https://selfsolve.apple.com/wcResults.do";
 
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
 
		// add header
		post.setHeader("User-Agent", USER_AGENT);
 
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));
 
		post.setEntity(new UrlEncodedFormEntity(parameters));
 
		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
                                    response.getStatusLine().getStatusCode());
 
		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		return result.toString();
 
	}
 
}