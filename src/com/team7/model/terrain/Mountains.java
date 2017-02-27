package com.team7.model.terrain;

import com.team7.model.areaEffects.DamageAreaEffect;
import com.team7.model.areaEffects.HealAreaEffect;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Impassable by starting Units, Explorer & Colonist
 */
public class Mountains extends Terrain {
    public Mountains() {
        setPassable(false);
        setMovementInfluence(0);
        setAreaEffects(new ArrayList<>
                (Arrays.asList(new HealAreaEffect(),
                        new DamageAreaEffect())));
    }
}
