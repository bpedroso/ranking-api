package com.bpedroso.ranking.service;

import static com.bpedroso.ranking.cache.RankingCaching.absoluteRanking;
import static com.bpedroso.ranking.cache.RankingCaching.relativeRanking;
import static java.lang.Integer.valueOf;

import java.util.List;

import com.bpedroso.ranking.domain.User;

public class ListService {

	private static final String REGEX_REMOVE_LETTERS = "[a-zA-Z]";

	//relative -> At100/3 (3 around 100)
	public List<User> relative(String position, String around) {
		return relativeRanking(valueOf(position.replaceAll(REGEX_REMOVE_LETTERS, "")), valueOf(around));
	}

	//absolute -> Top100, Top200, Top500
	public List<User> absolute(String value) {
		return absoluteRanking(valueOf(value.replaceAll(REGEX_REMOVE_LETTERS, "")));
	}

}
