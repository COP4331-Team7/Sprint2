package com.team7.model.areaEffects;

/**
 * 3 required AreaEffects:
 *  Heal health (HealAreaEffect)
 *  Take health (DamageAreaEffect
 *  InstantDeath (InstantDeathAreaEffect)
 */
public abstract class AreaEffect {
    private boolean isInstantDeath;
    private int healthEffect; // -100 to 100
    private String type;

    public boolean isInstantDeath() {
        return isInstantDeath;
    }

    public void setInstantDeath(boolean instantDeath) {
        isInstantDeath = instantDeath;
    }

    public int getHealthEffect() {
        return healthEffect;
    }

    public void setHealthEffect(int healthEffect) {
        this.healthEffect = healthEffect;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
