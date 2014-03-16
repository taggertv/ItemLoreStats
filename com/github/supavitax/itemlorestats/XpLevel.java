/*   1:    */ package com.github.supavitax.itemlorestats;
/*   2:    */ 
/*   3:    */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import java.util.List;
/*   7:    */ import org.bukkit.Material;
/*   8:    */ import org.bukkit.Server;
/*   9:    */ import org.bukkit.World;
/*  10:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  11:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  12:    */ import org.bukkit.entity.Player;
/*  13:    */ import org.bukkit.inventory.ItemStack;
/*  14:    */ import org.bukkit.inventory.PlayerInventory;
/*  15:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  16:    */ 
/*  17:    */ public class XpLevel
/*  18:    */ {
/*  19:    */   private FileConfiguration PlayerDataConfig;
/*  20: 16 */   Util_Colours util_Colours = new Util_Colours();
/*  21: 17 */   GearStats gearStats = new GearStats();
/*  22:    */   
/*  23:    */   public String getResponse(String getKeyFromLanguageFile)
/*  24:    */   {
/*  25:    */     try
/*  26:    */     {
/*  27: 22 */       this.PlayerDataConfig = new YamlConfiguration();
/*  28: 23 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*  29:    */       
/*  30: 25 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*  31:    */     }
/*  32:    */     catch (Exception e)
/*  33:    */     {
/*  34: 28 */       e.printStackTrace();
/*  35: 29 */       System.out.println("*********** Failed to load message from language file! ***********");
/*  36:    */     }
/*  37: 31 */     return "*********** Failed to load message from language file! ***********";
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void checkXpLevelHead(Player player)
/*  41:    */   {
/*  42: 37 */     final Player playerFinal = player;
/*  43: 38 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/*  44:    */     {
/*  45:    */       public void run()
/*  46:    */       {
/*  47: 40 */         if ((playerFinal.getInventory().getHelmet() != null) && 
/*  48: 41 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/*  49: 42 */           (XpLevel.this.gearStats.getXPLevelRequirementHead(playerFinal) > playerFinal.getLevel()))
/*  50:    */         {
/*  51: 43 */           int i = 36;
/*  52: 45 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/*  53: 46 */             if (item != null) {
/*  54: 47 */               i--;
/*  55:    */             }
/*  56:    */           }
/*  57: 51 */           if (i == 0)
/*  58:    */           {
/*  59: 52 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getHelmet()));
/*  60: 53 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.InventoryFull.Head"));
/*  61: 54 */             playerFinal.getInventory().setHelmet(new ItemStack(Material.AIR));
/*  62:    */           }
/*  63:    */           else
/*  64:    */           {
/*  65: 56 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getHelmet());
/*  66: 57 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.LevelTooLow.Head"));
/*  67: 58 */             playerFinal.getInventory().setHelmet(new ItemStack(Material.AIR));
/*  68:    */           }
/*  69: 61 */           playerFinal.updateInventory();
/*  70:    */         }
/*  71:    */       }
/*  72: 66 */     }, 2L);
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void checkXpLevelChest(Player player)
/*  76:    */   {
/*  77: 71 */     final Player playerFinal = player;
/*  78: 72 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/*  79:    */     {
/*  80:    */       public void run()
/*  81:    */       {
/*  82: 74 */         if ((playerFinal.getInventory().getChestplate() != null) && 
/*  83: 75 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/*  84: 76 */           (XpLevel.this.gearStats.getXPLevelRequirementChest(playerFinal) > playerFinal.getLevel()))
/*  85:    */         {
/*  86: 77 */           int i = 36;
/*  87: 79 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/*  88: 80 */             if (item != null) {
/*  89: 81 */               i--;
/*  90:    */             }
/*  91:    */           }
/*  92: 85 */           if (i == 0)
/*  93:    */           {
/*  94: 86 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getChestplate()));
/*  95: 87 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.InventoryFull.Chest"));
/*  96: 88 */             playerFinal.getInventory().setChestplate(new ItemStack(Material.AIR));
/*  97:    */           }
/*  98:    */           else
/*  99:    */           {
/* 100: 90 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getChestplate());
/* 101: 91 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.LevelTooLow.Chest"));
/* 102: 92 */             playerFinal.getInventory().setChestplate(new ItemStack(Material.AIR));
/* 103:    */           }
/* 104: 95 */           playerFinal.updateInventory();
/* 105:    */         }
/* 106:    */       }
/* 107:100 */     }, 2L);
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void checkXpLevelLegs(Player player)
/* 111:    */   {
/* 112:105 */     final Player playerFinal = player;
/* 113:106 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 114:    */     {
/* 115:    */       public void run()
/* 116:    */       {
/* 117:108 */         if ((playerFinal.getInventory().getLeggings() != null) && 
/* 118:109 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/* 119:110 */           (XpLevel.this.gearStats.getXPLevelRequirementLegs(playerFinal) > playerFinal.getLevel()))
/* 120:    */         {
/* 121:111 */           int i = 36;
/* 122:113 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 123:114 */             if (item != null) {
/* 124:115 */               i--;
/* 125:    */             }
/* 126:    */           }
/* 127:119 */           if (i == 0)
/* 128:    */           {
/* 129:120 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getLeggings()));
/* 130:121 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.InventoryFull.Legs"));
/* 131:122 */             playerFinal.getInventory().setLeggings(new ItemStack(Material.AIR));
/* 132:    */           }
/* 133:    */           else
/* 134:    */           {
/* 135:124 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getLeggings());
/* 136:125 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.LevelTooLow.Legs"));
/* 137:126 */             playerFinal.getInventory().setLeggings(new ItemStack(Material.AIR));
/* 138:    */           }
/* 139:129 */           playerFinal.updateInventory();
/* 140:    */         }
/* 141:    */       }
/* 142:134 */     }, 2L);
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void checkXpLevelBoots(Player player)
/* 146:    */   {
/* 147:139 */     final Player playerFinal = player;
/* 148:140 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 149:    */     {
/* 150:    */       public void run()
/* 151:    */       {
/* 152:142 */         if ((playerFinal.getInventory().getBoots() != null) && 
/* 153:143 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/* 154:144 */           (XpLevel.this.gearStats.getXPLevelRequirementBoots(playerFinal) > playerFinal.getLevel()))
/* 155:    */         {
/* 156:145 */           int i = 36;
/* 157:147 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 158:148 */             if (item != null) {
/* 159:149 */               i--;
/* 160:    */             }
/* 161:    */           }
/* 162:153 */           if (i == 0)
/* 163:    */           {
/* 164:154 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getBoots()));
/* 165:155 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.InventoryFull.Boots"));
/* 166:156 */             playerFinal.getInventory().setBoots(new ItemStack(Material.AIR));
/* 167:    */           }
/* 168:    */           else
/* 169:    */           {
/* 170:158 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getBoots());
/* 171:159 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.LevelTooLow.Boots"));
/* 172:160 */             playerFinal.getInventory().setBoots(new ItemStack(Material.AIR));
/* 173:    */           }
/* 174:163 */           playerFinal.updateInventory();
/* 175:    */         }
/* 176:    */       }
/* 177:168 */     }, 2L);
/* 178:    */   }
/* 179:    */   
/* 180:    */   public void checkXpLevelItemInHand(Player player)
/* 181:    */   {
/* 182:173 */     final Player playerFinal = player;
/* 183:174 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 184:    */     {
/* 185:    */       public void run()
/* 186:    */       {
/* 187:176 */         if ((playerFinal.getItemInHand() != null) && 
/* 188:177 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/* 189:178 */           (XpLevel.this.gearStats.getXPLevelRequirementItemInHand(playerFinal) > playerFinal.getLevel()))
/* 190:    */         {
/* 191:179 */           int i = 36;
/* 192:181 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 193:182 */             if (item != null) {
/* 194:183 */               i--;
/* 195:    */             }
/* 196:    */           }
/* 197:187 */           if (i == 0)
/* 198:    */           {
/* 199:188 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getItemInHand()));
/* 200:189 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.InventoryFull.ItemInHand"));
/* 201:190 */             playerFinal.getInventory().setItemInHand(new ItemStack(Material.AIR));
/* 202:    */           }
/* 203:    */           else
/* 204:    */           {
/* 205:192 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getItemInHand());
/* 206:193 */             playerFinal.sendMessage(XpLevel.this.getResponse("LevelRequirementMessages.LevelTooLow.ItemInHand"));
/* 207:194 */             playerFinal.getInventory().setItemInHand(new ItemStack(Material.AIR));
/* 208:    */           }
/* 209:197 */           playerFinal.updateInventory();
/* 210:    */         }
/* 211:    */       }
/* 212:202 */     }, 2L);
/* 213:    */   }
/* 214:    */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.XpLevel
 * JD-Core Version:    0.7.0.1
 */