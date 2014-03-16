/*    1:     */ package com.github.supavitax.itemlorestats;
/*    2:     */ 
/*    3:     */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*    4:     */ import com.github.supavitax.itemlorestats.Util.Util_WorldGuard;
/*    5:     */ import java.io.File;
/*    6:     */ import java.io.PrintStream;
/*    7:     */ import java.text.DecimalFormat;
/*    8:     */ import java.text.NumberFormat;
/*    9:     */ import java.util.List;
/*   10:     */ import java.util.Locale;
/*   11:     */ import java.util.Random;
/*   12:     */ import org.bukkit.ChatColor;
/*   13:     */ import org.bukkit.Effect;
/*   14:     */ import org.bukkit.Material;
/*   15:     */ import org.bukkit.World;
/*   16:     */ import org.bukkit.configuration.file.FileConfiguration;
/*   17:     */ import org.bukkit.configuration.file.YamlConfiguration;
/*   18:     */ import org.bukkit.enchantments.Enchantment;
/*   19:     */ import org.bukkit.entity.Arrow;
/*   20:     */ import org.bukkit.entity.Entity;
/*   21:     */ import org.bukkit.entity.EntityType;
/*   22:     */ import org.bukkit.entity.ItemFrame;
/*   23:     */ import org.bukkit.entity.LargeFireball;
/*   24:     */ import org.bukkit.entity.LivingEntity;
/*   25:     */ import org.bukkit.entity.Player;
/*   26:     */ import org.bukkit.entity.SmallFireball;
/*   27:     */ import org.bukkit.event.EventHandler;
/*   28:     */ import org.bukkit.event.Listener;
/*   29:     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*   30:     */ import org.bukkit.inventory.EntityEquipment;
/*   31:     */ import org.bukkit.inventory.ItemStack;
/*   32:     */ import org.bukkit.inventory.PlayerInventory;
/*   33:     */ import org.bukkit.inventory.meta.ItemMeta;
/*   34:     */ import org.bukkit.potion.PotionEffect;
/*   35:     */ import org.bukkit.potion.PotionEffectType;
/*   36:     */ 
/*   37:     */ public class DamageSystem
/*   38:     */   implements Listener
/*   39:     */ {
/*   40:     */   public ItemLoreStats instance;
/*   41:     */   private FileConfiguration PlayerDataConfig;
/*   42:  38 */   GearStats gearStats = new GearStats();
/*   43:  39 */   SetBonuses setBonuses = new SetBonuses();
/*   44:  40 */   Util_Colours util_Colours = new Util_Colours();
/*   45:     */   
/*   46:     */   public DamageSystem(ItemLoreStats i)
/*   47:     */   {
/*   48:  43 */     this.instance = i;
/*   49:     */   }
/*   50:     */   
/*   51:     */   private int random(int length)
/*   52:     */   {
/*   53:  47 */     return new Random().nextInt(length) + 1;
/*   54:     */   }
/*   55:     */   
/*   56:     */   private String randomRange(double min, double max)
/*   57:     */   {
/*   58:  51 */     Locale forceLocale = new Locale("en", "UK");
/*   59:  52 */     String decimalPattern = "#.#";
/*   60:     */     
/*   61:  54 */     DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(forceLocale);
/*   62:  55 */     decimalFormat.applyPattern(decimalPattern);
/*   63:     */     
/*   64:  57 */     String format = decimalFormat.format(min + Math.random() * (max - min));
/*   65:     */     
/*   66:  59 */     return format;
/*   67:     */   }
/*   68:     */   
/*   69:     */   public int materialToId(Material mat)
/*   70:     */   {
/*   71:  64 */     if (mat == Material.IRON_HOE) {
/*   72:  65 */       return 256;
/*   73:     */     }
/*   74:  66 */     if (mat == Material.IRON_PICKAXE) {
/*   75:  67 */       return 257;
/*   76:     */     }
/*   77:  68 */     if (mat == Material.IRON_AXE) {
/*   78:  69 */       return 258;
/*   79:     */     }
/*   80:  70 */     if (mat == Material.BOW) {
/*   81:  71 */       return 261;
/*   82:     */     }
/*   83:  72 */     if (mat == Material.IRON_SWORD) {
/*   84:  73 */       return 267;
/*   85:     */     }
/*   86:  74 */     if (mat == Material.WOOD_SWORD) {
/*   87:  75 */       return 268;
/*   88:     */     }
/*   89:  76 */     if (mat == Material.WOOD_SPADE) {
/*   90:  77 */       return 269;
/*   91:     */     }
/*   92:  78 */     if (mat == Material.WOOD_PICKAXE) {
/*   93:  79 */       return 270;
/*   94:     */     }
/*   95:  80 */     if (mat == Material.WOOD_AXE) {
/*   96:  81 */       return 271;
/*   97:     */     }
/*   98:  82 */     if (mat == Material.STONE_SWORD) {
/*   99:  83 */       return 272;
/*  100:     */     }
/*  101:  84 */     if (mat == Material.STONE_SPADE) {
/*  102:  85 */       return 273;
/*  103:     */     }
/*  104:  86 */     if (mat == Material.STONE_PICKAXE) {
/*  105:  87 */       return 274;
/*  106:     */     }
/*  107:  88 */     if (mat == Material.STONE_AXE) {
/*  108:  89 */       return 275;
/*  109:     */     }
/*  110:  90 */     if (mat == Material.DIAMOND_SWORD) {
/*  111:  91 */       return 276;
/*  112:     */     }
/*  113:  92 */     if (mat == Material.DIAMOND_SPADE) {
/*  114:  93 */       return 277;
/*  115:     */     }
/*  116:  94 */     if (mat == Material.DIAMOND_PICKAXE) {
/*  117:  95 */       return 278;
/*  118:     */     }
/*  119:  96 */     if (mat == Material.DIAMOND_AXE) {
/*  120:  97 */       return 279;
/*  121:     */     }
/*  122:  98 */     if (mat == Material.STICK) {
/*  123:  99 */       return 280;
/*  124:     */     }
/*  125: 100 */     if (mat == Material.GOLD_SWORD) {
/*  126: 101 */       return 283;
/*  127:     */     }
/*  128: 102 */     if (mat == Material.GOLD_SPADE) {
/*  129: 103 */       return 284;
/*  130:     */     }
/*  131: 104 */     if (mat == Material.GOLD_PICKAXE) {
/*  132: 105 */       return 285;
/*  133:     */     }
/*  134: 106 */     if (mat == Material.GOLD_AXE) {
/*  135: 107 */       return 286;
/*  136:     */     }
/*  137: 108 */     if (mat == Material.WOOD_HOE) {
/*  138: 109 */       return 290;
/*  139:     */     }
/*  140: 110 */     if (mat == Material.STONE_HOE) {
/*  141: 111 */       return 291;
/*  142:     */     }
/*  143: 112 */     if (mat == Material.IRON_HOE) {
/*  144: 113 */       return 292;
/*  145:     */     }
/*  146: 114 */     if (mat == Material.DIAMOND_HOE) {
/*  147: 115 */       return 293;
/*  148:     */     }
/*  149: 116 */     if (mat == Material.GOLD_HOE) {
/*  150: 117 */       return 294;
/*  151:     */     }
/*  152: 118 */     if (mat == Material.LEATHER_HELMET) {
/*  153: 119 */       return 298;
/*  154:     */     }
/*  155: 120 */     if (mat == Material.LEATHER_CHESTPLATE) {
/*  156: 121 */       return 299;
/*  157:     */     }
/*  158: 122 */     if (mat == Material.LEATHER_LEGGINGS) {
/*  159: 123 */       return 300;
/*  160:     */     }
/*  161: 124 */     if (mat == Material.LEATHER_BOOTS) {
/*  162: 125 */       return 301;
/*  163:     */     }
/*  164: 126 */     if (mat == Material.CHAINMAIL_HELMET) {
/*  165: 127 */       return 302;
/*  166:     */     }
/*  167: 128 */     if (mat == Material.CHAINMAIL_CHESTPLATE) {
/*  168: 129 */       return 303;
/*  169:     */     }
/*  170: 130 */     if (mat == Material.CHAINMAIL_LEGGINGS) {
/*  171: 131 */       return 304;
/*  172:     */     }
/*  173: 132 */     if (mat == Material.CHAINMAIL_BOOTS) {
/*  174: 133 */       return 305;
/*  175:     */     }
/*  176: 134 */     if (mat == Material.IRON_HELMET) {
/*  177: 135 */       return 306;
/*  178:     */     }
/*  179: 136 */     if (mat == Material.IRON_CHESTPLATE) {
/*  180: 137 */       return 307;
/*  181:     */     }
/*  182: 138 */     if (mat == Material.IRON_LEGGINGS) {
/*  183: 139 */       return 308;
/*  184:     */     }
/*  185: 140 */     if (mat == Material.IRON_BOOTS) {
/*  186: 141 */       return 309;
/*  187:     */     }
/*  188: 142 */     if (mat == Material.DIAMOND_HELMET) {
/*  189: 143 */       return 310;
/*  190:     */     }
/*  191: 144 */     if (mat == Material.DIAMOND_CHESTPLATE) {
/*  192: 145 */       return 311;
/*  193:     */     }
/*  194: 146 */     if (mat == Material.DIAMOND_LEGGINGS) {
/*  195: 147 */       return 312;
/*  196:     */     }
/*  197: 148 */     if (mat == Material.DIAMOND_BOOTS) {
/*  198: 149 */       return 313;
/*  199:     */     }
/*  200: 150 */     if (mat == Material.GOLD_HELMET) {
/*  201: 151 */       return 314;
/*  202:     */     }
/*  203: 152 */     if (mat == Material.GOLD_CHESTPLATE) {
/*  204: 153 */       return 315;
/*  205:     */     }
/*  206: 154 */     if (mat == Material.GOLD_LEGGINGS) {
/*  207: 155 */       return 316;
/*  208:     */     }
/*  209: 156 */     if (mat == Material.GOLD_BOOTS) {
/*  210: 157 */       return 317;
/*  211:     */     }
/*  212: 160 */     return 1337;
/*  213:     */   }
/*  214:     */   
/*  215:     */   public String getResponse(String getKeyFromLanguageFile)
/*  216:     */   {
/*  217:     */     try
/*  218:     */     {
/*  219: 166 */       this.PlayerDataConfig = new YamlConfiguration();
/*  220: 167 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*  221:     */       
/*  222: 169 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*  223:     */     }
/*  224:     */     catch (Exception e)
/*  225:     */     {
/*  226: 172 */       e.printStackTrace();
/*  227: 173 */       System.out.println("*********** Failed to load message from language file! ***********");
/*  228:     */     }
/*  229: 175 */     return "*********** Failed to load message from language file! ***********";
/*  230:     */   }
/*  231:     */   
/*  232:     */   @EventHandler
/*  233:     */   public void onEntityDamage(EntityDamageByEntityEvent event)
/*  234:     */   {
/*  235: 182 */     if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(event.getDamager().getWorld().getName()))
/*  236:     */     {
/*  237: 183 */       if ((event.getEntity().hasMetadata("NPC")) || ((event.getEntity() instanceof ItemFrame)) || ((event.getEntity() instanceof LargeFireball)) || ((event.getEntity() instanceof SmallFireball))) {
/*  238: 183 */         return;
/*  239:     */       }
/*  240: 185 */       if (((event.getDamager() instanceof Player)) || ((event.getDamager() instanceof Arrow)))
/*  241:     */       {
/*  242: 186 */         Player getAttacker = null;
/*  243: 188 */         if ((event.getDamager() instanceof Arrow))
/*  244:     */         {
/*  245: 189 */           Arrow arrow = (Arrow)event.getDamager();
/*  246: 190 */           Entity shooter = arrow.getShooter();
/*  247: 191 */           if (!(shooter instanceof Player))
/*  248:     */           {
/*  249: 192 */             if ((event.getEntity() instanceof Player))
/*  250:     */             {
/*  251: 194 */               if (defenderDodge((Player)event.getEntity()))
/*  252:     */               {
/*  253: 195 */                 if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.dodgeAttack")) {
/*  254: 196 */                   ((Player)event.getEntity()).sendMessage(getResponse("DamageMessages.DodgeSuccess"));
/*  255:     */                 }
/*  256: 198 */                 event.setCancelled(true);
/*  257: 199 */                 return;
/*  258:     */               }
/*  259: 202 */               if (defenderBlock((Player)event.getEntity()))
/*  260:     */               {
/*  261: 203 */                 if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.blockAttack"))
/*  262:     */                 {
/*  263: 204 */                   ((Player)event.getEntity()).sendMessage(getResponse("DamageMessages.BlockSuccess"));
/*  264: 205 */                   ((Player)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));
/*  265:     */                 }
/*  266: 207 */                 event.setCancelled(true);
/*  267: 208 */                 return;
/*  268:     */               }
/*  269: 211 */               if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/*  270: 212 */                 durabilityCalcForArmour((Player)event.getEntity());
/*  271:     */               }
/*  272: 215 */               if (ItemLoreStats.plugin.getConfig().getString("npcModifier." + event.getDamager().getWorld().getName()) != null)
/*  273:     */               {
/*  274: 216 */                 double distance = ((LivingEntity)shooter).getMaxHealth() / ItemLoreStats.plugin.getConfig().getDouble("npcModifier." + shooter.getWorld().getName() + ".healthMultiplier");
/*  275: 217 */                 double newDamage = Math.round(event.getDamage() + distance * ItemLoreStats.plugin.getConfig().getDouble("npcModifier." + shooter.getWorld().getName() + ".damageMultiplier"));
/*  276:     */                 
/*  277: 219 */                 double getDefenderArmour = defenderArmour((Player)event.getEntity());
/*  278: 220 */                 double reducedDamage = newDamage / 100.0D * getDefenderArmour;
/*  279:     */                 
/*  280: 222 */                 damageDealtMessage(shooter, event.getEntity(), (int)(newDamage - reducedDamage));
/*  281:     */                 
/*  282: 224 */                 event.setDamage(newDamage - reducedDamage);
/*  283:     */               }
/*  284:     */               else
/*  285:     */               {
/*  286: 226 */                 double getDefenderArmour = defenderArmour((Player)event.getEntity());
/*  287: 227 */                 double reducedDamage = event.getDamage() / 100.0D * getDefenderArmour;
/*  288:     */                 
/*  289: 229 */                 damageDealtMessage(shooter, event.getEntity(), (int)(event.getDamage() - reducedDamage));
/*  290:     */                 
/*  291: 231 */                 event.setDamage(event.getDamage() - reducedDamage);
/*  292:     */               }
/*  293: 234 */               return;
/*  294:     */             }
/*  295: 236 */             return;
/*  296:     */           }
/*  297: 239 */           getAttacker = (Player)shooter;
/*  298:     */         }
/*  299:     */         else
/*  300:     */         {
/*  301: 243 */           if (((Player)event.getDamager()).getItemInHand().getType().equals(Material.BOW))
/*  302:     */           {
/*  303: 244 */             if ((event.getEntity() instanceof Player))
/*  304:     */             {
/*  305: 245 */               event.setDamage(1.0D);
/*  306: 246 */               damageDealtMessage(event.getDamager(), event.getEntity(), 1.0D);
/*  307: 247 */               return;
/*  308:     */             }
/*  309: 249 */             event.setDamage(1.0D);
/*  310: 250 */             damageDealtMessage(event.getDamager(), event.getEntity(), 1.0D);
/*  311: 251 */             return;
/*  312:     */           }
/*  313: 254 */           getAttacker = (Player)event.getDamager();
/*  314:     */         }
/*  315: 259 */         Entity getDefender = event.getEntity();
/*  316: 261 */         if (((getAttacker instanceof Player)) && ((getDefender instanceof Player)) && 
/*  317: 262 */           (ItemLoreStats.plugin.getWorldGuard() != null) && (
/*  318: 263 */           (ItemLoreStats.plugin.util_WorldGuard.playerInPVPRegion(getAttacker)) || (ItemLoreStats.plugin.util_WorldGuard.playerInPVPRegion((Player)getDefender)) || (ItemLoreStats.plugin.util_WorldGuard.playerInInvincibleRegion(getAttacker)) || (ItemLoreStats.plugin.util_WorldGuard.playerInInvincibleRegion((Player)getDefender)))) {
/*  319: 263 */           return;
/*  320:     */         }
/*  321: 267 */         if (defenderDodge(getDefender))
/*  322:     */         {
/*  323: 268 */           if ((getDefender instanceof Player))
/*  324:     */           {
/*  325: 269 */             if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.enemyDodgedAttack")) {
/*  326: 270 */               getAttacker.sendMessage(ChatColor.WHITE + ((Player)getDefender).getName() + " " + getResponse("DamageMessages.EnemyDodgeSuccess"));
/*  327:     */             }
/*  328: 272 */             if (((getDefender instanceof Player)) && 
/*  329: 273 */               (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.dodgeAttack"))) {
/*  330: 274 */               ((Player)getDefender).sendMessage(getResponse("DamageMessages.DodgeSuccess"));
/*  331:     */             }
/*  332:     */           }
/*  333: 278 */           event.setCancelled(true);
/*  334: 279 */           return;
/*  335:     */         }
/*  336: 282 */         if (defenderBlock(getDefender))
/*  337:     */         {
/*  338: 283 */           if (((getDefender instanceof Player)) && 
/*  339: 284 */             (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.blockAttack")))
/*  340:     */           {
/*  341: 285 */             ((Player)getDefender).sendMessage(getResponse("DamageMessages.BlockSuccess"));
/*  342: 286 */             ((Player)getDefender).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));
/*  343:     */           }
/*  344: 289 */           event.setCancelled(true);
/*  345: 290 */           return;
/*  346:     */         }
/*  347: 293 */         double getAttackerDamage = attackerDamage(getAttacker, getDefender, getDefender.getType(), event.getDamage());
/*  348: 294 */         double getDefenderArmour = defenderArmour(getDefender);
/*  349:     */         
/*  350: 296 */         double reducedDamage = getAttackerDamage / 100.0D * getDefenderArmour;
/*  351: 298 */         if (((getDefender instanceof Player)) && 
/*  352: 299 */           (reflect(getDefender) > 0.0D) && 
/*  353: 300 */           (random(100) <= reflect(getDefender)))
/*  354:     */         {
/*  355: 301 */           if ((getDefender instanceof Player))
/*  356:     */           {
/*  357: 302 */             double damage = getAttackerDamage - reducedDamage;
/*  358:     */             
/*  359: 304 */             getAttacker.damage(damage);
/*  360: 305 */             if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.enemyReflectedAttack")) {
/*  361: 306 */               getAttacker.sendMessage(ChatColor.WHITE + ((Player)getDefender).getName() + " " + getResponse("DamageMessages.EnemyReflectSuccess"));
/*  362:     */             }
/*  363: 308 */             if (((getDefender instanceof Player)) && 
/*  364: 309 */               (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.reflectAttack"))) {
/*  365: 310 */               ((Player)getDefender).sendMessage(getResponse("DamageMessages.ReflectSuccess"));
/*  366:     */             }
/*  367: 314 */             event.setCancelled(true);
/*  368: 315 */             return;
/*  369:     */           }
/*  370: 317 */           if ((getDefender instanceof LivingEntity))
/*  371:     */           {
/*  372: 318 */             double damage = getAttackerDamage - reducedDamage;
/*  373:     */             
/*  374: 320 */             getAttacker.damage(damage);
/*  375: 321 */             if ((ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.enemyReflectedAttack")) && 
/*  376: 322 */               ((getAttacker instanceof Player))) {
/*  377: 323 */               getAttacker.sendMessage(ChatColor.WHITE + ((Player)getDefender).getName() + " " + getResponse("DamageMessages.EnemyReflectSuccess"));
/*  378:     */             }
/*  379: 327 */             event.setCancelled(true);
/*  380: 328 */             return;
/*  381:     */           }
/*  382:     */         }
/*  383: 334 */         if (getAttacker.getHealth() < getAttacker.getMaxHealth())
/*  384:     */         {
/*  385: 335 */           double lifeStealHeal = lifeSteal(getDefender, getAttacker, getAttackerDamage - reducedDamage);
/*  386: 337 */           if (lifeStealHeal > Math.abs(getAttacker.getHealth() - getAttacker.getMaxHealth()))
/*  387:     */           {
/*  388: 338 */             double getRemainingHealth = Math.abs(getAttacker.getHealth() - getAttacker.getMaxHealth());
/*  389: 339 */             getAttacker.setHealth(getAttacker.getHealth() + getRemainingHealth);
/*  390:     */           }
/*  391:     */           else
/*  392:     */           {
/*  393: 341 */             getAttacker.setHealth(getAttacker.getHealth() + lifeStealHeal);
/*  394:     */           }
/*  395:     */         }
/*  396: 345 */         fire(getDefender, getAttacker);
/*  397: 346 */         ice(getDefender, getAttacker);
/*  398: 347 */         poison(getDefender, getAttacker);
/*  399: 348 */         wither(getDefender, getAttacker);
/*  400: 349 */         harming(getDefender, getAttacker);
/*  401: 350 */         blind(getDefender, getAttacker);
/*  402: 352 */         if ((!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) && 
/*  403: 353 */           (getAttacker.getItemInHand() != null) && (ItemLoreStats.plugin.isTool(getAttacker.getItemInHand().getType())))
/*  404:     */         {
/*  405: 354 */           getAttacker.getItemInHand().setDurability((short)-1);
/*  406: 355 */           if (getAttacker.getItemInHand().getItemMeta().hasLore())
/*  407:     */           {
/*  408: 357 */             String durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  409:     */             
/*  410: 359 */             List<String> getItemLore = getAttacker.getItemInHand().getItemMeta().getLore();
/*  411: 361 */             for (String getDurability : getItemLore) {
/*  412: 362 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  413:     */               {
/*  414: 363 */                 String durabilityRebuilder = "";
/*  415: 364 */                 String durabilityAmountColour = "";
/*  416: 365 */                 String prefixColourOnly = "";
/*  417:     */                 
/*  418: 367 */                 prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability));
/*  419:     */                 
/*  420: 369 */                 int index = getItemLore.indexOf(getDurability);
/*  421: 370 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  422: 371 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  423: 373 */                 if (currentValueMinusOne + 1 < 2)
/*  424:     */                 {
/*  425: 374 */                   getAttacker.playEffect(getAttacker.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/*  426: 375 */                   getAttacker.getInventory().remove(getAttacker.getItemInHand());
/*  427: 376 */                   event.setDamage(getAttackerDamage - reducedDamage);
/*  428: 377 */                   damageDealtMessage(getAttacker, getDefender, getAttackerDamage - reducedDamage);
/*  429: 378 */                   return;
/*  430:     */                 }
/*  431: 381 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  432:     */                 {
/*  433: 382 */                   if (getDurability.length() > 4)
/*  434:     */                   {
/*  435: 383 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  436: 384 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  437:     */                     } else {
/*  438: 386 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  439:     */                     }
/*  440:     */                   }
/*  441:     */                   else {
/*  442: 389 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  443:     */                   }
/*  444:     */                 }
/*  445:     */                 else {
/*  446: 392 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  447:     */                 }
/*  448: 395 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  449:     */                 {
/*  450: 396 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  451:     */                   {
/*  452: 397 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  453: 398 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  454:     */                     } else {
/*  455: 400 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  456:     */                     }
/*  457:     */                   }
/*  458:     */                   else {
/*  459: 403 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  460:     */                   }
/*  461:     */                 }
/*  462:     */                 else {
/*  463: 406 */                   durabilityAmountColour = prefixColourOnly;
/*  464:     */                 }
/*  465: 409 */                 currentValueMinusOne += enchantmentDurabilityLoss(getAttacker.getItemInHand());
/*  466:     */                 
/*  467: 411 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  468: 413 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  469:     */                 {
/*  470: 414 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  471: 415 */                     getAttacker.sendMessage(getAttacker.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  472:     */                   }
/*  473: 417 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  474:     */                 }
/*  475: 418 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  476:     */                 {
/*  477: 419 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  478: 420 */                     getAttacker.sendMessage(getAttacker.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  479:     */                   }
/*  480: 422 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  481:     */                 }
/*  482: 423 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  483:     */                 {
/*  484: 424 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  485: 425 */                     getAttacker.sendMessage(getAttacker.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  486:     */                   }
/*  487: 427 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  488:     */                 }
/*  489:     */                 else
/*  490:     */                 {
/*  491: 429 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  492:     */                 }
/*  493: 432 */                 getItemLore.set(index, durabilityRebuilder);
/*  494:     */               }
/*  495:     */             }
/*  496: 436 */             ItemStack setItemInHand = new ItemStack(getAttacker.getItemInHand());
/*  497: 437 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/*  498:     */             
/*  499: 439 */             setItemInHandMeta.setLore(getItemLore);
/*  500: 440 */             setItemInHand.setItemMeta(setItemInHandMeta);
/*  501:     */             
/*  502: 442 */             getAttacker.getInventory().remove(getAttacker.getItemInHand());
/*  503: 443 */             getAttacker.setItemInHand(new ItemStack(setItemInHand));
/*  504:     */           }
/*  505:     */         }
/*  506: 449 */         if (!event.getEntityType().equals(EntityType.PLAYER))
/*  507:     */         {
/*  508: 450 */           if ((event.getEntity() instanceof LivingEntity)) {
/*  509: 452 */             if (ItemLoreStats.plugin.isTool(getAttacker.getItemInHand().getType()))
/*  510:     */             {
/*  511: 453 */               if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/*  512: 454 */                 durabilityCalcForArmour((LivingEntity)getDefender);
/*  513:     */               }
/*  514: 456 */               event.setDamage(getAttackerDamage - reducedDamage);
/*  515:     */             }
/*  516:     */             else
/*  517:     */             {
/*  518: 458 */               event.setDamage(1.0D);
/*  519:     */             }
/*  520:     */           }
/*  521:     */         }
/*  522: 463 */         else if (ItemLoreStats.plugin.isTool(getAttacker.getItemInHand().getType()))
/*  523:     */         {
/*  524: 464 */           if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/*  525: 465 */             durabilityCalcForArmour((Player)getDefender);
/*  526:     */           }
/*  527: 467 */           event.setDamage(getAttackerDamage - reducedDamage);
/*  528:     */         }
/*  529:     */         else
/*  530:     */         {
/*  531: 469 */           event.setDamage(1.0D);
/*  532:     */         }
/*  533: 472 */         if (ItemLoreStats.plugin.isTool(getAttacker.getItemInHand().getType())) {
/*  534: 473 */           damageDealtMessage(getAttacker, getDefender, getAttackerDamage - reducedDamage);
/*  535:     */         } else {
/*  536: 475 */           damageDealtMessage(getAttacker, getDefender, 1.0D);
/*  537:     */         }
/*  538:     */       }
/*  539: 478 */       else if ((event.getEntity() instanceof Player))
/*  540:     */       {
/*  541: 481 */         Player player = (Player)event.getEntity();
/*  542: 483 */         if (defenderDodge(player))
/*  543:     */         {
/*  544: 484 */           if (((player instanceof Player)) && 
/*  545: 485 */             ((player instanceof Player)) && 
/*  546: 486 */             (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.dodgeAttack"))) {
/*  547: 487 */             player.sendMessage(getResponse("DamageMessages.DodgeSuccess"));
/*  548:     */           }
/*  549: 491 */           event.setCancelled(true);
/*  550: 492 */           return;
/*  551:     */         }
/*  552: 495 */         if (defenderBlock(player))
/*  553:     */         {
/*  554: 496 */           if (((player instanceof Player)) && 
/*  555: 497 */             ((player instanceof Player)) && 
/*  556: 498 */             (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.blockAttack")))
/*  557:     */           {
/*  558: 499 */             player.sendMessage(getResponse("DamageMessages.BlockSuccess"));
/*  559: 500 */             player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));
/*  560:     */           }
/*  561: 504 */           event.setCancelled(true);
/*  562: 505 */           return;
/*  563:     */         }
/*  564: 508 */         if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/*  565: 509 */           durabilityCalcForArmour((Player)event.getEntity());
/*  566:     */         }
/*  567: 512 */         if (ItemLoreStats.plugin.getConfig().getString("npcModifier." + event.getDamager().getWorld().getName()) != null)
/*  568:     */         {
/*  569: 513 */           if ((event.getDamager() instanceof LivingEntity))
/*  570:     */           {
/*  571: 514 */             double distance = ((LivingEntity)event.getDamager()).getMaxHealth() / ItemLoreStats.plugin.getConfig().getDouble("npcModifier." + event.getDamager().getWorld().getName() + ".healthMultiplier");
/*  572: 515 */             double newDamage = Math.round(event.getDamage() + distance * ItemLoreStats.plugin.getConfig().getDouble("npcModifier." + event.getDamager().getWorld().getName() + ".damageMultiplier"));
/*  573:     */             
/*  574: 517 */             double getDefenderArmour = defenderArmour(player);
/*  575: 518 */             double reducedDamage = newDamage / 100.0D * getDefenderArmour;
/*  576:     */             
/*  577: 520 */             damageDealtMessage(event.getDamager(), player, newDamage - reducedDamage);
/*  578:     */             
/*  579: 522 */             event.setDamage(newDamage - reducedDamage);
/*  580:     */           }
/*  581:     */         }
/*  582:     */         else
/*  583:     */         {
/*  584: 525 */           double getDefenderArmour = defenderArmour(player);
/*  585: 526 */           double reducedDamage = event.getDamage() / 100.0D * getDefenderArmour;
/*  586:     */           
/*  587: 528 */           damageDealtMessage(event.getDamager(), player, event.getDamage() - reducedDamage);
/*  588:     */           
/*  589: 530 */           event.setDamage(event.getDamage() - reducedDamage);
/*  590:     */         }
/*  591:     */       }
/*  592:     */     }
/*  593:     */   }
/*  594:     */   
/*  595:     */   public void durabilityCalcForArmour(Entity getDefender)
/*  596:     */   {
/*  597: 540 */     if ((getDefender instanceof Player))
/*  598:     */     {
/*  599: 542 */       if ((((Player)getDefender).getInventory().getHelmet() != null) && (ItemLoreStats.plugin.isHelmet(((Player)getDefender).getInventory().getHelmet().getType())))
/*  600:     */       {
/*  601: 543 */         ((Player)getDefender).getInventory().getHelmet().setDurability((short)0);
/*  602: 544 */         if (((Player)getDefender).getInventory().getHelmet().getItemMeta().hasLore())
/*  603:     */         {
/*  604: 546 */           String durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  605:     */           
/*  606: 548 */           List<String> getItemLore = ((Player)getDefender).getInventory().getHelmet().getItemMeta().getLore();
/*  607: 550 */           for (String getDurability : getItemLore) {
/*  608: 551 */             if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  609:     */             {
/*  610: 552 */               String durabilityRebuilder = "";
/*  611: 553 */               String durabilityAmountColour = "";
/*  612: 554 */               String prefixColourOnly = "";
/*  613:     */               
/*  614: 556 */               int index = getItemLore.indexOf(getDurability);
/*  615: 557 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  616: 558 */               int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  617: 560 */               if (currentValueMinusOne + 1 > 1)
/*  618:     */               {
/*  619: 561 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  620:     */                 {
/*  621: 562 */                   if (getDurability.length() > 4)
/*  622:     */                   {
/*  623: 563 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  624: 564 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  625:     */                     } else {
/*  626: 566 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  627:     */                     }
/*  628:     */                   }
/*  629:     */                   else {
/*  630: 569 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  631:     */                   }
/*  632:     */                 }
/*  633:     */                 else {
/*  634: 572 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  635:     */                 }
/*  636: 575 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  637:     */                 {
/*  638: 576 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  639:     */                   {
/*  640: 577 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  641: 578 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  642:     */                     } else {
/*  643: 580 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  644:     */                     }
/*  645:     */                   }
/*  646:     */                   else {
/*  647: 583 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  648:     */                   }
/*  649:     */                 }
/*  650:     */                 else {
/*  651: 586 */                   durabilityAmountColour = prefixColourOnly;
/*  652:     */                 }
/*  653: 589 */                 currentValueMinusOne += enchantmentDurabilityLoss(((Player)getDefender).getInventory().getHelmet());
/*  654:     */                 
/*  655: 591 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  656: 593 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  657:     */                 {
/*  658: 594 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  659: 595 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getHelmet().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  660:     */                   }
/*  661: 597 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  662:     */                 }
/*  663: 598 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  664:     */                 {
/*  665: 599 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  666: 600 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getHelmet().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  667:     */                   }
/*  668: 602 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  669:     */                 }
/*  670: 603 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  671:     */                 {
/*  672: 604 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  673: 605 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getHelmet().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  674:     */                   }
/*  675: 607 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  676:     */                 }
/*  677:     */                 else
/*  678:     */                 {
/*  679: 609 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  680:     */                 }
/*  681: 612 */                 getItemLore.set(index, durabilityRebuilder);
/*  682:     */                 
/*  683: 614 */                 ItemStack getItem = new ItemStack(((Player)getDefender).getInventory().getHelmet());
/*  684: 615 */                 ItemMeta getItemMeta = getItem.getItemMeta();
/*  685:     */                 
/*  686: 617 */                 getItemMeta.setLore(getItemLore);
/*  687: 618 */                 getItem.setItemMeta(getItemMeta);
/*  688:     */                 
/*  689: 620 */                 ((Player)getDefender).getInventory().remove(((Player)getDefender).getInventory().getHelmet());
/*  690: 621 */                 ((Player)getDefender).getInventory().setHelmet(new ItemStack(getItem));
/*  691:     */               }
/*  692:     */               else
/*  693:     */               {
/*  694: 623 */                 ((Player)getDefender).playEffect(((Player)getDefender).getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/*  695: 624 */                 ((Player)getDefender).getInventory().setHelmet(new ItemStack(Material.AIR));
/*  696:     */               }
/*  697:     */             }
/*  698:     */           }
/*  699:     */         }
/*  700:     */       }
/*  701: 631 */       if ((((Player)getDefender).getInventory().getChestplate() != null) && (ItemLoreStats.plugin.isChestplate(((Player)getDefender).getInventory().getChestplate().getType())))
/*  702:     */       {
/*  703: 632 */         ((Player)getDefender).getInventory().getChestplate().setDurability((short)0);
/*  704: 633 */         if (((Player)getDefender).getInventory().getChestplate().getItemMeta().hasLore())
/*  705:     */         {
/*  706: 635 */           String durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  707:     */           
/*  708: 637 */           List<String> getItemLore = ((Player)getDefender).getInventory().getChestplate().getItemMeta().getLore();
/*  709: 639 */           for (String getDurability : getItemLore) {
/*  710: 640 */             if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  711:     */             {
/*  712: 641 */               String durabilityRebuilder = "";
/*  713: 642 */               String durabilityAmountColour = "";
/*  714: 643 */               String prefixColourOnly = "";
/*  715:     */               
/*  716: 645 */               int index = getItemLore.indexOf(getDurability);
/*  717: 646 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  718: 647 */               int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  719: 649 */               if (currentValueMinusOne + 1 > 1)
/*  720:     */               {
/*  721: 650 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  722:     */                 {
/*  723: 651 */                   if (getDurability.length() > 4)
/*  724:     */                   {
/*  725: 652 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  726: 653 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  727:     */                     } else {
/*  728: 655 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  729:     */                     }
/*  730:     */                   }
/*  731:     */                   else {
/*  732: 658 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  733:     */                   }
/*  734:     */                 }
/*  735:     */                 else {
/*  736: 661 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  737:     */                 }
/*  738: 664 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  739:     */                 {
/*  740: 665 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  741:     */                   {
/*  742: 666 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  743: 667 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  744:     */                     } else {
/*  745: 669 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  746:     */                     }
/*  747:     */                   }
/*  748:     */                   else {
/*  749: 672 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  750:     */                   }
/*  751:     */                 }
/*  752:     */                 else {
/*  753: 675 */                   durabilityAmountColour = prefixColourOnly;
/*  754:     */                 }
/*  755: 678 */                 currentValueMinusOne += enchantmentDurabilityLoss(((Player)getDefender).getInventory().getChestplate());
/*  756:     */                 
/*  757: 680 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  758: 682 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  759:     */                 {
/*  760: 683 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  761: 684 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getChestplate().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  762:     */                   }
/*  763: 686 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  764:     */                 }
/*  765: 687 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  766:     */                 {
/*  767: 688 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  768: 689 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getChestplate().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  769:     */                   }
/*  770: 691 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  771:     */                 }
/*  772: 692 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  773:     */                 {
/*  774: 693 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  775: 694 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getChestplate().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  776:     */                   }
/*  777: 696 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  778:     */                 }
/*  779:     */                 else
/*  780:     */                 {
/*  781: 698 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  782:     */                 }
/*  783: 701 */                 getItemLore.set(index, durabilityRebuilder);
/*  784:     */                 
/*  785: 703 */                 ItemStack getItem = new ItemStack(((Player)getDefender).getInventory().getChestplate());
/*  786: 704 */                 ItemMeta getItemMeta = getItem.getItemMeta();
/*  787:     */                 
/*  788: 706 */                 getItemMeta.setLore(getItemLore);
/*  789: 707 */                 getItem.setItemMeta(getItemMeta);
/*  790:     */                 
/*  791: 709 */                 ((Player)getDefender).getInventory().remove(((Player)getDefender).getInventory().getChestplate());
/*  792: 710 */                 ((Player)getDefender).getInventory().setChestplate(new ItemStack(getItem));
/*  793:     */               }
/*  794:     */               else
/*  795:     */               {
/*  796: 712 */                 ((Player)getDefender).playEffect(((Player)getDefender).getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/*  797: 713 */                 ((Player)getDefender).getInventory().setChestplate(new ItemStack(Material.AIR));
/*  798:     */               }
/*  799:     */             }
/*  800:     */           }
/*  801:     */         }
/*  802:     */       }
/*  803: 720 */       if ((((Player)getDefender).getInventory().getLeggings() != null) && (ItemLoreStats.plugin.isLeggings(((Player)getDefender).getInventory().getLeggings().getType())))
/*  804:     */       {
/*  805: 721 */         ((Player)getDefender).getInventory().getLeggings().setDurability((short)0);
/*  806: 722 */         if (((Player)getDefender).getInventory().getLeggings().getItemMeta().hasLore())
/*  807:     */         {
/*  808: 724 */           String durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  809:     */           
/*  810: 726 */           List<String> getItemLore = ((Player)getDefender).getInventory().getLeggings().getItemMeta().getLore();
/*  811: 728 */           for (String getDurability : getItemLore) {
/*  812: 729 */             if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  813:     */             {
/*  814: 730 */               String durabilityRebuilder = "";
/*  815: 731 */               String durabilityAmountColour = "";
/*  816: 732 */               String prefixColourOnly = "";
/*  817:     */               
/*  818: 734 */               int index = getItemLore.indexOf(getDurability);
/*  819: 735 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  820: 736 */               int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  821: 738 */               if (currentValueMinusOne + 1 > 1)
/*  822:     */               {
/*  823: 739 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  824:     */                 {
/*  825: 740 */                   if (getDurability.length() > 4)
/*  826:     */                   {
/*  827: 741 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  828: 742 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  829:     */                     } else {
/*  830: 744 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  831:     */                     }
/*  832:     */                   }
/*  833:     */                   else {
/*  834: 747 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  835:     */                   }
/*  836:     */                 }
/*  837:     */                 else {
/*  838: 750 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  839:     */                 }
/*  840: 753 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  841:     */                 {
/*  842: 754 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  843:     */                   {
/*  844: 755 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  845: 756 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  846:     */                     } else {
/*  847: 758 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  848:     */                     }
/*  849:     */                   }
/*  850:     */                   else {
/*  851: 761 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  852:     */                   }
/*  853:     */                 }
/*  854:     */                 else {
/*  855: 764 */                   durabilityAmountColour = prefixColourOnly;
/*  856:     */                 }
/*  857: 767 */                 currentValueMinusOne += enchantmentDurabilityLoss(((Player)getDefender).getInventory().getLeggings());
/*  858:     */                 
/*  859: 769 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  860: 771 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  861:     */                 {
/*  862: 772 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  863: 773 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getLeggings().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  864:     */                   }
/*  865: 775 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  866:     */                 }
/*  867: 776 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  868:     */                 {
/*  869: 777 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  870: 778 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getLeggings().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  871:     */                   }
/*  872: 780 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  873:     */                 }
/*  874: 781 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  875:     */                 {
/*  876: 782 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  877: 783 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getLeggings().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  878:     */                   }
/*  879: 785 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  880:     */                 }
/*  881:     */                 else
/*  882:     */                 {
/*  883: 787 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  884:     */                 }
/*  885: 790 */                 getItemLore.set(index, durabilityRebuilder);
/*  886:     */                 
/*  887: 792 */                 ItemStack getItem = new ItemStack(((Player)getDefender).getInventory().getLeggings());
/*  888: 793 */                 ItemMeta getItemMeta = getItem.getItemMeta();
/*  889:     */                 
/*  890: 795 */                 getItemMeta.setLore(getItemLore);
/*  891: 796 */                 getItem.setItemMeta(getItemMeta);
/*  892:     */                 
/*  893: 798 */                 ((Player)getDefender).getInventory().remove(((Player)getDefender).getInventory().getLeggings());
/*  894: 799 */                 ((Player)getDefender).getInventory().setLeggings(new ItemStack(getItem));
/*  895:     */               }
/*  896:     */               else
/*  897:     */               {
/*  898: 801 */                 ((Player)getDefender).playEffect(((Player)getDefender).getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/*  899: 802 */                 ((Player)getDefender).getInventory().setLeggings(new ItemStack(Material.AIR));
/*  900:     */               }
/*  901:     */             }
/*  902:     */           }
/*  903:     */         }
/*  904:     */       }
/*  905: 809 */       if ((((Player)getDefender).getInventory().getBoots() != null) && (ItemLoreStats.plugin.isBoots(((Player)getDefender).getInventory().getBoots().getType())))
/*  906:     */       {
/*  907: 810 */         ((Player)getDefender).getInventory().getBoots().setDurability((short)0);
/*  908: 811 */         if (((Player)getDefender).getInventory().getBoots().getItemMeta().hasLore())
/*  909:     */         {
/*  910: 813 */           String durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  911:     */           
/*  912: 815 */           List<String> getItemLore = ((Player)getDefender).getInventory().getBoots().getItemMeta().getLore();
/*  913: 817 */           for (String getDurability : getItemLore) {
/*  914: 818 */             if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  915:     */             {
/*  916: 819 */               String durabilityRebuilder = "";
/*  917: 820 */               String durabilityAmountColour = "";
/*  918: 821 */               String prefixColourOnly = "";
/*  919:     */               
/*  920: 823 */               int index = getItemLore.indexOf(getDurability);
/*  921: 824 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  922: 825 */               int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  923: 827 */               if (currentValueMinusOne + 1 > 1)
/*  924:     */               {
/*  925: 828 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  926:     */                 {
/*  927: 829 */                   if (getDurability.length() > 4)
/*  928:     */                   {
/*  929: 830 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  930: 831 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  931:     */                     } else {
/*  932: 833 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  933:     */                     }
/*  934:     */                   }
/*  935:     */                   else {
/*  936: 836 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  937:     */                   }
/*  938:     */                 }
/*  939:     */                 else {
/*  940: 839 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  941:     */                 }
/*  942: 842 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  943:     */                 {
/*  944: 843 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  945:     */                   {
/*  946: 844 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  947: 845 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  948:     */                     } else {
/*  949: 847 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  950:     */                     }
/*  951:     */                   }
/*  952:     */                   else {
/*  953: 850 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  954:     */                   }
/*  955:     */                 }
/*  956:     */                 else {
/*  957: 853 */                   durabilityAmountColour = prefixColourOnly;
/*  958:     */                 }
/*  959: 856 */                 currentValueMinusOne += enchantmentDurabilityLoss(((Player)getDefender).getInventory().getBoots());
/*  960:     */                 
/*  961: 858 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  962: 860 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  963:     */                 {
/*  964: 861 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  965: 862 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getBoots().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  966:     */                   }
/*  967: 864 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  968:     */                 }
/*  969: 865 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  970:     */                 {
/*  971: 866 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  972: 867 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getBoots().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  973:     */                   }
/*  974: 869 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  975:     */                 }
/*  976: 870 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  977:     */                 {
/*  978: 871 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  979: 872 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getBoots().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  980:     */                   }
/*  981: 874 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  982:     */                 }
/*  983:     */                 else
/*  984:     */                 {
/*  985: 876 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  986:     */                 }
/*  987: 879 */                 getItemLore.set(index, durabilityRebuilder);
/*  988:     */                 
/*  989: 881 */                 ItemStack getItem = new ItemStack(((Player)getDefender).getInventory().getBoots());
/*  990: 882 */                 ItemMeta getItemMeta = getItem.getItemMeta();
/*  991:     */                 
/*  992: 884 */                 getItemMeta.setLore(getItemLore);
/*  993: 885 */                 getItem.setItemMeta(getItemMeta);
/*  994:     */                 
/*  995: 887 */                 ((Player)getDefender).getInventory().remove(((Player)getDefender).getInventory().getBoots());
/*  996: 888 */                 ((Player)getDefender).getInventory().setBoots(new ItemStack(getItem));
/*  997:     */               }
/*  998:     */               else
/*  999:     */               {
/* 1000: 890 */                 ((Player)getDefender).playEffect(((Player)getDefender).getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 1001: 891 */                 ((Player)getDefender).getInventory().setBoots(new ItemStack(Material.AIR));
/* 1002:     */               }
/* 1003:     */             }
/* 1004:     */           }
/* 1005:     */         }
/* 1006:     */       }
/* 1007:     */     }
/* 1008: 897 */     else if ((getDefender instanceof LivingEntity))
/* 1009:     */     {
/* 1010: 899 */       if ((((LivingEntity)getDefender).getEquipment().getHelmet() != null) && (ItemLoreStats.plugin.isHelmet(((LivingEntity)getDefender).getEquipment().getHelmet().getType())))
/* 1011:     */       {
/* 1012: 900 */         ((LivingEntity)getDefender).getEquipment().getHelmet().setDurability((short)0);
/* 1013: 901 */         if (((LivingEntity)getDefender).getEquipment().getHelmet().getItemMeta().hasLore())
/* 1014:     */         {
/* 1015: 903 */           String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 1016:     */           
/* 1017: 905 */           List<String> getItemLore = ((LivingEntity)getDefender).getEquipment().getHelmet().getItemMeta().getLore();
/* 1018: 907 */           for (String getDurability : getItemLore) {
/* 1019: 908 */             if (ChatColor.stripColor(getDurability).startsWith(durability))
/* 1020:     */             {
/* 1021: 909 */               int index = getItemLore.indexOf(getDurability);
/* 1022: 910 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durability.length()).split("/")[1].replace("]", "").trim();
/* 1023: 911 */               String currentValue = ChatColor.stripColor(getDurability).trim().replace("[", "").replace(":", "").trim().substring(durability.length()).split("/")[0].replace("]", "").trim();
/* 1024: 913 */               if (Integer.parseInt(currentValue) > 1)
/* 1025:     */               {
/* 1026: 914 */                 String durabilityRebuilder = durability + ": " + (Integer.parseInt(currentValue) - 1) + "/" + maximumValue;
/* 1027: 915 */                 getItemLore.set(index, durabilityRebuilder);
/* 1028:     */                 
/* 1029: 917 */                 ItemStack getHelmetItem = new ItemStack(((LivingEntity)getDefender).getEquipment().getHelmet());
/* 1030: 918 */                 ItemMeta getHelmetItemMeta = getHelmetItem.getItemMeta();
/* 1031:     */                 
/* 1032: 920 */                 getHelmetItemMeta.setLore(getItemLore);
/* 1033: 921 */                 getHelmetItem.setItemMeta(getHelmetItemMeta);
/* 1034:     */                 
/* 1035: 923 */                 ((LivingEntity)getDefender).getEquipment().setHelmet(new ItemStack(Material.AIR));
/* 1036: 924 */                 ((LivingEntity)getDefender).getEquipment().setHelmet(new ItemStack(getHelmetItem));
/* 1037:     */               }
/* 1038:     */               else
/* 1039:     */               {
/* 1040: 926 */                 ((LivingEntity)getDefender).getEquipment().setHelmet(new ItemStack(Material.AIR));
/* 1041:     */               }
/* 1042:     */             }
/* 1043:     */           }
/* 1044:     */         }
/* 1045:     */       }
/* 1046: 933 */       if ((((LivingEntity)getDefender).getEquipment().getChestplate() != null) && (ItemLoreStats.plugin.isChestplate(((LivingEntity)getDefender).getEquipment().getChestplate().getType())))
/* 1047:     */       {
/* 1048: 934 */         ((LivingEntity)getDefender).getEquipment().getChestplate().setDurability((short)0);
/* 1049: 935 */         if (((LivingEntity)getDefender).getEquipment().getChestplate().getItemMeta().hasLore())
/* 1050:     */         {
/* 1051: 937 */           String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 1052:     */           
/* 1053: 939 */           List<String> getItemLore = ((LivingEntity)getDefender).getEquipment().getChestplate().getItemMeta().getLore();
/* 1054: 941 */           for (String getDurability : getItemLore) {
/* 1055: 942 */             if (ChatColor.stripColor(getDurability).startsWith(durability))
/* 1056:     */             {
/* 1057: 943 */               int index = getItemLore.indexOf(getDurability);
/* 1058: 944 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durability.length()).split("/")[1].replace("]", "").trim();
/* 1059: 945 */               String currentValue = ChatColor.stripColor(getDurability).trim().replace("[", "").replace(":", "").trim().substring(durability.length()).split("/")[0].replace("]", "").trim();
/* 1060: 947 */               if (Integer.parseInt(currentValue) > 1)
/* 1061:     */               {
/* 1062: 948 */                 String durabilityRebuilder = durability + ": " + (Integer.parseInt(currentValue) - 1) + "/" + maximumValue;
/* 1063: 949 */                 getItemLore.set(index, durabilityRebuilder);
/* 1064:     */                 
/* 1065: 951 */                 ItemStack getChestItem = new ItemStack(((LivingEntity)getDefender).getEquipment().getChestplate());
/* 1066: 952 */                 ItemMeta getChestItemMeta = getChestItem.getItemMeta();
/* 1067:     */                 
/* 1068: 954 */                 getChestItemMeta.setLore(getItemLore);
/* 1069: 955 */                 getChestItem.setItemMeta(getChestItemMeta);
/* 1070:     */                 
/* 1071: 957 */                 ((LivingEntity)getDefender).getEquipment().setChestplate(new ItemStack(Material.AIR));
/* 1072: 958 */                 ((LivingEntity)getDefender).getEquipment().setChestplate(new ItemStack(getChestItem));
/* 1073:     */               }
/* 1074:     */               else
/* 1075:     */               {
/* 1076: 960 */                 ((LivingEntity)getDefender).getEquipment().setChestplate(new ItemStack(Material.AIR));
/* 1077:     */               }
/* 1078:     */             }
/* 1079:     */           }
/* 1080:     */         }
/* 1081:     */       }
/* 1082: 967 */       if ((((LivingEntity)getDefender).getEquipment().getLeggings() != null) && (ItemLoreStats.plugin.isLeggings(((LivingEntity)getDefender).getEquipment().getLeggings().getType())))
/* 1083:     */       {
/* 1084: 968 */         ((LivingEntity)getDefender).getEquipment().getLeggings().setDurability((short)0);
/* 1085: 969 */         if (((LivingEntity)getDefender).getEquipment().getLeggings().getItemMeta().hasLore())
/* 1086:     */         {
/* 1087: 971 */           String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 1088:     */           
/* 1089: 973 */           List<String> getItemLore = ((LivingEntity)getDefender).getEquipment().getLeggings().getItemMeta().getLore();
/* 1090: 975 */           for (String getDurability : getItemLore) {
/* 1091: 976 */             if (ChatColor.stripColor(getDurability).startsWith(durability))
/* 1092:     */             {
/* 1093: 977 */               int index = getItemLore.indexOf(getDurability);
/* 1094: 978 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durability.length()).split("/")[1].replace("]", "").trim();
/* 1095: 979 */               String currentValue = ChatColor.stripColor(getDurability).trim().replace("[", "").replace(":", "").trim().substring(durability.length()).split("/")[0].replace("]", "").trim();
/* 1096: 981 */               if (Integer.parseInt(currentValue) > 1)
/* 1097:     */               {
/* 1098: 982 */                 String durabilityRebuilder = durability + ": " + (Integer.parseInt(currentValue) - 1) + "/" + maximumValue;
/* 1099: 983 */                 getItemLore.set(index, durabilityRebuilder);
/* 1100:     */                 
/* 1101: 985 */                 ItemStack getLegsItem = new ItemStack(((LivingEntity)getDefender).getEquipment().getLeggings());
/* 1102: 986 */                 ItemMeta getLegsItemMeta = getLegsItem.getItemMeta();
/* 1103:     */                 
/* 1104: 988 */                 getLegsItemMeta.setLore(getItemLore);
/* 1105: 989 */                 getLegsItem.setItemMeta(getLegsItemMeta);
/* 1106:     */                 
/* 1107: 991 */                 ((LivingEntity)getDefender).getEquipment().setLeggings(new ItemStack(Material.AIR));
/* 1108: 992 */                 ((LivingEntity)getDefender).getEquipment().setLeggings(new ItemStack(getLegsItem));
/* 1109:     */               }
/* 1110:     */               else
/* 1111:     */               {
/* 1112: 994 */                 ((LivingEntity)getDefender).getEquipment().setLeggings(new ItemStack(Material.AIR));
/* 1113:     */               }
/* 1114:     */             }
/* 1115:     */           }
/* 1116:     */         }
/* 1117:     */       }
/* 1118:1001 */       if ((((LivingEntity)getDefender).getEquipment().getBoots() != null) && (ItemLoreStats.plugin.isBoots(((LivingEntity)getDefender).getEquipment().getBoots().getType())))
/* 1119:     */       {
/* 1120:1002 */         ((LivingEntity)getDefender).getEquipment().getBoots().setDurability((short)0);
/* 1121:1003 */         if (((LivingEntity)getDefender).getEquipment().getBoots().getItemMeta().hasLore())
/* 1122:     */         {
/* 1123:1005 */           String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 1124:     */           
/* 1125:1007 */           List<String> getItemLore = ((LivingEntity)getDefender).getEquipment().getBoots().getItemMeta().getLore();
/* 1126:1009 */           for (String getDurability : getItemLore) {
/* 1127:1010 */             if (ChatColor.stripColor(getDurability).startsWith(durability))
/* 1128:     */             {
/* 1129:1011 */               int index = getItemLore.indexOf(getDurability);
/* 1130:1012 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durability.length()).split("/")[1].replace("]", "").trim();
/* 1131:1013 */               String currentValue = ChatColor.stripColor(getDurability).trim().replace("[", "").replace(":", "").trim().substring(durability.length()).split("/")[0].replace("]", "").trim();
/* 1132:1015 */               if (Integer.parseInt(currentValue) > 1)
/* 1133:     */               {
/* 1134:1016 */                 String durabilityRebuilder = durability + ": " + (Integer.parseInt(currentValue) - 1) + "/" + maximumValue;
/* 1135:1017 */                 getItemLore.set(index, durabilityRebuilder);
/* 1136:     */                 
/* 1137:1019 */                 ItemStack getBootsItem = new ItemStack(((LivingEntity)getDefender).getEquipment().getBoots());
/* 1138:1020 */                 ItemMeta getBootsItemMeta = getBootsItem.getItemMeta();
/* 1139:     */                 
/* 1140:1022 */                 getBootsItemMeta.setLore(getItemLore);
/* 1141:1023 */                 getBootsItem.setItemMeta(getBootsItemMeta);
/* 1142:     */                 
/* 1143:1025 */                 ((LivingEntity)getDefender).getEquipment().setBoots(new ItemStack(Material.AIR));
/* 1144:1026 */                 ((LivingEntity)getDefender).getEquipment().setBoots(new ItemStack(getBootsItem));
/* 1145:     */               }
/* 1146:     */               else
/* 1147:     */               {
/* 1148:1028 */                 ((LivingEntity)getDefender).getEquipment().setBoots(new ItemStack(Material.AIR));
/* 1149:     */               }
/* 1150:     */             }
/* 1151:     */           }
/* 1152:     */         }
/* 1153:     */       }
/* 1154:     */     }
/* 1155:     */   }
/* 1156:     */   
/* 1157:     */   public void damageDealtMessage(Entity getAttacker, Entity getDefender, double damageDealt)
/* 1158:     */   {
/* 1159:1038 */     if ((getAttacker instanceof Player))
/* 1160:     */     {
/* 1161:1039 */       if ((getDefender instanceof Player))
/* 1162:     */       {
/* 1163:1040 */         if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.damageTaken")) {
/* 1164:1041 */           ((Player)getDefender).sendMessage(ChatColor.WHITE + "      " + ((Player)getAttacker).getName() + ChatColor.LIGHT_PURPLE + " hit you for " + ChatColor.GOLD + Double.valueOf(damageDealt).intValue() + ChatColor.LIGHT_PURPLE + " damage.");
/* 1165:     */         }
/* 1166:     */       }
/* 1167:1044 */       else if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.damageDone")) {
/* 1168:1045 */         ((Player)getAttacker).sendMessage(ChatColor.LIGHT_PURPLE + "      You hit a " + ChatColor.RESET + getDefender.getType().toString().substring(0, 1) + getDefender.getType().toString().substring(1, getDefender.getType().toString().length()).toLowerCase() + ChatColor.LIGHT_PURPLE + " for " + ChatColor.GOLD + Double.valueOf(damageDealt).intValue() + ChatColor.LIGHT_PURPLE + " damage.");
/* 1169:     */       }
/* 1170:     */     }
/* 1171:1049 */     else if (((getDefender instanceof Player)) && 
/* 1172:1050 */       (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.damageTaken"))) {
/* 1173:1051 */       ((Player)getDefender).sendMessage(ChatColor.WHITE + "      " + getAttacker.getType().toString().substring(0, 1) + getAttacker.getType().toString().substring(1, getAttacker.getType().toString().length()).toLowerCase() + ChatColor.LIGHT_PURPLE + " hit you for " + ChatColor.GOLD + Double.valueOf(damageDealt).intValue() + ChatColor.LIGHT_PURPLE + " damage.");
/* 1174:     */     }
/* 1175:     */   }
/* 1176:     */   
/* 1177:     */   public int enchantmentDurabilityLoss(ItemStack getItem)
/* 1178:     */   {
/* 1179:1059 */     int r = random(1000);
/* 1180:1061 */     if (getItem.containsEnchantment(Enchantment.DURABILITY)) {
/* 1181:1062 */       if (getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 1)
/* 1182:     */       {
/* 1183:1063 */         if (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingOne") > 0)
/* 1184:     */         {
/* 1185:1064 */           if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingOne") * 10) {
/* 1186:1065 */             return 0;
/* 1187:     */           }
/* 1188:1067 */           return 1;
/* 1189:     */         }
/* 1190:     */       }
/* 1191:1070 */       else if (getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 2)
/* 1192:     */       {
/* 1193:1071 */         if (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingTwo") > 0)
/* 1194:     */         {
/* 1195:1072 */           if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingTwo") * 10) {
/* 1196:1073 */             return 0;
/* 1197:     */           }
/* 1198:1075 */           return 1;
/* 1199:     */         }
/* 1200:     */       }
/* 1201:1078 */       else if ((getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 3) && 
/* 1202:1079 */         (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingThree") > 0))
/* 1203:     */       {
/* 1204:1080 */         if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingThree") * 10) {
/* 1205:1081 */           return 0;
/* 1206:     */         }
/* 1207:1083 */         return 1;
/* 1208:     */       }
/* 1209:     */     }
/* 1210:1088 */     return 0;
/* 1211:     */   }
/* 1212:     */   
/* 1213:     */   public String pvpModifiedDamage(Player player)
/* 1214:     */   {
/* 1215:1092 */     if (this.gearStats.getPvPDamageModifierItemInHand(player).contains("+")) {
/* 1216:1093 */       return "add_" + this.gearStats.getPvPDamageModifierItemInHand(player).substring(1);
/* 1217:     */     }
/* 1218:1094 */     if (this.gearStats.getPvPDamageModifierItemInHand(player).contains("-")) {
/* 1219:1095 */       return "remove_" + this.gearStats.getPvPDamageModifierItemInHand(player).substring(1);
/* 1220:     */     }
/* 1221:1097 */     return "add_0";
/* 1222:     */   }
/* 1223:     */   
/* 1224:     */   public String pveModifiedDamage(Player player)
/* 1225:     */   {
/* 1226:1101 */     if (this.gearStats.getPvEDamageModifierItemInHand(player).contains("+")) {
/* 1227:1102 */       return "add_" + this.gearStats.getPvEDamageModifierItemInHand(player).substring(1);
/* 1228:     */     }
/* 1229:1103 */     if (this.gearStats.getPvEDamageModifierItemInHand(player).contains("-")) {
/* 1230:1104 */       return "remove_" + this.gearStats.getPvEDamageModifierItemInHand(player).substring(1);
/* 1231:     */     }
/* 1232:1106 */     return "add_0";
/* 1233:     */   }
/* 1234:     */   
/* 1235:     */   public double attackerDamage(Player player, Entity getDefender, EntityType entityType, double weaponDamage)
/* 1236:     */   {
/* 1237:1112 */     double valueMin = 0.0D;
/* 1238:1113 */     double valueMax = 0.0D;
/* 1239:1114 */     double valueRand = 0.0D;
/* 1240:1115 */     double modifier = 0.0D;
/* 1241:1118 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType()))
/* 1242:     */     {
/* 1243:1119 */       valueMin = Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[0]) + Double.parseDouble(this.gearStats.getDamageItemInHand(player).split("-")[0]) + modifier;
/* 1244:1120 */       valueMax = Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[1]) + Double.parseDouble(this.gearStats.getDamageItemInHand(player).split("-")[1]) + modifier;
/* 1245:     */     }
/* 1246:     */     else
/* 1247:     */     {
/* 1248:1122 */       valueMin = Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[0]) + modifier;
/* 1249:1123 */       valueMax = Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[1]) + modifier;
/* 1250:     */     }
/* 1251:1126 */     if ((valueMin > 0.0D) && (valueMax > 0.0D))
/* 1252:     */     {
/* 1253:1127 */       double damage = Double.parseDouble(randomRange(valueMin, valueMax));
/* 1254:1129 */       if (criticalStrike(player, getDefender) > 1)
/* 1255:     */       {
/* 1256:1130 */         if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 1257:1131 */           valueRand = damage + damage * (ItemLoreStats.plugin.getConfig().getDouble("baseCritDamage") + (this.gearStats.getCritDamageGear(player) + this.gearStats.getCritDamageItemInHand(player))) / 100.0D;
/* 1258:     */         } else {
/* 1259:1133 */           valueRand = damage + damage * (ItemLoreStats.plugin.getConfig().getDouble("baseCritDamage") + this.gearStats.getCritDamageGear(player)) / 100.0D;
/* 1260:     */         }
/* 1261:     */       }
/* 1262:     */       else {
/* 1263:1136 */         valueRand = damage;
/* 1264:     */       }
/* 1265:     */     }
/* 1266:     */     else
/* 1267:     */     {
/* 1268:1139 */       valueRand = weaponDamage + modifier;
/* 1269:     */     }
/* 1270:1142 */     if ((getDefender instanceof Player))
/* 1271:     */     {
/* 1272:1143 */       if (pvpModifiedDamage(player).contains("add_"))
/* 1273:     */       {
/* 1274:1144 */         double modifyValue = valueRand + Double.parseDouble(pvpModifiedDamage(player).split("\\_")[1]);
/* 1275:1145 */         return modifyValue;
/* 1276:     */       }
/* 1277:1147 */       if (pvpModifiedDamage(player).contains("remove_"))
/* 1278:     */       {
/* 1279:1149 */         double modifyValue = valueRand - Double.parseDouble(pvpModifiedDamage(player).split("\\_")[1]);
/* 1280:1151 */         if (modifyValue <= 0.0D)
/* 1281:     */         {
/* 1282:1152 */           modifyValue = 0.0D;
/* 1283:1153 */           return modifyValue;
/* 1284:     */         }
/* 1285:1155 */         return modifyValue;
/* 1286:     */       }
/* 1287:     */     }
/* 1288:1158 */     else if ((getDefender instanceof LivingEntity))
/* 1289:     */     {
/* 1290:1159 */       if (pveModifiedDamage(player).contains("add_"))
/* 1291:     */       {
/* 1292:1160 */         double modifyValue = valueRand + Double.parseDouble(pveModifiedDamage(player).split("\\_")[1]);
/* 1293:     */         
/* 1294:1162 */         return modifyValue;
/* 1295:     */       }
/* 1296:1163 */       if (pveModifiedDamage(player).contains("remove_"))
/* 1297:     */       {
/* 1298:1164 */         double modifyValue = valueRand - Double.parseDouble(pveModifiedDamage(player).split("\\_")[1]);
/* 1299:1166 */         if (modifyValue <= 0.0D)
/* 1300:     */         {
/* 1301:1167 */           modifyValue = 0.0D;
/* 1302:1168 */           return modifyValue;
/* 1303:     */         }
/* 1304:1170 */         return modifyValue;
/* 1305:     */       }
/* 1306:     */     }
/* 1307:     */     else
/* 1308:     */     {
/* 1309:1174 */       return valueRand;
/* 1310:     */     }
/* 1311:1176 */     return valueRand;
/* 1312:     */   }
/* 1313:     */   
/* 1314:     */   public double defenderArmour(Entity getDefender)
/* 1315:     */   {
/* 1316:1180 */     if ((getDefender instanceof Player))
/* 1317:     */     {
/* 1318:1181 */       double modifier = this.setBonuses.checkHashMapArmour((Player)getDefender);
/* 1319:1183 */       if (ItemLoreStats.plugin.isTool(((Player)getDefender).getItemInHand().getType())) {
/* 1320:1184 */         return this.gearStats.getArmourGear((Player)getDefender) + this.gearStats.getArmourItemInHand((Player)getDefender) + modifier;
/* 1321:     */       }
/* 1322:1186 */       return this.gearStats.getArmourGear((Player)getDefender) + modifier;
/* 1323:     */     }
/* 1324:1189 */     return 0.0D;
/* 1325:     */   }
/* 1326:     */   
/* 1327:     */   public boolean defenderDodge(Entity getDefender)
/* 1328:     */   {
/* 1329:1192 */     if (!(getDefender instanceof Player)) {
/* 1330:1192 */       return false;
/* 1331:     */     }
/* 1332:1194 */     double dodgePercent = 0.0D;
/* 1333:1195 */     double modifier = this.setBonuses.checkHashMapDodge((Player)getDefender);
/* 1334:1197 */     if (ItemLoreStats.plugin.isTool(((Player)getDefender).getItemInHand().getType())) {
/* 1335:1198 */       dodgePercent = this.gearStats.getDodgeGear((Player)getDefender) + this.gearStats.getDodgeItemInHand((Player)getDefender) + modifier;
/* 1336:     */     } else {
/* 1337:1200 */       dodgePercent = this.gearStats.getDodgeGear((Player)getDefender) + modifier;
/* 1338:     */     }
/* 1339:1203 */     if (dodgePercent > 100.0D) {
/* 1340:1204 */       dodgePercent = 100.0D;
/* 1341:     */     }
/* 1342:1207 */     if (dodgePercent >= random(100)) {
/* 1343:1208 */       return true;
/* 1344:     */     }
/* 1345:1210 */     return false;
/* 1346:     */   }
/* 1347:     */   
/* 1348:     */   public boolean defenderBlock(Entity getDefender)
/* 1349:     */   {
/* 1350:1214 */     if (!(getDefender instanceof Player)) {
/* 1351:1214 */       return false;
/* 1352:     */     }
/* 1353:1216 */     double blockPercent = 0.0D;
/* 1354:1217 */     double modifier = this.setBonuses.checkHashMapBlock((Player)getDefender);
/* 1355:1219 */     if (ItemLoreStats.plugin.isTool(((Player)getDefender).getItemInHand().getType())) {
/* 1356:1220 */       blockPercent = this.gearStats.getBlockGear((Player)getDefender) + this.gearStats.getBlockItemInHand((Player)getDefender) + modifier;
/* 1357:     */     } else {
/* 1358:1222 */       blockPercent = this.gearStats.getBlockGear((Player)getDefender) + modifier;
/* 1359:     */     }
/* 1360:1225 */     if (blockPercent > 100.0D) {
/* 1361:1226 */       blockPercent = 100.0D;
/* 1362:     */     }
/* 1363:1229 */     if (blockPercent >= random(100)) {
/* 1364:1230 */       return true;
/* 1365:     */     }
/* 1366:1232 */     return false;
/* 1367:     */   }
/* 1368:     */   
/* 1369:     */   public void blind(Entity getDefender, Entity getAttacker)
/* 1370:     */   {
/* 1371:1237 */     if (!(getAttacker instanceof Player)) {
/* 1372:1237 */       return;
/* 1373:     */     }
/* 1374:1239 */     double blindPercent = 0.0D;
/* 1375:1240 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1376:1241 */     double modifier = this.setBonuses.checkHashMapBlind((Player)getAttacker);
/* 1377:1243 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1378:1244 */       blindPercent = this.gearStats.getBlindGear((Player)getAttacker) + this.gearStats.getBlindItemInHand((Player)getAttacker) + modifier;
/* 1379:     */     } else {
/* 1380:1246 */       blindPercent = this.gearStats.getBlindGear((Player)getAttacker) + modifier;
/* 1381:     */     }
/* 1382:1249 */     if (blindPercent > 100.0D) {
/* 1383:1250 */       blindPercent = 100.0D;
/* 1384:     */     }
/* 1385:1253 */     if (random(100) <= blindPercent)
/* 1386:     */     {
/* 1387:1254 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.blind")) {
/* 1388:1255 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.BlindSuccess"));
/* 1389:     */       }
/* 1390:1257 */       if (((getDefender instanceof Player)) && 
/* 1391:1258 */         (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyBlind"))) {
/* 1392:1259 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyBlindSuccess"));
/* 1393:     */       }
/* 1394:1262 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
/* 1395:     */     }
/* 1396:     */   }
/* 1397:     */   
/* 1398:     */   public double lifeSteal(Entity getDefender, Entity getAttacker, double weaponDamage)
/* 1399:     */   {
/* 1400:1266 */     if (!(getAttacker instanceof Player)) {
/* 1401:1266 */       return 0.0D;
/* 1402:     */     }
/* 1403:1268 */     double lifeStealHeal = 0.0D;
/* 1404:1269 */     double lifeStealPercent = 0.0D;
/* 1405:1270 */     double modifier = this.setBonuses.checkHashMapLifeSteal((Player)getAttacker);
/* 1406:1272 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1407:1273 */       lifeStealPercent = this.gearStats.getLifeStealGear((Player)getAttacker) + this.gearStats.getLifeStealItemInHand((Player)getAttacker) + modifier;
/* 1408:     */     } else {
/* 1409:1275 */       lifeStealPercent = this.gearStats.getLifeStealGear((Player)getAttacker) + modifier;
/* 1410:     */     }
/* 1411:1278 */     if (lifeStealPercent > 100.0D) {
/* 1412:1279 */       lifeStealPercent = 100.0D;
/* 1413:     */     }
/* 1414:1282 */     if (random(100) <= lifeStealPercent)
/* 1415:     */     {
/* 1416:1283 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.lifeSteal")) {
/* 1417:1284 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.LifeStealSuccess"));
/* 1418:     */       }
/* 1419:1286 */       if (((getDefender instanceof Player)) && 
/* 1420:1287 */         (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyLifeSteal"))) {
/* 1421:1288 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyLifeStealSuccess"));
/* 1422:     */       }
/* 1423:1291 */       lifeStealHeal = ItemLoreStats.plugin.getConfig().getDouble("lifeStealHeal") * weaponDamage;
/* 1424:1292 */       return lifeStealHeal;
/* 1425:     */     }
/* 1426:1294 */     lifeStealHeal = 0.0D;
/* 1427:1295 */     return lifeStealHeal;
/* 1428:     */   }
/* 1429:     */   
/* 1430:     */   public void fire(Entity getDefender, Entity getAttacker)
/* 1431:     */   {
/* 1432:1299 */     if (!(getAttacker instanceof Player)) {
/* 1433:1299 */       return;
/* 1434:     */     }
/* 1435:1301 */     double firePercent = 0.0D;
/* 1436:1302 */     double modifier = this.setBonuses.checkHashMapFire((Player)getAttacker);
/* 1437:1304 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1438:1305 */       firePercent = this.gearStats.getFireGear((Player)getAttacker) + this.gearStats.getFireItemInHand((Player)getAttacker) + modifier;
/* 1439:     */     } else {
/* 1440:1307 */       firePercent = this.gearStats.getFireGear((Player)getAttacker) + modifier;
/* 1441:     */     }
/* 1442:1310 */     if (firePercent > 100.0D) {
/* 1443:1311 */       firePercent = 100.0D;
/* 1444:     */     }
/* 1445:1314 */     if (random(100) <= firePercent)
/* 1446:     */     {
/* 1447:1315 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.fire")) {
/* 1448:1316 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.FireSuccess"));
/* 1449:     */       }
/* 1450:1318 */       if (((getDefender instanceof Player)) && 
/* 1451:1319 */         (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyFire"))) {
/* 1452:1320 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyFireSuccess"));
/* 1453:     */       }
/* 1454:1323 */       getDefender.setFireTicks(60);
/* 1455:     */     }
/* 1456:     */   }
/* 1457:     */   
/* 1458:     */   public void poison(Entity getDefender, Entity getAttacker)
/* 1459:     */   {
/* 1460:1327 */     if (!(getAttacker instanceof Player)) {
/* 1461:1327 */       return;
/* 1462:     */     }
/* 1463:1329 */     double poisonPercent = 0.0D;
/* 1464:1330 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1465:1331 */     double modifier = this.setBonuses.checkHashMapPoison((Player)getAttacker);
/* 1466:1333 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1467:1334 */       poisonPercent = this.gearStats.getPoisonGear((Player)getAttacker) + this.gearStats.getPoisonItemInHand((Player)getAttacker) + modifier;
/* 1468:     */     } else {
/* 1469:1336 */       poisonPercent = this.gearStats.getPoisonGear((Player)getAttacker) + modifier;
/* 1470:     */     }
/* 1471:1339 */     if (poisonPercent > 100.0D) {
/* 1472:1340 */       poisonPercent = 100.0D;
/* 1473:     */     }
/* 1474:1343 */     if (random(100) <= poisonPercent)
/* 1475:     */     {
/* 1476:1344 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.poison")) {
/* 1477:1345 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.PoisonSuccess"));
/* 1478:     */       }
/* 1479:1347 */       if (((getDefender instanceof Player)) && 
/* 1480:1348 */         (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyPoison"))) {
/* 1481:1349 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyPoisonSuccess"));
/* 1482:     */       }
/* 1483:1352 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
/* 1484:     */     }
/* 1485:     */   }
/* 1486:     */   
/* 1487:     */   public void wither(Entity getDefender, Entity getAttacker)
/* 1488:     */   {
/* 1489:1356 */     if (!(getAttacker instanceof Player)) {
/* 1490:1356 */       return;
/* 1491:     */     }
/* 1492:1358 */     double witherPercent = 0.0D;
/* 1493:1359 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1494:1360 */     double modifier = this.setBonuses.checkHashMapWither((Player)getAttacker);
/* 1495:1362 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1496:1363 */       witherPercent = this.gearStats.getWitherGear((Player)getAttacker) + this.gearStats.getWitherItemInHand((Player)getAttacker) + modifier;
/* 1497:     */     } else {
/* 1498:1365 */       witherPercent = this.gearStats.getWitherGear((Player)getAttacker) + modifier;
/* 1499:     */     }
/* 1500:1368 */     if (witherPercent > 100.0D) {
/* 1501:1369 */       witherPercent = 100.0D;
/* 1502:     */     }
/* 1503:1372 */     if (random(100) <= witherPercent)
/* 1504:     */     {
/* 1505:1373 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.wither")) {
/* 1506:1374 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.WitherSuccess"));
/* 1507:     */       }
/* 1508:1376 */       if (((getDefender instanceof Player)) && 
/* 1509:1377 */         (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyWither"))) {
/* 1510:1378 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyWitherSuccess"));
/* 1511:     */       }
/* 1512:1381 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
/* 1513:     */     }
/* 1514:     */   }
/* 1515:     */   
/* 1516:     */   public void harming(Entity getDefender, Entity getAttacker)
/* 1517:     */   {
/* 1518:1385 */     if (!(getAttacker instanceof Player)) {
/* 1519:1385 */       return;
/* 1520:     */     }
/* 1521:1387 */     double harmingPercent = 0.0D;
/* 1522:1388 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1523:1389 */     double modifier = this.setBonuses.checkHashMapHarming((Player)getAttacker);
/* 1524:1391 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1525:1392 */       harmingPercent = this.gearStats.getHarmingGear((Player)getAttacker) + this.gearStats.getHarmingItemInHand((Player)getAttacker) + modifier;
/* 1526:     */     } else {
/* 1527:1394 */       harmingPercent = this.gearStats.getHarmingGear((Player)getAttacker) + modifier;
/* 1528:     */     }
/* 1529:1397 */     if (harmingPercent > 100.0D) {
/* 1530:1398 */       harmingPercent = 100.0D;
/* 1531:     */     }
/* 1532:1401 */     if (random(100) <= harmingPercent)
/* 1533:     */     {
/* 1534:1402 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.harm")) {
/* 1535:1403 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.HarmSuccess"));
/* 1536:     */       }
/* 1537:1405 */       if (((getDefender instanceof Player)) && 
/* 1538:1406 */         (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyHarm"))) {
/* 1539:1407 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyHarmSuccess"));
/* 1540:     */       }
/* 1541:1410 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 60, 1));
/* 1542:     */     }
/* 1543:     */   }
/* 1544:     */   
/* 1545:     */   public void ice(Entity getDefender, Entity getAttacker)
/* 1546:     */   {
/* 1547:1414 */     if (!(getAttacker instanceof Player)) {
/* 1548:1414 */       return;
/* 1549:     */     }
/* 1550:1416 */     double icePercent = 0.0D;
/* 1551:1417 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1552:1418 */     double modifier = this.setBonuses.checkHashMapIce((Player)getAttacker);
/* 1553:1420 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1554:1421 */       icePercent = this.gearStats.getIceGear((Player)getAttacker) + this.gearStats.getIceItemInHand((Player)getAttacker) + modifier;
/* 1555:     */     } else {
/* 1556:1423 */       icePercent = this.gearStats.getIceGear((Player)getAttacker) + modifier;
/* 1557:     */     }
/* 1558:1426 */     if (icePercent > 100.0D) {
/* 1559:1427 */       icePercent = 100.0D;
/* 1560:     */     }
/* 1561:1430 */     if (random(100) <= icePercent)
/* 1562:     */     {
/* 1563:1431 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.ice")) {
/* 1564:1432 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.IceSuccess"));
/* 1565:     */       }
/* 1566:1434 */       if (((getDefender instanceof Player)) && 
/* 1567:1435 */         (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyIce"))) {
/* 1568:1436 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyIceSuccess"));
/* 1569:     */       }
/* 1570:1439 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
/* 1571:     */     }
/* 1572:     */   }
/* 1573:     */   
/* 1574:     */   public int criticalStrike(Entity getAttacker, Entity getDefender)
/* 1575:     */   {
/* 1576:1443 */     int critMultiplier = 1;
/* 1577:1444 */     double critPercent = 0.0D;
/* 1578:1445 */     double modifier = this.setBonuses.checkHashMapCritChance((Player)getAttacker);
/* 1579:1449 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1580:1450 */       critPercent = this.gearStats.getCritChanceGear((Player)getAttacker) + this.gearStats.getCritChanceItemInHand((Player)getAttacker) + modifier;
/* 1581:     */     } else {
/* 1582:1452 */       critPercent = this.gearStats.getCritChanceGear((Player)getAttacker) + modifier;
/* 1583:     */     }
/* 1584:1455 */     if (critPercent >= random(100))
/* 1585:     */     {
/* 1586:1456 */       if ((ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.critStrike")) && 
/* 1587:1457 */         ((getAttacker instanceof Player))) {
/* 1588:1458 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.CriticalStrikeSuccess"));
/* 1589:     */       }
/* 1590:1461 */       if (((getDefender instanceof Player)) && 
/* 1591:1462 */         (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyCritStrike")) && 
/* 1592:1463 */         ((getAttacker instanceof Player))) {
/* 1593:1464 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyCriticalStrikeSuccess"));
/* 1594:     */       }
/* 1595:1468 */       critMultiplier = 2;
/* 1596:1469 */       return critMultiplier;
/* 1597:     */     }
/* 1598:1472 */     critMultiplier = 1;
/* 1599:1473 */     return critMultiplier;
/* 1600:     */   }
/* 1601:     */   
/* 1602:     */   public double reflect(Entity getAttacker)
/* 1603:     */   {
/* 1604:1477 */     double reflect = 0.0D;
/* 1605:1478 */     double modifier = this.setBonuses.checkHashMapReflect((Player)getAttacker);
/* 1606:1480 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1607:1481 */       reflect = this.gearStats.getReflectGear((Player)getAttacker) + this.gearStats.getReflectItemInHand((Player)getAttacker) + modifier;
/* 1608:     */     } else {
/* 1609:1483 */       reflect = this.gearStats.getReflectGear((Player)getAttacker) + modifier;
/* 1610:     */     }
/* 1611:1486 */     return reflect;
/* 1612:     */   }
/* 1613:     */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.DamageSystem
 * JD-Core Version:    0.7.0.1
 */