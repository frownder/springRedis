package my;


import my.starworld.Player;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Class for saving/restoring Player object to Redis
 * Redis is persistent storage while memory is game service storage.
 * In case of long-time RTS like clash of clans, 
 * player object need to be stored onto persistent long-time storage
 * in preparation for memory/server fault.
 * 
 * @author yong.kim
 * @Date 2014.10.13
 */
@Service
public class RedisService implements IRedisService{

	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	/**
	 * object <-> json converter.(serialize/deserialize with json.
	 * json is faster than java serialization.
	 */
	private ObjectMapper mapper = new ObjectMapper( )
										//.configure( DeserializationConfig.Feature, false)
										.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.WRAPPER_ARRAY);
	
	/** 
	 * store a player object to redis db for long-time use
	 * @param player object of player to store to redis DB
	 */
	public void redisCachePlayer(Player player) throws Exception {
		
		String jsonStr = mapper.writeValueAsString(player);
		System.out.println(jsonStr);//test
		
		redisTemplate.opsForValue().set(player.getPlayerId(), jsonStr);
		
	}

	/** 
	 * restore a player object from redis db to memory 
	 * @param player   unique id of player
	 * @return player  player object retrieved from redis db
	 */
	public Player redisRestorePlayer(String playerId) throws Exception {
		
		String jsonStr = redisTemplate.opsForValue().get(playerId);
		
		Player player = mapper.readValue(jsonStr, Player.class);
		System.out.println(mapper.writeValueAsString(player)); //test
		return player;
	}

	

}
