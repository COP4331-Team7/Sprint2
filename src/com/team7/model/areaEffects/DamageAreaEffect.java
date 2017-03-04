package com.team7.model.areaEffects;

import com.team7.ProbabilityGenerator;

/**
 * Damages randomly
 */
public class DamageAreaEffect extends AreaEffect {
    public DamageAreaEffect() {
        setHealthEffect(ProbabilityGenerator.randomInteger(-30,-10));
        setInstantDeath(false);
        setType("DamageAreaEffect");
    }
}
