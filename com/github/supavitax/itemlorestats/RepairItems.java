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
/*  18: 18 */   Util_Colours util_Colours = new Util_Colours();
/*  19:    */   private FileConfiguration PlayerDataConfig;
/*  20:    */   
/*  21:    */   public String getResponse(String getKeyFromLanguageFile)
/*  22:    */   {
/*  23:    */     try
/*  24:    */     {
/*  25: 25 */       this.PlayerDataConfig = new YamlConfiguration();
/*  26: 26 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*  27:    */       
/*  28: 28 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*  29:    */     }
/*  30:    */     catch (Exception e)
/*  31:    */     {
/*  32: 31 */       e.printStackTrace();
/*  33: 32 */       System.out.println("*********** Failed to load message from language file! ***********");
/*  34:    */     }
/*  35: 34 */     return "*********** Failed to load message from language file! ***********";
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void repair(Player player, String type, String material)
/*  39:    */   {
/*  40: 39 */     int repairCost = ItemLoreStats.plugin.getConfig().getInt("durabilityAddedOnEachRepair." + type + "." + material);
/*  41:    */     List<String> splitItemLore;
/*  42: 41 */     if (player.getItemInHand().getItemMeta().hasLore())
/*  43:    */     {
/*  44: 43 */       splitItemLore = player.getItemInHand().getItemMeta().getLore();
/*  45: 45 */       for (String getItemStat : splitItemLore)
/*  46:    */       {
/*  47: 47 */         String durabilityAmountColour = "";
/*  48: 48 */         String prefixColourOnly = "";
/*  49: 49 */         String durabilityRebuilder = "";
/*  50: 51 */         if (ChatColor.stripColor(getItemStat).startsWith(ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "")))
/*  51:    */         {
/*  52: 53 */           int currentAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[0]).replaceAll("&([0-9a-f])", ""));
/*  53: 54 */           int maxAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[1]).replaceAll("&([0-9a-f])", ""));
/*  54: 55 */           int index = splitItemLore.indexOf(getItemStat);
/*  55: 57 */           if (currentAmount + repairCost > maxAmount) {
/*  56: 58 */             currentAmount = maxAmount;
/*  57:    */           } else {
/*  58: 60 */             currentAmount += repairCost;
/*  59:    */           }
/*  60: 63 */           if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)).contains("&"))
/*  61:    */           {
/*  62: 64 */             if (getItemStat.length() > 4)
/*  63:    */             {
/*  64: 65 */               if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(2, 4)).contains("&")) {
/*  65: 66 */                 prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(2, 4)));
/*  66:    */               } else {
/*  67: 68 */                 prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)));
/*  68:    */               }
/*  69:    */             }
/*  70:    */             else {
/*  71: 71 */               prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)));
/*  72:    */             }
/*  73:    */           }
/*  74:    */           else {
/*  75: 74 */             prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  76:    */           }
/*  77: 77 */           if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  78:    */           {
/*  79: 78 */             if (getItemStat.split("/")[1].trim().length() > 4)
/*  80:    */             {
/*  81: 79 */               if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  82: 80 */                 durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(2, 4);
/*  83:    */               } else {
/*  84: 82 */                 durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2);
/*  85:    */               }
/*  86:    */             }
/*  87:    */             else {
/*  88: 85 */               durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2);
/*  89:    */             }
/*  90:    */           }
/*  91:    */           else {
/*  92: 88 */             durabilityAmountColour = prefixColourOnly;
/*  93:    */           }
/*  94: 91 */           durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "") + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentAmount + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maxAmount;
/*  95:    */           
/*  96: 93 */           splitItemLore.set(index, durabilityRebuilder);
/*  97:    */           
/*  98: 95 */           ItemStack repairedItem = new ItemStack(player.getItemInHand());
/*  99: 96 */           ItemMeta repairedItemMeta = repairedItem.getItemMeta();
/* 100:    */           
/* 101: 98 */           repairedItemMeta.setLore(splitItemLore);
/* 102: 99 */           repairedItem.setItemMeta(repairedItemMeta);
/* 103:    */           
/* 104:101 */           player.setItemInHand(repairedItem);
/* 105:    */         }
/* 106:    */       }
/* 107:    */     }
/* 108:    */   }
/* 109:    */   
/* 110:    */   public boolean isFullDurability(Player player)
/* 111:    */   {
/* 112:109 */     if (player.getItemInHand().getItemMeta().hasLore())
/* 113:    */     {
/* 114:111 */       List<String> splitItemLore = player.getItemInHand().getItemMeta().getLore();
/* 115:113 */       for (String getItemStat : splitItemLore) {
/* 116:115 */         if (ChatColor.stripColor(getItemStat).startsWith(ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "")))
/* 117:    */         {
/* 118:117 */           int currentAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[0]).replaceAll("&([0-9a-f])", ""));
/* 119:118 */           int maxAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[1]).replaceAll("&([0-9a-f])", ""));
/* 120:120 */           if (currentAmount == maxAmount) {
/* 121:121 */             return true;
/* 122:    */           }
/* 123:123 */           return false;
/* 124:    */         }
/* 125:    */       }
/* 126:    */     }
/* 127:128 */     return false;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public void repairAxe(Player player)
/* 131:    */   {
/* 132:132 */     if (player.getItemInHand().getType().equals(Material.WOOD_AXE))
/* 133:    */     {
/* 134:133 */       if (!isFullDurability(player))
/* 135:    */       {
/* 136:134 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 137:    */         {
/* 138:135 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 3))
/* 139:    */           {
/* 140:136 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 141:137 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 3) });
/* 142:138 */             repair(player, "tools", "wood");
/* 143:139 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 144:    */           }
/* 145:    */           else
/* 146:    */           {
/* 147:141 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 148:    */           }
/* 149:    */         }
/* 150:    */         else {
/* 151:144 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 152:    */         }
/* 153:    */       }
/* 154:    */       else {
/* 155:147 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 156:    */       }
/* 157:    */     }
/* 158:149 */     else if (player.getItemInHand().getType().equals(Material.STONE_AXE))
/* 159:    */     {
/* 160:150 */       if (!isFullDurability(player))
/* 161:    */       {
/* 162:151 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 163:    */         {
/* 164:152 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 3))
/* 165:    */           {
/* 166:153 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 167:154 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 3) });
/* 168:155 */             repair(player, "tools", "stone");
/* 169:156 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 170:    */           }
/* 171:    */           else
/* 172:    */           {
/* 173:158 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 174:    */           }
/* 175:    */         }
/* 176:    */         else {
/* 177:161 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 178:    */         }
/* 179:    */       }
/* 180:    */       else {
/* 181:164 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 182:    */       }
/* 183:    */     }
/* 184:166 */     else if (player.getItemInHand().getType().equals(Material.IRON_AXE))
/* 185:    */     {
/* 186:167 */       if (!isFullDurability(player))
/* 187:    */       {
/* 188:168 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 189:    */         {
/* 190:169 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 3))
/* 191:    */           {
/* 192:170 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 193:171 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 3) });
/* 194:172 */             repair(player, "tools", "iron");
/* 195:173 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 196:    */           }
/* 197:    */           else
/* 198:    */           {
/* 199:175 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 200:    */           }
/* 201:    */         }
/* 202:    */         else {
/* 203:178 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 204:    */         }
/* 205:    */       }
/* 206:    */       else {
/* 207:181 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 208:    */       }
/* 209:    */     }
/* 210:183 */     else if (player.getItemInHand().getType().equals(Material.GOLD_AXE))
/* 211:    */     {
/* 212:184 */       if (!isFullDurability(player))
/* 213:    */       {
/* 214:185 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 215:    */         {
/* 216:186 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 3))
/* 217:    */           {
/* 218:187 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 219:188 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 3) });
/* 220:189 */             repair(player, "tools", "gold");
/* 221:190 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 222:    */           }
/* 223:    */           else
/* 224:    */           {
/* 225:192 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 226:    */           }
/* 227:    */         }
/* 228:    */         else {
/* 229:195 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 230:    */         }
/* 231:    */       }
/* 232:    */       else {
/* 233:198 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 234:    */       }
/* 235:    */     }
/* 236:200 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_AXE)) {
/* 237:201 */       if (!isFullDurability(player))
/* 238:    */       {
/* 239:202 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 240:    */         {
/* 241:203 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 3))
/* 242:    */           {
/* 243:204 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 244:205 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 3) });
/* 245:206 */             repair(player, "tools", "diamond");
/* 246:207 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 247:    */           }
/* 248:    */           else
/* 249:    */           {
/* 250:209 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 251:    */           }
/* 252:    */         }
/* 253:    */         else {
/* 254:212 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 255:    */         }
/* 256:    */       }
/* 257:    */       else {
/* 258:215 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 259:    */       }
/* 260:    */     }
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void repairHoe(Player player)
/* 264:    */   {
/* 265:220 */     if (player.getItemInHand().getType().equals(Material.WOOD_HOE))
/* 266:    */     {
/* 267:221 */       if (!isFullDurability(player))
/* 268:    */       {
/* 269:222 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 270:    */         {
/* 271:223 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 2))
/* 272:    */           {
/* 273:224 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 274:225 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 2) });
/* 275:226 */             repair(player, "tools", "wood");
/* 276:227 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 277:    */           }
/* 278:    */           else
/* 279:    */           {
/* 280:229 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 281:    */           }
/* 282:    */         }
/* 283:    */         else {
/* 284:232 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 285:    */         }
/* 286:    */       }
/* 287:    */       else {
/* 288:235 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 289:    */       }
/* 290:    */     }
/* 291:237 */     else if (player.getItemInHand().getType().equals(Material.STONE_HOE))
/* 292:    */     {
/* 293:238 */       if (!isFullDurability(player))
/* 294:    */       {
/* 295:239 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 296:    */         {
/* 297:240 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 2))
/* 298:    */           {
/* 299:241 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 300:242 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 2) });
/* 301:243 */             repair(player, "tools", "stone");
/* 302:244 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 303:    */           }
/* 304:    */           else
/* 305:    */           {
/* 306:246 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 307:    */           }
/* 308:    */         }
/* 309:    */         else {
/* 310:249 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 311:    */         }
/* 312:    */       }
/* 313:    */       else {
/* 314:252 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 315:    */       }
/* 316:    */     }
/* 317:254 */     else if (player.getItemInHand().getType().equals(Material.IRON_HOE))
/* 318:    */     {
/* 319:255 */       if (!isFullDurability(player))
/* 320:    */       {
/* 321:256 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 322:    */         {
/* 323:257 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 2))
/* 324:    */           {
/* 325:258 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 326:259 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 2) });
/* 327:260 */             repair(player, "tools", "iron");
/* 328:261 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 329:    */           }
/* 330:    */           else
/* 331:    */           {
/* 332:263 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 333:    */           }
/* 334:    */         }
/* 335:    */         else {
/* 336:266 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 337:    */         }
/* 338:    */       }
/* 339:    */       else {
/* 340:269 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 341:    */       }
/* 342:    */     }
/* 343:271 */     else if (player.getItemInHand().getType().equals(Material.GOLD_HOE))
/* 344:    */     {
/* 345:272 */       if (!isFullDurability(player))
/* 346:    */       {
/* 347:273 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 348:    */         {
/* 349:274 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 2))
/* 350:    */           {
/* 351:275 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 352:276 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 2) });
/* 353:277 */             repair(player, "tools", "gold");
/* 354:278 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 355:    */           }
/* 356:    */           else
/* 357:    */           {
/* 358:280 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 359:    */           }
/* 360:    */         }
/* 361:    */         else {
/* 362:283 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 363:    */         }
/* 364:    */       }
/* 365:    */       else {
/* 366:286 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 367:    */       }
/* 368:    */     }
/* 369:288 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_HOE)) {
/* 370:289 */       if (!isFullDurability(player))
/* 371:    */       {
/* 372:290 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 373:    */         {
/* 374:291 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 2))
/* 375:    */           {
/* 376:292 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 377:293 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 2) });
/* 378:294 */             repair(player, "tools", "diamond");
/* 379:295 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 380:    */           }
/* 381:    */           else
/* 382:    */           {
/* 383:297 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 384:    */           }
/* 385:    */         }
/* 386:    */         else {
/* 387:300 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 388:    */         }
/* 389:    */       }
/* 390:    */       else {
/* 391:303 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 392:    */       }
/* 393:    */     }
/* 394:    */   }
/* 395:    */   
/* 396:    */   public void repairSpade(Player player)
/* 397:    */   {
/* 398:308 */     if (player.getItemInHand().getType().equals(Material.WOOD_SPADE))
/* 399:    */     {
/* 400:309 */       if (!isFullDurability(player))
/* 401:    */       {
/* 402:310 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 403:    */         {
/* 404:311 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 1))
/* 405:    */           {
/* 406:312 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 407:313 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 1) });
/* 408:314 */             repair(player, "tools", "wood");
/* 409:315 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 410:    */           }
/* 411:    */           else
/* 412:    */           {
/* 413:317 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 414:    */           }
/* 415:    */         }
/* 416:    */         else {
/* 417:320 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 418:    */         }
/* 419:    */       }
/* 420:    */       else {
/* 421:323 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 422:    */       }
/* 423:    */     }
/* 424:325 */     else if (player.getItemInHand().getType().equals(Material.STONE_SPADE))
/* 425:    */     {
/* 426:326 */       if (!isFullDurability(player))
/* 427:    */       {
/* 428:327 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 429:    */         {
/* 430:328 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 1))
/* 431:    */           {
/* 432:329 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 433:330 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 1) });
/* 434:331 */             repair(player, "tools", "stone");
/* 435:332 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 436:    */           }
/* 437:    */           else
/* 438:    */           {
/* 439:334 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 440:    */           }
/* 441:    */         }
/* 442:    */         else {
/* 443:337 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 444:    */         }
/* 445:    */       }
/* 446:    */       else {
/* 447:340 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 448:    */       }
/* 449:    */     }
/* 450:342 */     else if (player.getItemInHand().getType().equals(Material.IRON_SPADE))
/* 451:    */     {
/* 452:343 */       if (!isFullDurability(player))
/* 453:    */       {
/* 454:344 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 455:    */         {
/* 456:345 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 1))
/* 457:    */           {
/* 458:346 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 459:347 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 1) });
/* 460:348 */             repair(player, "tools", "iron");
/* 461:349 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 462:    */           }
/* 463:    */           else
/* 464:    */           {
/* 465:351 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 466:    */           }
/* 467:    */         }
/* 468:    */         else {
/* 469:354 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 470:    */         }
/* 471:    */       }
/* 472:    */       else {
/* 473:357 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 474:    */       }
/* 475:    */     }
/* 476:359 */     else if (player.getItemInHand().getType().equals(Material.GOLD_SPADE))
/* 477:    */     {
/* 478:360 */       if (!isFullDurability(player))
/* 479:    */       {
/* 480:361 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 481:    */         {
/* 482:362 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 1))
/* 483:    */           {
/* 484:363 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 485:364 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 1) });
/* 486:365 */             repair(player, "tools", "gold");
/* 487:366 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 488:    */           }
/* 489:    */           else
/* 490:    */           {
/* 491:368 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 492:    */           }
/* 493:    */         }
/* 494:    */         else {
/* 495:371 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 496:    */         }
/* 497:    */       }
/* 498:    */       else {
/* 499:374 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 500:    */       }
/* 501:    */     }
/* 502:376 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_SPADE)) {
/* 503:377 */       if (!isFullDurability(player))
/* 504:    */       {
/* 505:378 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 506:    */         {
/* 507:379 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 1))
/* 508:    */           {
/* 509:380 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 510:381 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 1) });
/* 511:382 */             repair(player, "tools", "diamond");
/* 512:383 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 513:    */           }
/* 514:    */           else
/* 515:    */           {
/* 516:385 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 517:    */           }
/* 518:    */         }
/* 519:    */         else {
/* 520:388 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 521:    */         }
/* 522:    */       }
/* 523:    */       else {
/* 524:391 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 525:    */       }
/* 526:    */     }
/* 527:    */   }
/* 528:    */   
/* 529:    */   public void repairPickaxe(Player player)
/* 530:    */   {
/* 531:396 */     if (player.getItemInHand().getType().equals(Material.WOOD_PICKAXE))
/* 532:    */     {
/* 533:397 */       if (!isFullDurability(player))
/* 534:    */       {
/* 535:398 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 536:    */         {
/* 537:399 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 3))
/* 538:    */           {
/* 539:400 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 540:401 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 3) });
/* 541:402 */             repair(player, "tools", "wood");
/* 542:403 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 543:    */           }
/* 544:    */           else
/* 545:    */           {
/* 546:405 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 547:    */           }
/* 548:    */         }
/* 549:    */         else {
/* 550:408 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 551:    */         }
/* 552:    */       }
/* 553:    */       else {
/* 554:411 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 555:    */       }
/* 556:    */     }
/* 557:413 */     else if (player.getItemInHand().getType().equals(Material.STONE_PICKAXE))
/* 558:    */     {
/* 559:414 */       if (!isFullDurability(player))
/* 560:    */       {
/* 561:415 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 562:    */         {
/* 563:416 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 3))
/* 564:    */           {
/* 565:417 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 566:418 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 3) });
/* 567:419 */             repair(player, "tools", "stone");
/* 568:420 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 569:    */           }
/* 570:    */           else
/* 571:    */           {
/* 572:422 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 573:    */           }
/* 574:    */         }
/* 575:    */         else {
/* 576:425 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 577:    */         }
/* 578:    */       }
/* 579:    */       else {
/* 580:428 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 581:    */       }
/* 582:    */     }
/* 583:430 */     else if (player.getItemInHand().getType().equals(Material.IRON_PICKAXE))
/* 584:    */     {
/* 585:431 */       if (!isFullDurability(player))
/* 586:    */       {
/* 587:432 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 588:    */         {
/* 589:433 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 3))
/* 590:    */           {
/* 591:434 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 592:435 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 3) });
/* 593:436 */             repair(player, "tools", "iron");
/* 594:437 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 595:    */           }
/* 596:    */           else
/* 597:    */           {
/* 598:439 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 599:    */           }
/* 600:    */         }
/* 601:    */         else {
/* 602:442 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 603:    */         }
/* 604:    */       }
/* 605:    */       else {
/* 606:445 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 607:    */       }
/* 608:    */     }
/* 609:447 */     else if (player.getItemInHand().getType().equals(Material.GOLD_PICKAXE))
/* 610:    */     {
/* 611:448 */       if (!isFullDurability(player))
/* 612:    */       {
/* 613:449 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 614:    */         {
/* 615:450 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 3))
/* 616:    */           {
/* 617:451 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 618:452 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 3) });
/* 619:453 */             repair(player, "tools", "gold");
/* 620:454 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 621:    */           }
/* 622:    */           else
/* 623:    */           {
/* 624:456 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 625:    */           }
/* 626:    */         }
/* 627:    */         else {
/* 628:459 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 629:    */         }
/* 630:    */       }
/* 631:    */       else {
/* 632:462 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 633:    */       }
/* 634:    */     }
/* 635:464 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)) {
/* 636:465 */       if (!isFullDurability(player))
/* 637:    */       {
/* 638:466 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 2))
/* 639:    */         {
/* 640:467 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 3))
/* 641:    */           {
/* 642:468 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 2) });
/* 643:469 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 3) });
/* 644:470 */             repair(player, "tools", "diamond");
/* 645:471 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 646:    */           }
/* 647:    */           else
/* 648:    */           {
/* 649:473 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 650:    */           }
/* 651:    */         }
/* 652:    */         else {
/* 653:476 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 654:    */         }
/* 655:    */       }
/* 656:    */       else {
/* 657:479 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 658:    */       }
/* 659:    */     }
/* 660:    */   }
/* 661:    */   
/* 662:    */   public void repairSword(Player player)
/* 663:    */   {
/* 664:484 */     if (player.getItemInHand().getType().equals(Material.WOOD_SWORD))
/* 665:    */     {
/* 666:485 */       if (!isFullDurability(player))
/* 667:    */       {
/* 668:486 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 669:    */         {
/* 670:487 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 2))
/* 671:    */           {
/* 672:488 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 673:489 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 2) });
/* 674:490 */             repair(player, "tools", "wood");
/* 675:491 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 676:    */           }
/* 677:    */           else
/* 678:    */           {
/* 679:493 */             player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 680:    */           }
/* 681:    */         }
/* 682:    */         else {
/* 683:496 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 684:    */         }
/* 685:    */       }
/* 686:    */       else {
/* 687:499 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 688:    */       }
/* 689:    */     }
/* 690:501 */     else if (player.getItemInHand().getType().equals(Material.STONE_SWORD))
/* 691:    */     {
/* 692:502 */       if (!isFullDurability(player))
/* 693:    */       {
/* 694:503 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 695:    */         {
/* 696:504 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 2))
/* 697:    */           {
/* 698:505 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 699:506 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.COBBLESTONE, 2) });
/* 700:507 */             repair(player, "tools", "stone");
/* 701:508 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 702:    */           }
/* 703:    */           else
/* 704:    */           {
/* 705:510 */             player.sendMessage(getResponse("RepairMessages.NotEnoughCobblestone") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 706:    */           }
/* 707:    */         }
/* 708:    */         else {
/* 709:513 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 710:    */         }
/* 711:    */       }
/* 712:    */       else {
/* 713:516 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 714:    */       }
/* 715:    */     }
/* 716:518 */     else if (player.getItemInHand().getType().equals(Material.IRON_SWORD))
/* 717:    */     {
/* 718:519 */       if (!isFullDurability(player))
/* 719:    */       {
/* 720:520 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 721:    */         {
/* 722:521 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 2))
/* 723:    */           {
/* 724:522 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 725:523 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 2) });
/* 726:524 */             repair(player, "tools", "iron");
/* 727:525 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 728:    */           }
/* 729:    */           else
/* 730:    */           {
/* 731:527 */             player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 732:    */           }
/* 733:    */         }
/* 734:    */         else {
/* 735:530 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 736:    */         }
/* 737:    */       }
/* 738:    */       else {
/* 739:533 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 740:    */       }
/* 741:    */     }
/* 742:535 */     else if (player.getItemInHand().getType().equals(Material.GOLD_SWORD))
/* 743:    */     {
/* 744:536 */       if (!isFullDurability(player))
/* 745:    */       {
/* 746:537 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 747:    */         {
/* 748:538 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 2))
/* 749:    */           {
/* 750:539 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 751:540 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 2) });
/* 752:541 */             repair(player, "tools", "gold");
/* 753:542 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 754:    */           }
/* 755:    */           else
/* 756:    */           {
/* 757:544 */             player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 758:    */           }
/* 759:    */         }
/* 760:    */         else {
/* 761:547 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 762:    */         }
/* 763:    */       }
/* 764:    */       else {
/* 765:550 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 766:    */       }
/* 767:    */     }
/* 768:552 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) {
/* 769:553 */       if (!isFullDurability(player))
/* 770:    */       {
/* 771:554 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 1))
/* 772:    */         {
/* 773:555 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 2))
/* 774:    */           {
/* 775:556 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 1) });
/* 776:557 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 2) });
/* 777:558 */             repair(player, "tools", "diamond");
/* 778:559 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 779:    */           }
/* 780:    */           else
/* 781:    */           {
/* 782:561 */             player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 783:    */           }
/* 784:    */         }
/* 785:    */         else {
/* 786:564 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 787:    */         }
/* 788:    */       }
/* 789:    */       else {
/* 790:567 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 791:    */       }
/* 792:    */     }
/* 793:    */   }
/* 794:    */   
/* 795:    */   public void repairShears(Player player)
/* 796:    */   {
/* 797:572 */     if (player.getItemInHand().getType().equals(Material.SHEARS)) {
/* 798:573 */       if (!isFullDurability(player))
/* 799:    */       {
/* 800:574 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 2))
/* 801:    */         {
/* 802:575 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 2) });
/* 803:576 */           repair(player, "tools", "shears");
/* 804:577 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 805:    */         }
/* 806:    */         else
/* 807:    */         {
/* 808:579 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 809:    */         }
/* 810:    */       }
/* 811:    */       else {
/* 812:582 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 813:    */       }
/* 814:    */     }
/* 815:    */   }
/* 816:    */   
/* 817:    */   public void repairBow(Player player)
/* 818:    */   {
/* 819:587 */     if (player.getItemInHand().getType().equals(Material.BOW)) {
/* 820:588 */       if (!isFullDurability(player))
/* 821:    */       {
/* 822:589 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 3))
/* 823:    */         {
/* 824:590 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.STRING), 3))
/* 825:    */           {
/* 826:591 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 3) });
/* 827:592 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STRING, 3) });
/* 828:593 */             repair(player, "tools", "bow");
/* 829:594 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 830:    */           }
/* 831:    */           else
/* 832:    */           {
/* 833:596 */             player.sendMessage(getResponse("RepairMessages.NotEnoughString") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 834:    */           }
/* 835:    */         }
/* 836:    */         else {
/* 837:599 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 838:    */         }
/* 839:    */       }
/* 840:    */       else {
/* 841:602 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 842:    */       }
/* 843:    */     }
/* 844:    */   }
/* 845:    */   
/* 846:    */   public void repairStick(Player player)
/* 847:    */   {
/* 848:607 */     if (player.getItemInHand().getType().equals(Material.STICK)) {
/* 849:608 */       if (!isFullDurability(player))
/* 850:    */       {
/* 851:609 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.WOOD), 1))
/* 852:    */         {
/* 853:610 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.WOOD, 1) });
/* 854:611 */           repair(player, "tools", "stick");
/* 855:612 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 856:    */         }
/* 857:    */         else
/* 858:    */         {
/* 859:614 */           player.sendMessage(getResponse("RepairMessages.NotEnoughWood") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 860:    */         }
/* 861:    */       }
/* 862:    */       else {
/* 863:617 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 864:    */       }
/* 865:    */     }
/* 866:    */   }
/* 867:    */   
/* 868:    */   public void repairFlintAndSteel(Player player)
/* 869:    */   {
/* 870:622 */     if (player.getItemInHand().getType().equals(Material.FLINT_AND_STEEL)) {
/* 871:623 */       if (!isFullDurability(player))
/* 872:    */       {
/* 873:624 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 1))
/* 874:    */         {
/* 875:625 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.FLINT), 1))
/* 876:    */           {
/* 877:626 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.FLINT, 1) });
/* 878:627 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 1) });
/* 879:628 */             repair(player, "tools", "flintAndSteel");
/* 880:629 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 881:    */           }
/* 882:    */           else
/* 883:    */           {
/* 884:631 */             player.sendMessage(getResponse("RepairMessages.NotEnoughFlint") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 885:    */           }
/* 886:    */         }
/* 887:    */         else {
/* 888:634 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 889:    */         }
/* 890:    */       }
/* 891:    */       else {
/* 892:637 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 893:    */       }
/* 894:    */     }
/* 895:    */   }
/* 896:    */   
/* 897:    */   public void repairFishingRod(Player player)
/* 898:    */   {
/* 899:642 */     if (player.getItemInHand().getType().equals(Material.FISHING_ROD)) {
/* 900:643 */       if (!isFullDurability(player))
/* 901:    */       {
/* 902:644 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.STICK), 3))
/* 903:    */         {
/* 904:645 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.STRING), 2))
/* 905:    */           {
/* 906:646 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STICK, 3) });
/* 907:647 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.STRING, 2) });
/* 908:648 */             repair(player, "tools", "fishingRod");
/* 909:649 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 910:    */           }
/* 911:    */           else
/* 912:    */           {
/* 913:651 */             player.sendMessage(getResponse("RepairMessages.NotEnoughString") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 914:    */           }
/* 915:    */         }
/* 916:    */         else {
/* 917:654 */           player.sendMessage(getResponse("RepairMessages.NotEnoughSticks") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 918:    */         }
/* 919:    */       }
/* 920:    */       else {
/* 921:657 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 922:    */       }
/* 923:    */     }
/* 924:    */   }
/* 925:    */   
/* 926:    */   public void repairCarrotStick(Player player)
/* 927:    */   {
/* 928:662 */     if (player.getItemInHand().getType().equals(Material.CARROT_STICK)) {
/* 929:663 */       if (!isFullDurability(player))
/* 930:    */       {
/* 931:664 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.CARROT_ITEM), 1))
/* 932:    */         {
/* 933:665 */           if (player.getInventory().containsAtLeast(new ItemStack(Material.FISHING_ROD), 1))
/* 934:    */           {
/* 935:666 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.CARROT_ITEM, 1) });
/* 936:667 */             player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.FISHING_ROD, 1) });
/* 937:668 */             repair(player, "tools", "carrotStick");
/* 938:669 */             player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 939:    */           }
/* 940:    */           else
/* 941:    */           {
/* 942:671 */             player.sendMessage(getResponse("RepairMessages.NotEnoughFishingRod") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 943:    */           }
/* 944:    */         }
/* 945:    */         else {
/* 946:674 */           player.sendMessage(getResponse("RepairMessages.NotEnoughCarrots") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 947:    */         }
/* 948:    */       }
/* 949:    */       else {
/* 950:677 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 951:    */       }
/* 952:    */     }
/* 953:    */   }
/* 954:    */   
/* 955:    */   public void repairHelmet(Player player)
/* 956:    */   {
/* 957:682 */     if (!isFullDurability(player))
/* 958:    */     {
/* 959:683 */       if (player.getItemInHand().getType().equals(Material.LEATHER_HELMET))
/* 960:    */       {
/* 961:684 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.LEATHER), 5))
/* 962:    */         {
/* 963:685 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LEATHER, 5) });
/* 964:686 */           repair(player, "armour", "leather");
/* 965:687 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 966:    */         }
/* 967:    */         else
/* 968:    */         {
/* 969:689 */           player.sendMessage(getResponse("RepairMessages.NotEnoughLeather") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 970:    */         }
/* 971:    */       }
/* 972:    */       else {
/* 973:692 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 974:    */       }
/* 975:    */     }
/* 976:694 */     else if (player.getItemInHand().getType().equals(Material.IRON_HELMET))
/* 977:    */     {
/* 978:695 */       if (!isFullDurability(player))
/* 979:    */       {
/* 980:696 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 5))
/* 981:    */         {
/* 982:697 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 5) });
/* 983:698 */           repair(player, "armour", "iron");
/* 984:699 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 985:    */         }
/* 986:    */         else
/* 987:    */         {
/* 988:701 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* 989:    */         }
/* 990:    */       }
/* 991:    */       else {
/* 992:704 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* 993:    */       }
/* 994:    */     }
/* 995:706 */     else if (player.getItemInHand().getType().equals(Material.GOLD_HELMET))
/* 996:    */     {
/* 997:707 */       if (!isFullDurability(player))
/* 998:    */       {
/* 999:708 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 5))
/* :00:    */         {
/* :01:709 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 5) });
/* :02:710 */           repair(player, "armour", "gold");
/* :03:711 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :04:    */         }
/* :05:    */         else
/* :06:    */         {
/* :07:713 */           player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :08:    */         }
/* :09:    */       }
/* :10:    */       else {
/* :11:716 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :12:    */       }
/* :13:    */     }
/* :14:718 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_HELMET)) {
/* :15:719 */       if (!isFullDurability(player))
/* :16:    */       {
/* :17:720 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 5))
/* :18:    */         {
/* :19:721 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 5) });
/* :20:722 */           repair(player, "armour", "diamond");
/* :21:723 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :22:    */         }
/* :23:    */         else
/* :24:    */         {
/* :25:725 */           player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :26:    */         }
/* :27:    */       }
/* :28:    */       else {
/* :29:728 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :30:    */       }
/* :31:    */     }
/* :32:    */   }
/* :33:    */   
/* :34:    */   public void repairChestplate(Player player)
/* :35:    */   {
/* :36:733 */     if (player.getItemInHand().getType().equals(Material.LEATHER_CHESTPLATE))
/* :37:    */     {
/* :38:734 */       if (!isFullDurability(player))
/* :39:    */       {
/* :40:735 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.LEATHER), 8))
/* :41:    */         {
/* :42:736 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LEATHER, 8) });
/* :43:737 */           repair(player, "armour", "leather");
/* :44:738 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :45:    */         }
/* :46:    */         else
/* :47:    */         {
/* :48:740 */           player.sendMessage(getResponse("RepairMessages.NotEnoughLeather") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :49:    */         }
/* :50:    */       }
/* :51:    */       else {
/* :52:743 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :53:    */       }
/* :54:    */     }
/* :55:745 */     else if (player.getItemInHand().getType().equals(Material.IRON_CHESTPLATE))
/* :56:    */     {
/* :57:746 */       if (!isFullDurability(player))
/* :58:    */       {
/* :59:747 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 8))
/* :60:    */         {
/* :61:748 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 8) });
/* :62:749 */           repair(player, "armour", "iron");
/* :63:750 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :64:    */         }
/* :65:    */         else
/* :66:    */         {
/* :67:752 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :68:    */         }
/* :69:    */       }
/* :70:    */       else {
/* :71:755 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :72:    */       }
/* :73:    */     }
/* :74:757 */     else if (player.getItemInHand().getType().equals(Material.GOLD_CHESTPLATE))
/* :75:    */     {
/* :76:758 */       if (!isFullDurability(player))
/* :77:    */       {
/* :78:759 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 8))
/* :79:    */         {
/* :80:760 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 8) });
/* :81:761 */           repair(player, "armour", "gold");
/* :82:762 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :83:    */         }
/* :84:    */         else
/* :85:    */         {
/* :86:764 */           player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* :87:    */         }
/* :88:    */       }
/* :89:    */       else {
/* :90:767 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* :91:    */       }
/* :92:    */     }
/* :93:769 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_CHESTPLATE)) {
/* :94:770 */       if (!isFullDurability(player))
/* :95:    */       {
/* :96:771 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 8))
/* :97:    */         {
/* :98:772 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 8) });
/* :99:773 */           repair(player, "armour", "diamond");
/* ;00:774 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;01:    */         }
/* ;02:    */         else
/* ;03:    */         {
/* ;04:776 */           player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;05:    */         }
/* ;06:    */       }
/* ;07:    */       else {
/* ;08:779 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;09:    */       }
/* ;10:    */     }
/* ;11:    */   }
/* ;12:    */   
/* ;13:    */   public void repairLeggings(Player player)
/* ;14:    */   {
/* ;15:784 */     if (player.getItemInHand().getType().equals(Material.LEATHER_LEGGINGS))
/* ;16:    */     {
/* ;17:785 */       if (!isFullDurability(player))
/* ;18:    */       {
/* ;19:786 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.LEATHER), 7))
/* ;20:    */         {
/* ;21:787 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LEATHER, 7) });
/* ;22:788 */           repair(player, "armour", "leather");
/* ;23:789 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;24:    */         }
/* ;25:    */         else
/* ;26:    */         {
/* ;27:791 */           player.sendMessage(getResponse("RepairMessages.NotEnoughLeather") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;28:    */         }
/* ;29:    */       }
/* ;30:    */       else {
/* ;31:794 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;32:    */       }
/* ;33:    */     }
/* ;34:796 */     else if (player.getItemInHand().getType().equals(Material.IRON_LEGGINGS))
/* ;35:    */     {
/* ;36:797 */       if (!isFullDurability(player))
/* ;37:    */       {
/* ;38:798 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 7))
/* ;39:    */         {
/* ;40:799 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 7) });
/* ;41:800 */           repair(player, "armour", "iron");
/* ;42:801 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;43:    */         }
/* ;44:    */         else
/* ;45:    */         {
/* ;46:803 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;47:    */         }
/* ;48:    */       }
/* ;49:    */       else {
/* ;50:806 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;51:    */       }
/* ;52:    */     }
/* ;53:808 */     else if (player.getItemInHand().getType().equals(Material.GOLD_LEGGINGS))
/* ;54:    */     {
/* ;55:809 */       if (!isFullDurability(player))
/* ;56:    */       {
/* ;57:810 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 7))
/* ;58:    */         {
/* ;59:811 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 7) });
/* ;60:812 */           repair(player, "armour", "gold");
/* ;61:813 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;62:    */         }
/* ;63:    */         else
/* ;64:    */         {
/* ;65:815 */           player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;66:    */         }
/* ;67:    */       }
/* ;68:    */       else {
/* ;69:818 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;70:    */       }
/* ;71:    */     }
/* ;72:820 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_LEGGINGS)) {
/* ;73:821 */       if (!isFullDurability(player))
/* ;74:    */       {
/* ;75:822 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 7))
/* ;76:    */         {
/* ;77:823 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 7) });
/* ;78:824 */           repair(player, "armour", "diamond");
/* ;79:825 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;80:    */         }
/* ;81:    */         else
/* ;82:    */         {
/* ;83:827 */           player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* ;84:    */         }
/* ;85:    */       }
/* ;86:    */       else {
/* ;87:830 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* ;88:    */       }
/* ;89:    */     }
/* ;90:    */   }
/* ;91:    */   
/* ;92:    */   public void repairBoots(Player player)
/* ;93:    */   {
/* ;94:835 */     if (player.getItemInHand().getType().equals(Material.LEATHER_BOOTS))
/* ;95:    */     {
/* ;96:836 */       if (!isFullDurability(player))
/* ;97:    */       {
/* ;98:837 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.LEATHER), 4))
/* ;99:    */         {
/* <00:838 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.LEATHER, 4) });
/* <01:839 */           repair(player, "armour", "leather");
/* <02:840 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <03:    */         }
/* <04:    */         else
/* <05:    */         {
/* <06:842 */           player.sendMessage(getResponse("RepairMessages.NotEnoughLeather") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <07:    */         }
/* <08:    */       }
/* <09:    */       else {
/* <10:845 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* <11:    */       }
/* <12:    */     }
/* <13:847 */     else if (player.getItemInHand().getType().equals(Material.IRON_BOOTS))
/* <14:    */     {
/* <15:848 */       if (!isFullDurability(player))
/* <16:    */       {
/* <17:849 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 4))
/* <18:    */         {
/* <19:850 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.IRON_INGOT, 4) });
/* <20:851 */           repair(player, "armour", "iron");
/* <21:852 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <22:    */         }
/* <23:    */         else
/* <24:    */         {
/* <25:854 */           player.sendMessage(getResponse("RepairMessages.NotEnoughIron") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <26:    */         }
/* <27:    */       }
/* <28:    */       else {
/* <29:857 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* <30:    */       }
/* <31:    */     }
/* <32:859 */     else if (player.getItemInHand().getType().equals(Material.GOLD_BOOTS))
/* <33:    */     {
/* <34:860 */       if (!isFullDurability(player))
/* <35:    */       {
/* <36:861 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 4))
/* <37:    */         {
/* <38:862 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.GOLD_INGOT, 4) });
/* <39:863 */           repair(player, "armour", "gold");
/* <40:864 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <41:    */         }
/* <42:    */         else
/* <43:    */         {
/* <44:866 */           player.sendMessage(getResponse("RepairMessages.NotEnoughGold") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <45:    */         }
/* <46:    */       }
/* <47:    */       else {
/* <48:869 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* <49:    */       }
/* <50:    */     }
/* <51:871 */     else if (player.getItemInHand().getType().equals(Material.DIAMOND_BOOTS)) {
/* <52:872 */       if (!isFullDurability(player))
/* <53:    */       {
/* <54:873 */         if (player.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 4))
/* <55:    */         {
/* <56:874 */           player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.DIAMOND, 4) });
/* <57:875 */           repair(player, "armour", "diamond");
/* <58:876 */           player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <59:    */         }
/* <60:    */         else
/* <61:    */         {
/* <62:878 */           player.sendMessage(getResponse("RepairMessages.NotEnoughDiamond") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/* <63:    */         }
/* <64:    */       }
/* <65:    */       else {
/* <66:881 */         player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + " " + getResponse("RepairMessages.DoesntNeedRepair"));
/* <67:    */       }
/* <68:    */     }
/* <69:    */   }
/* <70:    */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.RepairItems
 * JD-Core Version:    0.7.0.1
 */