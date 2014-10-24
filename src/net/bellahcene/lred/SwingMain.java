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
package net.bellahcene.lred;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import net.bellahcene.lred.model.Level;
import net.bellahcene.lred.model.LevelStream;
import net.bellahcene.lred.model.LevelUnpacker;
import net.bellahcene.lred.view.EditorPanel;

/**
 *
 * @author valerian
 */
public class SwingMain {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.err.println("Invalid arguments");
            System.exit(2);
        }
        
            try {
            // Set cross-platform Java L&F (also called "Metal")
                UIManager.setLookAndFeel(
              UIManager.getSystemLookAndFeelClassName());
            } 
            catch (ClassNotFoundException | InstantiationException 
                    | IllegalAccessException | UnsupportedLookAndFeelException e) {
                System.err.println("Failed to open look and feel.");
            }

        
        try(FileInputStream fis = new FileInputStream(args[1])) {
           
            LevelUnpacker unpacker = new LevelUnpacker();
            
            byte[] levelData = new byte[16 * 1024];
            fis.skip(0x02A88);
            fis.read(levelData);
            
            List<Level> levels = unpacker.unpackLevels(new LevelStream(levelData, 0));
            openView(levels);
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to open file");
        } catch (IOException ex) {
            System.err.println("Failed to read file");
        } 
    }
    
    
    static void openView(List<Level> levels) {
        JFrame frm = new JFrame("LRed");
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        EditorPanel editorPanel = new EditorPanel();
        editorPanel.setLevels(levels);
        
        frm.setContentPane(editorPanel);
        frm.pack();
        frm.setVisible(true);
    }
}
