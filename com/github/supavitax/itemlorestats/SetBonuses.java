/*   1:    */ package com.github.supavitax.itemlorestats;
/*   2:    */ 
/*   3:    */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*   4:    */ import java.io.File;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import java.util.HashMap;
/*   7:    */ import java.util.Set;
/*   8:    */ import org.bukkit.configuration.ConfigurationSection;
/*   9:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  10:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  11:    */ import org.bukkit.entity.Player;
/*  12:    */ 
/*  13:    */ public class SetBonuses
/*  14:    */ {
/*  15:    */   private FileConfiguration PlayerDataConfig;
/*  16: 16 */   GearStats gearStats = new GearStats();
/*  17: 17 */   Util_Colours util_Colours = new Util_Colours();
/*  18:    */   
/*  19:    */   public void updateSetBonus(Player player)
/*  20:    */   {
/*  21:    */     try
/*  22:    */     {
/*  23: 21 */       this.PlayerDataConfig = new YamlConfiguration();
/*  24: 22 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + "SetBonuses.yml"));
/*  25:    */       
/*  26: 24 */       String gearArmour = "";
/*  27: 25 */       String gearDodge = "";
/*  28: 26 */       String gearBlock = "";
/*  29: 27 */       String gearCritChance = "";
/*  30: 28 */       String gearCritDamage = "";
/*  31: 29 */       String gearDamage = "";
/*  32: 30 */       String gearHealth = "";
/*  33: 31 */       String gearHealthRegen = "";
/*  34: 32 */       String gearLifeSteal = "";
/*  35: 33 */       String gearReflect = "";
/*  36: 34 */       String gearFire = "";
/*  37: 35 */       String gearIce = "";
/*  38: 36 */       String gearPoison = "";
/*  39: 37 */       String gearWither = "";
/*  40: 38 */       String gearHarming = "";
/*  41: 39 */       String gearBlind = "";
/*  42: 40 */       String gearXPMultiplier = "";
/*  43: 41 */       String gearSpeed = "";
/*  44:    */       
/*  45: 43 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".armour", Double.valueOf(0.0D));
/*  46: 44 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".dodge", Double.valueOf(0.0D));
/*  47: 45 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".block", Double.valueOf(0.0D));
/*  48: 46 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".damage", Double.valueOf(0.0D));
/*  49: 47 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critChance", Double.valueOf(0.0D));
/*  50: 48 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critDamage", Double.valueOf(0.0D));
/*  51: 49 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".health", Double.valueOf(0.0D));
/*  52: 50 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".healthRegen", Double.valueOf(0.0D));
/*  53: 51 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".lifeSteal", Double.valueOf(0.0D));
/*  54: 52 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".reflect", Double.valueOf(0.0D));
/*  55: 53 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".fire", Double.valueOf(0.0D));
/*  56: 54 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".ice", Double.valueOf(0.0D));
/*  57: 55 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".poison", Double.valueOf(0.0D));
/*  58: 56 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".wither", Double.valueOf(0.0D));
/*  59: 57 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".harming", Double.valueOf(0.0D));
/*  60: 58 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".blind", Double.valueOf(0.0D));
/*  61: 59 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".xpMultiplier", Double.valueOf(0.0D));
/*  62: 60 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".speed", Double.valueOf(0.0D));
/*  63:    */       
/*  64: 62 */       int a = this.PlayerDataConfig.getKeys(false).size() - 1;
/*  65: 63 */       String key = null;
/*  66:    */       do
/*  67:    */       {
/*  68: 65 */         int x = 0;
/*  69: 66 */         if (this.PlayerDataConfig.getKeys(false).toString().length() > 2) {
/*  70: 68 */           key = this.PlayerDataConfig.getKeys(false).toString().split(",")[a].replaceAll("\\[", "").replaceAll("\\]", "").trim();
/*  71:    */         }
/*  72: 71 */         if (key != null)
/*  73:    */         {
/*  74: 72 */           if (this.gearStats.getSetBonusHead(player).equals(key)) {
/*  75: 73 */             x++;
/*  76:    */           }
/*  77: 76 */           if (this.gearStats.getSetBonusChest(player).equals(key)) {
/*  78: 77 */             x++;
/*  79:    */           }
/*  80: 80 */           if (this.gearStats.getSetBonusLegs(player).equals(key)) {
/*  81: 81 */             x++;
/*  82:    */           }
/*  83: 84 */           if (this.gearStats.getSetBonusBoots(player).equals(key)) {
/*  84: 85 */             x++;
/*  85:    */           }
/*  86: 88 */           if (this.gearStats.getSetBonusItemInHand(player).equals(key)) {
/*  87: 89 */             x++;
/*  88:    */           }
/*  89: 92 */           for (int b = this.PlayerDataConfig.getConfigurationSection(key).getKeys(false).size() - 1; (b >= 0) && (b <= this.PlayerDataConfig.getConfigurationSection(key).getKeys(false).size() - 1); b--) {
/*  90: 94 */             if (this.PlayerDataConfig.getConfigurationSection(key).getKeys(false).toString().split(",")[b].replaceAll("\\[", "").replaceAll("\\]", "").trim() != null)
/*  91:    */             {
/*  92: 95 */               String keyFromParentKey = this.PlayerDataConfig.getConfigurationSection(key).getKeys(false).toString().split(",")[b].replaceAll("\\[", "").replaceAll("\\]", "").trim();
/*  93:    */               
/*  94: 97 */               gearArmour = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".armour");
/*  95: 98 */               gearDodge = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".dodge");
/*  96: 99 */               gearBlock = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".block");
/*  97:100 */               gearCritChance = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".critChance");
/*  98:101 */               gearCritDamage = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".critDamage");
/*  99:102 */               gearDamage = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".damage");
/* 100:103 */               gearHealth = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".health");
/* 101:104 */               gearHealthRegen = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".healthRegen");
/* 102:105 */               gearLifeSteal = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".lifeSteal");
/* 103:106 */               gearReflect = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".reflect");
/* 104:107 */               gearFire = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".fire");
/* 105:108 */               gearIce = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".ice");
/* 106:109 */               gearPoison = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".poison");
/* 107:110 */               gearWither = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".wither");
/* 108:111 */               gearHarming = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".harming");
/* 109:112 */               gearBlind = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".blind");
/* 110:113 */               gearXPMultiplier = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".xpMultiplier");
/* 111:114 */               gearSpeed = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".movementSpeed");
/* 112:116 */               if (x >= Integer.parseInt(keyFromParentKey))
/* 113:    */               {
/* 114:117 */                 if (gearArmour != null)
/* 115:    */                 {
/* 116:118 */                   player.sendMessage("armour hashmap set to " + Integer.parseInt(gearArmour));
/* 117:119 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".armour", Double.valueOf(Double.parseDouble(gearArmour)));
/* 118:    */                 }
/* 119:    */                 else
/* 120:    */                 {
/* 121:121 */                   player.sendMessage("armour hashmap set to 0");
/* 122:122 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".armour", Double.valueOf(0.0D));
/* 123:    */                 }
/* 124:125 */                 if (gearDodge != null)
/* 125:    */                 {
/* 126:126 */                   player.sendMessage("dodge hashmap set to " + Integer.parseInt(gearDodge));
/* 127:127 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".dodge", Double.valueOf(Double.parseDouble(gearDodge)));
/* 128:    */                 }
/* 129:    */                 else
/* 130:    */                 {
/* 131:129 */                   player.sendMessage("dodge hashmap set to 0");
/* 132:130 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".dodge", Double.valueOf(0.0D));
/* 133:    */                 }
/* 134:133 */                 if (gearBlock != null)
/* 135:    */                 {
/* 136:134 */                   player.sendMessage("block hashmap set to " + Integer.parseInt(gearBlock));
/* 137:135 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".block", Double.valueOf(Double.parseDouble(gearBlock)));
/* 138:    */                 }
/* 139:    */                 else
/* 140:    */                 {
/* 141:137 */                   player.sendMessage("block hashmap set to 0");
/* 142:138 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".block", Double.valueOf(0.0D));
/* 143:    */                 }
/* 144:141 */                 if (gearDamage != null)
/* 145:    */                 {
/* 146:142 */                   player.sendMessage("damage hashmap set to " + Integer.parseInt(gearDamage));
/* 147:143 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".damage", Double.valueOf(Double.parseDouble(gearDamage)));
/* 148:    */                 }
/* 149:    */                 else
/* 150:    */                 {
/* 151:145 */                   player.sendMessage("damage hashmap set to 0");
/* 152:146 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".damage", Double.valueOf(0.0D));
/* 153:    */                 }
/* 154:149 */                 if (gearCritChance != null)
/* 155:    */                 {
/* 156:150 */                   player.sendMessage("critchance hashmap set to " + Integer.parseInt(gearCritChance));
/* 157:151 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critChance", Double.valueOf(Double.parseDouble(gearCritChance)));
/* 158:    */                 }
/* 159:    */                 else
/* 160:    */                 {
/* 161:153 */                   player.sendMessage("critchance hashmap set to 0");
/* 162:154 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critChance", Double.valueOf(0.0D));
/* 163:    */                 }
/* 164:157 */                 if (gearCritDamage != null)
/* 165:    */                 {
/* 166:158 */                   player.sendMessage("critdamage hashmap set to " + Integer.parseInt(gearCritDamage));
/* 167:159 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critDamage", Double.valueOf(Double.parseDouble(gearCritDamage)));
/* 168:    */                 }
/* 169:    */                 else
/* 170:    */                 {
/* 171:161 */                   player.sendMessage("critdamage hashmap set to 0");
/* 172:162 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critDamage", Double.valueOf(0.0D));
/* 173:    */                 }
/* 174:165 */                 if (gearHealth != null)
/* 175:    */                 {
/* 176:166 */                   player.sendMessage("health hashmap set to " + Integer.parseInt(gearHealth));
/* 177:167 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".health", Double.valueOf(Double.parseDouble(gearHealth)));
/* 178:    */                 }
/* 179:    */                 else
/* 180:    */                 {
/* 181:170 */                   player.sendMessage("health hashmap set to 0");
/* 182:171 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".health", Double.valueOf(0.0D));
/* 183:    */                 }
/* 184:174 */                 if (gearHealthRegen != null)
/* 185:    */                 {
/* 186:175 */                   player.sendMessage("health regen hashmap set to " + Integer.parseInt(gearHealthRegen));
/* 187:176 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".healthRegen", Double.valueOf(Double.parseDouble(gearHealthRegen)));
/* 188:    */                 }
/* 189:    */                 else
/* 190:    */                 {
/* 191:179 */                   player.sendMessage("health regen hashmap set to 0");
/* 192:180 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".healthRegen", Double.valueOf(0.0D));
/* 193:    */                 }
/* 194:183 */                 if (gearLifeSteal != null)
/* 195:    */                 {
/* 196:184 */                   player.sendMessage("lifesteal hashmap set to " + Integer.parseInt(gearLifeSteal));
/* 197:185 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".lifeSteal", Double.valueOf(Double.parseDouble(gearLifeSteal)));
/* 198:    */                 }
/* 199:    */                 else
/* 200:    */                 {
/* 201:187 */                   player.sendMessage("lifesteal hashmap set to 0");
/* 202:188 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".lifeSteal", Double.valueOf(0.0D));
/* 203:    */                 }
/* 204:191 */                 if (gearReflect != null)
/* 205:    */                 {
/* 206:192 */                   player.sendMessage("reflect hashmap set to " + Integer.parseInt(gearReflect));
/* 207:193 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".reflect", Double.valueOf(Double.parseDouble(gearReflect)));
/* 208:    */                 }
/* 209:    */                 else
/* 210:    */                 {
/* 211:195 */                   player.sendMessage("reflect hashmap set to 0");
/* 212:196 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".reflect", Double.valueOf(0.0D));
/* 213:    */                 }
/* 214:199 */                 if (gearFire != null)
/* 215:    */                 {
/* 216:200 */                   player.sendMessage("fire hashmap set to " + Integer.parseInt(gearFire));
/* 217:201 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".fire", Double.valueOf(Double.parseDouble(gearFire)));
/* 218:    */                 }
/* 219:    */                 else
/* 220:    */                 {
/* 221:203 */                   player.sendMessage("fire hashmap set to 0");
/* 222:204 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".fire", Double.valueOf(0.0D));
/* 223:    */                 }
/* 224:207 */                 if (gearIce != null)
/* 225:    */                 {
/* 226:208 */                   player.sendMessage("ice hashmap set to " + Integer.parseInt(gearIce));
/* 227:209 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".ice", Double.valueOf(Double.parseDouble(gearIce)));
/* 228:    */                 }
/* 229:    */                 else
/* 230:    */                 {
/* 231:211 */                   player.sendMessage("ice hashmap set to 0");
/* 232:212 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".ice", Double.valueOf(0.0D));
/* 233:    */                 }
/* 234:215 */                 if (gearPoison != null)
/* 235:    */                 {
/* 236:216 */                   player.sendMessage("poison hashmap set to " + Integer.parseInt(gearPoison));
/* 237:217 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".poison", Double.valueOf(Double.parseDouble(gearPoison)));
/* 238:    */                 }
/* 239:    */                 else
/* 240:    */                 {
/* 241:219 */                   player.sendMessage("poison hashmap set to 0");
/* 242:220 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".poison", Double.valueOf(0.0D));
/* 243:    */                 }
/* 244:223 */                 if (gearWither != null)
/* 245:    */                 {
/* 246:224 */                   player.sendMessage("wither hashmap set to " + Integer.parseInt(gearWither));
/* 247:225 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".wither", Double.valueOf(Double.parseDouble(gearWither)));
/* 248:    */                 }
/* 249:    */                 else
/* 250:    */                 {
/* 251:227 */                   player.sendMessage("wither hashmap set to 0");
/* 252:228 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".wither", Double.valueOf(0.0D));
/* 253:    */                 }
/* 254:231 */                 if (gearHarming != null)
/* 255:    */                 {
/* 256:232 */                   player.sendMessage("harming hashmap set to " + Integer.parseInt(gearHarming));
/* 257:233 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".harming", Double.valueOf(Double.parseDouble(gearHarming)));
/* 258:    */                 }
/* 259:    */                 else
/* 260:    */                 {
/* 261:235 */                   player.sendMessage("harming hashmap set to 0");
/* 262:236 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".harming", Double.valueOf(0.0D));
/* 263:    */                 }
/* 264:239 */                 if (gearBlind != null)
/* 265:    */                 {
/* 266:240 */                   player.sendMessage("blind hashmap set to " + Integer.parseInt(gearBlind));
/* 267:241 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".blind", Double.valueOf(Double.parseDouble(gearBlind)));
/* 268:    */                 }
/* 269:    */                 else
/* 270:    */                 {
/* 271:243 */                   player.sendMessage("blind hashmap set to 0");
/* 272:244 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".blind", Double.valueOf(0.0D));
/* 273:    */                 }
/* 274:247 */                 if (gearXPMultiplier != null)
/* 275:    */                 {
/* 276:248 */                   player.sendMessage("xpMultiplier hashmap set to " + Integer.parseInt(gearXPMultiplier));
/* 277:249 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".xpMultiplier", Double.valueOf(Double.parseDouble(gearXPMultiplier)));
/* 278:    */                 }
/* 279:    */                 else
/* 280:    */                 {
/* 281:251 */                   player.sendMessage("xpMultiplier hashmap set to 0");
/* 282:252 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".xpMultiplier", Double.valueOf(0.0D));
/* 283:    */                 }
/* 284:255 */                 if (gearSpeed != null)
/* 285:    */                 {
/* 286:256 */                   player.sendMessage("speed hashmap set to " + Integer.parseInt(gearSpeed));
/* 287:257 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".speed", Double.valueOf(Double.parseDouble(gearSpeed)));
/* 288:    */                 }
/* 289:    */                 else
/* 290:    */                 {
/* 291:259 */                   player.sendMessage("speed hashmap set to 0");
/* 292:260 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".speed", Double.valueOf(0.0D));
/* 293:    */                 }
/* 294:    */               }
/* 295:    */             }
/* 296:    */           }
/* 297:    */         }
/* 298:266 */         a--;
/* 299:266 */         if (a < 0) {
/* 300:    */           break;
/* 301:    */         }
/* 302:266 */       } while (a <= this.PlayerDataConfig.getKeys(false).size() - 1);
/* 303:    */     }
/* 304:    */     catch (Exception e)
/* 305:    */     {
/* 306:270 */       e.printStackTrace();
/* 307:271 */       System.out.println("*********** Failed to load set bonus for " + player.getName() + "! ***********");
/* 308:    */     }
/* 309:    */   }
/* 310:    */   
/* 311:    */   public double checkHashMapArmour(Player player)
/* 312:    */   {
/* 313:276 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".armour") != null) {
/* 314:277 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".armour")).doubleValue();
/* 315:    */     }
/* 316:279 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 317:280 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".armour")).doubleValue();
/* 318:    */   }
/* 319:    */   
/* 320:    */   public double checkHashMapDodge(Player player)
/* 321:    */   {
/* 322:285 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".dodge") != null) {
/* 323:286 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".dodge")).doubleValue();
/* 324:    */     }
/* 325:288 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 326:289 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".dodge")).doubleValue();
/* 327:    */   }
/* 328:    */   
/* 329:    */   public double checkHashMapBlock(Player player)
/* 330:    */   {
/* 331:294 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".block") != null) {
/* 332:295 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".block")).doubleValue();
/* 333:    */     }
/* 334:297 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 335:298 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".block")).doubleValue();
/* 336:    */   }
/* 337:    */   
/* 338:    */   public double checkHashMapDamage(Player player)
/* 339:    */   {
/* 340:303 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".damage") != null) {
/* 341:304 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".damage")).doubleValue();
/* 342:    */     }
/* 343:306 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 344:307 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".damage")).doubleValue();
/* 345:    */   }
/* 346:    */   
/* 347:    */   public double checkHashMapCritChance(Player player)
/* 348:    */   {
/* 349:312 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critChance") != null) {
/* 350:313 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critChance")).doubleValue();
/* 351:    */     }
/* 352:315 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 353:316 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critChance")).doubleValue();
/* 354:    */   }
/* 355:    */   
/* 356:    */   public double checkHashMapCritDamage(Player player)
/* 357:    */   {
/* 358:321 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critDamage") != null) {
/* 359:322 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critDamage")).doubleValue();
/* 360:    */     }
/* 361:324 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 362:325 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critDamage")).doubleValue();
/* 363:    */   }
/* 364:    */   
/* 365:    */   public double checkHashMapHealth(Player player)
/* 366:    */   {
/* 367:330 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".health") != null) {
/* 368:331 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".health")).doubleValue();
/* 369:    */     }
/* 370:333 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 371:334 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".health")).doubleValue();
/* 372:    */   }
/* 373:    */   
/* 374:    */   public double checkHashMapHealthRegen(Player player)
/* 375:    */   {
/* 376:339 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".healthRegen") != null) {
/* 377:340 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".healthRegen")).doubleValue();
/* 378:    */     }
/* 379:342 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 380:343 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".healthRegen")).doubleValue();
/* 381:    */   }
/* 382:    */   
/* 383:    */   public double checkHashMapLifeSteal(Player player)
/* 384:    */   {
/* 385:348 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".lifeSteal") != null) {
/* 386:349 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".lifeSteal")).doubleValue();
/* 387:    */     }
/* 388:351 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 389:352 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".lifeSteal")).doubleValue();
/* 390:    */   }
/* 391:    */   
/* 392:    */   public double checkHashMapReflect(Player player)
/* 393:    */   {
/* 394:357 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".reflect") != null) {
/* 395:358 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".reflect")).doubleValue();
/* 396:    */     }
/* 397:360 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 398:361 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".reflect")).doubleValue();
/* 399:    */   }
/* 400:    */   
/* 401:    */   public double checkHashMapFire(Player player)
/* 402:    */   {
/* 403:366 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".fire") != null) {
/* 404:367 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".fire")).doubleValue();
/* 405:    */     }
/* 406:369 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 407:370 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".fire")).doubleValue();
/* 408:    */   }
/* 409:    */   
/* 410:    */   public double checkHashMapIce(Player player)
/* 411:    */   {
/* 412:375 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".ice") != null) {
/* 413:376 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".ice")).doubleValue();
/* 414:    */     }
/* 415:378 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 416:379 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".ice")).doubleValue();
/* 417:    */   }
/* 418:    */   
/* 419:    */   public double checkHashMapPoison(Player player)
/* 420:    */   {
/* 421:384 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".poison") != null) {
/* 422:385 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".poison")).doubleValue();
/* 423:    */     }
/* 424:387 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 425:388 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".poison")).doubleValue();
/* 426:    */   }
/* 427:    */   
/* 428:    */   public double checkHashMapWither(Player player)
/* 429:    */   {
/* 430:393 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".wither") != null) {
/* 431:394 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".wither")).doubleValue();
/* 432:    */     }
/* 433:396 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 434:397 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".wither")).doubleValue();
/* 435:    */   }
/* 436:    */   
/* 437:    */   public double checkHashMapHarming(Player player)
/* 438:    */   {
/* 439:402 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".harming") != null) {
/* 440:403 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".harming")).doubleValue();
/* 441:    */     }
/* 442:405 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 443:406 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".harming")).doubleValue();
/* 444:    */   }
/* 445:    */   
/* 446:    */   public double checkHashMapBlind(Player player)
/* 447:    */   {
/* 448:411 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".blind") != null) {
/* 449:412 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".blind")).doubleValue();
/* 450:    */     }
/* 451:414 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 452:415 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".blind")).doubleValue();
/* 453:    */   }
/* 454:    */   
/* 455:    */   public double checkHashMapXPMultiplier(Player player)
/* 456:    */   {
/* 457:420 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".xpMultiplier") != null) {
/* 458:421 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".xpMultiplier")).doubleValue();
/* 459:    */     }
/* 460:423 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 461:424 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".xpMultiplier")).doubleValue();
/* 462:    */   }
/* 463:    */   
/* 464:    */   public double checkHashMapSpeed(Player player)
/* 465:    */   {
/* 466:429 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".speed") != null) {
/* 467:430 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".speed")).doubleValue();
/* 468:    */     }
/* 469:432 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 470:433 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".speed")).doubleValue();
/* 471:    */   }
/* 472:    */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.SetBonuses
 * JD-Core Version:    0.7.0.1
 */