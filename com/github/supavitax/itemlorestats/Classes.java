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
/*  20: 20 */   Util_Colours util_Colours = new Util_Colours();
/*  21: 21 */   GearStats gearStats = new GearStats();
/*  22:    */   
/*  23:    */   public String getResponse(String getKeyFromLanguageFile)
/*  24:    */   {
/*  25:    */     try
/*  26:    */     {
/*  27: 26 */       this.PlayerDataConfig = new YamlConfiguration();
/*  28: 27 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*  29:    */       
/*  30: 29 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*  31:    */     }
/*  32:    */     catch (Exception e)
/*  33:    */     {
/*  34: 32 */       e.printStackTrace();
/*  35: 33 */       System.out.println("*********** Failed to load message from language file! ***********");
/*  36:    */     }
/*  37: 35 */     return "*********** Failed to load message from language file! ***********";
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void checkClassHead(Player player)
/*  41:    */   {
/*  42: 40 */     final Player playerFinal = player;
/*  43: 41 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/*  44:    */     {
/*  45:    */       public void run()
/*  46:    */       {
/*  47: 43 */         if ((playerFinal.getInventory().getHelmet() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (Classes.this.gearStats.getClassHead(playerFinal) != "") && (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassHead(playerFinal))))
/*  48:    */         {
/*  49: 47 */           int i = 36;
/*  50: 49 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/*  51: 50 */             if (item != null) {
/*  52: 51 */               i--;
/*  53:    */             }
/*  54:    */           }
/*  55: 55 */           if (i == 0)
/*  56:    */           {
/*  57: 56 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getHelmet()));
/*  58: 57 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.Head"));
/*  59: 58 */             playerFinal.getInventory().setHelmet(new ItemStack(Material.AIR));
/*  60:    */           }
/*  61:    */           else
/*  62:    */           {
/*  63: 60 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getHelmet());
/*  64: 61 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.Head"));
/*  65: 62 */             playerFinal.getInventory().setHelmet(new ItemStack(Material.AIR));
/*  66:    */           }
/*  67: 65 */           playerFinal.updateInventory();
/*  68:    */         }
/*  69:    */       }
/*  70: 65 */     }, 2L);
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void checkClassChest(Player player)
/*  74:    */   {
/*  75: 74 */     final Player playerFinal = player;
/*  76: 75 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/*  77:    */     {
/*  78:    */       public void run()
/*  79:    */       {
/*  80: 77 */         if ((playerFinal.getInventory().getChestplate() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (Classes.this.gearStats.getClassChest(playerFinal) != "") && (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassChest(playerFinal))))
/*  81:    */         {
/*  82: 81 */           int i = 36;
/*  83: 83 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/*  84: 84 */             if (item != null) {
/*  85: 85 */               i--;
/*  86:    */             }
/*  87:    */           }
/*  88: 89 */           if (i == 0)
/*  89:    */           {
/*  90: 90 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getChestplate()));
/*  91: 91 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.Chest"));
/*  92: 92 */             playerFinal.getInventory().setChestplate(new ItemStack(Material.AIR));
/*  93:    */           }
/*  94:    */           else
/*  95:    */           {
/*  96: 94 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getChestplate());
/*  97: 95 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.Chest"));
/*  98: 96 */             playerFinal.getInventory().setChestplate(new ItemStack(Material.AIR));
/*  99:    */           }
/* 100: 99 */           playerFinal.updateInventory();
/* 101:    */         }
/* 102:    */       }
/* 103: 99 */     }, 2L);
/* 104:    */   }
/* 105:    */   
/* 106:    */   public void checkClassLegs(Player player)
/* 107:    */   {
/* 108:108 */     final Player playerFinal = player;
/* 109:109 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 110:    */     {
/* 111:    */       public void run()
/* 112:    */       {
/* 113:111 */         if ((playerFinal.getInventory().getLeggings() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (Classes.this.gearStats.getClassLegs(playerFinal) != "") && (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassLegs(playerFinal))))
/* 114:    */         {
/* 115:115 */           int i = 36;
/* 116:117 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 117:118 */             if (item != null) {
/* 118:119 */               i--;
/* 119:    */             }
/* 120:    */           }
/* 121:123 */           if (i == 0)
/* 122:    */           {
/* 123:124 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getLeggings()));
/* 124:125 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.Legs"));
/* 125:126 */             playerFinal.getInventory().setLeggings(new ItemStack(Material.AIR));
/* 126:    */           }
/* 127:    */           else
/* 128:    */           {
/* 129:128 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getLeggings());
/* 130:129 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.Legs"));
/* 131:130 */             playerFinal.getInventory().setLeggings(new ItemStack(Material.AIR));
/* 132:    */           }
/* 133:133 */           playerFinal.updateInventory();
/* 134:    */         }
/* 135:    */       }
/* 136:133 */     }, 2L);
/* 137:    */   }
/* 138:    */   
/* 139:    */   public void checkClassBoots(Player player)
/* 140:    */   {
/* 141:142 */     final Player playerFinal = player;
/* 142:143 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 143:    */     {
/* 144:    */       public void run()
/* 145:    */       {
/* 146:145 */         if ((playerFinal.getInventory().getBoots() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (Classes.this.gearStats.getClassBoots(playerFinal) != "") && (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassBoots(playerFinal))))
/* 147:    */         {
/* 148:149 */           int i = 36;
/* 149:151 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 150:152 */             if (item != null) {
/* 151:153 */               i--;
/* 152:    */             }
/* 153:    */           }
/* 154:157 */           if (i == 0)
/* 155:    */           {
/* 156:158 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getBoots()));
/* 157:159 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.Boots"));
/* 158:160 */             playerFinal.getInventory().setBoots(new ItemStack(Material.AIR));
/* 159:    */           }
/* 160:    */           else
/* 161:    */           {
/* 162:162 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getBoots());
/* 163:163 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.Boots"));
/* 164:164 */             playerFinal.getInventory().setBoots(new ItemStack(Material.AIR));
/* 165:    */           }
/* 166:167 */           playerFinal.updateInventory();
/* 167:    */         }
/* 168:    */       }
/* 169:167 */     }, 2L);
/* 170:    */   }
/* 171:    */   
/* 172:    */   public void checkClassItemInHand(Player player)
/* 173:    */   {
/* 174:176 */     final Player playerFinal = player;
/* 175:177 */     ItemLoreStats.plugin.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 176:    */     {
/* 177:    */       public void run()
/* 178:    */       {
/* 179:179 */         if ((playerFinal.getItemInHand() != null) && (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(playerFinal.getWorld().getName())) && (Classes.this.gearStats.getClassItemInHand(playerFinal) != "") && (!playerFinal.hasPermission("ils.use." + Classes.this.gearStats.getClassItemInHand(playerFinal))))
/* 180:    */         {
/* 181:183 */           int i = 36;
/* 182:185 */           for (ItemStack item : playerFinal.getInventory().getContents()) {
/* 183:186 */             if (item != null) {
/* 184:187 */               i--;
/* 185:    */             }
/* 186:    */           }
/* 187:191 */           if (i == 0)
/* 188:    */           {
/* 189:192 */             playerFinal.getWorld().dropItem(playerFinal.getLocation(), new ItemStack(playerFinal.getInventory().getItemInHand()));
/* 190:193 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.InventoryFull.ItemInHand"));
/* 191:194 */             playerFinal.getInventory().setItemInHand(new ItemStack(Material.AIR));
/* 192:    */           }
/* 193:    */           else
/* 194:    */           {
/* 195:196 */             playerFinal.getInventory().setItem(playerFinal.getInventory().firstEmpty(), playerFinal.getInventory().getItemInHand());
/* 196:197 */             playerFinal.sendMessage(Classes.this.getResponse("ClassRequirementMessages.NotRequiredClass.ItemInHand"));
/* 197:198 */             playerFinal.getInventory().setItemInHand(new ItemStack(Material.AIR));
/* 198:    */           }
/* 199:201 */           playerFinal.updateInventory();
/* 200:    */         }
/* 201:    */       }
/* 202:201 */     }, 2L);
/* 203:    */   }
/* 204:    */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.Classes
 * JD-Core Version:    0.7.0.1
 */