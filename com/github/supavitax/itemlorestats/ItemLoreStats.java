/*    1:     */ package com.github.supavitax.itemlorestats;
/*    2:     */ 
/*    3:     */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*    4:     */ import com.github.supavitax.itemlorestats.Util.Util_Heroes;
/*    5:     */ import com.github.supavitax.itemlorestats.Util.Util_WorldGuard;
/*    6:     */ import com.herocraftonline.heroes.Heroes;
/*    7:     */ import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
/*    8:     */ import java.io.File;
/*    9:     */ import java.io.IOException;
/*   10:     */ import java.io.PrintStream;
/*   11:     */ import java.util.ArrayList;
/*   12:     */ import java.util.HashMap;
/*   13:     */ import java.util.Iterator;
/*   14:     */ import java.util.List;
/*   15:     */ import java.util.Random;
/*   16:     */ import java.util.logging.Logger;
/*   17:     */ import org.bukkit.Bukkit;
/*   18:     */ import org.bukkit.ChatColor;
/*   19:     */ import org.bukkit.Effect;
/*   20:     */ import org.bukkit.GameMode;
/*   21:     */ import org.bukkit.Location;
/*   22:     */ import org.bukkit.Material;
/*   23:     */ import org.bukkit.OfflinePlayer;
/*   24:     */ import org.bukkit.Server;
/*   25:     */ import org.bukkit.World;
/*   26:     */ import org.bukkit.block.Block;
/*   27:     */ import org.bukkit.command.Command;
/*   28:     */ import org.bukkit.command.CommandSender;
/*   29:     */ import org.bukkit.configuration.file.FileConfiguration;
/*   30:     */ import org.bukkit.configuration.file.FileConfigurationOptions;
/*   31:     */ import org.bukkit.configuration.file.YamlConfiguration;
/*   32:     */ import org.bukkit.enchantments.Enchantment;
/*   33:     */ import org.bukkit.entity.EntityType;
/*   34:     */ import org.bukkit.entity.HumanEntity;
/*   35:     */ import org.bukkit.entity.Item;
/*   36:     */ import org.bukkit.entity.LivingEntity;
/*   37:     */ import org.bukkit.entity.Player;
/*   38:     */ import org.bukkit.event.EventHandler;
/*   39:     */ import org.bukkit.event.EventPriority;
/*   40:     */ import org.bukkit.event.Listener;
/*   41:     */ import org.bukkit.event.block.Action;
/*   42:     */ import org.bukkit.event.block.BlockBreakEvent;
/*   43:     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*   44:     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*   45:     */ import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
/*   46:     */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*   47:     */ import org.bukkit.event.inventory.InventoryType;
/*   48:     */ import org.bukkit.event.inventory.PrepareItemCraftEvent;
/*   49:     */ import org.bukkit.event.player.PlayerChangedWorldEvent;
/*   50:     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*   51:     */ import org.bukkit.event.player.PlayerExpChangeEvent;
/*   52:     */ import org.bukkit.event.player.PlayerFishEvent;
/*   53:     */ import org.bukkit.event.player.PlayerInteractEvent;
/*   54:     */ import org.bukkit.event.player.PlayerItemBreakEvent;
/*   55:     */ import org.bukkit.event.player.PlayerItemHeldEvent;
/*   56:     */ import org.bukkit.event.player.PlayerJoinEvent;
/*   57:     */ import org.bukkit.event.player.PlayerLevelChangeEvent;
/*   58:     */ import org.bukkit.event.player.PlayerPickupItemEvent;
/*   59:     */ import org.bukkit.event.player.PlayerQuitEvent;
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
/*   81:  67 */   private static final Logger log = Logger.getLogger("Minecraft");
/*   82:     */   private FileConfiguration PlayerDataConfig;
/*   83:  70 */   public HashMap<String, Long> cooldowns = new HashMap();
/*   84:  71 */   public HashMap<String, Double> setBonusesModifiers = new HashMap();
/*   85:  73 */   CharacterSheet characterSheet = new CharacterSheet();
/*   86:  74 */   Classes classes = new Classes();
/*   87:  75 */   DamageSystem damageSystem = new DamageSystem(this);
/*   88:  76 */   EnvironmentalDamage environmentalDamage = new EnvironmentalDamage();
/*   89:  77 */   GearStats gearStats = new GearStats();
/*   90:  78 */   GenerateFromFile generateFromFile = new GenerateFromFile();
/*   91:  79 */   RepairItems repairItems = new RepairItems();
/*   92:  80 */   SetBonuses setBonuses = new SetBonuses();
/*   93:  81 */   Soulbound soulbound = new Soulbound();
/*   94:  82 */   Spells spells = new Spells();
/*   95:  83 */   Util_Colours util_Colours = new Util_Colours();
/*   96:     */   Util_WorldGuard util_WorldGuard;
/*   97:     */   Util_Heroes util_Heroes;
/*   98:  86 */   WriteDefaultFiles writeDefaultFiles = new WriteDefaultFiles();
/*   99:  87 */   XpLevel xpLevel = new XpLevel();
/*  100:     */   
/*  101:     */   public void onEnable()
/*  102:     */   {
/*  103:  92 */     getWorldGuard();
/*  104:  93 */     getHeroes();
/*  105:     */     
/*  106:  95 */     PluginManager plma = getServer().getPluginManager();
/*  107:  96 */     plma.registerEvents(new ItemLoreStatsListener(), this);
/*  108:  97 */     plma.registerEvents(new DamageSystem(this), this);
/*  109:  98 */     plma.registerEvents(new EnvironmentalDamage(), this);
/*  110:  99 */     plma.registerEvents(new EntityDrops(), this);
/*  111:     */     
/*  112: 101 */     plugin = this;
/*  113:     */     
/*  114: 103 */     this.writeDefaultFiles.checkExistence();
/*  115:     */     try
/*  116:     */     {
/*  117: 106 */       MetricsLite metrics = new MetricsLite(this);
/*  118: 107 */       metrics.start();
/*  119:     */     }
/*  120:     */     catch (IOException localIOException) {}
/*  121: 112 */     getConfig().options().copyDefaults(true);
/*  122: 113 */     saveConfig();
/*  123:     */   }
/*  124:     */   
/*  125:     */   public void onDisable()
/*  126:     */   {
/*  127: 118 */     log.info(String.format("[%s] Disabled Version %s", new Object[] { getDescription().getName(), getDescription().getVersion() }));
/*  128:     */   }
/*  129:     */   
/*  130:     */   public static ItemLoreStats getPlugin()
/*  131:     */   {
/*  132: 122 */     return (ItemLoreStats)Bukkit.getPluginManager().getPlugin("ItemLoreStats");
/*  133:     */   }
/*  134:     */   
/*  135:     */   public static boolean isInteger(String str)
/*  136:     */   {
/*  137:     */     try
/*  138:     */     {
/*  139: 127 */       Integer.parseInt(str);
/*  140: 128 */       return true;
/*  141:     */     }
/*  142:     */     catch (NumberFormatException nfe) {}
/*  143: 130 */     return false;
/*  144:     */   }
/*  145:     */   
/*  146:     */   public WorldGuardPlugin getWorldGuard()
/*  147:     */   {
/*  148: 135 */     Plugin WorldGuard = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
/*  149: 136 */     if ((WorldGuard == null) || (!(WorldGuard instanceof WorldGuardPlugin))) {
/*  150: 137 */       return null;
/*  151:     */     }
/*  152: 139 */     this.util_WorldGuard = new Util_WorldGuard(plugin);
/*  153: 140 */     return (WorldGuardPlugin)WorldGuard;
/*  154:     */   }
/*  155:     */   
/*  156:     */   public Heroes getHeroes()
/*  157:     */   {
/*  158: 143 */     Plugin Heroes = Bukkit.getServer().getPluginManager().getPlugin("Heroes");
/*  159: 144 */     if ((Heroes == null) || (!(Heroes instanceof Heroes))) {
/*  160: 145 */       return null;
/*  161:     */     }
/*  162: 147 */     this.util_Heroes = new Util_Heroes(plugin);
/*  163: 148 */     return (Heroes)Heroes;
/*  164:     */   }
/*  165:     */   
/*  166:     */   public String getResponse(String getKeyFromLanguageFile)
/*  167:     */   {
/*  168:     */     try
/*  169:     */     {
/*  170: 153 */       this.PlayerDataConfig = new YamlConfiguration();
/*  171: 154 */       this.PlayerDataConfig.load(new File(plugin.getDataFolder() + File.separator + getConfig().getString("languageFile") + ".yml"));
/*  172:     */       
/*  173: 156 */       return this.util_Colours.replaceTooltipColour(this.PlayerDataConfig.getString(getKeyFromLanguageFile));
/*  174:     */     }
/*  175:     */     catch (Exception e)
/*  176:     */     {
/*  177: 159 */       e.printStackTrace();
/*  178: 160 */       System.out.println("*********** Failed to load message from language file! ***********");
/*  179:     */     }
/*  180: 162 */     return "*********** Failed to load message from language file! ***********";
/*  181:     */   }
/*  182:     */   
/*  183:     */   private int random(int length)
/*  184:     */   {
/*  185: 167 */     return new Random().nextInt(length) + 1;
/*  186:     */   }
/*  187:     */   
/*  188:     */   private int randomRange(int min, int max)
/*  189:     */   {
/*  190: 170 */     return (int)(min + Math.random() * (max - min));
/*  191:     */   }
/*  192:     */   
/*  193:     */   public boolean isTool(Material material)
/*  194:     */   {
/*  195: 174 */     return (material == Material.STICK) || (material == Material.FLINT_AND_STEEL) || (material == Material.FISHING_ROD) || (material == Material.SHEARS) || (material == Material.BOW) || (material == Material.CARROT_STICK) || (material == Material.WOOD_SWORD) || (material == Material.STONE_SWORD) || (material == Material.GOLD_SWORD) || (material == Material.IRON_SWORD) || (material == Material.DIAMOND_SWORD) || (material == Material.WOOD_PICKAXE) || (material == Material.STONE_PICKAXE) || (material == Material.GOLD_PICKAXE) || (material == Material.IRON_PICKAXE) || (material == Material.DIAMOND_PICKAXE) || (material == Material.WOOD_AXE) || (material == Material.STONE_AXE) || (material == Material.GOLD_AXE) || (material == Material.IRON_AXE) || (material == Material.DIAMOND_AXE) || (material == Material.WOOD_SPADE) || (material == Material.STONE_SPADE) || (material == Material.GOLD_SPADE) || (material == Material.IRON_SPADE) || (material == Material.DIAMOND_SPADE) || (material == Material.WOOD_HOE) || (material == Material.STONE_HOE) || (material == Material.GOLD_HOE) || (material == Material.IRON_HOE) || (material == Material.DIAMOND_HOE);
/*  196:     */   }
/*  197:     */   
/*  198:     */   public boolean isArmour(Material material)
/*  199:     */   {
/*  200: 177 */     return (material == Material.LEATHER_HELMET) || (material == Material.CHAINMAIL_HELMET) || (material == Material.GOLD_HELMET) || (material == Material.IRON_HELMET) || (material == Material.DIAMOND_HELMET) || (material == Material.LEATHER_CHESTPLATE) || (material == Material.CHAINMAIL_CHESTPLATE) || (material == Material.GOLD_CHESTPLATE) || (material == Material.IRON_CHESTPLATE) || (material == Material.DIAMOND_CHESTPLATE) || (material == Material.LEATHER_LEGGINGS) || (material == Material.CHAINMAIL_LEGGINGS) || (material == Material.GOLD_LEGGINGS) || (material == Material.IRON_LEGGINGS) || (material == Material.DIAMOND_LEGGINGS) || (material == Material.LEATHER_BOOTS) || (material == Material.CHAINMAIL_BOOTS) || (material == Material.GOLD_BOOTS) || (material == Material.IRON_BOOTS) || (material == Material.DIAMOND_BOOTS);
/*  201:     */   }
/*  202:     */   
/*  203:     */   public boolean isHelmet(Material material)
/*  204:     */   {
/*  205: 180 */     return (material == Material.LEATHER_HELMET) || (material == Material.CHAINMAIL_HELMET) || (material == Material.GOLD_HELMET) || (material == Material.IRON_HELMET) || (material == Material.DIAMOND_HELMET);
/*  206:     */   }
/*  207:     */   
/*  208:     */   public boolean isChestplate(Material material)
/*  209:     */   {
/*  210: 183 */     return (material == Material.LEATHER_CHESTPLATE) || (material == Material.CHAINMAIL_CHESTPLATE) || (material == Material.GOLD_CHESTPLATE) || (material == Material.IRON_CHESTPLATE) || (material == Material.DIAMOND_CHESTPLATE);
/*  211:     */   }
/*  212:     */   
/*  213:     */   public boolean isLeggings(Material material)
/*  214:     */   {
/*  215: 186 */     return (material == Material.LEATHER_LEGGINGS) || (material == Material.CHAINMAIL_LEGGINGS) || (material == Material.GOLD_LEGGINGS) || (material == Material.IRON_LEGGINGS) || (material == Material.DIAMOND_LEGGINGS);
/*  216:     */   }
/*  217:     */   
/*  218:     */   public boolean isBoots(Material material)
/*  219:     */   {
/*  220: 189 */     return (material == Material.LEATHER_BOOTS) || (material == Material.CHAINMAIL_BOOTS) || (material == Material.GOLD_BOOTS) || (material == Material.IRON_BOOTS) || (material == Material.DIAMOND_BOOTS);
/*  221:     */   }
/*  222:     */   
/*  223:     */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*  224:     */   {
/*  225: 195 */     String armour = plugin.getConfig().getString("statNames.armour").replaceAll("&([0-9a-f])", "");
/*  226: 196 */     String dodge = plugin.getConfig().getString("statNames.dodge").replaceAll("&([0-9a-f])", "");
/*  227: 197 */     String block = plugin.getConfig().getString("statNames.block").replaceAll("&([0-9a-f])", "");
/*  228: 198 */     String critChance = plugin.getConfig().getString("statNames.critChance").replaceAll("&([0-9a-f])", "");
/*  229: 199 */     String critDamage = plugin.getConfig().getString("statNames.critDamage").replaceAll("&([0-9a-f])", "");
/*  230: 200 */     String damage = plugin.getConfig().getString("statNames.damage").replaceAll("&([0-9a-f])", "");
/*  231: 201 */     String health = plugin.getConfig().getString("statNames.health").replaceAll("&([0-9a-f])", "");
/*  232: 202 */     String healthRegen = plugin.getConfig().getString("statNames.healthregen").replaceAll("&([0-9a-f])", "");
/*  233: 203 */     String lifeSteal = plugin.getConfig().getString("statNames.lifesteal").replaceAll("&([0-9a-f])", "");
/*  234: 204 */     String reflect = plugin.getConfig().getString("statNames.reflect").replaceAll("&([0-9a-f])", "");
/*  235: 205 */     String fire = plugin.getConfig().getString("statNames.fire").replaceAll("&([0-9a-f])", "");
/*  236: 206 */     String ice = plugin.getConfig().getString("statNames.ice").replaceAll("&([0-9a-f])", "");
/*  237: 207 */     String poison = plugin.getConfig().getString("statNames.poison").replaceAll("&([0-9a-f])", "");
/*  238: 208 */     String wither = plugin.getConfig().getString("statNames.wither").replaceAll("&([0-9a-f])", "");
/*  239: 209 */     String harming = plugin.getConfig().getString("statNames.harming").replaceAll("&([0-9a-f])", "");
/*  240: 210 */     String blind = plugin.getConfig().getString("statNames.blind").replaceAll("&([0-9a-f])", "");
/*  241: 211 */     String movementspeed = plugin.getConfig().getString("statNames.movementspeed").replaceAll("&([0-9a-f])", "");
/*  242: 212 */     String weaponspeed = plugin.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/*  243: 213 */     String xplevel = plugin.getConfig().getString("statNames.xplevel").replaceAll("&([0-9a-f])", "");
/*  244: 214 */     String soulbound = plugin.getConfig().getString("statNames.soulbound").replaceAll("&([0-9a-f])", "");
/*  245: 215 */     String durability = plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/*  246: 217 */     if (cmd.getName().equalsIgnoreCase("ils"))
/*  247:     */     {
/*  248: 219 */       if (args.length == 0) {
/*  249: 220 */         if ((sender instanceof Player))
/*  250:     */         {
/*  251: 221 */           sender.sendMessage(ChatColor.GOLD + "Item Lore Stats " + ChatColor.LIGHT_PURPLE + "commands:");
/*  252: 222 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils");
/*  253: 223 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils reload");
/*  254: 224 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils stats");
/*  255: 225 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils version");
/*  256: 226 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils name " + ChatColor.GOLD + "<text>");
/*  257: 227 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils lore " + ChatColor.GOLD + "<player_name> <line#> <text>");
/*  258: 228 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils give " + ChatColor.GOLD + "<player_name> <item_name>");
/*  259: 229 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils give " + ChatColor.GOLD + "<player_name> <item_name>, <new_item_name>");
/*  260: 230 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils repair");
/*  261: 231 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils export " + ChatColor.GOLD + "<item_name>");
/*  262: 232 */           sender.sendMessage(ChatColor.LIGHT_PURPLE + "   /ils setMultiplier");
/*  263:     */         }
/*  264:     */         else
/*  265:     */         {
/*  266: 234 */           System.out.println("Item Lore Stats commands:");
/*  267: 235 */           System.out.println("   /ils");
/*  268: 236 */           System.out.println("   /ils reload");
/*  269: 237 */           System.out.println("   /ils stats");
/*  270: 238 */           System.out.println("   /ils version");
/*  271: 239 */           System.out.println("   /ils name <text>");
/*  272: 240 */           System.out.println("   /ils lore <player_name> <line#> <text>");
/*  273: 241 */           System.out.println("   /ils give <player_name> <item_name>");
/*  274: 242 */           System.out.println("   /ils give <player_name> <item_name>, <new_item_name>");
/*  275: 243 */           System.out.println("   /ils repair");
/*  276: 244 */           System.out.println("   /ils export <item_name>");
/*  277: 245 */           System.out.println("   /ils setMultiplier");
/*  278:     */         }
/*  279:     */       }
/*  280: 249 */       if (args.length > 0)
/*  281:     */       {
/*  282: 251 */         if (args[0].equalsIgnoreCase("version"))
/*  283:     */         {
/*  284: 252 */           if ((sender instanceof Player))
/*  285:     */           {
/*  286: 253 */             Player player = (Player)sender;
/*  287: 254 */             player.sendMessage(ChatColor.GOLD + "[ItemLoreStats] " + ChatColor.GREEN + " Currently running version " + getDescription().getVersion());
/*  288: 255 */             return true;
/*  289:     */           }
/*  290: 257 */           System.out.println("[ItemLoreStats] Currently running version " + getDescription().getVersion());
/*  291:     */         }
/*  292: 261 */         if (args[0].equalsIgnoreCase("name")) {
/*  293: 262 */           if ((sender instanceof Player))
/*  294:     */           {
/*  295: 263 */             Player player = (Player)sender;
/*  296: 264 */             if (player.hasPermission("ils.admin"))
/*  297:     */             {
/*  298: 265 */               if (args.length > 1)
/*  299:     */               {
/*  300: 266 */                 String storeName = player.getItemInHand().getItemMeta().getDisplayName();
/*  301: 267 */                 String newName = "";
/*  302:     */                 
/*  303: 269 */                 ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  304: 270 */                 ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  305: 272 */                 for (int i = 1; i < args.length; i++) {
/*  306: 273 */                   if (i >= 2) {
/*  307: 274 */                     newName = newName + " " + args[i];
/*  308:     */                   } else {
/*  309: 276 */                     newName = args[i];
/*  310:     */                   }
/*  311:     */                 }
/*  312: 280 */                 getItemInHandMeta.setDisplayName(this.util_Colours.replaceTooltipColour(newName));
/*  313: 281 */                 getItemInHand.setItemMeta(getItemInHandMeta);
/*  314: 282 */                 player.sendMessage(ChatColor.LIGHT_PURPLE + "Changed the name of " + ChatColor.RESET + storeName + ChatColor.LIGHT_PURPLE + " to " + ChatColor.RESET + this.util_Colours.replaceTooltipColour(newName));
/*  315: 283 */                 player.getInventory().remove(player.getItemInHand());
/*  316: 284 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  317:     */               }
/*  318:     */               else
/*  319:     */               {
/*  320: 286 */                 player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError") + " For example, /ils name " + ChatColor.DARK_RED + "Hand of God");
/*  321:     */               }
/*  322:     */             }
/*  323:     */             else {
/*  324: 290 */               player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  325:     */             }
/*  326:     */           }
/*  327:     */           else
/*  328:     */           {
/*  329: 293 */             System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/*  330:     */           }
/*  331:     */         }
/*  332: 297 */         if (args[0].equalsIgnoreCase("give")) {
/*  333: 298 */           if ((sender instanceof Player))
/*  334:     */           {
/*  335: 299 */             Player player = (Player)sender;
/*  336: 300 */             if (player.hasPermission("ils.admin"))
/*  337:     */             {
/*  338: 301 */               if (args.length > 1)
/*  339:     */               {
/*  340: 302 */                 if (player.getServer().getPlayer(args[1]) != null)
/*  341:     */                 {
/*  342: 303 */                   if (args.length > 2)
/*  343:     */                   {
/*  344: 305 */                     String newItemName = "";
/*  345: 306 */                     String replaceNewItemName = "";
/*  346: 308 */                     for (int i = 0; i < args.length; i++) {
/*  347: 309 */                       if (i >= 3) {
/*  348: 310 */                         newItemName = newItemName + " " + args[i];
/*  349:     */                       } else {
/*  350: 312 */                         newItemName = args[i];
/*  351:     */                       }
/*  352:     */                     }
/*  353: 316 */                     if (newItemName.contains(","))
/*  354:     */                     {
/*  355: 317 */                       replaceNewItemName = newItemName.split(",")[1].trim();
/*  356: 318 */                       newItemName = newItemName.split(",")[0].trim();
/*  357:     */                     }
/*  358: 321 */                     if (new File(getDataFolder() + File.separator + "SavedItems" + File.separator + newItemName + ".yml").exists())
/*  359:     */                     {
/*  360: 322 */                       Player givePlayer = player.getServer().getPlayer(args[1]);
/*  361: 324 */                       if (givePlayer.getInventory().firstEmpty() == -1) {
/*  362: 325 */                         player.sendMessage(givePlayer.getName() + ChatColor.RED + " " + getResponse("ErrorMessages.NotEnoughSpaceError"));
/*  363: 327 */                       } else if (!replaceNewItemName.equals("")) {
/*  364: 328 */                         givePlayer.getInventory().addItem(new ItemStack[] { this.generateFromFile.importWeapon(newItemName, replaceNewItemName, player.getName()) });
/*  365:     */                       } else {
/*  366: 330 */                         givePlayer.getInventory().addItem(new ItemStack[] { this.generateFromFile.importWeapon(newItemName, "noChange", player.getName()) });
/*  367:     */                       }
/*  368:     */                     }
/*  369:     */                     else
/*  370:     */                     {
/*  371: 334 */                       player.sendMessage(ChatColor.RED + newItemName + " " + getResponse("ErrorMessages.DoesntExistError"));
/*  372:     */                     }
/*  373:     */                   }
/*  374:     */                   else
/*  375:     */                   {
/*  376: 337 */                     player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError"));
/*  377:     */                   }
/*  378:     */                 }
/*  379:     */                 else {
/*  380: 340 */                   player.sendMessage(ChatColor.RED + args[1] + " " + getResponse("ErrorMessages.PlayerNotOnlineError"));
/*  381:     */                 }
/*  382:     */               }
/*  383:     */               else {
/*  384: 344 */                 player.sendMessage(getResponse("ErrorMessages.EnterPlayerNameError"));
/*  385:     */               }
/*  386:     */             }
/*  387:     */             else {
/*  388: 347 */               player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  389:     */             }
/*  390:     */           }
/*  391: 350 */           else if (args.length > 1)
/*  392:     */           {
/*  393: 351 */             if (Bukkit.getServer().getPlayer(args[1]) != null)
/*  394:     */             {
/*  395: 352 */               if (args.length > 2)
/*  396:     */               {
/*  397: 354 */                 String newItemName = "";
/*  398: 355 */                 String replaceNewItemName = "";
/*  399: 357 */                 for (int i = 0; i < args.length; i++) {
/*  400: 358 */                   if (i >= 3) {
/*  401: 359 */                     newItemName = newItemName + " " + args[i];
/*  402:     */                   } else {
/*  403: 361 */                     newItemName = args[i];
/*  404:     */                   }
/*  405:     */                 }
/*  406: 365 */                 if (newItemName.contains(",")) {
/*  407: 366 */                   replaceNewItemName = newItemName.split(",")[1].trim();
/*  408:     */                 }
/*  409: 369 */                 if (new File(getDataFolder() + File.separator + "SavedItems" + File.separator + newItemName + ".yml").exists())
/*  410:     */                 {
/*  411: 370 */                   Player givePlayer = Bukkit.getServer().getPlayer(args[1]);
/*  412: 372 */                   if (givePlayer.getInventory().firstEmpty() == -1)
/*  413:     */                   {
/*  414: 373 */                     System.out.println(givePlayer.getName() + " " + getResponse("ErrorMessages.NotEnoughSpaceError"));
/*  415:     */                   }
/*  416: 375 */                   else if (newItemName.contains(","))
/*  417:     */                   {
/*  418: 376 */                     System.out.println(givePlayer.getName() + " successfully received " + replaceNewItemName + ".");
/*  419: 377 */                     givePlayer.getInventory().addItem(new ItemStack[] { this.generateFromFile.importWeapon(newItemName, replaceNewItemName, givePlayer.getName()) });
/*  420:     */                   }
/*  421:     */                   else
/*  422:     */                   {
/*  423: 379 */                     System.out.println(givePlayer.getName() + " successfully received " + newItemName + ".");
/*  424: 380 */                     givePlayer.getInventory().addItem(new ItemStack[] { this.generateFromFile.importWeapon(newItemName, "noChange", givePlayer.getName()) });
/*  425:     */                   }
/*  426:     */                 }
/*  427:     */                 else
/*  428:     */                 {
/*  429: 384 */                   System.out.println("[ILS] " + newItemName + " " + getResponse("ErrorMessages.DoesntExistError"));
/*  430:     */                 }
/*  431:     */               }
/*  432:     */               else
/*  433:     */               {
/*  434: 387 */                 System.out.println("[ILS]" + getResponse("ErrorMessages.IncludeItemNameError"));
/*  435:     */               }
/*  436:     */             }
/*  437:     */             else {
/*  438: 390 */               System.out.println("[ILS] " + args[1] + " " + getResponse("ErrorMessages.PlayerNotOnlineError"));
/*  439:     */             }
/*  440:     */           }
/*  441:     */           else
/*  442:     */           {
/*  443: 393 */             System.out.println("[ILS]" + getResponse("ErrorMessages.EnterPlayerNameError"));
/*  444:     */           }
/*  445:     */         }
/*  446: 398 */         if (args[0].equalsIgnoreCase("export")) {
/*  447: 399 */           if ((sender instanceof Player))
/*  448:     */           {
/*  449: 400 */             Player player = (Player)sender;
/*  450: 401 */             if (player.hasPermission("ils.admin"))
/*  451:     */             {
/*  452: 402 */               if (args.length > 1)
/*  453:     */               {
/*  454: 404 */                 String newItemName = "";
/*  455: 406 */                 for (int i = 0; i < args.length; i++) {
/*  456: 407 */                   if (i >= 2) {
/*  457: 408 */                     newItemName = newItemName + " " + args[i];
/*  458:     */                   } else {
/*  459: 410 */                     newItemName = args[i];
/*  460:     */                   }
/*  461:     */                 }
/*  462: 414 */                 if (new File(getDataFolder() + File.separator + "SavedItems" + File.separator + newItemName + ".yml").exists()) {
/*  463: 415 */                   player.sendMessage(getResponse("ErrorMessages.ItemAlreadyExistsError"));
/*  464:     */                 } else {
/*  465: 417 */                   this.generateFromFile.exportWeapon(player, newItemName);
/*  466:     */                 }
/*  467:     */               }
/*  468:     */               else
/*  469:     */               {
/*  470: 420 */                 player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError") + " For example, /ils export " + ChatColor.DARK_RED + "Warbringer");
/*  471:     */               }
/*  472:     */             }
/*  473:     */             else {
/*  474: 423 */               player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  475:     */             }
/*  476:     */           }
/*  477:     */           else
/*  478:     */           {
/*  479: 426 */             System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/*  480:     */           }
/*  481:     */         }
/*  482:     */         List<String> storeItemLore;
/*  483: 430 */         if (args[0].equalsIgnoreCase("lore")) {
/*  484: 431 */           if ((sender instanceof Player))
/*  485:     */           {
/*  486: 432 */             if (args.length > 1)
/*  487:     */             {
/*  488: 433 */               if (Bukkit.getServer().getPlayer(args[1]) != null)
/*  489:     */               {
/*  490: 434 */                 Player player = Bukkit.getServer().getPlayer(args[1]);
/*  491: 435 */                 if (player.getItemInHand() != null)
/*  492:     */                 {
/*  493: 436 */                   if (player.getItemInHand().getType() != Material.AIR)
/*  494:     */                   {
/*  495: 437 */                     if (sender.hasPermission("ils.admin"))
/*  496:     */                     {
/*  497: 438 */                       if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  498:     */                       {
/*  499: 439 */                         if (args.length > 2)
/*  500:     */                         {
/*  501: 440 */                           if (isInteger(args[2]))
/*  502:     */                           {
/*  503: 441 */                             if (args.length > 3)
/*  504:     */                             {
/*  505: 442 */                               if (args.length > 4)
/*  506:     */                               {
/*  507: 444 */                                 String newLineText = "";
/*  508:     */                                 List<String> getItemLore;
/*  509:     */                                 List<String> getItemLore;
/*  510: 446 */                                 if (player.getItemInHand().getItemMeta().hasLore()) {
/*  511: 447 */                                   getItemLore = player.getItemInHand().getItemMeta().getLore();
/*  512:     */                                 } else {
/*  513: 449 */                                   getItemLore = new ArrayList();
/*  514:     */                                 }
/*  515: 452 */                                 List<String> storeItemLore = getItemLore;
/*  516: 454 */                                 if (Integer.parseInt(args[2]) - 1 >= getItemLore.size())
/*  517:     */                                 {
/*  518: 456 */                                   ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  519: 457 */                                   ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  520: 459 */                                   for (int i = 0; i < args.length; i++) {
/*  521: 460 */                                     if (i >= 4) {
/*  522: 461 */                                       newLineText = newLineText + " " + args[i];
/*  523:     */                                     } else {
/*  524: 463 */                                       newLineText = args[i];
/*  525:     */                                     }
/*  526:     */                                   }
/*  527: 467 */                                   storeItemLore.add(this.util_Colours.replaceTooltipColour(newLineText));
/*  528:     */                                   
/*  529: 469 */                                   getItemInHandMeta.setLore(storeItemLore);
/*  530:     */                                   
/*  531: 471 */                                   getItemInHand.setItemMeta(getItemInHandMeta);
/*  532: 473 */                                   if (player.getItemInHand().getItemMeta().getDisplayName() != null) {
/*  533: 474 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getItemMeta().getDisplayName());
/*  534:     */                                   } else {
/*  535: 476 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getType().name().toString());
/*  536:     */                                   }
/*  537: 479 */                                   player.getInventory().remove(player.getInventory().getItemInHand());
/*  538: 480 */                                   player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  539:     */                                 }
/*  540:     */                                 else
/*  541:     */                                 {
/*  542: 484 */                                   int lineNumber = Integer.parseInt(args[2]);
/*  543:     */                                   
/*  544: 486 */                                   ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  545: 487 */                                   ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  546: 489 */                                   for (int i = 0; i < args.length; i++) {
/*  547: 490 */                                     if (i >= 4) {
/*  548: 491 */                                       newLineText = newLineText + " " + args[i];
/*  549:     */                                     } else {
/*  550: 493 */                                       newLineText = args[i];
/*  551:     */                                     }
/*  552:     */                                   }
/*  553: 497 */                                   storeItemLore.set(lineNumber - 1, this.util_Colours.replaceTooltipColour(newLineText));
/*  554:     */                                   
/*  555: 499 */                                   getItemInHandMeta.setLore(storeItemLore);
/*  556:     */                                   
/*  557: 501 */                                   getItemInHand.setItemMeta(getItemInHandMeta);
/*  558: 503 */                                   if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  559:     */                                   {
/*  560: 504 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getItemMeta().getDisplayName());
/*  561: 505 */                                     final Player playerFinal = player;
/*  562: 506 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  563:     */                                     {
/*  564:     */                                       public void run()
/*  565:     */                                       {
/*  566: 508 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  567: 509 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  568: 510 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  569:     */                                       }
/*  570: 512 */                                     }, 2L);
/*  571:     */                                   }
/*  572:     */                                   else
/*  573:     */                                   {
/*  574: 514 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getType().name().toString());
/*  575: 515 */                                     final Player playerFinal = player;
/*  576: 516 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  577:     */                                     {
/*  578:     */                                       public void run()
/*  579:     */                                       {
/*  580: 518 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  581: 519 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  582: 520 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  583:     */                                       }
/*  584: 522 */                                     }, 2L);
/*  585:     */                                   }
/*  586: 525 */                                   player.getInventory().remove(player.getItemInHand());
/*  587: 526 */                                   player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  588:     */                                 }
/*  589:     */                               }
/*  590:     */                               else
/*  591:     */                               {
/*  592: 529 */                                 player.sendMessage(ChatColor.RED + "You need to give the stat a value. For example, /ils lore " + args[2] + " " + "Damage: " + ChatColor.DARK_RED + "+15");
/*  593:     */                               }
/*  594:     */                             }
/*  595:     */                             else {
/*  596: 532 */                               player.sendMessage(ChatColor.RED + "You need a stat to add. For example, /ils lore " + args[1] + " " + args[2] + " " + ChatColor.DARK_RED + "Damage: +15");
/*  597:     */                             }
/*  598:     */                           }
/*  599:     */                           else {
/*  600: 535 */                             player.sendMessage(ChatColor.RED + args[2] + " is not a line number. For example, /ils lore " + ChatColor.DARK_RED + "1");
/*  601:     */                           }
/*  602:     */                         }
/*  603:     */                         else {
/*  604: 538 */                           player.sendMessage(ChatColor.RED + "You need a line number and a stat. For example, /ils lore " + ChatColor.DARK_RED + "1 " + "Damage: +15");
/*  605:     */                         }
/*  606:     */                       }
/*  607:     */                       else {
/*  608: 541 */                         player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError") + " For example, " + ChatColor.DARK_RED + "/ils name Cursed Sword");
/*  609:     */                       }
/*  610:     */                     }
/*  611:     */                     else {
/*  612: 544 */                       player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  613:     */                     }
/*  614:     */                   }
/*  615:     */                   else {
/*  616: 547 */                     player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  617:     */                   }
/*  618:     */                 }
/*  619:     */                 else {
/*  620: 550 */                   player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  621:     */                 }
/*  622:     */               }
/*  623:     */               else
/*  624:     */               {
/*  625: 553 */                 Player player = (Player)sender;
/*  626: 554 */                 if (player.getItemInHand() != null)
/*  627:     */                 {
/*  628: 555 */                   if (player.getItemInHand().getType() != Material.AIR)
/*  629:     */                   {
/*  630: 556 */                     if (sender.hasPermission("ils.admin"))
/*  631:     */                     {
/*  632: 557 */                       if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  633:     */                       {
/*  634: 558 */                         if (args.length > 1)
/*  635:     */                         {
/*  636: 559 */                           if (isInteger(args[1]))
/*  637:     */                           {
/*  638: 560 */                             if (args.length > 2)
/*  639:     */                             {
/*  640: 561 */                               if (args.length > 3)
/*  641:     */                               {
/*  642: 563 */                                 String newLineText = "";
/*  643:     */                                 List<String> getItemLore;
/*  644:     */                                 List<String> getItemLore;
/*  645: 565 */                                 if (player.getItemInHand().getItemMeta().hasLore()) {
/*  646: 566 */                                   getItemLore = player.getItemInHand().getItemMeta().getLore();
/*  647:     */                                 } else {
/*  648: 568 */                                   getItemLore = new ArrayList();
/*  649:     */                                 }
/*  650: 571 */                                 storeItemLore = getItemLore;
/*  651: 573 */                                 if (Integer.parseInt(args[1]) - 1 >= getItemLore.size())
/*  652:     */                                 {
/*  653: 575 */                                   ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  654: 576 */                                   ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  655: 578 */                                   for (int i = 0; i < args.length; i++) {
/*  656: 579 */                                     if (i >= 3) {
/*  657: 580 */                                       newLineText = newLineText + " " + args[i];
/*  658:     */                                     } else {
/*  659: 582 */                                       newLineText = args[i];
/*  660:     */                                     }
/*  661:     */                                   }
/*  662: 586 */                                   storeItemLore.add(this.util_Colours.replaceTooltipColour(newLineText));
/*  663:     */                                   
/*  664: 588 */                                   getItemInHandMeta.setLore(storeItemLore);
/*  665:     */                                   
/*  666: 590 */                                   getItemInHand.setItemMeta(getItemInHandMeta);
/*  667: 592 */                                   if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  668:     */                                   {
/*  669: 593 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getItemMeta().getDisplayName());
/*  670: 594 */                                     final Player playerFinal = player;
/*  671: 595 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  672:     */                                     {
/*  673:     */                                       public void run()
/*  674:     */                                       {
/*  675: 597 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  676: 598 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  677: 599 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  678:     */                                       }
/*  679: 601 */                                     }, 2L);
/*  680:     */                                   }
/*  681:     */                                   else
/*  682:     */                                   {
/*  683: 603 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getType().name().toString());
/*  684: 604 */                                     final Player playerFinal = player;
/*  685: 605 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  686:     */                                     {
/*  687:     */                                       public void run()
/*  688:     */                                       {
/*  689: 607 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  690: 608 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  691: 609 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  692:     */                                       }
/*  693: 611 */                                     }, 2L);
/*  694:     */                                   }
/*  695: 614 */                                   player.getInventory().remove(player.getItemInHand());
/*  696: 615 */                                   player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  697:     */                                 }
/*  698:     */                                 else
/*  699:     */                                 {
/*  700: 619 */                                   int lineNumber = Integer.parseInt(args[1]);
/*  701:     */                                   
/*  702: 621 */                                   ItemStack getItemInHand = new ItemStack(player.getItemInHand());
/*  703: 622 */                                   ItemMeta getItemInHandMeta = getItemInHand.getItemMeta();
/*  704: 624 */                                   for (int i = 0; i < args.length; i++) {
/*  705: 625 */                                     if (i >= 3) {
/*  706: 626 */                                       newLineText = newLineText + " " + args[i];
/*  707:     */                                     } else {
/*  708: 628 */                                       newLineText = args[i];
/*  709:     */                                     }
/*  710:     */                                   }
/*  711: 632 */                                   storeItemLore.set(lineNumber - 1, this.util_Colours.replaceTooltipColour(newLineText));
/*  712:     */                                   
/*  713: 634 */                                   getItemInHandMeta.setLore(storeItemLore);
/*  714:     */                                   
/*  715: 636 */                                   getItemInHand.setItemMeta(getItemInHandMeta);
/*  716: 638 */                                   if (player.getItemInHand().getItemMeta().getDisplayName() != null)
/*  717:     */                                   {
/*  718: 639 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getItemMeta().getDisplayName());
/*  719: 640 */                                     final Player playerFinal = player;
/*  720: 641 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  721:     */                                     {
/*  722:     */                                       public void run()
/*  723:     */                                       {
/*  724: 643 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  725: 644 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  726: 645 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  727:     */                                       }
/*  728: 647 */                                     }, 2L);
/*  729:     */                                   }
/*  730:     */                                   else
/*  731:     */                                   {
/*  732: 649 */                                     player.sendMessage(ChatColor.LIGHT_PURPLE + "Lore added to " + ChatColor.RESET + player.getItemInHand().getType().name());
/*  733: 650 */                                     final Player playerFinal = player;
/*  734: 651 */                                     getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/*  735:     */                                     {
/*  736:     */                                       public void run()
/*  737:     */                                       {
/*  738: 653 */                                         ItemLoreStats.this.updateHealth(playerFinal);
/*  739: 654 */                                         ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/*  740: 655 */                                         ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/*  741:     */                                       }
/*  742: 657 */                                     }, 2L);
/*  743:     */                                   }
/*  744: 660 */                                   player.getInventory().remove(player.getItemInHand());
/*  745: 661 */                                   player.getInventory().addItem(new ItemStack[] { new ItemStack(getItemInHand) });
/*  746:     */                                 }
/*  747:     */                               }
/*  748:     */                               else
/*  749:     */                               {
/*  750: 665 */                                 player.sendMessage(ChatColor.RED + "You need to give the stat a value. For example, /ils lore " + args[1] + " " + "Damage: " + ChatColor.DARK_RED + "+15");
/*  751:     */                               }
/*  752:     */                             }
/*  753:     */                             else {
/*  754: 668 */                               player.sendMessage(ChatColor.RED + "You need a stat to add. For example, /ils lore " + args[1] + " " + ChatColor.DARK_RED + "Damage: +15");
/*  755:     */                             }
/*  756:     */                           }
/*  757:     */                           else {
/*  758: 671 */                             player.sendMessage(ChatColor.RED + args[1] + " is not a line number. For example, /ils lore " + ChatColor.DARK_RED + "1 " + ChatColor.RED + "Damage: +15");
/*  759:     */                           }
/*  760:     */                         }
/*  761:     */                         else {
/*  762: 674 */                           player.sendMessage(ChatColor.RED + "You need a line number and a stat. For example, /ils lore " + ChatColor.DARK_RED + "1 " + "Damage: +15");
/*  763:     */                         }
/*  764:     */                       }
/*  765:     */                       else {
/*  766: 677 */                         player.sendMessage(getResponse("ErrorMessages.IncludeItemNameError") + " For example, " + ChatColor.DARK_RED + "/ils name Cursed Sword");
/*  767:     */                       }
/*  768:     */                     }
/*  769:     */                     else {
/*  770: 680 */                       player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  771:     */                     }
/*  772:     */                   }
/*  773:     */                   else {
/*  774: 683 */                     player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  775:     */                   }
/*  776:     */                 }
/*  777:     */                 else {
/*  778: 686 */                   player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  779:     */                 }
/*  780:     */               }
/*  781:     */             }
/*  782:     */             else {
/*  783: 690 */               sender.sendMessage(ChatColor.RED + "You need a line number or player name. For example, /ils lore " + ChatColor.DARK_RED + "1" + ChatColor.RED + " or /ils lore " + ChatColor.DARK_RED + sender.getName());
/*  784:     */             }
/*  785:     */           }
/*  786:     */           else {
/*  787: 693 */             System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/*  788:     */           }
/*  789:     */         }
/*  790: 697 */         if (args.length == 1)
/*  791:     */         {
/*  792: 698 */           if (args[0].equalsIgnoreCase("repair"))
/*  793:     */           {
/*  794: 699 */             if ((sender instanceof Player))
/*  795:     */             {
/*  796: 700 */               Player player = (Player)sender;
/*  797: 702 */               if (!player.hasPermission("ils.admin")) {
/*  798: 703 */                 player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  799: 705 */               } else if (player.getItemInHand() != null)
/*  800:     */               {
/*  801: 706 */                 if (player.getItemInHand().getType() != Material.AIR)
/*  802:     */                 {
/*  803: 707 */                   if (player.getItemInHand().getItemMeta().hasLore())
/*  804:     */                   {
/*  805: 709 */                     List<String> splitItemLore = player.getItemInHand().getItemMeta().getLore();
/*  806: 711 */                     for (String getItemStat : splitItemLore)
/*  807:     */                     {
/*  808: 713 */                       String durabilityAmountColour = "";
/*  809: 714 */                       String prefixColourOnly = "";
/*  810: 715 */                       String durabilityRebuilder = "";
/*  811: 717 */                       if (ChatColor.stripColor(getItemStat).startsWith(plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "")))
/*  812:     */                       {
/*  813: 719 */                         int maxAmount = Integer.parseInt(this.util_Colours.extractAndReplaceTooltipColour(ChatColor.stripColor(getItemStat).split(": ")[1].split("/")[1]).replaceAll("&([0-9a-f])", ""));
/*  814: 720 */                         int index = splitItemLore.indexOf(getItemStat);
/*  815: 722 */                         if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)).contains("&"))
/*  816:     */                         {
/*  817: 723 */                           if (getItemStat.length() > 4)
/*  818:     */                           {
/*  819: 724 */                             if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(2, 4)).contains("&")) {
/*  820: 725 */                               prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2))) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(2, 4)));
/*  821:     */                             } else {
/*  822: 727 */                               prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)));
/*  823:     */                             }
/*  824:     */                           }
/*  825:     */                           else {
/*  826: 730 */                             prefixColourOnly = this.util_Colours.replaceTooltipColour(this.util_Colours.extractAndReplaceTooltipColour(getItemStat.substring(0, 2)));
/*  827:     */                           }
/*  828:     */                         }
/*  829:     */                         else {
/*  830: 733 */                           prefixColourOnly = this.util_Colours.replaceTooltipColour("&5&o");
/*  831:     */                         }
/*  832: 736 */                         if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim().substring(0, 2)).contains("&"))
/*  833:     */                         {
/*  834: 737 */                           if (getItemStat.split("/")[1].trim().length() > 4)
/*  835:     */                           {
/*  836: 738 */                             if (this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim().substring(2, 4)).contains("&")) {
/*  837: 739 */                               durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2) + this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(2, 4);
/*  838:     */                             } else {
/*  839: 741 */                               durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2);
/*  840:     */                             }
/*  841:     */                           }
/*  842:     */                           else {
/*  843: 744 */                             durabilityAmountColour = this.util_Colours.extractAndReplaceTooltipColour(getItemStat.split("/")[1].trim()).substring(0, 2);
/*  844:     */                           }
/*  845:     */                         }
/*  846:     */                         else {
/*  847: 747 */                           durabilityAmountColour = prefixColourOnly;
/*  848:     */                         }
/*  849: 750 */                         durabilityRebuilder = this.util_Colours.replaceTooltipColour(prefixColourOnly) + plugin.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "") + ": " + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maxAmount + this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maxAmount;
/*  850:     */                         
/*  851: 752 */                         splitItemLore.set(index, durabilityRebuilder);
/*  852:     */                         
/*  853: 754 */                         ItemStack repairedItem = new ItemStack(player.getItemInHand());
/*  854: 755 */                         ItemMeta repairedItemMeta = repairedItem.getItemMeta();
/*  855:     */                         
/*  856: 757 */                         repairedItemMeta.setLore(splitItemLore);
/*  857: 758 */                         repairedItem.setItemMeta(repairedItemMeta);
/*  858:     */                         
/*  859: 760 */                         player.setItemInHand(repairedItem);
/*  860:     */                         
/*  861: 762 */                         player.sendMessage(getResponse("RepairMessages.RepairSuccessful") + " " + player.getItemInHand().getItemMeta().getDisplayName());
/*  862:     */                       }
/*  863:     */                     }
/*  864:     */                   }
/*  865:     */                   else
/*  866:     */                   {
/*  867: 766 */                     player.sendMessage(getResponse("ErrorMessages.NoLoreError"));
/*  868:     */                   }
/*  869:     */                 }
/*  870:     */                 else {
/*  871: 769 */                   player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  872:     */                 }
/*  873:     */               }
/*  874:     */               else {
/*  875: 772 */                 player.sendMessage(getResponse("ErrorMessages.NullItemInHandError"));
/*  876:     */               }
/*  877: 775 */               return true;
/*  878:     */             }
/*  879: 777 */             System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/*  880:     */           }
/*  881: 781 */           if (args[0].equalsIgnoreCase("reload"))
/*  882:     */           {
/*  883: 782 */             if ((sender instanceof Player))
/*  884:     */             {
/*  885: 783 */               Player player = (Player)sender;
/*  886: 785 */               if (!player.hasPermission("ils.admin"))
/*  887:     */               {
/*  888: 786 */                 player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/*  889:     */               }
/*  890:     */               else
/*  891:     */               {
/*  892: 788 */                 reloadConfig();
/*  893: 789 */                 player.sendMessage(ChatColor.GOLD + "[ItemLoreStats] " + ChatColor.GREEN + " Configuration Reloaded!");
/*  894:     */               }
/*  895: 791 */               return true;
/*  896:     */             }
/*  897: 793 */             reloadConfig();
/*  898: 794 */             System.out.println("[ItemLoreStats] Configuration Reloaded!");
/*  899:     */           }
/*  900: 798 */           if (args[0].equalsIgnoreCase("createlore"))
/*  901:     */           {
/*  902: 799 */             if ((sender instanceof Player))
/*  903:     */             {
/*  904: 800 */               Player player = (Player)sender;
/*  905: 801 */               if ((sender.isOp()) || (sender.hasPermission("ils.admin")))
/*  906:     */               {
/*  907: 803 */                 ItemStack debugItem = new ItemStack(Material.LEATHER_HELMET, 1);
/*  908:     */                 
/*  909: 805 */                 ItemMeta debugItemMeta = debugItem.getItemMeta();
/*  910:     */                 
/*  911: 807 */                 ArrayList<String> debugItemList = new ArrayList();
/*  912: 808 */                 debugItemMeta.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Fire-Chanter Wrap");
/*  913: 809 */                 debugItemList.add("");
/*  914: 810 */                 debugItemList.add(ChatColor.AQUA + armour + ": " + ChatColor.DARK_GREEN + "2" + ChatColor.GREEN + "%");
/*  915: 811 */                 debugItemList.add(ChatColor.AQUA + health + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "45");
/*  916: 812 */                 debugItemList.add(ChatColor.AQUA + healthRegen + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  917: 813 */                 debugItemList.add(ChatColor.RED + fire + ": " + ChatColor.DARK_GREEN + "9" + ChatColor.GREEN + "%");
/*  918: 814 */                 debugItemList.add(ChatColor.DARK_RED + wither + ": " + ChatColor.DARK_GREEN + "4" + ChatColor.GREEN + "%");
/*  919: 815 */                 debugItemList.add("");
/*  920: 816 */                 debugItemList.add(ChatColor.GOLD + durability + ": " + "1250/1250");
/*  921: 817 */                 debugItemList.add("");
/*  922: 818 */                 debugItemList.add(ChatColor.DARK_AQUA + soulbound + " " + player.getName());
/*  923: 819 */                 debugItemMeta.setLore(debugItemList);
/*  924: 820 */                 debugItem.setItemMeta(debugItemMeta);
/*  925: 821 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem) });
/*  926:     */                 
/*  927: 823 */                 ItemStack debugItem1 = new ItemStack(Material.IRON_CHESTPLATE, 1);
/*  928: 824 */                 ItemMeta debugItemMeta1 = debugItem1.getItemMeta();
/*  929: 825 */                 ArrayList<String> debugItemList1 = new ArrayList();
/*  930: 826 */                 debugItemMeta1.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Revenant Chestplate of Health");
/*  931: 827 */                 debugItemList1.add("");
/*  932: 828 */                 debugItemList1.add(ChatColor.AQUA + armour + ": " + ChatColor.DARK_GREEN + "5" + ChatColor.GREEN + "%");
/*  933: 829 */                 debugItemList1.add(ChatColor.AQUA + health + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "109");
/*  934: 830 */                 debugItemList1.add(ChatColor.AQUA + healthRegen + ": " + ChatColor.DARK_GREEN + "4" + ChatColor.GREEN + "%");
/*  935: 831 */                 debugItemList1.add(ChatColor.LIGHT_PURPLE + poison + ": " + ChatColor.DARK_GREEN + "2" + ChatColor.GREEN + "%");
/*  936: 832 */                 debugItemList1.add(ChatColor.GREEN + lifeSteal + ": " + ChatColor.DARK_GREEN + "4" + ChatColor.GREEN + "%");
/*  937: 833 */                 debugItemList1.add("");
/*  938: 834 */                 debugItemList1.add(ChatColor.GOLD + durability + ": " + "1750/1750");
/*  939: 835 */                 debugItemList1.add("");
/*  940: 836 */                 debugItemList1.add(ChatColor.DARK_AQUA + xplevel + ": 2");
/*  941: 837 */                 debugItemMeta1.setLore(debugItemList1);
/*  942: 838 */                 debugItem1.setItemMeta(debugItemMeta1);
/*  943: 839 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem1) });
/*  944:     */                 
/*  945: 841 */                 ItemStack debugItem2 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
/*  946: 842 */                 ItemMeta debugItemMeta2 = debugItem2.getItemMeta();
/*  947: 843 */                 ArrayList<String> debugItemList2 = new ArrayList();
/*  948: 844 */                 debugItemMeta2.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "DragonScale Leg Wraps");
/*  949: 845 */                 debugItemList2.add("");
/*  950: 846 */                 debugItemList2.add(ChatColor.AQUA + armour + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  951: 847 */                 debugItemList2.add(ChatColor.AQUA + health + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "59");
/*  952: 848 */                 debugItemList2.add(ChatColor.AQUA + healthRegen + ": " + ChatColor.DARK_GREEN + "2" + ChatColor.GREEN + "%");
/*  953: 849 */                 debugItemList2.add(ChatColor.RED + fire + ": " + ChatColor.DARK_GREEN + "5" + ChatColor.GREEN + "%");
/*  954: 850 */                 debugItemList2.add(ChatColor.GREEN + lifeSteal + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  955: 851 */                 debugItemList2.add("");
/*  956: 852 */                 debugItemList2.add(ChatColor.GOLD + durability + ": " + "1500/1500");
/*  957: 853 */                 debugItemList2.add("");
/*  958: 854 */                 debugItemList2.add(ChatColor.DARK_AQUA + xplevel + ": 3");
/*  959: 855 */                 debugItemMeta2.setLore(debugItemList2);
/*  960: 856 */                 debugItem2.setItemMeta(debugItemMeta2);
/*  961: 857 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem2) });
/*  962:     */                 
/*  963: 859 */                 ItemStack debugItem3 = new ItemStack(Material.DIAMOND_BOOTS, 1);
/*  964: 860 */                 ItemMeta debugItemMeta3 = debugItem3.getItemMeta();
/*  965: 861 */                 ArrayList<String> debugItemList3 = new ArrayList();
/*  966: 862 */                 debugItemMeta3.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Boots of the Black Glacier");
/*  967: 863 */                 debugItemList3.add("");
/*  968: 864 */                 debugItemList3.add(ChatColor.AQUA + armour + ": " + ChatColor.DARK_GREEN + "9" + ChatColor.GREEN + "%");
/*  969: 865 */                 debugItemList3.add(ChatColor.AQUA + health + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "97");
/*  970: 866 */                 debugItemList3.add(ChatColor.AQUA + healthRegen + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  971: 867 */                 debugItemList3.add(ChatColor.BLUE + ice + ": " + ChatColor.DARK_GREEN + "5" + ChatColor.GREEN + "%");
/*  972: 868 */                 debugItemList3.add(ChatColor.DARK_RED + wither + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  973: 869 */                 debugItemList3.add(ChatColor.YELLOW + movementspeed + ": " + ChatColor.DARK_GREEN + "8" + ChatColor.GREEN + "%");
/*  974: 870 */                 debugItemList3.add("");
/*  975: 871 */                 debugItemList3.add(ChatColor.GOLD + durability + ": " + "1500/1500");
/*  976: 872 */                 debugItemList3.add("");
/*  977: 873 */                 debugItemList3.add(ChatColor.DARK_AQUA + xplevel + ": 4");
/*  978: 874 */                 debugItemMeta3.setLore(debugItemList3);
/*  979: 875 */                 debugItem3.setItemMeta(debugItemMeta3);
/*  980: 876 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem3) });
/*  981:     */                 
/*  982: 878 */                 ItemStack debugItem4 = new ItemStack(Material.DIAMOND_SWORD, 1);
/*  983: 879 */                 ItemMeta debugItemMeta4 = debugItem4.getItemMeta();
/*  984: 880 */                 ArrayList<String> debugItemList4 = new ArrayList();
/*  985: 881 */                 debugItemMeta4.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Slaying Thrustblade");
/*  986: 882 */                 debugItemList4.add("");
/*  987: 883 */                 debugItemList4.add(ChatColor.AQUA + weaponspeed + ": " + ChatColor.RED + "Slow");
/*  988: 884 */                 debugItemList4.add(ChatColor.AQUA + damage + ": " + ChatColor.GREEN + "+" + ChatColor.DARK_GREEN + "68" + ChatColor.DARK_GREEN + "-" + ChatColor.DARK_GREEN + "93");
/*  989: 885 */                 debugItemList4.add(ChatColor.AQUA + critChance + ": " + ChatColor.DARK_GREEN + "4" + ChatColor.GREEN + "%");
/*  990: 886 */                 debugItemList4.add(ChatColor.AQUA + critDamage + ": " + ChatColor.DARK_GREEN + "12" + ChatColor.GREEN + "%");
/*  991: 887 */                 debugItemList4.add(ChatColor.YELLOW + reflect + ": " + ChatColor.DARK_GREEN + "6" + ChatColor.GREEN + "%");
/*  992: 888 */                 debugItemList4.add(ChatColor.RED + fire + ": " + ChatColor.DARK_GREEN + "7" + ChatColor.GREEN + "%");
/*  993: 889 */                 debugItemList4.add(ChatColor.DARK_PURPLE + harming + ": " + ChatColor.DARK_GREEN + "3" + ChatColor.GREEN + "%");
/*  994: 890 */                 debugItemList4.add("");
/*  995: 891 */                 debugItemList4.add(ChatColor.GOLD + durability + ": " + "350/350");
/*  996: 892 */                 debugItemList4.add("");
/*  997: 893 */                 debugItemList4.add(ChatColor.DARK_AQUA + "Level: 1");
/*  998: 894 */                 debugItemList4.add(ChatColor.DARK_AQUA + soulbound + " " + player.getName());
/*  999: 895 */                 debugItemMeta4.setLore(debugItemList4);
/* 1000: 896 */                 debugItem4.setItemMeta(debugItemMeta4);
/* 1001: 897 */                 player.getInventory().addItem(new ItemStack[] { new ItemStack(debugItem4) });
/* 1002:     */                 
/* 1003: 899 */                 player.sendMessage(ChatColor.RED + "[DEBUGGER] " + ChatColor.WHITE + "items created!");
/* 1004:     */               }
/* 1005:     */               else
/* 1006:     */               {
/* 1007: 901 */                 player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/* 1008:     */               }
/* 1009:     */             }
/* 1010:     */             else
/* 1011:     */             {
/* 1012: 904 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1013:     */             }
/* 1014:     */           }
/* 1015: 906 */           else if (args[0].equalsIgnoreCase("setMultiplier"))
/* 1016:     */           {
/* 1017: 907 */             if ((sender instanceof Player))
/* 1018:     */             {
/* 1019: 908 */               Player player = (Player)sender;
/* 1020: 909 */               if ((sender.isOp()) || (sender.hasPermission("ils.admin")))
/* 1021:     */               {
/* 1022: 910 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".healthMultiplier", Double.valueOf(0.045D));
/* 1023: 911 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".damageMultiplier", Double.valueOf(0.04D));
/* 1024: 912 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".location.x", Integer.valueOf(player.getLocation().getBlockX()));
/* 1025: 913 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".location.y", Integer.valueOf(player.getLocation().getBlockY()));
/* 1026: 914 */                 getConfig().set("npcModifier." + player.getWorld().getName() + ".location.z", Integer.valueOf(player.getLocation().getBlockZ()));
/* 1027: 915 */                 saveConfig();
/* 1028: 916 */                 player.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully set the NPC multiplier to multiply health and damage from " + ChatColor.GOLD + player.getLocation().getBlockX() + ChatColor.LIGHT_PURPLE + ", " + ChatColor.GOLD + player.getLocation().getBlockY() + ChatColor.LIGHT_PURPLE + ", " + ChatColor.GOLD + player.getLocation().getBlockZ() + ChatColor.LIGHT_PURPLE + ".");
/* 1029:     */               }
/* 1030:     */               else
/* 1031:     */               {
/* 1032: 918 */                 player.sendMessage(getResponse("ErrorMessages.PermissionDeniedError"));
/* 1033:     */               }
/* 1034:     */             }
/* 1035:     */             else
/* 1036:     */             {
/* 1037: 921 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1038:     */             }
/* 1039:     */           }
/* 1040: 923 */           else if (args[0].equalsIgnoreCase("stats"))
/* 1041:     */           {
/* 1042: 924 */             if ((sender instanceof Player))
/* 1043:     */             {
/* 1044: 925 */               Player player = (Player)sender;
/* 1045: 926 */               this.characterSheet.returnStats(player, getHealthValue(player));
/* 1046:     */             }
/* 1047:     */             else
/* 1048:     */             {
/* 1049: 928 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1050:     */             }
/* 1051:     */           }
/* 1052: 930 */           else if (args[0].equalsIgnoreCase("health"))
/* 1053:     */           {
/* 1054: 931 */             if ((sender instanceof Player))
/* 1055:     */             {
/* 1056: 932 */               Player player = (Player)sender;
/* 1057: 933 */               player.sendMessage(ChatColor.RED + "[DEBUGGER] " + ChatColor.WHITE + player.getHealth() + " out of " + player.getMaxHealth());
/* 1058:     */             }
/* 1059:     */             else
/* 1060:     */             {
/* 1061: 935 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1062:     */             }
/* 1063:     */           }
/* 1064: 937 */           else if (args[0].equalsIgnoreCase("durability"))
/* 1065:     */           {
/* 1066: 938 */             if ((sender instanceof Player))
/* 1067:     */             {
/* 1068: 939 */               Player player = (Player)sender;
/* 1069: 940 */               player.sendMessage(ChatColor.RED + "[DEBUGGER] " + player.getItemInHand().getType().getMaxDurability());
/* 1070:     */             }
/* 1071:     */             else
/* 1072:     */             {
/* 1073: 942 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1074:     */             }
/* 1075:     */           }
/* 1076: 944 */           else if (args[0].equalsIgnoreCase("speed"))
/* 1077:     */           {
/* 1078: 945 */             if ((sender instanceof Player))
/* 1079:     */             {
/* 1080: 946 */               Player player = (Player)sender;
/* 1081: 947 */               player.sendMessage(ChatColor.RED + "[DEBUGGER] " + ChatColor.WHITE + player.getWalkSpeed());
/* 1082:     */             }
/* 1083:     */             else
/* 1084:     */             {
/* 1085: 949 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1086:     */             }
/* 1087:     */           }
/* 1088: 951 */           else if (args[0].equalsIgnoreCase("mob"))
/* 1089:     */           {
/* 1090: 952 */             if ((sender instanceof Player))
/* 1091:     */             {
/* 1092: 953 */               Player player = (Player)sender;
/* 1093: 954 */               if ((sender.isOp()) || (sender.hasPermission("ils.admin")))
/* 1094:     */               {
/* 1095: 955 */                 LivingEntity mob = (LivingEntity)player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
/* 1096: 956 */                 mob.setCustomName("Bob");
/* 1097: 957 */                 mob.setMaxHealth(25000.0D);
/* 1098: 958 */                 mob.setHealth(25000.0D);
/* 1099: 959 */                 System.out.println(mob.getHealth());
/* 1100: 960 */                 mob.getEquipment().setHelmet(player.getItemInHand());
/* 1101: 961 */                 mob.setCustomNameVisible(true);
/* 1102:     */               }
/* 1103:     */             }
/* 1104:     */             else
/* 1105:     */             {
/* 1106: 964 */               System.out.println("[ILS]" + getResponse("ErrorMessages.IngameOnlyError"));
/* 1107:     */             }
/* 1108:     */           }
/* 1109: 966 */           else if (args[0].equalsIgnoreCase("update"))
/* 1110:     */           {
/* 1111:     */             Player player;
/* 1112: 967 */             if ((sender instanceof Player))
/* 1113:     */             {
/* 1114: 968 */               player = (Player)sender;
/* 1115: 969 */               if ((sender.isOp()) || (sender.hasPermission("ils.admin")))
/* 1116:     */               {
/* 1117: 970 */                 Updater updater = new Updater(this, 67983, getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
/* 1118:     */                 
/* 1119: 972 */                 final Player playerFinal = player;
/* 1120: 973 */                 getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/* 1121:     */                 {
/* 1122:     */                   public void run()
/* 1123:     */                   {
/* 1124: 975 */                     playerFinal.sendMessage(ChatColor.GOLD + "Item Lore Stats" + ChatColor.GREEN + " update has successfully completed. To complete the update and ensure please stop the server and delete/backup the /SavedItems/ directory, the /SavedMobs/ directory and the 3 language files. Once that is completed you can start the server back up.");
/* 1125:     */                   }
/* 1126: 977 */                 }, 260L);
/* 1127:     */               }
/* 1128:     */             }
/* 1129:     */             else
/* 1130:     */             {
/* 1131: 980 */               player = new Updater(this, 67983, getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
/* 1132:     */             }
/* 1133:     */           }
/* 1134:     */         }
/* 1135:     */       }
/* 1136:     */     }
/* 1137: 986 */     return false;
/* 1138:     */   }
/* 1139:     */   
/* 1140:     */   public void debugMessage(Player player, String message)
/* 1141:     */   {
/* 1142: 990 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1143:     */     {
/* 1144: 991 */       if (!getConfig().getBoolean("debugMessages")) {
/* 1145: 991 */         return;
/* 1146:     */       }
/* 1147: 993 */       player.sendMessage(ChatColor.RED + "[DEBUGGER] " + ChatColor.WHITE + message);
/* 1148:     */     }
/* 1149:     */   }
/* 1150:     */   
/* 1151:     */   public void checkForUpdate(Player player)
/* 1152:     */   {
/* 1153: 998 */     if (getConfig().getBoolean("checkForUpdates"))
/* 1154:     */     {
/* 1155: 999 */       Updater updater = new Updater(plugin, 67983, plugin.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
/* 1156:1000 */       boolean update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
/* 1157:1001 */       final String name = updater.getLatestName();
/* 1158:1006 */       if (update) {
/* 1159:1007 */         getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/* 1160:     */         {
/* 1161:     */           public void run()
/* 1162:     */           {
/* 1163:1009 */             if (Integer.parseInt(ItemLoreStats.this.getDescription().getVersion().replace(".", "")) < Integer.parseInt(name.split(" v")[1].replace(".", ""))) {
/* 1164:1010 */               for (Player player : Bukkit.getOnlinePlayers()) {
/* 1165:1011 */                 if (player.isOp())
/* 1166:     */                 {
/* 1167:1012 */                   player.sendMessage("");
/* 1168:1013 */                   player.sendMessage(ChatColor.GREEN + "               <------- " + ChatColor.GOLD + "Update Available" + ChatColor.GREEN + " ------->");
/* 1169:1014 */                   player.sendMessage(ChatColor.GREEN + "An update is available for " + ChatColor.GOLD + ItemLoreStats.this.getDescription().getName() + ChatColor.GREEN + ". You have " + ChatColor.GOLD + "Item Lore Stats v" + ItemLoreStats.this.getDescription().getVersion() + ChatColor.GREEN + " and " + ChatColor.GOLD + name + ChatColor.GREEN + " is available from" + ChatColor.GOLD + " http://dev.bukkit.org/bukkit-plugins/item-lore-stats/ " + ChatColor.GREEN + ".");
/* 1170:1015 */                   player.sendMessage(ChatColor.GREEN + "             Type " + ChatColor.BLUE + "/ils update " + ChatColor.GREEN + "to download the update.");
/* 1171:1016 */                   player.sendMessage("");
/* 1172:     */                 }
/* 1173:     */               }
/* 1174:     */             }
/* 1175:     */           }
/* 1176:1021 */         }, 80L);
/* 1177:     */       }
/* 1178:     */     }
/* 1179:     */   }
/* 1180:     */   
/* 1181:     */   public double getHealthValue(Player player)
/* 1182:     */   {
/* 1183:1027 */     if (plugin.getConfig().getInt("baseHealth") == 0) {
/* 1184:1027 */       return 0.0D;
/* 1185:     */     }
/* 1186:1029 */     double health = 0.0D;
/* 1187:1031 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1188:     */     {
/* 1189:1032 */       if (plugin.getHeroes() != null)
/* 1190:     */       {
/* 1191:1033 */         double maxHealth = this.util_Heroes.getHeroBaseHealth(player) + this.util_Heroes.getHeroHealthPerLevel(player) * this.util_Heroes.getHeroLevel(player);
/* 1192:1035 */         if (isTool(player.getItemInHand().getType()))
/* 1193:     */         {
/* 1194:1036 */           double newHP = Double.valueOf(maxHealth + (this.gearStats.getHealthGear(player) + this.gearStats.getHealthItemInHand(player))).intValue();
/* 1195:     */           
/* 1196:1038 */           return newHP;
/* 1197:     */         }
/* 1198:1040 */         double newHP = Double.valueOf(maxHealth + this.gearStats.getHealthGear(player)).intValue();
/* 1199:     */         
/* 1200:1042 */         return newHP;
/* 1201:     */       }
/* 1202:1045 */       double maxHealth = getConfig().getDouble("baseHealth") + getConfig().getInt("healthPerLevel") * player.getLevel();
/* 1203:1047 */       if (isTool(player.getItemInHand().getType()))
/* 1204:     */       {
/* 1205:1048 */         double newHP = maxHealth + (this.gearStats.getHealthGear(player) + this.gearStats.getHealthItemInHand(player));
/* 1206:     */         
/* 1207:1050 */         return newHP;
/* 1208:     */       }
/* 1209:1052 */       double newHP = maxHealth + this.gearStats.getHealthGear(player);
/* 1210:     */       
/* 1211:1054 */       return newHP;
/* 1212:     */     }
/* 1213:1058 */     return health;
/* 1214:     */   }
/* 1215:     */   
/* 1216:     */   public void updateHealth(Player player)
/* 1217:     */   {
/* 1218:1062 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1219:     */     {
/* 1220:1063 */       double modifier = 0.0D;
/* 1221:1065 */       if (plugin.getHeroes() != null)
/* 1222:     */       {
/* 1223:1066 */         double maxHealth = this.util_Heroes.getHeroBaseHealth(player) + modifier + this.util_Heroes.getHeroHealthPerLevel(player) * this.util_Heroes.getHeroLevel(player);
/* 1224:1068 */         if (isTool(player.getItemInHand().getType()))
/* 1225:     */         {
/* 1226:1069 */           double newHP = Double.valueOf(maxHealth + (this.gearStats.getHealthGear(player) + this.gearStats.getHealthItemInHand(player))).intValue();
/* 1227:     */           
/* 1228:1071 */           player.setMaxHealth(newHP);
/* 1229:     */         }
/* 1230:     */         else
/* 1231:     */         {
/* 1232:1073 */           double newHP = Double.valueOf(maxHealth + this.gearStats.getHealthGear(player)).intValue();
/* 1233:     */           
/* 1234:1075 */           player.setMaxHealth(newHP);
/* 1235:     */         }
/* 1236:1078 */         if (getConfig().getDouble("healthScale") > 0.0D) {
/* 1237:1079 */           player.setHealthScale(getConfig().getDouble("healthScale"));
/* 1238:     */         }
/* 1239:     */       }
/* 1240:     */       else
/* 1241:     */       {
/* 1242:1082 */         if (plugin.getConfig().getInt("baseHealth") == 0) {
/* 1243:1082 */           return;
/* 1244:     */         }
/* 1245:1083 */         double maxHealth = getConfig().getDouble("baseHealth") + modifier + getConfig().getDouble("healthPerLevel") * player.getLevel();
/* 1246:1085 */         if (isTool(player.getItemInHand().getType()))
/* 1247:     */         {
/* 1248:1086 */           double newHP = Double.valueOf(maxHealth + (this.gearStats.getHealthGear(player) + this.gearStats.getHealthItemInHand(player))).intValue();
/* 1249:     */           
/* 1250:1088 */           player.setMaxHealth(newHP);
/* 1251:     */         }
/* 1252:     */         else
/* 1253:     */         {
/* 1254:1090 */           double newHP = Double.valueOf(maxHealth + this.gearStats.getHealthGear(player)).intValue();
/* 1255:     */           
/* 1256:1092 */           player.setMaxHealth(newHP);
/* 1257:     */         }
/* 1258:1095 */         if (getConfig().getDouble("healthScale") > 0.0D) {
/* 1259:1096 */           player.setHealthScale(getConfig().getDouble("healthScale"));
/* 1260:     */         }
/* 1261:     */       }
/* 1262:     */     }
/* 1263:     */   }
/* 1264:     */   
/* 1265:     */   public void updatePlayerSpeed(Player player)
/* 1266:     */   {
/* 1267:1103 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1268:     */     {
/* 1269:1104 */       final Player playerFinal = player;
/* 1270:1105 */       getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
/* 1271:     */       {
/* 1272:     */         public void run()
/* 1273:     */         {
/* 1274:1108 */           float compressedModifier = 0.0F;
/* 1275:1111 */           if (ItemLoreStats.this.isTool(playerFinal.getItemInHand().getType()))
/* 1276:     */           {
/* 1277:1112 */             float maxSpeed = 0.99F;
/* 1278:1113 */             float speed = (float)(0.002000000094994903D * (ItemLoreStats.this.gearStats.getMovementSpeedGear(playerFinal) + ItemLoreStats.this.gearStats.getMovementSpeedItemInHand(playerFinal)) + compressedModifier + 0.2000000029802322D);
/* 1279:1115 */             if (speed > maxSpeed) {
/* 1280:1116 */               playerFinal.setWalkSpeed(maxSpeed);
/* 1281:     */             } else {
/* 1282:1118 */               playerFinal.setWalkSpeed(speed);
/* 1283:     */             }
/* 1284:1120 */             ItemLoreStats.this.debugMessage(playerFinal, "Speed updated.");
/* 1285:     */           }
/* 1286:     */           else
/* 1287:     */           {
/* 1288:1122 */             float maxSpeed = 0.99F;
/* 1289:1123 */             float speed = (float)(0.002000000094994903D * ItemLoreStats.this.gearStats.getMovementSpeedGear(playerFinal) + compressedModifier + 0.2000000029802322D);
/* 1290:1125 */             if (speed > maxSpeed) {
/* 1291:1126 */               playerFinal.setWalkSpeed(maxSpeed);
/* 1292:     */             } else {
/* 1293:1128 */               playerFinal.setWalkSpeed(speed);
/* 1294:     */             }
/* 1295:     */           }
/* 1296:     */         }
/* 1297:1132 */       }, 2L);
/* 1298:     */     }
/* 1299:     */     else
/* 1300:     */     {
/* 1301:1134 */       player.setWalkSpeed(0.2F);
/* 1302:     */     }
/* 1303:     */   }
/* 1304:     */   
/* 1305:     */   public void removeWeaponSpeedEffects(Player player)
/* 1306:     */   {
/* 1307:1139 */     if (!getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName())) {
/* 1308:1140 */       if (player.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 1309:1141 */         player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 1310:1142 */       } else if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 1311:1143 */         player.removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 1312:     */       }
/* 1313:     */     }
/* 1314:     */   }
/* 1315:     */   
/* 1316:     */   public class ItemLoreStatsListener
/* 1317:     */     implements Listener
/* 1318:     */   {
/* 1319:     */     public ItemLoreStatsListener() {}
/* 1320:     */     
/* 1321:     */     @EventHandler
/* 1322:     */     public void onRegenHealth(EntityRegainHealthEvent event)
/* 1323:     */     {
/* 1324:1152 */       if ((event.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.SATIATED)) && 
/* 1325:1153 */         ((event.getEntity() instanceof Player)))
/* 1326:     */       {
/* 1327:1155 */         Player player = (Player)event.getEntity();
/* 1328:1156 */         if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1329:     */         {
/* 1330:1158 */           if (ItemLoreStats.plugin.getConfig().getDouble("baseHealthRegen") == 0.0D) {
/* 1331:1158 */             return;
/* 1332:     */           }
/* 1333:1160 */           double gearRegen = 0.0D;
/* 1334:1161 */           double modifier = 0.0D;
/* 1335:1164 */           if (ItemLoreStats.this.isTool(player.getItemInHand().getType())) {
/* 1336:1165 */             gearRegen = ItemLoreStats.this.gearStats.getHealthRegenGear(player) + ItemLoreStats.this.gearStats.getHealthRegenItemInHand(player);
/* 1337:     */           } else {
/* 1338:1167 */             gearRegen = ItemLoreStats.this.gearStats.getHealthRegenGear(player);
/* 1339:     */           }
/* 1340:1170 */           double baseRegen = ItemLoreStats.this.getConfig().getDouble("baseHealthRegen");
/* 1341:1171 */           double modifiedHealthRegen = player.getMaxHealth() / 100.0D * (gearRegen + baseRegen + modifier);
/* 1342:     */           
/* 1343:1173 */           event.setAmount(modifiedHealthRegen);
/* 1344:     */           
/* 1345:1175 */           ItemLoreStats.this.debugMessage(player, "Health regenerating. Currently " + ChatColor.RED + Math.round(player.getHealth()) + ChatColor.RESET + "/" + ChatColor.GREEN + Math.round(player.getMaxHealth()) + ".");
/* 1346:     */         }
/* 1347:     */       }
/* 1348:     */     }
/* 1349:     */     
/* 1350:     */     @EventHandler
/* 1351:     */     public void onPlayerRespawn(PlayerRespawnEvent event)
/* 1352:     */     {
/* 1353:1183 */       Player player = event.getPlayer();
/* 1354:1185 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1355:     */       {
/* 1356:1186 */         final Player playerFinal = player;
/* 1357:1187 */         ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1358:     */         {
/* 1359:     */           public void run()
/* 1360:     */           {
/* 1361:1189 */             ItemLoreStats.this.updateHealth(playerFinal);
/* 1362:1190 */             ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/* 1363:1191 */             ItemLoreStats.this.setBonuses.updateSetBonus(playerFinal);
/* 1364:1192 */             ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/* 1365:     */           }
/* 1366:1194 */         }, 2L);
/* 1367:1195 */         ItemLoreStats.this.debugMessage(player, "Health updated.");
/* 1368:     */       }
/* 1369:     */     }
/* 1370:     */     
/* 1371:     */     @EventHandler
/* 1372:     */     public void onPlayerPickupExp(PlayerExpChangeEvent event)
/* 1373:     */     {
/* 1374:1201 */       Player player = event.getPlayer();
/* 1375:1202 */       if (ItemLoreStats.this.isTool(player.getItemInHand().getType()))
/* 1376:     */       {
/* 1377:1203 */         if (ItemLoreStats.this.gearStats.getXPMultiplierGear(player) + ItemLoreStats.this.gearStats.getXPMultiplierItemInHand(player) > 0.0D)
/* 1378:     */         {
/* 1379:1204 */           double bonusExp = 0.0D;
/* 1380:1205 */           double xpMultiplier = ItemLoreStats.this.gearStats.getXPMultiplierGear(player) + ItemLoreStats.this.gearStats.getXPMultiplierItemInHand(player);
/* 1381:     */           
/* 1382:1207 */           bonusExp = event.getAmount() * xpMultiplier / 100.0D;
/* 1383:1208 */           player.giveExp((int)bonusExp);
/* 1384:     */         }
/* 1385:     */       }
/* 1386:1211 */       else if (ItemLoreStats.this.gearStats.getXPMultiplierGear(player) > 0.0D)
/* 1387:     */       {
/* 1388:1212 */         double bonusExp = 0.0D;
/* 1389:1213 */         double xpMultiplier = ItemLoreStats.this.gearStats.getXPMultiplierGear(player);
/* 1390:     */         
/* 1391:1215 */         bonusExp = event.getAmount() * xpMultiplier / 100.0D;
/* 1392:1216 */         player.giveExp((int)bonusExp);
/* 1393:     */       }
/* 1394:     */     }
/* 1395:     */     
/* 1396:     */     @EventHandler
/* 1397:     */     public void onPlayerLevel(PlayerLevelChangeEvent event)
/* 1398:     */     {
/* 1399:1221 */       ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1400:     */     }
/* 1401:     */     
/* 1402:     */     @EventHandler
/* 1403:     */     public void onPlayerJoin(PlayerJoinEvent event)
/* 1404:     */     {
/* 1405:1227 */       final Player playerFinal = event.getPlayer();
/* 1406:1228 */       ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1407:     */       {
/* 1408:     */         public void run()
/* 1409:     */         {
/* 1410:1230 */           ItemLoreStats.this.updateHealth(playerFinal);
/* 1411:1231 */           ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/* 1412:1232 */           ItemLoreStats.this.setBonuses.updateSetBonus(playerFinal);
/* 1413:1233 */           ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/* 1414:1234 */           playerFinal.setHealth(playerFinal.getMaxHealth());
/* 1415:     */         }
/* 1416:1236 */       }, 2L);
/* 1417:     */       
/* 1418:1238 */       ItemLoreStats.this.checkForUpdate(event.getPlayer());
/* 1419:     */     }
/* 1420:     */     
/* 1421:     */     @EventHandler
/* 1422:     */     public void onPlayerQuit(PlayerQuitEvent event)
/* 1423:     */     {
/* 1424:1244 */       event.getPlayer().setMaxHealth(20.0D);
/* 1425:     */     }
/* 1426:     */     
/* 1427:     */     @EventHandler
/* 1428:     */     public void onDropItemEvent(PlayerDropItemEvent event)
/* 1429:     */     {
/* 1430:1249 */       Player player = event.getPlayer();
/* 1431:1250 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1432:     */       {
/* 1433:1251 */         final Player playerFinal = player;
/* 1434:1252 */         ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1435:     */         {
/* 1436:     */           public void run()
/* 1437:     */           {
/* 1438:1254 */             ItemLoreStats.this.updateHealth(playerFinal);
/* 1439:1255 */             ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/* 1440:1256 */             ItemLoreStats.this.setBonuses.updateSetBonus(playerFinal);
/* 1441:1257 */             ItemLoreStats.this.removeWeaponSpeedEffects(playerFinal);
/* 1442:     */           }
/* 1443:1259 */         }, 2L);
/* 1444:     */       }
/* 1445:     */     }
/* 1446:     */     
/* 1447:     */     public void swapItems(int slotA, int slotB, Inventory inv)
/* 1448:     */     {
/* 1449:1264 */       ItemStack itemStackA = inv.getItem(slotA);
/* 1450:1265 */       ItemStack itemStackB = inv.getItem(slotB);
/* 1451:1266 */       inv.setItem(slotA, itemStackB);
/* 1452:1267 */       inv.setItem(slotB, itemStackA);
/* 1453:     */     }
/* 1454:     */     
/* 1455:     */     @EventHandler
/* 1456:     */     public void onPlayerHeldItemChange(final PlayerItemHeldEvent event)
/* 1457:     */     {
/* 1458:1285 */       Player player = event.getPlayer();
/* 1459:1287 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1460:     */       {
/* 1461:1289 */         final Player playerFinal = event.getPlayer();
/* 1462:1290 */         ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1463:     */         {
/* 1464:     */           public void run()
/* 1465:     */           {
/* 1466:1293 */             ItemStack checkItemHeld = playerFinal.getInventory().getItem(event.getNewSlot());
/* 1467:1295 */             if ((checkItemHeld != null) && 
/* 1468:1296 */               (checkItemHeld.getType() != Material.AIR) && 
/* 1469:1297 */               (checkItemHeld.getItemMeta() != null) && 
/* 1470:1298 */               (checkItemHeld.getItemMeta().getLore() != null) && 
/* 1471:1299 */               (ItemLoreStats.this.isTool(checkItemHeld.getType())))
/* 1472:     */             {
/* 1473:1301 */               String weaponSpeed = "";
/* 1474:1302 */               String strippedWeaponSpeed = ItemLoreStats.this.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/* 1475:1304 */               if ((ItemLoreStats.this.gearStats.playerHeldItemChangeSoulboundNameItemInHand(checkItemHeld) != null) && 
/* 1476:1305 */                 (!ItemLoreStats.this.gearStats.playerHeldItemChangeSoulboundNameItemInHand(checkItemHeld).equals(event.getPlayer().getName())))
/* 1477:     */               {
/* 1478:1306 */                 ItemLoreStats.ItemLoreStatsListener.this.swapItems(event.getNewSlot(), event.getPreviousSlot(), playerFinal.getInventory());
/* 1479:1307 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1480:1308 */                 ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand is bound to someone else. Item in hand bound to: " + ItemLoreStats.this.gearStats.playerHeldItemChangeSoulboundNameItemInHand(checkItemHeld));
/* 1481:1309 */                 return;
/* 1482:     */               }
/* 1483:1313 */               if ((ItemLoreStats.this.gearStats.playerHeldItemChangeClassItemInHand(checkItemHeld) != null) && 
/* 1484:1314 */                 (!event.getPlayer().hasPermission("ils.use." + ItemLoreStats.this.gearStats.playerHeldItemChangeClassItemInHand(checkItemHeld))))
/* 1485:     */               {
/* 1486:1315 */                 ItemLoreStats.ItemLoreStatsListener.this.swapItems(event.getNewSlot(), event.getPreviousSlot(), playerFinal.getInventory());
/* 1487:1316 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1488:1317 */                 ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand can only be used by players with the " + ItemLoreStats.this.gearStats.playerHeldItemChangeClassItemInHand(checkItemHeld) + " permission node.");
/* 1489:1318 */                 return;
/* 1490:     */               }
/* 1491:1322 */               if ((ItemLoreStats.this.gearStats.playerHeldItemChangeXPLevelRequirementItemInHand(checkItemHeld) != 0) && 
/* 1492:1323 */                 (ItemLoreStats.this.gearStats.playerHeldItemChangeXPLevelRequirementItemInHand(checkItemHeld) > event.getPlayer().getLevel()))
/* 1493:     */               {
/* 1494:1324 */                 ItemLoreStats.ItemLoreStatsListener.this.swapItems(event.getNewSlot(), event.getPreviousSlot(), playerFinal.getInventory());
/* 1495:1325 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1496:1326 */                 ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand requires a higher level. Item in hand level: " + ItemLoreStats.this.gearStats.playerHeldItemChangeXPLevelRequirementItemInHand(checkItemHeld) + ", Player level: " + event.getPlayer().getLevel());
/* 1497:1327 */                 return;
/* 1498:     */               }
/* 1499:1331 */               String[] splitLore = checkItemHeld.getItemMeta().getLore().toString().split(", ");
/* 1500:1332 */               for (String getStat : splitLore) {
/* 1501:1334 */                 if (ChatColor.stripColor(getStat).startsWith(strippedWeaponSpeed + ": "))
/* 1502:     */                 {
/* 1503:1335 */                   if (weaponSpeed.length() < 2)
/* 1504:     */                   {
/* 1505:1336 */                     weaponSpeed = ChatColor.stripColor(getStat).replace("[", "").trim().substring((strippedWeaponSpeed + ": ").length()).replace("]", "").trim().toLowerCase();
/* 1506:1337 */                     if (weaponSpeed.toString().equalsIgnoreCase("fast"))
/* 1507:     */                     {
/* 1508:1338 */                       if (event.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 1509:1339 */                         event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 1510:     */                       }
/* 1511:1341 */                       ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1512:1342 */                       ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1513:1343 */                       ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1514:1344 */                       event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1));
/* 1515:1345 */                       return;
/* 1516:     */                     }
/* 1517:1346 */                     if (weaponSpeed.toString().equalsIgnoreCase("slow"))
/* 1518:     */                     {
/* 1519:1347 */                       if (event.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 1520:1348 */                         event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 1521:     */                       }
/* 1522:1350 */                       ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1523:1351 */                       ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1524:1352 */                       ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1525:1353 */                       event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, 1));
/* 1526:1354 */                       return;
/* 1527:     */                     }
/* 1528:1356 */                     ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1529:1357 */                     ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1530:1358 */                     ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1531:1359 */                     ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1532:1360 */                     return;
/* 1533:     */                   }
/* 1534:1363 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1535:     */                 }
/* 1536:1366 */                 else if (weaponSpeed.length() < 2)
/* 1537:     */                 {
/* 1538:1367 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1539:     */                 }
/* 1540:     */               }
/* 1541:     */             }
/* 1542:1378 */             ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1543:1379 */             ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1544:1380 */             ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1545:1381 */             ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1546:     */           }
/* 1547:1384 */         }, 2L);
/* 1548:     */       }
/* 1549:     */     }
/* 1550:     */     
/* 1551:1390 */     private List<Integer> tempButton = new ArrayList();
/* 1552:1391 */     private ItemStack button = new ItemStack(Material.STONE_BUTTON);
/* 1553:     */     
/* 1554:     */     @EventHandler(priority=EventPriority.NORMAL, ignoreCancelled=true)
/* 1555:     */     public void onPlayerPickupItemEvent(PlayerPickupItemEvent event)
/* 1556:     */     {
/* 1557:1395 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 1558:     */       {
/* 1559:1396 */         if (event.getItem().getItemStack().getItemMeta().getLore() == null) {
/* 1560:1396 */           return;
/* 1561:     */         }
/* 1562:1398 */         if ((ItemLoreStats.this.isTool(event.getItem().getItemStack().getType())) || (ItemLoreStats.this.isArmour(event.getItem().getItemStack().getType())))
/* 1563:     */         {
/* 1564:1400 */           String weaponSpeed = "0";
/* 1565:1401 */           String strippedWeaponSpeed = ItemLoreStats.this.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/* 1566:1403 */           if ((ItemLoreStats.this.gearStats.getSoulboundNameItemInHand(event.getPlayer()) != null) && 
/* 1567:1404 */             (ItemLoreStats.this.gearStats.getSoulboundNameItemInHand(event.getPlayer()) != event.getPlayer().getName()))
/* 1568:     */           {
/* 1569:1405 */             final PlayerInventory inventory = event.getPlayer().getInventory();
/* 1570:1407 */             for (int i = 0; i < 9; i++) {
/* 1571:1408 */               if (inventory.getItem(i) == null)
/* 1572:     */               {
/* 1573:1409 */                 inventory.setItem(i, this.button);
/* 1574:1410 */                 this.tempButton.add(Integer.valueOf(i));
/* 1575:     */               }
/* 1576:     */             }
/* 1577:1414 */             ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1578:     */             {
/* 1579:     */               public void run()
/* 1580:     */               {
/* 1581:1416 */                 for (Iterator localIterator = ItemLoreStats.ItemLoreStatsListener.this.tempButton.iterator(); localIterator.hasNext();)
/* 1582:     */                 {
/* 1583:1416 */                   int i = ((Integer)localIterator.next()).intValue();
/* 1584:1417 */                   inventory.clear(i);
/* 1585:     */                 }
/* 1586:1419 */                 ItemLoreStats.ItemLoreStatsListener.this.tempButton.clear();
/* 1587:     */               }
/* 1588:1421 */             }, 1L);
/* 1589:     */             
/* 1590:1423 */             ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand is bound to someone else. Item in hand bound to: " + ItemLoreStats.this.gearStats.getSoulboundNameItemInHand(event.getPlayer()));
/* 1591:     */           }
/* 1592:1427 */           if ((ItemLoreStats.this.gearStats.getClassItemInHand(event.getPlayer()) != null) && 
/* 1593:1428 */             (!event.getPlayer().hasPermission("ils.use." + ItemLoreStats.this.gearStats.getClassItemInHand(event.getPlayer()))))
/* 1594:     */           {
/* 1595:1429 */             final PlayerInventory inventory = event.getPlayer().getInventory();
/* 1596:1431 */             for (int i = 0; i < 9; i++) {
/* 1597:1432 */               if (inventory.getItem(i) == null)
/* 1598:     */               {
/* 1599:1433 */                 inventory.setItem(i, this.button);
/* 1600:1434 */                 this.tempButton.add(Integer.valueOf(i));
/* 1601:     */               }
/* 1602:     */             }
/* 1603:1438 */             ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1604:     */             {
/* 1605:     */               public void run()
/* 1606:     */               {
/* 1607:1440 */                 for (Iterator localIterator = ItemLoreStats.ItemLoreStatsListener.this.tempButton.iterator(); localIterator.hasNext();)
/* 1608:     */                 {
/* 1609:1440 */                   int i = ((Integer)localIterator.next()).intValue();
/* 1610:1441 */                   inventory.clear(i);
/* 1611:     */                 }
/* 1612:1443 */                 ItemLoreStats.ItemLoreStatsListener.this.tempButton.clear();
/* 1613:     */               }
/* 1614:1445 */             }, 1L);
/* 1615:     */             
/* 1616:1447 */             ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand can only be used by players with the " + ItemLoreStats.this.gearStats.getClassItemInHand(event.getPlayer()) + " permission node.");
/* 1617:     */           }
/* 1618:1451 */           if ((ItemLoreStats.this.gearStats.getXPLevelRequirementItemInHand(event.getPlayer()) != 0) && 
/* 1619:1452 */             (ItemLoreStats.this.gearStats.getXPLevelRequirementItemInHand(event.getPlayer()) >= event.getPlayer().getLevel()))
/* 1620:     */           {
/* 1621:1453 */             final PlayerInventory inventory = event.getPlayer().getInventory();
/* 1622:1455 */             for (int i = 0; i < 9; i++) {
/* 1623:1456 */               if (inventory.getItem(i) == null)
/* 1624:     */               {
/* 1625:1457 */                 inventory.setItem(i, this.button);
/* 1626:1458 */                 this.tempButton.add(Integer.valueOf(i));
/* 1627:     */               }
/* 1628:     */             }
/* 1629:1462 */             ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 1630:     */             {
/* 1631:     */               public void run()
/* 1632:     */               {
/* 1633:1464 */                 for (Iterator localIterator = ItemLoreStats.ItemLoreStatsListener.this.tempButton.iterator(); localIterator.hasNext();)
/* 1634:     */                 {
/* 1635:1464 */                   int i = ((Integer)localIterator.next()).intValue();
/* 1636:1465 */                   inventory.clear(i);
/* 1637:     */                 }
/* 1638:1467 */                 ItemLoreStats.ItemLoreStatsListener.this.tempButton.clear();
/* 1639:     */               }
/* 1640:1469 */             }, 1L);
/* 1641:     */             
/* 1642:1471 */             ItemLoreStats.this.debugMessage(event.getPlayer(), "Item in hand requires a higher level. Item in hand level: " + ItemLoreStats.this.gearStats.getXPLevelRequirementItemInHand(event.getPlayer()) + ", Player level: " + event.getPlayer().getLevel());
/* 1643:     */           }
/* 1644:1475 */           String[] splitLore = event.getItem().getItemStack().getItemMeta().getLore().toString().split(", ");
/* 1645:1477 */           for (String getStat : splitLore) {
/* 1646:1478 */             if (ChatColor.stripColor(getStat).startsWith(strippedWeaponSpeed + ": "))
/* 1647:     */             {
/* 1648:1479 */               if (weaponSpeed.length() < 2)
/* 1649:     */               {
/* 1650:1480 */                 weaponSpeed = ChatColor.stripColor(getStat).replace("[", "").trim().substring((strippedWeaponSpeed + ": ").length()).replace("]", "").trim().toLowerCase();
/* 1651:1482 */                 if (weaponSpeed.toString().equalsIgnoreCase("fast"))
/* 1652:     */                 {
/* 1653:1483 */                   if (event.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 1654:1484 */                     event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 1655:     */                   }
/* 1656:1486 */                   ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1657:1487 */                   ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1658:1488 */                   ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1659:1489 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1660:1490 */                   event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1));
/* 1661:1491 */                   return;
/* 1662:     */                 }
/* 1663:1492 */                 if (weaponSpeed.toString().equalsIgnoreCase("slow"))
/* 1664:     */                 {
/* 1665:1493 */                   if (event.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 1666:1494 */                     event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 1667:     */                   }
/* 1668:1496 */                   ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1669:1497 */                   ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1670:1498 */                   ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1671:1499 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1672:1500 */                   event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, 1));
/* 1673:1501 */                   return;
/* 1674:     */                 }
/* 1675:1502 */                 if (weaponSpeed.toString().equalsIgnoreCase("normal"))
/* 1676:     */                 {
/* 1677:1503 */                   ItemLoreStats.this.updateHealth(event.getPlayer());
/* 1678:1504 */                   ItemLoreStats.this.updatePlayerSpeed(event.getPlayer());
/* 1679:1505 */                   ItemLoreStats.this.setBonuses.updateSetBonus(event.getPlayer());
/* 1680:1506 */                   ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1681:     */                 }
/* 1682:     */               }
/* 1683:     */               else
/* 1684:     */               {
/* 1685:1510 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1686:     */               }
/* 1687:     */             }
/* 1688:1513 */             else if (weaponSpeed.length() < 2) {
/* 1689:1514 */               ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1690:     */             }
/* 1691:     */           }
/* 1692:     */         }
/* 1693:     */         else
/* 1694:     */         {
/* 1695:1519 */           ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 1696:     */         }
/* 1697:     */       }
/* 1698:     */     }
/* 1699:     */     
/* 1700:     */     @EventHandler
/* 1701:     */     public void onInventoryClose(InventoryCloseEvent event)
/* 1702:     */     {
/* 1703:1529 */       if ((!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName())) && 
/* 1704:1530 */         (ItemLoreStats.this.getServer().getPlayer(event.getPlayer().getName()).isOnline()) && (
/* 1705:1531 */         (event.getInventory().getType().equals(InventoryType.ANVIL)) || 
/* 1706:1532 */         (event.getInventory().getType().equals(InventoryType.BEACON)) || 
/* 1707:1533 */         (event.getInventory().getType().equals(InventoryType.BREWING)) || 
/* 1708:1534 */         (event.getInventory().getType().equals(InventoryType.CHEST)) || 
/* 1709:1535 */         (event.getInventory().getType().equals(InventoryType.CRAFTING)) || 
/* 1710:1536 */         (event.getInventory().getType().equals(InventoryType.CREATIVE)) || 
/* 1711:1537 */         (event.getInventory().getType().equals(InventoryType.DISPENSER)) || 
/* 1712:1538 */         (event.getInventory().getType().equals(InventoryType.DROPPER)) || 
/* 1713:1539 */         (event.getInventory().getType().equals(InventoryType.ENCHANTING)) || 
/* 1714:1540 */         (event.getInventory().getType().equals(InventoryType.ENDER_CHEST)) || 
/* 1715:1541 */         (event.getInventory().getType().equals(InventoryType.FURNACE)) || 
/* 1716:1542 */         (event.getInventory().getType().equals(InventoryType.HOPPER)) || 
/* 1717:1543 */         (event.getInventory().getType().equals(InventoryType.MERCHANT)) || 
/* 1718:1544 */         (event.getInventory().getType().equals(InventoryType.PLAYER)) || 
/* 1719:1545 */         (event.getInventory().getType().equals(InventoryType.WORKBENCH))))
/* 1720:     */       {
/* 1721:1547 */         Player player = (Player)event.getPlayer();
/* 1722:     */         
/* 1723:1549 */         ItemLoreStats.this.xpLevel.checkXpLevelHead(player);
/* 1724:1550 */         ItemLoreStats.this.xpLevel.checkXpLevelChest(player);
/* 1725:1551 */         ItemLoreStats.this.xpLevel.checkXpLevelLegs(player);
/* 1726:1552 */         ItemLoreStats.this.xpLevel.checkXpLevelBoots(player);
/* 1727:1553 */         ItemLoreStats.this.xpLevel.checkXpLevelItemInHand(player);
/* 1728:     */         
/* 1729:1555 */         ItemLoreStats.this.soulbound.checkSoulboundHead(player);
/* 1730:1556 */         ItemLoreStats.this.soulbound.checkSoulboundChest(player);
/* 1731:1557 */         ItemLoreStats.this.soulbound.checkSoulboundLegs(player);
/* 1732:1558 */         ItemLoreStats.this.soulbound.checkSoulboundBoots(player);
/* 1733:1559 */         ItemLoreStats.this.soulbound.checkSoulboundItemInHand(player);
/* 1734:     */         
/* 1735:1561 */         ItemLoreStats.this.classes.checkClassHead(player);
/* 1736:1562 */         ItemLoreStats.this.classes.checkClassChest(player);
/* 1737:1563 */         ItemLoreStats.this.classes.checkClassLegs(player);
/* 1738:1564 */         ItemLoreStats.this.classes.checkClassBoots(player);
/* 1739:1565 */         ItemLoreStats.this.classes.checkClassItemInHand(player);
/* 1740:1567 */         if (!ItemLoreStats.this.getServer().getOfflinePlayer(player.getName()).isOnline()) {
/* 1741:1567 */           return;
/* 1742:     */         }
/* 1743:1569 */         if (player.getItemInHand() != null)
/* 1744:     */         {
/* 1745:1570 */           if (player.getItemInHand().getType() != Material.AIR)
/* 1746:     */           {
/* 1747:1571 */             if (player.getItemInHand().getItemMeta() != null)
/* 1748:     */             {
/* 1749:1572 */               if (player.getItemInHand().getItemMeta().getLore() != null)
/* 1750:     */               {
/* 1751:1573 */                 String weaponSpeed = "0";
/* 1752:1574 */                 String strippedWeaponSpeed = ItemLoreStats.this.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/* 1753:     */                 
/* 1754:1576 */                 String[] splitLore = player.getItemInHand().getItemMeta().getLore().toString().split(", ");
/* 1755:     */                 
/* 1756:1578 */                 ItemLoreStats.this.updateHealth(player);
/* 1757:1580 */                 for (String getStat : splitLore) {
/* 1758:1581 */                   if (ChatColor.stripColor(getStat).startsWith(strippedWeaponSpeed))
/* 1759:     */                   {
/* 1760:1582 */                     if (weaponSpeed.length() < 2)
/* 1761:     */                     {
/* 1762:1583 */                       weaponSpeed = ChatColor.stripColor(getStat).replace("[", "").trim().substring((strippedWeaponSpeed + ": ").length()).replace("]", "").trim().toLowerCase();
/* 1763:1584 */                       if (weaponSpeed.toString().equalsIgnoreCase("fast"))
/* 1764:     */                       {
/* 1765:1585 */                         if (player.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 1766:1586 */                           player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 1767:     */                         }
/* 1768:1588 */                         ItemLoreStats.this.updateHealth(player);
/* 1769:1589 */                         ItemLoreStats.this.updatePlayerSpeed(player);
/* 1770:1590 */                         ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1771:1591 */                         ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1772:1592 */                         player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1));
/* 1773:1593 */                         return;
/* 1774:     */                       }
/* 1775:1594 */                       if (weaponSpeed.toString().equalsIgnoreCase("slow"))
/* 1776:     */                       {
/* 1777:1595 */                         if (player.hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 1778:1596 */                           player.removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 1779:     */                         }
/* 1780:1598 */                         ItemLoreStats.this.updateHealth(player);
/* 1781:1599 */                         ItemLoreStats.this.updatePlayerSpeed(player);
/* 1782:1600 */                         ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1783:1601 */                         ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1784:1602 */                         player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, 1));
/* 1785:1603 */                         return;
/* 1786:     */                       }
/* 1787:1604 */                       if (weaponSpeed.toString().equalsIgnoreCase("normal"))
/* 1788:     */                       {
/* 1789:1605 */                         ItemLoreStats.this.updateHealth(player);
/* 1790:1606 */                         ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1791:1607 */                         ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1792:1608 */                         ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1793:     */                       }
/* 1794:     */                     }
/* 1795:     */                   }
/* 1796:1613 */                   else if (weaponSpeed.length() < 2) {
/* 1797:1614 */                     ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1798:     */                   }
/* 1799:     */                 }
/* 1800:     */               }
/* 1801:     */               else
/* 1802:     */               {
/* 1803:1619 */                 ItemLoreStats.this.updateHealth(player);
/* 1804:1620 */                 ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1805:1621 */                 ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1806:1622 */                 ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1807:     */               }
/* 1808:     */             }
/* 1809:     */             else
/* 1810:     */             {
/* 1811:1625 */               ItemLoreStats.this.updateHealth(player);
/* 1812:1626 */               ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1813:1627 */               ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1814:1628 */               ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1815:     */             }
/* 1816:     */           }
/* 1817:     */           else
/* 1818:     */           {
/* 1819:1631 */             ItemLoreStats.this.updateHealth(player);
/* 1820:1632 */             ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1821:1633 */             ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1822:1634 */             ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1823:     */           }
/* 1824:1636 */           player.updateInventory();
/* 1825:1637 */           ItemLoreStats.this.debugMessage(player, "Inventory updated.");
/* 1826:     */         }
/* 1827:     */         else
/* 1828:     */         {
/* 1829:1639 */           ItemLoreStats.this.updateHealth(player);
/* 1830:1640 */           ItemLoreStats.this.updatePlayerSpeed((Player)event.getPlayer());
/* 1831:1641 */           ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 1832:1642 */           ItemLoreStats.this.removeWeaponSpeedEffects(player);
/* 1833:     */         }
/* 1834:     */       }
/* 1835:     */     }
/* 1836:     */     
/* 1837:     */     public Material getRepairBlock()
/* 1838:     */     {
/* 1839:1650 */       if (ItemLoreStats.this.getConfig().getString("durabilityAddedOnEachRepair.repairBlock") != null)
/* 1840:     */       {
/* 1841:1651 */         Material repairBlock = Material.getMaterial(ItemLoreStats.this.getConfig().getString("durabilityAddedOnEachRepair.repairBlock"));
/* 1842:1652 */         return repairBlock;
/* 1843:     */       }
/* 1844:1654 */       return Material.WORKBENCH;
/* 1845:     */     }
/* 1846:     */     
/* 1847:     */     @EventHandler
/* 1848:     */     public void repairItemOnLeftClick(PlayerInteractEvent event)
/* 1849:     */     {
/* 1850:1659 */       if (((event.getAction() == Action.LEFT_CLICK_BLOCK) && (ItemLoreStats.this.isTool(event.getPlayer().getItemInHand().getType()))) || ((event.getAction() == Action.LEFT_CLICK_BLOCK) && (ItemLoreStats.this.isArmour(event.getPlayer().getItemInHand().getType())) && 
/* 1851:1660 */         (event.getClickedBlock().getType().equals(getRepairBlock())) && 
/* 1852:1661 */         (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))))
/* 1853:     */       {
/* 1854:1662 */         if ((ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) || (event.getClickedBlock() == null)) {
/* 1855:1662 */           return;
/* 1856:     */         }
/* 1857:1664 */         Player player = event.getPlayer();
/* 1858:1666 */         if (player.getItemInHand().getType().toString().contains("_SWORD")) {
/* 1859:1667 */           ItemLoreStats.this.repairItems.repairSword(player);
/* 1860:1668 */         } else if (player.getItemInHand().getType().toString().contains("_PICKAXE")) {
/* 1861:1669 */           ItemLoreStats.this.repairItems.repairPickaxe(player);
/* 1862:1670 */         } else if (player.getItemInHand().getType().toString().contains("_AXE")) {
/* 1863:1671 */           ItemLoreStats.this.repairItems.repairAxe(player);
/* 1864:1672 */         } else if (player.getItemInHand().getType().toString().contains("_HOE")) {
/* 1865:1673 */           ItemLoreStats.this.repairItems.repairHoe(player);
/* 1866:1674 */         } else if (player.getItemInHand().getType().toString().contains("BOW")) {
/* 1867:1675 */           ItemLoreStats.this.repairItems.repairBow(player);
/* 1868:1676 */         } else if (player.getItemInHand().getType().toString().contains("SHEARS")) {
/* 1869:1677 */           ItemLoreStats.this.repairItems.repairShears(player);
/* 1870:1678 */         } else if (player.getItemInHand().getType().toString().contains("STICK")) {
/* 1871:1679 */           ItemLoreStats.this.repairItems.repairStick(player);
/* 1872:1680 */         } else if (player.getItemInHand().getType().toString().contains("FLINT_AND_STEEL")) {
/* 1873:1681 */           ItemLoreStats.this.repairItems.repairFlintAndSteel(player);
/* 1874:1682 */         } else if (player.getItemInHand().getType().toString().contains("_SPADE")) {
/* 1875:1683 */           ItemLoreStats.this.repairItems.repairSpade(player);
/* 1876:1684 */         } else if (player.getItemInHand().getType().toString().contains("_HELMET")) {
/* 1877:1685 */           ItemLoreStats.this.repairItems.repairHelmet(player);
/* 1878:1686 */         } else if (player.getItemInHand().getType().toString().contains("_CHESTPLATE")) {
/* 1879:1687 */           ItemLoreStats.this.repairItems.repairChestplate(player);
/* 1880:1688 */         } else if (player.getItemInHand().getType().toString().contains("_LEGGINGS")) {
/* 1881:1689 */           ItemLoreStats.this.repairItems.repairLeggings(player);
/* 1882:1690 */         } else if (player.getItemInHand().getType().toString().contains("_BOOTS")) {
/* 1883:1691 */           ItemLoreStats.this.repairItems.repairBoots(player);
/* 1884:1692 */         } else if (player.getItemInHand().getType().toString().contains("FISHING_ROD")) {
/* 1885:1693 */           ItemLoreStats.this.repairItems.repairFishingRod(player);
/* 1886:1694 */         } else if (player.getItemInHand().getType().toString().contains("CARROT_STICK")) {
/* 1887:1695 */           ItemLoreStats.this.repairItems.repairCarrotStick(player);
/* 1888:     */         }
/* 1889:     */       }
/* 1890:     */     }
/* 1891:     */     
/* 1892:     */     public boolean hasCooldown(String playerName, int getSeconds, String spellType)
/* 1893:     */     {
/* 1894:1704 */       if (ItemLoreStats.this.cooldowns.get(playerName) != null)
/* 1895:     */       {
/* 1896:1705 */         if (((Long)ItemLoreStats.this.cooldowns.get(playerName)).longValue() < System.currentTimeMillis() - getSeconds * 1000) {
/* 1897:1706 */           return false;
/* 1898:     */         }
/* 1899:1708 */         long currentTime = System.currentTimeMillis();
/* 1900:1709 */         long oldTime = ((Long)ItemLoreStats.this.cooldowns.get(playerName)).longValue();
/* 1901:     */         
/* 1902:1711 */         String remainingCooldown = String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000)));
/* 1903:1712 */         String modifiedPlayerName = playerName.substring(0, playerName.length() - 4);
/* 1904:1714 */         if (remainingCooldown.length() > 7) {
/* 1905:1715 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 5) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(5, 7) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1906:1716 */         } else if (remainingCooldown.length() > 6) {
/* 1907:1717 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 4) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(4, 6) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1908:1718 */         } else if (remainingCooldown.length() > 5) {
/* 1909:1719 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 3) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(3, 5) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1910:1720 */         } else if (remainingCooldown.length() > 4) {
/* 1911:1721 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 2) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(2, 4) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1912:1722 */         } else if (remainingCooldown.length() > 3) {
/* 1913:1723 */           Bukkit.getServer().getPlayer(modifiedPlayerName).sendMessage("      " + ChatColor.GOLD + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(0, 1) + "." + String.valueOf(Math.abs(currentTime - (oldTime + getSeconds * 1000))).substring(1, 3) + " " + ItemLoreStats.this.getResponse("SpellMessages.CastSpell.CooldownRemaining"));
/* 1914:     */         }
/* 1915:1729 */         return true;
/* 1916:     */       }
/* 1917:1732 */       return false;
/* 1918:     */     }
/* 1919:     */     
/* 1920:     */     @EventHandler
/* 1921:     */     public void onSpellUse(PlayerInteractEvent event)
/* 1922:     */     {
/* 1923:1737 */       Player player = event.getPlayer();
/* 1924:1738 */       if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 1925:     */       {
/* 1926:1739 */         if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1927:     */         {
/* 1928:1740 */           if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 1929:1740 */             return;
/* 1930:     */           }
/* 1931:1741 */           if (ItemLoreStats.plugin.util_WorldGuard != null)
/* 1932:     */           {
/* 1933:1742 */             if (!ItemLoreStats.plugin.util_WorldGuard.playerInPVPRegion(player))
/* 1934:     */             {
/* 1935:1743 */               if ((ItemLoreStats.this.gearStats.getTnT(player)) && 
/* 1936:1744 */                 (!hasCooldown(player.getName() + ".tnt", ItemLoreStats.this.getConfig().getInt("statNames.spells.tnt.cooldown"), "tnt")))
/* 1937:     */               {
/* 1938:1745 */                 ItemLoreStats.this.spells.castTnT(player);
/* 1939:1746 */                 ItemLoreStats.this.cooldowns.put(player.getName() + ".tnt", Long.valueOf(System.currentTimeMillis()));
/* 1940:     */               }
/* 1941:1749 */               if ((ItemLoreStats.this.gearStats.getFireball(player)) && 
/* 1942:1750 */                 (!hasCooldown(player.getName() + ".bal", ItemLoreStats.this.getConfig().getInt("statNames.spells.fireball.cooldown"), "fireball")))
/* 1943:     */               {
/* 1944:1751 */                 ItemLoreStats.this.spells.castFireball(player);
/* 1945:1752 */                 ItemLoreStats.this.cooldowns.put(player.getName() + ".bal", Long.valueOf(System.currentTimeMillis()));
/* 1946:     */               }
/* 1947:1755 */               if ((ItemLoreStats.this.gearStats.getLightning(player)) && 
/* 1948:1756 */                 (!hasCooldown(player.getName() + ".lig", ItemLoreStats.this.getConfig().getInt("statNames.spells.lightning.cooldown"), "lightning")))
/* 1949:     */               {
/* 1950:1757 */                 ItemLoreStats.this.spells.castLightning(player);
/* 1951:1758 */                 ItemLoreStats.this.cooldowns.put(player.getName() + ".lig", Long.valueOf(System.currentTimeMillis()));
/* 1952:     */               }
/* 1953:1761 */               if ((ItemLoreStats.this.gearStats.getFrostbolt(player)) && 
/* 1954:1762 */                 (!hasCooldown(player.getName() + ".fbt", ItemLoreStats.this.getConfig().getInt("statNames.spells.frostbolt.cooldown"), "frostbolt")))
/* 1955:     */               {
/* 1956:1763 */                 ItemLoreStats.this.spells.castFrostbolt(player);
/* 1957:1764 */                 ItemLoreStats.this.cooldowns.put(player.getName() + ".fbt", Long.valueOf(System.currentTimeMillis()));
/* 1958:     */               }
/* 1959:     */             }
/* 1960:     */           }
/* 1961:     */           else
/* 1962:     */           {
/* 1963:1769 */             if ((ItemLoreStats.this.gearStats.getTnT(player)) && 
/* 1964:1770 */               (!hasCooldown(player.getName() + ".tnt", ItemLoreStats.this.getConfig().getInt("statNames.spells.tnt.cooldown"), "tnt")))
/* 1965:     */             {
/* 1966:1771 */               ItemLoreStats.this.spells.castTnT(player);
/* 1967:1772 */               ItemLoreStats.this.cooldowns.put(player.getName() + ".tnt", Long.valueOf(System.currentTimeMillis()));
/* 1968:     */             }
/* 1969:1775 */             if ((ItemLoreStats.this.gearStats.getFireball(player)) && 
/* 1970:1776 */               (!hasCooldown(player.getName() + ".bal", ItemLoreStats.this.getConfig().getInt("statNames.spells.fireball.cooldown"), "fireball")))
/* 1971:     */             {
/* 1972:1777 */               ItemLoreStats.this.spells.castFireball(player);
/* 1973:1778 */               ItemLoreStats.this.cooldowns.put(player.getName() + ".bal", Long.valueOf(System.currentTimeMillis()));
/* 1974:     */             }
/* 1975:1781 */             if ((ItemLoreStats.this.gearStats.getLightning(player)) && 
/* 1976:1782 */               (!hasCooldown(player.getName() + ".lig", ItemLoreStats.this.getConfig().getInt("statNames.spells.lightning.cooldown"), "lightning")))
/* 1977:     */             {
/* 1978:1783 */               ItemLoreStats.this.spells.castLightning(player);
/* 1979:1784 */               ItemLoreStats.this.cooldowns.put(player.getName() + ".lig", Long.valueOf(System.currentTimeMillis()));
/* 1980:     */             }
/* 1981:1787 */             if ((ItemLoreStats.this.gearStats.getFrostbolt(player)) && 
/* 1982:1788 */               (!hasCooldown(player.getName() + ".fbt", ItemLoreStats.this.getConfig().getInt("statNames.spells.frostbolt.cooldown"), "frostbolt")))
/* 1983:     */             {
/* 1984:1789 */               ItemLoreStats.this.spells.castFrostbolt(player);
/* 1985:1790 */               ItemLoreStats.this.cooldowns.put(player.getName() + ".fbt", Long.valueOf(System.currentTimeMillis()));
/* 1986:     */             }
/* 1987:     */           }
/* 1988:     */         }
/* 1989:     */       }
/* 1990:1796 */       else if (((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && (ItemLoreStats.this.isArmour(event.getPlayer().getItemInHand().getType())))
/* 1991:     */       {
/* 1992:1797 */         if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
/* 1993:1797 */           event.setCancelled(true);
/* 1994:     */         }
/* 1995:1798 */         if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 1996:     */         {
/* 1997:1799 */           ItemLoreStats.this.xpLevel.checkXpLevelHead(player);
/* 1998:1800 */           ItemLoreStats.this.xpLevel.checkXpLevelChest(player);
/* 1999:1801 */           ItemLoreStats.this.xpLevel.checkXpLevelLegs(player);
/* 2000:1802 */           ItemLoreStats.this.xpLevel.checkXpLevelBoots(player);
/* 2001:1803 */           ItemLoreStats.this.xpLevel.checkXpLevelItemInHand(player);
/* 2002:     */           
/* 2003:1805 */           ItemLoreStats.this.soulbound.checkSoulboundHead(player);
/* 2004:1806 */           ItemLoreStats.this.soulbound.checkSoulboundChest(player);
/* 2005:1807 */           ItemLoreStats.this.soulbound.checkSoulboundLegs(player);
/* 2006:1808 */           ItemLoreStats.this.soulbound.checkSoulboundBoots(player);
/* 2007:1809 */           ItemLoreStats.this.soulbound.checkSoulboundItemInHand(player);
/* 2008:     */           
/* 2009:1811 */           ItemLoreStats.this.classes.checkClassHead(player);
/* 2010:1812 */           ItemLoreStats.this.classes.checkClassChest(player);
/* 2011:1813 */           ItemLoreStats.this.classes.checkClassLegs(player);
/* 2012:1814 */           ItemLoreStats.this.classes.checkClassBoots(player);
/* 2013:1815 */           ItemLoreStats.this.classes.checkClassItemInHand(player);
/* 2014:     */           
/* 2015:1817 */           ItemLoreStats.this.updateHealth(player);
/* 2016:1818 */           ItemLoreStats.this.updatePlayerSpeed(player);
/* 2017:1819 */           ItemLoreStats.this.setBonuses.updateSetBonus(player);
/* 2018:     */           
/* 2019:1821 */           ItemLoreStats.this.debugMessage(player, "Inventory updated.");
/* 2020:     */         }
/* 2021:     */       }
/* 2022:     */     }
/* 2023:     */     
/* 2024:     */     @EventHandler
/* 2025:     */     public void onPlayerChangeWorld(PlayerChangedWorldEvent event)
/* 2026:     */     {
/* 2027:1829 */       if ((event.getPlayer() instanceof Player))
/* 2028:     */       {
/* 2029:1831 */         Player player = event.getPlayer();
/* 2030:     */         
/* 2031:1833 */         final Player playerFinal = event.getPlayer();
/* 2032:     */         
/* 2033:1835 */         ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 2034:     */         {
/* 2035:     */           public void run()
/* 2036:     */           {
/* 2037:1837 */             ItemLoreStats.this.updateHealth(playerFinal);
/* 2038:1838 */             ItemLoreStats.this.updatePlayerSpeed(playerFinal);
/* 2039:1839 */             ItemLoreStats.this.setBonuses.updateSetBonus(playerFinal);
/* 2040:     */           }
/* 2041:1841 */         }, 2L);
/* 2042:1843 */         if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(player.getWorld().getName()))
/* 2043:     */         {
/* 2044:1844 */           String weaponSpeed = "0";
/* 2045:1846 */           if ((player.getItemInHand() != null) && 
/* 2046:1847 */             (player.getItemInHand().getType() != Material.AIR) && 
/* 2047:1848 */             (player.getItemInHand().getItemMeta() != null) && 
/* 2048:1849 */             (player.getItemInHand().getItemMeta().getLore() != null) && (
/* 2049:1850 */             (ItemLoreStats.this.isTool(player.getItemInHand().getType())) || (ItemLoreStats.this.isArmour(player.getItemInHand().getType()))))
/* 2050:     */           {
/* 2051:1852 */             String strippedWeaponSpeed = ItemLoreStats.this.getConfig().getString("statNames.weaponspeed").replaceAll("&([0-9a-f])", "");
/* 2052:     */             
/* 2053:1854 */             String[] splitLore = player.getItemInHand().getItemMeta().getLore().toString().split(", ");
/* 2054:1856 */             for (String getStat : splitLore) {
/* 2055:1858 */               if (ChatColor.stripColor(getStat).startsWith(strippedWeaponSpeed))
/* 2056:     */               {
/* 2057:1859 */                 if (weaponSpeed.length() < 2)
/* 2058:     */                 {
/* 2059:1860 */                   weaponSpeed = ChatColor.stripColor(getStat).replace("[", "").trim().substring((strippedWeaponSpeed + ": ").length()).replace("]", "").trim().toLowerCase();
/* 2060:1861 */                   if (weaponSpeed.toString().equalsIgnoreCase("fast"))
/* 2061:     */                   {
/* 2062:1862 */                     if (event.getPlayer().hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
/* 2063:1863 */                       event.getPlayer().removePotionEffect(PotionEffectType.SLOW_DIGGING);
/* 2064:     */                     }
/* 2065:1865 */                     event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1));
/* 2066:     */                   }
/* 2067:1866 */                   else if (weaponSpeed.toString().equalsIgnoreCase("slow"))
/* 2068:     */                   {
/* 2069:1867 */                     if (event.getPlayer().hasPotionEffect(PotionEffectType.FAST_DIGGING)) {
/* 2070:1868 */                       event.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
/* 2071:     */                     }
/* 2072:1870 */                     event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 99999, 1));
/* 2073:     */                   }
/* 2074:1871 */                   else if (weaponSpeed.toString().equalsIgnoreCase("normal"))
/* 2075:     */                   {
/* 2076:1872 */                     ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 2077:     */                   }
/* 2078:     */                 }
/* 2079:     */               }
/* 2080:1876 */               else if (weaponSpeed.length() < 2) {
/* 2081:1877 */                 ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 2082:     */               }
/* 2083:     */             }
/* 2084:     */           }
/* 2085:     */         }
/* 2086:     */         else
/* 2087:     */         {
/* 2088:1887 */           ItemLoreStats.this.removeWeaponSpeedEffects(event.getPlayer());
/* 2089:     */         }
/* 2090:     */       }
/* 2091:     */     }
/* 2092:     */     
/* 2093:     */     @EventHandler(ignoreCancelled=true)
/* 2094:     */     public void disableItemBreak(PlayerItemBreakEvent event)
/* 2095:     */     {
/* 2096:1895 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2097:     */       {
/* 2098:1896 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2099:1896 */           return;
/* 2100:     */         }
/* 2101:1897 */         final Player player = event.getPlayer();
/* 2102:1898 */         final ItemStack item = event.getBrokenItem();
/* 2103:1899 */         item.setDurability((short)0);
/* 2104:     */         try
/* 2105:     */         {
/* 2106:1902 */           if ((event.getBrokenItem() != null) && 
/* 2107:1903 */             (event.getBrokenItem().getItemMeta().hasLore())) {
/* 2108:1904 */             if (ItemLoreStats.this.isTool(event.getBrokenItem().getType())) {
/* 2109:1905 */               event.getBrokenItem().setDurability((short)1);
/* 2110:1906 */             } else if (ItemLoreStats.this.isArmour(event.getBrokenItem().getType())) {
/* 2111:1907 */               if (ItemLoreStats.this.isHelmet(event.getBrokenItem().getType())) {
/* 2112:1908 */                 ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 2113:     */                 {
/* 2114:     */                   public void run()
/* 2115:     */                   {
/* 2116:1910 */                     player.getInventory().setHelmet(item);
/* 2117:     */                   }
/* 2118:1912 */                 }, 1L);
/* 2119:1913 */               } else if (ItemLoreStats.this.isChestplate(event.getBrokenItem().getType())) {
/* 2120:1914 */                 ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 2121:     */                 {
/* 2122:     */                   public void run()
/* 2123:     */                   {
/* 2124:1916 */                     player.getInventory().setChestplate(item);
/* 2125:     */                   }
/* 2126:1918 */                 }, 1L);
/* 2127:1919 */               } else if (ItemLoreStats.this.isLeggings(event.getBrokenItem().getType())) {
/* 2128:1920 */                 ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 2129:     */                 {
/* 2130:     */                   public void run()
/* 2131:     */                   {
/* 2132:1922 */                     player.getInventory().setLeggings(item);
/* 2133:     */                   }
/* 2134:1924 */                 }, 1L);
/* 2135:1925 */               } else if (ItemLoreStats.this.isBoots(event.getBrokenItem().getType())) {
/* 2136:1926 */                 ItemLoreStats.this.getServer().getScheduler().scheduleSyncDelayedTask(ItemLoreStats.plugin, new Runnable()
/* 2137:     */                 {
/* 2138:     */                   public void run()
/* 2139:     */                   {
/* 2140:1928 */                     player.getInventory().setBoots(item);
/* 2141:     */                   }
/* 2142:1930 */                 }, 1L);
/* 2143:     */               }
/* 2144:     */             }
/* 2145:     */           }
/* 2146:     */         }
/* 2147:     */         catch (Exception ex)
/* 2148:     */         {
/* 2149:1936 */           System.out.println(ex);
/* 2150:     */         }
/* 2151:     */       }
/* 2152:     */     }
/* 2153:     */     
/* 2154:     */     @EventHandler
/* 2155:     */     public void addDurabilityToCraftedItem(PrepareItemCraftEvent event)
/* 2156:     */     {
/* 2157:1944 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getView().getPlayer().getWorld().getName()))
/* 2158:     */       {
/* 2159:1945 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2160:1945 */           return;
/* 2161:     */         }
/* 2162:1946 */         if (event.isRepair())
/* 2163:     */         {
/* 2164:1947 */           event.getInventory().setResult(null);
/* 2165:     */         }
/* 2166:1949 */         else if (((ItemLoreStats.this.isTool(event.getRecipe().getResult().getType())) || (ItemLoreStats.this.isArmour(event.getRecipe().getResult().getType()))) && 
/* 2167:1950 */           (ItemLoreStats.this.getConfig().getBoolean("defaultCraftedDurability.enableDurabilityOnCrafted")))
/* 2168:     */         {
/* 2169:1951 */           ItemStack craftedItem = event.getInventory().getResult();
/* 2170:1952 */           ItemMeta craftedItemMeta = craftedItem.getItemMeta();
/* 2171:     */           
/* 2172:1954 */           List<String> setItemLore = new ArrayList();
/* 2173:     */           
/* 2174:1956 */           String durability = ItemLoreStats.plugin.getConfig().getString("statNames.durability");
/* 2175:1957 */           String durabilityNoColour = durability.replaceAll("&([0-9a-f])", "");
/* 2176:     */           
/* 2177:1959 */           String type = "";
/* 2178:1960 */           String selectedDurability = "";
/* 2179:1962 */           if (craftedItem.getType().toString().contains("WOOD")) {
/* 2180:1963 */             type = "wood";
/* 2181:1964 */           } else if (craftedItem.getType().toString().contains("LEATHER")) {
/* 2182:1965 */             type = "leather";
/* 2183:1966 */           } else if (craftedItem.getType().toString().contains("STONE")) {
/* 2184:1967 */             type = "stone";
/* 2185:1968 */           } else if (craftedItem.getType().toString().contains("CHAINMAIL")) {
/* 2186:1969 */             type = "chainmail";
/* 2187:1970 */           } else if (craftedItem.getType().toString().contains("IRON")) {
/* 2188:1971 */             type = "iron";
/* 2189:1972 */           } else if (craftedItem.getType().toString().contains("GOLD")) {
/* 2190:1973 */             type = "gold";
/* 2191:1974 */           } else if (craftedItem.getType().toString().contains("DIAMOND")) {
/* 2192:1975 */             type = "diamond";
/* 2193:1976 */           } else if (craftedItem.getType().toString().contains("BOW")) {
/* 2194:1977 */             type = "bow";
/* 2195:1978 */           } else if (craftedItem.getType().toString().contains("SHEARS")) {
/* 2196:1979 */             type = "shears";
/* 2197:1980 */           } else if (craftedItem.getType().toString().contains("STICK")) {
/* 2198:1981 */             type = "stick";
/* 2199:1982 */           } else if (craftedItem.getType().toString().contains("FLINT_AND_STEEL")) {
/* 2200:1983 */             type = "flintAndSteel";
/* 2201:1984 */           } else if (craftedItem.getType().toString().contains("FISHING_ROD")) {
/* 2202:1985 */             type = "fishingRod";
/* 2203:1986 */           } else if (craftedItem.getType().toString().contains("CARROT_STICK")) {
/* 2204:1987 */             type = "carrotStick";
/* 2205:     */           }
/* 2206:1990 */           if (ItemLoreStats.this.isTool(craftedItem.getType()))
/* 2207:     */           {
/* 2208:1991 */             if (!ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).equals("0")) {
/* 2209:1992 */               if (ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).contains("-"))
/* 2210:     */               {
/* 2211:1993 */                 selectedDurability = String.valueOf(ItemLoreStats.this.randomRange(Integer.parseInt(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 2212:1994 */                 setItemLore.add(ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability);
/* 2213:     */               }
/* 2214:     */               else
/* 2215:     */               {
/* 2216:1996 */                 selectedDurability = String.valueOf(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.tools." + type).replaceAll("&([0-9a-f])", "").trim());
/* 2217:1997 */                 setItemLore.add(ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.tools.").append(type).toString()))) + selectedDurability);
/* 2218:     */               }
/* 2219:     */             }
/* 2220:     */           }
/* 2221:2000 */           else if ((ItemLoreStats.this.isArmour(craftedItem.getType())) && 
/* 2222:2001 */             (!ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).equals("0"))) {
/* 2223:2002 */             if (ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).contains("-"))
/* 2224:     */             {
/* 2225:2003 */               selectedDurability = String.valueOf(ItemLoreStats.this.randomRange(Integer.parseInt(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).split("-")[1].replaceAll("&([0-9a-f])", "").trim()), Integer.parseInt(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).split("-")[0].replaceAll("&([0-9a-f])", "").trim())));
/* 2226:2004 */               setItemLore.add(ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability);
/* 2227:     */             }
/* 2228:     */             else
/* 2229:     */             {
/* 2230:2006 */               selectedDurability = String.valueOf(ItemLoreStats.this.getConfig().getString("defaultCraftedDurability.armour." + type).replaceAll("&([0-9a-f])", "").trim());
/* 2231:2007 */               setItemLore.add(ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(durability)) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractTooltipColour(ItemLoreStats.this.getConfig().getString(new StringBuilder("defaultCraftedDurability.armour.").append(type).toString()))) + selectedDurability);
/* 2232:     */             }
/* 2233:     */           }
/* 2234:2012 */           craftedItemMeta.setDisplayName(ChatColor.RESET + event.getInventory().getResult().getType().toString().replaceAll("WOOD_", "Wooden ").replaceAll("LEATHER_", "Leather ").replaceAll("STONE_", "Stone ").replaceAll("IRON_", "Iron ").replaceAll("GOLD_", "Golden ").replaceAll("DIAMOND_", "Diamond ").replaceAll("FISHING_ROD", "Fishing Rod").replaceAll("BOW", "Bow").replaceAll("SHEARS", "Shears").replaceAll("STICK", "Stick").replaceAll("FLINT_AND_STEEL", "Flint and Steel").replaceAll("CARROT_STICK", "Carrot on a Stick").replaceAll("SWORD", "Sword").replaceAll("HOE", "Hoe").replaceAll("SPADE", "Spade").replaceAll("PICKAXE", "Pickaxe").replace("AXE", "Axe").replaceAll("HELMET", "Helmet").replace("CHESTPLATE", "Chestplate").replaceAll("LEGGINGS", "Leggings").replace("BOOTS", "Boots"));
/* 2235:     */           
/* 2236:2014 */           craftedItemMeta.setLore(setItemLore);
/* 2237:2015 */           craftedItem.setItemMeta(craftedItemMeta);
/* 2238:     */           
/* 2239:2017 */           event.getInventory().setResult(craftedItem);
/* 2240:     */         }
/* 2241:     */       }
/* 2242:     */     }
/* 2243:     */     
/* 2244:     */     @EventHandler
/* 2245:     */     public void blockBreakDurability(BlockBreakEvent event)
/* 2246:     */     {
/* 2247:2026 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2248:     */       {
/* 2249:2027 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2250:2027 */           return;
/* 2251:     */         }
/* 2252:2028 */         Player player = event.getPlayer();
/* 2253:2030 */         if ((player.getItemInHand() != null) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 2254:     */         {
/* 2255:2031 */           player.getItemInHand().setDurability((short)-1);
/* 2256:2032 */           if (player.getItemInHand().getItemMeta().hasLore())
/* 2257:     */           {
/* 2258:2034 */             String durabilityNoColour = ItemLoreStats.this.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 2259:     */             
/* 2260:2036 */             List<String> getItemLore = player.getItemInHand().getItemMeta().getLore();
/* 2261:2038 */             for (String getDurability : getItemLore) {
/* 2262:2039 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/* 2263:     */               {
/* 2264:2040 */                 String durabilityRebuilder = "";
/* 2265:2041 */                 String durabilityAmountColour = "";
/* 2266:2042 */                 String prefixColourOnly = "";
/* 2267:     */                 
/* 2268:2044 */                 int index = getItemLore.indexOf(getDurability);
/* 2269:2045 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/* 2270:2046 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/* 2271:2048 */                 if (currentValueMinusOne + 1 < 2)
/* 2272:     */                 {
/* 2273:2049 */                   player.playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 2274:2050 */                   player.getInventory().remove(player.getItemInHand());
/* 2275:2051 */                   return;
/* 2276:     */                 }
/* 2277:2054 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/* 2278:     */                 {
/* 2279:2055 */                   if (getDurability.length() > 4)
/* 2280:     */                   {
/* 2281:2056 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/* 2282:2057 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/* 2283:     */                     } else {
/* 2284:2059 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2285:     */                     }
/* 2286:     */                   }
/* 2287:     */                   else {
/* 2288:2062 */                     prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2289:     */                   }
/* 2290:     */                 }
/* 2291:     */                 else {
/* 2292:2065 */                   prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour("&5&o");
/* 2293:     */                 }
/* 2294:2068 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/* 2295:     */                 {
/* 2296:2069 */                   if (getDurability.split("/")[1].trim().length() > 4)
/* 2297:     */                   {
/* 2298:2070 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/* 2299:2071 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/* 2300:     */                     } else {
/* 2301:2073 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2302:     */                     }
/* 2303:     */                   }
/* 2304:     */                   else {
/* 2305:2076 */                     durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2306:     */                   }
/* 2307:     */                 }
/* 2308:     */                 else {
/* 2309:2079 */                   durabilityAmountColour = prefixColourOnly;
/* 2310:     */                 }
/* 2311:2082 */                 currentValueMinusOne += enchantmentDurabilityLoss(player.getItemInHand());
/* 2312:     */                 
/* 2313:2084 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/* 2314:2086 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/* 2315:     */                 {
/* 2316:2087 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/* 2317:2088 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2318:     */                   }
/* 2319:2090 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2320:     */                 }
/* 2321:2091 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/* 2322:     */                 {
/* 2323:2092 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/* 2324:2093 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2325:     */                   }
/* 2326:2095 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2327:     */                 }
/* 2328:2096 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/* 2329:     */                 {
/* 2330:2097 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/* 2331:2098 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2332:     */                   }
/* 2333:2100 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2334:     */                 }
/* 2335:     */                 else
/* 2336:     */                 {
/* 2337:2102 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2338:     */                 }
/* 2339:2105 */                 getItemLore.set(index, durabilityRebuilder);
/* 2340:     */               }
/* 2341:     */             }
/* 2342:2109 */             ItemStack setItemInHand = new ItemStack(player.getItemInHand());
/* 2343:2110 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/* 2344:     */             
/* 2345:2112 */             setItemInHandMeta.setLore(getItemLore);
/* 2346:2113 */             setItemInHand.setItemMeta(setItemInHandMeta);
/* 2347:     */             
/* 2348:2115 */             player.getInventory().remove(player.getItemInHand());
/* 2349:2116 */             player.setItemInHand(new ItemStack(setItemInHand));
/* 2350:     */           }
/* 2351:     */         }
/* 2352:     */       }
/* 2353:     */     }
/* 2354:     */     
/* 2355:     */     @EventHandler
/* 2356:     */     public void fishingDurability(PlayerFishEvent event)
/* 2357:     */     {
/* 2358:2124 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2359:     */       {
/* 2360:2125 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2361:2125 */           return;
/* 2362:     */         }
/* 2363:2126 */         Player player = event.getPlayer();
/* 2364:2128 */         if ((player.getItemInHand() != null) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 2365:     */         {
/* 2366:2129 */           player.getItemInHand().setDurability((short)-1);
/* 2367:2130 */           if (player.getItemInHand().getItemMeta().hasLore())
/* 2368:     */           {
/* 2369:2132 */             String durabilityNoColour = ItemLoreStats.this.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 2370:     */             
/* 2371:2134 */             List<String> getItemLore = player.getItemInHand().getItemMeta().getLore();
/* 2372:2136 */             for (String getDurability : getItemLore) {
/* 2373:2137 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/* 2374:     */               {
/* 2375:2138 */                 String durabilityRebuilder = "";
/* 2376:2139 */                 String durabilityAmountColour = "";
/* 2377:2140 */                 String prefixColourOnly = "";
/* 2378:     */                 
/* 2379:2142 */                 int index = getItemLore.indexOf(getDurability);
/* 2380:2143 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/* 2381:2144 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/* 2382:2146 */                 if (currentValueMinusOne + 1 < 2)
/* 2383:     */                 {
/* 2384:2147 */                   player.playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 2385:2148 */                   player.getInventory().remove(player.getItemInHand());
/* 2386:2149 */                   return;
/* 2387:     */                 }
/* 2388:2152 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/* 2389:     */                 {
/* 2390:2153 */                   if (getDurability.length() > 4)
/* 2391:     */                   {
/* 2392:2154 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/* 2393:2155 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/* 2394:     */                     } else {
/* 2395:2157 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2396:     */                     }
/* 2397:     */                   }
/* 2398:     */                   else {
/* 2399:2160 */                     prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2400:     */                   }
/* 2401:     */                 }
/* 2402:     */                 else {
/* 2403:2163 */                   prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour("&5&o");
/* 2404:     */                 }
/* 2405:2166 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/* 2406:     */                 {
/* 2407:2167 */                   if (getDurability.split("/")[1].trim().length() > 4)
/* 2408:     */                   {
/* 2409:2168 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/* 2410:2169 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/* 2411:     */                     } else {
/* 2412:2171 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2413:     */                     }
/* 2414:     */                   }
/* 2415:     */                   else {
/* 2416:2174 */                     durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2417:     */                   }
/* 2418:     */                 }
/* 2419:     */                 else {
/* 2420:2177 */                   durabilityAmountColour = prefixColourOnly;
/* 2421:     */                 }
/* 2422:2180 */                 currentValueMinusOne += enchantmentDurabilityLoss(player.getItemInHand());
/* 2423:     */                 
/* 2424:2182 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/* 2425:2184 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/* 2426:     */                 {
/* 2427:2185 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/* 2428:2186 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2429:     */                   }
/* 2430:2188 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2431:     */                 }
/* 2432:2189 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/* 2433:     */                 {
/* 2434:2190 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/* 2435:2191 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2436:     */                   }
/* 2437:2193 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2438:     */                 }
/* 2439:2194 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/* 2440:     */                 {
/* 2441:2195 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/* 2442:2196 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2443:     */                   }
/* 2444:2198 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2445:     */                 }
/* 2446:     */                 else
/* 2447:     */                 {
/* 2448:2200 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2449:     */                 }
/* 2450:2203 */                 getItemLore.set(index, durabilityRebuilder);
/* 2451:     */               }
/* 2452:     */             }
/* 2453:2207 */             ItemStack setItemInHand = new ItemStack(player.getItemInHand());
/* 2454:2208 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/* 2455:     */             
/* 2456:2210 */             setItemInHandMeta.setLore(getItemLore);
/* 2457:2211 */             setItemInHand.setItemMeta(setItemInHandMeta);
/* 2458:     */             
/* 2459:2213 */             player.getInventory().remove(player.getItemInHand());
/* 2460:2214 */             player.setItemInHand(new ItemStack(setItemInHand));
/* 2461:     */           }
/* 2462:     */         }
/* 2463:     */       }
/* 2464:     */     }
/* 2465:     */     
/* 2466:     */     @EventHandler
/* 2467:     */     public void carrotStickDurability(PlayerInteractEvent event)
/* 2468:     */     {
/* 2469:2222 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2470:     */       {
/* 2471:2223 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2472:2223 */           return;
/* 2473:     */         }
/* 2474:2224 */         Player player = event.getPlayer();
/* 2475:2226 */         if ((player.getItemInHand().getType().equals(Material.CARROT_STICK)) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && 
/* 2476:2227 */           (player.getItemInHand() != null) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 2477:     */         {
/* 2478:2228 */           player.getItemInHand().setDurability((short)-1);
/* 2479:2229 */           if (player.getItemInHand().getItemMeta().hasLore())
/* 2480:     */           {
/* 2481:2231 */             String durabilityNoColour = ItemLoreStats.this.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 2482:     */             
/* 2483:2233 */             List<String> getItemLore = player.getItemInHand().getItemMeta().getLore();
/* 2484:2235 */             for (String getDurability : getItemLore) {
/* 2485:2236 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/* 2486:     */               {
/* 2487:2237 */                 String durabilityRebuilder = "";
/* 2488:2238 */                 String durabilityAmountColour = "";
/* 2489:2239 */                 String prefixColourOnly = "";
/* 2490:     */                 
/* 2491:2241 */                 int index = getItemLore.indexOf(getDurability);
/* 2492:2242 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/* 2493:2243 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/* 2494:2245 */                 if (currentValueMinusOne + 1 < 2)
/* 2495:     */                 {
/* 2496:2246 */                   player.playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 2497:2247 */                   player.getInventory().remove(player.getItemInHand());
/* 2498:2248 */                   return;
/* 2499:     */                 }
/* 2500:2251 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/* 2501:     */                 {
/* 2502:2252 */                   if (getDurability.length() > 4)
/* 2503:     */                   {
/* 2504:2253 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/* 2505:2254 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/* 2506:     */                     } else {
/* 2507:2256 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2508:     */                     }
/* 2509:     */                   }
/* 2510:     */                   else {
/* 2511:2259 */                     prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2512:     */                   }
/* 2513:     */                 }
/* 2514:     */                 else {
/* 2515:2262 */                   prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour("&5&o");
/* 2516:     */                 }
/* 2517:2265 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/* 2518:     */                 {
/* 2519:2266 */                   if (getDurability.split("/")[1].trim().length() > 4)
/* 2520:     */                   {
/* 2521:2267 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/* 2522:2268 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/* 2523:     */                     } else {
/* 2524:2270 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2525:     */                     }
/* 2526:     */                   }
/* 2527:     */                   else {
/* 2528:2273 */                     durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2529:     */                   }
/* 2530:     */                 }
/* 2531:     */                 else {
/* 2532:2276 */                   durabilityAmountColour = prefixColourOnly;
/* 2533:     */                 }
/* 2534:2279 */                 currentValueMinusOne += enchantmentDurabilityLoss(player.getItemInHand());
/* 2535:     */                 
/* 2536:2281 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/* 2537:2283 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/* 2538:     */                 {
/* 2539:2284 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/* 2540:2285 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2541:     */                   }
/* 2542:2287 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2543:     */                 }
/* 2544:2288 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/* 2545:     */                 {
/* 2546:2289 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/* 2547:2290 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2548:     */                   }
/* 2549:2292 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2550:     */                 }
/* 2551:2293 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/* 2552:     */                 {
/* 2553:2294 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/* 2554:2295 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2555:     */                   }
/* 2556:2297 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2557:     */                 }
/* 2558:     */                 else
/* 2559:     */                 {
/* 2560:2299 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2561:     */                 }
/* 2562:2302 */                 getItemLore.set(index, durabilityRebuilder);
/* 2563:     */               }
/* 2564:     */             }
/* 2565:2306 */             ItemStack setItemInHand = new ItemStack(player.getItemInHand());
/* 2566:2307 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/* 2567:     */             
/* 2568:2309 */             setItemInHandMeta.setLore(getItemLore);
/* 2569:2310 */             setItemInHand.setItemMeta(setItemInHandMeta);
/* 2570:     */             
/* 2571:2312 */             player.getInventory().remove(player.getItemInHand());
/* 2572:2313 */             player.setItemInHand(new ItemStack(setItemInHand));
/* 2573:     */           }
/* 2574:     */         }
/* 2575:     */       }
/* 2576:     */     }
/* 2577:     */     
/* 2578:     */     @EventHandler
/* 2579:     */     public void flintAndSteelDurability(PlayerInteractEvent event)
/* 2580:     */     {
/* 2581:2322 */       if (!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getPlayer().getWorld().getName()))
/* 2582:     */       {
/* 2583:2323 */         if (ItemLoreStats.this.getConfig().getBoolean("usingMcMMO")) {
/* 2584:2323 */           return;
/* 2585:     */         }
/* 2586:2324 */         Player player = event.getPlayer();
/* 2587:2326 */         if ((player.getItemInHand().getType().equals(Material.FLINT_AND_STEEL)) && ((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) && 
/* 2588:2327 */           (player.getItemInHand() != null) && (ItemLoreStats.this.isTool(player.getItemInHand().getType())))
/* 2589:     */         {
/* 2590:2328 */           player.getItemInHand().setDurability((short)-1);
/* 2591:2329 */           if (player.getItemInHand().getItemMeta().hasLore())
/* 2592:     */           {
/* 2593:2331 */             String durabilityNoColour = ItemLoreStats.this.getConfig().getString("statNames.durability").replaceAll("&([0-9a-f])", "");
/* 2594:     */             
/* 2595:2333 */             List<String> getItemLore = player.getItemInHand().getItemMeta().getLore();
/* 2596:2335 */             for (String getDurability : getItemLore) {
/* 2597:2336 */               if (ChatColor.stripColor(getDurability).startsWith(durabilityNoColour))
/* 2598:     */               {
/* 2599:2337 */                 String durabilityRebuilder = "";
/* 2600:2338 */                 String durabilityAmountColour = "";
/* 2601:2339 */                 String prefixColourOnly = "";
/* 2602:     */                 
/* 2603:2341 */                 int index = getItemLore.indexOf(getDurability);
/* 2604:2342 */                 String maximumValue = ChatColor.stripColor(getDurability).trim().replace("[", "").substring(durabilityNoColour.length()).split("/")[1].replace("]", "").trim();
/* 2605:2343 */                 int currentValueMinusOne = Integer.parseInt(ChatColor.stripColor(getDurability).trim().replace("[", "").replace(": ", "").trim().substring(durabilityNoColour.length()).split("/")[0].replace("§", "").replace("]", "").trim()) - 1;
/* 2606:2345 */                 if (currentValueMinusOne + 1 < 2)
/* 2607:     */                 {
/* 2608:2346 */                   player.playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
/* 2609:2347 */                   player.getInventory().remove(player.getItemInHand());
/* 2610:2348 */                   return;
/* 2611:     */                 }
/* 2612:2351 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)).contains("&"))
/* 2613:     */                 {
/* 2614:2352 */                   if (getDurability.length() > 4)
/* 2615:     */                   {
/* 2616:2353 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)).contains("&")) {
/* 2617:2354 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2))) + ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(2, 4)));
/* 2618:     */                     } else {
/* 2619:2356 */                       prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2620:     */                     }
/* 2621:     */                   }
/* 2622:     */                   else {
/* 2623:2359 */                     prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour(ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.substring(0, 2)));
/* 2624:     */                   }
/* 2625:     */                 }
/* 2626:     */                 else {
/* 2627:2362 */                   prefixColourOnly = ItemLoreStats.this.util_Colours.replaceTooltipColour("&5&o");
/* 2628:     */                 }
/* 2629:2365 */                 if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(0, 2)).contains("&"))
/* 2630:     */                 {
/* 2631:2366 */                   if (getDurability.split("/")[1].trim().length() > 4)
/* 2632:     */                   {
/* 2633:2367 */                     if (ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim().substring(2, 4)).contains("&")) {
/* 2634:2368 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2) + ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(2, 4);
/* 2635:     */                     } else {
/* 2636:2370 */                       durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2637:     */                     }
/* 2638:     */                   }
/* 2639:     */                   else {
/* 2640:2373 */                     durabilityAmountColour = ItemLoreStats.this.util_Colours.extractAndReplaceTooltipColour(getDurability.split("/")[1].trim()).substring(0, 2);
/* 2641:     */                   }
/* 2642:     */                 }
/* 2643:     */                 else {
/* 2644:2376 */                   durabilityAmountColour = prefixColourOnly;
/* 2645:     */                 }
/* 2646:2379 */                 currentValueMinusOne += enchantmentDurabilityLoss(player.getItemInHand());
/* 2647:     */                 
/* 2648:2381 */                 int remainingDurabilityPercentage = currentValueMinusOne * 100 / Integer.parseInt(maximumValue);
/* 2649:2383 */                 if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 2.6D)
/* 2650:     */                 {
/* 2651:2384 */                   if ((remainingDurabilityPercentage == 25) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable25%DurabilityWarning"))) {
/* 2652:2385 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.RED + "25%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2653:     */                   }
/* 2654:2387 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.RED + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2655:     */                 }
/* 2656:2388 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 5.1D)
/* 2657:     */                 {
/* 2658:2389 */                   if ((remainingDurabilityPercentage == 50) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable50%DurabilityWarning"))) {
/* 2659:2390 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.YELLOW + "50%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2660:     */                   }
/* 2661:2392 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.YELLOW + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2662:     */                 }
/* 2663:2393 */                 else if (currentValueMinusOne < Integer.parseInt(maximumValue) / 10 * 7.6D)
/* 2664:     */                 {
/* 2665:2394 */                   if ((remainingDurabilityPercentage == 75) && (ItemLoreStats.plugin.getConfig().getBoolean("displayDurabilityWarnings.enable75%DurabilityWarning"))) {
/* 2666:2395 */                     player.sendMessage(player.getItemInHand().getItemMeta().getDisplayName() + ChatColor.LIGHT_PURPLE + " is at " + ChatColor.GREEN + "75%" + ChatColor.LIGHT_PURPLE + " durability.");
/* 2667:     */                   }
/* 2668:2397 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ChatColor.GREEN + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2669:     */                 }
/* 2670:     */                 else
/* 2671:     */                 {
/* 2672:2399 */                   durabilityRebuilder = ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + durabilityNoColour + ": " + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + currentValueMinusOne + ItemLoreStats.this.util_Colours.replaceTooltipColour(prefixColourOnly) + "/" + ItemLoreStats.this.util_Colours.replaceTooltipColour(durabilityAmountColour) + maximumValue;
/* 2673:     */                 }
/* 2674:2402 */                 getItemLore.set(index, durabilityRebuilder);
/* 2675:     */               }
/* 2676:     */             }
/* 2677:2406 */             ItemStack setItemInHand = new ItemStack(player.getItemInHand());
/* 2678:2407 */             ItemMeta setItemInHandMeta = setItemInHand.getItemMeta();
/* 2679:     */             
/* 2680:2409 */             setItemInHandMeta.setLore(getItemLore);
/* 2681:2410 */             setItemInHand.setItemMeta(setItemInHandMeta);
/* 2682:     */             
/* 2683:2412 */             player.getInventory().remove(player.getItemInHand());
/* 2684:2413 */             player.setItemInHand(new ItemStack(setItemInHand));
/* 2685:     */           }
/* 2686:     */         }
/* 2687:     */       }
/* 2688:     */     }
/* 2689:     */     
/* 2690:     */     public int enchantmentDurabilityLoss(ItemStack getItem)
/* 2691:     */     {
/* 2692:2421 */       int r = ItemLoreStats.this.random(1000);
/* 2693:2423 */       if (getItem.containsEnchantment(Enchantment.DURABILITY)) {
/* 2694:2424 */         if (getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 1)
/* 2695:     */         {
/* 2696:2425 */           if (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingOne") > 0)
/* 2697:     */           {
/* 2698:2426 */             if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingOne") * 10) {
/* 2699:2427 */               return 0;
/* 2700:     */             }
/* 2701:2429 */             return 1;
/* 2702:     */           }
/* 2703:     */         }
/* 2704:2432 */         else if (getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 2)
/* 2705:     */         {
/* 2706:2433 */           if (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingTwo") > 0)
/* 2707:     */           {
/* 2708:2434 */             if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingTwo") * 10) {
/* 2709:2435 */               return 0;
/* 2710:     */             }
/* 2711:2437 */             return 1;
/* 2712:     */           }
/* 2713:     */         }
/* 2714:2440 */         else if ((getItem.getEnchantmentLevel(Enchantment.DURABILITY) == 3) && 
/* 2715:2441 */           (ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingThree") > 0))
/* 2716:     */         {
/* 2717:2442 */           if (r >= ItemLoreStats.plugin.getConfig().getInt("durabilityLoss.enchant.unbreakingThree") * 10) {
/* 2718:2443 */             return 0;
/* 2719:     */           }
/* 2720:2445 */           return 1;
/* 2721:     */         }
/* 2722:     */       }
/* 2723:2450 */       return 0;
/* 2724:     */     }
/* 2725:     */     
/* 2726:     */     @EventHandler
/* 2727:     */     public void modifyMobHealth(CreatureSpawnEvent event)
/* 2728:     */     {
/* 2729:2455 */       if ((!ItemLoreStats.this.getConfig().getStringList("disabledInWorlds").contains(event.getEntity().getWorld().getName())) && 
/* 2730:2456 */         (ItemLoreStats.this.getConfig().getString("npcModifier." + event.getEntity().getWorld().getName()) != null))
/* 2731:     */       {
/* 2732:2457 */         Location loc = new Location(event.getEntity().getWorld(), ItemLoreStats.this.getConfig().getInt("npcModifier." + event.getEntity().getWorld().getName() + ".location.x"), ItemLoreStats.this.getConfig().getInt("npcModifier." + event.getEntity().getWorld().getName() + ".location.y"), ItemLoreStats.this.getConfig().getInt("npcModifier." + event.getEntity().getWorld().getName() + ".location.z"));
/* 2733:     */         
/* 2734:2459 */         double distance = event.getEntity().getLocation().distance(loc);
/* 2735:2460 */         double newHealth = Math.round(event.getEntity().getHealth() + distance * ItemLoreStats.this.getConfig().getDouble("npcModifier." + event.getEntity().getWorld().getName() + ".healthMultiplier"));
/* 2736:     */         
/* 2737:2462 */         event.getEntity().setMaxHealth(newHealth);
/* 2738:2463 */         event.getEntity().setHealth(newHealth);
/* 2739:     */       }
/* 2740:     */     }
/* 2741:     */   }
/* 2742:     */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.ItemLoreStats
 * JD-Core Version:    0.7.0.1
 */