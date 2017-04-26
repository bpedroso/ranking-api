package com.bpedroso.ranking.cache;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.synchronizedSortedMap;
import static java.util.Optional.ofNullable;

import java.util.HashSet;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.bpedroso.ranking.domain.User;

public interface RankingCaching {

	static final SortedMap<Integer, HashSet<String>> gameRanking = synchronizedSortedMap(new TreeMap<Integer, HashSet<String>>(reverseOrder()));
	
	static void reindex(User user) {
		HashSet<String> users = ofNullable(gameRanking.get(user.getScore())).orElse(new HashSet<>());
		users.add(user.getUserId());
		gameRanking.put(user.getScore(), users);
	}

	static List<User> absoluteRanking(int count) {
		return gameRanking.entrySet().stream()
				.limit(count)
				.flatMap(rank -> rank.getValue().stream().map(user -> new User(user, rank.getKey())))
				.limit(count)
				.collect(Collectors.toList());
	}

	static List<User> relativeRanking(int position, int around) {
		return gameRanking.entrySet().stream()
				.skip(position - around)
				.limit(around * 2 + 1)
				.peek(System.out::println)
				.flatMap(rank -> rank.getValue().stream().map(user -> new User(user, rank.getKey())))
				.collect(Collectors.toList());
	}
}
