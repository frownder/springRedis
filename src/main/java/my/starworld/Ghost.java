package my.starworld;

/**
 * Simple Ghost class which has only cloak() ability.
 * initial damage=10. upagre damage= +1
 * and Ghost don't have stimpack function.
 * @see superclass AttackUnit
 * @author yong.kim
 * @Date 2014.10.12
 */

public class Ghost extends AttackUnit {

	protected static final int INITIAL_DAMAGE_LEVEL = 10;
	protected static final int INCREASE_DAMAGE = 1;
	
	public Ghost(){
		super();
	}
	
	protected void initDamage() {
		damage = INITIAL_DAMAGE_LEVEL;
	}

	protected void increaseDamageOneLevel() {
		damage += INCREASE_DAMAGE;
	}
	
	/**
	 * make Ghost invisible for a while.
	 */
	public void cloaking(){
		System.out.println(this.getClass().getName() + ": cloaking ");
	}

	/**
	 * Ghost do not have stimPack ability. so make it useless here.
	 * @return "" don't have stimaPack
	 */
	public String stimPack(){
		return "";
	}

	
}
