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
/*  17:    */ public class Classes
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
/*  40:    */   public void checkClassHead(Player player)
/*  41:    */   {
/*  42: 37 */     final Player playerFinal = player;
/*  43: 38 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/*  44:    */     {
/*  45:    */       public void run()
/*  46:    */       {
/*  47: 40 */         if ((playerFinal.getInventory().getHelmet() != null) && 
/*  48: 41 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/*  49: 42 */           (Classes.this.gearStats.getClassHead(playerFinal) != "") && 
/*  50: 43 */           (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassHead(playerFinal))))
/*  51:    */         {
/*  52: 44 */           int i = 36;
/*  53: 46 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/*  54: 47 */             if (item != null) {
/*  55: 48 */               i--;
/*  56:    */             }
/*  57:    */           }
/*  58: 52 */           if (i == 0)
/*  59:    */           {
/*  60: 53 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getHelmet()));
/*  61: 54 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.Head"));
/*  62: 55 */             playerFinal.getInventory().setHelmet(new ItemStack(Material.AIR));
/*  63:    */           }
/*  64:    */           else
/*  65:    */           {
/*  66: 57 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getHelmet());
/*  67: 58 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.Head"));
/*  68: 59 */             playerFinal.getInventory().setHelmet(new ItemStack(Material.AIR));
/*  69:    */           }
/*  70: 62 */           playerFinal.updateInventory();
/*  71:    */         }
/*  72:    */       }
/*  73: 68 */     }, 2L);
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void checkClassChest(Player player)
/*  77:    */   {
/*  78: 73 */     final Player playerFinal = player;
/*  79: 74 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/*  80:    */     {
/*  81:    */       public void run()
/*  82:    */       {
/*  83: 76 */         if ((playerFinal.getInventory().getChestplate() != null) && 
/*  84: 77 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/*  85: 78 */           (Classes.this.gearStats.getClassChest(playerFinal) != "") && 
/*  86: 79 */           (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassChest(playerFinal))))
/*  87:    */         {
/*  88: 80 */           int i = 36;
/*  89: 82 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/*  90: 83 */             if (item != null) {
/*  91: 84 */               i--;
/*  92:    */             }
/*  93:    */           }
/*  94: 88 */           if (i == 0)
/*  95:    */           {
/*  96: 89 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getChestplate()));
/*  97: 90 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.Chest"));
/*  98: 91 */             playerFinal.getInventory().setChestplate(new ItemStack(Material.AIR));
/*  99:    */           }
/* 100:    */           else
/* 101:    */           {
/* 102: 93 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getChestplate());
/* 103: 94 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.Chest"));
/* 104: 95 */             playerFinal.getInventory().setChestplate(new ItemStack(Material.AIR));
/* 105:    */           }
/* 106: 98 */           playerFinal.updateInventory();
/* 107:    */         }
/* 108:    */       }
/* 109:104 */     }, 2L);
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void checkClassLegs(Player player)
/* 113:    */   {
/* 114:109 */     final Player playerFinal = player;
/* 115:110 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 116:    */     {
/* 117:    */       public void run()
/* 118:    */       {
/* 119:112 */         if ((playerFinal.getInventory().getLeggings() != null) && 
/* 120:113 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/* 121:114 */           (Classes.this.gearStats.getClassLegs(playerFinal) != "") && 
/* 122:115 */           (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassLegs(playerFinal))))
/* 123:    */         {
/* 124:116 */           int i = 36;
/* 125:118 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 126:119 */             if (item != null) {
/* 127:120 */               i--;
/* 128:    */             }
/* 129:    */           }
/* 130:124 */           if (i == 0)
/* 131:    */           {
/* 132:125 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getLeggings()));
/* 133:126 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.Legs"));
/* 134:127 */             playerFinal.getInventory().setLeggings(new ItemStack(Material.AIR));
/* 135:    */           }
/* 136:    */           else
/* 137:    */           {
/* 138:129 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getLeggings());
/* 139:130 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.Legs"));
/* 140:131 */             playerFinal.getInventory().setLeggings(new ItemStack(Material.AIR));
/* 141:    */           }
/* 142:134 */           playerFinal.updateInventory();
/* 143:    */         }
/* 144:    */       }
/* 145:140 */     }, 2L);
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void checkClassBoots(Player player)
/* 149:    */   {
/* 150:145 */     final Player playerFinal = player;
/* 151:146 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 152:    */     {
/* 153:    */       public void run()
/* 154:    */       {
/* 155:148 */         if ((playerFinal.getInventory().getBoots() != null) && 
/* 156:149 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/* 157:150 */           (Classes.this.gearStats.getClassBoots(playerFinal) != "") && 
/* 158:151 */           (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassBoots(playerFinal))))
/* 159:    */         {
/* 160:152 */           int i = 36;
/* 161:154 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 162:155 */             if (item != null) {
/* 163:156 */               i--;
/* 164:    */             }
/* 165:    */           }
/* 166:160 */           if (i == 0)
/* 167:    */           {
/* 168:161 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getBoots()));
/* 169:162 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.Boots"));
/* 170:163 */             playerFinal.getInventory().setBoots(new ItemStack(Material.AIR));
/* 171:    */           }
/* 172:    */           else
/* 173:    */           {
/* 174:165 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getBoots());
/* 175:166 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.Boots"));
/* 176:167 */             playerFinal.getInventory().setBoots(new ItemStack(Material.AIR));
/* 177:    */           }
/* 178:170 */           playerFinal.updateInventory();
/* 179:    */         }
/* 180:    */       }
/* 181:176 */     }, 2L);
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void checkClassItemInHand(Player player)
/* 185:    */   {
/* 186:181 */     final Player playerFinal = player;
/* 187:182 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 188:    */     {
/* 189:    */       public void run()
/* 190:    */       {
/* 191:184 */         if ((playerFinal.getItemInHand() != null) && 
/* 192:185 */           (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && 
/* 193:186 */           (Classes.this.gearStats.getClassItemInHand(playerFinal) != "") && 
/* 194:187 */           (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassItemInHand(playerFinal))))
/* 195:    */         {
/* 196:188 */           int i = 36;
/* 197:190 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 198:191 */             if (item != null) {
/* 199:192 */               i--;
/* 200:    */             }
/* 201:    */           }
/* 202:196 */           if (i == 0)
/* 203:    */           {
/* 204:197 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getItemInHand()));
/* 205:198 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.ItemInHand"));
/* 206:199 */             playerFinal.getInventory().setItemInHand(new ItemStack(Material.AIR));
/* 207:    */           }
/* 208:    */           else
/* 209:    */           {
/* 210:201 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getItemInHand());
/* 211:202 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.ItemInHand"));
/* 212:203 */             playerFinal.getInventory().setItemInHand(new ItemStack(Material.AIR));
/* 213:    */           }
/* 214:206 */           playerFinal.updateInventory();
/* 215:    */         }
/* 216:    */       }
/* 217:212 */     }, 2L);
/* 218:    */   }
/* 219:    */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.Classes
 * JD-Core Version:    0.7.0.1
 */