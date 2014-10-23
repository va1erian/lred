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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author valerian
 */
public class LevelStreamTest {
    
    
    static final byte[] LEVEL_STREAM_DATA = {
            (byte)0b00110001,
            (byte)0b01100010,
            (byte)0b11000100
        };
    
    
    /**
     * Test of pullBit method, of class LevelStream.
     */
    @Test
    public void testPullBit() {
        LevelStream instance = new LevelStream(LEVEL_STREAM_DATA, 0);
        
        assertFalse(instance.pullBit());
        assertFalse(instance.pullBit());
        assertTrue(instance.pullBit());
    }

    /**
     * Test of pullTwoBits method, of class LevelStream.
     */
    @Test
    public void testPullTwoBits() {
        LevelStream instance = new LevelStream(LEVEL_STREAM_DATA, 0);
        
        assertEquals(0, instance.pullTwoBits());
        assertEquals(0b11, instance.pullTwoBits());
        assertEquals(0b00, instance.pullTwoBits());
        
    }

    /**
     * Test of pullThreeBits method, of class LevelStream.
     */
    @Test
    public void testPullThreeBits() {
        LevelStream instance = new LevelStream(LEVEL_STREAM_DATA, 0);
        
        assertEquals(0b001, instance.pullThreeBits());
        assertEquals(0b100, instance.pullThreeBits());
        assertEquals(0b010, instance.pullThreeBits());
    }

    /**
     * Test of pullFiveBits method, of class LevelStream.
     */
    @Test
    public void testPullFiveBits() {
        LevelStream instance = new LevelStream(LEVEL_STREAM_DATA, 0);
        
        assertEquals(0b00110, instance.pullFiveBits());
        assertEquals(0b00101, instance.pullFiveBits());
    }

    @Test
    public void testOffset() {
        LevelStream instance = new LevelStream(LEVEL_STREAM_DATA, 4);
        
        assertFalse(instance.pullBit());
        assertEquals(0b001, instance.pullThreeBits());
        
    }
    
}
