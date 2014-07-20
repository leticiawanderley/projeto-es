package br.edu.ufcg.maonamassa.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	private final String SERVER_URL = "https://mao-na-massa.appspot.com";
	private final String CREATE_ROUTE = "create/";
	private final String UPDATE_ROUTE = "update/";
	private final String DELETE_ROUTE = "delete/";
	private final String SEARCH_ROUTE = "search/";

	public void create(User user) throws Exception {
		String result = jsonify();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(CREATE_ROUTE + "?");
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
	
	@SuppressWarnings("unchecked")
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
		stringBuilder.append(SERVER_URL);
		stringBuilder.append("/");
		stringBuilder.append(handlerArguments);
		String url = stringBuilder.toString();
		// TODO Envia o request para o server com os argumentos
		return HttpURLConnection.sendGet(url);
	}

	public void update(User user) throws Exception{
		String result = jsonify();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(UPDATE_ROUTE + "?");
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
		stringBuilder.append(DELETE_ROUTE + "?");
		stringBuilder.append("what=" + this.getClass().getName());
		stringBuilder.append("user_id=" + user.getId());
		stringBuilder.append("token=" + user.getAccessToken());
		stringBuilder.append("json=" + result);
		String handlerArguments = stringBuilder.toString();
		sendRequest(handlerArguments);
	}
	
	public List<Recipe> search(StorableQuery query) throws Exception{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(SEARCH_ROUTE + "?");
		stringBuilder.append("where=" + query.getWhere());
		String desc = query.isDesc()?"true":"false";
		stringBuilder.append("desc=" + desc);
		stringBuilder.append("limit=" + query.getLimit());
		String handlerArguments = stringBuilder.toString();
		String result = sendRequest(handlerArguments);
		return desjsonifyList(result);
	}
	
}
