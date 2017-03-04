package com.team7.model.areaEffects;

import com.team7.ProbabilityGenerator;

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
