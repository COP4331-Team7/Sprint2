package com.team7.model.areaEffects;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Damages randomly
 */
public class DamageAreaEffect extends AreaEffect {
    public DamageAreaEffect() {
        setHealthEffect(ThreadLocalRandom.current().nextInt(-30, -10));
        setInstantDeath(false);
        setType("DamageAreaEffect");
    }
}
