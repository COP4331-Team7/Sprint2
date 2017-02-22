package com.team7.model.terrain;

import com.team7.model.areaEffects.DamageAreaEffect;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class Desert extends Terrain {
    public Desert() {
        setPassable(true);
        setMovementInfluence(-1);
        setAreaEffects(new ArrayList<>
                (Arrays.asList(new DamageAreaEffect())));
    }
}

