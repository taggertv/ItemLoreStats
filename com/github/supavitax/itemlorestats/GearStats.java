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
/*   19:  16 */   Util_Colours util_Colours = new Util_Colours();
/*   20:     */   private FileConfiguration PlayerDataConfig;
/*   21:  20 */   String armour = null;
/*   22:  21 */   String dodge = null;
/*   23:  22 */   String block = null;
/*   24:  23 */   String critChance = null;
/*   25:  24 */   String critDamage = null;
/*   26:  25 */   String damage = null;
/*   27:  26 */   String health = null;
/*   28:  27 */   String healthRegen = null;
/*   29:  28 */   String lifeSteal = null;
/*   30:  29 */   String lifeStealHeal = null;
/*   31:  30 */   String reflect = null;
/*   32:  31 */   String ice = null;
/*   33:  32 */   String fire = null;
/*   34:  33 */   String poison = null;
/*   35:  34 */   String wither = null;
/*   36:  35 */   String harming = null;
/*   37:  36 */   String blind = null;
/*   38:  37 */   String movementspeed = null;
/*   39:  38 */   String xpmultiplier = null;
/*   40:  39 */   String pvpdamage = null;
/*   41:  40 */   String pvedamage = null;
/*   42:  41 */   String setbonus = null;
/*   43:  42 */   String xplevel = null;
/*   44:  43 */   String className = null;
/*   45:  44 */   String soulbound = null;
/*   46:  45 */   String durability = null;
/*   47:  46 */   String clickToCast = null;
/*   48:  47 */   String tnt = null;
/*   49:  48 */   String fireball = null;
/*   50:  49 */   String lightning = null;
/*   51:  50 */   String frostbolt = null;
/*   52:  51 */   String heal = null;
/*   53:     */   
/*   54:     */   public String getResponse(String getKeyFromLanguageFile)
/*   55:     */   {
/*   56:     */     try
/*   57:     */     {
/*   58:  56 */       this.PlayerDataConfig = new YamlConfiguration();
/*   59:  57 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*   60:     */       
/*   61:  59 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*   62:     */     }
/*   63:     */     catch (Exception e)
/*   64:     */     {
/*   65:  62 */       e.printStackTrace();
/*   66:  63 */       System.out.print("*********** Failed to load message from language file! ***********");
/*   67:     */     }
/*   68:  65 */     return "*********** Failed to load message from language file! ***********";
/*   69:     */   }
/*   70:     */   
/*   71:     */   public double getArmourGear(Player player)
/*   72:     */   {
/*   73:  73 */     this.armour = ItemLoreStats.plugin.getConfig().getString("statNames.armour").replaceAll("&([0-9a-f])", "");
/*   74:     */     
/*   75:  75 */     double headValue = 0.0D;
/*   76:  76 */     double chestValue = 0.0D;
/*   77:  77 */     double legsValue = 0.0D;
/*   78:  78 */     double bootsValue = 0.0D;
/*   79:     */     
/*   80:  80 */     ItemStack head = player.getInventory().getHelmet();
/*   81:     */     String value;
/*   82:  82 */     if ((head != null) && 
/*   83:  83 */       (head.hasItemMeta()) && 
/*   84:  84 */       (head.getItemMeta().hasLore()))
/*   85:     */     {
/*   86:  85 */       List<String> headLore = head.getItemMeta().getLore();
/*   87:  86 */       for (String line : headLore) {
/*   88:  87 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/*   89:     */         {
/*   90:  88 */           value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/*   91:  89 */           headValue = Double.parseDouble(value);
/*   92:     */         }
/*   93:     */       }
/*   94:     */     }
/*   95:  96 */     ItemStack chest = player.getInventory().getChestplate();
/*   96:     */     String value;
/*   97:  98 */     if ((chest != null) && 
/*   98:  99 */       (chest.hasItemMeta()) && 
/*   99: 100 */       (chest.getItemMeta().hasLore()))
/*  100:     */     {
/*  101: 101 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  102: 102 */       for (String line : chestLore) {
/*  103: 103 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/*  104:     */         {
/*  105: 104 */           value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/*  106: 105 */           chestValue = Double.parseDouble(value);
/*  107:     */         }
/*  108:     */       }
/*  109:     */     }
/*  110: 112 */     ItemStack legs = player.getInventory().getLeggings();
/*  111:     */     String value;
/*  112: 114 */     if ((legs != null) && 
/*  113: 115 */       (legs.hasItemMeta()) && 
/*  114: 116 */       (legs.getItemMeta().hasLore()))
/*  115:     */     {
/*  116: 117 */       Object legsLore = legs.getItemMeta().getLore();
/*  117: 118 */       for (String line : (List)legsLore) {
/*  118: 119 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/*  119:     */         {
/*  120: 120 */           value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/*  121: 121 */           legsValue = Double.parseDouble(value);
/*  122:     */         }
/*  123:     */       }
/*  124:     */     }
/*  125: 128 */     ItemStack boots = player.getInventory().getBoots();
/*  126: 130 */     if ((boots != null) && 
/*  127: 131 */       (boots.hasItemMeta()) && 
/*  128: 132 */       (boots.getItemMeta().hasLore()))
/*  129:     */     {
/*  130: 133 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  131: 134 */       for (String line : bootsLore) {
/*  132: 135 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/*  133:     */         {
/*  134: 136 */           String value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/*  135:     */           
/*  136:     */ 
/*  137: 139 */           bootsValue = Double.parseDouble(value);
/*  138:     */         }
/*  139:     */       }
/*  140:     */     }
/*  141: 145 */     return headValue + chestValue + legsValue + bootsValue;
/*  142:     */   }
/*  143:     */   
/*  144:     */   public double getDodgeGear(Player player)
/*  145:     */   {
/*  146: 149 */     this.dodge = ItemLoreStats.plugin.getConfig().getString("statNames.dodge").replaceAll("&([0-9a-f])", "");
/*  147:     */     
/*  148: 151 */     double headValue = 0.0D;
/*  149: 152 */     double chestValue = 0.0D;
/*  150: 153 */     double legsValue = 0.0D;
/*  151: 154 */     double bootsValue = 0.0D;
/*  152:     */     
/*  153: 156 */     ItemStack head = player.getInventory().getHelmet();
/*  154:     */     String value;
/*  155: 158 */     if ((head != null) && 
/*  156: 159 */       (head.hasItemMeta()) && 
/*  157: 160 */       (head.getItemMeta().hasLore()))
/*  158:     */     {
/*  159: 161 */       List<String> headLore = head.getItemMeta().getLore();
/*  160: 162 */       for (String line : headLore) {
/*  161: 163 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/*  162:     */         {
/*  163: 164 */           value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/*  164: 165 */           headValue = Double.parseDouble(value);
/*  165:     */         }
/*  166:     */       }
/*  167:     */     }
/*  168: 172 */     ItemStack chest = player.getInventory().getChestplate();
/*  169:     */     String value;
/*  170: 174 */     if ((chest != null) && 
/*  171: 175 */       (chest.hasItemMeta()) && 
/*  172: 176 */       (chest.getItemMeta().hasLore()))
/*  173:     */     {
/*  174: 177 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  175: 178 */       for (String line : chestLore) {
/*  176: 179 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/*  177:     */         {
/*  178: 180 */           value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/*  179: 181 */           chestValue = Double.parseDouble(value);
/*  180:     */         }
/*  181:     */       }
/*  182:     */     }
/*  183: 188 */     ItemStack legs = player.getInventory().getLeggings();
/*  184:     */     String value;
/*  185: 190 */     if ((legs != null) && 
/*  186: 191 */       (legs.hasItemMeta()) && 
/*  187: 192 */       (legs.getItemMeta().hasLore()))
/*  188:     */     {
/*  189: 193 */       Object legsLore = legs.getItemMeta().getLore();
/*  190: 194 */       for (String line : (List)legsLore) {
/*  191: 195 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/*  192:     */         {
/*  193: 196 */           value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/*  194: 197 */           legsValue = Double.parseDouble(value);
/*  195:     */         }
/*  196:     */       }
/*  197:     */     }
/*  198: 204 */     ItemStack boots = player.getInventory().getBoots();
/*  199: 206 */     if ((boots != null) && 
/*  200: 207 */       (boots.hasItemMeta()) && 
/*  201: 208 */       (boots.getItemMeta().hasLore()))
/*  202:     */     {
/*  203: 209 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  204: 210 */       for (String line : bootsLore) {
/*  205: 211 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/*  206:     */         {
/*  207: 212 */           String value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/*  208: 213 */           bootsValue = Double.parseDouble(value);
/*  209:     */         }
/*  210:     */       }
/*  211:     */     }
/*  212: 220 */     return headValue + chestValue + legsValue + bootsValue;
/*  213:     */   }
/*  214:     */   
/*  215:     */   public double getBlockGear(Player player)
/*  216:     */   {
/*  217: 224 */     this.block = ItemLoreStats.plugin.getConfig().getString("statNames.block").replaceAll("&([0-9a-f])", "");
/*  218:     */     
/*  219: 226 */     double headValue = 0.0D;
/*  220: 227 */     double chestValue = 0.0D;
/*  221: 228 */     double legsValue = 0.0D;
/*  222: 229 */     double bootsValue = 0.0D;
/*  223:     */     
/*  224: 231 */     ItemStack head = player.getInventory().getHelmet();
/*  225:     */     String value;
/*  226: 233 */     if ((head != null) && 
/*  227: 234 */       (head.hasItemMeta()) && 
/*  228: 235 */       (head.getItemMeta().hasLore()))
/*  229:     */     {
/*  230: 236 */       List<String> headLore = head.getItemMeta().getLore();
/*  231: 237 */       for (String line : headLore) {
/*  232: 238 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/*  233:     */         {
/*  234: 239 */           value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/*  235: 240 */           headValue = Double.parseDouble(value);
/*  236:     */         }
/*  237:     */       }
/*  238:     */     }
/*  239: 247 */     ItemStack chest = player.getInventory().getChestplate();
/*  240:     */     String value;
/*  241: 249 */     if ((chest != null) && 
/*  242: 250 */       (chest.hasItemMeta()) && 
/*  243: 251 */       (chest.getItemMeta().hasLore()))
/*  244:     */     {
/*  245: 252 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  246: 253 */       for (String line : chestLore) {
/*  247: 254 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/*  248:     */         {
/*  249: 255 */           value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/*  250: 256 */           chestValue = Double.parseDouble(value);
/*  251:     */         }
/*  252:     */       }
/*  253:     */     }
/*  254: 263 */     ItemStack legs = player.getInventory().getLeggings();
/*  255:     */     String value;
/*  256: 265 */     if ((legs != null) && 
/*  257: 266 */       (legs.hasItemMeta()) && 
/*  258: 267 */       (legs.getItemMeta().hasLore()))
/*  259:     */     {
/*  260: 268 */       Object legsLore = legs.getItemMeta().getLore();
/*  261: 269 */       for (String line : (List)legsLore) {
/*  262: 270 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/*  263:     */         {
/*  264: 271 */           value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/*  265: 272 */           legsValue = Double.parseDouble(value);
/*  266:     */         }
/*  267:     */       }
/*  268:     */     }
/*  269: 279 */     ItemStack boots = player.getInventory().getBoots();
/*  270: 281 */     if ((boots != null) && 
/*  271: 282 */       (boots.hasItemMeta()) && 
/*  272: 283 */       (boots.getItemMeta().hasLore()))
/*  273:     */     {
/*  274: 284 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  275: 285 */       for (String line : bootsLore) {
/*  276: 286 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/*  277:     */         {
/*  278: 287 */           String value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/*  279: 288 */           bootsValue = Double.parseDouble(value);
/*  280:     */         }
/*  281:     */       }
/*  282:     */     }
/*  283: 295 */     return headValue + chestValue + legsValue + bootsValue;
/*  284:     */   }
/*  285:     */   
/*  286:     */   public String getDamageGear(Player player)
/*  287:     */   {
/*  288: 299 */     this.damage = ItemLoreStats.plugin.getConfig().getString("statNames.damage").replaceAll("&([0-9a-f])", "");
/*  289:     */     
/*  290: 301 */     double headMinValue = 0.0D;
/*  291: 302 */     double headMaxValue = 0.0D;
/*  292: 303 */     double chestMinValue = 0.0D;
/*  293: 304 */     double chestMaxValue = 0.0D;
/*  294: 305 */     double legsMinValue = 0.0D;
/*  295: 306 */     double legsMaxValue = 0.0D;
/*  296: 307 */     double bootsMinValue = 0.0D;
/*  297: 308 */     double bootsMaxValue = 0.0D;
/*  298:     */     
/*  299: 310 */     ItemStack head = player.getInventory().getHelmet();
/*  300:     */     String value;
/*  301: 312 */     if ((head != null) && 
/*  302: 313 */       (head.hasItemMeta()) && 
/*  303: 314 */       (head.getItemMeta().hasLore()))
/*  304:     */     {
/*  305: 315 */       List<String> headLore = head.getItemMeta().getLore();
/*  306: 316 */       for (String line : headLore) {
/*  307: 317 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/*  308:     */         {
/*  309: 318 */           value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/*  310: 319 */           if (value.contains("-"))
/*  311:     */           {
/*  312: 320 */             headMinValue = Double.parseDouble(value.split("-")[0]);
/*  313: 321 */             headMaxValue = Double.parseDouble(value.split("-")[1]);
/*  314:     */           }
/*  315:     */           else
/*  316:     */           {
/*  317: 323 */             headMinValue = Double.parseDouble(value);
/*  318: 324 */             headMaxValue = Double.parseDouble(value);
/*  319:     */           }
/*  320:     */         }
/*  321:     */       }
/*  322:     */     }
/*  323: 332 */     ItemStack chest = player.getInventory().getChestplate();
/*  324:     */     String value;
/*  325: 334 */     if ((chest != null) && 
/*  326: 335 */       (chest.hasItemMeta()) && 
/*  327: 336 */       (chest.getItemMeta().hasLore()))
/*  328:     */     {
/*  329: 337 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  330: 338 */       for (String line : chestLore) {
/*  331: 339 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/*  332:     */         {
/*  333: 340 */           value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/*  334: 341 */           if (value.contains("-"))
/*  335:     */           {
/*  336: 342 */             chestMinValue = Double.parseDouble(value.split("-")[0]);
/*  337: 343 */             chestMaxValue = Double.parseDouble(value.split("-")[1]);
/*  338:     */           }
/*  339:     */           else
/*  340:     */           {
/*  341: 345 */             chestMinValue = Double.parseDouble(value);
/*  342: 346 */             chestMaxValue = Double.parseDouble(value);
/*  343:     */           }
/*  344:     */         }
/*  345:     */       }
/*  346:     */     }
/*  347: 354 */     ItemStack legs = player.getInventory().getLeggings();
/*  348:     */     String value;
/*  349: 356 */     if ((legs != null) && 
/*  350: 357 */       (legs.hasItemMeta()) && 
/*  351: 358 */       (legs.getItemMeta().hasLore()))
/*  352:     */     {
/*  353: 359 */       Object legsLore = legs.getItemMeta().getLore();
/*  354: 360 */       for (String line : (List)legsLore) {
/*  355: 361 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/*  356:     */         {
/*  357: 362 */           value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/*  358: 363 */           if (value.contains("-"))
/*  359:     */           {
/*  360: 364 */             legsMinValue = Double.parseDouble(value.split("-")[0]);
/*  361: 365 */             legsMaxValue = Double.parseDouble(value.split("-")[1]);
/*  362:     */           }
/*  363:     */           else
/*  364:     */           {
/*  365: 367 */             legsMinValue = Double.parseDouble(value);
/*  366: 368 */             legsMaxValue = Double.parseDouble(value);
/*  367:     */           }
/*  368:     */         }
/*  369:     */       }
/*  370:     */     }
/*  371: 376 */     ItemStack boots = player.getInventory().getBoots();
/*  372: 378 */     if ((boots != null) && 
/*  373: 379 */       (boots.hasItemMeta()) && 
/*  374: 380 */       (boots.getItemMeta().hasLore()))
/*  375:     */     {
/*  376: 381 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  377: 382 */       for (String line : bootsLore) {
/*  378: 383 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/*  379:     */         {
/*  380: 384 */           String value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/*  381: 385 */           if (value.contains("-"))
/*  382:     */           {
/*  383: 386 */             bootsMinValue = Double.parseDouble(value.split("-")[0]);
/*  384: 387 */             bootsMaxValue = Double.parseDouble(value.split("-")[1]);
/*  385:     */           }
/*  386:     */           else
/*  387:     */           {
/*  388: 389 */             bootsMinValue = Double.parseDouble(value);
/*  389: 390 */             bootsMaxValue = Double.parseDouble(value);
/*  390:     */           }
/*  391:     */         }
/*  392:     */       }
/*  393:     */     }
/*  394: 398 */     return headMinValue + chestMinValue + legsMinValue + bootsMinValue + "-" + (headMaxValue + chestMaxValue + legsMaxValue + bootsMaxValue);
/*  395:     */   }
/*  396:     */   
/*  397:     */   public double getCritChanceGear(Player player)
/*  398:     */   {
/*  399: 402 */     this.critChance = ItemLoreStats.plugin.getConfig().getString("statNames.critChance").replaceAll("&([0-9a-f])", "");
/*  400:     */     
/*  401: 404 */     double headValue = 0.0D;
/*  402: 405 */     double chestValue = 0.0D;
/*  403: 406 */     double legsValue = 0.0D;
/*  404: 407 */     double bootsValue = 0.0D;
/*  405:     */     
/*  406: 409 */     ItemStack head = player.getInventory().getHelmet();
/*  407:     */     String value;
/*  408: 411 */     if ((head != null) && 
/*  409: 412 */       (head.hasItemMeta()) && 
/*  410: 413 */       (head.getItemMeta().hasLore()))
/*  411:     */     {
/*  412: 414 */       List<String> headLore = head.getItemMeta().getLore();
/*  413: 415 */       for (String line : headLore) {
/*  414: 416 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/*  415:     */         {
/*  416: 417 */           value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/*  417: 418 */           headValue = Double.parseDouble(value);
/*  418:     */         }
/*  419:     */       }
/*  420:     */     }
/*  421: 425 */     ItemStack chest = player.getInventory().getChestplate();
/*  422:     */     String value;
/*  423: 427 */     if ((chest != null) && 
/*  424: 428 */       (chest.hasItemMeta()) && 
/*  425: 429 */       (chest.getItemMeta().hasLore()))
/*  426:     */     {
/*  427: 430 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  428: 431 */       for (String line : chestLore) {
/*  429: 432 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/*  430:     */         {
/*  431: 433 */           value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/*  432: 434 */           chestValue = Double.parseDouble(value);
/*  433:     */         }
/*  434:     */       }
/*  435:     */     }
/*  436: 441 */     ItemStack legs = player.getInventory().getLeggings();
/*  437:     */     String value;
/*  438: 443 */     if ((legs != null) && 
/*  439: 444 */       (legs.hasItemMeta()) && 
/*  440: 445 */       (legs.getItemMeta().hasLore()))
/*  441:     */     {
/*  442: 446 */       Object legsLore = legs.getItemMeta().getLore();
/*  443: 447 */       for (String line : (List)legsLore) {
/*  444: 448 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/*  445:     */         {
/*  446: 449 */           value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/*  447: 450 */           legsValue = Double.parseDouble(value);
/*  448:     */         }
/*  449:     */       }
/*  450:     */     }
/*  451: 457 */     ItemStack boots = player.getInventory().getBoots();
/*  452: 459 */     if ((boots != null) && 
/*  453: 460 */       (boots.hasItemMeta()) && 
/*  454: 461 */       (boots.getItemMeta().hasLore()))
/*  455:     */     {
/*  456: 462 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  457: 463 */       for (String line : bootsLore) {
/*  458: 464 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/*  459:     */         {
/*  460: 465 */           String value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/*  461: 466 */           bootsValue = Double.parseDouble(value);
/*  462:     */         }
/*  463:     */       }
/*  464:     */     }
/*  465: 473 */     return headValue + chestValue + legsValue + bootsValue;
/*  466:     */   }
/*  467:     */   
/*  468:     */   public double getCritDamageGear(Player player)
/*  469:     */   {
/*  470: 477 */     this.critDamage = ItemLoreStats.plugin.getConfig().getString("statNames.critDamage").replaceAll("&([0-9a-f])", "");
/*  471:     */     
/*  472: 479 */     double headValue = 0.0D;
/*  473: 480 */     double chestValue = 0.0D;
/*  474: 481 */     double legsValue = 0.0D;
/*  475: 482 */     double bootsValue = 0.0D;
/*  476:     */     
/*  477: 484 */     ItemStack head = player.getInventory().getHelmet();
/*  478:     */     String value;
/*  479: 486 */     if ((head != null) && 
/*  480: 487 */       (head.hasItemMeta()) && 
/*  481: 488 */       (head.getItemMeta().hasLore()))
/*  482:     */     {
/*  483: 489 */       List<String> headLore = head.getItemMeta().getLore();
/*  484: 490 */       for (String line : headLore) {
/*  485: 491 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/*  486:     */         {
/*  487: 492 */           value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/*  488: 493 */           headValue = Double.parseDouble(value);
/*  489:     */         }
/*  490:     */       }
/*  491:     */     }
/*  492: 500 */     ItemStack chest = player.getInventory().getChestplate();
/*  493:     */     String value;
/*  494: 502 */     if ((chest != null) && 
/*  495: 503 */       (chest.hasItemMeta()) && 
/*  496: 504 */       (chest.getItemMeta().hasLore()))
/*  497:     */     {
/*  498: 505 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  499: 506 */       for (String line : chestLore) {
/*  500: 507 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/*  501:     */         {
/*  502: 508 */           value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/*  503: 509 */           chestValue = Double.parseDouble(value);
/*  504:     */         }
/*  505:     */       }
/*  506:     */     }
/*  507: 516 */     ItemStack legs = player.getInventory().getLeggings();
/*  508:     */     String value;
/*  509: 518 */     if ((legs != null) && 
/*  510: 519 */       (legs.hasItemMeta()) && 
/*  511: 520 */       (legs.getItemMeta().hasLore()))
/*  512:     */     {
/*  513: 521 */       Object legsLore = legs.getItemMeta().getLore();
/*  514: 522 */       for (String line : (List)legsLore) {
/*  515: 523 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/*  516:     */         {
/*  517: 524 */           value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/*  518: 525 */           legsValue = Double.parseDouble(value);
/*  519:     */         }
/*  520:     */       }
/*  521:     */     }
/*  522: 532 */     ItemStack boots = player.getInventory().getBoots();
/*  523: 534 */     if ((boots != null) && 
/*  524: 535 */       (boots.hasItemMeta()) && 
/*  525: 536 */       (boots.getItemMeta().hasLore()))
/*  526:     */     {
/*  527: 537 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  528: 538 */       for (String line : bootsLore) {
/*  529: 539 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/*  530:     */         {
/*  531: 540 */           String value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/*  532: 541 */           bootsValue = Double.parseDouble(value);
/*  533:     */         }
/*  534:     */       }
/*  535:     */     }
/*  536: 548 */     return headValue + chestValue + legsValue + bootsValue;
/*  537:     */   }
/*  538:     */   
/*  539:     */   public double getHealthGear(Player player)
/*  540:     */   {
/*  541: 552 */     this.health = ItemLoreStats.plugin.getConfig().getString("statNames.health").replaceAll("&([0-9a-f])", "");
/*  542:     */     
/*  543: 554 */     double headValue = 0.0D;
/*  544: 555 */     double chestValue = 0.0D;
/*  545: 556 */     double legsValue = 0.0D;
/*  546: 557 */     double bootsValue = 0.0D;
/*  547:     */     
/*  548: 559 */     ItemStack head = player.getInventory().getHelmet();
/*  549:     */     String value;
/*  550: 561 */     if ((head != null) && 
/*  551: 562 */       (head.hasItemMeta()) && 
/*  552: 563 */       (head.getItemMeta().hasLore()))
/*  553:     */     {
/*  554: 564 */       List<String> headLore = head.getItemMeta().getLore();
/*  555: 565 */       for (String line : headLore) {
/*  556: 566 */         if (ChatColor.stripColor(line).startsWith(this.health + ": "))
/*  557:     */         {
/*  558: 567 */           value = ChatColor.stripColor(line).substring((this.health + ": ").length()).trim();
/*  559: 568 */           headValue = Double.parseDouble(value);
/*  560:     */         }
/*  561:     */       }
/*  562:     */     }
/*  563: 576 */     ItemStack chest = player.getInventory().getChestplate();
/*  564:     */     String value;
/*  565: 578 */     if ((chest != null) && 
/*  566: 579 */       (chest.hasItemMeta()) && 
/*  567: 580 */       (chest.getItemMeta().hasLore()))
/*  568:     */     {
/*  569: 581 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  570: 582 */       for (String line : chestLore) {
/*  571: 583 */         if (ChatColor.stripColor(line).startsWith(this.health + ": "))
/*  572:     */         {
/*  573: 584 */           value = ChatColor.stripColor(line).substring((this.health + ": ").length()).trim();
/*  574: 585 */           chestValue = Double.parseDouble(value);
/*  575:     */         }
/*  576:     */       }
/*  577:     */     }
/*  578: 592 */     ItemStack legs = player.getInventory().getLeggings();
/*  579:     */     String value;
/*  580: 594 */     if ((legs != null) && 
/*  581: 595 */       (legs.hasItemMeta()) && 
/*  582: 596 */       (legs.getItemMeta().hasLore()))
/*  583:     */     {
/*  584: 597 */       Object legsLore = legs.getItemMeta().getLore();
/*  585: 598 */       for (String line : (List)legsLore) {
/*  586: 599 */         if (ChatColor.stripColor(line).startsWith(this.health + ": "))
/*  587:     */         {
/*  588: 600 */           value = ChatColor.stripColor(line).substring((this.health + ": ").length()).trim();
/*  589: 601 */           legsValue = Double.parseDouble(value);
/*  590:     */         }
/*  591:     */       }
/*  592:     */     }
/*  593: 608 */     ItemStack boots = player.getInventory().getBoots();
/*  594: 610 */     if ((boots != null) && 
/*  595: 611 */       (boots.hasItemMeta()) && 
/*  596: 612 */       (boots.getItemMeta().hasLore()))
/*  597:     */     {
/*  598: 613 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  599: 614 */       for (String line : bootsLore) {
/*  600: 615 */         if (ChatColor.stripColor(line).startsWith(this.health + ": "))
/*  601:     */         {
/*  602: 616 */           String value = ChatColor.stripColor(line).substring((this.health + ": ").length()).trim();
/*  603: 617 */           bootsValue = Double.parseDouble(value);
/*  604:     */         }
/*  605:     */       }
/*  606:     */     }
/*  607: 624 */     return headValue + chestValue + legsValue + bootsValue;
/*  608:     */   }
/*  609:     */   
/*  610:     */   public double getHealthRegenGear(Player player)
/*  611:     */   {
/*  612: 628 */     this.healthRegen = ItemLoreStats.plugin.getConfig().getString("statNames.healthregen").replaceAll("&([0-9a-f])", "");
/*  613:     */     
/*  614: 630 */     double headValue = 0.0D;
/*  615: 631 */     double chestValue = 0.0D;
/*  616: 632 */     double legsValue = 0.0D;
/*  617: 633 */     double bootsValue = 0.0D;
/*  618:     */     
/*  619: 635 */     ItemStack head = player.getInventory().getHelmet();
/*  620:     */     String value;
/*  621: 637 */     if ((head != null) && 
/*  622: 638 */       (head.hasItemMeta()) && 
/*  623: 639 */       (head.getItemMeta().hasLore()))
/*  624:     */     {
/*  625: 640 */       List<String> headLore = head.getItemMeta().getLore();
/*  626: 641 */       for (String line : headLore) {
/*  627: 642 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/*  628:     */         {
/*  629: 643 */           value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/*  630: 644 */           headValue = Double.parseDouble(value);
/*  631:     */         }
/*  632:     */       }
/*  633:     */     }
/*  634: 651 */     ItemStack chest = player.getInventory().getChestplate();
/*  635:     */     String value;
/*  636: 653 */     if ((chest != null) && 
/*  637: 654 */       (chest.hasItemMeta()) && 
/*  638: 655 */       (chest.getItemMeta().hasLore()))
/*  639:     */     {
/*  640: 656 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  641: 657 */       for (String line : chestLore) {
/*  642: 658 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/*  643:     */         {
/*  644: 659 */           value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/*  645: 660 */           chestValue = Double.parseDouble(value);
/*  646:     */         }
/*  647:     */       }
/*  648:     */     }
/*  649: 667 */     ItemStack legs = player.getInventory().getLeggings();
/*  650:     */     String value;
/*  651: 669 */     if ((legs != null) && 
/*  652: 670 */       (legs.hasItemMeta()) && 
/*  653: 671 */       (legs.getItemMeta().hasLore()))
/*  654:     */     {
/*  655: 672 */       Object legsLore = legs.getItemMeta().getLore();
/*  656: 673 */       for (String line : (List)legsLore) {
/*  657: 674 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/*  658:     */         {
/*  659: 675 */           value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/*  660: 676 */           legsValue = Double.parseDouble(value);
/*  661:     */         }
/*  662:     */       }
/*  663:     */     }
/*  664: 683 */     ItemStack boots = player.getInventory().getBoots();
/*  665: 685 */     if ((boots != null) && 
/*  666: 686 */       (boots.hasItemMeta()) && 
/*  667: 687 */       (boots.getItemMeta().hasLore()))
/*  668:     */     {
/*  669: 688 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  670: 689 */       for (String line : bootsLore) {
/*  671: 690 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/*  672:     */         {
/*  673: 691 */           String value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/*  674: 692 */           bootsValue = Double.parseDouble(value);
/*  675:     */         }
/*  676:     */       }
/*  677:     */     }
/*  678: 699 */     return headValue + chestValue + legsValue + bootsValue;
/*  679:     */   }
/*  680:     */   
/*  681:     */   public double getLifeStealGear(Player player)
/*  682:     */   {
/*  683: 703 */     this.lifeSteal = ItemLoreStats.plugin.getConfig().getString("statNames.lifesteal").replaceAll("&([0-9a-f])", "");
/*  684:     */     
/*  685: 705 */     double headValue = 0.0D;
/*  686: 706 */     double chestValue = 0.0D;
/*  687: 707 */     double legsValue = 0.0D;
/*  688: 708 */     double bootsValue = 0.0D;
/*  689:     */     
/*  690: 710 */     ItemStack head = player.getInventory().getHelmet();
/*  691:     */     String value;
/*  692: 712 */     if ((head != null) && 
/*  693: 713 */       (head.hasItemMeta()) && 
/*  694: 714 */       (head.getItemMeta().hasLore()))
/*  695:     */     {
/*  696: 715 */       List<String> headLore = head.getItemMeta().getLore();
/*  697: 716 */       for (String line : headLore) {
/*  698: 717 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/*  699:     */         {
/*  700: 718 */           value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/*  701: 719 */           headValue = Double.parseDouble(value);
/*  702:     */         }
/*  703:     */       }
/*  704:     */     }
/*  705: 726 */     ItemStack chest = player.getInventory().getChestplate();
/*  706:     */     String value;
/*  707: 728 */     if ((chest != null) && 
/*  708: 729 */       (chest.hasItemMeta()) && 
/*  709: 730 */       (chest.getItemMeta().hasLore()))
/*  710:     */     {
/*  711: 731 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  712: 732 */       for (String line : chestLore) {
/*  713: 733 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/*  714:     */         {
/*  715: 734 */           value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/*  716: 735 */           chestValue = Double.parseDouble(value);
/*  717:     */         }
/*  718:     */       }
/*  719:     */     }
/*  720: 742 */     ItemStack legs = player.getInventory().getLeggings();
/*  721:     */     String value;
/*  722: 744 */     if ((legs != null) && 
/*  723: 745 */       (legs.hasItemMeta()) && 
/*  724: 746 */       (legs.getItemMeta().hasLore()))
/*  725:     */     {
/*  726: 747 */       Object legsLore = legs.getItemMeta().getLore();
/*  727: 748 */       for (String line : (List)legsLore) {
/*  728: 749 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/*  729:     */         {
/*  730: 750 */           value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/*  731: 751 */           legsValue = Double.parseDouble(value);
/*  732:     */         }
/*  733:     */       }
/*  734:     */     }
/*  735: 758 */     ItemStack boots = player.getInventory().getBoots();
/*  736: 760 */     if ((boots != null) && 
/*  737: 761 */       (boots.hasItemMeta()) && 
/*  738: 762 */       (boots.getItemMeta().hasLore()))
/*  739:     */     {
/*  740: 763 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  741: 764 */       for (String line : bootsLore) {
/*  742: 765 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/*  743:     */         {
/*  744: 766 */           String value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/*  745: 767 */           bootsValue = Double.parseDouble(value);
/*  746:     */         }
/*  747:     */       }
/*  748:     */     }
/*  749: 774 */     return headValue + chestValue + legsValue + bootsValue;
/*  750:     */   }
/*  751:     */   
/*  752:     */   public double getLifeStealHealGear(Player player)
/*  753:     */   {
/*  754: 778 */     this.lifeStealHeal = ItemLoreStats.plugin.getConfig().getString("statNames.lifestealheal").replaceAll("&([0-9a-f])", "");
/*  755:     */     
/*  756: 780 */     double headValue = 0.0D;
/*  757: 781 */     double chestValue = 0.0D;
/*  758: 782 */     double legsValue = 0.0D;
/*  759: 783 */     double bootsValue = 0.0D;
/*  760:     */     
/*  761: 785 */     ItemStack head = player.getInventory().getHelmet();
/*  762:     */     String value;
/*  763: 787 */     if ((head != null) && 
/*  764: 788 */       (head.hasItemMeta()) && 
/*  765: 789 */       (head.getItemMeta().hasLore()))
/*  766:     */     {
/*  767: 790 */       List<String> headLore = head.getItemMeta().getLore();
/*  768: 791 */       for (String line : headLore) {
/*  769: 792 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/*  770:     */         {
/*  771: 793 */           value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/*  772: 794 */           headValue = Double.parseDouble(value);
/*  773:     */         }
/*  774:     */       }
/*  775:     */     }
/*  776: 801 */     ItemStack chest = player.getInventory().getChestplate();
/*  777:     */     String value;
/*  778: 803 */     if ((chest != null) && 
/*  779: 804 */       (chest.hasItemMeta()) && 
/*  780: 805 */       (chest.getItemMeta().hasLore()))
/*  781:     */     {
/*  782: 806 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  783: 807 */       for (String line : chestLore) {
/*  784: 808 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/*  785:     */         {
/*  786: 809 */           value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/*  787: 810 */           chestValue = Double.parseDouble(value);
/*  788:     */         }
/*  789:     */       }
/*  790:     */     }
/*  791: 817 */     ItemStack legs = player.getInventory().getLeggings();
/*  792:     */     String value;
/*  793: 819 */     if ((legs != null) && 
/*  794: 820 */       (legs.hasItemMeta()) && 
/*  795: 821 */       (legs.getItemMeta().hasLore()))
/*  796:     */     {
/*  797: 822 */       Object legsLore = legs.getItemMeta().getLore();
/*  798: 823 */       for (String line : (List)legsLore) {
/*  799: 824 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/*  800:     */         {
/*  801: 825 */           value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/*  802: 826 */           legsValue = Double.parseDouble(value);
/*  803:     */         }
/*  804:     */       }
/*  805:     */     }
/*  806: 833 */     ItemStack boots = player.getInventory().getBoots();
/*  807: 835 */     if ((boots != null) && 
/*  808: 836 */       (boots.hasItemMeta()) && 
/*  809: 837 */       (boots.getItemMeta().hasLore()))
/*  810:     */     {
/*  811: 838 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  812: 839 */       for (String line : bootsLore) {
/*  813: 840 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/*  814:     */         {
/*  815: 841 */           String value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/*  816: 842 */           bootsValue = Double.parseDouble(value);
/*  817:     */         }
/*  818:     */       }
/*  819:     */     }
/*  820: 849 */     return headValue + chestValue + legsValue + bootsValue;
/*  821:     */   }
/*  822:     */   
/*  823:     */   public double getReflectGear(Player player)
/*  824:     */   {
/*  825: 853 */     this.reflect = ItemLoreStats.plugin.getConfig().getString("statNames.reflect").replaceAll("&([0-9a-f])", "");
/*  826:     */     
/*  827: 855 */     double headValue = 0.0D;
/*  828: 856 */     double chestValue = 0.0D;
/*  829: 857 */     double legsValue = 0.0D;
/*  830: 858 */     double bootsValue = 0.0D;
/*  831:     */     
/*  832: 860 */     ItemStack head = player.getInventory().getHelmet();
/*  833:     */     String value;
/*  834: 862 */     if ((head != null) && 
/*  835: 863 */       (head.hasItemMeta()) && 
/*  836: 864 */       (head.getItemMeta().hasLore()))
/*  837:     */     {
/*  838: 865 */       List<String> headLore = head.getItemMeta().getLore();
/*  839: 866 */       for (String line : headLore) {
/*  840: 867 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/*  841:     */         {
/*  842: 868 */           value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/*  843: 869 */           headValue = Double.parseDouble(value);
/*  844:     */         }
/*  845:     */       }
/*  846:     */     }
/*  847: 876 */     ItemStack chest = player.getInventory().getChestplate();
/*  848:     */     String value;
/*  849: 878 */     if ((chest != null) && 
/*  850: 879 */       (chest.hasItemMeta()) && 
/*  851: 880 */       (chest.getItemMeta().hasLore()))
/*  852:     */     {
/*  853: 881 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  854: 882 */       for (String line : chestLore) {
/*  855: 883 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/*  856:     */         {
/*  857: 884 */           value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/*  858: 885 */           chestValue = Double.parseDouble(value);
/*  859:     */         }
/*  860:     */       }
/*  861:     */     }
/*  862: 892 */     ItemStack legs = player.getInventory().getLeggings();
/*  863:     */     String value;
/*  864: 894 */     if ((legs != null) && 
/*  865: 895 */       (legs.hasItemMeta()) && 
/*  866: 896 */       (legs.getItemMeta().hasLore()))
/*  867:     */     {
/*  868: 897 */       Object legsLore = legs.getItemMeta().getLore();
/*  869: 898 */       for (String line : (List)legsLore) {
/*  870: 899 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/*  871:     */         {
/*  872: 900 */           value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/*  873: 901 */           legsValue = Double.parseDouble(value);
/*  874:     */         }
/*  875:     */       }
/*  876:     */     }
/*  877: 908 */     ItemStack boots = player.getInventory().getBoots();
/*  878: 910 */     if ((boots != null) && 
/*  879: 911 */       (boots.hasItemMeta()) && 
/*  880: 912 */       (boots.getItemMeta().hasLore()))
/*  881:     */     {
/*  882: 913 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  883: 914 */       for (String line : bootsLore) {
/*  884: 915 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/*  885:     */         {
/*  886: 916 */           String value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/*  887: 917 */           bootsValue = Double.parseDouble(value);
/*  888:     */         }
/*  889:     */       }
/*  890:     */     }
/*  891: 924 */     return headValue + chestValue + legsValue + bootsValue;
/*  892:     */   }
/*  893:     */   
/*  894:     */   public double getFireGear(Player player)
/*  895:     */   {
/*  896: 928 */     this.fire = ItemLoreStats.plugin.getConfig().getString("statNames.fire").replaceAll("&([0-9a-f])", "");
/*  897:     */     
/*  898: 930 */     double headValue = 0.0D;
/*  899: 931 */     double chestValue = 0.0D;
/*  900: 932 */     double legsValue = 0.0D;
/*  901: 933 */     double bootsValue = 0.0D;
/*  902:     */     
/*  903: 935 */     ItemStack head = player.getInventory().getHelmet();
/*  904:     */     String value;
/*  905: 937 */     if ((head != null) && 
/*  906: 938 */       (head.hasItemMeta()) && 
/*  907: 939 */       (head.getItemMeta().hasLore()))
/*  908:     */     {
/*  909: 940 */       List<String> headLore = head.getItemMeta().getLore();
/*  910: 941 */       for (String line : headLore) {
/*  911: 942 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/*  912:     */         {
/*  913: 943 */           value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/*  914: 944 */           headValue = Double.parseDouble(value);
/*  915:     */         }
/*  916:     */       }
/*  917:     */     }
/*  918: 951 */     ItemStack chest = player.getInventory().getChestplate();
/*  919:     */     String value;
/*  920: 953 */     if ((chest != null) && 
/*  921: 954 */       (chest.hasItemMeta()) && 
/*  922: 955 */       (chest.getItemMeta().hasLore()))
/*  923:     */     {
/*  924: 956 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  925: 957 */       for (String line : chestLore) {
/*  926: 958 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/*  927:     */         {
/*  928: 959 */           value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/*  929: 960 */           chestValue = Double.parseDouble(value);
/*  930:     */         }
/*  931:     */       }
/*  932:     */     }
/*  933: 967 */     ItemStack legs = player.getInventory().getLeggings();
/*  934:     */     String value;
/*  935: 969 */     if ((legs != null) && 
/*  936: 970 */       (legs.hasItemMeta()) && 
/*  937: 971 */       (legs.getItemMeta().hasLore()))
/*  938:     */     {
/*  939: 972 */       Object legsLore = legs.getItemMeta().getLore();
/*  940: 973 */       for (String line : (List)legsLore) {
/*  941: 974 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/*  942:     */         {
/*  943: 975 */           value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/*  944: 976 */           legsValue = Double.parseDouble(value);
/*  945:     */         }
/*  946:     */       }
/*  947:     */     }
/*  948: 983 */     ItemStack boots = player.getInventory().getBoots();
/*  949: 985 */     if ((boots != null) && 
/*  950: 986 */       (boots.hasItemMeta()) && 
/*  951: 987 */       (boots.getItemMeta().hasLore()))
/*  952:     */     {
/*  953: 988 */       List<String> bootsLore = boots.getItemMeta().getLore();
/*  954: 989 */       for (String line : bootsLore) {
/*  955: 990 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/*  956:     */         {
/*  957: 991 */           String value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/*  958: 992 */           bootsValue = Double.parseDouble(value);
/*  959:     */         }
/*  960:     */       }
/*  961:     */     }
/*  962: 999 */     return headValue + chestValue + legsValue + bootsValue;
/*  963:     */   }
/*  964:     */   
/*  965:     */   public double getIceGear(Player player)
/*  966:     */   {
/*  967:1003 */     this.ice = ItemLoreStats.plugin.getConfig().getString("statNames.ice").replaceAll("&([0-9a-f])", "");
/*  968:     */     
/*  969:1005 */     double headValue = 0.0D;
/*  970:1006 */     double chestValue = 0.0D;
/*  971:1007 */     double legsValue = 0.0D;
/*  972:1008 */     double bootsValue = 0.0D;
/*  973:     */     
/*  974:1010 */     ItemStack head = player.getInventory().getHelmet();
/*  975:     */     String value;
/*  976:1012 */     if ((head != null) && 
/*  977:1013 */       (head.hasItemMeta()) && 
/*  978:1014 */       (head.getItemMeta().hasLore()))
/*  979:     */     {
/*  980:1015 */       List<String> headLore = head.getItemMeta().getLore();
/*  981:1016 */       for (String line : headLore) {
/*  982:1017 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/*  983:     */         {
/*  984:1018 */           value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/*  985:1019 */           headValue = Double.parseDouble(value);
/*  986:     */         }
/*  987:     */       }
/*  988:     */     }
/*  989:1026 */     ItemStack chest = player.getInventory().getChestplate();
/*  990:     */     String value;
/*  991:1028 */     if ((chest != null) && 
/*  992:1029 */       (chest.hasItemMeta()) && 
/*  993:1030 */       (chest.getItemMeta().hasLore()))
/*  994:     */     {
/*  995:1031 */       List<String> chestLore = chest.getItemMeta().getLore();
/*  996:1032 */       for (String line : chestLore) {
/*  997:1033 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/*  998:     */         {
/*  999:1034 */           value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/* 1000:1035 */           chestValue = Double.parseDouble(value);
/* 1001:     */         }
/* 1002:     */       }
/* 1003:     */     }
/* 1004:1042 */     ItemStack legs = player.getInventory().getLeggings();
/* 1005:     */     String value;
/* 1006:1044 */     if ((legs != null) && 
/* 1007:1045 */       (legs.hasItemMeta()) && 
/* 1008:1046 */       (legs.getItemMeta().hasLore()))
/* 1009:     */     {
/* 1010:1047 */       Object legsLore = legs.getItemMeta().getLore();
/* 1011:1048 */       for (String line : (List)legsLore) {
/* 1012:1049 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/* 1013:     */         {
/* 1014:1050 */           value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/* 1015:1051 */           legsValue = Double.parseDouble(value);
/* 1016:     */         }
/* 1017:     */       }
/* 1018:     */     }
/* 1019:1058 */     ItemStack boots = player.getInventory().getBoots();
/* 1020:1060 */     if ((boots != null) && 
/* 1021:1061 */       (boots.hasItemMeta()) && 
/* 1022:1062 */       (boots.getItemMeta().hasLore()))
/* 1023:     */     {
/* 1024:1063 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1025:1064 */       for (String line : bootsLore) {
/* 1026:1065 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/* 1027:     */         {
/* 1028:1066 */           String value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/* 1029:1067 */           bootsValue = Double.parseDouble(value);
/* 1030:     */         }
/* 1031:     */       }
/* 1032:     */     }
/* 1033:1074 */     return headValue + chestValue + legsValue + bootsValue;
/* 1034:     */   }
/* 1035:     */   
/* 1036:     */   public double getPoisonGear(Player player)
/* 1037:     */   {
/* 1038:1078 */     this.poison = ItemLoreStats.plugin.getConfig().getString("statNames.poison").replaceAll("&([0-9a-f])", "");
/* 1039:     */     
/* 1040:1080 */     double headValue = 0.0D;
/* 1041:1081 */     double chestValue = 0.0D;
/* 1042:1082 */     double legsValue = 0.0D;
/* 1043:1083 */     double bootsValue = 0.0D;
/* 1044:     */     
/* 1045:1085 */     ItemStack head = player.getInventory().getHelmet();
/* 1046:     */     String value;
/* 1047:1087 */     if ((head != null) && 
/* 1048:1088 */       (head.hasItemMeta()) && 
/* 1049:1089 */       (head.getItemMeta().hasLore()))
/* 1050:     */     {
/* 1051:1090 */       List<String> headLore = head.getItemMeta().getLore();
/* 1052:1091 */       for (String line : headLore) {
/* 1053:1092 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/* 1054:     */         {
/* 1055:1093 */           value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/* 1056:1094 */           headValue = Double.parseDouble(value);
/* 1057:     */         }
/* 1058:     */       }
/* 1059:     */     }
/* 1060:1101 */     ItemStack chest = player.getInventory().getChestplate();
/* 1061:     */     String value;
/* 1062:1103 */     if ((chest != null) && 
/* 1063:1104 */       (chest.hasItemMeta()) && 
/* 1064:1105 */       (chest.getItemMeta().hasLore()))
/* 1065:     */     {
/* 1066:1106 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1067:1107 */       for (String line : chestLore) {
/* 1068:1108 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/* 1069:     */         {
/* 1070:1109 */           value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/* 1071:1110 */           chestValue = Double.parseDouble(value);
/* 1072:     */         }
/* 1073:     */       }
/* 1074:     */     }
/* 1075:1117 */     ItemStack legs = player.getInventory().getLeggings();
/* 1076:     */     String value;
/* 1077:1119 */     if ((legs != null) && 
/* 1078:1120 */       (legs.hasItemMeta()) && 
/* 1079:1121 */       (legs.getItemMeta().hasLore()))
/* 1080:     */     {
/* 1081:1122 */       Object legsLore = legs.getItemMeta().getLore();
/* 1082:1123 */       for (String line : (List)legsLore) {
/* 1083:1124 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/* 1084:     */         {
/* 1085:1125 */           value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/* 1086:1126 */           legsValue = Double.parseDouble(value);
/* 1087:     */         }
/* 1088:     */       }
/* 1089:     */     }
/* 1090:1133 */     ItemStack boots = player.getInventory().getBoots();
/* 1091:1135 */     if ((boots != null) && 
/* 1092:1136 */       (boots.hasItemMeta()) && 
/* 1093:1137 */       (boots.getItemMeta().hasLore()))
/* 1094:     */     {
/* 1095:1138 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1096:1139 */       for (String line : bootsLore) {
/* 1097:1140 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/* 1098:     */         {
/* 1099:1141 */           String value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/* 1100:1142 */           bootsValue = Double.parseDouble(value);
/* 1101:     */         }
/* 1102:     */       }
/* 1103:     */     }
/* 1104:1149 */     return headValue + chestValue + legsValue + bootsValue;
/* 1105:     */   }
/* 1106:     */   
/* 1107:     */   public double getWitherGear(Player player)
/* 1108:     */   {
/* 1109:1153 */     this.wither = ItemLoreStats.plugin.getConfig().getString("statNames.wither").replaceAll("&([0-9a-f])", "");
/* 1110:     */     
/* 1111:1155 */     double headValue = 0.0D;
/* 1112:1156 */     double chestValue = 0.0D;
/* 1113:1157 */     double legsValue = 0.0D;
/* 1114:1158 */     double bootsValue = 0.0D;
/* 1115:     */     
/* 1116:1160 */     ItemStack head = player.getInventory().getHelmet();
/* 1117:     */     String value;
/* 1118:1162 */     if ((head != null) && 
/* 1119:1163 */       (head.hasItemMeta()) && 
/* 1120:1164 */       (head.getItemMeta().hasLore()))
/* 1121:     */     {
/* 1122:1165 */       List<String> headLore = head.getItemMeta().getLore();
/* 1123:1166 */       for (String line : headLore) {
/* 1124:1167 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/* 1125:     */         {
/* 1126:1168 */           value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/* 1127:1169 */           headValue = Double.parseDouble(value);
/* 1128:     */         }
/* 1129:     */       }
/* 1130:     */     }
/* 1131:1176 */     ItemStack chest = player.getInventory().getChestplate();
/* 1132:     */     String value;
/* 1133:1178 */     if ((chest != null) && 
/* 1134:1179 */       (chest.hasItemMeta()) && 
/* 1135:1180 */       (chest.getItemMeta().hasLore()))
/* 1136:     */     {
/* 1137:1181 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1138:1182 */       for (String line : chestLore) {
/* 1139:1183 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/* 1140:     */         {
/* 1141:1184 */           value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/* 1142:1185 */           chestValue = Double.parseDouble(value);
/* 1143:     */         }
/* 1144:     */       }
/* 1145:     */     }
/* 1146:1192 */     ItemStack legs = player.getInventory().getLeggings();
/* 1147:     */     String value;
/* 1148:1194 */     if ((legs != null) && 
/* 1149:1195 */       (legs.hasItemMeta()) && 
/* 1150:1196 */       (legs.getItemMeta().hasLore()))
/* 1151:     */     {
/* 1152:1197 */       Object legsLore = legs.getItemMeta().getLore();
/* 1153:1198 */       for (String line : (List)legsLore) {
/* 1154:1199 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/* 1155:     */         {
/* 1156:1200 */           value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/* 1157:1201 */           legsValue = Double.parseDouble(value);
/* 1158:     */         }
/* 1159:     */       }
/* 1160:     */     }
/* 1161:1208 */     ItemStack boots = player.getInventory().getBoots();
/* 1162:1210 */     if ((boots != null) && 
/* 1163:1211 */       (boots.hasItemMeta()) && 
/* 1164:1212 */       (boots.getItemMeta().hasLore()))
/* 1165:     */     {
/* 1166:1213 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1167:1214 */       for (String line : bootsLore) {
/* 1168:1215 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/* 1169:     */         {
/* 1170:1216 */           String value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/* 1171:1217 */           bootsValue = Double.parseDouble(value);
/* 1172:     */         }
/* 1173:     */       }
/* 1174:     */     }
/* 1175:1224 */     return headValue + chestValue + legsValue + bootsValue;
/* 1176:     */   }
/* 1177:     */   
/* 1178:     */   public double getHarmingGear(Player player)
/* 1179:     */   {
/* 1180:1228 */     this.harming = ItemLoreStats.plugin.getConfig().getString("statNames.harming").replaceAll("&([0-9a-f])", "");
/* 1181:     */     
/* 1182:1230 */     double headValue = 0.0D;
/* 1183:1231 */     double chestValue = 0.0D;
/* 1184:1232 */     double legsValue = 0.0D;
/* 1185:1233 */     double bootsValue = 0.0D;
/* 1186:     */     
/* 1187:1235 */     ItemStack head = player.getInventory().getHelmet();
/* 1188:     */     String value;
/* 1189:1237 */     if ((head != null) && 
/* 1190:1238 */       (head.hasItemMeta()) && 
/* 1191:1239 */       (head.getItemMeta().hasLore()))
/* 1192:     */     {
/* 1193:1240 */       List<String> headLore = head.getItemMeta().getLore();
/* 1194:1241 */       for (String line : headLore) {
/* 1195:1242 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1196:     */         {
/* 1197:1243 */           value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1198:1244 */           headValue = Double.parseDouble(value);
/* 1199:     */         }
/* 1200:     */       }
/* 1201:     */     }
/* 1202:1251 */     ItemStack chest = player.getInventory().getChestplate();
/* 1203:     */     String value;
/* 1204:1253 */     if ((chest != null) && 
/* 1205:1254 */       (chest.hasItemMeta()) && 
/* 1206:1255 */       (chest.getItemMeta().hasLore()))
/* 1207:     */     {
/* 1208:1256 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1209:1257 */       for (String line : chestLore) {
/* 1210:1258 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1211:     */         {
/* 1212:1259 */           value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1213:1260 */           chestValue = Double.parseDouble(value);
/* 1214:     */         }
/* 1215:     */       }
/* 1216:     */     }
/* 1217:1267 */     ItemStack legs = player.getInventory().getLeggings();
/* 1218:     */     String value;
/* 1219:1269 */     if ((legs != null) && 
/* 1220:1270 */       (legs.hasItemMeta()) && 
/* 1221:1271 */       (legs.getItemMeta().hasLore()))
/* 1222:     */     {
/* 1223:1272 */       Object legsLore = legs.getItemMeta().getLore();
/* 1224:1273 */       for (String line : (List)legsLore) {
/* 1225:1274 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1226:     */         {
/* 1227:1275 */           value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1228:1276 */           legsValue = Double.parseDouble(value);
/* 1229:     */         }
/* 1230:     */       }
/* 1231:     */     }
/* 1232:1283 */     ItemStack boots = player.getInventory().getBoots();
/* 1233:1285 */     if ((boots != null) && 
/* 1234:1286 */       (boots.hasItemMeta()) && 
/* 1235:1287 */       (boots.getItemMeta().hasLore()))
/* 1236:     */     {
/* 1237:1288 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1238:1289 */       for (String line : bootsLore) {
/* 1239:1290 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1240:     */         {
/* 1241:1291 */           String value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1242:1292 */           bootsValue = Double.parseDouble(value);
/* 1243:     */         }
/* 1244:     */       }
/* 1245:     */     }
/* 1246:1299 */     return headValue + chestValue + legsValue + bootsValue;
/* 1247:     */   }
/* 1248:     */   
/* 1249:     */   public double getBlindGear(Player player)
/* 1250:     */   {
/* 1251:1303 */     this.blind = ItemLoreStats.plugin.getConfig().getString("statNames.blind").replaceAll("&([0-9a-f])", "");
/* 1252:     */     
/* 1253:1305 */     double headValue = 0.0D;
/* 1254:1306 */     double chestValue = 0.0D;
/* 1255:1307 */     double legsValue = 0.0D;
/* 1256:1308 */     double bootsValue = 0.0D;
/* 1257:     */     
/* 1258:1310 */     ItemStack head = player.getInventory().getHelmet();
/* 1259:     */     String value;
/* 1260:1312 */     if ((head != null) && 
/* 1261:1313 */       (head.hasItemMeta()) && 
/* 1262:1314 */       (head.getItemMeta().hasLore()))
/* 1263:     */     {
/* 1264:1315 */       List<String> headLore = head.getItemMeta().getLore();
/* 1265:1316 */       for (String line : headLore) {
/* 1266:1317 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1267:     */         {
/* 1268:1318 */           value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1269:1319 */           headValue = Double.parseDouble(value);
/* 1270:     */         }
/* 1271:     */       }
/* 1272:     */     }
/* 1273:1326 */     ItemStack chest = player.getInventory().getChestplate();
/* 1274:     */     String value;
/* 1275:1328 */     if ((chest != null) && 
/* 1276:1329 */       (chest.hasItemMeta()) && 
/* 1277:1330 */       (chest.getItemMeta().hasLore()))
/* 1278:     */     {
/* 1279:1331 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1280:1332 */       for (String line : chestLore) {
/* 1281:1333 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1282:     */         {
/* 1283:1334 */           value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1284:1335 */           chestValue = Double.parseDouble(value);
/* 1285:     */         }
/* 1286:     */       }
/* 1287:     */     }
/* 1288:1342 */     ItemStack legs = player.getInventory().getLeggings();
/* 1289:     */     String value;
/* 1290:1344 */     if ((legs != null) && 
/* 1291:1345 */       (legs.hasItemMeta()) && 
/* 1292:1346 */       (legs.getItemMeta().hasLore()))
/* 1293:     */     {
/* 1294:1347 */       Object legsLore = legs.getItemMeta().getLore();
/* 1295:1348 */       for (String line : (List)legsLore) {
/* 1296:1349 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1297:     */         {
/* 1298:1350 */           value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1299:1351 */           legsValue = Double.parseDouble(value);
/* 1300:     */         }
/* 1301:     */       }
/* 1302:     */     }
/* 1303:1358 */     ItemStack boots = player.getInventory().getBoots();
/* 1304:1360 */     if ((boots != null) && 
/* 1305:1361 */       (boots.hasItemMeta()) && 
/* 1306:1362 */       (boots.getItemMeta().hasLore()))
/* 1307:     */     {
/* 1308:1363 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1309:1364 */       for (String line : bootsLore) {
/* 1310:1365 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1311:     */         {
/* 1312:1366 */           String value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1313:1367 */           bootsValue = Double.parseDouble(value);
/* 1314:     */         }
/* 1315:     */       }
/* 1316:     */     }
/* 1317:1374 */     return headValue + chestValue + legsValue + bootsValue;
/* 1318:     */   }
/* 1319:     */   
/* 1320:     */   public double getMovementSpeedGear(Player player)
/* 1321:     */   {
/* 1322:1378 */     this.movementspeed = ItemLoreStats.plugin.getConfig().getString("statNames.movementspeed").replaceAll("&([0-9a-f])", "");
/* 1323:     */     
/* 1324:1380 */     double headValue = 0.0D;
/* 1325:1381 */     double chestValue = 0.0D;
/* 1326:1382 */     double legsValue = 0.0D;
/* 1327:1383 */     double bootsValue = 0.0D;
/* 1328:     */     
/* 1329:1385 */     ItemStack head = player.getInventory().getHelmet();
/* 1330:     */     String value;
/* 1331:1387 */     if ((head != null) && 
/* 1332:1388 */       (head.hasItemMeta()) && 
/* 1333:1389 */       (head.getItemMeta().hasLore()))
/* 1334:     */     {
/* 1335:1390 */       List<String> headLore = head.getItemMeta().getLore();
/* 1336:1391 */       for (String line : headLore) {
/* 1337:1392 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1338:     */         {
/* 1339:1393 */           value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1340:1394 */           headValue = Double.parseDouble(value);
/* 1341:     */         }
/* 1342:     */       }
/* 1343:     */     }
/* 1344:1401 */     ItemStack chest = player.getInventory().getChestplate();
/* 1345:     */     String value;
/* 1346:1403 */     if ((chest != null) && 
/* 1347:1404 */       (chest.hasItemMeta()) && 
/* 1348:1405 */       (chest.getItemMeta().hasLore()))
/* 1349:     */     {
/* 1350:1406 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1351:1407 */       for (String line : chestLore) {
/* 1352:1408 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1353:     */         {
/* 1354:1409 */           value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1355:1410 */           chestValue = Double.parseDouble(value);
/* 1356:     */         }
/* 1357:     */       }
/* 1358:     */     }
/* 1359:1417 */     ItemStack legs = player.getInventory().getLeggings();
/* 1360:     */     String value;
/* 1361:1419 */     if ((legs != null) && 
/* 1362:1420 */       (legs.hasItemMeta()) && 
/* 1363:1421 */       (legs.getItemMeta().hasLore()))
/* 1364:     */     {
/* 1365:1422 */       Object legsLore = legs.getItemMeta().getLore();
/* 1366:1423 */       for (String line : (List)legsLore) {
/* 1367:1424 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1368:     */         {
/* 1369:1425 */           value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1370:1426 */           legsValue = Double.parseDouble(value);
/* 1371:     */         }
/* 1372:     */       }
/* 1373:     */     }
/* 1374:1433 */     ItemStack boots = player.getInventory().getBoots();
/* 1375:1435 */     if ((boots != null) && 
/* 1376:1436 */       (boots.hasItemMeta()) && 
/* 1377:1437 */       (boots.getItemMeta().hasLore()))
/* 1378:     */     {
/* 1379:1438 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1380:1439 */       for (String line : bootsLore) {
/* 1381:1440 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1382:     */         {
/* 1383:1441 */           String value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1384:1442 */           bootsValue = Double.parseDouble(value);
/* 1385:     */         }
/* 1386:     */       }
/* 1387:     */     }
/* 1388:1449 */     return headValue + chestValue + legsValue + bootsValue;
/* 1389:     */   }
/* 1390:     */   
/* 1391:     */   public double getXPMultiplierGear(Player player)
/* 1392:     */   {
/* 1393:1453 */     this.xpmultiplier = ItemLoreStats.plugin.getConfig().getString("statNames.xpmultiplier").replaceAll("&([0-9a-f])", "");
/* 1394:     */     
/* 1395:1455 */     double headValue = 0.0D;
/* 1396:1456 */     double chestValue = 0.0D;
/* 1397:1457 */     double legsValue = 0.0D;
/* 1398:1458 */     double bootsValue = 0.0D;
/* 1399:     */     
/* 1400:1460 */     ItemStack head = player.getInventory().getHelmet();
/* 1401:     */     String value;
/* 1402:1462 */     if ((head != null) && 
/* 1403:1463 */       (head.hasItemMeta()) && 
/* 1404:1464 */       (head.getItemMeta().hasLore()))
/* 1405:     */     {
/* 1406:1465 */       List<String> headLore = head.getItemMeta().getLore();
/* 1407:1466 */       for (String line : headLore) {
/* 1408:1467 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1409:     */         {
/* 1410:1468 */           value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1411:1469 */           headValue = Double.parseDouble(value);
/* 1412:     */         }
/* 1413:     */       }
/* 1414:     */     }
/* 1415:1476 */     ItemStack chest = player.getInventory().getChestplate();
/* 1416:     */     String value;
/* 1417:1478 */     if ((chest != null) && 
/* 1418:1479 */       (chest.hasItemMeta()) && 
/* 1419:1480 */       (chest.getItemMeta().hasLore()))
/* 1420:     */     {
/* 1421:1481 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 1422:1482 */       for (String line : chestLore) {
/* 1423:1483 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1424:     */         {
/* 1425:1484 */           value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1426:1485 */           chestValue = Double.parseDouble(value);
/* 1427:     */         }
/* 1428:     */       }
/* 1429:     */     }
/* 1430:1492 */     ItemStack legs = player.getInventory().getLeggings();
/* 1431:     */     String value;
/* 1432:1494 */     if ((legs != null) && 
/* 1433:1495 */       (legs.hasItemMeta()) && 
/* 1434:1496 */       (legs.getItemMeta().hasLore()))
/* 1435:     */     {
/* 1436:1497 */       Object legsLore = legs.getItemMeta().getLore();
/* 1437:1498 */       for (String line : (List)legsLore) {
/* 1438:1499 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1439:     */         {
/* 1440:1500 */           value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1441:1501 */           legsValue = Double.parseDouble(value);
/* 1442:     */         }
/* 1443:     */       }
/* 1444:     */     }
/* 1445:1508 */     ItemStack boots = player.getInventory().getBoots();
/* 1446:1510 */     if ((boots != null) && 
/* 1447:1511 */       (boots.hasItemMeta()) && 
/* 1448:1512 */       (boots.getItemMeta().hasLore()))
/* 1449:     */     {
/* 1450:1513 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 1451:1514 */       for (String line : bootsLore) {
/* 1452:1515 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1453:     */         {
/* 1454:1516 */           String value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1455:1517 */           bootsValue = Double.parseDouble(value);
/* 1456:     */         }
/* 1457:     */       }
/* 1458:     */     }
/* 1459:1524 */     return headValue + chestValue + legsValue + bootsValue;
/* 1460:     */   }
/* 1461:     */   
/* 1462:     */   public double getArmourItemInHand(Player player)
/* 1463:     */   {
/* 1464:1533 */     this.armour = ItemLoreStats.plugin.getConfig().getString("statNames.armour").replaceAll("&([0-9a-f])", "");
/* 1465:     */     
/* 1466:1535 */     double storeLoreValues = 0.0D;
/* 1467:     */     
/* 1468:1537 */     ItemStack itemInHand = player.getItemInHand();
/* 1469:1539 */     if ((itemInHand != null) && 
/* 1470:1540 */       (itemInHand.hasItemMeta()) && 
/* 1471:1541 */       (itemInHand.getItemMeta().hasLore()))
/* 1472:     */     {
/* 1473:1542 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1474:1543 */       for (String line : itemInHandLore) {
/* 1475:1544 */         if (ChatColor.stripColor(line).startsWith(this.armour + ": "))
/* 1476:     */         {
/* 1477:1545 */           String value = ChatColor.stripColor(line).substring((this.armour + ": ").length()).replace("%", "").trim();
/* 1478:1546 */           storeLoreValues = Double.parseDouble(value);
/* 1479:1547 */           return storeLoreValues;
/* 1480:     */         }
/* 1481:     */       }
/* 1482:     */     }
/* 1483:1553 */     return storeLoreValues;
/* 1484:     */   }
/* 1485:     */   
/* 1486:     */   public double getDodgeItemInHand(Player player)
/* 1487:     */   {
/* 1488:1557 */     this.dodge = ItemLoreStats.plugin.getConfig().getString("statNames.dodge").replaceAll("&([0-9a-f])", "");
/* 1489:     */     
/* 1490:1559 */     double storeLoreValues = 0.0D;
/* 1491:     */     
/* 1492:1561 */     ItemStack itemInHand = player.getItemInHand();
/* 1493:1563 */     if ((itemInHand != null) && 
/* 1494:1564 */       (itemInHand.hasItemMeta()) && 
/* 1495:1565 */       (itemInHand.getItemMeta().hasLore()))
/* 1496:     */     {
/* 1497:1566 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1498:1567 */       for (String line : itemInHandLore) {
/* 1499:1568 */         if (ChatColor.stripColor(line).startsWith(this.dodge + ": "))
/* 1500:     */         {
/* 1501:1569 */           String value = ChatColor.stripColor(line).substring((this.dodge + ": ").length()).replace("%", "").trim();
/* 1502:1570 */           storeLoreValues = Double.parseDouble(value);
/* 1503:1571 */           return storeLoreValues;
/* 1504:     */         }
/* 1505:     */       }
/* 1506:     */     }
/* 1507:1577 */     return storeLoreValues;
/* 1508:     */   }
/* 1509:     */   
/* 1510:     */   public double getBlockItemInHand(Player player)
/* 1511:     */   {
/* 1512:1581 */     this.block = ItemLoreStats.plugin.getConfig().getString("statNames.block").replaceAll("&([0-9a-f])", "");
/* 1513:     */     
/* 1514:1583 */     double storeLoreValues = 0.0D;
/* 1515:     */     
/* 1516:1585 */     ItemStack itemInHand = player.getItemInHand();
/* 1517:1587 */     if ((itemInHand != null) && 
/* 1518:1588 */       (itemInHand.hasItemMeta()) && 
/* 1519:1589 */       (itemInHand.getItemMeta().hasLore()))
/* 1520:     */     {
/* 1521:1590 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1522:1591 */       for (String line : itemInHandLore) {
/* 1523:1592 */         if (ChatColor.stripColor(line).startsWith(this.block + ": "))
/* 1524:     */         {
/* 1525:1593 */           String value = ChatColor.stripColor(line).substring((this.block + ": ").length()).replace("%", "").trim();
/* 1526:1594 */           storeLoreValues = Double.parseDouble(value);
/* 1527:1595 */           return storeLoreValues;
/* 1528:     */         }
/* 1529:     */       }
/* 1530:     */     }
/* 1531:1601 */     return storeLoreValues;
/* 1532:     */   }
/* 1533:     */   
/* 1534:     */   public String getDamageItemInHand(Player player)
/* 1535:     */   {
/* 1536:1605 */     this.damage = ItemLoreStats.plugin.getConfig().getString("statNames.damage").replaceAll("&([0-9a-f])", "");
/* 1537:     */     
/* 1538:1607 */     double itemInHandMinValue = 0.0D;
/* 1539:1608 */     double itemInHandMaxValue = 0.0D;
/* 1540:     */     
/* 1541:1610 */     ItemStack itemInHand = player.getItemInHand();
/* 1542:1612 */     if ((itemInHand != null) && 
/* 1543:1613 */       (itemInHand.hasItemMeta()) && 
/* 1544:1614 */       (itemInHand.getItemMeta().hasLore()))
/* 1545:     */     {
/* 1546:1615 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1547:1616 */       for (String line : itemInHandLore) {
/* 1548:1617 */         if (ChatColor.stripColor(line).startsWith(this.damage + ": +"))
/* 1549:     */         {
/* 1550:1618 */           String value = ChatColor.stripColor(line).substring((this.damage + ": +").length()).trim();
/* 1551:1619 */           if (value.contains("-"))
/* 1552:     */           {
/* 1553:1620 */             itemInHandMinValue = Double.parseDouble(value.split("-")[0]);
/* 1554:1621 */             itemInHandMaxValue = Double.parseDouble(value.split("-")[1]);
/* 1555:     */           }
/* 1556:     */           else
/* 1557:     */           {
/* 1558:1623 */             itemInHandMinValue = Double.parseDouble(value);
/* 1559:1624 */             itemInHandMaxValue = Double.parseDouble(value);
/* 1560:     */           }
/* 1561:     */         }
/* 1562:     */       }
/* 1563:     */     }
/* 1564:1631 */     return itemInHandMinValue + "-" + itemInHandMaxValue;
/* 1565:     */   }
/* 1566:     */   
/* 1567:     */   public double getCritChanceItemInHand(Player player)
/* 1568:     */   {
/* 1569:1635 */     this.critChance = ItemLoreStats.plugin.getConfig().getString("statNames.critChance").replaceAll("&([0-9a-f])", "");
/* 1570:     */     
/* 1571:1637 */     double storeLoreValues = 0.0D;
/* 1572:     */     
/* 1573:1639 */     ItemStack itemInHand = player.getItemInHand();
/* 1574:1641 */     if ((itemInHand != null) && 
/* 1575:1642 */       (itemInHand.hasItemMeta()) && 
/* 1576:1643 */       (itemInHand.getItemMeta().hasLore()))
/* 1577:     */     {
/* 1578:1644 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1579:1645 */       for (String line : itemInHandLore) {
/* 1580:1646 */         if (ChatColor.stripColor(line).startsWith(this.critChance + ": "))
/* 1581:     */         {
/* 1582:1647 */           String value = ChatColor.stripColor(line).substring((this.critChance + ": ").length()).replace("%", "").trim();
/* 1583:1648 */           storeLoreValues = Double.parseDouble(value);
/* 1584:1649 */           return storeLoreValues;
/* 1585:     */         }
/* 1586:     */       }
/* 1587:     */     }
/* 1588:1655 */     return storeLoreValues;
/* 1589:     */   }
/* 1590:     */   
/* 1591:     */   public double getCritDamageItemInHand(Player player)
/* 1592:     */   {
/* 1593:1659 */     this.critDamage = ItemLoreStats.plugin.getConfig().getString("statNames.critDamage").replaceAll("&([0-9a-f])", "");
/* 1594:     */     
/* 1595:1661 */     double storeLoreValues = 0.0D;
/* 1596:     */     
/* 1597:1663 */     ItemStack itemInHand = player.getItemInHand();
/* 1598:1665 */     if ((itemInHand != null) && 
/* 1599:1666 */       (itemInHand.hasItemMeta()) && 
/* 1600:1667 */       (itemInHand.getItemMeta().hasLore()))
/* 1601:     */     {
/* 1602:1668 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1603:1669 */       for (String line : itemInHandLore) {
/* 1604:1670 */         if (ChatColor.stripColor(line).startsWith(this.critDamage + ": "))
/* 1605:     */         {
/* 1606:1671 */           String value = ChatColor.stripColor(line).substring((this.critDamage + ": ").length()).replace("%", "").trim();
/* 1607:1672 */           storeLoreValues = Double.parseDouble(value);
/* 1608:1673 */           return storeLoreValues;
/* 1609:     */         }
/* 1610:     */       }
/* 1611:     */     }
/* 1612:1679 */     return storeLoreValues;
/* 1613:     */   }
/* 1614:     */   
/* 1615:     */   public double getHealthItemInHand(Player player)
/* 1616:     */   {
/* 1617:1683 */     this.health = ItemLoreStats.plugin.getConfig().getString("statNames.health").replaceAll("&([0-9a-f])", "");
/* 1618:     */     
/* 1619:1685 */     double storeLoreValues = 0.0D;
/* 1620:     */     
/* 1621:1687 */     ItemStack itemInHand = player.getItemInHand();
/* 1622:1689 */     if ((itemInHand != null) && 
/* 1623:1690 */       (itemInHand.hasItemMeta()) && 
/* 1624:1691 */       (itemInHand.getItemMeta().hasLore()))
/* 1625:     */     {
/* 1626:1692 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1627:1693 */       for (String line : itemInHandLore) {
/* 1628:1694 */         if (ChatColor.stripColor(line).startsWith(this.health + ": +"))
/* 1629:     */         {
/* 1630:1695 */           String value = ChatColor.stripColor(line).substring((this.health + ": +").length()).trim();
/* 1631:1696 */           storeLoreValues = Double.parseDouble(value);
/* 1632:1697 */           return storeLoreValues;
/* 1633:     */         }
/* 1634:     */       }
/* 1635:     */     }
/* 1636:1703 */     return storeLoreValues;
/* 1637:     */   }
/* 1638:     */   
/* 1639:     */   public double getHealthRegenItemInHand(Player player)
/* 1640:     */   {
/* 1641:1707 */     this.healthRegen = ItemLoreStats.plugin.getConfig().getString("statNames.healthregen").replaceAll("&([0-9a-f])", "");
/* 1642:     */     
/* 1643:1709 */     double storeLoreValues = 0.0D;
/* 1644:     */     
/* 1645:1711 */     ItemStack itemInHand = player.getItemInHand();
/* 1646:1713 */     if ((itemInHand != null) && 
/* 1647:1714 */       (itemInHand.hasItemMeta()) && 
/* 1648:1715 */       (itemInHand.getItemMeta().hasLore()))
/* 1649:     */     {
/* 1650:1716 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1651:1717 */       for (String line : itemInHandLore) {
/* 1652:1718 */         if (ChatColor.stripColor(line).startsWith(this.healthRegen + ": "))
/* 1653:     */         {
/* 1654:1719 */           String value = ChatColor.stripColor(line).substring((this.healthRegen + ": ").length()).replace("%", "").trim();
/* 1655:1720 */           storeLoreValues = Double.parseDouble(value);
/* 1656:1721 */           return storeLoreValues;
/* 1657:     */         }
/* 1658:     */       }
/* 1659:     */     }
/* 1660:1727 */     return storeLoreValues;
/* 1661:     */   }
/* 1662:     */   
/* 1663:     */   public double getLifeStealItemInHand(Player player)
/* 1664:     */   {
/* 1665:1731 */     this.lifeSteal = ItemLoreStats.plugin.getConfig().getString("statNames.lifesteal").replaceAll("&([0-9a-f])", "");
/* 1666:     */     
/* 1667:1733 */     double storeLoreValues = 0.0D;
/* 1668:     */     
/* 1669:1735 */     ItemStack itemInHand = player.getItemInHand();
/* 1670:1737 */     if ((itemInHand != null) && 
/* 1671:1738 */       (itemInHand.hasItemMeta()) && 
/* 1672:1739 */       (itemInHand.getItemMeta().hasLore()))
/* 1673:     */     {
/* 1674:1740 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1675:1741 */       for (String line : itemInHandLore) {
/* 1676:1742 */         if (ChatColor.stripColor(line).startsWith(this.lifeSteal + ": "))
/* 1677:     */         {
/* 1678:1743 */           String value = ChatColor.stripColor(line).substring((this.lifeSteal + ": ").length()).replace("%", "").trim();
/* 1679:1744 */           storeLoreValues = Double.parseDouble(value);
/* 1680:1745 */           return storeLoreValues;
/* 1681:     */         }
/* 1682:     */       }
/* 1683:     */     }
/* 1684:1751 */     return storeLoreValues;
/* 1685:     */   }
/* 1686:     */   
/* 1687:     */   public double getLifeStealHealItemInHand(Player player)
/* 1688:     */   {
/* 1689:1755 */     this.lifeStealHeal = ItemLoreStats.plugin.getConfig().getString("statNames.lifestealheal").replaceAll("&([0-9a-f])", "");
/* 1690:     */     
/* 1691:1757 */     double storeLoreValues = 0.0D;
/* 1692:     */     
/* 1693:1759 */     ItemStack itemInHand = player.getItemInHand();
/* 1694:1761 */     if ((itemInHand != null) && 
/* 1695:1762 */       (itemInHand.hasItemMeta()) && 
/* 1696:1763 */       (itemInHand.getItemMeta().hasLore()))
/* 1697:     */     {
/* 1698:1764 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1699:1765 */       for (String line : itemInHandLore) {
/* 1700:1766 */         if (ChatColor.stripColor(line).startsWith(this.lifeStealHeal + ": "))
/* 1701:     */         {
/* 1702:1767 */           String value = ChatColor.stripColor(line).substring((this.lifeStealHeal + ": ").length()).replace("%", "").trim();
/* 1703:1768 */           storeLoreValues = Double.parseDouble(value);
/* 1704:1769 */           return storeLoreValues;
/* 1705:     */         }
/* 1706:     */       }
/* 1707:     */     }
/* 1708:1775 */     return storeLoreValues;
/* 1709:     */   }
/* 1710:     */   
/* 1711:     */   public double getReflectItemInHand(Player player)
/* 1712:     */   {
/* 1713:1779 */     this.reflect = ItemLoreStats.plugin.getConfig().getString("statNames.reflect").replaceAll("&([0-9a-f])", "");
/* 1714:     */     
/* 1715:1781 */     double storeLoreValues = 0.0D;
/* 1716:     */     
/* 1717:1783 */     ItemStack itemInHand = player.getItemInHand();
/* 1718:1785 */     if ((itemInHand != null) && 
/* 1719:1786 */       (itemInHand.hasItemMeta()) && 
/* 1720:1787 */       (itemInHand.getItemMeta().hasLore()))
/* 1721:     */     {
/* 1722:1788 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1723:1789 */       for (String line : itemInHandLore) {
/* 1724:1790 */         if (ChatColor.stripColor(line).startsWith(this.reflect + ": "))
/* 1725:     */         {
/* 1726:1791 */           String value = ChatColor.stripColor(line).substring((this.reflect + ": ").length()).replace("%", "").trim();
/* 1727:1792 */           storeLoreValues = Double.parseDouble(value);
/* 1728:1793 */           return storeLoreValues;
/* 1729:     */         }
/* 1730:     */       }
/* 1731:     */     }
/* 1732:1799 */     return storeLoreValues;
/* 1733:     */   }
/* 1734:     */   
/* 1735:     */   public double getIceItemInHand(Player player)
/* 1736:     */   {
/* 1737:1803 */     this.ice = ItemLoreStats.plugin.getConfig().getString("statNames.ice").replaceAll("&([0-9a-f])", "");
/* 1738:     */     
/* 1739:1805 */     double storeLoreValues = 0.0D;
/* 1740:     */     
/* 1741:1807 */     ItemStack itemInHand = player.getItemInHand();
/* 1742:1809 */     if ((itemInHand != null) && 
/* 1743:1810 */       (itemInHand.hasItemMeta()) && 
/* 1744:1811 */       (itemInHand.getItemMeta().hasLore()))
/* 1745:     */     {
/* 1746:1812 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1747:1813 */       for (String line : itemInHandLore) {
/* 1748:1814 */         if (ChatColor.stripColor(line).startsWith(this.ice + ": "))
/* 1749:     */         {
/* 1750:1815 */           String value = ChatColor.stripColor(line).substring((this.ice + ": ").length()).replace("%", "").trim();
/* 1751:1816 */           storeLoreValues = Double.parseDouble(value);
/* 1752:1817 */           return storeLoreValues;
/* 1753:     */         }
/* 1754:     */       }
/* 1755:     */     }
/* 1756:1823 */     return storeLoreValues;
/* 1757:     */   }
/* 1758:     */   
/* 1759:     */   public double getFireItemInHand(Player player)
/* 1760:     */   {
/* 1761:1827 */     this.fire = ItemLoreStats.plugin.getConfig().getString("statNames.fire").replaceAll("&([0-9a-f])", "");
/* 1762:     */     
/* 1763:1829 */     double storeLoreValues = 0.0D;
/* 1764:     */     
/* 1765:1831 */     ItemStack itemInHand = player.getItemInHand();
/* 1766:1833 */     if ((itemInHand != null) && 
/* 1767:1834 */       (itemInHand.hasItemMeta()) && 
/* 1768:1835 */       (itemInHand.getItemMeta().hasLore()))
/* 1769:     */     {
/* 1770:1836 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1771:1837 */       for (String line : itemInHandLore) {
/* 1772:1838 */         if (ChatColor.stripColor(line).startsWith(this.fire + ": "))
/* 1773:     */         {
/* 1774:1839 */           String value = ChatColor.stripColor(line).substring((this.fire + ": ").length()).replace("%", "").trim();
/* 1775:1840 */           storeLoreValues = Double.parseDouble(value);
/* 1776:1841 */           return storeLoreValues;
/* 1777:     */         }
/* 1778:     */       }
/* 1779:     */     }
/* 1780:1847 */     return storeLoreValues;
/* 1781:     */   }
/* 1782:     */   
/* 1783:     */   public double getPoisonItemInHand(Player player)
/* 1784:     */   {
/* 1785:1851 */     this.poison = ItemLoreStats.plugin.getConfig().getString("statNames.poison").replaceAll("&([0-9a-f])", "");
/* 1786:     */     
/* 1787:1853 */     double storeLoreValues = 0.0D;
/* 1788:     */     
/* 1789:1855 */     ItemStack itemInHand = player.getItemInHand();
/* 1790:1857 */     if ((itemInHand != null) && 
/* 1791:1858 */       (itemInHand.hasItemMeta()) && 
/* 1792:1859 */       (itemInHand.getItemMeta().hasLore()))
/* 1793:     */     {
/* 1794:1860 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1795:1861 */       for (String line : itemInHandLore) {
/* 1796:1862 */         if (ChatColor.stripColor(line).startsWith(this.poison + ": "))
/* 1797:     */         {
/* 1798:1863 */           String value = ChatColor.stripColor(line).substring((this.poison + ": ").length()).replace("%", "").trim();
/* 1799:1864 */           storeLoreValues = Double.parseDouble(value);
/* 1800:1865 */           return storeLoreValues;
/* 1801:     */         }
/* 1802:     */       }
/* 1803:     */     }
/* 1804:1871 */     return storeLoreValues;
/* 1805:     */   }
/* 1806:     */   
/* 1807:     */   public double getWitherItemInHand(Player player)
/* 1808:     */   {
/* 1809:1875 */     this.wither = ItemLoreStats.plugin.getConfig().getString("statNames.wither").replaceAll("&([0-9a-f])", "");
/* 1810:     */     
/* 1811:1877 */     double storeLoreValues = 0.0D;
/* 1812:     */     
/* 1813:1879 */     ItemStack itemInHand = player.getItemInHand();
/* 1814:1881 */     if ((itemInHand != null) && 
/* 1815:1882 */       (itemInHand.hasItemMeta()) && 
/* 1816:1883 */       (itemInHand.getItemMeta().hasLore()))
/* 1817:     */     {
/* 1818:1884 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1819:1885 */       for (String line : itemInHandLore) {
/* 1820:1886 */         if (ChatColor.stripColor(line).startsWith(this.wither + ": "))
/* 1821:     */         {
/* 1822:1887 */           String value = ChatColor.stripColor(line).substring((this.wither + ": ").length()).replace("%", "").trim();
/* 1823:1888 */           storeLoreValues = Double.parseDouble(value);
/* 1824:1889 */           return storeLoreValues;
/* 1825:     */         }
/* 1826:     */       }
/* 1827:     */     }
/* 1828:1895 */     return storeLoreValues;
/* 1829:     */   }
/* 1830:     */   
/* 1831:     */   public double getHarmingItemInHand(Player player)
/* 1832:     */   {
/* 1833:1899 */     this.harming = ItemLoreStats.plugin.getConfig().getString("statNames.harming").replaceAll("&([0-9a-f])", "");
/* 1834:     */     
/* 1835:1901 */     double storeLoreValues = 0.0D;
/* 1836:     */     
/* 1837:1903 */     ItemStack itemInHand = player.getItemInHand();
/* 1838:1905 */     if ((itemInHand != null) && 
/* 1839:1906 */       (itemInHand.hasItemMeta()) && 
/* 1840:1907 */       (itemInHand.getItemMeta().hasLore()))
/* 1841:     */     {
/* 1842:1908 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1843:1909 */       for (String line : itemInHandLore) {
/* 1844:1910 */         if (ChatColor.stripColor(line).startsWith(this.harming + ": "))
/* 1845:     */         {
/* 1846:1911 */           String value = ChatColor.stripColor(line).substring((this.harming + ": ").length()).replace("%", "").trim();
/* 1847:1912 */           storeLoreValues = Double.parseDouble(value);
/* 1848:1913 */           return storeLoreValues;
/* 1849:     */         }
/* 1850:     */       }
/* 1851:     */     }
/* 1852:1919 */     return storeLoreValues;
/* 1853:     */   }
/* 1854:     */   
/* 1855:     */   public double getBlindItemInHand(Player player)
/* 1856:     */   {
/* 1857:1923 */     this.blind = ItemLoreStats.plugin.getConfig().getString("statNames.blind").replaceAll("&([0-9a-f])", "");
/* 1858:     */     
/* 1859:1925 */     double storeLoreValues = 0.0D;
/* 1860:     */     
/* 1861:1927 */     ItemStack itemInHand = player.getItemInHand();
/* 1862:1929 */     if ((itemInHand != null) && 
/* 1863:1930 */       (itemInHand.hasItemMeta()) && 
/* 1864:1931 */       (itemInHand.getItemMeta().hasLore()))
/* 1865:     */     {
/* 1866:1932 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1867:1933 */       for (String line : itemInHandLore) {
/* 1868:1934 */         if (ChatColor.stripColor(line).startsWith(this.blind + ": "))
/* 1869:     */         {
/* 1870:1935 */           String value = ChatColor.stripColor(line).substring((this.blind + ": ").length()).replace("%", "").trim();
/* 1871:1936 */           storeLoreValues = Double.parseDouble(value);
/* 1872:1937 */           return storeLoreValues;
/* 1873:     */         }
/* 1874:     */       }
/* 1875:     */     }
/* 1876:1943 */     return storeLoreValues;
/* 1877:     */   }
/* 1878:     */   
/* 1879:     */   public double getMovementSpeedItemInHand(Player player)
/* 1880:     */   {
/* 1881:1947 */     this.movementspeed = ItemLoreStats.plugin.getConfig().getString("statNames.movementspeed").replaceAll("&([0-9a-f])", "");
/* 1882:     */     
/* 1883:1949 */     double storeLoreValues = 0.0D;
/* 1884:     */     
/* 1885:1951 */     ItemStack itemInHand = player.getItemInHand();
/* 1886:1953 */     if ((itemInHand != null) && 
/* 1887:1954 */       (itemInHand.hasItemMeta()) && 
/* 1888:1955 */       (itemInHand.getItemMeta().hasLore()))
/* 1889:     */     {
/* 1890:1956 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1891:1957 */       for (String line : itemInHandLore) {
/* 1892:1958 */         if (ChatColor.stripColor(line).startsWith(this.movementspeed + ": "))
/* 1893:     */         {
/* 1894:1959 */           String value = ChatColor.stripColor(line).substring((this.movementspeed + ": ").length()).replace("%", "").trim();
/* 1895:1960 */           storeLoreValues = Double.parseDouble(value);
/* 1896:1961 */           return storeLoreValues;
/* 1897:     */         }
/* 1898:     */       }
/* 1899:     */     }
/* 1900:1967 */     return storeLoreValues;
/* 1901:     */   }
/* 1902:     */   
/* 1903:     */   public double getXPMultiplierItemInHand(Player player)
/* 1904:     */   {
/* 1905:1971 */     this.xpmultiplier = ItemLoreStats.plugin.getConfig().getString("statNames.xpmultiplier").replaceAll("&([0-9a-f])", "");
/* 1906:     */     
/* 1907:1973 */     double storeLoreValues = 0.0D;
/* 1908:     */     
/* 1909:1975 */     ItemStack itemInHand = player.getItemInHand();
/* 1910:1977 */     if ((itemInHand != null) && 
/* 1911:1978 */       (itemInHand.hasItemMeta()) && 
/* 1912:1979 */       (itemInHand.getItemMeta().hasLore()))
/* 1913:     */     {
/* 1914:1980 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1915:1981 */       for (String line : itemInHandLore) {
/* 1916:1982 */         if (ChatColor.stripColor(line).startsWith(this.xpmultiplier + ": "))
/* 1917:     */         {
/* 1918:1983 */           String value = ChatColor.stripColor(line).substring((this.xpmultiplier + ": ").length()).replace("%", "").trim();
/* 1919:1984 */           storeLoreValues = Double.parseDouble(value);
/* 1920:1985 */           return storeLoreValues;
/* 1921:     */         }
/* 1922:     */       }
/* 1923:     */     }
/* 1924:1991 */     return storeLoreValues;
/* 1925:     */   }
/* 1926:     */   
/* 1927:     */   public String getPvPDamageModifierItemInHand(Player player)
/* 1928:     */   {
/* 1929:1995 */     this.pvpdamage = ItemLoreStats.plugin.getConfig().getString("statNames.pvpdamage").replaceAll("&([0-9a-f])", "");
/* 1930:     */     
/* 1931:1997 */     ItemStack itemInHand = player.getItemInHand();
/* 1932:1999 */     if ((itemInHand != null) && 
/* 1933:2000 */       (itemInHand.hasItemMeta()) && 
/* 1934:2001 */       (itemInHand.getItemMeta().hasLore()))
/* 1935:     */     {
/* 1936:2002 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1937:2003 */       for (String line : itemInHandLore) {
/* 1938:2004 */         if (ChatColor.stripColor(line).startsWith(this.pvpdamage + ": "))
/* 1939:     */         {
/* 1940:2005 */           String value = ChatColor.stripColor(line).substring((this.pvpdamage + ": ").length()).trim();
/* 1941:2006 */           return value;
/* 1942:     */         }
/* 1943:     */       }
/* 1944:     */     }
/* 1945:2012 */     return "0.0";
/* 1946:     */   }
/* 1947:     */   
/* 1948:     */   public String getPvEDamageModifierItemInHand(Player player)
/* 1949:     */   {
/* 1950:2016 */     this.pvedamage = ItemLoreStats.plugin.getConfig().getString("statNames.pvedamage").replaceAll("&([0-9a-f])", "");
/* 1951:     */     
/* 1952:2018 */     ItemStack itemInHand = player.getItemInHand();
/* 1953:2020 */     if ((itemInHand != null) && 
/* 1954:2021 */       (itemInHand.hasItemMeta()) && 
/* 1955:2022 */       (itemInHand.getItemMeta().hasLore()))
/* 1956:     */     {
/* 1957:2023 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 1958:2024 */       for (String line : itemInHandLore) {
/* 1959:2025 */         if (ChatColor.stripColor(line).startsWith(this.pvedamage + ": "))
/* 1960:     */         {
/* 1961:2026 */           String value = ChatColor.stripColor(line).substring((this.pvedamage + ": ").length()).trim();
/* 1962:2027 */           return value;
/* 1963:     */         }
/* 1964:     */       }
/* 1965:     */     }
/* 1966:2033 */     return "0.0";
/* 1967:     */   }
/* 1968:     */   
/* 1969:     */   public int getXPLevelRequirementHead(Player player)
/* 1970:     */   {
/* 1971:2041 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 1972:     */     
/* 1973:2043 */     int storeLoreValues = 0;
/* 1974:     */     
/* 1975:2045 */     ItemStack item = player.getInventory().getHelmet();
/* 1976:2047 */     if ((item != null) && 
/* 1977:2048 */       (item.hasItemMeta()) && 
/* 1978:2049 */       (item.getItemMeta().hasLore()))
/* 1979:     */     {
/* 1980:2050 */       List<String> itemLore = item.getItemMeta().getLore();
/* 1981:2051 */       for (String line : itemLore) {
/* 1982:2052 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 1983:     */         {
/* 1984:2053 */           String value = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 1985:2054 */           storeLoreValues = Integer.parseInt(value);
/* 1986:2055 */           return storeLoreValues;
/* 1987:     */         }
/* 1988:     */       }
/* 1989:     */     }
/* 1990:2061 */     return storeLoreValues;
/* 1991:     */   }
/* 1992:     */   
/* 1993:     */   public int getXPLevelRequirementChest(Player player)
/* 1994:     */   {
/* 1995:2065 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 1996:     */     
/* 1997:2067 */     int storeLoreValues = 0;
/* 1998:     */     
/* 1999:2069 */     ItemStack item = player.getInventory().getChestplate();
/* 2000:2071 */     if ((item != null) && 
/* 2001:2072 */       (item.hasItemMeta()) && 
/* 2002:2073 */       (item.getItemMeta().hasLore()))
/* 2003:     */     {
/* 2004:2074 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2005:2075 */       for (String line : itemLore) {
/* 2006:2076 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 2007:     */         {
/* 2008:2077 */           String value = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 2009:2078 */           storeLoreValues = Integer.parseInt(value);
/* 2010:2079 */           return storeLoreValues;
/* 2011:     */         }
/* 2012:     */       }
/* 2013:     */     }
/* 2014:2085 */     return storeLoreValues;
/* 2015:     */   }
/* 2016:     */   
/* 2017:     */   public int getXPLevelRequirementLegs(Player player)
/* 2018:     */   {
/* 2019:2089 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 2020:     */     
/* 2021:2091 */     int storeLoreValues = 0;
/* 2022:     */     
/* 2023:2093 */     ItemStack item = player.getInventory().getLeggings();
/* 2024:2095 */     if ((item != null) && 
/* 2025:2096 */       (item.hasItemMeta()) && 
/* 2026:2097 */       (item.getItemMeta().hasLore()))
/* 2027:     */     {
/* 2028:2098 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2029:2099 */       for (String line : itemLore) {
/* 2030:2100 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 2031:     */         {
/* 2032:2101 */           String value = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 2033:2102 */           storeLoreValues = Integer.parseInt(value);
/* 2034:2103 */           return storeLoreValues;
/* 2035:     */         }
/* 2036:     */       }
/* 2037:     */     }
/* 2038:2109 */     return storeLoreValues;
/* 2039:     */   }
/* 2040:     */   
/* 2041:     */   public int getXPLevelRequirementBoots(Player player)
/* 2042:     */   {
/* 2043:2113 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 2044:     */     
/* 2045:2115 */     int storeLoreValues = 0;
/* 2046:     */     
/* 2047:2117 */     ItemStack item = player.getInventory().getBoots();
/* 2048:2119 */     if ((item != null) && 
/* 2049:2120 */       (item.hasItemMeta()) && 
/* 2050:2121 */       (item.getItemMeta().hasLore()))
/* 2051:     */     {
/* 2052:2122 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2053:2123 */       for (String line : itemLore) {
/* 2054:2124 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 2055:     */         {
/* 2056:2125 */           String value = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 2057:2126 */           storeLoreValues = Integer.parseInt(value);
/* 2058:2127 */           return storeLoreValues;
/* 2059:     */         }
/* 2060:     */       }
/* 2061:     */     }
/* 2062:2133 */     return storeLoreValues;
/* 2063:     */   }
/* 2064:     */   
/* 2065:     */   public int getXPLevelRequirementItemInHand(Player player)
/* 2066:     */   {
/* 2067:2137 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 2068:     */     
/* 2069:2139 */     int storeLoreValues = 0;
/* 2070:     */     
/* 2071:2141 */     ItemStack itemInHand = player.getItemInHand();
/* 2072:2143 */     if ((itemInHand != null) && 
/* 2073:2144 */       (itemInHand.hasItemMeta()) && 
/* 2074:2145 */       (itemInHand.getItemMeta().hasLore()))
/* 2075:     */     {
/* 2076:2146 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 2077:2147 */       for (String line : itemInHandLore) {
/* 2078:2148 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 2079:     */         {
/* 2080:2149 */           String xpLevelValue = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 2081:     */           try
/* 2082:     */           {
/* 2083:2151 */             return Integer.parseInt(xpLevelValue);
/* 2084:     */           }
/* 2085:     */           catch (NumberFormatException localNumberFormatException) {}
/* 2086:     */         }
/* 2087:     */       }
/* 2088:     */     }
/* 2089:2161 */     return storeLoreValues;
/* 2090:     */   }
/* 2091:     */   
/* 2092:     */   public String getSetBonusHead(Player player)
/* 2093:     */   {
/* 2094:2167 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 2095:2168 */     String headKey = "";
/* 2096:     */     
/* 2097:2170 */     ItemStack head = player.getInventory().getHelmet();
/* 2098:2172 */     if ((head != null) && 
/* 2099:2173 */       (head.hasItemMeta()) && 
/* 2100:2174 */       (head.getItemMeta().hasLore()))
/* 2101:     */     {
/* 2102:2175 */       List<String> headLore = head.getItemMeta().getLore();
/* 2103:2176 */       for (String line : headLore) {
/* 2104:2177 */         if (line.contains(this.setbonus))
/* 2105:     */         {
/* 2106:2178 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 2107:2179 */           headKey = value;
/* 2108:2180 */           return headKey;
/* 2109:     */         }
/* 2110:     */       }
/* 2111:     */     }
/* 2112:2186 */     return headKey;
/* 2113:     */   }
/* 2114:     */   
/* 2115:     */   public String getSetBonusChest(Player player)
/* 2116:     */   {
/* 2117:2189 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 2118:2190 */     String chestKey = "";
/* 2119:     */     
/* 2120:2192 */     ItemStack chest = player.getInventory().getChestplate();
/* 2121:2194 */     if ((chest != null) && 
/* 2122:2195 */       (chest.hasItemMeta()) && 
/* 2123:2196 */       (chest.getItemMeta().hasLore()))
/* 2124:     */     {
/* 2125:2197 */       List<String> chestLore = chest.getItemMeta().getLore();
/* 2126:2198 */       for (String line : chestLore) {
/* 2127:2199 */         if (line.contains(this.setbonus))
/* 2128:     */         {
/* 2129:2200 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 2130:2201 */           chestKey = value;
/* 2131:2202 */           return chestKey;
/* 2132:     */         }
/* 2133:     */       }
/* 2134:     */     }
/* 2135:2208 */     return chestKey;
/* 2136:     */   }
/* 2137:     */   
/* 2138:     */   public String getSetBonusLegs(Player player)
/* 2139:     */   {
/* 2140:2211 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 2141:2212 */     String legsKey = "";
/* 2142:     */     
/* 2143:2214 */     ItemStack legs = player.getInventory().getLeggings();
/* 2144:2216 */     if ((legs != null) && 
/* 2145:2217 */       (legs.hasItemMeta()) && 
/* 2146:2218 */       (legs.getItemMeta().hasLore()))
/* 2147:     */     {
/* 2148:2219 */       List<String> legsLore = legs.getItemMeta().getLore();
/* 2149:2220 */       for (String line : legsLore) {
/* 2150:2221 */         if (line.contains(this.setbonus))
/* 2151:     */         {
/* 2152:2222 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 2153:2223 */           legsKey = value;
/* 2154:2224 */           return legsKey;
/* 2155:     */         }
/* 2156:     */       }
/* 2157:     */     }
/* 2158:2230 */     return legsKey;
/* 2159:     */   }
/* 2160:     */   
/* 2161:     */   public String getSetBonusBoots(Player player)
/* 2162:     */   {
/* 2163:2233 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 2164:2234 */     String bootsKey = "";
/* 2165:     */     
/* 2166:2236 */     ItemStack boots = player.getInventory().getBoots();
/* 2167:2238 */     if ((boots != null) && 
/* 2168:2239 */       (boots.hasItemMeta()) && 
/* 2169:2240 */       (boots.getItemMeta().hasLore()))
/* 2170:     */     {
/* 2171:2241 */       List<String> bootsLore = boots.getItemMeta().getLore();
/* 2172:2242 */       for (String line : bootsLore) {
/* 2173:2243 */         if (line.contains(this.setbonus))
/* 2174:     */         {
/* 2175:2244 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 2176:2245 */           bootsKey = value;
/* 2177:2246 */           return bootsKey;
/* 2178:     */         }
/* 2179:     */       }
/* 2180:     */     }
/* 2181:2252 */     return bootsKey;
/* 2182:     */   }
/* 2183:     */   
/* 2184:     */   public String getSetBonusItemInHand(Player player)
/* 2185:     */   {
/* 2186:2255 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 2187:2256 */     String weaponKey = "";
/* 2188:     */     
/* 2189:2258 */     ItemStack itemInHand = player.getItemInHand();
/* 2190:2260 */     if ((itemInHand != null) && 
/* 2191:2261 */       (itemInHand.hasItemMeta()) && 
/* 2192:2262 */       (ItemLoreStats.plugin.isTool(itemInHand.getType())) && 
/* 2193:2263 */       (itemInHand.getItemMeta().hasLore()))
/* 2194:     */     {
/* 2195:2264 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 2196:2265 */       for (String line : itemInHandLore) {
/* 2197:2266 */         if (line.contains(this.setbonus))
/* 2198:     */         {
/* 2199:2267 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 2200:2268 */           weaponKey = value;
/* 2201:2269 */           return weaponKey;
/* 2202:     */         }
/* 2203:     */       }
/* 2204:     */     }
/* 2205:2276 */     return weaponKey;
/* 2206:     */   }
/* 2207:     */   
/* 2208:     */   public String getSoulboundNameHead(Player player)
/* 2209:     */   {
/* 2210:2284 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 2211:     */     
/* 2212:2286 */     String storeLoreValues = "";
/* 2213:     */     
/* 2214:2288 */     ItemStack item = player.getInventory().getHelmet();
/* 2215:2290 */     if ((item != null) && 
/* 2216:2291 */       (item.hasItemMeta()) && 
/* 2217:2292 */       (item.getItemMeta().hasLore()))
/* 2218:     */     {
/* 2219:2293 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2220:2294 */       for (String line : itemLore) {
/* 2221:2295 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 2222:     */         {
/* 2223:2296 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 2224:2297 */           storeLoreValues = value;
/* 2225:2298 */           return storeLoreValues;
/* 2226:     */         }
/* 2227:     */       }
/* 2228:     */     }
/* 2229:2304 */     return null;
/* 2230:     */   }
/* 2231:     */   
/* 2232:     */   public String getSoulboundNameChest(Player player)
/* 2233:     */   {
/* 2234:2308 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 2235:     */     
/* 2236:2310 */     String storeLoreValues = "";
/* 2237:     */     
/* 2238:2312 */     ItemStack item = player.getInventory().getChestplate();
/* 2239:2314 */     if ((item != null) && 
/* 2240:2315 */       (item.hasItemMeta()) && 
/* 2241:2316 */       (item.getItemMeta().hasLore()))
/* 2242:     */     {
/* 2243:2317 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2244:2318 */       for (String line : itemLore) {
/* 2245:2319 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 2246:     */         {
/* 2247:2320 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 2248:2321 */           storeLoreValues = value;
/* 2249:2322 */           return storeLoreValues;
/* 2250:     */         }
/* 2251:     */       }
/* 2252:     */     }
/* 2253:2328 */     return null;
/* 2254:     */   }
/* 2255:     */   
/* 2256:     */   public String getSoulboundNameLegs(Player player)
/* 2257:     */   {
/* 2258:2332 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 2259:     */     
/* 2260:2334 */     String storeLoreValues = "";
/* 2261:     */     
/* 2262:2336 */     ItemStack item = player.getInventory().getLeggings();
/* 2263:2338 */     if ((item != null) && 
/* 2264:2339 */       (item.hasItemMeta()) && 
/* 2265:2340 */       (item.getItemMeta().hasLore()))
/* 2266:     */     {
/* 2267:2341 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2268:2342 */       for (String line : itemLore) {
/* 2269:2343 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 2270:     */         {
/* 2271:2344 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 2272:2345 */           storeLoreValues = value;
/* 2273:2346 */           return storeLoreValues;
/* 2274:     */         }
/* 2275:     */       }
/* 2276:     */     }
/* 2277:2352 */     return null;
/* 2278:     */   }
/* 2279:     */   
/* 2280:     */   public String getSoulboundNameBoots(Player player)
/* 2281:     */   {
/* 2282:2356 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 2283:     */     
/* 2284:2358 */     String storeLoreValues = "";
/* 2285:     */     
/* 2286:2360 */     ItemStack item = player.getInventory().getBoots();
/* 2287:2362 */     if ((item != null) && 
/* 2288:2363 */       (item.hasItemMeta()) && 
/* 2289:2364 */       (item.getItemMeta().hasLore()))
/* 2290:     */     {
/* 2291:2365 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2292:2366 */       for (String line : itemLore) {
/* 2293:2367 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 2294:     */         {
/* 2295:2368 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 2296:2369 */           storeLoreValues = value;
/* 2297:2370 */           return storeLoreValues;
/* 2298:     */         }
/* 2299:     */       }
/* 2300:     */     }
/* 2301:2376 */     return null;
/* 2302:     */   }
/* 2303:     */   
/* 2304:     */   public String getSoulboundNameItemInHand(Player player)
/* 2305:     */   {
/* 2306:2380 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 2307:     */     
/* 2308:2382 */     String storeLoreValues = "";
/* 2309:     */     
/* 2310:2384 */     ItemStack item = player.getItemInHand();
/* 2311:2386 */     if ((item != null) && 
/* 2312:2387 */       (item.hasItemMeta()) && 
/* 2313:2388 */       (item.getItemMeta().hasLore()))
/* 2314:     */     {
/* 2315:2389 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2316:2390 */       for (String line : itemLore) {
/* 2317:2391 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 2318:     */         {
/* 2319:2392 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 2320:2393 */           storeLoreValues = value;
/* 2321:2394 */           return storeLoreValues;
/* 2322:     */         }
/* 2323:     */       }
/* 2324:     */     }
/* 2325:2400 */     return null;
/* 2326:     */   }
/* 2327:     */   
/* 2328:     */   public String getClassHead(Player player)
/* 2329:     */   {
/* 2330:2407 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2331:     */     
/* 2332:2409 */     String storeLoreValues = "";
/* 2333:     */     
/* 2334:2411 */     ItemStack item = player.getInventory().getHelmet();
/* 2335:2413 */     if ((item != null) && 
/* 2336:2414 */       (item.hasItemMeta()) && 
/* 2337:2415 */       (item.getItemMeta().hasLore()))
/* 2338:     */     {
/* 2339:2416 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2340:2417 */       for (String line : itemLore) {
/* 2341:2418 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2342:     */         {
/* 2343:2419 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2344:2420 */           storeLoreValues = value;
/* 2345:2421 */           return storeLoreValues;
/* 2346:     */         }
/* 2347:     */       }
/* 2348:     */     }
/* 2349:2427 */     return storeLoreValues;
/* 2350:     */   }
/* 2351:     */   
/* 2352:     */   public String getClassChest(Player player)
/* 2353:     */   {
/* 2354:2431 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2355:     */     
/* 2356:2433 */     String storeLoreValues = "";
/* 2357:     */     
/* 2358:2435 */     ItemStack item = player.getInventory().getChestplate();
/* 2359:2437 */     if ((item != null) && 
/* 2360:2438 */       (item.hasItemMeta()) && 
/* 2361:2439 */       (item.getItemMeta().hasLore()))
/* 2362:     */     {
/* 2363:2440 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2364:2441 */       for (String line : itemLore) {
/* 2365:2442 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2366:     */         {
/* 2367:2443 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2368:2444 */           storeLoreValues = value;
/* 2369:2445 */           return storeLoreValues;
/* 2370:     */         }
/* 2371:     */       }
/* 2372:     */     }
/* 2373:2451 */     return storeLoreValues;
/* 2374:     */   }
/* 2375:     */   
/* 2376:     */   public String getClassLegs(Player player)
/* 2377:     */   {
/* 2378:2455 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2379:     */     
/* 2380:2457 */     String storeLoreValues = "";
/* 2381:     */     
/* 2382:2459 */     ItemStack item = player.getInventory().getLeggings();
/* 2383:2461 */     if ((item != null) && 
/* 2384:2462 */       (item.hasItemMeta()) && 
/* 2385:2463 */       (item.getItemMeta().hasLore()))
/* 2386:     */     {
/* 2387:2464 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2388:2465 */       for (String line : itemLore) {
/* 2389:2466 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2390:     */         {
/* 2391:2467 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2392:2468 */           storeLoreValues = value;
/* 2393:2469 */           return storeLoreValues;
/* 2394:     */         }
/* 2395:     */       }
/* 2396:     */     }
/* 2397:2475 */     return storeLoreValues;
/* 2398:     */   }
/* 2399:     */   
/* 2400:     */   public String getClassBoots(Player player)
/* 2401:     */   {
/* 2402:2479 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2403:     */     
/* 2404:2481 */     String storeLoreValues = "";
/* 2405:     */     
/* 2406:2483 */     ItemStack item = player.getInventory().getBoots();
/* 2407:2485 */     if ((item != null) && 
/* 2408:2486 */       (item.hasItemMeta()) && 
/* 2409:2487 */       (item.getItemMeta().hasLore()))
/* 2410:     */     {
/* 2411:2488 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2412:2489 */       for (String line : itemLore) {
/* 2413:2490 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2414:     */         {
/* 2415:2491 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2416:2492 */           storeLoreValues = value;
/* 2417:2493 */           return storeLoreValues;
/* 2418:     */         }
/* 2419:     */       }
/* 2420:     */     }
/* 2421:2499 */     return storeLoreValues;
/* 2422:     */   }
/* 2423:     */   
/* 2424:     */   public String getClassItemInHand(Player player)
/* 2425:     */   {
/* 2426:2503 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2427:     */     
/* 2428:2505 */     String storeLoreValues = "";
/* 2429:     */     
/* 2430:2507 */     ItemStack item = player.getItemInHand();
/* 2431:2509 */     if ((item != null) && 
/* 2432:2510 */       (item.hasItemMeta()) && 
/* 2433:2511 */       (item.getItemMeta().hasLore()))
/* 2434:     */     {
/* 2435:2512 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2436:2513 */       for (String line : itemLore) {
/* 2437:2514 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2438:     */         {
/* 2439:2515 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2440:2516 */           storeLoreValues = value;
/* 2441:2517 */           return storeLoreValues;
/* 2442:     */         }
/* 2443:     */       }
/* 2444:     */     }
/* 2445:2523 */     return storeLoreValues;
/* 2446:     */   }
/* 2447:     */   
/* 2448:     */   public String playerHeldItemChangeSetBonusItemInHand(ItemStack itemstack)
/* 2449:     */   {
/* 2450:2530 */     this.setbonus = ItemLoreStats.plugin.getConfig().getString("statNames.setbonus").replaceAll("&([0-9a-f])", "");
/* 2451:2531 */     String weaponKey = "";
/* 2452:     */     
/* 2453:2533 */     ItemStack itemInHand = itemstack;
/* 2454:2535 */     if ((itemInHand != null) && 
/* 2455:2536 */       (itemInHand.hasItemMeta()) && 
/* 2456:2537 */       (ItemLoreStats.plugin.isTool(itemInHand.getType())) && 
/* 2457:2538 */       (itemInHand.getItemMeta().hasLore()))
/* 2458:     */     {
/* 2459:2539 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 2460:2540 */       for (String line : itemInHandLore) {
/* 2461:2541 */         if (line.contains(this.setbonus))
/* 2462:     */         {
/* 2463:2542 */           String value = this.util_Colours.extractAndReplaceTooltipColour(line.substring(0, 6));
/* 2464:2543 */           weaponKey = value;
/* 2465:2544 */           return weaponKey;
/* 2466:     */         }
/* 2467:     */       }
/* 2468:     */     }
/* 2469:2551 */     return weaponKey;
/* 2470:     */   }
/* 2471:     */   
/* 2472:     */   public String playerHeldItemChangeSoulboundNameItemInHand(ItemStack itemstack)
/* 2473:     */   {
/* 2474:2554 */     this.soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/* 2475:     */     
/* 2476:2556 */     String storeLoreValues = "";
/* 2477:     */     
/* 2478:2558 */     ItemStack item = itemstack;
/* 2479:2560 */     if ((item != null) && 
/* 2480:2561 */       (item.hasItemMeta()) && 
/* 2481:2562 */       (item.getItemMeta().hasLore()))
/* 2482:     */     {
/* 2483:2563 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2484:2564 */       for (String line : itemLore) {
/* 2485:2565 */         if (ChatColor.stripColor(line).startsWith(this.soulbound))
/* 2486:     */         {
/* 2487:2566 */           String value = ChatColor.stripColor(line).substring(this.soulbound.length()).trim();
/* 2488:2567 */           storeLoreValues = value;
/* 2489:2568 */           return storeLoreValues;
/* 2490:     */         }
/* 2491:     */       }
/* 2492:     */     }
/* 2493:2574 */     return null;
/* 2494:     */   }
/* 2495:     */   
/* 2496:     */   public String playerHeldItemChangeClassItemInHand(ItemStack itemstack)
/* 2497:     */   {
/* 2498:2577 */     this.className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class").replaceAll("&([0-9a-f])", "");
/* 2499:     */     
/* 2500:2579 */     String storeLoreValues = "";
/* 2501:     */     
/* 2502:2581 */     ItemStack item = itemstack;
/* 2503:2583 */     if ((item != null) && 
/* 2504:2584 */       (item.hasItemMeta()) && 
/* 2505:2585 */       (item.getItemMeta().hasLore()))
/* 2506:     */     {
/* 2507:2586 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2508:2587 */       for (String line : itemLore) {
/* 2509:2588 */         if (ChatColor.stripColor(line).startsWith(this.className + ": "))
/* 2510:     */         {
/* 2511:2589 */           String value = ChatColor.stripColor(line).substring((this.className + ": ").length()).trim();
/* 2512:2590 */           storeLoreValues = value;
/* 2513:2591 */           return storeLoreValues;
/* 2514:     */         }
/* 2515:     */       }
/* 2516:     */     }
/* 2517:2597 */     return null;
/* 2518:     */   }
/* 2519:     */   
/* 2520:     */   public int playerHeldItemChangeXPLevelRequirementItemInHand(ItemStack itemstack)
/* 2521:     */   {
/* 2522:2601 */     this.xplevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/* 2523:     */     
/* 2524:2603 */     int storeLoreValues = 0;
/* 2525:     */     
/* 2526:2605 */     ItemStack itemInHand = itemstack;
/* 2527:2607 */     if ((itemInHand != null) && 
/* 2528:2608 */       (itemInHand.hasItemMeta()) && 
/* 2529:2609 */       (itemInHand.getItemMeta().hasLore()))
/* 2530:     */     {
/* 2531:2610 */       List<String> itemInHandLore = itemInHand.getItemMeta().getLore();
/* 2532:2611 */       for (String line : itemInHandLore) {
/* 2533:2612 */         if (ChatColor.stripColor(line).startsWith(this.xplevel + ": "))
/* 2534:     */         {
/* 2535:2613 */           String xpLevelValue = ChatColor.stripColor(line).substring((this.xplevel + ": ").length()).trim();
/* 2536:     */           try
/* 2537:     */           {
/* 2538:2615 */             return Integer.parseInt(xpLevelValue);
/* 2539:     */           }
/* 2540:     */           catch (NumberFormatException localNumberFormatException) {}
/* 2541:     */         }
/* 2542:     */       }
/* 2543:     */     }
/* 2544:2625 */     return 0;
/* 2545:     */   }
/* 2546:     */   
/* 2547:     */   public boolean getTnT(Player player)
/* 2548:     */   {
/* 2549:2634 */     this.tnt = ItemLoreStats.plugin.getConfig().getString("statNames.spells.tnt.tnt").replaceAll("&([0-9a-f])", "");
/* 2550:     */     
/* 2551:2636 */     ItemStack item = player.getItemInHand();
/* 2552:2638 */     if ((item != null) && 
/* 2553:2639 */       (item.hasItemMeta()) && 
/* 2554:2640 */       (item.getItemMeta().hasLore()))
/* 2555:     */     {
/* 2556:2641 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2557:2642 */       for (String line : itemLore) {
/* 2558:2643 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.tnt)) {
/* 2559:2644 */           return true;
/* 2560:     */         }
/* 2561:     */       }
/* 2562:     */     }
/* 2563:2650 */     return false;
/* 2564:     */   }
/* 2565:     */   
/* 2566:     */   public boolean getFireball(Player player)
/* 2567:     */   {
/* 2568:2654 */     this.fireball = ItemLoreStats.plugin.getConfig().getString("statNames.spells.fireball.fireball").replaceAll("&([0-9a-f])", "");
/* 2569:     */     
/* 2570:2656 */     ItemStack item = player.getItemInHand();
/* 2571:2658 */     if ((item != null) && 
/* 2572:2659 */       (item.hasItemMeta()) && 
/* 2573:2660 */       (item.getItemMeta().hasLore()))
/* 2574:     */     {
/* 2575:2661 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2576:2662 */       for (String line : itemLore) {
/* 2577:2663 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.fireball)) {
/* 2578:2664 */           return true;
/* 2579:     */         }
/* 2580:     */       }
/* 2581:     */     }
/* 2582:2670 */     return false;
/* 2583:     */   }
/* 2584:     */   
/* 2585:     */   public boolean getLightning(Player player)
/* 2586:     */   {
/* 2587:2674 */     this.lightning = ItemLoreStats.plugin.getConfig().getString("statNames.spells.lightning.lightning").replaceAll("&([0-9a-f])", "");
/* 2588:     */     
/* 2589:2676 */     ItemStack item = player.getItemInHand();
/* 2590:2678 */     if ((item != null) && 
/* 2591:2679 */       (item.hasItemMeta()) && 
/* 2592:2680 */       (item.getItemMeta().hasLore()))
/* 2593:     */     {
/* 2594:2681 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2595:2682 */       for (String line : itemLore) {
/* 2596:2683 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.lightning)) {
/* 2597:2684 */           return true;
/* 2598:     */         }
/* 2599:     */       }
/* 2600:     */     }
/* 2601:2690 */     return false;
/* 2602:     */   }
/* 2603:     */   
/* 2604:     */   public boolean getFrostbolt(Player player)
/* 2605:     */   {
/* 2606:2694 */     this.frostbolt = ItemLoreStats.plugin.getConfig().getString("statNames.spells.frostbolt.frostbolt").replaceAll("&([0-9a-f])", "");
/* 2607:     */     
/* 2608:2696 */     ItemStack item = player.getItemInHand();
/* 2609:2698 */     if ((item != null) && 
/* 2610:2699 */       (item.hasItemMeta()) && 
/* 2611:2700 */       (item.getItemMeta().hasLore()))
/* 2612:     */     {
/* 2613:2701 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2614:2702 */       for (String line : itemLore) {
/* 2615:2703 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.frostbolt)) {
/* 2616:2704 */           return true;
/* 2617:     */         }
/* 2618:     */       }
/* 2619:     */     }
/* 2620:2710 */     return false;
/* 2621:     */   }
/* 2622:     */   
/* 2623:     */   public boolean getHeal(Player player)
/* 2624:     */   {
/* 2625:2714 */     this.heal = ItemLoreStats.plugin.getConfig().getString("statNames.spells.heal.heal").replaceAll("&([0-9a-f])", "");
/* 2626:     */     
/* 2627:2716 */     ItemStack item = player.getItemInHand();
/* 2628:2718 */     if ((item != null) && 
/* 2629:2719 */       (item.hasItemMeta()) && 
/* 2630:2720 */       (item.getItemMeta().hasLore()))
/* 2631:     */     {
/* 2632:2721 */       List<String> itemLore = item.getItemMeta().getLore();
/* 2633:2722 */       for (String line : itemLore) {
/* 2634:2723 */         if (ChatColor.stripColor(line).startsWith(ChatColor.stripColor(getResponse("SpellMessages.CastSpell.ItemInHand")) + " " + this.heal)) {
/* 2635:2724 */           return true;
/* 2636:     */         }
/* 2637:     */       }
/* 2638:     */     }
/* 2639:2730 */     return false;
/* 2640:     */   }
/* 2641:     */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.GearStats
 * JD-Core Version:    0.7.0.1
 */