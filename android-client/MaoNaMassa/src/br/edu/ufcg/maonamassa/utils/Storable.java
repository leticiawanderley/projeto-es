package br.edu.ufcg.maonamassa.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.*;
import com.google.gson.*;


/*
 * Classe que deve ser herdada por todos os objetos que irão ser armazenados 
 * na Datastore do Google App Engine server. Essa classe abstrai a create, update e delete.
 * Breve colocarei mais detalhes de como funcionará, porque ainda não pensei.
 */

public abstract class Storable<T> {

	public void create(){
		
		String result = jsonify();
	
		String handlerArguments = "create?what=" + this.getClass().getName() + "json=" + result;
		
		sendRequest(handlerArguments);
	}

	public String jsonify() {
		Type fooType = new TypeToken<T>() {}.getType();
		Gson json = new GsonBuilder().serializeNulls().create();
		String result = json.toJson(this, fooType);
		return result;
	}
	
	public T desjsonify(String jsonstr) {
		Gson json = new GsonBuilder().serializeNulls().create();
		@SuppressWarnings("unchecked")
		T fromJson = (T) json.fromJson(jsonstr, this.getClass());
		return fromJson;
	}
	
	private void sendRequest(String handlerArguments) {
		// TODO Envia o request para o server com os argumentos
	}

	public void update(){
		
	}
	
	public void delete(){
		
	}
	
}
