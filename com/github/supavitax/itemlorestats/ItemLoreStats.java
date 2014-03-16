/*    1:     */ package com.github.supavitax.itemlorestats;
/*    2:     */ 
/*    3:     */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*    4:     */ import com.github.supavitax.itemlorestats.Util.Util_Heroes;
/*    5:     */ import com.github.supavitax.itemlorestats.Util.Util_WorldGuard;
/*    6:     */ import com.herocraftonline.heroes.Heroes;
/*    7:     */ import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
/*    8:     */ import com.sucy.skill.SkillAPI;
/*    9:     */ import java.io.File;
/*   10:     */ import java.io.IOException;
/*   11:     */ import java.io.PrintStream;
/*   12:     */ import java.util.ArrayList;
/*   13:     */ import java.util.HashMap;
/*   14:     */ import java.util.Iterator;
/*   15:     */ import java.util.List;
/*   16:     */ import java.util.Random;
/*   17:     */ import java.util.logging.Logger;
/*   18:     */ import org.bukkit.Bukkit;
/*   19:     */ import org.bukkit.ChatColor;
/*   20:     */ import org.bukkit.Effect;
/*   21:     */ import org.bukkit.GameMode;
/*   22:     */ import org.bukkit.Location;
/*   23:     */ import org.bukkit.Material;
/*   24:     */ import org.bukkit.OfflinePlayer;
/*   25:     */ import org.bukkit.Server;
/*   26:     */ import org.bukkit.World;
/*   27:     */ import org.bukkit.block.Block;
/*   28:     */ import org.bukkit.command.Command;
/*   29:     */ import org.bukkit.command.CommandSender;
/*   30:     */ import org.bukkit.configuration.file.FileConfiguration;
/*   31:     */ import org.bukkit.configuration.file.FileConfigurationOptions;
/*   32:     */ import org.bukkit.configuration.file.YamlConfiguration;
/*   33:     */ import org.bukkit.enchantments.Enchantment;
/*   34:     */ import org.bukkit.entity.EntityType;
/*   35:     */ import org.bukkit.entity.HumanEntity;
/*   36:     */ import org.bukkit.entity.Item;
/*   37:     */ import org.bukkit.entity.LivingEntity;
/*   38:     */ import org.bukkit.entity.Player;
/*   39:     */ import org.bukkit.event.EventHandler;
/*   40:     */ import org.bukkit.event.EventPriority;
/*   41:     */ import org.bukkit.event.Listener;
/*   42:     */ import org.bukkit.event.block.Action;
/*   43:     */ import org.bukkit.event.block.BlockBreakEvent;
/*   44:     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*   45:     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*   46:     */ import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
/*   47:     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*   48:     */ import org.bukkit.event.inventory.InventoryType;
/*   49:     */ import org.bukkit.event.inventory.PrepareItemCraftEvent;
/*   50:     */ import org.bukkit.event.player.PlayerChangedWorldEvent;
/*   51:     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*   52:     */ import org.bukkit.event.player.PlayerExpChangeEvent;
/*   53:     */ import org.bukkit.event.player.PlayerFishEvent;
/*   54:     */ import org.bukkit.event.player.PlayerInteractEvent;
/*   55:     */ import org.bukkit.event.player.PlayerItemBreakEvent;
/*   56:     */ import org.bukkit.event.player.PlayerItemHeldEvent;
/*   57:     */ import org.bukkit.event.player.PlayerJoinEvent;
/*   58:     */ import org.bukkit.event.player.PlayerLevelChangeEvent;
/*   59:     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*   60:     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*   61:     */ import org.bukkit.inventory.CraftingInventory;
/*   62:     */ import org.bukkit.inventory.EntityEquipment;
/*   63:     */ import org.bukkit.inventory.Inventory;
/*   64:     */ import org.bukkit.inventory.InventoryView;
/*   65:     */ import org.bukkit.inventory.ItemStack;
/*   66:     */ import org.bukkit.inventory.PlayerInventory;
/*   67:     */ import org.bukkit.inventory.Recipe;
/*   68:     */ import org.bukkit.inventory.meta.ItemMeta;
/*   69:     */ import org.bukkit.plugin.Plugin;
/*   70:     */ import org.bukkit.plugin.PluginDescriptionFile;
/*   71:     */ import org.bukkit.plugin.PluginManager;
/*   72:     */ import org.bukkit.plugin.java.JavaPlugin;
/*   73:     */ import org.bukkit.potion.PotionEffect;
/*   74:     */ import org.bukkit.potion.PotionEffectType;
/*   75:     */ import org.bukkit.scheduler.BukkitScheduler;
/*   76:     */ 
/*   77:     */ public class ItemLoreStats
/*   78:     */   extends JavaPlugin
/*   79:     */ {
/*   80:     */   public static ItemLoreStats plugin;
/*   81:  83 */   private static final Logger log = Logger.getLogger("Minecraft");
/*   82:     */   private FileConfiguration PlayerDataConfig;
/*   83:     */   public HashMap<String, Long> cooldowns;
/*   84:     */   public HashMap<String, Double> setBonusesModifiers;
/*   85:     */   private SkillAPI api;
/*   86:     */   CharacterSheet characterSheet;
/*   87:     */   Classes classes;
/*   88:     */   DamageSystem damageSystem;
/*   89:     */   EnvironmentalDamage environmentalDamage;
/*   90:     */   GearStats gearStats;
/*   91:     */   GenerateFromFile generateFromFile;
/*   92:     */   RepairItems repairItems;
/*   93:     */   SetBonuses setBonuses;
/*   94:     */   Soulbound soulbound;
/*   95:     */   Spells spells;
/*   96:     */   Util_Colours util_Colours;
/*   97:     */   Util_WorldGuard util_WorldGuard;
/*   98:     */   Util_Heroes util_Heroes;
/*   99:     */   WriteDefaultFiles writeDefaultFiles;
/*  100:     */   XpLevel xpLevel;
/*  101:     */   
/*  102:     */   public ItemLoreStats()
/*  103:     */   {
/*  104:  85 */     this.cooldowns = new HashMap();
/*  105:  86 */     this.setBonusesModifiers = new HashMap();
/*  106:     */     
/*  107:     */ 
/*  108:  89 */     this.characterSheet = new CharacterSheet();
/*  109:  90 */     this.classes = new Classes();
/*  110:  91 */     this.damageSystem = new DamageSystem(this);
/*  111:  92 */     this.environmentalDamage = new EnvironmentalDamage();
/*  112:  93 */     this.gearStats = new GearStats();
/*  113:  94 */     this.generateFromFile = new GenerateFromFile();
/*  114:  95 */     this.repairItems = new RepairItems();
/*  115:  96 */     this.setBonuses = new SetBonuses();
/*  116:  97 */     this.soulbound = new Soulbound();
/*  117:  98 */     this.spells = new Spells();
/*  118:  99 */     this.util_Colours = new Util_Colours();
/*  119:     */     
/*  120:     */ 
/*  121: 102 */     this.writeDefaultFiles = new WriteDefaultFiles();
/*  122: 103 */     this.xpLevel = new XpLevel();
/*  123:     */   }
/*  124:     */   
/*  125:     */   public void onEnable()
/*  126:     */   {
/*  127: 107 */     getWorldGuard();
/*  128: 108 */     getHeroes();
/*  129: 109 */     this.api = ((SkillAPI)Bukkit.getPluginManager().getPlugin("SkillAPI"));
/*  130: 110 */     PluginManager plma = getServer().getPluginManager();
/*  131: 111 */     plma.registerEvents(new ItemLoreStatsListener(), this);
/*  132: 112 */     plma.registerEvents(new DamageSystem(this), this);
/*  133: 113 */     plma.registerEvents(new EnvironmentalDamage(), this);
/*  134: 114 */     plma.registerEvents(new EntityDrops(), this);
/*  135:     */     
/*  136: 116 */     plugin = this;
/*  137:     */     
/*  138: 118 */     this.writeDefaultFiles.checkExistence();
/*  139:     */     try
/*  140:     */     {
/*  141: 121 */       MetricsLite metrics = new MetricsLite(this);
/*  142: 122 */       metrics.start();
/*  143:     */     }
/*  144:     */     catch (IOException localIOException) {}
/*  145: 127 */     getConfig().options().copyDefaults(true);
/*  146: 128 */     saveConfig();
/*  147:     */   }
/*  148:     */   
/*  149:     */   public void onDisable()
/*  150:     */   {
/*  151: 133 */     log.info(String.format("[%s] Disabled Version %s", new Object[] { getDescription().getName(), getDescription().getVersion() }));
/*  152:     */   }
/*  153:     */   
/*  154:     */   public static ItemLoreStats getPlugin()
/*  155:     */   {
/*  156: 137 */     return (ItemLoreStats)Bukkit.getPluginManager().getPlugin("ItemLoreStats");
/*  157:     */   }
/*  158:     */   
/*  159:     */   public static boolean isInteger(String str)
/*  160:     */   {
/*  161:     */     try
/*  162:     */     {
/*  163: 142 */       Integer.parseInt(str);
/*  164: 143 */       return true;
/*  165:     */     }
/*  166:     */     catch (NumberFormatException nfe) {}
/*  167: 145 */     return false;
/*  168:     */   }
/*  169:     */   
/*  170:     */   public WorldGuardPlugin getWorldGuard()
/*  171:     */   {
/*  172: 150 */     Plugin WorldGuard = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
/*  173: 151 */     if ((WorldGuard == null) || (!(WorldGuard instanceof WorldGuardPlugin))) {
/*  174: 152 */       return null;
/*  175:     */     }
/*  176: 154 */     this.util_WorldGuard = new Util_WorldGuard(plugin);
/*  177: 155 */     return (WorldGuardPlugin)WorldGuard;
/*  178:     */   }
/*  179:     */   
/*  180:     */   public Heroes getHeroes()
/*  181:     */   {
/*  182: 158 */     Plugin Heroes = Bukkit.getServer().getPluginManager().getPlugin("Heroes");
/*  183: 159 */     if ((Heroes == null) || (!(Heroes instanceof Heroes))) {
/*  184: 160 */       return null;
/*  185:     */     }
/*  186: 162 */     this.util_Heroes = new Util_Heroes(plugin);
/*  187: 163 */     return (Heroes)Heroes;
/*  188:     */   }
/*  189:     */   
/*  190:     */   public String getResponse(String getKeyFromLanguageFile)
/*  191:     */   {
/*  192:     */     try
/*  193:     */     {
/*  194: 168 */       this.PlayerDataConfig = new YamlConfiguration();
/*  195: 169 */       this.PlayerDataConfig.load(new File(plugin.getDataFolder() + File.separator + getConfig().getString("languageFile") + ".yml"));
/*  196:     */       
/*  197: 171 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*  198:     */     }
/*  199:     */     catch (Exception e)
/*  200:     */     {
/*  201: 174 */       e.printStackTrace();
/*  202: 175 */       System.out.println("*********** Failed to load message from language file! ***********");
/*  203:     */     }
/*  204: 177 */     return "*********** Failed to load message from language file! ***********";
/*  205:     */   }
/*  206:     */   
/*  207:     */   private int random(int length)
/*  208:     */   {
/*  209: 182 */     return new Random().nextInt(length) + 1;
/*  210:     */   }
/*  211:     */   
/*  212:     */   private int randomRange(int min, int max)
/*  213:     */   {
/*  214: 185 */     return (int)(min + Math.random() * (max - min));
/*  215:     */   }
/*  216:     */   
/*  217:     */   public boolean isTool(Material material)
/*  218:     */   {
/*  219: 189 */     return (material == Material.STICK) || (material == Material.FLINT_AND_STEEL) || (material == Material.FISHING_ROD) || (material == Material.SHEARS) || (material == Material.BOW) || (material == Material.CARROT_STICK) || (material == Material.WOOD_SWORD) || (material == Material.STONE_SWORD) || (material == Material.GOLD_SWORD) || (material == Material.IRON_SWORD) || (material == Material.DIAMOND_SWORD) || (material == Material.WOOD_PICKAXE) || (material == Material.STONE_PICKAXE) || (material == Material.GOLD_PICKAXE) || (material == Material.IRON_PICKAXE) || (material == Material.DIAMOND_PICKAXE) || (material == Material.WOOD_AXE) || (material == Material.STONE_AXE) || (material == Material.GOLD_AXE) || (material == Material.IRON_AXE) || (material == Material.DIAMOND_AXE) || (material == Material.WOOD_SPADE) || (material == Material.STONE_SPADE) || (material == Material.GOLD_SPADE) || (material == Material.IRON_SPADE) || (material == Material.DIAMOND_SPADE) || (material == Material.WOOD_HOE) || (material == Material.STONE_HOE) || (material == Material.GOLD_HOE) || (material == Material.IRON_HOE) || (material == Material.DIAMOND_HOE);
/*  220:     */   }
/*  221:     */   
/*  222:     */   public boolean isArmour(Material material)
/*  223:     */   {
/*  224: 192 */     return (material == Material.LEATHER_HELMET) || (material == Material.CHAINMAIL_HELMET) || (material == Material.GOLD_HELMET) || (material == Material.IRON_HELMET) || (material == Material.DIAMOND_HELMET) || (material == Material.LEATHER_CHESTPLATE) || (material == Material.CHAINMAIL_CHESTPLATE) || (material == Material.GOLD_CHESTPLATE) || (material == Material.IRON_CHESTPLATE) || (material == Material.DIAMOND_CHESTPLATE) || (material == Material.LEATHER_LEGGINGS) || (material == Material.CHAINMAIL_LEGGINGS) || (material == Material.GOLD_LEGGINGS) || (material == Material.IRON_LEGGINGS) || (material == Material.DIAMOND_LEGGINGS) || (material == Material.LEATHER_BOOTS) || (material == Material.CHAINMAIL_BOOTS) || (material == Material.GOLD_BOOTS) || (material == Material.IRON_BOOTS) || (material == Material.DIAMOND_BOOTS);
/*  225:     */   }
/*  226:     */   
/*  227:     */   public boolean isHelmet(Material material)
/*  228:     */   {
/*  229: 195 */     return (material == Material.LEATHER_HELMET) || (material == Material.CHAINMAIL_HELMET) || (material == Material.GOLD_HELMET) || (material == Material.IRON_HELMET) || (material == Material.DIAMOND_HELMET);
/*  230:     */   }
/*  231:     */   
/*  232:     */   public boolean isChestplate(Material material)
/*  233:     */   {
/*  234: 198 */     return (material == Material.LEATHER_CHESTPLATE) || (material == Material.CHAINMAIL_CHESTPLATE) || (material == Material.GOLD_CHESTPLATE) || (material == Material.IRON_CHESTPLATE) || (material == Material.DIAMOND_CHESTPLATE);
/*  235:     */   }
/*  236:     */   
/*  237:     */   public boolean isLeggings(Material material)
/*  238:     */   {
/*  239: 201 */     return (material == Material.LEATHER_LEGGINGS) || (material == Material.CHAINMAIL_LEGGINGS) || (material == Material.GOLD_LEGGINGS) || (material == Material.IRON_LEGGINGS) || (material == Material.DIAMOND_LEGGINGS);
/*  240:     */   }
/*  241:     */   
/*  242:     */   public boolean isBoots(Material material)
/*  243:     */   {
/*  244: 204 */     return (material == Material.LEATHER_BOOTS) || (material == Material.CHAINMAIL_BOOTS) || (material == Material.GOLD_BOOTS) || (material == Material.IRON_BOOTS) || (material == Material.DIAMOND_BOOTS);
/*  245:     */   }
/*  246:     */   
/*  247:     */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*  248:     */   {
/*  249: 209 */     String armour = plugin.getConfig().getString("statNames.armour").replaceAll("&([0-9a-f])", "");
/*  250: 210 */     String dodge = plugin.getConfig().getString("statNames.dodge").replaceAll("&([0-9a-f])", "");
/*  251: 211 */     String block = plugin.getConfig().getString("statNames.block").replaceAll("&([0-9a-f])", "");
/*  252: 212 */     String critChance = plugin.getConfig().getString("statNames.critChance").replaceAll("&([0-9a-f])", "");
/*  253: 213 */     String critDamage = plugin.getConfig().getString("statNames.critDamage").replaceAll("&([0-9a-f])", "");
/*  254: 214 */     String damage = plugin.getConfig().getString("statNames.damage").replaceAll("&([0-9a-f])", "");
/*  255: 215 */     String health = plugin.getConfig().getString("statNames.health").replaceAll("&([0-9a-f])", "");
/*  256: 216 */     String healthRegen = plugin.getConfig().getString("statNames.healthregen").replaceAll("&([0-9a-f])", "");
/*  257: 217 */     String lifeSteal = plugin.getConfig().getString("statNames.lifesteal").replaceAll("&([0-9a-f])", "");
/*  258: 218 */     String reflect = plugin.getConfig().getString("statNames.reflect").replaceAll("&([0-9a-f])", "");
/*  259: 219 */     String fire = plugin.getConfig().getString("statNames.fire").replaceAll("&([0-9a-f])", "");
/*  260: 220 */     String ice = plugin.getConfig().getString("statNames.ice").replaceAll("&([0-9a-f])", "");
/*  261: 221 */     String poison = plugin.getConfig().getString("statNames.poison").replaceAll("&([0-9a-f])", "");
/*  262: 222 */     String wither = plugin.getConfig().getString("statNames.wither").replaceAll("&([0-9a-f])", "");
/*  263: 223 */     String harming = plugin.getConfig().getString("statNames.harming").replaceAll("&([0-9a-f])", "");
/*  264: 224 */     String blind = plugin.getConfig().getString("statNames.blind").replaceAll("&([0-9a-f])", "");
/*  265: 225 */     String movementspeed = plugin.getConfig().getString("statNames.movementspeed").replaceAll("&([0-9a-f])", "");
/*  266: 226 */     String weaponspeed = plugin.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/*  267: 227 */     String xplevel = plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/*  268: 228 */     String soulbound = plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/*  269: 229 */     String durability = plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  270: 231 */     if (cmd.getName().equalsIgnoreCase("ils"))
/*  271:     */     {
/*  272: 233 */       if (args.length == 0) {
/*  273: 234 */         if ((sender instanceof Player))
/*  274:     */         {
/*  275: 235 */           sender.sendMessage(ChatColor.GOLD + "Item Lore Stats " + ChatColor.LIGHT_PURPLE + "commands:");
/*  276: 236 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils");
/*  277: 237 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils reload");
/*  278: 238 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils stats");
/*  279: 239 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils version");
/*  280: 240 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils name " + ChatColor.GOLD + "<text>");
/*  281: 241 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils lore " + ChatColor.GOLD + "<player_name> <line#> <text>");
/*  282: 242 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils give " + ChatColor.GOLD + "<player_name> <item_name>");
/*  283: 243 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils give " + ChatColor.GOLD + "<player_name> <item_name>, <new_item_name>");
/*  284: 244 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils repair");
/*  285: 245 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils export " + ChatColor.GOLD + "<item_name>");
/*  286: 246 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils setMultiplier");
/*  287:     */         }
/*  288:     */         else
/*  289:     */         {
/*  290: 248 */           System.out.println("Item Lore Stats commands:");
/*  291: 249 */           System.out.println("   /ils");
/*  292: 250 */           System.out.println("   /ils reload");
/*  293: 251 */           System.out.println("   /ils stats");
/*  294: 252 */           System.out.println("   /ils version");
/*  295: 253 */           System.out.println("   /ils name <text>");
/*  296: 254 */           System.out.println("   /ils lore <player_name> <line#> <text>");
/*  297: 255 */           System.out.println("   /ils give <player_name> <item_name>");
/*  298: 256 */           System.out.println("   /ils give <player_name> <item_name>, <new_item_name>");
/*  299: 257 */           System.out.println("   /ils repair");
/*  300: 258 */           System.out.println("   /ils export <item_name>");
/*  301: 259 */           System.out.println("   /ils setMultiplier");
/*  302:     */         }
/*  303:     */       }
/*  304: 263 */       if (args.length > 0)
/*  305:     */       {
/*  306: 265 */         if (args[0].equalsIgnoreCase("version"))
/*  307:     */         {
/*  308: 266 */           if ((sender instanceof Player))
/*  309:     */           {
/*  310: 267 */             Player player = (Player)sender;
/*  311: 268 */             player.sendMessage(ChatColor.GOLD + "[ItemLoreStats] " + ChatColor.GREEN + " Currently running version " + getDescription().getVersion());
/*  312: 269 */             return true;
/*  313:     */           }
/*  314: 271 */           System.out.println("[ItemLoreStats] Currently running version " + getDescription().getVersion());
/*  315:     */         }
/*  316: 274 */         if (args[0].equalsIgnoreCase("name")) {
/*  317: 275 */           if ((sender instanceof Player))
/*  318:     */           {
/*  319: 276 */             Player player = (Player)sender;
/*  320: 277 */             if (player.hasPermission("ils.admin"))
/*  321:     */             {
/*  322: 278 */               if (args.length > 1)
/*  323:     */               {
/*  324: 279 */                 String storeName = player.getItemInHand().getItemMeta().getDisplayName();
/*  325: 280 */                 String newName = "";
/*  326:     */                 
/*  327: 282 */                 ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  328: 283 */                 ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  329: 285 */                 for (int i = 1; i < args.length; i++) {
/*  330: 286 */                   if (i >= 2) {
/*  331: 287 */                     newName = newName + " " + args[i];
/*  332:     */                   } else {
/*  333: 289 */                     newName = args[i];
/*  334:     */                   }
/*  335:     */                 }
/*  336: 293 */                 getItemInHandMeta.setDisplayName(this.util_Colours.replaceTooltipColour(newName));
/*  337: 294 */                 getItemInHand.setItemMeta(getItemInHandMeta);
/*  338: 295 */                 player.sendMessage(ChatColor.LIGHT_PURPLE + "Changed the name of " + ChatColor.RESET + storeName + ChatColor.LIGHT_PURPLE + " to " + ChatColor.RESET + this.util_Colours.replaceTooltipColour(newName));
/*  339: 296 */                 player.getInventory().remove(player.getItemInHand());
/*  340: 297 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  341:     */               }
/*  342:     */               else
/*  343:     */               {
/*  344: 299 */                 player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError") + " For example, /ils name " + ChatColor.DARK_RED + "Hand of God");
/*  345:     */               }
/*  346:     */             }
/*  347:     */             else {
/*  348: 303 */               player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  349:     */             }
/*  350:     */           }
/*  351:     */           else
/*  352:     */           {
/*  353: 306 */             System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/*  354:     */           }
/*  355:     */         }
/*  356: 310 */         if (args[0].equalsIgnoreCase("give")) {
/*  357: 311 */           if ((sender instanceof Player))
/*  358:     */           {
/*  359: 312 */             Player player = (Player)sender;
/*  360: 313 */             if (player.hasPermission("ils.admin"))
/*  361:     */             {
/*  362: 314 */               if (args.length > 1)
/*  363:     */               {
/*  364: 315 */                 if (player.getServer().getPlayer(args[1]) != null)
/*  365:     */                 {
/*  366: 316 */                   if (args.length > 2)
/*  367:     */                   {
/*  368: 318 */                     String newItemName = "";
/*  369: 319 */                     String replaceNewItemName = "";
/*  370: 321 */                     for (int i = 0; i < args.length; i++) {
/*  371: 322 */                       if (i >= 3) {
/*  372: 323 */                         newItemName = newItemName + " " + args[i];
/*  373:     */                       } else {
/*  374: 325 */                         newItemName = args[i];
/*  375:     */                       }
/*  376:     */                     }
/*  377: 329 */                     if (newItemName.contains(","))
/*  378:     */                     {
/*  379: 330 */                       replaceNewItemName = newItemName.split(",")[1].trim();
/*  380: 331 */                       newItemName = newItemName.split(",")[0].trim();
/*  381:     */                     }
/*  382: 334 */                     if (new File(getDataFolder() + File.separator + "SavedItems" + File.separator + newItemName + ".yml").exists())
/*  383:     */                     {
/*  384: 335 */                       Player givePlayer = player.getServer().getPlayer(args[1]);
/*  385: 337 */                       if (givePlayer.getInventory().firstEmpty() == -1) {
/*  386: 338 */                         player.sendMessage(givePlayer.getName() + ChatColor.RED + " " + getResponse("ErrorMessages.NotEnoughSpaceError"));
/*  387: 340 */                       } else if (!replaceNewItemName.equals("")) {
/*  388: 341 */                         givePlayer.getInventory().addItem(new ItemStack[] { this.generateFromFile.importWeapon(newItemName, replaceNewItemName, player.getName()) });
/*  389:     */                       } else {
/*  390: 343 */                         givePlayer.getInventory().addItem(new ItemStack[] { this.generateFromFile.importWeapon(newItemName, "noChange", player.getName()) });
/*  391:     */                       }
/*  392:     */                     }
/*  393:     */                     else
/*  394:     */                     {
/*  395: 347 */                       player.sendMessage(ChatColor.RED + newItemName + " " + getResponse("ErrorMessages.DoesntExistError"));
/*  396:     */                     }
/*  397:     */                   }
/*  398:     */                   else
/*  399:     */                   {
/*  400: 350 */                     player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError"));
/*  401:     */                   }
/*  402:     */                 }
/*  403:     */                 else {
/*  404: 353 */                   player.sendMessage(ChatColor.RED + args[1] + " " + getResponse("ErrorMessages.PlayerNotOnlineError"));
/*  405:     */                 }
/*  406:     */               }
/*  407:     */               else {
/*  408: 357 */                 player.sendMessage(getResponse("ErrorMessages.EnterPlayerNameError"));
/*  409:     */               }
/*  410:     */             }
/*  411:     */             else {
/*  412: 360 */               player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  413:     */             }
/*  414:     */           }
/*  415: 363 */           else if (args.length > 1)
/*  416:     */           {
/*  417: 364 */             if (Bukkit.getServer().getPlayer(args[1]) != null)
/*  418:     */             {
/*  419: 365 */               if (args.length > 2)
/*  420:     */               {
/*  421: 367 */                 String newItemName = "";
/*  422: 368 */                 String replaceNewItemName = "";
/*  423: 370 */                 for (int i = 0; i < args.length; i++) {
/*  424: 371 */                   if (i >= 3) {
/*  425: 372 */                     newItemName = newItemName + " " + args[i];
/*  426:     */                   } else {
/*  427: 374 */                     newItemName = args[i];
/*  428:     */                   }
/*  429:     */                 }
/*  430: 378 */                 if (newItemName.contains(",")) {
/*  431: 379 */                   replaceNewItemName = newItemName.split(",")[1].trim();
/*  432:     */                 }
/*  433: 382 */                 if (new File(getDataFolder() + File.separator + "SavedItems" + File.separator + newItemName + ".yml").exists())
/*  434:     */                 {
/*  435: 383 */                   Player givePlayer = Bukkit.getServer().getPlayer(args[1]);
/*  436: 385 */                   if (givePlayer.getInventory().firstEmpty() == -1)
/*  437:     */                   {
/*  438: 386 */                     System.out.println(givePlayer.getName() + " " + getResponse("ErrorMessages.NotEnoughSpaceError"));
/*  439:     */                   }
/*  440: 388 */                   else if (newItemName.contains(","))
/*  441:     */                   {
/*  442: 389 */                     System.out.println(givePlayer.getName() + " successfully received " + replaceNewItemName + ".");
/*  443: 390 */                     givePlayer.getInventory().addItem(new ItemStack[] { this.generateFromFile.importWeapon(newItemName, replaceNewItemName, givePlayer.getName()) });
/*  444:     */                   }
/*  445:     */                   else
/*  446:     */                   {
/*  447: 392 */                     System.out.println(givePlayer.getName() + " successfully received " + newItemName + ".");
/*  448: 393 */                     givePlayer.getInventory().addItem(new ItemStack[] { this.generateFromFile.importWeapon(newItemName, "noChange", givePlayer.getName()) });
/*  449:     */                   }
/*  450:     */                 }
/*  451:     */                 else
/*  452:     */                 {
/*  453: 397 */                   System.out.println("[ILS] " + newItemName + " " + getResponse("ErrorMessages.DoesntExistError"));
/*  454:     */                 }
/*  455:     */               }
/*  456:     */               else
/*  457:     */               {
/*  458: 400 */                 System.out.println("[ILS]" + getResponse("ErrorMessages.IncludeItemNameError"));
/*  459:     */               }
/*  460:     */             }
/*  461:     */             else {
/*  462: 403 */               System.out.println("[ILS] " + args[1] + " " + getResponse("ErrorMessages.PlayerNotOnlineError"));
/*  463:     */             }
/*  464:     */           }
/*  465:     */           else
/*  466:     */           {
/*  467: 407 */             System.out.println("[ILS]" + getResponse("ErrorMessages.EnterPlayerNameError"));
/*  468:     */           }
/*  469:     */         }
/*  470: 412 */         if (args[0].equalsIgnoreCase("export")) {
/*  471: 413 */           if ((sender instanceof Player))
/*  472:     */           {
/*  473: 414 */             Player player = (Player)sender;
/*  474: 415 */             if (player.hasPermission("ils.admin"))
/*  475:     */             {
/*  476: 416 */               if (args.length > 1)
/*  477:     */               {
/*  478: 418 */                 String newItemName = "";
/*  479: 420 */                 for (int i = 0; i < args.length; i++) {
/*  480: 421 */                   if (i >= 2) {
/*  481: 422 */                     newItemName = newItemName + " " + args[i];
/*  482:     */                   } else {
/*  483: 424 */                     newItemName = args[i];
/*  484:     */                   }
/*  485:     */                 }
/*  486: 428 */                 if (new File(getDataFolder() + File.separator + "SavedItems" + File.separator + newItemName + ".yml").exists()) {
/*  487: 429 */                   player.sendMessage(getResponse("ErrorMessages.ItemAlreadyExistsError"));
/*  488:     */                 } else {
/*  489: 431 */                   this.generateFromFile.exportWeapon(player, newItemName);
/*  490:     */                 }
/*  491:     */               }
/*  492:     */               else
/*  493:     */               {
/*  494: 434 */                 player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError") + " For example, /ils export " + ChatColor.DARK_RED + "Warbringer");
/*  495:     */               }
/*  496:     */             }
/*  497:     */             else {
/*  498: 437 */               player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  499:     */             }
/*  500:     */           }
/*  501:     */           else
/*  502:     */           {
/*  503: 441 */             System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/*  504:     */           }
/*  505:     */         }
/*  506: 444 */         if (args[0].equalsIgnoreCase("lore")) {
/*  507: 445 */           if ((sender instanceof Player))
/*  508:     */           {
/*  509: 446 */             if (args.length > 1)
/*  510:     */             {
/*  511: 447 */               if (Bukkit.getServer().getPlayer(args[1]) != null)
/*  512:     */               {
/*  513: 448 */                 Player player = Bukkit.getServer().getPlayer(args[1]);
/*  514: 449 */                 if (player.getItemInHand() != null)
/*  515:     */                 {
/*  516: 450 */                   if (player.getItemInHand().getType() != Material.AIR)
/*  517:     */                   {
/*  518: 451 */                     if (sender.hasPermission("ils.admin"))
/*  519:     */                     {
/*  520: 452 */                       if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  521:     */                       {
/*  522: 453 */                         if (args.length > 2)
/*  523:     */                         {
/*  524: 454 */                           if (isInteger(args[2]))
/*  525:     */                           {
/*  526: 455 */                             if (args.length > 3)
/*  527:     */                             {
/*  528: 456 */                               if (args.length > 4)
/*  529:     */                               {
/*  530: 458 */                                 String newLineText = "";
/*  531:     */                                 List<String> getItemLore;
/*  532:     */                                 List<String> getItemLore;
/*  533: 460 */                                 if (player.getItemInHand().getItemMeta().hasLore()) {
/*  534: 461 */                                   getItemLore = player.getItemInHand().getItemMeta().getLore();
/*  535:     */                                 } else {
/*  536: 463 */                                   getItemLore = new ArrayList();
/*  537:     */                                 }
/*  538: 466 */                                 List storeItemLore = getItemLore;
/*  539: 468 */                                 if (Integer.parseInt(args[2]) - 1 >= getItemLore.size())
/*  540:     */                                 {
/*  541: 470 */                                   ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  542: 471 */                                   ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  543: 473 */                                   for (int i = 0; i < args.length; i++) {
/*  544: 474 */                                     if (i >= 4) {
/*  545: 475 */                                       newLineText = newLineText + " " + args[i];
/*  546:     */                                     } else {
/*  547: 477 */                                       newLineText = args[i];
/*  548:     */                                     }
/*  549:     */                                   }
/*  550: 481 */                                   storeItemLore.add(this.util_Colours.replaceTooltipColour(newLineText));
/*  551:     */                                   
/*  552: 483 */                                   getItemInHandMeta.setLore(storeItemLore);
/*  553:     */                                   
/*  554: 485 */                                   getItemInHand.setItemMeta(getItemInHandMeta);
/*  555: 487 */                                   if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
/*  556: 488 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getItemMeta().getDisplayName());
/*  557:     */                                   } else {
/*  558: 490 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getType().name().toString());
/*  559:     */                                   }
/*  560: 493 */                                   player.getInventory().remove(player.getInventory().getItemInHand());
/*  561: 494 */                                   player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  562:     */                                 }
/*  563:     */                                 else
/*  564:     */                                 {
/*  565: 498 */                                   int lineNumber = Integer.parseInt(args[2]);
/*  566:     */                                   
/*  567: 500 */                                   ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  568: 501 */                                   ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  569: 503 */                                   for (int i = 0; i < args.length; i++) {
/*  570: 504 */                                     if (i >= 4) {
/*  571: 505 */                                       newLineText = newLineText + " " + args[i];
/*  572:     */                                     } else {
/*  573: 507 */                                       newLineText = args[i];
/*  574:     */                                     }
/*  575:     */                                   }
/*  576: 511 */                                   storeItemLore.set(lineNumber - 1, this.util_Colours.replaceTooltipColour(newLineText));
/*  577:     */                                   
/*  578: 513 */                                   getItemInHandMeta.setLore(storeItemLore);
/*  579:     */                                   
/*  580: 515 */                                   getItemInHand.setItemMeta(getItemInHandMeta);
/*  581: 517 */                                   if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  582:     */                                   {
/*  583: 518 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getItemMeta().getDisplayName());
/*  584: 519 */                                     final Player playerFinal = player;
/*  585: 520 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  586:     */                                     {
/*  587:     */                                       public void run()
/*  588:     */                                       {
/*  589: 522 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  590: 523 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  591: 524 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  592:     */                                       }
/*  593: 524 */                                     }, 2L);
/*  594:     */                                   }
/*  595:     */                                   else
/*  596:     */                                   {
/*  597: 529 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getType().name().toString());
/*  598: 530 */                                     final Player playerFinal = player;
/*  599: 531 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  600:     */                                     {
/*  601:     */                                       public void run()
/*  602:     */                                       {
/*  603: 533 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  604: 534 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  605: 535 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  606:     */                                       }
/*  607: 535 */                                     }, 2L);
/*  608:     */                                   }
/*  609: 541 */                                   player.getInventory().remove(player.getItemInHand());
/*  610: 542 */                                   player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  611:     */                                 }
/*  612:     */                               }
/*  613:     */                               else
/*  614:     */                               {
/*  615: 545 */                                 player.sendMessage(ChatColor.RED + "You need to give the stat a value. For example, /ils lore " + args[2] + " " + "Damage: " + ChatColor.DARK_RED + "+15");
/*  616:     */                               }
/*  617:     */                             }
/*  618:     */                             else {
/*  619: 548 */                               player.sendMessage(ChatColor.RED + "You need a stat to add. For example, /ils lore " + args[1] + " " + args[2] + " " + ChatColor.DARK_RED + "Damage: +15");
/*  620:     */                             }
/*  621:     */                           }
/*  622:     */                           else {
/*  623: 551 */                             player.sendMessage(ChatColor.RED + args[2] + " is not a line number. For example, /ils lore " + ChatColor.DARK_RED + "1");
/*  624:     */                           }
/*  625:     */                         }
/*  626:     */                         else {
/*  627: 554 */                           player.sendMessage(ChatColor.RED + "You need a line number and a stat. For example, /ils lore " + ChatColor.DARK_RED + "1 " + "Damage: +15");
/*  628:     */                         }
/*  629:     */                       }
/*  630:     */                       else {
/*  631: 557 */                         player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError") + " For example, " + ChatColor.DARK_RED + "/ils name Cursed Sword");
/*  632:     */                       }
/*  633:     */                     }
/*  634:     */                     else {
/*  635: 560 */                       player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  636:     */                     }
/*  637:     */                   }
/*  638:     */                   else {
/*  639: 563 */                     player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  640:     */                   }
/*  641:     */                 }
/*  642:     */                 else {
/*  643: 566 */                   player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  644:     */                 }
/*  645:     */               }
/*  646:     */               else
/*  647:     */               {
/*  648: 569 */                 Player player = (Player)sender;
/*  649: 570 */                 if (player.getItemInHand() != null)
/*  650:     */                 {
/*  651: 571 */                   if (player.getItemInHand().getType() != Material.AIR)
/*  652:     */                   {
/*  653: 572 */                     if (sender.hasPermission("ils.admin"))
/*  654:     */                     {
/*  655: 573 */                       if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  656:     */                       {
/*  657: 574 */                         if (args.length > 1)
/*  658:     */                         {
/*  659: 575 */                           if (isInteger(args[1]))
/*  660:     */                           {
/*  661: 576 */                             if (args.length > 2)
/*  662:     */                             {
/*  663: 577 */                               if (args.length > 3)
/*  664:     */                               {
/*  665: 579 */                                 String newLineText = "";
/*  666:     */                                 List<String> getItemLore;
/*  667:     */                                 List<String> getItemLore;
/*  668: 581 */                                 if (player.getItemInHand().getItemMeta().hasLore()) {
/*  669: 582 */                                   getItemLore = player.getItemInHand().getItemMeta().getLore();
/*  670:     */                                 } else {
/*  671: 584 */                                   getItemLore = new ArrayList();
/*  672:     */                                 }
/*  673: 587 */                                 List storeItemLore = getItemLore;
/*  674: 589 */                                 if (Integer.parseInt(args[1]) - 1 >= getItemLore.size())
/*  675:     */                                 {
/*  676: 591 */                                   ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  677: 592 */                                   ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  678: 594 */                                   for (int i = 0; i < args.length; i++) {
/*  679: 595 */                                     if (i >= 3) {
/*  680: 596 */                                       newLineText = newLineText + " " + args[i];
/*  681:     */                                     } else {
/*  682: 598 */                                       newLineText = args[i];
/*  683:     */                                     }
/*  684:     */                                   }
/*  685: 602 */                                   storeItemLore.add(this.util_Colours.replaceTooltipColour(newLineText));
/*  686:     */                                   
/*  687: 604 */                                   getItemInHandMeta.setLore(storeItemLore);
/*  688:     */                                   
/*  689: 606 */                                   getItemInHand.setItemMeta(getItemInHandMeta);
/*  690: 608 */                                   if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  691:     */                                   {
/*  692: 609 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getItemMeta().getDisplayName());
/*  693: 610 */                                     final Player playerFinal = player;
/*  694: 611 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  695:     */                                     {
/*  696:     */                                       public void run()
/*  697:     */                                       {
/*  698: 613 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  699: 614 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  700: 615 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  701:     */                                       }
/*  702: 615 */                                     }, 2L);
/*  703:     */                                   }
/*  704:     */                                   else
/*  705:     */                                   {
/*  706: 620 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getType().name().toString());
/*  707: 621 */                                     final Player playerFinal = player;
/*  708: 622 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  709:     */                                     {
/*  710:     */                                       public void run()
/*  711:     */                                       {
/*  712: 624 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  713: 625 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  714: 626 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  715:     */                                       }
/*  716: 626 */                                     }, 2L);
/*  717:     */                                   }
/*  718: 632 */                                   player.getInventory().remove(player.getItemInHand());
/*  719: 633 */                                   player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  720:     */                                 }
/*  721:     */                                 else
/*  722:     */                                 {
/*  723: 637 */                                   int lineNumber = Integer.parseInt(args[1]);
/*  724:     */                                   
/*  725: 639 */                                   ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  726: 640 */                                   ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  727: 642 */                                   for (int i = 0; i < args.length; i++) {
/*  728: 643 */                                     if (i >= 3) {
/*  729: 644 */                                       newLineText = newLineText + " " + args[i];
/*  730:     */                                     } else {
/*  731: 646 */                                       newLineText = args[i];
/*  732:     */                                     }
/*  733:     */                                   }
/*  734: 650 */                                   storeItemLore.set(lineNumber - 1, this.util_Colours.replaceTooltipColour(newLineText));
/*  735:     */                                   
/*  736: 652 */                                   getItemInHandMeta.setLore(storeItemLore);
/*  737:     */                                   
/*  738: 654 */                                   getItemInHand.setItemMeta(getItemInHandMeta);
/*  739: 656 */                                   if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  740:     */                                   {
/*  741: 657 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getItemMeta().getDisplayName());
/*  742: 658 */                                     final Player playerFinal = player;
/*  743: 659 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  744:     */                                     {
/*  745:     */                                       public void run()
/*  746:     */                                       {
/*  747: 661 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  748: 662 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  749: 663 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  750:     */                                       }
/*  751: 663 */                                     }, 2L);
/*  752:     */                                   }
/*  753:     */                                   else
/*  754:     */                                   {
/*  755: 668 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getType().name());
/*  756: 669 */                                     final Player playerFinal = player;
/*  757: 670 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  758:     */                                     {
/*  759:     */                                       public void run()
/*  760:     */                                       {
/*  761: 672 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  762: 673 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  763: 674 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  764:     */                                       }
/*  765: 674 */                                     }, 2L);
/*  766:     */                                   }
/*  767: 680 */                                   player.getInventory().remove(player.getItemInHand());
/*  768: 681 */                                   player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  769:     */                                 }
/*  770:     */                               }
/*  771:     */                               else
/*  772:     */                               {
/*  773: 685 */                                 player.sendMessage(ChatColor.RED + "You need to give the stat a value. For example, /ils lore " + args[1] + " " + "Damage: " + ChatColor.DARK_RED + "+15");
/*  774:     */                               }
/*  775:     */                             }
/*  776:     */                             else {
/*  777: 688 */                               player.sendMessage(ChatColor.RED + "You need a stat to add. For example, /ils lore " + args[1] + " " + ChatColor.DARK_RED + "Damage: +15");
/*  778:     */                             }
/*  779:     */                           }
/*  780:     */                           else {
/*  781: 691 */                             player.sendMessage(ChatColor.RED + args[1] + " is not a line number. For example, /ils lore " + ChatColor.DARK_RED + "1 " + ChatColor.RED + "Damage: +15");
/*  782:     */                           }
/*  783:     */                         }
/*  784:     */                         else {
/*  785: 694 */                           player.sendMessage(ChatColor.RED + "You need a line number and a stat. For example, /ils lore " + ChatColor.DARK_RED + "1 " + "Damage: +15");
/*  786:     */                         }
/*  787:     */                       }
/*  788:     */                       else {
/*  789: 697 */                         player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError") + " For example, " + ChatColor.DARK_RED + "/ils name Cursed Sword");
/*  790:     */                       }
/*  791:     */                     }
/*  792:     */                     else {
/*  793: 700 */                       player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  794:     */                     }
/*  795:     */                   }
/*  796:     */                   else {
/*  797: 703 */                     player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  798:     */                   }
/*  799:     */                 }
/*  800:     */                 else {
/*  801: 706 */                   player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  802:     */                 }
/*  803:     */               }
/*  804:     */             }
/*  805:     */             else {
/*  806: 710 */               sender.sendMessage(ChatColor.RED + "You need a line number or player name. For example, /ils lore " + ChatColor.DARK_RED + "1" + ChatColor.RED + " or /ils lore " + ChatColor.DARK_RED + sender.getName());
/*  807:     */             }
/*  808:     */           }
/*  809:     */           else {
/*  810: 713 */             System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/*  811:     */           }
/*  812:     */         }
/*  813: 717 */         if (args.length == 1)
/*  814:     */         {
/*  815: 718 */           if (args[0].equalsIgnoreCase("repair"))
/*  816:     */           {
/*  817: 719 */             if ((sender instanceof Player))
/*  818:     */             {
/*  819: 720 */               Player player = (Player)sender;
/*  820: 722 */               if (!player.hasPermission("ils.admin")) {
/*  821: 723 */                 player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  822: 725 */               } else if (player.getItemInHand() != null)
/*  823:     */               {
/*  824: 726 */                 if (player.getItemInHand().getType() != Material.AIR)
/*  825:     */                 {
/*  826:     */                   List<String> splitItemLore;
/*  827: 727 */                   if (player.getItemInHand().getItemMeta().hasLore())
/*  828:     */                   {
/*  829: 729 */                     splitItemLore = player.getItemInHand().getItemMeta().getLore();
/*  830: 731 */                     for (String getItemStat : splitItemLore)
/*  831:     */                     {
/*  832: 733 */                       String durabilityAmountColour = "";
/*  833: 734 */                       String prefixColourOnly = "";
/*  834: 735 */                       String durabilityRebuilder = "";
/*  835: 737 */                       if (ChatColor.stripColor(getItemStat).startsWith(plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "")))
/*  836:     */                       {
/*  837: 739 */                         int maxAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[1]).replaceAll("&([0-9a-f])", ""));
/*  838: 740 */                         int index = splitItemLore.indexOf(getItemStat);
/*  839: 742 */                         if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)).contains("&"))
/*  840:     */                         {
/*  841: 743 */                           if (getItemStat.length() > 4)
/*  842:     */                           {
/*  843: 744 */                             if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(2, 4)).contains("&")) {
/*  844: 745 */                               prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(2, 4)));
/*  845:     */                             } else {
/*  846: 747 */                               prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)));
/*  847:     */                             }
/*  848:     */                           }
/*  849:     */                           else {
/*  850: 750 */                             prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)));
/*  851:     */                           }
/*  852:     */                         }
/*  853:     */                         else {
/*  854: 753 */                           prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  855:     */                         }
/*  856: 756 */                         if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  857:     */                         {
/*  858: 757 */                           if (getItemStat.split("/")[1].trim().length() > 4)
/*  859:     */                           {
/*  860: 758 */                             if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  861: 759 */                               durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(2, 4);
/*  862:     */                             } else {
/*  863: 761 */                               durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2);
/*  864:     */                             }
/*  865:     */                           }
/*  866:     */                           else {
/*  867: 764 */                             durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2);
/*  868:     */                           }
/*  869:     */                         }
/*  870:     */                         else {
/*  871: 767 */                           durabilityAmountColour = prefixColourOnly;
/*  872:     */                         }
/*  873: 770 */                         durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "") + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maxAmount + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maxAmount;
/*  874:     */                         
/*  875: 772 */                         splitItemLore.set(index, durabilityRebuilder);
/*  876:     */                         
/*  877: 774 */                         ItemStack repairedItem = new ItemStack(player.getItemInHand());
/*  878: 775 */                         ItemMeta repairedItemMeta = repairedItem.getItemMeta();
/*  879:     */                         
/*  880: 777 */                         repairedItemMeta.setLore(splitItemLore);
/*  881: 778 */                         repairedItem.setItemMeta(repairedItemMeta);
/*  882:     */                         
/*  883: 780 */                         player.setItemInHand(repairedItem);
/*  884:     */                         
/*  885: 782 */                         player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/*  886:     */                       }
/*  887:     */                     }
/*  888:     */                   }
/*  889:     */                   else
/*  890:     */                   {
/*  891: 786 */                     player.sendMessage(getResponse("ErrorMessages.NoLoreError"));
/*  892:     */                   }
/*  893:     */                 }
/*  894:     */                 else
/*  895:     */                 {
/*  896: 789 */                   player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  897:     */                 }
/*  898:     */               }
/*  899:     */               else {
/*  900: 792 */                 player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  901:     */               }
/*  902: 795 */               return true;
/*  903:     */             }
/*  904: 797 */             System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/*  905:     */           }
/*  906: 800 */           if (args[0].equalsIgnoreCase("reload"))
/*  907:     */           {
/*  908: 801 */             if ((sender instanceof Player))
/*  909:     */             {
/*  910: 802 */               Player player = (Player)sender;
/*  911: 804 */               if (!player.hasPermission("ils.admin"))
/*  912:     */               {
/*  913: 805 */                 player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  914:     */               }
/*  915:     */               else
/*  916:     */               {
/*  917: 807 */                 reloadConfig();
/*  918: 808 */                 player.sendMessage(ChatColor.GOLD + "[ItemLoreStats] " + ChatColor.GREEN + " Configuration Reloaded!");
/*  919:     */               }
/*  920: 810 */               return true;
/*  921:     */             }
/*  922: 812 */             reloadConfig();
/*  923: 813 */             System.out.println("[ItemLoreStats] Configuration Reloaded!");
/*  924:     */           }
/*  925: 816 */           if (args[0].equalsIgnoreCase("createlore"))
/*  926:     */           {
/*  927: 817 */             if ((sender instanceof Player))
/*  928:     */             {
/*  929: 818 */               Player player = (Player)sender;
/*  930: 819 */               if ((sender.isOp()) || (sender.hasPermission("ils.admin")))
/*  931:     */               {
/*  932: 821 */                 ItemStack debugItem = new ItemStack(Material.LEATHER_HELMET, 1);
/*  933:     */                 
/*  934: 823 */                 ItemMeta debugItemMeta = debugItem.getItemMeta();
/*  935:     */                 
/*  936: 825 */                 ArrayList debugItemList = new ArrayList();
/*  937: 826 */                 debugItemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Fire-Chanter Wrap");
/*  938: 827 */                 debugItemList.add("");
/*  939: 828 */                 debugItemList.add(ChatColor.AQUA + armour + ": " + ChatColor.DARK_GREEN + "2" + ChatColor.GREEN + "%");
/*  940: 829 */                 debugItemList.add(ChatColor.AQUA + health + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "45");
/*  941: 830 */                 debugItemList.add(ChatColor.AQUA + healthRegen + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  942: 831 */                 debugItemList.add(ChatColor.RED + fire + ": " + ChatColor.DARK_GREEN + "9" + ChatColor.GREEN + "%");
/*  943: 832 */                 debugItemList.add(ChatColor.DARK_RED + wither + ": " + ChatColor.DARK_GREEN + "4" + ChatColor.GREEN + "%");
/*  944: 833 */                 debugItemList.add("");
/*  945: 834 */                 debugItemList.add(ChatColor.GOLD + durability + ": " + "1250/1250");
/*  946: 835 */                 debugItemList.add("");
/*  947: 836 */                 debugItemList.add(ChatColor.DARK_AQUA + soulbound + " " + player.getName());
/*  948: 837 */                 debugItemMeta.setLore(debugItemList);
/*  949: 838 */                 debugItem.setItemMeta(debugItemMeta);
/*  950: 839 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem) });
/*  951:     */                 
/*  952: 841 */                 ItemStack debugItem1 = new ItemStack(Material.IRON_CHESTPLATE, 1);
/*  953: 842 */                 ItemMeta debugItemMeta1 = debugItem1.getItemMeta();
/*  954: 843 */                 ArrayList debugItemList1 = new ArrayList();
/*  955: 844 */                 debugItemMeta1.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Revenant Chestplate of Health");
/*  956: 845 */                 debugItemList1.add("");
/*  957: 846 */                 debugItemList1.add(ChatColor.AQUA + armour + ": " + ChatColor.DARK_GREEN + "5" + ChatColor.GREEN + "%");
/*  958: 847 */                 debugItemList1.add(ChatColor.AQUA + health + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "109");
/*  959: 848 */                 debugItemList1.add(ChatColor.AQUA + healthRegen + ": " + ChatColor.DARK_GREEN + "4" + ChatColor.GREEN + "%");
/*  960: 849 */                 debugItemList1.add(ChatColor.LIGHT_PURPLE + poison + ": " + ChatColor.DARK_GREEN + "2" + ChatColor.GREEN + "%");
/*  961: 850 */                 debugItemList1.add(ChatColor.GREEN + lifeSteal + ": " + ChatColor.DARK_GREEN + "4" + ChatColor.GREEN + "%");
/*  962: 851 */                 debugItemList1.add("");
/*  963: 852 */                 debugItemList1.add(ChatColor.GOLD + durability + ": " + "1750/1750");
/*  964: 853 */                 debugItemList1.add("");
/*  965: 854 */                 debugItemList1.add(ChatColor.DARK_AQUA + xplevel + ": 2");
/*  966: 855 */                 debugItemMeta1.setLore(debugItemList1);
/*  967: 856 */                 debugItem1.setItemMeta(debugItemMeta1);
/*  968: 857 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem1) });
/*  969:     */                 
/*  970: 859 */                 ItemStack debugItem2 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
/*  971: 860 */                 ItemMeta debugItemMeta2 = debugItem2.getItemMeta();
/*  972: 861 */                 ArrayList debugItemList2 = new ArrayList();
/*  973: 862 */                 debugItemMeta2.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "DragonScale Leg Wraps");
/*  974: 863 */                 debugItemList2.add("");
/*  975: 864 */                 debugItemList2.add(ChatColor.AQUA + armour + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  976: 865 */                 debugItemList2.add(ChatColor.AQUA + health + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "59");
/*  977: 866 */                 debugItemList2.add(ChatColor.AQUA + healthRegen + ": " + ChatColor.DARK_GREEN + "2" + ChatColor.GREEN + "%");
/*  978: 867 */                 debugItemList2.add(ChatColor.RED + fire + ": " + ChatColor.DARK_GREEN + "5" + ChatColor.GREEN + "%");
/*  979: 868 */                 debugItemList2.add(ChatColor.GREEN + lifeSteal + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  980: 869 */                 debugItemList2.add("");
/*  981: 870 */                 debugItemList2.add(ChatColor.GOLD + durability + ": " + "1500/1500");
/*  982: 871 */                 debugItemList2.add("");
/*  983: 872 */                 debugItemList2.add(ChatColor.DARK_AQUA + xplevel + ": 3");
/*  984: 873 */                 debugItemMeta2.setLore(debugItemList2);
/*  985: 874 */                 debugItem2.setItemMeta(debugItemMeta2);
/*  986: 875 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem2) });
/*  987:     */                 
/*  988: 877 */                 ItemStack debugItem3 = new ItemStack(Material.DIAMOND_BOOTS, 1);
/*  989: 878 */                 ItemMeta debugItemMeta3 = debugItem3.getItemMeta();
/*  990: 879 */                 ArrayList debugItemList3 = new ArrayList();
/*  991: 880 */                 debugItemMeta3.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Boots of the Black Glacier");
/*  992: 881 */                 debugItemList3.add("");
/*  993: 882 */                 debugItemList3.add(ChatColor.AQUA + armour + ": " + ChatColor.DARK_GREEN + "9" + ChatColor.GREEN + "%");
/*  994: 883 */                 debugItemList3.add(ChatColor.AQUA + health + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "97");
/*  995: 884 */                 debugItemList3.add(ChatColor.AQUA + healthRegen + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  996: 885 */                 debugItemList3.add(ChatColor.BLUE + ice + ": " + ChatColor.DARK_GREEN + "5" + ChatColor.GREEN + "%");
/*  997: 886 */                 debugItemList3.add(ChatColor.DARK_RED + wither + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  998: 887 */                 debugItemList3.add(ChatColor.YELLOW + movementspeed + ": " + ChatColor.DARK_GREEN + "8" + ChatColor.GREEN + "%");
/*  999: 888 */                 debugItemList3.add("");
/* 1000: 889 */                 debugItemList3.add(ChatColor.GOLD + durability + ": " + "1500/1500");
/* 1001: 890 */                 debugItemList3.add("");
/* 1002: 891 */                 debugItemList3.add(ChatColor.DARK_AQUA + xplevel + ": 4");
/* 1003: 892 */                 debugItemMeta3.setLore(debugItemList3);
/* 1004: 893 */                 debugItem3.setItemMeta(debugItemMeta3);
/* 1005: 894 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem3) });
/* 1006:     */                 
/* 1007: 896 */                 ItemStack debugItem4 = new ItemStack(Material.DIAMOND_SWORD, 1);
/* 1008: 897 */                 ItemMeta debugItemMeta4 = debugItem4.getItemMeta();
/* 1009: 898 */                 ArrayList debugItemList4 = new ArrayList();
/* 1010: 899 */                 debugItemMeta4.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Slaying Thrustblade");
/* 1011: 900 */                 debugItemList4.add("");
/* 1012: 901 */                 debugItemList4.add(ChatColor.AQUA + weaponspeed + ": " + ChatColor.RED + "Slow");
/* 1013: 902 */                 debugItemList4.add(ChatColor.AQUA + damage + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "68" + ChatColor.DARK_GREEN + "-" + ChatColor.DARK_GREEN + "93");
/* 1014: 903 */                 debugItemList4.add(ChatColor.AQUA + critChance + ": " + ChatColor.DARK_GREEN + "4" + ChatColor.GREEN + "%");
/* 1015: 904 */                 debugItemList4.add(ChatColor.AQUA + critDamage + ": " + ChatColor.DARK_GREEN + "12" + ChatColor.GREEN + "%");
/* 1016: 905 */                 debugItemList4.add(ChatColor.YELLOW + reflect + ": " + ChatColor.DARK_GREEN + "6" + ChatColor.GREEN + "%");
/* 1017: 906 */                 debugItemList4.add(ChatColor.RED + fire + ": " + ChatColor.DARK_GREEN + "7" + ChatColor.GREEN + "%");
/* 1018: 907 */                 debugItemList4.add(ChatColor.DARK_PURPLE + harming + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/* 1019: 908 */                 debugItemList4.add("");
/* 1020: 909 */                 debugItemList4.add(ChatColor.GOLD + durability + ": " + "350/350");
/* 1021: 910 */                 debugItemList4.add("");
/* 1022: 911 */                 debugItemList4.add(ChatColor.DARK_AQUA + "Level: 1");
/* 1023: 912 */                 debugItemList4.add(ChatColor.DARK_AQUA + soulbound + " " + player.getName());
/* 1024: 913 */                 debugItemMeta4.setLore(debugItemList4);
/* 1025: 914 */                 debugItem4.setItemMeta(debugItemMeta4);
/* 1026: 915 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem4) });
/* 1027:     */                 
/* 1028: 917 */                 player.sendMessage(ChatColor.RED + "[DEBUGGER] " + ChatColor.WHITE + "items created!");
/* 1029:     */               }
/* 1030:     */               else
/* 1031:     */               {
/* 1032: 919 */                 player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/* 1033:     */               }
/* 1034:     */             }
/* 1035:     */             else
/* 1036:     */             {
/* 1037: 922 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1038:     */             }
/* 1039:     */           }
/* 1040: 924 */           else if (args[0].equalsIgnoreCase("setMultiplier"))
/* 1041:     */           {
/* 1042: 925 */             if ((sender instanceof Player))
/* 1043:     */             {
/* 1044: 926 */               Player player = (Player)sender;
/* 1045: 927 */               if ((sender.isOp()) || (sender.hasPermission("ils.admin")))
/* 1046:     */               {
/* 1047: 928 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".healthMultiplier", Double.valueOf(0.045D));
/* 1048: 929 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".damageMultiplier", Double.valueOf(0.04D));
/* 1049: 930 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".location.x", Integer.valueOf(player.getLocation().getBlockX()));
/* 1050: 931 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".location.y", Integer.valueOf(player.getLocation().getBlockY()));
/* 1051: 932 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".location.z", Integer.valueOf(player.getLocation().getBlockZ()));
/* 1052: 933 */                 saveConfig();
/* 1053: 934 */                 player.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully set the NPC multiplier to multiply health and damage from " + ChatColor.GOLD + player.getLocation().getBlockX() + ChatColor.LIGHT_PURPLE + ", " + ChatColor.GOLD + player.getLocation().getBlockY() + ChatColor.LIGHT_PURPLE + ", " + ChatColor.GOLD + player.getLocation().getBlockZ() + ChatColor.LIGHT_PURPLE + ".");
/* 1054:     */               }
/* 1055:     */               else
/* 1056:     */               {
/* 1057: 936 */                 player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/* 1058:     */               }
/* 1059:     */             }
/* 1060:     */             else
/* 1061:     */             {
/* 1062: 939 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1063:     */             }
/* 1064:     */           }
/* 1065: 941 */           else if (args[0].equalsIgnoreCase("stats"))
/* 1066:     */           {
/* 1067: 942 */             if ((sender instanceof Player))
/* 1068:     */             {
/* 1069: 943 */               Player player = (Player)sender;
/* 1070: 944 */               this.characterSheet.returnStats(player, getHealthValue(player));
/* 1071:     */             }
/* 1072:     */             else
/* 1073:     */             {
/* 1074: 946 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1075:     */             }
/* 1076:     */           }
/* 1077: 948 */           else if (args[0].equalsIgnoreCase("health"))
/* 1078:     */           {
/* 1079: 949 */             if ((sender instanceof Player))
/* 1080:     */             {
/* 1081: 950 */               Player player = (Player)sender;
/* 1082: 951 */               player.sendMessage(ChatColor.RED + "[DEBUGGER] " + ChatColor.WHITE + player.getHealth() + " out of " + player.getMaxHealth());
/* 1083:     */             }
/* 1084:     */             else
/* 1085:     */             {
/* 1086: 953 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1087:     */             }
/* 1088:     */           }
/* 1089: 955 */           else if (args[0].equalsIgnoreCase("durability"))
/* 1090:     */           {
/* 1091: 956 */             if ((sender instanceof Player))
/* 1092:     */             {
/* 1093: 957 */               Player player = (Player)sender;
/* 1094: 958 */               player.sendMessage(ChatColor.RED + "[DEBUGGER] " + player.getItemInHand().getType().getMaxDurability());
/* 1095:     */             }
/* 1096:     */             else
/* 1097:     */             {
/* 1098: 960 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1099:     */             }
/* 1100:     */           }
/* 1101: 962 */           else if (args[0].equalsIgnoreCase("speed"))
/* 1102:     */           {
/* 1103: 963 */             if ((sender instanceof Player))
/* 1104:     */             {
/* 1105: 964 */               Player player = (Player)sender;
/* 1106: 965 */               player.sendMessage(ChatColor.RED + "[DEBUGGER] " + ChatColor.WHITE + player.getWalkSpeed());
/* 1107:     */             }
/* 1108:     */             else
/* 1109:     */             {
/* 1110: 967 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1111:     */             }
/* 1112:     */           }
/* 1113: 969 */           else if (args[0].equalsIgnoreCase("mob"))
/* 1114:     */           {
/* 1115: 970 */             if ((sender instanceof Player))
/* 1116:     */             {
/* 1117: 971 */               Player player = (Player)sender;
/* 1118: 972 */               if ((sender.isOp()) || (sender.hasPermission("ils.admin")))
/* 1119:     */               {
/* 1120: 973 */                 LivingEntity mob = (LivingEntity)player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
/* 1121: 974 */                 mob.setCustomName("Bob");
/* 1122: 975 */                 mob.setMaxHealth(25000.0D);
/* 1123: 976 */                 mob.setHealth(25000.0D);
/* 1124: 977 */                 System.out.println(mob.getHealth());
/* 1125: 978 */                 mob.getEquipment().setHelmet(player.getItemInHand());
/* 1126: 979 */                 mob.setCustomNameVisible(true);
/* 1127:     */               }
/* 1128:     */             }
/* 1129:     */             else
/* 1130:     */             {
/* 1131: 982 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1132:     */             }
/* 1133:     */           }
/* 1134: 984 */           else if (args[0].equalsIgnoreCase("update")) {
/* 1135: 987 */             if ((sender instanceof Player))
/* 1136:     */             {
/* 1137: 988 */               Player player = (Player)sender;
/* 1138: 989 */               if ((sender.isOp()) || (sender.hasPermission("ils.admin")))
/* 1139:     */               {
/* 1140: 991 */                 final Player playerFinal = player;
/* 1141: 992 */                 getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/* 1142:     */                 {
/* 1143:     */                   public void run()
/* 1144:     */                   {
/* 1145: 994 */                     playerFinal.sendMessage(ChatColor.GOLD + "Item Lore Stats" + ChatColor.GREEN + " update has successfully completed. To complete the update and ensure please stop the server and delete/backup the /SavedItems/ directory, the /SavedMobs/ directory and the 3 language files. Once that is completed you can start the server back up.");
/* 1146:     */                   }
/* 1147: 994 */                 }, 260L);
/* 1148:     */               }
/* 1149:     */             }
/* 1150:     */           }
/* 1151:     */         }
/* 1152:     */       }
/* 1153:     */     }
/* 1154:1005 */     return false;
/* 1155:     */   }
/* 1156:     */   
/* 1157:     */   public void debugMessage(Player player, String message)
/* 1158:     */   {
/* 1159:1009 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1160:     */     {
/* 1161:1010 */       if (!getConfig().getBoolean("debugMessages")) {
/* 1162:1010 */         return;
/* 1163:     */       }
/* 1164:1012 */       player.sendMessage(ChatColor.RED + "[DEBUGGER] " + ChatColor.WHITE + message);
/* 1165:     */     }
/* 1166:     */   }
/* 1167:     */   
/* 1168:     */   public double getHealthValue(Player player)
/* 1169:     */   {
/* 1170:1018 */     if (plugin.getConfig().getInt("baseHealth") == 0) {
/* 1171:1018 */       return 0.0D;
/* 1172:     */     }
/* 1173:1020 */     double health = 0.0D;
/* 1174:1022 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1175:     */     {
/* 1176:1023 */       if (plugin.getHeroes() != null)
/* 1177:     */       {
/* 1178:1024 */         double maxHealth = this.util_Heroes.getHeroBaseHealth(player) + this.util_Heroes.getHeroHealthPerLevel(player) * this.util_Heroes.getHeroLevel(player);
/* 1179:1026 */         if (isTool(player.getItemInHand().getType()))
/* 1180:     */         {
/* 1181:1027 */           double newHP = Double.valueOf(maxHealth + (this.gearStats.getHealthGear(player) + this.gearStats.getHealthItemInHand(player))).intValue();
/* 1182:     */           
/* 1183:1029 */           return newHP;
/* 1184:     */         }
/* 1185:1031 */         double newHP = Double.valueOf(maxHealth + this.gearStats.getHealthGear(player)).intValue();
/* 1186:     */         
/* 1187:1033 */         return newHP;
/* 1188:     */       }
/* 1189:1036 */       double maxHealth = getConfig().getDouble("baseHealth") + getConfig().getInt("healthPerLevel") * player.getLevel();
/* 1190:1038 */       if (isTool(player.getItemInHand().getType()))
/* 1191:     */       {
/* 1192:1039 */         double newHP = maxHealth + (this.gearStats.getHealthGear(player) + this.gearStats.getHealthItemInHand(player));
/* 1193:     */         
/* 1194:1041 */         return newHP;
/* 1195:     */       }
/* 1196:1043 */       double newHP = maxHealth + this.gearStats.getHealthGear(player);
/* 1197:     */       
/* 1198:1045 */       return newHP;
/* 1199:     */     }
/* 1200:1048 */     return health;
/* 1201:     */   }
/* 1202:     */   
/* 1203:     */   public void updateHealth(Player player)
/* 1204:     */   {
/* 1205:1052 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1206:     */     {
/* 1207:1053 */       double modifier = 0.0D;
/* 1208:1055 */       if (plugin.getHeroes() != null)
/* 1209:     */       {
/* 1210:1057 */         double maxHealth = player.getMaxHealth();
/* 1211:1058 */         if (isTool(player.getItemInHand().getType()))
/* 1212:     */         {
/* 1213:1059 */           double newHP = Double.valueOf(maxHealth + (this.gearStats.getHealthGear(player) + this.gearStats.getHealthItemInHand(player))).intValue();
/* 1214:     */           
/* 1215:1061 */           player.setMaxHealth(newHP);
/* 1216:     */         }
/* 1217:     */         else
/* 1218:     */         {
/* 1219:1063 */           double newHP = Double.valueOf(maxHealth + this.gearStats.getHealthGear(player)).intValue();
/* 1220:     */           
/* 1221:1065 */           player.setMaxHealth(newHP);
/* 1222:     */         }
/* 1223:     */       }
/* 1224:     */       else
/* 1225:     */       {
/* 1226:1069 */         if (plugin.getConfig().getInt("baseHealth") == 0) {
/* 1227:1069 */           return;
/* 1228:     */         }
/* 1229:1071 */         double maxHealth = player.getMaxHealth();
/* 1230:1073 */         if (isTool(player.getItemInHand().getType()))
/* 1231:     */         {
/* 1232:1074 */           double newHP = Double.valueOf(maxHealth + (this.gearStats.getHealthGear(player) + this.gearStats.getHealthItemInHand(player))).intValue();
/* 1233:     */           
/* 1234:1076 */           player.setMaxHealth(newHP);
/* 1235:     */         }
/* 1236:     */         else
/* 1237:     */         {
/* 1238:1078 */           double newHP = Double.valueOf(maxHealth + this.gearStats.getHealthGear(player)).intValue();
/* 1239:     */           
/* 1240:1080 */           player.setMaxHealth(newHP);
/* 1241:     */         }
/* 1242:     */       }
/* 1243:     */     }
/* 1244:     */   }
/* 1245:     */   
/* 1246:     */   public void updatePlayerSpeed(Player player)
/* 1247:     */   {
/* 1248:1089 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1249:     */     {
/* 1250:1090 */       final Player playerFinal = player;
/* 1251:1091 */       getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/* 1252:     */       {
/* 1253:     */         public void run()
/* 1254:     */         {
/* 1255:1094 */           float compressedModifier = 0.0F;
/* 1256:1096 */           if (ItemLoreStats.this.isTool(playerFinal.getItemInHand().getType()))
/* 1257:     */           {
/* 1258:1097 */             float maxSpeed = 0.99F;
/* 1259:1098 */             float speed = (float)(0.0020000000949949D * (ItemLoreStats.this.gearStats.getMovementSpeedGear(playerFinal) + ItemLoreStats.this.gearStats.getMovementSpeedItemInHand(playerFinal)) + compressedModifier + 0.2000000029802322D);
/* 1260:1100 */             if (speed > maxSpeed) {
/* 1261:1101 */               playerFinal.setWalkSpeed(maxSpeed);
/* 1262:     */             } else {
/* 1263:1103 */               playerFinal.setWalkSpeed(speed);
/* 1264:     */             }
/* 1265:1105 */             ItemLoreStats.this.debugMessage(playerFinal, "Speed updated.");
/* 1266:     */           }
/* 1267:     */           else
/* 1268:     */           {
/* 1269:1107 */             float maxSpeed = 0.99F;
/* 1270:1108 */             float speed = (float)(0.0020000000949949D * ItemLoreStats.this.gearStats.getMovementSpeedGear(playerFinal) + compressedModifier + 0.2000000029802322D);
/* 1271:1110 */             if (speed > maxSpeed) {
/* 1272:1111 */               playerFinal.setWalkSpeed(maxSpeed);
/* 1273:     */             } else {
/* 1274:1113 */               playerFinal.setWalkSpeed(speed);
/* 1275:     */             }
/* 1276:     */           }
/* 1277:     */         }
/* 1278:1113 */       }, 2L);
/* 1279:     */     }
/* 1280:     */     else
/* 1281:     */     {
/* 1282:1119 */       player.setWalkSpeed(0.2F);
/* 1283:     */     }
/* 1284:     */   }
/* 1285:     */   
/* 1286:     */   public void removeWeaponSpeedEffects(Player player)
/* 1287:     */   {
/* 1288:1124 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName())) {
/* 1289:1125 */       if (player.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 1290:1126 */         player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 1291:1127 */       } else if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 1292:1128 */         player.removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 1293:     */       }
/* 1294:     */     }
/* 1295:     */   }
/* 1296:     */   
/* 1297:     */   public class ItemLoreStatsListener
/* 1298:     */     implements Listener
/* 1299:     */   {
/* 1300:1134 */     private List<Integer> tempButton = new ArrayList();
/* 1301:1135 */     private ItemStack button = new ItemStack(Material.STONE_BUTTON);
/* 1302:     */     
/* 1303:     */     public ItemLoreStatsListener() {}
/* 1304:     */     
/* 1305:     */     @EventHandler
/* 1306:     */     public void onRegenHealth(EntityRegainHealthEvent event)
/* 1307:     */     {
/* 1308:1144 */       if ((event.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.SATIATED)) && ((event.getEntity() instanceof Player)))
/* 1309:     */       {
/* 1310:1147 */         Player player = (Player)event.getEntity();
/* 1311:1148 */         if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1312:     */         {
/* 1313:1150 */           if (ItemLoreStats.plugin.getConfig().getDouble("baseHealthRegen") == 0.0D) {
/* 1314:1150 */             return;
/* 1315:     */           }
/* 1316:1152 */           double gearRegen = 0.0D;
/* 1317:1153 */           double modifier = 0.0D;
/* 1318:1155 */           if (ItemLoreStats.this.isTool(player.getItemInHand().getType())) {
/* 1319:1156 */             gearRegen = ItemLoreStats.this.gearStats.getHealthRegenGear(player) + ItemLoreStats.this.gearStats.getHealthRegenItemInHand(player);
/* 1320:     */           } else {
/* 1321:1158 */             gearRegen = ItemLoreStats.this.gearStats.getHealthRegenGear(player);
/* 1322:     */           }
/* 1323:1161 */           double baseRegen = ItemLoreStats.this.getConfig().getDouble("baseHealthRegen");
/* 1324:1162 */           double modifiedHealthRegen = player.getMaxHealth() / 100.0D * (gearRegen + baseRegen + modifier);
/* 1325:     */           
/* 1326:1164 */           event.setAmount(modifiedHealthRegen);
/* 1327:     */           
/* 1328:1166 */           ItemLoreStats.this.debugMessage(player, "Health regenerating. Currently " + ChatColor.RED + Math.round(player.getHealth()) + ChatColor.RESET + "/" + ChatColor.GREEN + Math.round(player.getMaxHealth()) + ".");
/* 1329:     */         }
/* 1330:     */       }
/* 1331:     */     }
/* 1332:     */     
/* 1333:     */     @EventHandler
/* 1334:     */     public void onPlayerRespawn(PlayerRespawnEvent event)
/* 1335:     */     {
/* 1336:1174 */       Player player = event.getPlayer();
/* 1337:1176 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1338:     */       {
/* 1339:1177 */         final Player playerFinal = player;
/* 1340:1178 */         ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1341:     */         {
/* 1342:     */           public void run()
/* 1343:     */           {
/* 1344:1180 */             ItemLoreStats.this.updateHealth(playerFinal);
/* 1345:1181 */             ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/* 1346:1182 */             ItemLoreStats.this.setBonuses.updateSetBonus(playerFinal);
/* 1347:1183 */             ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/* 1348:     */           }
/* 1349:1183 */         }, 2L);
/* 1350:     */         
/* 1351:     */ 
/* 1352:     */ 
/* 1353:1187 */         ItemLoreStats.this.debugMessage(player, "Health updated.");
/* 1354:     */       }
/* 1355:     */     }
/* 1356:     */     
/* 1357:     */     @EventHandler
/* 1358:     */     public void onPlayerPickupExp(PlayerExpChangeEvent event)
/* 1359:     */     {
/* 1360:1193 */       Player player = event.getPlayer();
/* 1361:1194 */       if (ItemLoreStats.this.isTool(player.getItemInHand().getType()))
/* 1362:     */       {
/* 1363:1195 */         if (ItemLoreStats.this.gearStats.getXPMultiplierGear(player) + ItemLoreStats.this.gearStats.getXPMultiplierItemInHand(player) > 0.0D)
/* 1364:     */         {
/* 1365:1196 */           double bonusExp = 0.0D;
/* 1366:1197 */           double xpMultiplier = ItemLoreStats.this.gearStats.getXPMultiplierGear(player) + ItemLoreStats.this.gearStats.getXPMultiplierItemInHand(player);
/* 1367:     */           
/* 1368:1199 */           bonusExp = event.getAmount() * xpMultiplier / 100.0D;
/* 1369:1200 */           player.giveExp((int)bonusExp);
/* 1370:     */         }
/* 1371:     */       }
/* 1372:1203 */       else if (ItemLoreStats.this.gearStats.getXPMultiplierGear(player) > 0.0D)
/* 1373:     */       {
/* 1374:1204 */         double bonusExp = 0.0D;
/* 1375:1205 */         double xpMultiplier = ItemLoreStats.this.gearStats.getXPMultiplierGear(player);
/* 1376:     */         
/* 1377:1207 */         bonusExp = event.getAmount() * xpMultiplier / 100.0D;
/* 1378:1208 */         player.giveExp((int)bonusExp);
/* 1379:     */       }
/* 1380:     */     }
/* 1381:     */     
/* 1382:     */     @EventHandler
/* 1383:     */     public void onPlayerLevel(PlayerLevelChangeEvent event)
/* 1384:     */     {
/* 1385:1213 */       ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1386:     */     }
/* 1387:     */     
/* 1388:     */     @EventHandler
/* 1389:     */     public void onPlayerJoin(PlayerJoinEvent event)
/* 1390:     */     {
/* 1391:1219 */       final Player playerFinal = event.getPlayer();
/* 1392:1220 */       ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1393:     */       {
/* 1394:     */         public void run()
/* 1395:     */         {
/* 1396:1222 */           ItemLoreStats.this.updateHealth(playerFinal);
/* 1397:1223 */           ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/* 1398:1224 */           ItemLoreStats.this.setBonuses.updateSetBonus(playerFinal);
/* 1399:1225 */           ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/* 1400:1226 */           playerFinal.setHealth(playerFinal.getMaxHealth());
/* 1401:     */         }
/* 1402:1226 */       }, 2L);
/* 1403:     */     }
/* 1404:     */     
/* 1405:     */     @EventHandler
/* 1406:     */     public void onDropItemEvent(PlayerDropItemEvent event)
/* 1407:     */     {
/* 1408:1235 */       Player player = event.getPlayer();
/* 1409:1236 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1410:     */       {
/* 1411:1237 */         final Player playerFinal = player;
/* 1412:1238 */         ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1413:     */         {
/* 1414:     */           public void run()
/* 1415:     */           {
/* 1416:1240 */             ItemLoreStats.this.updateHealth(playerFinal);
/* 1417:1241 */             ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/* 1418:1242 */             ItemLoreStats.this.setBonuses.updateSetBonus(playerFinal);
/* 1419:1243 */             ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/* 1420:     */           }
/* 1421:1243 */         }, 2L);
/* 1422:     */       }
/* 1423:     */     }
/* 1424:     */     
/* 1425:     */     public void swapItems(int slotA, int slotB, Inventory inv)
/* 1426:     */     {
/* 1427:1251 */       ItemStack itemStackA = inv.getItem(slotA);
/* 1428:1252 */       ItemStack itemStackB = inv.getItem(slotB);
/* 1429:1253 */       inv.setItem(slotA, itemStackB);
/* 1430:1254 */       inv.setItem(slotB, itemStackA);
/* 1431:     */     }
/* 1432:     */     
/* 1433:     */     @EventHandler
/* 1434:     */     public void onPlayerHeldItemChange(final PlayerItemHeldEvent event)
/* 1435:     */     {
/* 1436:1260 */       Player player = event.getPlayer();
/* 1437:1262 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1438:     */       {
/* 1439:1264 */         final Player playerFinal = event.getPlayer();
/* 1440:1265 */         ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1441:     */         {
/* 1442:     */           public void run()
/* 1443:     */           {
/* 1444:1268 */             ItemStack checkItemHeld = playerFinal.getInventory().getItem(event.getNewSlot());
/* 1445:1270 */             if ((checkItemHeld != null) && (checkItemHeld.getType() != Material.AIR) && (checkItemHeld.getItemMeta() != null) && (checkItemHeld.getItemMeta().getLore() != null) && (ItemLoreStats.this.isTool(checkItemHeld.getType())))
/* 1446:     */             {
/* 1447:1276 */               String weaponSpeed = "";
/* 1448:1277 */               String strippedWeaponSpeed = ItemLoreStats.this.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/* 1449:1279 */               if ((ItemLoreStats.this.gearStats.playerHeldItemChangeSoulboundNameItemInHand(checkItemHeld) != null) && (!ItemLoreStats.this.gearStats.playerHeldItemChangeSoulboundNameItemInHand(checkItemHeld).equals(event.getPlayer().getName())))
/* 1450:     */               {
/* 1451:1281 */                 ItemLoreStats.ItemLoreStatsListener.this.swapItems(event.getNewSlot(), event.getPreviousSlot(), playerFinal.getInventory());
/* 1452:1282 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1453:1283 */                 ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand is bound to someone else. Item in hand bound to: " + ItemLoreStats.this.gearStats.playerHeldItemChangeSoulboundNameItemInHand(checkItemHeld));
/* 1454:1284 */                 return;
/* 1455:     */               }
/* 1456:1287 */               if ((ItemLoreStats.this.gearStats.playerHeldItemChangeClassItemInHand(checkItemHeld) != null) && (!event.getPlayer().hasPermission("ils.use." + ItemLoreStats.this.gearStats.playerHeldItemChangeClassItemInHand(checkItemHeld))))
/* 1457:     */               {
/* 1458:1289 */                 ItemLoreStats.ItemLoreStatsListener.this.swapItems(event.getNewSlot(), event.getPreviousSlot(), playerFinal.getInventory());
/* 1459:1290 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1460:1291 */                 ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand can only be used by players with the " + ItemLoreStats.this.gearStats.playerHeldItemChangeClassItemInHand(checkItemHeld) + " permission node.");
/* 1461:1292 */                 return;
/* 1462:     */               }
/* 1463:1295 */               if ((ItemLoreStats.this.gearStats.playerHeldItemChangeXPLevelRequirementItemInHand(checkItemHeld) != 0) && (ItemLoreStats.this.gearStats.playerHeldItemChangeXPLevelRequirementItemInHand(checkItemHeld) > event.getPlayer().getLevel()))
/* 1464:     */               {
/* 1465:1297 */                 ItemLoreStats.ItemLoreStatsListener.this.swapItems(event.getNewSlot(), event.getPreviousSlot(), playerFinal.getInventory());
/* 1466:1298 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1467:1299 */                 ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand requires a higher level. Item in hand level: " + ItemLoreStats.this.gearStats.playerHeldItemChangeXPLevelRequirementItemInHand(checkItemHeld) + ", Player level: " + event.getPlayer().getLevel());
/* 1468:1300 */                 return;
/* 1469:     */               }
/* 1470:1303 */               String[] splitLore = checkItemHeld.getItemMeta().getLore().toString().split(", ");
/* 1471:1304 */               for (String getStat : splitLore) {
/* 1472:1306 */                 if (ChatColor.stripColor(getStat).startsWith(strippedWeaponSpeed + ": "))
/* 1473:     */                 {
/* 1474:1307 */                   if (weaponSpeed.length() < 2)
/* 1475:     */                   {
/* 1476:1308 */                     weaponSpeed = ChatColor.stripColor(getStat).replace("[", "").trim().substring((strippedWeaponSpeed + ": ").length()).replace("]", "").trim().toLowerCase();
/* 1477:1309 */                     if (weaponSpeed.toString().equalsIgnoreCase("fast"))
/* 1478:     */                     {
/* 1479:1310 */                       if (event.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 1480:1311 */                         event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 1481:     */                       }
/* 1482:1313 */                       ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1483:1314 */                       ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1484:1315 */                       ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1485:1316 */                       event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1));
/* 1486:1317 */                       return;
/* 1487:     */                     }
/* 1488:1318 */                     if (weaponSpeed.toString().equalsIgnoreCase("slow"))
/* 1489:     */                     {
/* 1490:1319 */                       if (event.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 1491:1320 */                         event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 1492:     */                       }
/* 1493:1322 */                       ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1494:1323 */                       ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1495:1324 */                       ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1496:1325 */                       event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, 1));
/* 1497:1326 */                       return;
/* 1498:     */                     }
/* 1499:1328 */                     ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1500:1329 */                     ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1501:1330 */                     ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1502:1331 */                     ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1503:1332 */                     return;
/* 1504:     */                   }
/* 1505:1335 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1506:     */                 }
/* 1507:1337 */                 else if (weaponSpeed.length() < 2)
/* 1508:     */                 {
/* 1509:1338 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1510:     */                 }
/* 1511:     */               }
/* 1512:     */             }
/* 1513:1345 */             ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1514:1346 */             ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1515:1347 */             ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1516:1348 */             ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1517:     */           }
/* 1518:1348 */         }, 2L);
/* 1519:     */       }
/* 1520:     */     }
/* 1521:     */     
/* 1522:     */     @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
/* 1523:     */     public void onPlayerPickupItemEvent(PlayerPickupItemEvent event)
/* 1524:     */     {
/* 1525:1358 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 1526:     */       {
/* 1527:1359 */         if (event.getItem().getItemStack().getItemMeta().getLore() == null) {
/* 1528:1359 */           return;
/* 1529:     */         }
/* 1530:1361 */         if ((ItemLoreStats.this.isTool(event.getItem().getItemStack().getType())) || (ItemLoreStats.this.isArmour(event.getItem().getItemStack().getType())))
/* 1531:     */         {
/* 1532:1363 */           String weaponSpeed = "0";
/* 1533:1364 */           String strippedWeaponSpeed = ItemLoreStats.this.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/* 1534:1366 */           if ((ItemLoreStats.this.gearStats.getSoulboundNameItemInHand(event.getPlayer()) != null) && (ItemLoreStats.this.gearStats.getSoulboundNameItemInHand(event.getPlayer()) != event.getPlayer().getName()))
/* 1535:     */           {
/* 1536:1368 */             final PlayerInventory inventory = event.getPlayer().getInventory();
/* 1537:1370 */             for (int i = 0; i < 9; i++) {
/* 1538:1371 */               if (inventory.getItem(i) == null)
/* 1539:     */               {
/* 1540:1372 */                 inventory.setItem(i, this.button);
/* 1541:1373 */                 this.tempButton.add(Integer.valueOf(i));
/* 1542:     */               }
/* 1543:     */             }
/* 1544:1377 */             ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1545:     */             {
/* 1546:     */               public void run()
/* 1547:     */               {
/* 1548:1379 */                 for (Iterator localIterator = ItemLoreStats.ItemLoreStatsListener.this.tempButton.iterator(); localIterator.hasNext();)
/* 1549:     */                 {
/* 1550:1379 */                   int i = ((Integer)localIterator.next()).intValue();
/* 1551:1380 */                   inventory.clear(i);
/* 1552:     */                 }
/* 1553:1382 */                 ItemLoreStats.ItemLoreStatsListener.this.tempButton.clear();
/* 1554:     */               }
/* 1555:1382 */             }, 1L);
/* 1556:     */             
/* 1557:     */ 
/* 1558:     */ 
/* 1559:     */ 
/* 1560:1387 */             ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand is bound to someone else. Item in hand bound to: " + ItemLoreStats.this.gearStats.getSoulboundNameItemInHand(event.getPlayer()));
/* 1561:     */           }
/* 1562:1390 */           if ((ItemLoreStats.this.gearStats.getClassItemInHand(event.getPlayer()) != null) && (!event.getPlayer().hasPermission("ils.use." + ItemLoreStats.this.gearStats.getClassItemInHand(event.getPlayer()))))
/* 1563:     */           {
/* 1564:1392 */             final PlayerInventory inventory = event.getPlayer().getInventory();
/* 1565:1394 */             for (int i = 0; i < 9; i++) {
/* 1566:1395 */               if (inventory.getItem(i) == null)
/* 1567:     */               {
/* 1568:1396 */                 inventory.setItem(i, this.button);
/* 1569:1397 */                 this.tempButton.add(Integer.valueOf(i));
/* 1570:     */               }
/* 1571:     */             }
/* 1572:1401 */             ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1573:     */             {
/* 1574:     */               public void run()
/* 1575:     */               {
/* 1576:1403 */                 for (Iterator localIterator = ItemLoreStats.ItemLoreStatsListener.this.tempButton.iterator(); localIterator.hasNext();)
/* 1577:     */                 {
/* 1578:1403 */                   int i = ((Integer)localIterator.next()).intValue();
/* 1579:1404 */                   inventory.clear(i);
/* 1580:     */                 }
/* 1581:1406 */                 ItemLoreStats.ItemLoreStatsListener.this.tempButton.clear();
/* 1582:     */               }
/* 1583:1406 */             }, 1L);
/* 1584:     */             
/* 1585:     */ 
/* 1586:     */ 
/* 1587:     */ 
/* 1588:1411 */             ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand can only be used by players with the " + ItemLoreStats.this.gearStats.getClassItemInHand(event.getPlayer()) + " permission node.");
/* 1589:     */           }
/* 1590:1414 */           if ((ItemLoreStats.this.gearStats.getXPLevelRequirementItemInHand(event.getPlayer()) != 0) && (ItemLoreStats.this.gearStats.getXPLevelRequirementItemInHand(event.getPlayer()) >= event.getPlayer().getLevel()))
/* 1591:     */           {
/* 1592:1416 */             final PlayerInventory inventory = event.getPlayer().getInventory();
/* 1593:1418 */             for (int i = 0; i < 9; i++) {
/* 1594:1419 */               if (inventory.getItem(i) == null)
/* 1595:     */               {
/* 1596:1420 */                 inventory.setItem(i, this.button);
/* 1597:1421 */                 this.tempButton.add(Integer.valueOf(i));
/* 1598:     */               }
/* 1599:     */             }
/* 1600:1425 */             ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1601:     */             {
/* 1602:     */               public void run()
/* 1603:     */               {
/* 1604:1427 */                 for (Iterator localIterator = ItemLoreStats.ItemLoreStatsListener.this.tempButton.iterator(); localIterator.hasNext();)
/* 1605:     */                 {
/* 1606:1427 */                   int i = ((Integer)localIterator.next()).intValue();
/* 1607:1428 */                   inventory.clear(i);
/* 1608:     */                 }
/* 1609:1430 */                 ItemLoreStats.ItemLoreStatsListener.this.tempButton.clear();
/* 1610:     */               }
/* 1611:1430 */             }, 1L);
/* 1612:     */             
/* 1613:     */ 
/* 1614:     */ 
/* 1615:     */ 
/* 1616:1435 */             ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand requires a higher level. Item in hand level: " + ItemLoreStats.this.gearStats.getXPLevelRequirementItemInHand(event.getPlayer()) + ", Player level: " + event.getPlayer().getLevel());
/* 1617:     */           }
/* 1618:1438 */           String[] splitLore = event.getItem().getItemStack().getItemMeta().getLore().toString().split(", ");
/* 1619:1440 */           for (String getStat : splitLore) {
/* 1620:1441 */             if (ChatColor.stripColor(getStat).startsWith(strippedWeaponSpeed + ": "))
/* 1621:     */             {
/* 1622:1442 */               if (weaponSpeed.length() < 2)
/* 1623:     */               {
/* 1624:1443 */                 weaponSpeed = ChatColor.stripColor(getStat).replace("[", "").trim().substring((strippedWeaponSpeed + ": ").length()).replace("]", "").trim().toLowerCase();
/* 1625:1445 */                 if (weaponSpeed.toString().equalsIgnoreCase("fast"))
/* 1626:     */                 {
/* 1627:1446 */                   if (event.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 1628:1447 */                     event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 1629:     */                   }
/* 1630:1449 */                   ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1631:1450 */                   ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1632:1451 */                   ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1633:1452 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1634:1453 */                   event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1));
/* 1635:1454 */                   return;
/* 1636:     */                 }
/* 1637:1455 */                 if (weaponSpeed.toString().equalsIgnoreCase("slow"))
/* 1638:     */                 {
/* 1639:1456 */                   if (event.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 1640:1457 */                     event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 1641:     */                   }
/* 1642:1459 */                   ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1643:1460 */                   ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1644:1461 */                   ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1645:1462 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1646:1463 */                   event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, 1));
/* 1647:1464 */                   return;
/* 1648:     */                 }
/* 1649:1465 */                 if (weaponSpeed.toString().equalsIgnoreCase("normal"))
/* 1650:     */                 {
/* 1651:1466 */                   ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1652:1467 */                   ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1653:1468 */                   ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1654:1469 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1655:     */                 }
/* 1656:     */               }
/* 1657:     */               else
/* 1658:     */               {
/* 1659:1473 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1660:     */               }
/* 1661:     */             }
/* 1662:1476 */             else if (weaponSpeed.length() < 2) {
/* 1663:1477 */               ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1664:     */             }
/* 1665:     */           }
/* 1666:     */         }
/* 1667:     */         else
/* 1668:     */         {
/* 1669:1482 */           ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1670:     */         }
/* 1671:     */       }
/* 1672:     */     }
/* 1673:     */     
/* 1674:     */     @EventHandler
/* 1675:     */     public void onInventoryClose(InventoryCloseEvent event)
/* 1676:     */     {
/* 1677:1490 */       if ((!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName())) && (ItemLoreStats.this.getServer().getPlayer(event.getPlayer().getName()).isOnline()) && ((event.getInventory().getType().equals(InventoryType.ANVIL)) || (event.getInventory().getType().equals(InventoryType.BEACON)) || (event.getInventory().getType().equals(InventoryType.BREWING)) || (event.getInventory().getType().equals(InventoryType.CHEST)) || (event.getInventory().getType().equals(InventoryType.CRAFTING)) || (event.getInventory().getType().equals(InventoryType.CREATIVE)) || (event.getInventory().getType().equals(InventoryType.DISPENSER)) || (event.getInventory().getType().equals(InventoryType.DROPPER)) || (event.getInventory().getType().equals(InventoryType.ENCHANTING)) || (event.getInventory().getType().equals(InventoryType.ENDER_CHEST)) || (event.getInventory().getType().equals(InventoryType.FURNACE)) || (event.getInventory().getType().equals(InventoryType.HOPPER)) || (event.getInventory().getType().equals(InventoryType.MERCHANT)) || (event.getInventory().getType().equals(InventoryType.PLAYER)) || (event.getInventory().getType().equals(InventoryType.WORKBENCH))))
/* 1678:     */       {
/* 1679:1508 */         Player player = (Player)event.getPlayer();
/* 1680:     */         
/* 1681:1510 */         ItemLoreStats.this.xpLevel.checkXpLevelHead(player);
/* 1682:1511 */         ItemLoreStats.this.xpLevel.checkXpLevelChest(player);
/* 1683:1512 */         ItemLoreStats.this.xpLevel.checkXpLevelLegs(player);
/* 1684:1513 */         ItemLoreStats.this.xpLevel.checkXpLevelBoots(player);
/* 1685:1514 */         ItemLoreStats.this.xpLevel.checkXpLevelItemInHand(player);
/* 1686:     */         
/* 1687:1516 */         ItemLoreStats.this.soulbound.checkSoulboundHead(player);
/* 1688:1517 */         ItemLoreStats.this.soulbound.checkSoulboundChest(player);
/* 1689:1518 */         ItemLoreStats.this.soulbound.checkSoulboundLegs(player);
/* 1690:1519 */         ItemLoreStats.this.soulbound.checkSoulboundBoots(player);
/* 1691:1520 */         ItemLoreStats.this.soulbound.checkSoulboundItemInHand(player);
/* 1692:     */         
/* 1693:1522 */         ItemLoreStats.this.classes.checkClassHead(player);
/* 1694:1523 */         ItemLoreStats.this.classes.checkClassChest(player);
/* 1695:1524 */         ItemLoreStats.this.classes.checkClassLegs(player);
/* 1696:1525 */         ItemLoreStats.this.classes.checkClassBoots(player);
/* 1697:1526 */         ItemLoreStats.this.classes.checkClassItemInHand(player);
/* 1698:1528 */         if (!ItemLoreStats.this.getServer().getOfflinePlayer(player.getName()).isOnline()) {
/* 1699:1528 */           return;
/* 1700:     */         }
/* 1701:1530 */         if (player.getItemInHand() != null)
/* 1702:     */         {
/* 1703:1531 */           if (player.getItemInHand().getType() != Material.AIR)
/* 1704:     */           {
/* 1705:1532 */             if (player.getItemInHand().getItemMeta() != null)
/* 1706:     */             {
/* 1707:1533 */               if (player.getItemInHand().getItemMeta().getLore() != null)
/* 1708:     */               {
/* 1709:1534 */                 String weaponSpeed = "0";
/* 1710:1535 */                 String strippedWeaponSpeed = ItemLoreStats.this.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/* 1711:     */                 
/* 1712:1537 */                 String[] splitLore = player.getItemInHand().getItemMeta().getLore().toString().split(", ");
/* 1713:     */                 
/* 1714:1539 */                 ItemLoreStats.this.updateHealth(player);
/* 1715:1541 */                 for (String getStat : splitLore) {
/* 1716:1542 */                   if (ChatColor.stripColor(getStat).startsWith(strippedWeaponSpeed))
/* 1717:     */                   {
/* 1718:1543 */                     if (weaponSpeed.length() < 2)
/* 1719:     */                     {
/* 1720:1544 */                       weaponSpeed = ChatColor.stripColor(getStat).replace("[", "").trim().substring((strippedWeaponSpeed + ": ").length()).replace("]", "").trim().toLowerCase();
/* 1721:1545 */                       if (weaponSpeed.toString().equalsIgnoreCase("fast"))
/* 1722:     */                       {
/* 1723:1546 */                         if (player.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 1724:1547 */                           player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 1725:     */                         }
/* 1726:1549 */                         ItemLoreStats.this.updateHealth(player);
/* 1727:1550 */                         ItemLoreStats.this.updatePlayerSpeed(player);
/* 1728:1551 */                         ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1729:1552 */                         ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1730:1553 */                         player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1));
/* 1731:1554 */                         return;
/* 1732:     */                       }
/* 1733:1555 */                       if (weaponSpeed.toString().equalsIgnoreCase("slow"))
/* 1734:     */                       {
/* 1735:1556 */                         if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 1736:1557 */                           player.removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 1737:     */                         }
/* 1738:1559 */                         ItemLoreStats.this.updateHealth(player);
/* 1739:1560 */                         ItemLoreStats.this.updatePlayerSpeed(player);
/* 1740:1561 */                         ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1741:1562 */                         ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1742:1563 */                         player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, 1));
/* 1743:1564 */                         return;
/* 1744:     */                       }
/* 1745:1565 */                       if (weaponSpeed.toString().equalsIgnoreCase("normal"))
/* 1746:     */                       {
/* 1747:1566 */                         ItemLoreStats.this.updateHealth(player);
/* 1748:1567 */                         ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1749:1568 */                         ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1750:1569 */                         ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1751:     */                       }
/* 1752:     */                     }
/* 1753:     */                   }
/* 1754:1574 */                   else if (weaponSpeed.length() < 2) {
/* 1755:1575 */                     ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1756:     */                   }
/* 1757:     */                 }
/* 1758:     */               }
/* 1759:     */               else
/* 1760:     */               {
/* 1761:1580 */                 ItemLoreStats.this.updateHealth(player);
/* 1762:1581 */                 ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1763:1582 */                 ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1764:1583 */                 ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1765:     */               }
/* 1766:     */             }
/* 1767:     */             else
/* 1768:     */             {
/* 1769:1586 */               ItemLoreStats.this.updateHealth(player);
/* 1770:1587 */               ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1771:1588 */               ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1772:1589 */               ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1773:     */             }
/* 1774:     */           }
/* 1775:     */           else
/* 1776:     */           {
/* 1777:1592 */             ItemLoreStats.this.updateHealth(player);
/* 1778:1593 */             ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1779:1594 */             ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1780:1595 */             ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1781:     */           }
/* 1782:1597 */           player.updateInventory();
/* 1783:1598 */           ItemLoreStats.this.debugMessage(player, "Inventory updated.");
/* 1784:     */         }
/* 1785:     */         else
/* 1786:     */         {
/* 1787:1600 */           ItemLoreStats.this.updateHealth(player);
/* 1788:1601 */           ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1789:1602 */           ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1790:1603 */           ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1791:     */         }
/* 1792:     */       }
/* 1793:     */     }
/* 1794:     */     
/* 1795:     */     public Material getRepairBlock()
/* 1796:     */     {
/* 1797:1610 */       if (ItemLoreStats.this.getConfig().getString("durabilityAddedOnEachRepair.repairBlock") != null)
/* 1798:     */       {
/* 1799:1611 */         Material repairBlock = Material.getMaterial(ItemLoreStats.this.getConfig().getString("durabilityAddedOnEachRepair.repairBlock"));
/* 1800:1612 */         return repairBlock;
/* 1801:     */       }
/* 1802:1614 */       return Material.WORKBENCH;
/* 1803:     */     }
/* 1804:     */     
/* 1805:     */     @EventHandler
/* 1806:     */     public void repairItemOnLeftClick(PlayerInteractEvent event)
/* 1807:     */     {
/* 1808:1619 */       if (((event.getAction() == Action.LEFT_CLICK_BLOCK) && (ItemLoreStats.this.isTool(event.getPlayer().getItemInHand().getType()))) || ((event.getAction() == Action.LEFT_CLICK_BLOCK) && (ItemLoreStats.this.isArmour(event.getPlayer().getItemInHand().getType())) && (event.getClickedBlock().getType().equals(getRepairBlock())) && (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))))
/* 1809:     */       {
/* 1810:1622 */         if ((ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) || (event.getClickedBlock() == null)) {
/* 1811:1622 */           return;
/* 1812:     */         }
/* 1813:1624 */         Player player = event.getPlayer();
/* 1814:1626 */         if (player.getItemInHand().getType().toString().contains("_SWORD")) {
/* 1815:1627 */           ItemLoreStats.this.repairItems.repairSword(player);
/* 1816:1628 */         } else if (player.getItemInHand().getType().toString().contains("_PICKAXE")) {
/* 1817:1629 */           ItemLoreStats.this.repairItems.repairPickaxe(player);
/* 1818:1630 */         } else if (player.getItemInHand().getType().toString().contains("_AXE")) {
/* 1819:1631 */           ItemLoreStats.this.repairItems.repairAxe(player);
/* 1820:1632 */         } else if (player.getItemInHand().getType().toString().contains("_HOE")) {
/* 1821:1633 */           ItemLoreStats.this.repairItems.repairHoe(player);
/* 1822:1634 */         } else if (player.getItemInHand().getType().toString().contains("BOW")) {
/* 1823:1635 */           ItemLoreStats.this.repairItems.repairBow(player);
/* 1824:1636 */         } else if (player.getItemInHand().getType().toString().contains("SHEARS")) {
/* 1825:1637 */           ItemLoreStats.this.repairItems.repairShears(player);
/* 1826:1638 */         } else if (player.getItemInHand().getType().toString().contains("STICK")) {
/* 1827:1639 */           ItemLoreStats.this.repairItems.repairStick(player);
/* 1828:1640 */         } else if (player.getItemInHand().getType().toString().contains("FLINT_AND_STEEL")) {
/* 1829:1641 */           ItemLoreStats.this.repairItems.repairFlintAndSteel(player);
/* 1830:1642 */         } else if (player.getItemInHand().getType().toString().contains("_SPADE")) {
/* 1831:1643 */           ItemLoreStats.this.repairItems.repairSpade(player);
/* 1832:1644 */         } else if (player.getItemInHand().getType().toString().contains("_HELMET")) {
/* 1833:1645 */           ItemLoreStats.this.repairItems.repairHelmet(player);
/* 1834:1646 */         } else if (player.getItemInHand().getType().toString().contains("_CHESTPLATE")) {
/* 1835:1647 */           ItemLoreStats.this.repairItems.repairChestplate(player);
/* 1836:1648 */         } else if (player.getItemInHand().getType().toString().contains("_LEGGINGS")) {
/* 1837:1649 */           ItemLoreStats.this.repairItems.repairLeggings(player);
/* 1838:1650 */         } else if (player.getItemInHand().getType().toString().contains("_BOOTS")) {
/* 1839:1651 */           ItemLoreStats.this.repairItems.repairBoots(player);
/* 1840:1652 */         } else if (player.getItemInHand().getType().toString().contains("FISHING_ROD")) {
/* 1841:1653 */           ItemLoreStats.this.repairItems.repairFishingRod(player);
/* 1842:1654 */         } else if (player.getItemInHand().getType().toString().contains("CARROT_STICK")) {
/* 1843:1655 */           ItemLoreStats.this.repairItems.repairCarrotStick(player);
/* 1844:     */         }
/* 1845:     */       }
/* 1846:     */     }
/* 1847:     */     
/* 1848:     */     public boolean hasCooldown(String playerName, int getSeconds, String spellType)
/* 1849:     */     {
/* 1850:1661 */       if (ItemLoreStats.this.cooldowns.get(playerName) != null)
/* 1851:     */       {
/* 1852:1662 */         if (((Long)ItemLoreStats.this.cooldowns.get(playerName)).longValue() < System.currentTimeMillis() - getSeconds * 1000) {
/* 1853:1663 */           return false;
/* 1854:     */         }
/* 1855:1665 */         long currentTime = System.currentTimeMillis();
/* 1856:1666 */         long oldTime = ((Long)ItemLoreStats.this.cooldowns.get(playerName)).longValue();
/* 1857:     */         
/* 1858:1668 */         String remainingCooldown = String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000)));
/* 1859:1669 */         String modifiedPlayerName = playerName.substring(0, playerName.length() - 4);
/* 1860:1671 */         if (remainingCooldown.length() > 7) {
/* 1861:1672 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 5) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(5, 7) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1862:1673 */         } else if (remainingCooldown.length() > 6) {
/* 1863:1674 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 4) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(4, 6) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1864:1675 */         } else if (remainingCooldown.length() > 5) {
/* 1865:1676 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 3) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(3, 5) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1866:1677 */         } else if (remainingCooldown.length() > 4) {
/* 1867:1678 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 2) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(2, 4) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1868:1679 */         } else if (remainingCooldown.length() > 3) {
/* 1869:1680 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 1) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(1, 3) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1870:     */         }
/* 1871:1683 */         return true;
/* 1872:     */       }
/* 1873:1686 */       return false;
/* 1874:     */     }
/* 1875:     */     
/* 1876:     */     @EventHandler
/* 1877:     */     public void onSpellUse(PlayerInteractEvent event)
/* 1878:     */     {
/* 1879:1691 */       Player player = event.getPlayer();
/* 1880:1692 */       if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 1881:     */       {
/* 1882:1693 */         if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1883:     */         {
/* 1884:1694 */           if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 1885:1694 */             return;
/* 1886:     */           }
/* 1887:1695 */           if (ItemLoreStats.plugin.util_WorldGuard != null)
/* 1888:     */           {
/* 1889:1696 */             if (!ItemLoreStats.plugin.util_WorldGuard.playerInPVPRegion(player))
/* 1890:     */             {
/* 1891:1697 */               if ((ItemLoreStats.this.gearStats.getTnT(player)) && (!hasCooldown(player.getName() + ".tnt", ItemLoreStats.this.getConfig().getInt("statNames.spells.tnt.cooldown"), "tnt")))
/* 1892:     */               {
/* 1893:1699 */                 ItemLoreStats.this.spells.castTnT(player);
/* 1894:1700 */                 ItemLoreStats.this.cooldowns.put(player.getName() + ".tnt", Long.valueOf(System.currentTimeMillis()));
/* 1895:     */               }
/* 1896:1703 */               if ((ItemLoreStats.this.gearStats.getFireball(player)) && (!hasCooldown(player.getName() + ".bal", ItemLoreStats.this.getConfig().getInt("statNames.spells.fireball.cooldown"), "fireball")))
/* 1897:     */               {
/* 1898:1705 */                 ItemLoreStats.this.spells.castFireball(player);
/* 1899:1706 */                 ItemLoreStats.this.cooldowns.put(player.getName() + ".bal", Long.valueOf(System.currentTimeMillis()));
/* 1900:     */               }
/* 1901:1709 */               if ((ItemLoreStats.this.gearStats.getLightning(player)) && (!hasCooldown(player.getName() + ".lig", ItemLoreStats.this.getConfig().getInt("statNames.spells.lightning.cooldown"), "lightning")))
/* 1902:     */               {
/* 1903:1711 */                 ItemLoreStats.this.spells.castLightning(player);
/* 1904:1712 */                 ItemLoreStats.this.cooldowns.put(player.getName() + ".lig", Long.valueOf(System.currentTimeMillis()));
/* 1905:     */               }
/* 1906:1715 */               if ((ItemLoreStats.this.gearStats.getFrostbolt(player)) && (!hasCooldown(player.getName() + ".fbt", ItemLoreStats.this.getConfig().getInt("statNames.spells.frostbolt.cooldown"), "frostbolt")))
/* 1907:     */               {
/* 1908:1717 */                 ItemLoreStats.this.spells.castFrostbolt(player);
/* 1909:1718 */                 ItemLoreStats.this.cooldowns.put(player.getName() + ".fbt", Long.valueOf(System.currentTimeMillis()));
/* 1910:     */               }
/* 1911:     */             }
/* 1912:     */           }
/* 1913:     */           else
/* 1914:     */           {
/* 1915:1723 */             if ((ItemLoreStats.this.gearStats.getTnT(player)) && (!hasCooldown(player.getName() + ".tnt", ItemLoreStats.this.getConfig().getInt("statNames.spells.tnt.cooldown"), "tnt")))
/* 1916:     */             {
/* 1917:1725 */               ItemLoreStats.this.spells.castTnT(player);
/* 1918:1726 */               ItemLoreStats.this.cooldowns.put(player.getName() + ".tnt", Long.valueOf(System.currentTimeMillis()));
/* 1919:     */             }
/* 1920:1729 */             if ((ItemLoreStats.this.gearStats.getFireball(player)) && (!hasCooldown(player.getName() + ".bal", ItemLoreStats.this.getConfig().getInt("statNames.spells.fireball.cooldown"), "fireball")))
/* 1921:     */             {
/* 1922:1731 */               ItemLoreStats.this.spells.castFireball(player);
/* 1923:1732 */               ItemLoreStats.this.cooldowns.put(player.getName() + ".bal", Long.valueOf(System.currentTimeMillis()));
/* 1924:     */             }
/* 1925:1735 */             if ((ItemLoreStats.this.gearStats.getLightning(player)) && (!hasCooldown(player.getName() + ".lig", ItemLoreStats.this.getConfig().getInt("statNames.spells.lightning.cooldown"), "lightning")))
/* 1926:     */             {
/* 1927:1737 */               ItemLoreStats.this.spells.castLightning(player);
/* 1928:1738 */               ItemLoreStats.this.cooldowns.put(player.getName() + ".lig", Long.valueOf(System.currentTimeMillis()));
/* 1929:     */             }
/* 1930:1741 */             if ((ItemLoreStats.this.gearStats.getFrostbolt(player)) && (!hasCooldown(player.getName() + ".fbt", ItemLoreStats.this.getConfig().getInt("statNames.spells.frostbolt.cooldown"), "frostbolt")))
/* 1931:     */             {
/* 1932:1743 */               ItemLoreStats.this.spells.castFrostbolt(player);
/* 1933:1744 */               ItemLoreStats.this.cooldowns.put(player.getName() + ".fbt", Long.valueOf(System.currentTimeMillis()));
/* 1934:     */             }
/* 1935:     */           }
/* 1936:     */         }
/* 1937:     */       }
/* 1938:1750 */       else if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && (ItemLoreStats.this.isArmour(event.getPlayer().getItemInHand().getType())))
/* 1939:     */       {
/* 1940:1751 */         if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
/* 1941:1751 */           event.setCancelled(true);
/* 1942:     */         }
/* 1943:1752 */         if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1944:     */         {
/* 1945:1753 */           ItemLoreStats.this.xpLevel.checkXpLevelHead(player);
/* 1946:1754 */           ItemLoreStats.this.xpLevel.checkXpLevelChest(player);
/* 1947:1755 */           ItemLoreStats.this.xpLevel.checkXpLevelLegs(player);
/* 1948:1756 */           ItemLoreStats.this.xpLevel.checkXpLevelBoots(player);
/* 1949:1757 */           ItemLoreStats.this.xpLevel.checkXpLevelItemInHand(player);
/* 1950:     */           
/* 1951:1759 */           ItemLoreStats.this.soulbound.checkSoulboundHead(player);
/* 1952:1760 */           ItemLoreStats.this.soulbound.checkSoulboundChest(player);
/* 1953:1761 */           ItemLoreStats.this.soulbound.checkSoulboundLegs(player);
/* 1954:1762 */           ItemLoreStats.this.soulbound.checkSoulboundBoots(player);
/* 1955:1763 */           ItemLoreStats.this.soulbound.checkSoulboundItemInHand(player);
/* 1956:     */           
/* 1957:1765 */           ItemLoreStats.this.classes.checkClassHead(player);
/* 1958:1766 */           ItemLoreStats.this.classes.checkClassChest(player);
/* 1959:1767 */           ItemLoreStats.this.classes.checkClassLegs(player);
/* 1960:1768 */           ItemLoreStats.this.classes.checkClassBoots(player);
/* 1961:1769 */           ItemLoreStats.this.classes.checkClassItemInHand(player);
/* 1962:     */           
/* 1963:1771 */           ItemLoreStats.this.updateHealth(player);
/* 1964:1772 */           ItemLoreStats.this.updatePlayerSpeed(player);
/* 1965:1773 */           ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1966:     */           
/* 1967:1775 */           ItemLoreStats.this.debugMessage(player, "Inventory updated.");
/* 1968:     */         }
/* 1969:     */       }
/* 1970:     */     }
/* 1971:     */     
/* 1972:     */     @EventHandler
/* 1973:     */     public void onPlayerChangeWorld(PlayerChangedWorldEvent event)
/* 1974:     */     {
/* 1975:1783 */       if ((event.getPlayer() instanceof Player))
/* 1976:     */       {
/* 1977:1785 */         Player player = event.getPlayer();
/* 1978:     */         
/* 1979:1787 */         final Player playerFinal = event.getPlayer();
/* 1980:     */         
/* 1981:1789 */         ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1982:     */         {
/* 1983:     */           public void run()
/* 1984:     */           {
/* 1985:1791 */             ItemLoreStats.this.updateHealth(playerFinal);
/* 1986:1792 */             ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/* 1987:1793 */             ItemLoreStats.this.setBonuses.updateSetBonus(playerFinal);
/* 1988:     */           }
/* 1989:1793 */         }, 2L);
/* 1990:1798 */         if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1991:     */         {
/* 1992:1799 */           String weaponSpeed = "0";
/* 1993:1801 */           if ((player.getItemInHand() != null) && (player.getItemInHand().getType() != Material.AIR) && (player.getItemInHand().getItemMeta() != null) && (player.getItemInHand().getItemMeta().getLore() != null) && ((ItemLoreStats.this.isTool(player.getItemInHand().getType())) || (ItemLoreStats.this.isArmour(player.getItemInHand().getType()))))
/* 1994:     */           {
/* 1995:1807 */             String strippedWeaponSpeed = ItemLoreStats.this.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/* 1996:     */             
/* 1997:1809 */             String[] splitLore = player.getItemInHand().getItemMeta().getLore().toString().split(", ");
/* 1998:1811 */             for (String getStat : splitLore) {
/* 1999:1813 */               if (ChatColor.stripColor(getStat).startsWith(strippedWeaponSpeed))
/* 2000:     */               {
/* 2001:1814 */                 if (weaponSpeed.length() < 2)
/* 2002:     */                 {
/* 2003:1815 */                   weaponSpeed = ChatColor.stripColor(getStat).replace("[", "").trim().substring((strippedWeaponSpeed + ": ").length()).replace("]", "").trim().toLowerCase();
/* 2004:1816 */                   if (weaponSpeed.toString().equalsIgnoreCase("fast"))
/* 2005:     */                   {
/* 2006:1817 */                     if (event.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 2007:1818 */                       event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 2008:     */                     }
/* 2009:1820 */                     event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1));
/* 2010:     */                   }
/* 2011:1821 */                   else if (weaponSpeed.toString().equalsIgnoreCase("slow"))
/* 2012:     */                   {
/* 2013:1822 */                     if (event.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 2014:1823 */                       event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 2015:     */                     }
/* 2016:1825 */                     event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, 1));
/* 2017:     */                   }
/* 2018:1826 */                   else if (weaponSpeed.toString().equalsIgnoreCase("normal"))
/* 2019:     */                   {
/* 2020:1827 */                     ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 2021:     */                   }
/* 2022:     */                 }
/* 2023:     */               }
/* 2024:1831 */               else if (weaponSpeed.length() < 2) {
/* 2025:1832 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 2026:     */               }
/* 2027:     */             }
/* 2028:     */           }
/* 2029:     */         }
/* 2030:     */         else
/* 2031:     */         {
/* 2032:1842 */           ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 2033:     */         }
/* 2034:     */       }
/* 2035:     */     }
/* 2036:     */     
/* 2037:     */     @EventHandler(ignoreCancelled=true)
/* 2038:     */     public void disableItemBreak(PlayerItemBreakEvent event)
/* 2039:     */     {
/* 2040:1850 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2041:     */       {
/* 2042:1851 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2043:1851 */           return;
/* 2044:     */         }
/* 2045:1852 */         final Player player = event.getPlayer();
/* 2046:1853 */         final ItemStack item = event.getBrokenItem();
/* 2047:1854 */         item.setDurability((short)0);
/* 2048:     */         try
/* 2049:     */         {
/* 2050:1857 */           if ((event.getBrokenItem() != null) && (event.getBrokenItem().getItemMeta().hasLore())) {
/* 2051:1859 */             if (ItemLoreStats.this.isTool(event.getBrokenItem().getType())) {
/* 2052:1860 */               event.getBrokenItem().setDurability((short)1);
/* 2053:1861 */             } else if (ItemLoreStats.this.isArmour(event.getBrokenItem().getType())) {
/* 2054:1862 */               if (ItemLoreStats.this.isHelmet(event.getBrokenItem().getType())) {
/* 2055:1863 */                 ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 2056:     */                 {
/* 2057:     */                   public void run()
/* 2058:     */                   {
/* 2059:1865 */                     player.getInventory().setHelmet(item);
/* 2060:     */                   }
/* 2061:1865 */                 }, 1L);
/* 2062:1869 */               } else if (ItemLoreStats.this.isChestplate(event.getBrokenItem().getType())) {
/* 2063:1870 */                 ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 2064:     */                 {
/* 2065:     */                   public void run()
/* 2066:     */                   {
/* 2067:1872 */                     player.getInventory().setChestplate(item);
/* 2068:     */                   }
/* 2069:1872 */                 }, 1L);
/* 2070:1876 */               } else if (ItemLoreStats.this.isLeggings(event.getBrokenItem().getType())) {
/* 2071:1877 */                 ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 2072:     */                 {
/* 2073:     */                   public void run()
/* 2074:     */                   {
/* 2075:1879 */                     player.getInventory().setLeggings(item);
/* 2076:     */                   }
/* 2077:1879 */                 }, 1L);
/* 2078:1883 */               } else if (ItemLoreStats.this.isBoots(event.getBrokenItem().getType())) {
/* 2079:1884 */                 ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 2080:     */                 {
/* 2081:     */                   public void run()
/* 2082:     */                   {
/* 2083:1886 */                     player.getInventory().setBoots(item);
/* 2084:     */                   }
/* 2085:1886 */                 }, 1L);
/* 2086:     */               }
/* 2087:     */             }
/* 2088:     */           }
/* 2089:     */         }
/* 2090:     */         catch (Exception ex)
/* 2091:     */         {
/* 2092:1895 */           System.out.println(ex);
/* 2093:     */         }
/* 2094:     */       }
/* 2095:     */     }
/* 2096:     */     
/* 2097:     */     @EventHandler
/* 2098:     */     public void addDurabilityToCraftedItem(PrepareItemCraftEvent event)
/* 2099:     */     {
/* 2100:1903 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getView().getPlayer().getWorld().getName()))
/* 2101:     */       {
/* 2102:1904 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2103:1904 */           return;
/* 2104:     */         }
/* 2105:1905 */         if (event.isRepair())
/* 2106:     */         {
/* 2107:1906 */           event.getInventory().setResult(null);
/* 2108:     */         }
/* 2109:1908 */         else if (((ItemLoreStats.this.isTool(event.getRecipe().getResult().getType())) || (ItemLoreStats.this.isArmour(event.getRecipe().getResult().getType()))) && (ItemLoreStats.this.getConfig().getBoolean("defaultCraftedDurability.enableDurabilityOnCrafted")))
/* 2110:     */         {
/* 2111:1910 */           ItemStack craftedItem = event.getInventory().getResult();
/* 2112:1911 */           ItemMeta craftedItemMeta = craftedItem.getItemMeta();
/* 2113:     */           
/* 2114:1913 */           List setItemLore = new ArrayList();
/* 2115:     */           
/* 2116:1915 */           String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability");
/* 2117:1916 */           String durabilityNoColour = durability.replaceAll("&([0-9a-f])", "");
/* 2118:     */           
/* 2119:1918 */           String type = "";
/* 2120:1919 */           String selectedDurability = "";
/* 2121:1921 */           if (craftedItem.getType().toString().contains("WOOD")) {
/* 2122:1922 */             type = "wood";
/* 2123:1923 */           } else if (craftedItem.getType().toString().contains("LEATHER")) {
/* 2124:1924 */             type = "leather";
/* 2125:1925 */           } else if (craftedItem.getType().toString().contains("STONE")) {
/* 2126:1926 */             type = "stone";
/* 2127:1927 */           } else if (craftedItem.getType().toString().contains("CHAINMAIL")) {
/* 2128:1928 */             type = "chainmail";
/* 2129:1929 */           } else if (craftedItem.getType().toString().contains("IRON")) {
/* 2130:1930 */             type = "iron";
/* 2131:1931 */           } else if (craftedItem.getType().toString().contains("GOLD")) {
/* 2132:1932 */             type = "gold";
/* 2133:1933 */           } else if (craftedItem.getType().toString().contains("DIAMOND")) {
/* 2134:1934 */             type = "diamond";
/* 2135:1935 */           } else if (craftedItem.getType().toString().contains("BOW")) {
/* 2136:1936 */             type = "bow";
/* 2137:1937 */           } else if (craftedItem.getType().toString().contains("SHEARS")) {
/* 2138:1938 */             type = "shears";
/* 2139:1939 */           } else if (craftedItem.getType().toString().contains("STICK")) {
/* 2140:1940 */             type = "stick";
/* 2141:1941 */           } else if (craftedItem.getType().toString().contains("FLINT_AND_STEEL")) {
/* 2142:1942 */             type = "flintAndSteel";
/* 2143:1943 */           } else if (craftedItem.getType().toString().contains("FISHING_ROD")) {
/* 2144:1944 */             type = "fishingRod";
/* 2145:1945 */           } else if (craftedItem.getType().toString().contains("CARROT_STICK")) {
/* 2146:1946 */             type = "carrotStick";
/* 2147:     */           }
/* 2148:1949 */           if (ItemLoreStats.this.isTool(craftedItem.getType()))
/* 2149:     */           {
/* 2150:1950 */             if (!ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).equals("0")) {
/* 2151:1951 */               if (ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).contains("-"))
/* 2152:     */               {
/* 2153:1952 */                 selectedDurability = String.valueOf(ItemLoreStats.this.randomRange(Integer.parseInt(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 2154:1953 */                 setItemLore.add(ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability);
/* 2155:     */               }
/* 2156:     */               else
/* 2157:     */               {
/* 2158:1955 */                 selectedDurability = String.valueOf(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).replaceAll("&([0-9a-f])", "").trim());
/* 2159:1956 */                 setItemLore.add(ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability);
/* 2160:     */               }
/* 2161:     */             }
/* 2162:     */           }
/* 2163:1959 */           else if ((ItemLoreStats.this.isArmour(craftedItem.getType())) && (!ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).equals("0"))) {
/* 2164:1961 */             if (ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).contains("-"))
/* 2165:     */             {
/* 2166:1962 */               selectedDurability = String.valueOf(ItemLoreStats.this.randomRange(Integer.parseInt(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 2167:1963 */               setItemLore.add(ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability);
/* 2168:     */             }
/* 2169:     */             else
/* 2170:     */             {
/* 2171:1965 */               selectedDurability = String.valueOf(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).replaceAll("&([0-9a-f])", "").trim());
/* 2172:1966 */               setItemLore.add(ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability);
/* 2173:     */             }
/* 2174:     */           }
/* 2175:1971 */           craftedItemMeta.setDisplayName(ChatColor.RESET + event.getInventory().getResult().getType().toString().replaceAll("WOOD_", "Wooden ").replaceAll("LEATHER_", "Leather ").replaceAll("STONE_", "Stone ").replaceAll("IRON_", "Iron ").replaceAll("GOLD_", "Golden ").replaceAll("DIAMOND_", "Diamond ").replaceAll("FISHING_ROD", "Fishing Rod").replaceAll("BOW", "Bow").replaceAll("SHEARS", "Shears").replaceAll("STICK", "Stick").replaceAll("FLINT_AND_STEEL", "Flint and Steel").replaceAll("CARROT_STICK", "Carrot on a Stick").replaceAll("SWORD", "Sword").replaceAll("HOE", "Hoe").replaceAll("SPADE", "Spade").replaceAll("PICKAXE", "Pickaxe").replace("AXE", "Axe").replaceAll("HELMET", "Helmet").replace("CHESTPLATE", "Chestplate").replaceAll("LEGGINGS", "Leggings").replace("BOOTS", "Boots"));
/* 2176:     */           
/* 2177:1973 */           craftedItemMeta.setLore(setItemLore);
/* 2178:1974 */           craftedItem.setItemMeta(craftedItemMeta);
/* 2179:     */           
/* 2180:1976 */           event.getInventory().setResult(craftedItem);
/* 2181:     */         }
/* 2182:     */       }
/* 2183:     */     }
/* 2184:     */     
/* 2185:     */     @EventHandler
/* 2186:     */     public void blockBreakDurability(BlockBreakEvent event)
/* 2187:     */     {
/* 2188:1984 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2189:     */       {
/* 2190:1985 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2191:1985 */           return;
/* 2192:     */         }
/* 2193:1986 */         Player player = event.getPlayer();
/* 2194:1988 */         if ((player.getItemInHand() != null) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 2195:     */         {
/* 2196:1989 */           player.getItemInHand().setDurability((short)-1);
/* 2197:1990 */           if (player.getItemInHand().getItemMeta().hasLore())
/* 2198:     */           {
/* 2199:1992 */             String durabilityNoColour = ItemLoreStats.this.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 2200:     */             
/* 2201:1994 */             List<String> getItemLore = player.getItemInHand().getItemMeta().getLore();
/* 2202:1996 */             for (String getDurability : getItemLore) {
/* 2203:1997 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/* 2204:     */               {
/* 2205:1998 */                 String durabilityRebuilder = "";
/* 2206:1999 */                 String durabilityAmountColour = "";
/* 2207:2000 */                 String prefixColourOnly = "";
/* 2208:     */                 
/* 2209:2002 */                 int index = getItemLore.indexOf(getDurability);
/* 2210:2003 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/* 2211:2004 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/* 2212:2006 */                 if (currentValueMinusOne + 1 < 2)
/* 2213:     */                 {
/* 2214:2007 */                   player.playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 2215:2008 */                   player.getInventory().remove(player.getItemInHand());
/* 2216:2009 */                   return;
/* 2217:     */                 }
/* 2218:2012 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/* 2219:     */                 {
/* 2220:2013 */                   if (getDurability.length() > 4)
/* 2221:     */                   {
/* 2222:2014 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/* 2223:2015 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/* 2224:     */                     } else {
/* 2225:2017 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2226:     */                     }
/* 2227:     */                   }
/* 2228:     */                   else {
/* 2229:2020 */                     prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2230:     */                   }
/* 2231:     */                 }
/* 2232:     */                 else {
/* 2233:2023 */                   prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour("&5&o");
/* 2234:     */                 }
/* 2235:2026 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/* 2236:     */                 {
/* 2237:2027 */                   if (getDurability.split("/")[1].trim().length() > 4)
/* 2238:     */                   {
/* 2239:2028 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/* 2240:2029 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/* 2241:     */                     } else {
/* 2242:2031 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2243:     */                     }
/* 2244:     */                   }
/* 2245:     */                   else {
/* 2246:2034 */                     durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2247:     */                   }
/* 2248:     */                 }
/* 2249:     */                 else {
/* 2250:2037 */                   durabilityAmountColour = prefixColourOnly;
/* 2251:     */                 }
/* 2252:2040 */                 currentValueMinusOne += enchantmentDurabilityLoss(player.getItemInHand());
/* 2253:     */                 
/* 2254:2042 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/* 2255:2044 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/* 2256:     */                 {
/* 2257:2045 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/* 2258:2046 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2259:     */                   }
/* 2260:2048 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2261:     */                 }
/* 2262:2049 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/* 2263:     */                 {
/* 2264:2050 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/* 2265:2051 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2266:     */                   }
/* 2267:2053 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2268:     */                 }
/* 2269:2054 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/* 2270:     */                 {
/* 2271:2055 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/* 2272:2056 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2273:     */                   }
/* 2274:2058 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2275:     */                 }
/* 2276:     */                 else
/* 2277:     */                 {
/* 2278:2060 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2279:     */                 }
/* 2280:2063 */                 getItemLore.set(index, durabilityRebuilder);
/* 2281:     */               }
/* 2282:     */             }
/* 2283:2067 */             ItemStack setItemInHand = new ItemStack(player.getItemInHand());
/* 2284:2068 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/* 2285:     */             
/* 2286:2070 */             setItemInHandMeta.setLore(getItemLore);
/* 2287:2071 */             setItemInHand.setItemMeta(setItemInHandMeta);
/* 2288:     */             
/* 2289:2073 */             player.getInventory().remove(player.getItemInHand());
/* 2290:2074 */             player.setItemInHand(new ItemStack(setItemInHand));
/* 2291:     */           }
/* 2292:     */         }
/* 2293:     */       }
/* 2294:     */     }
/* 2295:     */     
/* 2296:     */     @EventHandler
/* 2297:     */     public void fishingDurability(PlayerFishEvent event)
/* 2298:     */     {
/* 2299:2082 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2300:     */       {
/* 2301:2083 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2302:2083 */           return;
/* 2303:     */         }
/* 2304:2084 */         Player player = event.getPlayer();
/* 2305:2086 */         if ((player.getItemInHand() != null) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 2306:     */         {
/* 2307:2087 */           player.getItemInHand().setDurability((short)-1);
/* 2308:2088 */           if (player.getItemInHand().getItemMeta().hasLore())
/* 2309:     */           {
/* 2310:2090 */             String durabilityNoColour = ItemLoreStats.this.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 2311:     */             
/* 2312:2092 */             List<String> getItemLore = player.getItemInHand().getItemMeta().getLore();
/* 2313:2094 */             for (String getDurability : getItemLore) {
/* 2314:2095 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/* 2315:     */               {
/* 2316:2096 */                 String durabilityRebuilder = "";
/* 2317:2097 */                 String durabilityAmountColour = "";
/* 2318:2098 */                 String prefixColourOnly = "";
/* 2319:     */                 
/* 2320:2100 */                 int index = getItemLore.indexOf(getDurability);
/* 2321:2101 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/* 2322:2102 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/* 2323:2104 */                 if (currentValueMinusOne + 1 < 2)
/* 2324:     */                 {
/* 2325:2105 */                   player.playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 2326:2106 */                   player.getInventory().remove(player.getItemInHand());
/* 2327:2107 */                   return;
/* 2328:     */                 }
/* 2329:2110 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/* 2330:     */                 {
/* 2331:2111 */                   if (getDurability.length() > 4)
/* 2332:     */                   {
/* 2333:2112 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/* 2334:2113 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/* 2335:     */                     } else {
/* 2336:2115 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2337:     */                     }
/* 2338:     */                   }
/* 2339:     */                   else {
/* 2340:2118 */                     prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2341:     */                   }
/* 2342:     */                 }
/* 2343:     */                 else {
/* 2344:2121 */                   prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour("&5&o");
/* 2345:     */                 }
/* 2346:2124 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/* 2347:     */                 {
/* 2348:2125 */                   if (getDurability.split("/")[1].trim().length() > 4)
/* 2349:     */                   {
/* 2350:2126 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/* 2351:2127 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/* 2352:     */                     } else {
/* 2353:2129 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2354:     */                     }
/* 2355:     */                   }
/* 2356:     */                   else {
/* 2357:2132 */                     durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2358:     */                   }
/* 2359:     */                 }
/* 2360:     */                 else {
/* 2361:2135 */                   durabilityAmountColour = prefixColourOnly;
/* 2362:     */                 }
/* 2363:2138 */                 currentValueMinusOne += enchantmentDurabilityLoss(player.getItemInHand());
/* 2364:     */                 
/* 2365:2140 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/* 2366:2142 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/* 2367:     */                 {
/* 2368:2143 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/* 2369:2144 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2370:     */                   }
/* 2371:2146 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2372:     */                 }
/* 2373:2147 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/* 2374:     */                 {
/* 2375:2148 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/* 2376:2149 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2377:     */                   }
/* 2378:2151 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2379:     */                 }
/* 2380:2152 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/* 2381:     */                 {
/* 2382:2153 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/* 2383:2154 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2384:     */                   }
/* 2385:2156 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2386:     */                 }
/* 2387:     */                 else
/* 2388:     */                 {
/* 2389:2158 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2390:     */                 }
/* 2391:2161 */                 getItemLore.set(index, durabilityRebuilder);
/* 2392:     */               }
/* 2393:     */             }
/* 2394:2165 */             ItemStack setItemInHand = new ItemStack(player.getItemInHand());
/* 2395:2166 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/* 2396:     */             
/* 2397:2168 */             setItemInHandMeta.setLore(getItemLore);
/* 2398:2169 */             setItemInHand.setItemMeta(setItemInHandMeta);
/* 2399:     */             
/* 2400:2171 */             player.getInventory().remove(player.getItemInHand());
/* 2401:2172 */             player.setItemInHand(new ItemStack(setItemInHand));
/* 2402:     */           }
/* 2403:     */         }
/* 2404:     */       }
/* 2405:     */     }
/* 2406:     */     
/* 2407:     */     @EventHandler
/* 2408:     */     public void carrotStickDurability(PlayerInteractEvent event)
/* 2409:     */     {
/* 2410:2180 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2411:     */       {
/* 2412:2181 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2413:2181 */           return;
/* 2414:     */         }
/* 2415:2182 */         Player player = event.getPlayer();
/* 2416:2184 */         if ((player.getItemInHand().getType().equals(Material.CARROT_STICK)) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && (player.getItemInHand() != null) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 2417:     */         {
/* 2418:2186 */           player.getItemInHand().setDurability((short)-1);
/* 2419:2187 */           if (player.getItemInHand().getItemMeta().hasLore())
/* 2420:     */           {
/* 2421:2189 */             String durabilityNoColour = ItemLoreStats.this.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 2422:     */             
/* 2423:2191 */             List<String> getItemLore = player.getItemInHand().getItemMeta().getLore();
/* 2424:2193 */             for (String getDurability : getItemLore) {
/* 2425:2194 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/* 2426:     */               {
/* 2427:2195 */                 String durabilityRebuilder = "";
/* 2428:2196 */                 String durabilityAmountColour = "";
/* 2429:2197 */                 String prefixColourOnly = "";
/* 2430:     */                 
/* 2431:2199 */                 int index = getItemLore.indexOf(getDurability);
/* 2432:2200 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/* 2433:2201 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/* 2434:2203 */                 if (currentValueMinusOne + 1 < 2)
/* 2435:     */                 {
/* 2436:2204 */                   player.playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 2437:2205 */                   player.getInventory().remove(player.getItemInHand());
/* 2438:2206 */                   return;
/* 2439:     */                 }
/* 2440:2209 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/* 2441:     */                 {
/* 2442:2210 */                   if (getDurability.length() > 4)
/* 2443:     */                   {
/* 2444:2211 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/* 2445:2212 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/* 2446:     */                     } else {
/* 2447:2214 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2448:     */                     }
/* 2449:     */                   }
/* 2450:     */                   else {
/* 2451:2217 */                     prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2452:     */                   }
/* 2453:     */                 }
/* 2454:     */                 else {
/* 2455:2220 */                   prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour("&5&o");
/* 2456:     */                 }
/* 2457:2223 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/* 2458:     */                 {
/* 2459:2224 */                   if (getDurability.split("/")[1].trim().length() > 4)
/* 2460:     */                   {
/* 2461:2225 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/* 2462:2226 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/* 2463:     */                     } else {
/* 2464:2228 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2465:     */                     }
/* 2466:     */                   }
/* 2467:     */                   else {
/* 2468:2231 */                     durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2469:     */                   }
/* 2470:     */                 }
/* 2471:     */                 else {
/* 2472:2234 */                   durabilityAmountColour = prefixColourOnly;
/* 2473:     */                 }
/* 2474:2237 */                 currentValueMinusOne += enchantmentDurabilityLoss(player.getItemInHand());
/* 2475:     */                 
/* 2476:2239 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/* 2477:2241 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/* 2478:     */                 {
/* 2479:2242 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/* 2480:2243 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2481:     */                   }
/* 2482:2245 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2483:     */                 }
/* 2484:2246 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/* 2485:     */                 {
/* 2486:2247 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/* 2487:2248 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2488:     */                   }
/* 2489:2250 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2490:     */                 }
/* 2491:2251 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/* 2492:     */                 {
/* 2493:2252 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/* 2494:2253 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2495:     */                   }
/* 2496:2255 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2497:     */                 }
/* 2498:     */                 else
/* 2499:     */                 {
/* 2500:2257 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2501:     */                 }
/* 2502:2260 */                 getItemLore.set(index, durabilityRebuilder);
/* 2503:     */               }
/* 2504:     */             }
/* 2505:2264 */             ItemStack setItemInHand = new ItemStack(player.getItemInHand());
/* 2506:2265 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/* 2507:     */             
/* 2508:2267 */             setItemInHandMeta.setLore(getItemLore);
/* 2509:2268 */             setItemInHand.setItemMeta(setItemInHandMeta);
/* 2510:     */             
/* 2511:2270 */             player.getInventory().remove(player.getItemInHand());
/* 2512:2271 */             player.setItemInHand(new ItemStack(setItemInHand));
/* 2513:     */           }
/* 2514:     */         }
/* 2515:     */       }
/* 2516:     */     }
/* 2517:     */     
/* 2518:     */     @EventHandler
/* 2519:     */     public void flintAndSteelDurability(PlayerInteractEvent event)
/* 2520:     */     {
/* 2521:2280 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2522:     */       {
/* 2523:2281 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2524:2281 */           return;
/* 2525:     */         }
/* 2526:2282 */         Player player = event.getPlayer();
/* 2527:2284 */         if ((player.getItemInHand().getType().equals(Material.FLINT_AND_STEEL)) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && (player.getItemInHand() != null) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 2528:     */         {
/* 2529:2286 */           player.getItemInHand().setDurability((short)-1);
/* 2530:2287 */           if (player.getItemInHand().getItemMeta().hasLore())
/* 2531:     */           {
/* 2532:2289 */             String durabilityNoColour = ItemLoreStats.this.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 2533:     */             
/* 2534:2291 */             List<String> getItemLore = player.getItemInHand().getItemMeta().getLore();
/* 2535:2293 */             for (String getDurability : getItemLore) {
/* 2536:2294 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/* 2537:     */               {
/* 2538:2295 */                 String durabilityRebuilder = "";
/* 2539:2296 */                 String durabilityAmountColour = "";
/* 2540:2297 */                 String prefixColourOnly = "";
/* 2541:     */                 
/* 2542:2299 */                 int index = getItemLore.indexOf(getDurability);
/* 2543:2300 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/* 2544:2301 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/* 2545:2303 */                 if (currentValueMinusOne + 1 < 2)
/* 2546:     */                 {
/* 2547:2304 */                   player.playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 2548:2305 */                   player.getInventory().remove(player.getItemInHand());
/* 2549:2306 */                   return;
/* 2550:     */                 }
/* 2551:2309 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/* 2552:     */                 {
/* 2553:2310 */                   if (getDurability.length() > 4)
/* 2554:     */                   {
/* 2555:2311 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/* 2556:2312 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/* 2557:     */                     } else {
/* 2558:2314 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2559:     */                     }
/* 2560:     */                   }
/* 2561:     */                   else {
/* 2562:2317 */                     prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2563:     */                   }
/* 2564:     */                 }
/* 2565:     */                 else {
/* 2566:2320 */                   prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour("&5&o");
/* 2567:     */                 }
/* 2568:2323 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/* 2569:     */                 {
/* 2570:2324 */                   if (getDurability.split("/")[1].trim().length() > 4)
/* 2571:     */                   {
/* 2572:2325 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/* 2573:2326 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/* 2574:     */                     } else {
/* 2575:2328 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2576:     */                     }
/* 2577:     */                   }
/* 2578:     */                   else {
/* 2579:2331 */                     durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2580:     */                   }
/* 2581:     */                 }
/* 2582:     */                 else {
/* 2583:2334 */                   durabilityAmountColour = prefixColourOnly;
/* 2584:     */                 }
/* 2585:2337 */                 currentValueMinusOne += enchantmentDurabilityLoss(player.getItemInHand());
/* 2586:     */                 
/* 2587:2339 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/* 2588:2341 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/* 2589:     */                 {
/* 2590:2342 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/* 2591:2343 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2592:     */                   }
/* 2593:2345 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2594:     */                 }
/* 2595:2346 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/* 2596:     */                 {
/* 2597:2347 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/* 2598:2348 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2599:     */                   }
/* 2600:2350 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2601:     */                 }
/* 2602:2351 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/* 2603:     */                 {
/* 2604:2352 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/* 2605:2353 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2606:     */                   }
/* 2607:2355 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2608:     */                 }
/* 2609:     */                 else
/* 2610:     */                 {
/* 2611:2357 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2612:     */                 }
/* 2613:2360 */                 getItemLore.set(index, durabilityRebuilder);
/* 2614:     */               }
/* 2615:     */             }
/* 2616:2364 */             ItemStack setItemInHand = new ItemStack(player.getItemInHand());
/* 2617:2365 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/* 2618:     */             
/* 2619:2367 */             setItemInHandMeta.setLore(getItemLore);
/* 2620:2368 */             setItemInHand.setItemMeta(setItemInHandMeta);
/* 2621:     */             
/* 2622:2370 */             player.getInventory().remove(player.getItemInHand());
/* 2623:2371 */             player.setItemInHand(new ItemStack(setItemInHand));
/* 2624:     */           }
/* 2625:     */         }
/* 2626:     */       }
/* 2627:     */     }
/* 2628:     */     
/* 2629:     */     public int enchantmentDurabilityLoss(ItemStack getItem)
/* 2630:     */     {
/* 2631:2379 */       int r = ItemLoreStats.this.random(1000);
/* 2632:2381 */       if (getItem.containsEnchantment(Enchantment.DURABILITY)) {
/* 2633:2382 */         if (getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 1)
/* 2634:     */         {
/* 2635:2383 */           if (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingOne") > 0)
/* 2636:     */           {
/* 2637:2384 */             if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingOne") * 10) {
/* 2638:2385 */               return 0;
/* 2639:     */             }
/* 2640:2387 */             return 1;
/* 2641:     */           }
/* 2642:     */         }
/* 2643:2390 */         else if (getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 2)
/* 2644:     */         {
/* 2645:2391 */           if (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingTwo") > 0)
/* 2646:     */           {
/* 2647:2392 */             if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingTwo") * 10) {
/* 2648:2393 */               return 0;
/* 2649:     */             }
/* 2650:2395 */             return 1;
/* 2651:     */           }
/* 2652:     */         }
/* 2653:2398 */         else if ((getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 3) && (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingThree") > 0))
/* 2654:     */         {
/* 2655:2400 */           if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingThree") * 10) {
/* 2656:2401 */             return 0;
/* 2657:     */           }
/* 2658:2403 */           return 1;
/* 2659:     */         }
/* 2660:     */       }
/* 2661:2408 */       return 0;
/* 2662:     */     }
/* 2663:     */     
/* 2664:     */     @EventHandler
/* 2665:     */     public void modifyMobHealth(CreatureSpawnEvent event)
/* 2666:     */     {
/* 2667:2413 */       if ((!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getEntity().getWorld().getName())) && (ItemLoreStats.this.getConfig().getString("npcModifier." + event.getEntity().getWorld().getName()) != null))
/* 2668:     */       {
/* 2669:2415 */         Location loc = new Location(event.getEntity().getWorld(), ItemLoreStats.this.getConfig().getInt("npcModifier." + event.getEntity().getWorld().getName() + ".location.x"), ItemLoreStats.this.getConfig().getInt("npcModifier." + event.getEntity().getWorld().getName() + ".location.y"), ItemLoreStats.this.getConfig().getInt("npcModifier." + event.getEntity().getWorld().getName() + ".location.z"));
/* 2670:     */         
/* 2671:2417 */         double distance = event.getEntity().getLocation().distance(loc);
/* 2672:2418 */         double newHealth = Math.round(event.getEntity().getHealth() + distance * ItemLoreStats.this.getConfig().getDouble("npcModifier." + event.getEntity().getWorld().getName() + ".healthMultiplier"));
/* 2673:     */         
/* 2674:2420 */         event.getEntity().setMaxHealth(newHealth);
/* 2675:2421 */         event.getEntity().setHealth(newHealth);
/* 2676:     */       }
/* 2677:     */     }
/* 2678:     */   }
/* 2679:     */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.ItemLoreStats
 * JD-Core Version:    0.7.0.1
 */