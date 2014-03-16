/*    1:     */ package com.github.supavitax.itemlorestats;
/*    2:     */ 
/*    3:     */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*    4:     */ import java.io.File;
/*    5:     */ import java.io.PrintStream;
/*    6:     */ import java.util.List;
/*    7:     */ import org.bukkit.ChatColor;
/*    8:     */ import org.bukkit.configuration.file.FileConfiguration;
/*    9:     */ import org.bukkit.configuration.file.YamlConfiguration;
/*   10:     */ import org.bukkit.entity.Player;
/*   11:     */ import org.bukkit.event.Listener;
/*   12:     */ import org.bukkit.inventory.ItemStack;
/*   13:     */ import org.bukkit.inventory.PlayerInventory;
/*   14:     */ import org.bukkit.inventory.meta.ItemMeta;
/*   15:     */ 
/*   16:     */ public class GearStats
/*   17:     */   implements Listener
/*   18:     */ {
/*   19:  19 */   Util_Colours util_Colours = new Util_Colours();
/*   20:     */   private FileConfiguration PlayerDataConfig;
/*   21:  21 */   String armour = null;
/*   22:  22 */   String dodge = null;
/*   23:  23 */   String block = null;
/*   24:  24 */   String critChance = null;
/*   25:  25 */   String critDamage = null;
/*   26:  26 */   String damage = null;
/*   27:  27 */   String health = null;
/*   28:  28 */   String healthRegen = null;
/*   29:  29 */   String lifeSteal = null;
/*   30:  30 */   String lifeStealHeal = null;
/*   31:  31 */   String reflect = null;
/*   32:  32 */   String ice = null;
/*   33:  33 */   String fire = null;
/*   34:  34 */   String poison = null;
/*   35:  35 */   String wither = null;
/*   36:  36 */   String harming = null;
/*   37:  37 */   String blind = null;
/*   38:  38 */   String movementspeed = null;
/*   39:  39 */   String xpmultiplier = null;
/*   40:  40 */   String pvpdamage = null;
/*   41:  41 */   String pvedamage = null;
/*   42:  42 */   String setbonus = null;
/*   43:  43 */   String xplevel = null;
/*   44:  44 */   String className = null;
/*   45:  45 */   String soulbound = null;
/*   46:  46 */   String durability = null;
/*   47:  47 */   String clickToCast = null;
/*   48:  48 */   String tnt = null;
/*   49:  49 */   String fireball = null;
/*   50:  50 */   String lightning = null;
/*   51:  51 */   String frostbolt = null;
/*   52:  52 */   String heal = null;
/*   53:     */   
/*   54:     */   public String getResponse(String getKeyFromLanguageFile)
/*   55:     */   {
/*   56:     */     try
/*   57:     */     {
/*   58:  57 */       this.PlayerDataConfig = new YamlConfiguration();
/*   59:  58 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*   60:     */       
/*   61:  60 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*   62:     */     }
/*   63:     */     catch (Exception e)
/*   64:     */     {
/*   65:  63 */       e.printStackTrace();
/*   66:  64 */       System.out.print("*********** Failed to load message from language file! ***********");
/*   67:     */     }
/*   68:  66 */     return "*********** Failed to load message from language file! ***********";
/*   69:     */   }
/*   70:     */   
/*   71:     */   public double getArmourGear(Player player)
/*   72:     */   {
/*   73:  72 */     this.armour = ItemLoreStats.plugin.getConfig().getString("statNames.armour").replaceAll("&([0-9a-f])", "");
/*   74:     */     
/*   75:  74 */     double headValue = 0.0D;
/*   76:  75 */     double chestValue = 0.0D;
/*   77:  76 */     double legsValue = 0.0D;
/*   78:  77 */     double bootsValue = 0.0D;
/*   79:     */     
/*   80:  79 */     ItemStack head = player.getInventory().getHelmet();
/*   81:  80 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*   82:     */     {
/*   83:  83 */       List<String> headLore = head.getItemMeta().getLore();
/*   84:  84 */       for (String line : headLore) {
/*   85:  85 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/*   86:     */         {
/*   87:  86 */           String value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/*   88:  87 */           headValue = Double.parseDouble(value);
/*   89:     */         }
/*   90:     */       }
/*   91:     */     }
/*   92:  94 */     ItemStack chest = player.getInventory().getChestplate();
/*   93:  95 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*   94:     */     {
/*   95:  98 */       List<String> chestLore = chest.getItemMeta().getLore();
/*   96:  99 */       for (String line : chestLore) {
/*   97: 100 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/*   98:     */         {
/*   99: 101 */           String value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/*  100: 102 */           chestValue = Double.parseDouble(value);
/*  101:     */         }
/*  102:     */       }
/*  103:     */     }
/*  104: 109 */     ItemStack legs = player.getInventory().getLeggings();
/*  105: 110 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  106:     */     {
/*  107: 113 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  108: 114 */       for (String line : legsLore) {
/*  109: 115 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/*  110:     */         {
/*  111: 116 */           String value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/*  112: 117 */           legsValue = Double.parseDouble(value);
/*  113:     */         }
/*  114:     */       }
/*  115:     */     }
/*  116: 124 */     ItemStack boots = player.getInventory().getBoots();
/*  117: 126 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  118:     */     {
/*  119: 129 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  120: 130 */       for (String line : bootsLore) {
/*  121: 131 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/*  122:     */         {
/*  123: 132 */           String value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/*  124:     */           
/*  125: 134 */           bootsValue = Double.parseDouble(value);
/*  126:     */         }
/*  127:     */       }
/*  128:     */     }
/*  129: 140 */     return headValue + chestValue + legsValue + bootsValue;
/*  130:     */   }
/*  131:     */   
/*  132:     */   public double getDodgeGear(Player player)
/*  133:     */   {
/*  134: 144 */     this.dodge = ItemLoreStats.plugin.getConfig().getString("statNames.dodge").replaceAll("&([0-9a-f])", "");
/*  135:     */     
/*  136: 146 */     double headValue = 0.0D;
/*  137: 147 */     double chestValue = 0.0D;
/*  138: 148 */     double legsValue = 0.0D;
/*  139: 149 */     double bootsValue = 0.0D;
/*  140:     */     
/*  141: 151 */     ItemStack head = player.getInventory().getHelmet();
/*  142: 153 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  143:     */     {
/*  144: 156 */       List<String> headLore = head.getItemMeta().getLore();
/*  145: 157 */       for (String line : headLore) {
/*  146: 158 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/*  147:     */         {
/*  148: 159 */           String value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/*  149: 160 */           headValue = Double.parseDouble(value);
/*  150:     */         }
/*  151:     */       }
/*  152:     */     }
/*  153: 167 */     ItemStack chest = player.getInventory().getChestplate();
/*  154: 168 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  155:     */     {
/*  156: 171 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  157: 172 */       for (String line : chestLore) {
/*  158: 173 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/*  159:     */         {
/*  160: 174 */           String value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/*  161: 175 */           chestValue = Double.parseDouble(value);
/*  162:     */         }
/*  163:     */       }
/*  164:     */     }
/*  165: 182 */     ItemStack legs = player.getInventory().getLeggings();
/*  166: 183 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  167:     */     {
/*  168: 186 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  169: 187 */       for (String line : legsLore) {
/*  170: 188 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/*  171:     */         {
/*  172: 189 */           String value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/*  173: 190 */           legsValue = Double.parseDouble(value);
/*  174:     */         }
/*  175:     */       }
/*  176:     */     }
/*  177: 197 */     ItemStack boots = player.getInventory().getBoots();
/*  178: 199 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  179:     */     {
/*  180: 202 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  181: 203 */       for (String line : bootsLore) {
/*  182: 204 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/*  183:     */         {
/*  184: 205 */           String value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/*  185: 206 */           bootsValue = Double.parseDouble(value);
/*  186:     */         }
/*  187:     */       }
/*  188:     */     }
/*  189: 213 */     return headValue + chestValue + legsValue + bootsValue;
/*  190:     */   }
/*  191:     */   
/*  192:     */   public double getBlockGear(Player player)
/*  193:     */   {
/*  194: 217 */     this.block = ItemLoreStats.plugin.getConfig().getString("statNames.block").replaceAll("&([0-9a-f])", "");
/*  195:     */     
/*  196: 219 */     double headValue = 0.0D;
/*  197: 220 */     double chestValue = 0.0D;
/*  198: 221 */     double legsValue = 0.0D;
/*  199: 222 */     double bootsValue = 0.0D;
/*  200:     */     
/*  201: 224 */     ItemStack head = player.getInventory().getHelmet();
/*  202: 226 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  203:     */     {
/*  204: 229 */       List<String> headLore = head.getItemMeta().getLore();
/*  205: 230 */       for (String line : headLore) {
/*  206: 231 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/*  207:     */         {
/*  208: 232 */           String value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/*  209: 233 */           headValue = Double.parseDouble(value);
/*  210:     */         }
/*  211:     */       }
/*  212:     */     }
/*  213: 240 */     ItemStack chest = player.getInventory().getChestplate();
/*  214: 241 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  215:     */     {
/*  216: 244 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  217: 245 */       for (String line : chestLore) {
/*  218: 246 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/*  219:     */         {
/*  220: 247 */           String value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/*  221: 248 */           chestValue = Double.parseDouble(value);
/*  222:     */         }
/*  223:     */       }
/*  224:     */     }
/*  225: 255 */     ItemStack legs = player.getInventory().getLeggings();
/*  226: 256 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  227:     */     {
/*  228: 259 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  229: 260 */       for (String line : legsLore) {
/*  230: 261 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/*  231:     */         {
/*  232: 262 */           String value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/*  233: 263 */           legsValue = Double.parseDouble(value);
/*  234:     */         }
/*  235:     */       }
/*  236:     */     }
/*  237: 270 */     ItemStack boots = player.getInventory().getBoots();
/*  238: 272 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  239:     */     {
/*  240: 275 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  241: 276 */       for (String line : bootsLore) {
/*  242: 277 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/*  243:     */         {
/*  244: 278 */           String value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/*  245: 279 */           bootsValue = Double.parseDouble(value);
/*  246:     */         }
/*  247:     */       }
/*  248:     */     }
/*  249: 286 */     return headValue + chestValue + legsValue + bootsValue;
/*  250:     */   }
/*  251:     */   
/*  252:     */   public String getDamageGear(Player player)
/*  253:     */   {
/*  254: 290 */     this.damage = ItemLoreStats.plugin.getConfig().getString("statNames.damage").replaceAll("&([0-9a-f])", "");
/*  255:     */     
/*  256: 292 */     double headMinValue = 0.0D;
/*  257: 293 */     double headMaxValue = 0.0D;
/*  258: 294 */     double chestMinValue = 0.0D;
/*  259: 295 */     double chestMaxValue = 0.0D;
/*  260: 296 */     double legsMinValue = 0.0D;
/*  261: 297 */     double legsMaxValue = 0.0D;
/*  262: 298 */     double bootsMinValue = 0.0D;
/*  263: 299 */     double bootsMaxValue = 0.0D;
/*  264:     */     
/*  265: 301 */     ItemStack head = player.getInventory().getHelmet();
/*  266: 303 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  267:     */     {
/*  268: 306 */       List<String> headLore = head.getItemMeta().getLore();
/*  269: 307 */       for (String line : headLore) {
/*  270: 308 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/*  271:     */         {
/*  272: 309 */           String value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/*  273: 310 */           if (value.contains("-"))
/*  274:     */           {
/*  275: 311 */             headMinValue = Double.parseDouble(value.split("-")[0]);
/*  276: 312 */             headMaxValue = Double.parseDouble(value.split("-")[1]);
/*  277:     */           }
/*  278:     */           else
/*  279:     */           {
/*  280: 314 */             headMinValue = Double.parseDouble(value);
/*  281: 315 */             headMaxValue = Double.parseDouble(value);
/*  282:     */           }
/*  283:     */         }
/*  284:     */       }
/*  285:     */     }
/*  286: 323 */     ItemStack chest = player.getInventory().getChestplate();
/*  287: 324 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  288:     */     {
/*  289: 327 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  290: 328 */       for (String line : chestLore) {
/*  291: 329 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/*  292:     */         {
/*  293: 330 */           String value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/*  294: 331 */           if (value.contains("-"))
/*  295:     */           {
/*  296: 332 */             chestMinValue = Double.parseDouble(value.split("-")[0]);
/*  297: 333 */             chestMaxValue = Double.parseDouble(value.split("-")[1]);
/*  298:     */           }
/*  299:     */           else
/*  300:     */           {
/*  301: 335 */             chestMinValue = Double.parseDouble(value);
/*  302: 336 */             chestMaxValue = Double.parseDouble(value);
/*  303:     */           }
/*  304:     */         }
/*  305:     */       }
/*  306:     */     }
/*  307: 344 */     ItemStack legs = player.getInventory().getLeggings();
/*  308: 345 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  309:     */     {
/*  310: 348 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  311: 349 */       for (String line : legsLore) {
/*  312: 350 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/*  313:     */         {
/*  314: 351 */           String value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/*  315: 352 */           if (value.contains("-"))
/*  316:     */           {
/*  317: 353 */             legsMinValue = Double.parseDouble(value.split("-")[0]);
/*  318: 354 */             legsMaxValue = Double.parseDouble(value.split("-")[1]);
/*  319:     */           }
/*  320:     */           else
/*  321:     */           {
/*  322: 356 */             legsMinValue = Double.parseDouble(value);
/*  323: 357 */             legsMaxValue = Double.parseDouble(value);
/*  324:     */           }
/*  325:     */         }
/*  326:     */       }
/*  327:     */     }
/*  328: 365 */     ItemStack boots = player.getInventory().getBoots();
/*  329: 367 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  330:     */     {
/*  331: 370 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  332: 371 */       for (String line : bootsLore) {
/*  333: 372 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/*  334:     */         {
/*  335: 373 */           String value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/*  336: 374 */           if (value.contains("-"))
/*  337:     */           {
/*  338: 375 */             bootsMinValue = Double.parseDouble(value.split("-")[0]);
/*  339: 376 */             bootsMaxValue = Double.parseDouble(value.split("-")[1]);
/*  340:     */           }
/*  341:     */           else
/*  342:     */           {
/*  343: 378 */             bootsMinValue = Double.parseDouble(value);
/*  344: 379 */             bootsMaxValue = Double.parseDouble(value);
/*  345:     */           }
/*  346:     */         }
/*  347:     */       }
/*  348:     */     }
/*  349: 387 */     return headMinValue + chestMinValue + legsMinValue + bootsMinValue + "-" + (headMaxValue + chestMaxValue + legsMaxValue + bootsMaxValue);
/*  350:     */   }
/*  351:     */   
/*  352:     */   public double getCritChanceGear(Player player)
/*  353:     */   {
/*  354: 391 */     this.critChance = ItemLoreStats.plugin.getConfig().getString("statNames.critChance").replaceAll("&([0-9a-f])", "");
/*  355:     */     
/*  356: 393 */     double headValue = 0.0D;
/*  357: 394 */     double chestValue = 0.0D;
/*  358: 395 */     double legsValue = 0.0D;
/*  359: 396 */     double bootsValue = 0.0D;
/*  360:     */     
/*  361: 398 */     ItemStack head = player.getInventory().getHelmet();
/*  362: 400 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  363:     */     {
/*  364: 403 */       List<String> headLore = head.getItemMeta().getLore();
/*  365: 404 */       for (String line : headLore) {
/*  366: 405 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/*  367:     */         {
/*  368: 406 */           String value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/*  369: 407 */           headValue = Double.parseDouble(value);
/*  370:     */         }
/*  371:     */       }
/*  372:     */     }
/*  373: 414 */     ItemStack chest = player.getInventory().getChestplate();
/*  374: 415 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  375:     */     {
/*  376: 418 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  377: 419 */       for (String line : chestLore) {
/*  378: 420 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/*  379:     */         {
/*  380: 421 */           String value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/*  381: 422 */           chestValue = Double.parseDouble(value);
/*  382:     */         }
/*  383:     */       }
/*  384:     */     }
/*  385: 429 */     ItemStack legs = player.getInventory().getLeggings();
/*  386: 430 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  387:     */     {
/*  388: 433 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  389: 434 */       for (String line : legsLore) {
/*  390: 435 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/*  391:     */         {
/*  392: 436 */           String value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/*  393: 437 */           legsValue = Double.parseDouble(value);
/*  394:     */         }
/*  395:     */       }
/*  396:     */     }
/*  397: 444 */     ItemStack boots = player.getInventory().getBoots();
/*  398: 446 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  399:     */     {
/*  400: 449 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  401: 450 */       for (String line : bootsLore) {
/*  402: 451 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/*  403:     */         {
/*  404: 452 */           String value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/*  405: 453 */           bootsValue = Double.parseDouble(value);
/*  406:     */         }
/*  407:     */       }
/*  408:     */     }
/*  409: 460 */     return headValue + chestValue + legsValue + bootsValue;
/*  410:     */   }
/*  411:     */   
/*  412:     */   public double getCritDamageGear(Player player)
/*  413:     */   {
/*  414: 464 */     this.critDamage = ItemLoreStats.plugin.getConfig().getString("statNames.critDamage").replaceAll("&([0-9a-f])", "");
/*  415:     */     
/*  416: 466 */     double headValue = 0.0D;
/*  417: 467 */     double chestValue = 0.0D;
/*  418: 468 */     double legsValue = 0.0D;
/*  419: 469 */     double bootsValue = 0.0D;
/*  420:     */     
/*  421: 471 */     ItemStack head = player.getInventory().getHelmet();
/*  422: 473 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  423:     */     {
/*  424: 476 */       List<String> headLore = head.getItemMeta().getLore();
/*  425: 477 */       for (String line : headLore) {
/*  426: 478 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/*  427:     */         {
/*  428: 479 */           String value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/*  429: 480 */           headValue = Double.parseDouble(value);
/*  430:     */         }
/*  431:     */       }
/*  432:     */     }
/*  433: 487 */     ItemStack chest = player.getInventory().getChestplate();
/*  434: 488 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  435:     */     {
/*  436: 491 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  437: 492 */       for (String line : chestLore) {
/*  438: 493 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/*  439:     */         {
/*  440: 494 */           String value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/*  441: 495 */           chestValue = Double.parseDouble(value);
/*  442:     */         }
/*  443:     */       }
/*  444:     */     }
/*  445: 502 */     ItemStack legs = player.getInventory().getLeggings();
/*  446: 503 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  447:     */     {
/*  448: 506 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  449: 507 */       for (String line : legsLore) {
/*  450: 508 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/*  451:     */         {
/*  452: 509 */           String value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/*  453: 510 */           legsValue = Double.parseDouble(value);
/*  454:     */         }
/*  455:     */       }
/*  456:     */     }
/*  457: 517 */     ItemStack boots = player.getInventory().getBoots();
/*  458: 519 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  459:     */     {
/*  460: 522 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  461: 523 */       for (String line : bootsLore) {
/*  462: 524 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/*  463:     */         {
/*  464: 525 */           String value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/*  465: 526 */           bootsValue = Double.parseDouble(value);
/*  466:     */         }
/*  467:     */       }
/*  468:     */     }
/*  469: 533 */     return headValue + chestValue + legsValue + bootsValue;
/*  470:     */   }
/*  471:     */   
/*  472:     */   public double getHealthGear(Player player)
/*  473:     */   {
/*  474: 537 */     this.health = ItemLoreStats.plugin.getConfig().getString("statNames.health").replaceAll("&([0-9a-f])", "");
/*  475:     */     
/*  476: 539 */     double headValue = 0.0D;
/*  477: 540 */     double chestValue = 0.0D;
/*  478: 541 */     double legsValue = 0.0D;
/*  479: 542 */     double bootsValue = 0.0D;
/*  480:     */     
/*  481: 544 */     ItemStack head = player.getInventory().getHelmet();
/*  482: 546 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  483:     */     {
/*  484: 549 */       List<String> headLore = head.getItemMeta().getLore();
/*  485: 550 */       for (String line : headLore) {
/*  486: 551 */         if (ChatColor.stripColor(line).startsWith(this.health + ": "))
/*  487:     */         {
/*  488: 552 */           String value = ChatColor.stripColor(line).substring((this.health + ": ").length()).trim();
/*  489: 553 */           headValue = Double.parseDouble(value);
/*  490:     */         }
/*  491:     */       }
/*  492:     */     }
/*  493: 560 */     ItemStack chest = player.getInventory().getChestplate();
/*  494: 561 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  495:     */     {
/*  496: 564 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  497: 565 */       for (String line : chestLore) {
/*  498: 566 */         if (ChatColor.stripColor(line).startsWith(this.health + ": "))
/*  499:     */         {
/*  500: 567 */           String value = ChatColor.stripColor(line).substring((this.health + ": ").length()).trim();
/*  501: 568 */           chestValue = Double.parseDouble(value);
/*  502:     */         }
/*  503:     */       }
/*  504:     */     }
/*  505: 575 */     ItemStack legs = player.getInventory().getLeggings();
/*  506: 576 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  507:     */     {
/*  508: 579 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  509: 580 */       for (String line : legsLore) {
/*  510: 581 */         if (ChatColor.stripColor(line).startsWith(this.health + ": "))
/*  511:     */         {
/*  512: 582 */           String value = ChatColor.stripColor(line).substring((this.health + ": ").length()).trim();
/*  513: 583 */           legsValue = Double.parseDouble(value);
/*  514:     */         }
/*  515:     */       }
/*  516:     */     }
/*  517: 590 */     ItemStack boots = player.getInventory().getBoots();
/*  518: 592 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  519:     */     {
/*  520: 595 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  521: 596 */       for (String line : bootsLore) {
/*  522: 597 */         if (ChatColor.stripColor(line).startsWith(this.health + ": "))
/*  523:     */         {
/*  524: 598 */           String value = ChatColor.stripColor(line).substring((this.health + ": ").length()).trim();
/*  525: 599 */           bootsValue = Double.parseDouble(value);
/*  526:     */         }
/*  527:     */       }
/*  528:     */     }
/*  529: 606 */     return headValue + chestValue + legsValue + bootsValue;
/*  530:     */   }
/*  531:     */   
/*  532:     */   public double getHealthRegenGear(Player player)
/*  533:     */   {
/*  534: 610 */     this.healthRegen = ItemLoreStats.plugin.getConfig().getString("statNames.healthregen").replaceAll("&([0-9a-f])", "");
/*  535:     */     
/*  536: 612 */     double headValue = 0.0D;
/*  537: 613 */     double chestValue = 0.0D;
/*  538: 614 */     double legsValue = 0.0D;
/*  539: 615 */     double bootsValue = 0.0D;
/*  540:     */     
/*  541: 617 */     ItemStack head = player.getInventory().getHelmet();
/*  542: 619 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  543:     */     {
/*  544: 622 */       List<String> headLore = head.getItemMeta().getLore();
/*  545: 623 */       for (String line : headLore) {
/*  546: 624 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/*  547:     */         {
/*  548: 625 */           String value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/*  549: 626 */           headValue = Double.parseDouble(value);
/*  550:     */         }
/*  551:     */       }
/*  552:     */     }
/*  553: 633 */     ItemStack chest = player.getInventory().getChestplate();
/*  554: 634 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  555:     */     {
/*  556: 637 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  557: 638 */       for (String line : chestLore) {
/*  558: 639 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/*  559:     */         {
/*  560: 640 */           String value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/*  561: 641 */           chestValue = Double.parseDouble(value);
/*  562:     */         }
/*  563:     */       }
/*  564:     */     }
/*  565: 648 */     ItemStack legs = player.getInventory().getLeggings();
/*  566: 649 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  567:     */     {
/*  568: 652 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  569: 653 */       for (String line : legsLore) {
/*  570: 654 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/*  571:     */         {
/*  572: 655 */           String value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/*  573: 656 */           legsValue = Double.parseDouble(value);
/*  574:     */         }
/*  575:     */       }
/*  576:     */     }
/*  577: 663 */     ItemStack boots = player.getInventory().getBoots();
/*  578: 665 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  579:     */     {
/*  580: 668 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  581: 669 */       for (String line : bootsLore) {
/*  582: 670 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/*  583:     */         {
/*  584: 671 */           String value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/*  585: 672 */           bootsValue = Double.parseDouble(value);
/*  586:     */         }
/*  587:     */       }
/*  588:     */     }
/*  589: 679 */     return headValue + chestValue + legsValue + bootsValue;
/*  590:     */   }
/*  591:     */   
/*  592:     */   public double getLifeStealGear(Player player)
/*  593:     */   {
/*  594: 683 */     this.lifeSteal = ItemLoreStats.plugin.getConfig().getString("statNames.lifesteal").replaceAll("&([0-9a-f])", "");
/*  595:     */     
/*  596: 685 */     double headValue = 0.0D;
/*  597: 686 */     double chestValue = 0.0D;
/*  598: 687 */     double legsValue = 0.0D;
/*  599: 688 */     double bootsValue = 0.0D;
/*  600:     */     
/*  601: 690 */     ItemStack head = player.getInventory().getHelmet();
/*  602: 692 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  603:     */     {
/*  604: 695 */       List<String> headLore = head.getItemMeta().getLore();
/*  605: 696 */       for (String line : headLore) {
/*  606: 697 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/*  607:     */         {
/*  608: 698 */           String value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/*  609: 699 */           headValue = Double.parseDouble(value);
/*  610:     */         }
/*  611:     */       }
/*  612:     */     }
/*  613: 706 */     ItemStack chest = player.getInventory().getChestplate();
/*  614: 707 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  615:     */     {
/*  616: 710 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  617: 711 */       for (String line : chestLore) {
/*  618: 712 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/*  619:     */         {
/*  620: 713 */           String value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/*  621: 714 */           chestValue = Double.parseDouble(value);
/*  622:     */         }
/*  623:     */       }
/*  624:     */     }
/*  625: 721 */     ItemStack legs = player.getInventory().getLeggings();
/*  626: 722 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  627:     */     {
/*  628: 725 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  629: 726 */       for (String line : legsLore) {
/*  630: 727 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/*  631:     */         {
/*  632: 728 */           String value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/*  633: 729 */           legsValue = Double.parseDouble(value);
/*  634:     */         }
/*  635:     */       }
/*  636:     */     }
/*  637: 736 */     ItemStack boots = player.getInventory().getBoots();
/*  638: 738 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  639:     */     {
/*  640: 741 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  641: 742 */       for (String line : bootsLore) {
/*  642: 743 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/*  643:     */         {
/*  644: 744 */           String value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/*  645: 745 */           bootsValue = Double.parseDouble(value);
/*  646:     */         }
/*  647:     */       }
/*  648:     */     }
/*  649: 752 */     return headValue + chestValue + legsValue + bootsValue;
/*  650:     */   }
/*  651:     */   
/*  652:     */   public double getLifeStealHealGear(Player player)
/*  653:     */   {
/*  654: 756 */     this.lifeStealHeal = ItemLoreStats.plugin.getConfig().getString("statNames.lifestealheal").replaceAll("&([0-9a-f])", "");
/*  655:     */     
/*  656: 758 */     double headValue = 0.0D;
/*  657: 759 */     double chestValue = 0.0D;
/*  658: 760 */     double legsValue = 0.0D;
/*  659: 761 */     double bootsValue = 0.0D;
/*  660:     */     
/*  661: 763 */     ItemStack head = player.getInventory().getHelmet();
/*  662: 765 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  663:     */     {
/*  664: 768 */       List<String> headLore = head.getItemMeta().getLore();
/*  665: 769 */       for (String line : headLore) {
/*  666: 770 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/*  667:     */         {
/*  668: 771 */           String value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/*  669: 772 */           headValue = Double.parseDouble(value);
/*  670:     */         }
/*  671:     */       }
/*  672:     */     }
/*  673: 779 */     ItemStack chest = player.getInventory().getChestplate();
/*  674: 780 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  675:     */     {
/*  676: 783 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  677: 784 */       for (String line : chestLore) {
/*  678: 785 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/*  679:     */         {
/*  680: 786 */           String value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/*  681: 787 */           chestValue = Double.parseDouble(value);
/*  682:     */         }
/*  683:     */       }
/*  684:     */     }
/*  685: 794 */     ItemStack legs = player.getInventory().getLeggings();
/*  686: 795 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  687:     */     {
/*  688: 798 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  689: 799 */       for (String line : legsLore) {
/*  690: 800 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/*  691:     */         {
/*  692: 801 */           String value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/*  693: 802 */           legsValue = Double.parseDouble(value);
/*  694:     */         }
/*  695:     */       }
/*  696:     */     }
/*  697: 809 */     ItemStack boots = player.getInventory().getBoots();
/*  698: 811 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  699:     */     {
/*  700: 814 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  701: 815 */       for (String line : bootsLore) {
/*  702: 816 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/*  703:     */         {
/*  704: 817 */           String value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/*  705: 818 */           bootsValue = Double.parseDouble(value);
/*  706:     */         }
/*  707:     */       }
/*  708:     */     }
/*  709: 825 */     return headValue + chestValue + legsValue + bootsValue;
/*  710:     */   }
/*  711:     */   
/*  712:     */   public double getReflectGear(Player player)
/*  713:     */   {
/*  714: 829 */     this.reflect = ItemLoreStats.plugin.getConfig().getString("statNames.reflect").replaceAll("&([0-9a-f])", "");
/*  715:     */     
/*  716: 831 */     double headValue = 0.0D;
/*  717: 832 */     double chestValue = 0.0D;
/*  718: 833 */     double legsValue = 0.0D;
/*  719: 834 */     double bootsValue = 0.0D;
/*  720:     */     
/*  721: 836 */     ItemStack head = player.getInventory().getHelmet();
/*  722: 838 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  723:     */     {
/*  724: 841 */       List<String> headLore = head.getItemMeta().getLore();
/*  725: 842 */       for (String line : headLore) {
/*  726: 843 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/*  727:     */         {
/*  728: 844 */           String value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/*  729: 845 */           headValue = Double.parseDouble(value);
/*  730:     */         }
/*  731:     */       }
/*  732:     */     }
/*  733: 852 */     ItemStack chest = player.getInventory().getChestplate();
/*  734: 853 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  735:     */     {
/*  736: 856 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  737: 857 */       for (String line : chestLore) {
/*  738: 858 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/*  739:     */         {
/*  740: 859 */           String value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/*  741: 860 */           chestValue = Double.parseDouble(value);
/*  742:     */         }
/*  743:     */       }
/*  744:     */     }
/*  745: 867 */     ItemStack legs = player.getInventory().getLeggings();
/*  746: 868 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  747:     */     {
/*  748: 871 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  749: 872 */       for (String line : legsLore) {
/*  750: 873 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/*  751:     */         {
/*  752: 874 */           String value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/*  753: 875 */           legsValue = Double.parseDouble(value);
/*  754:     */         }
/*  755:     */       }
/*  756:     */     }
/*  757: 882 */     ItemStack boots = player.getInventory().getBoots();
/*  758: 884 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  759:     */     {
/*  760: 887 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  761: 888 */       for (String line : bootsLore) {
/*  762: 889 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/*  763:     */         {
/*  764: 890 */           String value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/*  765: 891 */           bootsValue = Double.parseDouble(value);
/*  766:     */         }
/*  767:     */       }
/*  768:     */     }
/*  769: 898 */     return headValue + chestValue + legsValue + bootsValue;
/*  770:     */   }
/*  771:     */   
/*  772:     */   public double getFireGear(Player player)
/*  773:     */   {
/*  774: 902 */     this.fire = ItemLoreStats.plugin.getConfig().getString("statNames.fire").replaceAll("&([0-9a-f])", "");
/*  775:     */     
/*  776: 904 */     double headValue = 0.0D;
/*  777: 905 */     double chestValue = 0.0D;
/*  778: 906 */     double legsValue = 0.0D;
/*  779: 907 */     double bootsValue = 0.0D;
/*  780:     */     
/*  781: 909 */     ItemStack head = player.getInventory().getHelmet();
/*  782: 911 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  783:     */     {
/*  784: 914 */       List<String> headLore = head.getItemMeta().getLore();
/*  785: 915 */       for (String line : headLore) {
/*  786: 916 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/*  787:     */         {
/*  788: 917 */           String value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/*  789: 918 */           headValue = Double.parseDouble(value);
/*  790:     */         }
/*  791:     */       }
/*  792:     */     }
/*  793: 925 */     ItemStack chest = player.getInventory().getChestplate();
/*  794: 926 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  795:     */     {
/*  796: 929 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  797: 930 */       for (String line : chestLore) {
/*  798: 931 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/*  799:     */         {
/*  800: 932 */           String value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/*  801: 933 */           chestValue = Double.parseDouble(value);
/*  802:     */         }
/*  803:     */       }
/*  804:     */     }
/*  805: 940 */     ItemStack legs = player.getInventory().getLeggings();
/*  806: 941 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  807:     */     {
/*  808: 944 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  809: 945 */       for (String line : legsLore) {
/*  810: 946 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/*  811:     */         {
/*  812: 947 */           String value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/*  813: 948 */           legsValue = Double.parseDouble(value);
/*  814:     */         }
/*  815:     */       }
/*  816:     */     }
/*  817: 955 */     ItemStack boots = player.getInventory().getBoots();
/*  818: 957 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  819:     */     {
/*  820: 960 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  821: 961 */       for (String line : bootsLore) {
/*  822: 962 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/*  823:     */         {
/*  824: 963 */           String value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/*  825: 964 */           bootsValue = Double.parseDouble(value);
/*  826:     */         }
/*  827:     */       }
/*  828:     */     }
/*  829: 971 */     return headValue + chestValue + legsValue + bootsValue;
/*  830:     */   }
/*  831:     */   
/*  832:     */   public double getIceGear(Player player)
/*  833:     */   {
/*  834: 975 */     this.ice = ItemLoreStats.plugin.getConfig().getString("statNames.ice").replaceAll("&([0-9a-f])", "");
/*  835:     */     
/*  836: 977 */     double headValue = 0.0D;
/*  837: 978 */     double chestValue = 0.0D;
/*  838: 979 */     double legsValue = 0.0D;
/*  839: 980 */     double bootsValue = 0.0D;
/*  840:     */     
/*  841: 982 */     ItemStack head = player.getInventory().getHelmet();
/*  842: 984 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  843:     */     {
/*  844: 987 */       List<String> headLore = head.getItemMeta().getLore();
/*  845: 988 */       for (String line : headLore) {
/*  846: 989 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/*  847:     */         {
/*  848: 990 */           String value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/*  849: 991 */           headValue = Double.parseDouble(value);
/*  850:     */         }
/*  851:     */       }
/*  852:     */     }
/*  853: 998 */     ItemStack chest = player.getInventory().getChestplate();
/*  854: 999 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  855:     */     {
/*  856:1002 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  857:1003 */       for (String line : chestLore) {
/*  858:1004 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/*  859:     */         {
/*  860:1005 */           String value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/*  861:1006 */           chestValue = Double.parseDouble(value);
/*  862:     */         }
/*  863:     */       }
/*  864:     */     }
/*  865:1013 */     ItemStack legs = player.getInventory().getLeggings();
/*  866:1014 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  867:     */     {
/*  868:1017 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  869:1018 */       for (String line : legsLore) {
/*  870:1019 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/*  871:     */         {
/*  872:1020 */           String value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/*  873:1021 */           legsValue = Double.parseDouble(value);
/*  874:     */         }
/*  875:     */       }
/*  876:     */     }
/*  877:1028 */     ItemStack boots = player.getInventory().getBoots();
/*  878:1030 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  879:     */     {
/*  880:1033 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  881:1034 */       for (String line : bootsLore) {
/*  882:1035 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/*  883:     */         {
/*  884:1036 */           String value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/*  885:1037 */           bootsValue = Double.parseDouble(value);
/*  886:     */         }
/*  887:     */       }
/*  888:     */     }
/*  889:1044 */     return headValue + chestValue + legsValue + bootsValue;
/*  890:     */   }
/*  891:     */   
/*  892:     */   public double getPoisonGear(Player player)
/*  893:     */   {
/*  894:1048 */     this.poison = ItemLoreStats.plugin.getConfig().getString("statNames.poison").replaceAll("&([0-9a-f])", "");
/*  895:     */     
/*  896:1050 */     double headValue = 0.0D;
/*  897:1051 */     double chestValue = 0.0D;
/*  898:1052 */     double legsValue = 0.0D;
/*  899:1053 */     double bootsValue = 0.0D;
/*  900:     */     
/*  901:1055 */     ItemStack head = player.getInventory().getHelmet();
/*  902:1057 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  903:     */     {
/*  904:1060 */       List<String> headLore = head.getItemMeta().getLore();
/*  905:1061 */       for (String line : headLore) {
/*  906:1062 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/*  907:     */         {
/*  908:1063 */           String value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/*  909:1064 */           headValue = Double.parseDouble(value);
/*  910:     */         }
/*  911:     */       }
/*  912:     */     }
/*  913:1071 */     ItemStack chest = player.getInventory().getChestplate();
/*  914:1072 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  915:     */     {
/*  916:1075 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  917:1076 */       for (String line : chestLore) {
/*  918:1077 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/*  919:     */         {
/*  920:1078 */           String value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/*  921:1079 */           chestValue = Double.parseDouble(value);
/*  922:     */         }
/*  923:     */       }
/*  924:     */     }
/*  925:1086 */     ItemStack legs = player.getInventory().getLeggings();
/*  926:1087 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  927:     */     {
/*  928:1090 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  929:1091 */       for (String line : legsLore) {
/*  930:1092 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/*  931:     */         {
/*  932:1093 */           String value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/*  933:1094 */           legsValue = Double.parseDouble(value);
/*  934:     */         }
/*  935:     */       }
/*  936:     */     }
/*  937:1101 */     ItemStack boots = player.getInventory().getBoots();
/*  938:1103 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  939:     */     {
/*  940:1106 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  941:1107 */       for (String line : bootsLore) {
/*  942:1108 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/*  943:     */         {
/*  944:1109 */           String value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/*  945:1110 */           bootsValue = Double.parseDouble(value);
/*  946:     */         }
/*  947:     */       }
/*  948:     */     }
/*  949:1117 */     return headValue + chestValue + legsValue + bootsValue;
/*  950:     */   }
/*  951:     */   
/*  952:     */   public double getWitherGear(Player player)
/*  953:     */   {
/*  954:1121 */     this.wither = ItemLoreStats.plugin.getConfig().getString("statNames.wither").replaceAll("&([0-9a-f])", "");
/*  955:     */     
/*  956:1123 */     double headValue = 0.0D;
/*  957:1124 */     double chestValue = 0.0D;
/*  958:1125 */     double legsValue = 0.0D;
/*  959:1126 */     double bootsValue = 0.0D;
/*  960:     */     
/*  961:1128 */     ItemStack head = player.getInventory().getHelmet();
/*  962:1130 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/*  963:     */     {
/*  964:1133 */       List<String> headLore = head.getItemMeta().getLore();
/*  965:1134 */       for (String line : headLore) {
/*  966:1135 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/*  967:     */         {
/*  968:1136 */           String value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/*  969:1137 */           headValue = Double.parseDouble(value);
/*  970:     */         }
/*  971:     */       }
/*  972:     */     }
/*  973:1144 */     ItemStack chest = player.getInventory().getChestplate();
/*  974:1145 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/*  975:     */     {
/*  976:1148 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  977:1149 */       for (String line : chestLore) {
/*  978:1150 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/*  979:     */         {
/*  980:1151 */           String value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/*  981:1152 */           chestValue = Double.parseDouble(value);
/*  982:     */         }
/*  983:     */       }
/*  984:     */     }
/*  985:1159 */     ItemStack legs = player.getInventory().getLeggings();
/*  986:1160 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/*  987:     */     {
/*  988:1163 */       List<String> legsLore = legs.getItemMeta().getLore();
/*  989:1164 */       for (String line : legsLore) {
/*  990:1165 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/*  991:     */         {
/*  992:1166 */           String value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/*  993:1167 */           legsValue = Double.parseDouble(value);
/*  994:     */         }
/*  995:     */       }
/*  996:     */     }
/*  997:1174 */     ItemStack boots = player.getInventory().getBoots();
/*  998:1176 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/*  999:     */     {
/* 1000:1179 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1001:1180 */       for (String line : bootsLore) {
/* 1002:1181 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/* 1003:     */         {
/* 1004:1182 */           String value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/* 1005:1183 */           bootsValue = Double.parseDouble(value);
/* 1006:     */         }
/* 1007:     */       }
/* 1008:     */     }
/* 1009:1190 */     return headValue + chestValue + legsValue + bootsValue;
/* 1010:     */   }
/* 1011:     */   
/* 1012:     */   public double getHarmingGear(Player player)
/* 1013:     */   {
/* 1014:1194 */     this.harming = ItemLoreStats.plugin.getConfig().getString("statNames.harming").replaceAll("&([0-9a-f])", "");
/* 1015:     */     
/* 1016:1196 */     double headValue = 0.0D;
/* 1017:1197 */     double chestValue = 0.0D;
/* 1018:1198 */     double legsValue = 0.0D;
/* 1019:1199 */     double bootsValue = 0.0D;
/* 1020:     */     
/* 1021:1201 */     ItemStack head = player.getInventory().getHelmet();
/* 1022:1203 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/* 1023:     */     {
/* 1024:1206 */       List<String> headLore = head.getItemMeta().getLore();
/* 1025:1207 */       for (String line : headLore) {
/* 1026:1208 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1027:     */         {
/* 1028:1209 */           String value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1029:1210 */           headValue = Double.parseDouble(value);
/* 1030:     */         }
/* 1031:     */       }
/* 1032:     */     }
/* 1033:1217 */     ItemStack chest = player.getInventory().getChestplate();
/* 1034:1218 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/* 1035:     */     {
/* 1036:1221 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1037:1222 */       for (String line : chestLore) {
/* 1038:1223 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1039:     */         {
/* 1040:1224 */           String value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1041:1225 */           chestValue = Double.parseDouble(value);
/* 1042:     */         }
/* 1043:     */       }
/* 1044:     */     }
/* 1045:1232 */     ItemStack legs = player.getInventory().getLeggings();
/* 1046:1233 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/* 1047:     */     {
/* 1048:1236 */       List<String> legsLore = legs.getItemMeta().getLore();
/* 1049:1237 */       for (String line : legsLore) {
/* 1050:1238 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1051:     */         {
/* 1052:1239 */           String value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1053:1240 */           legsValue = Double.parseDouble(value);
/* 1054:     */         }
/* 1055:     */       }
/* 1056:     */     }
/* 1057:1247 */     ItemStack boots = player.getInventory().getBoots();
/* 1058:1249 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/* 1059:     */     {
/* 1060:1252 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1061:1253 */       for (String line : bootsLore) {
/* 1062:1254 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1063:     */         {
/* 1064:1255 */           String value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1065:1256 */           bootsValue = Double.parseDouble(value);
/* 1066:     */         }
/* 1067:     */       }
/* 1068:     */     }
/* 1069:1263 */     return headValue + chestValue + legsValue + bootsValue;
/* 1070:     */   }
/* 1071:     */   
/* 1072:     */   public double getBlindGear(Player player)
/* 1073:     */   {
/* 1074:1267 */     this.blind = ItemLoreStats.plugin.getConfig().getString("statNames.blind").replaceAll("&([0-9a-f])", "");
/* 1075:     */     
/* 1076:1269 */     double headValue = 0.0D;
/* 1077:1270 */     double chestValue = 0.0D;
/* 1078:1271 */     double legsValue = 0.0D;
/* 1079:1272 */     double bootsValue = 0.0D;
/* 1080:     */     
/* 1081:1274 */     ItemStack head = player.getInventory().getHelmet();
/* 1082:1276 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/* 1083:     */     {
/* 1084:1279 */       List<String> headLore = head.getItemMeta().getLore();
/* 1085:1280 */       for (String line : headLore) {
/* 1086:1281 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1087:     */         {
/* 1088:1282 */           String value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1089:1283 */           headValue = Double.parseDouble(value);
/* 1090:     */         }
/* 1091:     */       }
/* 1092:     */     }
/* 1093:1290 */     ItemStack chest = player.getInventory().getChestplate();
/* 1094:1291 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/* 1095:     */     {
/* 1096:1294 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1097:1295 */       for (String line : chestLore) {
/* 1098:1296 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1099:     */         {
/* 1100:1297 */           String value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1101:1298 */           chestValue = Double.parseDouble(value);
/* 1102:     */         }
/* 1103:     */       }
/* 1104:     */     }
/* 1105:1305 */     ItemStack legs = player.getInventory().getLeggings();
/* 1106:1306 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/* 1107:     */     {
/* 1108:1309 */       List<String> legsLore = legs.getItemMeta().getLore();
/* 1109:1310 */       for (String line : legsLore) {
/* 1110:1311 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1111:     */         {
/* 1112:1312 */           String value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1113:1313 */           legsValue = Double.parseDouble(value);
/* 1114:     */         }
/* 1115:     */       }
/* 1116:     */     }
/* 1117:1320 */     ItemStack boots = player.getInventory().getBoots();
/* 1118:1322 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/* 1119:     */     {
/* 1120:1325 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1121:1326 */       for (String line : bootsLore) {
/* 1122:1327 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1123:     */         {
/* 1124:1328 */           String value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1125:1329 */           bootsValue = Double.parseDouble(value);
/* 1126:     */         }
/* 1127:     */       }
/* 1128:     */     }
/* 1129:1336 */     return headValue + chestValue + legsValue + bootsValue;
/* 1130:     */   }
/* 1131:     */   
/* 1132:     */   public double getMovementSpeedGear(Player player)
/* 1133:     */   {
/* 1134:1340 */     this.movementspeed = ItemLoreStats.plugin.getConfig().getString("statNames.movementspeed").replaceAll("&([0-9a-f])", "");
/* 1135:     */     
/* 1136:1342 */     double headValue = 0.0D;
/* 1137:1343 */     double chestValue = 0.0D;
/* 1138:1344 */     double legsValue = 0.0D;
/* 1139:1345 */     double bootsValue = 0.0D;
/* 1140:     */     
/* 1141:1347 */     ItemStack head = player.getInventory().getHelmet();
/* 1142:1349 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/* 1143:     */     {
/* 1144:1352 */       List<String> headLore = head.getItemMeta().getLore();
/* 1145:1353 */       for (String line : headLore) {
/* 1146:1354 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1147:     */         {
/* 1148:1355 */           String value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1149:1356 */           headValue = Double.parseDouble(value);
/* 1150:     */         }
/* 1151:     */       }
/* 1152:     */     }
/* 1153:1363 */     ItemStack chest = player.getInventory().getChestplate();
/* 1154:1364 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/* 1155:     */     {
/* 1156:1367 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1157:1368 */       for (String line : chestLore) {
/* 1158:1369 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1159:     */         {
/* 1160:1370 */           String value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1161:1371 */           chestValue = Double.parseDouble(value);
/* 1162:     */         }
/* 1163:     */       }
/* 1164:     */     }
/* 1165:1378 */     ItemStack legs = player.getInventory().getLeggings();
/* 1166:1379 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/* 1167:     */     {
/* 1168:1382 */       List<String> legsLore = legs.getItemMeta().getLore();
/* 1169:1383 */       for (String line : legsLore) {
/* 1170:1384 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1171:     */         {
/* 1172:1385 */           String value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1173:1386 */           legsValue = Double.parseDouble(value);
/* 1174:     */         }
/* 1175:     */       }
/* 1176:     */     }
/* 1177:1393 */     ItemStack boots = player.getInventory().getBoots();
/* 1178:1395 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/* 1179:     */     {
/* 1180:1398 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1181:1399 */       for (String line : bootsLore) {
/* 1182:1400 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1183:     */         {
/* 1184:1401 */           String value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1185:1402 */           bootsValue = Double.parseDouble(value);
/* 1186:     */         }
/* 1187:     */       }
/* 1188:     */     }
/* 1189:1409 */     return headValue + chestValue + legsValue + bootsValue;
/* 1190:     */   }
/* 1191:     */   
/* 1192:     */   public double getXPMultiplierGear(Player player)
/* 1193:     */   {
/* 1194:1413 */     this.xpmultiplier = ItemLoreStats.plugin.getConfig().getString("statNames.xpmultiplier").replaceAll("&([0-9a-f])", "");
/* 1195:     */     
/* 1196:1415 */     double headValue = 0.0D;
/* 1197:1416 */     double chestValue = 0.0D;
/* 1198:1417 */     double legsValue = 0.0D;
/* 1199:1418 */     double bootsValue = 0.0D;
/* 1200:     */     
/* 1201:1420 */     ItemStack head = player.getInventory().getHelmet();
/* 1202:1422 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/* 1203:     */     {
/* 1204:1425 */       List<String> headLore = head.getItemMeta().getLore();
/* 1205:1426 */       for (String line : headLore) {
/* 1206:1427 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1207:     */         {
/* 1208:1428 */           String value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1209:1429 */           headValue = Double.parseDouble(value);
/* 1210:     */         }
/* 1211:     */       }
/* 1212:     */     }
/* 1213:1436 */     ItemStack chest = player.getInventory().getChestplate();
/* 1214:1437 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/* 1215:     */     {
/* 1216:1440 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1217:1441 */       for (String line : chestLore) {
/* 1218:1442 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1219:     */         {
/* 1220:1443 */           String value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1221:1444 */           chestValue = Double.parseDouble(value);
/* 1222:     */         }
/* 1223:     */       }
/* 1224:     */     }
/* 1225:1451 */     ItemStack legs = player.getInventory().getLeggings();
/* 1226:1452 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/* 1227:     */     {
/* 1228:1455 */       List<String> legsLore = legs.getItemMeta().getLore();
/* 1229:1456 */       for (String line : legsLore) {
/* 1230:1457 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1231:     */         {
/* 1232:1458 */           String value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1233:1459 */           legsValue = Double.parseDouble(value);
/* 1234:     */         }
/* 1235:     */       }
/* 1236:     */     }
/* 1237:1466 */     ItemStack boots = player.getInventory().getBoots();
/* 1238:1468 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/* 1239:     */     {
/* 1240:1471 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1241:1472 */       for (String line : bootsLore) {
/* 1242:1473 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1243:     */         {
/* 1244:1474 */           String value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1245:1475 */           bootsValue = Double.parseDouble(value);
/* 1246:     */         }
/* 1247:     */       }
/* 1248:     */     }
/* 1249:1482 */     return headValue + chestValue + legsValue + bootsValue;
/* 1250:     */   }
/* 1251:     */   
/* 1252:     */   public double getArmourItemInHand(Player player)
/* 1253:     */   {
/* 1254:1487 */     this.armour = ItemLoreStats.plugin.getConfig().getString("statNames.armour").replaceAll("&([0-9a-f])", "");
/* 1255:     */     
/* 1256:1489 */     double storeLoreValues = 0.0D;
/* 1257:     */     
/* 1258:1491 */     ItemStack itemInHand = player.getItemInHand();
/* 1259:1493 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1260:     */     {
/* 1261:1496 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1262:1497 */       for (String line : itemInHandLore) {
/* 1263:1498 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/* 1264:     */         {
/* 1265:1499 */           String value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/* 1266:1500 */           storeLoreValues = Double.parseDouble(value);
/* 1267:1501 */           return storeLoreValues;
/* 1268:     */         }
/* 1269:     */       }
/* 1270:     */     }
/* 1271:1507 */     return storeLoreValues;
/* 1272:     */   }
/* 1273:     */   
/* 1274:     */   public double getDodgeItemInHand(Player player)
/* 1275:     */   {
/* 1276:1511 */     this.dodge = ItemLoreStats.plugin.getConfig().getString("statNames.dodge").replaceAll("&([0-9a-f])", "");
/* 1277:     */     
/* 1278:1513 */     double storeLoreValues = 0.0D;
/* 1279:     */     
/* 1280:1515 */     ItemStack itemInHand = player.getItemInHand();
/* 1281:1517 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1282:     */     {
/* 1283:1520 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1284:1521 */       for (String line : itemInHandLore) {
/* 1285:1522 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/* 1286:     */         {
/* 1287:1523 */           String value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/* 1288:1524 */           storeLoreValues = Double.parseDouble(value);
/* 1289:1525 */           return storeLoreValues;
/* 1290:     */         }
/* 1291:     */       }
/* 1292:     */     }
/* 1293:1531 */     return storeLoreValues;
/* 1294:     */   }
/* 1295:     */   
/* 1296:     */   public double getBlockItemInHand(Player player)
/* 1297:     */   {
/* 1298:1535 */     this.block = ItemLoreStats.plugin.getConfig().getString("statNames.block").replaceAll("&([0-9a-f])", "");
/* 1299:     */     
/* 1300:1537 */     double storeLoreValues = 0.0D;
/* 1301:     */     
/* 1302:1539 */     ItemStack itemInHand = player.getItemInHand();
/* 1303:1541 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1304:     */     {
/* 1305:1544 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1306:1545 */       for (String line : itemInHandLore) {
/* 1307:1546 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/* 1308:     */         {
/* 1309:1547 */           String value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/* 1310:1548 */           storeLoreValues = Double.parseDouble(value);
/* 1311:1549 */           return storeLoreValues;
/* 1312:     */         }
/* 1313:     */       }
/* 1314:     */     }
/* 1315:1555 */     return storeLoreValues;
/* 1316:     */   }
/* 1317:     */   
/* 1318:     */   public String getDamageItemInHand(Player player)
/* 1319:     */   {
/* 1320:1559 */     this.damage = ItemLoreStats.plugin.getConfig().getString("statNames.damage").replaceAll("&([0-9a-f])", "");
/* 1321:     */     
/* 1322:1561 */     double itemInHandMinValue = 0.0D;
/* 1323:1562 */     double itemInHandMaxValue = 0.0D;
/* 1324:     */     
/* 1325:1564 */     ItemStack itemInHand = player.getItemInHand();
/* 1326:1566 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1327:     */     {
/* 1328:1569 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1329:1570 */       for (String line : itemInHandLore) {
/* 1330:1571 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/* 1331:     */         {
/* 1332:1572 */           String value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/* 1333:1573 */           if (value.contains("-"))
/* 1334:     */           {
/* 1335:1574 */             itemInHandMinValue = Double.parseDouble(value.split("-")[0]);
/* 1336:1575 */             itemInHandMaxValue = Double.parseDouble(value.split("-")[1]);
/* 1337:     */           }
/* 1338:     */           else
/* 1339:     */           {
/* 1340:1577 */             itemInHandMinValue = Double.parseDouble(value);
/* 1341:1578 */             itemInHandMaxValue = Double.parseDouble(value);
/* 1342:     */           }
/* 1343:     */         }
/* 1344:     */       }
/* 1345:     */     }
/* 1346:1585 */     return itemInHandMinValue + "-" + itemInHandMaxValue;
/* 1347:     */   }
/* 1348:     */   
/* 1349:     */   public double getCritChanceItemInHand(Player player)
/* 1350:     */   {
/* 1351:1589 */     this.critChance = ItemLoreStats.plugin.getConfig().getString("statNames.critChance").replaceAll("&([0-9a-f])", "");
/* 1352:     */     
/* 1353:1591 */     double storeLoreValues = 0.0D;
/* 1354:     */     
/* 1355:1593 */     ItemStack itemInHand = player.getItemInHand();
/* 1356:1595 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1357:     */     {
/* 1358:1598 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1359:1599 */       for (String line : itemInHandLore) {
/* 1360:1600 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/* 1361:     */         {
/* 1362:1601 */           String value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/* 1363:1602 */           storeLoreValues = Double.parseDouble(value);
/* 1364:1603 */           return storeLoreValues;
/* 1365:     */         }
/* 1366:     */       }
/* 1367:     */     }
/* 1368:1609 */     return storeLoreValues;
/* 1369:     */   }
/* 1370:     */   
/* 1371:     */   public double getCritDamageItemInHand(Player player)
/* 1372:     */   {
/* 1373:1613 */     this.critDamage = ItemLoreStats.plugin.getConfig().getString("statNames.critDamage").replaceAll("&([0-9a-f])", "");
/* 1374:     */     
/* 1375:1615 */     double storeLoreValues = 0.0D;
/* 1376:     */     
/* 1377:1617 */     ItemStack itemInHand = player.getItemInHand();
/* 1378:1619 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1379:     */     {
/* 1380:1622 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1381:1623 */       for (String line : itemInHandLore) {
/* 1382:1624 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/* 1383:     */         {
/* 1384:1625 */           String value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/* 1385:1626 */           storeLoreValues = Double.parseDouble(value);
/* 1386:1627 */           return storeLoreValues;
/* 1387:     */         }
/* 1388:     */       }
/* 1389:     */     }
/* 1390:1633 */     return storeLoreValues;
/* 1391:     */   }
/* 1392:     */   
/* 1393:     */   public double getHealthItemInHand(Player player)
/* 1394:     */   {
/* 1395:1637 */     this.health = ItemLoreStats.plugin.getConfig().getString("statNames.health").replaceAll("&([0-9a-f])", "");
/* 1396:     */     
/* 1397:1639 */     double storeLoreValues = 0.0D;
/* 1398:     */     
/* 1399:1641 */     ItemStack itemInHand = player.getItemInHand();
/* 1400:1643 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1401:     */     {
/* 1402:1646 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1403:1647 */       for (String line : itemInHandLore) {
/* 1404:1648 */         if (ChatColor.stripColor(line).startsWith(this.health + ": +"))
/* 1405:     */         {
/* 1406:1649 */           String value = ChatColor.stripColor(line).substring((this.health + ": +").length()).trim();
/* 1407:1650 */           storeLoreValues = Double.parseDouble(value);
/* 1408:1651 */           return storeLoreValues;
/* 1409:     */         }
/* 1410:     */       }
/* 1411:     */     }
/* 1412:1657 */     return storeLoreValues;
/* 1413:     */   }
/* 1414:     */   
/* 1415:     */   public double getHealthRegenItemInHand(Player player)
/* 1416:     */   {
/* 1417:1661 */     this.healthRegen = ItemLoreStats.plugin.getConfig().getString("statNames.healthregen").replaceAll("&([0-9a-f])", "");
/* 1418:     */     
/* 1419:1663 */     double storeLoreValues = 0.0D;
/* 1420:     */     
/* 1421:1665 */     ItemStack itemInHand = player.getItemInHand();
/* 1422:1667 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1423:     */     {
/* 1424:1670 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1425:1671 */       for (String line : itemInHandLore) {
/* 1426:1672 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/* 1427:     */         {
/* 1428:1673 */           String value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/* 1429:1674 */           storeLoreValues = Double.parseDouble(value);
/* 1430:1675 */           return storeLoreValues;
/* 1431:     */         }
/* 1432:     */       }
/* 1433:     */     }
/* 1434:1681 */     return storeLoreValues;
/* 1435:     */   }
/* 1436:     */   
/* 1437:     */   public double getLifeStealItemInHand(Player player)
/* 1438:     */   {
/* 1439:1685 */     this.lifeSteal = ItemLoreStats.plugin.getConfig().getString("statNames.lifesteal").replaceAll("&([0-9a-f])", "");
/* 1440:     */     
/* 1441:1687 */     double storeLoreValues = 0.0D;
/* 1442:     */     
/* 1443:1689 */     ItemStack itemInHand = player.getItemInHand();
/* 1444:1691 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1445:     */     {
/* 1446:1694 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1447:1695 */       for (String line : itemInHandLore) {
/* 1448:1696 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/* 1449:     */         {
/* 1450:1697 */           String value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/* 1451:1698 */           storeLoreValues = Double.parseDouble(value);
/* 1452:1699 */           return storeLoreValues;
/* 1453:     */         }
/* 1454:     */       }
/* 1455:     */     }
/* 1456:1705 */     return storeLoreValues;
/* 1457:     */   }
/* 1458:     */   
/* 1459:     */   public double getLifeStealHealItemInHand(Player player)
/* 1460:     */   {
/* 1461:1709 */     this.lifeStealHeal = ItemLoreStats.plugin.getConfig().getString("statNames.lifestealheal").replaceAll("&([0-9a-f])", "");
/* 1462:     */     
/* 1463:1711 */     double storeLoreValues = 0.0D;
/* 1464:     */     
/* 1465:1713 */     ItemStack itemInHand = player.getItemInHand();
/* 1466:1715 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1467:     */     {
/* 1468:1718 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1469:1719 */       for (String line : itemInHandLore) {
/* 1470:1720 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/* 1471:     */         {
/* 1472:1721 */           String value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/* 1473:1722 */           storeLoreValues = Double.parseDouble(value);
/* 1474:1723 */           return storeLoreValues;
/* 1475:     */         }
/* 1476:     */       }
/* 1477:     */     }
/* 1478:1729 */     return storeLoreValues;
/* 1479:     */   }
/* 1480:     */   
/* 1481:     */   public double getReflectItemInHand(Player player)
/* 1482:     */   {
/* 1483:1733 */     this.reflect = ItemLoreStats.plugin.getConfig().getString("statNames.reflect").replaceAll("&([0-9a-f])", "");
/* 1484:     */     
/* 1485:1735 */     double storeLoreValues = 0.0D;
/* 1486:     */     
/* 1487:1737 */     ItemStack itemInHand = player.getItemInHand();
/* 1488:1739 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1489:     */     {
/* 1490:1742 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1491:1743 */       for (String line : itemInHandLore) {
/* 1492:1744 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/* 1493:     */         {
/* 1494:1745 */           String value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/* 1495:1746 */           storeLoreValues = Double.parseDouble(value);
/* 1496:1747 */           return storeLoreValues;
/* 1497:     */         }
/* 1498:     */       }
/* 1499:     */     }
/* 1500:1753 */     return storeLoreValues;
/* 1501:     */   }
/* 1502:     */   
/* 1503:     */   public double getIceItemInHand(Player player)
/* 1504:     */   {
/* 1505:1757 */     this.ice = ItemLoreStats.plugin.getConfig().getString("statNames.ice").replaceAll("&([0-9a-f])", "");
/* 1506:     */     
/* 1507:1759 */     double storeLoreValues = 0.0D;
/* 1508:     */     
/* 1509:1761 */     ItemStack itemInHand = player.getItemInHand();
/* 1510:1763 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1511:     */     {
/* 1512:1766 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1513:1767 */       for (String line : itemInHandLore) {
/* 1514:1768 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/* 1515:     */         {
/* 1516:1769 */           String value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/* 1517:1770 */           storeLoreValues = Double.parseDouble(value);
/* 1518:1771 */           return storeLoreValues;
/* 1519:     */         }
/* 1520:     */       }
/* 1521:     */     }
/* 1522:1777 */     return storeLoreValues;
/* 1523:     */   }
/* 1524:     */   
/* 1525:     */   public double getFireItemInHand(Player player)
/* 1526:     */   {
/* 1527:1781 */     this.fire = ItemLoreStats.plugin.getConfig().getString("statNames.fire").replaceAll("&([0-9a-f])", "");
/* 1528:     */     
/* 1529:1783 */     double storeLoreValues = 0.0D;
/* 1530:     */     
/* 1531:1785 */     ItemStack itemInHand = player.getItemInHand();
/* 1532:1787 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1533:     */     {
/* 1534:1790 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1535:1791 */       for (String line : itemInHandLore) {
/* 1536:1792 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/* 1537:     */         {
/* 1538:1793 */           String value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/* 1539:1794 */           storeLoreValues = Double.parseDouble(value);
/* 1540:1795 */           return storeLoreValues;
/* 1541:     */         }
/* 1542:     */       }
/* 1543:     */     }
/* 1544:1801 */     return storeLoreValues;
/* 1545:     */   }
/* 1546:     */   
/* 1547:     */   public double getPoisonItemInHand(Player player)
/* 1548:     */   {
/* 1549:1805 */     this.poison = ItemLoreStats.plugin.getConfig().getString("statNames.poison").replaceAll("&([0-9a-f])", "");
/* 1550:     */     
/* 1551:1807 */     double storeLoreValues = 0.0D;
/* 1552:     */     
/* 1553:1809 */     ItemStack itemInHand = player.getItemInHand();
/* 1554:1811 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1555:     */     {
/* 1556:1814 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1557:1815 */       for (String line : itemInHandLore) {
/* 1558:1816 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/* 1559:     */         {
/* 1560:1817 */           String value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/* 1561:1818 */           storeLoreValues = Double.parseDouble(value);
/* 1562:1819 */           return storeLoreValues;
/* 1563:     */         }
/* 1564:     */       }
/* 1565:     */     }
/* 1566:1825 */     return storeLoreValues;
/* 1567:     */   }
/* 1568:     */   
/* 1569:     */   public double getWitherItemInHand(Player player)
/* 1570:     */   {
/* 1571:1829 */     this.wither = ItemLoreStats.plugin.getConfig().getString("statNames.wither").replaceAll("&([0-9a-f])", "");
/* 1572:     */     
/* 1573:1831 */     double storeLoreValues = 0.0D;
/* 1574:     */     
/* 1575:1833 */     ItemStack itemInHand = player.getItemInHand();
/* 1576:1835 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1577:     */     {
/* 1578:1838 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1579:1839 */       for (String line : itemInHandLore) {
/* 1580:1840 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/* 1581:     */         {
/* 1582:1841 */           String value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/* 1583:1842 */           storeLoreValues = Double.parseDouble(value);
/* 1584:1843 */           return storeLoreValues;
/* 1585:     */         }
/* 1586:     */       }
/* 1587:     */     }
/* 1588:1849 */     return storeLoreValues;
/* 1589:     */   }
/* 1590:     */   
/* 1591:     */   public double getHarmingItemInHand(Player player)
/* 1592:     */   {
/* 1593:1853 */     this.harming = ItemLoreStats.plugin.getConfig().getString("statNames.harming").replaceAll("&([0-9a-f])", "");
/* 1594:     */     
/* 1595:1855 */     double storeLoreValues = 0.0D;
/* 1596:     */     
/* 1597:1857 */     ItemStack itemInHand = player.getItemInHand();
/* 1598:1859 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1599:     */     {
/* 1600:1862 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1601:1863 */       for (String line : itemInHandLore) {
/* 1602:1864 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1603:     */         {
/* 1604:1865 */           String value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1605:1866 */           storeLoreValues = Double.parseDouble(value);
/* 1606:1867 */           return storeLoreValues;
/* 1607:     */         }
/* 1608:     */       }
/* 1609:     */     }
/* 1610:1873 */     return storeLoreValues;
/* 1611:     */   }
/* 1612:     */   
/* 1613:     */   public double getBlindItemInHand(Player player)
/* 1614:     */   {
/* 1615:1877 */     this.blind = ItemLoreStats.plugin.getConfig().getString("statNames.blind").replaceAll("&([0-9a-f])", "");
/* 1616:     */     
/* 1617:1879 */     double storeLoreValues = 0.0D;
/* 1618:     */     
/* 1619:1881 */     ItemStack itemInHand = player.getItemInHand();
/* 1620:1883 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1621:     */     {
/* 1622:1886 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1623:1887 */       for (String line : itemInHandLore) {
/* 1624:1888 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1625:     */         {
/* 1626:1889 */           String value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1627:1890 */           storeLoreValues = Double.parseDouble(value);
/* 1628:1891 */           return storeLoreValues;
/* 1629:     */         }
/* 1630:     */       }
/* 1631:     */     }
/* 1632:1897 */     return storeLoreValues;
/* 1633:     */   }
/* 1634:     */   
/* 1635:     */   public double getMovementSpeedItemInHand(Player player)
/* 1636:     */   {
/* 1637:1901 */     this.movementspeed = ItemLoreStats.plugin.getConfig().getString("statNames.movementspeed").replaceAll("&([0-9a-f])", "");
/* 1638:     */     
/* 1639:1903 */     double storeLoreValues = 0.0D;
/* 1640:     */     
/* 1641:1905 */     ItemStack itemInHand = player.getItemInHand();
/* 1642:1907 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1643:     */     {
/* 1644:1910 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1645:1911 */       for (String line : itemInHandLore) {
/* 1646:1912 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1647:     */         {
/* 1648:1913 */           String value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1649:1914 */           storeLoreValues = Double.parseDouble(value);
/* 1650:1915 */           return storeLoreValues;
/* 1651:     */         }
/* 1652:     */       }
/* 1653:     */     }
/* 1654:1921 */     return storeLoreValues;
/* 1655:     */   }
/* 1656:     */   
/* 1657:     */   public double getXPMultiplierItemInHand(Player player)
/* 1658:     */   {
/* 1659:1925 */     this.xpmultiplier = ItemLoreStats.plugin.getConfig().getString("statNames.xpmultiplier").replaceAll("&([0-9a-f])", "");
/* 1660:     */     
/* 1661:1927 */     double storeLoreValues = 0.0D;
/* 1662:     */     
/* 1663:1929 */     ItemStack itemInHand = player.getItemInHand();
/* 1664:1931 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1665:     */     {
/* 1666:1934 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1667:1935 */       for (String line : itemInHandLore) {
/* 1668:1936 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1669:     */         {
/* 1670:1937 */           String value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1671:1938 */           storeLoreValues = Double.parseDouble(value);
/* 1672:1939 */           return storeLoreValues;
/* 1673:     */         }
/* 1674:     */       }
/* 1675:     */     }
/* 1676:1945 */     return storeLoreValues;
/* 1677:     */   }
/* 1678:     */   
/* 1679:     */   public String getPvPDamageModifierItemInHand(Player player)
/* 1680:     */   {
/* 1681:1949 */     this.pvpdamage = ItemLoreStats.plugin.getConfig().getString("statNames.pvpdamage").replaceAll("&([0-9a-f])", "");
/* 1682:     */     
/* 1683:1951 */     ItemStack itemInHand = player.getItemInHand();
/* 1684:1953 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1685:     */     {
/* 1686:1956 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1687:1957 */       for (String line : itemInHandLore) {
/* 1688:1958 */         if (ChatColor.stripColor(line).startsWith(this.pvpdamage + ": "))
/* 1689:     */         {
/* 1690:1959 */           String value = ChatColor.stripColor(line).substring((this.pvpdamage + ": ").length()).trim();
/* 1691:1960 */           return value;
/* 1692:     */         }
/* 1693:     */       }
/* 1694:     */     }
/* 1695:1966 */     return "0.0";
/* 1696:     */   }
/* 1697:     */   
/* 1698:     */   public String getPvEDamageModifierItemInHand(Player player)
/* 1699:     */   {
/* 1700:1970 */     this.pvedamage = ItemLoreStats.plugin.getConfig().getString("statNames.pvedamage").replaceAll("&([0-9a-f])", "");
/* 1701:     */     
/* 1702:1972 */     ItemStack itemInHand = player.getItemInHand();
/* 1703:1974 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1704:     */     {
/* 1705:1977 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1706:1978 */       for (String line : itemInHandLore) {
/* 1707:1979 */         if (ChatColor.stripColor(line).startsWith(this.pvedamage + ": "))
/* 1708:     */         {
/* 1709:1980 */           String value = ChatColor.stripColor(line).substring((this.pvedamage + ": ").length()).trim();
/* 1710:1981 */           return value;
/* 1711:     */         }
/* 1712:     */       }
/* 1713:     */     }
/* 1714:1987 */     return "0.0";
/* 1715:     */   }
/* 1716:     */   
/* 1717:     */   public int getXPLevelRequirementHead(Player player)
/* 1718:     */   {
/* 1719:1992 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 1720:     */     
/* 1721:1994 */     int storeLoreValues = 0;
/* 1722:     */     
/* 1723:1996 */     ItemStack item = player.getInventory().getHelmet();
/* 1724:1998 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 1725:     */     {
/* 1726:2001 */       List<String> itemLore = item.getItemMeta().getLore();
/* 1727:2002 */       for (String line : itemLore) {
/* 1728:2003 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 1729:     */         {
/* 1730:2004 */           String value = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 1731:2005 */           storeLoreValues = Integer.parseInt(value);
/* 1732:2006 */           return storeLoreValues;
/* 1733:     */         }
/* 1734:     */       }
/* 1735:     */     }
/* 1736:2012 */     return storeLoreValues;
/* 1737:     */   }
/* 1738:     */   
/* 1739:     */   public int getXPLevelRequirementChest(Player player)
/* 1740:     */   {
/* 1741:2016 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 1742:     */     
/* 1743:2018 */     int storeLoreValues = 0;
/* 1744:     */     
/* 1745:2020 */     ItemStack item = player.getInventory().getChestplate();
/* 1746:2022 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 1747:     */     {
/* 1748:2025 */       List<String> itemLore = item.getItemMeta().getLore();
/* 1749:2026 */       for (String line : itemLore) {
/* 1750:2027 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 1751:     */         {
/* 1752:2028 */           String value = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 1753:2029 */           storeLoreValues = Integer.parseInt(value);
/* 1754:2030 */           return storeLoreValues;
/* 1755:     */         }
/* 1756:     */       }
/* 1757:     */     }
/* 1758:2036 */     return storeLoreValues;
/* 1759:     */   }
/* 1760:     */   
/* 1761:     */   public int getXPLevelRequirementLegs(Player player)
/* 1762:     */   {
/* 1763:2040 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 1764:     */     
/* 1765:2042 */     int storeLoreValues = 0;
/* 1766:     */     
/* 1767:2044 */     ItemStack item = player.getInventory().getLeggings();
/* 1768:2046 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 1769:     */     {
/* 1770:2049 */       List<String> itemLore = item.getItemMeta().getLore();
/* 1771:2050 */       for (String line : itemLore) {
/* 1772:2051 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 1773:     */         {
/* 1774:2052 */           String value = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 1775:2053 */           storeLoreValues = Integer.parseInt(value);
/* 1776:2054 */           return storeLoreValues;
/* 1777:     */         }
/* 1778:     */       }
/* 1779:     */     }
/* 1780:2060 */     return storeLoreValues;
/* 1781:     */   }
/* 1782:     */   
/* 1783:     */   public int getXPLevelRequirementBoots(Player player)
/* 1784:     */   {
/* 1785:2064 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 1786:     */     
/* 1787:2066 */     int storeLoreValues = 0;
/* 1788:     */     
/* 1789:2068 */     ItemStack item = player.getInventory().getBoots();
/* 1790:2070 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 1791:     */     {
/* 1792:2073 */       List<String> itemLore = item.getItemMeta().getLore();
/* 1793:2074 */       for (String line : itemLore) {
/* 1794:2075 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 1795:     */         {
/* 1796:2076 */           String value = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 1797:2077 */           storeLoreValues = Integer.parseInt(value);
/* 1798:2078 */           return storeLoreValues;
/* 1799:     */         }
/* 1800:     */       }
/* 1801:     */     }
/* 1802:2084 */     return storeLoreValues;
/* 1803:     */   }
/* 1804:     */   
/* 1805:     */   public int getXPLevelRequirementItemInHand(Player player)
/* 1806:     */   {
/* 1807:2088 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 1808:     */     
/* 1809:2090 */     int storeLoreValues = 0;
/* 1810:     */     
/* 1811:2092 */     ItemStack itemInHand = player.getItemInHand();
/* 1812:2094 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 1813:     */     {
/* 1814:2097 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1815:2098 */       for (String line : itemInHandLore) {
/* 1816:2099 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 1817:     */         {
/* 1818:2100 */           String xpLevelValue = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 1819:     */           try
/* 1820:     */           {
/* 1821:2102 */             return Integer.parseInt(xpLevelValue);
/* 1822:     */           }
/* 1823:     */           catch (NumberFormatException localNumberFormatException) {}
/* 1824:     */         }
/* 1825:     */       }
/* 1826:     */     }
/* 1827:2112 */     return storeLoreValues;
/* 1828:     */   }
/* 1829:     */   
/* 1830:     */   public String getSetBonusHead(Player player)
/* 1831:     */   {
/* 1832:2117 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 1833:2118 */     String headKey = "";
/* 1834:     */     
/* 1835:2120 */     ItemStack head = player.getInventory().getHelmet();
/* 1836:2122 */     if ((head != null) && (head.hasItemMeta()) && (head.getItemMeta().hasLore()))
/* 1837:     */     {
/* 1838:2125 */       List<String> headLore = head.getItemMeta().getLore();
/* 1839:2126 */       for (String line : headLore) {
/* 1840:2127 */         if (line.contains(this.setbonus))
/* 1841:     */         {
/* 1842:2128 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 1843:2129 */           headKey = value;
/* 1844:2130 */           return headKey;
/* 1845:     */         }
/* 1846:     */       }
/* 1847:     */     }
/* 1848:2136 */     return headKey;
/* 1849:     */   }
/* 1850:     */   
/* 1851:     */   public String getSetBonusChest(Player player)
/* 1852:     */   {
/* 1853:2139 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 1854:2140 */     String chestKey = "";
/* 1855:     */     
/* 1856:2142 */     ItemStack chest = player.getInventory().getChestplate();
/* 1857:2144 */     if ((chest != null) && (chest.hasItemMeta()) && (chest.getItemMeta().hasLore()))
/* 1858:     */     {
/* 1859:2147 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1860:2148 */       for (String line : chestLore) {
/* 1861:2149 */         if (line.contains(this.setbonus))
/* 1862:     */         {
/* 1863:2150 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 1864:2151 */           chestKey = value;
/* 1865:2152 */           return chestKey;
/* 1866:     */         }
/* 1867:     */       }
/* 1868:     */     }
/* 1869:2158 */     return chestKey;
/* 1870:     */   }
/* 1871:     */   
/* 1872:     */   public String getSetBonusLegs(Player player)
/* 1873:     */   {
/* 1874:2161 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 1875:2162 */     String legsKey = "";
/* 1876:     */     
/* 1877:2164 */     ItemStack legs = player.getInventory().getLeggings();
/* 1878:2166 */     if ((legs != null) && (legs.hasItemMeta()) && (legs.getItemMeta().hasLore()))
/* 1879:     */     {
/* 1880:2169 */       List<String> legsLore = legs.getItemMeta().getLore();
/* 1881:2170 */       for (String line : legsLore) {
/* 1882:2171 */         if (line.contains(this.setbonus))
/* 1883:     */         {
/* 1884:2172 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 1885:2173 */           legsKey = value;
/* 1886:2174 */           return legsKey;
/* 1887:     */         }
/* 1888:     */       }
/* 1889:     */     }
/* 1890:2180 */     return legsKey;
/* 1891:     */   }
/* 1892:     */   
/* 1893:     */   public String getSetBonusBoots(Player player)
/* 1894:     */   {
/* 1895:2183 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 1896:2184 */     String bootsKey = "";
/* 1897:     */     
/* 1898:2186 */     ItemStack boots = player.getInventory().getBoots();
/* 1899:2188 */     if ((boots != null) && (boots.hasItemMeta()) && (boots.getItemMeta().hasLore()))
/* 1900:     */     {
/* 1901:2191 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1902:2192 */       for (String line : bootsLore) {
/* 1903:2193 */         if (line.contains(this.setbonus))
/* 1904:     */         {
/* 1905:2194 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 1906:2195 */           bootsKey = value;
/* 1907:2196 */           return bootsKey;
/* 1908:     */         }
/* 1909:     */       }
/* 1910:     */     }
/* 1911:2202 */     return bootsKey;
/* 1912:     */   }
/* 1913:     */   
/* 1914:     */   public String getSetBonusItemInHand(Player player)
/* 1915:     */   {
/* 1916:2205 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 1917:2206 */     String weaponKey = "";
/* 1918:     */     
/* 1919:2208 */     ItemStack itemInHand = player.getItemInHand();
/* 1920:2210 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (ItemLoreStats.plugin.isTool(itemInHand.getType())) && (itemInHand.getItemMeta().hasLore()))
/* 1921:     */     {
/* 1922:2214 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1923:2215 */       for (String line : itemInHandLore) {
/* 1924:2216 */         if (line.contains(this.setbonus))
/* 1925:     */         {
/* 1926:2217 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 1927:2218 */           weaponKey = value;
/* 1928:2219 */           return weaponKey;
/* 1929:     */         }
/* 1930:     */       }
/* 1931:     */     }
/* 1932:2226 */     return weaponKey;
/* 1933:     */   }
/* 1934:     */   
/* 1935:     */   public String getSoulboundNameHead(Player player)
/* 1936:     */   {
/* 1937:2231 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 1938:     */     
/* 1939:2233 */     String storeLoreValues = "";
/* 1940:     */     
/* 1941:2235 */     ItemStack item = player.getInventory().getHelmet();
/* 1942:2237 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 1943:     */     {
/* 1944:2240 */       List<String> itemLore = item.getItemMeta().getLore();
/* 1945:2241 */       for (String line : itemLore) {
/* 1946:2242 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 1947:     */         {
/* 1948:2243 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 1949:2244 */           storeLoreValues = value;
/* 1950:2245 */           return storeLoreValues;
/* 1951:     */         }
/* 1952:     */       }
/* 1953:     */     }
/* 1954:2251 */     return null;
/* 1955:     */   }
/* 1956:     */   
/* 1957:     */   public String getSoulboundNameChest(Player player)
/* 1958:     */   {
/* 1959:2255 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 1960:     */     
/* 1961:2257 */     String storeLoreValues = "";
/* 1962:     */     
/* 1963:2259 */     ItemStack item = player.getInventory().getChestplate();
/* 1964:2261 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 1965:     */     {
/* 1966:2264 */       List<String> itemLore = item.getItemMeta().getLore();
/* 1967:2265 */       for (String line : itemLore) {
/* 1968:2266 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 1969:     */         {
/* 1970:2267 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 1971:2268 */           storeLoreValues = value;
/* 1972:2269 */           return storeLoreValues;
/* 1973:     */         }
/* 1974:     */       }
/* 1975:     */     }
/* 1976:2275 */     return null;
/* 1977:     */   }
/* 1978:     */   
/* 1979:     */   public String getSoulboundNameLegs(Player player)
/* 1980:     */   {
/* 1981:2279 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 1982:     */     
/* 1983:2281 */     String storeLoreValues = "";
/* 1984:     */     
/* 1985:2283 */     ItemStack item = player.getInventory().getLeggings();
/* 1986:2285 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 1987:     */     {
/* 1988:2288 */       List<String> itemLore = item.getItemMeta().getLore();
/* 1989:2289 */       for (String line : itemLore) {
/* 1990:2290 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 1991:     */         {
/* 1992:2291 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 1993:2292 */           storeLoreValues = value;
/* 1994:2293 */           return storeLoreValues;
/* 1995:     */         }
/* 1996:     */       }
/* 1997:     */     }
/* 1998:2299 */     return null;
/* 1999:     */   }
/* 2000:     */   
/* 2001:     */   public String getSoulboundNameBoots(Player player)
/* 2002:     */   {
/* 2003:2303 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 2004:     */     
/* 2005:2305 */     String storeLoreValues = "";
/* 2006:     */     
/* 2007:2307 */     ItemStack item = player.getInventory().getBoots();
/* 2008:2309 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2009:     */     {
/* 2010:2312 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2011:2313 */       for (String line : itemLore) {
/* 2012:2314 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 2013:     */         {
/* 2014:2315 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 2015:2316 */           storeLoreValues = value;
/* 2016:2317 */           return storeLoreValues;
/* 2017:     */         }
/* 2018:     */       }
/* 2019:     */     }
/* 2020:2323 */     return null;
/* 2021:     */   }
/* 2022:     */   
/* 2023:     */   public String getSoulboundNameItemInHand(Player player)
/* 2024:     */   {
/* 2025:2327 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 2026:     */     
/* 2027:2329 */     String storeLoreValues = "";
/* 2028:     */     
/* 2029:2331 */     ItemStack item = player.getItemInHand();
/* 2030:2333 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2031:     */     {
/* 2032:2336 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2033:2337 */       for (String line : itemLore) {
/* 2034:2338 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 2035:     */         {
/* 2036:2339 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 2037:2340 */           storeLoreValues = value;
/* 2038:2341 */           return storeLoreValues;
/* 2039:     */         }
/* 2040:     */       }
/* 2041:     */     }
/* 2042:2347 */     return null;
/* 2043:     */   }
/* 2044:     */   
/* 2045:     */   public String getClassHead(Player player)
/* 2046:     */   {
/* 2047:2352 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2048:     */     
/* 2049:2354 */     String storeLoreValues = "";
/* 2050:     */     
/* 2051:2356 */     ItemStack item = player.getInventory().getHelmet();
/* 2052:2358 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2053:     */     {
/* 2054:2361 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2055:2362 */       for (String line : itemLore) {
/* 2056:2363 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2057:     */         {
/* 2058:2364 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2059:2365 */           storeLoreValues = value;
/* 2060:2366 */           return storeLoreValues;
/* 2061:     */         }
/* 2062:     */       }
/* 2063:     */     }
/* 2064:2372 */     return storeLoreValues;
/* 2065:     */   }
/* 2066:     */   
/* 2067:     */   public String getClassChest(Player player)
/* 2068:     */   {
/* 2069:2376 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2070:     */     
/* 2071:2378 */     String storeLoreValues = "";
/* 2072:     */     
/* 2073:2380 */     ItemStack item = player.getInventory().getChestplate();
/* 2074:2382 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2075:     */     {
/* 2076:2385 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2077:2386 */       for (String line : itemLore) {
/* 2078:2387 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2079:     */         {
/* 2080:2388 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2081:2389 */           storeLoreValues = value;
/* 2082:2390 */           return storeLoreValues;
/* 2083:     */         }
/* 2084:     */       }
/* 2085:     */     }
/* 2086:2396 */     return storeLoreValues;
/* 2087:     */   }
/* 2088:     */   
/* 2089:     */   public String getClassLegs(Player player)
/* 2090:     */   {
/* 2091:2400 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2092:     */     
/* 2093:2402 */     String storeLoreValues = "";
/* 2094:     */     
/* 2095:2404 */     ItemStack item = player.getInventory().getLeggings();
/* 2096:2406 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2097:     */     {
/* 2098:2409 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2099:2410 */       for (String line : itemLore) {
/* 2100:2411 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2101:     */         {
/* 2102:2412 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2103:2413 */           storeLoreValues = value;
/* 2104:2414 */           return storeLoreValues;
/* 2105:     */         }
/* 2106:     */       }
/* 2107:     */     }
/* 2108:2420 */     return storeLoreValues;
/* 2109:     */   }
/* 2110:     */   
/* 2111:     */   public String getClassBoots(Player player)
/* 2112:     */   {
/* 2113:2424 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2114:     */     
/* 2115:2426 */     String storeLoreValues = "";
/* 2116:     */     
/* 2117:2428 */     ItemStack item = player.getInventory().getBoots();
/* 2118:2430 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2119:     */     {
/* 2120:2433 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2121:2434 */       for (String line : itemLore) {
/* 2122:2435 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2123:     */         {
/* 2124:2436 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2125:2437 */           storeLoreValues = value;
/* 2126:2438 */           return storeLoreValues;
/* 2127:     */         }
/* 2128:     */       }
/* 2129:     */     }
/* 2130:2444 */     return storeLoreValues;
/* 2131:     */   }
/* 2132:     */   
/* 2133:     */   public String getClassItemInHand(Player player)
/* 2134:     */   {
/* 2135:2448 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2136:     */     
/* 2137:2450 */     String storeLoreValues = "";
/* 2138:     */     
/* 2139:2452 */     ItemStack item = player.getItemInHand();
/* 2140:2454 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2141:     */     {
/* 2142:2457 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2143:2458 */       for (String line : itemLore) {
/* 2144:2459 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2145:     */         {
/* 2146:2460 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2147:2461 */           storeLoreValues = value;
/* 2148:2462 */           return storeLoreValues;
/* 2149:     */         }
/* 2150:     */       }
/* 2151:     */     }
/* 2152:2468 */     return storeLoreValues;
/* 2153:     */   }
/* 2154:     */   
/* 2155:     */   public String playerHeldItemChangeSetBonusItemInHand(ItemStack itemstack)
/* 2156:     */   {
/* 2157:2473 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 2158:2474 */     String weaponKey = "";
/* 2159:     */     
/* 2160:2476 */     ItemStack itemInHand = itemstack;
/* 2161:2478 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (ItemLoreStats.plugin.isTool(itemInHand.getType())) && (itemInHand.getItemMeta().hasLore()))
/* 2162:     */     {
/* 2163:2482 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 2164:2483 */       for (String line : itemInHandLore) {
/* 2165:2484 */         if (line.contains(this.setbonus))
/* 2166:     */         {
/* 2167:2485 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 2168:2486 */           weaponKey = value;
/* 2169:2487 */           return weaponKey;
/* 2170:     */         }
/* 2171:     */       }
/* 2172:     */     }
/* 2173:2494 */     return weaponKey;
/* 2174:     */   }
/* 2175:     */   
/* 2176:     */   public String playerHeldItemChangeSoulboundNameItemInHand(ItemStack itemstack)
/* 2177:     */   {
/* 2178:2497 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 2179:     */     
/* 2180:2499 */     String storeLoreValues = "";
/* 2181:     */     
/* 2182:2501 */     ItemStack item = itemstack;
/* 2183:2503 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2184:     */     {
/* 2185:2506 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2186:2507 */       for (String line : itemLore) {
/* 2187:2508 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 2188:     */         {
/* 2189:2509 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 2190:2510 */           storeLoreValues = value;
/* 2191:2511 */           return storeLoreValues;
/* 2192:     */         }
/* 2193:     */       }
/* 2194:     */     }
/* 2195:2517 */     return null;
/* 2196:     */   }
/* 2197:     */   
/* 2198:     */   public String playerHeldItemChangeClassItemInHand(ItemStack itemstack)
/* 2199:     */   {
/* 2200:2520 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2201:     */     
/* 2202:2522 */     String storeLoreValues = "";
/* 2203:     */     
/* 2204:2524 */     ItemStack item = itemstack;
/* 2205:2526 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2206:     */     {
/* 2207:2529 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2208:2530 */       for (String line : itemLore) {
/* 2209:2531 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2210:     */         {
/* 2211:2532 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2212:2533 */           storeLoreValues = value;
/* 2213:2534 */           return storeLoreValues;
/* 2214:     */         }
/* 2215:     */       }
/* 2216:     */     }
/* 2217:2540 */     return null;
/* 2218:     */   }
/* 2219:     */   
/* 2220:     */   public int playerHeldItemChangeXPLevelRequirementItemInHand(ItemStack itemstack)
/* 2221:     */   {
/* 2222:2544 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 2223:     */     
/* 2224:2546 */     int storeLoreValues = 0;
/* 2225:     */     
/* 2226:2548 */     ItemStack itemInHand = itemstack;
/* 2227:2550 */     if ((itemInHand != null) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasLore()))
/* 2228:     */     {
/* 2229:2553 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 2230:2554 */       for (String line : itemInHandLore) {
/* 2231:2555 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 2232:     */         {
/* 2233:2556 */           String xpLevelValue = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 2234:     */           try
/* 2235:     */           {
/* 2236:2558 */             return Integer.parseInt(xpLevelValue);
/* 2237:     */           }
/* 2238:     */           catch (NumberFormatException localNumberFormatException) {}
/* 2239:     */         }
/* 2240:     */       }
/* 2241:     */     }
/* 2242:2568 */     return 0;
/* 2243:     */   }
/* 2244:     */   
/* 2245:     */   public boolean getTnT(Player player)
/* 2246:     */   {
/* 2247:2573 */     this.tnt = ItemLoreStats.plugin.getConfig().getString("statNames.spells.tnt.tnt").replaceAll("&([0-9a-f])", "");
/* 2248:     */     
/* 2249:2575 */     ItemStack item = player.getItemInHand();
/* 2250:2577 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2251:     */     {
/* 2252:2580 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2253:2581 */       for (String line : itemLore) {
/* 2254:2582 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.tnt)) {
/* 2255:2583 */           return true;
/* 2256:     */         }
/* 2257:     */       }
/* 2258:     */     }
/* 2259:2589 */     return false;
/* 2260:     */   }
/* 2261:     */   
/* 2262:     */   public boolean getFireball(Player player)
/* 2263:     */   {
/* 2264:2593 */     this.fireball = ItemLoreStats.plugin.getConfig().getString("statNames.spells.fireball.fireball").replaceAll("&([0-9a-f])", "");
/* 2265:     */     
/* 2266:2595 */     ItemStack item = player.getItemInHand();
/* 2267:2597 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2268:     */     {
/* 2269:2600 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2270:2601 */       for (String line : itemLore) {
/* 2271:2602 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.fireball)) {
/* 2272:2603 */           return true;
/* 2273:     */         }
/* 2274:     */       }
/* 2275:     */     }
/* 2276:2609 */     return false;
/* 2277:     */   }
/* 2278:     */   
/* 2279:     */   public boolean getLightning(Player player)
/* 2280:     */   {
/* 2281:2613 */     this.lightning = ItemLoreStats.plugin.getConfig().getString("statNames.spells.lightning.lightning").replaceAll("&([0-9a-f])", "");
/* 2282:     */     
/* 2283:2615 */     ItemStack item = player.getItemInHand();
/* 2284:2617 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2285:     */     {
/* 2286:2620 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2287:2621 */       for (String line : itemLore) {
/* 2288:2622 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.lightning)) {
/* 2289:2623 */           return true;
/* 2290:     */         }
/* 2291:     */       }
/* 2292:     */     }
/* 2293:2629 */     return false;
/* 2294:     */   }
/* 2295:     */   
/* 2296:     */   public boolean getFrostbolt(Player player)
/* 2297:     */   {
/* 2298:2633 */     this.frostbolt = ItemLoreStats.plugin.getConfig().getString("statNames.spells.frostbolt.frostbolt").replaceAll("&([0-9a-f])", "");
/* 2299:     */     
/* 2300:2635 */     ItemStack item = player.getItemInHand();
/* 2301:2637 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2302:     */     {
/* 2303:2640 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2304:2641 */       for (String line : itemLore) {
/* 2305:2642 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.frostbolt)) {
/* 2306:2643 */           return true;
/* 2307:     */         }
/* 2308:     */       }
/* 2309:     */     }
/* 2310:2649 */     return false;
/* 2311:     */   }
/* 2312:     */   
/* 2313:     */   public boolean getHeal(Player player)
/* 2314:     */   {
/* 2315:2653 */     this.heal = ItemLoreStats.plugin.getConfig().getString("statNames.spells.heal.heal").replaceAll("&([0-9a-f])", "");
/* 2316:     */     
/* 2317:2655 */     ItemStack item = player.getItemInHand();
/* 2318:2657 */     if ((item != null) && (item.hasItemMeta()) && (item.getItemMeta().hasLore()))
/* 2319:     */     {
/* 2320:2660 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2321:2661 */       for (String line : itemLore) {
/* 2322:2662 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.heal)) {
/* 2323:2663 */           return true;
/* 2324:     */         }
/* 2325:     */       }
/* 2326:     */     }
/* 2327:2669 */     return false;
/* 2328:     */   }
/* 2329:     */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.GearStats
 * JD-Core Version:    0.7.0.1
 */