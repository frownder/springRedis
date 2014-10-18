package my.starworld;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Player class test  
 *   => testOrder:  testFunction NAME_ASCENDING..  
 * @author yong.kim
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayerTest {

	private Player player1;
	private int count, medicCount, ghostCount;
	
	@Before
	public void setUp() throws Exception {
		count = medicCount = ghostCount= 0;		
		player1 = new Player("player1");
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * addUnit & removeUnit test
	 * @author yong.kim
	 */
	@Test
	public void a_addRemoveUnittest() {
		player1.addUnit( new Marine() );count++;
		player1.addUnit( new Medic() );count++;medicCount++;
		player1.addUnit( new Firebat() );count++;
		
		assertEquals(player1.allUnit.size(), count); //check if added unit is 3.
		
		player1.addUnit( new Ghost() );count++;ghostCount++;
		Marine marine1= new Marine();
		player1.addUnit( marine1 );count++;
		assertEquals(player1.allUnit.size(), count);
		
		player1.addUnit( marine1 ); //add again unit which is already added -> must be fail
		assertEquals(player1.allUnit.size(), count); //so same
		
		player1.removeUnit(marine1);count--;
		assertEquals(player1.allUnit.size(), count);
	}
	
	/**
	 * group command class.
	 * @author yong.kim
	 */
	@Test
	public void b_grouptest() {
		
		player1.groupAll();
		assertArrayEquals(player1.allUnit.toArray(), player1.selectedGroup.toArray());
		
		//test when unit is #count
		String retStr = player1.groupGo();
		assertTrue( Util.checkTokenCount(retStr, "GO", count));
		
		retStr = player1.groupHold();
		assertTrue( Util.checkTokenCount(retStr, "HOLD", count));
		
		//test when unit is #count. but medic don't have fire ability
		retStr = player1.groupFire();
		assertTrue( Util.checkTokenCount(retStr, "FIRE", count-medicCount));
		
		//test when unit is #count. but medic/ghost don't have stimpack ability
		retStr = player1.groupStimPack();
		assertTrue( Util.checkTokenCount(retStr, "STIM", count-medicCount-ghostCount));
	}

}
