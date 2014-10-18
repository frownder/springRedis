package my;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import my.starworld.Player;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RedisController {

	@Resource
	private IRedisService redisService;
	
	@Resource
	private IMemGameService memGameService;
	
	
	/**
	 * object <-> json converter for transferring Object to client
	 */
	private ObjectMapper mapper = new ObjectMapper( )
										.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.WRAPPER_ARRAY);
	
	/**
	 * returns a player to requesting client as a JSON format.
	 *   which contains all unit information the player has.
	 * @param request servlet request
	 * @param playerId player's unique id
	 * @return MSGURI which returns serialized JSON of Player Object
	 */
	@RequestMapping("/Player/{playerId}")     // base+/starworld/Player/playerId
	public String getPlayerDo(HttpServletRequest request, @PathVariable String playerId){
		 
		Player player = memGameService.getPlayer(playerId);
		try{
			request.setAttribute("message", mapper.writeValueAsString(player) );
		}catch (Exception e){
			request.setAttribute("message", "FAIL:"+e.toString() );
		}
		return "showMessage";  //WEB-INF/view/showMessage.jsp 
	}
	
	
	/**
	 * caching(or saving) Player to Redis DB. (generally player is in Memory of GameService (MemGameService))
	 * 	- this is useful when server has fault and need recovery
	 *  - or redis can be used as a main memory of service with some scalability assistance
	 * @param playerId  player's unique id
	 * @return MsgURI   OK/FAIL Message.
	 */
	@RequestMapping("/cachePlayer/{playerId}")
	public String cachePlayerToRedisDo(HttpServletRequest request, @PathVariable String playerId){
		
		String returnMsg = "OK";
		try{
			//getting player from Memory of GameService
			Player player = memGameService.getPlayer(playerId);
			
			//caching(=saving) to redis
			if(player==null)
				returnMsg = "FAIL:"+" player not exist in Memory of GameService" + "(playerId:"+playerId+")";
			else
				redisService.redisCachePlayer(player);
			
		}catch (Exception e){
			returnMsg = "FAIL:"+e.toString() ;
		}
		
		request.setAttribute("message", returnMsg );
		return "showMessage";  //WEB-INF/view/list.jsp 
	}
	
	
	/**
	 * restoring Player from Redis DB to Memory of GameService (MemGameService)
	 * 
	 * @param playerId  player's unique id
	 * @return MsgURI   OK/FAIL Message.
	 */
	@RequestMapping("/restorePlayer/{playerId}")  // base+/starworld/Player/playerId
	public String restorePlayerFromRedisDo(HttpServletRequest request, @PathVariable String playerId){
		
		
		String returnMsg = "OK";
		try{
			//getting player from redis
			Player player = redisService.redisRestorePlayer(playerId);
			
			//restore to Memory of GameService
			if(player==null)
				returnMsg = "FAIL:"+" player not exist in redis" + "(playerId:"+playerId+")";
			else
				memGameService.restorePlayer(player);
			
		}catch (Exception e){
			returnMsg = "FAIL:"+e.toString();
		}
		request.setAttribute("message", returnMsg );
		
		return "showMessage";   
	}
	

	@RequestMapping("/createPlayer/{playerId}")  // base+/starworld/createPlayer/playerId
	public String createPlayerDo(HttpServletRequest request, @PathVariable String playerId){
		
		request.setAttribute("message", "OK" );	
		try{
			memGameService.createPlayer(playerId);
		}catch (Exception e){
			request.setAttribute("message", "FAIL:"+e.toString() );
		}
		return "showMessage";  //WEB-INF/view/showMessage.jsp 
	}
	
	/**
	 * adds specified unit to the Player.
	 * @param request servletRequest
	 * @param playerId player's unique Id.
	 * @param unitType M:marine, E:medic, G:Ghost, F:Firebat
	 */
	@RequestMapping("/addUnit/{playerId}/{unitType}")
	public String addUnitDo(HttpServletRequest request, @PathVariable String playerId,
			@PathVariable char unitType){
		
		request.setAttribute("message", "OK" );	
		try{
			memGameService.addUnit(playerId, unitType);
		}catch (Exception e){
			request.setAttribute("message", "FAIL:"+e.toString() );
		}
		return "showMessage";  //WEB-INF/view/showMessage.jsp 
	}
	
	/**
	 * make a group from all units of player
	 * @param request servletRequest  
	 * @param playerId unique id of player
	 */
	@RequestMapping("/groupAll/{playerId}")
	public String groupAllDo(HttpServletRequest request, @PathVariable String playerId){
		
		request.setAttribute("message", "OK" );	
		try{
			memGameService.groupAll(playerId);
		}catch (Exception e){
			request.setAttribute("message", "FAIL:"+e.toString() );
		}
		return "showMessage";  //WEB-INF/view/showMessage.jsp 
	}
	
	/**
	 * command to selected group of the Player.
	 * @param request servletrequest
	 * @param playerId unique id of player
	 * @param commandType G:go, H:Hold, F:Fire, S:Stimpack
	 * @return String explaining acts like "GO!GO!" of 2units or "FIRE!FIRE!FIRE!" of 3 units
	 * @return FAIL:cause
	 */
	@RequestMapping("/groupCommand/{playerId}/{commandType}")
	public String groupCommandDo(HttpServletRequest request, @PathVariable String playerId,
			@PathVariable char commandType){
		
		try{
			String ret = memGameService.groupCommand(playerId,commandType);
			request.setAttribute("message", ret);
			
		}catch (Exception e){
			request.setAttribute("message", "FAIL:"+e.toString() );
		}
		return "showMessage";  //WEB-INF/view/showMessage.jsp 
	}
	
	
}
