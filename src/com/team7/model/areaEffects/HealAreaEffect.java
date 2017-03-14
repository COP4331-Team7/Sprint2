package com.team7.model.areaEffects;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Heals randomly
 */
public class HealAreaEffect extends AreaEffect {
    public HealAreaEffect() {
        setHealthEffect(ThreadLocalRandom.current().nextInt(10, 30));
        setInstantDeath(false);
        setType("HealAreaEffect");
    }
}
