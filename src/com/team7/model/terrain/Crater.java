package com.team7.model.terrain;

import com.team7.model.areaEffects.DamageAreaEffect;
import com.team7.model.areaEffects.HealAreaEffect;
import com.team7.model.areaEffects.InstantDeathAreaEffect;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class Crater extends Terrain {
    public Crater() {
        setPassable(true);
        setMovementInfluence(-2);
        setAreaEffects(new ArrayList<>
                (Arrays.asList(new DamageAreaEffect(),
                        new HealAreaEffect(),
                        new InstantDeathAreaEffect())));
    }
}
