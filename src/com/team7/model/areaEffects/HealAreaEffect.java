package com.team7.model.areaEffects;

import com.team7.ProbabilityGenerator;

/**
 * Heals randomly
 */
public class HealAreaEffect extends AreaEffect {
    public HealAreaEffect() {
        setHealthEffect(ProbabilityGenerator.randomInteger(10,30));
        setInstantDeath(false);
    }
}
