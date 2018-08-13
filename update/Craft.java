package pack.update;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Craft 
{
	
	private HashMap<Character, ItemStack> ingrs = new HashMap<>();
	private ItemStack result;
	private String[] shape;
	
	
	public Craft(ItemStack result, String[] shape, HashMap<Character, ItemStack> ingrs) {
		this.setResult(result);
		this.setShape(shape);
		this.setIngrs(ingrs);
	}


	public HashMap<Integer, ItemStack> convertToIntMap(){
		HashMap<Integer, ItemStack> ingridients = new HashMap<Integer, ItemStack>();
		String sh = shapeString();
		for(int i = 0; ingridients.size() < sh.length(); i++){
			if(sh.charAt(i) == ' ') ingridients.put(i, new ItemStack(Material.AIR));
			ingridients.put(i, ingrs.get(sh.charAt(i)));
		}
		return ingridients;
	}
	
	public HashMap<Character, ItemStack> getIngrs() {
		return ingrs;
	}

	public void setIngrs(HashMap<Character, ItemStack> ingrs) {
		this.ingrs = ingrs;
	}
	
	public String[] getShape() {
		return shape;
	}


	public void setShape(String[] shape) {
		this.shape = shape;
	}


	public ItemStack getResult() {
		return result;
	}
	
	private String shapeString(){
		return shape[0]+shape[1]+shape[2];
	}


	public void setResult(ItemStack result) {
		this.result = result;
	}
}
