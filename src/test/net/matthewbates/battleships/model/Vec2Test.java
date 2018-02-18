/*
 * Vec2Test.java
 * Battleships
 *
 * Copyright Matthew Bates (c) 2018. All rights reserved.
 */

package net.matthewbates.battleships.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vec2Test
{

    @Test
    void l1norm()
    {
        // from origin in cardinal directions
        assertEquals(0, new Vec2<>(0, 0).l1norm(new Vec2<>(0, 0)));
        assertEquals(5, new Vec2<>(0, 0).l1norm(new Vec2<>(5, 0)));
        assertEquals(5, new Vec2<>(0, 0).l1norm(new Vec2<>(0, 5)));
        assertEquals(5, new Vec2<>(0, 0).l1norm(new Vec2<>(-5, 0)));

        //neither vector is a zero vector
        assertEquals(5, new Vec2<>(2, 3).l1norm(new Vec2<>(7, 3)));

        // diagonal
        assertNotEquals(5, new Vec2<>(0, 0).l1norm(new Vec2<>(4, 3)));
        assertEquals(7, new Vec2<>(0, 0).l1norm(new Vec2<>(4, 3)));
    }

    @Test
    void l2norm()
    {
        // from origin in cardinal directions
        assertEquals(0, new Vec2<>(0, 0).l2norm(new Vec2<>(0, 0)));
        assertEquals(5, new Vec2<>(0, 0).l2norm(new Vec2<>(5, 0)));
        assertEquals(5, new Vec2<>(0, 0).l2norm(new Vec2<>(0, 5)));
        assertEquals(5, new Vec2<>(0, 0).l2norm(new Vec2<>(-5, 0)));

        // neither vector is a zero vector
        assertEquals(5, new Vec2<>(2, 3).l2norm(new Vec2<>(7, 3)));

        // diagonal
        assertEquals(5, new Vec2<>(0, 0).l2norm(new Vec2<>(4, 3)));
    }

    @Test
    void testToString()
    {
        Vec2<Integer> v = new Vec2<>(1, 2);
        assertTrue(v.toString().equals("(1, 2)"));
    }
}