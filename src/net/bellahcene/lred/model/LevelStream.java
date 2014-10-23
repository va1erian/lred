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

/**
 *
 * @author valerian
 */
public class LevelStream {
    final byte[] buf;
    final int startOffset;
    int pos;
    
    public LevelStream(byte[] data, int offset) {
        this.buf = data;
        this.startOffset = offset;
        this.pos = startOffset;
    }
    
    public boolean pullBit() {
        int bytePos = pos / 8;
        int bitPos  = pos % 8;
        byte value = buf[bytePos];
        
        value >>>= (7 - bitPos);
        value &= ~0xFE;
        
        pos++;
        
        return value == 1;
    }
    
    public byte pullTwoBits() {
        boolean firstBit  = pullBit();
        boolean secondBit = pullBit();
        
        byte value = (byte)(firstBit ? 1 : 0);
        value <<= 1;
        return (byte) ( value | (secondBit ? 1 : 0) );
        
    }
    
    public byte pullThreeBits() {
        byte    higherTwo = pullTwoBits();
        boolean lastBit   = pullBit();
        
        higherTwo <<= 1;
        return (byte) (higherTwo | (lastBit ? 1 : 0) );
        
    }
    
    public byte pullFiveBits() {
        byte higherThree = pullThreeBits();
        byte lowerTwo    = pullTwoBits();
        
        higherThree <<= 2;
        return (byte) (higherThree | lowerTwo);
    }
    
    
    public void reset() {
        pos = startOffset;
    }
}
