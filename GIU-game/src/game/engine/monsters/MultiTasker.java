package game.engine.monsters;
import game.engine.Role;

public class MultiTasker extends Monster{
   
	private int normalSpeed;
	
	public MultiTasker(String name, String description, Role role, int energy){
		super(name, description, role, energy);
		this.normalSpeed = 0;
	}
	
	public int getNormalSpeed(){
		return normalSpeed;
	}
	
	public void setNormalSpeed(int normalSpeed){
		this.normalSpeed = normalSpeed;
	}
}