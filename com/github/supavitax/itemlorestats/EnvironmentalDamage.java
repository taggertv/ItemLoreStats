/*   1:    */ package com.github.supavitax.itemlorestats;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import org.bukkit.World;
/*   5:    */ import org.bukkit.configuration.file.FileConfiguration;
/*   6:    */ import org.bukkit.entity.Player;
/*   7:    */ import org.bukkit.event.EventHandler;
/*   8:    */ import org.bukkit.event.Listener;
/*   9:    */ import org.bukkit.event.entity.EntityDamageEvent;
/*  10:    */ import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
/*  11:    */ 
/*  12:    */ public class EnvironmentalDamage
/*  13:    */   implements Listener
/*  14:    */ {
/*  15:    */   @EventHandler
/*  16:    */   public void onBlockExplosion(EntityDamageEvent event)
/*  17:    */   {
/*  18: 13 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))
/*  19:    */     {
/*  20: 14 */       if (!(event.getEntity() instanceof Player)) {
/*  21: 14 */         return;
/*  22:    */       }
/*  23: 16 */       Player player = (Player)event.getEntity();
/*  24: 18 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/*  25:    */       {
/*  26: 20 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.block_explosion") == 0) {
/*  27: 20 */           return;
/*  28:    */         }
/*  29: 22 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.block_explosion");
/*  30: 24 */         if (newDamage > player.getHealth()) {
/*  31: 25 */           event.setDamage(player.getHealth());
/*  32:    */         } else {
/*  33: 27 */           event.setDamage(newDamage);
/*  34:    */         }
/*  35:    */       }
/*  36:    */     }
/*  37:    */   }
/*  38:    */   
/*  39:    */   @EventHandler
/*  40:    */   public void onCactus(EntityDamageEvent event)
/*  41:    */   {
/*  42: 35 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.CONTACT))
/*  43:    */     {
/*  44: 36 */       if (!(event.getEntity() instanceof Player)) {
/*  45: 36 */         return;
/*  46:    */       }
/*  47: 38 */       Player player = (Player)event.getEntity();
/*  48: 40 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/*  49:    */       {
/*  50: 42 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.cactus") == 0) {
/*  51: 42 */           return;
/*  52:    */         }
/*  53: 44 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.cactus");
/*  54: 46 */         if (newDamage > player.getHealth()) {
/*  55: 47 */           event.setDamage(player.getHealth());
/*  56:    */         } else {
/*  57: 49 */           event.setDamage(newDamage);
/*  58:    */         }
/*  59:    */       }
/*  60:    */     }
/*  61:    */   }
/*  62:    */   
/*  63:    */   @EventHandler
/*  64:    */   public void onDrowning(EntityDamageEvent event)
/*  65:    */   {
/*  66: 57 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.DROWNING))
/*  67:    */     {
/*  68: 58 */       if (!(event.getEntity() instanceof Player)) {
/*  69: 58 */         return;
/*  70:    */       }
/*  71: 60 */       Player player = (Player)event.getEntity();
/*  72: 62 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/*  73:    */       {
/*  74: 64 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.drowning") == 0) {
/*  75: 64 */           return;
/*  76:    */         }
/*  77: 66 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.drowning");
/*  78: 68 */         if (newDamage > player.getHealth()) {
/*  79: 69 */           event.setDamage(player.getHealth());
/*  80:    */         } else {
/*  81: 71 */           event.setDamage(newDamage);
/*  82:    */         }
/*  83:    */       }
/*  84:    */     }
/*  85:    */   }
/*  86:    */   
/*  87:    */   @EventHandler
/*  88:    */   public void onEntityExplosion(EntityDamageEvent event)
/*  89:    */   {
/*  90: 79 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION))
/*  91:    */     {
/*  92: 80 */       if (!(event.getEntity() instanceof Player)) {
/*  93: 80 */         return;
/*  94:    */       }
/*  95: 82 */       Player player = (Player)event.getEntity();
/*  96: 84 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/*  97:    */       {
/*  98: 86 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.entity_explosion") == 0) {
/*  99: 86 */           return;
/* 100:    */         }
/* 101: 88 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.entity_explosion");
/* 102: 90 */         if (newDamage > player.getHealth()) {
/* 103: 91 */           event.setDamage(player.getHealth());
/* 104:    */         } else {
/* 105: 93 */           event.setDamage(newDamage);
/* 106:    */         }
/* 107:    */       }
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   @EventHandler
/* 112:    */   public void onFallDamage(EntityDamageEvent event)
/* 113:    */   {
/* 114:101 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL))
/* 115:    */     {
/* 116:102 */       if (!(event.getEntity() instanceof Player)) {
/* 117:102 */         return;
/* 118:    */       }
/* 119:104 */       Player player = (Player)event.getEntity();
/* 120:106 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 121:    */       {
/* 122:108 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fall") == 0) {
/* 123:108 */           return;
/* 124:    */         }
/* 125:110 */         double fallHeight = Double.parseDouble(String.valueOf(player.getFallDistance()).split("\\.")[0]) - 2.0D;
/* 126:    */         
/* 127:112 */         double percentageOfHealth = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fall");
/* 128:    */         
/* 129:114 */         double multiplyDamage = Double.parseDouble(String.valueOf(fallHeight)) * percentageOfHealth;
/* 130:116 */         if (multiplyDamage > player.getHealth()) {
/* 131:117 */           event.setDamage(player.getHealth());
/* 132:    */         } else {
/* 133:119 */           event.setDamage(multiplyDamage);
/* 134:    */         }
/* 135:    */       }
/* 136:    */     }
/* 137:    */   }
/* 138:    */   
/* 139:    */   @EventHandler
/* 140:    */   public void onFire(EntityDamageEvent event)
/* 141:    */   {
/* 142:127 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE))
/* 143:    */     {
/* 144:128 */       if (!(event.getEntity() instanceof Player)) {
/* 145:128 */         return;
/* 146:    */       }
/* 147:130 */       Player player = (Player)event.getEntity();
/* 148:132 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 149:    */       {
/* 150:134 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fire") == 0) {
/* 151:134 */           return;
/* 152:    */         }
/* 153:136 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fire");
/* 154:138 */         if (newDamage > player.getHealth()) {
/* 155:139 */           event.setDamage(player.getHealth());
/* 156:    */         } else {
/* 157:141 */           event.setDamage(newDamage);
/* 158:    */         }
/* 159:    */       }
/* 160:    */     }
/* 161:    */   }
/* 162:    */   
/* 163:    */   @EventHandler
/* 164:    */   public void onFireTick(EntityDamageEvent event)
/* 165:    */   {
/* 166:149 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
/* 167:    */     {
/* 168:150 */       if (!(event.getEntity() instanceof Player)) {
/* 169:150 */         return;
/* 170:    */       }
/* 171:152 */       Player player = (Player)event.getEntity();
/* 172:154 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 173:    */       {
/* 174:156 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fire_tick") == 0) {
/* 175:156 */           return;
/* 176:    */         }
/* 177:158 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fire_tick");
/* 178:160 */         if (newDamage > player.getHealth()) {
/* 179:161 */           event.setDamage(player.getHealth());
/* 180:    */         } else {
/* 181:163 */           event.setDamage(newDamage);
/* 182:    */         }
/* 183:    */       }
/* 184:    */     }
/* 185:    */   }
/* 186:    */   
/* 187:    */   @EventHandler
/* 188:    */   public void onLavaBurn(EntityDamageEvent event)
/* 189:    */   {
/* 190:171 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.LAVA))
/* 191:    */     {
/* 192:172 */       if (!(event.getEntity() instanceof Player)) {
/* 193:172 */         return;
/* 194:    */       }
/* 195:174 */       Player player = (Player)event.getEntity();
/* 196:176 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 197:    */       {
/* 198:178 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.lava") == 0) {
/* 199:178 */           return;
/* 200:    */         }
/* 201:180 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.lava");
/* 202:182 */         if (newDamage > player.getHealth()) {
/* 203:183 */           event.setDamage(player.getHealth());
/* 204:    */         } else {
/* 205:185 */           event.setDamage(newDamage);
/* 206:    */         }
/* 207:    */       }
/* 208:    */     }
/* 209:    */   }
/* 210:    */   
/* 211:    */   @EventHandler
/* 212:    */   public void onLightningStrike(EntityDamageEvent event)
/* 213:    */   {
/* 214:193 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING))
/* 215:    */     {
/* 216:194 */       if (!(event.getEntity() instanceof Player)) {
/* 217:194 */         return;
/* 218:    */       }
/* 219:196 */       Player player = (Player)event.getEntity();
/* 220:198 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 221:    */       {
/* 222:200 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.lightning") == 0) {
/* 223:200 */           return;
/* 224:    */         }
/* 225:202 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.lightning");
/* 226:204 */         if (newDamage > player.getHealth()) {
/* 227:205 */           event.setDamage(player.getHealth());
/* 228:    */         } else {
/* 229:207 */           event.setDamage(newDamage);
/* 230:    */         }
/* 231:    */       }
/* 232:    */     }
/* 233:    */   }
/* 234:    */   
/* 235:    */   @EventHandler
/* 236:    */   public void onMagic(EntityDamageEvent event)
/* 237:    */   {
/* 238:215 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.MAGIC))
/* 239:    */     {
/* 240:216 */       if (!(event.getEntity() instanceof Player)) {
/* 241:216 */         return;
/* 242:    */       }
/* 243:218 */       Player player = (Player)event.getEntity();
/* 244:220 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 245:    */       {
/* 246:222 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.magic") == 0) {
/* 247:222 */           return;
/* 248:    */         }
/* 249:224 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.magic");
/* 250:226 */         if (newDamage > player.getHealth()) {
/* 251:227 */           event.setDamage(player.getHealth());
/* 252:    */         } else {
/* 253:229 */           event.setDamage(newDamage);
/* 254:    */         }
/* 255:    */       }
/* 256:    */     }
/* 257:    */   }
/* 258:    */   
/* 259:    */   @EventHandler
/* 260:    */   public void onPoison(EntityDamageEvent event)
/* 261:    */   {
/* 262:237 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.POISON))
/* 263:    */     {
/* 264:238 */       if (!(event.getEntity() instanceof Player)) {
/* 265:238 */         return;
/* 266:    */       }
/* 267:240 */       Player player = (Player)event.getEntity();
/* 268:242 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 269:    */       {
/* 270:244 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.poison") == 0) {
/* 271:244 */           return;
/* 272:    */         }
/* 273:246 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.poison");
/* 274:248 */         if (newDamage > player.getHealth()) {
/* 275:249 */           event.setDamage(player.getHealth());
/* 276:    */         } else {
/* 277:251 */           event.setDamage(newDamage);
/* 278:    */         }
/* 279:    */       }
/* 280:    */     }
/* 281:    */   }
/* 282:    */   
/* 283:    */   @EventHandler
/* 284:    */   public void onStarvation(EntityDamageEvent event)
/* 285:    */   {
/* 286:259 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.STARVATION))
/* 287:    */     {
/* 288:260 */       if (!(event.getEntity() instanceof Player)) {
/* 289:260 */         return;
/* 290:    */       }
/* 291:262 */       Player player = (Player)event.getEntity();
/* 292:264 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 293:    */       {
/* 294:266 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.starvation") == 0) {
/* 295:266 */           return;
/* 296:    */         }
/* 297:268 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.starvation");
/* 298:270 */         if (newDamage > player.getHealth()) {
/* 299:271 */           event.setDamage(player.getHealth());
/* 300:    */         } else {
/* 301:273 */           event.setDamage(newDamage);
/* 302:    */         }
/* 303:    */       }
/* 304:    */     }
/* 305:    */   }
/* 306:    */   
/* 307:    */   @EventHandler
/* 308:    */   public void onSuffocation(EntityDamageEvent event)
/* 309:    */   {
/* 310:281 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.SUFFOCATION))
/* 311:    */     {
/* 312:282 */       if (!(event.getEntity() instanceof Player)) {
/* 313:282 */         return;
/* 314:    */       }
/* 315:284 */       Player player = (Player)event.getEntity();
/* 316:286 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 317:    */       {
/* 318:288 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.suffocation") == 0) {
/* 319:288 */           return;
/* 320:    */         }
/* 321:290 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.suffocation");
/* 322:292 */         if (newDamage > player.getHealth()) {
/* 323:293 */           event.setDamage(player.getHealth());
/* 324:    */         } else {
/* 325:295 */           event.setDamage(newDamage);
/* 326:    */         }
/* 327:    */       }
/* 328:    */     }
/* 329:    */   }
/* 330:    */   
/* 331:    */   @EventHandler
/* 332:    */   public void onSuicide(EntityDamageEvent event)
/* 333:    */   {
/* 334:303 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.SUICIDE))
/* 335:    */     {
/* 336:304 */       if (!(event.getEntity() instanceof Player)) {
/* 337:304 */         return;
/* 338:    */       }
/* 339:306 */       Player player = (Player)event.getEntity();
/* 340:308 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 341:    */       {
/* 342:310 */         double newDamage = player.getMaxHealth();
/* 343:312 */         if (newDamage > player.getHealth()) {
/* 344:313 */           event.setDamage(player.getHealth());
/* 345:    */         } else {
/* 346:315 */           event.setDamage(newDamage);
/* 347:    */         }
/* 348:    */       }
/* 349:    */     }
/* 350:    */   }
/* 351:    */   
/* 352:    */   @EventHandler
/* 353:    */   public void onThorns(EntityDamageEvent event)
/* 354:    */   {
/* 355:323 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.THORNS))
/* 356:    */     {
/* 357:324 */       if (!(event.getEntity() instanceof Player)) {
/* 358:324 */         return;
/* 359:    */       }
/* 360:326 */       Player player = (Player)event.getEntity();
/* 361:328 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 362:    */       {
/* 363:330 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.thorns") == 0) {
/* 364:330 */           return;
/* 365:    */         }
/* 366:332 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.thorns");
/* 367:334 */         if (newDamage > player.getHealth()) {
/* 368:335 */           event.setDamage(player.getHealth());
/* 369:    */         } else {
/* 370:337 */           event.setDamage(newDamage);
/* 371:    */         }
/* 372:    */       }
/* 373:    */     }
/* 374:    */   }
/* 375:    */   
/* 376:    */   @EventHandler
/* 377:    */   public void onVoid(EntityDamageEvent event)
/* 378:    */   {
/* 379:345 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID))
/* 380:    */     {
/* 381:346 */       if (!(event.getEntity() instanceof Player)) {
/* 382:346 */         return;
/* 383:    */       }
/* 384:348 */       Player player = (Player)event.getEntity();
/* 385:350 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 386:    */       {
/* 387:352 */         double newDamage = player.getMaxHealth();
/* 388:354 */         if (newDamage > player.getHealth()) {
/* 389:355 */           event.setDamage(player.getHealth());
/* 390:    */         } else {
/* 391:357 */           event.setDamage(newDamage);
/* 392:    */         }
/* 393:    */       }
/* 394:    */     }
/* 395:    */   }
/* 396:    */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.EnvironmentalDamage
 * JD-Core Version:    0.7.0.1
 */