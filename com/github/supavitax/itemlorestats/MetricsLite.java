/*   1:    */ package com.github.supavitax.itemlorestats;
/*   2:    */ 
/*   3:    */ import java.io.BufferedReader;
/*   4:    */ import java.io.ByteArrayOutputStream;
/*   5:    */ import java.io.File;
/*   6:    */ import java.io.IOException;
/*   7:    */ import java.io.InputStreamReader;
/*   8:    */ import java.io.OutputStream;
/*   9:    */ import java.io.PrintStream;
/*  10:    */ import java.io.UnsupportedEncodingException;
/*  11:    */ import java.net.Proxy;
/*  12:    */ import java.net.URL;
/*  13:    */ import java.net.URLConnection;
/*  14:    */ import java.net.URLEncoder;
/*  15:    */ import java.util.UUID;
/*  16:    */ import java.util.logging.Level;
/*  17:    */ import java.util.logging.Logger;
/*  18:    */ import java.util.zip.GZIPOutputStream;
/*  19:    */ import org.bukkit.Bukkit;
/*  20:    */ import org.bukkit.Server;
/*  21:    */ import org.bukkit.configuration.InvalidConfigurationException;
/*  22:    */ import org.bukkit.configuration.file.YamlConfiguration;
/*  23:    */ import org.bukkit.configuration.file.YamlConfigurationOptions;
/*  24:    */ import org.bukkit.plugin.Plugin;
/*  25:    */ import org.bukkit.plugin.PluginDescriptionFile;
/*  26:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  27:    */ import org.bukkit.scheduler.BukkitTask;
/*  28:    */ 
/*  29:    */ public class MetricsLite
/*  30:    */ {
/*  31:    */   private static final int REVISION = 7;
/*  32:    */   private static final String BASE_URL = "http://report.mcstats.org";
/*  33:    */   private static final String REPORT_URL = "/plugin/%s";
/*  34:    */   private static final int PING_INTERVAL = 15;
/*  35:    */   private final Plugin plugin;
/*  36:    */   private final YamlConfiguration configuration;
/*  37:    */   private final File configurationFile;
/*  38:    */   private final String guid;
/*  39:    */   private final boolean debug;
/*  40:103 */   private final Object optOutLock = new Object();
/*  41:108 */   private volatile BukkitTask task = null;
/*  42:    */   
/*  43:    */   public MetricsLite(Plugin plugin)
/*  44:    */     throws IOException
/*  45:    */   {
/*  46:111 */     if (plugin == null) {
/*  47:112 */       throw new IllegalArgumentException("Plugin cannot be null");
/*  48:    */     }
/*  49:115 */     this.plugin = plugin;
/*  50:    */     
/*  51:    */ 
/*  52:118 */     this.configurationFile = getConfigFile();
/*  53:119 */     this.configuration = YamlConfiguration.loadConfiguration(this.configurationFile);
/*  54:    */     
/*  55:    */ 
/*  56:122 */     this.configuration.addDefault("opt-out", Boolean.valueOf(false));
/*  57:123 */     this.configuration.addDefault("guid", UUID.randomUUID().toString());
/*  58:124 */     this.configuration.addDefault("debug", Boolean.valueOf(false));
/*  59:127 */     if (this.configuration.get("guid", null) == null)
/*  60:    */     {
/*  61:128 */       this.configuration.options().header("http://mcstats.org").copyDefaults(true);
/*  62:129 */       this.configuration.save(this.configurationFile);
/*  63:    */     }
/*  64:133 */     this.guid = this.configuration.getString("guid");
/*  65:134 */     this.debug = this.configuration.getBoolean("debug", false);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean start()
/*  69:    */   {
/*  70:145 */     synchronized (this.optOutLock)
/*  71:    */     {
/*  72:147 */       if (isOptOut())
/*  73:    */       {
/*  74:148 */         System.out.println("You have opted out of sending useful data to Mcstats.org");
/*  75:149 */         return false;
/*  76:    */       }
/*  77:153 */       if (this.task != null) {
/*  78:154 */         return true;
/*  79:    */       }
/*  80:158 */       this.task = this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, new Runnable()
/*  81:    */       {
/*  82:160 */         private boolean firstPost = true;
/*  83:    */         
/*  84:    */         public void run()
/*  85:    */         {
/*  86:    */           try
/*  87:    */           {
/*  88:165 */             synchronized (MetricsLite.this.optOutLock)
/*  89:    */             {
/*  90:167 */               if ((MetricsLite.this.isOptOut()) && (MetricsLite.this.task != null))
/*  91:    */               {
/*  92:168 */                 MetricsLite.this.task.cancel();
/*  93:169 */                 MetricsLite.this.task = null;
/*  94:    */               }
/*  95:    */             }
/*  96:176 */             MetricsLite.this.postPlugin(!this.firstPost);
/*  97:    */             
/*  98:    */ 
/*  99:    */ 
/* 100:180 */             this.firstPost = false;
/* 101:    */           }
/* 102:    */           catch (IOException e)
/* 103:    */           {
/* 104:182 */             if (MetricsLite.this.debug) {
/* 105:183 */               Bukkit.getLogger().log(Level.INFO, "[Metrics] " + e.getMessage());
/* 106:    */             }
/* 107:    */           }
/* 108:    */         }
/* 109:187 */       }, 0L, 18000L);
/* 110:    */       
/* 111:189 */       return true;
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   public boolean isOptOut()
/* 116:    */   {
/* 117:199 */     synchronized (this.optOutLock)
/* 118:    */     {
/* 119:    */       try
/* 120:    */       {
/* 121:202 */         this.configuration.load(getConfigFile());
/* 122:    */       }
/* 123:    */       catch (IOException ex)
/* 124:    */       {
/* 125:204 */         if (this.debug) {
/* 126:205 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/* 127:    */         }
/* 128:207 */         return true;
/* 129:    */       }
/* 130:    */       catch (InvalidConfigurationException ex)
/* 131:    */       {
/* 132:209 */         if (this.debug) {
/* 133:210 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/* 134:    */         }
/* 135:212 */         return true;
/* 136:    */       }
/* 137:214 */       return this.configuration.getBoolean("opt-out", false);
/* 138:    */     }
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void enable()
/* 142:    */     throws IOException
/* 143:    */   {
/* 144:225 */     synchronized (this.optOutLock)
/* 145:    */     {
/* 146:227 */       if (isOptOut())
/* 147:    */       {
/* 148:228 */         this.configuration.set("opt-out", Boolean.valueOf(false));
/* 149:229 */         this.configuration.save(this.configurationFile);
/* 150:    */       }
/* 151:233 */       if (this.task == null) {
/* 152:234 */         start();
/* 153:    */       }
/* 154:    */     }
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void disable()
/* 158:    */     throws IOException
/* 159:    */   {
/* 160:246 */     synchronized (this.optOutLock)
/* 161:    */     {
/* 162:248 */       if (!isOptOut())
/* 163:    */       {
/* 164:249 */         this.configuration.set("opt-out", Boolean.valueOf(true));
/* 165:250 */         this.configuration.save(this.configurationFile);
/* 166:    */       }
/* 167:254 */       if (this.task != null)
/* 168:    */       {
/* 169:255 */         this.task.cancel();
/* 170:256 */         this.task = null;
/* 171:    */       }
/* 172:    */     }
/* 173:    */   }
/* 174:    */   
/* 175:    */   public File getConfigFile()
/* 176:    */   {
/* 177:272 */     File pluginsFolder = this.plugin.getDataFolder().getParentFile();
/* 178:    */     
/* 179:    */ 
/* 180:275 */     return new File(new File(pluginsFolder, "PluginMetrics"), "config.yml");
/* 181:    */   }
/* 182:    */   
/* 183:    */   private void postPlugin(boolean isPing)
/* 184:    */     throws IOException
/* 185:    */   {
/* 186:283 */     PluginDescriptionFile description = this.plugin.getDescription();
/* 187:284 */     String pluginName = description.getName();
/* 188:285 */     boolean onlineMode = Bukkit.getServer().getOnlineMode();
/* 189:286 */     String pluginVersion = description.getVersion();
/* 190:287 */     String serverVersion = Bukkit.getVersion();
/* 191:288 */     int playersOnline = Bukkit.getServer().getOnlinePlayers().length;
/* 192:    */     
/* 193:    */ 
/* 194:    */ 
/* 195:    */ 
/* 196:293 */     StringBuilder json = new StringBuilder(1024);
/* 197:294 */     json.append('{');
/* 198:    */     
/* 199:    */ 
/* 200:297 */     appendJSONPair(json, "guid", this.guid);
/* 201:298 */     appendJSONPair(json, "plugin_version", pluginVersion);
/* 202:299 */     appendJSONPair(json, "server_version", serverVersion);
/* 203:300 */     appendJSONPair(json, "players_online", Integer.toString(playersOnline));
/* 204:    */     
/* 205:    */ 
/* 206:303 */     String osname = System.getProperty("os.name");
/* 207:304 */     String osarch = System.getProperty("os.arch");
/* 208:305 */     String osversion = System.getProperty("os.version");
/* 209:306 */     String java_version = System.getProperty("java.version");
/* 210:307 */     int coreCount = Runtime.getRuntime().availableProcessors();
/* 211:310 */     if (osarch.equals("amd64")) {
/* 212:311 */       osarch = "x86_64";
/* 213:    */     }
/* 214:314 */     appendJSONPair(json, "osname", osname);
/* 215:315 */     appendJSONPair(json, "osarch", osarch);
/* 216:316 */     appendJSONPair(json, "osversion", osversion);
/* 217:317 */     appendJSONPair(json, "cores", Integer.toString(coreCount));
/* 218:318 */     appendJSONPair(json, "auth_mode", onlineMode ? "1" : "0");
/* 219:319 */     appendJSONPair(json, "java_version", java_version);
/* 220:322 */     if (isPing) {
/* 221:323 */       appendJSONPair(json, "ping", "1");
/* 222:    */     }
/* 223:327 */     json.append('}');
/* 224:    */     
/* 225:    */ 
/* 226:330 */     URL url = new URL("http://report.mcstats.org" + String.format("/plugin/%s", new Object[] { urlEncode(pluginName) }));
/* 227:    */     URLConnection connection;
/* 228:    */     URLConnection connection;
/* 229:337 */     if (isMineshafterPresent()) {
/* 230:338 */       connection = url.openConnection(Proxy.NO_PROXY);
/* 231:    */     } else {
/* 232:340 */       connection = url.openConnection();
/* 233:    */     }
/* 234:344 */     byte[] uncompressed = json.toString().getBytes();
/* 235:345 */     byte[] compressed = gzip(json.toString());
/* 236:    */     
/* 237:    */ 
/* 238:348 */     connection.addRequestProperty("User-Agent", "MCStats/7");
/* 239:349 */     connection.addRequestProperty("Content-Type", "application/json");
/* 240:350 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 241:351 */     connection.addRequestProperty("Content-Length", Integer.toString(compressed.length));
/* 242:352 */     connection.addRequestProperty("Accept", "application/json");
/* 243:353 */     connection.addRequestProperty("Connection", "close");
/* 244:    */     
/* 245:355 */     connection.setDoOutput(true);
/* 246:357 */     if (this.debug) {
/* 247:358 */       System.out.println("[Metrics] Prepared request for " + pluginName + " uncompressed=" + uncompressed.length + " compressed=" + compressed.length);
/* 248:    */     }
/* 249:362 */     OutputStream os = connection.getOutputStream();
/* 250:363 */     os.write(compressed);
/* 251:364 */     os.flush();
/* 252:    */     
/* 253:    */ 
/* 254:367 */     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/* 255:368 */     String response = reader.readLine();
/* 256:    */     
/* 257:    */ 
/* 258:371 */     os.close();
/* 259:372 */     reader.close();
/* 260:374 */     if ((response == null) || (response.startsWith("ERR")) || (response.startsWith("7")))
/* 261:    */     {
/* 262:375 */       if (response == null) {
/* 263:376 */         response = "null";
/* 264:377 */       } else if (response.startsWith("7")) {
/* 265:378 */         response = response.substring(response.startsWith("7,") ? 2 : 1);
/* 266:    */       }
/* 267:381 */       throw new IOException(response);
/* 268:    */     }
/* 269:    */   }
/* 270:    */   
/* 271:    */   public static byte[] gzip(String input)
/* 272:    */   {
/* 273:392 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 274:393 */     GZIPOutputStream gzos = null;
/* 275:    */     try
/* 276:    */     {
/* 277:396 */       gzos = new GZIPOutputStream(baos);
/* 278:397 */       gzos.write(input.getBytes("UTF-8"));
/* 279:    */     }
/* 280:    */     catch (IOException e)
/* 281:    */     {
/* 282:399 */       e.printStackTrace();
/* 283:401 */       if (gzos != null) {
/* 284:    */         try
/* 285:    */         {
/* 286:402 */           gzos.close();
/* 287:    */         }
/* 288:    */         catch (IOException localIOException1) {}
/* 289:    */       }
/* 290:    */     }
/* 291:    */     finally
/* 292:    */     {
/* 293:401 */       if (gzos != null) {
/* 294:    */         try
/* 295:    */         {
/* 296:402 */           gzos.close();
/* 297:    */         }
/* 298:    */         catch (IOException localIOException2) {}
/* 299:    */       }
/* 300:    */     }
/* 301:407 */     return baos.toByteArray();
/* 302:    */   }
/* 303:    */   
/* 304:    */   private boolean isMineshafterPresent()
/* 305:    */   {
/* 306:    */     try
/* 307:    */     {
/* 308:417 */       Class.forName("mineshafter.MineServer");
/* 309:418 */       return true;
/* 310:    */     }
/* 311:    */     catch (Exception e) {}
/* 312:420 */     return false;
/* 313:    */   }
/* 314:    */   
/* 315:    */   private static void appendJSONPair(StringBuilder json, String key, String value)
/* 316:    */     throws UnsupportedEncodingException
/* 317:    */   {
/* 318:433 */     boolean isValueNumeric = false;
/* 319:    */     try
/* 320:    */     {
/* 321:436 */       if ((value.equals("0")) || (!value.endsWith("0")))
/* 322:    */       {
/* 323:437 */         Double.parseDouble(value);
/* 324:438 */         isValueNumeric = true;
/* 325:    */       }
/* 326:    */     }
/* 327:    */     catch (NumberFormatException e)
/* 328:    */     {
/* 329:441 */       isValueNumeric = false;
/* 330:    */     }
/* 331:444 */     if (json.charAt(json.length() - 1) != '{') {
/* 332:445 */       json.append(',');
/* 333:    */     }
/* 334:448 */     json.append(escapeJSON(key));
/* 335:449 */     json.append(':');
/* 336:451 */     if (isValueNumeric) {
/* 337:452 */       json.append(value);
/* 338:    */     } else {
/* 339:454 */       json.append(escapeJSON(value));
/* 340:    */     }
/* 341:    */   }
/* 342:    */   
/* 343:    */   private static String escapeJSON(String text)
/* 344:    */   {
/* 345:465 */     StringBuilder builder = new StringBuilder();
/* 346:    */     
/* 347:467 */     builder.append('"');
/* 348:468 */     for (int index = 0; index < text.length(); index++)
/* 349:    */     {
/* 350:469 */       char chr = text.charAt(index);
/* 351:471 */       switch (chr)
/* 352:    */       {
/* 353:    */       case '"': 
/* 354:    */       case '\\': 
/* 355:474 */         builder.append('\\');
/* 356:475 */         builder.append(chr);
/* 357:476 */         break;
/* 358:    */       case '\b': 
/* 359:478 */         builder.append("\\b");
/* 360:479 */         break;
/* 361:    */       case '\t': 
/* 362:481 */         builder.append("\\t");
/* 363:482 */         break;
/* 364:    */       case '\n': 
/* 365:484 */         builder.append("\\n");
/* 366:485 */         break;
/* 367:    */       case '\r': 
/* 368:487 */         builder.append("\\r");
/* 369:488 */         break;
/* 370:    */       default: 
/* 371:490 */         if (chr < ' ')
/* 372:    */         {
/* 373:491 */           String t = "000" + Integer.toHexString(chr);
/* 374:492 */           builder.append("\\u" + t.substring(t.length() - 4));
/* 375:    */         }
/* 376:    */         else
/* 377:    */         {
/* 378:494 */           builder.append(chr);
/* 379:    */         }
/* 380:    */         break;
/* 381:    */       }
/* 382:    */     }
/* 383:499 */     builder.append('"');
/* 384:    */     
/* 385:501 */     return builder.toString();
/* 386:    */   }
/* 387:    */   
/* 388:    */   private static String urlEncode(String text)
/* 389:    */     throws UnsupportedEncodingException
/* 390:    */   {
/* 391:511 */     return URLEncoder.encode(text, "UTF-8");
/* 392:    */   }
/* 393:    */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.MetricsLite
 * JD-Core Version:    0.7.0.1
 */