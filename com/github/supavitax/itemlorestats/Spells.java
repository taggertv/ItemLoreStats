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
/* 17:13 */     TNTPrimed tnt = (TNTPrimed)player.getWorld().spawn(player.getLocation().add(0.0D, 1.0D, 0.0D), TNTPrimed.class);
/* 18:14 */     tnt.setFuseTicks(ItemLoreStats.plugin.getConfig().getInt("statNames.spells.tnt.detonationTime") * 20);
/* 19:15 */     tnt.setTicksLived(1);
/* 20:   */     
/* 21:17 */     tnt.setVelocity(player.getLocation().getDirection().multiply(ItemLoreStats.plugin.getConfig().getInt("statNames.spells.tnt.speed")));
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void castFireball(Player player)
/* 25:   */   {
/* 26:23 */     Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(), player.getLocation().getYaw(), player.getLocation().getPitch());
/* 27:   */     
/* 28:25 */     SmallFireball fireball = (SmallFireball)player.getWorld().spawn(loc, SmallFireball.class);
/* 29:26 */     fireball.setShooter(player);
/* 30:27 */     fireball.setYield(0.0F);
/* 31:28 */     fireball.setIsIncendiary(false);
/* 32:   */     
/* 33:   */ 
/* 34:   */ 
/* 35:   */ 
/* 36:33 */     fireball.setVelocity(fireball.getLocation().getDirection().multiply(ItemLoreStats.plugin.getConfig().getInt("statNames.spells.fireball.speed")));
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void castLightning(Player player)
/* 40:   */   {
/* 41:39 */     Location loc = player.getTargetBlock(null, 100).getLocation();
/* 42:40 */     loc.getWorld().strikeLightning(loc);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void castFrostbolt(Player player)
/* 46:   */   {
/* 47:45 */     Snowball snowball = (Snowball)player.getWorld().spawn(player.getLocation().add(0.0D, 1.0D, 0.0D), Snowball.class);
/* 48:46 */     snowball.setShooter(player);
/* 49:   */     
/* 50:48 */     snowball.setVelocity(player.getLocation().getDirection().multiply(ItemLoreStats.plugin.getConfig().getInt("statNames.spells.frostbolt.speed")));
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\Taggert\Downloads\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.Spells
 * JD-Core Version:    0.7.0.1
 */