/*   1:    */ package com.github.supavitax.itemlorestats.Util;
/*   2:    */ 
/*   3:    */ import org.bukkit.ChatColor;
/*   4:    */ 
/*   5:    */ public class Util_Colours
/*   6:    */ {
/*   7:    */   public String extractTooltipColour(String input)
/*   8:    */   {
/*   9:  9 */     String toolTipColour = "";
/*  10: 11 */     if (input.contains("&r")) {
/*  11: 12 */       toolTipColour = toolTipColour + "&r";
/*  12:    */     }
/*  13: 15 */     if (input.contains("&o")) {
/*  14: 16 */       toolTipColour = toolTipColour + "&o";
/*  15:    */     }
/*  16: 19 */     if (input.contains("&n")) {
/*  17: 20 */       toolTipColour = toolTipColour + "&n";
/*  18:    */     }
/*  19: 23 */     if (input.contains("&m")) {
/*  20: 24 */       toolTipColour = toolTipColour + "&m";
/*  21:    */     }
/*  22: 27 */     if (input.contains("&l")) {
/*  23: 28 */       toolTipColour = toolTipColour + "&l";
/*  24:    */     }
/*  25: 31 */     if (input.contains("&k")) {
/*  26: 32 */       toolTipColour = toolTipColour + "&k";
/*  27:    */     }
/*  28: 35 */     if (input.contains("&f")) {
/*  29: 36 */       toolTipColour = toolTipColour + "&f";
/*  30:    */     }
/*  31: 39 */     if (input.contains("&re")) {
/*  32: 40 */       toolTipColour = toolTipColour + "&e";
/*  33:    */     }
/*  34: 43 */     if (input.contains("&d")) {
/*  35: 44 */       toolTipColour = toolTipColour + "&d";
/*  36:    */     }
/*  37: 47 */     if (input.contains("&c")) {
/*  38: 48 */       toolTipColour = toolTipColour + "&c";
/*  39:    */     }
/*  40: 51 */     if (input.contains("&b")) {
/*  41: 52 */       toolTipColour = toolTipColour + "&b";
/*  42:    */     }
/*  43: 55 */     if (input.contains("&a")) {
/*  44: 56 */       toolTipColour = toolTipColour + "&a";
/*  45:    */     }
/*  46: 59 */     if (input.contains("&9")) {
/*  47: 60 */       toolTipColour = toolTipColour + "&9";
/*  48:    */     }
/*  49: 63 */     if (input.contains("&8")) {
/*  50: 64 */       toolTipColour = toolTipColour + "&8";
/*  51:    */     }
/*  52: 67 */     if (input.contains("&7")) {
/*  53: 68 */       toolTipColour = toolTipColour + "&7";
/*  54:    */     }
/*  55: 71 */     if (input.contains("&6")) {
/*  56: 72 */       toolTipColour = toolTipColour + "&6";
/*  57:    */     }
/*  58: 75 */     if (input.contains("&5")) {
/*  59: 76 */       toolTipColour = toolTipColour + "&5";
/*  60:    */     }
/*  61: 79 */     if (input.contains("&4")) {
/*  62: 80 */       toolTipColour = toolTipColour + "&4";
/*  63:    */     }
/*  64: 83 */     if (input.contains("&3")) {
/*  65: 84 */       toolTipColour = toolTipColour + "&3";
/*  66:    */     }
/*  67: 87 */     if (input.contains("&2")) {
/*  68: 88 */       toolTipColour = toolTipColour + "&2";
/*  69:    */     }
/*  70: 91 */     if (input.contains("&1")) {
/*  71: 92 */       toolTipColour = toolTipColour + "&1";
/*  72:    */     }
/*  73: 95 */     if (input.contains("&0")) {
/*  74: 96 */       toolTipColour = toolTipColour + "&0";
/*  75:    */     }
/*  76: 99 */     return toolTipColour;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public String extractAndReplaceTooltipColour(String input)
/*  80:    */   {
/*  81:103 */     String toolTipColour = input;
/*  82:    */     
/*  83:105 */     toolTipColour = toolTipColour.replaceAll(ChatColor.RESET.toString(), "&r");
/*  84:106 */     toolTipColour = toolTipColour.replaceAll(ChatColor.ITALIC.toString(), "&o");
/*  85:107 */     toolTipColour = toolTipColour.replaceAll(ChatColor.UNDERLINE.toString(), "&n");
/*  86:108 */     toolTipColour = toolTipColour.replaceAll(ChatColor.STRIKETHROUGH.toString(), "&m");
/*  87:109 */     toolTipColour = toolTipColour.replaceAll(ChatColor.BOLD.toString(), "&l");
/*  88:110 */     toolTipColour = toolTipColour.replaceAll(ChatColor.MAGIC.toString(), "&k");
/*  89:111 */     toolTipColour = toolTipColour.replaceAll(ChatColor.WHITE.toString(), "&f");
/*  90:112 */     toolTipColour = toolTipColour.replaceAll(ChatColor.YELLOW.toString(), "&e");
/*  91:113 */     toolTipColour = toolTipColour.replaceAll(ChatColor.LIGHT_PURPLE.toString(), "&d");
/*  92:114 */     toolTipColour = toolTipColour.replaceAll(ChatColor.RED.toString(), "&c");
/*  93:115 */     toolTipColour = toolTipColour.replaceAll(ChatColor.AQUA.toString(), "&b");
/*  94:116 */     toolTipColour = toolTipColour.replaceAll(ChatColor.GREEN.toString(), "&a");
/*  95:117 */     toolTipColour = toolTipColour.replaceAll(ChatColor.BLUE.toString(), "&9");
/*  96:118 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_GRAY.toString(), "&8");
/*  97:119 */     toolTipColour = toolTipColour.replaceAll(ChatColor.GRAY.toString(), "&7");
/*  98:120 */     toolTipColour = toolTipColour.replaceAll(ChatColor.GOLD.toString(), "&6");
/*  99:121 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_PURPLE.toString(), "&5");
/* 100:122 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_RED.toString(), "&4");
/* 101:123 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_AQUA.toString(), "&3");
/* 102:124 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_GREEN.toString(), "&2");
/* 103:125 */     toolTipColour = toolTipColour.replaceAll(ChatColor.DARK_BLUE.toString(), "&1");
/* 104:126 */     toolTipColour = toolTipColour.replaceAll(ChatColor.BLACK.toString(), "&0");
/* 105:    */     
/* 106:128 */     return toolTipColour;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public String replaceTooltipColour(String input)
/* 110:    */   {
/* 111:132 */     String toolTipColour = input;
/* 112:    */     
/* 113:134 */     toolTipColour = toolTipColour.replaceAll("%n", "        ");
/* 114:135 */     toolTipColour = toolTipColour.replaceAll("&r", ChatColor.RESET + "");
/* 115:136 */     toolTipColour = toolTipColour.replaceAll("&o", ChatColor.ITALIC + "");
/* 116:137 */     toolTipColour = toolTipColour.replaceAll("&n", ChatColor.UNDERLINE + "");
/* 117:138 */     toolTipColour = toolTipColour.replaceAll("&m", ChatColor.STRIKETHROUGH + "");
/* 118:139 */     toolTipColour = toolTipColour.replaceAll("&l", ChatColor.BOLD + "");
/* 119:140 */     toolTipColour = toolTipColour.replaceAll("&k", ChatColor.MAGIC + "");
/* 120:141 */     toolTipColour = toolTipColour.replaceAll("&f", ChatColor.WHITE + "");
/* 121:142 */     toolTipColour = toolTipColour.replaceAll("&e", ChatColor.YELLOW + "");
/* 122:143 */     toolTipColour = toolTipColour.replaceAll("&d", ChatColor.LIGHT_PURPLE + "");
/* 123:144 */     toolTipColour = toolTipColour.replaceAll("&c", ChatColor.RED + "");
/* 124:145 */     toolTipColour = toolTipColour.replaceAll("&b", ChatColor.AQUA + "");
/* 125:146 */     toolTipColour = toolTipColour.replaceAll("&a", ChatColor.GREEN + "");
/* 126:147 */     toolTipColour = toolTipColour.replaceAll("&9", ChatColor.BLUE + "");
/* 127:148 */     toolTipColour = toolTipColour.replaceAll("&8", ChatColor.DARK_GRAY + "");
/* 128:149 */     toolTipColour = toolTipColour.replaceAll("&7", ChatColor.GRAY + "");
/* 129:150 */     toolTipColour = toolTipColour.replaceAll("&6", ChatColor.GOLD + "");
/* 130:151 */     toolTipColour = toolTipColour.replaceAll("&5", ChatColor.DARK_PURPLE + "");
/* 131:152 */     toolTipColour = toolTipColour.replaceAll("&4", ChatColor.DARK_RED + "");
/* 132:153 */     toolTipColour = toolTipColour.replaceAll("&3", ChatColor.DARK_AQUA + "");
/* 133:154 */     toolTipColour = toolTipColour.replaceAll("&2", ChatColor.DARK_GREEN + "");
/* 134:155 */     toolTipColour = toolTipColour.replaceAll("&1", ChatColor.DARK_BLUE + "");
/* 135:156 */     toolTipColour = toolTipColour.replaceAll("&0", ChatColor.BLACK + "");
/* 136:    */     
/* 137:158 */     return toolTipColour;
/* 138:    */   }
/* 139:    */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.Util.Util_Colours
 * JD-Core Version:    0.7.0.1
 */