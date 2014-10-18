package my.starworld;


/**
 * Simple Firebat class
 * initial damage=16. upagre damage= +2
 * @see superclass AttackUnit
 * @author yong.kim
 * @Date 2014.10.12
 */

public class Firebat extends AttackUnit {

	protected static final int INITIAL_DAMAGE_LEVEL = 16;
	protected static final int INCREASE_DAMAGE = 2;
	
	public Firebat(){
		super();
	}

	protected void initDamage() {
		damage = 16;
	}

	protected void increaseDamageOneLevel() {
		damage += 2;
	}

}
