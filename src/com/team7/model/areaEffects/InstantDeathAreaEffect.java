package com.team7.model.areaEffects;

/**
 * Instant Death
 */
public class InstantDeathAreaEffect extends AreaEffect {
    public InstantDeathAreaEffect() {
        setHealthEffect(0);
        setInstantDeath(true);
        setType("InstantDeathAreaEffect");
    }
}
