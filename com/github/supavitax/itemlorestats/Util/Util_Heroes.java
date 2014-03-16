/*  1:   */ package com.github.supavitax.itemlorestats.Util;
/*  2:   */ 
/*  3:   */ import com.github.supavitax.itemlorestats.ItemLoreStats;
/*  4:   */ import com.herocraftonline.heroes.Heroes;
/*  5:   */ import com.herocraftonline.heroes.characters.CharacterManager;
/*  6:   */ import com.herocraftonline.heroes.characters.Hero;
/*  7:   */ import com.herocraftonline.heroes.characters.classes.HeroClass;
/*  8:   */ import org.bukkit.Bukkit;
/*  9:   */ import org.bukkit.entity.Player;
/* 10:   */ 
/* 11:   */ public class Util_Heroes
/* 12:   */ {
/* 13:   */   ItemLoreStats main;
/* 14:   */   
/* 15:   */   public Util_Heroes(ItemLoreStats instance)
/* 16:   */   {
/* 17:17 */     this.main = instance;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public int getHeroBaseHealth(Player player)
/* 21:   */   {
/* 22:22 */     Hero hero = Heroes.getInstance().getCharacterManager().getHero(Bukkit.getPlayer(player.getName()));
/* 23:   */     
/* 24:24 */     HeroClass heroClass = hero.getHeroClass();
/* 25:25 */     int baseMax = heroClass.getBaseMaxHealth();
/* 26:   */     
/* 27:27 */     return baseMax;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public double getHeroHealthPerLevel(Player player)
/* 31:   */   {
/* 32:32 */     Hero hero = Heroes.getInstance().getCharacterManager().getHero(Bukkit.getPlayer(player.getName()));
/* 33:   */     
/* 34:34 */     HeroClass heroClass = hero.getHeroClass();
/* 35:35 */     double healthperLevel = heroClass.getMaxHealthPerLevel();
/* 36:   */     
/* 37:37 */     return healthperLevel;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public int getHeroLevel(Player player)
/* 41:   */   {
/* 42:42 */     Hero hero = Heroes.getInstance().getCharacterManager().getHero(Bukkit.getPlayer(player.getName()));
/* 43:   */     
/* 44:44 */     HeroClass heroClass = hero.getHeroClass();
/* 45:45 */     int level = hero.getLevel(heroClass);
/* 46:   */     
/* 47:47 */     return level;
/* 48:   */   }
/* 49:   */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.Util.Util_Heroes
 * JD-Core Version:    0.7.0.1
 */