/*   1:    */ package com.github.supavitax.itemlorestats;
/*   2:    */ 
/*   3:    */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import java.util.List;
/*   7:    */ import org.bukkit.ChatColor;
/*   8:    */ import org.bukkit.Material;
/*   9:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  10:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  11:    */ import org.bukkit.entity.Player;
/*  12:    */ import org.bukkit.inventory.ItemStack;
/*  13:    */ import org.bukkit.inventory.PlayerInventory;
/*  14:    */ import org.bukkit.inventory.meta.ItemMeta;
/*  15:    */ 
/*  16:    */ public class RepairItems
/*  17:    */ {
/*  18: 17 */   Util_Colours util_Colours = new Util_Colours();
/*  19:    */   private FileConfiguration PlayerDataConfig;
/*  20:    */   
/*  21:    */   public String getResponse(String getKeyFromLanguageFile)
/*  22:    */   {
/*  23:    */     try
/*  24:    */     {
/*  25: 24 */       this.PlayerDataConfig = new YamlConfiguration();
/*  26: 25 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*  27:    */       
/*  28: 27 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*  29:    */     }
/*  30:    */     catch (Exception e)
/*  31:    */     {
/*  32: 30 */       e.printStackTrace();
/*  33: 31 */       System.out.println("*********** Failed to load message from language file! ***********");
/*  34:    */     }
/*  35: 33 */     return "*********** Failed to load message from language file! ***********";
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void repair(Player player, String type, String material)
/*  39:    */   {
/*  40: 38 */     int repairCost = ItemLoreStats.plugin.getConfig().getInt("durabilityAddedOnEachRepair." + type + "." + material);
/*  41: 40 */     if (player.getItemInHand().getItemMeta().hasLore())
/*  42:    */     {
/*  43: 42 */       List<String> splitItemLore = player.getItemInHand().getItemMeta().getLore();
/*  44: 44 */       for (String getItemStat : splitItemLore)
/*  45:    */       {
/*  46: 46 */         String durabilityAmountColour = "";
/*  47: 47 */         String prefixColourOnly = "";
/*  48: 48 */         String durabilityRebuilder = "";
/*  49: 50 */         if (ChatColor.stripColor(getItemStat).startsWith(ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "")))
/*  50:    */         {
/*  51: 52 */           int currentAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[0]).replaceAll("&([0-9a-f])", ""));
/*  52: 53 */           int maxAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[1]).replaceAll("&([0-9a-f])", ""));
/*  53: 54 */           int index = splitItemLore.indexOf(getItemStat);
/*  54: 56 */           if (currentAmount + repairCost > maxAmount) {
/*  55: 57 */             currentAmount = maxAmount;
/*  56:    */           } else {
/*  57: 59 */             currentAmount += repairCost;
/*  58:    */           }
/*  59: 62 */           if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)).contains("&"))
/*  60:    */           {
/*  61: 63 */             if (getItemStat.length() > 4)
/*  62:    */             {
/*  63: 64 */               if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(2, 4)).contains("&")) {
/*  64: 65 */                 prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(2, 4)));
/*  65:    */               } else {
/*  66: 67 */                 prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)));
/*  67:    */               }
/*  68:    */             }
/*  69:    */             else {
/*  70: 70 */               prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)));
/*  71:    */             }
/*  72:    */           }
/*  73:    */           else {
/*  74: 73 */             prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  75:    */           }
/*  76: 76 */           if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  77:    */           {
/*  78: 77 */             if (getItemStat.split("/")[1].trim().length() > 4)
/*  79:    */             {
/*  80: 78 */               if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  81: 79 */                 durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(2, 4);
/*  82:    */               } else {
/*  83: 81 */                 durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2);
/*  84:    */               }
/*  85:    */             }
/*  86:    */             else {
/*  87: 84 */               durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2);
/*  88:    */             }
/*  89:    */           }
/*  90:    */           else {
/*  91: 87 */             durabilityAmountColour = prefixColourOnly;
/*  92:    */           }
/*  93: 90 */           durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "") + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentAmount + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maxAmount;
/*  94:    */           
/*  95: 92 */           splitItemLore.set(index, durabilityRebuilder);
/*  96:    */           
/*  97: 94 */           ItemStack repairedItem = new ItemStack(player.getItemInHand());
/*  98: 95 */           ItemMeta repairedItemMeta = repairedItem.getItemMeta();
/*  99:    */           
/* 100: 97 */           repairedItemMeta.setLore(splitItemLore);
/* 101: 98 */           repairedItem.setItemMeta(repairedItemMeta);
/* 102:    */           
/* 103:100 */           player.setItemInHand(repairedItem);
/* 104:    */         }
/* 105:    */       }
/* 106:    */     }
/* 107:    */   }
/* 108:    */   
/* 109:    */   public boolean isFullDurability(Player player)
/* 110:    */   {
/* 111:108 */     if (player.getItemInHand().getItemMeta().hasLore())
/* 112:    */     {
/* 113:110 */       List<String> splitItemLore = player.getItemInHand().getItemMeta().getLore();
/* 114:112 */       for (String getItemStat : splitItemLore) {
/* 115:114 */         if (ChatColor.stripColor(getItemStat).startsWith(ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "")))
/* 116:    */         {
/* 117:116 */           int currentAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[0]).replaceAll("&([0-9a-f])", ""));
/* 118:117 */           int maxAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[1]).replaceAll("&([0-9a-f])", ""));
/* 119:119 */           if (currentAmount == maxAmount) {
/* 120:120 */             return true;
/* 121:    */           }
/* 122:122 */           return false;
/* 123:    */         }
/* 124:    */       }
/* 125:    */     }
/* 126:127 */     return false;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public void repairAxe(Player player)
/* 130:    */   {
/* 131:131 */     if (player.getItemInHand().getType().equals(Material.WOOD_AXE))
/* 132:    */     {
/* 133:132 */       if (!isFullDurability(player))
/* 134:    */       {
/* 135:133 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 136:    */         {
/* 137:134 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 3))
/* 138:    */           {
/* 139:135 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 140:136 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 3) });
/* 141:137 */             repair(player, "tools", "wood");
/* 142:138 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 143:    */           }
/* 144:    */           else
/* 145:    */           {
/* 146:140 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 147:    */           }
/* 148:    */         }
/* 149:    */         else {
/* 150:143 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 151:    */         }
/* 152:    */       }
/* 153:    */       else {
/* 154:146 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 155:    */       }
/* 156:    */     }
/* 157:148 */     else if (player.getItemInHand().getType().equals(Material.STONE_AXE))
/* 158:    */     {
/* 159:149 */       if (!isFullDurability(player))
/* 160:    */       {
/* 161:150 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 162:    */         {
/* 163:151 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 3))
/* 164:    */           {
/* 165:152 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 166:153 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 3) });
/* 167:154 */             repair(player, "tools", "stone");
/* 168:155 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 169:    */           }
/* 170:    */           else
/* 171:    */           {
/* 172:157 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 173:    */           }
/* 174:    */         }
/* 175:    */         else {
/* 176:160 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 177:    */         }
/* 178:    */       }
/* 179:    */       else {
/* 180:163 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 181:    */       }
/* 182:    */     }
/* 183:165 */     else if (player.getItemInHand().getType().equals(Material.IRON_AXE))
/* 184:    */     {
/* 185:166 */       if (!isFullDurability(player))
/* 186:    */       {
/* 187:167 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 188:    */         {
/* 189:168 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 3))
/* 190:    */           {
/* 191:169 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 192:170 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 3) });
/* 193:171 */             repair(player, "tools", "iron");
/* 194:172 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 195:    */           }
/* 196:    */           else
/* 197:    */           {
/* 198:174 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 199:    */           }
/* 200:    */         }
/* 201:    */         else {
/* 202:177 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 203:    */         }
/* 204:    */       }
/* 205:    */       else {
/* 206:180 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 207:    */       }
/* 208:    */     }
/* 209:182 */     else if (player.getItemInHand().getType().equals(Material.GOLD_AXE))
/* 210:    */     {
/* 211:183 */       if (!isFullDurability(player))
/* 212:    */       {
/* 213:184 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 214:    */         {
/* 215:185 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 3))
/* 216:    */           {
/* 217:186 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 218:187 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 3) });
/* 219:188 */             repair(player, "tools", "gold");
/* 220:189 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 221:    */           }
/* 222:    */           else
/* 223:    */           {
/* 224:191 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 225:    */           }
/* 226:    */         }
/* 227:    */         else {
/* 228:194 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 229:    */         }
/* 230:    */       }
/* 231:    */       else {
/* 232:197 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 233:    */       }
/* 234:    */     }
/* 235:199 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_AXE)) {
/* 236:200 */       if (!isFullDurability(player))
/* 237:    */       {
/* 238:201 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 239:    */         {
/* 240:202 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 3))
/* 241:    */           {
/* 242:203 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 243:204 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 3) });
/* 244:205 */             repair(player, "tools", "diamond");
/* 245:206 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 246:    */           }
/* 247:    */           else
/* 248:    */           {
/* 249:208 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 250:    */           }
/* 251:    */         }
/* 252:    */         else {
/* 253:211 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 254:    */         }
/* 255:    */       }
/* 256:    */       else {
/* 257:214 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 258:    */       }
/* 259:    */     }
/* 260:    */   }
/* 261:    */   
/* 262:    */   public void repairHoe(Player player)
/* 263:    */   {
/* 264:220 */     if (player.getItemInHand().getType().equals(Material.WOOD_HOE))
/* 265:    */     {
/* 266:221 */       if (!isFullDurability(player))
/* 267:    */       {
/* 268:222 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 269:    */         {
/* 270:223 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 2))
/* 271:    */           {
/* 272:224 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 273:225 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 2) });
/* 274:226 */             repair(player, "tools", "wood");
/* 275:227 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 276:    */           }
/* 277:    */           else
/* 278:    */           {
/* 279:229 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 280:    */           }
/* 281:    */         }
/* 282:    */         else {
/* 283:232 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 284:    */         }
/* 285:    */       }
/* 286:    */       else {
/* 287:235 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 288:    */       }
/* 289:    */     }
/* 290:237 */     else if (player.getItemInHand().getType().equals(Material.STONE_HOE))
/* 291:    */     {
/* 292:238 */       if (!isFullDurability(player))
/* 293:    */       {
/* 294:239 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 295:    */         {
/* 296:240 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 2))
/* 297:    */           {
/* 298:241 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 299:242 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 2) });
/* 300:243 */             repair(player, "tools", "stone");
/* 301:244 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 302:    */           }
/* 303:    */           else
/* 304:    */           {
/* 305:246 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 306:    */           }
/* 307:    */         }
/* 308:    */         else {
/* 309:249 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 310:    */         }
/* 311:    */       }
/* 312:    */       else {
/* 313:252 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 314:    */       }
/* 315:    */     }
/* 316:254 */     else if (player.getItemInHand().getType().equals(Material.IRON_HOE))
/* 317:    */     {
/* 318:255 */       if (!isFullDurability(player))
/* 319:    */       {
/* 320:256 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 321:    */         {
/* 322:257 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 2))
/* 323:    */           {
/* 324:258 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 325:259 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 2) });
/* 326:260 */             repair(player, "tools", "iron");
/* 327:261 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 328:    */           }
/* 329:    */           else
/* 330:    */           {
/* 331:263 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 332:    */           }
/* 333:    */         }
/* 334:    */         else {
/* 335:266 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 336:    */         }
/* 337:    */       }
/* 338:    */       else {
/* 339:269 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 340:    */       }
/* 341:    */     }
/* 342:271 */     else if (player.getItemInHand().getType().equals(Material.GOLD_HOE))
/* 343:    */     {
/* 344:272 */       if (!isFullDurability(player))
/* 345:    */       {
/* 346:273 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 347:    */         {
/* 348:274 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 2))
/* 349:    */           {
/* 350:275 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 351:276 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 2) });
/* 352:277 */             repair(player, "tools", "gold");
/* 353:278 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 354:    */           }
/* 355:    */           else
/* 356:    */           {
/* 357:280 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 358:    */           }
/* 359:    */         }
/* 360:    */         else {
/* 361:283 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 362:    */         }
/* 363:    */       }
/* 364:    */       else {
/* 365:286 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 366:    */       }
/* 367:    */     }
/* 368:288 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_HOE)) {
/* 369:289 */       if (!isFullDurability(player))
/* 370:    */       {
/* 371:290 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 372:    */         {
/* 373:291 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 2))
/* 374:    */           {
/* 375:292 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 376:293 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 2) });
/* 377:294 */             repair(player, "tools", "diamond");
/* 378:295 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 379:    */           }
/* 380:    */           else
/* 381:    */           {
/* 382:297 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 383:    */           }
/* 384:    */         }
/* 385:    */         else {
/* 386:300 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 387:    */         }
/* 388:    */       }
/* 389:    */       else {
/* 390:303 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 391:    */       }
/* 392:    */     }
/* 393:    */   }
/* 394:    */   
/* 395:    */   public void repairSpade(Player player)
/* 396:    */   {
/* 397:309 */     if (player.getItemInHand().getType().equals(Material.WOOD_SPADE))
/* 398:    */     {
/* 399:310 */       if (!isFullDurability(player))
/* 400:    */       {
/* 401:311 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 402:    */         {
/* 403:312 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 1))
/* 404:    */           {
/* 405:313 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 406:314 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 1) });
/* 407:315 */             repair(player, "tools", "wood");
/* 408:316 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 409:    */           }
/* 410:    */           else
/* 411:    */           {
/* 412:318 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 413:    */           }
/* 414:    */         }
/* 415:    */         else {
/* 416:321 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 417:    */         }
/* 418:    */       }
/* 419:    */       else {
/* 420:324 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 421:    */       }
/* 422:    */     }
/* 423:326 */     else if (player.getItemInHand().getType().equals(Material.STONE_SPADE))
/* 424:    */     {
/* 425:327 */       if (!isFullDurability(player))
/* 426:    */       {
/* 427:328 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 428:    */         {
/* 429:329 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 1))
/* 430:    */           {
/* 431:330 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 432:331 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 1) });
/* 433:332 */             repair(player, "tools", "stone");
/* 434:333 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 435:    */           }
/* 436:    */           else
/* 437:    */           {
/* 438:335 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 439:    */           }
/* 440:    */         }
/* 441:    */         else {
/* 442:338 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 443:    */         }
/* 444:    */       }
/* 445:    */       else {
/* 446:341 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 447:    */       }
/* 448:    */     }
/* 449:343 */     else if (player.getItemInHand().getType().equals(Material.IRON_SPADE))
/* 450:    */     {
/* 451:344 */       if (!isFullDurability(player))
/* 452:    */       {
/* 453:345 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 454:    */         {
/* 455:346 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 1))
/* 456:    */           {
/* 457:347 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 458:348 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 1) });
/* 459:349 */             repair(player, "tools", "iron");
/* 460:350 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 461:    */           }
/* 462:    */           else
/* 463:    */           {
/* 464:352 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 465:    */           }
/* 466:    */         }
/* 467:    */         else {
/* 468:355 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 469:    */         }
/* 470:    */       }
/* 471:    */       else {
/* 472:358 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 473:    */       }
/* 474:    */     }
/* 475:360 */     else if (player.getItemInHand().getType().equals(Material.GOLD_SPADE))
/* 476:    */     {
/* 477:361 */       if (!isFullDurability(player))
/* 478:    */       {
/* 479:362 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 480:    */         {
/* 481:363 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 1))
/* 482:    */           {
/* 483:364 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 484:365 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 1) });
/* 485:366 */             repair(player, "tools", "gold");
/* 486:367 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 487:    */           }
/* 488:    */           else
/* 489:    */           {
/* 490:369 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 491:    */           }
/* 492:    */         }
/* 493:    */         else {
/* 494:372 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 495:    */         }
/* 496:    */       }
/* 497:    */       else {
/* 498:375 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 499:    */       }
/* 500:    */     }
/* 501:377 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_SPADE)) {
/* 502:378 */       if (!isFullDurability(player))
/* 503:    */       {
/* 504:379 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 505:    */         {
/* 506:380 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 1))
/* 507:    */           {
/* 508:381 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 509:382 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 1) });
/* 510:383 */             repair(player, "tools", "diamond");
/* 511:384 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 512:    */           }
/* 513:    */           else
/* 514:    */           {
/* 515:386 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 516:    */           }
/* 517:    */         }
/* 518:    */         else {
/* 519:389 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 520:    */         }
/* 521:    */       }
/* 522:    */       else {
/* 523:392 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 524:    */       }
/* 525:    */     }
/* 526:    */   }
/* 527:    */   
/* 528:    */   public void repairPickaxe(Player player)
/* 529:    */   {
/* 530:398 */     if (player.getItemInHand().getType().equals(Material.WOOD_PICKAXE))
/* 531:    */     {
/* 532:399 */       if (!isFullDurability(player))
/* 533:    */       {
/* 534:400 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 535:    */         {
/* 536:401 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 3))
/* 537:    */           {
/* 538:402 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 539:403 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 3) });
/* 540:404 */             repair(player, "tools", "wood");
/* 541:405 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 542:    */           }
/* 543:    */           else
/* 544:    */           {
/* 545:407 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 546:    */           }
/* 547:    */         }
/* 548:    */         else {
/* 549:410 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 550:    */         }
/* 551:    */       }
/* 552:    */       else {
/* 553:413 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 554:    */       }
/* 555:    */     }
/* 556:415 */     else if (player.getItemInHand().getType().equals(Material.STONE_PICKAXE))
/* 557:    */     {
/* 558:416 */       if (!isFullDurability(player))
/* 559:    */       {
/* 560:417 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 561:    */         {
/* 562:418 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 3))
/* 563:    */           {
/* 564:419 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 565:420 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 3) });
/* 566:421 */             repair(player, "tools", "stone");
/* 567:422 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 568:    */           }
/* 569:    */           else
/* 570:    */           {
/* 571:424 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 572:    */           }
/* 573:    */         }
/* 574:    */         else {
/* 575:427 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 576:    */         }
/* 577:    */       }
/* 578:    */       else {
/* 579:430 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 580:    */       }
/* 581:    */     }
/* 582:432 */     else if (player.getItemInHand().getType().equals(Material.IRON_PICKAXE))
/* 583:    */     {
/* 584:433 */       if (!isFullDurability(player))
/* 585:    */       {
/* 586:434 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 587:    */         {
/* 588:435 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 3))
/* 589:    */           {
/* 590:436 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 591:437 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 3) });
/* 592:438 */             repair(player, "tools", "iron");
/* 593:439 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 594:    */           }
/* 595:    */           else
/* 596:    */           {
/* 597:441 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 598:    */           }
/* 599:    */         }
/* 600:    */         else {
/* 601:444 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 602:    */         }
/* 603:    */       }
/* 604:    */       else {
/* 605:447 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 606:    */       }
/* 607:    */     }
/* 608:449 */     else if (player.getItemInHand().getType().equals(Material.GOLD_PICKAXE))
/* 609:    */     {
/* 610:450 */       if (!isFullDurability(player))
/* 611:    */       {
/* 612:451 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 613:    */         {
/* 614:452 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 3))
/* 615:    */           {
/* 616:453 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 617:454 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 3) });
/* 618:455 */             repair(player, "tools", "gold");
/* 619:456 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 620:    */           }
/* 621:    */           else
/* 622:    */           {
/* 623:458 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 624:    */           }
/* 625:    */         }
/* 626:    */         else {
/* 627:461 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 628:    */         }
/* 629:    */       }
/* 630:    */       else {
/* 631:464 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 632:    */       }
/* 633:    */     }
/* 634:466 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)) {
/* 635:467 */       if (!isFullDurability(player))
/* 636:    */       {
/* 637:468 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 638:    */         {
/* 639:469 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 3))
/* 640:    */           {
/* 641:470 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 642:471 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 3) });
/* 643:472 */             repair(player, "tools", "diamond");
/* 644:473 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 645:    */           }
/* 646:    */           else
/* 647:    */           {
/* 648:475 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 649:    */           }
/* 650:    */         }
/* 651:    */         else {
/* 652:478 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 653:    */         }
/* 654:    */       }
/* 655:    */       else {
/* 656:481 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 657:    */       }
/* 658:    */     }
/* 659:    */   }
/* 660:    */   
/* 661:    */   public void repairSword(Player player)
/* 662:    */   {
/* 663:487 */     if (player.getItemInHand().getType().equals(Material.WOOD_SWORD))
/* 664:    */     {
/* 665:488 */       if (!isFullDurability(player))
/* 666:    */       {
/* 667:489 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 668:    */         {
/* 669:490 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 2))
/* 670:    */           {
/* 671:491 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 672:492 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 2) });
/* 673:493 */             repair(player, "tools", "wood");
/* 674:494 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 675:    */           }
/* 676:    */           else
/* 677:    */           {
/* 678:496 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 679:    */           }
/* 680:    */         }
/* 681:    */         else {
/* 682:499 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 683:    */         }
/* 684:    */       }
/* 685:    */       else {
/* 686:502 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 687:    */       }
/* 688:    */     }
/* 689:504 */     else if (player.getItemInHand().getType().equals(Material.STONE_SWORD))
/* 690:    */     {
/* 691:505 */       if (!isFullDurability(player))
/* 692:    */       {
/* 693:506 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 694:    */         {
/* 695:507 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 2))
/* 696:    */           {
/* 697:508 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 698:509 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 2) });
/* 699:510 */             repair(player, "tools", "stone");
/* 700:511 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 701:    */           }
/* 702:    */           else
/* 703:    */           {
/* 704:513 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 705:    */           }
/* 706:    */         }
/* 707:    */         else {
/* 708:516 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 709:    */         }
/* 710:    */       }
/* 711:    */       else {
/* 712:519 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 713:    */       }
/* 714:    */     }
/* 715:521 */     else if (player.getItemInHand().getType().equals(Material.IRON_SWORD))
/* 716:    */     {
/* 717:522 */       if (!isFullDurability(player))
/* 718:    */       {
/* 719:523 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 720:    */         {
/* 721:524 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 2))
/* 722:    */           {
/* 723:525 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 724:526 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 2) });
/* 725:527 */             repair(player, "tools", "iron");
/* 726:528 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 727:    */           }
/* 728:    */           else
/* 729:    */           {
/* 730:530 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 731:    */           }
/* 732:    */         }
/* 733:    */         else {
/* 734:533 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 735:    */         }
/* 736:    */       }
/* 737:    */       else {
/* 738:536 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 739:    */       }
/* 740:    */     }
/* 741:538 */     else if (player.getItemInHand().getType().equals(Material.GOLD_SWORD))
/* 742:    */     {
/* 743:539 */       if (!isFullDurability(player))
/* 744:    */       {
/* 745:540 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 746:    */         {
/* 747:541 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 2))
/* 748:    */           {
/* 749:542 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 750:543 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 2) });
/* 751:544 */             repair(player, "tools", "gold");
/* 752:545 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 753:    */           }
/* 754:    */           else
/* 755:    */           {
/* 756:547 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 757:    */           }
/* 758:    */         }
/* 759:    */         else {
/* 760:550 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 761:    */         }
/* 762:    */       }
/* 763:    */       else {
/* 764:553 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 765:    */       }
/* 766:    */     }
/* 767:555 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
/* 768:556 */       if (!isFullDurability(player))
/* 769:    */       {
/* 770:557 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 771:    */         {
/* 772:558 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 2))
/* 773:    */           {
/* 774:559 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 775:560 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 2) });
/* 776:561 */             repair(player, "tools", "diamond");
/* 777:562 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 778:    */           }
/* 779:    */           else
/* 780:    */           {
/* 781:564 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 782:    */           }
/* 783:    */         }
/* 784:    */         else {
/* 785:567 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 786:    */         }
/* 787:    */       }
/* 788:    */       else {
/* 789:570 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 790:    */       }
/* 791:    */     }
/* 792:    */   }
/* 793:    */   
/* 794:    */   public void repairShears(Player player)
/* 795:    */   {
/* 796:576 */     if (player.getItemInHand().getType().equals(Material.SHEARS)) {
/* 797:577 */       if (!isFullDurability(player))
/* 798:    */       {
/* 799:578 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 2))
/* 800:    */         {
/* 801:579 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 2) });
/* 802:580 */           repair(player, "tools", "shears");
/* 803:581 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 804:    */         }
/* 805:    */         else
/* 806:    */         {
/* 807:583 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 808:    */         }
/* 809:    */       }
/* 810:    */       else {
/* 811:586 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 812:    */       }
/* 813:    */     }
/* 814:    */   }
/* 815:    */   
/* 816:    */   public void repairBow(Player player)
/* 817:    */   {
/* 818:592 */     if (player.getItemInHand().getType().equals(Material.BOW)) {
/* 819:593 */       if (!isFullDurability(player))
/* 820:    */       {
/* 821:594 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 3))
/* 822:    */         {
/* 823:595 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.STRING), 3))
/* 824:    */           {
/* 825:596 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 3) });
/* 826:597 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STRING, 3) });
/* 827:598 */             repair(player, "tools", "bow");
/* 828:599 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 829:    */           }
/* 830:    */           else
/* 831:    */           {
/* 832:601 */             player.sendMessage(getResponse("RepairMessages.NotEnoughString") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 833:    */           }
/* 834:    */         }
/* 835:    */         else {
/* 836:604 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 837:    */         }
/* 838:    */       }
/* 839:    */       else {
/* 840:607 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 841:    */       }
/* 842:    */     }
/* 843:    */   }
/* 844:    */   
/* 845:    */   public void repairStick(Player player)
/* 846:    */   {
/* 847:613 */     if (player.getItemInHand().getType().equals(Material.STICK)) {
/* 848:614 */       if (!isFullDurability(player))
/* 849:    */       {
/* 850:615 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 1))
/* 851:    */         {
/* 852:616 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 1) });
/* 853:617 */           repair(player, "tools", "stick");
/* 854:618 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 855:    */         }
/* 856:    */         else
/* 857:    */         {
/* 858:620 */           player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 859:    */         }
/* 860:    */       }
/* 861:    */       else {
/* 862:623 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 863:    */       }
/* 864:    */     }
/* 865:    */   }
/* 866:    */   
/* 867:    */   public void repairFlintAndSteel(Player player)
/* 868:    */   {
/* 869:629 */     if (player.getItemInHand().getType().equals(Material.FLINT_AND_STEEL)) {
/* 870:630 */       if (!isFullDurability(player))
/* 871:    */       {
/* 872:631 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 1))
/* 873:    */         {
/* 874:632 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.FLINT), 1))
/* 875:    */           {
/* 876:633 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.FLINT, 1) });
/* 877:634 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 1) });
/* 878:635 */             repair(player, "tools", "flintAndSteel");
/* 879:636 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 880:    */           }
/* 881:    */           else
/* 882:    */           {
/* 883:638 */             player.sendMessage(getResponse("RepairMessages.NotEnoughFlint") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 884:    */           }
/* 885:    */         }
/* 886:    */         else {
/* 887:641 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 888:    */         }
/* 889:    */       }
/* 890:    */       else {
/* 891:644 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 892:    */       }
/* 893:    */     }
/* 894:    */   }
/* 895:    */   
/* 896:    */   public void repairFishingRod(Player player)
/* 897:    */   {
/* 898:650 */     if (player.getItemInHand().getType().equals(Material.FISHING_ROD)) {
/* 899:651 */       if (!isFullDurability(player))
/* 900:    */       {
/* 901:652 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 3))
/* 902:    */         {
/* 903:653 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.STRING), 2))
/* 904:    */           {
/* 905:654 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 3) });
/* 906:655 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STRING, 2) });
/* 907:656 */             repair(player, "tools", "fishingRod");
/* 908:657 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 909:    */           }
/* 910:    */           else
/* 911:    */           {
/* 912:659 */             player.sendMessage(getResponse("RepairMessages.NotEnoughString") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 913:    */           }
/* 914:    */         }
/* 915:    */         else {
/* 916:662 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 917:    */         }
/* 918:    */       }
/* 919:    */       else {
/* 920:665 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 921:    */       }
/* 922:    */     }
/* 923:    */   }
/* 924:    */   
/* 925:    */   public void repairCarrotStick(Player player)
/* 926:    */   {
/* 927:671 */     if (player.getItemInHand().getType().equals(Material.CARROT_STICK)) {
/* 928:672 */       if (!isFullDurability(player))
/* 929:    */       {
/* 930:673 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.CARROT_ITEM), 1))
/* 931:    */         {
/* 932:674 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.FISHING_ROD), 1))
/* 933:    */           {
/* 934:675 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.CARROT_ITEM, 1) });
/* 935:676 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.FISHING_ROD, 1) });
/* 936:677 */             repair(player, "tools", "carrotStick");
/* 937:678 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 938:    */           }
/* 939:    */           else
/* 940:    */           {
/* 941:680 */             player.sendMessage(getResponse("RepairMessages.NotEnoughFishingRod") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 942:    */           }
/* 943:    */         }
/* 944:    */         else {
/* 945:683 */           player.sendMessage(getResponse("RepairMessages.NotEnoughCarrots") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 946:    */         }
/* 947:    */       }
/* 948:    */       else {
/* 949:686 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 950:    */       }
/* 951:    */     }
/* 952:    */   }
/* 953:    */   
/* 954:    */   public void repairHelmet(Player player)
/* 955:    */   {
/* 956:692 */     if (!isFullDurability(player))
/* 957:    */     {
/* 958:693 */       if (player.getItemInHand().getType().equals(Material.LEATHER_HELMET))
/* 959:    */       {
/* 960:694 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.LEATHER), 5))
/* 961:    */         {
/* 962:695 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LEATHER, 5) });
/* 963:696 */           repair(player, "armour", "leather");
/* 964:697 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 965:    */         }
/* 966:    */         else
/* 967:    */         {
/* 968:699 */           player.sendMessage(getResponse("RepairMessages.NotEnoughLeather") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 969:    */         }
/* 970:    */       }
/* 971:    */       else {
/* 972:702 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 973:    */       }
/* 974:    */     }
/* 975:704 */     else if (player.getItemInHand().getType().equals(Material.IRON_HELMET))
/* 976:    */     {
/* 977:705 */       if (!isFullDurability(player))
/* 978:    */       {
/* 979:706 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 5))
/* 980:    */         {
/* 981:707 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 5) });
/* 982:708 */           repair(player, "armour", "iron");
/* 983:709 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 984:    */         }
/* 985:    */         else
/* 986:    */         {
/* 987:711 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 988:    */         }
/* 989:    */       }
/* 990:    */       else {
/* 991:714 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 992:    */       }
/* 993:    */     }
/* 994:716 */     else if (player.getItemInHand().getType().equals(Material.GOLD_HELMET))
/* 995:    */     {
/* 996:717 */       if (!isFullDurability(player))
/* 997:    */       {
/* 998:718 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 5))
/* 999:    */         {
/* :00:719 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 5) });
/* :01:720 */           repair(player, "armour", "gold");
/* :02:721 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :03:    */         }
/* :04:    */         else
/* :05:    */         {
/* :06:723 */           player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :07:    */         }
/* :08:    */       }
/* :09:    */       else {
/* :10:726 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :11:    */       }
/* :12:    */     }
/* :13:728 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_HELMET)) {
/* :14:729 */       if (!isFullDurability(player))
/* :15:    */       {
/* :16:730 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 5))
/* :17:    */         {
/* :18:731 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 5) });
/* :19:732 */           repair(player, "armour", "diamond");
/* :20:733 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :21:    */         }
/* :22:    */         else
/* :23:    */         {
/* :24:735 */           player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :25:    */         }
/* :26:    */       }
/* :27:    */       else {
/* :28:738 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :29:    */       }
/* :30:    */     }
/* :31:    */   }
/* :32:    */   
/* :33:    */   public void repairChestplate(Player player)
/* :34:    */   {
/* :35:744 */     if (player.getItemInHand().getType().equals(Material.LEATHER_CHESTPLATE))
/* :36:    */     {
/* :37:745 */       if (!isFullDurability(player))
/* :38:    */       {
/* :39:746 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.LEATHER), 8))
/* :40:    */         {
/* :41:747 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LEATHER, 8) });
/* :42:748 */           repair(player, "armour", "leather");
/* :43:749 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :44:    */         }
/* :45:    */         else
/* :46:    */         {
/* :47:751 */           player.sendMessage(getResponse("RepairMessages.NotEnoughLeather") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :48:    */         }
/* :49:    */       }
/* :50:    */       else {
/* :51:754 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :52:    */       }
/* :53:    */     }
/* :54:756 */     else if (player.getItemInHand().getType().equals(Material.IRON_CHESTPLATE))
/* :55:    */     {
/* :56:757 */       if (!isFullDurability(player))
/* :57:    */       {
/* :58:758 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 8))
/* :59:    */         {
/* :60:759 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 8) });
/* :61:760 */           repair(player, "armour", "iron");
/* :62:761 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :63:    */         }
/* :64:    */         else
/* :65:    */         {
/* :66:763 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :67:    */         }
/* :68:    */       }
/* :69:    */       else {
/* :70:766 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :71:    */       }
/* :72:    */     }
/* :73:768 */     else if (player.getItemInHand().getType().equals(Material.GOLD_CHESTPLATE))
/* :74:    */     {
/* :75:769 */       if (!isFullDurability(player))
/* :76:    */       {
/* :77:770 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 8))
/* :78:    */         {
/* :79:771 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 8) });
/* :80:772 */           repair(player, "armour", "gold");
/* :81:773 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :82:    */         }
/* :83:    */         else
/* :84:    */         {
/* :85:775 */           player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :86:    */         }
/* :87:    */       }
/* :88:    */       else {
/* :89:778 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :90:    */       }
/* :91:    */     }
/* :92:780 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_CHESTPLATE)) {
/* :93:781 */       if (!isFullDurability(player))
/* :94:    */       {
/* :95:782 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 8))
/* :96:    */         {
/* :97:783 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 8) });
/* :98:784 */           repair(player, "armour", "diamond");
/* :99:785 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;00:    */         }
/* ;01:    */         else
/* ;02:    */         {
/* ;03:787 */           player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;04:    */         }
/* ;05:    */       }
/* ;06:    */       else {
/* ;07:790 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;08:    */       }
/* ;09:    */     }
/* ;10:    */   }
/* ;11:    */   
/* ;12:    */   public void repairLeggings(Player player)
/* ;13:    */   {
/* ;14:796 */     if (player.getItemInHand().getType().equals(Material.LEATHER_LEGGINGS))
/* ;15:    */     {
/* ;16:797 */       if (!isFullDurability(player))
/* ;17:    */       {
/* ;18:798 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.LEATHER), 7))
/* ;19:    */         {
/* ;20:799 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LEATHER, 7) });
/* ;21:800 */           repair(player, "armour", "leather");
/* ;22:801 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;23:    */         }
/* ;24:    */         else
/* ;25:    */         {
/* ;26:803 */           player.sendMessage(getResponse("RepairMessages.NotEnoughLeather") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;27:    */         }
/* ;28:    */       }
/* ;29:    */       else {
/* ;30:806 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;31:    */       }
/* ;32:    */     }
/* ;33:808 */     else if (player.getItemInHand().getType().equals(Material.IRON_LEGGINGS))
/* ;34:    */     {
/* ;35:809 */       if (!isFullDurability(player))
/* ;36:    */       {
/* ;37:810 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 7))
/* ;38:    */         {
/* ;39:811 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 7) });
/* ;40:812 */           repair(player, "armour", "iron");
/* ;41:813 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;42:    */         }
/* ;43:    */         else
/* ;44:    */         {
/* ;45:815 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;46:    */         }
/* ;47:    */       }
/* ;48:    */       else {
/* ;49:818 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;50:    */       }
/* ;51:    */     }
/* ;52:820 */     else if (player.getItemInHand().getType().equals(Material.GOLD_LEGGINGS))
/* ;53:    */     {
/* ;54:821 */       if (!isFullDurability(player))
/* ;55:    */       {
/* ;56:822 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 7))
/* ;57:    */         {
/* ;58:823 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 7) });
/* ;59:824 */           repair(player, "armour", "gold");
/* ;60:825 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;61:    */         }
/* ;62:    */         else
/* ;63:    */         {
/* ;64:827 */           player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;65:    */         }
/* ;66:    */       }
/* ;67:    */       else {
/* ;68:830 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;69:    */       }
/* ;70:    */     }
/* ;71:832 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_LEGGINGS)) {
/* ;72:833 */       if (!isFullDurability(player))
/* ;73:    */       {
/* ;74:834 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 7))
/* ;75:    */         {
/* ;76:835 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 7) });
/* ;77:836 */           repair(player, "armour", "diamond");
/* ;78:837 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;79:    */         }
/* ;80:    */         else
/* ;81:    */         {
/* ;82:839 */           player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;83:    */         }
/* ;84:    */       }
/* ;85:    */       else {
/* ;86:842 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;87:    */       }
/* ;88:    */     }
/* ;89:    */   }
/* ;90:    */   
/* ;91:    */   public void repairBoots(Player player)
/* ;92:    */   {
/* ;93:848 */     if (player.getItemInHand().getType().equals(Material.LEATHER_BOOTS))
/* ;94:    */     {
/* ;95:849 */       if (!isFullDurability(player))
/* ;96:    */       {
/* ;97:850 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.LEATHER), 4))
/* ;98:    */         {
/* ;99:851 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LEATHER, 4) });
/* <00:852 */           repair(player, "armour", "leather");
/* <01:853 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <02:    */         }
/* <03:    */         else
/* <04:    */         {
/* <05:855 */           player.sendMessage(getResponse("RepairMessages.NotEnoughLeather") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <06:    */         }
/* <07:    */       }
/* <08:    */       else {
/* <09:858 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* <10:    */       }
/* <11:    */     }
/* <12:860 */     else if (player.getItemInHand().getType().equals(Material.IRON_BOOTS))
/* <13:    */     {
/* <14:861 */       if (!isFullDurability(player))
/* <15:    */       {
/* <16:862 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 4))
/* <17:    */         {
/* <18:863 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 4) });
/* <19:864 */           repair(player, "armour", "iron");
/* <20:865 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <21:    */         }
/* <22:    */         else
/* <23:    */         {
/* <24:867 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <25:    */         }
/* <26:    */       }
/* <27:    */       else {
/* <28:870 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* <29:    */       }
/* <30:    */     }
/* <31:872 */     else if (player.getItemInHand().getType().equals(Material.GOLD_BOOTS))
/* <32:    */     {
/* <33:873 */       if (!isFullDurability(player))
/* <34:    */       {
/* <35:874 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4))
/* <36:    */         {
/* <37:875 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 4) });
/* <38:876 */           repair(player, "armour", "gold");
/* <39:877 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <40:    */         }
/* <41:    */         else
/* <42:    */         {
/* <43:879 */           player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <44:    */         }
/* <45:    */       }
/* <46:    */       else {
/* <47:882 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* <48:    */       }
/* <49:    */     }
/* <50:884 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_BOOTS)) {
/* <51:885 */       if (!isFullDurability(player))
/* <52:    */       {
/* <53:886 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 4))
/* <54:    */         {
/* <55:887 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 4) });
/* <56:888 */           repair(player, "armour", "diamond");
/* <57:889 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <58:    */         }
/* <59:    */         else
/* <60:    */         {
/* <61:891 */           player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <62:    */         }
/* <63:    */       }
/* <64:    */       else {
/* <65:894 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* <66:    */       }
/* <67:    */     }
/* <68:    */   }
/* <69:    */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.RepairItems
 * JD-Core Version:    0.7.0.1
 */