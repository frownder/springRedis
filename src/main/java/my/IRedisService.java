package my;

import java.util.*;

import my.starworld.*;

/**
 * Interface for RedisService which store/restore Player Object.
 * 
 * @author yong.kim
 */
public interface IRedisService {
	
	/** store a player's status for long-time use*/
	public void redisCachePlayer(Player player)  throws Exception;
	
	/** restore player from redis to memory*/
	public Player redisRestorePlayer(String playerId) throws Exception;
	
	
}
