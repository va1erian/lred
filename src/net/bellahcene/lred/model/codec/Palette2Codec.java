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
package net.bellahcene.lred.model.codec;

import net.bellahcene.lred.model.Level;
import net.bellahcene.lred.model.LevelStream;
import net.bellahcene.lred.model.RowCodec;

/**
 *
 * @author valerian
 */
public class Palette2Codec implements RowCodec {


    @Override
    public Level.Row unpackRow(LevelStream stream) {
        Level.Row theRow = new Level.Row();

        Level.Tile tileA = Level.Tile.fromByte(stream.pullThreeBits());
        Level.Tile tileB = Level.Tile.fromByte(stream.pullThreeBits());
        
        for(short i = 0; i < Level.ROW_LENGTH;  i++ ) {
            boolean bit = stream.pullBit();
            theRow.setTile(i, bit ? tileB : tileA);
        }
        
        return theRow;
    }

    @Override
    public LevelStream packRow(Level.Row row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}