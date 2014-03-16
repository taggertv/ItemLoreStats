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
/*   17:  16 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems").exists()) {
/*   18:  17 */       new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems").mkdirs();
/*   19:     */     }
/*   20:  19 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs").exists()) {
/*   21:  20 */       new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs").mkdirs();
/*   22:     */     }
/*   23:  22 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-en.yml").exists()) {
/*   24:  23 */       writeLanguageEN();
/*   25:     */     }
/*   26:  25 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-de.yml").exists()) {
/*   27:  26 */       writeLanguageDE();
/*   28:     */     }
/*   29:  28 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-pl.yml").exists()) {
/*   30:  29 */       writeLanguagePL();
/*   31:     */     }
/*   32:  31 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "blaze.yml").exists()) {
/*   33:  32 */       writeBlaze();
/*   34:     */     }
/*   35:  34 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "cave_spider.yml").exists()) {
/*   36:  35 */       writeCave_Spider();
/*   37:     */     }
/*   38:  37 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "creeper.yml").exists()) {
/*   39:  38 */       writeCreeper();
/*   40:     */     }
/*   41:  40 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "ender_dragon.yml").exists()) {
/*   42:  41 */       writeEnder_Dragon();
/*   43:     */     }
/*   44:  43 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "enderman.yml").exists()) {
/*   45:  44 */       writeEnderman();
/*   46:     */     }
/*   47:  46 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "ghast.yml").exists()) {
/*   48:  47 */       writeGhast();
/*   49:     */     }
/*   50:  49 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "magma_cube.yml").exists()) {
/*   51:  50 */       writeMagma_Cube();
/*   52:     */     }
/*   53:  52 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "silverfish.yml").exists()) {
/*   54:  53 */       writeSilverfish();
/*   55:     */     }
/*   56:  55 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "skeleton.yml").exists()) {
/*   57:  56 */       writeSkeleton();
/*   58:     */     }
/*   59:  58 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "slime.yml").exists()) {
/*   60:  59 */       writeSlime();
/*   61:     */     }
/*   62:  61 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "spider.yml").exists()) {
/*   63:  62 */       writeSpider();
/*   64:     */     }
/*   65:  64 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "witch.yml").exists()) {
/*   66:  65 */       writeWitch();
/*   67:     */     }
/*   68:  67 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "wither.yml").exists()) {
/*   69:  68 */       writeWither();
/*   70:     */     }
/*   71:  70 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "zombie.yml").exists()) {
/*   72:  71 */       writeZombie();
/*   73:     */     }
/*   74:  73 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "pig_zombie.yml").exists()) {
/*   75:  74 */       writePig_Zombie();
/*   76:     */     }
/*   77:  76 */     if (!new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + "SetBonuses.yml").exists()) {
/*   78:  77 */       writeSetBonuses();
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
/*   89:     */ 
/*   90:  89 */       this.PlayerDataConfig.set("5", null);
/*   91:  90 */       this.PlayerDataConfig.set("5.1.itemId", Integer.valueOf(306));
/*   92:  91 */       this.PlayerDataConfig.set("5.1.nameColour", "&9&l");
/*   93:  92 */       this.PlayerDataConfig.set("5.1.prefix", "random");
/*   94:  93 */       this.PlayerDataConfig.set("5.1.suffix", "random");
/*   95:  94 */       this.PlayerDataConfig.set("5.1.properties.armour", "&b3-7");
/*   96:  95 */       this.PlayerDataConfig.set("5.1.properties.healthRandomApply", Boolean.valueOf(true));
/*   97:  96 */       this.PlayerDataConfig.set("5.1.properties.health", "&b30-player+40");
/*   98:  97 */       this.PlayerDataConfig.set("5.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*   99:  98 */       this.PlayerDataConfig.set("5.1.properties.healthRegen", "&b2-5");
/*  100:  99 */       this.PlayerDataConfig.set("5.1.properties.blockRandomApply", Boolean.valueOf(true));
/*  101: 100 */       this.PlayerDataConfig.set("5.1.properties.block", "&b1-2");
/*  102: 101 */       this.PlayerDataConfig.set("5.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  103: 102 */       this.PlayerDataConfig.set("5.1.properties.movementSpeed", "&e1-3");
/*  104: 103 */       this.PlayerDataConfig.set("5.1.properties.durability", "&71800-2400");
/*  105: 104 */       this.PlayerDataConfig.set("5.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  106: 105 */       this.PlayerDataConfig.set("5.1.properties.soulbound", Boolean.valueOf(true));
/*  107: 106 */       this.PlayerDataConfig.set("5.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  108: 107 */       this.PlayerDataConfig.set("5.1.properties.xpLevel", "&b10-player+3");
/*  109:     */       
/*  110: 109 */       this.PlayerDataConfig.set("5.2.itemId", Integer.valueOf(307));
/*  111: 110 */       this.PlayerDataConfig.set("5.2.nameColour", "&d&l");
/*  112: 111 */       this.PlayerDataConfig.set("5.2.prefix", "random");
/*  113: 112 */       this.PlayerDataConfig.set("5.2.suffix", "random");
/*  114: 113 */       this.PlayerDataConfig.set("5.2.properties.armour", "&b3-7");
/*  115: 114 */       this.PlayerDataConfig.set("5.2.properties.healthRandomApply", Boolean.valueOf(true));
/*  116: 115 */       this.PlayerDataConfig.set("5.2.properties.health", "&b30-player+40");
/*  117: 116 */       this.PlayerDataConfig.set("5.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  118: 117 */       this.PlayerDataConfig.set("5.2.properties.healthRegen", "&b2-5");
/*  119: 118 */       this.PlayerDataConfig.set("5.2.properties.blockRandomApply", Boolean.valueOf(true));
/*  120: 119 */       this.PlayerDataConfig.set("5.2.properties.block", "&b1-2");
/*  121: 120 */       this.PlayerDataConfig.set("5.2.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  122: 121 */       this.PlayerDataConfig.set("5.2.properties.movementSpeed", "&e1-3");
/*  123: 122 */       this.PlayerDataConfig.set("5.2.properties.durability", "&71800-2400");
/*  124: 123 */       this.PlayerDataConfig.set("5.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  125: 124 */       this.PlayerDataConfig.set("5.2.properties.soulbound", Boolean.valueOf(true));
/*  126: 125 */       this.PlayerDataConfig.set("5.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  127: 126 */       this.PlayerDataConfig.set("5.2.properties.xpLevel", "&b10-player+3");
/*  128:     */       
/*  129: 128 */       this.PlayerDataConfig.set("5.3.itemId", Integer.valueOf(308));
/*  130: 129 */       this.PlayerDataConfig.set("5.3.nameColour", "&d&l");
/*  131: 130 */       this.PlayerDataConfig.set("5.3.prefix", "random");
/*  132: 131 */       this.PlayerDataConfig.set("5.3.suffix", "random");
/*  133: 132 */       this.PlayerDataConfig.set("5.3.properties.armour", "&b3-7");
/*  134: 133 */       this.PlayerDataConfig.set("5.3.properties.healthRandomApply", Boolean.valueOf(true));
/*  135: 134 */       this.PlayerDataConfig.set("5.3.properties.health", "&b30-player+40");
/*  136: 135 */       this.PlayerDataConfig.set("5.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  137: 136 */       this.PlayerDataConfig.set("5.3.properties.healthRegen", "&b2-5");
/*  138: 137 */       this.PlayerDataConfig.set("5.3.properties.blockRandomApply", Boolean.valueOf(true));
/*  139: 138 */       this.PlayerDataConfig.set("5.3.properties.block", "&b1-2");
/*  140: 139 */       this.PlayerDataConfig.set("5.3.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  141: 140 */       this.PlayerDataConfig.set("5.3.properties.movementSpeed", "&e1-3");
/*  142: 141 */       this.PlayerDataConfig.set("5.3.properties.durability", "&71800-2400");
/*  143: 142 */       this.PlayerDataConfig.set("5.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  144: 143 */       this.PlayerDataConfig.set("5.3.properties.soulbound", Boolean.valueOf(true));
/*  145: 144 */       this.PlayerDataConfig.set("5.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  146: 145 */       this.PlayerDataConfig.set("5.3.properties.xpLevel", "&b10-player+3");
/*  147:     */       
/*  148: 147 */       this.PlayerDataConfig.set("5.4.itemId", Integer.valueOf(309));
/*  149: 148 */       this.PlayerDataConfig.set("5.4.nameColour", "&d&l");
/*  150: 149 */       this.PlayerDataConfig.set("5.4.prefix", "random");
/*  151: 150 */       this.PlayerDataConfig.set("5.4.suffix", "random");
/*  152: 151 */       this.PlayerDataConfig.set("5.4.properties.armour", "&b3-7");
/*  153: 152 */       this.PlayerDataConfig.set("5.4.properties.healthRandomApply", Boolean.valueOf(true));
/*  154: 153 */       this.PlayerDataConfig.set("5.4.properties.health", "&b30-player+40");
/*  155: 154 */       this.PlayerDataConfig.set("5.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  156: 155 */       this.PlayerDataConfig.set("5.4.properties.healthRegen", "&b2-5");
/*  157: 156 */       this.PlayerDataConfig.set("5.4.properties.blockRandomApply", Boolean.valueOf(true));
/*  158: 157 */       this.PlayerDataConfig.set("5.4.properties.block", "&b1-2");
/*  159: 158 */       this.PlayerDataConfig.set("5.4.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  160: 159 */       this.PlayerDataConfig.set("5.4.properties.movementSpeed", "&e1-3");
/*  161: 160 */       this.PlayerDataConfig.set("5.4.properties.durability", "&71800-2400");
/*  162: 161 */       this.PlayerDataConfig.set("5.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  163: 162 */       this.PlayerDataConfig.set("5.4.properties.soulbound", Boolean.valueOf(true));
/*  164: 163 */       this.PlayerDataConfig.set("5.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  165: 164 */       this.PlayerDataConfig.set("5.4.properties.xpLevel", "&b10-player+3");
/*  166:     */       
/*  167:     */ 
/*  168: 167 */       this.PlayerDataConfig.set("5.5.itemId", Integer.valueOf(276));
/*  169: 168 */       this.PlayerDataConfig.set("5.5.nameColour", "&d&l");
/*  170: 169 */       this.PlayerDataConfig.set("5.5.prefix", "random");
/*  171: 170 */       this.PlayerDataConfig.set("5.5.suffix", "random");
/*  172: 171 */       this.PlayerDataConfig.set("5.5.properties.weaponspeed", "&2Fast");
/*  173: 172 */       this.PlayerDataConfig.set("5.5.properties.damage", "&b40-50-player+60-70");
/*  174: 173 */       this.PlayerDataConfig.set("5.5.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  175: 174 */       this.PlayerDataConfig.set("5.5.properties.critChance", "&b3-9");
/*  176: 175 */       this.PlayerDataConfig.set("5.5.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  177: 176 */       this.PlayerDataConfig.set("5.5.properties.critDamage", "&b8-23");
/*  178: 177 */       this.PlayerDataConfig.set("5.5.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  179: 178 */       this.PlayerDataConfig.set("5.5.properties.lifeSteal", "&23-7");
/*  180: 179 */       this.PlayerDataConfig.set("5.5.properties.blindRandomApply", Boolean.valueOf(true));
/*  181: 180 */       this.PlayerDataConfig.set("5.5.properties.blind", "&f2-5");
/*  182: 181 */       this.PlayerDataConfig.set("5.5.properties.iceRandomApply", Boolean.valueOf(true));
/*  183: 182 */       this.PlayerDataConfig.set("5.5.properties.ice", "&b1-4");
/*  184: 183 */       this.PlayerDataConfig.set("5.5.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  185: 184 */       this.PlayerDataConfig.set("5.5.properties.movementSpeed", "&e1-4");
/*  186: 185 */       this.PlayerDataConfig.set("5.5.properties.durability", "&7600-725");
/*  187: 186 */       this.PlayerDataConfig.set("5.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  188: 187 */       this.PlayerDataConfig.set("5.5.properties.soulbound", Boolean.valueOf(true));
/*  189: 188 */       this.PlayerDataConfig.set("5.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  190: 189 */       this.PlayerDataConfig.set("5.5.properties.xpLevel", "&b2-player+5");
/*  191:     */       
/*  192: 191 */       this.PlayerDataConfig.set("5.6.itemId", Integer.valueOf(279));
/*  193: 192 */       this.PlayerDataConfig.set("5.6.nameColour", "&d&l");
/*  194: 193 */       this.PlayerDataConfig.set("5.6.prefix", "random");
/*  195: 194 */       this.PlayerDataConfig.set("5.6.suffix", "random");
/*  196: 195 */       this.PlayerDataConfig.set("5.6.properties.weaponspeed", "&2Fast");
/*  197: 196 */       this.PlayerDataConfig.set("5.6.properties.damage", "&b40-50-player+60-70");
/*  198: 197 */       this.PlayerDataConfig.set("5.6.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  199: 198 */       this.PlayerDataConfig.set("5.6.properties.critChance", "&b3-9");
/*  200: 199 */       this.PlayerDataConfig.set("5.6.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  201: 200 */       this.PlayerDataConfig.set("5.6.properties.critDamage", "&b8-23");
/*  202: 201 */       this.PlayerDataConfig.set("5.6.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  203: 202 */       this.PlayerDataConfig.set("5.6.properties.lifeSteal", "&23-7");
/*  204: 203 */       this.PlayerDataConfig.set("5.6.properties.blindRandomApply", Boolean.valueOf(true));
/*  205: 204 */       this.PlayerDataConfig.set("5.6.properties.blind", "&f2-5");
/*  206: 205 */       this.PlayerDataConfig.set("5.6.properties.iceRandomApply", Boolean.valueOf(true));
/*  207: 206 */       this.PlayerDataConfig.set("5.6.properties.ice", "&b1-4");
/*  208: 207 */       this.PlayerDataConfig.set("5.6.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  209: 208 */       this.PlayerDataConfig.set("5.6.properties.movementSpeed", "&e1-4");
/*  210: 209 */       this.PlayerDataConfig.set("5.6.properties.durability", "&7600-725");
/*  211: 210 */       this.PlayerDataConfig.set("5.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  212: 211 */       this.PlayerDataConfig.set("5.6.properties.soulbound", Boolean.valueOf(true));
/*  213: 212 */       this.PlayerDataConfig.set("5.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  214: 213 */       this.PlayerDataConfig.set("5.6.properties.xpLevel", "&b2-player+5");
/*  215:     */       
/*  216: 215 */       this.PlayerDataConfig.set("5.7.itemId", Integer.valueOf(261));
/*  217: 216 */       this.PlayerDataConfig.set("5.7.nameColour", "&d&l");
/*  218: 217 */       this.PlayerDataConfig.set("5.7.prefix", "random");
/*  219: 218 */       this.PlayerDataConfig.set("5.7.suffix", "random");
/*  220: 219 */       this.PlayerDataConfig.set("5.7.properties.weaponspeed", "&2Fast");
/*  221: 220 */       this.PlayerDataConfig.set("5.7.properties.damage", "&b40-50-player+60-70");
/*  222: 221 */       this.PlayerDataConfig.set("5.7.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  223: 222 */       this.PlayerDataConfig.set("5.7.properties.critChance", "&b3-9");
/*  224: 223 */       this.PlayerDataConfig.set("5.7.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  225: 224 */       this.PlayerDataConfig.set("5.7.properties.critDamage", "&b8-23");
/*  226: 225 */       this.PlayerDataConfig.set("5.7.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  227: 226 */       this.PlayerDataConfig.set("5.7.properties.lifeSteal", "&23-7");
/*  228: 227 */       this.PlayerDataConfig.set("5.7.properties.blindRandomApply", Boolean.valueOf(true));
/*  229: 228 */       this.PlayerDataConfig.set("5.7.properties.blind", "&f2-5");
/*  230: 229 */       this.PlayerDataConfig.set("5.7.properties.iceRandomApply", Boolean.valueOf(true));
/*  231: 230 */       this.PlayerDataConfig.set("5.7.properties.ice", "&b1-4");
/*  232: 231 */       this.PlayerDataConfig.set("5.7.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  233: 232 */       this.PlayerDataConfig.set("5.7.properties.movementSpeed", "&e1-4");
/*  234: 233 */       this.PlayerDataConfig.set("5.7.properties.durability", "&7600-725");
/*  235: 234 */       this.PlayerDataConfig.set("5.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  236: 235 */       this.PlayerDataConfig.set("5.7.properties.soulbound", Boolean.valueOf(true));
/*  237: 236 */       this.PlayerDataConfig.set("5.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  238: 237 */       this.PlayerDataConfig.set("5.7.properties.xpLevel", "&b2-player+5");
/*  239:     */       
/*  240: 239 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  241:     */     }
/*  242:     */     catch (Exception e)
/*  243:     */     {
/*  244: 242 */       e.printStackTrace();
/*  245: 243 */       System.out.println("*********** Failed to save default blaze file! ***********");
/*  246:     */     }
/*  247:     */   }
/*  248:     */   
/*  249:     */   public void writeCave_Spider()
/*  250:     */   {
/*  251:     */     try
/*  252:     */     {
/*  253: 251 */       this.PlayerDataConfig = new YamlConfiguration();
/*  254: 252 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "cave_spider.yml");
/*  255:     */       
/*  256: 254 */       this.PlayerDataConfig.set("25", null);
/*  257: 255 */       this.PlayerDataConfig.set("25.1.itemId", Integer.valueOf(275));
/*  258: 256 */       this.PlayerDataConfig.set("25.1.nameColour", "&c");
/*  259: 257 */       this.PlayerDataConfig.set("25.1.prefix", "random");
/*  260: 258 */       this.PlayerDataConfig.set("25.1.suffix", "random");
/*  261: 259 */       this.PlayerDataConfig.set("25.1.properties.damage", "&b5-20-player+15-45");
/*  262: 260 */       this.PlayerDataConfig.set("25.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  263: 261 */       this.PlayerDataConfig.set("25.1.properties.critChance", "&b3-6");
/*  264: 262 */       this.PlayerDataConfig.set("25.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  265: 263 */       this.PlayerDataConfig.set("25.1.properties.critDamage", "&b2-4");
/*  266: 264 */       this.PlayerDataConfig.set("25.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  267: 265 */       this.PlayerDataConfig.set("25.1.properties.healthRegen", "&b1-2");
/*  268: 266 */       this.PlayerDataConfig.set("25.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  269: 267 */       this.PlayerDataConfig.set("25.1.properties.lifeSteal", "&21-3");
/*  270: 268 */       this.PlayerDataConfig.set("25.1.properties.poisonRandomApply", Boolean.valueOf(true));
/*  271: 269 */       this.PlayerDataConfig.set("25.1.properties.poison", "&a3-5");
/*  272: 270 */       this.PlayerDataConfig.set("25.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  273: 271 */       this.PlayerDataConfig.set("25.1.properties.movementSpeed", "&e2-4");
/*  274: 272 */       this.PlayerDataConfig.set("25.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  275: 273 */       this.PlayerDataConfig.set("25.1.properties.xpLevel", "&b3-player+2");
/*  276: 274 */       this.PlayerDataConfig.set("25.1.properties.durability", "&7600-750");
/*  277: 275 */       this.PlayerDataConfig.set("25.1.properties.droppedXp", "2");
/*  278:     */       
/*  279: 277 */       this.PlayerDataConfig.set("10", null);
/*  280: 278 */       this.PlayerDataConfig.set("10.1.itemId", Integer.valueOf(261));
/*  281: 279 */       this.PlayerDataConfig.set("10.1.nameColour", "&c");
/*  282: 280 */       this.PlayerDataConfig.set("10.1.prefix", "random");
/*  283: 281 */       this.PlayerDataConfig.set("10.1.suffix", "random");
/*  284: 282 */       this.PlayerDataConfig.set("10.1.properties.damage", "&b15-40-player+30-65");
/*  285: 283 */       this.PlayerDataConfig.set("10.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  286: 284 */       this.PlayerDataConfig.set("10.1.properties.critChance", "&b6-8");
/*  287: 285 */       this.PlayerDataConfig.set("10.5.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  288: 286 */       this.PlayerDataConfig.set("10.5.properties.critDamage", "&b1-5");
/*  289: 287 */       this.PlayerDataConfig.set("10.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  290: 288 */       this.PlayerDataConfig.set("10.1.properties.healthRegen", "&b1-3");
/*  291: 289 */       this.PlayerDataConfig.set("10.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  292: 290 */       this.PlayerDataConfig.set("10.1.properties.lifeSteal", "&22-5");
/*  293: 291 */       this.PlayerDataConfig.set("10.1.properties.poisonRandomApply", Boolean.valueOf(true));
/*  294: 292 */       this.PlayerDataConfig.set("10.1.properties.poison", "&a4-6");
/*  295: 293 */       this.PlayerDataConfig.set("10.1.properties.harmingRandomApply", Boolean.valueOf(true));
/*  296: 294 */       this.PlayerDataConfig.set("10.1.properties.harming", "&d1-2");
/*  297: 295 */       this.PlayerDataConfig.set("10.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  298: 296 */       this.PlayerDataConfig.set("10.1.properties.movementSpeed", "&e2-4");
/*  299: 297 */       this.PlayerDataConfig.set("10.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  300: 298 */       this.PlayerDataConfig.set("10.1.properties.xpLevel", "&b3-player+2");
/*  301: 299 */       this.PlayerDataConfig.set("10.1.properties.durability", "&7300-550");
/*  302: 300 */       this.PlayerDataConfig.set("10.1.properties.droppedXp", "3");
/*  303: 301 */       this.PlayerDataConfig.set("10.1.properties.spells.frostbolt", Boolean.valueOf(true));
/*  304:     */       
/*  305:     */ 
/*  306: 304 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  307:     */     }
/*  308:     */     catch (Exception e)
/*  309:     */     {
/*  310: 307 */       e.printStackTrace();
/*  311: 308 */       System.out.println("*********** Failed to save default cave_spider file! ***********");
/*  312:     */     }
/*  313:     */   }
/*  314:     */   
/*  315:     */   public void writeCreeper()
/*  316:     */   {
/*  317:     */     try
/*  318:     */     {
/*  319: 316 */       this.PlayerDataConfig = new YamlConfiguration();
/*  320: 317 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "creeper.yml");
/*  321:     */       
/*  322:     */ 
/*  323: 320 */       this.PlayerDataConfig.set("8", null);
/*  324: 321 */       this.PlayerDataConfig.set("8.1.itemId", Integer.valueOf(306));
/*  325: 322 */       this.PlayerDataConfig.set("8.1.nameColour", "&9&l");
/*  326: 323 */       this.PlayerDataConfig.set("8.1.prefix", "random");
/*  327: 324 */       this.PlayerDataConfig.set("8.1.suffix", "random");
/*  328: 325 */       this.PlayerDataConfig.set("8.1.properties.armour", "&b3-7");
/*  329: 326 */       this.PlayerDataConfig.set("8.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  330: 327 */       this.PlayerDataConfig.set("8.1.properties.health", "&b20-player+30");
/*  331: 328 */       this.PlayerDataConfig.set("8.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  332: 329 */       this.PlayerDataConfig.set("8.1.properties.healthRegen", "&b2-5");
/*  333: 330 */       this.PlayerDataConfig.set("8.1.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  334: 331 */       this.PlayerDataConfig.set("8.1.properties.block", "&b1-2");
/*  335: 332 */       this.PlayerDataConfig.set("8.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  336: 333 */       this.PlayerDataConfig.set("8.1.properties.movementSpeed", "&e2-4");
/*  337: 334 */       this.PlayerDataConfig.set("8.1.properties.durability", "&71800-2400");
/*  338: 335 */       this.PlayerDataConfig.set("8.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  339: 336 */       this.PlayerDataConfig.set("8.1.properties.soulbound", Boolean.valueOf(true));
/*  340: 337 */       this.PlayerDataConfig.set("8.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  341: 338 */       this.PlayerDataConfig.set("8.1.properties.xpLevel", "&b10-player+3");
/*  342:     */       
/*  343: 340 */       this.PlayerDataConfig.set("8.2.itemId", Integer.valueOf(307));
/*  344: 341 */       this.PlayerDataConfig.set("8.2.nameColour", "&9&l");
/*  345: 342 */       this.PlayerDataConfig.set("8.2.prefix", "random");
/*  346: 343 */       this.PlayerDataConfig.set("8.2.suffix", "random");
/*  347: 344 */       this.PlayerDataConfig.set("8.2.properties.armour", "&b3-7");
/*  348: 345 */       this.PlayerDataConfig.set("8.2.properties.healthRandomApply", Boolean.valueOf(true));
/*  349: 346 */       this.PlayerDataConfig.set("8.2.properties.health", "&b20-player+30");
/*  350: 347 */       this.PlayerDataConfig.set("8.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  351: 348 */       this.PlayerDataConfig.set("8.2.properties.healthRegen", "&b2-5");
/*  352: 349 */       this.PlayerDataConfig.set("8.2.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  353: 350 */       this.PlayerDataConfig.set("8.2.properties.block", "&b1-2");
/*  354: 351 */       this.PlayerDataConfig.set("8.2.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  355: 352 */       this.PlayerDataConfig.set("8.2.properties.movementSpeed", "&e2-4");
/*  356: 353 */       this.PlayerDataConfig.set("8.2.properties.durability", "&71800-2400");
/*  357: 354 */       this.PlayerDataConfig.set("8.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  358: 355 */       this.PlayerDataConfig.set("8.2.properties.soulbound", Boolean.valueOf(true));
/*  359: 356 */       this.PlayerDataConfig.set("8.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  360: 357 */       this.PlayerDataConfig.set("8.2.properties.xpLevel", "&b10-player+3");
/*  361:     */       
/*  362: 359 */       this.PlayerDataConfig.set("8.3.itemId", Integer.valueOf(308));
/*  363: 360 */       this.PlayerDataConfig.set("8.3.nameColour", "&9&l");
/*  364: 361 */       this.PlayerDataConfig.set("8.3.prefix", "random");
/*  365: 362 */       this.PlayerDataConfig.set("8.3.suffix", "random");
/*  366: 363 */       this.PlayerDataConfig.set("8.3.properties.armour", "&b3-7");
/*  367: 364 */       this.PlayerDataConfig.set("8.3.properties.healthRandomApply", Boolean.valueOf(true));
/*  368: 365 */       this.PlayerDataConfig.set("8.3.properties.health", "&b20-player+30");
/*  369: 366 */       this.PlayerDataConfig.set("8.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  370: 367 */       this.PlayerDataConfig.set("8.3.properties.healthRegen", "&b2-5");
/*  371: 368 */       this.PlayerDataConfig.set("8.3.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  372: 369 */       this.PlayerDataConfig.set("8.3.properties.block", "&b1-2");
/*  373: 370 */       this.PlayerDataConfig.set("8.3.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  374: 371 */       this.PlayerDataConfig.set("8.3.properties.movementSpeed", "&e2-4");
/*  375: 372 */       this.PlayerDataConfig.set("8.3.properties.durability", "&71800-2400");
/*  376: 373 */       this.PlayerDataConfig.set("8.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  377: 374 */       this.PlayerDataConfig.set("8.3.properties.soulbound", Boolean.valueOf(true));
/*  378: 375 */       this.PlayerDataConfig.set("8.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  379: 376 */       this.PlayerDataConfig.set("8.3.properties.xpLevel", "&b10-player+3");
/*  380:     */       
/*  381: 378 */       this.PlayerDataConfig.set("8.4.itemId", Integer.valueOf(309));
/*  382: 379 */       this.PlayerDataConfig.set("8.4.nameColour", "&9&l");
/*  383: 380 */       this.PlayerDataConfig.set("8.4.prefix", "random");
/*  384: 381 */       this.PlayerDataConfig.set("8.4.suffix", "random");
/*  385: 382 */       this.PlayerDataConfig.set("8.4.properties.armour", "&b3-7");
/*  386: 383 */       this.PlayerDataConfig.set("8.4.properties.healthRandomApply", Boolean.valueOf(true));
/*  387: 384 */       this.PlayerDataConfig.set("8.4.properties.health", "&b20-player+30");
/*  388: 385 */       this.PlayerDataConfig.set("8.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  389: 386 */       this.PlayerDataConfig.set("8.4.properties.healthRegen", "&b2-5");
/*  390: 387 */       this.PlayerDataConfig.set("8.4.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  391: 388 */       this.PlayerDataConfig.set("8.4.properties.block", "&b1-2");
/*  392: 389 */       this.PlayerDataConfig.set("8.4.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  393: 390 */       this.PlayerDataConfig.set("8.4.properties.movementSpeed", "&e2-4");
/*  394: 391 */       this.PlayerDataConfig.set("8.4.properties.durability", "&71800-2400");
/*  395: 392 */       this.PlayerDataConfig.set("8.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  396: 393 */       this.PlayerDataConfig.set("8.4.properties.soulbound", Boolean.valueOf(true));
/*  397: 394 */       this.PlayerDataConfig.set("8.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  398: 395 */       this.PlayerDataConfig.set("8.4.properties.xpLevel", "&b10-player+3");
/*  399:     */       
/*  400:     */ 
/*  401: 398 */       this.PlayerDataConfig.set("8.5.itemId", Integer.valueOf(258));
/*  402: 399 */       this.PlayerDataConfig.set("8.5.nameColour", "&9&l");
/*  403: 400 */       this.PlayerDataConfig.set("8.5.prefix", "random");
/*  404: 401 */       this.PlayerDataConfig.set("8.5.suffix", "random");
/*  405: 402 */       this.PlayerDataConfig.set("8.5.properties.weaponspeed", "&2Fast");
/*  406: 403 */       this.PlayerDataConfig.set("8.5.properties.damage", "&b1-6-player+1-7");
/*  407: 404 */       this.PlayerDataConfig.set("8.5.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  408: 405 */       this.PlayerDataConfig.set("8.5.properties.critChance", "&b1-7");
/*  409: 406 */       this.PlayerDataConfig.set("8.5.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  410: 407 */       this.PlayerDataConfig.set("8.5.properties.critDamage", "&b5-18");
/*  411: 408 */       this.PlayerDataConfig.set("8.5.properties.fireRandomApply", Boolean.valueOf(true));
/*  412: 409 */       this.PlayerDataConfig.set("8.5.properties.fire", "&42-6");
/*  413: 410 */       this.PlayerDataConfig.set("8.5.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  414: 411 */       this.PlayerDataConfig.set("8.5.properties.movementSpeed", "&e1-4");
/*  415: 412 */       this.PlayerDataConfig.set("8.5.properties.durability", "&7500-625");
/*  416: 413 */       this.PlayerDataConfig.set("8.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  417: 414 */       this.PlayerDataConfig.set("8.5.properties.soulbound", Boolean.valueOf(true));
/*  418: 415 */       this.PlayerDataConfig.set("8.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  419: 416 */       this.PlayerDataConfig.set("8.5.properties.xpLevel", "&b4-player+5");
/*  420:     */       
/*  421: 418 */       this.PlayerDataConfig.set("8.6.itemId", Integer.valueOf(257));
/*  422: 419 */       this.PlayerDataConfig.set("8.6.nameColour", "&9&l");
/*  423: 420 */       this.PlayerDataConfig.set("8.6.prefix", "random");
/*  424: 421 */       this.PlayerDataConfig.set("8.6.suffix", "random");
/*  425: 422 */       this.PlayerDataConfig.set("8.6.properties.weaponspeed", "&2Fast");
/*  426: 423 */       this.PlayerDataConfig.set("8.6.properties.damage", "&b1-6-player+1-7");
/*  427: 424 */       this.PlayerDataConfig.set("8.6.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  428: 425 */       this.PlayerDataConfig.set("8.6.properties.critChance", "&b1-7");
/*  429: 426 */       this.PlayerDataConfig.set("8.6.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  430: 427 */       this.PlayerDataConfig.set("8.6.properties.critDamage", "&b5-18");
/*  431: 428 */       this.PlayerDataConfig.set("8.6.properties.fireRandomApply", Boolean.valueOf(true));
/*  432: 429 */       this.PlayerDataConfig.set("8.6.properties.fire", "&42-6");
/*  433: 430 */       this.PlayerDataConfig.set("8.6.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  434: 431 */       this.PlayerDataConfig.set("8.6.properties.movementSpeed", "&e1-4");
/*  435: 432 */       this.PlayerDataConfig.set("8.6.properties.durability", "&7500-625");
/*  436: 433 */       this.PlayerDataConfig.set("8.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  437: 434 */       this.PlayerDataConfig.set("8.6.properties.soulbound", Boolean.valueOf(true));
/*  438: 435 */       this.PlayerDataConfig.set("8.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  439: 436 */       this.PlayerDataConfig.set("8.6.properties.xpLevel", "&b4-player+5");
/*  440:     */       
/*  441: 438 */       this.PlayerDataConfig.set("8.7.itemId", Integer.valueOf(261));
/*  442: 439 */       this.PlayerDataConfig.set("8.7.nameColour", "&9&l");
/*  443: 440 */       this.PlayerDataConfig.set("8.7.prefix", "random");
/*  444: 441 */       this.PlayerDataConfig.set("8.7.suffix", "random");
/*  445: 442 */       this.PlayerDataConfig.set("8.7.properties.weaponspeed", "&2Fast");
/*  446: 443 */       this.PlayerDataConfig.set("8.7.properties.damage", "&b1-6-player+1-7");
/*  447: 444 */       this.PlayerDataConfig.set("8.7.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  448: 445 */       this.PlayerDataConfig.set("8.7.properties.critChance", "&b1-7");
/*  449: 446 */       this.PlayerDataConfig.set("8.7.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  450: 447 */       this.PlayerDataConfig.set("8.7.properties.critDamage", "&b5-18");
/*  451: 448 */       this.PlayerDataConfig.set("8.7.properties.fireRandomApply", Boolean.valueOf(true));
/*  452: 449 */       this.PlayerDataConfig.set("8.7.properties.fire", "&42-6");
/*  453: 450 */       this.PlayerDataConfig.set("8.7.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  454: 451 */       this.PlayerDataConfig.set("8.7.properties.movementSpeed", "&b1-4");
/*  455: 452 */       this.PlayerDataConfig.set("8.7.properties.durability", "&7500-625");
/*  456: 453 */       this.PlayerDataConfig.set("8.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  457: 454 */       this.PlayerDataConfig.set("8.7.properties.soulbound", Boolean.valueOf(true));
/*  458: 455 */       this.PlayerDataConfig.set("8.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  459: 456 */       this.PlayerDataConfig.set("8.7.properties.xpLevel", "&b4-player+5");
/*  460:     */       
/*  461: 458 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  462:     */     }
/*  463:     */     catch (Exception e)
/*  464:     */     {
/*  465: 461 */       e.printStackTrace();
/*  466: 462 */       System.out.println("*********** Failed to save default creeper file! ***********");
/*  467:     */     }
/*  468:     */   }
/*  469:     */   
/*  470:     */   public void writeEnder_Dragon()
/*  471:     */   {
/*  472:     */     try
/*  473:     */     {
/*  474: 470 */       this.PlayerDataConfig = new YamlConfiguration();
/*  475: 471 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "ender_dragon.yml");
/*  476:     */       
/*  477: 473 */       this.PlayerDataConfig.set("100", null);
/*  478: 474 */       this.PlayerDataConfig.set("100.1.itemId", Integer.valueOf(276));
/*  479: 475 */       this.PlayerDataConfig.set("100.1.nameColour", "&9&l");
/*  480: 476 */       this.PlayerDataConfig.set("100.1.prefix", "random");
/*  481: 477 */       this.PlayerDataConfig.set("100.1.suffix", "random");
/*  482: 478 */       this.PlayerDataConfig.set("100.1.properties.damage", "&b1-6-player+1-7");
/*  483: 479 */       this.PlayerDataConfig.set("100.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  484: 480 */       this.PlayerDataConfig.set("100.1.properties.critChance", "&b8-14");
/*  485: 481 */       this.PlayerDataConfig.set("100.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  486: 482 */       this.PlayerDataConfig.set("100.1.properties.critDamage", "&b7-23");
/*  487: 483 */       this.PlayerDataConfig.set("100.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  488: 484 */       this.PlayerDataConfig.set("100.1.properties.health", "&b65-player+115");
/*  489: 485 */       this.PlayerDataConfig.set("100.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  490: 486 */       this.PlayerDataConfig.set("100.1.properties.healthRegen", "&b3-5");
/*  491: 487 */       this.PlayerDataConfig.set("100.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  492: 488 */       this.PlayerDataConfig.set("100.1.properties.lifeSteal", "&22-5");
/*  493: 489 */       this.PlayerDataConfig.set("100.1.properties.witherRandomApply", Boolean.valueOf(true));
/*  494: 490 */       this.PlayerDataConfig.set("100.1.properties.wither", "&74-6");
/*  495: 491 */       this.PlayerDataConfig.set("100.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  496: 492 */       this.PlayerDataConfig.set("100.1.properties.movementSpeed", "&e2-4");
/*  497: 493 */       this.PlayerDataConfig.set("100.1.properties.durability", "&71900-2450");
/*  498: 494 */       this.PlayerDataConfig.set("100.1.properties.droppedXp", "250");
/*  499: 495 */       this.PlayerDataConfig.set("100.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  500: 496 */       this.PlayerDataConfig.set("100.1.properties.soulbound", Boolean.valueOf(true));
/*  501: 497 */       this.PlayerDataConfig.set("100.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  502: 498 */       this.PlayerDataConfig.set("100.1.properties.xpLevel", "&b3-player+2");
/*  503: 499 */       this.PlayerDataConfig.set("100.1.properties.tntRandomApply", Boolean.valueOf(true));
/*  504: 500 */       this.PlayerDataConfig.set("100.1.properties.spells.tnt", Boolean.valueOf(true));
/*  505:     */       
/*  506: 502 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  507:     */     }
/*  508:     */     catch (Exception e)
/*  509:     */     {
/*  510: 505 */       e.printStackTrace();
/*  511: 506 */       System.out.println("*********** Failed to save default ender_dragon file! ***********");
/*  512:     */     }
/*  513:     */   }
/*  514:     */   
/*  515:     */   public void writeEnderman()
/*  516:     */   {
/*  517:     */     try
/*  518:     */     {
/*  519: 514 */       this.PlayerDataConfig = new YamlConfiguration();
/*  520: 515 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "enderman.yml");
/*  521:     */       
/*  522:     */ 
/*  523: 518 */       this.PlayerDataConfig.set("5", null);
/*  524: 519 */       this.PlayerDataConfig.set("5.1.itemId", Integer.valueOf(310));
/*  525: 520 */       this.PlayerDataConfig.set("5.1.nameColour", "&d&l");
/*  526: 521 */       this.PlayerDataConfig.set("5.1.prefix", "random");
/*  527: 522 */       this.PlayerDataConfig.set("5.1.suffix", "random");
/*  528: 523 */       this.PlayerDataConfig.set("5.1.properties.armour", "&b5-9");
/*  529: 524 */       this.PlayerDataConfig.set("5.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  530: 525 */       this.PlayerDataConfig.set("5.1.properties.health", "&b50-player+60");
/*  531: 526 */       this.PlayerDataConfig.set("5.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  532: 527 */       this.PlayerDataConfig.set("5.1.properties.healthRegen", "&b3-6");
/*  533: 528 */       this.PlayerDataConfig.set("5.1.properties.blockRandomApply", Boolean.valueOf(true));
/*  534: 529 */       this.PlayerDataConfig.set("5.1.properties.block", "&b1-3");
/*  535: 530 */       this.PlayerDataConfig.set("5.1.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  536: 531 */       this.PlayerDataConfig.set("5.1.properties.block", "&b1-3");
/*  537: 532 */       this.PlayerDataConfig.set("5.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  538: 533 */       this.PlayerDataConfig.set("5.1.properties.lifeSteal", "&23-6");
/*  539: 534 */       this.PlayerDataConfig.set("5.1.properties.durability", "&72500-3300");
/*  540: 535 */       this.PlayerDataConfig.set("5.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  541: 536 */       this.PlayerDataConfig.set("5.1.properties.soulbound", Boolean.valueOf(true));
/*  542: 537 */       this.PlayerDataConfig.set("5.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  543: 538 */       this.PlayerDataConfig.set("5.1.properties.xpLevel", "&b10-player+3");
/*  544:     */       
/*  545: 540 */       this.PlayerDataConfig.set("5.2.itemId", Integer.valueOf(311));
/*  546: 541 */       this.PlayerDataConfig.set("5.2.nameColour", "&d&l");
/*  547: 542 */       this.PlayerDataConfig.set("5.2.prefix", "random");
/*  548: 543 */       this.PlayerDataConfig.set("5.2.suffix", "random");
/*  549: 544 */       this.PlayerDataConfig.set("5.2.properties.armour", "&b5-9");
/*  550: 545 */       this.PlayerDataConfig.set("5.2.properties.healthRandomApply", Boolean.valueOf(true));
/*  551: 546 */       this.PlayerDataConfig.set("5.2.properties.health", "&b50-player+60");
/*  552: 547 */       this.PlayerDataConfig.set("5.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  553: 548 */       this.PlayerDataConfig.set("5.2.properties.healthRegen", "&b3-6");
/*  554: 549 */       this.PlayerDataConfig.set("5.2.properties.blockRandomApply", Boolean.valueOf(true));
/*  555: 550 */       this.PlayerDataConfig.set("5.2.properties.block", "&b1-3");
/*  556: 551 */       this.PlayerDataConfig.set("5.2.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  557: 552 */       this.PlayerDataConfig.set("5.2.properties.block", "&b1-3");
/*  558: 553 */       this.PlayerDataConfig.set("5.2.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  559: 554 */       this.PlayerDataConfig.set("5.2.properties.lifeSteal", "&23-6");
/*  560: 555 */       this.PlayerDataConfig.set("5.2.properties.durability", "&72500-3300");
/*  561: 556 */       this.PlayerDataConfig.set("5.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  562: 557 */       this.PlayerDataConfig.set("5.2.properties.soulbound", Boolean.valueOf(true));
/*  563: 558 */       this.PlayerDataConfig.set("5.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  564: 559 */       this.PlayerDataConfig.set("5.2.properties.xpLevel", "&b10-player+3");
/*  565:     */       
/*  566: 561 */       this.PlayerDataConfig.set("5.3.itemId", Integer.valueOf(312));
/*  567: 562 */       this.PlayerDataConfig.set("5.3.nameColour", "&d&l");
/*  568: 563 */       this.PlayerDataConfig.set("5.3.prefix", "random");
/*  569: 564 */       this.PlayerDataConfig.set("5.3.suffix", "random");
/*  570: 565 */       this.PlayerDataConfig.set("5.3.properties.armour", "&b5-9");
/*  571: 566 */       this.PlayerDataConfig.set("5.3.properties.healthRandomApply", Boolean.valueOf(true));
/*  572: 567 */       this.PlayerDataConfig.set("5.3.properties.health", "&b50-player+60");
/*  573: 568 */       this.PlayerDataConfig.set("5.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  574: 569 */       this.PlayerDataConfig.set("5.3.properties.healthRegen", "&b3-6");
/*  575: 570 */       this.PlayerDataConfig.set("5.3.properties.blockRandomApply", Boolean.valueOf(true));
/*  576: 571 */       this.PlayerDataConfig.set("5.3.properties.block", "&b1-3");
/*  577: 572 */       this.PlayerDataConfig.set("5.3.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  578: 573 */       this.PlayerDataConfig.set("5.3.properties.block", "&b1-3");
/*  579: 574 */       this.PlayerDataConfig.set("5.3.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  580: 575 */       this.PlayerDataConfig.set("5.3.properties.lifeSteal", "&23-6");
/*  581: 576 */       this.PlayerDataConfig.set("5.3.properties.durability", "&72500-3300");
/*  582: 577 */       this.PlayerDataConfig.set("5.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  583: 578 */       this.PlayerDataConfig.set("5.3.properties.soulbound", Boolean.valueOf(true));
/*  584: 579 */       this.PlayerDataConfig.set("5.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  585: 580 */       this.PlayerDataConfig.set("5.3.properties.xpLevel", "&b10-player+3");
/*  586:     */       
/*  587: 582 */       this.PlayerDataConfig.set("5.4.itemId", Integer.valueOf(313));
/*  588: 583 */       this.PlayerDataConfig.set("5.4.nameColour", "&d&l");
/*  589: 584 */       this.PlayerDataConfig.set("5.4.prefix", "random");
/*  590: 585 */       this.PlayerDataConfig.set("5.4.suffix", "random");
/*  591: 586 */       this.PlayerDataConfig.set("5.4.properties.armour", "&b5-9");
/*  592: 587 */       this.PlayerDataConfig.set("5.4.properties.healthRandomApply", Boolean.valueOf(true));
/*  593: 588 */       this.PlayerDataConfig.set("5.4.properties.health", "&b50-player+60");
/*  594: 589 */       this.PlayerDataConfig.set("5.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  595: 590 */       this.PlayerDataConfig.set("5.4.properties.healthRegen", "&b3-6");
/*  596: 591 */       this.PlayerDataConfig.set("5.4.properties.blockRandomApply", Boolean.valueOf(true));
/*  597: 592 */       this.PlayerDataConfig.set("5.4.properties.block", "&b1-3");
/*  598: 593 */       this.PlayerDataConfig.set("5.4.properties.dodgeRandomApply", Boolean.valueOf(true));
/*  599: 594 */       this.PlayerDataConfig.set("5.4.properties.block", "&b1-3");
/*  600: 595 */       this.PlayerDataConfig.set("5.4.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  601: 596 */       this.PlayerDataConfig.set("5.4.properties.lifeSteal", "&23-6");
/*  602: 597 */       this.PlayerDataConfig.set("5.4.properties.durability", "&72500-3300");
/*  603: 598 */       this.PlayerDataConfig.set("5.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  604: 599 */       this.PlayerDataConfig.set("5.4.properties.soulbound", Boolean.valueOf(true));
/*  605: 600 */       this.PlayerDataConfig.set("5.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  606: 601 */       this.PlayerDataConfig.set("5.4.properties.xpLevel", "&b10-player+3");
/*  607:     */       
/*  608:     */ 
/*  609: 604 */       this.PlayerDataConfig.set("5.5.itemId", Integer.valueOf(267));
/*  610: 605 */       this.PlayerDataConfig.set("5.5.nameColour", "&d&l");
/*  611: 606 */       this.PlayerDataConfig.set("5.5.prefix", "random");
/*  612: 607 */       this.PlayerDataConfig.set("5.5.suffix", "random");
/*  613: 608 */       this.PlayerDataConfig.set("5.5.properties.weaponspeed", "&eNormal");
/*  614: 609 */       this.PlayerDataConfig.set("5.5.properties.damage", "&b1-6-player+1-7");
/*  615: 610 */       this.PlayerDataConfig.set("5.5.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  616: 611 */       this.PlayerDataConfig.set("5.5.properties.critChance", "&b2-8");
/*  617: 612 */       this.PlayerDataConfig.set("5.5.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  618: 613 */       this.PlayerDataConfig.set("5.5.properties.critDamage", "&b7-23");
/*  619: 614 */       this.PlayerDataConfig.set("5.5.properties.blindRandomApply", Boolean.valueOf(true));
/*  620: 615 */       this.PlayerDataConfig.set("5.5.properties.blind", "&f3-7");
/*  621: 616 */       this.PlayerDataConfig.set("5.5.properties.iceRandomApply", Boolean.valueOf(true));
/*  622: 617 */       this.PlayerDataConfig.set("5.5.properties.ice", "&b1-4");
/*  623: 618 */       this.PlayerDataConfig.set("5.5.properties.durability", "&7500-625");
/*  624: 619 */       this.PlayerDataConfig.set("5.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  625: 620 */       this.PlayerDataConfig.set("5.5.properties.soulbound", Boolean.valueOf(true));
/*  626: 621 */       this.PlayerDataConfig.set("5.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  627: 622 */       this.PlayerDataConfig.set("5.5.properties.xpLevel", "&b3-player+5");
/*  628:     */       
/*  629: 624 */       this.PlayerDataConfig.set("5.6.itemId", Integer.valueOf(292));
/*  630: 625 */       this.PlayerDataConfig.set("5.6.nameColour", "&d&l");
/*  631: 626 */       this.PlayerDataConfig.set("5.6.prefix", "random");
/*  632: 627 */       this.PlayerDataConfig.set("5.6.suffix", "random");
/*  633: 628 */       this.PlayerDataConfig.set("5.6.properties.weaponspeed", "&eNormal");
/*  634: 629 */       this.PlayerDataConfig.set("5.6.properties.damage", "&b1-6-player+1-7");
/*  635: 630 */       this.PlayerDataConfig.set("5.6.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  636: 631 */       this.PlayerDataConfig.set("5.6.properties.critChance", "&b2-8");
/*  637: 632 */       this.PlayerDataConfig.set("5.6.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  638: 633 */       this.PlayerDataConfig.set("5.6.properties.critDamage", "&b7-23");
/*  639: 634 */       this.PlayerDataConfig.set("5.6.properties.blindRandomApply", Boolean.valueOf(true));
/*  640: 635 */       this.PlayerDataConfig.set("5.6.properties.blind", "&f3-7");
/*  641: 636 */       this.PlayerDataConfig.set("5.6.properties.iceRandomApply", Boolean.valueOf(true));
/*  642: 637 */       this.PlayerDataConfig.set("5.6.properties.ice", "&b1-4");
/*  643: 638 */       this.PlayerDataConfig.set("5.6.properties.durability", "&7500-625");
/*  644: 639 */       this.PlayerDataConfig.set("5.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  645: 640 */       this.PlayerDataConfig.set("5.6.properties.soulbound", Boolean.valueOf(true));
/*  646: 641 */       this.PlayerDataConfig.set("5.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  647: 642 */       this.PlayerDataConfig.set("5.6.properties.xpLevel", "&b3-player+5");
/*  648:     */       
/*  649: 644 */       this.PlayerDataConfig.set("5.7.itemId", Integer.valueOf(261));
/*  650: 645 */       this.PlayerDataConfig.set("5.7.nameColour", "&d&l");
/*  651: 646 */       this.PlayerDataConfig.set("5.7.prefix", "random");
/*  652: 647 */       this.PlayerDataConfig.set("5.7.suffix", "random");
/*  653: 648 */       this.PlayerDataConfig.set("5.7.properties.weaponspeed", "&eNormal");
/*  654: 649 */       this.PlayerDataConfig.set("5.7.properties.damage", "&b1-6-player+1-7");
/*  655: 650 */       this.PlayerDataConfig.set("5.7.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  656: 651 */       this.PlayerDataConfig.set("5.7.properties.critChance", "&b2-8");
/*  657: 652 */       this.PlayerDataConfig.set("5.7.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  658: 653 */       this.PlayerDataConfig.set("5.7.properties.critDamage", "&b7-23");
/*  659: 654 */       this.PlayerDataConfig.set("5.7.properties.blindRandomApply", Boolean.valueOf(true));
/*  660: 655 */       this.PlayerDataConfig.set("5.7.properties.blind", "&f3-7");
/*  661: 656 */       this.PlayerDataConfig.set("5.7.properties.iceRandomApply", Boolean.valueOf(true));
/*  662: 657 */       this.PlayerDataConfig.set("5.7.properties.ice", "&b1-4");
/*  663: 658 */       this.PlayerDataConfig.set("5.7.properties.durability", "&7500-625");
/*  664: 659 */       this.PlayerDataConfig.set("5.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  665: 660 */       this.PlayerDataConfig.set("5.7.properties.soulbound", Boolean.valueOf(true));
/*  666: 661 */       this.PlayerDataConfig.set("5.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  667: 662 */       this.PlayerDataConfig.set("5.7.properties.xpLevel", "&b3-player+5");
/*  668:     */       
/*  669: 664 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  670:     */     }
/*  671:     */     catch (Exception e)
/*  672:     */     {
/*  673: 667 */       e.printStackTrace();
/*  674: 668 */       System.out.println("*********** Failed to save default enderman file! ***********");
/*  675:     */     }
/*  676:     */   }
/*  677:     */   
/*  678:     */   public void writeGhast()
/*  679:     */   {
/*  680:     */     try
/*  681:     */     {
/*  682: 676 */       this.PlayerDataConfig = new YamlConfiguration();
/*  683: 677 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "ghast.yml");
/*  684:     */       
/*  685: 679 */       this.PlayerDataConfig.set("40", null);
/*  686: 680 */       this.PlayerDataConfig.set("40.1.itemId", Integer.valueOf(261));
/*  687: 681 */       this.PlayerDataConfig.set("40.1.nameColour", "&6&l");
/*  688: 682 */       this.PlayerDataConfig.set("40.1.prefix", "random");
/*  689: 683 */       this.PlayerDataConfig.set("40.1.suffix", "random");
/*  690: 684 */       this.PlayerDataConfig.set("40.1.properties.damage", "&b1-6-player+1-7");
/*  691: 685 */       this.PlayerDataConfig.set("40.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  692: 686 */       this.PlayerDataConfig.set("40.1.properties.critChance", "&b1-5");
/*  693: 687 */       this.PlayerDataConfig.set("40.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  694: 688 */       this.PlayerDataConfig.set("40.1.properties.critDamage", "&b8-14");
/*  695: 689 */       this.PlayerDataConfig.set("40.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  696: 690 */       this.PlayerDataConfig.set("40.1.properties.lifeSteal", "&23-5");
/*  697: 691 */       this.PlayerDataConfig.set("40.1.properties.fireRandomApply", Boolean.valueOf(true));
/*  698: 692 */       this.PlayerDataConfig.set("40.1.properties.fire", "&44-8");
/*  699: 693 */       this.PlayerDataConfig.set("40.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  700: 694 */       this.PlayerDataConfig.set("40.1.properties.movementSpeed", "&e1-4");
/*  701: 695 */       this.PlayerDataConfig.set("40.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  702: 696 */       this.PlayerDataConfig.set("40.1.properties.xpLevel", "&b3-player+2");
/*  703: 697 */       this.PlayerDataConfig.set("40.1.properties.durability", "&7900-1350");
/*  704: 698 */       this.PlayerDataConfig.set("40.1.properties.droppedXp", "2");
/*  705:     */       
/*  706: 700 */       this.PlayerDataConfig.set("25", null);
/*  707: 701 */       this.PlayerDataConfig.set("25.1.itemId", Integer.valueOf(309));
/*  708: 702 */       this.PlayerDataConfig.set("25.1.nameColour", "&6&l");
/*  709: 703 */       this.PlayerDataConfig.set("25.1.prefix", "random");
/*  710: 704 */       this.PlayerDataConfig.set("25.1.suffix", "random");
/*  711: 705 */       this.PlayerDataConfig.set("25.1.properties.armour", "&b3-7");
/*  712: 706 */       this.PlayerDataConfig.set("25.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  713: 707 */       this.PlayerDataConfig.set("25.1.properties.health", "&b40-player+80");
/*  714: 708 */       this.PlayerDataConfig.set("25.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  715: 709 */       this.PlayerDataConfig.set("25.1.properties.healthRegen", "&b2-5");
/*  716: 710 */       this.PlayerDataConfig.set("25.1.properties.fireRandomApply", Boolean.valueOf(true));
/*  717: 711 */       this.PlayerDataConfig.set("25.1.properties.fire", "&42-6");
/*  718: 712 */       this.PlayerDataConfig.set("25.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  719: 713 */       this.PlayerDataConfig.set("25.1.properties.movementSpeed", "&e2-3");
/*  720: 714 */       this.PlayerDataConfig.set("25.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  721: 715 */       this.PlayerDataConfig.set("25.1.properties.xpLevel", "&b3-player+2");
/*  722: 716 */       this.PlayerDataConfig.set("25.1.properties.durability", "&7350-650");
/*  723: 717 */       this.PlayerDataConfig.set("25.1.properties.fireballRandomApply", Boolean.valueOf(true));
/*  724: 718 */       this.PlayerDataConfig.set("25.1.properties.spells.fireball", Boolean.valueOf(true));
/*  725:     */       
/*  726: 720 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  727:     */     }
/*  728:     */     catch (Exception e)
/*  729:     */     {
/*  730: 723 */       e.printStackTrace();
/*  731: 724 */       System.out.println("*********** Failed to save default ghast file! ***********");
/*  732:     */     }
/*  733:     */   }
/*  734:     */   
/*  735:     */   public void writeMagma_Cube()
/*  736:     */   {
/*  737:     */     try
/*  738:     */     {
/*  739: 732 */       this.PlayerDataConfig = new YamlConfiguration();
/*  740: 733 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "magma_cube.yml");
/*  741:     */       
/*  742: 735 */       this.PlayerDataConfig.set("75", null);
/*  743: 736 */       this.PlayerDataConfig.set("75.1.itemId", Integer.valueOf(283));
/*  744: 737 */       this.PlayerDataConfig.set("75.1.nameColour", "&6&l");
/*  745: 738 */       this.PlayerDataConfig.set("75.1.prefix", "random");
/*  746: 739 */       this.PlayerDataConfig.set("75.1.suffix", "random");
/*  747: 740 */       this.PlayerDataConfig.set("75.1.properties.damage", "&b1-6-player+1-7");
/*  748: 741 */       this.PlayerDataConfig.set("75.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  749: 742 */       this.PlayerDataConfig.set("75.1.properties.critChance", "&b2-4");
/*  750: 743 */       this.PlayerDataConfig.set("75.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/*  751: 744 */       this.PlayerDataConfig.set("75.1.properties.critDamage", "&b8-14");
/*  752: 745 */       this.PlayerDataConfig.set("75.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  753: 746 */       this.PlayerDataConfig.set("75.1.properties.lifeSteal", "&22-5");
/*  754: 747 */       this.PlayerDataConfig.set("75.1.properties.fireRandomApply", Boolean.valueOf(true));
/*  755: 748 */       this.PlayerDataConfig.set("75.1.properties.fire", "&44-8");
/*  756: 749 */       this.PlayerDataConfig.set("75.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  757: 750 */       this.PlayerDataConfig.set("75.1.properties.movementSpeed", "&e1-4");
/*  758: 751 */       this.PlayerDataConfig.set("75.1.properties.durability", "&7300-550");
/*  759: 752 */       this.PlayerDataConfig.set("75.1.properties.droppedXp", "2");
/*  760: 753 */       this.PlayerDataConfig.set("75.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  761: 754 */       this.PlayerDataConfig.set("75.1.properties.soulbound", Boolean.valueOf(true));
/*  762: 755 */       this.PlayerDataConfig.set("75.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  763: 756 */       this.PlayerDataConfig.set("75.1.properties.xpLevel", "&b3-player+2");
/*  764:     */       
/*  765: 758 */       this.PlayerDataConfig.set("45", null);
/*  766: 759 */       this.PlayerDataConfig.set("45.1.itemId", Integer.valueOf(316));
/*  767: 760 */       this.PlayerDataConfig.set("45.1.nameColour", "&6&l");
/*  768: 761 */       this.PlayerDataConfig.set("45.1.prefix", "random");
/*  769: 762 */       this.PlayerDataConfig.set("45.1.suffix", "random");
/*  770: 763 */       this.PlayerDataConfig.set("45.1.properties.armour", "&b4-8");
/*  771: 764 */       this.PlayerDataConfig.set("45.1.properties.health", "&b30-player+60");
/*  772: 765 */       this.PlayerDataConfig.set("45.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  773: 766 */       this.PlayerDataConfig.set("45.1.properties.healthRegen", "&b1-3");
/*  774: 767 */       this.PlayerDataConfig.set("45.1.properties.fireRandomApply", Boolean.valueOf(true));
/*  775: 768 */       this.PlayerDataConfig.set("45.1.properties.fire", "&42-4");
/*  776: 769 */       this.PlayerDataConfig.set("45.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  777: 770 */       this.PlayerDataConfig.set("45.1.properties.movementSpeed", "&e2-3");
/*  778: 771 */       this.PlayerDataConfig.set("45.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  779: 772 */       this.PlayerDataConfig.set("45.1.properties.xpLevel", "&b3-player+2");
/*  780: 773 */       this.PlayerDataConfig.set("45.1.properties.durability", "&7800-1150");
/*  781:     */       
/*  782: 775 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  783:     */     }
/*  784:     */     catch (Exception e)
/*  785:     */     {
/*  786: 778 */       e.printStackTrace();
/*  787: 779 */       System.out.println("*********** Failed to save default magma_cube file! ***********");
/*  788:     */     }
/*  789:     */   }
/*  790:     */   
/*  791:     */   public void writeSilverfish()
/*  792:     */   {
/*  793:     */     try
/*  794:     */     {
/*  795: 787 */       this.PlayerDataConfig = new YamlConfiguration();
/*  796: 788 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "silverfish.yml");
/*  797:     */       
/*  798: 790 */       this.PlayerDataConfig.set("45", null);
/*  799: 791 */       this.PlayerDataConfig.set("45.1.itemId", Integer.valueOf(299));
/*  800: 792 */       this.PlayerDataConfig.set("45.1.nameColour", "&c&l");
/*  801: 793 */       this.PlayerDataConfig.set("45.1.prefix", "random");
/*  802: 794 */       this.PlayerDataConfig.set("45.1.suffix", "random");
/*  803: 795 */       this.PlayerDataConfig.set("45.1.properties.armour", "&b1-3");
/*  804: 796 */       this.PlayerDataConfig.set("45.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  805: 797 */       this.PlayerDataConfig.set("45.1.properties.health", "&b30-player+60");
/*  806: 798 */       this.PlayerDataConfig.set("45.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  807: 799 */       this.PlayerDataConfig.set("45.1.properties.healthRegen", "&b1-3");
/*  808: 800 */       this.PlayerDataConfig.set("45.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  809: 801 */       this.PlayerDataConfig.set("45.1.properties.movementSpeed", "&e2-5");
/*  810: 802 */       this.PlayerDataConfig.set("45.1.properties.durability", "&7500-950");
/*  811: 803 */       this.PlayerDataConfig.set("45.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  812: 804 */       this.PlayerDataConfig.set("45.1.properties.soulbound", Boolean.valueOf(true));
/*  813: 805 */       this.PlayerDataConfig.set("45.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  814: 806 */       this.PlayerDataConfig.set("45.1.properties.xpLevel", "&b3-player+2");
/*  815:     */       
/*  816: 808 */       this.PlayerDataConfig.set("20", null);
/*  817: 809 */       this.PlayerDataConfig.set("20.1.itemId", Integer.valueOf(301));
/*  818: 810 */       this.PlayerDataConfig.set("20.1.nameColour", "&c&l");
/*  819: 811 */       this.PlayerDataConfig.set("20.1.prefix", "random");
/*  820: 812 */       this.PlayerDataConfig.set("20.1.suffix", "random");
/*  821: 813 */       this.PlayerDataConfig.set("20.1.properties.armour", "&b2-4");
/*  822: 814 */       this.PlayerDataConfig.set("20.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  823: 815 */       this.PlayerDataConfig.set("20.1.properties.health", "&b50-player+30");
/*  824: 816 */       this.PlayerDataConfig.set("20.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  825: 817 */       this.PlayerDataConfig.set("20.1.properties.healthRegen", "&b1-1");
/*  826: 818 */       this.PlayerDataConfig.set("20.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/*  827: 819 */       this.PlayerDataConfig.set("20.1.properties.movementSpeed", "&e4-7");
/*  828: 820 */       this.PlayerDataConfig.set("20.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  829: 821 */       this.PlayerDataConfig.set("20.1.properties.xpLevel", "&b3-player+2");
/*  830: 822 */       this.PlayerDataConfig.set("20.1.properties.durability", "&7600-1100");
/*  831: 823 */       this.PlayerDataConfig.set("20.1.properties.droppedXp", "3");
/*  832:     */       
/*  833: 825 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  834:     */     }
/*  835:     */     catch (Exception e)
/*  836:     */     {
/*  837: 828 */       e.printStackTrace();
/*  838: 829 */       System.out.println("*********** Failed to save default silverfish file! ***********");
/*  839:     */     }
/*  840:     */   }
/*  841:     */   
/*  842:     */   public void writeSkeleton()
/*  843:     */   {
/*  844:     */     try
/*  845:     */     {
/*  846: 837 */       this.PlayerDataConfig = new YamlConfiguration();
/*  847: 838 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "skeleton.yml");
/*  848:     */       
/*  849:     */ 
/*  850: 841 */       this.PlayerDataConfig.set("10", null);
/*  851: 842 */       this.PlayerDataConfig.set("10.1.itemId", Integer.valueOf(314));
/*  852: 843 */       this.PlayerDataConfig.set("10.1.nameColour", "&a&l");
/*  853: 844 */       this.PlayerDataConfig.set("10.1.prefix", "random");
/*  854: 845 */       this.PlayerDataConfig.set("10.1.suffix", "random");
/*  855: 846 */       this.PlayerDataConfig.set("10.1.properties.armour", "&b3-7");
/*  856: 847 */       this.PlayerDataConfig.set("10.1.properties.healthRandomApply", Boolean.valueOf(true));
/*  857: 848 */       this.PlayerDataConfig.set("10.1.properties.health", "&b20-player+30");
/*  858: 849 */       this.PlayerDataConfig.set("10.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  859: 850 */       this.PlayerDataConfig.set("10.1.properties.healthRegen", "&b2-5");
/*  860: 851 */       this.PlayerDataConfig.set("10.1.properties.durability", "&72300-2600");
/*  861: 852 */       this.PlayerDataConfig.set("10.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  862: 853 */       this.PlayerDataConfig.set("10.1.properties.soulbound", Boolean.valueOf(true));
/*  863: 854 */       this.PlayerDataConfig.set("10.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  864: 855 */       this.PlayerDataConfig.set("10.1.properties.xpLevel", "&b10-player+3");
/*  865:     */       
/*  866: 857 */       this.PlayerDataConfig.set("10.2.itemId", Integer.valueOf(315));
/*  867: 858 */       this.PlayerDataConfig.set("10.2.nameColour", "&a&l");
/*  868: 859 */       this.PlayerDataConfig.set("10.2.prefix", "random");
/*  869: 860 */       this.PlayerDataConfig.set("10.2.suffix", "random");
/*  870: 861 */       this.PlayerDataConfig.set("10.2.properties.armour", "&b3-7");
/*  871: 862 */       this.PlayerDataConfig.set("10.2.properties.healthRandomApply", Boolean.valueOf(true));
/*  872: 863 */       this.PlayerDataConfig.set("10.2.properties.health", "&b20-player+30");
/*  873: 864 */       this.PlayerDataConfig.set("10.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  874: 865 */       this.PlayerDataConfig.set("10.2.properties.healthRegen", "&b2-5");
/*  875: 866 */       this.PlayerDataConfig.set("10.2.properties.durability", "&72300-2600");
/*  876: 867 */       this.PlayerDataConfig.set("10.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  877: 868 */       this.PlayerDataConfig.set("10.2.properties.soulbound", Boolean.valueOf(true));
/*  878: 869 */       this.PlayerDataConfig.set("10.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  879: 870 */       this.PlayerDataConfig.set("10.2.properties.xpLevel", "&b10-player+3");
/*  880:     */       
/*  881: 872 */       this.PlayerDataConfig.set("10.3.itemId", Integer.valueOf(316));
/*  882: 873 */       this.PlayerDataConfig.set("10.3.nameColour", "&a&l");
/*  883: 874 */       this.PlayerDataConfig.set("10.3.prefix", "random");
/*  884: 875 */       this.PlayerDataConfig.set("10.3.suffix", "random");
/*  885: 876 */       this.PlayerDataConfig.set("10.3.properties.armour", "&b3-7");
/*  886: 877 */       this.PlayerDataConfig.set("10.3.properties.healthRandomApply", Boolean.valueOf(true));
/*  887: 878 */       this.PlayerDataConfig.set("10.3.properties.health", "&b20-player+30");
/*  888: 879 */       this.PlayerDataConfig.set("10.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  889: 880 */       this.PlayerDataConfig.set("10.3.properties.healthRegen", "&b2-5");
/*  890: 881 */       this.PlayerDataConfig.set("10.3.properties.durability", "&72300-2600");
/*  891: 882 */       this.PlayerDataConfig.set("10.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  892: 883 */       this.PlayerDataConfig.set("10.3.properties.soulbound", Boolean.valueOf(true));
/*  893: 884 */       this.PlayerDataConfig.set("10.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  894: 885 */       this.PlayerDataConfig.set("10.3.properties.xpLevel", "&b10-player+3");
/*  895:     */       
/*  896: 887 */       this.PlayerDataConfig.set("10.4.itemId", Integer.valueOf(317));
/*  897: 888 */       this.PlayerDataConfig.set("10.4.nameColour", "&a&l");
/*  898: 889 */       this.PlayerDataConfig.set("10.4.prefix", "random");
/*  899: 890 */       this.PlayerDataConfig.set("10.4.suffix", "random");
/*  900: 891 */       this.PlayerDataConfig.set("10.4.properties.armour", "&b3-7");
/*  901: 892 */       this.PlayerDataConfig.set("10.4.properties.healthRandomApply", Boolean.valueOf(true));
/*  902: 893 */       this.PlayerDataConfig.set("10.4.properties.health", "&b20-player+30");
/*  903: 894 */       this.PlayerDataConfig.set("10.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/*  904: 895 */       this.PlayerDataConfig.set("10.4.properties.healthRegen", "&b2-5");
/*  905: 896 */       this.PlayerDataConfig.set("10.4.properties.durability", "&72300-2600");
/*  906: 897 */       this.PlayerDataConfig.set("10.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  907: 898 */       this.PlayerDataConfig.set("10.4.properties.soulbound", Boolean.valueOf(true));
/*  908: 899 */       this.PlayerDataConfig.set("10.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  909: 900 */       this.PlayerDataConfig.set("10.4.properties.xpLevel", "&b10-player+3");
/*  910:     */       
/*  911:     */ 
/*  912: 903 */       this.PlayerDataConfig.set("10.5.itemId", Integer.valueOf(283));
/*  913: 904 */       this.PlayerDataConfig.set("10.5.nameColour", "&a&l");
/*  914: 905 */       this.PlayerDataConfig.set("10.5.prefix", "random");
/*  915: 906 */       this.PlayerDataConfig.set("10.5.suffix", "random");
/*  916: 907 */       this.PlayerDataConfig.set("10.5.properties.weaponspeed", "&4Slow");
/*  917: 908 */       this.PlayerDataConfig.set("10.5.properties.damage", "&b1-6-player+1-7");
/*  918: 909 */       this.PlayerDataConfig.set("10.5.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  919: 910 */       this.PlayerDataConfig.set("10.5.properties.lifeSteal", "&22-6");
/*  920: 911 */       this.PlayerDataConfig.set("10.5.properties.iceRandomApply", Boolean.valueOf(true));
/*  921: 912 */       this.PlayerDataConfig.set("10.5.properties.ice", "&b1-5");
/*  922: 913 */       this.PlayerDataConfig.set("10.5.properties.durability", "&7400-525");
/*  923: 914 */       this.PlayerDataConfig.set("10.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  924: 915 */       this.PlayerDataConfig.set("10.5.properties.soulbound", Boolean.valueOf(true));
/*  925: 916 */       this.PlayerDataConfig.set("10.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  926: 917 */       this.PlayerDataConfig.set("10.5.properties.xpLevel", "&b6-player+7");
/*  927:     */       
/*  928: 919 */       this.PlayerDataConfig.set("10.6.itemId", Integer.valueOf(286));
/*  929: 920 */       this.PlayerDataConfig.set("10.6.nameColour", "&a&l");
/*  930: 921 */       this.PlayerDataConfig.set("10.6.prefix", "random");
/*  931: 922 */       this.PlayerDataConfig.set("10.6.suffix", "random");
/*  932: 923 */       this.PlayerDataConfig.set("10.6.properties.weaponspeed", "&4Slow");
/*  933: 924 */       this.PlayerDataConfig.set("10.6.properties.damage", "&b1-6-player+1-7");
/*  934: 925 */       this.PlayerDataConfig.set("10.6.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  935: 926 */       this.PlayerDataConfig.set("10.6.properties.lifeSteal", "&22-6");
/*  936: 927 */       this.PlayerDataConfig.set("10.6.properties.iceRandomApply", Boolean.valueOf(true));
/*  937: 928 */       this.PlayerDataConfig.set("10.6.properties.ice", "&b1-5");
/*  938: 929 */       this.PlayerDataConfig.set("10.6.properties.durability", "&7400-525");
/*  939: 930 */       this.PlayerDataConfig.set("10.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  940: 931 */       this.PlayerDataConfig.set("10.6.properties.soulbound", Boolean.valueOf(true));
/*  941: 932 */       this.PlayerDataConfig.set("10.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  942: 933 */       this.PlayerDataConfig.set("10.6.properties.xpLevel", "&b6-player+7");
/*  943:     */       
/*  944: 935 */       this.PlayerDataConfig.set("10.7.itemId", Integer.valueOf(285));
/*  945: 936 */       this.PlayerDataConfig.set("10.7.nameColour", "&a&l");
/*  946: 937 */       this.PlayerDataConfig.set("10.7.prefix", "random");
/*  947: 938 */       this.PlayerDataConfig.set("10.7.suffix", "random");
/*  948: 939 */       this.PlayerDataConfig.set("10.7.properties.weaponspeed", "&4Slow");
/*  949: 940 */       this.PlayerDataConfig.set("10.7.properties.damage", "&b1-6-player+1-7");
/*  950: 941 */       this.PlayerDataConfig.set("10.7.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  951: 942 */       this.PlayerDataConfig.set("10.7.properties.lifeSteal", "&22-6");
/*  952: 943 */       this.PlayerDataConfig.set("10.7.properties.iceRandomApply", Boolean.valueOf(true));
/*  953: 944 */       this.PlayerDataConfig.set("10.7.properties.ice", "&b1-5");
/*  954: 945 */       this.PlayerDataConfig.set("10.7.properties.durability", "&7400-525");
/*  955: 946 */       this.PlayerDataConfig.set("10.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  956: 947 */       this.PlayerDataConfig.set("10.7.properties.soulbound", Boolean.valueOf(true));
/*  957: 948 */       this.PlayerDataConfig.set("10.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  958: 949 */       this.PlayerDataConfig.set("10.7.properties.xpLevel", "&b6-player+7");
/*  959:     */       
/*  960: 951 */       this.PlayerDataConfig.set("10.8.itemId", Integer.valueOf(261));
/*  961: 952 */       this.PlayerDataConfig.set("10.8.nameColour", "&a&l");
/*  962: 953 */       this.PlayerDataConfig.set("10.8.prefix", "random");
/*  963: 954 */       this.PlayerDataConfig.set("10.8.suffix", "random");
/*  964: 955 */       this.PlayerDataConfig.set("10.8.properties.weaponspeed", "&4Slow");
/*  965: 956 */       this.PlayerDataConfig.set("10.8.properties.damage", "&b1-6-player+1-7");
/*  966: 957 */       this.PlayerDataConfig.set("10.8.properties.lifeStealRandomApply", Boolean.valueOf(true));
/*  967: 958 */       this.PlayerDataConfig.set("10.8.properties.lifeSteal", "&22-6");
/*  968: 959 */       this.PlayerDataConfig.set("10.8.properties.iceRandomApply", Boolean.valueOf(true));
/*  969: 960 */       this.PlayerDataConfig.set("10.8.properties.ice", "&b1-5");
/*  970: 961 */       this.PlayerDataConfig.set("10.8.properties.durability", "&7400-525");
/*  971: 962 */       this.PlayerDataConfig.set("10.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/*  972: 963 */       this.PlayerDataConfig.set("10.8.properties.soulbound", Boolean.valueOf(true));
/*  973: 964 */       this.PlayerDataConfig.set("10.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/*  974: 965 */       this.PlayerDataConfig.set("10.8.properties.xpLevel", "&b6-player+7");
/*  975:     */       
/*  976: 967 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/*  977:     */     }
/*  978:     */     catch (Exception e)
/*  979:     */     {
/*  980: 970 */       e.printStackTrace();
/*  981: 971 */       System.out.println("*********** Failed to save default skeleton file! ***********");
/*  982:     */     }
/*  983:     */   }
/*  984:     */   
/*  985:     */   public void writeSlime()
/*  986:     */   {
/*  987:     */     try
/*  988:     */     {
/*  989: 979 */       this.PlayerDataConfig = new YamlConfiguration();
/*  990: 980 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "slime.yml");
/*  991:     */       
/*  992: 982 */       this.PlayerDataConfig.set("30", null);
/*  993: 983 */       this.PlayerDataConfig.set("30.1.itemId", Integer.valueOf(275));
/*  994: 984 */       this.PlayerDataConfig.set("30.1.nameColour", "&a&l");
/*  995: 985 */       this.PlayerDataConfig.set("30.1.prefix", "random");
/*  996: 986 */       this.PlayerDataConfig.set("30.1.suffix", "random");
/*  997: 987 */       this.PlayerDataConfig.set("30.1.properties.damage", "&b1-6-player+1-7");
/*  998: 988 */       this.PlayerDataConfig.set("30.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/*  999: 989 */       this.PlayerDataConfig.set("30.1.properties.critChance", "&b1-2");
/* 1000: 990 */       this.PlayerDataConfig.set("30.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/* 1001: 991 */       this.PlayerDataConfig.set("30.1.properties.critDamage", "&b3-8");
/* 1002: 992 */       this.PlayerDataConfig.set("30.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1003: 993 */       this.PlayerDataConfig.set("30.1.properties.lifeSteal", "&21-2");
/* 1004: 994 */       this.PlayerDataConfig.set("30.1.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1005: 995 */       this.PlayerDataConfig.set("30.1.properties.poison", "&a3-4");
/* 1006: 996 */       this.PlayerDataConfig.set("30.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/* 1007: 997 */       this.PlayerDataConfig.set("30.1.properties.movementSpeed", "&e1-3");
/* 1008: 998 */       this.PlayerDataConfig.set("30.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1009: 999 */       this.PlayerDataConfig.set("30.1.properties.xpLevel", "&b3-player+2");
/* 1010:1000 */       this.PlayerDataConfig.set("30.1.properties.durability", "&7250-550");
/* 1011:     */       
/* 1012:1002 */       this.PlayerDataConfig.set("15", null);
/* 1013:1003 */       this.PlayerDataConfig.set("15.1.itemId", Integer.valueOf(272));
/* 1014:1004 */       this.PlayerDataConfig.set("15.1.nameColour", "&a&l");
/* 1015:1005 */       this.PlayerDataConfig.set("15.1.prefix", "random");
/* 1016:1006 */       this.PlayerDataConfig.set("15.1.suffix", "random");
/* 1017:1007 */       this.PlayerDataConfig.set("15.1.properties.damage", "&b1-6-player+1-7");
/* 1018:1008 */       this.PlayerDataConfig.set("15.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/* 1019:1009 */       this.PlayerDataConfig.set("15.1.properties.critChance", "&b1-3");
/* 1020:1010 */       this.PlayerDataConfig.set("15.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/* 1021:1011 */       this.PlayerDataConfig.set("15.1.properties.critDamage", "&b3-6");
/* 1022:1012 */       this.PlayerDataConfig.set("15.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1023:1013 */       this.PlayerDataConfig.set("15.1.properties.lifeSteal", "&21-3");
/* 1024:1014 */       this.PlayerDataConfig.set("15.1.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1025:1015 */       this.PlayerDataConfig.set("15.1.properties.poison", "&a1-6");
/* 1026:1016 */       this.PlayerDataConfig.set("15.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/* 1027:1017 */       this.PlayerDataConfig.set("15.1.properties.movementSpeed", "&e2-4");
/* 1028:1018 */       this.PlayerDataConfig.set("15.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1029:1019 */       this.PlayerDataConfig.set("15.1.properties.xpLevel", "&b3-player+2");
/* 1030:1020 */       this.PlayerDataConfig.set("15.1.properties.durability", "&7400-600");
/* 1031:     */       
/* 1032:1022 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1033:     */     }
/* 1034:     */     catch (Exception e)
/* 1035:     */     {
/* 1036:1025 */       e.printStackTrace();
/* 1037:1026 */       System.out.println("*********** Failed to save default slime file! ***********");
/* 1038:     */     }
/* 1039:     */   }
/* 1040:     */   
/* 1041:     */   public void writeSpider()
/* 1042:     */   {
/* 1043:     */     try
/* 1044:     */     {
/* 1045:1034 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1046:1035 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "spider.yml");
/* 1047:     */       
/* 1048:     */ 
/* 1049:1038 */       this.PlayerDataConfig.set("15", null);
/* 1050:1039 */       this.PlayerDataConfig.set("15.1.itemId", Integer.valueOf(298));
/* 1051:1040 */       this.PlayerDataConfig.set("15.1.nameColour", "&f&l");
/* 1052:1041 */       this.PlayerDataConfig.set("15.1.prefix", "random");
/* 1053:1042 */       this.PlayerDataConfig.set("15.1.suffix", "random");
/* 1054:1043 */       this.PlayerDataConfig.set("15.1.properties.armour", "&b2-5");
/* 1055:1044 */       this.PlayerDataConfig.set("15.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1056:1045 */       this.PlayerDataConfig.set("15.1.properties.health", "&b10-player+30");
/* 1057:1046 */       this.PlayerDataConfig.set("15.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1058:1047 */       this.PlayerDataConfig.set("15.1.properties.healthRegen", "&b1-4");
/* 1059:1048 */       this.PlayerDataConfig.set("15.1.properties.durability", "&71350-1500");
/* 1060:1049 */       this.PlayerDataConfig.set("15.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1061:1050 */       this.PlayerDataConfig.set("15.1.properties.soulbound", Boolean.valueOf(true));
/* 1062:1051 */       this.PlayerDataConfig.set("15.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1063:1052 */       this.PlayerDataConfig.set("15.1.properties.xpLevel", "&b10-player+3");
/* 1064:     */       
/* 1065:1054 */       this.PlayerDataConfig.set("15.2.itemId", Integer.valueOf(299));
/* 1066:1055 */       this.PlayerDataConfig.set("15.2.nameColour", "&f&l");
/* 1067:1056 */       this.PlayerDataConfig.set("15.2.prefix", "random");
/* 1068:1057 */       this.PlayerDataConfig.set("15.2.suffix", "random");
/* 1069:1058 */       this.PlayerDataConfig.set("15.2.properties.armour", "&b2-5");
/* 1070:1059 */       this.PlayerDataConfig.set("15.2.properties.healthRandomApply", Boolean.valueOf(true));
/* 1071:1060 */       this.PlayerDataConfig.set("15.2.properties.health", "&b10-player+30");
/* 1072:1061 */       this.PlayerDataConfig.set("15.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1073:1062 */       this.PlayerDataConfig.set("15.2.properties.healthRegen", "&b1-4");
/* 1074:1063 */       this.PlayerDataConfig.set("15.2.properties.durability", "&71350-1500");
/* 1075:1064 */       this.PlayerDataConfig.set("15.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1076:1065 */       this.PlayerDataConfig.set("15.2.properties.soulbound", Boolean.valueOf(true));
/* 1077:1066 */       this.PlayerDataConfig.set("15.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1078:1067 */       this.PlayerDataConfig.set("15.2.properties.xpLevel", "&b10-player+3");
/* 1079:     */       
/* 1080:1069 */       this.PlayerDataConfig.set("15.3.itemId", Integer.valueOf(300));
/* 1081:1070 */       this.PlayerDataConfig.set("15.3.nameColour", "&f&l");
/* 1082:1071 */       this.PlayerDataConfig.set("15.3.prefix", "random");
/* 1083:1072 */       this.PlayerDataConfig.set("15.3.suffix", "random");
/* 1084:1073 */       this.PlayerDataConfig.set("15.3.properties.armour", "&b2-5");
/* 1085:1074 */       this.PlayerDataConfig.set("15.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1086:1075 */       this.PlayerDataConfig.set("15.3.properties.health", "&b10-player+30");
/* 1087:1076 */       this.PlayerDataConfig.set("15.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1088:1077 */       this.PlayerDataConfig.set("15.3.properties.healthRegen", "&b1-4");
/* 1089:1078 */       this.PlayerDataConfig.set("15.3.properties.durability", "&71350-1500");
/* 1090:1079 */       this.PlayerDataConfig.set("15.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1091:1080 */       this.PlayerDataConfig.set("15.3.properties.soulbound", Boolean.valueOf(true));
/* 1092:1081 */       this.PlayerDataConfig.set("15.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1093:1082 */       this.PlayerDataConfig.set("15.3.properties.xpLevel", "&b10-player+3");
/* 1094:     */       
/* 1095:1084 */       this.PlayerDataConfig.set("15.3.itemId", Integer.valueOf(301));
/* 1096:1085 */       this.PlayerDataConfig.set("15.3.nameColour", "&f&l");
/* 1097:1086 */       this.PlayerDataConfig.set("15.3.prefix", "random");
/* 1098:1087 */       this.PlayerDataConfig.set("15.3.suffix", "random");
/* 1099:1088 */       this.PlayerDataConfig.set("15.3.properties.armour", "&b2-5");
/* 1100:1089 */       this.PlayerDataConfig.set("15.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1101:1090 */       this.PlayerDataConfig.set("15.3.properties.health", "&b10-player+30");
/* 1102:1091 */       this.PlayerDataConfig.set("15.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1103:1092 */       this.PlayerDataConfig.set("15.3.properties.healthRegen", "&b1-4");
/* 1104:1093 */       this.PlayerDataConfig.set("15.3.properties.durability", "&71350-1500");
/* 1105:1094 */       this.PlayerDataConfig.set("15.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1106:1095 */       this.PlayerDataConfig.set("15.3.properties.soulbound", Boolean.valueOf(true));
/* 1107:1096 */       this.PlayerDataConfig.set("15.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1108:1097 */       this.PlayerDataConfig.set("15.3.properties.xpLevel", "&b10-player+3");
/* 1109:     */       
/* 1110:     */ 
/* 1111:1100 */       this.PlayerDataConfig.set("15.4.itemId", Integer.valueOf(272));
/* 1112:1101 */       this.PlayerDataConfig.set("15.4.nameColour", "&f&l");
/* 1113:1102 */       this.PlayerDataConfig.set("15.4.prefix", "random");
/* 1114:1103 */       this.PlayerDataConfig.set("15.4.suffix", "random");
/* 1115:1104 */       this.PlayerDataConfig.set("15.4.properties.weaponspeed", "&4Slow");
/* 1116:1105 */       this.PlayerDataConfig.set("15.4.properties.damage", "&b1-6-player+1-7");
/* 1117:1106 */       this.PlayerDataConfig.set("15.4.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1118:1107 */       this.PlayerDataConfig.set("15.4.properties.lifeSteal", "&21-5");
/* 1119:1108 */       this.PlayerDataConfig.set("15.4.properties.iceRandomApply", Boolean.valueOf(true));
/* 1120:1109 */       this.PlayerDataConfig.set("15.4.properties.ice", "&b2-6");
/* 1121:1110 */       this.PlayerDataConfig.set("15.4.properties.durability", "&7300-425");
/* 1122:1111 */       this.PlayerDataConfig.set("15.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1123:1112 */       this.PlayerDataConfig.set("15.4.properties.soulbound", Boolean.valueOf(true));
/* 1124:1113 */       this.PlayerDataConfig.set("15.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1125:1114 */       this.PlayerDataConfig.set("15.4.properties.xpLevel", "&b8-player+5");
/* 1126:     */       
/* 1127:1116 */       this.PlayerDataConfig.set("15.5.itemId", Integer.valueOf(274));
/* 1128:1117 */       this.PlayerDataConfig.set("15.5.nameColour", "&f&l");
/* 1129:1118 */       this.PlayerDataConfig.set("15.5.prefix", "random");
/* 1130:1119 */       this.PlayerDataConfig.set("15.5.suffix", "random");
/* 1131:1120 */       this.PlayerDataConfig.set("15.5.properties.weaponspeed", "&4Slow");
/* 1132:1121 */       this.PlayerDataConfig.set("15.5.properties.damage", "&b1-6-player+1-7");
/* 1133:1122 */       this.PlayerDataConfig.set("15.5.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1134:1123 */       this.PlayerDataConfig.set("15.5.properties.lifeSteal", "&21-5");
/* 1135:1124 */       this.PlayerDataConfig.set("15.5.properties.iceRandomApply", Boolean.valueOf(true));
/* 1136:1125 */       this.PlayerDataConfig.set("15.5.properties.ice", "&b2-6");
/* 1137:1126 */       this.PlayerDataConfig.set("15.5.properties.durability", "&7300-425");
/* 1138:1127 */       this.PlayerDataConfig.set("15.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1139:1128 */       this.PlayerDataConfig.set("15.5.properties.soulbound", Boolean.valueOf(true));
/* 1140:1129 */       this.PlayerDataConfig.set("15.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1141:1130 */       this.PlayerDataConfig.set("15.5.properties.xpLevel", "&b8-player+5");
/* 1142:     */       
/* 1143:1132 */       this.PlayerDataConfig.set("15.6.itemId", Integer.valueOf(275));
/* 1144:1133 */       this.PlayerDataConfig.set("15.6.nameColour", "&f&l");
/* 1145:1134 */       this.PlayerDataConfig.set("15.6.prefix", "random");
/* 1146:1135 */       this.PlayerDataConfig.set("15.6.suffix", "random");
/* 1147:1136 */       this.PlayerDataConfig.set("15.6.properties.weaponspeed", "&4Slow");
/* 1148:1137 */       this.PlayerDataConfig.set("15.6.properties.damage", "&b1-6-player+1-7");
/* 1149:1138 */       this.PlayerDataConfig.set("15.6.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1150:1139 */       this.PlayerDataConfig.set("15.6.properties.lifeSteal", "&21-5");
/* 1151:1140 */       this.PlayerDataConfig.set("15.6.properties.iceRandomApply", Boolean.valueOf(true));
/* 1152:1141 */       this.PlayerDataConfig.set("15.6.properties.ice", "&b2-6");
/* 1153:1142 */       this.PlayerDataConfig.set("15.6.properties.durability", "&7300-425");
/* 1154:1143 */       this.PlayerDataConfig.set("15.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1155:1144 */       this.PlayerDataConfig.set("15.6.properties.soulbound", Boolean.valueOf(true));
/* 1156:1145 */       this.PlayerDataConfig.set("15.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1157:1146 */       this.PlayerDataConfig.set("15.6.properties.xpLevel", "&b8-player+5");
/* 1158:     */       
/* 1159:1148 */       this.PlayerDataConfig.set("15.7.itemId", Integer.valueOf(291));
/* 1160:1149 */       this.PlayerDataConfig.set("15.7.nameColour", "&f&l");
/* 1161:1150 */       this.PlayerDataConfig.set("15.7.prefix", "random");
/* 1162:1151 */       this.PlayerDataConfig.set("15.7.suffix", "random");
/* 1163:1152 */       this.PlayerDataConfig.set("15.7.properties.weaponspeed", "&4Slow");
/* 1164:1153 */       this.PlayerDataConfig.set("15.7.properties.damage", "&b1-6-player+1-7");
/* 1165:1154 */       this.PlayerDataConfig.set("15.7.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1166:1155 */       this.PlayerDataConfig.set("15.7.properties.lifeSteal", "&21-5");
/* 1167:1156 */       this.PlayerDataConfig.set("15.7.properties.iceRandomApply", Boolean.valueOf(true));
/* 1168:1157 */       this.PlayerDataConfig.set("15.7.properties.ice", "&b2-6");
/* 1169:1158 */       this.PlayerDataConfig.set("15.7.properties.durability", "&7300-425");
/* 1170:1159 */       this.PlayerDataConfig.set("15.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1171:1160 */       this.PlayerDataConfig.set("15.7.properties.soulbound", Boolean.valueOf(true));
/* 1172:1161 */       this.PlayerDataConfig.set("15.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1173:1162 */       this.PlayerDataConfig.set("15.7.properties.xpLevel", "&b8-player+5");
/* 1174:     */       
/* 1175:1164 */       this.PlayerDataConfig.set("15.8.itemId", Integer.valueOf(261));
/* 1176:1165 */       this.PlayerDataConfig.set("15.8.nameColour", "&f&l");
/* 1177:1166 */       this.PlayerDataConfig.set("15.8.prefix", "random");
/* 1178:1167 */       this.PlayerDataConfig.set("15.8.suffix", "random");
/* 1179:1168 */       this.PlayerDataConfig.set("15.8.properties.weaponspeed", "&4Slow");
/* 1180:1169 */       this.PlayerDataConfig.set("15.8.properties.damage", "&b1-6-player+1-7");
/* 1181:1170 */       this.PlayerDataConfig.set("15.8.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1182:1171 */       this.PlayerDataConfig.set("15.8.properties.lifeSteal", "&21-5");
/* 1183:1172 */       this.PlayerDataConfig.set("15.8.properties.iceRandomApply", Boolean.valueOf(true));
/* 1184:1173 */       this.PlayerDataConfig.set("15.8.properties.ice", "&b2-6");
/* 1185:1174 */       this.PlayerDataConfig.set("15.8.properties.durability", "&7300-425");
/* 1186:1175 */       this.PlayerDataConfig.set("15.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1187:1176 */       this.PlayerDataConfig.set("15.8.properties.soulbound", Boolean.valueOf(true));
/* 1188:1177 */       this.PlayerDataConfig.set("15.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1189:1178 */       this.PlayerDataConfig.set("15.8.properties.xpLevel", "&b8-player+5");
/* 1190:     */       
/* 1191:1180 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1192:     */     }
/* 1193:     */     catch (Exception e)
/* 1194:     */     {
/* 1195:1183 */       e.printStackTrace();
/* 1196:1184 */       System.out.println("*********** Failed to save default spider file! ***********");
/* 1197:     */     }
/* 1198:     */   }
/* 1199:     */   
/* 1200:     */   public void writeWitch()
/* 1201:     */   {
/* 1202:     */     try
/* 1203:     */     {
/* 1204:1192 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1205:1193 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "witch.yml");
/* 1206:     */       
/* 1207:     */ 
/* 1208:1196 */       this.PlayerDataConfig.set("10", null);
/* 1209:1197 */       this.PlayerDataConfig.set("10.1.itemId", Integer.valueOf(314));
/* 1210:1198 */       this.PlayerDataConfig.set("10.1.nameColour", "&a&l");
/* 1211:1199 */       this.PlayerDataConfig.set("10.1.prefix", "random");
/* 1212:1200 */       this.PlayerDataConfig.set("10.1.suffix", "random");
/* 1213:1201 */       this.PlayerDataConfig.set("10.1.properties.armour", "&b3-7");
/* 1214:1202 */       this.PlayerDataConfig.set("10.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1215:1203 */       this.PlayerDataConfig.set("10.1.properties.health", "&b20-player+30");
/* 1216:1204 */       this.PlayerDataConfig.set("10.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1217:1205 */       this.PlayerDataConfig.set("10.1.properties.healthRegen", "&b2-5");
/* 1218:1206 */       this.PlayerDataConfig.set("10.1.properties.blockRandomApply", Boolean.valueOf(true));
/* 1219:1207 */       this.PlayerDataConfig.set("10.1.properties.block", "&b1-3");
/* 1220:1208 */       this.PlayerDataConfig.set("10.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1221:1209 */       this.PlayerDataConfig.set("10.1.properties.lifeSteal", "&22-5");
/* 1222:1210 */       this.PlayerDataConfig.set("10.1.properties.durability", "&72300-2600");
/* 1223:1211 */       this.PlayerDataConfig.set("10.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1224:1212 */       this.PlayerDataConfig.set("10.1.properties.soulbound", Boolean.valueOf(true));
/* 1225:1213 */       this.PlayerDataConfig.set("10.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1226:1214 */       this.PlayerDataConfig.set("10.1.properties.xpLevel", "&b10-player+3");
/* 1227:     */       
/* 1228:1216 */       this.PlayerDataConfig.set("10.2.itemId", Integer.valueOf(315));
/* 1229:1217 */       this.PlayerDataConfig.set("10.2.nameColour", "&a&l");
/* 1230:1218 */       this.PlayerDataConfig.set("10.2.prefix", "random");
/* 1231:1219 */       this.PlayerDataConfig.set("10.2.suffix", "random");
/* 1232:1220 */       this.PlayerDataConfig.set("10.2.properties.armour", "&b3-7");
/* 1233:1221 */       this.PlayerDataConfig.set("10.2.properties.healthRandomApply", Boolean.valueOf(true));
/* 1234:1222 */       this.PlayerDataConfig.set("10.2.properties.health", "&b20-player+30");
/* 1235:1223 */       this.PlayerDataConfig.set("10.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1236:1224 */       this.PlayerDataConfig.set("10.2.properties.healthRegen", "&b2-5");
/* 1237:1225 */       this.PlayerDataConfig.set("10.2.properties.blockRandomApply", Boolean.valueOf(true));
/* 1238:1226 */       this.PlayerDataConfig.set("10.2.properties.block", "&b1-3");
/* 1239:1227 */       this.PlayerDataConfig.set("10.2.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1240:1228 */       this.PlayerDataConfig.set("10.2.properties.lifeSteal", "&22-5");
/* 1241:1229 */       this.PlayerDataConfig.set("10.2.properties.durability", "&72300-2600");
/* 1242:1230 */       this.PlayerDataConfig.set("10.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1243:1231 */       this.PlayerDataConfig.set("10.2.properties.soulbound", Boolean.valueOf(true));
/* 1244:1232 */       this.PlayerDataConfig.set("10.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1245:1233 */       this.PlayerDataConfig.set("10.2.properties.xpLevel", "&b10-player+3");
/* 1246:     */       
/* 1247:1235 */       this.PlayerDataConfig.set("10.3.itemId", Integer.valueOf(316));
/* 1248:1236 */       this.PlayerDataConfig.set("10.3.nameColour", "&a&l");
/* 1249:1237 */       this.PlayerDataConfig.set("10.3.prefix", "random");
/* 1250:1238 */       this.PlayerDataConfig.set("10.3.suffix", "random");
/* 1251:1239 */       this.PlayerDataConfig.set("10.3.properties.armour", "&b3-7");
/* 1252:1240 */       this.PlayerDataConfig.set("10.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1253:1241 */       this.PlayerDataConfig.set("10.3.properties.health", "&b20-player+30");
/* 1254:1242 */       this.PlayerDataConfig.set("10.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1255:1243 */       this.PlayerDataConfig.set("10.3.properties.healthRegen", "&b2-5");
/* 1256:1244 */       this.PlayerDataConfig.set("10.3.properties.blockRandomApply", Boolean.valueOf(true));
/* 1257:1245 */       this.PlayerDataConfig.set("10.3.properties.block", "&b1-3");
/* 1258:1246 */       this.PlayerDataConfig.set("10.3.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1259:1247 */       this.PlayerDataConfig.set("10.3.properties.lifeSteal", "&22-5");
/* 1260:1248 */       this.PlayerDataConfig.set("10.3.properties.durability", "&72300-2600");
/* 1261:1249 */       this.PlayerDataConfig.set("10.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1262:1250 */       this.PlayerDataConfig.set("10.3.properties.soulbound", Boolean.valueOf(true));
/* 1263:1251 */       this.PlayerDataConfig.set("10.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1264:1252 */       this.PlayerDataConfig.set("10.3.properties.xpLevel", "&b10-player+3");
/* 1265:     */       
/* 1266:1254 */       this.PlayerDataConfig.set("10.4.itemId", Integer.valueOf(317));
/* 1267:1255 */       this.PlayerDataConfig.set("10.4.nameColour", "&a&l");
/* 1268:1256 */       this.PlayerDataConfig.set("10.4.prefix", "random");
/* 1269:1257 */       this.PlayerDataConfig.set("10.4.suffix", "random");
/* 1270:1258 */       this.PlayerDataConfig.set("10.4.properties.armour", "&b3-7");
/* 1271:1259 */       this.PlayerDataConfig.set("10.4.properties.healthRandomApply", Boolean.valueOf(true));
/* 1272:1260 */       this.PlayerDataConfig.set("10.4.properties.health", "&b20-player+30");
/* 1273:1261 */       this.PlayerDataConfig.set("10.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1274:1262 */       this.PlayerDataConfig.set("10.4.properties.healthRegen", "&b2-5");
/* 1275:1263 */       this.PlayerDataConfig.set("10.4.properties.blockRandomApply", Boolean.valueOf(true));
/* 1276:1264 */       this.PlayerDataConfig.set("10.4.properties.block", "&b1-3");
/* 1277:1265 */       this.PlayerDataConfig.set("10.4.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1278:1266 */       this.PlayerDataConfig.set("10.4.properties.lifeSteal", "&22-5");
/* 1279:1267 */       this.PlayerDataConfig.set("10.4.properties.durability", "&72300-2600");
/* 1280:1268 */       this.PlayerDataConfig.set("10.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1281:1269 */       this.PlayerDataConfig.set("10.4.properties.soulbound", Boolean.valueOf(true));
/* 1282:1270 */       this.PlayerDataConfig.set("10.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1283:1271 */       this.PlayerDataConfig.set("10.4.properties.xpLevel", "&b10-player+3");
/* 1284:     */       
/* 1285:     */ 
/* 1286:1274 */       this.PlayerDataConfig.set("10.5.itemId", Integer.valueOf(283));
/* 1287:1275 */       this.PlayerDataConfig.set("10.5.nameColour", "&a&l");
/* 1288:1276 */       this.PlayerDataConfig.set("10.5.prefix", "random");
/* 1289:1277 */       this.PlayerDataConfig.set("10.5.suffix", "random");
/* 1290:1278 */       this.PlayerDataConfig.set("10.5.properties.weaponspeed", "&4Slow");
/* 1291:1279 */       this.PlayerDataConfig.set("10.5.properties.damage", "&b1-6-player+1-7");
/* 1292:1280 */       this.PlayerDataConfig.set("10.5.properties.witherRandomApply", Boolean.valueOf(true));
/* 1293:1281 */       this.PlayerDataConfig.set("10.5.properties.wither", "&72-6");
/* 1294:1282 */       this.PlayerDataConfig.set("10.5.properties.iceRandomApply", Boolean.valueOf(true));
/* 1295:1283 */       this.PlayerDataConfig.set("10.5.properties.ice", "&b1-5");
/* 1296:1284 */       this.PlayerDataConfig.set("10.5.properties.durability", "&7400-525");
/* 1297:1285 */       this.PlayerDataConfig.set("10.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1298:1286 */       this.PlayerDataConfig.set("10.5.properties.soulbound", Boolean.valueOf(true));
/* 1299:1287 */       this.PlayerDataConfig.set("10.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1300:1288 */       this.PlayerDataConfig.set("10.5.properties.xpLevel", "&b6-player+7");
/* 1301:     */       
/* 1302:1290 */       this.PlayerDataConfig.set("10.6.itemId", Integer.valueOf(286));
/* 1303:1291 */       this.PlayerDataConfig.set("10.6.nameColour", "&a&l");
/* 1304:1292 */       this.PlayerDataConfig.set("10.6.prefix", "random");
/* 1305:1293 */       this.PlayerDataConfig.set("10.6.suffix", "random");
/* 1306:1294 */       this.PlayerDataConfig.set("10.6.properties.weaponspeed", "&4Slow");
/* 1307:1295 */       this.PlayerDataConfig.set("10.6.properties.damage", "&b1-6-player+1-7");
/* 1308:1296 */       this.PlayerDataConfig.set("10.6.properties.witherRandomApply", Boolean.valueOf(true));
/* 1309:1297 */       this.PlayerDataConfig.set("10.6.properties.wither", "&72-6");
/* 1310:1298 */       this.PlayerDataConfig.set("10.6.properties.iceRandomApply", Boolean.valueOf(true));
/* 1311:1299 */       this.PlayerDataConfig.set("10.6.properties.ice", "&b1-5");
/* 1312:1300 */       this.PlayerDataConfig.set("10.6.properties.durability", "&7400-525");
/* 1313:1301 */       this.PlayerDataConfig.set("10.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1314:1302 */       this.PlayerDataConfig.set("10.6.properties.soulbound", Boolean.valueOf(true));
/* 1315:1303 */       this.PlayerDataConfig.set("10.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1316:1304 */       this.PlayerDataConfig.set("10.6.properties.xpLevel", "&b6-player+7");
/* 1317:     */       
/* 1318:1306 */       this.PlayerDataConfig.set("10.7.itemId", Integer.valueOf(285));
/* 1319:1307 */       this.PlayerDataConfig.set("10.7.nameColour", "&a&l");
/* 1320:1308 */       this.PlayerDataConfig.set("10.7.prefix", "random");
/* 1321:1309 */       this.PlayerDataConfig.set("10.7.suffix", "random");
/* 1322:1310 */       this.PlayerDataConfig.set("10.7.properties.weaponspeed", "&4Slow");
/* 1323:1311 */       this.PlayerDataConfig.set("10.7.properties.damage", "&b1-6-player+1-7");
/* 1324:1312 */       this.PlayerDataConfig.set("10.7.properties.witherRandomApply", Boolean.valueOf(true));
/* 1325:1313 */       this.PlayerDataConfig.set("10.7.properties.wither", "&72-6");
/* 1326:1314 */       this.PlayerDataConfig.set("10.7.properties.iceRandomApply", Boolean.valueOf(true));
/* 1327:1315 */       this.PlayerDataConfig.set("10.7.properties.ice", "&b1-5");
/* 1328:1316 */       this.PlayerDataConfig.set("10.7.properties.durability", "&7400-525");
/* 1329:1317 */       this.PlayerDataConfig.set("10.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1330:1318 */       this.PlayerDataConfig.set("10.7.properties.soulbound", Boolean.valueOf(true));
/* 1331:1319 */       this.PlayerDataConfig.set("10.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1332:1320 */       this.PlayerDataConfig.set("10.7.properties.xpLevel", "&b6-player+7");
/* 1333:     */       
/* 1334:1322 */       this.PlayerDataConfig.set("10.8.itemId", Integer.valueOf(261));
/* 1335:1323 */       this.PlayerDataConfig.set("10.8.nameColour", "&a&l");
/* 1336:1324 */       this.PlayerDataConfig.set("10.8.prefix", "random");
/* 1337:1325 */       this.PlayerDataConfig.set("10.8.suffix", "random");
/* 1338:1326 */       this.PlayerDataConfig.set("10.8.properties.weaponspeed", "&4Slow");
/* 1339:1327 */       this.PlayerDataConfig.set("10.8.properties.damage", "&b1-6-player+1-7");
/* 1340:1328 */       this.PlayerDataConfig.set("10.8.properties.witherRandomApply", Boolean.valueOf(true));
/* 1341:1329 */       this.PlayerDataConfig.set("10.8.properties.wither", "&72-6");
/* 1342:1330 */       this.PlayerDataConfig.set("10.8.properties.iceRandomApply", Boolean.valueOf(true));
/* 1343:1331 */       this.PlayerDataConfig.set("10.8.properties.ice", "&b1-5");
/* 1344:1332 */       this.PlayerDataConfig.set("10.8.properties.durability", "&7400-525");
/* 1345:1333 */       this.PlayerDataConfig.set("10.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1346:1334 */       this.PlayerDataConfig.set("10.8.properties.soulbound", Boolean.valueOf(true));
/* 1347:1335 */       this.PlayerDataConfig.set("10.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1348:1336 */       this.PlayerDataConfig.set("10.8.properties.xpLevel", "&b6-player+7");
/* 1349:     */       
/* 1350:1338 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1351:     */     }
/* 1352:     */     catch (Exception e)
/* 1353:     */     {
/* 1354:1341 */       e.printStackTrace();
/* 1355:1342 */       System.out.println("*********** Failed to save default witch file! ***********");
/* 1356:     */     }
/* 1357:     */   }
/* 1358:     */   
/* 1359:     */   public void writeWither()
/* 1360:     */   {
/* 1361:     */     try
/* 1362:     */     {
/* 1363:1350 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1364:1351 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "wither.yml");
/* 1365:     */       
/* 1366:1353 */       this.PlayerDataConfig.set("100", null);
/* 1367:1354 */       this.PlayerDataConfig.set("100.1.itemId", Integer.valueOf(279));
/* 1368:1355 */       this.PlayerDataConfig.set("100.1.nameColour", "&9&l");
/* 1369:1356 */       this.PlayerDataConfig.set("100.1.prefix", "random");
/* 1370:1357 */       this.PlayerDataConfig.set("100.1.suffix", "random");
/* 1371:1358 */       this.PlayerDataConfig.set("100.1.properties.damage", "&b1-6-player+1-7");
/* 1372:1359 */       this.PlayerDataConfig.set("100.1.properties.critChanceRandomApply", Boolean.valueOf(true));
/* 1373:1360 */       this.PlayerDataConfig.set("100.1.properties.critChance", "&b8-14");
/* 1374:1361 */       this.PlayerDataConfig.set("100.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/* 1375:1362 */       this.PlayerDataConfig.set("100.1.properties.critDamage", "&b14-23");
/* 1376:1363 */       this.PlayerDataConfig.set("100.1.properties.critDamageRandomApply", Boolean.valueOf(true));
/* 1377:1364 */       this.PlayerDataConfig.set("100.1.properties.critDamage", "&b3-9");
/* 1378:1365 */       this.PlayerDataConfig.set("100.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1379:1366 */       this.PlayerDataConfig.set("100.1.properties.health", "&b35-player+85");
/* 1380:1367 */       this.PlayerDataConfig.set("100.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1381:1368 */       this.PlayerDataConfig.set("100.1.properties.healthRegen", "&b3-5");
/* 1382:1369 */       this.PlayerDataConfig.set("100.1.properties.lifeStealRandomApply", Boolean.valueOf(true));
/* 1383:1370 */       this.PlayerDataConfig.set("100.1.properties.lifeSteal", "&22-5");
/* 1384:1371 */       this.PlayerDataConfig.set("100.1.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1385:1372 */       this.PlayerDataConfig.set("100.1.properties.poison", "&a3-5");
/* 1386:1373 */       this.PlayerDataConfig.set("100.1.properties.witherRandomApply", Boolean.valueOf(true));
/* 1387:1374 */       this.PlayerDataConfig.set("100.1.properties.wither", "&72-6");
/* 1388:1375 */       this.PlayerDataConfig.set("100.1.properties.witherRandomApply", Boolean.valueOf(true));
/* 1389:1376 */       this.PlayerDataConfig.set("100.1.properties.wither", "&72-6");
/* 1390:1377 */       this.PlayerDataConfig.set("100.1.properties.movementSpeedRandomApply", Boolean.valueOf(true));
/* 1391:1378 */       this.PlayerDataConfig.set("100.1.properties.movementSpeed", "&e2-4");
/* 1392:1379 */       this.PlayerDataConfig.set("100.1.properties.durability", "&71500-1950");
/* 1393:1380 */       this.PlayerDataConfig.set("100.1.properties.droppedXp", "75");
/* 1394:1381 */       this.PlayerDataConfig.set("100.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1395:1382 */       this.PlayerDataConfig.set("100.1.properties.soulbound", Boolean.valueOf(true));
/* 1396:1383 */       this.PlayerDataConfig.set("100.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1397:1384 */       this.PlayerDataConfig.set("100.1.properties.xpLevel", "&b3-player+2");
/* 1398:1385 */       this.PlayerDataConfig.set("100.1.properties.tntRandomApply", Boolean.valueOf(true));
/* 1399:1386 */       this.PlayerDataConfig.set("100.1.properties.spells.tnt", Boolean.valueOf(true));
/* 1400:     */       
/* 1401:1388 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1402:     */     }
/* 1403:     */     catch (Exception e)
/* 1404:     */     {
/* 1405:1391 */       e.printStackTrace();
/* 1406:1392 */       System.out.println("*********** Failed to save default wither file! ***********");
/* 1407:     */     }
/* 1408:     */   }
/* 1409:     */   
/* 1410:     */   public void writeZombie()
/* 1411:     */   {
/* 1412:     */     try
/* 1413:     */     {
/* 1414:1400 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1415:1401 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "zombie.yml");
/* 1416:     */       
/* 1417:     */ 
/* 1418:1404 */       this.PlayerDataConfig.set("15", null);
/* 1419:1405 */       this.PlayerDataConfig.set("15.1.itemId", Integer.valueOf(298));
/* 1420:1406 */       this.PlayerDataConfig.set("15.1.nameColour", "&f&l");
/* 1421:1407 */       this.PlayerDataConfig.set("15.1.prefix", "random");
/* 1422:1408 */       this.PlayerDataConfig.set("15.1.suffix", "random");
/* 1423:1409 */       this.PlayerDataConfig.set("15.1.properties.armour", "&b2-5");
/* 1424:1410 */       this.PlayerDataConfig.set("15.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1425:1411 */       this.PlayerDataConfig.set("15.1.properties.health", "&b10-player+30");
/* 1426:1412 */       this.PlayerDataConfig.set("15.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1427:1413 */       this.PlayerDataConfig.set("15.1.properties.healthRegen", "&b1-4");
/* 1428:1414 */       this.PlayerDataConfig.set("15.1.properties.durability", "&71350-1500");
/* 1429:1415 */       this.PlayerDataConfig.set("15.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1430:1416 */       this.PlayerDataConfig.set("15.1.properties.soulbound", Boolean.valueOf(true));
/* 1431:1417 */       this.PlayerDataConfig.set("15.1.properties.classRandomApply", Boolean.valueOf(true));
/* 1432:1418 */       this.PlayerDataConfig.set("15.1.properties.class", "random");
/* 1433:1419 */       this.PlayerDataConfig.set("15.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1434:1420 */       this.PlayerDataConfig.set("15.1.properties.xpLevel", "&b10-player+3");
/* 1435:     */       
/* 1436:1422 */       this.PlayerDataConfig.set("15.2.itemId", Integer.valueOf(299));
/* 1437:1423 */       this.PlayerDataConfig.set("15.2.nameColour", "&f&l");
/* 1438:1424 */       this.PlayerDataConfig.set("15.2.prefix", "random");
/* 1439:1425 */       this.PlayerDataConfig.set("15.2.suffix", "random");
/* 1440:1426 */       this.PlayerDataConfig.set("15.2.properties.armour", "&b2-5");
/* 1441:1427 */       this.PlayerDataConfig.set("15.2.properties.healthRandomApply", Boolean.valueOf(true));
/* 1442:1428 */       this.PlayerDataConfig.set("15.2.properties.health", "&b10-player+30");
/* 1443:1429 */       this.PlayerDataConfig.set("15.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1444:1430 */       this.PlayerDataConfig.set("15.2.properties.healthRegen", "&b1-4");
/* 1445:1431 */       this.PlayerDataConfig.set("15.2.properties.durability", "&71350-1500");
/* 1446:1432 */       this.PlayerDataConfig.set("15.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1447:1433 */       this.PlayerDataConfig.set("15.2.properties.soulbound", Boolean.valueOf(true));
/* 1448:1434 */       this.PlayerDataConfig.set("15.2.properties.classRandomApply", Boolean.valueOf(true));
/* 1449:1435 */       this.PlayerDataConfig.set("15.2.properties.class", "random");
/* 1450:1436 */       this.PlayerDataConfig.set("15.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1451:1437 */       this.PlayerDataConfig.set("15.2.properties.xpLevel", "&b10-player+3");
/* 1452:     */       
/* 1453:1439 */       this.PlayerDataConfig.set("15.3.itemId", Integer.valueOf(300));
/* 1454:1440 */       this.PlayerDataConfig.set("15.3.nameColour", "&f&l");
/* 1455:1441 */       this.PlayerDataConfig.set("15.3.prefix", "random");
/* 1456:1442 */       this.PlayerDataConfig.set("15.3.suffix", "random");
/* 1457:1443 */       this.PlayerDataConfig.set("15.3.properties.armour", "&b2-5");
/* 1458:1444 */       this.PlayerDataConfig.set("15.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1459:1445 */       this.PlayerDataConfig.set("15.3.properties.health", "&b10-player+30");
/* 1460:1446 */       this.PlayerDataConfig.set("15.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1461:1447 */       this.PlayerDataConfig.set("15.3.properties.healthRegen", "&b1-4");
/* 1462:1448 */       this.PlayerDataConfig.set("15.3.properties.durability", "&71350-1500");
/* 1463:1449 */       this.PlayerDataConfig.set("15.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1464:1450 */       this.PlayerDataConfig.set("15.3.properties.soulbound", Boolean.valueOf(true));
/* 1465:1451 */       this.PlayerDataConfig.set("15.3.properties.classRandomApply", Boolean.valueOf(true));
/* 1466:1452 */       this.PlayerDataConfig.set("15.3.properties.class", "random");
/* 1467:1453 */       this.PlayerDataConfig.set("15.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1468:1454 */       this.PlayerDataConfig.set("15.3.properties.xpLevel", "&b10-player+3");
/* 1469:     */       
/* 1470:1456 */       this.PlayerDataConfig.set("15.4.itemId", Integer.valueOf(301));
/* 1471:1457 */       this.PlayerDataConfig.set("15.4.nameColour", "&f&l");
/* 1472:1458 */       this.PlayerDataConfig.set("15.4.prefix", "random");
/* 1473:1459 */       this.PlayerDataConfig.set("15.4.suffix", "random");
/* 1474:1460 */       this.PlayerDataConfig.set("15.4.properties.armour", "&b2-5");
/* 1475:1461 */       this.PlayerDataConfig.set("15.4.properties.healthRandomApply", Boolean.valueOf(true));
/* 1476:1462 */       this.PlayerDataConfig.set("15.4.properties.health", "&b10-player+30");
/* 1477:1463 */       this.PlayerDataConfig.set("15.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1478:1464 */       this.PlayerDataConfig.set("15.4.properties.healthRegen", "&b1-4");
/* 1479:1465 */       this.PlayerDataConfig.set("15.4.properties.durability", "&71350-1500");
/* 1480:1466 */       this.PlayerDataConfig.set("15.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1481:1467 */       this.PlayerDataConfig.set("15.4.properties.soulbound", Boolean.valueOf(true));
/* 1482:1468 */       this.PlayerDataConfig.set("15.4.properties.classRandomApply", Boolean.valueOf(true));
/* 1483:1469 */       this.PlayerDataConfig.set("15.4.properties.class", "random");
/* 1484:1470 */       this.PlayerDataConfig.set("15.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1485:1471 */       this.PlayerDataConfig.set("15.4.properties.xpLevel", "&b10-player+3");
/* 1486:     */       
/* 1487:     */ 
/* 1488:1474 */       this.PlayerDataConfig.set("15.5.itemId", Integer.valueOf(268));
/* 1489:1475 */       this.PlayerDataConfig.set("15.5.nameColour", "&f&l");
/* 1490:1476 */       this.PlayerDataConfig.set("15.5.prefix", "random");
/* 1491:1477 */       this.PlayerDataConfig.set("15.5.suffix", "random");
/* 1492:1478 */       this.PlayerDataConfig.set("15.5.properties.weaponspeed", "&4Slow");
/* 1493:1479 */       this.PlayerDataConfig.set("15.5.properties.damage", "&b1-6-player+1-7");
/* 1494:1480 */       this.PlayerDataConfig.set("15.5.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1495:1481 */       this.PlayerDataConfig.set("15.5.properties.poison", "&a1-5");
/* 1496:1482 */       this.PlayerDataConfig.set("15.5.properties.witherRandomApply", Boolean.valueOf(true));
/* 1497:1483 */       this.PlayerDataConfig.set("15.5.properties.wither", "&71-5");
/* 1498:1484 */       this.PlayerDataConfig.set("15.5.properties.durability", "&7200-325");
/* 1499:1485 */       this.PlayerDataConfig.set("15.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1500:1486 */       this.PlayerDataConfig.set("15.5.properties.soulbound", Boolean.valueOf(true));
/* 1501:1487 */       this.PlayerDataConfig.set("15.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1502:1488 */       this.PlayerDataConfig.set("15.5.properties.xpLevel", "&b10-player+3");
/* 1503:     */       
/* 1504:1490 */       this.PlayerDataConfig.set("15.6.itemId", Integer.valueOf(271));
/* 1505:1491 */       this.PlayerDataConfig.set("15.6.nameColour", "&f&l");
/* 1506:1492 */       this.PlayerDataConfig.set("15.6.prefix", "random");
/* 1507:1493 */       this.PlayerDataConfig.set("15.6.suffix", "random");
/* 1508:1494 */       this.PlayerDataConfig.set("15.6.properties.weaponspeed", "&4Slow");
/* 1509:1495 */       this.PlayerDataConfig.set("15.6.properties.damage", "&b1-6-player+1-7");
/* 1510:1496 */       this.PlayerDataConfig.set("15.6.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1511:1497 */       this.PlayerDataConfig.set("15.6.properties.poison", "&a1-5");
/* 1512:1498 */       this.PlayerDataConfig.set("15.6.properties.witherRandomApply", Boolean.valueOf(true));
/* 1513:1499 */       this.PlayerDataConfig.set("15.6.properties.wither", "&71-5");
/* 1514:1500 */       this.PlayerDataConfig.set("15.6.properties.durability", "&7200-325");
/* 1515:1501 */       this.PlayerDataConfig.set("15.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1516:1502 */       this.PlayerDataConfig.set("15.6.properties.soulbound", Boolean.valueOf(true));
/* 1517:1503 */       this.PlayerDataConfig.set("15.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1518:1504 */       this.PlayerDataConfig.set("15.6.properties.xpLevel", "&b10-player+3");
/* 1519:     */       
/* 1520:1506 */       this.PlayerDataConfig.set("15.7.itemId", Integer.valueOf(270));
/* 1521:1507 */       this.PlayerDataConfig.set("15.7.nameColour", "&f&l");
/* 1522:1508 */       this.PlayerDataConfig.set("15.7.prefix", "random");
/* 1523:1509 */       this.PlayerDataConfig.set("15.7.suffix", "random");
/* 1524:1510 */       this.PlayerDataConfig.set("15.7.properties.weaponspeed", "&4Slow");
/* 1525:1511 */       this.PlayerDataConfig.set("15.7.properties.damage", "&b1-6-player+1-7");
/* 1526:1512 */       this.PlayerDataConfig.set("15.7.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1527:1513 */       this.PlayerDataConfig.set("15.7.properties.poison", "&a1-5");
/* 1528:1514 */       this.PlayerDataConfig.set("15.7.properties.witherRandomApply", Boolean.valueOf(true));
/* 1529:1515 */       this.PlayerDataConfig.set("15.7.properties.wither", "&71-5");
/* 1530:1516 */       this.PlayerDataConfig.set("15.7.properties.durability", "&7200-325");
/* 1531:1517 */       this.PlayerDataConfig.set("15.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1532:1518 */       this.PlayerDataConfig.set("15.7.properties.soulbound", Boolean.valueOf(true));
/* 1533:1519 */       this.PlayerDataConfig.set("15.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1534:1520 */       this.PlayerDataConfig.set("15.7.properties.xpLevel", "&b10-player+3");
/* 1535:     */       
/* 1536:1522 */       this.PlayerDataConfig.set("15.8.itemId", Integer.valueOf(261));
/* 1537:1523 */       this.PlayerDataConfig.set("15.8.nameColour", "&f&l");
/* 1538:1524 */       this.PlayerDataConfig.set("15.8.prefix", "random");
/* 1539:1525 */       this.PlayerDataConfig.set("15.8.suffix", "random");
/* 1540:1526 */       this.PlayerDataConfig.set("15.8.properties.weaponspeed", "&4Slow");
/* 1541:1527 */       this.PlayerDataConfig.set("15.8.properties.damage", "&b1-6-player+1-7");
/* 1542:1528 */       this.PlayerDataConfig.set("15.8.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1543:1529 */       this.PlayerDataConfig.set("15.8.properties.poison", "&a1-5");
/* 1544:1530 */       this.PlayerDataConfig.set("15.8.properties.witherRandomApply", Boolean.valueOf(true));
/* 1545:1531 */       this.PlayerDataConfig.set("15.8.properties.wither", "&71-5");
/* 1546:1532 */       this.PlayerDataConfig.set("15.8.properties.durability", "&7200-325");
/* 1547:1533 */       this.PlayerDataConfig.set("15.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1548:1534 */       this.PlayerDataConfig.set("15.8.properties.soulbound", Boolean.valueOf(true));
/* 1549:1535 */       this.PlayerDataConfig.set("15.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1550:1536 */       this.PlayerDataConfig.set("15.8.properties.xpLevel", "&b10-player+3");
/* 1551:     */       
/* 1552:1538 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1553:     */     }
/* 1554:     */     catch (Exception e)
/* 1555:     */     {
/* 1556:1541 */       e.printStackTrace();
/* 1557:1542 */       System.out.println("*********** Failed to save default zombie file! ***********");
/* 1558:     */     }
/* 1559:     */   }
/* 1560:     */   
/* 1561:     */   public void writePig_Zombie()
/* 1562:     */   {
/* 1563:     */     try
/* 1564:     */     {
/* 1565:1550 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1566:1551 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedMobs" + File.separator + "pig_zombie.yml");
/* 1567:     */       
/* 1568:     */ 
/* 1569:1554 */       this.PlayerDataConfig.set("15", null);
/* 1570:1555 */       this.PlayerDataConfig.set("15.1.itemId", Integer.valueOf(298));
/* 1571:1556 */       this.PlayerDataConfig.set("15.1.nameColour", "&f&l");
/* 1572:1557 */       this.PlayerDataConfig.set("15.1.prefix", "random");
/* 1573:1558 */       this.PlayerDataConfig.set("15.1.suffix", "random");
/* 1574:1559 */       this.PlayerDataConfig.set("15.1.properties.armour", "&b2-5");
/* 1575:1560 */       this.PlayerDataConfig.set("15.1.properties.healthRandomApply", Boolean.valueOf(true));
/* 1576:1561 */       this.PlayerDataConfig.set("15.1.properties.health", "&b10-player+30");
/* 1577:1562 */       this.PlayerDataConfig.set("15.1.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1578:1563 */       this.PlayerDataConfig.set("15.1.properties.healthRegen", "&b1-4");
/* 1579:1564 */       this.PlayerDataConfig.set("15.1.properties.durability", "&71350-1500");
/* 1580:1565 */       this.PlayerDataConfig.set("15.1.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1581:1566 */       this.PlayerDataConfig.set("15.1.properties.soulbound", Boolean.valueOf(true));
/* 1582:1567 */       this.PlayerDataConfig.set("15.1.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1583:1568 */       this.PlayerDataConfig.set("15.1.properties.xpLevel", "&b10-player+3");
/* 1584:     */       
/* 1585:1570 */       this.PlayerDataConfig.set("15.2.itemId", Integer.valueOf(299));
/* 1586:1571 */       this.PlayerDataConfig.set("15.2.nameColour", "&f&l");
/* 1587:1572 */       this.PlayerDataConfig.set("15.2.prefix", "random");
/* 1588:1573 */       this.PlayerDataConfig.set("15.2.suffix", "random");
/* 1589:1574 */       this.PlayerDataConfig.set("15.2.properties.armour", "&b2-5");
/* 1590:1575 */       this.PlayerDataConfig.set("15.2.properties.healthRandomApply", Boolean.valueOf(true));
/* 1591:1576 */       this.PlayerDataConfig.set("15.2.properties.health", "&b10-player+30");
/* 1592:1577 */       this.PlayerDataConfig.set("15.2.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1593:1578 */       this.PlayerDataConfig.set("15.2.properties.healthRegen", "&b1-4");
/* 1594:1579 */       this.PlayerDataConfig.set("15.2.properties.durability", "&71350-1500");
/* 1595:1580 */       this.PlayerDataConfig.set("15.2.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1596:1581 */       this.PlayerDataConfig.set("15.2.properties.soulbound", Boolean.valueOf(true));
/* 1597:1582 */       this.PlayerDataConfig.set("15.2.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1598:1583 */       this.PlayerDataConfig.set("15.2.properties.xpLevel", "&b10-player+3");
/* 1599:     */       
/* 1600:1585 */       this.PlayerDataConfig.set("15.3.itemId", Integer.valueOf(300));
/* 1601:1586 */       this.PlayerDataConfig.set("15.3.nameColour", "&f&l");
/* 1602:1587 */       this.PlayerDataConfig.set("15.3.prefix", "random");
/* 1603:1588 */       this.PlayerDataConfig.set("15.3.suffix", "random");
/* 1604:1589 */       this.PlayerDataConfig.set("15.3.properties.armour", "&b2-5");
/* 1605:1590 */       this.PlayerDataConfig.set("15.3.properties.healthRandomApply", Boolean.valueOf(true));
/* 1606:1591 */       this.PlayerDataConfig.set("15.3.properties.health", "&b10-player+30");
/* 1607:1592 */       this.PlayerDataConfig.set("15.3.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1608:1593 */       this.PlayerDataConfig.set("15.3.properties.healthRegen", "&b1-4");
/* 1609:1594 */       this.PlayerDataConfig.set("15.3.properties.durability", "&71350-1500");
/* 1610:1595 */       this.PlayerDataConfig.set("15.3.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1611:1596 */       this.PlayerDataConfig.set("15.3.properties.soulbound", Boolean.valueOf(true));
/* 1612:1597 */       this.PlayerDataConfig.set("15.3.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1613:1598 */       this.PlayerDataConfig.set("15.3.properties.xpLevel", "&b10-player+3");
/* 1614:     */       
/* 1615:1600 */       this.PlayerDataConfig.set("15.4.itemId", Integer.valueOf(301));
/* 1616:1601 */       this.PlayerDataConfig.set("15.4.nameColour", "&f&l");
/* 1617:1602 */       this.PlayerDataConfig.set("15.4.prefix", "random");
/* 1618:1603 */       this.PlayerDataConfig.set("15.4.suffix", "random");
/* 1619:1604 */       this.PlayerDataConfig.set("15.4.properties.armour", "&b2-5");
/* 1620:1605 */       this.PlayerDataConfig.set("15.4.properties.healthRandomApply", Boolean.valueOf(true));
/* 1621:1606 */       this.PlayerDataConfig.set("15.4.properties.health", "&b10-player+30");
/* 1622:1607 */       this.PlayerDataConfig.set("15.4.properties.healthRegenRandomApply", Boolean.valueOf(true));
/* 1623:1608 */       this.PlayerDataConfig.set("15.4.properties.healthRegen", "&b1-4");
/* 1624:1609 */       this.PlayerDataConfig.set("15.4.properties.durability", "&71350-1500");
/* 1625:1610 */       this.PlayerDataConfig.set("15.4.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1626:1611 */       this.PlayerDataConfig.set("15.4.properties.soulbound", Boolean.valueOf(true));
/* 1627:1612 */       this.PlayerDataConfig.set("15.4.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1628:1613 */       this.PlayerDataConfig.set("15.4.properties.xpLevel", "&b10-player+3");
/* 1629:     */       
/* 1630:     */ 
/* 1631:1616 */       this.PlayerDataConfig.set("15.5.itemId", Integer.valueOf(268));
/* 1632:1617 */       this.PlayerDataConfig.set("15.5.nameColour", "&f&l");
/* 1633:1618 */       this.PlayerDataConfig.set("15.5.prefix", "random");
/* 1634:1619 */       this.PlayerDataConfig.set("15.5.suffix", "random");
/* 1635:1620 */       this.PlayerDataConfig.set("15.5.properties.weaponspeed", "&4Slow");
/* 1636:1621 */       this.PlayerDataConfig.set("15.5.properties.damage", "&b1-6-player+1-7");
/* 1637:1622 */       this.PlayerDataConfig.set("15.5.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1638:1623 */       this.PlayerDataConfig.set("15.5.properties.poison", "&a1-5");
/* 1639:1624 */       this.PlayerDataConfig.set("15.5.properties.witherRandomApply", Boolean.valueOf(true));
/* 1640:1625 */       this.PlayerDataConfig.set("15.5.properties.wither", "&71-5");
/* 1641:1626 */       this.PlayerDataConfig.set("15.5.properties.durability", "&7200-325");
/* 1642:1627 */       this.PlayerDataConfig.set("15.5.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1643:1628 */       this.PlayerDataConfig.set("15.5.properties.soulbound", Boolean.valueOf(true));
/* 1644:1629 */       this.PlayerDataConfig.set("15.5.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1645:1630 */       this.PlayerDataConfig.set("15.5.properties.xpLevel", "&b10-player+3");
/* 1646:     */       
/* 1647:1632 */       this.PlayerDataConfig.set("15.6.itemId", Integer.valueOf(271));
/* 1648:1633 */       this.PlayerDataConfig.set("15.6.nameColour", "&f&l");
/* 1649:1634 */       this.PlayerDataConfig.set("15.6.prefix", "random");
/* 1650:1635 */       this.PlayerDataConfig.set("15.6.suffix", "random");
/* 1651:1636 */       this.PlayerDataConfig.set("15.6.properties.weaponspeed", "&4Slow");
/* 1652:1637 */       this.PlayerDataConfig.set("15.6.properties.damage", "&b1-6-player+1-7");
/* 1653:1638 */       this.PlayerDataConfig.set("15.6.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1654:1639 */       this.PlayerDataConfig.set("15.6.properties.poison", "&a1-5");
/* 1655:1640 */       this.PlayerDataConfig.set("15.6.properties.witherRandomApply", Boolean.valueOf(true));
/* 1656:1641 */       this.PlayerDataConfig.set("15.6.properties.wither", "&71-5");
/* 1657:1642 */       this.PlayerDataConfig.set("15.6.properties.durability", "&7200-325");
/* 1658:1643 */       this.PlayerDataConfig.set("15.6.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1659:1644 */       this.PlayerDataConfig.set("15.6.properties.soulbound", Boolean.valueOf(true));
/* 1660:1645 */       this.PlayerDataConfig.set("15.6.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1661:1646 */       this.PlayerDataConfig.set("15.6.properties.xpLevel", "&b10-player+3");
/* 1662:     */       
/* 1663:1648 */       this.PlayerDataConfig.set("15.7.itemId", Integer.valueOf(270));
/* 1664:1649 */       this.PlayerDataConfig.set("15.7.nameColour", "&f&l");
/* 1665:1650 */       this.PlayerDataConfig.set("15.7.prefix", "random");
/* 1666:1651 */       this.PlayerDataConfig.set("15.7.suffix", "random");
/* 1667:1652 */       this.PlayerDataConfig.set("15.7.properties.weaponspeed", "&4Slow");
/* 1668:1653 */       this.PlayerDataConfig.set("15.7.properties.damage", "&b1-6-player+1-7");
/* 1669:1654 */       this.PlayerDataConfig.set("15.7.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1670:1655 */       this.PlayerDataConfig.set("15.7.properties.poison", "&a1-5");
/* 1671:1656 */       this.PlayerDataConfig.set("15.7.properties.witherRandomApply", Boolean.valueOf(true));
/* 1672:1657 */       this.PlayerDataConfig.set("15.7.properties.wither", "&71-5");
/* 1673:1658 */       this.PlayerDataConfig.set("15.7.properties.durability", "&7200-325");
/* 1674:1659 */       this.PlayerDataConfig.set("15.7.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1675:1660 */       this.PlayerDataConfig.set("15.7.properties.soulbound", Boolean.valueOf(true));
/* 1676:1661 */       this.PlayerDataConfig.set("15.7.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1677:1662 */       this.PlayerDataConfig.set("15.7.properties.xpLevel", "&b10-player+3");
/* 1678:     */       
/* 1679:1664 */       this.PlayerDataConfig.set("15.8.itemId", Integer.valueOf(261));
/* 1680:1665 */       this.PlayerDataConfig.set("15.8.nameColour", "&f&l");
/* 1681:1666 */       this.PlayerDataConfig.set("15.8.prefix", "random");
/* 1682:1667 */       this.PlayerDataConfig.set("15.8.suffix", "random");
/* 1683:1668 */       this.PlayerDataConfig.set("15.8.properties.weaponspeed", "&4Slow");
/* 1684:1669 */       this.PlayerDataConfig.set("15.8.properties.damage", "&b1-6-player+1-7");
/* 1685:1670 */       this.PlayerDataConfig.set("15.8.properties.poisonRandomApply", Boolean.valueOf(true));
/* 1686:1671 */       this.PlayerDataConfig.set("15.8.properties.poison", "&a1-5");
/* 1687:1672 */       this.PlayerDataConfig.set("15.8.properties.witherRandomApply", Boolean.valueOf(true));
/* 1688:1673 */       this.PlayerDataConfig.set("15.8.properties.wither", "&71-5");
/* 1689:1674 */       this.PlayerDataConfig.set("15.8.properties.durability", "&7200-325");
/* 1690:1675 */       this.PlayerDataConfig.set("15.8.properties.soulboundRandomApply", Boolean.valueOf(true));
/* 1691:1676 */       this.PlayerDataConfig.set("15.8.properties.soulbound", Boolean.valueOf(true));
/* 1692:1677 */       this.PlayerDataConfig.set("15.8.properties.xpLevelRandomApply", Boolean.valueOf(true));
/* 1693:1678 */       this.PlayerDataConfig.set("15.8.properties.xpLevel", "&b10-player+3");
/* 1694:     */       
/* 1695:1680 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1696:     */     }
/* 1697:     */     catch (Exception e)
/* 1698:     */     {
/* 1699:1683 */       e.printStackTrace();
/* 1700:1684 */       System.out.println("*********** Failed to save default pig_zombie file! ***********");
/* 1701:     */     }
/* 1702:     */   }
/* 1703:     */   
/* 1704:     */   public void writeSetBonuses()
/* 1705:     */   {
/* 1706:     */     try
/* 1707:     */     {
/* 1708:1692 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1709:1693 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "SavedItems" + File.separator + "SetBonuses.yml");
/* 1710:     */       
/* 1711:1695 */       this.PlayerDataConfig.set("Sets", null);
/* 1712:     */       
/* 1713:     */ 
/* 1714:     */ 
/* 1715:1699 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1716:     */     }
/* 1717:     */     catch (Exception e)
/* 1718:     */     {
/* 1719:1702 */       e.printStackTrace();
/* 1720:1703 */       System.out.println("*********** Failed to save default SetBonuses file! ***********");
/* 1721:     */     }
/* 1722:     */   }
/* 1723:     */   
/* 1724:     */   public void writeLanguageEN()
/* 1725:     */   {
/* 1726:     */     try
/* 1727:     */     {
/* 1728:1711 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1729:1712 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-en.yml");
/* 1730:     */       
/* 1731:1714 */       this.PlayerDataConfig.set("ErrorMessages", null);
/* 1732:1715 */       this.PlayerDataConfig.set("ErrorMessages.PermissionDeniedError", "&cYou do not have permission to perform that command.");
/* 1733:1716 */       this.PlayerDataConfig.set("ErrorMessages.IngameOnlyError", "You can only run this command in-game!");
/* 1734:1717 */       this.PlayerDataConfig.set("ErrorMessages.DoesntExistError", "&cdoes not exist!");
/* 1735:1718 */       this.PlayerDataConfig.set("ErrorMessages.PlayerNotOnlineError", "&cis not online!");
/* 1736:1719 */       this.PlayerDataConfig.set("ErrorMessages.NotEnoughSpaceError", "&cdoes not have enough space in their inventory.");
/* 1737:1720 */       this.PlayerDataConfig.set("ErrorMessages.IncludeItemNameError", "&cYou need to include an item name!");
/* 1738:1721 */       this.PlayerDataConfig.set("ErrorMessages.EnterPlayerNameError", "&cYou need to enter a players name!");
/* 1739:1722 */       this.PlayerDataConfig.set("ErrorMessages.ItemAlreadyExistsError", "&cThat item already exists. Please choose a different name for the item.");
/* 1740:1723 */       this.PlayerDataConfig.set("ErrorMessages.NullItemInHandError", "&cYou need to be holding an item to use this command.");
/* 1741:1724 */       this.PlayerDataConfig.set("ErrorMessages.NoLoreError", "&cThat item doesn't contain any lore.");
/* 1742:1725 */       this.PlayerDataConfig.set("ClassRequirementMessages", null);
/* 1743:1726 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1744:1727 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1745:1728 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1746:1729 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1747:1730 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.ItemInHand", "&cYou dropped the item in your hand as you had no inventory slots free and you no longer have the required class to hold it.");
/* 1748:1731 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Head", "&cYou are not the required class to wear your Helmet.");
/* 1749:1732 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Chest", "&cYou are not the required class to wear your Chestplate.");
/* 1750:1733 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Legs", "&cYou are not the required class to wear your Pants.");
/* 1751:1734 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Boots", "&cYou are not the required class to wear your Boots.");
/* 1752:1735 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.ItemInHand", "&cYou are not the required class to hold that item.");
/* 1753:1736 */       this.PlayerDataConfig.set("LevelRequirementMessages", null);
/* 1754:1737 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and you no longer have the required level to wear it.");
/* 1755:1738 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and you no longer have the required level to wear it.");
/* 1756:1739 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and you no longer have the required level to wear it.");
/* 1757:1740 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and you no longer have the required level to wear it.");
/* 1758:1741 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.ItemInHand", "&cYou dropped the item in your hand as you had no inventory slots free and you no longer have the required level to hold it.");
/* 1759:1742 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Head", "&cYou do not have the required level to wear your Helmet.");
/* 1760:1743 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Chest", "&cYou do not have the required level to wear your Chestplate.");
/* 1761:1744 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Legs", "&cYou do not have the required level to wear your Pants.");
/* 1762:1745 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Boots", "&cYou do not have the required level to wear your Boots.");
/* 1763:1746 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.ItemInHand", "&cYou do not have the required level to hold that item.");
/* 1764:1747 */       this.PlayerDataConfig.set("DamageMessages", null);
/* 1765:1748 */       this.PlayerDataConfig.set("DamageMessages.CriticalStrikeSuccess", "&aCritical Strike!");
/* 1766:1749 */       this.PlayerDataConfig.set("DamageMessages.EnemyCriticalStrikeSuccess", "&ccrit you!");
/* 1767:1750 */       this.PlayerDataConfig.set("DamageMessages.DodgeSuccess", "&aDodge!");
/* 1768:1751 */       this.PlayerDataConfig.set("DamageMessages.EnemyDodgeSuccess", "&cdodged your attack!");
/* 1769:1752 */       this.PlayerDataConfig.set("DamageMessages.BlockSuccess", "&aBlocked!");
/* 1770:1753 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlockSuccess", "&cblocked your attack!");
/* 1771:1754 */       this.PlayerDataConfig.set("DamageMessages.LifeStealSuccess", "&aLife stolen!");
/* 1772:1755 */       this.PlayerDataConfig.set("DamageMessages.EnemyLifeStealSuccess", "&cstole some of your life!");
/* 1773:1756 */       this.PlayerDataConfig.set("DamageMessages.ReflectSuccess", "&aReflected!");
/* 1774:1757 */       this.PlayerDataConfig.set("DamageMessages.EnemyReflectSuccess", "&creflected your attack!");
/* 1775:1758 */       this.PlayerDataConfig.set("DamageMessages.FireSuccess", "&aTarget set alight!");
/* 1776:1759 */       this.PlayerDataConfig.set("DamageMessages.EnemyFireSuccess", "&cset you on fire!");
/* 1777:1760 */       this.PlayerDataConfig.set("DamageMessages.IceSuccess", "&aTarget slowed!");
/* 1778:1761 */       this.PlayerDataConfig.set("DamageMessages.EnemyIceSuccess", "&cslowed you!");
/* 1779:1762 */       this.PlayerDataConfig.set("DamageMessages.PoisonSuccess", "&aTarget poisoned!");
/* 1780:1763 */       this.PlayerDataConfig.set("DamageMessages.EnemyPoisonSuccess", "&cpoisoned you!");
/* 1781:1764 */       this.PlayerDataConfig.set("DamageMessages.WitherSuccess", "&aTarget withered!");
/* 1782:1765 */       this.PlayerDataConfig.set("DamageMessages.EnemyWitherSuccess", "&cwithered you!");
/* 1783:1766 */       this.PlayerDataConfig.set("DamageMessages.HarmSuccess", "&aTarget harmed!");
/* 1784:1767 */       this.PlayerDataConfig.set("DamageMessages.EnemyHarmSuccess", "&charmed you!");
/* 1785:1768 */       this.PlayerDataConfig.set("DamageMessages.BlindSuccess", "&aTarget blinded!");
/* 1786:1769 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlindSuccess", "&cblinded you!");
/* 1787:1770 */       this.PlayerDataConfig.set("SoulboundMessages", null);
/* 1788:1771 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and this item is bound to");
/* 1789:1772 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and this item is bound to");
/* 1790:1773 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and this item is bound to");
/* 1791:1774 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and this item is bound to");
/* 1792:1775 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.ItemInHand", "&cYou dropped item in your hand as you had no inventory slots free and this item is bound to");
/* 1793:1776 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Head", "&cThis Helmet is bound to");
/* 1794:1777 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Chest", "&cThis Chestplate is bound to");
/* 1795:1778 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Legs", "&cThese Leggings are bound to");
/* 1796:1779 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Boots", "&cThese Boots are bound to");
/* 1797:1780 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.ItemInHand", "&cThis item in your hand is bound to");
/* 1798:1781 */       this.PlayerDataConfig.set("SpellMessages", null);
/* 1799:1782 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.ItemInHand", "&eRight-click to cast");
/* 1800:1783 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.CooldownRemaining", "&dseconds remaining.");
/* 1801:1784 */       this.PlayerDataConfig.set("RepairMessages", null);
/* 1802:1785 */       this.PlayerDataConfig.set("RepairMessages.RepairSuccessful", "&aSuccessfully repaired");
/* 1803:1786 */       this.PlayerDataConfig.set("RepairMessages.DoesntNeedRepair", "&cdoesn't need repairing.");
/* 1804:1787 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughLeather", "&cNot enough Leather to repair");
/* 1805:1788 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cNot enough Wood to repair");
/* 1806:1789 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCobblestone", "&cNot enough Cobblestone to repair");
/* 1807:1790 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughIron", "&cNot enough Iron Ingots to repair");
/* 1808:1791 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughGold", "&cNot enough Gold Ingots to repair");
/* 1809:1792 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughDiamond", "&cNot enough Diamonds to repair");
/* 1810:1793 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughSticks", "&cNot enough Sticks to repair");
/* 1811:1794 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFlint", "&cNot enough Flint to repair");
/* 1812:1795 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cNot enough Wooden Planks to repair");
/* 1813:1796 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughString", "&cNot enough String to repair");
/* 1814:1797 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFishingRod", "&cYou need a Fishing Rod to repair");
/* 1815:1798 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCarrots", "&cYou need a Carrot to repair");
/* 1816:     */       
/* 1817:1800 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1818:     */     }
/* 1819:     */     catch (Exception e)
/* 1820:     */     {
/* 1821:1803 */       e.printStackTrace();
/* 1822:1804 */       System.out.println("*********** Failed to save default language-EN file! ***********");
/* 1823:     */     }
/* 1824:     */   }
/* 1825:     */   
/* 1826:     */   public void writeLanguageDE()
/* 1827:     */   {
/* 1828:     */     try
/* 1829:     */     {
/* 1830:1810 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1831:1811 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-de.yml");
/* 1832:     */       
/* 1833:1813 */       this.PlayerDataConfig.set("ErrorMessages", null);
/* 1834:1814 */       this.PlayerDataConfig.set("ErrorMessages.PermissionDeniedError", "&cDu hast keine Berechtigung dies zu tun.");
/* 1835:1815 */       this.PlayerDataConfig.set("ErrorMessages.IngameOnlyError", "Befehl nur In-Game ausfuehrbar!");
/* 1836:1816 */       this.PlayerDataConfig.set("ErrorMessages.DoesntExistError", "&cexistiert nicht!");
/* 1837:1817 */       this.PlayerDataConfig.set("ErrorMessages.PlayerNotOnlineError", "&cist nicht online!");
/* 1838:1818 */       this.PlayerDataConfig.set("ErrorMessages.NotEnoughSpaceError", "&chat nicht genug Platz im Inventar.");
/* 1839:1819 */       this.PlayerDataConfig.set("ErrorMessages.IncludeItemNameError", "&cDu musst den Namen des Items angeben!");
/* 1840:1820 */       this.PlayerDataConfig.set("ErrorMessages.EnterPlayerNameError", "&cDu musst einen Spielernamen angeben!");
/* 1841:1821 */       this.PlayerDataConfig.set("ErrorMessages.ItemAlreadyExistsError", "&cDieses Item gibt es bereits. Bitte benutze einen anderen Namen fuer das Item.");
/* 1842:1822 */       this.PlayerDataConfig.set("ErrorMessages.NullItemInHandError", "&cDu musst ein Item in der Hand halten um dies zu tun.");
/* 1843:1823 */       this.PlayerDataConfig.set("ErrorMessages.NoLoreError", "&cThat item doesn't contain any lore.");
/* 1844:1824 */       this.PlayerDataConfig.set("ClassRequirementMessages", null);
/* 1845:1825 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1846:1826 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1847:1827 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1848:1828 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1849:1829 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.ItemInHand", "&cYou dropped the item in your hand as you had no inventory slots free and you no longer have the required class to hold it.");
/* 1850:1830 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Head", "&cYou are not the required class to wear your Helmet.");
/* 1851:1831 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Chest", "&cYou are not the required class to wear your Chestplate.");
/* 1852:1832 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Legs", "&cYou are not the required class to wear your Pants.");
/* 1853:1833 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Boots", "&cYou are not the required class to wear your Boots.");
/* 1854:1834 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.ItemInHand", "&cYou are not the required class to hold that item.");
/* 1855:1835 */       this.PlayerDataConfig.set("LevelRequirementMessages", null);
/* 1856:1836 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Head", "&cDu hast deinen Helm fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast");
/* 1857:1837 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Chest", "&cDu hast deinen Brustpanzer fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast.");
/* 1858:1838 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Legs", "&cDu hast deine Hose fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast.");
/* 1859:1839 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Boots", "&cDu hast deine Schuhe fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast.");
/* 1860:1840 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.ItemInHand", "&cDu hast das Item fallengelassen, da du nicht das benoetigte Level besitzt und du keinen freien Inventar Slot mehr hast.");
/* 1861:1841 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Head", "&cDu besitzt nicht das Mindestlevel um den Helm zu tragen.");
/* 1862:1842 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Chest", "&cDu besitzt nicht das Mindestlevel um den Brustpanzer zu tragen.");
/* 1863:1843 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Legs", "&cDu besitzt nicht das Mindestlevel um die Hose zu tragen.");
/* 1864:1844 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Boots", "&cDu besitzt nicht das Mindestlevel um die Schuhe zu tragen.");
/* 1865:1845 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.ItemInHand", "&cDu besitzt nicht das Mindestlevel um dieses Item zu halten.");
/* 1866:1846 */       this.PlayerDataConfig.set("DamageMessages", null);
/* 1867:1847 */       this.PlayerDataConfig.set("DamageMessages.CriticalStrikeSuccess", "&aKritischer Treffer!");
/* 1868:1848 */       this.PlayerDataConfig.set("DamageMessages.EnemyCriticalStrikeSuccess", "&cgegner traf kritisch!");
/* 1869:1849 */       this.PlayerDataConfig.set("DamageMessages.DodgeSuccess", "&aAusgewichen!");
/* 1870:1850 */       this.PlayerDataConfig.set("DamageMessages.EnemyDodgeSuccess", "&cgegner ist ausgewichen!");
/* 1871:1851 */       this.PlayerDataConfig.set("DamageMessages.BlockSuccess", "&aAbgeblockt!");
/* 1872:1852 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlockSuccess", "&cgegner hat Angriff geblockt!");
/* 1873:1853 */       this.PlayerDataConfig.set("DamageMessages.LifeStealSuccess", "&aLeben gestohlen!");
/* 1874:1854 */       this.PlayerDataConfig.set("DamageMessages.EnemyLifeStealSuccess", "&cdir wurde Leben gestohlen!");
/* 1875:1855 */       this.PlayerDataConfig.set("DamageMessages.ReflectSuccess", "&aSchaden Reflektiert!!");
/* 1876:1856 */       this.PlayerDataConfig.set("DamageMessages.EnemyReflectSuccess", "&cGegner hat Schaden Reflektiert!");
/* 1877:1857 */       this.PlayerDataConfig.set("DamageMessages.FireSuccess", "&aGegner entzuendet!");
/* 1878:1858 */       this.PlayerDataConfig.set("DamageMessages.EnemyFireSuccess", "&cdu wurdest entzuendet!");
/* 1879:1859 */       this.PlayerDataConfig.set("DamageMessages.IceSuccess", "&aGegner verlangsamt!");
/* 1880:1860 */       this.PlayerDataConfig.set("DamageMessages.EnemyIceSuccess", "&cdu wurdest verlangsamt!");
/* 1881:1861 */       this.PlayerDataConfig.set("DamageMessages.PoisonSuccess", "&aGegner vergiftet!");
/* 1882:1862 */       this.PlayerDataConfig.set("DamageMessages.EnemyPoisonSuccess", "&cdu wurdest vergiftet!");
/* 1883:1863 */       this.PlayerDataConfig.set("DamageMessages.WitherSuccess", "&aGegner withered!");
/* 1884:1864 */       this.PlayerDataConfig.set("DamageMessages.EnemyWitherSuccess", "&cdu wurdest gewithered!");
/* 1885:1865 */       this.PlayerDataConfig.set("DamageMessages.HarmSuccess", "&aBonus Schaden am Gegner verursacht!");
/* 1886:1866 */       this.PlayerDataConfig.set("DamageMessages.EnemyHarmSuccess", "&cdu bist ohnmaechtig!");
/* 1887:1867 */       this.PlayerDataConfig.set("DamageMessages.BlindSuccess", "&aGegner geblendet!");
/* 1888:1868 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlindSuccess", "&cdu wurdest geblendet!");
/* 1889:1869 */       this.PlayerDataConfig.set("SoulboundMessages", null);
/* 1890:1870 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Head", "&cDu hast den Helm fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1891:1871 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Chest", "&cDu hast den Brustpanzer fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1892:1872 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Legs", "&cDu hast die Hose fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1893:1873 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Boots", "&cDu hast die Schuhe fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1894:1874 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.ItemInHand", "&cDu hast das Item fallengelassen, da du keinen freien Inventar Slot mehr besitzt und gebunden ist an Spieler");
/* 1895:1875 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Head", "&cDieser Helm ist gebunden an");
/* 1896:1876 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Chest", "&cDieser Brustpanzer ist gebunden an");
/* 1897:1877 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Legs", "&cDiese Hose ist gebunden an");
/* 1898:1878 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Boots", "&cDiese Schuhe sind gebunden an");
/* 1899:1879 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.ItemInHand", "&cDieses Item ist gebunden an");
/* 1900:1880 */       this.PlayerDataConfig.set("SpellMessages", null);
/* 1901:1881 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.ItemInHand", "Rechtsklick zum Aktivieren von");
/* 1902:1882 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.CooldownRemaining", "&dsekunden verbleibend.");
/* 1903:1883 */       this.PlayerDataConfig.set("RepairMessages", null);
/* 1904:1884 */       this.PlayerDataConfig.set("RepairMessages.RepairSuccessful", "&aErfolgreich repariert");
/* 1905:1885 */       this.PlayerDataConfig.set("RepairMessages.DoesntNeedRepair", "&cmuss nicht repariert werden.");
/* 1906:1886 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughLeather", "&cDu hast nicht genug Leder zum reparieren");
/* 1907:1887 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cDu hast nicht genug Holz zum reparieren");
/* 1908:1888 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCobblestone", "&cDu hast nicht genug Cobblestone zum reparieren");
/* 1909:1889 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughIron", "&cDu hast nicht genug Eisenbarren zum reparieren");
/* 1910:1890 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughGold", "&cDu hast nicht genug Goldbarren zum reparieren");
/* 1911:1891 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughDiamond", "&cDu hast nicht genug Diamanten zum reparieren");
/* 1912:1892 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughSticks", "&cDu hast nicht genug Stoecke zum reparieren");
/* 1913:1893 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFlint", "&cDu hast nicht genug Deuerstein zum reparieren");
/* 1914:1894 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cDu hast nicht genug Holzerne Planken zum reparieren");
/* 1915:1895 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughString", "&cDu hast nicht genug Faden zum reparieren");
/* 1916:1896 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFishingRod", "&cDu brauchste eine Angel zum reparieren");
/* 1917:1897 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCarrots", "&cDu brauchste eine Karotte zum reparieren");
/* 1918:     */       
/* 1919:1899 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 1920:     */     }
/* 1921:     */     catch (Exception e)
/* 1922:     */     {
/* 1923:1902 */       e.printStackTrace();
/* 1924:1903 */       System.out.println("*********** Nicht zu Standardsprache-DE-Datei speichern! ***********");
/* 1925:     */     }
/* 1926:     */   }
/* 1927:     */   
/* 1928:     */   public void writeLanguagePL()
/* 1929:     */   {
/* 1930:     */     try
/* 1931:     */     {
/* 1932:1911 */       this.PlayerDataConfig = new YamlConfiguration();
/* 1933:1912 */       this.PlayerDataFile = new File(ItemLoreStats.plugin.getDataFolder() + File.separator + "language-pl.yml");
/* 1934:     */       
/* 1935:1914 */       this.PlayerDataConfig.set("ErrorMessages", null);
/* 1936:1915 */       this.PlayerDataConfig.set("ErrorMessages.PermissionDeniedError", "&cNie masz pozwolenia aby uzywac tej komendy!");
/* 1937:1916 */       this.PlayerDataConfig.set("ErrorMessages.IngameOnlyError", "Mozesz uzyc tej komendy tylko w grze!");
/* 1938:1917 */       this.PlayerDataConfig.set("ErrorMessages.DoesntExistError", "&cNie istnieje!");
/* 1939:1918 */       this.PlayerDataConfig.set("ErrorMessages.PlayerNotOnlineError", "&cJest nie aktywny");
/* 1940:1919 */       this.PlayerDataConfig.set("ErrorMessages.NotEnoughSpaceError", "&cNie ma wystarczajaco miejsca w ekwipunku.");
/* 1941:1920 */       this.PlayerDataConfig.set("ErrorMessages.IncludeItemNameError", "&cMusisz wpisac nazwe przedmiotu!");
/* 1942:1921 */       this.PlayerDataConfig.set("ErrorMessages.EnterPlayerNameError", "&cMusisz wpisac nazwe gracza!");
/* 1943:1922 */       this.PlayerDataConfig.set("ErrorMessages.ItemAlreadyExistsError", "&cTen przedmiot juz istnieje. Prosze wybierz inna nazwe dla tego przedmiotu.");
/* 1944:1923 */       this.PlayerDataConfig.set("ErrorMessages.NullItemInHandError", "&cMusisz trzymac przedmiot w rece, aby uzyc tej komendy.");
/* 1945:1924 */       this.PlayerDataConfig.set("ErrorMessages.NoLoreError", "&cThat item doesn't contain any lore.");
/* 1946:1925 */       this.PlayerDataConfig.set("ClassRequirementMessages", null);
/* 1947:1926 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Head", "&cYou dropped your Head item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1948:1927 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Chest", "&cYou dropped your Chest item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1949:1928 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Legs", "&cYou dropped your Legs item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1950:1929 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.Boots", "&cYou dropped your Boots item as you had no inventory slots free and you no longer have the required class to wear it.");
/* 1951:1930 */       this.PlayerDataConfig.set("ClassRequirementMessages.InventoryFull.ItemInHand", "&cYou dropped the item in your hand as you had no inventory slots free and you no longer have the required class to hold it.");
/* 1952:1931 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Head", "&cYou are not the required class to wear your Helmet.");
/* 1953:1932 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Chest", "&cYou are not the required class to wear your Chestplate.");
/* 1954:1933 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Legs", "&cYou are not the required class to wear your Pants.");
/* 1955:1934 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.Boots", "&cYou are not the required class to wear your Boots.");
/* 1956:1935 */       this.PlayerDataConfig.set("ClassRequirementMessages.NotRequiredClass.ItemInHand", "&cYou are not the required class to hold that item.");
/* 1957:1936 */       this.PlayerDataConfig.set("LevelRequirementMessages", null);
/* 1958:1937 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Head", "&cUpusciles swoj Helm, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1959:1938 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Chest", "&cUpusciles swoja Zbroje, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1960:1939 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Legs", "&cUpusciles swoje Spodnie, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1961:1940 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.Boots", "&cUpusciles swoje Buty, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1962:1941 */       this.PlayerDataConfig.set("LevelRequirementMessages.InventoryFull.ItemInHand", "&cUpusciles swoj przedmiot, ktory trzymales, poniewaz nie miales wolnego miejsca i wymaganego poziomu by go zalozyc.");
/* 1963:1942 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Head", "&cNie masz wymaganego poziomu aby zalozyc swoj Helm.");
/* 1964:1943 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Chest", "&cNie masz wymaganego poziomu aby zalozyc swoja Zbroje.");
/* 1965:1944 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Legs", "&cNie masz wymaganego poziomu aby zalozyc swoje Spodnie.");
/* 1966:1945 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.Boots", "&cNie masz wymaganego poziomu aby zalozyc swoje Buty.");
/* 1967:1946 */       this.PlayerDataConfig.set("LevelRequirementMessages.LevelTooLow.ItemInHand", "&cNie masz wymaganego poziomu aby trzymac ten przedmiot.");
/* 1968:1947 */       this.PlayerDataConfig.set("DamageMessages", null);
/* 1969:1948 */       this.PlayerDataConfig.set("DamageMessages.CriticalStrikeSuccess", "&aKrytyczne uderzenie!");
/* 1970:1949 */       this.PlayerDataConfig.set("DamageMessages.EnemyCriticalStrikeSuccess", "&cotrzymano krytyczne obrazenie!");
/* 1971:1950 */       this.PlayerDataConfig.set("DamageMessages.DodgeSuccess", "&aUnik!");
/* 1972:1951 */       this.PlayerDataConfig.set("DamageMessages.EnemyDodgeSuccess", "&cuniknal twoj atak!");
/* 1973:1952 */       this.PlayerDataConfig.set("DamageMessages.BlockSuccess", "&aBlok!");
/* 1974:1953 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlockSuccess", "&czablokowany twoj atak!");
/* 1975:1954 */       this.PlayerDataConfig.set("DamageMessages.LifeStealSuccess", "&aWyssane zycie!");
/* 1976:1955 */       this.PlayerDataConfig.set("DamageMessages.EnemyLifeStealSuccess", "&cwykradl tobie troche zycia!");
/* 1977:1956 */       this.PlayerDataConfig.set("DamageMessages.ReflectSuccess", "&aReflected!");
/* 1978:1957 */       this.PlayerDataConfig.set("DamageMessages.EnemyReflectSuccess", "&creflected your attack!");
/* 1979:1958 */       this.PlayerDataConfig.set("DamageMessages.FireSuccess", "&aCel podpalony!");
/* 1980:1959 */       this.PlayerDataConfig.set("DamageMessages.EnemyFireSuccess", "&cpodpalil ciebie!");
/* 1981:1960 */       this.PlayerDataConfig.set("DamageMessages.IceSuccess", "&aCel spowolniony!");
/* 1982:1961 */       this.PlayerDataConfig.set("DamageMessages.EnemyIceSuccess", "&cspowolnil ciebie!");
/* 1983:1962 */       this.PlayerDataConfig.set("DamageMessages.PoisonSuccess", "&aCel zatruty!");
/* 1984:1963 */       this.PlayerDataConfig.set("DamageMessages.EnemyPoisonSuccess", "&cotrul ciebie!");
/* 1985:1964 */       this.PlayerDataConfig.set("DamageMessages.WitherSuccess", "&aCel bezwladny!");
/* 1986:1965 */       this.PlayerDataConfig.set("DamageMessages.EnemyWitherSuccess", "&cobezwladnil ciebie!");
/* 1987:1966 */       this.PlayerDataConfig.set("DamageMessages.HarmSuccess", "&acel zraniony!");
/* 1988:1967 */       this.PlayerDataConfig.set("DamageMessages.EnemyHarmSuccess", "&czranill ciebie!");
/* 1989:1968 */       this.PlayerDataConfig.set("DamageMessages.BlindSuccess", "&aCel oslepiony!");
/* 1990:1969 */       this.PlayerDataConfig.set("DamageMessages.EnemyBlindSuccess", "&coslepil ciebie!");
/* 1991:1970 */       this.PlayerDataConfig.set("SoulboundMessages", null);
/* 1992:1971 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Head", "&cUpusciles swoj Helm, poniewaz nie miales miejsca w plecaku i dlatego, ze ten przedmiot jest przywiazany do innego gracza");
/* 1993:1972 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Chest", "&cUpusciles swoja Zbroje, poniewaz nie miales miejsca w plecaku i dlatego, ze ten przedmiot jest przywiazany do innego gracza");
/* 1994:1973 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Legs", "&cUpusciles swoje Spodnie, poniewaz nie miales miejsca w plecaku i dlatego, ze ten nnego gracza");
/* 1995:1974 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.Boots", "&cUpusciles swoje Buty, poniewaz nie miales miejsca w plecaku i dlatego, ze ten nnego gracza");
/* 1996:1975 */       this.PlayerDataConfig.set("SoulboundMessages.InventoryFull.ItemInHand", "&cUpusciles przedmiot, ktory trzymales, poniewaz nie miales miejsca w plecaku i dlatego, ze ten przedmiot jest przywiazany do innego gracza");
/* 1997:1976 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Head", "&cTen Helm jest przywiazany do innego gracza");
/* 1998:1977 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Chest", "&cTa Zbroja jest przywiazana do innego gracza");
/* 1999:1978 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Legs", "&cTe Spodnie sa przywiazane do innego gracza");
/* 2000:1979 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.Boots", "&cTe Buty sa przywiazane do innego gracza");
/* 2001:1980 */       this.PlayerDataConfig.set("SoulboundMessages.BoundToSomeoneElse.ItemInHand", "&cTen przedmiot, ktory trzymasz jest przywiazany do innego gracza");
/* 2002:1981 */       this.PlayerDataConfig.set("SpellMessages", null);
/* 2003:1982 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.ItemInHand", "Nacisnij prawy przycisk myszki aby uzyc czaru");
/* 2004:1983 */       this.PlayerDataConfig.set("SpellMessages.CastSpell.CooldownRemaining", "&dpozostaly sekundy.");
/* 2005:1984 */       this.PlayerDataConfig.set("RepairMessages", null);
/* 2006:1985 */       this.PlayerDataConfig.set("RepairMessages.RepairSuccessful", "&aUdana naprawa");
/* 2007:1986 */       this.PlayerDataConfig.set("RepairMessages.DoesntNeedRepair", "&cTen przedmiot nie potrzebuje naprawy.");
/* 2008:1987 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughLeather", "&cNie masz wystarczajaco Skory, aby naprawic ten przedmiot");
/* 2009:1988 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cNie masz wystarczajaco Drewna, aby naprawic ten przedmiot");
/* 2010:1989 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCobblestone", "&cNie masz wystarczajaco Bruku, aby naprawic ten przedmiot");
/* 2011:1990 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughIron", "&cNie masz wystarczajaco Zelaza, aby naprawic ten przedmiot");
/* 2012:1991 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughGold", "&cNie masz wystarczajaco Zlota, aby naprawic ten przedmiot");
/* 2013:1992 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughDiamond", "&cNie masz wystarczajaco Diamentow, aby naprawic ten przedmiot");
/* 2014:1993 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughSticks", "&cNie masz wystarczajaco Kijow, aby naprawic ten przedmiot");
/* 2015:1994 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFlint", "&cNie masz wystarczajaco krzemien, aby naprawic ten przedmiot");
/* 2016:1995 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughWood", "&cNie masz wystarczajaco Drewniane Deski, aby naprawic ten przedmiot");
/* 2017:1996 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughString", "&cNie masz wystarczajaco Cieciw, aby naprawic ten przedmiot");
/* 2018:1997 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughFishingRod", "&cPotrzebujesz Wedki, aby naprawic ten przedmiot");
/* 2019:1998 */       this.PlayerDataConfig.set("RepairMessages.NotEnoughCarrots", "&cPotrzebujesz Marchewki, aby naprawic ten przedmiot");
/* 2020:     */       
/* 2021:2000 */       this.PlayerDataConfig.save(this.PlayerDataFile);
/* 2022:     */     }
/* 2023:     */     catch (Exception e)
/* 2024:     */     {
/* 2025:2003 */       e.printStackTrace();
/* 2026:2004 */       System.out.println("*********** Failed to save default language-PL file! ***********");
/* 2027:     */     }
/* 2028:     */   }
/* 2029:     */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.WriteDefaultFiles
 * JD-Core Version:    0.7.0.1
 */