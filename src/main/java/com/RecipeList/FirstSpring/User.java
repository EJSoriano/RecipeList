package com.RecipeList.FirstSpring;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
		
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		private String login;
		private String password;
		
		protected User() { }
		
		public User(String login, String password) {
			this.login = login;
			this.password = password;
		}

		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		@Override
		public String toString() {
			return "ID: " + id + "\nLogin: " + login + "\nPassword: " + password + "\n";
		}
	

}
