/*
 * Main.java
 * Battleships
 *
 * Copyright Matthew Bates (c) 2018. All rights reserved.
 */

package net.matthewbates.battleships;

import net.matthewbates.battleships.model.GameBoard;

import java.util.HashSet;

class Main
{

    public static void main(String[] args)
    {
        GameBoard board = new GameBoard(10, 10, new HashSet<>());
    }
}
