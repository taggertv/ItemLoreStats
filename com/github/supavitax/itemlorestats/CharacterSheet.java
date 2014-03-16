/*   1:    */ package com.github.supavitax.itemlorestats;
/*   2:    */ 
/*   3:    */ import com.github.supavitax.itemlorestats.Util.Util_Colours;
/*   4:    */ import org.bukkit.ChatColor;
/*   5:    */ import org.bukkit.configuration.file.FileConfiguration;
/*   6:    */ import org.bukkit.entity.Player;
/*   7:    */ import org.bukkit.inventory.ItemStack;
/*   8:    */ 
/*   9:    */ public class CharacterSheet
/*  10:    */ {
/*  11: 11 */   GearStats gearStats = new GearStats();
/*  12: 12 */   SetBonuses setBonuses = new SetBonuses();
/*  13: 13 */   Util_Colours util_Colours = new Util_Colours();
/*  14:    */   
/*  15:    */   public void returnStats(Player player, double passHealth)
/*  16:    */   {
/*  17: 17 */     String armour = ItemLoreStats.plugin.getConfig().getString("statNames.armour");
/*  18: 18 */     String dodge = ItemLoreStats.plugin.getConfig().getString("statNames.dodge");
/*  19: 19 */     String block = ItemLoreStats.plugin.getConfig().getString("statNames.block");
/*  20: 20 */     String critChance = ItemLoreStats.plugin.getConfig().getString("statNames.critChance");
/*  21: 21 */     String critDamage = ItemLoreStats.plugin.getConfig().getString("statNames.critDamage");
/*  22: 22 */     String damage = ItemLoreStats.plugin.getConfig().getString("statNames.damage");
/*  23: 23 */     String health = ItemLoreStats.plugin.getConfig().getString("statNames.health");
/*  24: 24 */     String healthRegen = ItemLoreStats.plugin.getConfig().getString("statNames.healthregen");
/*  25: 25 */     String lifeSteal = ItemLoreStats.plugin.getConfig().getString("statNames.lifesteal");
/*  26: 26 */     String reflect = ItemLoreStats.plugin.getConfig().getString("statNames.reflect");
/*  27: 27 */     String fire = ItemLoreStats.plugin.getConfig().getString("statNames.fire");
/*  28: 28 */     String ice = ItemLoreStats.plugin.getConfig().getString("statNames.ice");
/*  29: 29 */     String poison = ItemLoreStats.plugin.getConfig().getString("statNames.poison");
/*  30: 30 */     String wither = ItemLoreStats.plugin.getConfig().getString("statNames.wither");
/*  31: 31 */     String harming = ItemLoreStats.plugin.getConfig().getString("statNames.harming");
/*  32: 32 */     String blind = ItemLoreStats.plugin.getConfig().getString("statNames.blind");
/*  33: 33 */     String xpmultiplier = ItemLoreStats.plugin.getConfig().getString("statNames.xpmultiplier");
/*  34: 34 */     String movementspeed = ItemLoreStats.plugin.getConfig().getString("statNames.movementspeed");
/*  35:    */     
/*  36: 36 */     double armourModifier = this.setBonuses.checkHashMapArmour(player);
/*  37: 37 */     double dodgeModifier = this.setBonuses.checkHashMapDodge(player);
/*  38: 38 */     double blockModifier = this.setBonuses.checkHashMapBlock(player);
/*  39: 39 */     double damageModifier = this.setBonuses.checkHashMapDamage(player);
/*  40: 40 */     double critChanceModifier = this.setBonuses.checkHashMapCritChance(player);
/*  41: 41 */     double critDamageModifier = this.setBonuses.checkHashMapCritDamage(player);
/*  42: 42 */     double healthModifier = this.setBonuses.checkHashMapHealth(player);
/*  43: 43 */     double healthRegenModifier = this.setBonuses.checkHashMapHealthRegen(player);
/*  44: 44 */     double lifeStealModifier = this.setBonuses.checkHashMapLifeSteal(player);
/*  45: 45 */     double reflectModifier = this.setBonuses.checkHashMapReflect(player);
/*  46: 46 */     double fireModifier = this.setBonuses.checkHashMapFire(player);
/*  47: 47 */     double iceModifier = this.setBonuses.checkHashMapIce(player);
/*  48: 48 */     double poisonModifier = this.setBonuses.checkHashMapPoison(player);
/*  49: 49 */     double witherModifier = this.setBonuses.checkHashMapWither(player);
/*  50: 50 */     double harmingModifier = this.setBonuses.checkHashMapHarming(player);
/*  51: 51 */     double blindModifier = this.setBonuses.checkHashMapBlind(player);
/*  52: 52 */     double xpmultiplierModifier = this.setBonuses.checkHashMapXPMultiplier(player);
/*  53: 53 */     double speedModifier = this.setBonuses.checkHashMapSpeed(player);
/*  54:    */     
/*  55: 55 */     player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Stats:");
/*  56: 57 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/*  57: 58 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(armour) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getArmourGear(player) + this.gearStats.getArmourItemInHand(player) + armourModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  58:    */     } else {
/*  59: 60 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(armour) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getArmourGear(player) + armourModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(armour)) + "%");
/*  60:    */     }
/*  61: 63 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/*  62: 64 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(dodge) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getDodgeGear(player) + this.gearStats.getDodgeItemInHand(player) + dodgeModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  63:    */     } else {
/*  64: 66 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(dodge) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getDodgeGear(player) + dodgeModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(dodge)) + "%");
/*  65:    */     }
/*  66: 69 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/*  67: 70 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(block) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getBlockGear(player) + this.gearStats.getBlockItemInHand(player) + blockModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  68:    */     } else {
/*  69: 72 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(block) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getBlockGear(player) + blockModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(block)) + "%");
/*  70:    */     }
/*  71: 75 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/*  72: 76 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(damage) + ": " + ChatColor.LIGHT_PURPLE + (Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[0]) + Double.parseDouble(this.gearStats.getDamageItemInHand(player).split("-")[0]) + damageModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + " - " + ChatColor.LIGHT_PURPLE + (Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[1]) + Double.parseDouble(this.gearStats.getDamageItemInHand(player).split("-")[1]) + damageModifier));
/*  73:    */     } else {
/*  74: 78 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(damage) + ": " + ChatColor.LIGHT_PURPLE + (Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[0]) + damageModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(damage)) + " - " + ChatColor.LIGHT_PURPLE + (Double.parseDouble(this.gearStats.getDamageGear(player).split("-")[1]) + damageModifier));
/*  75:    */     }
/*  76: 81 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/*  77: 82 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(critChance) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getCritChanceGear(player) + this.gearStats.getCritChanceItemInHand(player) + critChanceModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  78:    */     } else {
/*  79: 84 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(critChance) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getCritChanceGear(player) + critChanceModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critChance)) + "%");
/*  80:    */     }
/*  81: 87 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/*  82: 88 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(critDamage) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getCritDamageGear(player) + this.gearStats.getCritDamageItemInHand(player) + critDamageModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/*  83:    */     } else {
/*  84: 90 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(critDamage) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getCritDamageGear(player) + critDamageModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(critDamage)) + "%");
/*  85:    */     }
/*  86: 93 */     player.sendMessage("    " + this.util_Colours.replaceTooltipColour(health) + ": " + ChatColor.LIGHT_PURPLE + (passHealth + healthModifier));
/*  87: 95 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType()))
/*  88:    */     {
/*  89: 96 */       if (ItemLoreStats.plugin.getConfig().getInt("baseHealthRegen") < 1) {
/*  90: 97 */         player.sendMessage("    " + this.util_Colours.replaceTooltipColour(healthRegen) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getHealthRegenGear(player) + this.gearStats.getHealthRegenItemInHand(player) + healthRegenModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/*  91:    */       } else {
/*  92: 99 */         player.sendMessage("    " + this.util_Colours.replaceTooltipColour(healthRegen) + ": " + ChatColor.LIGHT_PURPLE + (ItemLoreStats.plugin.getConfig().getInt("baseHealthRegen") + this.gearStats.getHealthRegenGear(player) + this.gearStats.getHealthRegenItemInHand(player) + healthRegenModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/*  93:    */       }
/*  94:    */     }
/*  95:102 */     else if (ItemLoreStats.plugin.getConfig().getInt("baseHealthRegen") < 1) {
/*  96:103 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(healthRegen) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getHealthRegenGear(player) + healthRegenModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/*  97:    */     } else {
/*  98:105 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(healthRegen) + ": " + ChatColor.LIGHT_PURPLE + (ItemLoreStats.plugin.getConfig().getInt("baseHealthRegen") + this.gearStats.getHealthRegenGear(player) + healthRegenModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(healthRegen)) + "%");
/*  99:    */     }
/* 100:108 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 101:109 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(lifeSteal) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getLifeStealGear(player) + this.gearStats.getLifeStealItemInHand(player) + lifeStealModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 102:    */     } else {
/* 103:111 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(lifeSteal) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getLifeStealGear(player) + lifeStealModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(lifeSteal)) + "%");
/* 104:    */     }
/* 105:114 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 106:115 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(reflect) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getReflectGear(player) + this.gearStats.getReflectItemInHand(player) + reflectModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 107:    */     } else {
/* 108:117 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(reflect) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getReflectGear(player) + reflectModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(reflect)) + "%");
/* 109:    */     }
/* 110:120 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 111:121 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(fire) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getFireGear(player) + this.gearStats.getFireItemInHand(player) + fireModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 112:    */     } else {
/* 113:123 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(fire) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getFireGear(player) + fireModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(fire)) + "%");
/* 114:    */     }
/* 115:126 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 116:127 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(ice) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getIceGear(player) + this.gearStats.getIceItemInHand(player) + iceModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 117:    */     } else {
/* 118:129 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(ice) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getIceGear(player) + iceModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(ice)) + "%");
/* 119:    */     }
/* 120:132 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 121:133 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(poison) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getPoisonGear(player) + this.gearStats.getPoisonItemInHand(player) + poisonModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 122:    */     } else {
/* 123:135 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(poison) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getPoisonGear(player) + poisonModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(poison)) + "%");
/* 124:    */     }
/* 125:138 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 126:139 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(wither) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getWitherGear(player) + this.gearStats.getWitherItemInHand(player) + witherModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 127:    */     } else {
/* 128:141 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(wither) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getWitherGear(player) + witherModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(wither)) + "%");
/* 129:    */     }
/* 130:144 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 131:145 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(harming) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getHarmingGear(player) + this.gearStats.getHarmingItemInHand(player) + harmingModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 132:    */     } else {
/* 133:147 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(harming) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getHarmingGear(player) + harmingModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(harming)) + "%");
/* 134:    */     }
/* 135:150 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 136:151 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(blind) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getBlindGear(player) + this.gearStats.getBlindItemInHand(player) + blindModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 137:    */     } else {
/* 138:153 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(blind) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getBlindGear(player) + blindModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(blind)) + "%");
/* 139:    */     }
/* 140:156 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 141:157 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(xpmultiplier) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getXPMultiplierGear(player) + this.gearStats.getXPMultiplierItemInHand(player) + xpmultiplierModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpmultiplier)) + "%");
/* 142:    */     } else {
/* 143:159 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(xpmultiplier) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getXPMultiplierGear(player) + xpmultiplierModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(xpmultiplier)) + "%");
/* 144:    */     }
/* 145:162 */     if (ItemLoreStats.plugin.isTool(player.getItemInHand().getType())) {
/* 146:163 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(movementspeed) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getMovementSpeedGear(player) + this.gearStats.getMovementSpeedItemInHand(player) + speedModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementspeed)) + "%");
/* 147:    */     } else {
/* 148:165 */       player.sendMessage("    " + this.util_Colours.replaceTooltipColour(movementspeed) + ": " + ChatColor.LIGHT_PURPLE + (this.gearStats.getMovementSpeedGear(player) + speedModifier) + this.util_Colours.replaceTooltipColour(this.util_Colours.extractTooltipColour(movementspeed)) + "%");
/* 149:    */     }
/* 150:    */   }
/* 151:    */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.CharacterSheet
 * JD-Core Version:    0.7.0.1
 */