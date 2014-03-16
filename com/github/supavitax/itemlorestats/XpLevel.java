/*   1:    */ package com.github.supavitax.itemlorestats;
/*   2:    */ 
/*   3:    */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import java.util.List;
/*   7:    */ import org.bukkit.Server;
/*   8:    */ import org.bukkit.World;
/*   9:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  10:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  11:    */ import org.bukkit.entity.Player;
/*  12:    */ import org.bukkit.inventory.ItemStack;
/*  13:    */ import org.bukkit.inventory.PlayerInventory;
/*  14:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  15:    */ 
/*  16:    */ public class XpLevel
/*  17:    */ {
/*  18:    */   private FileConfiguration PlayerDataConfig;
/*  19: 20 */   Util_Colours util_Colours = new Util_Colours();
/*  20: 21 */   GearStats gearStats = new GearStats();
/*  21:    */   
/*  22:    */   public String getResponse(String getKeyFromLanguageFile)
/*  23:    */   {
/*  24:    */     try
/*  25:    */     {
/*  26: 26 */       this.PlayerDataConfig = new YamlConfiguration();
/*  27: 27 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*  28:    */       
/*  29: 29 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*  30:    */     }
/*  31:    */     catch (Exception e)
/*  32:    */     {
/*  33: 32 */       e.printStackTrace();
/*  34: 33 */       System.out.println("*********** Failed to load message from language file! ***********");
/*  35:    */     }
/*  36: 35 */     return "*********** Failed to load message from language file! ***********";
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void checkXpLevelHead(Player player)
/*  40:    */   {
/*  41: 40 */     final Player playerFinal = player;
/*  42: 41 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/*  43:    */     {
/*  44:    */       public void run()
/*  45:    */       {
/*  46: 43 */         if ((playerFinal.getInventory().getHelmet() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (XpLevel.this.gearStats.getXPLevelRequirementHead(playerFinal) > playerFinal.getLevel()))
/*  47:    */         {
/*  48: 46 */           int i = 36;
/*  49: 48 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/*  50: 49 */             if (item != null) {
/*  51: 50 */               i--;
/*  52:    */             }
/*  53:    */           }
/*  54: 55 */           playerFinal.updateInventory();
/*  55:    */         }
/*  56:    */       }
/*  57: 55 */     }, 2L);
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void checkXpLevelChest(Player player)
/*  61:    */   {
/*  62: 64 */     final Player playerFinal = player;
/*  63: 65 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/*  64:    */     {
/*  65:    */       public void run()
/*  66:    */       {
/*  67: 67 */         if ((playerFinal.getInventory().getChestplate() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (XpLevel.this.gearStats.getXPLevelRequirementChest(playerFinal) > playerFinal.getLevel()))
/*  68:    */         {
/*  69: 70 */           int i = 36;
/*  70: 72 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/*  71: 73 */             if (item != null) {
/*  72: 74 */               i--;
/*  73:    */             }
/*  74:    */           }
/*  75: 79 */           playerFinal.updateInventory();
/*  76:    */         }
/*  77:    */       }
/*  78: 79 */     }, 2L);
/*  79:    */   }
/*  80:    */   
/*  81:    */   public void checkXpLevelLegs(Player player)
/*  82:    */   {
/*  83: 88 */     final Player playerFinal = player;
/*  84: 89 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/*  85:    */     {
/*  86:    */       public void run()
/*  87:    */       {
/*  88: 91 */         if ((playerFinal.getInventory().getLeggings() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (XpLevel.this.gearStats.getXPLevelRequirementLegs(playerFinal) > playerFinal.getLevel()))
/*  89:    */         {
/*  90: 94 */           int i = 36;
/*  91: 96 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/*  92: 97 */             if (item != null) {
/*  93: 98 */               i--;
/*  94:    */             }
/*  95:    */           }
/*  96:102 */           playerFinal.updateInventory();
/*  97:    */         }
/*  98:    */       }
/*  99:102 */     }, 2L);
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void checkXpLevelBoots(Player player)
/* 103:    */   {
/* 104:111 */     final Player playerFinal = player;
/* 105:112 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 106:    */     {
/* 107:    */       public void run()
/* 108:    */       {
/* 109:114 */         if ((playerFinal.getInventory().getBoots() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (XpLevel.this.gearStats.getXPLevelRequirementBoots(playerFinal) > playerFinal.getLevel()))
/* 110:    */         {
/* 111:117 */           int i = 36;
/* 112:119 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 113:120 */             if (item != null) {
/* 114:121 */               i--;
/* 115:    */             }
/* 116:    */           }
/* 117:126 */           playerFinal.updateInventory();
/* 118:    */         }
/* 119:    */       }
/* 120:126 */     }, 2L);
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void checkXpLevelItemInHand(Player player)
/* 124:    */   {
/* 125:135 */     final Player playerFinal = player;
/* 126:136 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 127:    */     {
/* 128:    */       public void run()
/* 129:    */       {
/* 130:138 */         if ((playerFinal.getItemInHand() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (XpLevel.this.gearStats.getXPLevelRequirementItemInHand(playerFinal) > playerFinal.getLevel()))
/* 131:    */         {
/* 132:141 */           int i = 36;
/* 133:143 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 134:144 */             if (item != null) {
/* 135:145 */               i--;
/* 136:    */             }
/* 137:    */           }
/* 138:150 */           playerFinal.updateInventory();
/* 139:    */         }
/* 140:    */       }
/* 141:150 */     }, 2L);
/* 142:    */   }
/* 143:    */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.XpLevel
 * JD-Core Version:    0.7.0.1
 */