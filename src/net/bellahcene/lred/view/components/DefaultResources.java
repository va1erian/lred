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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author valerian
 */
public class DefaultResources implements LevelViewResources {

    private final Dimension dim;
    
    public DefaultResources(Dimension d) {
        dim = d;
    }
    
    @Override
    public Dimension dimension() {
        return dim;
    }

    @Override
    public Image openSpace() {
        return makeRect(Color.BLACK);
    }

    @Override
    public Image brickWall() {
        return makeRect(Color.RED);
    }

    @Override
    public Image solidWall() {
        return makeRect(Color.DARK_GRAY);
    }

    @Override
    public Image ladder() {
        return makeRect(Color.MAGENTA);
    }

    @Override
    public Image rope() {
        return makeRect(Color.ORANGE);
    }

    @Override
    public Image fakeFloor() {
        return makeRect(Color.LIGHT_GRAY);
    }

    @Override
    public Image goalLadder() {
        return makeRect(Color.WHITE);
    }

    @Override
    public Image goldPile() {
        return makeRect(Color.YELLOW);
    }

    @Override
    public Image enemy() {
        return makeRect(Color.PINK);
    }

    @Override
    public Image player() {
        return makeRect(Color.CYAN);
    }
    
    
    private Image makeRect(Color c) {
        BufferedImage img = new BufferedImage(dim.width, dim.height, BufferedImage.OPAQUE);
        Graphics g = img.getGraphics();
        
        g.setColor(c);
        g.fill3DRect(0, 0, dim.width, dim.height, true);
        
        return img;
    }
}
