package br.edu.ufcg.maonamassa.utils;

public class StorableQuery {

	private String order;
	

	private boolean desc;
	private String where;
	private int limit;
	
	
	public StorableQuery() {
		this.desc = false;
	}
	
	public String getOrder() {
		return order;
	}

	public boolean isDesc() {
		return desc;
	}

	public String getWhere() {
		return where;
	}

	public int getLimit() {
		return limit;
	}
	
	public StorableQuery order(String order){
		this.order = order;
		return this;
	}
	
	public StorableQuery desc() {
		this.desc = true;
		return this;
	}
	
	public StorableQuery where(String where) {
		this.where = where;
		return this;
	}
	
	public StorableQuery limit(int limit){
		this.limit = limit;
		return this;
	}


	
	
}
