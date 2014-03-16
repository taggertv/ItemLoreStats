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
/*  40: 40 */   private final Object optOutLock = new Object();
/*  41: 42 */   private volatile BukkitTask task = null;
/*  42:    */   
/*  43:    */   public MetricsLite(Plugin plugin)
/*  44:    */     throws IOException
/*  45:    */   {
/*  46: 45 */     if (plugin == null) {
/*  47: 46 */       throw new IllegalArgumentException("Plugin cannot be null");
/*  48:    */     }
/*  49: 49 */     this.plugin = plugin;
/*  50:    */     
/*  51: 51 */     this.configurationFile = getConfigFile();
/*  52: 52 */     this.configuration = YamlConfiguration.loadConfiguration(this.configurationFile);
/*  53:    */     
/*  54: 54 */     this.configuration.addDefault("opt-out", Boolean.valueOf(false));
/*  55: 55 */     this.configuration.addDefault("guid", UUID.randomUUID().toString());
/*  56: 56 */     this.configuration.addDefault("debug", Boolean.valueOf(false));
/*  57: 58 */     if (this.configuration.get("guid", null) == null)
/*  58:    */     {
/*  59: 59 */       this.configuration.options().header("http://mcstats.org").copyDefaults(true);
/*  60: 60 */       this.configuration.save(this.configurationFile);
/*  61:    */     }
/*  62: 63 */     this.guid = this.configuration.getString("guid");
/*  63: 64 */     this.debug = this.configuration.getBoolean("debug", false);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public boolean start()
/*  67:    */   {
/*  68: 69 */     synchronized (this.optOutLock)
/*  69:    */     {
/*  70: 71 */       if (isOptOut())
/*  71:    */       {
/*  72: 72 */         System.out.println("You have opted out of sending useful data to Mcstats.org");
/*  73: 73 */         return false;
/*  74:    */       }
/*  75: 76 */       if (this.task != null) {
/*  76: 77 */         return true;
/*  77:    */       }
/*  78: 80 */       this.task = this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, new Runnable()
/*  79:    */       {
/*  80: 82 */         private boolean firstPost = true;
/*  81:    */         
/*  82:    */         public void run()
/*  83:    */         {
/*  84:    */           try
/*  85:    */           {
/*  86: 87 */             synchronized (MetricsLite.this.optOutLock)
/*  87:    */             {
/*  88: 89 */               if ((MetricsLite.this.isOptOut()) && (MetricsLite.this.task != null))
/*  89:    */               {
/*  90: 90 */                 MetricsLite.this.task.cancel();
/*  91: 91 */                 MetricsLite.this.task = null;
/*  92:    */               }
/*  93:    */             }
/*  94: 96 */             MetricsLite.this.postPlugin(!this.firstPost);
/*  95:    */             
/*  96: 98 */             this.firstPost = false;
/*  97:    */           }
/*  98:    */           catch (IOException e)
/*  99:    */           {
/* 100:100 */             if (MetricsLite.this.debug) {
/* 101:101 */               Bukkit.getLogger().log(Level.INFO, "[Metrics] " + e.getMessage());
/* 102:    */             }
/* 103:    */           }
/* 104:    */         }
/* 105:101 */       }, 0L, 18000L);
/* 106:    */       
/* 107:    */ 
/* 108:    */ 
/* 109:    */ 
/* 110:    */ 
/* 111:107 */       return true;
/* 112:    */     }
/* 113:    */   }
/* 114:    */   
/* 115:    */   public boolean isOptOut()
/* 116:    */   {
/* 117:113 */     synchronized (this.optOutLock)
/* 118:    */     {
/* 119:    */       try
/* 120:    */       {
/* 121:116 */         this.configuration.load(getConfigFile());
/* 122:    */       }
/* 123:    */       catch (IOException ex)
/* 124:    */       {
/* 125:118 */         if (this.debug) {
/* 126:119 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/* 127:    */         }
/* 128:121 */         return true;
/* 129:    */       }
/* 130:    */       catch (InvalidConfigurationException ex)
/* 131:    */       {
/* 132:123 */         if (this.debug) {
/* 133:124 */           Bukkit.getLogger().log(Level.INFO, "[Metrics] " + ex.getMessage());
/* 134:    */         }
/* 135:126 */         return true;
/* 136:    */       }
/* 137:128 */       return this.configuration.getBoolean("opt-out", false);
/* 138:    */     }
/* 139:    */   }
/* 140:    */   
/* 141:    */   public void enable()
/* 142:    */     throws IOException
/* 143:    */   {
/* 144:135 */     synchronized (this.optOutLock)
/* 145:    */     {
/* 146:137 */       if (isOptOut())
/* 147:    */       {
/* 148:138 */         this.configuration.set("opt-out", Boolean.valueOf(false));
/* 149:139 */         this.configuration.save(this.configurationFile);
/* 150:    */       }
/* 151:142 */       if (this.task == null) {
/* 152:143 */         start();
/* 153:    */       }
/* 154:    */     }
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void disable()
/* 158:    */     throws IOException
/* 159:    */   {
/* 160:150 */     synchronized (this.optOutLock)
/* 161:    */     {
/* 162:152 */       if (!isOptOut())
/* 163:    */       {
/* 164:153 */         this.configuration.set("opt-out", Boolean.valueOf(true));
/* 165:154 */         this.configuration.save(this.configurationFile);
/* 166:    */       }
/* 167:157 */       if (this.task != null)
/* 168:    */       {
/* 169:158 */         this.task.cancel();
/* 170:159 */         this.task = null;
/* 171:    */       }
/* 172:    */     }
/* 173:    */   }
/* 174:    */   
/* 175:    */   public File getConfigFile()
/* 176:    */   {
/* 177:166 */     File pluginsFolder = this.plugin.getDataFolder().getParentFile();
/* 178:    */     
/* 179:168 */     return new File(new File(pluginsFolder, "PluginMetrics"), "config.yml");
/* 180:    */   }
/* 181:    */   
/* 182:    */   private void postPlugin(boolean isPing)
/* 183:    */     throws IOException
/* 184:    */   {
/* 185:174 */     PluginDescriptionFile description = this.plugin.getDescription();
/* 186:175 */     String pluginName = description.getName();
/* 187:176 */     boolean onlineMode = Bukkit.getServer().getOnlineMode();
/* 188:177 */     String pluginVersion = description.getVersion();
/* 189:178 */     String serverVersion = Bukkit.getVersion();
/* 190:179 */     int playersOnline = Bukkit.getServer().getOnlinePlayers().length;
/* 191:    */     
/* 192:181 */     StringBuilder json = new StringBuilder(1024);
/* 193:182 */     json.append('{');
/* 194:    */     
/* 195:184 */     appendJSONPair(json, "guid", this.guid);
/* 196:185 */     appendJSONPair(json, "plugin_version", pluginVersion);
/* 197:186 */     appendJSONPair(json, "server_version", serverVersion);
/* 198:187 */     appendJSONPair(json, "players_online", Integer.toString(playersOnline));
/* 199:    */     
/* 200:189 */     String osname = System.getProperty("os.name");
/* 201:190 */     String osarch = System.getProperty("os.arch");
/* 202:191 */     String osversion = System.getProperty("os.version");
/* 203:192 */     String java_version = System.getProperty("java.version");
/* 204:193 */     int coreCount = Runtime.getRuntime().availableProcessors();
/* 205:195 */     if (osarch.equals("amd64")) {
/* 206:196 */       osarch = "x86_64";
/* 207:    */     }
/* 208:199 */     appendJSONPair(json, "osname", osname);
/* 209:200 */     appendJSONPair(json, "osarch", osarch);
/* 210:201 */     appendJSONPair(json, "osversion", osversion);
/* 211:202 */     appendJSONPair(json, "cores", Integer.toString(coreCount));
/* 212:203 */     appendJSONPair(json, "auth_mode", onlineMode ? "1" : "0");
/* 213:204 */     appendJSONPair(json, "java_version", java_version);
/* 214:206 */     if (isPing) {
/* 215:207 */       appendJSONPair(json, "ping", "1");
/* 216:    */     }
/* 217:210 */     json.append('}');
/* 218:    */     
/* 219:212 */     URL url = new URL("http://report.mcstats.org" + String.format("/plugin/%s", new Object[] { urlEncode(pluginName) }));
/* 220:    */     URLConnection connection;
/* 221:    */     URLConnection connection;
/* 222:214 */     if (isMineshafterPresent()) {
/* 223:215 */       connection = url.openConnection(Proxy.NO_PROXY);
/* 224:    */     } else {
/* 225:217 */       connection = url.openConnection();
/* 226:    */     }
/* 227:220 */     byte[] uncompressed = json.toString().getBytes();
/* 228:221 */     byte[] compressed = gzip(json.toString());
/* 229:    */     
/* 230:223 */     connection.addRequestProperty("User-Agent", "MCStats/7");
/* 231:224 */     connection.addRequestProperty("Content-Type", "application/json");
/* 232:225 */     connection.addRequestProperty("Content-Encoding", "gzip");
/* 233:226 */     connection.addRequestProperty("Content-Length", Integer.toString(compressed.length));
/* 234:227 */     connection.addRequestProperty("Accept", "application/json");
/* 235:228 */     connection.addRequestProperty("Connection", "close");
/* 236:    */     
/* 237:230 */     connection.setDoOutput(true);
/* 238:232 */     if (this.debug) {
/* 239:233 */       System.out.println("[Metrics] Prepared request for " + pluginName + " uncompressed=" + uncompressed.length + " compressed=" + compressed.length);
/* 240:    */     }
/* 241:236 */     OutputStream os = connection.getOutputStream();
/* 242:237 */     os.write(compressed);
/* 243:238 */     os.flush();
/* 244:    */     
/* 245:240 */     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/* 246:241 */     String response = reader.readLine();
/* 247:    */     
/* 248:243 */     os.close();
/* 249:244 */     reader.close();
/* 250:246 */     if ((response == null) || (response.startsWith("ERR")) || (response.startsWith("7")))
/* 251:    */     {
/* 252:247 */       if (response == null) {
/* 253:248 */         response = "null";
/* 254:249 */       } else if (response.startsWith("7")) {
/* 255:250 */         response = response.substring(response.startsWith("7,") ? 2 : 1);
/* 256:    */       }
/* 257:253 */       throw new IOException(response);
/* 258:    */     }
/* 259:    */   }
/* 260:    */   
/* 261:    */   public static byte[] gzip(String input)
/* 262:    */   {
/* 263:259 */     baos = new ByteArrayOutputStream();
/* 264:260 */     GZIPOutputStream gzos = null;
/* 265:    */     try
/* 266:    */     {
/* 267:263 */       gzos = new GZIPOutputStream(baos);
/* 268:264 */       gzos.write(input.getBytes("UTF-8"));
/* 269:    */       
/* 270:    */ 
/* 271:    */ 
/* 272:    */ 
/* 273:    */ 
/* 274:    */ 
/* 275:    */ 
/* 276:    */ 
/* 277:    */ 
/* 278:    */ 
/* 279:    */ 
/* 280:    */ 
/* 281:    */ 
/* 282:    */ 
/* 283:    */ 
/* 284:    */ 
/* 285:    */ 
/* 286:    */ 
/* 287:    */ 
/* 288:284 */       return baos.toByteArray();
/* 289:    */     }
/* 290:    */     catch (IOException e)
/* 291:    */     {
/* 292:266 */       e.printStackTrace();
/* 293:268 */       if (gzos != null) {
/* 294:    */         try
/* 295:    */         {
/* 296:269 */           gzos.close();
/* 297:    */         }
/* 298:    */         catch (IOException localIOException1) {}
/* 299:    */       }
/* 300:    */     }
/* 301:    */     finally
/* 302:    */     {
/* 303:277 */       if (gzos != null) {
/* 304:    */         try
/* 305:    */         {
/* 306:278 */           gzos.close();
/* 307:    */         }
/* 308:    */         catch (IOException localIOException2) {}
/* 309:    */       }
/* 310:    */     }
/* 311:    */   }
/* 312:    */   
/* 313:    */   private boolean isMineshafterPresent()
/* 314:    */   {
/* 315:    */     try
/* 316:    */     {
/* 317:291 */       Class.forName("mineshafter.MineServer");
/* 318:292 */       return true;
/* 319:    */     }
/* 320:    */     catch (Exception e) {}
/* 321:294 */     return false;
/* 322:    */   }
/* 323:    */   
/* 324:    */   private static void appendJSONPair(StringBuilder json, String key, String value)
/* 325:    */     throws UnsupportedEncodingException
/* 326:    */   {
/* 327:300 */     boolean isValueNumeric = false;
/* 328:    */     try
/* 329:    */     {
/* 330:303 */       if ((value.equals("0")) || (!value.endsWith("0")))
/* 331:    */       {
/* 332:304 */         Double.parseDouble(value);
/* 333:305 */         isValueNumeric = true;
/* 334:    */       }
/* 335:    */     }
/* 336:    */     catch (NumberFormatException e)
/* 337:    */     {
/* 338:308 */       isValueNumeric = false;
/* 339:    */     }
/* 340:311 */     if (json.charAt(json.length() - 1) != '{') {
/* 341:312 */       json.append(',');
/* 342:    */     }
/* 343:315 */     json.append(escapeJSON(key));
/* 344:316 */     json.append(':');
/* 345:318 */     if (isValueNumeric) {
/* 346:319 */       json.append(value);
/* 347:    */     } else {
/* 348:321 */       json.append(escapeJSON(value));
/* 349:    */     }
/* 350:    */   }
/* 351:    */   
/* 352:    */   private static String escapeJSON(String text)
/* 353:    */   {
/* 354:326 */     StringBuilder builder = new StringBuilder();
/* 355:    */     
/* 356:328 */     builder.append('"');
/* 357:329 */     for (int index = 0; index < text.length(); index++)
/* 358:    */     {
/* 359:330 */       char chr = text.charAt(index);
/* 360:332 */       switch (chr)
/* 361:    */       {
/* 362:    */       case '"': 
/* 363:    */       case '\\': 
/* 364:335 */         builder.append('\\');
/* 365:336 */         builder.append(chr);
/* 366:337 */         break;
/* 367:    */       case '\b': 
/* 368:339 */         builder.append("\\b");
/* 369:340 */         break;
/* 370:    */       case '\t': 
/* 371:342 */         builder.append("\\t");
/* 372:343 */         break;
/* 373:    */       case '\n': 
/* 374:345 */         builder.append("\\n");
/* 375:346 */         break;
/* 376:    */       case '\r': 
/* 377:348 */         builder.append("\\r");
/* 378:349 */         break;
/* 379:    */       default: 
/* 380:351 */         if (chr < ' ')
/* 381:    */         {
/* 382:352 */           String t = "000" + Integer.toHexString(chr);
/* 383:353 */           builder.append("\\u" + t.substring(t.length() - 4));
/* 384:    */         }
/* 385:    */         else
/* 386:    */         {
/* 387:355 */           builder.append(chr);
/* 388:    */         }
/* 389:    */         break;
/* 390:    */       }
/* 391:    */     }
/* 392:360 */     builder.append('"');
/* 393:    */     
/* 394:362 */     return builder.toString();
/* 395:    */   }
/* 396:    */   
/* 397:    */   private static String urlEncode(String text)
/* 398:    */     throws UnsupportedEncodingException
/* 399:    */   {
/* 400:368 */     return URLEncoder.encode(text, "UTF-8");
/* 401:    */   }
/* 402:    */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.MetricsLite
 * JD-Core Version:    0.7.0.1
 */