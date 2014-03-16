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
/*  18: 18 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))
/*  19:    */     {
/*  20: 19 */       if (!(event.getEntity() instanceof Player)) {
/*  21: 19 */         return;
/*  22:    */       }
/*  23: 21 */       Player player = (Player)event.getEntity();
/*  24: 23 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/*  25:    */       {
/*  26: 25 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.block_explosion") == 0) {
/*  27: 25 */           return;
/*  28:    */         }
/*  29: 27 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.block_explosion");
/*  30: 29 */         if (newDamage > player.getHealth()) {
/*  31: 30 */           event.setDamage(player.getHealth());
/*  32:    */         } else {
/*  33: 32 */           event.setDamage(newDamage);
/*  34:    */         }
/*  35:    */       }
/*  36:    */     }
/*  37:    */   }
/*  38:    */   
/*  39:    */   @EventHandler
/*  40:    */   public void onCactus(EntityDamageEvent event)
/*  41:    */   {
/*  42: 40 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.CONTACT))
/*  43:    */     {
/*  44: 41 */       if (!(event.getEntity() instanceof Player)) {
/*  45: 41 */         return;
/*  46:    */       }
/*  47: 43 */       Player player = (Player)event.getEntity();
/*  48: 45 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/*  49:    */       {
/*  50: 47 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.cactus") == 0) {
/*  51: 47 */           return;
/*  52:    */         }
/*  53: 49 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.cactus");
/*  54: 51 */         if (newDamage > player.getHealth()) {
/*  55: 52 */           event.setDamage(player.getHealth());
/*  56:    */         } else {
/*  57: 54 */           event.setDamage(newDamage);
/*  58:    */         }
/*  59:    */       }
/*  60:    */     }
/*  61:    */   }
/*  62:    */   
/*  63:    */   @EventHandler
/*  64:    */   public void onDrowning(EntityDamageEvent event)
/*  65:    */   {
/*  66: 62 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.DROWNING))
/*  67:    */     {
/*  68: 63 */       if (!(event.getEntity() instanceof Player)) {
/*  69: 63 */         return;
/*  70:    */       }
/*  71: 65 */       Player player = (Player)event.getEntity();
/*  72: 67 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/*  73:    */       {
/*  74: 69 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.drowning") == 0) {
/*  75: 69 */           return;
/*  76:    */         }
/*  77: 71 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.drowning");
/*  78: 73 */         if (newDamage > player.getHealth()) {
/*  79: 74 */           event.setDamage(player.getHealth());
/*  80:    */         } else {
/*  81: 76 */           event.setDamage(newDamage);
/*  82:    */         }
/*  83:    */       }
/*  84:    */     }
/*  85:    */   }
/*  86:    */   
/*  87:    */   @EventHandler
/*  88:    */   public void onEntityExplosion(EntityDamageEvent event)
/*  89:    */   {
/*  90: 84 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION))
/*  91:    */     {
/*  92: 85 */       if (!(event.getEntity() instanceof Player)) {
/*  93: 85 */         return;
/*  94:    */       }
/*  95: 87 */       Player player = (Player)event.getEntity();
/*  96: 89 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/*  97:    */       {
/*  98: 91 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.entity_explosion") == 0) {
/*  99: 91 */           return;
/* 100:    */         }
/* 101: 93 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.entity_explosion");
/* 102: 95 */         if (newDamage > player.getHealth()) {
/* 103: 96 */           event.setDamage(player.getHealth());
/* 104:    */         } else {
/* 105: 98 */           event.setDamage(newDamage);
/* 106:    */         }
/* 107:    */       }
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   @EventHandler
/* 112:    */   public void onFallDamage(EntityDamageEvent event)
/* 113:    */   {
/* 114:106 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL))
/* 115:    */     {
/* 116:107 */       if (!(event.getEntity() instanceof Player)) {
/* 117:107 */         return;
/* 118:    */       }
/* 119:109 */       Player player = (Player)event.getEntity();
/* 120:111 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 121:    */       {
/* 122:113 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fall") == 0) {
/* 123:113 */           return;
/* 124:    */         }
/* 125:115 */         double fallHeight = Double.parseDouble(String.valueOf(player.getFallDistance()).split("\\.")[0]) - 2.0D;
/* 126:    */         
/* 127:117 */         double percentageOfHealth = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fall");
/* 128:    */         
/* 129:119 */         double multiplyDamage = Double.parseDouble(String.valueOf(fallHeight)) * percentageOfHealth;
/* 130:121 */         if (multiplyDamage > player.getHealth()) {
/* 131:122 */           event.setDamage(player.getHealth());
/* 132:    */         } else {
/* 133:124 */           event.setDamage(multiplyDamage);
/* 134:    */         }
/* 135:    */       }
/* 136:    */     }
/* 137:    */   }
/* 138:    */   
/* 139:    */   @EventHandler
/* 140:    */   public void onFire(EntityDamageEvent event)
/* 141:    */   {
/* 142:132 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE))
/* 143:    */     {
/* 144:133 */       if (!(event.getEntity() instanceof Player)) {
/* 145:133 */         return;
/* 146:    */       }
/* 147:135 */       Player player = (Player)event.getEntity();
/* 148:137 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 149:    */       {
/* 150:139 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fire") == 0) {
/* 151:139 */           return;
/* 152:    */         }
/* 153:141 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fire");
/* 154:143 */         if (newDamage > player.getHealth()) {
/* 155:144 */           event.setDamage(player.getHealth());
/* 156:    */         } else {
/* 157:146 */           event.setDamage(newDamage);
/* 158:    */         }
/* 159:    */       }
/* 160:    */     }
/* 161:    */   }
/* 162:    */   
/* 163:    */   @EventHandler
/* 164:    */   public void onFireTick(EntityDamageEvent event)
/* 165:    */   {
/* 166:154 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
/* 167:    */     {
/* 168:155 */       if (!(event.getEntity() instanceof Player)) {
/* 169:155 */         return;
/* 170:    */       }
/* 171:157 */       Player player = (Player)event.getEntity();
/* 172:159 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 173:    */       {
/* 174:161 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fire_tick") == 0) {
/* 175:161 */           return;
/* 176:    */         }
/* 177:163 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.fire_tick");
/* 178:165 */         if (newDamage > player.getHealth()) {
/* 179:166 */           event.setDamage(player.getHealth());
/* 180:    */         } else {
/* 181:168 */           event.setDamage(newDamage);
/* 182:    */         }
/* 183:    */       }
/* 184:    */     }
/* 185:    */   }
/* 186:    */   
/* 187:    */   @EventHandler
/* 188:    */   public void onLavaBurn(EntityDamageEvent event)
/* 189:    */   {
/* 190:176 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.LAVA))
/* 191:    */     {
/* 192:177 */       if (!(event.getEntity() instanceof Player)) {
/* 193:177 */         return;
/* 194:    */       }
/* 195:179 */       Player player = (Player)event.getEntity();
/* 196:181 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 197:    */       {
/* 198:183 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.lava") == 0) {
/* 199:183 */           return;
/* 200:    */         }
/* 201:185 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.lava");
/* 202:187 */         if (newDamage > player.getHealth()) {
/* 203:188 */           event.setDamage(player.getHealth());
/* 204:    */         } else {
/* 205:190 */           event.setDamage(newDamage);
/* 206:    */         }
/* 207:    */       }
/* 208:    */     }
/* 209:    */   }
/* 210:    */   
/* 211:    */   @EventHandler
/* 212:    */   public void onLightningStrike(EntityDamageEvent event)
/* 213:    */   {
/* 214:198 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING))
/* 215:    */     {
/* 216:199 */       if (!(event.getEntity() instanceof Player)) {
/* 217:199 */         return;
/* 218:    */       }
/* 219:201 */       Player player = (Player)event.getEntity();
/* 220:203 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 221:    */       {
/* 222:205 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.lightning") == 0) {
/* 223:205 */           return;
/* 224:    */         }
/* 225:207 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.lightning");
/* 226:209 */         if (newDamage > player.getHealth()) {
/* 227:210 */           event.setDamage(player.getHealth());
/* 228:    */         } else {
/* 229:212 */           event.setDamage(newDamage);
/* 230:    */         }
/* 231:    */       }
/* 232:    */     }
/* 233:    */   }
/* 234:    */   
/* 235:    */   @EventHandler
/* 236:    */   public void onMagic(EntityDamageEvent event)
/* 237:    */   {
/* 238:220 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.MAGIC))
/* 239:    */     {
/* 240:221 */       if (!(event.getEntity() instanceof Player)) {
/* 241:221 */         return;
/* 242:    */       }
/* 243:223 */       Player player = (Player)event.getEntity();
/* 244:225 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 245:    */       {
/* 246:227 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.magic") == 0) {
/* 247:227 */           return;
/* 248:    */         }
/* 249:229 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.magic");
/* 250:231 */         if (newDamage > player.getHealth()) {
/* 251:232 */           event.setDamage(player.getHealth());
/* 252:    */         } else {
/* 253:234 */           event.setDamage(newDamage);
/* 254:    */         }
/* 255:    */       }
/* 256:    */     }
/* 257:    */   }
/* 258:    */   
/* 259:    */   @EventHandler
/* 260:    */   public void onPoison(EntityDamageEvent event)
/* 261:    */   {
/* 262:242 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.POISON))
/* 263:    */     {
/* 264:243 */       if (!(event.getEntity() instanceof Player)) {
/* 265:243 */         return;
/* 266:    */       }
/* 267:245 */       Player player = (Player)event.getEntity();
/* 268:247 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 269:    */       {
/* 270:249 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.poison") == 0) {
/* 271:249 */           return;
/* 272:    */         }
/* 273:251 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.poison");
/* 274:253 */         if (newDamage > player.getHealth()) {
/* 275:254 */           event.setDamage(player.getHealth());
/* 276:    */         } else {
/* 277:256 */           event.setDamage(newDamage);
/* 278:    */         }
/* 279:    */       }
/* 280:    */     }
/* 281:    */   }
/* 282:    */   
/* 283:    */   @EventHandler
/* 284:    */   public void onStarvation(EntityDamageEvent event)
/* 285:    */   {
/* 286:264 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.STARVATION))
/* 287:    */     {
/* 288:265 */       if (!(event.getEntity() instanceof Player)) {
/* 289:265 */         return;
/* 290:    */       }
/* 291:267 */       Player player = (Player)event.getEntity();
/* 292:269 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 293:    */       {
/* 294:271 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.starvation") == 0) {
/* 295:271 */           return;
/* 296:    */         }
/* 297:273 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.starvation");
/* 298:275 */         if (newDamage > player.getHealth()) {
/* 299:276 */           event.setDamage(player.getHealth());
/* 300:    */         } else {
/* 301:278 */           event.setDamage(newDamage);
/* 302:    */         }
/* 303:    */       }
/* 304:    */     }
/* 305:    */   }
/* 306:    */   
/* 307:    */   @EventHandler
/* 308:    */   public void onSuffocation(EntityDamageEvent event)
/* 309:    */   {
/* 310:286 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.SUFFOCATION))
/* 311:    */     {
/* 312:287 */       if (!(event.getEntity() instanceof Player)) {
/* 313:287 */         return;
/* 314:    */       }
/* 315:289 */       Player player = (Player)event.getEntity();
/* 316:291 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 317:    */       {
/* 318:293 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.suffocation") == 0) {
/* 319:293 */           return;
/* 320:    */         }
/* 321:295 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.suffocation");
/* 322:297 */         if (newDamage > player.getHealth()) {
/* 323:298 */           event.setDamage(player.getHealth());
/* 324:    */         } else {
/* 325:300 */           event.setDamage(newDamage);
/* 326:    */         }
/* 327:    */       }
/* 328:    */     }
/* 329:    */   }
/* 330:    */   
/* 331:    */   @EventHandler
/* 332:    */   public void onSuicide(EntityDamageEvent event)
/* 333:    */   {
/* 334:308 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.SUICIDE))
/* 335:    */     {
/* 336:309 */       if (!(event.getEntity() instanceof Player)) {
/* 337:309 */         return;
/* 338:    */       }
/* 339:311 */       Player player = (Player)event.getEntity();
/* 340:313 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 341:    */       {
/* 342:315 */         double newDamage = player.getMaxHealth();
/* 343:317 */         if (newDamage > player.getHealth()) {
/* 344:318 */           event.setDamage(player.getHealth());
/* 345:    */         } else {
/* 346:320 */           event.setDamage(newDamage);
/* 347:    */         }
/* 348:    */       }
/* 349:    */     }
/* 350:    */   }
/* 351:    */   
/* 352:    */   @EventHandler
/* 353:    */   public void onThorns(EntityDamageEvent event)
/* 354:    */   {
/* 355:328 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.THORNS))
/* 356:    */     {
/* 357:329 */       if (!(event.getEntity() instanceof Player)) {
/* 358:329 */         return;
/* 359:    */       }
/* 360:331 */       Player player = (Player)event.getEntity();
/* 361:333 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 362:    */       {
/* 363:335 */         if (ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.thorns") == 0) {
/* 364:335 */           return;
/* 365:    */         }
/* 366:337 */         double newDamage = player.getMaxHealth() / 100.0D * ItemLoreStats.plugin.getConfig().getInt("environmentalDamage.thorns");
/* 367:339 */         if (newDamage > player.getHealth()) {
/* 368:340 */           event.setDamage(player.getHealth());
/* 369:    */         } else {
/* 370:342 */           event.setDamage(newDamage);
/* 371:    */         }
/* 372:    */       }
/* 373:    */     }
/* 374:    */   }
/* 375:    */   
/* 376:    */   @EventHandler
/* 377:    */   public void onVoid(EntityDamageEvent event)
/* 378:    */   {
/* 379:350 */     if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID))
/* 380:    */     {
/* 381:351 */       if (!(event.getEntity() instanceof Player)) {
/* 382:351 */         return;
/* 383:    */       }
/* 384:353 */       Player player = (Player)event.getEntity();
/* 385:355 */       if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 386:    */       {
/* 387:357 */         double newDamage = player.getMaxHealth();
/* 388:359 */         if (newDamage > player.getHealth()) {
/* 389:360 */           event.setDamage(player.getHealth());
/* 390:    */         } else {
/* 391:362 */           event.setDamage(newDamage);
/* 392:    */         }
/* 393:    */       }
/* 394:    */     }
/* 395:    */   }
/* 396:    */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.EnvironmentalDamage
 * JD-Core Version:    0.7.0.1
 */