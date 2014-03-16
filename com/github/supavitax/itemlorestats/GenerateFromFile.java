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
/* 20:20 */   Util_Colours util_Colours = new Util_Colours();
/* 21:   */   private File PlayerDataFile;
/* 22:   */   private FileConfiguration PlayerDataConfig;
/* 23:   */   
/* 24:   */   public String getResponse(String getKeyFromLanguageFile)
/* 25:   */   {
/* 26:   */     try
/* 27:   */     {
/* 28:28 */       this.PlayerDataConfig = new YamlConfiguration();
/* 29:29 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/* 30:   */       
/* 31:31 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/* 32:   */     }
/* 33:   */     catch (Exception e)
/* 34:   */     {
/* 35:34 */       e.printStackTrace();
/* 36:35 */       System.out.println("*********** Failed to load message from language file! ***********");
/* 37:   */     }
/* 38:37 */     return "*********** Failed to load message from language file! ***********";
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void exportWeapon(Player player, String WeaponName)
/* 42:   */   {
/* 43:   */     try
/* 44:   */     {
/* 45:44 */       this.PlayerDataConfig = new YamlConfiguration();
/* 46:45 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + WeaponName + ".yml");
/* 47:   */       
/* 48:47 */       ItemStack itemInHand = player.getItemInHand();
/* 49:49 */       if (itemInHand != null) {
/* 50:50 */         if (itemInHand.getItemMeta().getLore() != null)
/* 51:   */         {
/* 52:52 */           this.PlayerDataConfig.set("Item", itemInHand);
/* 53:   */           
/* 54:54 */           this.PlayerDataConfig.save(this.PlayerDataFile);
/* 55:55 */           player.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully exported " + ChatColor.GOLD + WeaponName + ChatColor.LIGHT_PURPLE + "!");
/* 56:   */         }
/* 57:   */         else
/* 58:   */         {
/* 59:57 */           player.sendMessage(ChatColor.RED + "Failed to export: Item in hand doesn't contain any lore!");
/* 60:   */         }
/* 61:   */       }
/* 62:   */     }
/* 63:   */     catch (Exception e)
/* 64:   */     {
/* 65:61 */       player.sendMessage(ChatColor.RED + "Failed to export: Check console!");
/* 66:62 */       e.printStackTrace();
/* 67:63 */       System.out.println("*********** Item config failed to save! ***********");
/* 68:   */     }
/* 69:   */   }
/* 70:   */   
/* 71:   */   public ItemStack importWeapon(String weaponName, String newWeaponName, String playerName)
/* 72:   */   {
/* 73:   */     try
/* 74:   */     {
/* 75:70 */       this.PlayerDataConfig = new YamlConfiguration();
/* 76:71 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + weaponName + ".yml"));
/* 77:   */       
/* 78:73 */       ItemStack newItemInHand = this.PlayerDataConfig.getItemStack("Item");
/* 79:75 */       if (newWeaponName != "noChange")
/* 80:   */       {
/* 81:76 */         ItemMeta newItemInHandMeta = newItemInHand.getItemMeta();
/* 82:77 */         newItemInHandMeta.setDisplayName(this.util_Colours.replaceTooltipColour(newWeaponName));
/* 83:78 */         newItemInHand.setItemMeta(newItemInHandMeta);
/* 84:   */       }
/* 85:81 */       Bukkit.getServer().getOfflinePlayer(playerName).getPlayer().sendMessage(Bukkit.getServer().getOfflinePlayer(playerName).getPlayer().getName() + ChatColor.LIGHT_PURPLE + " successfully received " + ChatColor.RESET + newItemInHand.getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + ".");
/* 86:   */       
/* 87:83 */       return newItemInHand;
/* 88:   */     }
/* 89:   */     catch (Exception e)
/* 90:   */     {
/* 91:86 */       Bukkit.getServer().getOfflinePlayer(playerName).getPlayer().sendMessage(ChatColor.RED + "Failed to load: Check console!");
/* 92:87 */       e.printStackTrace();
/* 93:88 */       System.out.println("*********** Item config failed to load! ***********");
/* 94:   */     }
/* 95:91 */     return null;
/* 96:   */   }
/* 97:   */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.GenerateFromFile
 * JD-Core Version:    0.7.0.1
 */