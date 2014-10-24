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
package net.bellahcene.lred.view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;
import net.bellahcene.lred.model.Level;

/**
 *
 * @author valerian
 */
public class LevelView extends JComponent {
    
    private Level model;
    private final LevelViewResources resources;
    
    public LevelView(LevelViewResources res) {
        resources = res;
    }
    
    public void setModel(Level level) {
        model = level;
        this.repaint();
    }
    
    public Level getModel() {
        return model;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        int drawX, drawY = 0;
        Image tile = resources.openSpace();
        
        for(Level.Row r : model) {
            drawX = 0;
            for(Level.Tile t : r) {
                switch(t) {
                    case BRICK_WALL:  tile = resources.brickWall(); break;
                    case FAKE_FLOOR:  tile = resources.fakeFloor(); break;
                    case GOAL_LADDER: tile = resources.goalLadder(); break;
                    case GOLD_PILE:   tile = resources.goldPile(); break;
                    case LADDER:      tile = resources.ladder(); break;
                    case OPEN_SPACE:  tile = resources.openSpace(); break;
                    case ROPE:        tile = resources.rope(); break;
                    case SOLID_WALL:  tile = resources.solidWall(); break;
                }
                
                g.drawImage(tile, drawX, drawY, this);
                drawX += resources.dimension().width;
            }
            drawY += resources.dimension().height;
        }
        
    }
    
    @Override
    public Dimension getMinimumSize() {
        return new Dimension (
                resources.dimension().width * Level.ROW_LENGTH, 
                resources.dimension().height * Level.LEVEL_SIZE);  
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension (
                resources.dimension().width * Level.ROW_LENGTH, 
                resources.dimension().height * Level.LEVEL_SIZE);
    }
    
    
    
}
