package br.edu.ufcg.maonamassa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import br.edu.ufcg.maonamassa.models.Recipe;
import br.edu.ufcg.maonamassa.models.RecipeBook;
import br.edu.ufcg.maonamassa.models.User;

@SuppressLint("CommitPrefEdits")
public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;
	SharedPreferences prefBook;
	RecipeBook book = new RecipeBook();
	// Editor for Shared preferences
	Editor editor;
	Editor editorBook;
	
	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "Pref";
	private static final String PREFBOOK_NAME = "PrefBook";

	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";

	// User name (make variable public to access from outside)
	public static final String KEY_NAME = "name";

	// Email address (make variable public to access from outside)
	public static final String KEY_EMAIL = "email";

	public static final String KEY_PHOTO = "photo";

	public static final String KEY_TOKEN = "token";

	public static final String KEY_ID = "id";
	
	public static final String KEY_BOOK = "book";
	
	public static final String KEY_DONO = "donoName";

	// Constructor
	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		prefBook = _context.getSharedPreferences(PREFBOOK_NAME, PRIVATE_MODE);
		editor = pref.edit();
		editorBook = prefBook.edit();
		
	}

	/**
	 * Create login session
	 * */
	public void createLoginSession(String id, String name, String email,
			String photo, String token) {
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);

		// Storing name in pref
		editor.putString(KEY_NAME, name);

		// Storing email in pref
		editor.putString(KEY_EMAIL, email);

		// Storing photo in pref
		editor.putString(KEY_PHOTO, photo);
		editor.putString(KEY_ID, id);
		editor.putString(KEY_TOKEN, token);

		// commit changes
		editor.commit();
	}

	/**
	 * Check login method will check user login status If false it will redirect
	 * user to login page Else won't do anything
	 * */
	public void checkLogin() {
		// Check login status
		if (!this.isLoggedIn()) {
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, LoginActivity.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			// Staring Login Activity
			_context.startActivity(i);
		}

	}

	/**
	 * Get stored session data
	 * */
	public User getUserDetails() {
		return new User(pref.getString(KEY_ID, null), pref.getString(KEY_EMAIL,
				null), pref.getString(KEY_NAME, null), pref.getString(
				KEY_PHOTO, null), pref.getString(KEY_TOKEN, null));
	}
	
	
	
	/**
	 * Clear session details
	 * */
	public void logoutUser() {
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();

		// After logout redirect user to Main
		Intent i = new Intent(_context, MainActivity.class);
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// Add new Flag to start new Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		// Staring Login Activity
		_context.startActivity(i);
	}

	/**
	 * Quick check for login
	 * **/
	// Get Login State
	public boolean isLoggedIn() {
		return pref.getBoolean(IS_LOGIN, false);
	}
	
	public void addRecipeToBook(Recipe recipe) {
		book.addRecipe(recipe);
		saveBook();
		
	}
	public void saveBook(){
		RecipeBook previousRecipes = getBook();
		
		List<Recipe> recipes = book.getRecipes();
		recipes.addAll(previousRecipes.getRecipes());
		Set<String> recipe = new TreeSet<String>();
		for (Recipe r : recipes) {
			recipe.add(r.jsonify());
		}
		System.out.println(recipes.size());
		editorBook.putString(KEY_DONO, getUserDetails().getId());
		editorBook.putStringSet(KEY_BOOK, recipe);
		editorBook.commit();
	}
	
	public RecipeBook getBook(){
		Set<String> bookJ = prefBook.getStringSet(KEY_BOOK, null);
		RecipeBook book = new RecipeBook();
		Recipe x = new Recipe(null, null, null);
		for (String r : bookJ) {
			book.addRecipe(x.desjsonify(r));
		}
		return book;
	}
	
}