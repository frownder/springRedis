package my.starworld;

import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * Player can have several BionicTerranUnit.
 * manage selected Group for easy controlling as a one unit.
 *  group control includes move, hold and fire/stimpack for AttackUnit
 * @author yong.kim
 * @Date 2014.10.12
 */
@JsonAutoDetect
public class Player{

	/**
	 * player's unique id
	 */
	private String playerId;
	
	/**
	 * contains all the unit of this player
	 */
	protected List<BionicUnit> allUnit;
	

	/**
	 * contains & manages all the unit this player selected at this time.
	 */
	protected List<BionicUnit> selectedGroup;
	
	
	public Player(){
		allUnit = new LinkedList<BionicUnit>();
		selectedGroup = new LinkedList<BionicUnit>();
	}
	/**
	 * creator with playerId
	 * @param playerId unique id of player
	 */
	public Player(String playerId){
		this();
		this.playerId = playerId;
	}
	
	/**
	 * adds player's unit.
	 * @param unit  unit to add
	 */
	public void addUnit(BionicUnit unit){
		if ( !allUnit.contains(unit))
			allUnit.add(unit);
	}
	/**
	 * removes a unit from player
	 * @param unit  unit to remove
	 */
	public void removeUnit(BionicUnit unit){
		allUnit.remove(unit);
	}
	
	
	/**
	 * put all the unit of the player
	 * to the selectedGroup.
	 */
	public void groupAll(){
		for ( BionicUnit unit: allUnit){
			if ( !selectedGroup.contains(unit))
				selectedGroup.add(unit);
		}
	}
	
	public void addToGroup(BionicUnit unit){
		selectedGroup.add(unit);
	}
	public void clearGroup(){
		selectedGroup.clear();
	}
	
	/**
	 * moves selectedGroup as a one unit
	 * @return String "Go!Go!Go!" if 3 units are going.
	 */
	public String groupGo(){
		StringBuffer stb = new StringBuffer();
		for (BionicUnit unit : selectedGroup)
			stb.append(unit.go());
		
		return stb.toString();
	}
	
	/**
	 * stops the moving group
	 * @return String "HOLD!HOLD!HOLD!" if 3 units are holding.
	 */
	public String groupHold(){
		StringBuffer stb = new StringBuffer();
		for (BionicUnit unit : selectedGroup)
			stb.append(unit.hold());
		
		return stb.toString();
	}
	
	/**
	 * make all members of selectedGroup fire()
	 *  - among all members of selectedGroup only AttackUnits fire().
	 *  @return String "FIRE!FIRE!FIRE!" if 3 units are firing.
	 */
	public String groupFire(){
		StringBuffer stb = new StringBuffer();
		for (BionicUnit unit : selectedGroup)
			if (unit instanceof AttackUnit)
				stb.append( ((AttackUnit)unit).fire() );
		
		return stb.toString();
	}
	
	/**
	 * give stimpack ability to selectedGroup
	 * - among all members of group only AttackUnit get stimpack.
	 * @return String "STIM!STIM!STIM!" if 3 units are in stimpack mode.
	 */
	public String groupStimPack(){
		StringBuffer stb = new StringBuffer();
		for (BionicUnit unit : selectedGroup)
			if (unit instanceof AttackUnit)
				stb.append( ((AttackUnit)unit).stimPack() );
		
		return stb.toString();
	}
	
	

	/*getter & setter*/
	public String getPlayerId(){
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public List<BionicUnit> getAllUnit() {
		return allUnit;
	}
	public void setAllUnit(List<BionicUnit> allUnit) {
		this.allUnit = allUnit;
	}
	public List<BionicUnit> getSelectedGroup() {
		return selectedGroup;
	}
	public void setSelectedGroup(List<BionicUnit> selectedGroup) {
		this.selectedGroup = selectedGroup;
	}
	
	
}
