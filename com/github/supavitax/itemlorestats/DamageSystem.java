/*    1:     */ package com.github.supavitax.itemlorestats;
/*    2:     */ 
/*    3:     */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*    4:     */ import com.github.supavitax.itemlorestats.Util.Util_WorldGuard;
/*    5:     */ import com.sucy.skill.api.PlayerSkills;
/*    6:     */ import java.io.File;
/*    7:     */ import java.io.PrintStream;
/*    8:     */ import java.text.DecimalFormat;
/*    9:     */ import java.text.NumberFormat;
/*   10:     */ import java.util.List;
/*   11:     */ import java.util.Locale;
/*   12:     */ import java.util.Random;
/*   13:     */ import java.util.Stack;
/*   14:     */ import org.bukkit.ChatColor;
/*   15:     */ import org.bukkit.Effect;
/*   16:     */ import org.bukkit.Material;
/*   17:     */ import org.bukkit.World;
/*   18:     */ import org.bukkit.configuration.file.FileConfiguration;
/*   19:     */ import org.bukkit.configuration.file.YamlConfiguration;
/*   20:     */ import org.bukkit.enchantments.Enchantment;
/*   21:     */ import org.bukkit.entity.Arrow;
/*   22:     */ import org.bukkit.entity.Entity;
/*   23:     */ import org.bukkit.entity.EntityType;
/*   24:     */ import org.bukkit.entity.ItemFrame;
/*   25:     */ import org.bukkit.entity.LargeFireball;
/*   26:     */ import org.bukkit.entity.LivingEntity;
/*   27:     */ import org.bukkit.entity.Player;
/*   28:     */ import org.bukkit.entity.SmallFireball;
/*   29:     */ import org.bukkit.event.EventHandler;
/*   30:     */ import org.bukkit.event.Listener;
/*   31:     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*   32:     */ import org.bukkit.inventory.EntityEquipment;
/*   33:     */ import org.bukkit.inventory.ItemStack;
/*   34:     */ import org.bukkit.inventory.PlayerInventory;
/*   35:     */ import org.bukkit.inventory.meta.ItemMeta;
/*   36:     */ import org.bukkit.potion.PotionEffect;
/*   37:     */ import org.bukkit.potion.PotionEffectType;
/*   38:     */ 
/*   39:     */ public class DamageSystem
/*   40:     */   implements Listener
/*   41:     */ {
/*   42:     */   public ItemLoreStats instance;
/*   43:     */   private FileConfiguration PlayerDataConfig;
/*   44:  43 */   GearStats gearStats = new GearStats();
/*   45:  44 */   SetBonuses setBonuses = new SetBonuses();
/*   46:  45 */   Util_Colours util_Colours = new Util_Colours();
/*   47:     */   
/*   48:     */   public DamageSystem(ItemLoreStats i)
/*   49:     */   {
/*   50:  48 */     this.instance = i;
/*   51:     */   }
/*   52:     */   
/*   53:     */   private int random(int length)
/*   54:     */   {
/*   55:  52 */     return new Random().nextInt(length) + 1;
/*   56:     */   }
/*   57:     */   
/*   58:     */   private String randomRange(double min, double max)
/*   59:     */   {
/*   60:  56 */     Locale forceLocale = new Locale("en", "UK");
/*   61:  57 */     String decimalPattern = "#.#";
/*   62:     */     
/*   63:  59 */     DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(forceLocale);
/*   64:  60 */     decimalFormat.applyPattern(decimalPattern);
/*   65:     */     
/*   66:  62 */     String format = decimalFormat.format(min + Math.random() * (max - min));
/*   67:     */     
/*   68:  64 */     return format;
/*   69:     */   }
/*   70:     */   
/*   71:     */   public int materialToId(Material mat)
/*   72:     */   {
/*   73:  69 */     if (mat == Material.IRON_HOE) {
/*   74:  70 */       return 256;
/*   75:     */     }
/*   76:  71 */     if (mat == Material.IRON_PICKAXE) {
/*   77:  72 */       return 257;
/*   78:     */     }
/*   79:  73 */     if (mat == Material.IRON_AXE) {
/*   80:  74 */       return 258;
/*   81:     */     }
/*   82:  75 */     if (mat == Material.BOW) {
/*   83:  76 */       return 261;
/*   84:     */     }
/*   85:  77 */     if (mat == Material.IRON_SWORD) {
/*   86:  78 */       return 267;
/*   87:     */     }
/*   88:  79 */     if (mat == Material.WOOD_SWORD) {
/*   89:  80 */       return 268;
/*   90:     */     }
/*   91:  81 */     if (mat == Material.WOOD_SPADE) {
/*   92:  82 */       return 269;
/*   93:     */     }
/*   94:  83 */     if (mat == Material.WOOD_PICKAXE) {
/*   95:  84 */       return 270;
/*   96:     */     }
/*   97:  85 */     if (mat == Material.WOOD_AXE) {
/*   98:  86 */       return 271;
/*   99:     */     }
/*  100:  87 */     if (mat == Material.STONE_SWORD) {
/*  101:  88 */       return 272;
/*  102:     */     }
/*  103:  89 */     if (mat == Material.STONE_SPADE) {
/*  104:  90 */       return 273;
/*  105:     */     }
/*  106:  91 */     if (mat == Material.STONE_PICKAXE) {
/*  107:  92 */       return 274;
/*  108:     */     }
/*  109:  93 */     if (mat == Material.STONE_AXE) {
/*  110:  94 */       return 275;
/*  111:     */     }
/*  112:  95 */     if (mat == Material.DIAMOND_SWORD) {
/*  113:  96 */       return 276;
/*  114:     */     }
/*  115:  97 */     if (mat == Material.DIAMOND_SPADE) {
/*  116:  98 */       return 277;
/*  117:     */     }
/*  118:  99 */     if (mat == Material.DIAMOND_PICKAXE) {
/*  119: 100 */       return 278;
/*  120:     */     }
/*  121: 101 */     if (mat == Material.DIAMOND_AXE) {
/*  122: 102 */       return 279;
/*  123:     */     }
/*  124: 103 */     if (mat == Material.STICK) {
/*  125: 104 */       return 280;
/*  126:     */     }
/*  127: 105 */     if (mat == Material.GOLD_SWORD) {
/*  128: 106 */       return 283;
/*  129:     */     }
/*  130: 107 */     if (mat == Material.GOLD_SPADE) {
/*  131: 108 */       return 284;
/*  132:     */     }
/*  133: 109 */     if (mat == Material.GOLD_PICKAXE) {
/*  134: 110 */       return 285;
/*  135:     */     }
/*  136: 111 */     if (mat == Material.GOLD_AXE) {
/*  137: 112 */       return 286;
/*  138:     */     }
/*  139: 113 */     if (mat == Material.WOOD_HOE) {
/*  140: 114 */       return 290;
/*  141:     */     }
/*  142: 115 */     if (mat == Material.STONE_HOE) {
/*  143: 116 */       return 291;
/*  144:     */     }
/*  145: 117 */     if (mat == Material.IRON_HOE) {
/*  146: 118 */       return 292;
/*  147:     */     }
/*  148: 119 */     if (mat == Material.DIAMOND_HOE) {
/*  149: 120 */       return 293;
/*  150:     */     }
/*  151: 121 */     if (mat == Material.GOLD_HOE) {
/*  152: 122 */       return 294;
/*  153:     */     }
/*  154: 123 */     if (mat == Material.LEATHER_HELMET) {
/*  155: 124 */       return 298;
/*  156:     */     }
/*  157: 125 */     if (mat == Material.LEATHER_CHESTPLATE) {
/*  158: 126 */       return 299;
/*  159:     */     }
/*  160: 127 */     if (mat == Material.LEATHER_LEGGINGS) {
/*  161: 128 */       return 300;
/*  162:     */     }
/*  163: 129 */     if (mat == Material.LEATHER_BOOTS) {
/*  164: 130 */       return 301;
/*  165:     */     }
/*  166: 131 */     if (mat == Material.CHAINMAIL_HELMET) {
/*  167: 132 */       return 302;
/*  168:     */     }
/*  169: 133 */     if (mat == Material.CHAINMAIL_CHESTPLATE) {
/*  170: 134 */       return 303;
/*  171:     */     }
/*  172: 135 */     if (mat == Material.CHAINMAIL_LEGGINGS) {
/*  173: 136 */       return 304;
/*  174:     */     }
/*  175: 137 */     if (mat == Material.CHAINMAIL_BOOTS) {
/*  176: 138 */       return 305;
/*  177:     */     }
/*  178: 139 */     if (mat == Material.IRON_HELMET) {
/*  179: 140 */       return 306;
/*  180:     */     }
/*  181: 141 */     if (mat == Material.IRON_CHESTPLATE) {
/*  182: 142 */       return 307;
/*  183:     */     }
/*  184: 143 */     if (mat == Material.IRON_LEGGINGS) {
/*  185: 144 */       return 308;
/*  186:     */     }
/*  187: 145 */     if (mat == Material.IRON_BOOTS) {
/*  188: 146 */       return 309;
/*  189:     */     }
/*  190: 147 */     if (mat == Material.DIAMOND_HELMET) {
/*  191: 148 */       return 310;
/*  192:     */     }
/*  193: 149 */     if (mat == Material.DIAMOND_CHESTPLATE) {
/*  194: 150 */       return 311;
/*  195:     */     }
/*  196: 151 */     if (mat == Material.DIAMOND_LEGGINGS) {
/*  197: 152 */       return 312;
/*  198:     */     }
/*  199: 153 */     if (mat == Material.DIAMOND_BOOTS) {
/*  200: 154 */       return 313;
/*  201:     */     }
/*  202: 155 */     if (mat == Material.GOLD_HELMET) {
/*  203: 156 */       return 314;
/*  204:     */     }
/*  205: 157 */     if (mat == Material.GOLD_CHESTPLATE) {
/*  206: 158 */       return 315;
/*  207:     */     }
/*  208: 159 */     if (mat == Material.GOLD_LEGGINGS) {
/*  209: 160 */       return 316;
/*  210:     */     }
/*  211: 161 */     if (mat == Material.GOLD_BOOTS) {
/*  212: 162 */       return 317;
/*  213:     */     }
/*  214: 165 */     return 1337;
/*  215:     */   }
/*  216:     */   
/*  217:     */   public String getResponse(String getKeyFromLanguageFile)
/*  218:     */   {
/*  219:     */     try
/*  220:     */     {
/*  221: 171 */       this.PlayerDataConfig = new YamlConfiguration();
/*  222: 172 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*  223:     */       
/*  224: 174 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*  225:     */     }
/*  226:     */     catch (Exception e)
/*  227:     */     {
/*  228: 177 */       e.printStackTrace();
/*  229: 178 */       System.out.println("*********** Failed to load message from language file! ***********");
/*  230:     */     }
/*  231: 180 */     return "*********** Failed to load message from language file! ***********";
/*  232:     */   }
/*  233:     */   
/*  234:     */   @EventHandler
/*  235:     */   public void onEntityDamage(EntityDamageByEntityEvent event)
/*  236:     */   {
/*  237: 187 */     if (PlayerSkills.skillsBeingCast.size() > 0) {
/*  238: 189 */       return;
/*  239:     */     }
/*  240: 191 */     if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(event.getDamager().getWorld().getName()))
/*  241:     */     {
/*  242: 192 */       if ((event.getEntity().hasMetadata("NPC")) || ((event.getEntity() instanceof ItemFrame)) || ((event.getEntity() instanceof LargeFireball)) || ((event.getEntity() instanceof SmallFireball))) {
/*  243: 192 */         return;
/*  244:     */       }
/*  245: 194 */       if (((event.getDamager() instanceof Player)) || ((event.getDamager() instanceof Arrow)))
/*  246:     */       {
/*  247: 195 */         Player getAttacker = null;
/*  248: 197 */         if ((event.getDamager() instanceof Arrow))
/*  249:     */         {
/*  250: 198 */           Arrow arrow = (Arrow)event.getDamager();
/*  251: 199 */           Entity shooter = arrow.getShooter();
/*  252: 200 */           if (!(shooter instanceof Player))
/*  253:     */           {
/*  254: 201 */             if ((event.getEntity() instanceof Player))
/*  255:     */             {
/*  256: 203 */               if (defenderDodge((Player)event.getEntity()))
/*  257:     */               {
/*  258: 204 */                 if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.dodgeAttack")) {
/*  259: 205 */                   ((Player)event.getEntity()).sendMessage(getResponse("DamageMessages.DodgeSuccess"));
/*  260:     */                 }
/*  261: 207 */                 event.setCancelled(true);
/*  262: 208 */                 return;
/*  263:     */               }
/*  264: 211 */               if (defenderBlock((Player)event.getEntity()))
/*  265:     */               {
/*  266: 212 */                 if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.blockAttack"))
/*  267:     */                 {
/*  268: 213 */                   ((Player)event.getEntity()).sendMessage(getResponse("DamageMessages.BlockSuccess"));
/*  269: 214 */                   ((Player)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));
/*  270:     */                 }
/*  271: 216 */                 event.setCancelled(true);
/*  272: 217 */                 return;
/*  273:     */               }
/*  274: 220 */               if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/*  275: 221 */                 durabilityCalcForArmour((Player)event.getEntity());
/*  276:     */               }
/*  277: 224 */               if (ItemLoreStats.plugin.getConfig().getString("npcModifier." + event.getDamager().getWorld().getName()) != null)
/*  278:     */               {
/*  279: 225 */                 double distance = ((LivingEntity)shooter).getMaxHealth() / ItemLoreStats.plugin.getConfig().getDouble("npcModifier." + shooter.getWorld().getName() + ".healthMultiplier");
/*  280: 226 */                 double newDamage = Math.round(event.getDamage() + distance * ItemLoreStats.plugin.getConfig().getDouble("npcModifier." + shooter.getWorld().getName() + ".damageMultiplier"));
/*  281:     */                 
/*  282: 228 */                 double getDefenderArmour = defenderArmour((Player)event.getEntity());
/*  283: 229 */                 double reducedDamage = newDamage / 100.0D * getDefenderArmour;
/*  284:     */                 
/*  285: 231 */                 damageDealtMessage(shooter, event.getEntity(), (int)(newDamage - reducedDamage));
/*  286:     */                 
/*  287: 233 */                 event.setDamage(newDamage - reducedDamage);
/*  288:     */               }
/*  289:     */               else
/*  290:     */               {
/*  291: 235 */                 double getDefenderArmour = defenderArmour((Player)event.getEntity());
/*  292: 236 */                 double reducedDamage = event.getDamage() / 100.0D * getDefenderArmour;
/*  293:     */                 
/*  294: 238 */                 damageDealtMessage(shooter, event.getEntity(), (int)(event.getDamage() - reducedDamage));
/*  295:     */                 
/*  296: 240 */                 event.setDamage(event.getDamage() - reducedDamage);
/*  297:     */               }
/*  298: 243 */               return;
/*  299:     */             }
/*  300: 245 */             return;
/*  301:     */           }
/*  302: 248 */           getAttacker = (Player)shooter;
/*  303:     */         }
/*  304:     */         else
/*  305:     */         {
/*  306: 252 */           if (((Player)event.getDamager()).getItemInHand().getType().equals(Material.BOW))
/*  307:     */           {
/*  308: 253 */             if ((event.getEntity() instanceof Player))
/*  309:     */             {
/*  310: 254 */               event.setDamage(1.0D);
/*  311: 255 */               damageDealtMessage(event.getDamager(), event.getEntity(), 1.0D);
/*  312: 256 */               return;
/*  313:     */             }
/*  314: 258 */             event.setDamage(1.0D);
/*  315: 259 */             damageDealtMessage(event.getDamager(), event.getEntity(), 1.0D);
/*  316: 260 */             return;
/*  317:     */           }
/*  318: 263 */           getAttacker = (Player)event.getDamager();
/*  319:     */         }
/*  320: 266 */         Entity getDefender = event.getEntity();
/*  321: 268 */         if (((getAttacker instanceof Player)) && ((getDefender instanceof Player)) && (ItemLoreStats.plugin.getWorldGuard() != null) && ((ItemLoreStats.plugin.util_WorldGuard.playerInPVPRegion(getAttacker)) || (ItemLoreStats.plugin.util_WorldGuard.playerInPVPRegion((Player)getDefender)) || (ItemLoreStats.plugin.util_WorldGuard.playerInInvincibleRegion(getAttacker)) || (ItemLoreStats.plugin.util_WorldGuard.playerInInvincibleRegion((Player)getDefender)))) {
/*  322: 270 */           return;
/*  323:     */         }
/*  324: 272 */         if (defenderDodge(getDefender))
/*  325:     */         {
/*  326: 273 */           if ((getDefender instanceof Player))
/*  327:     */           {
/*  328: 274 */             if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.enemyDodgedAttack")) {
/*  329: 275 */               getAttacker.sendMessage(ChatColor.WHITE + ((Player)getDefender).getName() + " " + getResponse("DamageMessages.EnemyDodgeSuccess"));
/*  330:     */             }
/*  331: 277 */             if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.dodgeAttack"))) {
/*  332: 279 */               ((Player)getDefender).sendMessage(getResponse("DamageMessages.DodgeSuccess"));
/*  333:     */             }
/*  334:     */           }
/*  335: 283 */           event.setCancelled(true);
/*  336: 284 */           return;
/*  337:     */         }
/*  338: 287 */         if (defenderBlock(getDefender))
/*  339:     */         {
/*  340: 288 */           if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.blockAttack")))
/*  341:     */           {
/*  342: 290 */             ((Player)getDefender).sendMessage(getResponse("DamageMessages.BlockSuccess"));
/*  343: 291 */             ((Player)getDefender).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));
/*  344:     */           }
/*  345: 294 */           event.setCancelled(true);
/*  346: 295 */           return;
/*  347:     */         }
/*  348: 298 */         double getAttackerDamage = attackerDamage(getAttacker, getDefender, getDefender.getType(), event.getDamage());
/*  349: 299 */         double getDefenderArmour = defenderArmour(getDefender);
/*  350:     */         
/*  351: 301 */         double reducedDamage = getAttackerDamage / 100.0D * getDefenderArmour;
/*  352: 303 */         if (((getDefender instanceof Player)) && (reflect(getDefender) > 0.0D) && (random(100) <= reflect(getDefender)))
/*  353:     */         {
/*  354: 306 */           if ((getDefender instanceof Player))
/*  355:     */           {
/*  356: 307 */             double damage = getAttackerDamage - reducedDamage;
/*  357:     */             
/*  358: 309 */             getAttacker.damage(damage);
/*  359: 310 */             if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.enemyReflectedAttack")) {
/*  360: 311 */               getAttacker.sendMessage(ChatColor.WHITE + ((Player)getDefender).getName() + " " + getResponse("DamageMessages.EnemyReflectSuccess"));
/*  361:     */             }
/*  362: 313 */             if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.reflectAttack"))) {
/*  363: 315 */               ((Player)getDefender).sendMessage(getResponse("DamageMessages.ReflectSuccess"));
/*  364:     */             }
/*  365: 318 */             event.setCancelled(true);
/*  366: 319 */             return;
/*  367:     */           }
/*  368: 321 */           if ((getDefender instanceof LivingEntity))
/*  369:     */           {
/*  370: 322 */             double damage = getAttackerDamage - reducedDamage;
/*  371:     */             
/*  372: 324 */             getAttacker.damage(damage);
/*  373: 325 */             if ((ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.enemyReflectedAttack")) && ((getAttacker instanceof Player))) {
/*  374: 327 */               getAttacker.sendMessage(ChatColor.WHITE + ((Player)getDefender).getName() + " " + getResponse("DamageMessages.EnemyReflectSuccess"));
/*  375:     */             }
/*  376: 330 */             event.setCancelled(true);
/*  377: 331 */             return;
/*  378:     */           }
/*  379:     */         }
/*  380: 336 */         if (getAttacker.getHealth() < getAttacker.getMaxHealth())
/*  381:     */         {
/*  382: 337 */           double lifeStealHeal = lifeSteal(getDefender, getAttacker, getAttackerDamage - reducedDamage);
/*  383: 339 */           if (lifeStealHeal > Math.abs(getAttacker.getHealth() - getAttacker.getMaxHealth()))
/*  384:     */           {
/*  385: 340 */             double getRemainingHealth = Math.abs(getAttacker.getHealth() - getAttacker.getMaxHealth());
/*  386: 341 */             getAttacker.setHealth(getAttacker.getHealth() + getRemainingHealth);
/*  387:     */           }
/*  388:     */           else
/*  389:     */           {
/*  390: 343 */             getAttacker.setHealth(getAttacker.getHealth() + lifeStealHeal);
/*  391:     */           }
/*  392:     */         }
/*  393: 347 */         fire(getDefender, getAttacker);
/*  394: 348 */         ice(getDefender, getAttacker);
/*  395: 349 */         poison(getDefender, getAttacker);
/*  396: 350 */         wither(getDefender, getAttacker);
/*  397: 351 */         harming(getDefender, getAttacker);
/*  398: 352 */         blind(getDefender, getAttacker);
/*  399: 354 */         if ((!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) && (getAttacker.getItemInHand() != null) && (ItemLoreStats.plugin.isTool(getAttacker.getItemInHand().getType())))
/*  400:     */         {
/*  401: 356 */           getAttacker.getItemInHand().setDurability((short)-1);
/*  402: 357 */           if (getAttacker.getItemInHand().getItemMeta().hasLore())
/*  403:     */           {
/*  404: 359 */             String durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  405:     */             
/*  406: 361 */             List<String> getItemLore = getAttacker.getItemInHand().getItemMeta().getLore();
/*  407: 363 */             for (String getDurability : getItemLore) {
/*  408: 364 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  409:     */               {
/*  410: 365 */                 String durabilityRebuilder = "";
/*  411: 366 */                 String durabilityAmountColour = "";
/*  412: 367 */                 String prefixColourOnly = "";
/*  413:     */                 
/*  414: 369 */                 prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability));
/*  415:     */                 
/*  416: 371 */                 int index = getItemLore.indexOf(getDurability);
/*  417: 372 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  418: 373 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  419: 375 */                 if (currentValueMinusOne + 1 < 2)
/*  420:     */                 {
/*  421: 376 */                   getAttacker.playEffect(getAttacker.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/*  422: 377 */                   getAttacker.getInventory().remove(getAttacker.getItemInHand());
/*  423: 378 */                   event.setDamage(getAttackerDamage - reducedDamage);
/*  424: 379 */                   damageDealtMessage(getAttacker, getDefender, getAttackerDamage - reducedDamage);
/*  425: 380 */                   return;
/*  426:     */                 }
/*  427: 383 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  428:     */                 {
/*  429: 384 */                   if (getDurability.length() > 4)
/*  430:     */                   {
/*  431: 385 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  432: 386 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  433:     */                     } else {
/*  434: 388 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  435:     */                     }
/*  436:     */                   }
/*  437:     */                   else {
/*  438: 391 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  439:     */                   }
/*  440:     */                 }
/*  441:     */                 else {
/*  442: 394 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  443:     */                 }
/*  444: 397 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  445:     */                 {
/*  446: 398 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  447:     */                   {
/*  448: 399 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  449: 400 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  450:     */                     } else {
/*  451: 402 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  452:     */                     }
/*  453:     */                   }
/*  454:     */                   else {
/*  455: 405 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  456:     */                   }
/*  457:     */                 }
/*  458:     */                 else {
/*  459: 408 */                   durabilityAmountColour = prefixColourOnly;
/*  460:     */                 }
/*  461: 411 */                 currentValueMinusOne += enchantmentDurabilityLoss(getAttacker.getItemInHand());
/*  462:     */                 
/*  463: 413 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  464: 415 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  465:     */                 {
/*  466: 416 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  467: 417 */                     getAttacker.sendMessage(getAttacker.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  468:     */                   }
/*  469: 419 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  470:     */                 }
/*  471: 420 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  472:     */                 {
/*  473: 421 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  474: 422 */                     getAttacker.sendMessage(getAttacker.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  475:     */                   }
/*  476: 424 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  477:     */                 }
/*  478: 425 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  479:     */                 {
/*  480: 426 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  481: 427 */                     getAttacker.sendMessage(getAttacker.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  482:     */                   }
/*  483: 429 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  484:     */                 }
/*  485:     */                 else
/*  486:     */                 {
/*  487: 431 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  488:     */                 }
/*  489: 434 */                 getItemLore.set(index, durabilityRebuilder);
/*  490:     */               }
/*  491:     */             }
/*  492: 438 */             ItemStack setItemInHand = new ItemStack(getAttacker.getItemInHand());
/*  493: 439 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/*  494:     */             
/*  495: 441 */             setItemInHandMeta.setLore(getItemLore);
/*  496: 442 */             setItemInHand.setItemMeta(setItemInHandMeta);
/*  497:     */             
/*  498: 444 */             getAttacker.getInventory().remove(getAttacker.getItemInHand());
/*  499: 445 */             getAttacker.setItemInHand(new ItemStack(setItemInHand));
/*  500:     */           }
/*  501:     */         }
/*  502: 450 */         if (!event.getEntityType().equals(EntityType.PLAYER))
/*  503:     */         {
/*  504: 451 */           if ((event.getEntity() instanceof LivingEntity)) {
/*  505: 453 */             if (ItemLoreStats.plugin.isTool(getAttacker.getItemInHand().getType()))
/*  506:     */             {
/*  507: 454 */               if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/*  508: 455 */                 durabilityCalcForArmour((LivingEntity)getDefender);
/*  509:     */               }
/*  510: 457 */               event.setDamage(getAttackerDamage - reducedDamage);
/*  511:     */             }
/*  512:     */             else
/*  513:     */             {
/*  514: 459 */               event.setDamage(1.0D);
/*  515:     */             }
/*  516:     */           }
/*  517:     */         }
/*  518: 464 */         else if (ItemLoreStats.plugin.isTool(getAttacker.getItemInHand().getType()))
/*  519:     */         {
/*  520: 465 */           if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/*  521: 466 */             durabilityCalcForArmour((Player)getDefender);
/*  522:     */           }
/*  523: 468 */           event.setDamage(getAttackerDamage - reducedDamage);
/*  524:     */         }
/*  525:     */         else
/*  526:     */         {
/*  527: 470 */           event.setDamage(1.0D);
/*  528:     */         }
/*  529: 473 */         if (ItemLoreStats.plugin.isTool(getAttacker.getItemInHand().getType())) {
/*  530: 474 */           damageDealtMessage(getAttacker, getDefender, getAttackerDamage - reducedDamage);
/*  531:     */         } else {
/*  532: 476 */           damageDealtMessage(getAttacker, getDefender, 1.0D);
/*  533:     */         }
/*  534:     */       }
/*  535: 479 */       else if ((event.getEntity() instanceof Player))
/*  536:     */       {
/*  537: 481 */         Player player = (Player)event.getEntity();
/*  538: 483 */         if (defenderDodge(player))
/*  539:     */         {
/*  540: 484 */           if (((player instanceof Player)) && ((player instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.dodgeAttack"))) {
/*  541: 487 */             player.sendMessage(getResponse("DamageMessages.DodgeSuccess"));
/*  542:     */           }
/*  543: 490 */           event.setCancelled(true);
/*  544: 491 */           return;
/*  545:     */         }
/*  546: 494 */         if (defenderBlock(player))
/*  547:     */         {
/*  548: 495 */           if (((player instanceof Player)) && ((player instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.blockAttack")))
/*  549:     */           {
/*  550: 498 */             player.sendMessage(getResponse("DamageMessages.BlockSuccess"));
/*  551: 499 */             player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 1));
/*  552:     */           }
/*  553: 502 */           event.setCancelled(true);
/*  554: 503 */           return;
/*  555:     */         }
/*  556: 506 */         if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/*  557: 507 */           durabilityCalcForArmour((Player)event.getEntity());
/*  558:     */         }
/*  559: 510 */         if (ItemLoreStats.plugin.getConfig().getString("npcModifier." + event.getDamager().getWorld().getName()) != null)
/*  560:     */         {
/*  561: 511 */           if ((event.getDamager() instanceof LivingEntity))
/*  562:     */           {
/*  563: 512 */             double distance = ((LivingEntity)event.getDamager()).getMaxHealth() / ItemLoreStats.plugin.getConfig().getDouble("npcModifier." + event.getDamager().getWorld().getName() + ".healthMultiplier");
/*  564: 513 */             double newDamage = Math.round(event.getDamage() + distance * ItemLoreStats.plugin.getConfig().getDouble("npcModifier." + event.getDamager().getWorld().getName() + ".damageMultiplier"));
/*  565:     */             
/*  566: 515 */             double getDefenderArmour = defenderArmour(player);
/*  567: 516 */             double reducedDamage = newDamage / 100.0D * getDefenderArmour;
/*  568:     */             
/*  569: 518 */             damageDealtMessage(event.getDamager(), player, newDamage - reducedDamage);
/*  570:     */             
/*  571: 520 */             event.setDamage(newDamage - reducedDamage);
/*  572:     */           }
/*  573:     */         }
/*  574:     */         else
/*  575:     */         {
/*  576: 523 */           double getDefenderArmour = defenderArmour(player);
/*  577: 524 */           double reducedDamage = event.getDamage() / 100.0D * getDefenderArmour;
/*  578:     */           
/*  579: 526 */           damageDealtMessage(event.getDamager(), player, event.getDamage() - reducedDamage);
/*  580:     */           
/*  581: 528 */           event.setDamage(event.getDamage() - reducedDamage);
/*  582:     */         }
/*  583:     */       }
/*  584:     */     }
/*  585:     */   }
/*  586:     */   
/*  587:     */   public void durabilityCalcForArmour(Entity getDefender)
/*  588:     */   {
/*  589:     */     String durabilityNoColour;
/*  590:     */     List<String> getItemLore;
/*  591:     */     String durability;
/*  592:     */     List<String> getItemLore;
/*  593: 536 */     if ((getDefender instanceof Player))
/*  594:     */     {
/*  595:     */       String durabilityNoColour;
/*  596:     */       List<String> getItemLore;
/*  597: 538 */       if ((((Player)getDefender).getInventory().getHelmet() != null) && (ItemLoreStats.plugin.isHelmet(((Player)getDefender).getInventory().getHelmet().getType())))
/*  598:     */       {
/*  599: 539 */         ((Player)getDefender).getInventory().getHelmet().setDurability((short)0);
/*  600: 540 */         if (((Player)getDefender).getInventory().getHelmet().getItemMeta().hasLore())
/*  601:     */         {
/*  602: 542 */           durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  603:     */           
/*  604: 544 */           getItemLore = ((Player)getDefender).getInventory().getHelmet().getItemMeta().getLore();
/*  605: 546 */           for (String getDurability : getItemLore) {
/*  606: 547 */             if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  607:     */             {
/*  608: 548 */               String durabilityRebuilder = "";
/*  609: 549 */               String durabilityAmountColour = "";
/*  610: 550 */               String prefixColourOnly = "";
/*  611:     */               
/*  612: 552 */               int index = getItemLore.indexOf(getDurability);
/*  613: 553 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  614: 554 */               int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  615: 556 */               if (currentValueMinusOne + 1 > 1)
/*  616:     */               {
/*  617: 557 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  618:     */                 {
/*  619: 558 */                   if (getDurability.length() > 4)
/*  620:     */                   {
/*  621: 559 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  622: 560 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  623:     */                     } else {
/*  624: 562 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  625:     */                     }
/*  626:     */                   }
/*  627:     */                   else {
/*  628: 565 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  629:     */                   }
/*  630:     */                 }
/*  631:     */                 else {
/*  632: 568 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  633:     */                 }
/*  634: 571 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  635:     */                 {
/*  636: 572 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  637:     */                   {
/*  638: 573 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  639: 574 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  640:     */                     } else {
/*  641: 576 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  642:     */                     }
/*  643:     */                   }
/*  644:     */                   else {
/*  645: 579 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  646:     */                   }
/*  647:     */                 }
/*  648:     */                 else {
/*  649: 582 */                   durabilityAmountColour = prefixColourOnly;
/*  650:     */                 }
/*  651: 585 */                 currentValueMinusOne += enchantmentDurabilityLoss(((Player)getDefender).getInventory().getHelmet());
/*  652:     */                 
/*  653: 587 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  654: 589 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  655:     */                 {
/*  656: 590 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  657: 591 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getHelmet().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  658:     */                   }
/*  659: 593 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  660:     */                 }
/*  661: 594 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  662:     */                 {
/*  663: 595 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  664: 596 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getHelmet().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  665:     */                   }
/*  666: 598 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  667:     */                 }
/*  668: 599 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  669:     */                 {
/*  670: 600 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  671: 601 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getHelmet().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  672:     */                   }
/*  673: 603 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  674:     */                 }
/*  675:     */                 else
/*  676:     */                 {
/*  677: 605 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  678:     */                 }
/*  679: 608 */                 getItemLore.set(index, durabilityRebuilder);
/*  680:     */                 
/*  681: 610 */                 ItemStack getItem = new ItemStack(((Player)getDefender).getInventory().getHelmet());
/*  682: 611 */                 ItemMeta getItemMeta = getItem.getItemMeta();
/*  683:     */                 
/*  684: 613 */                 getItemMeta.setLore(getItemLore);
/*  685: 614 */                 getItem.setItemMeta(getItemMeta);
/*  686:     */                 
/*  687: 616 */                 ((Player)getDefender).getInventory().remove(((Player)getDefender).getInventory().getHelmet());
/*  688: 617 */                 ((Player)getDefender).getInventory().setHelmet(new ItemStack(getItem));
/*  689:     */               }
/*  690:     */               else
/*  691:     */               {
/*  692: 619 */                 ((Player)getDefender).playEffect(((Player)getDefender).getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/*  693: 620 */                 ((Player)getDefender).getInventory().setHelmet(new ItemStack(Material.AIR));
/*  694:     */               }
/*  695:     */             }
/*  696:     */           }
/*  697:     */         }
/*  698:     */       }
/*  699:     */       String durabilityNoColour;
/*  700:     */       List<String> getItemLore;
/*  701: 627 */       if ((((Player)getDefender).getInventory().getChestplate() != null) && (ItemLoreStats.plugin.isChestplate(((Player)getDefender).getInventory().getChestplate().getType())))
/*  702:     */       {
/*  703: 628 */         ((Player)getDefender).getInventory().getChestplate().setDurability((short)0);
/*  704: 629 */         if (((Player)getDefender).getInventory().getChestplate().getItemMeta().hasLore())
/*  705:     */         {
/*  706: 631 */           durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  707:     */           
/*  708: 633 */           getItemLore = ((Player)getDefender).getInventory().getChestplate().getItemMeta().getLore();
/*  709: 635 */           for (String getDurability : getItemLore) {
/*  710: 636 */             if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  711:     */             {
/*  712: 637 */               String durabilityRebuilder = "";
/*  713: 638 */               String durabilityAmountColour = "";
/*  714: 639 */               String prefixColourOnly = "";
/*  715:     */               
/*  716: 641 */               int index = getItemLore.indexOf(getDurability);
/*  717: 642 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  718: 643 */               int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  719: 645 */               if (currentValueMinusOne + 1 > 1)
/*  720:     */               {
/*  721: 646 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  722:     */                 {
/*  723: 647 */                   if (getDurability.length() > 4)
/*  724:     */                   {
/*  725: 648 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  726: 649 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  727:     */                     } else {
/*  728: 651 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  729:     */                     }
/*  730:     */                   }
/*  731:     */                   else {
/*  732: 654 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  733:     */                   }
/*  734:     */                 }
/*  735:     */                 else {
/*  736: 657 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  737:     */                 }
/*  738: 660 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  739:     */                 {
/*  740: 661 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  741:     */                   {
/*  742: 662 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  743: 663 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  744:     */                     } else {
/*  745: 665 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  746:     */                     }
/*  747:     */                   }
/*  748:     */                   else {
/*  749: 668 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  750:     */                   }
/*  751:     */                 }
/*  752:     */                 else {
/*  753: 671 */                   durabilityAmountColour = prefixColourOnly;
/*  754:     */                 }
/*  755: 674 */                 currentValueMinusOne += enchantmentDurabilityLoss(((Player)getDefender).getInventory().getChestplate());
/*  756:     */                 
/*  757: 676 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  758: 678 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  759:     */                 {
/*  760: 679 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  761: 680 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getChestplate().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  762:     */                   }
/*  763: 682 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  764:     */                 }
/*  765: 683 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  766:     */                 {
/*  767: 684 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  768: 685 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getChestplate().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  769:     */                   }
/*  770: 687 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  771:     */                 }
/*  772: 688 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  773:     */                 {
/*  774: 689 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  775: 690 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getChestplate().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  776:     */                   }
/*  777: 692 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  778:     */                 }
/*  779:     */                 else
/*  780:     */                 {
/*  781: 694 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  782:     */                 }
/*  783: 697 */                 getItemLore.set(index, durabilityRebuilder);
/*  784:     */                 
/*  785: 699 */                 ItemStack getItem = new ItemStack(((Player)getDefender).getInventory().getChestplate());
/*  786: 700 */                 ItemMeta getItemMeta = getItem.getItemMeta();
/*  787:     */                 
/*  788: 702 */                 getItemMeta.setLore(getItemLore);
/*  789: 703 */                 getItem.setItemMeta(getItemMeta);
/*  790:     */                 
/*  791: 705 */                 ((Player)getDefender).getInventory().remove(((Player)getDefender).getInventory().getChestplate());
/*  792: 706 */                 ((Player)getDefender).getInventory().setChestplate(new ItemStack(getItem));
/*  793:     */               }
/*  794:     */               else
/*  795:     */               {
/*  796: 708 */                 ((Player)getDefender).playEffect(((Player)getDefender).getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/*  797: 709 */                 ((Player)getDefender).getInventory().setChestplate(new ItemStack(Material.AIR));
/*  798:     */               }
/*  799:     */             }
/*  800:     */           }
/*  801:     */         }
/*  802:     */       }
/*  803:     */       String durabilityNoColour;
/*  804:     */       List<String> getItemLore;
/*  805: 716 */       if ((((Player)getDefender).getInventory().getLeggings() != null) && (ItemLoreStats.plugin.isLeggings(((Player)getDefender).getInventory().getLeggings().getType())))
/*  806:     */       {
/*  807: 717 */         ((Player)getDefender).getInventory().getLeggings().setDurability((short)0);
/*  808: 718 */         if (((Player)getDefender).getInventory().getLeggings().getItemMeta().hasLore())
/*  809:     */         {
/*  810: 720 */           durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  811:     */           
/*  812: 722 */           getItemLore = ((Player)getDefender).getInventory().getLeggings().getItemMeta().getLore();
/*  813: 724 */           for (String getDurability : getItemLore) {
/*  814: 725 */             if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  815:     */             {
/*  816: 726 */               String durabilityRebuilder = "";
/*  817: 727 */               String durabilityAmountColour = "";
/*  818: 728 */               String prefixColourOnly = "";
/*  819:     */               
/*  820: 730 */               int index = getItemLore.indexOf(getDurability);
/*  821: 731 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  822: 732 */               int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  823: 734 */               if (currentValueMinusOne + 1 > 1)
/*  824:     */               {
/*  825: 735 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  826:     */                 {
/*  827: 736 */                   if (getDurability.length() > 4)
/*  828:     */                   {
/*  829: 737 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  830: 738 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  831:     */                     } else {
/*  832: 740 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  833:     */                     }
/*  834:     */                   }
/*  835:     */                   else {
/*  836: 743 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  837:     */                   }
/*  838:     */                 }
/*  839:     */                 else {
/*  840: 746 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  841:     */                 }
/*  842: 749 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  843:     */                 {
/*  844: 750 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  845:     */                   {
/*  846: 751 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  847: 752 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  848:     */                     } else {
/*  849: 754 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  850:     */                     }
/*  851:     */                   }
/*  852:     */                   else {
/*  853: 757 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  854:     */                   }
/*  855:     */                 }
/*  856:     */                 else {
/*  857: 760 */                   durabilityAmountColour = prefixColourOnly;
/*  858:     */                 }
/*  859: 763 */                 currentValueMinusOne += enchantmentDurabilityLoss(((Player)getDefender).getInventory().getLeggings());
/*  860:     */                 
/*  861: 765 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  862: 767 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  863:     */                 {
/*  864: 768 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  865: 769 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getLeggings().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  866:     */                   }
/*  867: 771 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  868:     */                 }
/*  869: 772 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  870:     */                 {
/*  871: 773 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  872: 774 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getLeggings().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  873:     */                   }
/*  874: 776 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  875:     */                 }
/*  876: 777 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  877:     */                 {
/*  878: 778 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  879: 779 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getLeggings().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  880:     */                   }
/*  881: 781 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  882:     */                 }
/*  883:     */                 else
/*  884:     */                 {
/*  885: 783 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  886:     */                 }
/*  887: 786 */                 getItemLore.set(index, durabilityRebuilder);
/*  888:     */                 
/*  889: 788 */                 ItemStack getItem = new ItemStack(((Player)getDefender).getInventory().getLeggings());
/*  890: 789 */                 ItemMeta getItemMeta = getItem.getItemMeta();
/*  891:     */                 
/*  892: 791 */                 getItemMeta.setLore(getItemLore);
/*  893: 792 */                 getItem.setItemMeta(getItemMeta);
/*  894:     */                 
/*  895: 794 */                 ((Player)getDefender).getInventory().remove(((Player)getDefender).getInventory().getLeggings());
/*  896: 795 */                 ((Player)getDefender).getInventory().setLeggings(new ItemStack(getItem));
/*  897:     */               }
/*  898:     */               else
/*  899:     */               {
/*  900: 797 */                 ((Player)getDefender).playEffect(((Player)getDefender).getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/*  901: 798 */                 ((Player)getDefender).getInventory().setLeggings(new ItemStack(Material.AIR));
/*  902:     */               }
/*  903:     */             }
/*  904:     */           }
/*  905:     */         }
/*  906:     */       }
/*  907: 805 */       if ((((Player)getDefender).getInventory().getBoots() != null) && (ItemLoreStats.plugin.isBoots(((Player)getDefender).getInventory().getBoots().getType())))
/*  908:     */       {
/*  909: 806 */         ((Player)getDefender).getInventory().getBoots().setDurability((short)0);
/*  910: 807 */         if (((Player)getDefender).getInventory().getBoots().getItemMeta().hasLore())
/*  911:     */         {
/*  912: 809 */           durabilityNoColour = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  913:     */           
/*  914: 811 */           getItemLore = ((Player)getDefender).getInventory().getBoots().getItemMeta().getLore();
/*  915: 813 */           for (String getDurability : getItemLore) {
/*  916: 814 */             if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/*  917:     */             {
/*  918: 815 */               String durabilityRebuilder = "";
/*  919: 816 */               String durabilityAmountColour = "";
/*  920: 817 */               String prefixColourOnly = "";
/*  921:     */               
/*  922: 819 */               int index = getItemLore.indexOf(getDurability);
/*  923: 820 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/*  924: 821 */               int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/*  925: 823 */               if (currentValueMinusOne + 1 > 1)
/*  926:     */               {
/*  927: 824 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/*  928:     */                 {
/*  929: 825 */                   if (getDurability.length() > 4)
/*  930:     */                   {
/*  931: 826 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/*  932: 827 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/*  933:     */                     } else {
/*  934: 829 */                       prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  935:     */                     }
/*  936:     */                   }
/*  937:     */                   else {
/*  938: 832 */                     prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/*  939:     */                   }
/*  940:     */                 }
/*  941:     */                 else {
/*  942: 835 */                   prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  943:     */                 }
/*  944: 838 */                 if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  945:     */                 {
/*  946: 839 */                   if (getDurability.split("/")[1].trim().length() > 4)
/*  947:     */                   {
/*  948: 840 */                     if (this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  949: 841 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/*  950:     */                     } else {
/*  951: 843 */                       durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  952:     */                     }
/*  953:     */                   }
/*  954:     */                   else {
/*  955: 846 */                     durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/*  956:     */                   }
/*  957:     */                 }
/*  958:     */                 else {
/*  959: 849 */                   durabilityAmountColour = prefixColourOnly;
/*  960:     */                 }
/*  961: 852 */                 currentValueMinusOne += enchantmentDurabilityLoss(((Player)getDefender).getInventory().getBoots());
/*  962:     */                 
/*  963: 854 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/*  964: 856 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/*  965:     */                 {
/*  966: 857 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/*  967: 858 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getBoots().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  968:     */                   }
/*  969: 860 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  970:     */                 }
/*  971: 861 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/*  972:     */                 {
/*  973: 862 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/*  974: 863 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getBoots().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  975:     */                   }
/*  976: 865 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  977:     */                 }
/*  978: 866 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/*  979:     */                 {
/*  980: 867 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/*  981: 868 */                     ((Player)getDefender).sendMessage(((Player)getDefender).getInventory().getBoots().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/*  982:     */                   }
/*  983: 870 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  984:     */                 }
/*  985:     */                 else
/*  986:     */                 {
/*  987: 872 */                   durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/*  988:     */                 }
/*  989: 875 */                 getItemLore.set(index, durabilityRebuilder);
/*  990:     */                 
/*  991: 877 */                 ItemStack getItem = new ItemStack(((Player)getDefender).getInventory().getBoots());
/*  992: 878 */                 ItemMeta getItemMeta = getItem.getItemMeta();
/*  993:     */                 
/*  994: 880 */                 getItemMeta.setLore(getItemLore);
/*  995: 881 */                 getItem.setItemMeta(getItemMeta);
/*  996:     */                 
/*  997: 883 */                 ((Player)getDefender).getInventory().remove(((Player)getDefender).getInventory().getBoots());
/*  998: 884 */                 ((Player)getDefender).getInventory().setBoots(new ItemStack(getItem));
/*  999:     */               }
/* 1000:     */               else
/* 1001:     */               {
/* 1002: 886 */                 ((Player)getDefender).playEffect(((Player)getDefender).getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 1003: 887 */                 ((Player)getDefender).getInventory().setBoots(new ItemStack(Material.AIR));
/* 1004:     */               }
/* 1005:     */             }
/* 1006:     */           }
/* 1007:     */         }
/* 1008:     */       }
/* 1009:     */     }
/* 1010: 893 */     else if ((getDefender instanceof LivingEntity))
/* 1011:     */     {
/* 1012:     */       String durability;
/* 1013:     */       List<String> getItemLore;
/* 1014: 895 */       if ((((LivingEntity)getDefender).getEquipment().getHelmet() != null) && (ItemLoreStats.plugin.isHelmet(((LivingEntity)getDefender).getEquipment().getHelmet().getType())))
/* 1015:     */       {
/* 1016: 896 */         ((LivingEntity)getDefender).getEquipment().getHelmet().setDurability((short)0);
/* 1017: 897 */         if (((LivingEntity)getDefender).getEquipment().getHelmet().getItemMeta().hasLore())
/* 1018:     */         {
/* 1019: 899 */           durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 1020:     */           
/* 1021: 901 */           getItemLore = ((LivingEntity)getDefender).getEquipment().getHelmet().getItemMeta().getLore();
/* 1022: 903 */           for (String getDurability : getItemLore) {
/* 1023: 904 */             if (ChatColor.stripColor(getDurability).startsWith(durability))
/* 1024:     */             {
/* 1025: 905 */               int index = getItemLore.indexOf(getDurability);
/* 1026: 906 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durability.length()).split("/")[1].replace("]", "").trim();
/* 1027: 907 */               String currentValue = ChatColor.stripColor(getDurability).trim().replace("[", "").replace(":", "").trim().substring(durability.length()).split("/")[0].replace("]", "").trim();
/* 1028: 909 */               if (Integer.parseInt(currentValue) > 1)
/* 1029:     */               {
/* 1030: 910 */                 String durabilityRebuilder = durability + ": " + (Integer.parseInt(currentValue) - 1) + "/" + maximumValue;
/* 1031: 911 */                 getItemLore.set(index, durabilityRebuilder);
/* 1032:     */                 
/* 1033: 913 */                 ItemStack getHelmetItem = new ItemStack(((LivingEntity)getDefender).getEquipment().getHelmet());
/* 1034: 914 */                 ItemMeta getHelmetItemMeta = getHelmetItem.getItemMeta();
/* 1035:     */                 
/* 1036: 916 */                 getHelmetItemMeta.setLore(getItemLore);
/* 1037: 917 */                 getHelmetItem.setItemMeta(getHelmetItemMeta);
/* 1038:     */                 
/* 1039: 919 */                 ((LivingEntity)getDefender).getEquipment().setHelmet(new ItemStack(Material.AIR));
/* 1040: 920 */                 ((LivingEntity)getDefender).getEquipment().setHelmet(new ItemStack(getHelmetItem));
/* 1041:     */               }
/* 1042:     */               else
/* 1043:     */               {
/* 1044: 922 */                 ((LivingEntity)getDefender).getEquipment().setHelmet(new ItemStack(Material.AIR));
/* 1045:     */               }
/* 1046:     */             }
/* 1047:     */           }
/* 1048:     */         }
/* 1049:     */       }
/* 1050:     */       String durability;
/* 1051:     */       List<String> getItemLore;
/* 1052: 929 */       if ((((LivingEntity)getDefender).getEquipment().getChestplate() != null) && (ItemLoreStats.plugin.isChestplate(((LivingEntity)getDefender).getEquipment().getChestplate().getType())))
/* 1053:     */       {
/* 1054: 930 */         ((LivingEntity)getDefender).getEquipment().getChestplate().setDurability((short)0);
/* 1055: 931 */         if (((LivingEntity)getDefender).getEquipment().getChestplate().getItemMeta().hasLore())
/* 1056:     */         {
/* 1057: 933 */           durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 1058:     */           
/* 1059: 935 */           getItemLore = ((LivingEntity)getDefender).getEquipment().getChestplate().getItemMeta().getLore();
/* 1060: 937 */           for (String getDurability : getItemLore) {
/* 1061: 938 */             if (ChatColor.stripColor(getDurability).startsWith(durability))
/* 1062:     */             {
/* 1063: 939 */               int index = getItemLore.indexOf(getDurability);
/* 1064: 940 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durability.length()).split("/")[1].replace("]", "").trim();
/* 1065: 941 */               String currentValue = ChatColor.stripColor(getDurability).trim().replace("[", "").replace(":", "").trim().substring(durability.length()).split("/")[0].replace("]", "").trim();
/* 1066: 943 */               if (Integer.parseInt(currentValue) > 1)
/* 1067:     */               {
/* 1068: 944 */                 String durabilityRebuilder = durability + ": " + (Integer.parseInt(currentValue) - 1) + "/" + maximumValue;
/* 1069: 945 */                 getItemLore.set(index, durabilityRebuilder);
/* 1070:     */                 
/* 1071: 947 */                 ItemStack getChestItem = new ItemStack(((LivingEntity)getDefender).getEquipment().getChestplate());
/* 1072: 948 */                 ItemMeta getChestItemMeta = getChestItem.getItemMeta();
/* 1073:     */                 
/* 1074: 950 */                 getChestItemMeta.setLore(getItemLore);
/* 1075: 951 */                 getChestItem.setItemMeta(getChestItemMeta);
/* 1076:     */                 
/* 1077: 953 */                 ((LivingEntity)getDefender).getEquipment().setChestplate(new ItemStack(Material.AIR));
/* 1078: 954 */                 ((LivingEntity)getDefender).getEquipment().setChestplate(new ItemStack(getChestItem));
/* 1079:     */               }
/* 1080:     */               else
/* 1081:     */               {
/* 1082: 956 */                 ((LivingEntity)getDefender).getEquipment().setChestplate(new ItemStack(Material.AIR));
/* 1083:     */               }
/* 1084:     */             }
/* 1085:     */           }
/* 1086:     */         }
/* 1087:     */       }
/* 1088:     */       String durability;
/* 1089:     */       List<String> getItemLore;
/* 1090: 963 */       if ((((LivingEntity)getDefender).getEquipment().getLeggings() != null) && (ItemLoreStats.plugin.isLeggings(((LivingEntity)getDefender).getEquipment().getLeggings().getType())))
/* 1091:     */       {
/* 1092: 964 */         ((LivingEntity)getDefender).getEquipment().getLeggings().setDurability((short)0);
/* 1093: 965 */         if (((LivingEntity)getDefender).getEquipment().getLeggings().getItemMeta().hasLore())
/* 1094:     */         {
/* 1095: 967 */           durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 1096:     */           
/* 1097: 969 */           getItemLore = ((LivingEntity)getDefender).getEquipment().getLeggings().getItemMeta().getLore();
/* 1098: 971 */           for (String getDurability : getItemLore) {
/* 1099: 972 */             if (ChatColor.stripColor(getDurability).startsWith(durability))
/* 1100:     */             {
/* 1101: 973 */               int index = getItemLore.indexOf(getDurability);
/* 1102: 974 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durability.length()).split("/")[1].replace("]", "").trim();
/* 1103: 975 */               String currentValue = ChatColor.stripColor(getDurability).trim().replace("[", "").replace(":", "").trim().substring(durability.length()).split("/")[0].replace("]", "").trim();
/* 1104: 977 */               if (Integer.parseInt(currentValue) > 1)
/* 1105:     */               {
/* 1106: 978 */                 String durabilityRebuilder = durability + ": " + (Integer.parseInt(currentValue) - 1) + "/" + maximumValue;
/* 1107: 979 */                 getItemLore.set(index, durabilityRebuilder);
/* 1108:     */                 
/* 1109: 981 */                 ItemStack getLegsItem = new ItemStack(((LivingEntity)getDefender).getEquipment().getLeggings());
/* 1110: 982 */                 ItemMeta getLegsItemMeta = getLegsItem.getItemMeta();
/* 1111:     */                 
/* 1112: 984 */                 getLegsItemMeta.setLore(getItemLore);
/* 1113: 985 */                 getLegsItem.setItemMeta(getLegsItemMeta);
/* 1114:     */                 
/* 1115: 987 */                 ((LivingEntity)getDefender).getEquipment().setLeggings(new ItemStack(Material.AIR));
/* 1116: 988 */                 ((LivingEntity)getDefender).getEquipment().setLeggings(new ItemStack(getLegsItem));
/* 1117:     */               }
/* 1118:     */               else
/* 1119:     */               {
/* 1120: 990 */                 ((LivingEntity)getDefender).getEquipment().setLeggings(new ItemStack(Material.AIR));
/* 1121:     */               }
/* 1122:     */             }
/* 1123:     */           }
/* 1124:     */         }
/* 1125:     */       }
/* 1126: 997 */       if ((((LivingEntity)getDefender).getEquipment().getBoots() != null) && (ItemLoreStats.plugin.isBoots(((LivingEntity)getDefender).getEquipment().getBoots().getType())))
/* 1127:     */       {
/* 1128: 998 */         ((LivingEntity)getDefender).getEquipment().getBoots().setDurability((short)0);
/* 1129: 999 */         if (((LivingEntity)getDefender).getEquipment().getBoots().getItemMeta().hasLore())
/* 1130:     */         {
/* 1131:1001 */           durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 1132:     */           
/* 1133:1003 */           getItemLore = ((LivingEntity)getDefender).getEquipment().getBoots().getItemMeta().getLore();
/* 1134:1005 */           for (String getDurability : getItemLore) {
/* 1135:1006 */             if (ChatColor.stripColor(getDurability).startsWith(durability))
/* 1136:     */             {
/* 1137:1007 */               int index = getItemLore.indexOf(getDurability);
/* 1138:1008 */               String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durability.length()).split("/")[1].replace("]", "").trim();
/* 1139:1009 */               String currentValue = ChatColor.stripColor(getDurability).trim().replace("[", "").replace(":", "").trim().substring(durability.length()).split("/")[0].replace("]", "").trim();
/* 1140:1011 */               if (Integer.parseInt(currentValue) > 1)
/* 1141:     */               {
/* 1142:1012 */                 String durabilityRebuilder = durability + ": " + (Integer.parseInt(currentValue) - 1) + "/" + maximumValue;
/* 1143:1013 */                 getItemLore.set(index, durabilityRebuilder);
/* 1144:     */                 
/* 1145:1015 */                 ItemStack getBootsItem = new ItemStack(((LivingEntity)getDefender).getEquipment().getBoots());
/* 1146:1016 */                 ItemMeta getBootsItemMeta = getBootsItem.getItemMeta();
/* 1147:     */                 
/* 1148:1018 */                 getBootsItemMeta.setLore(getItemLore);
/* 1149:1019 */                 getBootsItem.setItemMeta(getBootsItemMeta);
/* 1150:     */                 
/* 1151:1021 */                 ((LivingEntity)getDefender).getEquipment().setBoots(new ItemStack(Material.AIR));
/* 1152:1022 */                 ((LivingEntity)getDefender).getEquipment().setBoots(new ItemStack(getBootsItem));
/* 1153:     */               }
/* 1154:     */               else
/* 1155:     */               {
/* 1156:1024 */                 ((LivingEntity)getDefender).getEquipment().setBoots(new ItemStack(Material.AIR));
/* 1157:     */               }
/* 1158:     */             }
/* 1159:     */           }
/* 1160:     */         }
/* 1161:     */       }
/* 1162:     */     }
/* 1163:     */   }
/* 1164:     */   
/* 1165:     */   public void damageDealtMessage(Entity getAttacker, Entity getDefender, double damageDealt)
/* 1166:     */   {
/* 1167:1034 */     if ((getAttacker instanceof Player))
/* 1168:     */     {
/* 1169:1035 */       if ((getDefender instanceof Player))
/* 1170:     */       {
/* 1171:1036 */         if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.damageTaken")) {
/* 1172:1037 */           ((Player)getDefender).sendMessage(ChatColor.WHITE + "      " + ((Player)getAttacker).getName() + ChatColor.LIGHT_PURPLE + " hit you for " + ChatColor.GOLD + Double.valueOf(damageDealt).intValue() + ChatColor.LIGHT_PURPLE + " damage.");
/* 1173:     */         }
/* 1174:     */       }
/* 1175:1040 */       else if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.damageDone")) {
/* 1176:1041 */         ((Player)getAttacker).sendMessage(ChatColor.LIGHT_PURPLE + "      You hit a " + ChatColor.RESET + getDefender.getType().toString().substring(0, 1) + getDefender.getType().toString().substring(1, getDefender.getType().toString().length()).toLowerCase() + ChatColor.LIGHT_PURPLE + " for " + ChatColor.GOLD + Double.valueOf(damageDealt).intValue() + ChatColor.LIGHT_PURPLE + " damage.");
/* 1177:     */       }
/* 1178:     */     }
/* 1179:1045 */     else if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.damageTaken"))) {
/* 1180:1047 */       ((Player)getDefender).sendMessage(ChatColor.WHITE + "      " + getAttacker.getType().toString().substring(0, 1) + getAttacker.getType().toString().substring(1, getAttacker.getType().toString().length()).toLowerCase() + ChatColor.LIGHT_PURPLE + " hit you for " + ChatColor.GOLD + Double.valueOf(damageDealt).intValue() + ChatColor.LIGHT_PURPLE + " damage.");
/* 1181:     */     }
/* 1182:     */   }
/* 1183:     */   
/* 1184:     */   public int enchantmentDurabilityLoss(ItemStack getItem)
/* 1185:     */   {
/* 1186:1052 */     int r = random(1000);
/* 1187:1054 */     if (getItem.containsEnchantment(Enchantment.DURABILITY)) {
/* 1188:1055 */       if (getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 1)
/* 1189:     */       {
/* 1190:1056 */         if (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingOne") > 0)
/* 1191:     */         {
/* 1192:1057 */           if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingOne") * 10) {
/* 1193:1058 */             return 0;
/* 1194:     */           }
/* 1195:1060 */           return 1;
/* 1196:     */         }
/* 1197:     */       }
/* 1198:1063 */       else if (getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 2)
/* 1199:     */       {
/* 1200:1064 */         if (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingTwo") > 0)
/* 1201:     */         {
/* 1202:1065 */           if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingTwo") * 10) {
/* 1203:1066 */             return 0;
/* 1204:     */           }
/* 1205:1068 */           return 1;
/* 1206:     */         }
/* 1207:     */       }
/* 1208:1071 */       else if ((getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 3) && (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingThree") > 0))
/* 1209:     */       {
/* 1210:1073 */         if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingThree") * 10) {
/* 1211:1074 */           return 0;
/* 1212:     */         }
/* 1213:1076 */         return 1;
/* 1214:     */       }
/* 1215:     */     }
/* 1216:1081 */     return 0;
/* 1217:     */   }
/* 1218:     */   
/* 1219:     */   public String pvpModifiedDamage(Player player)
/* 1220:     */   {
/* 1221:1085 */     if (this.gearStats.getPvPDamageModifierItemInHand(player).contains("+")) {
/* 1222:1086 */       return "add_" + this.gearStats.getPvPDamageModifierItemInHand(player).substring(1);
/* 1223:     */     }
/* 1224:1087 */     if (this.gearStats.getPvPDamageModifierItemInHand(player).contains("-")) {
/* 1225:1088 */       return "remove_" + this.gearStats.getPvPDamageModifierItemInHand(player).substring(1);
/* 1226:     */     }
/* 1227:1090 */     return "add_0";
/* 1228:     */   }
/* 1229:     */   
/* 1230:     */   public String pveModifiedDamage(Player player)
/* 1231:     */   {
/* 1232:1094 */     if (this.gearStats.getPvEDamageModifierItemInHand(player).contains("+")) {
/* 1233:1095 */       return "add_" + this.gearStats.getPvEDamageModifierItemInHand(player).substring(1);
/* 1234:     */     }
/* 1235:1096 */     if (this.gearStats.getPvEDamageModifierItemInHand(player).contains("-")) {
/* 1236:1097 */       return "remove_" + this.gearStats.getPvEDamageModifierItemInHand(player).substring(1);
/* 1237:     */     }
/* 1238:1099 */     return "add_0";
/* 1239:     */   }
/* 1240:     */   
/* 1241:     */   public double attackerDamage(Player player, Entity getDefender, EntityType entityType, double weaponDamage)
/* 1242:     */   {
/* 1243:1104 */     double valueMin = 0.0D;
/* 1244:1105 */     double valueMax = 0.0D;
/* 1245:1106 */     double valueRand = 0.0D;
/* 1246:1107 */     double modifier = 0.0D;
/* 1247:1109 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType()))
/* 1248:     */     {
/* 1249:1110 */       valueMin = Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[0]) + Double.parseDouble(this.gearStats.getDamageItemInHand(player).split("-")[0]) + modifier;
/* 1250:1111 */       valueMax = Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[1]) + Double.parseDouble(this.gearStats.getDamageItemInHand(player).split("-")[1]) + modifier;
/* 1251:     */     }
/* 1252:     */     else
/* 1253:     */     {
/* 1254:1113 */       valueMin = Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[0]) + modifier;
/* 1255:1114 */       valueMax = Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[1]) + modifier;
/* 1256:     */     }
/* 1257:1117 */     if ((valueMin > 0.0D) && (valueMax > 0.0D))
/* 1258:     */     {
/* 1259:1118 */       double damage = Double.parseDouble(randomRange(valueMin, valueMax));
/* 1260:1120 */       if (criticalStrike(player, getDefender) > 1)
/* 1261:     */       {
/* 1262:1121 */         if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 1263:1122 */           valueRand = damage + damage * (ItemLoreStats.plugin.getConfig().getDouble("baseCritDamage") + (this.gearStats.getCritDamageGear(player) + this.gearStats.getCritDamageItemInHand(player))) / 100.0D;
/* 1264:     */         } else {
/* 1265:1124 */           valueRand = damage + damage * (ItemLoreStats.plugin.getConfig().getDouble("baseCritDamage") + this.gearStats.getCritDamageGear(player)) / 100.0D;
/* 1266:     */         }
/* 1267:     */       }
/* 1268:     */       else {
/* 1269:1127 */         valueRand = damage;
/* 1270:     */       }
/* 1271:     */     }
/* 1272:     */     else
/* 1273:     */     {
/* 1274:1130 */       valueRand = weaponDamage + modifier;
/* 1275:     */     }
/* 1276:1133 */     if ((getDefender instanceof Player))
/* 1277:     */     {
/* 1278:1134 */       if (pvpModifiedDamage(player).contains("add_"))
/* 1279:     */       {
/* 1280:1135 */         double modifyValue = valueRand + Double.parseDouble(pvpModifiedDamage(player).split("\\_")[1]);
/* 1281:1136 */         return modifyValue;
/* 1282:     */       }
/* 1283:1138 */       if (pvpModifiedDamage(player).contains("remove_"))
/* 1284:     */       {
/* 1285:1140 */         double modifyValue = valueRand - Double.parseDouble(pvpModifiedDamage(player).split("\\_")[1]);
/* 1286:1142 */         if (modifyValue <= 0.0D)
/* 1287:     */         {
/* 1288:1143 */           modifyValue = 0.0D;
/* 1289:1144 */           return modifyValue;
/* 1290:     */         }
/* 1291:1146 */         return modifyValue;
/* 1292:     */       }
/* 1293:     */     }
/* 1294:1149 */     else if ((getDefender instanceof LivingEntity))
/* 1295:     */     {
/* 1296:1150 */       if (pveModifiedDamage(player).contains("add_"))
/* 1297:     */       {
/* 1298:1151 */         double modifyValue = valueRand + Double.parseDouble(pveModifiedDamage(player).split("\\_")[1]);
/* 1299:     */         
/* 1300:1153 */         return modifyValue;
/* 1301:     */       }
/* 1302:1154 */       if (pveModifiedDamage(player).contains("remove_"))
/* 1303:     */       {
/* 1304:1155 */         double modifyValue = valueRand - Double.parseDouble(pveModifiedDamage(player).split("\\_")[1]);
/* 1305:1157 */         if (modifyValue <= 0.0D)
/* 1306:     */         {
/* 1307:1158 */           modifyValue = 0.0D;
/* 1308:1159 */           return modifyValue;
/* 1309:     */         }
/* 1310:1161 */         return modifyValue;
/* 1311:     */       }
/* 1312:     */     }
/* 1313:     */     else
/* 1314:     */     {
/* 1315:1165 */       return valueRand;
/* 1316:     */     }
/* 1317:1167 */     return valueRand;
/* 1318:     */   }
/* 1319:     */   
/* 1320:     */   public double defenderArmour(Entity getDefender)
/* 1321:     */   {
/* 1322:1171 */     if ((getDefender instanceof Player))
/* 1323:     */     {
/* 1324:1172 */       double modifier = this.setBonuses.checkHashMapArmour((Player)getDefender);
/* 1325:1174 */       if (ItemLoreStats.plugin.isTool(((Player)getDefender).getItemInHand().getType())) {
/* 1326:1175 */         return this.gearStats.getArmourGear((Player)getDefender) + this.gearStats.getArmourItemInHand((Player)getDefender) + modifier;
/* 1327:     */       }
/* 1328:1177 */       return this.gearStats.getArmourGear((Player)getDefender) + modifier;
/* 1329:     */     }
/* 1330:1180 */     return 0.0D;
/* 1331:     */   }
/* 1332:     */   
/* 1333:     */   public boolean defenderDodge(Entity getDefender)
/* 1334:     */   {
/* 1335:1183 */     if (!(getDefender instanceof Player)) {
/* 1336:1183 */       return false;
/* 1337:     */     }
/* 1338:1185 */     double dodgePercent = 0.0D;
/* 1339:1186 */     double modifier = this.setBonuses.checkHashMapDodge((Player)getDefender);
/* 1340:1188 */     if (ItemLoreStats.plugin.isTool(((Player)getDefender).getItemInHand().getType())) {
/* 1341:1189 */       dodgePercent = this.gearStats.getDodgeGear((Player)getDefender) + this.gearStats.getDodgeItemInHand((Player)getDefender) + modifier;
/* 1342:     */     } else {
/* 1343:1191 */       dodgePercent = this.gearStats.getDodgeGear((Player)getDefender) + modifier;
/* 1344:     */     }
/* 1345:1194 */     if (dodgePercent > 100.0D) {
/* 1346:1195 */       dodgePercent = 100.0D;
/* 1347:     */     }
/* 1348:1198 */     if (dodgePercent >= random(100)) {
/* 1349:1199 */       return true;
/* 1350:     */     }
/* 1351:1201 */     return false;
/* 1352:     */   }
/* 1353:     */   
/* 1354:     */   public boolean defenderBlock(Entity getDefender)
/* 1355:     */   {
/* 1356:1205 */     if (!(getDefender instanceof Player)) {
/* 1357:1205 */       return false;
/* 1358:     */     }
/* 1359:1207 */     double blockPercent = 0.0D;
/* 1360:1208 */     double modifier = this.setBonuses.checkHashMapBlock((Player)getDefender);
/* 1361:1210 */     if (ItemLoreStats.plugin.isTool(((Player)getDefender).getItemInHand().getType())) {
/* 1362:1211 */       blockPercent = this.gearStats.getBlockGear((Player)getDefender) + this.gearStats.getBlockItemInHand((Player)getDefender) + modifier;
/* 1363:     */     } else {
/* 1364:1213 */       blockPercent = this.gearStats.getBlockGear((Player)getDefender) + modifier;
/* 1365:     */     }
/* 1366:1216 */     if (blockPercent > 100.0D) {
/* 1367:1217 */       blockPercent = 100.0D;
/* 1368:     */     }
/* 1369:1220 */     if (blockPercent >= random(100)) {
/* 1370:1221 */       return true;
/* 1371:     */     }
/* 1372:1223 */     return false;
/* 1373:     */   }
/* 1374:     */   
/* 1375:     */   public void blind(Entity getDefender, Entity getAttacker)
/* 1376:     */   {
/* 1377:1228 */     if (!(getAttacker instanceof Player)) {
/* 1378:1228 */       return;
/* 1379:     */     }
/* 1380:1230 */     double blindPercent = 0.0D;
/* 1381:1231 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1382:1232 */     double modifier = this.setBonuses.checkHashMapBlind((Player)getAttacker);
/* 1383:1234 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1384:1235 */       blindPercent = this.gearStats.getBlindGear((Player)getAttacker) + this.gearStats.getBlindItemInHand((Player)getAttacker) + modifier;
/* 1385:     */     } else {
/* 1386:1237 */       blindPercent = this.gearStats.getBlindGear((Player)getAttacker) + modifier;
/* 1387:     */     }
/* 1388:1240 */     if (blindPercent > 100.0D) {
/* 1389:1241 */       blindPercent = 100.0D;
/* 1390:     */     }
/* 1391:1244 */     if (random(100) <= blindPercent)
/* 1392:     */     {
/* 1393:1245 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.blind")) {
/* 1394:1246 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.BlindSuccess"));
/* 1395:     */       }
/* 1396:1248 */       if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyBlind"))) {
/* 1397:1250 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyBlindSuccess"));
/* 1398:     */       }
/* 1399:1253 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
/* 1400:     */     }
/* 1401:     */   }
/* 1402:     */   
/* 1403:     */   public double lifeSteal(Entity getDefender, Entity getAttacker, double weaponDamage)
/* 1404:     */   {
/* 1405:1257 */     if (!(getAttacker instanceof Player)) {
/* 1406:1257 */       return 0.0D;
/* 1407:     */     }
/* 1408:1259 */     double lifeStealHeal = 0.0D;
/* 1409:1260 */     double lifeStealPercent = 0.0D;
/* 1410:1261 */     double modifier = this.setBonuses.checkHashMapLifeSteal((Player)getAttacker);
/* 1411:1263 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1412:1264 */       lifeStealPercent = this.gearStats.getLifeStealGear((Player)getAttacker) + this.gearStats.getLifeStealItemInHand((Player)getAttacker) + modifier;
/* 1413:     */     } else {
/* 1414:1266 */       lifeStealPercent = this.gearStats.getLifeStealGear((Player)getAttacker) + modifier;
/* 1415:     */     }
/* 1416:1269 */     if (lifeStealPercent > 100.0D) {
/* 1417:1270 */       lifeStealPercent = 100.0D;
/* 1418:     */     }
/* 1419:1273 */     if (random(100) <= lifeStealPercent)
/* 1420:     */     {
/* 1421:1274 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.lifeSteal")) {
/* 1422:1275 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.LifeStealSuccess"));
/* 1423:     */       }
/* 1424:1277 */       if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyLifeSteal"))) {
/* 1425:1279 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyLifeStealSuccess"));
/* 1426:     */       }
/* 1427:1282 */       lifeStealHeal = ItemLoreStats.plugin.getConfig().getDouble("lifeStealHeal") * weaponDamage;
/* 1428:1283 */       return lifeStealHeal;
/* 1429:     */     }
/* 1430:1285 */     lifeStealHeal = 0.0D;
/* 1431:1286 */     return lifeStealHeal;
/* 1432:     */   }
/* 1433:     */   
/* 1434:     */   public void fire(Entity getDefender, Entity getAttacker)
/* 1435:     */   {
/* 1436:1290 */     if (!(getAttacker instanceof Player)) {
/* 1437:1290 */       return;
/* 1438:     */     }
/* 1439:1292 */     double firePercent = 0.0D;
/* 1440:1293 */     double modifier = this.setBonuses.checkHashMapFire((Player)getAttacker);
/* 1441:1295 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1442:1296 */       firePercent = this.gearStats.getFireGear((Player)getAttacker) + this.gearStats.getFireItemInHand((Player)getAttacker) + modifier;
/* 1443:     */     } else {
/* 1444:1298 */       firePercent = this.gearStats.getFireGear((Player)getAttacker) + modifier;
/* 1445:     */     }
/* 1446:1301 */     if (firePercent > 100.0D) {
/* 1447:1302 */       firePercent = 100.0D;
/* 1448:     */     }
/* 1449:1305 */     if (random(100) <= firePercent)
/* 1450:     */     {
/* 1451:1306 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.fire")) {
/* 1452:1307 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.FireSuccess"));
/* 1453:     */       }
/* 1454:1309 */       if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyFire"))) {
/* 1455:1311 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyFireSuccess"));
/* 1456:     */       }
/* 1457:1314 */       getDefender.setFireTicks(60);
/* 1458:     */     }
/* 1459:     */   }
/* 1460:     */   
/* 1461:     */   public void poison(Entity getDefender, Entity getAttacker)
/* 1462:     */   {
/* 1463:1318 */     if (!(getAttacker instanceof Player)) {
/* 1464:1318 */       return;
/* 1465:     */     }
/* 1466:1320 */     double poisonPercent = 0.0D;
/* 1467:1321 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1468:1322 */     double modifier = this.setBonuses.checkHashMapPoison((Player)getAttacker);
/* 1469:1324 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1470:1325 */       poisonPercent = this.gearStats.getPoisonGear((Player)getAttacker) + this.gearStats.getPoisonItemInHand((Player)getAttacker) + modifier;
/* 1471:     */     } else {
/* 1472:1327 */       poisonPercent = this.gearStats.getPoisonGear((Player)getAttacker) + modifier;
/* 1473:     */     }
/* 1474:1330 */     if (poisonPercent > 100.0D) {
/* 1475:1331 */       poisonPercent = 100.0D;
/* 1476:     */     }
/* 1477:1334 */     if (random(100) <= poisonPercent)
/* 1478:     */     {
/* 1479:1335 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.poison")) {
/* 1480:1336 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.PoisonSuccess"));
/* 1481:     */       }
/* 1482:1338 */       if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyPoison"))) {
/* 1483:1340 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyPoisonSuccess"));
/* 1484:     */       }
/* 1485:1343 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
/* 1486:     */     }
/* 1487:     */   }
/* 1488:     */   
/* 1489:     */   public void wither(Entity getDefender, Entity getAttacker)
/* 1490:     */   {
/* 1491:1347 */     if (!(getAttacker instanceof Player)) {
/* 1492:1347 */       return;
/* 1493:     */     }
/* 1494:1349 */     double witherPercent = 0.0D;
/* 1495:1350 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1496:1351 */     double modifier = this.setBonuses.checkHashMapWither((Player)getAttacker);
/* 1497:1353 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1498:1354 */       witherPercent = this.gearStats.getWitherGear((Player)getAttacker) + this.gearStats.getWitherItemInHand((Player)getAttacker) + modifier;
/* 1499:     */     } else {
/* 1500:1356 */       witherPercent = this.gearStats.getWitherGear((Player)getAttacker) + modifier;
/* 1501:     */     }
/* 1502:1359 */     if (witherPercent > 100.0D) {
/* 1503:1360 */       witherPercent = 100.0D;
/* 1504:     */     }
/* 1505:1363 */     if (random(100) <= witherPercent)
/* 1506:     */     {
/* 1507:1364 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.wither")) {
/* 1508:1365 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.WitherSuccess"));
/* 1509:     */       }
/* 1510:1367 */       if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyWither"))) {
/* 1511:1369 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyWitherSuccess"));
/* 1512:     */       }
/* 1513:1372 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
/* 1514:     */     }
/* 1515:     */   }
/* 1516:     */   
/* 1517:     */   public void harming(Entity getDefender, Entity getAttacker)
/* 1518:     */   {
/* 1519:1376 */     if (!(getAttacker instanceof Player)) {
/* 1520:1376 */       return;
/* 1521:     */     }
/* 1522:1378 */     double harmingPercent = 0.0D;
/* 1523:1379 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1524:1380 */     double modifier = this.setBonuses.checkHashMapHarming((Player)getAttacker);
/* 1525:1382 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1526:1383 */       harmingPercent = this.gearStats.getHarmingGear((Player)getAttacker) + this.gearStats.getHarmingItemInHand((Player)getAttacker) + modifier;
/* 1527:     */     } else {
/* 1528:1385 */       harmingPercent = this.gearStats.getHarmingGear((Player)getAttacker) + modifier;
/* 1529:     */     }
/* 1530:1388 */     if (harmingPercent > 100.0D) {
/* 1531:1389 */       harmingPercent = 100.0D;
/* 1532:     */     }
/* 1533:1392 */     if (random(100) <= harmingPercent)
/* 1534:     */     {
/* 1535:1393 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.harm")) {
/* 1536:1394 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.HarmSuccess"));
/* 1537:     */       }
/* 1538:1396 */       if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyHarm"))) {
/* 1539:1398 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyHarmSuccess"));
/* 1540:     */       }
/* 1541:1401 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 60, 1));
/* 1542:     */     }
/* 1543:     */   }
/* 1544:     */   
/* 1545:     */   public void ice(Entity getDefender, Entity getAttacker)
/* 1546:     */   {
/* 1547:1405 */     if (!(getAttacker instanceof Player)) {
/* 1548:1405 */       return;
/* 1549:     */     }
/* 1550:1407 */     double icePercent = 0.0D;
/* 1551:1408 */     LivingEntity entity = (LivingEntity)getDefender;
/* 1552:1409 */     double modifier = this.setBonuses.checkHashMapIce((Player)getAttacker);
/* 1553:1411 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1554:1412 */       icePercent = this.gearStats.getIceGear((Player)getAttacker) + this.gearStats.getIceItemInHand((Player)getAttacker) + modifier;
/* 1555:     */     } else {
/* 1556:1414 */       icePercent = this.gearStats.getIceGear((Player)getAttacker) + modifier;
/* 1557:     */     }
/* 1558:1417 */     if (icePercent > 100.0D) {
/* 1559:1418 */       icePercent = 100.0D;
/* 1560:     */     }
/* 1561:1421 */     if (random(100) <= icePercent)
/* 1562:     */     {
/* 1563:1422 */       if (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.ice")) {
/* 1564:1423 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.IceSuccess"));
/* 1565:     */       }
/* 1566:1425 */       if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyIce"))) {
/* 1567:1427 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyIceSuccess"));
/* 1568:     */       }
/* 1569:1430 */       entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
/* 1570:     */     }
/* 1571:     */   }
/* 1572:     */   
/* 1573:     */   public int criticalStrike(Entity getAttacker, Entity getDefender)
/* 1574:     */   {
/* 1575:1434 */     int critMultiplier = 1;
/* 1576:1435 */     double critPercent = 0.0D;
/* 1577:1436 */     double modifier = this.setBonuses.checkHashMapCritChance((Player)getAttacker);
/* 1578:1438 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1579:1439 */       critPercent = this.gearStats.getCritChanceGear((Player)getAttacker) + this.gearStats.getCritChanceItemInHand((Player)getAttacker) + modifier;
/* 1580:     */     } else {
/* 1581:1441 */       critPercent = this.gearStats.getCritChanceGear((Player)getAttacker) + modifier;
/* 1582:     */     }
/* 1583:1444 */     if (critPercent >= random(100))
/* 1584:     */     {
/* 1585:1445 */       if ((ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.outgoing.critStrike")) && ((getAttacker instanceof Player))) {
/* 1586:1447 */         ((Player)getAttacker).sendMessage(getResponse("DamageMessages.CriticalStrikeSuccess"));
/* 1587:     */       }
/* 1588:1450 */       if (((getDefender instanceof Player)) && (ItemLoreStats.plugin.getConfig().getBoolean("combatMessages.incoming.enemyCritStrike")) && ((getAttacker instanceof Player))) {
/* 1589:1453 */         ((Player)getDefender).sendMessage(((Player)getAttacker).getName() + " " + getResponse("DamageMessages.EnemyCriticalStrikeSuccess"));
/* 1590:     */       }
/* 1591:1456 */       critMultiplier = 2;
/* 1592:1457 */       return critMultiplier;
/* 1593:     */     }
/* 1594:1460 */     critMultiplier = 1;
/* 1595:1461 */     return critMultiplier;
/* 1596:     */   }
/* 1597:     */   
/* 1598:     */   public double reflect(Entity getAttacker)
/* 1599:     */   {
/* 1600:1465 */     double reflect = 0.0D;
/* 1601:1466 */     double modifier = this.setBonuses.checkHashMapReflect((Player)getAttacker);
/* 1602:1468 */     if (ItemLoreStats.plugin.isTool(((Player)getAttacker).getItemInHand().getType())) {
/* 1603:1469 */       reflect = this.gearStats.getReflectGear((Player)getAttacker) + this.gearStats.getReflectItemInHand((Player)getAttacker) + modifier;
/* 1604:     */     } else {
/* 1605:1471 */       reflect = this.gearStats.getReflectGear((Player)getAttacker) + modifier;
/* 1606:     */     }
/* 1607:1474 */     return reflect;
/* 1608:     */   }
/* 1609:     */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.DamageSystem
 * JD-Core Version:    0.7.0.1
 */