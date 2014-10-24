/*
 * Copyright (C) 2014 valerian
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package net.bellahcene.lred.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bellahcene.lred.util.Position;

/**
 *
 * @author valerian
 */
public class Level implements Iterable<Level.Row> {
    
    public static final short ROW_LENGTH = 28;
    public static final short LEVEL_SIZE = 14;
    public static final short MAX_ENEMY  = 8;
    public enum Tile {
        OPEN_SPACE,
        BRICK_WALL,
        SOLID_WALL,
        LADDER,
        ROPE,
        FAKE_FLOOR,
        GOAL_LADDER,
        GOLD_PILE;
        
        public static Tile fromByte (byte b) {
            return Tile.values()[b];
        }
    }
    
    public static class Row implements Iterable<Tile>{
        protected Tile[] elements;
        
        public Row() {
            elements = new Tile[ROW_LENGTH];
            for(int i = 0; i < elements.length; i++) {
                elements[i] = Tile.OPEN_SPACE;
            }
        }
        
        public Tile getTile(short pos) {
            if(pos < 0 || pos > ROW_LENGTH) {
                throw new IllegalArgumentException( "position must be between 0 and " + ROW_LENGTH);
            }
            
            return elements[pos];
        }
        
        public void setTile(short pos, Tile tile) {
            if(pos < 0 || pos > ROW_LENGTH) {
                throw new IllegalArgumentException( "position must be between 0 and " + ROW_LENGTH);
            }  
            
            elements[pos] = tile;
        }
        
        public void fill(Tile tile) {
            Arrays.fill(elements, tile);
        }

        @Override
        public Iterator<Tile> iterator() {
            return Arrays.asList(elements).iterator();
        }
    }
    
    private Row[] map;
    private List<Position> enemies;
    private Position       startPos;
    
    public Level() {
        map     = new Row[LEVEL_SIZE];
        enemies = new ArrayList<>(MAX_ENEMY);
    }
    
    @Override
    public Iterator<Row> iterator() {
        return Arrays.asList(map).iterator();
    }
    
    public void addEnemy(Position pos) {
        if(enemies.size() >= MAX_ENEMY) {
            throw new IllegalStateException();
        }
        
        enemies.add(pos);
    }
    
    public Iterator<Position> enemies() {
        return enemies.iterator();
    }
    
    public void setStartPosition(Position pos) {
        startPos = pos;
    }
    
    public Position getStartPosition() {
        return startPos;
    }
    
    public void setRow(int pos, Row row) {
        map[pos] = row;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < map.length; ++i) {
            for(Tile t : map[i]) {
                switch(t) {
                    case BRICK_WALL:  sb.append('X'); break;
                    case FAKE_FLOOR:  sb.append('F'); break;
                    case GOAL_LADDER: sb.append('I'); break;
                    case GOLD_PILE:   sb.append('G'); break;
                    case LADDER:      sb.append('H'); break;
                    case OPEN_SPACE:  sb.append(' '); break;
                    case ROPE:        sb.append('-'); break;
                    case SOLID_WALL:  sb.append('#'); break;
                }
            }
            sb.append('\n');
        }
        
        sb.append(enemies.size()).append(" enemies(s)\n");
        for(Position p : enemies) {
            sb.append(p.getX()).append(", ").append(p.getY()).append('\n');
        }
        
        
        
        return sb.toString();
    }
}
