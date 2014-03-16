/*  1:   */ package com.github.supavitax.itemlorestats;
/*  2:   */ 
/*  3:   */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*  4:   */ import java.io.File;
/*  5:   */ import java.io.PrintStream;
/*  6:   */ import org.bukkit.Bukkit;
/*  7:   */ import org.bukkit.ChatColor;
/*  8:   */ import org.bukkit.OfflinePlayer;
/*  9:   */ import org.bukkit.Server;
/* 10:   */ import org.bukkit.configuration.file.FileConfiguration;
/* 11:   */ import org.bukkit.configuration.file.YamlConfiguration;
/* 12:   */ import org.bukkit.entity.Player;
/* 13:   */ import org.bukkit.event.Listener;
/* 14:   */ import org.bukkit.inventory.ItemStack;
/* 15:   */ import org.bukkit.inventory.meta.ItemMeta;
/* 16:   */ 
/* 17:   */ public class GenerateFromFile
/* 18:   */   implements Listener
/* 19:   */ {
/* 20:17 */   Util_Colours util_Colours = new Util_Colours();
/* 21:   */   private File PlayerDataFile;
/* 22:   */   private FileConfiguration PlayerDataConfig;
/* 23:   */   
/* 24:   */   public String getResponse(String getKeyFromLanguageFile)
/* 25:   */   {
/* 26:   */     try
/* 27:   */     {
/* 28:25 */       this.PlayerDataConfig = new YamlConfiguration();
/* 29:26 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/* 30:   */       
/* 31:28 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/* 32:   */     }
/* 33:   */     catch (Exception e)
/* 34:   */     {
/* 35:31 */       e.printStackTrace();
/* 36:32 */       System.out.println("*********** Failed to load message from language file! ***********");
/* 37:   */     }
/* 38:34 */     return "*********** Failed to load message from language file! ***********";
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void exportWeapon(Player player, String WeaponName)
/* 42:   */   {
/* 43:   */     try
/* 44:   */     {
/* 45:41 */       this.PlayerDataConfig = new YamlConfiguration();
/* 46:42 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + WeaponName + ".yml");
/* 47:   */       
/* 48:44 */       ItemStack itemInHand = player.getItemInHand();
/* 49:46 */       if (itemInHand != null) {
/* 50:47 */         if (itemInHand.getItemMeta().getLore() != null)
/* 51:   */         {
/* 52:49 */           this.PlayerDataConfig.set("Item", itemInHand);
/* 53:   */           
/* 54:   */ 
/* 55:52 */           this.PlayerDataConfig.save(this.PlayerDataFile);
/* 56:53 */           player.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully exported " + ChatColor.GOLD + WeaponName + ChatColor.LIGHT_PURPLE + "!");
/* 57:   */         }
/* 58:   */         else
/* 59:   */         {
/* 60:55 */           player.sendMessage(ChatColor.RED + "Failed to export: Item in hand doesn't contain any lore!");
/* 61:   */         }
/* 62:   */       }
/* 63:   */     }
/* 64:   */     catch (Exception e)
/* 65:   */     {
/* 66:59 */       player.sendMessage(ChatColor.RED + "Failed to export: Check console!");
/* 67:60 */       e.printStackTrace();
/* 68:61 */       System.out.println("*********** Item config failed to save! ***********");
/* 69:   */     }
/* 70:   */   }
/* 71:   */   
/* 72:   */   public ItemStack importWeapon(String weaponName, String newWeaponName, String playerName)
/* 73:   */   {
/* 74:   */     try
/* 75:   */     {
/* 76:68 */       this.PlayerDataConfig = new YamlConfiguration();
/* 77:69 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + weaponName + ".yml"));
/* 78:   */       
/* 79:71 */       ItemStack newItemInHand = this.PlayerDataConfig.getItemStack("Item");
/* 80:73 */       if (newWeaponName != "noChange")
/* 81:   */       {
/* 82:74 */         ItemMeta newItemInHandMeta = newItemInHand.getItemMeta();
/* 83:75 */         newItemInHandMeta.setDisplayName(this.util_Colours.replaceTooltipColour(newWeaponName));
/* 84:76 */         newItemInHand.setItemMeta(newItemInHandMeta);
/* 85:   */       }
/* 86:79 */       Bukkit.getServer().getOfflinePlayer(playerName).getPlayer().sendMessage(Bukkit.getServer().getOfflinePlayer(playerName).getPlayer().getName() + ChatColor.LIGHT_PURPLE + " successfully received " + ChatColor.RESET + newItemInHand.getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + ".");
/* 87:   */       
/* 88:81 */       return newItemInHand;
/* 89:   */     }
/* 90:   */     catch (Exception e)
/* 91:   */     {
/* 92:84 */       Bukkit.getServer().getOfflinePlayer(playerName).getPlayer().sendMessage(ChatColor.RED + "Failed to load: Check console!");
/* 93:85 */       e.printStackTrace();
/* 94:86 */       System.out.println("*********** Item config failed to load! ***********");
/* 95:   */     }
/* 96:89 */     return null;
/* 97:   */   }
/* 98:   */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.GenerateFromFile
 * JD-Core Version:    0.7.0.1
 */