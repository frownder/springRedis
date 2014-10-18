package my;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import my.starworld.*;



@Service
public class MemGameService implements IMemGameService {

	private HashMap<String, Player> hashMapAllPlayer = new HashMap<String,Player>();
	
	/**
	 * Finds the player and returns it. Using playerId parameter.
	 * @param playerId  player's unique Id.
	 * @return player   player that matches playerId parameter
	 */
	public Player getPlayer(String playerId){
		return hashMapAllPlayer.get(playerId);
	}
	
	
	/**
	 * restoring Player from redis to Memory of GameService
	 *  - needed when GameService server has some problem so there's no player in Memory.
	 *   
	 * @param player obtained player from redis. 
	 */
	public void restorePlayer(Player player) {
		if( !hashMapAllPlayer.containsKey( player.getPlayerId() ))
			hashMapAllPlayer.put( player.getPlayerId(), player);
	}

	
	/**
	 * create a new player with playerId 
	 * @param playerId unique id of player
	 */
	public void createPlayer(String playerId) {
		hashMapAllPlayer.put(playerId, new Player(playerId));
	}

	/**
	 * add a unit to a specified player  
	 * @param playerId unique id of player
	 * @param unitType  M:Marine, E:Medic, F:Firebat, G:Ghost
	 */
	public void addUnit(String playerId, char unitType) throws Exception {
		
		switch (unitType){
			case 'M': getPlayer(playerId).addUnit(new Marine());
				break;
			case 'E': getPlayer(playerId).addUnit(new Medic());
				break;
			case 'F': getPlayer(playerId).addUnit(new Firebat());
				break;
			case 'G': getPlayer(playerId).addUnit(new Ghost());
				break;
			default: throw new Exception("MemGameService addUnit unitType is wrong:"+unitType);
		}
	}

	/**
	 * make a group from all units of player  
	 * @param playerId unique id of player
	 */
	public void groupAll(String playerId) {
		getPlayer(playerId).groupAll();
	}

	/**
	 * command to selected group of the Player.
	 *  - throws exception when parameter is wrong
	 * @param playerId player's unique Id.
	 * @param commandType G:go, H:Hold, F:Fire, S:Stimpack
	 * @return String format is "GO!GO!GO!" if 3 units are going
	 */
	public String groupCommand(String playerId, char commandType) throws Exception{
		
		switch (commandType){
			case 'G': return getPlayer(playerId).groupGo();
			
			case 'H': return getPlayer(playerId).groupHold();
				
			case 'F': return getPlayer(playerId).groupFire();
				
			case 'S': return getPlayer(playerId).groupStimPack();
				
			default:throw new Exception("MemGameService groupCommand commandType is wrong:"+commandType);
		}
		
	}
}
