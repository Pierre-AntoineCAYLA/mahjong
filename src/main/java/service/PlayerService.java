package service;

import java.util.List;

import model.Player;

public abstract class PlayerService {

	private static final PlayerService instance = new PlayerServiceImpl();
    public static PlayerService getInstance()
    {
        return instance;
    }
	public abstract void createPlayer(String name1, String name2, String name3, String name4, Integer playerEst);
	public abstract List<Player> getPlayers();
}
