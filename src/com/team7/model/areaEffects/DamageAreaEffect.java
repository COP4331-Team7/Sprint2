package com.team7.model.areaEffects;

import com.team7.ProbabilityGenerator;

/**
 * Damages randomly
 */
public class DamageAreaEffect extends AreaEffect {
    public DamageAreaEffect() {
        setHealthEffect(ProbabilityGenerator.randomInteger(-10,-30));
        setInstantDeath(false);
    }
}
