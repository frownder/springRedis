package my.starworld;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;


/**
 * HtmlUnit test class 
 * for testing Controller+Service.  With servletEngine running.
 *   => testOrder:  testFunction NAME_ASCENDING..  
 * @author yong.kim
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HtmlUnitTest {

	@Autowired
	private WebApplicationContext context;
	private WebClient webClient;
	
	// base url modifiable.
	private static final String urlBase = "http://localhost:8080/springRedis";
	
	/**
	 * object <-> json converter.(serialize/deserialize with json.
	 * json is faster than java serialization.
	 */
	private ObjectMapper mapper = new ObjectMapper( )
										//.configure( DeserializationConfig.Feature, false)
										.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.WRAPPER_ARRAY);
	
	
	
	@Before
	public void setup() {
	  webClient = new WebClient();
	}
	
	@After
	public void tearDown() throws Exception {
		
		webClient.closeAllWindows();
	}

	/**
	 * test for RedisContoller#createPlayer
	 * @throws Exception of HtmlUnit
	 */
	@Test
	public void a_createPlayerTest() throws Exception{
		
		final HtmlPage page = webClient.getPage(urlBase+"/starworld/createPlayer/player001");
		assertEquals("OK", page.asText());
	}

	/**
	 * test for RedisContoller#addUnit
	 * @throws Exception of HtmlUnit
	 */
	@Test
	public void b_addUnitTest() throws Exception{
		
		//add Marine
		HtmlPage page = webClient.getPage(urlBase+"/starworld/addUnit/player001/M");
		assertEquals("OK", page.asText());
		
		//add Medic
		page = webClient.getPage(urlBase+"/starworld/addUnit/player001/E");
		assertEquals("OK", page.asText());
		
		//add Marine
		page = webClient.getPage(urlBase+"/starworld/addUnit/player001/M");
		assertEquals("OK", page.asText());
		
		//add Firebat
		page = webClient.getPage(urlBase+"/starworld/addUnit/player001/F");
		assertEquals("OK", page.asText());
		
		//add Ghost
		page = webClient.getPage(urlBase+"/starworld/addUnit/player001/G");
		assertEquals("OK", page.asText());
				
	}
	
	/**
	 * test for RedisContoller#groupAll
	 * @throws Exception of HtmlUnit
	 */
	@Test
	public void c_groupAllTest() throws Exception{
		
		final HtmlPage page = webClient.getPage(urlBase+"/starworld/groupAll/player001");
		assertEquals("OK", page.asText());
	}
	
	
	/**
	 * test for RedisContoller#groupCommandDo
	 * - commandType G:go, H:Hold, F:Fire, S:Stimpack
	 * @throws Exception of HtmlUnit
	 */
	@Test
	public void d_gorupCommandTest() throws Exception{
		
		//groupGo
		HtmlPage page = webClient.getPage(urlBase+"/starworld/groupCommand/player001/G");
		StringTokenizer st = new StringTokenizer(page.asText(),"!");
		assertTrue(Util.checkTokenCount(page.asText(), "GO", 5));
		
		//groupHold
		page = webClient.getPage(urlBase+"/starworld/groupCommand/player001/H");
		st = new StringTokenizer(page.asText(),"!");
		assertTrue(Util.checkTokenCount(page.asText(), "HOLD", 5) );
		
		//groupFire  : Medic doesn't have Fire ability
		page = webClient.getPage(urlBase+"/starworld/groupCommand/player001/F");
		st = new StringTokenizer(page.asText(),"!");
		assertTrue(Util.checkTokenCount(page.asText(), "FIRE", 4) );
		
		//groupStimpack : Ghost doesn't have Stimpack ability
		page = webClient.getPage(urlBase+"/starworld/groupCommand/player001/S");
		st = new StringTokenizer(page.asText(),"!");
		assertTrue(Util.checkTokenCount(page.asText(), "STIM", 3) );
		
	}
	
	/**
	 * test for RedisContoller#cachePlayerDo
	 * @throws Exception of HtmlUnit
	 */
	@Test
	public void e_cachePlayerTest() throws Exception{
		
		final HtmlPage page = webClient.getPage(urlBase+"/starworld/createPlayer/playerH");
		assertEquals("OK", page.asText());
	}
	
	/**
	 * test for RedisContoller#restorePlayerDo
	 * @throws Exception of HtmlUnit
	 */
	@Test
	public void f_restorePlayerTest() throws Exception{
		
		final HtmlPage page = webClient.getPage(urlBase+"/starworld/createPlayer/playerH");
		assertEquals("OK", page.asText());
	}
	
	
	

	/**
	 * test for RedisContoller#getPlayerDo
	 * @throws Exception of HtmlUnit
	 */
	@Test
	public void g_getPlayerTest() throws Exception{
		
		final HtmlPage page = webClient.getPage(urlBase+"/starworld/Player/player001");
		String playerJson = page.asText();
		
		
		//test player
		Player p = new Player("player001");
		p.addUnit(new Marine());
		p.addUnit(new Medic());
		p.addUnit(new Marine());
		p.addUnit(new Firebat());
		p.addUnit(new Ghost());
		
		p.groupAll();
		
		System.out.println(mapper.writeValueAsString(p));
		System.out.println(playerJson);
		
		//compare client-side JSON vs test player JSON  
		assertEquals( mapper.writeValueAsString(p), playerJson);
		
	}

	
}
