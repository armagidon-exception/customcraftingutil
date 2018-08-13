package pack.update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftUtils implements Listener 
{
	
	private static List<Craft> crafts = new ArrayList<Craft>();
	private static Main main;
	private static List<CraftInterface> cs = new ArrayList<CraftInterface>();
	
	@EventHandler
	public void onCraft(PrepareItemCraftEvent e) {
		CraftingInventory ci = e.getInventory();
		ItemStack[] matrix = ci.getMatrix();
		if(matrix.length < 9) return;
		crafts.forEach(x->{
			checkCraft(x.getResult(), ci, x.convertToIntMap());
		});
		
	}
	
	public CraftUtils(Main main) {
		CraftUtils.main = main;
		Bukkit.getPluginManager().registerEvents(this, main);
	}
	
	public CraftUtils setComponents(CraftInterface... craftsutils){
		for(CraftInterface c : craftsutils){
			cs.add(c);
		}
		return this;
	}
	
	public void addCraft(){
		cs.forEach(x2->{
			ItemStack result = x2.getResult();
			String[] shape = x2.getShape();
			HashMap<Character, ItemStack> ingrs = x2.getIngredientMap();
			
			
			ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(main, UUID.randomUUID().toString()), result);
			recipe.shape(shape);
			//Пробегаемся по карте с знаками и итемами
			ingrs.forEach((x, x1)->{
				recipe.setIngredient(x, x1.getType());
			});
			Bukkit.addRecipe(recipe);
			//Добавляем в лист с рецептами
			crafts.add(new Craft(result, shape, ingrs));
		});
	}
	
	//Проверка крафта
	private void checkCraft(ItemStack result, CraftingInventory ci, HashMap<Integer, ItemStack> ingridients){
		ItemStack[] matrix = ci.getMatrix();
		//Пробежка по слотам
		for(int i =0; i <9; i++){
			if(ingridients.containsKey(i)){//Если в карте ингридиентов есть этот слот - то
				if(matrix[i] == null || !matrix[i].equals(ingridients.get(i))){ //Если слот пуст или он не ровняется тому что есть в карте - то ничего не делаем
					return;
				}
			} else {
				//Иначе проверяем не ровняется ли нулю этот слот
				if(matrix[i] != null) return;
			}
		}
		ci.setResult(result);
	}
}
