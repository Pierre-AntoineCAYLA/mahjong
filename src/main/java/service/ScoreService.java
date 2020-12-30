package service;

import java.util.Iterator;
import java.util.List;

import model.Player;

public class ScoreService {

	public static void updateScorePlayer(List<Integer> scores, Integer mahJongPlayer) {
		List<Player> players = PlayerService.getInstance().getPlayers();
		int i = 0;
		int lastEastPlayer = 5;
		Iterator<Player> iterator = players.iterator();
		boolean find = false;
		while (iterator.hasNext() && !find) {
			Player player = iterator.next();
			if ((player).isEast()) {
				find = true;
				lastEastPlayer = player.getIndex();
			}
		}
		for (Player player : players) {
			Integer score = 0;
			if (player.isEast()) {
				for (int j = 0; j < 4; j++) {
					if (j != i)
						score = score + 2 * (scores.get(i) - scores.get(j));
				}
				player.addScore(score);
			} else {
				for (int j = 0; j < 4; j++) {
					if (j == lastEastPlayer)
						score = score + 2 * (scores.get(i) - scores.get(j));
					else
						score = score + scores.get(i) - scores.get(j);
				}
				player.addScore(score);
			}
			i = i + 1;
		}
		players.get(lastEastPlayer).setEast(false);
		players.get(mahJongPlayer).setEast(true);
	}

}
