package my.starworld;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;

/**
 * Base class of Terran Bionic Attack Unit which defines basic acts of attackable Bionic Units.
 * to make it simple  attackUnit has  damage, attackRange attribute and fire() and stimPack() abilities.
 * @see subclass Marine, Firebat and Ghost
 * @author yong.kim
 * @Date 2014.10.12
 */
@JsonTypeInfo( use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({ @Type(value= Marine.class, name="Marine"),
					@Type(value= Firebat.class, name="Firebat"),
					@Type(value= Ghost.class, name="Ghost"),})
public abstract class AttackUnit extends BionicUnit {
	
	
	/**
	 * damage will be upgraded(value added) as damagelevel goes up. 
	 * damage and damage-upgrading value differs for each unit.
	 */
	protected int damage;
	
	/**
	 * damagelevel is from 1 to MAX_DAMAGE_LEVEL 
	 */
	protected int currentDamageLevel;
	protected static final int MAX_DAMAGE_LEVEL=3;
	
	/**
	 * Creator of AttackUnit. 
	 * setting attackRange, assuming all attackRange of all AttackUnits are same.
	 */
	public AttackUnit(){
		super();
		
		currentDamageLevel=1;
		initDamage();
	}
	
	
	/**
	 * AttackUnit has it's own damage on creation.
	 */
	protected abstract void initDamage();
	
	
	/**
	 * increase damage level up to MAX_DAMAGE_LEVEL times.
	 */
	public void increaseDamageLevel(){
		if (currentDamageLevel < MAX_DAMAGE_LEVEL){
			increaseDamageOneLevel();
			currentDamageLevel++;
		}
	}
	/**
	 * increase damage level just one STEP- different for each AttackUnit
	 */
	protected abstract void increaseDamageOneLevel();
	
	
	public String fire(){
		System.out.println(this.getClass().getName() + ": fire - with damageLevel:" + getDamage());
		return "FIRE!";
	}
	
	public String stimPack(){
		System.out.println(this.getClass().getName() + ": stimpack ");
		return "STIM!";
	}


	/* getter & setter */
	public int getDamage(){
		return damage;
	}
		
	public int getCurrentDamageLevel() {
		return currentDamageLevel;
	}

	public void setCurrentDamageLevel(int currentDamageLevel) {
		this.currentDamageLevel = currentDamageLevel;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	
}
