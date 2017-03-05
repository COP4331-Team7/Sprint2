package com.team7.model.terrain;

import com.team7.model.areaEffects.DamageAreaEffect;
import com.team7.model.areaEffects.HealAreaEffect;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The most common Terrain in the game
 */
public class Flatland extends Terrain {
    public Flatland() {
        setTerrainType("Flatland");
        setPassable(true);
        setMovementInfluence(0);
        setAreaEffects(new ArrayList<>
                (Arrays.asList(new HealAreaEffect(),
                        new DamageAreaEffect())));
    }
}
