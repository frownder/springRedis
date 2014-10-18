package my.starworld;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BionicAllTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Bionic Unit armor test
	 */
	@Test
	public void armorTest() {
		
		BionicUnit bUnit[] = new BionicUnit[4];
		bUnit[0] = new Marine();
		bUnit[1] = new Medic();
		bUnit[2] = new Firebat();
		bUnit[3] = new Ghost();
		
		for(BionicUnit b: bUnit){
			assertSame(1, b.getArmorLevel());
			
			b.increaseArmorLevel();
			assertSame(2, b.getArmorLevel());
			
			for(int i=0; i<100; i++)
				b.increaseArmorLevel();
			assertSame(BionicUnit.MAX_ARMOR_LEVEL, b.getArmorLevel());
		}
	}

	/** getting INITIAL_DAMAGE_LEVEL of AttackUnit
	 */
	private int aUnit_INIT_DAMAGE_LEVEL(AttackUnit a){
		if (a instanceof Marine) return Marine.INITIAL_DAMAGE_LEVEL;
		if (a instanceof Ghost) return Ghost.INITIAL_DAMAGE_LEVEL;
		if (a instanceof Firebat) return Firebat.INITIAL_DAMAGE_LEVEL;
		return 0;
	}
	/** getting INITIAL_DAMAGE_LEVEL of AttackUnit
	 */
	private int aUnit_INCREASE_DAMAGE_VALUE(AttackUnit a){
		if (a instanceof Marine) return Marine.INCREASE_DAMAGE;
		if (a instanceof Ghost) return Ghost.INCREASE_DAMAGE;
		if (a instanceof Firebat) return Firebat.INCREASE_DAMAGE;
		return 0;
	}
	/**
	 * AttackUnit Damage Test
	 * - checking inital Damage Level & IncreaseDamageLevel
	 */
	@Test
	public void damageTest() {
		AttackUnit aUnit[] = new AttackUnit[3];
		aUnit[0] = new Marine();
		aUnit[1] = new Firebat();
		aUnit[2] = new Ghost();
		
		for(AttackUnit a:aUnit){
			//check init level
			assertSame(aUnit_INIT_DAMAGE_LEVEL(a), a.getDamage());
			
			//check increase
			a.increaseDamageLevel();
			assertSame(aUnit_INIT_DAMAGE_LEVEL(a)+aUnit_INCREASE_DAMAGE_VALUE(a), a.getDamage());
			
			//increase too much
			for(int i=0; i<100; i++)
				a.increaseDamageLevel();
			
			assertSame(aUnit_INIT_DAMAGE_LEVEL(a) + (AttackUnit.MAX_DAMAGE_LEVEL-1) * aUnit_INCREASE_DAMAGE_VALUE(a), 
						a.getDamage());
			
		}
		
	}
	
	
}
