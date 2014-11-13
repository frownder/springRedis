package my;

import my.starworld.Player;
import java.util.List;

/**
 * Interface for GameService using Memory
 * which simplifies the game main logic of server-based MMORTS Game.
 * - holds Player object in memory (and providing all game logic if it's real)
 * 
 * @author yong.kim
 * @Date 2014.10.13
 */
public interface IMemGameService {

	/**
	 * returns all Player 
	 * @return List of all player which hashMapAllPlayer contains
	 */
	public List<Player> getPlayerList();
	/**
	 * Finds the player and returns it. Using playerId parameter.
	 * @param playerId  player's unique Id.
	 * @return player   player that matches playerId parameter
	 */
	public Player getPlayer(String playerId);
	
	/**
	 * restoring Player from redis to Memory of GameService
	 *  - needed when GameService server has some problem so there's no player in Memory.
	 *   
	 * @param player obtained player from redis. 
	 */
	public void restorePlayer(Player player);
	
		
	public void createPlayer(String playerId);
	
	/**
	 * adds specified unit to the Player.
	 *  - throws exception when parameter is wrong
	 * @param playerId player's unique Id.
	 * @param unitType M:marine, E:medic, G:Ghost, F:Firebat
	 */
	public void addUnit(String playerId, char unitType) throws Exception;
	
	public void groupAll(String playerId);
	
	
	/**
	 * command to selected group of the Player.
	 *  - throws exception when parameter is wrong
	 * @param playerId player's unique Id.
	 * @param commandType G:go, H:Hold, F:Fire, S:Stimpack
	 * @return String simple string to explain acts of units
	 */
	public String groupCommand(String playerId, char commandType) throws Exception;
	
}
