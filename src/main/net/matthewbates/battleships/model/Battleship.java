/*
 * Battleship.java
 * Battleships
 *
 * Created by Matthew Bates on 18/02/18 17:33
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 18/02/18 17:28
 */

package net.matthewbates.battleships.model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;

/** The {@code Battleship} class represents a battleship that can be placed on the game board. */
// TODO: potentially refactor to be immutable
public class Battleship
{

    /** Stores the coordinates of the back of the ship. */
    private final Vec2<Integer> sternPos;
    /** Stores the coordinates of the front of the ship. */
    private final Vec2<Integer> bowPos;
    /**
     * The type/size of the ship. Currently available typed are {@code Destroyer} of size 4 and {@code Battleship} of
     * size 5.
     */
    private final Type type;
    /** A mapping of the status of each ship tile; if {@code true}, that tile of the ship has been damaged. */
    private final Map<Vec2<Integer>, Boolean> damagedAreas;
    /*
    A Set is used instead of a List here as we don't actually care about the ordering of these coordinates, just
    whether a certain tile is part of the ship.
    */
    /** Represents the coordinates of the set of tiles that the ship covers. */
    private final Set<Vec2<Integer>> hullCoordinates;

    /**
     * Constructs a ship from two coordinates and a type.
     *
     * @param sternPos a {@code Vec2} coordinate for the back of the ship
     * @param bowPos   a {@code Vec2} coordinate for the front of the ship
     * @param type     The {@code Battleship.Type} representing the type of ship to be constructed
     * @throws IllegalStateException if the size of the ship does not match the given type
     */
    public Battleship(@NotNull Vec2<Integer> sternPos, @NotNull Vec2<Integer> bowPos, @NotNull Type type)
    {
        if ((int) sternPos.l1norm(bowPos) != type.getSize() - 1)
            throw new IllegalStateException("Battleship size does not reflect its given type");
        this.sternPos = sternPos;
        this.bowPos = bowPos;
        this.type = type;
        hullCoordinates = generateHullCoordinates();
        damagedAreas = new HashMap<>();
        hullCoordinates.forEach(k -> damagedAreas.put(k, false));
    }

    public Battleship(@NotNull Battleship b)
    {
        sternPos = b.sternPos;
        bowPos = b.bowPos;
        type = b.type;
        hullCoordinates = b.hullCoordinates;
        damagedAreas = b.damagedAreas;
    }

    public Vec2<Integer> getSternPos()
    {
        return sternPos;
    }

    public Vec2<Integer> getBowPos()
    {
        return bowPos;
    }

    public Type getType()
    {
        return type;
    }

    public Map<Vec2<Integer>, Boolean> getDamagedAreas()
    {
        return damagedAreas;
    }

    /**
     * Generates a list of coordinates covered by this Battleship.
     *
     * @return A list of coordinates covered by this Battleship.
     */
    @Contract(pure = true)
    private Set<Vec2<Integer>> generateHullCoordinates()
    {
        Set<Vec2<Integer>> xs = new HashSet<>();
        IntStream.rangeClosed(min(sternPos.x, bowPos.x), max(sternPos.x, bowPos.x))
                .forEach((int i) -> IntStream.rangeClosed(min(sternPos.y, bowPos.y), max(sternPos.y, bowPos.y))
                        .forEach((int j) -> xs.add(new Vec2<>(i, j))));
        return xs;
    }

    public Set<Vec2<Integer>> getHullCoordinates()
    {
        return hullCoordinates;
    }

    public boolean attemptHit(Vec2<Integer> coord)
    {
        if (hullCoordinates.contains(coord))
        {
            damagedAreas.put(coord, true);
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(sternPos, bowPos, type, damagedAreas);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battleship that = (Battleship) o;
        return Objects.equals(sternPos, that.sternPos) &&
                Objects.equals(bowPos, that.bowPos) &&
                type == that.type &&
                Objects.equals(damagedAreas, that.damagedAreas);
    }

    /**
     * The {@code Battleship.Type} enum represents the type of the ship.
     * This is used to validate the coordinates and size of the ship by could also be used to render different types of
     * ships using different characters/colours in a terminal, sprites in a 2D window or models in a 3D window.
     */
    public enum Type
    {
        DESTROYER(4),
        BATTLESHIP(5);

        private final int size;

        Type(int size)
        {
            this.size = size;
        }

        public int getSize()
        {
            return size;
        }
    }
}
