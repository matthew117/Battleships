/*
 * Vec2.java
 * Battleships
 *
 * Created by Matthew Bates on 18/02/18 17:33
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 18/02/18 16:52
 */

package net.matthewbates.battleships.model;

import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

//TODO: potentially create a vector pool
// Uses generics but should probably be implemented as several primitive specific classes for efficiency
// e.g. Vec2i for ints, Vec2f for floats etc.

/**
 * A simple vector class to hold two numerical components.
 *
 * @param <T> The parameterized type value allows the user to create vectors of any subclass of {@code Number}
 */
public class Vec2<T extends Number>
{
    public final T x;
    public final T y;

    public Vec2(T x, T y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the l1norm of this vector and the provided vector. The l1norm is also referred to as the manhattan
     * distance or the taxicab distance.
     *
     * @param v the other vector
     * @return the l1norm of {@code this} and the vector {@code v}
     */
    public double l1norm(Vec2<T> v)
    {
        double vx = x.doubleValue() - v.x.doubleValue();
        double vy = y.doubleValue() - v.y.doubleValue();
        return abs(vx) + abs(vy);
    }

    /**
     * Calculates the l2norm of this vector and the provided vector. The l2norm is also referred to as the distance or
     * the magnitude.
     *
     * @param v the other vector
     * @return the l2norm of {@code this} and the vector {@code v}
     */
    public double l2norm(Vec2<T> v)
    {
        double vx = x.doubleValue() - v.x.doubleValue();
        double vy = y.doubleValue() - v.y.doubleValue();
        return sqrt(vx * vx + vy * vy);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2<?> vec2 = (Vec2<?>) o;
        return Objects.equals(x, vec2.x) && Objects.equals(y, vec2.y);
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ')';
    }
}
