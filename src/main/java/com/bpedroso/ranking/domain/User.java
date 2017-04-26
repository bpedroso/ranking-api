package com.bpedroso.ranking.domain;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private int score;
	
	public User(String userId, int score) {
		super();
		this.userId = userId;
		this.score = score;
	}

	public String getUserId() {
		return userId;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", score=" + score + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
