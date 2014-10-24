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
import java.util.List;
import net.bellahcene.lred.model.codec.EmptyRowExceptCodec;
import net.bellahcene.lred.model.codec.Palette2Codec;
import net.bellahcene.lred.model.codec.Palette4Codec;
import net.bellahcene.lred.model.codec.RLECodec;
import net.bellahcene.lred.model.codec.UncompressedCodec;
import net.bellahcene.lred.util.Position;

/**
 *
 * @author valerian
 */
public class LevelUnpacker {
    
    RLECodec rleCodec = new RLECodec();
    UncompressedCodec uncompressedCodec = new UncompressedCodec();
    Palette2Codec palette2Codec = new Palette2Codec();
    Palette4Codec palette4Codec = new Palette4Codec();
    EmptyRowExceptCodec emptyRowExceptCodec = new EmptyRowExceptCodec();
    
    
    public List<Level> unpackLevels(LevelStream stream) {
        ArrayList<Level> levels = new ArrayList<>();
        
        for(int j = 0; j < 50; j++) {
            System.out.println("Level " + j);
            byte enemyCount;
            Level aLevel = new Level();

            for(int i = 0; i < Level.LEVEL_SIZE; i++) {
                byte rowType = stream.pullTwoBits();
                switch(rowType) {
                    case 0: 
                        aLevel.setRow(i, rleCodec.unpackRow(stream));
                        break;
                    case 1:
                        aLevel.setRow(i, palette2Codec.unpackRow(stream));
                        break;
                    case 2:
                        aLevel.setRow(i, emptyRowExceptCodec.unpackRow(stream));
                        break;
                    case 3:
                        if(stream.pullBit()){
                            aLevel.setRow(i, uncompressedCodec.unpackRow(stream));
                        } else {
                            aLevel.setRow(i, palette4Codec.unpackRow(stream));
                        }
                        break;
                }
            }

            enemyCount = stream.pullThreeBits();

            for(int i = 0; i < enemyCount; ++i) {
                Position enemyPos = new Position();
                enemyPos.setX( stream.pullFiveBits());
                enemyPos.setY( stream.pullFiveBits());
                aLevel.addEnemy(enemyPos);
            }

            Position startPos = new Position();
            startPos.setX(stream.pullFiveBits());
            startPos.setY(stream.pullFiveBits());
            aLevel.setStartPosition(startPos);

            System.out.println(aLevel);
            levels.add(aLevel);
        }
        return levels;
    }
    
}
