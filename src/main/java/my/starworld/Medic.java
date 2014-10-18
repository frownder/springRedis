package my.starworld;

import org.codehaus.jackson.annotate.JsonAutoDetect;


/**
 * Simple Medic class which has only heal() ability.
 * and medic don't have damageLevel.
 * @see superclass BionicUnit
 * @author yong.kim
 * @Date 2014.10.12
 */
@JsonAutoDetect
public class Medic extends BionicUnit {
	 
	public Medic(){
		super();
	}
	
	/**
	 * heal other bionic units
	 */
	public void heal(){
		System.out.println(this.getClass().getName() + ": heal ");
	}
	
}
