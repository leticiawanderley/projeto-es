package br.edu.ufcg.maonamassa.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import br.edu.ufcg.maonamassa.models.User;

import com.google.gson.Gson;
import com.google.gson.reflect.*;
import com.google.gson.*;

/***
 * @author JoaoPedro
 * This class must be ONLY inherited by Recipe. 
 * All other classes are stored by hierarchy.
 * An Recipe object when is stored, also stores its Step objects.
 * Storable abstracts the create, update, delete and retrieve operations
 *
 * @param <Recipe>
 */
/*
 * 
 */

public abstract class Storable<Recipe> {
	
	
	public void create(User user) throws Exception {
		String result = jsonify();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Routes.CREATE_ROUTE + "?");
		stringBuilder.append("what=" + this.getClass().getName());
		stringBuilder.append("user_id=" + user.getId());
		stringBuilder.append("token=" + user.getAccessToken());
		stringBuilder.append("json=" + result);
		String handlerArguments = stringBuilder.toString();
		
		sendRequest(handlerArguments);
	}

	public String jsonify() {
		Type fooType = new TypeToken<Recipe>() {}.getType();
		Gson json = new GsonBuilder().serializeNulls().create();
		String result = json.toJson(this, fooType);
		return result;
	}
	
	public Recipe desjsonify(String jsonstr) {
		Gson json = new GsonBuilder().serializeNulls().create();
		@SuppressWarnings("unchecked")
		Recipe fromJson =  (Recipe) json.fromJson(jsonstr, this.getClass());
		return fromJson;
	}
	
	public String jsonifyList(List<Recipe> that) {
		Type fooType = new TypeToken<List<Recipe>>() {}.getType();
		Gson json = new Gson();
		String result = json.toJson(that, fooType);
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Recipe> desjsonifyList(String jsonstr) {
		Gson json = new GsonBuilder().serializeNulls().create();
		Type fooType = new TypeToken<Recipe>() {}.getType();
		List<Map> fromJson = json.fromJson(jsonstr, fooType);
		List<Recipe> resultList = new ArrayList<Recipe>();
		for(Map map:fromJson){
		       String tmpJson = json.toJson(map, fooType);
		       resultList.add(desjsonify(tmpJson));
		    }
		return resultList;
	}
	
	private String sendRequest(String handlerArguments) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Routes.SERVER_URL);
		stringBuilder.append("/");
		stringBuilder.append(handlerArguments);
		String url = stringBuilder.toString();
		// TODO Envia o request para o server com os argumentos
		
		HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response = httpclient.execute(new HttpGet(url));
	    StatusLine statusLine = response.getStatusLine();
	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        response.getEntity().writeTo(out);
	        out.close();
	        String responseString = out.toString();
	        return responseString;
	    } else{
	        //Closes the connection.
	        response.getEntity().getContent().close();
	        throw new IOException(statusLine.getReasonPhrase());
	    }
	}

	public void update(User user) throws Exception{
		String result = jsonify();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Routes.UPDATE_ROUTE + "?");
		stringBuilder.append("what=" + this.getClass().getName());
		stringBuilder.append("user_id=" + user.getId());
		stringBuilder.append("token=" + user.getAccessToken());
		stringBuilder.append("json=" + result);
		String handlerArguments = stringBuilder.toString();
		
		sendRequest(handlerArguments);
	}
	
	public void delete(User user) throws Exception{
		String result = jsonify();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Routes.DELETE_ROUTE + "?");
		stringBuilder.append("what=" + this.getClass().getName());
		stringBuilder.append("user_id=" + user.getId());
		stringBuilder.append("token=" + user.getAccessToken());
		stringBuilder.append("json=" + result);
		String handlerArguments = stringBuilder.toString();
		sendRequest(handlerArguments);
	}
	
	/*
	 * This method doesn't work for Android version above 3.0
	 */
	@Deprecated
	public List<Recipe> search(StorableQuery query) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Routes.SEARCH_ROUTE + "?");
		stringBuilder.append("where=" + query.getWhere());
		String desc = query.isDesc()?"true":"false";
		stringBuilder.append("desc=" + desc);
		stringBuilder.append("limit=" + query.getLimit());
		String handlerArguments = stringBuilder.toString();
		String result = null;
 
		result = sendRequest(handlerArguments);
		return desjsonifyList(result);
	}
	
	
	
	
}
