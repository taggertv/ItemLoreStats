/*    1:     */ package com.github.supavitax.itemlorestats;
/*    2:     */ 
/*    3:     */ import org.bukkit.entity.Player;
/*    4:     */ import org.bukkit.inventory.ItemStack;
/*    5:     */ 
/*    6:     */ class ItemLoreStats$9
/*    7:     */   implements Runnable
/*    8:     */ {
/*    9:     */   ItemLoreStats$9(ItemLoreStats paramItemLoreStats, Player paramPlayer) {}
/*   10:     */   
/*   11:     */   public void run()
/*   12:     */   {
/*   13:1120 */     float compressedModifier = 0.0F;
/*   14:1122 */     if (this.this$0.isTool(this.val$playerFinal.getItemInHand().getType()))
/*   15:     */     {
/*   16:1123 */       float maxSpeed = 0.99F;
/*   17:1124 */       float speed = (float)(0.0020000000949949D * (this.this$0.gearStats.getMovementSpeedGear(this.val$playerFinal) + this.this$0.gearStats.getMovementSpeedItemInHand(this.val$playerFinal)) + compressedModifier + 0.2000000029802322D);
/*   18:1126 */       if (speed > maxSpeed) {
/*   19:1127 */         this.val$playerFinal.setWalkSpeed(maxSpeed);
/*   20:     */       } else {
/*   21:1129 */         this.val$playerFinal.setWalkSpeed(speed);
/*   22:     */       }
/*   23:1131 */       this.this$0.debugMessage(this.val$playerFinal, "Speed updated.");
/*   24:     */     }
/*   25:     */     else
/*   26:     */     {
/*   27:1133 */       float maxSpeed = 0.99F;
/*   28:1134 */       float speed = (float)(0.0020000000949949D * this.this$0.gearStats.getMovementSpeedGear(this.val$playerFinal) + compressedModifier + 0.2000000029802322D);
/*   29:1136 */       if (speed > maxSpeed) {
/*   30:1137 */         this.val$playerFinal.setWalkSpeed(maxSpeed);
/*   31:     */       } else {
/*   32:1139 */         this.val$playerFinal.setWalkSpeed(speed);
/*   33:     */       }
/*   34:     */     }
/*   35:     */   }
/*   36:     */ }


/* Location:           E:\ItemLoreStats.jar
 * Qualified Name:     com.github.supavitax.itemlorestats.ItemLoreStats.9
 * JD-Core Version:    0.7.0.1
 */