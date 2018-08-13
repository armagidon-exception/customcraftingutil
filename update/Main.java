package pack.update;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements CraftInterface, Listener
{
	@Override
	public void onEnable() {
		new CraftUtils(this).setComponents(this).addCraft();
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public ItemStack getResult() {
		ItemStack i = toItemStack(Material.DIRT);
		ItemMeta me = i.getItemMeta();
		me.setDisplayName("DIRT");
		i.setItemMeta(me);
		return i;
	}

	@EventHandler
	public void onL(PlayerJoinEvent e) {
		ItemStack h = toItemStack(Material.DIAMOND);
		h.setAmount(50);
		ItemMeta m = h.getItemMeta();
		m.setDisplayName(ChatColor.AQUA+"GGG");
		h.setItemMeta(m);
		e.getPlayer().getInventory().addItem(h, new ItemStack(Material.DIAMOND, 50));
	}
	
	@Override
	public String[] getShape() {
		return new String[]{"   ","DDD","   "};
	}

	@Override
	public HashMap<Character, ItemStack> getIngredientMap() {
		HashMap<Character, ItemStack> i = new HashMap<>();
		ItemStack h = toItemStack(Material.DIAMOND);
		ItemMeta m = h.getItemMeta();
		m.setDisplayName("GGG");
		h.setItemMeta(m);
		i.put('D', h);
		return i;
	}
}
