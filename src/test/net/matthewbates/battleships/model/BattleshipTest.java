/*
 * BattleshipTest.java
 * Battleships
 *
 * Created by Matthew Bates on 18/02/18 17:33
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 18/02/18 16:55
 */

package net.matthewbates.battleships.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipTest
{
    @Test
    void constructor()
    {
        assertThrows(IllegalStateException.class, () ->
                             new Battleship(new Vec2<>(0, 0), new Vec2<>(0, 0), Battleship.Type.DESTROYER),
                     "Battleship size does not reflect its given type");
        assertThrows(IllegalStateException.class, () ->
                             new Battleship(new Vec2<>(0, 0), new Vec2<>(3, 0), Battleship.Type.BATTLESHIP),
                     "Battleship size does not reflect its given type");
        assertThrows(IllegalStateException.class, () ->
                             new Battleship(new Vec2<>(0, 0), new Vec2<>(4, 0), Battleship.Type.DESTROYER),
                     "Battleship size does not reflect its given type");

        Battleship destroyer = new Battleship(new Vec2<>(0, 0), new Vec2<>(3, 0), Battleship.Type.DESTROYER);
        Battleship battleship = new Battleship(new Vec2<>(0, 0), new Vec2<>(4, 0), Battleship.Type.BATTLESHIP);
    }

    @Test
    void getHullCoordinates()
    {
        // north heading
        Battleship b = new Battleship(new Vec2<>(1, 4), new Vec2<>(1, 1), Battleship.Type.DESTROYER);
        Set<Vec2<Integer>> xs = new HashSet<>(Arrays.asList(new Vec2<>(1, 4), new Vec2<>(1, 3),
                                                            new Vec2<>(1, 2), new Vec2<>(1, 1)));
        assertEquals(xs, b.getHullCoordinates());

        // east heading
        b = new Battleship(new Vec2<>(1, 1), new Vec2<>(4, 1), Battleship.Type.DESTROYER);
        xs = new HashSet<>(Arrays.asList(new Vec2<>(1, 1), new Vec2<>(2, 1), new Vec2<>(3, 1),
                                         new Vec2<>(4, 1)));
        assertEquals(xs, b.getHullCoordinates());

        // south heading
        b = new Battleship(new Vec2<>(1, 1), new Vec2<>(1, 4), Battleship.Type.DESTROYER);
        xs = new HashSet<>(Arrays.asList(new Vec2<>(1, 1), new Vec2<>(1, 2), new Vec2<>(1, 3),
                                         new Vec2<>(1, 4)));
        assertEquals(xs, b.getHullCoordinates());

        // west heading
        b = new Battleship(new Vec2<>(4, 1), new Vec2<>(1, 1), Battleship.Type.DESTROYER);
        xs = new HashSet<>(Arrays.asList(new Vec2<>(4, 1), new Vec2<>(3, 1), new Vec2<>(2, 1),
                                         new Vec2<>(1, 1)));
        assertEquals(xs, b.getHullCoordinates());

        // check that list calculates all 5 values when the ship is a battleship
        b = new Battleship(new Vec2<>(1, 1), new Vec2<>(5, 1), Battleship.Type.BATTLESHIP);
        xs = new HashSet<>(Arrays.asList(new Vec2<>(1, 1), new Vec2<>(2, 1), new Vec2<>(3, 1),
                                         new Vec2<>(4, 1), new Vec2<>(5, 1)));
        assertEquals(xs, b.getHullCoordinates());
    }

    @Test
    void attemptHit()
    {
        Battleship b = new Battleship(new Vec2<>(1, 1), new Vec2<>(4, 1), Battleship.Type.DESTROYER);
        Battleship a = new Battleship(b);
        assertFalse(b.attemptHit(new Vec2<>(1, 2))); // should be a miss
        assertEquals(a, b); // the object is unchanged
        assertFalse(b.getDamagedAreas().containsValue(true)); // should be undamaged

        b = new Battleship(new Vec2<>(1, 1), new Vec2<>(4, 1), Battleship.Type.DESTROYER);
        assertTrue(b.attemptHit(new Vec2<>(1, 1))); // should return true on hit
        assertTrue(b.getDamagedAreas().get(new Vec2<>(1, 1))); // should be damaged at (1, 1)
        assertFalse(b.getDamagedAreas().get(new Vec2<>(2, 1))); // should be undamaged elsewhere
        assertFalse(b.getDamagedAreas().get(new Vec2<>(3, 1)));
        assertFalse(b.getDamagedAreas().get(new Vec2<>(4, 1)));

        b = new Battleship(new Vec2<>(1, 1), new Vec2<>(4, 1), Battleship.Type.DESTROYER);
        assertTrue(b.attemptHit(new Vec2<>(1, 1))); // should be a hit
        assertTrue(b.getDamagedAreas().get(new Vec2<>(1, 1))); // should be damaged at (1, 1)
        assertFalse(b.attemptHit(new Vec2<>(1, 2))); // should be a miss
    }
}