package com.bpedroso.ranking.service;

import static com.bpedroso.ranking.cache.RankingCaching.reindex;
import static com.bpedroso.ranking.cache.UserCaching.userRankingInfo;
import static java.lang.Integer.valueOf;

import com.bpedroso.ranking.domain.User;

/*
 * 	absolute score -> {user: 123, score:250}
 *	relative score -> {user: 123, score: "+10"} or {user: 123, score: " 10"} or {user: 123, score: "-20"}
 */
public class UpdateService {

	public void update(String userId, String score) {
		final Integer actualScore = userRankingInfo.get(userId);
		
		int updatedScore = valueOf(score.trim());
		if (score.contains("+") || score.contains(" ") || score.contains("-")) {
			updatedScore = updatedScore + actualScore;
		}

		reindex(new User(userId, updatedScore));
		userRankingInfo.put(userId, updatedScore);
	}

}
