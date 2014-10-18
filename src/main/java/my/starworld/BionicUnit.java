package my.starworld;


import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * Base class of Terran Bionic Unit which defines basic acts of Bionic Units.
 * @see subclass AttackUnit (Marine, Firebat), and Medic
 * @author yong.kim
 * @Date 2014.10.12
 */
@JsonTypeInfo( use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({ @Type(value= Medic.class, name="Medic") })
public abstract class BionicUnit{

	/** level which indicates level of armor. from 1 to MAX_ARMOR_LEVEL*/
	protected int armorLevel;
	
	
	protected static final int MAX_ARMOR_LEVEL=3;
	
	/**
	 * Creator of BionicUnit
	 * amrmorLevel is set to 1. 
	 */
	public BionicUnit(){
		armorLevel=1;
	}
	
	/**
	 * increase armorLevel up to MAX_ARMOR_LEVEL. 
	 */
	public void increaseArmorLevel(){
		if	(armorLevel <	 MAX_ARMOR_LEVEL)
			armorLevel++;
	}

	
	/**
	 * moves unit to some position
	 * @return String "GO!"
	 */
	public String go(){
		System.out.println(this.getClass().getName() + ": go ");
		return "GO!";
	}
	
	/**
	 * stop moving and make it stay.
	 * @return String "HOLD!"
	 */
	public String hold(){
		System.out.println(this.getClass().getName() + ": hold ");
		return "HOLD!";
	}

	
	/*getter & setter*/
	public int getArmorLevel(){
		return armorLevel;
	}
	public void setArmorLevel(int armorLevel) {
		this.armorLevel = armorLevel;
	}

}
