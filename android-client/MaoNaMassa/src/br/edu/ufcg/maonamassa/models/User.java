package br.edu.ufcg.maonamassa.models;

public class User {
	
	private Long id;
	
	private String email;
	
	private String name;
	
	private String accessToken;
	
	private RecipeBook book;
	
	public User(Long id, String email, String name, String accessToken) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.accessToken = accessToken;
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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
