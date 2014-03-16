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
/*  16: 13 */   GearStats gearStats = new GearStats();
/*  17: 14 */   Util_Colours util_Colours = new Util_Colours();
/*  18:    */   
/*  19:    */   public void updateSetBonus(Player player)
/*  20:    */   {
/*  21:    */     try
/*  22:    */     {
/*  23: 18 */       this.PlayerDataConfig = new YamlConfiguration();
/*  24: 19 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + "SetBonuses.yml"));
/*  25:    */       
/*  26: 21 */       String gearArmour = "";
/*  27: 22 */       String gearDodge = "";
/*  28: 23 */       String gearBlock = "";
/*  29: 24 */       String gearCritChance = "";
/*  30: 25 */       String gearCritDamage = "";
/*  31: 26 */       String gearDamage = "";
/*  32: 27 */       String gearHealth = "";
/*  33: 28 */       String gearHealthRegen = "";
/*  34: 29 */       String gearLifeSteal = "";
/*  35: 30 */       String gearReflect = "";
/*  36: 31 */       String gearFire = "";
/*  37: 32 */       String gearIce = "";
/*  38: 33 */       String gearPoison = "";
/*  39: 34 */       String gearWither = "";
/*  40: 35 */       String gearHarming = "";
/*  41: 36 */       String gearBlind = "";
/*  42: 37 */       String gearXPMultiplier = "";
/*  43: 38 */       String gearSpeed = "";
/*  44:    */       
/*  45: 40 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".armour", Double.valueOf(0.0D));
/*  46: 41 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".dodge", Double.valueOf(0.0D));
/*  47: 42 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".block", Double.valueOf(0.0D));
/*  48: 43 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".damage", Double.valueOf(0.0D));
/*  49: 44 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critChance", Double.valueOf(0.0D));
/*  50: 45 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critDamage", Double.valueOf(0.0D));
/*  51: 46 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".health", Double.valueOf(0.0D));
/*  52: 47 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".healthRegen", Double.valueOf(0.0D));
/*  53: 48 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".lifeSteal", Double.valueOf(0.0D));
/*  54: 49 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".reflect", Double.valueOf(0.0D));
/*  55: 50 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".fire", Double.valueOf(0.0D));
/*  56: 51 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".ice", Double.valueOf(0.0D));
/*  57: 52 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".poison", Double.valueOf(0.0D));
/*  58: 53 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".wither", Double.valueOf(0.0D));
/*  59: 54 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".harming", Double.valueOf(0.0D));
/*  60: 55 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".blind", Double.valueOf(0.0D));
/*  61: 56 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".xpMultiplier", Double.valueOf(0.0D));
/*  62: 57 */       ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".speed", Double.valueOf(0.0D));
/*  63:    */       
/*  64: 59 */       int a = this.PlayerDataConfig.getKeys(false).size() - 1;
/*  65:    */       do
/*  66:    */       {
/*  67: 60 */         int x = 0;
/*  68: 61 */         String key = this.PlayerDataConfig.getKeys(false).toString().split(",")[a].replaceAll("\\[", "").replaceAll("\\]", "").trim();
/*  69: 64 */         if (key != null)
/*  70:    */         {
/*  71: 65 */           if (this.gearStats.getSetBonusHead(player).equals(key)) {
/*  72: 66 */             x++;
/*  73:    */           }
/*  74: 69 */           if (this.gearStats.getSetBonusChest(player).equals(key)) {
/*  75: 70 */             x++;
/*  76:    */           }
/*  77: 73 */           if (this.gearStats.getSetBonusLegs(player).equals(key)) {
/*  78: 74 */             x++;
/*  79:    */           }
/*  80: 77 */           if (this.gearStats.getSetBonusBoots(player).equals(key)) {
/*  81: 78 */             x++;
/*  82:    */           }
/*  83: 81 */           if (this.gearStats.getSetBonusItemInHand(player).equals(key)) {
/*  84: 82 */             x++;
/*  85:    */           }
/*  86: 85 */           for (int b = this.PlayerDataConfig.getConfigurationSection(key).getKeys(false).size() - 1; (b >= 0) && (b <= this.PlayerDataConfig.getConfigurationSection(key).getKeys(false).size() - 1); b--) {
/*  87: 87 */             if (this.PlayerDataConfig.getConfigurationSection(key).getKeys(false).toString().split(",")[b].replaceAll("\\[", "").replaceAll("\\]", "").trim() != null)
/*  88:    */             {
/*  89: 88 */               String keyFromParentKey = this.PlayerDataConfig.getConfigurationSection(key).getKeys(false).toString().split(",")[b].replaceAll("\\[", "").replaceAll("\\]", "").trim();
/*  90:    */               
/*  91: 90 */               gearArmour = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".armour");
/*  92: 91 */               gearDodge = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".dodge");
/*  93: 92 */               gearBlock = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".block");
/*  94: 93 */               gearCritChance = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".critChance");
/*  95: 94 */               gearCritDamage = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".critDamage");
/*  96: 95 */               gearDamage = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".damage");
/*  97: 96 */               gearHealth = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".health");
/*  98: 97 */               gearHealthRegen = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".healthRegen");
/*  99: 98 */               gearLifeSteal = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".lifeSteal");
/* 100: 99 */               gearReflect = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".reflect");
/* 101:100 */               gearFire = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".fire");
/* 102:101 */               gearIce = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".ice");
/* 103:102 */               gearPoison = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".poison");
/* 104:103 */               gearWither = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".wither");
/* 105:104 */               gearHarming = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".harming");
/* 106:105 */               gearBlind = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".blind");
/* 107:106 */               gearXPMultiplier = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".xpMultiplier");
/* 108:107 */               gearSpeed = this.PlayerDataConfig.getString(key + "." + keyFromParentKey + ".movementSpeed");
/* 109:109 */               if (x >= Integer.parseInt(keyFromParentKey))
/* 110:    */               {
/* 111:110 */                 if (gearArmour != null)
/* 112:    */                 {
/* 113:111 */                   player.sendMessage("armour hashmap set to " + Integer.parseInt(gearArmour));
/* 114:112 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".armour", Double.valueOf(Double.parseDouble(gearArmour)));
/* 115:    */                 }
/* 116:    */                 else
/* 117:    */                 {
/* 118:114 */                   player.sendMessage("armour hashmap set to 0");
/* 119:115 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".armour", Double.valueOf(0.0D));
/* 120:    */                 }
/* 121:118 */                 if (gearDodge != null)
/* 122:    */                 {
/* 123:119 */                   player.sendMessage("dodge hashmap set to " + Integer.parseInt(gearDodge));
/* 124:120 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".dodge", Double.valueOf(Double.parseDouble(gearDodge)));
/* 125:    */                 }
/* 126:    */                 else
/* 127:    */                 {
/* 128:122 */                   player.sendMessage("dodge hashmap set to 0");
/* 129:123 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".dodge", Double.valueOf(0.0D));
/* 130:    */                 }
/* 131:126 */                 if (gearBlock != null)
/* 132:    */                 {
/* 133:127 */                   player.sendMessage("block hashmap set to " + Integer.parseInt(gearBlock));
/* 134:128 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".block", Double.valueOf(Double.parseDouble(gearBlock)));
/* 135:    */                 }
/* 136:    */                 else
/* 137:    */                 {
/* 138:130 */                   player.sendMessage("block hashmap set to 0");
/* 139:131 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".block", Double.valueOf(0.0D));
/* 140:    */                 }
/* 141:134 */                 if (gearDamage != null)
/* 142:    */                 {
/* 143:135 */                   player.sendMessage("damage hashmap set to " + Integer.parseInt(gearDamage));
/* 144:136 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".damage", Double.valueOf(Double.parseDouble(gearDamage)));
/* 145:    */                 }
/* 146:    */                 else
/* 147:    */                 {
/* 148:138 */                   player.sendMessage("damage hashmap set to 0");
/* 149:139 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".damage", Double.valueOf(0.0D));
/* 150:    */                 }
/* 151:142 */                 if (gearCritChance != null)
/* 152:    */                 {
/* 153:143 */                   player.sendMessage("critchance hashmap set to " + Integer.parseInt(gearCritChance));
/* 154:144 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critChance", Double.valueOf(Double.parseDouble(gearCritChance)));
/* 155:    */                 }
/* 156:    */                 else
/* 157:    */                 {
/* 158:146 */                   player.sendMessage("critchance hashmap set to 0");
/* 159:147 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critChance", Double.valueOf(0.0D));
/* 160:    */                 }
/* 161:150 */                 if (gearCritDamage != null)
/* 162:    */                 {
/* 163:151 */                   player.sendMessage("critdamage hashmap set to " + Integer.parseInt(gearCritDamage));
/* 164:152 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critDamage", Double.valueOf(Double.parseDouble(gearCritDamage)));
/* 165:    */                 }
/* 166:    */                 else
/* 167:    */                 {
/* 168:154 */                   player.sendMessage("critdamage hashmap set to 0");
/* 169:155 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".critDamage", Double.valueOf(0.0D));
/* 170:    */                 }
/* 171:158 */                 if (gearHealth != null)
/* 172:    */                 {
/* 173:159 */                   player.sendMessage("health hashmap set to " + Integer.parseInt(gearHealth));
/* 174:160 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".health", Double.valueOf(Double.parseDouble(gearHealth)));
/* 175:    */                 }
/* 176:    */                 else
/* 177:    */                 {
/* 178:163 */                   player.sendMessage("health hashmap set to 0");
/* 179:164 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".health", Double.valueOf(0.0D));
/* 180:    */                 }
/* 181:167 */                 if (gearHealthRegen != null)
/* 182:    */                 {
/* 183:168 */                   player.sendMessage("health regen hashmap set to " + Integer.parseInt(gearHealthRegen));
/* 184:169 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".healthRegen", Double.valueOf(Double.parseDouble(gearHealthRegen)));
/* 185:    */                 }
/* 186:    */                 else
/* 187:    */                 {
/* 188:172 */                   player.sendMessage("health regen hashmap set to 0");
/* 189:173 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".healthRegen", Double.valueOf(0.0D));
/* 190:    */                 }
/* 191:176 */                 if (gearLifeSteal != null)
/* 192:    */                 {
/* 193:177 */                   player.sendMessage("lifesteal hashmap set to " + Integer.parseInt(gearLifeSteal));
/* 194:178 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".lifeSteal", Double.valueOf(Double.parseDouble(gearLifeSteal)));
/* 195:    */                 }
/* 196:    */                 else
/* 197:    */                 {
/* 198:180 */                   player.sendMessage("lifesteal hashmap set to 0");
/* 199:181 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".lifeSteal", Double.valueOf(0.0D));
/* 200:    */                 }
/* 201:184 */                 if (gearReflect != null)
/* 202:    */                 {
/* 203:185 */                   player.sendMessage("reflect hashmap set to " + Integer.parseInt(gearReflect));
/* 204:186 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".reflect", Double.valueOf(Double.parseDouble(gearReflect)));
/* 205:    */                 }
/* 206:    */                 else
/* 207:    */                 {
/* 208:188 */                   player.sendMessage("reflect hashmap set to 0");
/* 209:189 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".reflect", Double.valueOf(0.0D));
/* 210:    */                 }
/* 211:192 */                 if (gearFire != null)
/* 212:    */                 {
/* 213:193 */                   player.sendMessage("fire hashmap set to " + Integer.parseInt(gearFire));
/* 214:194 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".fire", Double.valueOf(Double.parseDouble(gearFire)));
/* 215:    */                 }
/* 216:    */                 else
/* 217:    */                 {
/* 218:196 */                   player.sendMessage("fire hashmap set to 0");
/* 219:197 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".fire", Double.valueOf(0.0D));
/* 220:    */                 }
/* 221:200 */                 if (gearIce != null)
/* 222:    */                 {
/* 223:201 */                   player.sendMessage("ice hashmap set to " + Integer.parseInt(gearIce));
/* 224:202 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".ice", Double.valueOf(Double.parseDouble(gearIce)));
/* 225:    */                 }
/* 226:    */                 else
/* 227:    */                 {
/* 228:204 */                   player.sendMessage("ice hashmap set to 0");
/* 229:205 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".ice", Double.valueOf(0.0D));
/* 230:    */                 }
/* 231:208 */                 if (gearPoison != null)
/* 232:    */                 {
/* 233:209 */                   player.sendMessage("poison hashmap set to " + Integer.parseInt(gearPoison));
/* 234:210 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".poison", Double.valueOf(Double.parseDouble(gearPoison)));
/* 235:    */                 }
/* 236:    */                 else
/* 237:    */                 {
/* 238:212 */                   player.sendMessage("poison hashmap set to 0");
/* 239:213 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".poison", Double.valueOf(0.0D));
/* 240:    */                 }
/* 241:216 */                 if (gearWither != null)
/* 242:    */                 {
/* 243:217 */                   player.sendMessage("wither hashmap set to " + Integer.parseInt(gearWither));
/* 244:218 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".wither", Double.valueOf(Double.parseDouble(gearWither)));
/* 245:    */                 }
/* 246:    */                 else
/* 247:    */                 {
/* 248:220 */                   player.sendMessage("wither hashmap set to 0");
/* 249:221 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".wither", Double.valueOf(0.0D));
/* 250:    */                 }
/* 251:224 */                 if (gearHarming != null)
/* 252:    */                 {
/* 253:225 */                   player.sendMessage("harming hashmap set to " + Integer.parseInt(gearHarming));
/* 254:226 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".harming", Double.valueOf(Double.parseDouble(gearHarming)));
/* 255:    */                 }
/* 256:    */                 else
/* 257:    */                 {
/* 258:228 */                   player.sendMessage("harming hashmap set to 0");
/* 259:229 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".harming", Double.valueOf(0.0D));
/* 260:    */                 }
/* 261:232 */                 if (gearBlind != null)
/* 262:    */                 {
/* 263:233 */                   player.sendMessage("blind hashmap set to " + Integer.parseInt(gearBlind));
/* 264:234 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".blind", Double.valueOf(Double.parseDouble(gearBlind)));
/* 265:    */                 }
/* 266:    */                 else
/* 267:    */                 {
/* 268:236 */                   player.sendMessage("blind hashmap set to 0");
/* 269:237 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".blind", Double.valueOf(0.0D));
/* 270:    */                 }
/* 271:240 */                 if (gearXPMultiplier != null)
/* 272:    */                 {
/* 273:241 */                   player.sendMessage("xpMultiplier hashmap set to " + Integer.parseInt(gearXPMultiplier));
/* 274:242 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".xpMultiplier", Double.valueOf(Double.parseDouble(gearXPMultiplier)));
/* 275:    */                 }
/* 276:    */                 else
/* 277:    */                 {
/* 278:244 */                   player.sendMessage("xpMultiplier hashmap set to 0");
/* 279:245 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".xpMultiplier", Double.valueOf(0.0D));
/* 280:    */                 }
/* 281:248 */                 if (gearSpeed != null)
/* 282:    */                 {
/* 283:249 */                   player.sendMessage("speed hashmap set to " + Integer.parseInt(gearSpeed));
/* 284:250 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".speed", Double.valueOf(Double.parseDouble(gearSpeed)));
/* 285:    */                 }
/* 286:    */                 else
/* 287:    */                 {
/* 288:252 */                   player.sendMessage("speed hashmap set to 0");
/* 289:253 */                   ItemLoreStats.plugin.setBonusesModifiers.put(player.getName() + ".speed", Double.valueOf(0.0D));
/* 290:    */                 }
/* 291:    */               }
/* 292:    */             }
/* 293:    */           }
/* 294:    */         }
/* 295: 59 */         a--;
/* 296: 59 */         if (a < 0) {
/* 297:    */           break;
/* 298:    */         }
/* 299: 59 */       } while (a <= this.PlayerDataConfig.getKeys(false).size() - 1);
/* 300:    */     }
/* 301:    */     catch (Exception e)
/* 302:    */     {
/* 303:262 */       e.printStackTrace();
/* 304:263 */       System.out.println("*********** Failed to load set bonus for " + player.getName() + "! ***********");
/* 305:    */     }
/* 306:    */   }
/* 307:    */   
/* 308:    */   public double checkHashMapArmour(Player player)
/* 309:    */   {
/* 310:268 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".armour") != null) {
/* 311:269 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".armour")).doubleValue();
/* 312:    */     }
/* 313:271 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 314:272 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".armour")).doubleValue();
/* 315:    */   }
/* 316:    */   
/* 317:    */   public double checkHashMapDodge(Player player)
/* 318:    */   {
/* 319:277 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".dodge") != null) {
/* 320:278 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".dodge")).doubleValue();
/* 321:    */     }
/* 322:280 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 323:281 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".dodge")).doubleValue();
/* 324:    */   }
/* 325:    */   
/* 326:    */   public double checkHashMapBlock(Player player)
/* 327:    */   {
/* 328:286 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".block") != null) {
/* 329:287 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".block")).doubleValue();
/* 330:    */     }
/* 331:289 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 332:290 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".block")).doubleValue();
/* 333:    */   }
/* 334:    */   
/* 335:    */   public double checkHashMapDamage(Player player)
/* 336:    */   {
/* 337:295 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".damage") != null) {
/* 338:296 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".damage")).doubleValue();
/* 339:    */     }
/* 340:298 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 341:299 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".damage")).doubleValue();
/* 342:    */   }
/* 343:    */   
/* 344:    */   public double checkHashMapCritChance(Player player)
/* 345:    */   {
/* 346:304 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critChance") != null) {
/* 347:305 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critChance")).doubleValue();
/* 348:    */     }
/* 349:307 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 350:308 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critChance")).doubleValue();
/* 351:    */   }
/* 352:    */   
/* 353:    */   public double checkHashMapCritDamage(Player player)
/* 354:    */   {
/* 355:313 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critDamage") != null) {
/* 356:314 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critDamage")).doubleValue();
/* 357:    */     }
/* 358:316 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 359:317 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".critDamage")).doubleValue();
/* 360:    */   }
/* 361:    */   
/* 362:    */   public double checkHashMapHealth(Player player)
/* 363:    */   {
/* 364:322 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".health") != null) {
/* 365:323 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".health")).doubleValue();
/* 366:    */     }
/* 367:325 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 368:326 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".health")).doubleValue();
/* 369:    */   }
/* 370:    */   
/* 371:    */   public double checkHashMapHealthRegen(Player player)
/* 372:    */   {
/* 373:331 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".healthRegen") != null) {
/* 374:332 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".healthRegen")).doubleValue();
/* 375:    */     }
/* 376:334 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 377:335 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".healthRegen")).doubleValue();
/* 378:    */   }
/* 379:    */   
/* 380:    */   public double checkHashMapLifeSteal(Player player)
/* 381:    */   {
/* 382:340 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".lifeSteal") != null) {
/* 383:341 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".lifeSteal")).doubleValue();
/* 384:    */     }
/* 385:343 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 386:344 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".lifeSteal")).doubleValue();
/* 387:    */   }
/* 388:    */   
/* 389:    */   public double checkHashMapReflect(Player player)
/* 390:    */   {
/* 391:349 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".reflect") != null) {
/* 392:350 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".reflect")).doubleValue();
/* 393:    */     }
/* 394:352 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 395:353 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".reflect")).doubleValue();
/* 396:    */   }
/* 397:    */   
/* 398:    */   public double checkHashMapFire(Player player)
/* 399:    */   {
/* 400:358 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".fire") != null) {
/* 401:359 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".fire")).doubleValue();
/* 402:    */     }
/* 403:361 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 404:362 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".fire")).doubleValue();
/* 405:    */   }
/* 406:    */   
/* 407:    */   public double checkHashMapIce(Player player)
/* 408:    */   {
/* 409:367 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".ice") != null) {
/* 410:368 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".ice")).doubleValue();
/* 411:    */     }
/* 412:370 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 413:371 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".ice")).doubleValue();
/* 414:    */   }
/* 415:    */   
/* 416:    */   public double checkHashMapPoison(Player player)
/* 417:    */   {
/* 418:376 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".poison") != null) {
/* 419:377 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".poison")).doubleValue();
/* 420:    */     }
/* 421:379 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 422:380 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".poison")).doubleValue();
/* 423:    */   }
/* 424:    */   
/* 425:    */   public double checkHashMapWither(Player player)
/* 426:    */   {
/* 427:385 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".wither") != null) {
/* 428:386 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".wither")).doubleValue();
/* 429:    */     }
/* 430:388 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 431:389 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".wither")).doubleValue();
/* 432:    */   }
/* 433:    */   
/* 434:    */   public double checkHashMapHarming(Player player)
/* 435:    */   {
/* 436:394 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".harming") != null) {
/* 437:395 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".harming")).doubleValue();
/* 438:    */     }
/* 439:397 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 440:398 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".harming")).doubleValue();
/* 441:    */   }
/* 442:    */   
/* 443:    */   public double checkHashMapBlind(Player player)
/* 444:    */   {
/* 445:403 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".blind") != null) {
/* 446:404 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".blind")).doubleValue();
/* 447:    */     }
/* 448:406 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 449:407 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".blind")).doubleValue();
/* 450:    */   }
/* 451:    */   
/* 452:    */   public double checkHashMapXPMultiplier(Player player)
/* 453:    */   {
/* 454:412 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".xpMultiplier") != null) {
/* 455:413 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".xpMultiplier")).doubleValue();
/* 456:    */     }
/* 457:415 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 458:416 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".xpMultiplier")).doubleValue();
/* 459:    */   }
/* 460:    */   
/* 461:    */   public double checkHashMapSpeed(Player player)
/* 462:    */   {
/* 463:421 */     if (ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".speed") != null) {
/* 464:422 */       return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".speed")).doubleValue();
/* 465:    */     }
/* 466:424 */     ItemLoreStats.plugin.setBonuses.updateSetBonus(player);
/* 467:425 */     return ((Double)ItemLoreStats.plugin.setBonusesModifiers.get(player.getName() + ".speed")).doubleValue();
/* 468:    */   }
/* 469:    */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.SetBonuses
 * JD-Core Version:    0.7.0.1
 */