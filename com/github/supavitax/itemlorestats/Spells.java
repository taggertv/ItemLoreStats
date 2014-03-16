/*  1:   */ package com.github.supavitax.itemlorestats;
/*  2:   */ 
/*  3:   */ import org.bukkit.Location;
/*  4:   */ import org.bukkit.World;
/*  5:   */ import org.bukkit.block.Block;
/*  6:   */ import org.bukkit.configuration.file.FileConfiguration;
/*  7:   */ import org.bukkit.entity.Player;
/*  8:   */ import org.bukkit.entity.SmallFireball;
/*  9:   */ import org.bukkit.entity.Snowball;
/* 10:   */ import org.bukkit.entity.TNTPrimed;
/* 11:   */ import org.bukkit.util.Vector;
/* 12:   */ 
/* 13:   */ public class Spells
/* 14:   */ {
/* 15:   */   public void castTnT(Player player)
/* 16:   */   {
/* 17:17 */     TNTPrimed tnt = (TNTPrimed)player.getWorld().spawn(player.getLocation().add(0.0D, 1.0D, 0.0D), TNTPrimed.class);
/* 18:18 */     tnt.setFuseTicks(ItemLoreStats.plugin.getConfig().getInt("statNames.spells.tnt.detonationTime") * 20);
/* 19:19 */     tnt.setTicksLived(1);
/* 20:   */     
/* 21:21 */     tnt.setVelocity(player.getLocation().getDirection().multiply(ItemLoreStats.plugin.getConfig().getInt("statNames.spells.tnt.speed")));
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void castFireball(Player player)
/* 25:   */   {
/* 26:26 */     Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(), player.getLocation().getYaw(), player.getLocation().getPitch());
/* 27:   */     
/* 28:28 */     SmallFireball fireball = (SmallFireball)player.getWorld().spawn(loc, SmallFireball.class);
/* 29:29 */     fireball.setShooter(player);
/* 30:30 */     fireball.setYield(0.0F);
/* 31:31 */     fireball.setIsIncendiary(false);
/* 32:   */     
/* 33:33 */     fireball.setVelocity(fireball.getLocation().getDirection().multiply(ItemLoreStats.plugin.getConfig().getInt("statNames.spells.fireball.speed")));
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void castLightning(Player player)
/* 37:   */   {
/* 38:38 */     Location loc = player.getTargetBlock(null, 100).getLocation();
/* 39:39 */     loc.getWorld().strikeLightning(loc);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void castFrostbolt(Player player)
/* 43:   */   {
/* 44:44 */     Snowball snowball = (Snowball)player.getWorld().spawn(player.getLocation().add(0.0D, 1.0D, 0.0D), Snowball.class);
/* 45:45 */     snowball.setShooter(player);
/* 46:   */     
/* 47:47 */     snowball.setVelocity(player.getLocation().getDirection().multiply(ItemLoreStats.plugin.getConfig().getInt("statNames.spells.frostbolt.speed")));
/* 48:   */   }
/* 49:   */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.Spells
 * JD-Core Version:    0.7.0.1
 */