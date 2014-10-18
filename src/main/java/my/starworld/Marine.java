package my.starworld;


/**
 * Simple Marine class
 * initial damage=6. upagre damage= +1
 * @see superclass AttackUnit
 * @author yong.kim
 * @Date 2014.10.12
 */

public class Marine extends AttackUnit {

	protected static final int INITIAL_DAMAGE_LEVEL = 6;
	protected static final int INCREASE_DAMAGE = 1;
	
	public Marine(){
		super();
	}

	protected void initDamage() {
		damage = INITIAL_DAMAGE_LEVEL;
	}

	protected void increaseDamageOneLevel() {
		damage += INCREASE_DAMAGE;
	}

}
