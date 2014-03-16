/*   1:    */ package com.github.supavitax.itemlorestats.Util;
/*   2:    */ 
/*   3:    */ import org.bukkit.ChatColor;
/*   4:    */ 
/*   5:    */ public class Util_Colours
/*   6:    */ {
/*   7:    */   public String extractTooltipColour(String input)
/*   8:    */   {
/*   9:  8 */     String toolTipColour = "";
/*  10: 10 */     if (input.contains("&r")) {
/*  11: 11 */       toolTipColour = toolTipColour + "&r";
/*  12:    */     }
/*  13: 14 */     if (input.contains("&o")) {
/*  14: 15 */       toolTipColour = toolTipColour + "&o";
/*  15:    */     }
/*  16: 18 */     if (input.contains("&n")) {
/*  17: 19 */       toolTipColour = toolTipColour + "&n";
/*  18:    */     }
/*  19: 22 */     if (input.contains("&m")) {
/*  20: 23 */       toolTipColour = toolTipColour + "&m";
/*  21:    */     }
/*  22: 26 */     if (input.contains("&l")) {
/*  23: 27 */       toolTipColour = toolTipColour + "&l";
/*  24:    */     }
/*  25: 30 */     if (input.contains("&k")) {
/*  26: 31 */       toolTipColour = toolTipColour + "&k";
/*  27:    */     }
/*  28: 34 */     if (input.contains("&f")) {
/*  29: 35 */       toolTipColour = toolTipColour + "&f";
/*  30:    */     }
/*  31: 38 */     if (input.contains("&re")) {
/*  32: 39 */       toolTipColour = toolTipColour + "&e";
/*  33:    */     }
/*  34: 42 */     if (input.contains("&d")) {
/*  35: 43 */       toolTipColour = toolTipColour + "&d";
/*  36:    */     }
/*  37: 46 */     if (input.contains("&c")) {
/*  38: 47 */       toolTipColour = toolTipColour + "&c";
/*  39:    */     }
/*  40: 50 */     if (input.contains("&b")) {
/*  41: 51 */       toolTipColour = toolTipColour + "&b";
/*  42:    */     }
/*  43: 54 */     if (input.contains("&a")) {
/*  44: 55 */       toolTipColour = toolTipColour + "&a";
/*  45:    */     }
/*  46: 58 */     if (input.contains("&9")) {
/*  47: 59 */       toolTipColour = toolTipColour + "&9";
/*  48:    */     }
/*  49: 62 */     if (input.contains("&8")) {
/*  50: 63 */       toolTipColour = toolTipColour + "&8";
/*  51:    */     }
/*  52: 66 */     if (input.contains("&7")) {
/*  53: 67 */       toolTipColour = toolTipColour + "&7";
/*  54:    */     }
/*  55: 70 */     if (input.contains("&6")) {
/*  56: 71 */       toolTipColour = toolTipColour + "&6";
/*  57:    */     }
/*  58: 74 */     if (input.contains("&5")) {
/*  59: 75 */       toolTipColour = toolTipColour + "&5";
/*  60:    */     }
/*  61: 78 */     if (input.contains("&4")) {
/*  62: 79 */       toolTipColour = toolTipColour + "&4";
/*  63:    */     }
/*  64: 82 */     if (input.contains("&3")) {
/*  65: 83 */       toolTipColour = toolTipColour + "&3";
/*  66:    */     }
/*  67: 86 */     if (input.contains("&2")) {
/*  68: 87 */       toolTipColour = toolTipColour + "&2";
/*  69:    */     }
/*  70: 90 */     if (input.contains("&1")) {
/*  71: 91 */       toolTipColour = toolTipColour + "&1";
/*  72:    */     }
/*  73: 94 */     if (input.contains("&0")) {
/*  74: 95 */       toolTipColour = toolTipColour + "&0";
/*  75:    */     }
/*  76: 98 */     return toolTipColour;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public String extractAndReplaceTooltipColour(String input)
/*  80:    */   {
/*  81:102 */     String toolTipColour = input;
/*  82:    */     
/*  83:104 */     toolTipColour = toolTipColour.replaceAll(ChatColor.RESET.toString(), "&r");
/*  84:105 */     toolTipColour = toolTipColour.replaceAll(ChatColor.ITALIC.toString(), "&o");
/*  85:106 */     toolTipColour = toolTipColour.replaceAll(ChatColor.UNDERLINE.toString(), "&n");
/*  86:107 */     toolTipColour = toolTipColour.replaceAll(ChatColor.STRIKETHROUGH.toString(), "&m");
/*  87:108 */     toolTipColour = toolTipColour.replaceAll(ChatColor.BOLD.toString(), "&l");
/*  88:109 */     toolTipColour = toolTipColour.replaceAll(ChatColor.MAGIC.toString(), "&k");
/*  89:110 */     toolTipColour = toolTipColour.replaceAll(ChatColor.WHITE.toString(), "&f");
/*  90:111 */     toolTipColour = toolTipColour.replaceAll(ChatColor.YELLOW.toString(), "&e");
/*  91:112 */     toolTipColour = toolTipColour.replaceAll(ChatColor.LIGHT_PURPLE.toString(), "&d");
/*  92:113 */     toolTipColour = toolTipColour.replaceAll(ChatColor.RED.toString(), "&c");
/*  93:114 */     toolTipColour = toolTipColour.replaceAll(ChatColor.AQUA.toString(), "&b");
/*  94:115 */     toolTipColour = toolTipColour.replaceAll(ChatColor.GREEN.toString(), "&a");
/*  95:116 */     toolTipColour = toolTipColour.replaceAll(ChatColor.BLUE.toString(), "&9");
/*  96:117 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_GRAY.toString(), "&8");
/*  97:118 */     toolTipColour = toolTipColour.replaceAll(ChatColor.GRAY.toString(), "&7");
/*  98:119 */     toolTipColour = toolTipColour.replaceAll(ChatColor.GOLD.toString(), "&6");
/*  99:120 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_PURPLE.toString(), "&5");
/* 100:121 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_RED.toString(), "&4");
/* 101:122 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_AQUA.toString(), "&3");
/* 102:123 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_GREEN.toString(), "&2");
/* 103:124 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_BLUE.toString(), "&1");
/* 104:125 */     toolTipColour = toolTipColour.replaceAll(ChatColor.BLACK.toString(), "&0");
/* 105:    */     
/* 106:127 */     return toolTipColour;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public String replaceTooltipColour(String input)
/* 110:    */   {
/* 111:131 */     String toolTipColour = input;
/* 112:    */     
/* 113:133 */     toolTipColour = toolTipColour.replaceAll("%n", "        ");
/* 114:134 */     toolTipColour = toolTipColour.replaceAll("&r", ChatColor.RESET);
/* 115:135 */     toolTipColour = toolTipColour.replaceAll("&o", ChatColor.ITALIC);
/* 116:136 */     toolTipColour = toolTipColour.replaceAll("&n", ChatColor.UNDERLINE);
/* 117:137 */     toolTipColour = toolTipColour.replaceAll("&m", ChatColor.STRIKETHROUGH);
/* 118:138 */     toolTipColour = toolTipColour.replaceAll("&l", ChatColor.BOLD);
/* 119:139 */     toolTipColour = toolTipColour.replaceAll("&k", ChatColor.MAGIC);
/* 120:140 */     toolTipColour = toolTipColour.replaceAll("&f", ChatColor.WHITE);
/* 121:141 */     toolTipColour = toolTipColour.replaceAll("&e", ChatColor.YELLOW);
/* 122:142 */     toolTipColour = toolTipColour.replaceAll("&d", ChatColor.LIGHT_PURPLE);
/* 123:143 */     toolTipColour = toolTipColour.replaceAll("&c", ChatColor.RED);
/* 124:144 */     toolTipColour = toolTipColour.replaceAll("&b", ChatColor.AQUA);
/* 125:145 */     toolTipColour = toolTipColour.replaceAll("&a", ChatColor.GREEN);
/* 126:146 */     toolTipColour = toolTipColour.replaceAll("&9", ChatColor.BLUE);
/* 127:147 */     toolTipColour = toolTipColour.replaceAll("&8", ChatColor.DARK_GRAY);
/* 128:148 */     toolTipColour = toolTipColour.replaceAll("&7", ChatColor.GRAY);
/* 129:149 */     toolTipColour = toolTipColour.replaceAll("&6", ChatColor.GOLD);
/* 130:150 */     toolTipColour = toolTipColour.replaceAll("&5", ChatColor.DARK_PURPLE);
/* 131:151 */     toolTipColour = toolTipColour.replaceAll("&4", ChatColor.DARK_RED);
/* 132:152 */     toolTipColour = toolTipColour.replaceAll("&3", ChatColor.DARK_AQUA);
/* 133:153 */     toolTipColour = toolTipColour.replaceAll("&2", ChatColor.DARK_GREEN);
/* 134:154 */     toolTipColour = toolTipColour.replaceAll("&1", ChatColor.DARK_BLUE);
/* 135:155 */     toolTipColour = toolTipColour.replaceAll("&0", ChatColor.BLACK);
/* 136:    */     
/* 137:157 */     return toolTipColour;
/* 138:    */   }
/* 139:    */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.Util.Util_Colours
 * JD-Core Version:    0.7.0.1
 */