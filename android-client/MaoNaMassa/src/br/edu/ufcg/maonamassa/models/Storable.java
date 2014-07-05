package br.edu.ufcg.maonamassa.models;

import java.lang.reflect.Field;

/*
 * Classe que deve ser herdada por todos os objetos que irão ser armazenados 
 * na Datastore do Google App Engine server. Essa classe abstrai a create, update e delete.
 * Breve colocarei mais detalhes de como funcionará, porque ainda não pensei.
 */

public abstract class Storable<T> {

	private Field[] getFields() {
		Class<?> aClass = this.getClass();
		return aClass.getFields();
	}
	
	public void create(){
		StringBuilder handlerArguments = new StringBuilder();
		handlerArguments.append("create" + this.getClass().getName() + "?");
		for(Field field: getFields()){
			String value = "";
			try {
				value = field.get(this).toString();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			handlerArguments.append(field.getName() + "=" + value);
		}
		sendRequest(handlerArguments.toString());
	}
	
	private void sendRequest(String handlerArguments) {
		// TODO Envia o request para o server com os argumentos
	}

	public void update(){
		StringBuilder handlerArguments = new StringBuilder();
		handlerArguments.append("update" + this.getClass().getName() + "?");
		for(Field field: getFields()){
			String value = "";
			try {
				value = field.get(this).toString();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			handlerArguments.append(field.getName() + "=" + value);
		}
		sendRequest(handlerArguments.toString());
	}
	
	public void delete(){
		StringBuilder handlerArguments = new StringBuilder();
		handlerArguments.append("delete" + this.getClass().getName() + "?");
		String value = "";
		Field field = null;
		try {
			field = this.getClass().getField("id");
			value = field.get(this).toString();
		} catch (Exception e) {
				e.printStackTrace();
		} 
		handlerArguments.append(field.getName() + "=" + value);
		sendRequest(handlerArguments.toString());
	}
	
}
