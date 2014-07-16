package br.edu.ufcg.maonamassa.models;

public class User {
	
	private Long id;
	
	private String email;
	
	private RecipeBook book;
	
	public User(String email) {
		this.email = email;
		this.book = new RecipeBook();
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RecipeBook getBook() {
		return book;
	}

	public void setBook(RecipeBook book) {
		this.book = book;
	}	
}
