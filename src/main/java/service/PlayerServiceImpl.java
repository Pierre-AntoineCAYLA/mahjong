package service;

import java.util.ArrayList;
import java.util.List;

import model.Player;

public class PlayerServiceImpl extends PlayerService{

	private static List<Player> players=new ArrayList<>();
	
	@Override
	public void createPlayer(String name1, String name2, String name3, String name4, Integer estPlayer) {
		players.add(new Player(name1,0));
		players.add(new Player(name2,1));
		players.add(new Player(name3,2));
		players.add(new Player(name4,3));
		players.get(estPlayer).setEast(true);
	}
	
	@Override
	public List<Player> getPlayers(){
		return players;
	}
}
