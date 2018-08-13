package pack.update;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface CraftInterface
{
	public ItemStack getResult();
	
	public String[] getShape();
	
	public HashMap<Character, ItemStack> getIngredientMap();
	
	default ItemStack toItemStack(Material mat){
		return new ItemStack(mat);
	}
}
