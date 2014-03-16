/*    1:     */ package com.github.supavitax.itemlorestats;
/*    2:     */ 
/*    3:     */ import java.io.File;
/*    4:     */ import java.io.PrintStream;
/*    5:     */ import org.bukkit.configuration.file.FileConfiguration;
/*    6:     */ import org.bukkit.configuration.file.YamlConfiguration;
/*    7:     */ import org.bukkit.event.Listener;
/*    8:     */ 
/*    9:     */ public class WriteDefaultFiles
/*   10:     */   implements Listener
/*   11:     */ {
/*   12:     */   private File PlayerDataFile;
/*   13:     */   private FileConfiguration PlayerDataConfig;
/*   14:     */   
/*   15:     */   public void checkExistence()
/*   16:     */   {
/*   17:  17 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems").exists()) {
/*   18:  18 */       new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems").mkdirs();
/*   19:     */     }
/*   20:  20 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs").exists()) {
/*   21:  21 */       new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs").mkdirs();
/*   22:     */     }
/*   23:  23 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-en.yml").exists()) {
/*   24:  24 */       writeLanguageEN();
/*   25:     */     }
/*   26:  26 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-de.yml").exists()) {
/*   27:  27 */       writeLanguageDE();
/*   28:     */     }
/*   29:  29 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-pl.yml").exists()) {
/*   30:  30 */       writeLanguagePL();
/*   31:     */     }
/*   32:  32 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "blaze.yml").exists()) {
/*   33:  33 */       writeBlaze();
/*   34:     */     }
/*   35:  35 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "cave_spider.yml").exists()) {
/*   36:  36 */       writeCave_Spider();
/*   37:     */     }
/*   38:  38 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "creeper.yml").exists()) {
/*   39:  39 */       writeCreeper();
/*   40:     */     }
/*   41:  41 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "ender_dragon.yml").exists()) {
/*   42:  42 */       writeEnder_Dragon();
/*   43:     */     }
/*   44:  44 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "enderman.yml").exists()) {
/*   45:  45 */       writeEnderman();
/*   46:     */     }
/*   47:  47 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "ghast.yml").exists()) {
/*   48:  48 */       writeGhast();
/*   49:     */     }
/*   50:  50 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "magma_cube.yml").exists()) {
/*   51:  51 */       writeMagma_Cube();
/*   52:     */     }
/*   53:  53 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "silverfish.yml").exists()) {
/*   54:  54 */       writeSilverfish();
/*   55:     */     }
/*   56:  56 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "skeleton.yml").exists()) {
/*   57:  57 */       writeSkeleton();
/*   58:     */     }
/*   59:  59 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "slime.yml").exists()) {
/*   60:  60 */       writeSlime();
/*   61:     */     }
/*   62:  62 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "spider.yml").exists()) {
/*   63:  63 */       writeSpider();
/*   64:     */     }
/*   65:  65 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "witch.yml").exists()) {
/*   66:  66 */       writeWitch();
/*   67:     */     }
/*   68:  68 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "wither.yml").exists()) {
/*   69:  69 */       writeWither();
/*   70:     */     }
/*   71:  71 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "zombie.yml").exists()) {
/*   72:  72 */       writeZombie();
/*   73:     */     }
/*   74:  74 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "pig_zombie.yml").exists()) {
/*   75:  75 */       writePig_Zombie();
/*   76:     */     }
/*   77:  77 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + "SetBonuses.yml").exists()) {
/*   78:  78 */       writeSetBonuses();
/*   79:     */     }
/*   80:     */   }
/*   81:     */   
/*   82:     */   public void writeBlaze()
/*   83:     */   {
/*   84:     */     try
/*   85:     */     {
/*   86:  85 */       this.PlayerDataConfig = new YamlConfiguration();
/*   87:  86 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "blaze.yml");
/*   88:     */       
/*   89:  88 */       this.PlayerDataConfig.set("5", null);
/*   90:  89 */       this.PlayerDataConfig.set("5.1.itemId", Integer.valueOf(306));
/*   91:  90 */       this.PlayerDataConfig.set("5.1.nameColour", "&9&l");
/*   92:  91 */       this.PlayerDataConfig.set("5.1.prefix", "random");
/*   93:  92 */       this.PlayerDataConfig.set("5.1.suffix", "random");
/*   94:  93 */       this.PlayerDataConfig.set("5.1.properties.armour", "&b3-7");
/*   95:  94 */       this.PlayerDataConfig.set("5.1.properties.healthRandomApply", Boolean.valueOf(true));
/*   96:  95 */       this.PlayerDataConfig.set("5.1.properties.health", "&b30-player+40");
/*   97:  96 */       this.PlayerDataConfig.set("5.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*   98:  97 */       this.PlayerDataConfig.set("5.1.properties.healthRegen", "&b2-5");
/*   99:  98 */       this.PlayerDataConfig.set("5.1.properties.blockRandomApply", Boolean.valueOf(true));
/*  100:  99 */       this.PlayerDataConfig.set("5.1.properties.block", "&b1-2");
/*  101: 100 */       this.PlayerDataConfig.set("5.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  102: 101 */       this.PlayerDataConfig.set("5.1.properties.movementSpeed", "&e1-3");
/*  103: 102 */       this.PlayerDataConfig.set("5.1.properties.durability", "&71800-2400");
/*  104: 103 */       this.PlayerDataConfig.set("5.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  105: 104 */       this.PlayerDataConfig.set("5.1.properties.soulbound", Boolean.valueOf(true));
/*  106: 105 */       this.PlayerDataConfig.set("5.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  107: 106 */       this.PlayerDataConfig.set("5.1.properties.xpLevel", "&b10-player+3");
/*  108:     */       
/*  109: 108 */       this.PlayerDataConfig.set("5.2.itemId", Integer.valueOf(307));
/*  110: 109 */       this.PlayerDataConfig.set("5.2.nameColour", "&d&l");
/*  111: 110 */       this.PlayerDataConfig.set("5.2.prefix", "random");
/*  112: 111 */       this.PlayerDataConfig.set("5.2.suffix", "random");
/*  113: 112 */       this.PlayerDataConfig.set("5.2.properties.armour", "&b3-7");
/*  114: 113 */       this.PlayerDataConfig.set("5.2.properties.healthRandomApply", Boolean.valueOf(true));
/*  115: 114 */       this.PlayerDataConfig.set("5.2.properties.health", "&b30-player+40");
/*  116: 115 */       this.PlayerDataConfig.set("5.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  117: 116 */       this.PlayerDataConfig.set("5.2.properties.healthRegen", "&b2-5");
/*  118: 117 */       this.PlayerDataConfig.set("5.2.properties.blockRandomApply", Boolean.valueOf(true));
/*  119: 118 */       this.PlayerDataConfig.set("5.2.properties.block", "&b1-2");
/*  120: 119 */       this.PlayerDataConfig.set("5.2.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  121: 120 */       this.PlayerDataConfig.set("5.2.properties.movementSpeed", "&e1-3");
/*  122: 121 */       this.PlayerDataConfig.set("5.2.properties.durability", "&71800-2400");
/*  123: 122 */       this.PlayerDataConfig.set("5.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  124: 123 */       this.PlayerDataConfig.set("5.2.properties.soulbound", Boolean.valueOf(true));
/*  125: 124 */       this.PlayerDataConfig.set("5.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  126: 125 */       this.PlayerDataConfig.set("5.2.properties.xpLevel", "&b10-player+3");
/*  127:     */       
/*  128: 127 */       this.PlayerDataConfig.set("5.3.itemId", Integer.valueOf(308));
/*  129: 128 */       this.PlayerDataConfig.set("5.3.nameColour", "&d&l");
/*  130: 129 */       this.PlayerDataConfig.set("5.3.prefix", "random");
/*  131: 130 */       this.PlayerDataConfig.set("5.3.suffix", "random");
/*  132: 131 */       this.PlayerDataConfig.set("5.3.properties.armour", "&b3-7");
/*  133: 132 */       this.PlayerDataConfig.set("5.3.properties.healthRandomApply", Boolean.valueOf(true));
/*  134: 133 */       this.PlayerDataConfig.set("5.3.properties.health", "&b30-player+40");
/*  135: 134 */       this.PlayerDataConfig.set("5.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  136: 135 */       this.PlayerDataConfig.set("5.3.properties.healthRegen", "&b2-5");
/*  137: 136 */       this.PlayerDataConfig.set("5.3.properties.blockRandomApply", Boolean.valueOf(true));
/*  138: 137 */       this.PlayerDataConfig.set("5.3.properties.block", "&b1-2");
/*  139: 138 */       this.PlayerDataConfig.set("5.3.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  140: 139 */       this.PlayerDataConfig.set("5.3.properties.movementSpeed", "&e1-3");
/*  141: 140 */       this.PlayerDataConfig.set("5.3.properties.durability", "&71800-2400");
/*  142: 141 */       this.PlayerDataConfig.set("5.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  143: 142 */       this.PlayerDataConfig.set("5.3.properties.soulbound", Boolean.valueOf(true));
/*  144: 143 */       this.PlayerDataConfig.set("5.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  145: 144 */       this.PlayerDataConfig.set("5.3.properties.xpLevel", "&b10-player+3");
/*  146:     */       
/*  147: 146 */       this.PlayerDataConfig.set("5.4.itemId", Integer.valueOf(309));
/*  148: 147 */       this.PlayerDataConfig.set("5.4.nameColour", "&d&l");
/*  149: 148 */       this.PlayerDataConfig.set("5.4.prefix", "random");
/*  150: 149 */       this.PlayerDataConfig.set("5.4.suffix", "random");
/*  151: 150 */       this.PlayerDataConfig.set("5.4.properties.armour", "&b3-7");
/*  152: 151 */       this.PlayerDataConfig.set("5.4.properties.healthRandomApply", Boolean.valueOf(true));
/*  153: 152 */       this.PlayerDataConfig.set("5.4.properties.health", "&b30-player+40");
/*  154: 153 */       this.PlayerDataConfig.set("5.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  155: 154 */       this.PlayerDataConfig.set("5.4.properties.healthRegen", "&b2-5");
/*  156: 155 */       this.PlayerDataConfig.set("5.4.properties.blockRandomApply", Boolean.valueOf(true));
/*  157: 156 */       this.PlayerDataConfig.set("5.4.properties.block", "&b1-2");
/*  158: 157 */       this.PlayerDataConfig.set("5.4.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  159: 158 */       this.PlayerDataConfig.set("5.4.properties.movementSpeed", "&e1-3");
/*  160: 159 */       this.PlayerDataConfig.set("5.4.properties.durability", "&71800-2400");
/*  161: 160 */       this.PlayerDataConfig.set("5.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  162: 161 */       this.PlayerDataConfig.set("5.4.properties.soulbound", Boolean.valueOf(true));
/*  163: 162 */       this.PlayerDataConfig.set("5.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  164: 163 */       this.PlayerDataConfig.set("5.4.properties.xpLevel", "&b10-player+3");
/*  165:     */       
/*  166: 165 */       this.PlayerDataConfig.set("5.5.itemId", Integer.valueOf(276));
/*  167: 166 */       this.PlayerDataConfig.set("5.5.nameColour", "&d&l");
/*  168: 167 */       this.PlayerDataConfig.set("5.5.prefix", "random");
/*  169: 168 */       this.PlayerDataConfig.set("5.5.suffix", "random");
/*  170: 169 */       this.PlayerDataConfig.set("5.5.properties.weaponspeed", "&2Fast");
/*  171: 170 */       this.PlayerDataConfig.set("5.5.properties.damage", "&b40-50-player+60-70");
/*  172: 171 */       this.PlayerDataConfig.set("5.5.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  173: 172 */       this.PlayerDataConfig.set("5.5.properties.critChance", "&b3-9");
/*  174: 173 */       this.PlayerDataConfig.set("5.5.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  175: 174 */       this.PlayerDataConfig.set("5.5.properties.critDamage", "&b8-23");
/*  176: 175 */       this.PlayerDataConfig.set("5.5.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  177: 176 */       this.PlayerDataConfig.set("5.5.properties.lifeSteal", "&23-7");
/*  178: 177 */       this.PlayerDataConfig.set("5.5.properties.blindRandomApply", Boolean.valueOf(true));
/*  179: 178 */       this.PlayerDataConfig.set("5.5.properties.blind", "&f2-5");
/*  180: 179 */       this.PlayerDataConfig.set("5.5.properties.iceRandomApply", Boolean.valueOf(true));
/*  181: 180 */       this.PlayerDataConfig.set("5.5.properties.ice", "&b1-4");
/*  182: 181 */       this.PlayerDataConfig.set("5.5.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  183: 182 */       this.PlayerDataConfig.set("5.5.properties.movementSpeed", "&e1-4");
/*  184: 183 */       this.PlayerDataConfig.set("5.5.properties.durability", "&7600-725");
/*  185: 184 */       this.PlayerDataConfig.set("5.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  186: 185 */       this.PlayerDataConfig.set("5.5.properties.soulbound", Boolean.valueOf(true));
/*  187: 186 */       this.PlayerDataConfig.set("5.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  188: 187 */       this.PlayerDataConfig.set("5.5.properties.xpLevel", "&b2-player+5");
/*  189:     */       
/*  190: 189 */       this.PlayerDataConfig.set("5.6.itemId", Integer.valueOf(279));
/*  191: 190 */       this.PlayerDataConfig.set("5.6.nameColour", "&d&l");
/*  192: 191 */       this.PlayerDataConfig.set("5.6.prefix", "random");
/*  193: 192 */       this.PlayerDataConfig.set("5.6.suffix", "random");
/*  194: 193 */       this.PlayerDataConfig.set("5.6.properties.weaponspeed", "&2Fast");
/*  195: 194 */       this.PlayerDataConfig.set("5.6.properties.damage", "&b40-50-player+60-70");
/*  196: 195 */       this.PlayerDataConfig.set("5.6.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  197: 196 */       this.PlayerDataConfig.set("5.6.properties.critChance", "&b3-9");
/*  198: 197 */       this.PlayerDataConfig.set("5.6.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  199: 198 */       this.PlayerDataConfig.set("5.6.properties.critDamage", "&b8-23");
/*  200: 199 */       this.PlayerDataConfig.set("5.6.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  201: 200 */       this.PlayerDataConfig.set("5.6.properties.lifeSteal", "&23-7");
/*  202: 201 */       this.PlayerDataConfig.set("5.6.properties.blindRandomApply", Boolean.valueOf(true));
/*  203: 202 */       this.PlayerDataConfig.set("5.6.properties.blind", "&f2-5");
/*  204: 203 */       this.PlayerDataConfig.set("5.6.properties.iceRandomApply", Boolean.valueOf(true));
/*  205: 204 */       this.PlayerDataConfig.set("5.6.properties.ice", "&b1-4");
/*  206: 205 */       this.PlayerDataConfig.set("5.6.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  207: 206 */       this.PlayerDataConfig.set("5.6.properties.movementSpeed", "&e1-4");
/*  208: 207 */       this.PlayerDataConfig.set("5.6.properties.durability", "&7600-725");
/*  209: 208 */       this.PlayerDataConfig.set("5.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  210: 209 */       this.PlayerDataConfig.set("5.6.properties.soulbound", Boolean.valueOf(true));
/*  211: 210 */       this.PlayerDataConfig.set("5.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  212: 211 */       this.PlayerDataConfig.set("5.6.properties.xpLevel", "&b2-player+5");
/*  213:     */       
/*  214: 213 */       this.PlayerDataConfig.set("5.7.itemId", Integer.valueOf(261));
/*  215: 214 */       this.PlayerDataConfig.set("5.7.nameColour", "&d&l");
/*  216: 215 */       this.PlayerDataConfig.set("5.7.prefix", "random");
/*  217: 216 */       this.PlayerDataConfig.set("5.7.suffix", "random");
/*  218: 217 */       this.PlayerDataConfig.set("5.7.properties.weaponspeed", "&2Fast");
/*  219: 218 */       this.PlayerDataConfig.set("5.7.properties.damage", "&b40-50-player+60-70");
/*  220: 219 */       this.PlayerDataConfig.set("5.7.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  221: 220 */       this.PlayerDataConfig.set("5.7.properties.critChance", "&b3-9");
/*  222: 221 */       this.PlayerDataConfig.set("5.7.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  223: 222 */       this.PlayerDataConfig.set("5.7.properties.critDamage", "&b8-23");
/*  224: 223 */       this.PlayerDataConfig.set("5.7.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  225: 224 */       this.PlayerDataConfig.set("5.7.properties.lifeSteal", "&23-7");
/*  226: 225 */       this.PlayerDataConfig.set("5.7.properties.blindRandomApply", Boolean.valueOf(true));
/*  227: 226 */       this.PlayerDataConfig.set("5.7.properties.blind", "&f2-5");
/*  228: 227 */       this.PlayerDataConfig.set("5.7.properties.iceRandomApply", Boolean.valueOf(true));
/*  229: 228 */       this.PlayerDataConfig.set("5.7.properties.ice", "&b1-4");
/*  230: 229 */       this.PlayerDataConfig.set("5.7.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  231: 230 */       this.PlayerDataConfig.set("5.7.properties.movementSpeed", "&e1-4");
/*  232: 231 */       this.PlayerDataConfig.set("5.7.properties.durability", "&7600-725");
/*  233: 232 */       this.PlayerDataConfig.set("5.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  234: 233 */       this.PlayerDataConfig.set("5.7.properties.soulbound", Boolean.valueOf(true));
/*  235: 234 */       this.PlayerDataConfig.set("5.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  236: 235 */       this.PlayerDataConfig.set("5.7.properties.xpLevel", "&b2-player+5");
/*  237:     */       
/*  238: 237 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  239:     */     }
/*  240:     */     catch (Exception e)
/*  241:     */     {
/*  242: 240 */       e.printStackTrace();
/*  243: 241 */       System.out.println("*********** Failed to save default blaze file! ***********");
/*  244:     */     }
/*  245:     */   }
/*  246:     */   
/*  247:     */   public void writeCave_Spider()
/*  248:     */   {
/*  249:     */     try
/*  250:     */     {
/*  251: 249 */       this.PlayerDataConfig = new YamlConfiguration();
/*  252: 250 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "cave_spider.yml");
/*  253:     */       
/*  254: 252 */       this.PlayerDataConfig.set("25", null);
/*  255: 253 */       this.PlayerDataConfig.set("25.1.itemId", Integer.valueOf(275));
/*  256: 254 */       this.PlayerDataConfig.set("25.1.nameColour", "&c");
/*  257: 255 */       this.PlayerDataConfig.set("25.1.prefix", "random");
/*  258: 256 */       this.PlayerDataConfig.set("25.1.suffix", "random");
/*  259: 257 */       this.PlayerDataConfig.set("25.1.properties.damage", "&b5-20-player+15-45");
/*  260: 258 */       this.PlayerDataConfig.set("25.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  261: 259 */       this.PlayerDataConfig.set("25.1.properties.critChance", "&b3-6");
/*  262: 260 */       this.PlayerDataConfig.set("25.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  263: 261 */       this.PlayerDataConfig.set("25.1.properties.critDamage", "&b2-4");
/*  264: 262 */       this.PlayerDataConfig.set("25.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  265: 263 */       this.PlayerDataConfig.set("25.1.properties.healthRegen", "&b1-2");
/*  266: 264 */       this.PlayerDataConfig.set("25.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  267: 265 */       this.PlayerDataConfig.set("25.1.properties.lifeSteal", "&21-3");
/*  268: 266 */       this.PlayerDataConfig.set("25.1.properties.poisonRandomApply", Boolean.valueOf(true));
/*  269: 267 */       this.PlayerDataConfig.set("25.1.properties.poison", "&a3-5");
/*  270: 268 */       this.PlayerDataConfig.set("25.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  271: 269 */       this.PlayerDataConfig.set("25.1.properties.movementSpeed", "&e2-4");
/*  272: 270 */       this.PlayerDataConfig.set("25.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  273: 271 */       this.PlayerDataConfig.set("25.1.properties.xpLevel", "&b3-player+2");
/*  274: 272 */       this.PlayerDataConfig.set("25.1.properties.durability", "&7600-750");
/*  275: 273 */       this.PlayerDataConfig.set("25.1.properties.droppedXp", "2");
/*  276:     */       
/*  277: 275 */       this.PlayerDataConfig.set("10", null);
/*  278: 276 */       this.PlayerDataConfig.set("10.1.itemId", Integer.valueOf(261));
/*  279: 277 */       this.PlayerDataConfig.set("10.1.nameColour", "&c");
/*  280: 278 */       this.PlayerDataConfig.set("10.1.prefix", "random");
/*  281: 279 */       this.PlayerDataConfig.set("10.1.suffix", "random");
/*  282: 280 */       this.PlayerDataConfig.set("10.1.properties.damage", "&b15-40-player+30-65");
/*  283: 281 */       this.PlayerDataConfig.set("10.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  284: 282 */       this.PlayerDataConfig.set("10.1.properties.critChance", "&b6-8");
/*  285: 283 */       this.PlayerDataConfig.set("10.5.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  286: 284 */       this.PlayerDataConfig.set("10.5.properties.critDamage", "&b1-5");
/*  287: 285 */       this.PlayerDataConfig.set("10.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  288: 286 */       this.PlayerDataConfig.set("10.1.properties.healthRegen", "&b1-3");
/*  289: 287 */       this.PlayerDataConfig.set("10.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  290: 288 */       this.PlayerDataConfig.set("10.1.properties.lifeSteal", "&22-5");
/*  291: 289 */       this.PlayerDataConfig.set("10.1.properties.poisonRandomApply", Boolean.valueOf(true));
/*  292: 290 */       this.PlayerDataConfig.set("10.1.properties.poison", "&a4-6");
/*  293: 291 */       this.PlayerDataConfig.set("10.1.properties.harmingRandomApply", Boolean.valueOf(true));
/*  294: 292 */       this.PlayerDataConfig.set("10.1.properties.harming", "&d1-2");
/*  295: 293 */       this.PlayerDataConfig.set("10.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  296: 294 */       this.PlayerDataConfig.set("10.1.properties.movementSpeed", "&e2-4");
/*  297: 295 */       this.PlayerDataConfig.set("10.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  298: 296 */       this.PlayerDataConfig.set("10.1.properties.xpLevel", "&b3-player+2");
/*  299: 297 */       this.PlayerDataConfig.set("10.1.properties.durability", "&7300-550");
/*  300: 298 */       this.PlayerDataConfig.set("10.1.properties.droppedXp", "3");
/*  301: 299 */       this.PlayerDataConfig.set("10.1.properties.spells.frostbolt", Boolean.valueOf(true));
/*  302:     */       
/*  303: 301 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  304:     */     }
/*  305:     */     catch (Exception e)
/*  306:     */     {
/*  307: 304 */       e.printStackTrace();
/*  308: 305 */       System.out.println("*********** Failed to save default cave_spider file! ***********");
/*  309:     */     }
/*  310:     */   }
/*  311:     */   
/*  312:     */   public void writeCreeper()
/*  313:     */   {
/*  314:     */     try
/*  315:     */     {
/*  316: 313 */       this.PlayerDataConfig = new YamlConfiguration();
/*  317: 314 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "creeper.yml");
/*  318:     */       
/*  319: 316 */       this.PlayerDataConfig.set("8", null);
/*  320: 317 */       this.PlayerDataConfig.set("8.1.itemId", Integer.valueOf(306));
/*  321: 318 */       this.PlayerDataConfig.set("8.1.nameColour", "&9&l");
/*  322: 319 */       this.PlayerDataConfig.set("8.1.prefix", "random");
/*  323: 320 */       this.PlayerDataConfig.set("8.1.suffix", "random");
/*  324: 321 */       this.PlayerDataConfig.set("8.1.properties.armour", "&b3-7");
/*  325: 322 */       this.PlayerDataConfig.set("8.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  326: 323 */       this.PlayerDataConfig.set("8.1.properties.health", "&b20-player+30");
/*  327: 324 */       this.PlayerDataConfig.set("8.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  328: 325 */       this.PlayerDataConfig.set("8.1.properties.healthRegen", "&b2-5");
/*  329: 326 */       this.PlayerDataConfig.set("8.1.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  330: 327 */       this.PlayerDataConfig.set("8.1.properties.block", "&b1-2");
/*  331: 328 */       this.PlayerDataConfig.set("8.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  332: 329 */       this.PlayerDataConfig.set("8.1.properties.movementSpeed", "&e2-4");
/*  333: 330 */       this.PlayerDataConfig.set("8.1.properties.durability", "&71800-2400");
/*  334: 331 */       this.PlayerDataConfig.set("8.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  335: 332 */       this.PlayerDataConfig.set("8.1.properties.soulbound", Boolean.valueOf(true));
/*  336: 333 */       this.PlayerDataConfig.set("8.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  337: 334 */       this.PlayerDataConfig.set("8.1.properties.xpLevel", "&b10-player+3");
/*  338:     */       
/*  339: 336 */       this.PlayerDataConfig.set("8.2.itemId", Integer.valueOf(307));
/*  340: 337 */       this.PlayerDataConfig.set("8.2.nameColour", "&9&l");
/*  341: 338 */       this.PlayerDataConfig.set("8.2.prefix", "random");
/*  342: 339 */       this.PlayerDataConfig.set("8.2.suffix", "random");
/*  343: 340 */       this.PlayerDataConfig.set("8.2.properties.armour", "&b3-7");
/*  344: 341 */       this.PlayerDataConfig.set("8.2.properties.healthRandomApply", Boolean.valueOf(true));
/*  345: 342 */       this.PlayerDataConfig.set("8.2.properties.health", "&b20-player+30");
/*  346: 343 */       this.PlayerDataConfig.set("8.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  347: 344 */       this.PlayerDataConfig.set("8.2.properties.healthRegen", "&b2-5");
/*  348: 345 */       this.PlayerDataConfig.set("8.2.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  349: 346 */       this.PlayerDataConfig.set("8.2.properties.block", "&b1-2");
/*  350: 347 */       this.PlayerDataConfig.set("8.2.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  351: 348 */       this.PlayerDataConfig.set("8.2.properties.movementSpeed", "&e2-4");
/*  352: 349 */       this.PlayerDataConfig.set("8.2.properties.durability", "&71800-2400");
/*  353: 350 */       this.PlayerDataConfig.set("8.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  354: 351 */       this.PlayerDataConfig.set("8.2.properties.soulbound", Boolean.valueOf(true));
/*  355: 352 */       this.PlayerDataConfig.set("8.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  356: 353 */       this.PlayerDataConfig.set("8.2.properties.xpLevel", "&b10-player+3");
/*  357:     */       
/*  358: 355 */       this.PlayerDataConfig.set("8.3.itemId", Integer.valueOf(308));
/*  359: 356 */       this.PlayerDataConfig.set("8.3.nameColour", "&9&l");
/*  360: 357 */       this.PlayerDataConfig.set("8.3.prefix", "random");
/*  361: 358 */       this.PlayerDataConfig.set("8.3.suffix", "random");
/*  362: 359 */       this.PlayerDataConfig.set("8.3.properties.armour", "&b3-7");
/*  363: 360 */       this.PlayerDataConfig.set("8.3.properties.healthRandomApply", Boolean.valueOf(true));
/*  364: 361 */       this.PlayerDataConfig.set("8.3.properties.health", "&b20-player+30");
/*  365: 362 */       this.PlayerDataConfig.set("8.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  366: 363 */       this.PlayerDataConfig.set("8.3.properties.healthRegen", "&b2-5");
/*  367: 364 */       this.PlayerDataConfig.set("8.3.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  368: 365 */       this.PlayerDataConfig.set("8.3.properties.block", "&b1-2");
/*  369: 366 */       this.PlayerDataConfig.set("8.3.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  370: 367 */       this.PlayerDataConfig.set("8.3.properties.movementSpeed", "&e2-4");
/*  371: 368 */       this.PlayerDataConfig.set("8.3.properties.durability", "&71800-2400");
/*  372: 369 */       this.PlayerDataConfig.set("8.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  373: 370 */       this.PlayerDataConfig.set("8.3.properties.soulbound", Boolean.valueOf(true));
/*  374: 371 */       this.PlayerDataConfig.set("8.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  375: 372 */       this.PlayerDataConfig.set("8.3.properties.xpLevel", "&b10-player+3");
/*  376:     */       
/*  377: 374 */       this.PlayerDataConfig.set("8.4.itemId", Integer.valueOf(309));
/*  378: 375 */       this.PlayerDataConfig.set("8.4.nameColour", "&9&l");
/*  379: 376 */       this.PlayerDataConfig.set("8.4.prefix", "random");
/*  380: 377 */       this.PlayerDataConfig.set("8.4.suffix", "random");
/*  381: 378 */       this.PlayerDataConfig.set("8.4.properties.armour", "&b3-7");
/*  382: 379 */       this.PlayerDataConfig.set("8.4.properties.healthRandomApply", Boolean.valueOf(true));
/*  383: 380 */       this.PlayerDataConfig.set("8.4.properties.health", "&b20-player+30");
/*  384: 381 */       this.PlayerDataConfig.set("8.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  385: 382 */       this.PlayerDataConfig.set("8.4.properties.healthRegen", "&b2-5");
/*  386: 383 */       this.PlayerDataConfig.set("8.4.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  387: 384 */       this.PlayerDataConfig.set("8.4.properties.block", "&b1-2");
/*  388: 385 */       this.PlayerDataConfig.set("8.4.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  389: 386 */       this.PlayerDataConfig.set("8.4.properties.movementSpeed", "&e2-4");
/*  390: 387 */       this.PlayerDataConfig.set("8.4.properties.durability", "&71800-2400");
/*  391: 388 */       this.PlayerDataConfig.set("8.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  392: 389 */       this.PlayerDataConfig.set("8.4.properties.soulbound", Boolean.valueOf(true));
/*  393: 390 */       this.PlayerDataConfig.set("8.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  394: 391 */       this.PlayerDataConfig.set("8.4.properties.xpLevel", "&b10-player+3");
/*  395:     */       
/*  396: 393 */       this.PlayerDataConfig.set("8.5.itemId", Integer.valueOf(258));
/*  397: 394 */       this.PlayerDataConfig.set("8.5.nameColour", "&9&l");
/*  398: 395 */       this.PlayerDataConfig.set("8.5.prefix", "random");
/*  399: 396 */       this.PlayerDataConfig.set("8.5.suffix", "random");
/*  400: 397 */       this.PlayerDataConfig.set("8.5.properties.weaponspeed", "&2Fast");
/*  401: 398 */       this.PlayerDataConfig.set("8.5.properties.damage", "&b1-6-player+1-7");
/*  402: 399 */       this.PlayerDataConfig.set("8.5.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  403: 400 */       this.PlayerDataConfig.set("8.5.properties.critChance", "&b1-7");
/*  404: 401 */       this.PlayerDataConfig.set("8.5.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  405: 402 */       this.PlayerDataConfig.set("8.5.properties.critDamage", "&b5-18");
/*  406: 403 */       this.PlayerDataConfig.set("8.5.properties.fireRandomApply", Boolean.valueOf(true));
/*  407: 404 */       this.PlayerDataConfig.set("8.5.properties.fire", "&42-6");
/*  408: 405 */       this.PlayerDataConfig.set("8.5.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  409: 406 */       this.PlayerDataConfig.set("8.5.properties.movementSpeed", "&e1-4");
/*  410: 407 */       this.PlayerDataConfig.set("8.5.properties.durability", "&7500-625");
/*  411: 408 */       this.PlayerDataConfig.set("8.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  412: 409 */       this.PlayerDataConfig.set("8.5.properties.soulbound", Boolean.valueOf(true));
/*  413: 410 */       this.PlayerDataConfig.set("8.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  414: 411 */       this.PlayerDataConfig.set("8.5.properties.xpLevel", "&b4-player+5");
/*  415:     */       
/*  416: 413 */       this.PlayerDataConfig.set("8.6.itemId", Integer.valueOf(257));
/*  417: 414 */       this.PlayerDataConfig.set("8.6.nameColour", "&9&l");
/*  418: 415 */       this.PlayerDataConfig.set("8.6.prefix", "random");
/*  419: 416 */       this.PlayerDataConfig.set("8.6.suffix", "random");
/*  420: 417 */       this.PlayerDataConfig.set("8.6.properties.weaponspeed", "&2Fast");
/*  421: 418 */       this.PlayerDataConfig.set("8.6.properties.damage", "&b1-6-player+1-7");
/*  422: 419 */       this.PlayerDataConfig.set("8.6.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  423: 420 */       this.PlayerDataConfig.set("8.6.properties.critChance", "&b1-7");
/*  424: 421 */       this.PlayerDataConfig.set("8.6.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  425: 422 */       this.PlayerDataConfig.set("8.6.properties.critDamage", "&b5-18");
/*  426: 423 */       this.PlayerDataConfig.set("8.6.properties.fireRandomApply", Boolean.valueOf(true));
/*  427: 424 */       this.PlayerDataConfig.set("8.6.properties.fire", "&42-6");
/*  428: 425 */       this.PlayerDataConfig.set("8.6.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  429: 426 */       this.PlayerDataConfig.set("8.6.properties.movementSpeed", "&e1-4");
/*  430: 427 */       this.PlayerDataConfig.set("8.6.properties.durability", "&7500-625");
/*  431: 428 */       this.PlayerDataConfig.set("8.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  432: 429 */       this.PlayerDataConfig.set("8.6.properties.soulbound", Boolean.valueOf(true));
/*  433: 430 */       this.PlayerDataConfig.set("8.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  434: 431 */       this.PlayerDataConfig.set("8.6.properties.xpLevel", "&b4-player+5");
/*  435:     */       
/*  436: 433 */       this.PlayerDataConfig.set("8.7.itemId", Integer.valueOf(261));
/*  437: 434 */       this.PlayerDataConfig.set("8.7.nameColour", "&9&l");
/*  438: 435 */       this.PlayerDataConfig.set("8.7.prefix", "random");
/*  439: 436 */       this.PlayerDataConfig.set("8.7.suffix", "random");
/*  440: 437 */       this.PlayerDataConfig.set("8.7.properties.weaponspeed", "&2Fast");
/*  441: 438 */       this.PlayerDataConfig.set("8.7.properties.damage", "&b1-6-player+1-7");
/*  442: 439 */       this.PlayerDataConfig.set("8.7.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  443: 440 */       this.PlayerDataConfig.set("8.7.properties.critChance", "&b1-7");
/*  444: 441 */       this.PlayerDataConfig.set("8.7.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  445: 442 */       this.PlayerDataConfig.set("8.7.properties.critDamage", "&b5-18");
/*  446: 443 */       this.PlayerDataConfig.set("8.7.properties.fireRandomApply", Boolean.valueOf(true));
/*  447: 444 */       this.PlayerDataConfig.set("8.7.properties.fire", "&42-6");
/*  448: 445 */       this.PlayerDataConfig.set("8.7.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  449: 446 */       this.PlayerDataConfig.set("8.7.properties.movementSpeed", "&b1-4");
/*  450: 447 */       this.PlayerDataConfig.set("8.7.properties.durability", "&7500-625");
/*  451: 448 */       this.PlayerDataConfig.set("8.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  452: 449 */       this.PlayerDataConfig.set("8.7.properties.soulbound", Boolean.valueOf(true));
/*  453: 450 */       this.PlayerDataConfig.set("8.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  454: 451 */       this.PlayerDataConfig.set("8.7.properties.xpLevel", "&b4-player+5");
/*  455:     */       
/*  456: 453 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  457:     */     }
/*  458:     */     catch (Exception e)
/*  459:     */     {
/*  460: 456 */       e.printStackTrace();
/*  461: 457 */       System.out.println("*********** Failed to save default creeper file! ***********");
/*  462:     */     }
/*  463:     */   }
/*  464:     */   
/*  465:     */   public void writeEnder_Dragon()
/*  466:     */   {
/*  467:     */     try
/*  468:     */     {
/*  469: 465 */       this.PlayerDataConfig = new YamlConfiguration();
/*  470: 466 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "ender_dragon.yml");
/*  471:     */       
/*  472: 468 */       this.PlayerDataConfig.set("100", null);
/*  473: 469 */       this.PlayerDataConfig.set("100.1.itemId", Integer.valueOf(276));
/*  474: 470 */       this.PlayerDataConfig.set("100.1.nameColour", "&9&l");
/*  475: 471 */       this.PlayerDataConfig.set("100.1.prefix", "random");
/*  476: 472 */       this.PlayerDataConfig.set("100.1.suffix", "random");
/*  477: 473 */       this.PlayerDataConfig.set("100.1.properties.damage", "&b1-6-player+1-7");
/*  478: 474 */       this.PlayerDataConfig.set("100.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  479: 475 */       this.PlayerDataConfig.set("100.1.properties.critChance", "&b8-14");
/*  480: 476 */       this.PlayerDataConfig.set("100.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  481: 477 */       this.PlayerDataConfig.set("100.1.properties.critDamage", "&b7-23");
/*  482: 478 */       this.PlayerDataConfig.set("100.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  483: 479 */       this.PlayerDataConfig.set("100.1.properties.health", "&b65-player+115");
/*  484: 480 */       this.PlayerDataConfig.set("100.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  485: 481 */       this.PlayerDataConfig.set("100.1.properties.healthRegen", "&b3-5");
/*  486: 482 */       this.PlayerDataConfig.set("100.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  487: 483 */       this.PlayerDataConfig.set("100.1.properties.lifeSteal", "&22-5");
/*  488: 484 */       this.PlayerDataConfig.set("100.1.properties.witherRandomApply", Boolean.valueOf(true));
/*  489: 485 */       this.PlayerDataConfig.set("100.1.properties.wither", "&74-6");
/*  490: 486 */       this.PlayerDataConfig.set("100.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  491: 487 */       this.PlayerDataConfig.set("100.1.properties.movementSpeed", "&e2-4");
/*  492: 488 */       this.PlayerDataConfig.set("100.1.properties.durability", "&71900-2450");
/*  493: 489 */       this.PlayerDataConfig.set("100.1.properties.droppedXp", "250");
/*  494: 490 */       this.PlayerDataConfig.set("100.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  495: 491 */       this.PlayerDataConfig.set("100.1.properties.soulbound", Boolean.valueOf(true));
/*  496: 492 */       this.PlayerDataConfig.set("100.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  497: 493 */       this.PlayerDataConfig.set("100.1.properties.xpLevel", "&b3-player+2");
/*  498: 494 */       this.PlayerDataConfig.set("100.1.properties.tntRandomApply", Boolean.valueOf(true));
/*  499: 495 */       this.PlayerDataConfig.set("100.1.properties.spells.tnt", Boolean.valueOf(true));
/*  500:     */       
/*  501: 497 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  502:     */     }
/*  503:     */     catch (Exception e)
/*  504:     */     {
/*  505: 500 */       e.printStackTrace();
/*  506: 501 */       System.out.println("*********** Failed to save default ender_dragon file! ***********");
/*  507:     */     }
/*  508:     */   }
/*  509:     */   
/*  510:     */   public void writeEnderman()
/*  511:     */   {
/*  512:     */     try
/*  513:     */     {
/*  514: 509 */       this.PlayerDataConfig = new YamlConfiguration();
/*  515: 510 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "enderman.yml");
/*  516:     */       
/*  517: 512 */       this.PlayerDataConfig.set("5", null);
/*  518: 513 */       this.PlayerDataConfig.set("5.1.itemId", Integer.valueOf(310));
/*  519: 514 */       this.PlayerDataConfig.set("5.1.nameColour", "&d&l");
/*  520: 515 */       this.PlayerDataConfig.set("5.1.prefix", "random");
/*  521: 516 */       this.PlayerDataConfig.set("5.1.suffix", "random");
/*  522: 517 */       this.PlayerDataConfig.set("5.1.properties.armour", "&b5-9");
/*  523: 518 */       this.PlayerDataConfig.set("5.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  524: 519 */       this.PlayerDataConfig.set("5.1.properties.health", "&b50-player+60");
/*  525: 520 */       this.PlayerDataConfig.set("5.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  526: 521 */       this.PlayerDataConfig.set("5.1.properties.healthRegen", "&b3-6");
/*  527: 522 */       this.PlayerDataConfig.set("5.1.properties.blockRandomApply", Boolean.valueOf(true));
/*  528: 523 */       this.PlayerDataConfig.set("5.1.properties.block", "&b1-3");
/*  529: 524 */       this.PlayerDataConfig.set("5.1.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  530: 525 */       this.PlayerDataConfig.set("5.1.properties.block", "&b1-3");
/*  531: 526 */       this.PlayerDataConfig.set("5.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  532: 527 */       this.PlayerDataConfig.set("5.1.properties.lifeSteal", "&23-6");
/*  533: 528 */       this.PlayerDataConfig.set("5.1.properties.durability", "&72500-3300");
/*  534: 529 */       this.PlayerDataConfig.set("5.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  535: 530 */       this.PlayerDataConfig.set("5.1.properties.soulbound", Boolean.valueOf(true));
/*  536: 531 */       this.PlayerDataConfig.set("5.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  537: 532 */       this.PlayerDataConfig.set("5.1.properties.xpLevel", "&b10-player+3");
/*  538:     */       
/*  539: 534 */       this.PlayerDataConfig.set("5.2.itemId", Integer.valueOf(311));
/*  540: 535 */       this.PlayerDataConfig.set("5.2.nameColour", "&d&l");
/*  541: 536 */       this.PlayerDataConfig.set("5.2.prefix", "random");
/*  542: 537 */       this.PlayerDataConfig.set("5.2.suffix", "random");
/*  543: 538 */       this.PlayerDataConfig.set("5.2.properties.armour", "&b5-9");
/*  544: 539 */       this.PlayerDataConfig.set("5.2.properties.healthRandomApply", Boolean.valueOf(true));
/*  545: 540 */       this.PlayerDataConfig.set("5.2.properties.health", "&b50-player+60");
/*  546: 541 */       this.PlayerDataConfig.set("5.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  547: 542 */       this.PlayerDataConfig.set("5.2.properties.healthRegen", "&b3-6");
/*  548: 543 */       this.PlayerDataConfig.set("5.2.properties.blockRandomApply", Boolean.valueOf(true));
/*  549: 544 */       this.PlayerDataConfig.set("5.2.properties.block", "&b1-3");
/*  550: 545 */       this.PlayerDataConfig.set("5.2.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  551: 546 */       this.PlayerDataConfig.set("5.2.properties.block", "&b1-3");
/*  552: 547 */       this.PlayerDataConfig.set("5.2.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  553: 548 */       this.PlayerDataConfig.set("5.2.properties.lifeSteal", "&23-6");
/*  554: 549 */       this.PlayerDataConfig.set("5.2.properties.durability", "&72500-3300");
/*  555: 550 */       this.PlayerDataConfig.set("5.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  556: 551 */       this.PlayerDataConfig.set("5.2.properties.soulbound", Boolean.valueOf(true));
/*  557: 552 */       this.PlayerDataConfig.set("5.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  558: 553 */       this.PlayerDataConfig.set("5.2.properties.xpLevel", "&b10-player+3");
/*  559:     */       
/*  560: 555 */       this.PlayerDataConfig.set("5.3.itemId", Integer.valueOf(312));
/*  561: 556 */       this.PlayerDataConfig.set("5.3.nameColour", "&d&l");
/*  562: 557 */       this.PlayerDataConfig.set("5.3.prefix", "random");
/*  563: 558 */       this.PlayerDataConfig.set("5.3.suffix", "random");
/*  564: 559 */       this.PlayerDataConfig.set("5.3.properties.armour", "&b5-9");
/*  565: 560 */       this.PlayerDataConfig.set("5.3.properties.healthRandomApply", Boolean.valueOf(true));
/*  566: 561 */       this.PlayerDataConfig.set("5.3.properties.health", "&b50-player+60");
/*  567: 562 */       this.PlayerDataConfig.set("5.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  568: 563 */       this.PlayerDataConfig.set("5.3.properties.healthRegen", "&b3-6");
/*  569: 564 */       this.PlayerDataConfig.set("5.3.properties.blockRandomApply", Boolean.valueOf(true));
/*  570: 565 */       this.PlayerDataConfig.set("5.3.properties.block", "&b1-3");
/*  571: 566 */       this.PlayerDataConfig.set("5.3.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  572: 567 */       this.PlayerDataConfig.set("5.3.properties.block", "&b1-3");
/*  573: 568 */       this.PlayerDataConfig.set("5.3.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  574: 569 */       this.PlayerDataConfig.set("5.3.properties.lifeSteal", "&23-6");
/*  575: 570 */       this.PlayerDataConfig.set("5.3.properties.durability", "&72500-3300");
/*  576: 571 */       this.PlayerDataConfig.set("5.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  577: 572 */       this.PlayerDataConfig.set("5.3.properties.soulbound", Boolean.valueOf(true));
/*  578: 573 */       this.PlayerDataConfig.set("5.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  579: 574 */       this.PlayerDataConfig.set("5.3.properties.xpLevel", "&b10-player+3");
/*  580:     */       
/*  581: 576 */       this.PlayerDataConfig.set("5.4.itemId", Integer.valueOf(313));
/*  582: 577 */       this.PlayerDataConfig.set("5.4.nameColour", "&d&l");
/*  583: 578 */       this.PlayerDataConfig.set("5.4.prefix", "random");
/*  584: 579 */       this.PlayerDataConfig.set("5.4.suffix", "random");
/*  585: 580 */       this.PlayerDataConfig.set("5.4.properties.armour", "&b5-9");
/*  586: 581 */       this.PlayerDataConfig.set("5.4.properties.healthRandomApply", Boolean.valueOf(true));
/*  587: 582 */       this.PlayerDataConfig.set("5.4.properties.health", "&b50-player+60");
/*  588: 583 */       this.PlayerDataConfig.set("5.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  589: 584 */       this.PlayerDataConfig.set("5.4.properties.healthRegen", "&b3-6");
/*  590: 585 */       this.PlayerDataConfig.set("5.4.properties.blockRandomApply", Boolean.valueOf(true));
/*  591: 586 */       this.PlayerDataConfig.set("5.4.properties.block", "&b1-3");
/*  592: 587 */       this.PlayerDataConfig.set("5.4.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  593: 588 */       this.PlayerDataConfig.set("5.4.properties.block", "&b1-3");
/*  594: 589 */       this.PlayerDataConfig.set("5.4.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  595: 590 */       this.PlayerDataConfig.set("5.4.properties.lifeSteal", "&23-6");
/*  596: 591 */       this.PlayerDataConfig.set("5.4.properties.durability", "&72500-3300");
/*  597: 592 */       this.PlayerDataConfig.set("5.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  598: 593 */       this.PlayerDataConfig.set("5.4.properties.soulbound", Boolean.valueOf(true));
/*  599: 594 */       this.PlayerDataConfig.set("5.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  600: 595 */       this.PlayerDataConfig.set("5.4.properties.xpLevel", "&b10-player+3");
/*  601:     */       
/*  602: 597 */       this.PlayerDataConfig.set("5.5.itemId", Integer.valueOf(267));
/*  603: 598 */       this.PlayerDataConfig.set("5.5.nameColour", "&d&l");
/*  604: 599 */       this.PlayerDataConfig.set("5.5.prefix", "random");
/*  605: 600 */       this.PlayerDataConfig.set("5.5.suffix", "random");
/*  606: 601 */       this.PlayerDataConfig.set("5.5.properties.weaponspeed", "&eNormal");
/*  607: 602 */       this.PlayerDataConfig.set("5.5.properties.damage", "&b1-6-player+1-7");
/*  608: 603 */       this.PlayerDataConfig.set("5.5.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  609: 604 */       this.PlayerDataConfig.set("5.5.properties.critChance", "&b2-8");
/*  610: 605 */       this.PlayerDataConfig.set("5.5.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  611: 606 */       this.PlayerDataConfig.set("5.5.properties.critDamage", "&b7-23");
/*  612: 607 */       this.PlayerDataConfig.set("5.5.properties.blindRandomApply", Boolean.valueOf(true));
/*  613: 608 */       this.PlayerDataConfig.set("5.5.properties.blind", "&f3-7");
/*  614: 609 */       this.PlayerDataConfig.set("5.5.properties.iceRandomApply", Boolean.valueOf(true));
/*  615: 610 */       this.PlayerDataConfig.set("5.5.properties.ice", "&b1-4");
/*  616: 611 */       this.PlayerDataConfig.set("5.5.properties.durability", "&7500-625");
/*  617: 612 */       this.PlayerDataConfig.set("5.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  618: 613 */       this.PlayerDataConfig.set("5.5.properties.soulbound", Boolean.valueOf(true));
/*  619: 614 */       this.PlayerDataConfig.set("5.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  620: 615 */       this.PlayerDataConfig.set("5.5.properties.xpLevel", "&b3-player+5");
/*  621:     */       
/*  622: 617 */       this.PlayerDataConfig.set("5.6.itemId", Integer.valueOf(292));
/*  623: 618 */       this.PlayerDataConfig.set("5.6.nameColour", "&d&l");
/*  624: 619 */       this.PlayerDataConfig.set("5.6.prefix", "random");
/*  625: 620 */       this.PlayerDataConfig.set("5.6.suffix", "random");
/*  626: 621 */       this.PlayerDataConfig.set("5.6.properties.weaponspeed", "&eNormal");
/*  627: 622 */       this.PlayerDataConfig.set("5.6.properties.damage", "&b1-6-player+1-7");
/*  628: 623 */       this.PlayerDataConfig.set("5.6.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  629: 624 */       this.PlayerDataConfig.set("5.6.properties.critChance", "&b2-8");
/*  630: 625 */       this.PlayerDataConfig.set("5.6.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  631: 626 */       this.PlayerDataConfig.set("5.6.properties.critDamage", "&b7-23");
/*  632: 627 */       this.PlayerDataConfig.set("5.6.properties.blindRandomApply", Boolean.valueOf(true));
/*  633: 628 */       this.PlayerDataConfig.set("5.6.properties.blind", "&f3-7");
/*  634: 629 */       this.PlayerDataConfig.set("5.6.properties.iceRandomApply", Boolean.valueOf(true));
/*  635: 630 */       this.PlayerDataConfig.set("5.6.properties.ice", "&b1-4");
/*  636: 631 */       this.PlayerDataConfig.set("5.6.properties.durability", "&7500-625");
/*  637: 632 */       this.PlayerDataConfig.set("5.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  638: 633 */       this.PlayerDataConfig.set("5.6.properties.soulbound", Boolean.valueOf(true));
/*  639: 634 */       this.PlayerDataConfig.set("5.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  640: 635 */       this.PlayerDataConfig.set("5.6.properties.xpLevel", "&b3-player+5");
/*  641:     */       
/*  642: 637 */       this.PlayerDataConfig.set("5.7.itemId", Integer.valueOf(261));
/*  643: 638 */       this.PlayerDataConfig.set("5.7.nameColour", "&d&l");
/*  644: 639 */       this.PlayerDataConfig.set("5.7.prefix", "random");
/*  645: 640 */       this.PlayerDataConfig.set("5.7.suffix", "random");
/*  646: 641 */       this.PlayerDataConfig.set("5.7.properties.weaponspeed", "&eNormal");
/*  647: 642 */       this.PlayerDataConfig.set("5.7.properties.damage", "&b1-6-player+1-7");
/*  648: 643 */       this.PlayerDataConfig.set("5.7.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  649: 644 */       this.PlayerDataConfig.set("5.7.properties.critChance", "&b2-8");
/*  650: 645 */       this.PlayerDataConfig.set("5.7.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  651: 646 */       this.PlayerDataConfig.set("5.7.properties.critDamage", "&b7-23");
/*  652: 647 */       this.PlayerDataConfig.set("5.7.properties.blindRandomApply", Boolean.valueOf(true));
/*  653: 648 */       this.PlayerDataConfig.set("5.7.properties.blind", "&f3-7");
/*  654: 649 */       this.PlayerDataConfig.set("5.7.properties.iceRandomApply", Boolean.valueOf(true));
/*  655: 650 */       this.PlayerDataConfig.set("5.7.properties.ice", "&b1-4");
/*  656: 651 */       this.PlayerDataConfig.set("5.7.properties.durability", "&7500-625");
/*  657: 652 */       this.PlayerDataConfig.set("5.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  658: 653 */       this.PlayerDataConfig.set("5.7.properties.soulbound", Boolean.valueOf(true));
/*  659: 654 */       this.PlayerDataConfig.set("5.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  660: 655 */       this.PlayerDataConfig.set("5.7.properties.xpLevel", "&b3-player+5");
/*  661:     */       
/*  662: 657 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  663:     */     }
/*  664:     */     catch (Exception e)
/*  665:     */     {
/*  666: 660 */       e.printStackTrace();
/*  667: 661 */       System.out.println("*********** Failed to save default enderman file! ***********");
/*  668:     */     }
/*  669:     */   }
/*  670:     */   
/*  671:     */   public void writeGhast()
/*  672:     */   {
/*  673:     */     try
/*  674:     */     {
/*  675: 669 */       this.PlayerDataConfig = new YamlConfiguration();
/*  676: 670 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "ghast.yml");
/*  677:     */       
/*  678: 672 */       this.PlayerDataConfig.set("40", null);
/*  679: 673 */       this.PlayerDataConfig.set("40.1.itemId", Integer.valueOf(261));
/*  680: 674 */       this.PlayerDataConfig.set("40.1.nameColour", "&6&l");
/*  681: 675 */       this.PlayerDataConfig.set("40.1.prefix", "random");
/*  682: 676 */       this.PlayerDataConfig.set("40.1.suffix", "random");
/*  683: 677 */       this.PlayerDataConfig.set("40.1.properties.damage", "&b1-6-player+1-7");
/*  684: 678 */       this.PlayerDataConfig.set("40.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  685: 679 */       this.PlayerDataConfig.set("40.1.properties.critChance", "&b1-5");
/*  686: 680 */       this.PlayerDataConfig.set("40.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  687: 681 */       this.PlayerDataConfig.set("40.1.properties.critDamage", "&b8-14");
/*  688: 682 */       this.PlayerDataConfig.set("40.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  689: 683 */       this.PlayerDataConfig.set("40.1.properties.lifeSteal", "&23-5");
/*  690: 684 */       this.PlayerDataConfig.set("40.1.properties.fireRandomApply", Boolean.valueOf(true));
/*  691: 685 */       this.PlayerDataConfig.set("40.1.properties.fire", "&44-8");
/*  692: 686 */       this.PlayerDataConfig.set("40.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  693: 687 */       this.PlayerDataConfig.set("40.1.properties.movementSpeed", "&e1-4");
/*  694: 688 */       this.PlayerDataConfig.set("40.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  695: 689 */       this.PlayerDataConfig.set("40.1.properties.xpLevel", "&b3-player+2");
/*  696: 690 */       this.PlayerDataConfig.set("40.1.properties.durability", "&7900-1350");
/*  697: 691 */       this.PlayerDataConfig.set("40.1.properties.droppedXp", "2");
/*  698:     */       
/*  699: 693 */       this.PlayerDataConfig.set("25", null);
/*  700: 694 */       this.PlayerDataConfig.set("25.1.itemId", Integer.valueOf(309));
/*  701: 695 */       this.PlayerDataConfig.set("25.1.nameColour", "&6&l");
/*  702: 696 */       this.PlayerDataConfig.set("25.1.prefix", "random");
/*  703: 697 */       this.PlayerDataConfig.set("25.1.suffix", "random");
/*  704: 698 */       this.PlayerDataConfig.set("25.1.properties.armour", "&b3-7");
/*  705: 699 */       this.PlayerDataConfig.set("25.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  706: 700 */       this.PlayerDataConfig.set("25.1.properties.health", "&b40-player+80");
/*  707: 701 */       this.PlayerDataConfig.set("25.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  708: 702 */       this.PlayerDataConfig.set("25.1.properties.healthRegen", "&b2-5");
/*  709: 703 */       this.PlayerDataConfig.set("25.1.properties.fireRandomApply", Boolean.valueOf(true));
/*  710: 704 */       this.PlayerDataConfig.set("25.1.properties.fire", "&42-6");
/*  711: 705 */       this.PlayerDataConfig.set("25.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  712: 706 */       this.PlayerDataConfig.set("25.1.properties.movementSpeed", "&e2-3");
/*  713: 707 */       this.PlayerDataConfig.set("25.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  714: 708 */       this.PlayerDataConfig.set("25.1.properties.xpLevel", "&b3-player+2");
/*  715: 709 */       this.PlayerDataConfig.set("25.1.properties.durability", "&7350-650");
/*  716: 710 */       this.PlayerDataConfig.set("25.1.properties.fireballRandomApply", Boolean.valueOf(true));
/*  717: 711 */       this.PlayerDataConfig.set("25.1.properties.spells.fireball", Boolean.valueOf(true));
/*  718:     */       
/*  719: 713 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  720:     */     }
/*  721:     */     catch (Exception e)
/*  722:     */     {
/*  723: 716 */       e.printStackTrace();
/*  724: 717 */       System.out.println("*********** Failed to save default ghast file! ***********");
/*  725:     */     }
/*  726:     */   }
/*  727:     */   
/*  728:     */   public void writeMagma_Cube()
/*  729:     */   {
/*  730:     */     try
/*  731:     */     {
/*  732: 725 */       this.PlayerDataConfig = new YamlConfiguration();
/*  733: 726 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "magma_cube.yml");
/*  734:     */       
/*  735: 728 */       this.PlayerDataConfig.set("75", null);
/*  736: 729 */       this.PlayerDataConfig.set("75.1.itemId", Integer.valueOf(283));
/*  737: 730 */       this.PlayerDataConfig.set("75.1.nameColour", "&6&l");
/*  738: 731 */       this.PlayerDataConfig.set("75.1.prefix", "random");
/*  739: 732 */       this.PlayerDataConfig.set("75.1.suffix", "random");
/*  740: 733 */       this.PlayerDataConfig.set("75.1.properties.damage", "&b1-6-player+1-7");
/*  741: 734 */       this.PlayerDataConfig.set("75.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  742: 735 */       this.PlayerDataConfig.set("75.1.properties.critChance", "&b2-4");
/*  743: 736 */       this.PlayerDataConfig.set("75.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  744: 737 */       this.PlayerDataConfig.set("75.1.properties.critDamage", "&b8-14");
/*  745: 738 */       this.PlayerDataConfig.set("75.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  746: 739 */       this.PlayerDataConfig.set("75.1.properties.lifeSteal", "&22-5");
/*  747: 740 */       this.PlayerDataConfig.set("75.1.properties.fireRandomApply", Boolean.valueOf(true));
/*  748: 741 */       this.PlayerDataConfig.set("75.1.properties.fire", "&44-8");
/*  749: 742 */       this.PlayerDataConfig.set("75.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  750: 743 */       this.PlayerDataConfig.set("75.1.properties.movementSpeed", "&e1-4");
/*  751: 744 */       this.PlayerDataConfig.set("75.1.properties.durability", "&7300-550");
/*  752: 745 */       this.PlayerDataConfig.set("75.1.properties.droppedXp", "2");
/*  753: 746 */       this.PlayerDataConfig.set("75.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  754: 747 */       this.PlayerDataConfig.set("75.1.properties.soulbound", Boolean.valueOf(true));
/*  755: 748 */       this.PlayerDataConfig.set("75.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  756: 749 */       this.PlayerDataConfig.set("75.1.properties.xpLevel", "&b3-player+2");
/*  757:     */       
/*  758: 751 */       this.PlayerDataConfig.set("45", null);
/*  759: 752 */       this.PlayerDataConfig.set("45.1.itemId", Integer.valueOf(316));
/*  760: 753 */       this.PlayerDataConfig.set("45.1.nameColour", "&6&l");
/*  761: 754 */       this.PlayerDataConfig.set("45.1.prefix", "random");
/*  762: 755 */       this.PlayerDataConfig.set("45.1.suffix", "random");
/*  763: 756 */       this.PlayerDataConfig.set("45.1.properties.armour", "&b4-8");
/*  764: 757 */       this.PlayerDataConfig.set("45.1.properties.health", "&b30-player+60");
/*  765: 758 */       this.PlayerDataConfig.set("45.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  766: 759 */       this.PlayerDataConfig.set("45.1.properties.healthRegen", "&b1-3");
/*  767: 760 */       this.PlayerDataConfig.set("45.1.properties.fireRandomApply", Boolean.valueOf(true));
/*  768: 761 */       this.PlayerDataConfig.set("45.1.properties.fire", "&42-4");
/*  769: 762 */       this.PlayerDataConfig.set("45.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  770: 763 */       this.PlayerDataConfig.set("45.1.properties.movementSpeed", "&e2-3");
/*  771: 764 */       this.PlayerDataConfig.set("45.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  772: 765 */       this.PlayerDataConfig.set("45.1.properties.xpLevel", "&b3-player+2");
/*  773: 766 */       this.PlayerDataConfig.set("45.1.properties.durability", "&7800-1150");
/*  774:     */       
/*  775: 768 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  776:     */     }
/*  777:     */     catch (Exception e)
/*  778:     */     {
/*  779: 771 */       e.printStackTrace();
/*  780: 772 */       System.out.println("*********** Failed to save default magma_cube file! ***********");
/*  781:     */     }
/*  782:     */   }
/*  783:     */   
/*  784:     */   public void writeSilverfish()
/*  785:     */   {
/*  786:     */     try
/*  787:     */     {
/*  788: 780 */       this.PlayerDataConfig = new YamlConfiguration();
/*  789: 781 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "silverfish.yml");
/*  790:     */       
/*  791: 783 */       this.PlayerDataConfig.set("45", null);
/*  792: 784 */       this.PlayerDataConfig.set("45.1.itemId", Integer.valueOf(299));
/*  793: 785 */       this.PlayerDataConfig.set("45.1.nameColour", "&c&l");
/*  794: 786 */       this.PlayerDataConfig.set("45.1.prefix", "random");
/*  795: 787 */       this.PlayerDataConfig.set("45.1.suffix", "random");
/*  796: 788 */       this.PlayerDataConfig.set("45.1.properties.armour", "&b1-3");
/*  797: 789 */       this.PlayerDataConfig.set("45.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  798: 790 */       this.PlayerDataConfig.set("45.1.properties.health", "&b30-player+60");
/*  799: 791 */       this.PlayerDataConfig.set("45.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  800: 792 */       this.PlayerDataConfig.set("45.1.properties.healthRegen", "&b1-3");
/*  801: 793 */       this.PlayerDataConfig.set("45.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  802: 794 */       this.PlayerDataConfig.set("45.1.properties.movementSpeed", "&e2-5");
/*  803: 795 */       this.PlayerDataConfig.set("45.1.properties.durability", "&7500-950");
/*  804: 796 */       this.PlayerDataConfig.set("45.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  805: 797 */       this.PlayerDataConfig.set("45.1.properties.soulbound", Boolean.valueOf(true));
/*  806: 798 */       this.PlayerDataConfig.set("45.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  807: 799 */       this.PlayerDataConfig.set("45.1.properties.xpLevel", "&b3-player+2");
/*  808:     */       
/*  809: 801 */       this.PlayerDataConfig.set("20", null);
/*  810: 802 */       this.PlayerDataConfig.set("20.1.itemId", Integer.valueOf(301));
/*  811: 803 */       this.PlayerDataConfig.set("20.1.nameColour", "&c&l");
/*  812: 804 */       this.PlayerDataConfig.set("20.1.prefix", "random");
/*  813: 805 */       this.PlayerDataConfig.set("20.1.suffix", "random");
/*  814: 806 */       this.PlayerDataConfig.set("20.1.properties.armour", "&b2-4");
/*  815: 807 */       this.PlayerDataConfig.set("20.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  816: 808 */       this.PlayerDataConfig.set("20.1.properties.health", "&b50-player+30");
/*  817: 809 */       this.PlayerDataConfig.set("20.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  818: 810 */       this.PlayerDataConfig.set("20.1.properties.healthRegen", "&b1-1");
/*  819: 811 */       this.PlayerDataConfig.set("20.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  820: 812 */       this.PlayerDataConfig.set("20.1.properties.movementSpeed", "&e4-7");
/*  821: 813 */       this.PlayerDataConfig.set("20.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  822: 814 */       this.PlayerDataConfig.set("20.1.properties.xpLevel", "&b3-player+2");
/*  823: 815 */       this.PlayerDataConfig.set("20.1.properties.durability", "&7600-1100");
/*  824: 816 */       this.PlayerDataConfig.set("20.1.properties.droppedXp", "3");
/*  825:     */       
/*  826: 818 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  827:     */     }
/*  828:     */     catch (Exception e)
/*  829:     */     {
/*  830: 821 */       e.printStackTrace();
/*  831: 822 */       System.out.println("*********** Failed to save default silverfish file! ***********");
/*  832:     */     }
/*  833:     */   }
/*  834:     */   
/*  835:     */   public void writeSkeleton()
/*  836:     */   {
/*  837:     */     try
/*  838:     */     {
/*  839: 830 */       this.PlayerDataConfig = new YamlConfiguration();
/*  840: 831 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "skeleton.yml");
/*  841:     */       
/*  842: 833 */       this.PlayerDataConfig.set("10", null);
/*  843: 834 */       this.PlayerDataConfig.set("10.1.itemId", Integer.valueOf(314));
/*  844: 835 */       this.PlayerDataConfig.set("10.1.nameColour", "&a&l");
/*  845: 836 */       this.PlayerDataConfig.set("10.1.prefix", "random");
/*  846: 837 */       this.PlayerDataConfig.set("10.1.suffix", "random");
/*  847: 838 */       this.PlayerDataConfig.set("10.1.properties.armour", "&b3-7");
/*  848: 839 */       this.PlayerDataConfig.set("10.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  849: 840 */       this.PlayerDataConfig.set("10.1.properties.health", "&b20-player+30");
/*  850: 841 */       this.PlayerDataConfig.set("10.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  851: 842 */       this.PlayerDataConfig.set("10.1.properties.healthRegen", "&b2-5");
/*  852: 843 */       this.PlayerDataConfig.set("10.1.properties.durability", "&72300-2600");
/*  853: 844 */       this.PlayerDataConfig.set("10.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  854: 845 */       this.PlayerDataConfig.set("10.1.properties.soulbound", Boolean.valueOf(true));
/*  855: 846 */       this.PlayerDataConfig.set("10.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  856: 847 */       this.PlayerDataConfig.set("10.1.properties.xpLevel", "&b10-player+3");
/*  857:     */       
/*  858: 849 */       this.PlayerDataConfig.set("10.2.itemId", Integer.valueOf(315));
/*  859: 850 */       this.PlayerDataConfig.set("10.2.nameColour", "&a&l");
/*  860: 851 */       this.PlayerDataConfig.set("10.2.prefix", "random");
/*  861: 852 */       this.PlayerDataConfig.set("10.2.suffix", "random");
/*  862: 853 */       this.PlayerDataConfig.set("10.2.properties.armour", "&b3-7");
/*  863: 854 */       this.PlayerDataConfig.set("10.2.properties.healthRandomApply", Boolean.valueOf(true));
/*  864: 855 */       this.PlayerDataConfig.set("10.2.properties.health", "&b20-player+30");
/*  865: 856 */       this.PlayerDataConfig.set("10.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  866: 857 */       this.PlayerDataConfig.set("10.2.properties.healthRegen", "&b2-5");
/*  867: 858 */       this.PlayerDataConfig.set("10.2.properties.durability", "&72300-2600");
/*  868: 859 */       this.PlayerDataConfig.set("10.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  869: 860 */       this.PlayerDataConfig.set("10.2.properties.soulbound", Boolean.valueOf(true));
/*  870: 861 */       this.PlayerDataConfig.set("10.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  871: 862 */       this.PlayerDataConfig.set("10.2.properties.xpLevel", "&b10-player+3");
/*  872:     */       
/*  873: 864 */       this.PlayerDataConfig.set("10.3.itemId", Integer.valueOf(316));
/*  874: 865 */       this.PlayerDataConfig.set("10.3.nameColour", "&a&l");
/*  875: 866 */       this.PlayerDataConfig.set("10.3.prefix", "random");
/*  876: 867 */       this.PlayerDataConfig.set("10.3.suffix", "random");
/*  877: 868 */       this.PlayerDataConfig.set("10.3.properties.armour", "&b3-7");
/*  878: 869 */       this.PlayerDataConfig.set("10.3.properties.healthRandomApply", Boolean.valueOf(true));
/*  879: 870 */       this.PlayerDataConfig.set("10.3.properties.health", "&b20-player+30");
/*  880: 871 */       this.PlayerDataConfig.set("10.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  881: 872 */       this.PlayerDataConfig.set("10.3.properties.healthRegen", "&b2-5");
/*  882: 873 */       this.PlayerDataConfig.set("10.3.properties.durability", "&72300-2600");
/*  883: 874 */       this.PlayerDataConfig.set("10.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  884: 875 */       this.PlayerDataConfig.set("10.3.properties.soulbound", Boolean.valueOf(true));
/*  885: 876 */       this.PlayerDataConfig.set("10.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  886: 877 */       this.PlayerDataConfig.set("10.3.properties.xpLevel", "&b10-player+3");
/*  887:     */       
/*  888: 879 */       this.PlayerDataConfig.set("10.4.itemId", Integer.valueOf(317));
/*  889: 880 */       this.PlayerDataConfig.set("10.4.nameColour", "&a&l");
/*  890: 881 */       this.PlayerDataConfig.set("10.4.prefix", "random");
/*  891: 882 */       this.PlayerDataConfig.set("10.4.suffix", "random");
/*  892: 883 */       this.PlayerDataConfig.set("10.4.properties.armour", "&b3-7");
/*  893: 884 */       this.PlayerDataConfig.set("10.4.properties.healthRandomApply", Boolean.valueOf(true));
/*  894: 885 */       this.PlayerDataConfig.set("10.4.properties.health", "&b20-player+30");
/*  895: 886 */       this.PlayerDataConfig.set("10.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  896: 887 */       this.PlayerDataConfig.set("10.4.properties.healthRegen", "&b2-5");
/*  897: 888 */       this.PlayerDataConfig.set("10.4.properties.durability", "&72300-2600");
/*  898: 889 */       this.PlayerDataConfig.set("10.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  899: 890 */       this.PlayerDataConfig.set("10.4.properties.soulbound", Boolean.valueOf(true));
/*  900: 891 */       this.PlayerDataConfig.set("10.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  901: 892 */       this.PlayerDataConfig.set("10.4.properties.xpLevel", "&b10-player+3");
/*  902:     */       
/*  903: 894 */       this.PlayerDataConfig.set("10.5.itemId", Integer.valueOf(283));
/*  904: 895 */       this.PlayerDataConfig.set("10.5.nameColour", "&a&l");
/*  905: 896 */       this.PlayerDataConfig.set("10.5.prefix", "random");
/*  906: 897 */       this.PlayerDataConfig.set("10.5.suffix", "random");
/*  907: 898 */       this.PlayerDataConfig.set("10.5.properties.weaponspeed", "&4Slow");
/*  908: 899 */       this.PlayerDataConfig.set("10.5.properties.damage", "&b1-6-player+1-7");
/*  909: 900 */       this.PlayerDataConfig.set("10.5.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  910: 901 */       this.PlayerDataConfig.set("10.5.properties.lifeSteal", "&22-6");
/*  911: 902 */       this.PlayerDataConfig.set("10.5.properties.iceRandomApply", Boolean.valueOf(true));
/*  912: 903 */       this.PlayerDataConfig.set("10.5.properties.ice", "&b1-5");
/*  913: 904 */       this.PlayerDataConfig.set("10.5.properties.durability", "&7400-525");
/*  914: 905 */       this.PlayerDataConfig.set("10.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  915: 906 */       this.PlayerDataConfig.set("10.5.properties.soulbound", Boolean.valueOf(true));
/*  916: 907 */       this.PlayerDataConfig.set("10.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  917: 908 */       this.PlayerDataConfig.set("10.5.properties.xpLevel", "&b6-player+7");
/*  918:     */       
/*  919: 910 */       this.PlayerDataConfig.set("10.6.itemId", Integer.valueOf(286));
/*  920: 911 */       this.PlayerDataConfig.set("10.6.nameColour", "&a&l");
/*  921: 912 */       this.PlayerDataConfig.set("10.6.prefix", "random");
/*  922: 913 */       this.PlayerDataConfig.set("10.6.suffix", "random");
/*  923: 914 */       this.PlayerDataConfig.set("10.6.properties.weaponspeed", "&4Slow");
/*  924: 915 */       this.PlayerDataConfig.set("10.6.properties.damage", "&b1-6-player+1-7");
/*  925: 916 */       this.PlayerDataConfig.set("10.6.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  926: 917 */       this.PlayerDataConfig.set("10.6.properties.lifeSteal", "&22-6");
/*  927: 918 */       this.PlayerDataConfig.set("10.6.properties.iceRandomApply", Boolean.valueOf(true));
/*  928: 919 */       this.PlayerDataConfig.set("10.6.properties.ice", "&b1-5");
/*  929: 920 */       this.PlayerDataConfig.set("10.6.properties.durability", "&7400-525");
/*  930: 921 */       this.PlayerDataConfig.set("10.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  931: 922 */       this.PlayerDataConfig.set("10.6.properties.soulbound", Boolean.valueOf(true));
/*  932: 923 */       this.PlayerDataConfig.set("10.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  933: 924 */       this.PlayerDataConfig.set("10.6.properties.xpLevel", "&b6-player+7");
/*  934:     */       
/*  935: 926 */       this.PlayerDataConfig.set("10.7.itemId", Integer.valueOf(285));
/*  936: 927 */       this.PlayerDataConfig.set("10.7.nameColour", "&a&l");
/*  937: 928 */       this.PlayerDataConfig.set("10.7.prefix", "random");
/*  938: 929 */       this.PlayerDataConfig.set("10.7.suffix", "random");
/*  939: 930 */       this.PlayerDataConfig.set("10.7.properties.weaponspeed", "&4Slow");
/*  940: 931 */       this.PlayerDataConfig.set("10.7.properties.damage", "&b1-6-player+1-7");
/*  941: 932 */       this.PlayerDataConfig.set("10.7.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  942: 933 */       this.PlayerDataConfig.set("10.7.properties.lifeSteal", "&22-6");
/*  943: 934 */       this.PlayerDataConfig.set("10.7.properties.iceRandomApply", Boolean.valueOf(true));
/*  944: 935 */       this.PlayerDataConfig.set("10.7.properties.ice", "&b1-5");
/*  945: 936 */       this.PlayerDataConfig.set("10.7.properties.durability", "&7400-525");
/*  946: 937 */       this.PlayerDataConfig.set("10.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  947: 938 */       this.PlayerDataConfig.set("10.7.properties.soulbound", Boolean.valueOf(true));
/*  948: 939 */       this.PlayerDataConfig.set("10.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  949: 940 */       this.PlayerDataConfig.set("10.7.properties.xpLevel", "&b6-player+7");
/*  950:     */       
/*  951: 942 */       this.PlayerDataConfig.set("10.8.itemId", Integer.valueOf(261));
/*  952: 943 */       this.PlayerDataConfig.set("10.8.nameColour", "&a&l");
/*  953: 944 */       this.PlayerDataConfig.set("10.8.prefix", "random");
/*  954: 945 */       this.PlayerDataConfig.set("10.8.suffix", "random");
/*  955: 946 */       this.PlayerDataConfig.set("10.8.properties.weaponspeed", "&4Slow");
/*  956: 947 */       this.PlayerDataConfig.set("10.8.properties.damage", "&b1-6-player+1-7");
/*  957: 948 */       this.PlayerDataConfig.set("10.8.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  958: 949 */       this.PlayerDataConfig.set("10.8.properties.lifeSteal", "&22-6");
/*  959: 950 */       this.PlayerDataConfig.set("10.8.properties.iceRandomApply", Boolean.valueOf(true));
/*  960: 951 */       this.PlayerDataConfig.set("10.8.properties.ice", "&b1-5");
/*  961: 952 */       this.PlayerDataConfig.set("10.8.properties.durability", "&7400-525");
/*  962: 953 */       this.PlayerDataConfig.set("10.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  963: 954 */       this.PlayerDataConfig.set("10.8.properties.soulbound", Boolean.valueOf(true));
/*  964: 955 */       this.PlayerDataConfig.set("10.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  965: 956 */       this.PlayerDataConfig.set("10.8.properties.xpLevel", "&b6-player+7");
/*  966:     */       
/*  967: 958 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  968:     */     }
/*  969:     */     catch (Exception e)
/*  970:     */     {
/*  971: 961 */       e.printStackTrace();
/*  972: 962 */       System.out.println("*********** Failed to save default skeleton file! ***********");
/*  973:     */     }
/*  974:     */   }
/*  975:     */   
/*  976:     */   public void writeSlime()
/*  977:     */   {
/*  978:     */     try
/*  979:     */     {
/*  980: 970 */       this.PlayerDataConfig = new YamlConfiguration();
/*  981: 971 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "slime.yml");
/*  982:     */       
/*  983: 973 */       this.PlayerDataConfig.set("30", null);
/*  984: 974 */       this.PlayerDataConfig.set("30.1.itemId", Integer.valueOf(275));
/*  985: 975 */       this.PlayerDataConfig.set("30.1.nameColour", "&a&l");
/*  986: 976 */       this.PlayerDataConfig.set("30.1.prefix", "random");
/*  987: 977 */       this.PlayerDataConfig.set("30.1.suffix", "random");
/*  988: 978 */       this.PlayerDataConfig.set("30.1.properties.damage", "&b1-6-player+1-7");
/*  989: 979 */       this.PlayerDataConfig.set("30.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  990: 980 */       this.PlayerDataConfig.set("30.1.properties.critChance", "&b1-2");
/*  991: 981 */       this.PlayerDataConfig.set("30.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  992: 982 */       this.PlayerDataConfig.set("30.1.properties.critDamage", "&b3-8");
/*  993: 983 */       this.PlayerDataConfig.set("30.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  994: 984 */       this.PlayerDataConfig.set("30.1.properties.lifeSteal", "&21-2");
/*  995: 985 */       this.PlayerDataConfig.set("30.1.properties.poisonRandomApply", Boolean.valueOf(true));
/*  996: 986 */       this.PlayerDataConfig.set("30.1.properties.poison", "&a3-4");
/*  997: 987 */       this.PlayerDataConfig.set("30.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  998: 988 */       this.PlayerDataConfig.set("30.1.properties.movementSpeed", "&e1-3");
/*  999: 989 */       this.PlayerDataConfig.set("30.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1000: 990 */       this.PlayerDataConfig.set("30.1.properties.xpLevel", "&b3-player+2");
/* 1001: 991 */       this.PlayerDataConfig.set("30.1.properties.durability", "&7250-550");
/* 1002:     */       
/* 1003: 993 */       this.PlayerDataConfig.set("15", null);
/* 1004: 994 */       this.PlayerDataConfig.set("15.1.itemId", Integer.valueOf(272));
/* 1005: 995 */       this.PlayerDataConfig.set("15.1.nameColour", "&a&l");
/* 1006: 996 */       this.PlayerDataConfig.set("15.1.prefix", "random");
/* 1007: 997 */       this.PlayerDataConfig.set("15.1.suffix", "random");
/* 1008: 998 */       this.PlayerDataConfig.set("15.1.properties.damage", "&b1-6-player+1-7");
/* 1009: 999 */       this.PlayerDataConfig.set("15.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/* 1010:1000 */       this.PlayerDataConfig.set("15.1.properties.critChance", "&b1-3");
/* 1011:1001 */       this.PlayerDataConfig.set("15.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/* 1012:1002 */       this.PlayerDataConfig.set("15.1.properties.critDamage", "&b3-6");
/* 1013:1003 */       this.PlayerDataConfig.set("15.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1014:1004 */       this.PlayerDataConfig.set("15.1.properties.lifeSteal", "&21-3");
/* 1015:1005 */       this.PlayerDataConfig.set("15.1.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1016:1006 */       this.PlayerDataConfig.set("15.1.properties.poison", "&a1-6");
/* 1017:1007 */       this.PlayerDataConfig.set("15.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/* 1018:1008 */       this.PlayerDataConfig.set("15.1.properties.movementSpeed", "&e2-4");
/* 1019:1009 */       this.PlayerDataConfig.set("15.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1020:1010 */       this.PlayerDataConfig.set("15.1.properties.xpLevel", "&b3-player+2");
/* 1021:1011 */       this.PlayerDataConfig.set("15.1.properties.durability", "&7400-600");
/* 1022:     */       
/* 1023:1013 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1024:     */     }
/* 1025:     */     catch (Exception e)
/* 1026:     */     {
/* 1027:1016 */       e.printStackTrace();
/* 1028:1017 */       System.out.println("*********** Failed to save default slime file! ***********");
/* 1029:     */     }
/* 1030:     */   }
/* 1031:     */   
/* 1032:     */   public void writeSpider()
/* 1033:     */   {
/* 1034:     */     try
/* 1035:     */     {
/* 1036:1025 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1037:1026 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "spider.yml");
/* 1038:     */       
/* 1039:1028 */       this.PlayerDataConfig.set("15", null);
/* 1040:1029 */       this.PlayerDataConfig.set("15.1.itemId", Integer.valueOf(298));
/* 1041:1030 */       this.PlayerDataConfig.set("15.1.nameColour", "&f&l");
/* 1042:1031 */       this.PlayerDataConfig.set("15.1.prefix", "random");
/* 1043:1032 */       this.PlayerDataConfig.set("15.1.suffix", "random");
/* 1044:1033 */       this.PlayerDataConfig.set("15.1.properties.armour", "&b2-5");
/* 1045:1034 */       this.PlayerDataConfig.set("15.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1046:1035 */       this.PlayerDataConfig.set("15.1.properties.health", "&b10-player+30");
/* 1047:1036 */       this.PlayerDataConfig.set("15.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1048:1037 */       this.PlayerDataConfig.set("15.1.properties.healthRegen", "&b1-4");
/* 1049:1038 */       this.PlayerDataConfig.set("15.1.properties.durability", "&71350-1500");
/* 1050:1039 */       this.PlayerDataConfig.set("15.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1051:1040 */       this.PlayerDataConfig.set("15.1.properties.soulbound", Boolean.valueOf(true));
/* 1052:1041 */       this.PlayerDataConfig.set("15.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1053:1042 */       this.PlayerDataConfig.set("15.1.properties.xpLevel", "&b10-player+3");
/* 1054:     */       
/* 1055:1044 */       this.PlayerDataConfig.set("15.2.itemId", Integer.valueOf(299));
/* 1056:1045 */       this.PlayerDataConfig.set("15.2.nameColour", "&f&l");
/* 1057:1046 */       this.PlayerDataConfig.set("15.2.prefix", "random");
/* 1058:1047 */       this.PlayerDataConfig.set("15.2.suffix", "random");
/* 1059:1048 */       this.PlayerDataConfig.set("15.2.properties.armour", "&b2-5");
/* 1060:1049 */       this.PlayerDataConfig.set("15.2.properties.healthRandomApply", Boolean.valueOf(true));
/* 1061:1050 */       this.PlayerDataConfig.set("15.2.properties.health", "&b10-player+30");
/* 1062:1051 */       this.PlayerDataConfig.set("15.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1063:1052 */       this.PlayerDataConfig.set("15.2.properties.healthRegen", "&b1-4");
/* 1064:1053 */       this.PlayerDataConfig.set("15.2.properties.durability", "&71350-1500");
/* 1065:1054 */       this.PlayerDataConfig.set("15.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1066:1055 */       this.PlayerDataConfig.set("15.2.properties.soulbound", Boolean.valueOf(true));
/* 1067:1056 */       this.PlayerDataConfig.set("15.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1068:1057 */       this.PlayerDataConfig.set("15.2.properties.xpLevel", "&b10-player+3");
/* 1069:     */       
/* 1070:1059 */       this.PlayerDataConfig.set("15.3.itemId", Integer.valueOf(300));
/* 1071:1060 */       this.PlayerDataConfig.set("15.3.nameColour", "&f&l");
/* 1072:1061 */       this.PlayerDataConfig.set("15.3.prefix", "random");
/* 1073:1062 */       this.PlayerDataConfig.set("15.3.suffix", "random");
/* 1074:1063 */       this.PlayerDataConfig.set("15.3.properties.armour", "&b2-5");
/* 1075:1064 */       this.PlayerDataConfig.set("15.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1076:1065 */       this.PlayerDataConfig.set("15.3.properties.health", "&b10-player+30");
/* 1077:1066 */       this.PlayerDataConfig.set("15.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1078:1067 */       this.PlayerDataConfig.set("15.3.properties.healthRegen", "&b1-4");
/* 1079:1068 */       this.PlayerDataConfig.set("15.3.properties.durability", "&71350-1500");
/* 1080:1069 */       this.PlayerDataConfig.set("15.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1081:1070 */       this.PlayerDataConfig.set("15.3.properties.soulbound", Boolean.valueOf(true));
/* 1082:1071 */       this.PlayerDataConfig.set("15.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1083:1072 */       this.PlayerDataConfig.set("15.3.properties.xpLevel", "&b10-player+3");
/* 1084:     */       
/* 1085:1074 */       this.PlayerDataConfig.set("15.3.itemId", Integer.valueOf(301));
/* 1086:1075 */       this.PlayerDataConfig.set("15.3.nameColour", "&f&l");
/* 1087:1076 */       this.PlayerDataConfig.set("15.3.prefix", "random");
/* 1088:1077 */       this.PlayerDataConfig.set("15.3.suffix", "random");
/* 1089:1078 */       this.PlayerDataConfig.set("15.3.properties.armour", "&b2-5");
/* 1090:1079 */       this.PlayerDataConfig.set("15.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1091:1080 */       this.PlayerDataConfig.set("15.3.properties.health", "&b10-player+30");
/* 1092:1081 */       this.PlayerDataConfig.set("15.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1093:1082 */       this.PlayerDataConfig.set("15.3.properties.healthRegen", "&b1-4");
/* 1094:1083 */       this.PlayerDataConfig.set("15.3.properties.durability", "&71350-1500");
/* 1095:1084 */       this.PlayerDataConfig.set("15.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1096:1085 */       this.PlayerDataConfig.set("15.3.properties.soulbound", Boolean.valueOf(true));
/* 1097:1086 */       this.PlayerDataConfig.set("15.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1098:1087 */       this.PlayerDataConfig.set("15.3.properties.xpLevel", "&b10-player+3");
/* 1099:     */       
/* 1100:1089 */       this.PlayerDataConfig.set("15.4.itemId", Integer.valueOf(272));
/* 1101:1090 */       this.PlayerDataConfig.set("15.4.nameColour", "&f&l");
/* 1102:1091 */       this.PlayerDataConfig.set("15.4.prefix", "random");
/* 1103:1092 */       this.PlayerDataConfig.set("15.4.suffix", "random");
/* 1104:1093 */       this.PlayerDataConfig.set("15.4.properties.weaponspeed", "&4Slow");
/* 1105:1094 */       this.PlayerDataConfig.set("15.4.properties.damage", "&b1-6-player+1-7");
/* 1106:1095 */       this.PlayerDataConfig.set("15.4.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1107:1096 */       this.PlayerDataConfig.set("15.4.properties.lifeSteal", "&21-5");
/* 1108:1097 */       this.PlayerDataConfig.set("15.4.properties.iceRandomApply", Boolean.valueOf(true));
/* 1109:1098 */       this.PlayerDataConfig.set("15.4.properties.ice", "&b2-6");
/* 1110:1099 */       this.PlayerDataConfig.set("15.4.properties.durability", "&7300-425");
/* 1111:1100 */       this.PlayerDataConfig.set("15.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1112:1101 */       this.PlayerDataConfig.set("15.4.properties.soulbound", Boolean.valueOf(true));
/* 1113:1102 */       this.PlayerDataConfig.set("15.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1114:1103 */       this.PlayerDataConfig.set("15.4.properties.xpLevel", "&b8-player+5");
/* 1115:     */       
/* 1116:1105 */       this.PlayerDataConfig.set("15.5.itemId", Integer.valueOf(274));
/* 1117:1106 */       this.PlayerDataConfig.set("15.5.nameColour", "&f&l");
/* 1118:1107 */       this.PlayerDataConfig.set("15.5.prefix", "random");
/* 1119:1108 */       this.PlayerDataConfig.set("15.5.suffix", "random");
/* 1120:1109 */       this.PlayerDataConfig.set("15.5.properties.weaponspeed", "&4Slow");
/* 1121:1110 */       this.PlayerDataConfig.set("15.5.properties.damage", "&b1-6-player+1-7");
/* 1122:1111 */       this.PlayerDataConfig.set("15.5.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1123:1112 */       this.PlayerDataConfig.set("15.5.properties.lifeSteal", "&21-5");
/* 1124:1113 */       this.PlayerDataConfig.set("15.5.properties.iceRandomApply", Boolean.valueOf(true));
/* 1125:1114 */       this.PlayerDataConfig.set("15.5.properties.ice", "&b2-6");
/* 1126:1115 */       this.PlayerDataConfig.set("15.5.properties.durability", "&7300-425");
/* 1127:1116 */       this.PlayerDataConfig.set("15.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1128:1117 */       this.PlayerDataConfig.set("15.5.properties.soulbound", Boolean.valueOf(true));
/* 1129:1118 */       this.PlayerDataConfig.set("15.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1130:1119 */       this.PlayerDataConfig.set("15.5.properties.xpLevel", "&b8-player+5");
/* 1131:     */       
/* 1132:1121 */       this.PlayerDataConfig.set("15.6.itemId", Integer.valueOf(275));
/* 1133:1122 */       this.PlayerDataConfig.set("15.6.nameColour", "&f&l");
/* 1134:1123 */       this.PlayerDataConfig.set("15.6.prefix", "random");
/* 1135:1124 */       this.PlayerDataConfig.set("15.6.suffix", "random");
/* 1136:1125 */       this.PlayerDataConfig.set("15.6.properties.weaponspeed", "&4Slow");
/* 1137:1126 */       this.PlayerDataConfig.set("15.6.properties.damage", "&b1-6-player+1-7");
/* 1138:1127 */       this.PlayerDataConfig.set("15.6.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1139:1128 */       this.PlayerDataConfig.set("15.6.properties.lifeSteal", "&21-5");
/* 1140:1129 */       this.PlayerDataConfig.set("15.6.properties.iceRandomApply", Boolean.valueOf(true));
/* 1141:1130 */       this.PlayerDataConfig.set("15.6.properties.ice", "&b2-6");
/* 1142:1131 */       this.PlayerDataConfig.set("15.6.properties.durability", "&7300-425");
/* 1143:1132 */       this.PlayerDataConfig.set("15.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1144:1133 */       this.PlayerDataConfig.set("15.6.properties.soulbound", Boolean.valueOf(true));
/* 1145:1134 */       this.PlayerDataConfig.set("15.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1146:1135 */       this.PlayerDataConfig.set("15.6.properties.xpLevel", "&b8-player+5");
/* 1147:     */       
/* 1148:1137 */       this.PlayerDataConfig.set("15.7.itemId", Integer.valueOf(291));
/* 1149:1138 */       this.PlayerDataConfig.set("15.7.nameColour", "&f&l");
/* 1150:1139 */       this.PlayerDataConfig.set("15.7.prefix", "random");
/* 1151:1140 */       this.PlayerDataConfig.set("15.7.suffix", "random");
/* 1152:1141 */       this.PlayerDataConfig.set("15.7.properties.weaponspeed", "&4Slow");
/* 1153:1142 */       this.PlayerDataConfig.set("15.7.properties.damage", "&b1-6-player+1-7");
/* 1154:1143 */       this.PlayerDataConfig.set("15.7.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1155:1144 */       this.PlayerDataConfig.set("15.7.properties.lifeSteal", "&21-5");
/* 1156:1145 */       this.PlayerDataConfig.set("15.7.properties.iceRandomApply", Boolean.valueOf(true));
/* 1157:1146 */       this.PlayerDataConfig.set("15.7.properties.ice", "&b2-6");
/* 1158:1147 */       this.PlayerDataConfig.set("15.7.properties.durability", "&7300-425");
/* 1159:1148 */       this.PlayerDataConfig.set("15.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1160:1149 */       this.PlayerDataConfig.set("15.7.properties.soulbound", Boolean.valueOf(true));
/* 1161:1150 */       this.PlayerDataConfig.set("15.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1162:1151 */       this.PlayerDataConfig.set("15.7.properties.xpLevel", "&b8-player+5");
/* 1163:     */       
/* 1164:1153 */       this.PlayerDataConfig.set("15.8.itemId", Integer.valueOf(261));
/* 1165:1154 */       this.PlayerDataConfig.set("15.8.nameColour", "&f&l");
/* 1166:1155 */       this.PlayerDataConfig.set("15.8.prefix", "random");
/* 1167:1156 */       this.PlayerDataConfig.set("15.8.suffix", "random");
/* 1168:1157 */       this.PlayerDataConfig.set("15.8.properties.weaponspeed", "&4Slow");
/* 1169:1158 */       this.PlayerDataConfig.set("15.8.properties.damage", "&b1-6-player+1-7");
/* 1170:1159 */       this.PlayerDataConfig.set("15.8.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1171:1160 */       this.PlayerDataConfig.set("15.8.properties.lifeSteal", "&21-5");
/* 1172:1161 */       this.PlayerDataConfig.set("15.8.properties.iceRandomApply", Boolean.valueOf(true));
/* 1173:1162 */       this.PlayerDataConfig.set("15.8.properties.ice", "&b2-6");
/* 1174:1163 */       this.PlayerDataConfig.set("15.8.properties.durability", "&7300-425");
/* 1175:1164 */       this.PlayerDataConfig.set("15.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1176:1165 */       this.PlayerDataConfig.set("15.8.properties.soulbound", Boolean.valueOf(true));
/* 1177:1166 */       this.PlayerDataConfig.set("15.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1178:1167 */       this.PlayerDataConfig.set("15.8.properties.xpLevel", "&b8-player+5");
/* 1179:     */       
/* 1180:1169 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1181:     */     }
/* 1182:     */     catch (Exception e)
/* 1183:     */     {
/* 1184:1172 */       e.printStackTrace();
/* 1185:1173 */       System.out.println("*********** Failed to save default spider file! ***********");
/* 1186:     */     }
/* 1187:     */   }
/* 1188:     */   
/* 1189:     */   public void writeWitch()
/* 1190:     */   {
/* 1191:     */     try
/* 1192:     */     {
/* 1193:1181 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1194:1182 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "witch.yml");
/* 1195:     */       
/* 1196:1184 */       this.PlayerDataConfig.set("10", null);
/* 1197:1185 */       this.PlayerDataConfig.set("10.1.itemId", Integer.valueOf(314));
/* 1198:1186 */       this.PlayerDataConfig.set("10.1.nameColour", "&a&l");
/* 1199:1187 */       this.PlayerDataConfig.set("10.1.prefix", "random");
/* 1200:1188 */       this.PlayerDataConfig.set("10.1.suffix", "random");
/* 1201:1189 */       this.PlayerDataConfig.set("10.1.properties.armour", "&b3-7");
/* 1202:1190 */       this.PlayerDataConfig.set("10.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1203:1191 */       this.PlayerDataConfig.set("10.1.properties.health", "&b20-player+30");
/* 1204:1192 */       this.PlayerDataConfig.set("10.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1205:1193 */       this.PlayerDataConfig.set("10.1.properties.healthRegen", "&b2-5");
/* 1206:1194 */       this.PlayerDataConfig.set("10.1.properties.blockRandomApply", Boolean.valueOf(true));
/* 1207:1195 */       this.PlayerDataConfig.set("10.1.properties.block", "&b1-3");
/* 1208:1196 */       this.PlayerDataConfig.set("10.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1209:1197 */       this.PlayerDataConfig.set("10.1.properties.lifeSteal", "&22-5");
/* 1210:1198 */       this.PlayerDataConfig.set("10.1.properties.durability", "&72300-2600");
/* 1211:1199 */       this.PlayerDataConfig.set("10.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1212:1200 */       this.PlayerDataConfig.set("10.1.properties.soulbound", Boolean.valueOf(true));
/* 1213:1201 */       this.PlayerDataConfig.set("10.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1214:1202 */       this.PlayerDataConfig.set("10.1.properties.xpLevel", "&b10-player+3");
/* 1215:     */       
/* 1216:1204 */       this.PlayerDataConfig.set("10.2.itemId", Integer.valueOf(315));
/* 1217:1205 */       this.PlayerDataConfig.set("10.2.nameColour", "&a&l");
/* 1218:1206 */       this.PlayerDataConfig.set("10.2.prefix", "random");
/* 1219:1207 */       this.PlayerDataConfig.set("10.2.suffix", "random");
/* 1220:1208 */       this.PlayerDataConfig.set("10.2.properties.armour", "&b3-7");
/* 1221:1209 */       this.PlayerDataConfig.set("10.2.properties.healthRandomApply", Boolean.valueOf(true));
/* 1222:1210 */       this.PlayerDataConfig.set("10.2.properties.health", "&b20-player+30");
/* 1223:1211 */       this.PlayerDataConfig.set("10.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1224:1212 */       this.PlayerDataConfig.set("10.2.properties.healthRegen", "&b2-5");
/* 1225:1213 */       this.PlayerDataConfig.set("10.2.properties.blockRandomApply", Boolean.valueOf(true));
/* 1226:1214 */       this.PlayerDataConfig.set("10.2.properties.block", "&b1-3");
/* 1227:1215 */       this.PlayerDataConfig.set("10.2.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1228:1216 */       this.PlayerDataConfig.set("10.2.properties.lifeSteal", "&22-5");
/* 1229:1217 */       this.PlayerDataConfig.set("10.2.properties.durability", "&72300-2600");
/* 1230:1218 */       this.PlayerDataConfig.set("10.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1231:1219 */       this.PlayerDataConfig.set("10.2.properties.soulbound", Boolean.valueOf(true));
/* 1232:1220 */       this.PlayerDataConfig.set("10.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1233:1221 */       this.PlayerDataConfig.set("10.2.properties.xpLevel", "&b10-player+3");
/* 1234:     */       
/* 1235:1223 */       this.PlayerDataConfig.set("10.3.itemId", Integer.valueOf(316));
/* 1236:1224 */       this.PlayerDataConfig.set("10.3.nameColour", "&a&l");
/* 1237:1225 */       this.PlayerDataConfig.set("10.3.prefix", "random");
/* 1238:1226 */       this.PlayerDataConfig.set("10.3.suffix", "random");
/* 1239:1227 */       this.PlayerDataConfig.set("10.3.properties.armour", "&b3-7");
/* 1240:1228 */       this.PlayerDataConfig.set("10.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1241:1229 */       this.PlayerDataConfig.set("10.3.properties.health", "&b20-player+30");
/* 1242:1230 */       this.PlayerDataConfig.set("10.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1243:1231 */       this.PlayerDataConfig.set("10.3.properties.healthRegen", "&b2-5");
/* 1244:1232 */       this.PlayerDataConfig.set("10.3.properties.blockRandomApply", Boolean.valueOf(true));
/* 1245:1233 */       this.PlayerDataConfig.set("10.3.properties.block", "&b1-3");
/* 1246:1234 */       this.PlayerDataConfig.set("10.3.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1247:1235 */       this.PlayerDataConfig.set("10.3.properties.lifeSteal", "&22-5");
/* 1248:1236 */       this.PlayerDataConfig.set("10.3.properties.durability", "&72300-2600");
/* 1249:1237 */       this.PlayerDataConfig.set("10.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1250:1238 */       this.PlayerDataConfig.set("10.3.properties.soulbound", Boolean.valueOf(true));
/* 1251:1239 */       this.PlayerDataConfig.set("10.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1252:1240 */       this.PlayerDataConfig.set("10.3.properties.xpLevel", "&b10-player+3");
/* 1253:     */       
/* 1254:1242 */       this.PlayerDataConfig.set("10.4.itemId", Integer.valueOf(317));
/* 1255:1243 */       this.PlayerDataConfig.set("10.4.nameColour", "&a&l");
/* 1256:1244 */       this.PlayerDataConfig.set("10.4.prefix", "random");
/* 1257:1245 */       this.PlayerDataConfig.set("10.4.suffix", "random");
/* 1258:1246 */       this.PlayerDataConfig.set("10.4.properties.armour", "&b3-7");
/* 1259:1247 */       this.PlayerDataConfig.set("10.4.properties.healthRandomApply", Boolean.valueOf(true));
/* 1260:1248 */       this.PlayerDataConfig.set("10.4.properties.health", "&b20-player+30");
/* 1261:1249 */       this.PlayerDataConfig.set("10.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1262:1250 */       this.PlayerDataConfig.set("10.4.properties.healthRegen", "&b2-5");
/* 1263:1251 */       this.PlayerDataConfig.set("10.4.properties.blockRandomApply", Boolean.valueOf(true));
/* 1264:1252 */       this.PlayerDataConfig.set("10.4.properties.block", "&b1-3");
/* 1265:1253 */       this.PlayerDataConfig.set("10.4.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1266:1254 */       this.PlayerDataConfig.set("10.4.properties.lifeSteal", "&22-5");
/* 1267:1255 */       this.PlayerDataConfig.set("10.4.properties.durability", "&72300-2600");
/* 1268:1256 */       this.PlayerDataConfig.set("10.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1269:1257 */       this.PlayerDataConfig.set("10.4.properties.soulbound", Boolean.valueOf(true));
/* 1270:1258 */       this.PlayerDataConfig.set("10.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1271:1259 */       this.PlayerDataConfig.set("10.4.properties.xpLevel", "&b10-player+3");
/* 1272:     */       
/* 1273:1261 */       this.PlayerDataConfig.set("10.5.itemId", Integer.valueOf(283));
/* 1274:1262 */       this.PlayerDataConfig.set("10.5.nameColour", "&a&l");
/* 1275:1263 */       this.PlayerDataConfig.set("10.5.prefix", "random");
/* 1276:1264 */       this.PlayerDataConfig.set("10.5.suffix", "random");
/* 1277:1265 */       this.PlayerDataConfig.set("10.5.properties.weaponspeed", "&4Slow");
/* 1278:1266 */       this.PlayerDataConfig.set("10.5.properties.damage", "&b1-6-player+1-7");
/* 1279:1267 */       this.PlayerDataConfig.set("10.5.properties.witherRandomApply", Boolean.valueOf(true));
/* 1280:1268 */       this.PlayerDataConfig.set("10.5.properties.wither", "&72-6");
/* 1281:1269 */       this.PlayerDataConfig.set("10.5.properties.iceRandomApply", Boolean.valueOf(true));
/* 1282:1270 */       this.PlayerDataConfig.set("10.5.properties.ice", "&b1-5");
/* 1283:1271 */       this.PlayerDataConfig.set("10.5.properties.durability", "&7400-525");
/* 1284:1272 */       this.PlayerDataConfig.set("10.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1285:1273 */       this.PlayerDataConfig.set("10.5.properties.soulbound", Boolean.valueOf(true));
/* 1286:1274 */       this.PlayerDataConfig.set("10.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1287:1275 */       this.PlayerDataConfig.set("10.5.properties.xpLevel", "&b6-player+7");
/* 1288:     */       
/* 1289:1277 */       this.PlayerDataConfig.set("10.6.itemId", Integer.valueOf(286));
/* 1290:1278 */       this.PlayerDataConfig.set("10.6.nameColour", "&a&l");
/* 1291:1279 */       this.PlayerDataConfig.set("10.6.prefix", "random");
/* 1292:1280 */       this.PlayerDataConfig.set("10.6.suffix", "random");
/* 1293:1281 */       this.PlayerDataConfig.set("10.6.properties.weaponspeed", "&4Slow");
/* 1294:1282 */       this.PlayerDataConfig.set("10.6.properties.damage", "&b1-6-player+1-7");
/* 1295:1283 */       this.PlayerDataConfig.set("10.6.properties.witherRandomApply", Boolean.valueOf(true));
/* 1296:1284 */       this.PlayerDataConfig.set("10.6.properties.wither", "&72-6");
/* 1297:1285 */       this.PlayerDataConfig.set("10.6.properties.iceRandomApply", Boolean.valueOf(true));
/* 1298:1286 */       this.PlayerDataConfig.set("10.6.properties.ice", "&b1-5");
/* 1299:1287 */       this.PlayerDataConfig.set("10.6.properties.durability", "&7400-525");
/* 1300:1288 */       this.PlayerDataConfig.set("10.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1301:1289 */       this.PlayerDataConfig.set("10.6.properties.soulbound", Boolean.valueOf(true));
/* 1302:1290 */       this.PlayerDataConfig.set("10.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1303:1291 */       this.PlayerDataConfig.set("10.6.properties.xpLevel", "&b6-player+7");
/* 1304:     */       
/* 1305:1293 */       this.PlayerDataConfig.set("10.7.itemId", Integer.valueOf(285));
/* 1306:1294 */       this.PlayerDataConfig.set("10.7.nameColour", "&a&l");
/* 1307:1295 */       this.PlayerDataConfig.set("10.7.prefix", "random");
/* 1308:1296 */       this.PlayerDataConfig.set("10.7.suffix", "random");
/* 1309:1297 */       this.PlayerDataConfig.set("10.7.properties.weaponspeed", "&4Slow");
/* 1310:1298 */       this.PlayerDataConfig.set("10.7.properties.damage", "&b1-6-player+1-7");
/* 1311:1299 */       this.PlayerDataConfig.set("10.7.properties.witherRandomApply", Boolean.valueOf(true));
/* 1312:1300 */       this.PlayerDataConfig.set("10.7.properties.wither", "&72-6");
/* 1313:1301 */       this.PlayerDataConfig.set("10.7.properties.iceRandomApply", Boolean.valueOf(true));
/* 1314:1302 */       this.PlayerDataConfig.set("10.7.properties.ice", "&b1-5");
/* 1315:1303 */       this.PlayerDataConfig.set("10.7.properties.durability", "&7400-525");
/* 1316:1304 */       this.PlayerDataConfig.set("10.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1317:1305 */       this.PlayerDataConfig.set("10.7.properties.soulbound", Boolean.valueOf(true));
/* 1318:1306 */       this.PlayerDataConfig.set("10.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1319:1307 */       this.PlayerDataConfig.set("10.7.properties.xpLevel", "&b6-player+7");
/* 1320:     */       
/* 1321:1309 */       this.PlayerDataConfig.set("10.8.itemId", Integer.valueOf(261));
/* 1322:1310 */       this.PlayerDataConfig.set("10.8.nameColour", "&a&l");
/* 1323:1311 */       this.PlayerDataConfig.set("10.8.prefix", "random");
/* 1324:1312 */       this.PlayerDataConfig.set("10.8.suffix", "random");
/* 1325:1313 */       this.PlayerDataConfig.set("10.8.properties.weaponspeed", "&4Slow");
/* 1326:1314 */       this.PlayerDataConfig.set("10.8.properties.damage", "&b1-6-player+1-7");
/* 1327:1315 */       this.PlayerDataConfig.set("10.8.properties.witherRandomApply", Boolean.valueOf(true));
/* 1328:1316 */       this.PlayerDataConfig.set("10.8.properties.wither", "&72-6");
/* 1329:1317 */       this.PlayerDataConfig.set("10.8.properties.iceRandomApply", Boolean.valueOf(true));
/* 1330:1318 */       this.PlayerDataConfig.set("10.8.properties.ice", "&b1-5");
/* 1331:1319 */       this.PlayerDataConfig.set("10.8.properties.durability", "&7400-525");
/* 1332:1320 */       this.PlayerDataConfig.set("10.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1333:1321 */       this.PlayerDataConfig.set("10.8.properties.soulbound", Boolean.valueOf(true));
/* 1334:1322 */       this.PlayerDataConfig.set("10.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1335:1323 */       this.PlayerDataConfig.set("10.8.properties.xpLevel", "&b6-player+7");
/* 1336:     */       
/* 1337:1325 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1338:     */     }
/* 1339:     */     catch (Exception e)
/* 1340:     */     {
/* 1341:1328 */       e.printStackTrace();
/* 1342:1329 */       System.out.println("*********** Failed to save default witch file! ***********");
/* 1343:     */     }
/* 1344:     */   }
/* 1345:     */   
/* 1346:     */   public void writeWither()
/* 1347:     */   {
/* 1348:     */     try
/* 1349:     */     {
/* 1350:1337 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1351:1338 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "wither.yml");
/* 1352:     */       
/* 1353:1340 */       this.PlayerDataConfig.set("100", null);
/* 1354:1341 */       this.PlayerDataConfig.set("100.1.itemId", Integer.valueOf(279));
/* 1355:1342 */       this.PlayerDataConfig.set("100.1.nameColour", "&9&l");
/* 1356:1343 */       this.PlayerDataConfig.set("100.1.prefix", "random");
/* 1357:1344 */       this.PlayerDataConfig.set("100.1.suffix", "random");
/* 1358:1345 */       this.PlayerDataConfig.set("100.1.properties.damage", "&b1-6-player+1-7");
/* 1359:1346 */       this.PlayerDataConfig.set("100.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/* 1360:1347 */       this.PlayerDataConfig.set("100.1.properties.critChance", "&b8-14");
/* 1361:1348 */       this.PlayerDataConfig.set("100.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/* 1362:1349 */       this.PlayerDataConfig.set("100.1.properties.critDamage", "&b14-23");
/* 1363:1350 */       this.PlayerDataConfig.set("100.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/* 1364:1351 */       this.PlayerDataConfig.set("100.1.properties.critDamage", "&b3-9");
/* 1365:1352 */       this.PlayerDataConfig.set("100.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1366:1353 */       this.PlayerDataConfig.set("100.1.properties.health", "&b35-player+85");
/* 1367:1354 */       this.PlayerDataConfig.set("100.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1368:1355 */       this.PlayerDataConfig.set("100.1.properties.healthRegen", "&b3-5");
/* 1369:1356 */       this.PlayerDataConfig.set("100.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1370:1357 */       this.PlayerDataConfig.set("100.1.properties.lifeSteal", "&22-5");
/* 1371:1358 */       this.PlayerDataConfig.set("100.1.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1372:1359 */       this.PlayerDataConfig.set("100.1.properties.poison", "&a3-5");
/* 1373:1360 */       this.PlayerDataConfig.set("100.1.properties.witherRandomApply", Boolean.valueOf(true));
/* 1374:1361 */       this.PlayerDataConfig.set("100.1.properties.wither", "&72-6");
/* 1375:1362 */       this.PlayerDataConfig.set("100.1.properties.witherRandomApply", Boolean.valueOf(true));
/* 1376:1363 */       this.PlayerDataConfig.set("100.1.properties.wither", "&72-6");
/* 1377:1364 */       this.PlayerDataConfig.set("100.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/* 1378:1365 */       this.PlayerDataConfig.set("100.1.properties.movementSpeed", "&e2-4");
/* 1379:1366 */       this.PlayerDataConfig.set("100.1.properties.durability", "&71500-1950");
/* 1380:1367 */       this.PlayerDataConfig.set("100.1.properties.droppedXp", "75");
/* 1381:1368 */       this.PlayerDataConfig.set("100.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1382:1369 */       this.PlayerDataConfig.set("100.1.properties.soulbound", Boolean.valueOf(true));
/* 1383:1370 */       this.PlayerDataConfig.set("100.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1384:1371 */       this.PlayerDataConfig.set("100.1.properties.xpLevel", "&b3-player+2");
/* 1385:1372 */       this.PlayerDataConfig.set("100.1.properties.tntRandomApply", Boolean.valueOf(true));
/* 1386:1373 */       this.PlayerDataConfig.set("100.1.properties.spells.tnt", Boolean.valueOf(true));
/* 1387:     */       
/* 1388:1375 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1389:     */     }
/* 1390:     */     catch (Exception e)
/* 1391:     */     {
/* 1392:1378 */       e.printStackTrace();
/* 1393:1379 */       System.out.println("*********** Failed to save default wither file! ***********");
/* 1394:     */     }
/* 1395:     */   }
/* 1396:     */   
/* 1397:     */   public void writeZombie()
/* 1398:     */   {
/* 1399:     */     try
/* 1400:     */     {
/* 1401:1387 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1402:1388 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "zombie.yml");
/* 1403:     */       
/* 1404:1390 */       this.PlayerDataConfig.set("15", null);
/* 1405:1391 */       this.PlayerDataConfig.set("15.1.itemId", Integer.valueOf(298));
/* 1406:1392 */       this.PlayerDataConfig.set("15.1.nameColour", "&f&l");
/* 1407:1393 */       this.PlayerDataConfig.set("15.1.prefix", "random");
/* 1408:1394 */       this.PlayerDataConfig.set("15.1.suffix", "random");
/* 1409:1395 */       this.PlayerDataConfig.set("15.1.properties.armour", "&b2-5");
/* 1410:1396 */       this.PlayerDataConfig.set("15.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1411:1397 */       this.PlayerDataConfig.set("15.1.properties.health", "&b10-player+30");
/* 1412:1398 */       this.PlayerDataConfig.set("15.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1413:1399 */       this.PlayerDataConfig.set("15.1.properties.healthRegen", "&b1-4");
/* 1414:1400 */       this.PlayerDataConfig.set("15.1.properties.durability", "&71350-1500");
/* 1415:1401 */       this.PlayerDataConfig.set("15.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1416:1402 */       this.PlayerDataConfig.set("15.1.properties.soulbound", Boolean.valueOf(true));
/* 1417:1403 */       this.PlayerDataConfig.set("15.1.properties.classRandomApply", Boolean.valueOf(true));
/* 1418:1404 */       this.PlayerDataConfig.set("15.1.properties.class", "random");
/* 1419:1405 */       this.PlayerDataConfig.set("15.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1420:1406 */       this.PlayerDataConfig.set("15.1.properties.xpLevel", "&b10-player+3");
/* 1421:     */       
/* 1422:1408 */       this.PlayerDataConfig.set("15.2.itemId", Integer.valueOf(299));
/* 1423:1409 */       this.PlayerDataConfig.set("15.2.nameColour", "&f&l");
/* 1424:1410 */       this.PlayerDataConfig.set("15.2.prefix", "random");
/* 1425:1411 */       this.PlayerDataConfig.set("15.2.suffix", "random");
/* 1426:1412 */       this.PlayerDataConfig.set("15.2.properties.armour", "&b2-5");
/* 1427:1413 */       this.PlayerDataConfig.set("15.2.properties.healthRandomApply", Boolean.valueOf(true));
/* 1428:1414 */       this.PlayerDataConfig.set("15.2.properties.health", "&b10-player+30");
/* 1429:1415 */       this.PlayerDataConfig.set("15.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1430:1416 */       this.PlayerDataConfig.set("15.2.properties.healthRegen", "&b1-4");
/* 1431:1417 */       this.PlayerDataConfig.set("15.2.properties.durability", "&71350-1500");
/* 1432:1418 */       this.PlayerDataConfig.set("15.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1433:1419 */       this.PlayerDataConfig.set("15.2.properties.soulbound", Boolean.valueOf(true));
/* 1434:1420 */       this.PlayerDataConfig.set("15.2.properties.classRandomApply", Boolean.valueOf(true));
/* 1435:1421 */       this.PlayerDataConfig.set("15.2.properties.class", "random");
/* 1436:1422 */       this.PlayerDataConfig.set("15.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1437:1423 */       this.PlayerDataConfig.set("15.2.properties.xpLevel", "&b10-player+3");
/* 1438:     */       
/* 1439:1425 */       this.PlayerDataConfig.set("15.3.itemId", Integer.valueOf(300));
/* 1440:1426 */       this.PlayerDataConfig.set("15.3.nameColour", "&f&l");
/* 1441:1427 */       this.PlayerDataConfig.set("15.3.prefix", "random");
/* 1442:1428 */       this.PlayerDataConfig.set("15.3.suffix", "random");
/* 1443:1429 */       this.PlayerDataConfig.set("15.3.properties.armour", "&b2-5");
/* 1444:1430 */       this.PlayerDataConfig.set("15.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1445:1431 */       this.PlayerDataConfig.set("15.3.properties.health", "&b10-player+30");
/* 1446:1432 */       this.PlayerDataConfig.set("15.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1447:1433 */       this.PlayerDataConfig.set("15.3.properties.healthRegen", "&b1-4");
/* 1448:1434 */       this.PlayerDataConfig.set("15.3.properties.durability", "&71350-1500");
/* 1449:1435 */       this.PlayerDataConfig.set("15.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1450:1436 */       this.PlayerDataConfig.set("15.3.properties.soulbound", Boolean.valueOf(true));
/* 1451:1437 */       this.PlayerDataConfig.set("15.3.properties.classRandomApply", Boolean.valueOf(true));
/* 1452:1438 */       this.PlayerDataConfig.set("15.3.properties.class", "random");
/* 1453:1439 */       this.PlayerDataConfig.set("15.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1454:1440 */       this.PlayerDataConfig.set("15.3.properties.xpLevel", "&b10-player+3");
/* 1455:     */       
/* 1456:1442 */       this.PlayerDataConfig.set("15.4.itemId", Integer.valueOf(301));
/* 1457:1443 */       this.PlayerDataConfig.set("15.4.nameColour", "&f&l");
/* 1458:1444 */       this.PlayerDataConfig.set("15.4.prefix", "random");
/* 1459:1445 */       this.PlayerDataConfig.set("15.4.suffix", "random");
/* 1460:1446 */       this.PlayerDataConfig.set("15.4.properties.armour", "&b2-5");
/* 1461:1447 */       this.PlayerDataConfig.set("15.4.properties.healthRandomApply", Boolean.valueOf(true));
/* 1462:1448 */       this.PlayerDataConfig.set("15.4.properties.health", "&b10-player+30");
/* 1463:1449 */       this.PlayerDataConfig.set("15.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1464:1450 */       this.PlayerDataConfig.set("15.4.properties.healthRegen", "&b1-4");
/* 1465:1451 */       this.PlayerDataConfig.set("15.4.properties.durability", "&71350-1500");
/* 1466:1452 */       this.PlayerDataConfig.set("15.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1467:1453 */       this.PlayerDataConfig.set("15.4.properties.soulbound", Boolean.valueOf(true));
/* 1468:1454 */       this.PlayerDataConfig.set("15.4.properties.classRandomApply", Boolean.valueOf(true));
/* 1469:1455 */       this.PlayerDataConfig.set("15.4.properties.class", "random");
/* 1470:1456 */       this.PlayerDataConfig.set("15.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1471:1457 */       this.PlayerDataConfig.set("15.4.properties.xpLevel", "&b10-player+3");
/* 1472:     */       
/* 1473:1459 */       this.PlayerDataConfig.set("15.5.itemId", Integer.valueOf(268));
/* 1474:1460 */       this.PlayerDataConfig.set("15.5.nameColour", "&f&l");
/* 1475:1461 */       this.PlayerDataConfig.set("15.5.prefix", "random");
/* 1476:1462 */       this.PlayerDataConfig.set("15.5.suffix", "random");
/* 1477:1463 */       this.PlayerDataConfig.set("15.5.properties.weaponspeed", "&4Slow");
/* 1478:1464 */       this.PlayerDataConfig.set("15.5.properties.damage", "&b1-6-player+1-7");
/* 1479:1465 */       this.PlayerDataConfig.set("15.5.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1480:1466 */       this.PlayerDataConfig.set("15.5.properties.poison", "&a1-5");
/* 1481:1467 */       this.PlayerDataConfig.set("15.5.properties.witherRandomApply", Boolean.valueOf(true));
/* 1482:1468 */       this.PlayerDataConfig.set("15.5.properties.wither", "&71-5");
/* 1483:1469 */       this.PlayerDataConfig.set("15.5.properties.durability", "&7200-325");
/* 1484:1470 */       this.PlayerDataConfig.set("15.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1485:1471 */       this.PlayerDataConfig.set("15.5.properties.soulbound", Boolean.valueOf(true));
/* 1486:1472 */       this.PlayerDataConfig.set("15.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1487:1473 */       this.PlayerDataConfig.set("15.5.properties.xpLevel", "&b10-player+3");
/* 1488:     */       
/* 1489:1475 */       this.PlayerDataConfig.set("15.6.itemId", Integer.valueOf(271));
/* 1490:1476 */       this.PlayerDataConfig.set("15.6.nameColour", "&f&l");
/* 1491:1477 */       this.PlayerDataConfig.set("15.6.prefix", "random");
/* 1492:1478 */       this.PlayerDataConfig.set("15.6.suffix", "random");
/* 1493:1479 */       this.PlayerDataConfig.set("15.6.properties.weaponspeed", "&4Slow");
/* 1494:1480 */       this.PlayerDataConfig.set("15.6.properties.damage", "&b1-6-player+1-7");
/* 1495:1481 */       this.PlayerDataConfig.set("15.6.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1496:1482 */       this.PlayerDataConfig.set("15.6.properties.poison", "&a1-5");
/* 1497:1483 */       this.PlayerDataConfig.set("15.6.properties.witherRandomApply", Boolean.valueOf(true));
/* 1498:1484 */       this.PlayerDataConfig.set("15.6.properties.wither", "&71-5");
/* 1499:1485 */       this.PlayerDataConfig.set("15.6.properties.durability", "&7200-325");
/* 1500:1486 */       this.PlayerDataConfig.set("15.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1501:1487 */       this.PlayerDataConfig.set("15.6.properties.soulbound", Boolean.valueOf(true));
/* 1502:1488 */       this.PlayerDataConfig.set("15.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1503:1489 */       this.PlayerDataConfig.set("15.6.properties.xpLevel", "&b10-player+3");
/* 1504:     */       
/* 1505:1491 */       this.PlayerDataConfig.set("15.7.itemId", Integer.valueOf(270));
/* 1506:1492 */       this.PlayerDataConfig.set("15.7.nameColour", "&f&l");
/* 1507:1493 */       this.PlayerDataConfig.set("15.7.prefix", "random");
/* 1508:1494 */       this.PlayerDataConfig.set("15.7.suffix", "random");
/* 1509:1495 */       this.PlayerDataConfig.set("15.7.properties.weaponspeed", "&4Slow");
/* 1510:1496 */       this.PlayerDataConfig.set("15.7.properties.damage", "&b1-6-player+1-7");
/* 1511:1497 */       this.PlayerDataConfig.set("15.7.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1512:1498 */       this.PlayerDataConfig.set("15.7.properties.poison", "&a1-5");
/* 1513:1499 */       this.PlayerDataConfig.set("15.7.properties.witherRandomApply", Boolean.valueOf(true));
/* 1514:1500 */       this.PlayerDataConfig.set("15.7.properties.wither", "&71-5");
/* 1515:1501 */       this.PlayerDataConfig.set("15.7.properties.durability", "&7200-325");
/* 1516:1502 */       this.PlayerDataConfig.set("15.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1517:1503 */       this.PlayerDataConfig.set("15.7.properties.soulbound", Boolean.valueOf(true));
/* 1518:1504 */       this.PlayerDataConfig.set("15.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1519:1505 */       this.PlayerDataConfig.set("15.7.properties.xpLevel", "&b10-player+3");
/* 1520:     */       
/* 1521:1507 */       this.PlayerDataConfig.set("15.8.itemId", Integer.valueOf(261));
/* 1522:1508 */       this.PlayerDataConfig.set("15.8.nameColour", "&f&l");
/* 1523:1509 */       this.PlayerDataConfig.set("15.8.prefix", "random");
/* 1524:1510 */       this.PlayerDataConfig.set("15.8.suffix", "random");
/* 1525:1511 */       this.PlayerDataConfig.set("15.8.properties.weaponspeed", "&4Slow");
/* 1526:1512 */       this.PlayerDataConfig.set("15.8.properties.damage", "&b1-6-player+1-7");
/* 1527:1513 */       this.PlayerDataConfig.set("15.8.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1528:1514 */       this.PlayerDataConfig.set("15.8.properties.poison", "&a1-5");
/* 1529:1515 */       this.PlayerDataConfig.set("15.8.properties.witherRandomApply", Boolean.valueOf(true));
/* 1530:1516 */       this.PlayerDataConfig.set("15.8.properties.wither", "&71-5");
/* 1531:1517 */       this.PlayerDataConfig.set("15.8.properties.durability", "&7200-325");
/* 1532:1518 */       this.PlayerDataConfig.set("15.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1533:1519 */       this.PlayerDataConfig.set("15.8.properties.soulbound", Boolean.valueOf(true));
/* 1534:1520 */       this.PlayerDataConfig.set("15.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1535:1521 */       this.PlayerDataConfig.set("15.8.properties.xpLevel", "&b10-player+3");
/* 1536:     */       
/* 1537:1523 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1538:     */     }
/* 1539:     */     catch (Exception e)
/* 1540:     */     {
/* 1541:1526 */       e.printStackTrace();
/* 1542:1527 */       System.out.println("*********** Failed to save default zombie file! ***********");
/* 1543:     */     }
/* 1544:     */   }
/* 1545:     */   
/* 1546:     */   public void writePig_Zombie()
/* 1547:     */   {
/* 1548:     */     try
/* 1549:     */     {
/* 1550:1535 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1551:1536 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "pig_zombie.yml");
/* 1552:     */       
/* 1553:1538 */       this.PlayerDataConfig.set("15", null);
/* 1554:1539 */       this.PlayerDataConfig.set("15.1.itemId", Integer.valueOf(298));
/* 1555:1540 */       this.PlayerDataConfig.set("15.1.nameColour", "&f&l");
/* 1556:1541 */       this.PlayerDataConfig.set("15.1.prefix", "random");
/* 1557:1542 */       this.PlayerDataConfig.set("15.1.suffix", "random");
/* 1558:1543 */       this.PlayerDataConfig.set("15.1.properties.armour", "&b2-5");
/* 1559:1544 */       this.PlayerDataConfig.set("15.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1560:1545 */       this.PlayerDataConfig.set("15.1.properties.health", "&b10-player+30");
/* 1561:1546 */       this.PlayerDataConfig.set("15.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1562:1547 */       this.PlayerDataConfig.set("15.1.properties.healthRegen", "&b1-4");
/* 1563:1548 */       this.PlayerDataConfig.set("15.1.properties.durability", "&71350-1500");
/* 1564:1549 */       this.PlayerDataConfig.set("15.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1565:1550 */       this.PlayerDataConfig.set("15.1.properties.soulbound", Boolean.valueOf(true));
/* 1566:1551 */       this.PlayerDataConfig.set("15.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1567:1552 */       this.PlayerDataConfig.set("15.1.properties.xpLevel", "&b10-player+3");
/* 1568:     */       
/* 1569:1554 */       this.PlayerDataConfig.set("15.2.itemId", Integer.valueOf(299));
/* 1570:1555 */       this.PlayerDataConfig.set("15.2.nameColour", "&f&l");
/* 1571:1556 */       this.PlayerDataConfig.set("15.2.prefix", "random");
/* 1572:1557 */       this.PlayerDataConfig.set("15.2.suffix", "random");
/* 1573:1558 */       this.PlayerDataConfig.set("15.2.properties.armour", "&b2-5");
/* 1574:1559 */       this.PlayerDataConfig.set("15.2.properties.healthRandomApply", Boolean.valueOf(true));
/* 1575:1560 */       this.PlayerDataConfig.set("15.2.properties.health", "&b10-player+30");
/* 1576:1561 */       this.PlayerDataConfig.set("15.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1577:1562 */       this.PlayerDataConfig.set("15.2.properties.healthRegen", "&b1-4");
/* 1578:1563 */       this.PlayerDataConfig.set("15.2.properties.durability", "&71350-1500");
/* 1579:1564 */       this.PlayerDataConfig.set("15.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1580:1565 */       this.PlayerDataConfig.set("15.2.properties.soulbound", Boolean.valueOf(true));
/* 1581:1566 */       this.PlayerDataConfig.set("15.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1582:1567 */       this.PlayerDataConfig.set("15.2.properties.xpLevel", "&b10-player+3");
/* 1583:     */       
/* 1584:1569 */       this.PlayerDataConfig.set("15.3.itemId", Integer.valueOf(300));
/* 1585:1570 */       this.PlayerDataConfig.set("15.3.nameColour", "&f&l");
/* 1586:1571 */       this.PlayerDataConfig.set("15.3.prefix", "random");
/* 1587:1572 */       this.PlayerDataConfig.set("15.3.suffix", "random");
/* 1588:1573 */       this.PlayerDataConfig.set("15.3.properties.armour", "&b2-5");
/* 1589:1574 */       this.PlayerDataConfig.set("15.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1590:1575 */       this.PlayerDataConfig.set("15.3.properties.health", "&b10-player+30");
/* 1591:1576 */       this.PlayerDataConfig.set("15.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1592:1577 */       this.PlayerDataConfig.set("15.3.properties.healthRegen", "&b1-4");
/* 1593:1578 */       this.PlayerDataConfig.set("15.3.properties.durability", "&71350-1500");
/* 1594:1579 */       this.PlayerDataConfig.set("15.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1595:1580 */       this.PlayerDataConfig.set("15.3.properties.soulbound", Boolean.valueOf(true));
/* 1596:1581 */       this.PlayerDataConfig.set("15.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1597:1582 */       this.PlayerDataConfig.set("15.3.properties.xpLevel", "&b10-player+3");
/* 1598:     */       
/* 1599:1584 */       this.PlayerDataConfig.set("15.4.itemId", Integer.valueOf(301));
/* 1600:1585 */       this.PlayerDataConfig.set("15.4.nameColour", "&f&l");
/* 1601:1586 */       this.PlayerDataConfig.set("15.4.prefix", "random");
/* 1602:1587 */       this.PlayerDataConfig.set("15.4.suffix", "random");
/* 1603:1588 */       this.PlayerDataConfig.set("15.4.properties.armour", "&b2-5");
/* 1604:1589 */       this.PlayerDataConfig.set("15.4.properties.healthRandomApply", Boolean.valueOf(true));
/* 1605:1590 */       this.PlayerDataConfig.set("15.4.properties.health", "&b10-player+30");
/* 1606:1591 */       this.PlayerDataConfig.set("15.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1607:1592 */       this.PlayerDataConfig.set("15.4.properties.healthRegen", "&b1-4");
/* 1608:1593 */       this.PlayerDataConfig.set("15.4.properties.durability", "&71350-1500");
/* 1609:1594 */       this.PlayerDataConfig.set("15.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1610:1595 */       this.PlayerDataConfig.set("15.4.properties.soulbound", Boolean.valueOf(true));
/* 1611:1596 */       this.PlayerDataConfig.set("15.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1612:1597 */       this.PlayerDataConfig.set("15.4.properties.xpLevel", "&b10-player+3");
/* 1613:     */       
/* 1614:1599 */       this.PlayerDataConfig.set("15.5.itemId", Integer.valueOf(268));
/* 1615:1600 */       this.PlayerDataConfig.set("15.5.nameColour", "&f&l");
/* 1616:1601 */       this.PlayerDataConfig.set("15.5.prefix", "random");
/* 1617:1602 */       this.PlayerDataConfig.set("15.5.suffix", "random");
/* 1618:1603 */       this.PlayerDataConfig.set("15.5.properties.weaponspeed", "&4Slow");
/* 1619:1604 */       this.PlayerDataConfig.set("15.5.properties.damage", "&b1-6-player+1-7");
/* 1620:1605 */       this.PlayerDataConfig.set("15.5.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1621:1606 */       this.PlayerDataConfig.set("15.5.properties.poison", "&a1-5");
/* 1622:1607 */       this.PlayerDataConfig.set("15.5.properties.witherRandomApply", Boolean.valueOf(true));
/* 1623:1608 */       this.PlayerDataConfig.set("15.5.properties.wither", "&71-5");
/* 1624:1609 */       this.PlayerDataConfig.set("15.5.properties.durability", "&7200-325");
/* 1625:1610 */       this.PlayerDataConfig.set("15.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1626:1611 */       this.PlayerDataConfig.set("15.5.properties.soulbound", Boolean.valueOf(true));
/* 1627:1612 */       this.PlayerDataConfig.set("15.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1628:1613 */       this.PlayerDataConfig.set("15.5.properties.xpLevel", "&b10-player+3");
/* 1629:     */       
/* 1630:1615 */       this.PlayerDataConfig.set("15.6.itemId", Integer.valueOf(271));
/* 1631:1616 */       this.PlayerDataConfig.set("15.6.nameColour", "&f&l");
/* 1632:1617 */       this.PlayerDataConfig.set("15.6.prefix", "random");
/* 1633:1618 */       this.PlayerDataConfig.set("15.6.suffix", "random");
/* 1634:1619 */       this.PlayerDataConfig.set("15.6.properties.weaponspeed", "&4Slow");
/* 1635:1620 */       this.PlayerDataConfig.set("15.6.properties.damage", "&b1-6-player+1-7");
/* 1636:1621 */       this.PlayerDataConfig.set("15.6.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1637:1622 */       this.PlayerDataConfig.set("15.6.properties.poison", "&a1-5");
/* 1638:1623 */       this.PlayerDataConfig.set("15.6.properties.witherRandomApply", Boolean.valueOf(true));
/* 1639:1624 */       this.PlayerDataConfig.set("15.6.properties.wither", "&71-5");
/* 1640:1625 */       this.PlayerDataConfig.set("15.6.properties.durability", "&7200-325");
/* 1641:1626 */       this.PlayerDataConfig.set("15.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1642:1627 */       this.PlayerDataConfig.set("15.6.properties.soulbound", Boolean.valueOf(true));
/* 1643:1628 */       this.PlayerDataConfig.set("15.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1644:1629 */       this.PlayerDataConfig.set("15.6.properties.xpLevel", "&b10-player+3");
/* 1645:     */       
/* 1646:1631 */       this.PlayerDataConfig.set("15.7.itemId", Integer.valueOf(270));
/* 1647:1632 */       this.PlayerDataConfig.set("15.7.nameColour", "&f&l");
/* 1648:1633 */       this.PlayerDataConfig.set("15.7.prefix", "random");
/* 1649:1634 */       this.PlayerDataConfig.set("15.7.suffix", "random");
/* 1650:1635 */       this.PlayerDataConfig.set("15.7.properties.weaponspeed", "&4Slow");
/* 1651:1636 */       this.PlayerDataConfig.set("15.7.properties.damage", "&b1-6-player+1-7");
/* 1652:1637 */       this.PlayerDataConfig.set("15.7.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1653:1638 */       this.PlayerDataConfig.set("15.7.properties.poison", "&a1-5");
/* 1654:1639 */       this.PlayerDataConfig.set("15.7.properties.witherRandomApply", Boolean.valueOf(true));
/* 1655:1640 */       this.PlayerDataConfig.set("15.7.properties.wither", "&71-5");
/* 1656:1641 */       this.PlayerDataConfig.set("15.7.properties.durability", "&7200-325");
/* 1657:1642 */       this.PlayerDataConfig.set("15.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1658:1643 */       this.PlayerDataConfig.set("15.7.properties.soulbound", Boolean.valueOf(true));
/* 1659:1644 */       this.PlayerDataConfig.set("15.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1660:1645 */       this.PlayerDataConfig.set("15.7.properties.xpLevel", "&b10-player+3");
/* 1661:     */       
/* 1662:1647 */       this.PlayerDataConfig.set("15.8.itemId", Integer.valueOf(261));
/* 1663:1648 */       this.PlayerDataConfig.set("15.8.nameColour", "&f&l");
/* 1664:1649 */       this.PlayerDataConfig.set("15.8.prefix", "random");
/* 1665:1650 */       this.PlayerDataConfig.set("15.8.suffix", "random");
/* 1666:1651 */       this.PlayerDataConfig.set("15.8.properties.weaponspeed", "&4Slow");
/* 1667:1652 */       this.PlayerDataConfig.set("15.8.properties.damage", "&b1-6-player+1-7");
/* 1668:1653 */       this.PlayerDataConfig.set("15.8.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1669:1654 */       this.PlayerDataConfig.set("15.8.properties.poison", "&a1-5");
/* 1670:1655 */       this.PlayerDataConfig.set("15.8.properties.witherRandomApply", Boolean.valueOf(true));
/* 1671:1656 */       this.PlayerDataConfig.set("15.8.properties.wither", "&71-5");
/* 1672:1657 */       this.PlayerDataConfig.set("15.8.properties.durability", "&7200-325");
/* 1673:1658 */       this.PlayerDataConfig.set("15.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1674:1659 */       this.PlayerDataConfig.set("15.8.properties.soulbound", Boolean.valueOf(true));
/* 1675:1660 */       this.PlayerDataConfig.set("15.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1676:1661 */       this.PlayerDataConfig.set("15.8.properties.xpLevel", "&b10-player+3");
/* 1677:     */       
/* 1678:1663 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1679:     */     }
/* 1680:     */     catch (Exception e)
/* 1681:     */     {
/* 1682:1666 */       e.printStackTrace();
/* 1683:1667 */       System.out.println("*********** Failed to save default pig_zombie file! ***********");
/* 1684:     */     }
/* 1685:     */   }
/* 1686:     */   
/* 1687:     */   public void writeSetBonuses()
/* 1688:     */   {
/* 1689:     */     try
/* 1690:     */     {
/* 1691:1675 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1692:1676 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + "SetBonuses.yml");
/* 1693:     */       
/* 1694:1678 */       this.PlayerDataConfig.set("Sets", null);
/* 1695:     */       
/* 1696:1680 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1697:     */     }
/* 1698:     */     catch (Exception e)
/* 1699:     */     {
/* 1700:1683 */       e.printStackTrace();
/* 1701:1684 */       System.out.println("*********** Failed to save default SetBonuses file! ***********");
/* 1702:     */     }
/* 1703:     */   }
/* 1704:     */   
/* 1705:     */   public void writeLanguageEN()
/* 1706:     */   {
/* 1707:     */     try
/* 1708:     */     {
/* 1709:1692 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1710:1693 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-en.yml");
/* 1711:     */       
/* 1712:1695 */       this.PlayerDataConfig.set("ErrorMessages", null);
/* 1713:1696 */       this.PlayerDataConfig.set("ErrorMessages.PermissionDeniedError", "&cYou do not have permission to perform that command.");
/* 1714:1697 */       this.PlayerDataConfig.set("ErrorMessages.IngameOnlyError", "You can only run this command in-game!");
/* 1715:1698 */       this.PlayerDataConfig.set("ErrorMessages.DoesntExistError", "&cdoes not exist!");
/* 1716:1699 */       this.PlayerDataConfig.set("ErrorMessages.PlayerNotOnlineError", "&cis not online!");
/* 1717:1700 */       this.PlayerDataConfig.set("ErrorMessages.NotEnoughSpaceError", "&cdoes not have enough space in their inventory.");
/* 1718:1701 */       this.PlayerDataConfig.set("ErrorMessages.IncludeItemNameError", "&cYou need to include an item name!");
/* 1719:1702 */       this.PlayerDataConfig.set("ErrorMessages.EnterPlayerNameError", "&cYou need to enter a players name!");
/* 1720:1703 */       this.PlayerDataConfig.set("ErrorMessages.ItemAlreadyExistsError", "&cThat item already exists. Please choose a different name for the item.");
/* 1721:1704 */       this.PlayerDataConfig.set("ErrorMessages.NullItemInHandError", "&cYou need to be holding an item to use this command.");
/* 1722:1705 */       this.PlayerDataConfig.set("ErrorMessages.NoLoreError", "&cThat item doesn't contain any lore.");
/* 1723:1706 */       this.PlayerDataConfig.set("ClassRequirementMessages", null);
/* 1724:1707 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1725:1708 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1726:1709 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1727:1710 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1728:1711 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.ItemInHand", "&cYou dropped the item in your hand as you had no inventory slots free and you no longer have the required class to hold it.");
/* 1729:1712 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Head", "&cYou are not the required class to wear your Helmet.");
/* 1730:1713 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Chest", "&cYou are not the required class to wear your Chestplate.");
/* 1731:1714 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Legs", "&cYou are not the required class to wear your Pants.");
/* 1732:1715 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Boots", "&cYou are not the required class to wear your Boots.");
/* 1733:1716 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.ItemInHand", "&cYou are not the required class to hold that item.");
/* 1734:1717 */       this.PlayerDataConfig.set("LevelRequirementMessages", null);
/* 1735:1718 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and you no longer have the required level to wear it.");
/* 1736:1719 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and you no longer have the required level to wear it.");
/* 1737:1720 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and you no longer have the required level to wear it.");
/* 1738:1721 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and you no longer have the required level to wear it.");
/* 1739:1722 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.ItemInHand", "&cYou dropped the item in your hand as you had no inventory slots free and you no longer have the required level to hold it.");
/* 1740:1723 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Head", "&cYou do not have the required level to wear your Helmet.");
/* 1741:1724 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Chest", "&cYou do not have the required level to wear your Chestplate.");
/* 1742:1725 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Legs", "&cYou do not have the required level to wear your Pants.");
/* 1743:1726 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Boots", "&cYou do not have the required level to wear your Boots.");
/* 1744:1727 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.ItemInHand", "&cYou do not have the required level to hold that item.");
/* 1745:1728 */       this.PlayerDataConfig.set("DamageMessages", null);
/* 1746:1729 */       this.PlayerDataConfig.set("DamageMessages.CriticalStrikeSuccess", "&aCritical Strike!");
/* 1747:1730 */       this.PlayerDataConfig.set("DamageMessages.EnemyCriticalStrikeSuccess", "&ccrit you!");
/* 1748:1731 */       this.PlayerDataConfig.set("DamageMessages.DodgeSuccess", "&aDodge!");
/* 1749:1732 */       this.PlayerDataConfig.set("DamageMessages.EnemyDodgeSuccess", "&cdodged your attack!");
/* 1750:1733 */       this.PlayerDataConfig.set("DamageMessages.BlockSuccess", "&aBlocked!");
/* 1751:1734 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlockSuccess", "&cblocked your attack!");
/* 1752:1735 */       this.PlayerDataConfig.set("DamageMessages.LifeStealSuccess", "&aLife stolen!");
/* 1753:1736 */       this.PlayerDataConfig.set("DamageMessages.EnemyLifeStealSuccess", "&cstole some of your life!");
/* 1754:1737 */       this.PlayerDataConfig.set("DamageMessages.ReflectSuccess", "&aReflected!");
/* 1755:1738 */       this.PlayerDataConfig.set("DamageMessages.EnemyReflectSuccess", "&creflected your attack!");
/* 1756:1739 */       this.PlayerDataConfig.set("DamageMessages.FireSuccess", "&aTarget set alight!");
/* 1757:1740 */       this.PlayerDataConfig.set("DamageMessages.EnemyFireSuccess", "&cset you on fire!");
/* 1758:1741 */       this.PlayerDataConfig.set("DamageMessages.IceSuccess", "&aTarget slowed!");
/* 1759:1742 */       this.PlayerDataConfig.set("DamageMessages.EnemyIceSuccess", "&cslowed you!");
/* 1760:1743 */       this.PlayerDataConfig.set("DamageMessages.PoisonSuccess", "&aTarget poisoned!");
/* 1761:1744 */       this.PlayerDataConfig.set("DamageMessages.EnemyPoisonSuccess", "&cpoisoned you!");
/* 1762:1745 */       this.PlayerDataConfig.set("DamageMessages.WitherSuccess", "&aTarget withered!");
/* 1763:1746 */       this.PlayerDataConfig.set("DamageMessages.EnemyWitherSuccess", "&cwithered you!");
/* 1764:1747 */       this.PlayerDataConfig.set("DamageMessages.HarmSuccess", "&aTarget harmed!");
/* 1765:1748 */       this.PlayerDataConfig.set("DamageMessages.EnemyHarmSuccess", "&charmed you!");
/* 1766:1749 */       this.PlayerDataConfig.set("DamageMessages.BlindSuccess", "&aTarget blinded!");
/* 1767:1750 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlindSuccess", "&cblinded you!");
/* 1768:1751 */       this.PlayerDataConfig.set("SoulboundMessages", null);
/* 1769:1752 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and this item is bound to");
/* 1770:1753 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and this item is bound to");
/* 1771:1754 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and this item is bound to");
/* 1772:1755 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and this item is bound to");
/* 1773:1756 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.ItemInHand", "&cYou dropped item in your hand as you had no inventory slots free and this item is bound to");
/* 1774:1757 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Head", "&cThis Helmet is bound to");
/* 1775:1758 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Chest", "&cThis Chestplate is bound to");
/* 1776:1759 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Legs", "&cThese Leggings are bound to");
/* 1777:1760 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Boots", "&cThese Boots are bound to");
/* 1778:1761 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.ItemInHand", "&cThis item in your hand is bound to");
/* 1779:1762 */       this.PlayerDataConfig.set("SpellMessages", null);
/* 1780:1763 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.ItemInHand", "&eRight-click to cast");
/* 1781:1764 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.CooldownRemaining", "&dseconds remaining.");
/* 1782:1765 */       this.PlayerDataConfig.set("RepairMessages", null);
/* 1783:1766 */       this.PlayerDataConfig.set("RepairMessages.RepairSuccessful", "&aSuccessfully repaired");
/* 1784:1767 */       this.PlayerDataConfig.set("RepairMessages.DoesntNeedRepair", "&cdoesn't need repairing.");
/* 1785:1768 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughLeather", "&cNot enough Leather to repair");
/* 1786:1769 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cNot enough Wood to repair");
/* 1787:1770 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCobblestone", "&cNot enough Cobblestone to repair");
/* 1788:1771 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughIron", "&cNot enough Iron Ingots to repair");
/* 1789:1772 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughGold", "&cNot enough Gold Ingots to repair");
/* 1790:1773 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughDiamond", "&cNot enough Diamonds to repair");
/* 1791:1774 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughSticks", "&cNot enough Sticks to repair");
/* 1792:1775 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFlint", "&cNot enough Flint to repair");
/* 1793:1776 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cNot enough Wooden Planks to repair");
/* 1794:1777 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughString", "&cNot enough String to repair");
/* 1795:1778 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFishingRod", "&cYou need a Fishing Rod to repair");
/* 1796:1779 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCarrots", "&cYou need a Carrot to repair");
/* 1797:     */       
/* 1798:1781 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1799:     */     }
/* 1800:     */     catch (Exception e)
/* 1801:     */     {
/* 1802:1784 */       e.printStackTrace();
/* 1803:1785 */       System.out.println("*********** Failed to save default language-EN file! ***********");
/* 1804:     */     }
/* 1805:     */   }
/* 1806:     */   
/* 1807:     */   public void writeLanguageDE()
/* 1808:     */   {
/* 1809:     */     try
/* 1810:     */     {
/* 1811:1791 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1812:1792 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-de.yml");
/* 1813:     */       
/* 1814:1794 */       this.PlayerDataConfig.set("ErrorMessages", null);
/* 1815:1795 */       this.PlayerDataConfig.set("ErrorMessages.PermissionDeniedError", "&cDu hast keine Berechtigung dies zu tun.");
/* 1816:1796 */       this.PlayerDataConfig.set("ErrorMessages.IngameOnlyError", "Befehl nur In-Game ausfuehrbar!");
/* 1817:1797 */       this.PlayerDataConfig.set("ErrorMessages.DoesntExistError", "&cexistiert nicht!");
/* 1818:1798 */       this.PlayerDataConfig.set("ErrorMessages.PlayerNotOnlineError", "&cist nicht online!");
/* 1819:1799 */       this.PlayerDataConfig.set("ErrorMessages.NotEnoughSpaceError", "&chat nicht genug Platz im Inventar.");
/* 1820:1800 */       this.PlayerDataConfig.set("ErrorMessages.IncludeItemNameError", "&cDu musst den Namen des Items angeben!");
/* 1821:1801 */       this.PlayerDataConfig.set("ErrorMessages.EnterPlayerNameError", "&cDu musst einen Spielernamen angeben!");
/* 1822:1802 */       this.PlayerDataConfig.set("ErrorMessages.ItemAlreadyExistsError", "&cDieses Item gibt es bereits. Bitte benutze einen anderen Namen fuer das Item.");
/* 1823:1803 */       this.PlayerDataConfig.set("ErrorMessages.NullItemInHandError", "&cDu musst ein Item in der Hand halten um dies zu tun.");
/* 1824:1804 */       this.PlayerDataConfig.set("ErrorMessages.NoLoreError", "&cThat item doesn't contain any lore.");
/* 1825:1805 */       this.PlayerDataConfig.set("ClassRequirementMessages", null);
/* 1826:1806 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1827:1807 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1828:1808 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1829:1809 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1830:1810 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.ItemInHand", "&cYou dropped the item in your hand as you had no inventory slots free and you no longer have the required class to hold it.");
/* 1831:1811 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Head", "&cYou are not the required class to wear your Helmet.");
/* 1832:1812 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Chest", "&cYou are not the required class to wear your Chestplate.");
/* 1833:1813 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Legs", "&cYou are not the required class to wear your Pants.");
/* 1834:1814 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Boots", "&cYou are not the required class to wear your Boots.");
/* 1835:1815 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.ItemInHand", "&cYou are not the required class to hold that item.");
/* 1836:1816 */       this.PlayerDataConfig.set("LevelRequirementMessages", null);
/* 1837:1817 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Head", "&cDu hast deinen Helm fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast");
/* 1838:1818 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Chest", "&cDu hast deinen Brustpanzer fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast.");
/* 1839:1819 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Legs", "&cDu hast deine Hose fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast.");
/* 1840:1820 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Boots", "&cDu hast deine Schuhe fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast.");
/* 1841:1821 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.ItemInHand", "&cDu hast das Item fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast.");
/* 1842:1822 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Head", "&cDu besitzt nicht das Mindestlevel um den Helm zu tragen.");
/* 1843:1823 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Chest", "&cDu besitzt nicht das Mindestlevel um den Brustpanzer zu tragen.");
/* 1844:1824 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Legs", "&cDu besitzt nicht das Mindestlevel um die Hose zu tragen.");
/* 1845:1825 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Boots", "&cDu besitzt nicht das Mindestlevel um die Schuhe zu tragen.");
/* 1846:1826 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.ItemInHand", "&cDu besitzt nicht das Mindestlevel um dieses Item zu halten.");
/* 1847:1827 */       this.PlayerDataConfig.set("DamageMessages", null);
/* 1848:1828 */       this.PlayerDataConfig.set("DamageMessages.CriticalStrikeSuccess", "&aKritischer Treffer!");
/* 1849:1829 */       this.PlayerDataConfig.set("DamageMessages.EnemyCriticalStrikeSuccess", "&cgegner traf kritisch!");
/* 1850:1830 */       this.PlayerDataConfig.set("DamageMessages.DodgeSuccess", "&aAusgewichen!");
/* 1851:1831 */       this.PlayerDataConfig.set("DamageMessages.EnemyDodgeSuccess", "&cgegner ist ausgewichen!");
/* 1852:1832 */       this.PlayerDataConfig.set("DamageMessages.BlockSuccess", "&aAbgeblockt!");
/* 1853:1833 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlockSuccess", "&cgegner hat Angriff geblockt!");
/* 1854:1834 */       this.PlayerDataConfig.set("DamageMessages.LifeStealSuccess", "&aLeben gestohlen!");
/* 1855:1835 */       this.PlayerDataConfig.set("DamageMessages.EnemyLifeStealSuccess", "&cdir wurde Leben gestohlen!");
/* 1856:1836 */       this.PlayerDataConfig.set("DamageMessages.ReflectSuccess", "&aSchaden Reflektiert!!");
/* 1857:1837 */       this.PlayerDataConfig.set("DamageMessages.EnemyReflectSuccess", "&cGegner hat Schaden Reflektiert!");
/* 1858:1838 */       this.PlayerDataConfig.set("DamageMessages.FireSuccess", "&aGegner entzuendet!");
/* 1859:1839 */       this.PlayerDataConfig.set("DamageMessages.EnemyFireSuccess", "&cdu wurdest entzuendet!");
/* 1860:1840 */       this.PlayerDataConfig.set("DamageMessages.IceSuccess", "&aGegner verlangsamt!");
/* 1861:1841 */       this.PlayerDataConfig.set("DamageMessages.EnemyIceSuccess", "&cdu wurdest verlangsamt!");
/* 1862:1842 */       this.PlayerDataConfig.set("DamageMessages.PoisonSuccess", "&aGegner vergiftet!");
/* 1863:1843 */       this.PlayerDataConfig.set("DamageMessages.EnemyPoisonSuccess", "&cdu wurdest vergiftet!");
/* 1864:1844 */       this.PlayerDataConfig.set("DamageMessages.WitherSuccess", "&aGegner withered!");
/* 1865:1845 */       this.PlayerDataConfig.set("DamageMessages.EnemyWitherSuccess", "&cdu wurdest gewithered!");
/* 1866:1846 */       this.PlayerDataConfig.set("DamageMessages.HarmSuccess", "&aBonus Schaden am Gegner verursacht!");
/* 1867:1847 */       this.PlayerDataConfig.set("DamageMessages.EnemyHarmSuccess", "&cdu bist ohnmaechtig!");
/* 1868:1848 */       this.PlayerDataConfig.set("DamageMessages.BlindSuccess", "&aGegner geblendet!");
/* 1869:1849 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlindSuccess", "&cdu wurdest geblendet!");
/* 1870:1850 */       this.PlayerDataConfig.set("SoulboundMessages", null);
/* 1871:1851 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Head", "&cDu hast den Helm fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1872:1852 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Chest", "&cDu hast den Brustpanzer fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1873:1853 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Legs", "&cDu hast die Hose fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1874:1854 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Boots", "&cDu hast die Schuhe fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1875:1855 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.ItemInHand", "&cDu hast das Item fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1876:1856 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Head", "&cDieser Helm ist gebunden an");
/* 1877:1857 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Chest", "&cDieser Brustpanzer ist gebunden an");
/* 1878:1858 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Legs", "&cDiese Hose ist gebunden an");
/* 1879:1859 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Boots", "&cDiese Schuhe sind gebunden an");
/* 1880:1860 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.ItemInHand", "&cDieses Item ist gebunden an");
/* 1881:1861 */       this.PlayerDataConfig.set("SpellMessages", null);
/* 1882:1862 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.ItemInHand", "Rechtsklick zum Aktivieren von");
/* 1883:1863 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.CooldownRemaining", "&dsekunden verbleibend.");
/* 1884:1864 */       this.PlayerDataConfig.set("RepairMessages", null);
/* 1885:1865 */       this.PlayerDataConfig.set("RepairMessages.RepairSuccessful", "&aErfolgreich repariert");
/* 1886:1866 */       this.PlayerDataConfig.set("RepairMessages.DoesntNeedRepair", "&cmuss nicht repariert werden.");
/* 1887:1867 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughLeather", "&cDu hast nicht genug Leder zum reparieren");
/* 1888:1868 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cDu hast nicht genug Holz zum reparieren");
/* 1889:1869 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCobblestone", "&cDu hast nicht genug Cobblestone zum reparieren");
/* 1890:1870 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughIron", "&cDu hast nicht genug Eisenbarren zum reparieren");
/* 1891:1871 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughGold", "&cDu hast nicht genug Goldbarren zum reparieren");
/* 1892:1872 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughDiamond", "&cDu hast nicht genug Diamanten zum reparieren");
/* 1893:1873 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughSticks", "&cDu hast nicht genug Stoecke zum reparieren");
/* 1894:1874 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFlint", "&cDu hast nicht genug Deuerstein zum reparieren");
/* 1895:1875 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cDu hast nicht genug Holzerne Planken zum reparieren");
/* 1896:1876 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughString", "&cDu hast nicht genug Faden zum reparieren");
/* 1897:1877 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFishingRod", "&cDu brauchste eine Angel zum reparieren");
/* 1898:1878 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCarrots", "&cDu brauchste eine Karotte zum reparieren");
/* 1899:     */       
/* 1900:1880 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1901:     */     }
/* 1902:     */     catch (Exception e)
/* 1903:     */     {
/* 1904:1883 */       e.printStackTrace();
/* 1905:1884 */       System.out.println("*********** Nicht zu Standardsprache-DE-Datei speichern! ***********");
/* 1906:     */     }
/* 1907:     */   }
/* 1908:     */   
/* 1909:     */   public void writeLanguagePL()
/* 1910:     */   {
/* 1911:     */     try
/* 1912:     */     {
/* 1913:1892 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1914:1893 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-pl.yml");
/* 1915:     */       
/* 1916:1895 */       this.PlayerDataConfig.set("ErrorMessages", null);
/* 1917:1896 */       this.PlayerDataConfig.set("ErrorMessages.PermissionDeniedError", "&cNie masz pozwolenia aby uzywac tej komendy!");
/* 1918:1897 */       this.PlayerDataConfig.set("ErrorMessages.IngameOnlyError", "Mozesz uzyc tej komendy tylko w grze!");
/* 1919:1898 */       this.PlayerDataConfig.set("ErrorMessages.DoesntExistError", "&cNie istnieje!");
/* 1920:1899 */       this.PlayerDataConfig.set("ErrorMessages.PlayerNotOnlineError", "&cJest nie aktywny");
/* 1921:1900 */       this.PlayerDataConfig.set("ErrorMessages.NotEnoughSpaceError", "&cNie ma wystarczajaco miejsca w ekwipunku.");
/* 1922:1901 */       this.PlayerDataConfig.set("ErrorMessages.IncludeItemNameError", "&cMusisz wpisac nazwe przedmiotu!");
/* 1923:1902 */       this.PlayerDataConfig.set("ErrorMessages.EnterPlayerNameError", "&cMusisz wpisac nazwe gracza!");
/* 1924:1903 */       this.PlayerDataConfig.set("ErrorMessages.ItemAlreadyExistsError", "&cTen przedmiot juz istnieje. Prosze wybierz inna nazwe dla tego przedmiotu.");
/* 1925:1904 */       this.PlayerDataConfig.set("ErrorMessages.NullItemInHandError", "&cMusisz trzymac przedmiot w rece, aby uzyc tej komendy.");
/* 1926:1905 */       this.PlayerDataConfig.set("ErrorMessages.NoLoreError", "&cThat item doesn't contain any lore.");
/* 1927:1906 */       this.PlayerDataConfig.set("ClassRequirementMessages", null);
/* 1928:1907 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1929:1908 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1930:1909 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1931:1910 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1932:1911 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.ItemInHand", "&cYou dropped the item in your hand as you had no inventory slots free and you no longer have the required class to hold it.");
/* 1933:1912 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Head", "&cYou are not the required class to wear your Helmet.");
/* 1934:1913 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Chest", "&cYou are not the required class to wear your Chestplate.");
/* 1935:1914 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Legs", "&cYou are not the required class to wear your Pants.");
/* 1936:1915 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Boots", "&cYou are not the required class to wear your Boots.");
/* 1937:1916 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.ItemInHand", "&cYou are not the required class to hold that item.");
/* 1938:1917 */       this.PlayerDataConfig.set("LevelRequirementMessages", null);
/* 1939:1918 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Head", "&cUpusciles swoj Helm, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1940:1919 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Chest", "&cUpusciles swoja Zbroje, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1941:1920 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Legs", "&cUpusciles swoje Spodnie, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1942:1921 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Boots", "&cUpusciles swoje Buty, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1943:1922 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.ItemInHand", "&cUpusciles swoj przedmiot, ktory trzymales, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1944:1923 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Head", "&cNie masz wymaganego poziomu aby zalozyc swoj Helm.");
/* 1945:1924 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Chest", "&cNie masz wymaganego poziomu aby zalozyc swoja Zbroje.");
/* 1946:1925 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Legs", "&cNie masz wymaganego poziomu aby zalozyc swoje Spodnie.");
/* 1947:1926 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Boots", "&cNie masz wymaganego poziomu aby zalozyc swoje Buty.");
/* 1948:1927 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.ItemInHand", "&cNie masz wymaganego poziomu aby trzymac ten przedmiot.");
/* 1949:1928 */       this.PlayerDataConfig.set("DamageMessages", null);
/* 1950:1929 */       this.PlayerDataConfig.set("DamageMessages.CriticalStrikeSuccess", "&aKrytyczne uderzenie!");
/* 1951:1930 */       this.PlayerDataConfig.set("DamageMessages.EnemyCriticalStrikeSuccess", "&cotrzymano krytyczne obrazenie!");
/* 1952:1931 */       this.PlayerDataConfig.set("DamageMessages.DodgeSuccess", "&aUnik!");
/* 1953:1932 */       this.PlayerDataConfig.set("DamageMessages.EnemyDodgeSuccess", "&cuniknal twoj atak!");
/* 1954:1933 */       this.PlayerDataConfig.set("DamageMessages.BlockSuccess", "&aBlok!");
/* 1955:1934 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlockSuccess", "&czablokowany twoj atak!");
/* 1956:1935 */       this.PlayerDataConfig.set("DamageMessages.LifeStealSuccess", "&aWyssane zycie!");
/* 1957:1936 */       this.PlayerDataConfig.set("DamageMessages.EnemyLifeStealSuccess", "&cwykradl tobie troche zycia!");
/* 1958:1937 */       this.PlayerDataConfig.set("DamageMessages.ReflectSuccess", "&aReflected!");
/* 1959:1938 */       this.PlayerDataConfig.set("DamageMessages.EnemyReflectSuccess", "&creflected your attack!");
/* 1960:1939 */       this.PlayerDataConfig.set("DamageMessages.FireSuccess", "&aCel podpalony!");
/* 1961:1940 */       this.PlayerDataConfig.set("DamageMessages.EnemyFireSuccess", "&cpodpalil ciebie!");
/* 1962:1941 */       this.PlayerDataConfig.set("DamageMessages.IceSuccess", "&aCel spowolniony!");
/* 1963:1942 */       this.PlayerDataConfig.set("DamageMessages.EnemyIceSuccess", "&cspowolnil ciebie!");
/* 1964:1943 */       this.PlayerDataConfig.set("DamageMessages.PoisonSuccess", "&aCel zatruty!");
/* 1965:1944 */       this.PlayerDataConfig.set("DamageMessages.EnemyPoisonSuccess", "&cotrul ciebie!");
/* 1966:1945 */       this.PlayerDataConfig.set("DamageMessages.WitherSuccess", "&aCel bezwladny!");
/* 1967:1946 */       this.PlayerDataConfig.set("DamageMessages.EnemyWitherSuccess", "&cobezwladnil ciebie!");
/* 1968:1947 */       this.PlayerDataConfig.set("DamageMessages.HarmSuccess", "&acel zraniony!");
/* 1969:1948 */       this.PlayerDataConfig.set("DamageMessages.EnemyHarmSuccess", "&czranill ciebie!");
/* 1970:1949 */       this.PlayerDataConfig.set("DamageMessages.BlindSuccess", "&aCel oslepiony!");
/* 1971:1950 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlindSuccess", "&coslepil ciebie!");
/* 1972:1951 */       this.PlayerDataConfig.set("SoulboundMessages", null);
/* 1973:1952 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Head", "&cUpusciles swoj Helm, poniewaz nie miales miejsca w plecaku i dlatego, ze ten przedmiot jest przywiazany do innego gracza");
/* 1974:1953 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Chest", "&cUpusciles swoja Zbroje, poniewaz nie miales miejsca w plecaku i dlatego, ze ten przedmiot jest przywiazany do innego gracza");
/* 1975:1954 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Legs", "&cUpusciles swoje Spodnie, poniewaz nie miales miejsca w plecaku i dlatego, ze ten nnego gracza");
/* 1976:1955 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Boots", "&cUpusciles swoje Buty, poniewaz nie miales miejsca w plecaku i dlatego, ze ten nnego gracza");
/* 1977:1956 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.ItemInHand", "&cUpusciles przedmiot, ktory trzymales, poniewaz nie miales miejsca w plecaku i dlatego, ze ten przedmiot jest przywiazany do innego gracza");
/* 1978:1957 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Head", "&cTen Helm jest przywiazany do innego gracza");
/* 1979:1958 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Chest", "&cTa Zbroja jest przywiazana do innego gracza");
/* 1980:1959 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Legs", "&cTe Spodnie sa przywiazane do innego gracza");
/* 1981:1960 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Boots", "&cTe Buty sa przywiazane do innego gracza");
/* 1982:1961 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.ItemInHand", "&cTen przedmiot, ktory trzymasz jest przywiazany do innego gracza");
/* 1983:1962 */       this.PlayerDataConfig.set("SpellMessages", null);
/* 1984:1963 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.ItemInHand", "Nacisnij prawy przycisk myszki aby uzyc czaru");
/* 1985:1964 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.CooldownRemaining", "&dpozostaly sekundy.");
/* 1986:1965 */       this.PlayerDataConfig.set("RepairMessages", null);
/* 1987:1966 */       this.PlayerDataConfig.set("RepairMessages.RepairSuccessful", "&aUdana naprawa");
/* 1988:1967 */       this.PlayerDataConfig.set("RepairMessages.DoesntNeedRepair", "&cTen przedmiot nie potrzebuje naprawy.");
/* 1989:1968 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughLeather", "&cNie masz wystarczajaco Skory, aby naprawic ten przedmiot");
/* 1990:1969 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cNie masz wystarczajaco Drewna, aby naprawic ten przedmiot");
/* 1991:1970 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCobblestone", "&cNie masz wystarczajaco Bruku, aby naprawic ten przedmiot");
/* 1992:1971 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughIron", "&cNie masz wystarczajaco Zelaza, aby naprawic ten przedmiot");
/* 1993:1972 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughGold", "&cNie masz wystarczajaco Zlota, aby naprawic ten przedmiot");
/* 1994:1973 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughDiamond", "&cNie masz wystarczajaco Diamentow, aby naprawic ten przedmiot");
/* 1995:1974 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughSticks", "&cNie masz wystarczajaco Kijow, aby naprawic ten przedmiot");
/* 1996:1975 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFlint", "&cNie masz wystarczajaco krzemien, aby naprawic ten przedmiot");
/* 1997:1976 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cNie masz wystarczajaco Drewniane Deski, aby naprawic ten przedmiot");
/* 1998:1977 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughString", "&cNie masz wystarczajaco Cieciw, aby naprawic ten przedmiot");
/* 1999:1978 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFishingRod", "&cPotrzebujesz Wedki, aby naprawic ten przedmiot");
/* 2000:1979 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCarrots", "&cPotrzebujesz Marchewki, aby naprawic ten przedmiot");
/* 2001:     */       
/* 2002:1981 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 2003:     */     }
/* 2004:     */     catch (Exception e)
/* 2005:     */     {
/* 2006:1984 */       e.printStackTrace();
/* 2007:1985 */       System.out.println("*********** Failed to save default language-PL file! ***********");
/* 2008:     */     }
/* 2009:     */   }
/* 2010:     */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.WriteDefaultFiles
 * JD-Core Version:    0.7.0.1
 */