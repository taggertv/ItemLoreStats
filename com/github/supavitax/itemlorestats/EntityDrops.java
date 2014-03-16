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
/*   35:  29 */   Util_Colours util_Colours = new Util_Colours();
/*   36:     */   private FileConfiguration PlayerDataConfig;
/*   37:     */   
/*   38:     */   private int random(int length)
/*   39:     */   {
/*   40:  34 */     return new Random().nextInt(length) + 1;
/*   41:     */   }
/*   42:     */   
/*   43:     */   private int randomKeySelection(int length)
/*   44:     */   {
/*   45:  38 */     return new Random().nextInt(length);
/*   46:     */   }
/*   47:     */   
/*   48:     */   private String randomRange(String min, String max)
/*   49:     */   {
/*   50:  46 */     Locale forceLocale = new Locale("en", "UK");
/*   51:  47 */     String decimalPattern = "#.#";
/*   52:     */     
/*   53:  49 */     DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(forceLocale);
/*   54:  50 */     decimalFormat.applyPattern(decimalPattern);
/*   55:     */     
/*   56:  52 */     String format = decimalFormat.format(Double.valueOf(min).doubleValue() + Math.random() * (Double.valueOf(max).doubleValue() - Double.valueOf(min).doubleValue()));
/*   57:     */     
/*   58:  54 */     return format;
/*   59:     */   }
/*   60:     */   
/*   61:     */   private int randomRangeInt(int min, int max)
/*   62:     */   {
/*   63:  59 */     return (int)(min + Math.random() * (max - min));
/*   64:     */   }
/*   65:     */   
/*   66:     */   public boolean dropChance(int setDropChance)
/*   67:     */   {
/*   68:  63 */     if (random(100) <= setDropChance) {
/*   69:  64 */       return true;
/*   70:     */     }
/*   71:  66 */     return false;
/*   72:     */   }
/*   73:     */   
/*   74:     */   private String randomClass()
/*   75:     */   {
/*   76:  70 */     List<String> getListClasses = ItemLoreStats.plugin.getConfig().getStringList("statNames.class.classList");
/*   77:  71 */     String selectClass = (String)getListClasses.get(random(getListClasses.size()) - 1);
/*   78:  72 */     return selectClass;
/*   79:     */   }
/*   80:     */   
/*   81:     */   public String getResponse(String getKeyFromLanguageFile)
/*   82:     */   {
/*   83:     */     try
/*   84:     */     {
/*   85:  78 */       this.PlayerDataConfig = new YamlConfiguration();
/*   86:  79 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + ItemLoreStats.plugin.getConfig().getString("languageFile") + ".yml"));
/*   87:     */       
/*   88:  81 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*   89:     */     }
/*   90:     */     catch (Exception e)
/*   91:     */     {
/*   92:  84 */       e.printStackTrace();
/*   93:  85 */       System.out.println("*********** Failed to load message from language file! ***********");
/*   94:     */     }
/*   95:  87 */     return "*********** Failed to load message from language file! ***********";
/*   96:     */   }
/*   97:     */   
/*   98:     */   public Material idToMaterial(int id)
/*   99:     */   {
/*  100:  93 */     if (id == 256) {
/*  101:  94 */       return Material.IRON_HOE;
/*  102:     */     }
/*  103:  95 */     if (id == 257) {
/*  104:  96 */       return Material.IRON_PICKAXE;
/*  105:     */     }
/*  106:  97 */     if (id == 258) {
/*  107:  98 */       return Material.IRON_AXE;
/*  108:     */     }
/*  109:  99 */     if (id == 261) {
/*  110: 100 */       return Material.BOW;
/*  111:     */     }
/*  112: 101 */     if (id == 267) {
/*  113: 102 */       return Material.IRON_SWORD;
/*  114:     */     }
/*  115: 103 */     if (id == 268) {
/*  116: 104 */       return Material.WOOD_SWORD;
/*  117:     */     }
/*  118: 105 */     if (id == 269) {
/*  119: 106 */       return Material.WOOD_SPADE;
/*  120:     */     }
/*  121: 107 */     if (id == 270) {
/*  122: 108 */       return Material.WOOD_PICKAXE;
/*  123:     */     }
/*  124: 109 */     if (id == 271) {
/*  125: 110 */       return Material.WOOD_AXE;
/*  126:     */     }
/*  127: 111 */     if (id == 272) {
/*  128: 112 */       return Material.STONE_SWORD;
/*  129:     */     }
/*  130: 113 */     if (id == 273) {
/*  131: 114 */       return Material.STONE_SPADE;
/*  132:     */     }
/*  133: 115 */     if (id == 274) {
/*  134: 116 */       return Material.STONE_PICKAXE;
/*  135:     */     }
/*  136: 117 */     if (id == 275) {
/*  137: 118 */       return Material.STONE_AXE;
/*  138:     */     }
/*  139: 119 */     if (id == 276) {
/*  140: 120 */       return Material.DIAMOND_SWORD;
/*  141:     */     }
/*  142: 121 */     if (id == 277) {
/*  143: 122 */       return Material.DIAMOND_SPADE;
/*  144:     */     }
/*  145: 123 */     if (id == 278) {
/*  146: 124 */       return Material.DIAMOND_PICKAXE;
/*  147:     */     }
/*  148: 125 */     if (id == 279) {
/*  149: 126 */       return Material.DIAMOND_AXE;
/*  150:     */     }
/*  151: 127 */     if (id == 280) {
/*  152: 128 */       return Material.STICK;
/*  153:     */     }
/*  154: 129 */     if (id == 283) {
/*  155: 130 */       return Material.GOLD_SWORD;
/*  156:     */     }
/*  157: 131 */     if (id == 284) {
/*  158: 132 */       return Material.GOLD_SPADE;
/*  159:     */     }
/*  160: 133 */     if (id == 285) {
/*  161: 134 */       return Material.GOLD_PICKAXE;
/*  162:     */     }
/*  163: 135 */     if (id == 286) {
/*  164: 136 */       return Material.GOLD_AXE;
/*  165:     */     }
/*  166: 137 */     if (id == 290) {
/*  167: 138 */       return Material.WOOD_HOE;
/*  168:     */     }
/*  169: 139 */     if (id == 291) {
/*  170: 140 */       return Material.STONE_HOE;
/*  171:     */     }
/*  172: 141 */     if (id == 292) {
/*  173: 142 */       return Material.IRON_HOE;
/*  174:     */     }
/*  175: 143 */     if (id == 293) {
/*  176: 144 */       return Material.DIAMOND_HOE;
/*  177:     */     }
/*  178: 145 */     if (id == 294) {
/*  179: 146 */       return Material.GOLD_HOE;
/*  180:     */     }
/*  181: 147 */     if (id == 298) {
/*  182: 148 */       return Material.LEATHER_HELMET;
/*  183:     */     }
/*  184: 149 */     if (id == 299) {
/*  185: 150 */       return Material.LEATHER_CHESTPLATE;
/*  186:     */     }
/*  187: 151 */     if (id == 300) {
/*  188: 152 */       return Material.LEATHER_LEGGINGS;
/*  189:     */     }
/*  190: 153 */     if (id == 301) {
/*  191: 154 */       return Material.LEATHER_BOOTS;
/*  192:     */     }
/*  193: 155 */     if (id == 302) {
/*  194: 156 */       return Material.CHAINMAIL_HELMET;
/*  195:     */     }
/*  196: 157 */     if (id == 303) {
/*  197: 158 */       return Material.CHAINMAIL_CHESTPLATE;
/*  198:     */     }
/*  199: 159 */     if (id == 304) {
/*  200: 160 */       return Material.CHAINMAIL_LEGGINGS;
/*  201:     */     }
/*  202: 161 */     if (id == 305) {
/*  203: 162 */       return Material.CHAINMAIL_BOOTS;
/*  204:     */     }
/*  205: 163 */     if (id == 306) {
/*  206: 164 */       return Material.IRON_HELMET;
/*  207:     */     }
/*  208: 165 */     if (id == 307) {
/*  209: 166 */       return Material.IRON_CHESTPLATE;
/*  210:     */     }
/*  211: 167 */     if (id == 308) {
/*  212: 168 */       return Material.IRON_LEGGINGS;
/*  213:     */     }
/*  214: 169 */     if (id == 309) {
/*  215: 170 */       return Material.IRON_BOOTS;
/*  216:     */     }
/*  217: 171 */     if (id == 310) {
/*  218: 172 */       return Material.DIAMOND_HELMET;
/*  219:     */     }
/*  220: 173 */     if (id == 311) {
/*  221: 174 */       return Material.DIAMOND_CHESTPLATE;
/*  222:     */     }
/*  223: 175 */     if (id == 312) {
/*  224: 176 */       return Material.DIAMOND_LEGGINGS;
/*  225:     */     }
/*  226: 177 */     if (id == 313) {
/*  227: 178 */       return Material.DIAMOND_BOOTS;
/*  228:     */     }
/*  229: 179 */     if (id == 314) {
/*  230: 180 */       return Material.GOLD_HELMET;
/*  231:     */     }
/*  232: 181 */     if (id == 315) {
/*  233: 182 */       return Material.GOLD_CHESTPLATE;
/*  234:     */     }
/*  235: 183 */     if (id == 316) {
/*  236: 184 */       return Material.GOLD_LEGGINGS;
/*  237:     */     }
/*  238: 185 */     if (id == 317) {
/*  239: 186 */       return Material.GOLD_BOOTS;
/*  240:     */     }
/*  241: 189 */     return Material.POTATO;
/*  242:     */   }
/*  243:     */   
/*  244:     */   public String prefix(String entity, String dropChance)
/*  245:     */   {
/*  246:     */     try
/*  247:     */     {
/*  248: 194 */       this.PlayerDataConfig = new YamlConfiguration();
/*  249: 195 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entity + ".yml"));
/*  250: 197 */       if (this.PlayerDataConfig.getString(dropChance + ".prefix").equalsIgnoreCase("Random"))
/*  251:     */       {
/*  252: 198 */         List<String> getListPrefix = ItemLoreStats.plugin.getConfig().getStringList("prefix.random");
/*  253:     */         
/*  254: 200 */         return (String)getListPrefix.get(random(getListPrefix.size()) - 1);
/*  255:     */       }
/*  256: 203 */       if (!this.PlayerDataConfig.getString(dropChance + ".prefix").equalsIgnoreCase("Stat")) {
/*  257: 206 */         return this.PlayerDataConfig.getString(dropChance + ".prefix");
/*  258:     */       }
/*  259:     */     }
/*  260:     */     catch (Exception e)
/*  261:     */     {
/*  262: 210 */       e.printStackTrace();
/*  263: 211 */       System.out.println("*********** Failed to load prefix from " + entity + " file! ***********");
/*  264:     */     }
/*  265: 214 */     return "Error getting prefix!";
/*  266:     */   }
/*  267:     */   
/*  268:     */   public String suffix(String entity, String dropChance)
/*  269:     */   {
/*  270:     */     try
/*  271:     */     {
/*  272: 220 */       this.PlayerDataConfig = new YamlConfiguration();
/*  273: 221 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entity + ".yml"));
/*  274: 223 */       if (this.PlayerDataConfig.getString(dropChance + ".suffix").equalsIgnoreCase("Random"))
/*  275:     */       {
/*  276: 224 */         List<String> getListSuffix = ItemLoreStats.plugin.getConfig().getStringList("suffix.random");
/*  277:     */         
/*  278: 226 */         return (String)getListSuffix.get(random(getListSuffix.size()) - 1);
/*  279:     */       }
/*  280: 229 */       if (!this.PlayerDataConfig.getString(dropChance + ".suffix").equalsIgnoreCase("Stat")) {
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
/*  294: 246 */     if ((getMaterial.toString().contains("_SWORD")) || (getMaterial.toString().contains("_AXE")) || (getMaterial.toString().contains("_HOE")) || (getMaterial.toString().contains("_SPADE")) || (getMaterial.toString().contains("_PICKAXE")) || (getMaterial.toString().contains("BOW")) || (getMaterial.toString().contains("STICK")))
/*  295:     */     {
/*  296: 247 */       if (getMaterial.toString().contains("BOW"))
/*  297:     */       {
/*  298: 248 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.bow");
/*  299:     */         
/*  300: 250 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  301:     */         
/*  302: 252 */         return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Bow"));
/*  303:     */       }
/*  304: 253 */       if (getMaterial.toString().contains("STICK"))
/*  305:     */       {
/*  306: 254 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.stick");
/*  307:     */         
/*  308: 256 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  309:     */         
/*  310: 258 */         return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Stick"));
/*  311:     */       }
/*  312: 259 */       if (getMaterial.toString().contains("WOOD_"))
/*  313:     */       {
/*  314: 261 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.wood");
/*  315:     */         
/*  316: 263 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  317: 265 */         if (getMaterial.toString().contains("_SWORD")) {
/*  318: 266 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  319:     */         }
/*  320: 267 */         if (getMaterial.toString().contains("_AXE")) {
/*  321: 268 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  322:     */         }
/*  323: 269 */         if (getMaterial.toString().contains("_HOE")) {
/*  324: 270 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  325:     */         }
/*  326: 271 */         if (getMaterial.toString().contains("_SPADE")) {
/*  327: 272 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  328:     */         }
/*  329: 273 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  330: 274 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  331:     */         }
/*  332:     */       }
/*  333: 277 */       else if (getMaterial.toString().contains("STONE_"))
/*  334:     */       {
/*  335: 279 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.stone");
/*  336:     */         
/*  337: 281 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  338: 283 */         if (getMaterial.toString().contains("_SWORD")) {
/*  339: 284 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  340:     */         }
/*  341: 285 */         if (getMaterial.toString().contains("_AXE")) {
/*  342: 286 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  343:     */         }
/*  344: 287 */         if (getMaterial.toString().contains("_HOE")) {
/*  345: 288 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  346:     */         }
/*  347: 289 */         if (getMaterial.toString().contains("_SPADE")) {
/*  348: 290 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  349:     */         }
/*  350: 291 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  351: 292 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  352:     */         }
/*  353:     */       }
/*  354: 295 */       else if (getMaterial.toString().contains("IRON_"))
/*  355:     */       {
/*  356: 297 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.iron");
/*  357:     */         
/*  358: 299 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  359: 301 */         if (getMaterial.toString().contains("_SWORD")) {
/*  360: 302 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  361:     */         }
/*  362: 303 */         if (getMaterial.toString().contains("_AXE")) {
/*  363: 304 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  364:     */         }
/*  365: 305 */         if (getMaterial.toString().contains("_HOE")) {
/*  366: 306 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  367:     */         }
/*  368: 307 */         if (getMaterial.toString().contains("_SPADE")) {
/*  369: 308 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  370:     */         }
/*  371: 309 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  372: 310 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  373:     */         }
/*  374:     */       }
/*  375: 313 */       else if (getMaterial.toString().contains("GOLD_"))
/*  376:     */       {
/*  377: 315 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.gold");
/*  378:     */         
/*  379: 317 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  380: 319 */         if (getMaterial.toString().contains("_SWORD")) {
/*  381: 320 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  382:     */         }
/*  383: 321 */         if (getMaterial.toString().contains("_AXE")) {
/*  384: 322 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  385:     */         }
/*  386: 323 */         if (getMaterial.toString().contains("_HOE")) {
/*  387: 324 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  388:     */         }
/*  389: 325 */         if (getMaterial.toString().contains("_SPADE")) {
/*  390: 326 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  391:     */         }
/*  392: 327 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  393: 328 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  394:     */         }
/*  395:     */       }
/*  396: 331 */       else if (getMaterial.toString().contains("DIAMOND_"))
/*  397:     */       {
/*  398: 333 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.tools.diamond");
/*  399:     */         
/*  400: 335 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  401: 337 */         if (getMaterial.toString().contains("_SWORD")) {
/*  402: 338 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Sword"));
/*  403:     */         }
/*  404: 339 */         if (getMaterial.toString().contains("_AXE")) {
/*  405: 340 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Axe"));
/*  406:     */         }
/*  407: 341 */         if (getMaterial.toString().contains("_HOE")) {
/*  408: 342 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Hoe"));
/*  409:     */         }
/*  410: 343 */         if (getMaterial.toString().contains("_SPADE")) {
/*  411: 344 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Spade"));
/*  412:     */         }
/*  413: 345 */         if (getMaterial.toString().contains("_PICKAXE")) {
/*  414: 346 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Pickaxe"));
/*  415:     */         }
/*  416:     */       }
/*  417:     */     }
/*  418: 350 */     else if ((getMaterial.toString().contains("_HELMET")) || (getMaterial.toString().contains("_CHESTPLATE")) || (getMaterial.toString().contains("_LEGGINGS")) || (getMaterial.toString().contains("_BOOTS")))
/*  419:     */     {
/*  420: 351 */       if (getMaterial.toString().contains("LEATHER_"))
/*  421:     */       {
/*  422: 353 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.armour.leather");
/*  423:     */         
/*  424: 355 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  425: 357 */         if (getMaterial.toString().contains("_HELMET")) {
/*  426: 358 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Helmet"));
/*  427:     */         }
/*  428: 359 */         if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  429: 360 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Chestplate"));
/*  430:     */         }
/*  431: 361 */         if (getMaterial.toString().contains("_LEGGINGS")) {
/*  432: 362 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Leggings"));
/*  433:     */         }
/*  434: 363 */         if (getMaterial.toString().contains("_BOOTS")) {
/*  435: 364 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Boots"));
/*  436:     */         }
/*  437:     */       }
/*  438: 367 */       else if (getMaterial.toString().contains("IRON_"))
/*  439:     */       {
/*  440: 369 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.armour.iron");
/*  441:     */         
/*  442: 371 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  443: 373 */         if (getMaterial.toString().contains("_HELMET")) {
/*  444: 374 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Helmet"));
/*  445:     */         }
/*  446: 375 */         if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  447: 376 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Chestplate"));
/*  448:     */         }
/*  449: 377 */         if (getMaterial.toString().contains("_LEGGINGS")) {
/*  450: 378 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Leggings"));
/*  451:     */         }
/*  452: 379 */         if (getMaterial.toString().contains("_BOOTS")) {
/*  453: 380 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Boots"));
/*  454:     */         }
/*  455:     */       }
/*  456: 383 */       else if (getMaterial.toString().contains("GOLD_"))
/*  457:     */       {
/*  458: 385 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.armour.gold");
/*  459:     */         
/*  460: 387 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  461: 389 */         if (getMaterial.toString().contains("_HELMET")) {
/*  462: 390 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Helmet"));
/*  463:     */         }
/*  464: 391 */         if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  465: 392 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Chestplate"));
/*  466:     */         }
/*  467: 393 */         if (getMaterial.toString().contains("_LEGGINGS")) {
/*  468: 394 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Leggings"));
/*  469:     */         }
/*  470: 395 */         if (getMaterial.toString().contains("_BOOTS")) {
/*  471: 396 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Boots"));
/*  472:     */         }
/*  473:     */       }
/*  474: 399 */       else if (getMaterial.toString().contains("DIAMOND_"))
/*  475:     */       {
/*  476: 401 */         List<String> getRandomLore = ItemLoreStats.plugin.getConfig().getStringList("randomLore.armour.diamond");
/*  477:     */         
/*  478: 403 */         String selectRandomLore = (String)getRandomLore.get(random(getRandomLore.size()) - 1);
/*  479: 405 */         if (getMaterial.toString().contains("_HELMET")) {
/*  480: 406 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Helmet"));
/*  481:     */         }
/*  482: 407 */         if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  483: 408 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Chestplate"));
/*  484:     */         }
/*  485: 409 */         if (getMaterial.toString().contains("_LEGGINGS")) {
/*  486: 410 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Leggings"));
/*  487:     */         }
/*  488: 411 */         if (getMaterial.toString().contains("_BOOTS")) {
/*  489: 412 */           return this.util_Colours.replaceTooltipColour(selectRandomLore.replace("{item}", "Boots"));
/*  490:     */         }
/*  491:     */       }
/*  492:     */     }
/*  493: 418 */     return "ERROR";
/*  494:     */   }
/*  495:     */   
/*  496:     */   public String setType(ItemStack getMaterial)
/*  497:     */   {
/*  498: 423 */     if (getMaterial.toString().contains("_SWORD")) {
/*  499: 424 */       return "Sword";
/*  500:     */     }
/*  501: 425 */     if (getMaterial.toString().contains("_AXE")) {
/*  502: 426 */       return "Axe";
/*  503:     */     }
/*  504: 427 */     if (getMaterial.toString().contains("_HOE")) {
/*  505: 428 */       return "Hoe";
/*  506:     */     }
/*  507: 429 */     if (getMaterial.toString().contains("_PICKAXE")) {
/*  508: 430 */       return "Pickaxe";
/*  509:     */     }
/*  510: 431 */     if (getMaterial.toString().contains("BOW")) {
/*  511: 432 */       return "Bow";
/*  512:     */     }
/*  513: 433 */     if (getMaterial.toString().contains("STICK")) {
/*  514: 434 */       return "Bow";
/*  515:     */     }
/*  516: 435 */     if (getMaterial.toString().contains("_SPADE")) {
/*  517: 436 */       return "Spade";
/*  518:     */     }
/*  519: 437 */     if (getMaterial.toString().contains("_HELMET")) {
/*  520: 438 */       return "Helmet";
/*  521:     */     }
/*  522: 439 */     if (getMaterial.toString().contains("_CHESTPLATE")) {
/*  523: 440 */       return "Chestplate";
/*  524:     */     }
/*  525: 441 */     if (getMaterial.toString().contains("_LEGGINGS")) {
/*  526: 442 */       return "Leggings";
/*  527:     */     }
/*  528: 443 */     if (getMaterial.toString().contains("_BOOTS")) {
/*  529: 444 */       return "Boots";
/*  530:     */     }
/*  531: 447 */     return "Error";
/*  532:     */   }
/*  533:     */   
/*  534:     */   public String getMinStat(Player player, String entityName, String propertiesType, String dropChance)
/*  535:     */   {
/*  536:     */     try
/*  537:     */     {
/*  538: 453 */       this.PlayerDataConfig = new YamlConfiguration();
/*  539: 454 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entityName + ".yml"));
/*  540: 456 */       if (propertiesType.equals("damage"))
/*  541:     */       {
/*  542: 457 */         double setDamage = Double.parseDouble(randomRange(String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[0].split("-")[0].replaceAll("&([0-9a-f])", "").trim().replace("+", "")), String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[0].split("-")[1].replaceAll("&([0-9a-f])", "").trim())));
/*  543: 459 */         if (player.getLevel() < setDamage) {
/*  544: 461 */           return String.valueOf(player.getLevel());
/*  545:     */         }
/*  546: 468 */         return String.valueOf(player.getLevel() - setDamage);
/*  547:     */       }
/*  548: 475 */       if (player.getLevel() < Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim()))
/*  549:     */       {
/*  550: 476 */         if (ItemLoreStats.plugin.getConfig().getBoolean("tooltipStatsAsDoubles")) {
/*  551: 477 */           return String.valueOf(player.getLevel());
/*  552:     */         }
/*  553: 479 */         return String.valueOf(Double.valueOf(player.getLevel()).intValue());
/*  554:     */       }
/*  555: 482 */       if (ItemLoreStats.plugin.getConfig().getBoolean("tooltipStatsAsDoubles")) {
/*  556: 483 */         return String.valueOf(player.getLevel() - Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  557:     */       }
/*  558: 486 */       return String.valueOf(Double.valueOf(player.getLevel() - Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim())).intValue());
/*  559:     */     }
/*  560:     */     catch (Exception e)
/*  561:     */     {
/*  562: 493 */       e.printStackTrace();
/*  563: 494 */       System.out.println("*********** Failed to load minStat from " + entityName + " file! ***********");
/*  564:     */     }
/*  565: 497 */     return "1337";
/*  566:     */   }
/*  567:     */   
/*  568:     */   public int getMinStatInt(Player player, String entityName, String propertiesType, String dropChance)
/*  569:     */   {
/*  570:     */     try
/*  571:     */     {
/*  572: 503 */       this.PlayerDataConfig = new YamlConfiguration();
/*  573: 504 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entityName + ".yml"));
/*  574: 506 */       if (propertiesType.equals("damage"))
/*  575:     */       {
/*  576: 507 */         double setDamage = Double.parseDouble(randomRange(String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[0].split("-")[0].replaceAll("&([0-9a-f])", "").trim().replace("+", "")), String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[0].split("-")[1].replaceAll("&([0-9a-f])", "").trim())));
/*  577: 510 */         if (player.getLevel() < setDamage) {
/*  578: 511 */           return Double.valueOf(player.getLevel()).intValue();
/*  579:     */         }
/*  580: 513 */         return Double.valueOf(player.getLevel() - setDamage).intValue();
/*  581:     */       }
/*  582: 516 */       if (player.getLevel() < Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim())) {
/*  583: 517 */         return Double.valueOf(player.getLevel()).intValue();
/*  584:     */       }
/*  585: 519 */       return Double.valueOf(player.getLevel() - Double.parseDouble(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-")[0].replaceAll("&([0-9a-f])", "").trim())).intValue();
/*  586:     */     }
/*  587:     */     catch (Exception e)
/*  588:     */     {
/*  589: 524 */       e.printStackTrace();
/*  590: 525 */       System.out.println("*********** Failed to load minStat from " + entityName + " file! ***********");
/*  591:     */     }
/*  592: 528 */     return 1337;
/*  593:     */   }
/*  594:     */   
/*  595:     */   public String getMaxStat(Player player, String entityName, String propertiesType, String dropChance)
/*  596:     */   {
/*  597:     */     try
/*  598:     */     {
/*  599: 534 */       this.PlayerDataConfig = new YamlConfiguration();
/*  600: 535 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entityName + ".yml"));
/*  601: 537 */       if (propertiesType.equals("damage"))
/*  602:     */       {
/*  603: 538 */         double setDamage = Double.parseDouble(randomRange(String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[1].split("-")[0].replaceAll("&([0-9a-f])", "").trim().replace("+", "")), String.valueOf(this.PlayerDataConfig.getString(dropChance + ".properties." + propertiesType).split("-player+")[1].split("-")[1].replaceAll("&([0-9a-f])", "").trim())));
/*  604: 540 */         if (player.getLevel() < setDamage) {
/*  605: 542 */           return String.valueOf(player.getLevel());
/*  606:     */         }
/*  607: 548 */         return String.valueOf(player.getLevel() + setDamage);
/*  608:     */       }
/*  609:     */     }
/*  610:     */     catch (Exception e)
/*  611:     */     {
/*  612: 556 */       e.printStackTrace();
/*  613: 557 */       System.out.println("*********** Failed to load maxStat from " + entityName + " file! ***********");
/*  614:     */     }
/*  615: 560 */     return "1337";
/*  616:     */   }
/*  617:     */   
/*  618:     */   public List<String> setLore(Player player, String entity, String dropChance, Material material)
/*  619:     */   {
/*  620: 566 */     String armour = ItemLoreStats.plugin.getConfig().getString("statNames.armour");
/*  621: 567 */     String armourNoColour = armour.replaceAll("&([0-9a-f])", "");
/*  622: 568 */     String dodge = ItemLoreStats.plugin.getConfig().getString("statNames.dodge");
/*  623: 569 */     String dodgeNoColour = dodge.replaceAll("&([0-9a-f])", "");
/*  624: 570 */     String block = ItemLoreStats.plugin.getConfig().getString("statNames.block");
/*  625: 571 */     String blockNoColour = block.replaceAll("&([0-9a-f])", "");
/*  626: 572 */     String critChance = ItemLoreStats.plugin.getConfig().getString("statNames.critChance");
/*  627: 573 */     String critChanceNoColour = critChance.replaceAll("&([0-9a-f])", "");
/*  628: 574 */     String critDamage = ItemLoreStats.plugin.getConfig().getString("statNames.critDamage");
/*  629: 575 */     String critDamageNoColour = critDamage.replaceAll("&([0-9a-f])", "");
/*  630: 576 */     String damage = ItemLoreStats.plugin.getConfig().getString("statNames.damage");
/*  631: 577 */     String damageNoColour = damage.replaceAll("&([0-9a-f])", "");
/*  632: 578 */     String health = ItemLoreStats.plugin.getConfig().getString("statNames.health");
/*  633: 579 */     String healthNoColour = health.replaceAll("&([0-9a-f])", "");
/*  634: 580 */     String healthRegen = ItemLoreStats.plugin.getConfig().getString("statNames.healthregen");
/*  635: 581 */     String healthRegenNoColour = healthRegen.replaceAll("&([0-9a-f])", "");
/*  636: 582 */     String lifeSteal = ItemLoreStats.plugin.getConfig().getString("statNames.lifesteal");
/*  637: 583 */     String lifeStealNoColour = lifeSteal.replaceAll("&([0-9a-f])", "");
/*  638: 584 */     String reflect = ItemLoreStats.plugin.getConfig().getString("statNames.reflect");
/*  639: 585 */     String reflectNoColour = reflect.replaceAll("&([0-9a-f])", "");
/*  640: 586 */     String fire = ItemLoreStats.plugin.getConfig().getString("statNames.fire");
/*  641: 587 */     String fireNoColour = fire.replaceAll("&([0-9a-f])", "");
/*  642: 588 */     String ice = ItemLoreStats.plugin.getConfig().getString("statNames.ice");
/*  643: 589 */     String iceNoColour = ice.replaceAll("&([0-9a-f])", "");
/*  644: 590 */     String poison = ItemLoreStats.plugin.getConfig().getString("statNames.poison");
/*  645: 591 */     String poisonNoColour = poison.replaceAll("&([0-9a-f])", "");
/*  646: 592 */     String wither = ItemLoreStats.plugin.getConfig().getString("statNames.wither");
/*  647: 593 */     String witherNoColour = wither.replaceAll("&([0-9a-f])", "");
/*  648: 594 */     String harming = ItemLoreStats.plugin.getConfig().getString("statNames.harming");
/*  649: 595 */     String harmingNoColour = harming.replaceAll("&([0-9a-f])", "");
/*  650: 596 */     String blind = ItemLoreStats.plugin.getConfig().getString("statNames.blind");
/*  651: 597 */     String blindNoColour = blind.replaceAll("&([0-9a-f])", "");
/*  652: 598 */     String movementSpeed = ItemLoreStats.plugin.getConfig().getString("statNames.movementspeed");
/*  653: 599 */     String movementSpeedNoColour = movementSpeed.replaceAll("&([0-9a-f])", "");
/*  654: 600 */     String xpMultiplier = ItemLoreStats.plugin.getConfig().getString("statNames.xpmultiplier");
/*  655: 601 */     String xpMultiplierNoColour = xpMultiplier.replaceAll("&([0-9a-f])", "");
/*  656: 602 */     String weaponSpeed = ItemLoreStats.plugin.getConfig().getString("statNames.weaponspeed");
/*  657: 603 */     String weaponSpeedNoColour = weaponSpeed.replaceAll("&([0-9a-f])", "");
/*  658: 604 */     String xpLevel = ItemLoreStats.plugin.getConfig().getString("statNames.xplevel");
/*  659: 605 */     String xpLevelNoColour = xpLevel.replaceAll("&([0-9a-f])", "");
/*  660: 606 */     String className = ItemLoreStats.plugin.getConfig().getString("statNames.class.class");
/*  661: 607 */     String classNameNoColour = className.replaceAll("&([0-9a-f])", "");
/*  662: 608 */     String soulbound = ItemLoreStats.plugin.getConfig().getString("statNames.soulbound");
/*  663: 609 */     String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability");
/*  664: 610 */     String durabilityNoColour = durability.replaceAll("&([0-9a-f])", "");
/*  665: 611 */     String tnt = ItemLoreStats.plugin.getConfig().getString("statNames.spells.tnt.tnt");
/*  666: 612 */     String tntNoColour = tnt.replaceAll("&([0-9a-f])", "");
/*  667: 613 */     String fireball = ItemLoreStats.plugin.getConfig().getString("statNames.spells.fireball.fireball");
/*  668: 614 */     String fireballNoColour = fireball.replaceAll("&([0-9a-f])", "");
/*  669: 615 */     String lightning = ItemLoreStats.plugin.getConfig().getString("statNames.spells.lightning.lightning");
/*  670: 616 */     String lightningNoColour = lightning.replaceAll("&([0-9a-f])", "");
/*  671: 617 */     String frostbolt = ItemLoreStats.plugin.getConfig().getString("statNames.spells.frostbolt.frostbolt");
/*  672: 618 */     String frostboltNoColour = frostbolt.replaceAll("&([0-9a-f])", "");
/*  673:     */     
/*  674: 620 */     int randomApplyChance = Math.abs(ItemLoreStats.plugin.getConfig().getInt("randomApplyChance") - 100);
/*  675:     */     
/*  676: 622 */     double armourValue = 0.0D;
/*  677: 623 */     double dodgeValue = 0.0D;
/*  678: 624 */     double blockValue = 0.0D;
/*  679: 625 */     double critChanceValue = 0.0D;
/*  680: 626 */     double critDamageValue = 0.0D;
/*  681: 627 */     double healthValue = 0.0D;
/*  682: 628 */     double healthRegenValue = 0.0D;
/*  683: 629 */     double lifeStealValue = 0.0D;
/*  684: 630 */     double reflectValue = 0.0D;
/*  685: 631 */     double fireValue = 0.0D;
/*  686: 632 */     double iceValue = 0.0D;
/*  687: 633 */     double poisonValue = 0.0D;
/*  688: 634 */     double witherValue = 0.0D;
/*  689: 635 */     double harmingValue = 0.0D;
/*  690: 636 */     double blindValue = 0.0D;
/*  691: 637 */     double xpMultiplierValue = 0.0D;
/*  692: 638 */     double movementSpeedValue = 0.0D;
/*  693:     */     
/*  694: 640 */     List<String> setLoreList = new ArrayList(Arrays.asList(new String[] { "" }));
/*  695: 641 */     List<Double> highestValue = new ArrayList();
/*  696:     */     
/*  697: 643 */     int playerLevel = player.getLevel();
/*  698: 645 */     if (playerLevel < 1) {
/*  699: 646 */       playerLevel = 1;
/*  700:     */     } else {
/*  701: 648 */       playerLevel = player.getLevel();
/*  702:     */     }
/*  703:     */     try
/*  704:     */     {
/*  705: 652 */       this.PlayerDataConfig = new YamlConfiguration();
/*  706: 653 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entity.toString().toLowerCase() + ".yml"));
/*  707: 655 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.weaponspeed")) && 
/*  708: 656 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.weaponspeed").equals("0"))) {
/*  709: 657 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.weaponspeed").equalsIgnoreCase("slow"))
/*  710:     */         {
/*  711: 658 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.weaponspeedRandomApply"))
/*  712:     */           {
/*  713: 659 */             if (random(100) >= randomApplyChance) {
/*  714: 660 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  715:     */             }
/*  716:     */           }
/*  717:     */           else {
/*  718: 663 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  719:     */           }
/*  720:     */         }
/*  721: 665 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.weaponspeed").contains("fast"))
/*  722:     */         {
/*  723: 666 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.weaponspeedRandomApply"))
/*  724:     */           {
/*  725: 667 */             if (random(100) >= randomApplyChance) {
/*  726: 668 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  727:     */             }
/*  728:     */           }
/*  729:     */           else {
/*  730: 671 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  731:     */           }
/*  732:     */         }
/*  733: 674 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.weaponspeedRandomApply"))
/*  734:     */         {
/*  735: 675 */           if (random(100) >= randomApplyChance) {
/*  736: 676 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  737:     */           }
/*  738:     */         }
/*  739:     */         else {
/*  740: 679 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(weaponSpeed)) + weaponSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.weaponspeed").toString())));
/*  741:     */         }
/*  742:     */       }
/*  743: 685 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.armour")) && 
/*  744: 686 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.armour").equals("0"))) {
/*  745: 687 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.armour").equalsIgnoreCase("player"))
/*  746:     */         {
/*  747: 688 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.armourRandomApply"))
/*  748:     */           {
/*  749: 689 */             if (random(100) >= randomApplyChance)
/*  750:     */             {
/*  751: 690 */               armourValue = playerLevel;
/*  752: 691 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  753:     */             }
/*  754:     */           }
/*  755:     */           else
/*  756:     */           {
/*  757: 694 */             armourValue = playerLevel;
/*  758: 695 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  759:     */           }
/*  760:     */         }
/*  761: 697 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.armour").contains("-player+"))
/*  762:     */         {
/*  763: 698 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.armourRandomApply"))
/*  764:     */           {
/*  765: 699 */             if (random(100) >= randomApplyChance)
/*  766:     */             {
/*  767: 700 */               armourValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "armour", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  768: 701 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  769:     */             }
/*  770:     */           }
/*  771:     */           else
/*  772:     */           {
/*  773: 704 */             armourValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "armour", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  774: 705 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  775:     */           }
/*  776:     */         }
/*  777: 708 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.armourRandomApply"))
/*  778:     */         {
/*  779: 709 */           if (random(100) >= randomApplyChance)
/*  780:     */           {
/*  781: 710 */             armourValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  782: 711 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  783:     */           }
/*  784:     */         }
/*  785:     */         else
/*  786:     */         {
/*  787: 714 */           armourValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.armour").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  788: 715 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + armourNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.armour").toString()))) + armourValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  789:     */         }
/*  790:     */       }
/*  791: 721 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.dodge")) && 
/*  792: 722 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.dodge").equals("0"))) {
/*  793: 723 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.dodge").equalsIgnoreCase("player"))
/*  794:     */         {
/*  795: 724 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.dodgeRandomApply"))
/*  796:     */           {
/*  797: 725 */             if (random(100) >= randomApplyChance)
/*  798:     */             {
/*  799: 726 */               dodgeValue = playerLevel;
/*  800: 727 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  801:     */             }
/*  802:     */           }
/*  803:     */           else
/*  804:     */           {
/*  805: 730 */             dodgeValue = playerLevel;
/*  806: 731 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  807:     */           }
/*  808:     */         }
/*  809: 733 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.dodge").contains("-player+"))
/*  810:     */         {
/*  811: 734 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.dodgeRandomApply"))
/*  812:     */           {
/*  813: 735 */             if (random(100) >= randomApplyChance)
/*  814:     */             {
/*  815: 736 */               dodgeValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "dodge", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  816: 737 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + dodgeValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  817:     */             }
/*  818:     */           }
/*  819:     */           else
/*  820:     */           {
/*  821: 740 */             dodgeValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "dodge", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  822: 741 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + dodgeValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  823:     */           }
/*  824:     */         }
/*  825: 744 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.dodgeRandomApply"))
/*  826:     */         {
/*  827: 745 */           if (random(100) >= randomApplyChance)
/*  828:     */           {
/*  829: 746 */             dodgeValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  830: 747 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + dodgeValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  831:     */           }
/*  832:     */         }
/*  833:     */         else
/*  834:     */         {
/*  835: 750 */           dodgeValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.dodge").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  836: 751 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + dodgeNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.dodge").toString()))) + dodgeValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  837:     */         }
/*  838:     */       }
/*  839: 757 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.block")) && 
/*  840: 758 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.block").equals("0"))) {
/*  841: 759 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.block").equalsIgnoreCase("player"))
/*  842:     */         {
/*  843: 760 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blockRandomApply"))
/*  844:     */           {
/*  845: 761 */             if (random(100) >= randomApplyChance)
/*  846:     */             {
/*  847: 762 */               blockValue = playerLevel;
/*  848: 763 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  849:     */             }
/*  850:     */           }
/*  851:     */           else
/*  852:     */           {
/*  853: 766 */             blockValue = playerLevel;
/*  854: 767 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  855:     */           }
/*  856:     */         }
/*  857: 769 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.block").contains("-player+"))
/*  858:     */         {
/*  859: 770 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blockRandomApply"))
/*  860:     */           {
/*  861: 771 */             if (random(100) >= randomApplyChance)
/*  862:     */             {
/*  863: 772 */               blockValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "block", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.block").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  864: 773 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + blockValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  865:     */             }
/*  866:     */           }
/*  867:     */           else
/*  868:     */           {
/*  869: 776 */             blockValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "block", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.block").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  870: 777 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + blockValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  871:     */           }
/*  872:     */         }
/*  873: 780 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blockRandomApply"))
/*  874:     */         {
/*  875: 781 */           if (random(100) >= randomApplyChance)
/*  876:     */           {
/*  877: 782 */             blockValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.block").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.block").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  878: 783 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + blockValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  879:     */           }
/*  880:     */         }
/*  881:     */         else
/*  882:     */         {
/*  883: 786 */           blockValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.block").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.block").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  884: 787 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + blockNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.block").toString()))) + blockValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  885:     */         }
/*  886:     */       }
/*  887: 793 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.damage")) && 
/*  888: 794 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.damage").equals("0"))) {
/*  889: 795 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.damage").equalsIgnoreCase("player"))
/*  890:     */         {
/*  891: 796 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.damageRandomApply"))
/*  892:     */           {
/*  893: 797 */             if (random(100) >= randomApplyChance) {
/*  894: 798 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + playerLevel);
/*  895:     */             }
/*  896:     */           }
/*  897:     */           else {
/*  898: 801 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + playerLevel);
/*  899:     */           }
/*  900:     */         }
/*  901: 803 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.damage").contains("-player+"))
/*  902:     */         {
/*  903: 804 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.damageRandomApply"))
/*  904:     */           {
/*  905: 805 */             if (random(100) >= randomApplyChance) {
/*  906: 806 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + getMinStat(player, entity.toString().toLowerCase(), "damage", dropChance) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + "-" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + getMaxStat(player, entity.toString().toLowerCase(), "damage", dropChance));
/*  907:     */             }
/*  908:     */           }
/*  909:     */           else {
/*  910: 809 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + getMinStat(player, entity.toString().toLowerCase(), "damage", dropChance) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + "-" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + getMaxStat(player, entity.toString().toLowerCase(), "damage", dropChance));
/*  911:     */           }
/*  912:     */         }
/*  913: 812 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.damageRandomApply"))
/*  914:     */         {
/*  915: 813 */           if (random(100) >= randomApplyChance) {
/*  916: 814 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + randomRange(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()).split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()).split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  917:     */           }
/*  918:     */         }
/*  919:     */         else {
/*  920: 817 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + damageNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()))) + randomRange(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()).split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.damage").toString()).split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  921:     */         }
/*  922:     */       }
/*  923: 823 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.critChance")) && 
/*  924: 824 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.critChance").equals("0"))) {
/*  925: 825 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.critChance").equalsIgnoreCase("player"))
/*  926:     */         {
/*  927: 826 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critChanceRandomApply"))
/*  928:     */           {
/*  929: 827 */             if (random(100) >= randomApplyChance)
/*  930:     */             {
/*  931: 828 */               critChanceValue = playerLevel;
/*  932: 829 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  933:     */             }
/*  934:     */           }
/*  935:     */           else
/*  936:     */           {
/*  937: 832 */             critChanceValue = playerLevel;
/*  938: 833 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  939:     */           }
/*  940:     */         }
/*  941: 835 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.critChance").contains("-player+"))
/*  942:     */         {
/*  943: 836 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critChanceRandomApply"))
/*  944:     */           {
/*  945: 837 */             if (random(100) >= randomApplyChance)
/*  946:     */             {
/*  947: 838 */               critChanceValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "critChance", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  948: 839 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + critChanceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  949:     */             }
/*  950:     */           }
/*  951:     */           else
/*  952:     */           {
/*  953: 842 */             critChanceValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "critChance", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  954: 843 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + critChanceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  955:     */           }
/*  956:     */         }
/*  957: 846 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critChanceRandomApply"))
/*  958:     */         {
/*  959: 847 */           if (random(100) >= randomApplyChance)
/*  960:     */           {
/*  961: 848 */             critChanceValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  962: 849 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + critChanceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  963:     */           }
/*  964:     */         }
/*  965:     */         else
/*  966:     */         {
/*  967: 852 */           critChanceValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.critChance").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/*  968: 853 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + critChanceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critChance").toString()))) + critChanceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  969:     */         }
/*  970:     */       }
/*  971: 859 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.critDamage")) && 
/*  972: 860 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").equals("0"))) {
/*  973: 861 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").equalsIgnoreCase("player"))
/*  974:     */         {
/*  975: 862 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critDamageRandomApply"))
/*  976:     */           {
/*  977: 863 */             if (random(100) >= randomApplyChance)
/*  978:     */             {
/*  979: 864 */               critDamageValue = playerLevel;
/*  980: 865 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/*  981:     */             }
/*  982:     */           }
/*  983:     */           else
/*  984:     */           {
/*  985: 868 */             critDamageValue = playerLevel;
/*  986: 869 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/*  987:     */           }
/*  988:     */         }
/*  989: 871 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").contains("-player+"))
/*  990:     */         {
/*  991: 872 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critDamageRandomApply"))
/*  992:     */           {
/*  993: 873 */             if (random(100) >= randomApplyChance)
/*  994:     */             {
/*  995: 874 */               critDamageValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "critDamage", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/*  996: 875 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + critDamageValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/*  997:     */             }
/*  998:     */           }
/*  999:     */           else
/* 1000:     */           {
/* 1001: 878 */             critDamageValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "critDamage", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1002: 879 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + critDamageValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/* 1003:     */           }
/* 1004:     */         }
/* 1005: 882 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.critDamageRandomApply"))
/* 1006:     */         {
/* 1007: 883 */           if (random(100) >= randomApplyChance)
/* 1008:     */           {
/* 1009: 884 */             critDamageValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1010: 885 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + critDamageValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/* 1011:     */           }
/* 1012:     */         }
/* 1013:     */         else
/* 1014:     */         {
/* 1015: 888 */           critDamageValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.critDamage").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1016: 889 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + critDamageNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.critDamage").toString()))) + critDamageValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/* 1017:     */         }
/* 1018:     */       }
/* 1019: 895 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.health")) && 
/* 1020: 896 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.health").equals("0"))) {
/* 1021: 897 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.health").equalsIgnoreCase("player"))
/* 1022:     */         {
/* 1023: 898 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRandomApply"))
/* 1024:     */           {
/* 1025: 899 */             if (random(100) >= randomApplyChance)
/* 1026:     */             {
/* 1027: 900 */               healthValue = playerLevel;
/* 1028: 901 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + playerLevel);
/* 1029:     */             }
/* 1030:     */           }
/* 1031:     */           else
/* 1032:     */           {
/* 1033: 904 */             healthValue = playerLevel;
/* 1034: 905 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + playerLevel);
/* 1035:     */           }
/* 1036:     */         }
/* 1037: 907 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.health").contains("-player+"))
/* 1038:     */         {
/* 1039: 908 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRandomApply"))
/* 1040:     */           {
/* 1041: 909 */             if (random(100) >= randomApplyChance)
/* 1042:     */             {
/* 1043: 910 */               healthValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "health", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.health").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1044: 911 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + healthValue);
/* 1045:     */             }
/* 1046:     */           }
/* 1047:     */           else
/* 1048:     */           {
/* 1049: 914 */             healthValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "health", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.health").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1050: 915 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + healthValue);
/* 1051:     */           }
/* 1052:     */         }
/* 1053: 918 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRandomApply"))
/* 1054:     */         {
/* 1055: 919 */           if (random(100) >= randomApplyChance)
/* 1056:     */           {
/* 1057: 920 */             healthValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.health").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.health").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1058: 921 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + healthValue);
/* 1059:     */           }
/* 1060:     */         }
/* 1061:     */         else
/* 1062:     */         {
/* 1063: 924 */           healthValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.health").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.health").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1064: 925 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(health)) + healthNoColour + ": +" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.health").toString()))) + healthValue);
/* 1065:     */         }
/* 1066:     */       }
/* 1067: 931 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.healthRegen")) && 
/* 1068: 932 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").equals("0"))) {
/* 1069: 933 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").equalsIgnoreCase("player"))
/* 1070:     */         {
/* 1071: 934 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRegenRandomApply"))
/* 1072:     */           {
/* 1073: 935 */             if (random(100) >= randomApplyChance)
/* 1074:     */             {
/* 1075: 936 */               healthRegenValue = playerLevel;
/* 1076: 937 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1077:     */             }
/* 1078:     */           }
/* 1079:     */           else
/* 1080:     */           {
/* 1081: 940 */             healthRegenValue = playerLevel;
/* 1082: 941 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1083:     */           }
/* 1084:     */         }
/* 1085: 943 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").contains("-player+"))
/* 1086:     */         {
/* 1087: 944 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRegenRandomApply"))
/* 1088:     */           {
/* 1089: 945 */             if (random(100) >= randomApplyChance)
/* 1090:     */             {
/* 1091: 946 */               healthRegenValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "healthRegen", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1092: 947 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + healthRegenValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1093:     */             }
/* 1094:     */           }
/* 1095:     */           else
/* 1096:     */           {
/* 1097: 950 */             healthRegenValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "healthRegen", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1098: 951 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + healthRegenValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1099:     */           }
/* 1100:     */         }
/* 1101: 954 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.healthRegenRandomApply"))
/* 1102:     */         {
/* 1103: 955 */           if (random(100) >= randomApplyChance)
/* 1104:     */           {
/* 1105: 956 */             healthRegenValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1106: 957 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + healthRegenValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1107:     */           }
/* 1108:     */         }
/* 1109:     */         else
/* 1110:     */         {
/* 1111: 960 */           healthRegenValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.healthRegen").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1112: 961 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + healthRegenNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.healthRegen").toString()))) + healthRegenValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/* 1113:     */         }
/* 1114:     */       }
/* 1115: 967 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.lifeSteal")) && 
/* 1116: 968 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").equals("0"))) {
/* 1117: 969 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").equalsIgnoreCase("player"))
/* 1118:     */         {
/* 1119: 970 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.lifeStealRandomApply"))
/* 1120:     */           {
/* 1121: 971 */             if (random(100) >= randomApplyChance)
/* 1122:     */             {
/* 1123: 972 */               lifeStealValue = playerLevel;
/* 1124: 973 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1125:     */             }
/* 1126:     */           }
/* 1127:     */           else
/* 1128:     */           {
/* 1129: 976 */             lifeStealValue = playerLevel;
/* 1130: 977 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1131:     */           }
/* 1132:     */         }
/* 1133: 979 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").contains("-player+"))
/* 1134:     */         {
/* 1135: 980 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.lifeStealRandomApply"))
/* 1136:     */           {
/* 1137: 981 */             if (random(100) >= randomApplyChance)
/* 1138:     */             {
/* 1139: 982 */               lifeStealValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "lifeSteal", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1140: 983 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + lifeStealValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1141:     */             }
/* 1142:     */           }
/* 1143:     */           else
/* 1144:     */           {
/* 1145: 986 */             lifeStealValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "lifeSteal", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1146: 987 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + lifeStealValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1147:     */           }
/* 1148:     */         }
/* 1149: 990 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.lifeStealRandomApply"))
/* 1150:     */         {
/* 1151: 991 */           if (random(100) >= randomApplyChance)
/* 1152:     */           {
/* 1153: 992 */             lifeStealValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1154: 993 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + lifeStealValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1155:     */           }
/* 1156:     */         }
/* 1157:     */         else
/* 1158:     */         {
/* 1159: 996 */           lifeStealValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.lifeSteal").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1160: 997 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + lifeStealNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.lifeSteal").toString()))) + lifeStealValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 1161:     */         }
/* 1162:     */       }
/* 1163:1003 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.reflect")) && 
/* 1164:1004 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.reflect").equals("0"))) {
/* 1165:1005 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.reflect").equalsIgnoreCase("player"))
/* 1166:     */         {
/* 1167:1006 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.reflectRandomApply"))
/* 1168:     */           {
/* 1169:1007 */             if (random(100) >= randomApplyChance)
/* 1170:     */             {
/* 1171:1008 */               reflectValue = playerLevel;
/* 1172:1009 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1173:     */             }
/* 1174:     */           }
/* 1175:     */           else
/* 1176:     */           {
/* 1177:1012 */             reflectValue = playerLevel;
/* 1178:1013 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1179:     */           }
/* 1180:     */         }
/* 1181:1015 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.reflect").contains("-player+"))
/* 1182:     */         {
/* 1183:1016 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.reflectRandomApply"))
/* 1184:     */           {
/* 1185:1017 */             if (random(100) >= randomApplyChance)
/* 1186:     */             {
/* 1187:1018 */               reflectValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "reflect", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1188:1019 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + reflectValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1189:     */             }
/* 1190:     */           }
/* 1191:     */           else
/* 1192:     */           {
/* 1193:1022 */             reflectValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "reflect", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1194:1023 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + reflectValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1195:     */           }
/* 1196:     */         }
/* 1197:1026 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.reflectRandomApply"))
/* 1198:     */         {
/* 1199:1027 */           if (random(100) >= randomApplyChance)
/* 1200:     */           {
/* 1201:1028 */             reflectValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1202:1029 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + reflectValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1203:     */           }
/* 1204:     */         }
/* 1205:     */         else
/* 1206:     */         {
/* 1207:1032 */           reflectValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.reflect").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1208:1033 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + reflectNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.reflect").toString()))) + reflectValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 1209:     */         }
/* 1210:     */       }
/* 1211:1039 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.fire")) && 
/* 1212:1040 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.fire").equals("0"))) {
/* 1213:1041 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.fire").equalsIgnoreCase("player"))
/* 1214:     */         {
/* 1215:1042 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.fireRandomApply"))
/* 1216:     */           {
/* 1217:1043 */             if (random(100) >= randomApplyChance)
/* 1218:     */             {
/* 1219:1044 */               fireValue = playerLevel;
/* 1220:1045 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1221:     */             }
/* 1222:     */           }
/* 1223:     */           else
/* 1224:     */           {
/* 1225:1048 */             fireValue = playerLevel;
/* 1226:1049 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1227:     */           }
/* 1228:     */         }
/* 1229:1051 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.fire").contains("-player+"))
/* 1230:     */         {
/* 1231:1052 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.fireRandomApply"))
/* 1232:     */           {
/* 1233:1053 */             if (random(100) >= randomApplyChance)
/* 1234:     */             {
/* 1235:1054 */               fireValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "fire", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1236:1055 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + fireValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1237:     */             }
/* 1238:     */           }
/* 1239:     */           else
/* 1240:     */           {
/* 1241:1058 */             fireValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "fire", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1242:1059 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + fireValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1243:     */           }
/* 1244:     */         }
/* 1245:1062 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.fireRandomApply"))
/* 1246:     */         {
/* 1247:1063 */           if (random(100) >= randomApplyChance)
/* 1248:     */           {
/* 1249:1064 */             fireValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1250:1065 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + fireValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1251:     */           }
/* 1252:     */         }
/* 1253:     */         else
/* 1254:     */         {
/* 1255:1068 */           fireValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.fire").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1256:1069 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + fireNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.fire").toString()))) + fireValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 1257:     */         }
/* 1258:     */       }
/* 1259:1075 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.ice")) && 
/* 1260:1076 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.ice").equals("0"))) {
/* 1261:1077 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.ice").equalsIgnoreCase("player"))
/* 1262:     */         {
/* 1263:1078 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.iceRandomApply"))
/* 1264:     */           {
/* 1265:1079 */             if (random(100) >= randomApplyChance)
/* 1266:     */             {
/* 1267:1080 */               iceValue = playerLevel;
/* 1268:1081 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1269:     */             }
/* 1270:     */           }
/* 1271:     */           else
/* 1272:     */           {
/* 1273:1084 */             iceValue = playerLevel;
/* 1274:1085 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1275:     */           }
/* 1276:     */         }
/* 1277:1087 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.ice").contains("-player+"))
/* 1278:     */         {
/* 1279:1088 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.iceRandomApply"))
/* 1280:     */           {
/* 1281:1089 */             if (random(100) >= randomApplyChance)
/* 1282:     */             {
/* 1283:1090 */               iceValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "ice", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1284:1091 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + iceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1285:     */             }
/* 1286:     */           }
/* 1287:     */           else
/* 1288:     */           {
/* 1289:1094 */             iceValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "ice", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1290:1095 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + iceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1291:     */           }
/* 1292:     */         }
/* 1293:1098 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.iceRandomApply"))
/* 1294:     */         {
/* 1295:1099 */           if (random(100) >= randomApplyChance)
/* 1296:     */           {
/* 1297:1100 */             iceValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1298:1101 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + iceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1299:     */           }
/* 1300:     */         }
/* 1301:     */         else
/* 1302:     */         {
/* 1303:1104 */           iceValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.ice").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1304:1105 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + iceNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.ice").toString()))) + iceValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 1305:     */         }
/* 1306:     */       }
/* 1307:1111 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.poison")) && 
/* 1308:1112 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.poison").equals("0"))) {
/* 1309:1113 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.poison").equalsIgnoreCase("player"))
/* 1310:     */         {
/* 1311:1114 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.poisonRandomApply"))
/* 1312:     */           {
/* 1313:1115 */             if (random(100) >= randomApplyChance)
/* 1314:     */             {
/* 1315:1116 */               poisonValue = playerLevel;
/* 1316:1117 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1317:     */             }
/* 1318:     */           }
/* 1319:     */           else
/* 1320:     */           {
/* 1321:1120 */             poisonValue = playerLevel;
/* 1322:1121 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1323:     */           }
/* 1324:     */         }
/* 1325:1123 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.poison").contains("-player+"))
/* 1326:     */         {
/* 1327:1124 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.poisonRandomApply"))
/* 1328:     */           {
/* 1329:1125 */             if (random(100) >= randomApplyChance)
/* 1330:     */             {
/* 1331:1126 */               poisonValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "poison", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1332:1127 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + poisonValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1333:     */             }
/* 1334:     */           }
/* 1335:     */           else
/* 1336:     */           {
/* 1337:1130 */             poisonValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "poison", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1338:1131 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + poisonValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1339:     */           }
/* 1340:     */         }
/* 1341:1134 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.poisonRandomApply"))
/* 1342:     */         {
/* 1343:1135 */           if (random(100) >= randomApplyChance)
/* 1344:     */           {
/* 1345:1136 */             poisonValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1346:1137 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + poisonValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1347:     */           }
/* 1348:     */         }
/* 1349:     */         else
/* 1350:     */         {
/* 1351:1140 */           poisonValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.poison").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1352:1141 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + poisonNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.poison").toString()))) + poisonValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 1353:     */         }
/* 1354:     */       }
/* 1355:1147 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.wither")) && 
/* 1356:1148 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.wither").equals("0"))) {
/* 1357:1149 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.wither").equalsIgnoreCase("player"))
/* 1358:     */         {
/* 1359:1150 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.witherRandomApply"))
/* 1360:     */           {
/* 1361:1151 */             if (random(100) >= randomApplyChance)
/* 1362:     */             {
/* 1363:1152 */               witherValue = playerLevel;
/* 1364:1153 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1365:     */             }
/* 1366:     */           }
/* 1367:     */           else
/* 1368:     */           {
/* 1369:1156 */             witherValue = playerLevel;
/* 1370:1157 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1371:     */           }
/* 1372:     */         }
/* 1373:1159 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.wither").contains("-player+"))
/* 1374:     */         {
/* 1375:1160 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.witherRandomApply"))
/* 1376:     */           {
/* 1377:1161 */             if (random(100) >= randomApplyChance)
/* 1378:     */             {
/* 1379:1162 */               witherValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "wither", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1380:1163 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + witherValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1381:     */             }
/* 1382:     */           }
/* 1383:     */           else
/* 1384:     */           {
/* 1385:1166 */             witherValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "wither", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1386:1167 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + witherValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1387:     */           }
/* 1388:     */         }
/* 1389:1170 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.witherRandomApply"))
/* 1390:     */         {
/* 1391:1171 */           if (random(100) >= randomApplyChance)
/* 1392:     */           {
/* 1393:1172 */             witherValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1394:1173 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + witherValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1395:     */           }
/* 1396:     */         }
/* 1397:     */         else
/* 1398:     */         {
/* 1399:1176 */           witherValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.wither").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1400:1177 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + witherNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.wither").toString()))) + witherValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 1401:     */         }
/* 1402:     */       }
/* 1403:1183 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.harming")) && 
/* 1404:1184 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.harming").equals("0"))) {
/* 1405:1185 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.harming").equalsIgnoreCase("player"))
/* 1406:     */         {
/* 1407:1186 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.harmingRandomApply"))
/* 1408:     */           {
/* 1409:1187 */             if (random(100) >= randomApplyChance)
/* 1410:     */             {
/* 1411:1188 */               harmingValue = playerLevel;
/* 1412:1189 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1413:     */             }
/* 1414:     */           }
/* 1415:     */           else
/* 1416:     */           {
/* 1417:1192 */             harmingValue = playerLevel;
/* 1418:1193 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1419:     */           }
/* 1420:     */         }
/* 1421:1195 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.harming").contains("-player+"))
/* 1422:     */         {
/* 1423:1196 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.harmingRandomApply"))
/* 1424:     */           {
/* 1425:1197 */             if (random(100) >= randomApplyChance)
/* 1426:     */             {
/* 1427:1198 */               harmingValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "harming", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1428:1199 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + harmingValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1429:     */             }
/* 1430:     */           }
/* 1431:     */           else
/* 1432:     */           {
/* 1433:1202 */             harmingValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "harming", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1434:1203 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + harmingValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1435:     */           }
/* 1436:     */         }
/* 1437:1206 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.harmingRandomApply"))
/* 1438:     */         {
/* 1439:1207 */           if (random(100) >= randomApplyChance)
/* 1440:     */           {
/* 1441:1208 */             harmingValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1442:1209 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + harmingValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1443:     */           }
/* 1444:     */         }
/* 1445:     */         else
/* 1446:     */         {
/* 1447:1212 */           harmingValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.harming").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1448:1213 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + harmingNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.harming").toString()))) + harmingValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 1449:     */         }
/* 1450:     */       }
/* 1451:1219 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.blind")) && 
/* 1452:1220 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.blind").equals("0"))) {
/* 1453:1221 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.blind").equalsIgnoreCase("player"))
/* 1454:     */         {
/* 1455:1222 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blindRandomApply"))
/* 1456:     */           {
/* 1457:1223 */             if (random(100) >= randomApplyChance)
/* 1458:     */             {
/* 1459:1224 */               blindValue = playerLevel;
/* 1460:1225 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1461:     */             }
/* 1462:     */           }
/* 1463:     */           else
/* 1464:     */           {
/* 1465:1228 */             blindValue = playerLevel;
/* 1466:1229 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1467:     */           }
/* 1468:     */         }
/* 1469:1231 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.blind").contains("-player+"))
/* 1470:     */         {
/* 1471:1232 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blindRandomApply"))
/* 1472:     */           {
/* 1473:1233 */             if (random(100) >= randomApplyChance)
/* 1474:     */             {
/* 1475:1234 */               blindValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "blind", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1476:1235 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + blindValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1477:     */             }
/* 1478:     */           }
/* 1479:     */           else
/* 1480:     */           {
/* 1481:1238 */             blindValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "blind", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1482:1239 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + blindValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1483:     */           }
/* 1484:     */         }
/* 1485:1242 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.blindRandomApply"))
/* 1486:     */         {
/* 1487:1243 */           if (random(100) >= randomApplyChance)
/* 1488:     */           {
/* 1489:1244 */             blindValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1490:1245 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + blindValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1491:     */           }
/* 1492:     */         }
/* 1493:     */         else
/* 1494:     */         {
/* 1495:1248 */           blindValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.blind").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1496:1249 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + blindNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.blind").toString()))) + blindValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 1497:     */         }
/* 1498:     */       }
/* 1499:1255 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.xpMultiplier")) && 
/* 1500:1256 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").equals("0"))) {
/* 1501:1257 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").equalsIgnoreCase("player"))
/* 1502:     */         {
/* 1503:1258 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpMultiplierRandomApply"))
/* 1504:     */           {
/* 1505:1259 */             if (random(100) >= randomApplyChance)
/* 1506:     */             {
/* 1507:1260 */               xpMultiplierValue = playerLevel;
/* 1508:1261 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1509:     */             }
/* 1510:     */           }
/* 1511:     */           else
/* 1512:     */           {
/* 1513:1264 */             xpMultiplierValue = playerLevel;
/* 1514:1265 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1515:     */           }
/* 1516:     */         }
/* 1517:1267 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").contains("-player+"))
/* 1518:     */         {
/* 1519:1268 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpMultiplierRandomApply"))
/* 1520:     */           {
/* 1521:1269 */             if (random(100) >= randomApplyChance)
/* 1522:     */             {
/* 1523:1270 */               xpMultiplierValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "xpMultiplier", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1524:1271 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + xpMultiplierValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1525:     */             }
/* 1526:     */           }
/* 1527:     */           else
/* 1528:     */           {
/* 1529:1274 */             xpMultiplierValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "xpMultiplier", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1530:1275 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + xpMultiplierValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1531:     */           }
/* 1532:     */         }
/* 1533:1278 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpMultiplierRandomApply"))
/* 1534:     */         {
/* 1535:1279 */           if (random(100) >= randomApplyChance)
/* 1536:     */           {
/* 1537:1280 */             xpMultiplierValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1538:1281 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + xpMultiplierValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1539:     */           }
/* 1540:     */         }
/* 1541:     */         else
/* 1542:     */         {
/* 1543:1284 */           xpMultiplierValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.xpMultiplier").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1544:1285 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + xpMultiplierNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpMultiplier").toString()))) + xpMultiplierValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpMultiplier)) + "%");
/* 1545:     */         }
/* 1546:     */       }
/* 1547:1291 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.movementSpeed")) && 
/* 1548:1292 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").equals("0"))) {
/* 1549:1293 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").equalsIgnoreCase("player"))
/* 1550:     */         {
/* 1551:1294 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.movementSpeedRandomApply"))
/* 1552:     */           {
/* 1553:1295 */             if (random(100) >= randomApplyChance)
/* 1554:     */             {
/* 1555:1296 */               movementSpeedValue = playerLevel;
/* 1556:1297 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1557:     */             }
/* 1558:     */           }
/* 1559:     */           else
/* 1560:     */           {
/* 1561:1300 */             movementSpeedValue = playerLevel;
/* 1562:1301 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1563:     */           }
/* 1564:     */         }
/* 1565:1303 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").contains("-player+"))
/* 1566:     */         {
/* 1567:1304 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.movementSpeedRandomApply"))
/* 1568:     */           {
/* 1569:1305 */             if (random(100) >= randomApplyChance)
/* 1570:     */             {
/* 1571:1306 */               movementSpeedValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "movementSpeed", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1572:1307 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + movementSpeedValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1573:     */             }
/* 1574:     */           }
/* 1575:     */           else
/* 1576:     */           {
/* 1577:1310 */             movementSpeedValue = Double.parseDouble(randomRange(getMinStat(player, entity.toString().toLowerCase(), "movementSpeed", dropChance), String.valueOf(playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("\\+")[1].replaceAll("&([0-9a-f])", "").trim()))));
/* 1578:1311 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + movementSpeedValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1579:     */           }
/* 1580:     */         }
/* 1581:1314 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.movementSpeedRandomApply"))
/* 1582:     */         {
/* 1583:1315 */           if (random(100) >= randomApplyChance)
/* 1584:     */           {
/* 1585:1316 */             movementSpeedValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1586:1317 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + movementSpeedValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1587:     */           }
/* 1588:     */         }
/* 1589:     */         else
/* 1590:     */         {
/* 1591:1320 */           movementSpeedValue = Double.parseDouble(randomRange(this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("-")[1].replaceAll("&([0-9a-f])", "").trim(), this.PlayerDataConfig.getString(dropChance + ".properties.movementSpeed").split("-")[0].replaceAll("&([0-9a-f])", "").trim()));
/* 1592:1321 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + movementSpeedNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.movementSpeed").toString()))) + movementSpeedValue + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementSpeed)) + "%");
/* 1593:     */         }
/* 1594:     */       }
/* 1595:1327 */       if (!ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO"))
/* 1596:     */       {
/* 1597:1328 */         setLoreList.add("");
/* 1598:     */         
/* 1599:1330 */         String selectedDurability = "";
/* 1600:1332 */         if ((this.PlayerDataConfig.contains(dropChance + ".properties.durability")) && 
/* 1601:1333 */           (!this.PlayerDataConfig.getString(dropChance + ".properties.durability").equals("0"))) {
/* 1602:1334 */           if (this.PlayerDataConfig.getString(dropChance + ".properties.durability").equalsIgnoreCase("player"))
/* 1603:     */           {
/* 1604:1335 */             if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.durabilityRandomApply"))
/* 1605:     */             {
/* 1606:1336 */               if (random(100) >= randomApplyChance) {
/* 1607:1337 */                 setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + playerLevel);
/* 1608:     */               }
/* 1609:     */             }
/* 1610:     */             else {
/* 1611:1340 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + playerLevel);
/* 1612:     */             }
/* 1613:     */           }
/* 1614:1342 */           else if (this.PlayerDataConfig.getString(dropChance + ".properties.durability").contains("-player+"))
/* 1615:     */           {
/* 1616:1343 */             if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.durabilityRandomApply"))
/* 1617:     */             {
/* 1618:1344 */               if (random(100) >= randomApplyChance)
/* 1619:     */               {
/* 1620:1345 */                 selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 1621:1346 */                 setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability);
/* 1622:     */               }
/* 1623:     */             }
/* 1624:     */             else
/* 1625:     */             {
/* 1626:1349 */               selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 1627:1350 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability);
/* 1628:     */             }
/* 1629:     */           }
/* 1630:1353 */           else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.durabilityRandomApply"))
/* 1631:     */           {
/* 1632:1354 */             if (random(100) >= randomApplyChance)
/* 1633:     */             {
/* 1634:1355 */               selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 1635:1356 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability);
/* 1636:     */             }
/* 1637:     */           }
/* 1638:     */           else
/* 1639:     */           {
/* 1640:1359 */             selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(dropChance + ".properties.durability").split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 1641:1360 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.durability").toString()))) + selectedDurability);
/* 1642:     */           }
/* 1643:     */         }
/* 1644:     */       }
/* 1645:1367 */       if (ItemLoreStats.plugin.getConfig().getBoolean("addRandomLore")) {
/* 1646:1368 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.addRandomLoreRandomApply"))
/* 1647:     */         {
/* 1648:1369 */           if (random(100) >= randomApplyChance)
/* 1649:     */           {
/* 1650:1370 */             setLoreList.add("");
/* 1651:1371 */             setLoreList.add(randomLore(material));
/* 1652:     */           }
/* 1653:     */         }
/* 1654:     */         else
/* 1655:     */         {
/* 1656:1374 */           setLoreList.add("");
/* 1657:1375 */           setLoreList.add(randomLore(material));
/* 1658:     */         }
/* 1659:     */       }
/* 1660:1379 */       setLoreList.add("");
/* 1661:1381 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.xpLevel")) && 
/* 1662:1382 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.xpLevel").equals("0"))) {
/* 1663:1383 */         if (this.PlayerDataConfig.getString(dropChance + ".properties.xpLevel").equalsIgnoreCase("player"))
/* 1664:     */         {
/* 1665:1384 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpLevelRandomApply"))
/* 1666:     */           {
/* 1667:1385 */             if (random(100) >= randomApplyChance) {
/* 1668:1386 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1669:     */             }
/* 1670:     */           }
/* 1671:     */           else {
/* 1672:1389 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + playerLevel + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1673:     */           }
/* 1674:     */         }
/* 1675:1391 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.xpLevel").contains("-player+"))
/* 1676:     */         {
/* 1677:1392 */           if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpLevelRandomApply"))
/* 1678:     */           {
/* 1679:1393 */             if (random(100) >= randomApplyChance) {
/* 1680:1394 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + randomRangeInt(getMinStatInt(player, entity.toString().toLowerCase(), "xpLevel", dropChance), playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("\\+")[1].replaceAll("&([0-9a-f])", "").trim())) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1681:     */             }
/* 1682:     */           }
/* 1683:     */           else {
/* 1684:1397 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + randomRangeInt(getMinStatInt(player, entity.toString().toLowerCase(), "xpLevel", dropChance), playerLevel + Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("\\+")[1].replaceAll("&([0-9a-f])", "").trim())) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1685:     */           }
/* 1686:     */         }
/* 1687:1400 */         else if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.xpLevelRandomApply"))
/* 1688:     */         {
/* 1689:1401 */           if (random(100) >= randomApplyChance) {
/* 1690:1402 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("-")[0].replaceAll("&([0-9a-f])", "").trim())) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1691:     */           }
/* 1692:     */         }
/* 1693:     */         else {
/* 1694:1405 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)) + xpLevelNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()))) + randomRangeInt(Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.xpLevel").toString()).split("-")[0].replaceAll("&([0-9a-f])", "").trim())) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpLevel)));
/* 1695:     */         }
/* 1696:     */       }
/* 1697:1411 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.class")) && 
/* 1698:1412 */         (!this.PlayerDataConfig.getString(dropChance + ".properties.class").equals("0"))) {
/* 1699:1413 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.classRandomApply"))
/* 1700:     */         {
/* 1701:1414 */           if (random(100) >= randomApplyChance)
/* 1702:     */           {
/* 1703:1415 */             if (this.PlayerDataConfig.getString(dropChance + ".properties.class").equalsIgnoreCase("random")) {
/* 1704:1416 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(randomClass())));
/* 1705:     */             } else {
/* 1706:1418 */               setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(className)));
/* 1707:     */             }
/* 1708:     */           }
/* 1709:1421 */           else if (this.PlayerDataConfig.getString(dropChance + ".properties.class").equalsIgnoreCase("random")) {
/* 1710:1422 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(randomClass())));
/* 1711:     */           } else {
/* 1712:1424 */             setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(className)));
/* 1713:     */           }
/* 1714:     */         }
/* 1715:1428 */         else if (this.PlayerDataConfig.getString(dropChance + ".properties.class").equalsIgnoreCase("random")) {
/* 1716:1429 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(randomClass())));
/* 1717:     */         } else {
/* 1718:1431 */           setLoreList.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(className)) + classNameNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".properties.class").toString()))) + this.util_Colours.replaceTooltipColour(this.util_Colours.replaceTooltipColour(className)));
/* 1719:     */         }
/* 1720:     */       }
/* 1721:1437 */       if ((this.PlayerDataConfig.contains(dropChance + ".properties.soulbound")) && 
/* 1722:1438 */         (this.PlayerDataConfig.getBoolean(dropChance + ".properties.soulbound"))) {
/* 1723:1439 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.soulboundRandomApply"))
/* 1724:     */         {
/* 1725:1440 */           if (random(100) >= randomApplyChance) {
/* 1726:1441 */             setLoreList.add(this.util_Colours.replaceTooltipColour(soulbound) + " " + player.getName());
/* 1727:     */           }
/* 1728:     */         }
/* 1729:     */         else {
/* 1730:1444 */           setLoreList.add(this.util_Colours.replaceTooltipColour(soulbound) + " " + player.getName());
/* 1731:     */         }
/* 1732:     */       }
/* 1733:1449 */       if ((this.PlayerDataConfig.contains(dropChance + ".spells.tnt")) && 
/* 1734:1450 */         (this.PlayerDataConfig.getBoolean(dropChance + ".spells.tnt"))) {
/* 1735:1451 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.tntRandomApply"))
/* 1736:     */         {
/* 1737:1452 */           if (random(100) >= randomApplyChance) {
/* 1738:1453 */             setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + tntNoColour);
/* 1739:     */           }
/* 1740:     */         }
/* 1741:     */         else {
/* 1742:1456 */           setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + tntNoColour);
/* 1743:     */         }
/* 1744:     */       }
/* 1745:1461 */       if ((this.PlayerDataConfig.contains(dropChance + ".spells.fireball")) && 
/* 1746:1462 */         (this.PlayerDataConfig.getBoolean(dropChance + ".spells.fireball"))) {
/* 1747:1463 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.fireballRandomApply"))
/* 1748:     */         {
/* 1749:1464 */           if (random(100) >= randomApplyChance) {
/* 1750:1465 */             setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + fireballNoColour);
/* 1751:     */           }
/* 1752:     */         }
/* 1753:     */         else {
/* 1754:1468 */           setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + fireballNoColour);
/* 1755:     */         }
/* 1756:     */       }
/* 1757:1473 */       if ((this.PlayerDataConfig.contains(dropChance + ".spells.lightning")) && 
/* 1758:1474 */         (this.PlayerDataConfig.getBoolean(dropChance + ".spells.lightning"))) {
/* 1759:1475 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.lightningRandomApply"))
/* 1760:     */         {
/* 1761:1476 */           if (random(100) >= randomApplyChance) {
/* 1762:1477 */             setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + lightningNoColour);
/* 1763:     */           }
/* 1764:     */         }
/* 1765:     */         else {
/* 1766:1480 */           setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + lightningNoColour);
/* 1767:     */         }
/* 1768:     */       }
/* 1769:1485 */       if ((this.PlayerDataConfig.contains(dropChance + ".spells.frostbolt")) && 
/* 1770:1486 */         (this.PlayerDataConfig.getBoolean(dropChance + ".spells.frostbolt"))) {
/* 1771:1487 */         if (this.PlayerDataConfig.getBoolean(dropChance + ".properties.frostboltRandomApply"))
/* 1772:     */         {
/* 1773:1488 */           if (random(100) >= randomApplyChance) {
/* 1774:1489 */             setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + frostboltNoColour);
/* 1775:     */           }
/* 1776:     */         }
/* 1777:     */         else {
/* 1778:1492 */           setLoreList.add(getResponse("SpellMessages.CastSpell.ItemInHand") + " " + frostboltNoColour);
/* 1779:     */         }
/* 1780:     */       }
/* 1781:1497 */       highestValue.add(Double.valueOf(armourValue));
/* 1782:1498 */       highestValue.add(Double.valueOf(dodgeValue));
/* 1783:1499 */       highestValue.add(Double.valueOf(blockValue));
/* 1784:1500 */       highestValue.add(Double.valueOf(critChanceValue));
/* 1785:1501 */       highestValue.add(Double.valueOf(critDamageValue));
/* 1786:1502 */       highestValue.add(Double.valueOf(healthRegenValue));
/* 1787:1503 */       highestValue.add(Double.valueOf(lifeStealValue));
/* 1788:1504 */       highestValue.add(Double.valueOf(reflectValue));
/* 1789:1505 */       highestValue.add(Double.valueOf(fireValue));
/* 1790:1506 */       highestValue.add(Double.valueOf(iceValue));
/* 1791:1507 */       highestValue.add(Double.valueOf(poisonValue));
/* 1792:1508 */       highestValue.add(Double.valueOf(witherValue));
/* 1793:1509 */       highestValue.add(Double.valueOf(harmingValue));
/* 1794:1510 */       highestValue.add(Double.valueOf(blindValue));
/* 1795:1511 */       highestValue.add(Double.valueOf(xpMultiplierValue));
/* 1796:1512 */       highestValue.add(Double.valueOf(movementSpeedValue));
/* 1797:1513 */       highestValue.add(Double.valueOf(1.0D));
/* 1798:     */       
/* 1799:1515 */       double maxValue = ((Double)Collections.max(highestValue)).doubleValue();
/* 1800:1517 */       while (highestValue.contains(Double.valueOf(maxValue)))
/* 1801:     */       {
/* 1802:1518 */         int i = highestValue.indexOf(Double.valueOf(maxValue));
/* 1803:1519 */         if (highestValue.indexOf(Double.valueOf(maxValue)) == 0) {
/* 1804:1520 */           setLoreList.add("armour");
/* 1805:1521 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 1) {
/* 1806:1522 */           setLoreList.add("dodge");
/* 1807:1523 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 2) {
/* 1808:1524 */           setLoreList.add("block");
/* 1809:1525 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 3) {
/* 1810:1526 */           setLoreList.add("critChance");
/* 1811:1527 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 4) {
/* 1812:1528 */           setLoreList.add("critDamage");
/* 1813:1529 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 5) {
/* 1814:1530 */           setLoreList.add("healthRegen");
/* 1815:1531 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 6) {
/* 1816:1532 */           setLoreList.add("lifeSteal");
/* 1817:1533 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 7) {
/* 1818:1534 */           setLoreList.add("reflect");
/* 1819:1535 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 8) {
/* 1820:1536 */           setLoreList.add("fire");
/* 1821:1537 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 9) {
/* 1822:1538 */           setLoreList.add("ice");
/* 1823:1539 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 10) {
/* 1824:1540 */           setLoreList.add("poison");
/* 1825:1541 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 11) {
/* 1826:1542 */           setLoreList.add("wither");
/* 1827:1543 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 12) {
/* 1828:1544 */           setLoreList.add("harming");
/* 1829:1545 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 13) {
/* 1830:1546 */           setLoreList.add("blind");
/* 1831:1547 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 14) {
/* 1832:1548 */           setLoreList.add("xpMultiplier");
/* 1833:1549 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 15) {
/* 1834:1550 */           setLoreList.add("movementSpeed");
/* 1835:1551 */         } else if (highestValue.indexOf(Double.valueOf(maxValue)) == 16) {
/* 1836:1552 */           setLoreList.add(".");
/* 1837:     */         }
/* 1838:1555 */         highestValue.set(i, Double.valueOf(-1.0D));
/* 1839:     */       }
/* 1840:1558 */       return setLoreList;
/* 1841:     */     }
/* 1842:     */     catch (Exception e)
/* 1843:     */     {
/* 1844:1561 */       e.printStackTrace();
/* 1845:1562 */       System.out.println("*********** Failed to load lore from " + entity + " file! ***********");
/* 1846:     */     }
/* 1847:1565 */     return setLoreList;
/* 1848:     */   }
/* 1849:     */   
/* 1850:     */   public ItemStack gearGenerator(Player player, String entityType, String dropChance)
/* 1851:     */   {
/* 1852:     */     try
/* 1853:     */     {
/* 1854:1572 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1855:1573 */       this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + entityType + ".yml"));
/* 1856:     */       
/* 1857:1575 */       Material generateMaterial = idToMaterial(this.PlayerDataConfig.getInt(dropChance + ".itemId"));
/* 1858:     */       
/* 1859:1577 */       ItemStack createItem = new ItemStack(generateMaterial, 1);
/* 1860:1578 */       ItemMeta createdItemMeta = createItem.getItemMeta();
/* 1861:     */       
/* 1862:1580 */       List<String> generateLore = setLore(player, entityType, dropChance, generateMaterial);
/* 1863:     */       
/* 1864:1582 */       String setPrefix = prefix(entityType, dropChance);
/* 1865:1583 */       String setSuffix = suffix(entityType, dropChance);
/* 1866:1585 */       if (this.PlayerDataConfig.getString(dropChance + ".prefix").equalsIgnoreCase("stat"))
/* 1867:     */       {
/* 1868:1586 */         int selectRandomPrefixFromStat = randomKeySelection(ItemLoreStats.plugin.getConfig().getStringList("prefix." + ((String)generateLore.get(generateLore.size() - 1)).toString()).size());
/* 1869:1587 */         setPrefix = this.util_Colours.replaceTooltipColour(ItemLoreStats.plugin.getConfig().getStringList("prefix." + ((String)generateLore.get(generateLore.size() - 1)).toString()).toString().split(",")[selectRandomPrefixFromStat].replaceAll("\\[", "").replaceAll("\\]", "").trim());
/* 1870:     */       }
/* 1871:1590 */       if (this.PlayerDataConfig.getString(dropChance + ".suffix").equalsIgnoreCase("stat"))
/* 1872:     */       {
/* 1873:1591 */         int selectRandomSuffixFromStat = randomKeySelection(ItemLoreStats.plugin.getConfig().getStringList("suffix." + ((String)generateLore.get(generateLore.size() - 1)).toString()).size());
/* 1874:1592 */         setSuffix = this.util_Colours.replaceTooltipColour(ItemLoreStats.plugin.getConfig().getStringList("suffix." + ((String)generateLore.get(generateLore.size() - 1)).toString()).toString().split(",")[selectRandomSuffixFromStat].replaceAll("\\[", "").replaceAll("\\]", "").trim());
/* 1875:     */       }
/* 1876:1595 */       generateLore.remove(generateLore.size() - 1);
/* 1877:     */       
/* 1878:1597 */       createdItemMeta.setDisplayName(this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(new StringBuilder(String.valueOf(dropChance)).append(".nameColour").toString()) + setPrefix + " " + setType(createItem) + " " + setSuffix));
/* 1879:     */       
/* 1880:1599 */       generateLore.add(0, "");
/* 1881:1600 */       generateLore.remove(1);
/* 1882:     */       
/* 1883:1602 */       createdItemMeta.setLore(generateLore);
/* 1884:1603 */       createItem.setItemMeta(createdItemMeta);
/* 1885:     */       
/* 1886:1605 */       return createItem;
/* 1887:     */     }
/* 1888:     */     catch (Exception e)
/* 1889:     */     {
/* 1890:1607 */       e.printStackTrace();
/* 1891:1608 */       System.out.println("*********** Failed to create ItemStack for " + entityType + "! ***********");
/* 1892:     */     }
/* 1893:1611 */     return new ItemStack(Material.POTATO);
/* 1894:     */   }
/* 1895:     */   
/* 1896:     */   public void removeArmourDrop(LivingEntity mob)
/* 1897:     */   {
/* 1898:1616 */     LivingEntity entity = mob;
/* 1899:1618 */     if ((entity instanceof Player)) {
/* 1900:1619 */       return;
/* 1901:     */     }
/* 1902:1621 */     entity.getEquipment().setHelmetDropChance(0.0F);
/* 1903:1622 */     entity.getEquipment().setChestplateDropChance(0.0F);
/* 1904:1623 */     entity.getEquipment().setLeggingsDropChance(0.0F);
/* 1905:1624 */     entity.getEquipment().setBootsDropChance(0.0F);
/* 1906:1625 */     entity.getEquipment().setItemInHandDropChance(0.0F);
/* 1907:     */   }
/* 1908:     */   
/* 1909:     */   @EventHandler
/* 1910:     */   public void dropGear(EntityDeathEvent mob)
/* 1911:     */   {
/* 1912:1631 */     if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(mob.getEntity().getWorld().getName()))
/* 1913:     */     {
/* 1914:1632 */       if (!ItemLoreStats.plugin.getConfig().getBoolean("dropCustomILSLoot")) {
/* 1915:1632 */         return;
/* 1916:     */       }
/* 1917:1633 */       removeArmourDrop(mob.getEntity());
/* 1918:1635 */       if ((mob.getEntity().getKiller() instanceof Player)) {
/* 1919:1637 */         if (new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + mob.getEntity().getCustomName() + ".yml").exists()) {
/* 1920:     */           try
/* 1921:     */           {
/* 1922:1640 */             this.PlayerDataConfig = new YamlConfiguration();
/* 1923:1641 */             this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + mob.getEntity().getCustomName() + ".yml"));
/* 1924:     */             
/* 1925:1643 */             int dropChance = random(100);
/* 1926:     */             
/* 1927:1645 */             int i = this.PlayerDataConfig.getKeys(false).size() - 1;
/* 1928:     */             do
/* 1929:     */             {
/* 1930:1646 */               if (dropChance <= Integer.parseInt(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()))
/* 1931:     */               {
/* 1932:1647 */                 int selectRandomDrop = randomKeySelection(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).size());
/* 1933:1648 */                 mob.getDrops().add(gearGenerator(mob.getEntity().getKiller(), mob.getEntity().getCustomName(), this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim() + "." + this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()));
/* 1934:1649 */                 if (this.PlayerDataConfig.getString(String.valueOf(new StringBuilder(String.valueOf(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim())).append(".").append(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()).toString()) + ".properties.droppedXp") == null) {
/* 1935:     */                   break;
/* 1936:     */                 }
/* 1937:1650 */                 mob.setDroppedExp(Integer.parseInt(this.PlayerDataConfig.getString(String.valueOf(new StringBuilder(String.valueOf(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim())).append(".").append(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()).toString()) + ".properties.droppedXp")));
/* 1938:     */                 
/* 1939:1652 */                 break;
/* 1940:     */               }
/* 1941:1645 */               i--;
/* 1942:1645 */               if (i < 0) {
/* 1943:     */                 break;
/* 1944:     */               }
/* 1945:1645 */             } while (i <= this.PlayerDataConfig.getKeys(false).size() - 1);
/* 1946:     */           }
/* 1947:     */           catch (Exception e)
/* 1948:     */           {
/* 1949:1656 */             e.printStackTrace();
/* 1950:1657 */             System.out.println("*********** Failed to drop gear from " + mob.getEntity().getCustomName() + "! ***********");
/* 1951:     */           }
/* 1952:1660 */         } else if (new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + mob.getEntityType().toString().toLowerCase() + ".yml").exists()) {
/* 1953:     */           try
/* 1954:     */           {
/* 1955:1663 */             this.PlayerDataConfig = new YamlConfiguration();
/* 1956:1664 */             this.PlayerDataConfig.load(new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + mob.getEntityType().toString().toLowerCase() + ".yml"));
/* 1957:     */             
/* 1958:1666 */             int dropChance = random(100);
/* 1959:     */             
/* 1960:1668 */             int i = this.PlayerDataConfig.getKeys(false).size() - 1;
/* 1961:     */             do
/* 1962:     */             {
/* 1963:1669 */               if (dropChance <= Integer.parseInt(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()))
/* 1964:     */               {
/* 1965:1670 */                 int selectRandomDrop = randomKeySelection(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).size());
/* 1966:1671 */                 mob.getDrops().add(gearGenerator(mob.getEntity().getKiller(), mob.getEntityType().toString().toLowerCase(), this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim() + "." + this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()));
/* 1967:1672 */                 if (this.PlayerDataConfig.getString(String.valueOf(new StringBuilder(String.valueOf(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim())).append(".").append(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()).toString()) + ".properties.droppedXp") == null) {
/* 1968:     */                   break;
/* 1969:     */                 }
/* 1970:1673 */                 mob.setDroppedExp(Integer.parseInt(this.PlayerDataConfig.getString(String.valueOf(new StringBuilder(String.valueOf(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim())).append(".").append(this.PlayerDataConfig.getConfigurationSection(this.PlayerDataConfig.getKeys(false).toString().split(",")[i].replaceAll("\\[", "").replaceAll("\\]", "").trim()).getKeys(false).toString().split(",")[selectRandomDrop].replaceAll("\\[", "").replaceAll("\\]", "").trim()).toString()) + ".properties.droppedXp")));
/* 1971:     */                 
/* 1972:1675 */                 break;
/* 1973:     */               }
/* 1974:1668 */               i--;
/* 1975:1668 */               if (i < 0) {
/* 1976:     */                 break;
/* 1977:     */               }
/* 1978:1668 */             } while (i <= this.PlayerDataConfig.getKeys(false).size() - 1);
/* 1979:     */           }
/* 1980:     */           catch (Exception e)
/* 1981:     */           {
/* 1982:1679 */             e.printStackTrace();
/* 1983:1680 */             System.out.println("*********** Failed to drop gear from " + mob.getEntityType().toString().toLowerCase() + "! ***********");
/* 1984:     */           }
/* 1985:     */         }
/* 1986:     */       }
/* 1987:     */     }
/* 1988:     */   }
/* 1989:     */   
/* 1990:     */   @EventHandler
/* 1991:     */   public void addDurabilityToDroppedItem(EntityDeathEvent event)
/* 1992:     */   {
/* 1993:1691 */     if ((event.getEntity() instanceof Player)) {
/* 1994:1691 */       return;
/* 1995:     */     }
/* 1996:1692 */     if (ItemLoreStats.plugin.getConfig().getBoolean("usingMcMMO")) {
/* 1997:1692 */       return;
/* 1998:     */     }
/* 1999:1694 */     if (!ItemLoreStats.plugin.getConfig().getStringList("disabledInWorlds").contains(event.getEntity().getLocation().getWorld().getName())) {
/* 2000:1695 */       for (int i = 0; i < event.getDrops().size(); i++) {
/* 2001:1696 */         if (((ItemLoreStats.plugin.isTool(((ItemStack)event.getDrops().get(i)).getType())) || (ItemLoreStats.plugin.isArmour(((ItemStack)event.getDrops().get(i)).getType()))) && 
/* 2002:1697 */           (ItemLoreStats.plugin.getConfig().getBoolean("defaultCraftedDurability.enableDurabilityOnCrafted")) && 
/* 2003:1698 */           (((ItemStack)event.getDrops().get(i)).getItemMeta() != null) && 
/* 2004:1699 */           (((ItemStack)event.getDrops().get(i)).getItemMeta().getLore() != null) && 
/* 2005:1700 */           (!((ItemStack)event.getDrops().get(i)).getItemMeta().getLore().toString().contains("Durability:")))
/* 2006:     */         {
/* 2007:1702 */           ItemStack droppedItem = (ItemStack)event.getDrops().get(i);
/* 2008:1703 */           ItemMeta droppedItemMeta = droppedItem.getItemMeta();
/* 2009:     */           
/* 2010:1705 */           List<String> setItemLore = new ArrayList();
/* 2011:     */           
/* 2012:1707 */           String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability");
/* 2013:1708 */           String durabilityNoColour = durability.replaceAll("&([0-9a-f])", "");
/* 2014:     */           
/* 2015:1710 */           String type = "";
/* 2016:1711 */           String selectedDurability = "";
/* 2017:1713 */           if (droppedItem.getType().toString().contains("WOOD")) {
/* 2018:1714 */             type = "wood";
/* 2019:1715 */           } else if (droppedItem.getType().toString().contains("LEATHER")) {
/* 2020:1716 */             type = "leather";
/* 2021:1717 */           } else if (droppedItem.getType().toString().contains("STONE")) {
/* 2022:1718 */             type = "stone";
/* 2023:1719 */           } else if (droppedItem.getType().toString().contains("CHAINMAIL")) {
/* 2024:1720 */             type = "chainmail";
/* 2025:1721 */           } else if (droppedItem.getType().toString().contains("IRON")) {
/* 2026:1722 */             type = "iron";
/* 2027:1723 */           } else if (droppedItem.getType().toString().contains("GOLD")) {
/* 2028:1724 */             type = "gold";
/* 2029:1725 */           } else if (droppedItem.getType().toString().contains("DIAMOND")) {
/* 2030:1726 */             type = "diamond";
/* 2031:1727 */           } else if (droppedItem.getType().toString().contains("BOW")) {
/* 2032:1728 */             type = "bow";
/* 2033:1729 */           } else if (droppedItem.getType().toString().contains("SHEARS")) {
/* 2034:1730 */             type = "shears";
/* 2035:1731 */           } else if (droppedItem.getType().toString().contains("FISHING_ROD")) {
/* 2036:1732 */             type = "fishingRod";
/* 2037:1733 */           } else if (droppedItem.getType().toString().contains("CARROT_STICK")) {
/* 2038:1734 */             type = "carrotStick";
/* 2039:     */           }
/* 2040:1737 */           if (ItemLoreStats.plugin.isTool(droppedItem.getType()))
/* 2041:     */           {
/* 2042:1738 */             if (!ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).equals("0")) {
/* 2043:1739 */               if (ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).contains("-"))
/* 2044:     */               {
/* 2045:1740 */                 selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 2046:1741 */                 setItemLore.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability);
/* 2047:     */               }
/* 2048:     */               else
/* 2049:     */               {
/* 2050:1743 */                 selectedDurability = String.valueOf(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.tools." + type).replaceAll("&([0-9a-f])", "").trim());
/* 2051:1744 */                 setItemLore.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability);
/* 2052:     */               }
/* 2053:     */             }
/* 2054:     */           }
/* 2055:1747 */           else if ((ItemLoreStats.plugin.isArmour(droppedItem.getType())) && 
/* 2056:1748 */             (!ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).equals("0"))) {
/* 2057:1749 */             if (ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).contains("-"))
/* 2058:     */             {
/* 2059:1750 */               selectedDurability = String.valueOf(randomRangeInt(Integer.parseInt(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 2060:1751 */               setItemLore.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability);
/* 2061:     */             }
/* 2062:     */             else
/* 2063:     */             {
/* 2064:1753 */               selectedDurability = String.valueOf(ItemLoreStats.plugin.getConfig().getString("defaultCraftedDurability.armour." + type).replaceAll("&([0-9a-f])", "").trim());
/* 2065:1754 */               setItemLore.add(this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(durability)) + "/" + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ItemLoreStats.plugin.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability);
/* 2066:     */             }
/* 2067:     */           }
/* 2068:1759 */           droppedItemMeta.setDisplayName(ChatColor.RESET + ((ItemStack)event.getDrops().get(i)).getType().toString().replaceAll("WOOD_", "Wooden ").replaceAll("LEATHER_", "Leather ").replaceAll("STONE_", "Stone ").replaceAll("IRON_", "Iron ").replaceAll("GOLD_", "Golden ").replaceAll("DIAMOND_", "Diamond ").replaceAll("FISHING_ROD", "Fishing Rod").replaceAll("BOW", "Bow").replaceAll("SHEARS", "Shears").replaceAll("CARROT_STICK", "Carrot on a Stick").replaceAll("SWORD", "Sword").replaceAll("HOE", "Hoe").replaceAll("SPADE", "Spade").replaceAll("PICKAXE", "Pickaxe").replace("AXE", "Axe").replaceAll("HELMET", "Helmet").replace("CHESTPLATE", "Chestplate").replaceAll("LEGGINGS", "Leggings").replace("BOOTS", "Boots"));
/* 2069:     */           
/* 2070:1761 */           droppedItemMeta.setLore(setItemLore);
/* 2071:1762 */           droppedItem.setItemMeta(droppedItemMeta);
/* 2072:     */           
/* 2073:1764 */           event.getDrops().remove(i);
/* 2074:1765 */           event.getDrops().add(droppedItem);
/* 2075:     */         }
/* 2076:     */       }
/* 2077:     */     }
/* 2078:     */   }
/* 2079:     */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.EntityDrops
 * JD-Core Version:    0.7.0.1
 */