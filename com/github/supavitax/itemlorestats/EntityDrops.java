/*    1:     */ package com.github.supavitax.itemlorestats;
/*    2:     */ 
/*    3:     */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*    4:     */ import java.io.File;
/*    5:     */ import java.io.PrintStream;
/*    6:     */ import java.text.DecimalFormat;
/*    7:     */ import java.text.NumberFormat;
/*    8:     */ import java.util.ArrayList;
/*    9:     */ import java.util.Arrays;
/*   10:     */ import java.util.Collections;
/*   11:     */ import java.util.List;
/*   12:     */ import java.util.Locale;
/*   13:     */ import java.util.Random;
/*   14:     */ import java.util.Set;
/*   15:     */ import org.bukkit.ChatColor;
/*   16:     */ import org.bukkit.Location;
/*   17:     */ import org.bukkit.Material;
/*   18:     */ import org.bukkit.World;
/*   19:     */ import org.bukkit.configuration.ConfigurationSection;
/*   20:     */ import org.bukkit.configuration.file.FileConfiguration;
/*   21:     */ import org.bukkit.configuration.file.YamlConfiguration;
/*   22:     */ import org.bukkit.entity.EntityType;
/*   23:     */ import org.bukkit.entity.LivingEntity;
/*   24:     */ import org.bukkit.entity.Player;
/*   25:     */ import org.bukkit.event.EventHandler;
/*   26:     */ import org.bukkit.event.Listener;
/*   27:     */ import org.bukkit.event.entity.EntityDeathEvent;
/*   28:     */ import org.bukkit.inventory.EntityEquipment;
/*   29:     */ import org.bukkit.inventory.ItemStack;
/*   30:     */ import org.bukkit.inventory.meta.ItemMeta;
/*   31:     */ 
/*   32:     */ public class EntityDrops
/*   33:     */   implements Listener
/*   34:     */ {
/*   35:  35 */   Util_Colours util_Colours = new Util_Colours();
/*   36:     */   private FileConfiguration PlayerDataConfig;
/*   37:     */   
/*   38:     */   private int random(int length)
/*   39:     */   {
/*   40:  40 */     return new Random().nextInt(length) + 1;
/*   41:     */   }
/*   42:     */   
/*   43:     */   private int randomKeySelection(int length)
/*   44:     */   {
/*   45:  44 */     return new Random().nextInt(length);
/*   46:     */   }
/*   47:     */   
/*   48:     */   private String randomRange(String min, String max)
/*   49:     */   {
/*   50:  49 */     Locale forceLocale = new Locale("en", "UK");
/*   51:  50 */     String decimalPattern = "#.#";
/*   52:     */     
/*   53:  52 */     DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(forceLocale);
/*   54:  53 */     decimalFormat.applyPattern(decimalPattern);
/*   55:     */     
/*   56:  55 */     String format = decimalFormat.format(Double.valueOf(min).doubleValue() + Math.random() * (Double.valueOf(max).doubleValue() - Double.valueOf(min).doubleValue()));
/*   57:     */     
/*   58:  57 */     return format;
/*   59:     */   }
/*   60:     */   
/*   61:     */   private int randomRangeInt(int min, int max)
/*   62:     */   {
/*   63:  62 */     return (int)(min + Math.random() * (max - min));
/*   64:     */   }
/*   65:     */   
/*   66:     */   public boolean dropChance(int setDropChance)
/*   67:     */   {
/*   68:  66 */     if (random(100) <= setDropChance) {
/*   69:  67 */       return true;
/*   70:     */     }
/*   71:  69 */     return false;
/*   72:     */   }
/*   73:     */   
/*   74:     */   private String randomClass()
/*   75:     */   {
/*   76:  73 */     List getListClasses = ItemLoreStats.plugin.getConfig().getStringList("statNames.class.classList");
/*   77:  74 */     String selectClass = (String)getListClasses.get(random(getListClasses.size()) - 1);
/*   78:  75 */     return selectClass;
/*   79:     */   }
/*   80:     */   
/*   81:     */   public String getResponse(String getKeyFromLanguageFile)
/*   82:     */   {
/*   83:     */     try
/*   84:     */     {
/*   85:  81 */       this.PlayerDataConfig = new YamlConfiguration();
/*   86:  82 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*   87:     */       
/*   88:  84 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*   89:     */     }
/*   90:     */     catch (Exception e)
/*   91:     */     {
/*   92:  87 */       e.printStackTrace();
/*   93:  88 */       System.out.println("*********** Failed to load message from language file! ***********");
/*   94:     */     }
/*   95:  90 */     return "*********** Failed to load message from language file! ***********";
/*   96:     */   }
/*   97:     */   
/*   98:     */   public Material idToMaterial(int id)
/*   99:     */   {
/*  100:  95 */     if (id == 256) {
/*  101:  96 */       return Material.IRON_HOE;
/*  102:     */     }
/*  103:  97 */     if (id == 257) {
/*  104:  98 */       return Material.IRON_PICKAXE;
/*  105:     */     }
/*  106:  99 */     if (id == 258) {
/*  107: 100 */       return Material.IRON_AXE;
/*  108:     */     }
/*  109: 101 */     if (id == 261) {
/*  110: 102 */       return Material.BOW;
/*  111:     */     }
/*  112: 103 */     if (id == 267) {
/*  113: 104 */       return Material.IRON_SWORD;
/*  114:     */     }
/*  115: 105 */     if (id == 268) {
/*  116: 106 */       return Material.WOOD_SWORD;
/*  117:     */     }
/*  118: 107 */     if (id == 269) {
/*  119: 108 */       return Material.WOOD_SPADE;
/*  120:     */     }
/*  121: 109 */     if (id == 270) {
/*  122: 110 */       return Material.WOOD_PICKAXE;
/*  123:     */     }
/*  124: 111 */     if (id == 271) {
/*  125: 112 */       return Material.WOOD_AXE;
/*  126:     */     }
/*  127: 113 */     if (id == 272) {
/*  128: 114 */       return Material.STONE_SWORD;
/*  129:     */     }
/*  130: 115 */     if (id == 273) {
/*  131: 116 */       return Material.STONE_SPADE;
/*  132:     */     }
/*  133: 117 */     if (id == 274) {
/*  134: 118 */       return Material.STONE_PICKAXE;
/*  135:     */     }
/*  136: 119 */     if (id == 275) {
/*  137: 120 */       return Material.STONE_AXE;
/*  138:     */     }
/*  139: 121 */     if (id == 276) {
/*  140: 122 */       return Material.DIAMOND_SWORD;
/*  141:     */     }
/*  142: 123 */     if (id == 277) {
/*  143: 124 */       return Material.DIAMOND_SPADE;
/*  144:     */     }
/*  145: 125 */     if (id == 278) {
/*  146: 126 */       return Material.DIAMOND_PICKAXE;
/*  147:     */     }
/*  148: 127 */     if (id == 279) {
/*  149: 128 */       return Material.DIAMOND_AXE;
/*  150:     */     }
/*  151: 129 */     if (id == 280) {
/*  152: 130 */       return Material.STICK;
/*  153:     */     }
/*  154: 131 */     if (id == 283) {
/*  155: 132 */       return Material.GOLD_SWORD;
/*  156:     */     }
/*  157: 133 */     if (id == 284) {
/*  158: 134 */       return Material.GOLD_SPADE;
/*  159:     */     }
/*  160: 135 */     if (id == 285) {
/*  161: 136 */       return Material.GOLD_PICKAXE;
/*  162:     */     }
/*  163: 137 */     if (id == 286) {
/*  164: 138 */       return Material.GOLD_AXE;
/*  165:     */     }
/*  166: 139 */     if (id == 290) {
/*  167: 140 */       return Material.WOOD_HOE;
/*  168:     */     }
/*  169: 141 */     if (id == 291) {
/*  170: 142 */       return Material.STONE_HOE;
/*  171:     */     }
/*  172: 143 */     if (id == 292) {
/*  173: 144 */       return Material.IRON_HOE;
/*  174:     */     }
/*  175: 145 */     if (id == 293) {
/*  176: 146 */       return Material.DIAMOND_HOE;
/*  177:     */     }
/*  178: 147 */     if (id == 294) {
/*  179: 148 */       return Material.GOLD_HOE;
/*  180:     */     }
/*  181: 149 */     if (id == 298) {
/*  182: 150 */       return Material.LEATHER_HELMET;
/*  183:     */     }
/*  184: 151 */     if (id == 299) {
/*  185: 152 */       return Material.LEATHER_CHESTPLATE;
/*  186:     */     }
/*  187: 153 */     if (id == 300) {
/*  188: 154 */       return Material.LEATHER_LEGGINGS;
/*  189:     */     }
/*  190: 155 */     if (id == 301) {
/*  191: 156 */       return Material.LEATHER_BOOTS;
/*  192:     */     }
/*  193: 157 */     if (id == 302) {
/*  194: 158 */       return Material.CHAINMAIL_HELMET;
/*  195:     */     }
/*  196: 159 */     if (id == 303) {
/*  197: 160 */       return Material.CHAINMAIL_CHESTPLATE;
/*  198:     */     }
/*  199: 161 */     if (id == 304) {
/*  200: 162 */       return Material.CHAINMAIL_LEGGINGS;
/*  201:     */     }
/*  202: 163 */     if (id == 305) {
/*  203: 164 */       return Material.CHAINMAIL_BOOTS;
/*  204:     */     }
/*  205: 165 */     if (id == 306) {
/*  206: 166 */       return Material.IRON_HELMET;
/*  207:     */     }
/*  208: 167 */     if (id == 307) {
/*  209: 168 */       return Material.IRON_CHESTPLATE;
/*  210:     */     }
/*  211: 169 */     if (id == 308) {
/*  212: 170 */       return Material.IRON_LEGGINGS;
/*  213:     */     }
/*  214: 171 */     if (id == 309) {
/*  215: 172 */       return Material.IRON_BOOTS;
/*  216:     */     }
/*  217: 173 */     if (id == 310) {
/*  218: 174 */       return Material.DIAMOND_HELMET;
/*  219:     */     }
/*  220: 175 */     if (id == 311) {
/*  221: 176 */       return Material.DIAMOND_CHESTPLATE;
/*  222:     */     }
/*  223: 177 */     if (id == 312) {
/*  224: 178 */       return Material.DIAMOND_LEGGINGS;
/*  225:     */     }
/*  226: 179 */     if (id == 313) {
/*  227: 180 */       return Material.DIAMOND_BOOTS;
/*  228:     */     }
/*  229: 181 */     if (id == 314) {
/*  230: 182 */       return Material.GOLD_HELMET;
/*  231:     */     }
/*  232: 183 */     if (id == 315) {
/*  233: 184 */       return Material.GOLD_CHESTPLATE;
/*  234:     */     }
/*  235: 185 */     if (id == 316) {
/*  236: 186 */       return Material.GOLD_LEGGINGS;
/*  237:     */     }
/*  238: 187 */     if (id == 317) {
/*  239: 188 */       return Material.GOLD_BOOTS;
/*  240:     */     }
/*  241: 191 */     return Material.POTATO;
/*  242:     */   }
/*  243:     */   
/*  244:     */   public String prefix(String entity, String dropChance)
/*  245:     */   {
/*  246:     */     try
/*  247:     */     {
/*  248: 196 */       this.PlayerDataConfig = new YamlConfiguration();
/*  249: 197 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entity + ".yml"));
/*  250: 199 */       if (this.PlayerDataConfig.getString(dropChance + ".prefix").equalsIgnoreCase("Random"))
/*  251:     */       {
/*  252: 200 */         List getListPrefix = ItemLoreStats.plugin.getConfig().getStringList("prefix.random");
/*  253:     */         
/*  254: 202 */         return (String)getListPrefix.get(random(getListPrefix.size()) - 1);
/*  255:     */       }
/*  256: 205 */       if (!this.PlayerDataConfig.getString(dropChance + ".prefix").equalsIgnoreCase("Stat")) {
/*  257: 207 */         return this.PlayerDataConfig.getString(dropChance + ".prefix");
/*  258:     */       }
/*  259:     */     }
/*  260:     */     catch (Exception e)
/*  261:     */     {
/*  262: 211 */       e.printStackTrace();
/*  263: 212 */       System.out.println("*********** Failed to load prefix from " + entity + " file! ***********");
/*  264:     */     }
/*  265: 215 */     return "Error getting prefix!";
/*  266:     */   }
/*  267:     */   
/*  268:     */   public String suffix(String entity, String dropChance)
/*  269:     */   {
/*  270:     */     try
/*  271:     */     {
/*  272: 221 */       this.PlayerDataConfig = new YamlConfiguration();
/*  273: 222 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entity + ".yml"));
/*  274: 224 */       if (this.PlayerDataConfig.getString(dropChance + ".suffix").equalsIgnoreCase("Random"))
/*  275:     */       {
/*  276: 225 */         List getListSuffix = ItemLoreStats.plugin.getConfig().getStringList("suffix.random");
/*  277:     */         
/*  278: 227 */         return (String)getListSuffix.get(random(getListSuffix.size()) - 1);
/*  279:     */       }
/*  280: 230 */       if (!this.PlayerDataConfig.getString(dropChance + ".suffix").equalsIgnoreCase("Stat")) {
/*  281: 232 */         return this.PlayerDataConfig.getString(dropChance + ".suffix");
/*  282:     */       }
/*  283:     */     }
/*  284:     */     catch (Exception e)
/*  285:     */     {
/*  286: 236 */       e.printStackTrace();
/*  287: 237 */       System.out.println("*********** Failed to load suffix from " + entity + " file! ***********");
/*  288:     */     }
/*  289: 240 */     return "Error getting suffix!";
/*  290:     */   }
/*  291:     */   
/*  292:     */   public String randomLore(Material getMaterial)
/*  293:     */   {
/*  294: 245 */     if ((getMaterial.toString().contains("_SWORD")) || (getMaterial.toString().contains("_AXE")) || (getMaterial.toString().contains("_HOE")) || (getMaterial.toString().contains("_SPADE")) || (getMaterial.toString().contains("_PICKAXE")) || (getMaterial.toString().contains("BOW")) || (getMaterial.toString().contains("STICK")))
/*  295:     */     {
/*  296: 246 */       if (getMaterial.toString().contains("BOW"))
/*  297:     */       {
/*  298: 247 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.bow");
/*  299:     */         
/*  300: 249 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  301:     */         
/*  302: 251 */         return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Bow"));
/*  303:     */       }
/*  304: 252 */       if (getMaterial.toString().contains("STICK"))
/*  305:     */       {
/*  306: 253 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.stick");
/*  307:     */         
/*  308: 255 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  309:     */         
/*  310: 257 */         return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Stick"));
/*  311:     */       }
/*  312: 258 */       if (getMaterial.toString().contains("WOOD_"))
/*  313:     */       {
/*  314: 260 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.wood");
/*  315:     */         
/*  316: 262 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  317: 264 */         if (getMaterial.toString().contains("_SWORD")) {
/*  318: 265 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  319:     */         }
/*  320: 266 */         if (getMaterial.toString().contains("_AXE")) {
/*  321: 267 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  322:     */         }
/*  323: 268 */         if (getMaterial.toString().contains("_HOE")) {
/*  324: 269 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  325:     */         }
/*  326: 270 */         if (getMaterial.toString().contains("_SPADE")) {
/*  327: 271 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  328:     */         }
/*  329: 272 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  330: 273 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  331:     */         }
/*  332:     */       }
/*  333: 276 */       else if (getMaterial.toString().contains("STONE_"))
/*  334:     */       {
/*  335: 278 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.stone");
/*  336:     */         
/*  337: 280 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  338: 282 */         if (getMaterial.toString().contains("_SWORD")) {
/*  339: 283 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  340:     */         }
/*  341: 284 */         if (getMaterial.toString().contains("_AXE")) {
/*  342: 285 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  343:     */         }
/*  344: 286 */         if (getMaterial.toString().contains("_HOE")) {
/*  345: 287 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  346:     */         }
/*  347: 288 */         if (getMaterial.toString().contains("_SPADE")) {
/*  348: 289 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  349:     */         }
/*  350: 290 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  351: 291 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  352:     */         }
/*  353:     */       }
/*  354: 294 */       else if (getMaterial.toString().contains("IRON_"))
/*  355:     */       {
/*  356: 296 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.iron");
/*  357:     */         
/*  358: 298 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  359: 300 */         if (getMaterial.toString().contains("_SWORD")) {
/*  360: 301 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  361:     */         }
/*  362: 302 */         if (getMaterial.toString().contains("_AXE")) {
/*  363: 303 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  364:     */         }
/*  365: 304 */         if (getMaterial.toString().contains("_HOE")) {
/*  366: 305 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  367:     */         }
/*  368: 306 */         if (getMaterial.toString().contains("_SPADE")) {
/*  369: 307 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  370:     */         }
/*  371: 308 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  372: 309 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  373:     */         }
/*  374:     */       }
/*  375: 312 */       else if (getMaterial.toString().contains("GOLD_"))
/*  376:     */       {
/*  377: 314 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.gold");
/*  378:     */         
/*  379: 316 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  380: 318 */         if (getMaterial.toString().contains("_SWORD")) {
/*  381: 319 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  382:     */         }
/*  383: 320 */         if (getMaterial.toString().contains("_AXE")) {
/*  384: 321 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  385:     */         }
/*  386: 322 */         if (getMaterial.toString().contains("_HOE")) {
/*  387: 323 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  388:     */         }
/*  389: 324 */         if (getMaterial.toString().contains("_SPADE")) {
/*  390: 325 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  391:     */         }
/*  392: 326 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  393: 327 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  394:     */         }
/*  395:     */       }
/*  396: 330 */       else if (getMaterial.toString().contains("DIAMOND_"))
/*  397:     */       {
/*  398: 332 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.diamond");
/*  399:     */         
/*  400: 334 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  401: 336 */         if (getMaterial.toString().contains("_SWORD")) {
/*  402: 337 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  403:     */         }
/*  404: 338 */         if (getMaterial.toString().contains("_AXE")) {
/*  405: 339 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  406:     */         }
/*  407: 340 */         if (getMaterial.toString().contains("_HOE")) {
/*  408: 341 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  409:     */         }
/*  410: 342 */         if (getMaterial.toString().contains("_SPADE")) {
/*  411: 343 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  412:     */         }
/*  413: 344 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  414: 345 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  415:     */         }
/*  416:     */       }
/*  417:     */     }
/*  418: 349 */     else if ((getMaterial.toString().contains("_HELMET")) || (getMaterial.toString().contains("_CHESTPLATE")) || (getMaterial.toString().contains("_LEGGINGS")) || (getMaterial.toString().contains("_BOOTS")))
/*  419:     */     {
/*  420: 350 */       if (getMaterial.toString().contains("LEATHER_"))
/*  421:     */       {
/*  422: 352 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.armour.leather");
/*  423:     */         
/*  424: 354 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  425: 356 */         if (getMaterial.toString().contains("_HELMET")) {
/*  426: 357 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Helmet"));
/*  427:     */         }
/*  428: 358 */         if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  429: 359 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Chestplate"));
/*  430:     */         }
/*  431: 360 */         if (getMaterial.toString().contains("_LEGGINGS")) {
/*  432: 361 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Leggings"));
/*  433:     */         }
/*  434: 362 */         if (getMaterial.toString().contains("_BOOTS")) {
/*  435: 363 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Boots"));
/*  436:     */         }
/*  437:     */       }
/*  438: 366 */       else if (getMaterial.toString().contains("IRON_"))
/*  439:     */       {
/*  440: 368 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.armour.iron");
/*  441:     */         
/*  442: 370 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  443: 372 */         if (getMaterial.toString().contains("_HELMET")) {
/*  444: 373 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Helmet"));
/*  445:     */         }
/*  446: 374 */         if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  447: 375 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Chestplate"));
/*  448:     */         }
/*  449: 376 */         if (getMaterial.toString().contains("_LEGGINGS")) {
/*  450: 377 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Leggings"));
/*  451:     */         }
/*  452: 378 */         if (getMaterial.toString().contains("_BOOTS")) {
/*  453: 379 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Boots"));
/*  454:     */         }
/*  455:     */       }
/*  456: 382 */       else if (getMaterial.toString().contains("GOLD_"))
/*  457:     */       {
/*  458: 384 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.armour.gold");
/*  459:     */         
/*  460: 386 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  461: 388 */         if (getMaterial.toString().contains("_HELMET")) {
/*  462: 389 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Helmet"));
/*  463:     */         }
/*  464: 390 */         if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  465: 391 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Chestplate"));
/*  466:     */         }
/*  467: 392 */         if (getMaterial.toString().contains("_LEGGINGS")) {
/*  468: 393 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Leggings"));
/*  469:     */         }
/*  470: 394 */         if (getMaterial.toString().contains("_BOOTS")) {
/*  471: 395 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Boots"));
/*  472:     */         }
/*  473:     */       }
/*  474: 398 */       else if (getMaterial.toString().contains("DIAMOND_"))
/*  475:     */       {
/*  476: 400 */         List getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.armour.diamond");
/*  477:     */         
/*  478: 402 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  479: 404 */         if (getMaterial.toString().contains("_HELMET")) {
/*  480: 405 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Helmet"));
/*  481:     */         }
/*  482: 406 */         if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  483: 407 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Chestplate"));
/*  484:     */         }
/*  485: 408 */         if (getMaterial.toString().contains("_LEGGINGS")) {
/*  486: 409 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Leggings"));
/*  487:     */         }
/*  488: 410 */         if (getMaterial.toString().contains("_BOOTS")) {
/*  489: 411 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Boots"));
/*  490:     */         }
/*  491:     */       }
/*  492:     */     }
/*  493: 417 */     return "ERROR";
/*  494:     */   }
/*  495:     */   
/*  496:     */   public String setType(ItemStack getMaterial)
/*  497:     */   {
/*  498: 422 */     if (getMaterial.toString().contains("_SWORD")) {
/*  499: 423 */       return "Sword";
/*  500:     */     }
/*  501: 424 */     if (getMaterial.toString().contains("_AXE")) {
/*  502: 425 */       return "Axe";
/*  503:     */     }
/*  504: 426 */     if (getMaterial.toString().contains("_HOE")) {
/*  505: 427 */       return "Hoe";
/*  506:     */     }
/*  507: 428 */     if (getMaterial.toString().contains("_PICKAXE")) {
/*  508: 429 */       return "Pickaxe";
/*  509:     */     }
/*  510: 430 */     if (getMaterial.toString().contains("BOW")) {
/*  511: 431 */       return "Bow";
/*  512:     */     }
/*  513: 432 */     if (getMaterial.toString().contains("STICK")) {
/*  514: 433 */       return "Bow";
/*  515:     */     }
/*  516: 434 */     if (getMaterial.toString().contains("_SPADE")) {
/*  517: 435 */       return "Spade";
/*  518:     */     }
/*  519: 436 */     if (getMaterial.toString().contains("_HELMET")) {
/*  520: 437 */       return "Helmet";
/*  521:     */     }
/*  522: 438 */     if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  523: 439 */       return "Chestplate";
/*  524:     */     }
/*  525: 440 */     if (getMaterial.toString().contains("_LEGGINGS")) {
/*  526: 441 */       return "Leggings";
/*  527:     */     }
/*  528: 442 */     if (getMaterial.toString().contains("_BOOTS")) {
/*  529: 443 */       return "Boots";
/*  530:     */     }
/*  531: 446 */     return "Error";
/*  532:     */   }
/*  533:     */   
/*  534:     */   public String getMinStat(Player player, String entityName, String propertiesType, String dropChance)
/*  535:     */   {
/*  536:     */     try
/*  537:     */     {
/*  538: 452 */       this.PlayerDataConfig = new YamlConfiguration();
/*  539: 453 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entityName + ".yml"));
/*  540: 455 */       if (propertiesType.equals("damage"))
/*  541:     */       {
/*  542: 456 */         double setDamage = Double.parseDouble(randomRange(String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[0].split("-")[0].replaceAll("&([0-9a-f])", "").trim().replace("+", "")), String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[0].split("-")[1].replaceAll("&([0-9a-f])", "").trim())));
/*  543: 458 */         if (player.getLevel() < setDamage) {
/*  544: 460 */           return String.valueOf(player.getLevel());
/*  545:     */         }
/*  546: 463 */         return String.valueOf(player.getLevel() - setDamage);
/*  547:     */       }
/*  548: 466 */       if (player.getLevel() < Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim()))
/*  549:     */       {
/*  550: 467 */         if (ItemLoreStats.plugin.getConfig().getBoolean("tooltipStatsAsDoubles")) {
/*  551: 468 */           return String.valueOf(player.getLevel());
/*  552:     */         }
/*  553: 470 */         return String.valueOf(Double.valueOf(player.getLevel()).intValue());
/*  554:     */       }
/*  555: 473 */       if (ItemLoreStats.plugin.getConfig().getBoolean("tooltipStatsAsDoubles")) {
/*  556: 474 */         return String.valueOf(player.getLevel() - Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  557:     */       }
/*  558: 477 */       return String.valueOf(Double.valueOf(player.getLevel() - Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim())).intValue());
/*  559:     */     }
/*  560:     */     catch (Exception e)
/*  561:     */     {
/*  562: 481 */       e.printStackTrace();
/*  563: 482 */       System.out.println("*********** Failed to load minStat from " + entityName + " file! ***********");
/*  564:     */     }
/*  565: 485 */     return "1337";
/*  566:     */   }
/*  567:     */   
/*  568:     */   public int getMinStatInt(Player player, String entityName, String propertiesType, String dropChance)
/*  569:     */   {
/*  570:     */     try
/*  571:     */     {
/*  572: 491 */       this.PlayerDataConfig = new YamlConfiguration();
/*  573: 492 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entityName + ".yml"));
/*  574: 494 */       if (propertiesType.equals("damage"))
/*  575:     */       {
/*  576: 495 */         double setDamage = Double.parseDouble(randomRange(String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[0].split("-")[0].replaceAll("&([0-9a-f])", "").trim().replace("+", "")), String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[0].split("-")[1].replaceAll("&([0-9a-f])", "").trim())));
/*  577: 497 */         if (player.getLevel() < setDamage) {
/*  578: 498 */           return Double.valueOf(player.getLevel()).intValue();
/*  579:     */         }
/*  580: 500 */         return Double.valueOf(player.getLevel() - setDamage).intValue();
/*  581:     */       }
/*  582: 503 */       if (player.getLevel() < Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim())) {
/*  583: 504 */         return Double.valueOf(player.getLevel()).intValue();
/*  584:     */       }
/*  585: 506 */       return Double.valueOf(player.getLevel() - Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim())).intValue();
/*  586:     */     }
/*  587:     */     catch (Exception e)
/*  588:     */     {
/*  589: 510 */       e.printStackTrace();
/*  590: 511 */       System.out.println("*********** Failed to load minStat from " + entityName + " file! ***********");
/*  591:     */     }
/*  592: 514 */     return 1337;
/*  593:     */   }
/*  594:     */   
/*  595:     */   public String getMaxStat(Player player, String entityName, String propertiesType, String dropChance)
/*  596:     */   {
/*  597:     */     try
/*  598:     */     {
/*  599: 520 */       this.PlayerDataConfig = new YamlConfiguration();
/*  600: 521 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entityName + ".yml"));
/*  601: 523 */       if (propertiesType.equals("damage"))
/*  602:     */       {
/*  603: 524 */         double setDamage = Double.parseDouble(randomRange(String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[1].split("-")[0].replaceAll("&([0-9a-f])", "").trim().replace("+", "")), String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[1].split("-")[1].replaceAll("&([0-9a-f])", "").trim())));
/*  604: 526 */         if (player.getLevel() < setDamage) {
/*  605: 528 */           return String.valueOf(player.getLevel());
/*  606:     */         }
/*  607: 531 */         return String.valueOf(player.getLevel() + setDamage);
/*  608:     */       }
/*  609:     */     }
/*  610:     */     catch (Exception e)
/*  611:     */     {
/*  612: 537 */       e.printStackTrace();
/*  613: 538 */       System.out.println("*********** Failed to load maxStat from " + entityName + " file! ***********");
/*  614:     */     }
/*  615: 541 */     return "1337";
/*  616:     */   }
/*  617:     */   
/*  618:     */   public List<String> setLore(Player player, String entity, String dropChance, Material material)
/*  619:     */   {
/*  620: 546 */     String armour = ItemLoreStats.plugin.getConfig().getString("statNames.armour");
/*  621: 547 */     String armourNoColour = armour.replaceAll("&([0-9a-f])", "");
/*  622: 548 */     String dodge = ItemLoreStats.plugin.getConfig().getString("statNames.dodge");
/*  623: 549 */     String dodgeNoColour = dodge.replaceAll("&([0-9a-f])", "");
/*  624: 550 */     String block = ItemLoreStats.plugin.getConfig().getString("statNames.block");
/*  625: 551 */     String blockNoColour = block.replaceAll("&([0-9a-f])", "");
/*  626: 552 */     String critChance = ItemLoreStats.plugin.getConfig().getString("statNames.critChance");
/*  627: 553 */     String critChanceNoColour = critChance.replaceAll("&([0-9a-f])", "");
/*  628: 554 */     String critDamage = ItemLoreStats.plugin.getConfig().getString("statNames.critDamage");
/*  629: 555 */     String critDamageNoColour = critDamage.replaceAll("&([0-9a-f])", "");
/*  630: 556 */     String damage = ItemLoreStats.plugin.getConfig().getString("statNames.damage");
/*  631: 557 */     String damageNoColour = damage.replaceAll("&([0-9a-f])", "");
/*  632: 558 */     String health = ItemLoreStats.plugin.getConfig().getString("statNames.health");
/*  633: 559 */     String healthNoColour = health.replaceAll("&([0-9a-f])", "");
/*  634: 560 */     String healthRegen = ItemLoreStats.plugin.getConfig().getString("statNames.healthregen");
/*  635: 561 */     String healthRegenNoColour = healthRegen.replaceAll("&([0-9a-f])", "");
/*  636: 562 */     String lifeSteal = ItemLoreStats.plugin.getConfig().getString("statNames.lifesteal");
/*  637: 563 */     String lifeStealNoColour = lifeSteal.replaceAll("&([0-9a-f])", "");
/*  638: 564 */     String reflect = ItemLoreStats.plugin.getConfig().getString("statNames.reflect");
/*  639: 565 */     String reflectNoColour = reflect.replaceAll("&([0-9a-f])", "");
/*  640: 566 */     String fire = ItemLoreStats.plugin.getConfig().getString("statNames.fire");
/*  641: 567 */     String fireNoColour = fire.replaceAll("&([0-9a-f])", "");
/*  642: 568 */     String ice = ItemLoreStats.plugin.getConfig().getString("statNames.ice");
/*  643: 569 */     String iceNoColour = ice.replaceAll("&([0-9a-f])", "");
/*  644: 570 */     String poison = ItemLoreStats.plugin.getConfig().getString("statNames.poison");
/*  645: 571 */     String poisonNoColour = poison.replaceAll("&([0-9a-f])", "");
/*  646: 572 */     String wither = ItemLoreStats.plugin.getConfig().getString("statNames.wither");
/*  647: 573 */     String witherNoColour = wither.replaceAll("&([0-9a-f])", "");
/*  648: 574 */     String harming = ItemLoreStats.plugin.getConfig().getString("statNames.harming");
/*  649: 575 */     String harmingNoColour = harming.replaceAll("&([0-9a-f])", "");
/*  650: 576 */     String blind = ItemLoreStats.plugin.getConfig().getString("statNames.blind");
/*  651: 577 */     String blindNoColour = blind.replaceAll("&([0-9a-f])", "");
/*  652: 578 */     String movementSpeed = ItemLoreStats.plugin.getConfig().getString("statNames.movementspeed");
/*  653: 579 */     String movementSpeedNoColour = movementSpeed.replaceAll("&([0-9a-f])", "");
/*  654: 580 */     String xpMultiplier = ItemLoreStats.plugin.getConfig().getString("statNames.xpmultiplier");
/*  655: 581 */     String xpMultiplierNoColour = xpMultiplier.replaceAll("&([0-9a-f])", "");
/*  656: 582 */     String weaponSpeed = ItemLoreStats.plugin.getConfig().getString("statNames.weaponspeed");
/*  657: 583 */     String weaponSpeedNoColour = weaponSpeed.replaceAll("&([0-9a-f])", "");
/*  658: 584 */     String xpLevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel");
/*  659: 585 */     String xpLevelNoColour = xpLevel.replaceAll("&([0-9a-f])", "");
/*  660: 586 */     String className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class");
/*  661: 587 */     String classNameNoColour = className.replaceAll("&([0-9a-f])", "");
/*  662: 588 */     String soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound");
/*  663: 589 */     String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability");
/*  664: 590 */     String durabilityNoColour = durability.replaceAll("&([0-9a-f])", "");
/*  665: 591 */     String tnt = ItemLoreStats.plugin.getConfig().getString("statNames.spells.tnt.tnt");
/*  666: 592 */     String tntNoColour = tnt.replaceAll("&([0-9a-f])", "");
/*  667: 593 */     String fireball = ItemLoreStats.plugin.getConfig().getString("statNames.spells.fireball.fireball");
/*  668: 594 */     String fireballNoColour = fireball.replaceAll("&([0-9a-f])", "");
/*  669: 595 */     String lightning = ItemLoreStats.plugin.getConfig().getString("statNames.spells.lightning.lightning");
/*  670: 596 */     String lightningNoColour = lightning.replaceAll("&([0-9a-f])", "");
/*  671: 597 */     String frostbolt = ItemLoreStats.plugin.getConfig().getString("statNames.spells.frostbolt.frostbolt");
/*  672: 598 */     String frostboltNoColour = frostbolt.replaceAll("&([0-9a-f])", "");
/*  673:     */     
/*  674: 600 */     int randomApplyChance = Math.abs(ItemLoreStats.plugin.getConfig().getInt("randomApplyChance") - 100);
/*  675:     */     
/*  676: 602 */     double armourValue = 0.0D;
/*  677: 603 */     double dodgeValue = 0.0D;
/*  678: 604 */     double blockValue = 0.0D;
/*  679: 605 */     double critChanceValue = 0.0D;
/*  680: 606 */     double critDamageValue = 0.0D;
/*  681: 607 */     double healthValue = 0.0D;
/*  682: 608 */     double healthRegenValue = 0.0D;
/*  683: 609 */     double lifeStealValue = 0.0D;
/*  684: 610 */     double reflectValue = 0.0D;
/*  685: 611 */     double fireValue = 0.0D;
/*  686: 612 */     double iceValue = 0.0D;
/*  687: 613 */     double poisonValue = 0.0D;
/*  688: 614 */     double witherValue = 0.0D;
/*  689: 615 */     double harmingValue = 0.0D;
/*  690: 616 */     double blindValue = 0.0D;
/*  691: 617 */     double xpMultiplierValue = 0.0D;
/*  692: 618 */     double movementSpeedValue = 0.0D;
/*  693:     */     
/*  694: 620 */     List setLoreList = new ArrayList(Arrays.asList(new String[] { "" }));
/*  695: 621 */     List highestValue = new ArrayList();
/*  696:     */     
/*  697: 623 */     int playerLevel = player.getLevel();
/*  698: 625 */     if (playerLevel < 1) {
/*  699: 626 */       playerLevel = 1;
/*  700:     */     } else {
/*  701: 628 */       playerLevel = player.getLevel();
/*  702:     */     }
/*  703:     */     try
/*  704:     */     {
/*  705: 632 */       this.PlayerDataConfig = new YamlConfiguration();
/*  706: 633 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entity.toString().toLowerCase() + ".yml"));
/*  707: 635 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.weaponspeed")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.weaponspeed").equals("0"))) {
/*  708: 637 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.weaponspeed").equalsIgnoreCase("slow"))
/*  709:     */         {
/*  710: 638 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.weaponspeedRandomApply"))
/*  711:     */           {
/*  712: 639 */             if (random(100) >= randomApplyChance) {
/*  713: 640 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  714:     */             }
/*  715:     */           }
/*  716:     */           else {
/*  717: 643 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  718:     */           }
/*  719:     */         }
/*  720: 645 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.weaponspeed").contains("fast"))
/*  721:     */         {
/*  722: 646 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.weaponspeedRandomApply"))
/*  723:     */           {
/*  724: 647 */             if (random(100) >= randomApplyChance) {
/*  725: 648 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  726:     */             }
/*  727:     */           }
/*  728:     */           else {
/*  729: 651 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  730:     */           }
/*  731:     */         }
/*  732: 654 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.weaponspeedRandomApply"))
/*  733:     */         {
/*  734: 655 */           if (random(100) >= randomApplyChance) {
/*  735: 656 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  736:     */           }
/*  737:     */         }
/*  738:     */         else {
/*  739: 659 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  740:     */         }
/*  741:     */       }
/*  742: 664 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.armour")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.armour").equals("0"))) {
/*  743: 666 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.armour").equalsIgnoreCase("player"))
/*  744:     */         {
/*  745: 667 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.armourRandomApply"))
/*  746:     */           {
/*  747: 668 */             if (random(100) >= randomApplyChance)
/*  748:     */             {
/*  749: 669 */               armourValue = playerLevel;
/*  750: 670 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  751:     */             }
/*  752:     */           }
/*  753:     */           else
/*  754:     */           {
/*  755: 673 */             armourValue = playerLevel;
/*  756: 674 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  757:     */           }
/*  758:     */         }
/*  759: 676 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.armour").contains("-player+"))
/*  760:     */         {
/*  761: 677 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.armourRandomApply"))
/*  762:     */           {
/*  763: 678 */             if (random(100) >= randomApplyChance)
/*  764:     */             {
/*  765: 679 */               armourValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "armour", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  766: 680 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  767:     */             }
/*  768:     */           }
/*  769:     */           else
/*  770:     */           {
/*  771: 683 */             armourValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "armour", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  772: 684 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  773:     */           }
/*  774:     */         }
/*  775: 687 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.armourRandomApply"))
/*  776:     */         {
/*  777: 688 */           if (random(100) >= randomApplyChance)
/*  778:     */           {
/*  779: 689 */             armourValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  780: 690 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  781:     */           }
/*  782:     */         }
/*  783:     */         else
/*  784:     */         {
/*  785: 693 */           armourValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  786: 694 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  787:     */         }
/*  788:     */       }
/*  789: 699 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.dodge")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.dodge").equals("0"))) {
/*  790: 701 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.dodge").equalsIgnoreCase("player"))
/*  791:     */         {
/*  792: 702 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.dodgeRandomApply"))
/*  793:     */           {
/*  794: 703 */             if (random(100) >= randomApplyChance)
/*  795:     */             {
/*  796: 704 */               dodgeValue = playerLevel;
/*  797: 705 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  798:     */             }
/*  799:     */           }
/*  800:     */           else
/*  801:     */           {
/*  802: 708 */             dodgeValue = playerLevel;
/*  803: 709 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  804:     */           }
/*  805:     */         }
/*  806: 711 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.dodge").contains("-player+"))
/*  807:     */         {
/*  808: 712 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.dodgeRandomApply"))
/*  809:     */           {
/*  810: 713 */             if (random(100) >= randomApplyChance)
/*  811:     */             {
/*  812: 714 */               dodgeValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "dodge", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  813: 715 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + dodgeValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  814:     */             }
/*  815:     */           }
/*  816:     */           else
/*  817:     */           {
/*  818: 718 */             dodgeValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "dodge", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  819: 719 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + dodgeValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  820:     */           }
/*  821:     */         }
/*  822: 722 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.dodgeRandomApply"))
/*  823:     */         {
/*  824: 723 */           if (random(100) >= randomApplyChance)
/*  825:     */           {
/*  826: 724 */             dodgeValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  827: 725 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + dodgeValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  828:     */           }
/*  829:     */         }
/*  830:     */         else
/*  831:     */         {
/*  832: 728 */           dodgeValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  833: 729 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + dodgeValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  834:     */         }
/*  835:     */       }
/*  836: 734 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.block")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.block").equals("0"))) {
/*  837: 736 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.block").equalsIgnoreCase("player"))
/*  838:     */         {
/*  839: 737 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blockRandomApply"))
/*  840:     */           {
/*  841: 738 */             if (random(100) >= randomApplyChance)
/*  842:     */             {
/*  843: 739 */               blockValue = playerLevel;
/*  844: 740 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  845:     */             }
/*  846:     */           }
/*  847:     */           else
/*  848:     */           {
/*  849: 743 */             blockValue = playerLevel;
/*  850: 744 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  851:     */           }
/*  852:     */         }
/*  853: 746 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.block").contains("-player+"))
/*  854:     */         {
/*  855: 747 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blockRandomApply"))
/*  856:     */           {
/*  857: 748 */             if (random(100) >= randomApplyChance)
/*  858:     */             {
/*  859: 749 */               blockValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "block", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.block").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  860: 750 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + blockValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  861:     */             }
/*  862:     */           }
/*  863:     */           else
/*  864:     */           {
/*  865: 753 */             blockValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "block", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.block").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  866: 754 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + blockValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  867:     */           }
/*  868:     */         }
/*  869: 757 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blockRandomApply"))
/*  870:     */         {
/*  871: 758 */           if (random(100) >= randomApplyChance)
/*  872:     */           {
/*  873: 759 */             blockValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.block").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.block").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  874: 760 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + blockValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  875:     */           }
/*  876:     */         }
/*  877:     */         else
/*  878:     */         {
/*  879: 763 */           blockValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.block").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.block").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  880: 764 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + blockValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  881:     */         }
/*  882:     */       }
/*  883: 769 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.damage")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.damage").equals("0"))) {
/*  884: 771 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.damage").equalsIgnoreCase("player"))
/*  885:     */         {
/*  886: 772 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.damageRandomApply"))
/*  887:     */           {
/*  888: 773 */             if (random(100) >= randomApplyChance) {
/*  889: 774 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + playerLevel);
/*  890:     */             }
/*  891:     */           }
/*  892:     */           else {
/*  893: 777 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + playerLevel);
/*  894:     */           }
/*  895:     */         }
/*  896: 779 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.damage").contains("-player+"))
/*  897:     */         {
/*  898: 780 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.damageRandomApply"))
/*  899:     */           {
/*  900: 781 */             if (random(100) >= randomApplyChance) {
/*  901: 782 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + getMinStat(player, entity.toString().toLowerCase(), "damage", dropChance) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + "-" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + getMaxStat(player, entity.toString().toLowerCase(), "damage", dropChance));
/*  902:     */             }
/*  903:     */           }
/*  904:     */           else {
/*  905: 785 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + getMinStat(player, entity.toString().toLowerCase(), "damage", dropChance) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + "-" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + getMaxStat(player, entity.toString().toLowerCase(), "damage", dropChance));
/*  906:     */           }
/*  907:     */         }
/*  908: 788 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.damageRandomApply"))
/*  909:     */         {
/*  910: 789 */           if (random(100) >= randomApplyChance) {
/*  911: 790 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + randomRange(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()).split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()).split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  912:     */           }
/*  913:     */         }
/*  914:     */         else {
/*  915: 793 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + randomRange(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()).split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()).split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  916:     */         }
/*  917:     */       }
/*  918: 798 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.critChance")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.critChance").equals("0"))) {
/*  919: 800 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.critChance").equalsIgnoreCase("player"))
/*  920:     */         {
/*  921: 801 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critChanceRandomApply"))
/*  922:     */           {
/*  923: 802 */             if (random(100) >= randomApplyChance)
/*  924:     */             {
/*  925: 803 */               critChanceValue = playerLevel;
/*  926: 804 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  927:     */             }
/*  928:     */           }
/*  929:     */           else
/*  930:     */           {
/*  931: 807 */             critChanceValue = playerLevel;
/*  932: 808 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  933:     */           }
/*  934:     */         }
/*  935: 810 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.critChance").contains("-player+"))
/*  936:     */         {
/*  937: 811 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critChanceRandomApply"))
/*  938:     */           {
/*  939: 812 */             if (random(100) >= randomApplyChance)
/*  940:     */             {
/*  941: 813 */               critChanceValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "critChance", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  942: 814 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + critChanceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  943:     */             }
/*  944:     */           }
/*  945:     */           else
/*  946:     */           {
/*  947: 817 */             critChanceValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "critChance", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  948: 818 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + critChanceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  949:     */           }
/*  950:     */         }
/*  951: 821 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critChanceRandomApply"))
/*  952:     */         {
/*  953: 822 */           if (random(100) >= randomApplyChance)
/*  954:     */           {
/*  955: 823 */             critChanceValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  956: 824 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + critChanceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  957:     */           }
/*  958:     */         }
/*  959:     */         else
/*  960:     */         {
/*  961: 827 */           critChanceValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  962: 828 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + critChanceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  963:     */         }
/*  964:     */       }
/*  965: 833 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.critDamage")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").equals("0"))) {
/*  966: 835 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").equalsIgnoreCase("player"))
/*  967:     */         {
/*  968: 836 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critDamageRandomApply"))
/*  969:     */           {
/*  970: 837 */             if (random(100) >= randomApplyChance)
/*  971:     */             {
/*  972: 838 */               critDamageValue = playerLevel;
/*  973: 839 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/*  974:     */             }
/*  975:     */           }
/*  976:     */           else
/*  977:     */           {
/*  978: 842 */             critDamageValue = playerLevel;
/*  979: 843 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/*  980:     */           }
/*  981:     */         }
/*  982: 845 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").contains("-player+"))
/*  983:     */         {
/*  984: 846 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critDamageRandomApply"))
/*  985:     */           {
/*  986: 847 */             if (random(100) >= randomApplyChance)
/*  987:     */             {
/*  988: 848 */               critDamageValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "critDamage", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  989: 849 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + critDamageValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/*  990:     */             }
/*  991:     */           }
/*  992:     */           else
/*  993:     */           {
/*  994: 852 */             critDamageValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "critDamage", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  995: 853 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + critDamageValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/*  996:     */           }
/*  997:     */         }
/*  998: 856 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critDamageRandomApply"))
/*  999:     */         {
/* 1000: 857 */           if (random(100) >= randomApplyChance)
/* 1001:     */           {
/* 1002: 858 */             critDamageValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1003: 859 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + critDamageValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/* 1004:     */           }
/* 1005:     */         }
/* 1006:     */         else
/* 1007:     */         {
/* 1008: 862 */           critDamageValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1009: 863 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + critDamageValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/* 1010:     */         }
/* 1011:     */       }
/* 1012: 868 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.health")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.health").equals("0"))) {
/* 1013: 870 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.health").equalsIgnoreCase("player"))
/* 1014:     */         {
/* 1015: 871 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRandomApply"))
/* 1016:     */           {
/* 1017: 872 */             if (random(100) >= randomApplyChance)
/* 1018:     */             {
/* 1019: 873 */               healthValue = playerLevel;
/* 1020: 874 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + playerLevel);
/* 1021:     */             }
/* 1022:     */           }
/* 1023:     */           else
/* 1024:     */           {
/* 1025: 877 */             healthValue = playerLevel;
/* 1026: 878 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + playerLevel);
/* 1027:     */           }
/* 1028:     */         }
/* 1029: 880 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.health").contains("-player+"))
/* 1030:     */         {
/* 1031: 881 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRandomApply"))
/* 1032:     */           {
/* 1033: 882 */             if (random(100) >= randomApplyChance)
/* 1034:     */             {
/* 1035: 883 */               healthValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "health", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.health").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1036: 884 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + healthValue);
/* 1037:     */             }
/* 1038:     */           }
/* 1039:     */           else
/* 1040:     */           {
/* 1041: 887 */             healthValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "health", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.health").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1042: 888 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + healthValue);
/* 1043:     */           }
/* 1044:     */         }
/* 1045: 891 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRandomApply"))
/* 1046:     */         {
/* 1047: 892 */           if (random(100) >= randomApplyChance)
/* 1048:     */           {
/* 1049: 893 */             healthValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.health").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.health").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1050: 894 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + healthValue);
/* 1051:     */           }
/* 1052:     */         }
/* 1053:     */         else
/* 1054:     */         {
/* 1055: 897 */           healthValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.health").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.health").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1056: 898 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + healthValue);
/* 1057:     */         }
/* 1058:     */       }
/* 1059: 903 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.healthRegen")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").equals("0"))) {
/* 1060: 905 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").equalsIgnoreCase("player"))
/* 1061:     */         {
/* 1062: 906 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRegenRandomApply"))
/* 1063:     */           {
/* 1064: 907 */             if (random(100) >= randomApplyChance)
/* 1065:     */             {
/* 1066: 908 */               healthRegenValue = playerLevel;
/* 1067: 909 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1068:     */             }
/* 1069:     */           }
/* 1070:     */           else
/* 1071:     */           {
/* 1072: 912 */             healthRegenValue = playerLevel;
/* 1073: 913 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1074:     */           }
/* 1075:     */         }
/* 1076: 915 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").contains("-player+"))
/* 1077:     */         {
/* 1078: 916 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRegenRandomApply"))
/* 1079:     */           {
/* 1080: 917 */             if (random(100) >= randomApplyChance)
/* 1081:     */             {
/* 1082: 918 */               healthRegenValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "healthRegen", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1083: 919 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + healthRegenValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1084:     */             }
/* 1085:     */           }
/* 1086:     */           else
/* 1087:     */           {
/* 1088: 922 */             healthRegenValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "healthRegen", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1089: 923 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + healthRegenValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1090:     */           }
/* 1091:     */         }
/* 1092: 926 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRegenRandomApply"))
/* 1093:     */         {
/* 1094: 927 */           if (random(100) >= randomApplyChance)
/* 1095:     */           {
/* 1096: 928 */             healthRegenValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1097: 929 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + healthRegenValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1098:     */           }
/* 1099:     */         }
/* 1100:     */         else
/* 1101:     */         {
/* 1102: 932 */           healthRegenValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1103: 933 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + healthRegenValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1104:     */         }
/* 1105:     */       }
/* 1106: 938 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.lifeSteal")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").equals("0"))) {
/* 1107: 940 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").equalsIgnoreCase("player"))
/* 1108:     */         {
/* 1109: 941 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.lifeStealRandomApply"))
/* 1110:     */           {
/* 1111: 942 */             if (random(100) >= randomApplyChance)
/* 1112:     */             {
/* 1113: 943 */               lifeStealValue = playerLevel;
/* 1114: 944 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1115:     */             }
/* 1116:     */           }
/* 1117:     */           else
/* 1118:     */           {
/* 1119: 947 */             lifeStealValue = playerLevel;
/* 1120: 948 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1121:     */           }
/* 1122:     */         }
/* 1123: 950 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").contains("-player+"))
/* 1124:     */         {
/* 1125: 951 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.lifeStealRandomApply"))
/* 1126:     */           {
/* 1127: 952 */             if (random(100) >= randomApplyChance)
/* 1128:     */             {
/* 1129: 953 */               lifeStealValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "lifeSteal", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1130: 954 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + lifeStealValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1131:     */             }
/* 1132:     */           }
/* 1133:     */           else
/* 1134:     */           {
/* 1135: 957 */             lifeStealValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "lifeSteal", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1136: 958 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + lifeStealValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1137:     */           }
/* 1138:     */         }
/* 1139: 961 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.lifeStealRandomApply"))
/* 1140:     */         {
/* 1141: 962 */           if (random(100) >= randomApplyChance)
/* 1142:     */           {
/* 1143: 963 */             lifeStealValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1144: 964 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + lifeStealValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1145:     */           }
/* 1146:     */         }
/* 1147:     */         else
/* 1148:     */         {
/* 1149: 967 */           lifeStealValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1150: 968 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + lifeStealValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1151:     */         }
/* 1152:     */       }
/* 1153: 973 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.reflect")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.reflect").equals("0"))) {
/* 1154: 975 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.reflect").equalsIgnoreCase("player"))
/* 1155:     */         {
/* 1156: 976 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.reflectRandomApply"))
/* 1157:     */           {
/* 1158: 977 */             if (random(100) >= randomApplyChance)
/* 1159:     */             {
/* 1160: 978 */               reflectValue = playerLevel;
/* 1161: 979 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1162:     */             }
/* 1163:     */           }
/* 1164:     */           else
/* 1165:     */           {
/* 1166: 982 */             reflectValue = playerLevel;
/* 1167: 983 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1168:     */           }
/* 1169:     */         }
/* 1170: 985 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.reflect").contains("-player+"))
/* 1171:     */         {
/* 1172: 986 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.reflectRandomApply"))
/* 1173:     */           {
/* 1174: 987 */             if (random(100) >= randomApplyChance)
/* 1175:     */             {
/* 1176: 988 */               reflectValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "reflect", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1177: 989 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + reflectValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1178:     */             }
/* 1179:     */           }
/* 1180:     */           else
/* 1181:     */           {
/* 1182: 992 */             reflectValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "reflect", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1183: 993 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + reflectValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1184:     */           }
/* 1185:     */         }
/* 1186: 996 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.reflectRandomApply"))
/* 1187:     */         {
/* 1188: 997 */           if (random(100) >= randomApplyChance)
/* 1189:     */           {
/* 1190: 998 */             reflectValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1191: 999 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + reflectValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1192:     */           }
/* 1193:     */         }
/* 1194:     */         else
/* 1195:     */         {
/* 1196:1002 */           reflectValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1197:1003 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + reflectValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1198:     */         }
/* 1199:     */       }
/* 1200:1008 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.fire")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.fire").equals("0"))) {
/* 1201:1010 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.fire").equalsIgnoreCase("player"))
/* 1202:     */         {
/* 1203:1011 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.fireRandomApply"))
/* 1204:     */           {
/* 1205:1012 */             if (random(100) >= randomApplyChance)
/* 1206:     */             {
/* 1207:1013 */               fireValue = playerLevel;
/* 1208:1014 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1209:     */             }
/* 1210:     */           }
/* 1211:     */           else
/* 1212:     */           {
/* 1213:1017 */             fireValue = playerLevel;
/* 1214:1018 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1215:     */           }
/* 1216:     */         }
/* 1217:1020 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.fire").contains("-player+"))
/* 1218:     */         {
/* 1219:1021 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.fireRandomApply"))
/* 1220:     */           {
/* 1221:1022 */             if (random(100) >= randomApplyChance)
/* 1222:     */             {
/* 1223:1023 */               fireValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "fire", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1224:1024 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + fireValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1225:     */             }
/* 1226:     */           }
/* 1227:     */           else
/* 1228:     */           {
/* 1229:1027 */             fireValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "fire", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1230:1028 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + fireValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1231:     */           }
/* 1232:     */         }
/* 1233:1031 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.fireRandomApply"))
/* 1234:     */         {
/* 1235:1032 */           if (random(100) >= randomApplyChance)
/* 1236:     */           {
/* 1237:1033 */             fireValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1238:1034 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + fireValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1239:     */           }
/* 1240:     */         }
/* 1241:     */         else
/* 1242:     */         {
/* 1243:1037 */           fireValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1244:1038 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + fireValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1245:     */         }
/* 1246:     */       }
/* 1247:1043 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.ice")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.ice").equals("0"))) {
/* 1248:1045 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.ice").equalsIgnoreCase("player"))
/* 1249:     */         {
/* 1250:1046 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.iceRandomApply"))
/* 1251:     */           {
/* 1252:1047 */             if (random(100) >= randomApplyChance)
/* 1253:     */             {
/* 1254:1048 */               iceValue = playerLevel;
/* 1255:1049 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1256:     */             }
/* 1257:     */           }
/* 1258:     */           else
/* 1259:     */           {
/* 1260:1052 */             iceValue = playerLevel;
/* 1261:1053 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1262:     */           }
/* 1263:     */         }
/* 1264:1055 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.ice").contains("-player+"))
/* 1265:     */         {
/* 1266:1056 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.iceRandomApply"))
/* 1267:     */           {
/* 1268:1057 */             if (random(100) >= randomApplyChance)
/* 1269:     */             {
/* 1270:1058 */               iceValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "ice", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1271:1059 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + iceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1272:     */             }
/* 1273:     */           }
/* 1274:     */           else
/* 1275:     */           {
/* 1276:1062 */             iceValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "ice", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1277:1063 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + iceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1278:     */           }
/* 1279:     */         }
/* 1280:1066 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.iceRandomApply"))
/* 1281:     */         {
/* 1282:1067 */           if (random(100) >= randomApplyChance)
/* 1283:     */           {
/* 1284:1068 */             iceValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1285:1069 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + iceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1286:     */           }
/* 1287:     */         }
/* 1288:     */         else
/* 1289:     */         {
/* 1290:1072 */           iceValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1291:1073 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + iceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1292:     */         }
/* 1293:     */       }
/* 1294:1078 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.poison")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.poison").equals("0"))) {
/* 1295:1080 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.poison").equalsIgnoreCase("player"))
/* 1296:     */         {
/* 1297:1081 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.poisonRandomApply"))
/* 1298:     */           {
/* 1299:1082 */             if (random(100) >= randomApplyChance)
/* 1300:     */             {
/* 1301:1083 */               poisonValue = playerLevel;
/* 1302:1084 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1303:     */             }
/* 1304:     */           }
/* 1305:     */           else
/* 1306:     */           {
/* 1307:1087 */             poisonValue = playerLevel;
/* 1308:1088 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1309:     */           }
/* 1310:     */         }
/* 1311:1090 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.poison").contains("-player+"))
/* 1312:     */         {
/* 1313:1091 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.poisonRandomApply"))
/* 1314:     */           {
/* 1315:1092 */             if (random(100) >= randomApplyChance)
/* 1316:     */             {
/* 1317:1093 */               poisonValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "poison", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1318:1094 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + poisonValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1319:     */             }
/* 1320:     */           }
/* 1321:     */           else
/* 1322:     */           {
/* 1323:1097 */             poisonValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "poison", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1324:1098 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + poisonValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1325:     */           }
/* 1326:     */         }
/* 1327:1101 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.poisonRandomApply"))
/* 1328:     */         {
/* 1329:1102 */           if (random(100) >= randomApplyChance)
/* 1330:     */           {
/* 1331:1103 */             poisonValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1332:1104 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + poisonValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1333:     */           }
/* 1334:     */         }
/* 1335:     */         else
/* 1336:     */         {
/* 1337:1107 */           poisonValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1338:1108 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + poisonValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1339:     */         }
/* 1340:     */       }
/* 1341:1113 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.wither")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.wither").equals("0"))) {
/* 1342:1115 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.wither").equalsIgnoreCase("player"))
/* 1343:     */         {
/* 1344:1116 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.witherRandomApply"))
/* 1345:     */           {
/* 1346:1117 */             if (random(100) >= randomApplyChance)
/* 1347:     */             {
/* 1348:1118 */               witherValue = playerLevel;
/* 1349:1119 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1350:     */             }
/* 1351:     */           }
/* 1352:     */           else
/* 1353:     */           {
/* 1354:1122 */             witherValue = playerLevel;
/* 1355:1123 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1356:     */           }
/* 1357:     */         }
/* 1358:1125 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.wither").contains("-player+"))
/* 1359:     */         {
/* 1360:1126 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.witherRandomApply"))
/* 1361:     */           {
/* 1362:1127 */             if (random(100) >= randomApplyChance)
/* 1363:     */             {
/* 1364:1128 */               witherValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "wither", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1365:1129 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + witherValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1366:     */             }
/* 1367:     */           }
/* 1368:     */           else
/* 1369:     */           {
/* 1370:1132 */             witherValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "wither", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1371:1133 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + witherValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1372:     */           }
/* 1373:     */         }
/* 1374:1136 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.witherRandomApply"))
/* 1375:     */         {
/* 1376:1137 */           if (random(100) >= randomApplyChance)
/* 1377:     */           {
/* 1378:1138 */             witherValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1379:1139 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + witherValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1380:     */           }
/* 1381:     */         }
/* 1382:     */         else
/* 1383:     */         {
/* 1384:1142 */           witherValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1385:1143 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + witherValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1386:     */         }
/* 1387:     */       }
/* 1388:1148 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.harming")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.harming").equals("0"))) {
/* 1389:1150 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.harming").equalsIgnoreCase("player"))
/* 1390:     */         {
/* 1391:1151 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.harmingRandomApply"))
/* 1392:     */           {
/* 1393:1152 */             if (random(100) >= randomApplyChance)
/* 1394:     */             {
/* 1395:1153 */               harmingValue = playerLevel;
/* 1396:1154 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1397:     */             }
/* 1398:     */           }
/* 1399:     */           else
/* 1400:     */           {
/* 1401:1157 */             harmingValue = playerLevel;
/* 1402:1158 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1403:     */           }
/* 1404:     */         }
/* 1405:1160 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.harming").contains("-player+"))
/* 1406:     */         {
/* 1407:1161 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.harmingRandomApply"))
/* 1408:     */           {
/* 1409:1162 */             if (random(100) >= randomApplyChance)
/* 1410:     */             {
/* 1411:1163 */               harmingValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "harming", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1412:1164 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + harmingValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1413:     */             }
/* 1414:     */           }
/* 1415:     */           else
/* 1416:     */           {
/* 1417:1167 */             harmingValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "harming", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1418:1168 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + harmingValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1419:     */           }
/* 1420:     */         }
/* 1421:1171 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.harmingRandomApply"))
/* 1422:     */         {
/* 1423:1172 */           if (random(100) >= randomApplyChance)
/* 1424:     */           {
/* 1425:1173 */             harmingValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1426:1174 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + harmingValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1427:     */           }
/* 1428:     */         }
/* 1429:     */         else
/* 1430:     */         {
/* 1431:1177 */           harmingValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1432:1178 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + harmingValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1433:     */         }
/* 1434:     */       }
/* 1435:1183 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.blind")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.blind").equals("0"))) {
/* 1436:1185 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.blind").equalsIgnoreCase("player"))
/* 1437:     */         {
/* 1438:1186 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blindRandomApply"))
/* 1439:     */           {
/* 1440:1187 */             if (random(100) >= randomApplyChance)
/* 1441:     */             {
/* 1442:1188 */               blindValue = playerLevel;
/* 1443:1189 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1444:     */             }
/* 1445:     */           }
/* 1446:     */           else
/* 1447:     */           {
/* 1448:1192 */             blindValue = playerLevel;
/* 1449:1193 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1450:     */           }
/* 1451:     */         }
/* 1452:1195 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.blind").contains("-player+"))
/* 1453:     */         {
/* 1454:1196 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blindRandomApply"))
/* 1455:     */           {
/* 1456:1197 */             if (random(100) >= randomApplyChance)
/* 1457:     */             {
/* 1458:1198 */               blindValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "blind", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1459:1199 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + blindValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1460:     */             }
/* 1461:     */           }
/* 1462:     */           else
/* 1463:     */           {
/* 1464:1202 */             blindValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "blind", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1465:1203 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + blindValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1466:     */           }
/* 1467:     */         }
/* 1468:1206 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blindRandomApply"))
/* 1469:     */         {
/* 1470:1207 */           if (random(100) >= randomApplyChance)
/* 1471:     */           {
/* 1472:1208 */             blindValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1473:1209 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + blindValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1474:     */           }
/* 1475:     */         }
/* 1476:     */         else
/* 1477:     */         {
/* 1478:1212 */           blindValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1479:1213 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + blindValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1480:     */         }
/* 1481:     */       }
/* 1482:1218 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.xpMultiplier")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").equals("0"))) {
/* 1483:1220 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").equalsIgnoreCase("player"))
/* 1484:     */         {
/* 1485:1221 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpMultiplierRandomApply"))
/* 1486:     */           {
/* 1487:1222 */             if (random(100) >= randomApplyChance)
/* 1488:     */             {
/* 1489:1223 */               xpMultiplierValue = playerLevel;
/* 1490:1224 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1491:     */             }
/* 1492:     */           }
/* 1493:     */           else
/* 1494:     */           {
/* 1495:1227 */             xpMultiplierValue = playerLevel;
/* 1496:1228 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1497:     */           }
/* 1498:     */         }
/* 1499:1230 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").contains("-player+"))
/* 1500:     */         {
/* 1501:1231 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpMultiplierRandomApply"))
/* 1502:     */           {
/* 1503:1232 */             if (random(100) >= randomApplyChance)
/* 1504:     */             {
/* 1505:1233 */               xpMultiplierValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "xpMultiplier", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1506:1234 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + xpMultiplierValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1507:     */             }
/* 1508:     */           }
/* 1509:     */           else
/* 1510:     */           {
/* 1511:1237 */             xpMultiplierValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "xpMultiplier", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1512:1238 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + xpMultiplierValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1513:     */           }
/* 1514:     */         }
/* 1515:1241 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpMultiplierRandomApply"))
/* 1516:     */         {
/* 1517:1242 */           if (random(100) >= randomApplyChance)
/* 1518:     */           {
/* 1519:1243 */             xpMultiplierValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1520:1244 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + xpMultiplierValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1521:     */           }
/* 1522:     */         }
/* 1523:     */         else
/* 1524:     */         {
/* 1525:1247 */           xpMultiplierValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1526:1248 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + xpMultiplierValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1527:     */         }
/* 1528:     */       }
/* 1529:1253 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.movementSpeed")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").equals("0"))) {
/* 1530:1255 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").equalsIgnoreCase("player"))
/* 1531:     */         {
/* 1532:1256 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.movementSpeedRandomApply"))
/* 1533:     */           {
/* 1534:1257 */             if (random(100) >= randomApplyChance)
/* 1535:     */             {
/* 1536:1258 */               movementSpeedValue = playerLevel;
/* 1537:1259 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1538:     */             }
/* 1539:     */           }
/* 1540:     */           else
/* 1541:     */           {
/* 1542:1262 */             movementSpeedValue = playerLevel;
/* 1543:1263 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1544:     */           }
/* 1545:     */         }
/* 1546:1265 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").contains("-player+"))
/* 1547:     */         {
/* 1548:1266 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.movementSpeedRandomApply"))
/* 1549:     */           {
/* 1550:1267 */             if (random(100) >= randomApplyChance)
/* 1551:     */             {
/* 1552:1268 */               movementSpeedValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "movementSpeed", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1553:1269 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + movementSpeedValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1554:     */             }
/* 1555:     */           }
/* 1556:     */           else
/* 1557:     */           {
/* 1558:1272 */             movementSpeedValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "movementSpeed", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1559:1273 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + movementSpeedValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1560:     */           }
/* 1561:     */         }
/* 1562:1276 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.movementSpeedRandomApply"))
/* 1563:     */         {
/* 1564:1277 */           if (random(100) >= randomApplyChance)
/* 1565:     */           {
/* 1566:1278 */             movementSpeedValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1567:1279 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + movementSpeedValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1568:     */           }
/* 1569:     */         }
/* 1570:     */         else
/* 1571:     */         {
/* 1572:1282 */           movementSpeedValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1573:1283 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + movementSpeedValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1574:     */         }
/* 1575:     */       }
/* 1576:1288 */       if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO"))
/* 1577:     */       {
/* 1578:1289 */         setLoreList.add("");
/* 1579:     */         
/* 1580:1291 */         String selectedDurability = "";
/* 1581:1293 */         if ((this.PlayerDataConfig.contains(dropChance + ".properties.durability")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.durability").equals("0"))) {
/* 1582:1295 */           if (this.PlayerDataConfig.getString(dropChance + ".properties.durability").equalsIgnoreCase("player"))
/* 1583:     */           {
/* 1584:1296 */             if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.durabilityRandomApply"))
/* 1585:     */             {
/* 1586:1297 */               if (random(100) >= randomApplyChance) {
/* 1587:1298 */                 setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + playerLevel);
/* 1588:     */               }
/* 1589:     */             }
/* 1590:     */             else {
/* 1591:1301 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + playerLevel);
/* 1592:     */             }
/* 1593:     */           }
/* 1594:1303 */           else if (this.PlayerDataConfig.getString(dropChance + ".properties.durability").contains("-player+"))
/* 1595:     */           {
/* 1596:1304 */             if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.durabilityRandomApply"))
/* 1597:     */             {
/* 1598:1305 */               if (random(100) >= randomApplyChance)
/* 1599:     */               {
/* 1600:1306 */                 selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 1601:1307 */                 setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability);
/* 1602:     */               }
/* 1603:     */             }
/* 1604:     */             else
/* 1605:     */             {
/* 1606:1310 */               selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 1607:1311 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability);
/* 1608:     */             }
/* 1609:     */           }
/* 1610:1314 */           else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.durabilityRandomApply"))
/* 1611:     */           {
/* 1612:1315 */             if (random(100) >= randomApplyChance)
/* 1613:     */             {
/* 1614:1316 */               selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 1615:1317 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability);
/* 1616:     */             }
/* 1617:     */           }
/* 1618:     */           else
/* 1619:     */           {
/* 1620:1320 */             selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 1621:1321 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability);
/* 1622:     */           }
/* 1623:     */         }
/* 1624:     */       }
/* 1625:1328 */       if (ItemLoreStats.plugin.getConfig().getBoolean("addRandomLore")) {
/* 1626:1329 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.addRandomLoreRandomApply"))
/* 1627:     */         {
/* 1628:1330 */           if (random(100) >= randomApplyChance)
/* 1629:     */           {
/* 1630:1331 */             setLoreList.add("");
/* 1631:1332 */             setLoreList.add(randomLore(material));
/* 1632:     */           }
/* 1633:     */         }
/* 1634:     */         else
/* 1635:     */         {
/* 1636:1335 */           setLoreList.add("");
/* 1637:1336 */           setLoreList.add(randomLore(material));
/* 1638:     */         }
/* 1639:     */       }
/* 1640:1340 */       setLoreList.add("");
/* 1641:1342 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.xpLevel")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.xpLevel").equals("0"))) {
/* 1642:1344 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.xpLevel").equalsIgnoreCase("player"))
/* 1643:     */         {
/* 1644:1345 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpLevelRandomApply"))
/* 1645:     */           {
/* 1646:1346 */             if (random(100) >= randomApplyChance) {
/* 1647:1347 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1648:     */             }
/* 1649:     */           }
/* 1650:     */           else {
/* 1651:1350 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1652:     */           }
/* 1653:     */         }
/* 1654:1352 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.xpLevel").contains("-player+"))
/* 1655:     */         {
/* 1656:1353 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpLevelRandomApply"))
/* 1657:     */           {
/* 1658:1354 */             if (random(100) >= randomApplyChance) {
/* 1659:1355 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + randomRangeInt(getMinStatInt(player, entity.toString().toLowerCase(), "xpLevel", dropChance), playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("\\+")[1].replaceAll("&([0-9a-f])", "").trim())) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1660:     */             }
/* 1661:     */           }
/* 1662:     */           else {
/* 1663:1358 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + randomRangeInt(getMinStatInt(player, entity.toString().toLowerCase(), "xpLevel", dropChance), playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("\\+")[1].replaceAll("&([0-9a-f])", "").trim())) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1664:     */           }
/* 1665:     */         }
/* 1666:1361 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpLevelRandomApply"))
/* 1667:     */         {
/* 1668:1362 */           if (random(100) >= randomApplyChance) {
/* 1669:1363 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("-")[0].replaceAll("&([0-9a-f])", "").trim())) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1670:     */           }
/* 1671:     */         }
/* 1672:     */         else {
/* 1673:1366 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("-")[0].replaceAll("&([0-9a-f])", "").trim())) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1674:     */         }
/* 1675:     */       }
/* 1676:1371 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.class")) && (!this.PlayerDataConfig.getString(dropChance + ".properties.class").equals("0"))) {
/* 1677:1373 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.classRandomApply"))
/* 1678:     */         {
/* 1679:1374 */           if (random(100) >= randomApplyChance)
/* 1680:     */           {
/* 1681:1375 */             if (this.PlayerDataConfig.getString(dropChance + ".properties.class").equalsIgnoreCase("random")) {
/* 1682:1376 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(randomClass())));
/* 1683:     */             } else {
/* 1684:1378 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(className)));
/* 1685:     */             }
/* 1686:     */           }
/* 1687:1381 */           else if (this.PlayerDataConfig.getString(dropChance + ".properties.class").equalsIgnoreCase("random")) {
/* 1688:1382 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(randomClass())));
/* 1689:     */           } else {
/* 1690:1384 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(className)));
/* 1691:     */           }
/* 1692:     */         }
/* 1693:1388 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.class").equalsIgnoreCase("random")) {
/* 1694:1389 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(randomClass())));
/* 1695:     */         } else {
/* 1696:1391 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(className)));
/* 1697:     */         }
/* 1698:     */       }
/* 1699:1396 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.soulbound")) && (this.PlayerDataConfig.getBoolean(dropChance + ".properties.soulbound"))) {
/* 1700:1398 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.soulboundRandomApply"))
/* 1701:     */         {
/* 1702:1399 */           if (random(100) >= randomApplyChance) {
/* 1703:1400 */             setLoreList.add(this.util_Colours.replaceTooltipColour(soulbound) + " " + player.getName());
/* 1704:     */           }
/* 1705:     */         }
/* 1706:     */         else {
/* 1707:1403 */           setLoreList.add(this.util_Colours.replaceTooltipColour(soulbound) + " " + player.getName());
/* 1708:     */         }
/* 1709:     */       }
/* 1710:1408 */       if ((this.PlayerDataConfig.contains(dropChance + ".spells.tnt")) && (this.PlayerDataConfig.getBoolean(dropChance + ".spells.tnt"))) {
/* 1711:1410 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.tntRandomApply"))
/* 1712:     */         {
/* 1713:1411 */           if (random(100) >= randomApplyChance) {
/* 1714:1412 */             setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + tntNoColour);
/* 1715:     */           }
/* 1716:     */         }
/* 1717:     */         else {
/* 1718:1415 */           setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + tntNoColour);
/* 1719:     */         }
/* 1720:     */       }
/* 1721:1420 */       if ((this.PlayerDataConfig.contains(dropChance + ".spells.fireball")) && (this.PlayerDataConfig.getBoolean(dropChance + ".spells.fireball"))) {
/* 1722:1422 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.fireballRandomApply"))
/* 1723:     */         {
/* 1724:1423 */           if (random(100) >= randomApplyChance) {
/* 1725:1424 */             setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + fireballNoColour);
/* 1726:     */           }
/* 1727:     */         }
/* 1728:     */         else {
/* 1729:1427 */           setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + fireballNoColour);
/* 1730:     */         }
/* 1731:     */       }
/* 1732:1432 */       if ((this.PlayerDataConfig.contains(dropChance + ".spells.lightning")) && (this.PlayerDataConfig.getBoolean(dropChance + ".spells.lightning"))) {
/* 1733:1434 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.lightningRandomApply"))
/* 1734:     */         {
/* 1735:1435 */           if (random(100) >= randomApplyChance) {
/* 1736:1436 */             setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + lightningNoColour);
/* 1737:     */           }
/* 1738:     */         }
/* 1739:     */         else {
/* 1740:1439 */           setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + lightningNoColour);
/* 1741:     */         }
/* 1742:     */       }
/* 1743:1444 */       if ((this.PlayerDataConfig.contains(dropChance + ".spells.frostbolt")) && (this.PlayerDataConfig.getBoolean(dropChance + ".spells.frostbolt"))) {
/* 1744:1446 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.frostboltRandomApply"))
/* 1745:     */         {
/* 1746:1447 */           if (random(100) >= randomApplyChance) {
/* 1747:1448 */             setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + frostboltNoColour);
/* 1748:     */           }
/* 1749:     */         }
/* 1750:     */         else {
/* 1751:1451 */           setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + frostboltNoColour);
/* 1752:     */         }
/* 1753:     */       }
/* 1754:1456 */       highestValue.add(Double.valueOf(armourValue));
/* 1755:1457 */       highestValue.add(Double.valueOf(dodgeValue));
/* 1756:1458 */       highestValue.add(Double.valueOf(blockValue));
/* 1757:1459 */       highestValue.add(Double.valueOf(critChanceValue));
/* 1758:1460 */       highestValue.add(Double.valueOf(critDamageValue));
/* 1759:1461 */       highestValue.add(Double.valueOf(healthRegenValue));
/* 1760:1462 */       highestValue.add(Double.valueOf(lifeStealValue));
/* 1761:1463 */       highestValue.add(Double.valueOf(reflectValue));
/* 1762:1464 */       highestValue.add(Double.valueOf(fireValue));
/* 1763:1465 */       highestValue.add(Double.valueOf(iceValue));
/* 1764:1466 */       highestValue.add(Double.valueOf(poisonValue));
/* 1765:1467 */       highestValue.add(Double.valueOf(witherValue));
/* 1766:1468 */       highestValue.add(Double.valueOf(harmingValue));
/* 1767:1469 */       highestValue.add(Double.valueOf(blindValue));
/* 1768:1470 */       highestValue.add(Double.valueOf(xpMultiplierValue));
/* 1769:1471 */       highestValue.add(Double.valueOf(movementSpeedValue));
/* 1770:1472 */       highestValue.add(Double.valueOf(1.0D));
/* 1771:     */       
/* 1772:1474 */       double maxValue = ((Double)Collections.max(highestValue)).doubleValue();
/* 1773:1476 */       while (highestValue.contains(Double.valueOf(maxValue)))
/* 1774:     */       {
/* 1775:1477 */         int i = highestValue.indexOf(Double.valueOf(maxValue));
/* 1776:1478 */         if (highestValue.indexOf(Double.valueOf(maxValue)) == 0) {
/* 1777:1479 */           setLoreList.add("armour");
/* 1778:1480 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 1) {
/* 1779:1481 */           setLoreList.add("dodge");
/* 1780:1482 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 2) {
/* 1781:1483 */           setLoreList.add("block");
/* 1782:1484 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 3) {
/* 1783:1485 */           setLoreList.add("critChance");
/* 1784:1486 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 4) {
/* 1785:1487 */           setLoreList.add("critDamage");
/* 1786:1488 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 5) {
/* 1787:1489 */           setLoreList.add("healthRegen");
/* 1788:1490 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 6) {
/* 1789:1491 */           setLoreList.add("lifeSteal");
/* 1790:1492 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 7) {
/* 1791:1493 */           setLoreList.add("reflect");
/* 1792:1494 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 8) {
/* 1793:1495 */           setLoreList.add("fire");
/* 1794:1496 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 9) {
/* 1795:1497 */           setLoreList.add("ice");
/* 1796:1498 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 10) {
/* 1797:1499 */           setLoreList.add("poison");
/* 1798:1500 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 11) {
/* 1799:1501 */           setLoreList.add("wither");
/* 1800:1502 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 12) {
/* 1801:1503 */           setLoreList.add("harming");
/* 1802:1504 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 13) {
/* 1803:1505 */           setLoreList.add("blind");
/* 1804:1506 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 14) {
/* 1805:1507 */           setLoreList.add("xpMultiplier");
/* 1806:1508 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 15) {
/* 1807:1509 */           setLoreList.add("movementSpeed");
/* 1808:1510 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 16) {
/* 1809:1511 */           setLoreList.add(".");
/* 1810:     */         }
/* 1811:1514 */         highestValue.set(i, Double.valueOf(-1.0D));
/* 1812:     */       }
/* 1813:1517 */       return setLoreList;
/* 1814:     */     }
/* 1815:     */     catch (Exception e)
/* 1816:     */     {
/* 1817:1520 */       e.printStackTrace();
/* 1818:1521 */       System.out.println("*********** Failed to load lore from " + entity + " file! ***********");
/* 1819:     */     }
/* 1820:1524 */     return setLoreList;
/* 1821:     */   }
/* 1822:     */   
/* 1823:     */   public ItemStack gearGenerator(Player player, String entityType, String dropChance)
/* 1824:     */   {
/* 1825:     */     try
/* 1826:     */     {
/* 1827:1531 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1828:1532 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entityType + ".yml"));
/* 1829:     */       
/* 1830:1534 */       Material generateMaterial = idToMaterial(this.PlayerDataConfig.getInt(dropChance + ".itemId"));
/* 1831:     */       
/* 1832:1536 */       ItemStack createItem = new ItemStack(generateMaterial, 1);
/* 1833:1537 */       ItemMeta createdItemMeta = createItem.getItemMeta();
/* 1834:     */       
/* 1835:1539 */       List generateLore = setLore(player, entityType, dropChance, generateMaterial);
/* 1836:     */       
/* 1837:1541 */       String setPrefix = prefix(entityType, dropChance);
/* 1838:1542 */       String setSuffix = suffix(entityType, dropChance);
/* 1839:1544 */       if (this.PlayerDataConfig.getString(dropChance + ".prefix").equalsIgnoreCase("stat"))
/* 1840:     */       {
/* 1841:1545 */         int selectRandomPrefixFromStat = randomKeySelection(ItemLoreStats.plugin.getConfig().getStringList("prefix." + ((String)generateLore.get(generateLore.size() - 1)).toString()).size());
/* 1842:1546 */         setPrefix = this.util_Colours.replaceTooltipColour(ItemLoreStats.plugin.getConfig().getStringList("prefix." + ((String)generateLore.get(generateLore.size() - 1)).toString()).toString().split(",")[selectRandomPrefixFromStat].replaceAll("\\[", "").replaceAll("\\]", "").trim());
/* 1843:     */       }
/* 1844:1549 */       if (this.PlayerDataConfig.getString(dropChance + ".suffix").equalsIgnoreCase("stat"))
/* 1845:     */       {
/* 1846:1550 */         int selectRandomSuffixFromStat = randomKeySelection(ItemLoreStats.plugin.getConfig().getStringList("suffix." + ((String)generateLore.get(generateLore.size() - 1)).toString()).size());
/* 1847:1551 */         setSuffix = this.util_Colours.replaceTooltipColour(ItemLoreStats.plugin.getConfig().getStringList("suffix." + ((String)generateLore.get(generateLore.size() - 1)).toString()).toString().split(",")[selectRandomSuffixFromStat].replaceAll("\\[", "").replaceAll("\\]", "").trim());
/* 1848:     */       }
/* 1849:1554 */       generateLore.remove(generateLore.size() - 1);
/* 1850:     */       
/* 1851:1556 */       createdItemMeta.setDisplayName(this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".nameColour").toString()) + setPrefix + " " + setType(createItem) + " " + setSuffix));
/* 1852:     */       
/* 1853:1558 */       generateLore.add(0, "");
/* 1854:1559 */       generateLore.remove(1);
/* 1855:     */       
/* 1856:1561 */       createdItemMeta.setLore(generateLore);
/* 1857:1562 */       createItem.setItemMeta(createdItemMeta);
/* 1858:     */       
/* 1859:1564 */       return createItem;
/* 1860:     */     }
/* 1861:     */     catch (Exception e)
/* 1862:     */     {
/* 1863:1566 */       e.printStackTrace();
/* 1864:1567 */       System.out.println("*********** Failed to create ItemStack for " + entityType + "! ***********");
/* 1865:     */     }
/* 1866:1570 */     return new ItemStack(Material.POTATO);
/* 1867:     */   }
/* 1868:     */   
/* 1869:     */   public void removeArmourDrop(LivingEntity mob)
/* 1870:     */   {
/* 1871:1575 */     LivingEntity entity = mob;
/* 1872:1577 */     if ((entity instanceof Player)) {
/* 1873:1578 */       return;
/* 1874:     */     }
/* 1875:1580 */     entity.getEquipment().setHelmetDropChance(0.0F);
/* 1876:1581 */     entity.getEquipment().setChestplateDropChance(0.0F);
/* 1877:1582 */     entity.getEquipment().setLeggingsDropChance(0.0F);
/* 1878:1583 */     entity.getEquipment().setBootsDropChance(0.0F);
/* 1879:1584 */     entity.getEquipment().setItemInHandDropChance(0.0F);
/* 1880:     */   }
/* 1881:     */   
/* 1882:     */   @EventHandler
/* 1883:     */   public void dropGear(EntityDeathEvent mob)
/* 1884:     */   {
/* 1885:1590 */     if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(mob.getEntity().getWorld().getName()))
/* 1886:     */     {
/* 1887:1591 */       if (!ItemLoreStats.plugin.getConfig().getBoolean("dropCustomILSLoot")) {
/* 1888:1591 */         return;
/* 1889:     */       }
/* 1890:1592 */       removeArmourDrop(mob.getEntity());
/* 1891:1594 */       if ((mob.getEntity().getKiller() instanceof Player)) {
/* 1892:1596 */         if (new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + mob.getEntity().getCustomName() + ".yml").exists()) {
/* 1893:     */           try
/* 1894:     */           {
/* 1895:1599 */             this.PlayerDataConfig = new YamlConfiguration();
/* 1896:1600 */             this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + mob.getEntity().getCustomName() + ".yml"));
/* 1897:     */             
/* 1898:1602 */             int dropChance = random(100);
/* 1899:     */             
/* 1900:1604 */             int i = this.PlayerDataConfig.getKeys(false).size() - 1;
/* 1901:     */             do
/* 1902:     */             {
/* 1903:1605 */               if (dropChance <= Integer.parseInt(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()))
/* 1904:     */               {
/* 1905:1606 */                 int selectRandomDrop = randomKeySelection(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).size());
/* 1906:1607 */                 mob.getDrops().add(gearGenerator(mob.getEntity().getKiller(), mob.getEntity().getCustomName(), this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim() + "." + this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()));
/* 1907:1608 */                 if (this.PlayerDataConfig.getString(String.valueOf(new StringBuilder(String.valueOf(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim())).append(".").append(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()).toString()) + ".properties.droppedXp") != null) {
/* 1908:1609 */                   mob.setDroppedExp(Integer.parseInt(this.PlayerDataConfig.getString(String.valueOf(new StringBuilder(String.valueOf(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim())).append(".").append(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()).toString()) + ".properties.droppedXp")));
/* 1909:     */                 }
/* 1910:     */               }
/* 1911:     */               else
/* 1912:     */               {
/* 1913:1613 */                 i--;
/* 1914:1613 */                 if (i < 0) {
/* 1915:     */                   break;
/* 1916:     */                 }
/* 1917:     */               }
/* 1918:1613 */             } while (i <= this.PlayerDataConfig.getKeys(false).size() - 1);
/* 1919:     */           }
/* 1920:     */           catch (Exception e)
/* 1921:     */           {
/* 1922:1617 */             e.printStackTrace();
/* 1923:1618 */             System.out.println("*********** Failed to drop gear from " + mob.getEntity().getCustomName() + "! ***********");
/* 1924:     */           }
/* 1925:1621 */         } else if (new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + mob.getEntityType().toString().toLowerCase() + ".yml").exists()) {
/* 1926:     */           try
/* 1927:     */           {
/* 1928:1624 */             this.PlayerDataConfig = new YamlConfiguration();
/* 1929:1625 */             this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + mob.getEntityType().toString().toLowerCase() + ".yml"));
/* 1930:     */             
/* 1931:1627 */             int dropChance = random(100);
/* 1932:     */             
/* 1933:1629 */             int i = this.PlayerDataConfig.getKeys(false).size() - 1;
/* 1934:     */             do
/* 1935:     */             {
/* 1936:1630 */               if (dropChance <= Integer.parseInt(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()))
/* 1937:     */               {
/* 1938:1631 */                 int selectRandomDrop = randomKeySelection(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).size());
/* 1939:1632 */                 mob.getDrops().add(gearGenerator(mob.getEntity().getKiller(), mob.getEntityType().toString().toLowerCase(), this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim() + "." + this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()));
/* 1940:1633 */                 if (this.PlayerDataConfig.getString(String.valueOf(new StringBuilder(String.valueOf(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim())).append(".").append(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()).toString()) + ".properties.droppedXp") != null) {
/* 1941:1634 */                   mob.setDroppedExp(Integer.parseInt(this.PlayerDataConfig.getString(String.valueOf(new StringBuilder(String.valueOf(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim())).append(".").append(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()).toString()) + ".properties.droppedXp")));
/* 1942:     */                 }
/* 1943:     */               }
/* 1944:     */               else
/* 1945:     */               {
/* 1946:1638 */                 i--;
/* 1947:1638 */                 if (i < 0) {
/* 1948:     */                   break;
/* 1949:     */                 }
/* 1950:     */               }
/* 1951:1638 */             } while (i <= this.PlayerDataConfig.getKeys(false).size() - 1);
/* 1952:     */           }
/* 1953:     */           catch (Exception e)
/* 1954:     */           {
/* 1955:1642 */             e.printStackTrace();
/* 1956:1643 */             System.out.println("*********** Failed to drop gear from " + mob.getEntityType().toString().toLowerCase() + "! ***********");
/* 1957:     */           }
/* 1958:     */         }
/* 1959:     */       }
/* 1960:     */     }
/* 1961:     */   }
/* 1962:     */   
/* 1963:     */   @EventHandler
/* 1964:     */   public void addDurabilityToDroppedItem(EntityDeathEvent event)
/* 1965:     */   {
/* 1966:1652 */     if ((event.getEntity() instanceof Player)) {
/* 1967:1652 */       return;
/* 1968:     */     }
/* 1969:1653 */     if (ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/* 1970:1653 */       return;
/* 1971:     */     }
/* 1972:1655 */     if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(event.getEntity().getLocation().getWorld().getName())) {
/* 1973:1656 */       for (int i = 0; i < event.getDrops().size(); i++) {
/* 1974:1657 */         if (((ItemLoreStats.plugin.isTool(((ItemStack)event.getDrops().get(i)).getType())) || (ItemLoreStats.plugin.isArmour(((ItemStack)event.getDrops().get(i)).getType()))) && (ItemLoreStats.plugin.getConfig().getBoolean("defaultCraftedDurability.enableDurabilityOnCrafted")) && (((ItemStack)event.getDrops().get(i)).getItemMeta() != null) && (((ItemStack)event.getDrops().get(i)).getItemMeta().getLore() != null) && (!((ItemStack)event.getDrops().get(i)).getItemMeta().getLore().toString().contains("Durability:")))
/* 1975:     */         {
/* 1976:1663 */           ItemStack droppedItem = (ItemStack)event.getDrops().get(i);
/* 1977:1664 */           ItemMeta droppedItemMeta = droppedItem.getItemMeta();
/* 1978:     */           
/* 1979:1666 */           List setItemLore = new ArrayList();
/* 1980:     */           
/* 1981:1668 */           String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability");
/* 1982:1669 */           String durabilityNoColour = durability.replaceAll("&([0-9a-f])", "");
/* 1983:     */           
/* 1984:1671 */           String type = "";
/* 1985:1672 */           String selectedDurability = "";
/* 1986:1674 */           if (droppedItem.getType().toString().contains("WOOD")) {
/* 1987:1675 */             type = "wood";
/* 1988:1676 */           } else if (droppedItem.getType().toString().contains("LEATHER")) {
/* 1989:1677 */             type = "leather";
/* 1990:1678 */           } else if (droppedItem.getType().toString().contains("STONE")) {
/* 1991:1679 */             type = "stone";
/* 1992:1680 */           } else if (droppedItem.getType().toString().contains("CHAINMAIL")) {
/* 1993:1681 */             type = "chainmail";
/* 1994:1682 */           } else if (droppedItem.getType().toString().contains("IRON")) {
/* 1995:1683 */             type = "iron";
/* 1996:1684 */           } else if (droppedItem.getType().toString().contains("GOLD")) {
/* 1997:1685 */             type = "gold";
/* 1998:1686 */           } else if (droppedItem.getType().toString().contains("DIAMOND")) {
/* 1999:1687 */             type = "diamond";
/* 2000:1688 */           } else if (droppedItem.getType().toString().contains("BOW")) {
/* 2001:1689 */             type = "bow";
/* 2002:1690 */           } else if (droppedItem.getType().toString().contains("SHEARS")) {
/* 2003:1691 */             type = "shears";
/* 2004:1692 */           } else if (droppedItem.getType().toString().contains("FISHING_ROD")) {
/* 2005:1693 */             type = "fishingRod";
/* 2006:1694 */           } else if (droppedItem.getType().toString().contains("CARROT_STICK")) {
/* 2007:1695 */             type = "carrotStick";
/* 2008:     */           }
/* 2009:1698 */           if (ItemLoreStats.plugin.isTool(droppedItem.getType()))
/* 2010:     */           {
/* 2011:1699 */             if (!ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).equals("0")) {
/* 2012:1700 */               if (ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).contains("-"))
/* 2013:     */               {
/* 2014:1701 */                 selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 2015:1702 */                 setItemLore.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability);
/* 2016:     */               }
/* 2017:     */               else
/* 2018:     */               {
/* 2019:1704 */                 selectedDurability = String.valueOf(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).replaceAll("&([0-9a-f])", "").trim());
/* 2020:1705 */                 setItemLore.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability);
/* 2021:     */               }
/* 2022:     */             }
/* 2023:     */           }
/* 2024:1708 */           else if ((ItemLoreStats.plugin.isArmour(droppedItem.getType())) && (!ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).equals("0"))) {
/* 2025:1710 */             if (ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).contains("-"))
/* 2026:     */             {
/* 2027:1711 */               selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 2028:1712 */               setItemLore.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability);
/* 2029:     */             }
/* 2030:     */             else
/* 2031:     */             {
/* 2032:1714 */               selectedDurability = String.valueOf(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).replaceAll("&([0-9a-f])", "").trim());
/* 2033:1715 */               setItemLore.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability);
/* 2034:     */             }
/* 2035:     */           }
/* 2036:1720 */           droppedItemMeta.setDisplayName(ChatColor.RESET + ((ItemStack)event.getDrops().get(i)).getType().toString().replaceAll("WOOD_", "Wooden ").replaceAll("LEATHER_", "Leather ").replaceAll("STONE_", "Stone ").replaceAll("IRON_", "Iron ").replaceAll("GOLD_", "Golden ").replaceAll("DIAMOND_", "Diamond ").replaceAll("FISHING_ROD", "Fishing Rod").replaceAll("BOW", "Bow").replaceAll("SHEARS", "Shears").replaceAll("CARROT_STICK", "Carrot on a Stick").replaceAll("SWORD", "Sword").replaceAll("HOE", "Hoe").replaceAll("SPADE", "Spade").replaceAll("PICKAXE", "Pickaxe").replace("AXE", "Axe").replaceAll("HELMET", "Helmet").replace("CHESTPLATE", "Chestplate").replaceAll("LEGGINGS", "Leggings").replace("BOOTS", "Boots"));
/* 2037:     */           
/* 2038:1722 */           droppedItemMeta.setLore(setItemLore);
/* 2039:1723 */           droppedItem.setItemMeta(droppedItemMeta);
/* 2040:     */           
/* 2041:1725 */           event.getDrops().remove(i);
/* 2042:1726 */           event.getDrops().add(droppedItem);
/* 2043:     */         }
/* 2044:     */       }
/* 2045:     */     }
/* 2046:     */   }
/* 2047:     */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.EntityDrops
 * JD-Core Version:    0.7.0.1
 */