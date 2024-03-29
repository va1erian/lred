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
package net.bellahcene.lred.view;

import java.awt.Dimension;
import java.util.List;
import net.bellahcene.lred.model.Level;
import net.bellahcene.lred.view.components.DefaultResources;
import net.bellahcene.lred.view.components.LevelView;
import net.bellahcene.lred.view.components.LevelViewResources;

/**
 *
 * @author valerian
 */
public class EditorFrame extends javax.swing.JFrame {

    private final LevelView view;
    private final LevelViewResources res;
    private List<Level> levels;
    
    int currentLevelIndex = 0;
    
    /**
     * Creates new form EditorPanel
     */
    public EditorFrame() {
        initComponents();
        
        res = new DefaultResources(new Dimension(24,24));
        view = new LevelView(res);
        
        viewScrollPanel.setViewportView(view);
    }
    
    public void setLevels(List<Level> levels) {
        this.levels = levels;
        view.setModel(levels.get(currentLevelIndex));
    }
    
    private void goToPreviousLevel() {
        if(currentLevelIndex  > 0) {
            currentLevelIndex--;
            view.setModel(levels.get(currentLevelIndex));
        }
    }
    
    private void gotoNextLevel() {
        if(currentLevelIndex < 49) {
            currentLevelIndex++;
            view.setModel(levels.get(currentLevelIndex));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewScrollPanel = new javax.swing.JScrollPane();
        toolbar = new javax.swing.JToolBar();
        prevTbBtn = new javax.swing.JButton();
        nextTbBtn = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        quitMenuItem = new javax.swing.JMenuItem();

        viewScrollPanel.setMinimumSize(new java.awt.Dimension(200, 100));
        viewScrollPanel.setName("viewScrollPanel"); // NOI18N
        getContentPane().add(viewScrollPanel, java.awt.BorderLayout.CENTER);

        toolbar.setRollover(true);

        prevTbBtn.setText("Previous");
        prevTbBtn.setFocusable(false);
        prevTbBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        prevTbBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        prevTbBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevTbBtnActionPerformed(evt);
            }
        });
        toolbar.add(prevTbBtn);

        nextTbBtn.setText("Next");
        nextTbBtn.setFocusable(false);
        nextTbBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nextTbBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nextTbBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextTbBtnActionPerformed(evt);
            }
        });
        toolbar.add(nextTbBtn);

        getContentPane().add(toolbar, java.awt.BorderLayout.PAGE_START);

        fileMenu.setText("File");

        quitMenuItem.setText("jMenuItem1");
        quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(quitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void prevTbBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevTbBtnActionPerformed
        goToPreviousLevel();
    }//GEN-LAST:event_prevTbBtnActionPerformed

    private void nextTbBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextTbBtnActionPerformed
        gotoNextLevel();
    }//GEN-LAST:event_nextTbBtnActionPerformed

    private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quitMenuItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton nextTbBtn;
    private javax.swing.JButton prevTbBtn;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JScrollPane viewScrollPanel;
    // End of variables declaration//GEN-END:variables
}
