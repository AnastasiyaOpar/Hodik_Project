/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.intsys16.editorwindow;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.UndoRedo;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.Lookups;

/**
 * Top component which displays something.
 */
//@ConvertAsProperties(
//        dtd = "-//org.intsys16.editorwindow//GraphicEditor//EN",
//        autostore = false
//)
//@TopComponent.Description(
//        preferredID = "GraphicEditorTopComponent",
//        //iconBase="SET/PATH/TO/ICON/HERE", 
//        persistenceType = TopComponent.PERSISTENCE_ALWAYS
//)
//@TopComponent.Registration(mode = "editor", openAtStartup = false)
//@ActionID(category = "Window", id = "org.intsys16.editorwindow.GraphicEditorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_GraphicEditorAction",
//        preferredID = "GraphicEditorTopComponent"
//) 
@MultiViewElement.Registration(
        displayName = "#LBL_GraphicDisplayName",
        //iconBase = "nl/cloudfarming/client/farm/model/house.png",
        mimeType = "application/multieditor",
        persistenceType = TopComponent.PERSISTENCE_NEVER,
        preferredID = "GraphicView",
        position = 200)
@Messages({
    "CTL_GraphicEditorAction=Graphic Editor",
    "CTL_GraphicEditorTopComponent=Graphic Editor Window",
    "HINT_GraphicEditorTopComponent=This is a GraphicEditor window",
    "LBL_GraphicDisplayName=Graphic view"
})
public final class GraphicEditorTopComponent extends TopComponent implements MultiViewElement {

    private MultiViewElementCallback callback = null;
    private JToolBar toolbar = new JToolBar();
    private TopComponent multiPanel;
    private static JFXPanel fxPanel;
    //private ProgramNode progNode;
    private DnD dragNDrop = new DnD();
    private Lookup lookup;
    
    public GraphicEditorTopComponent() {
        java.util.logging.Logger.getLogger(getClass().getName()).log(Level.WARNING, 
                "Empty constructor for {0} was called", getClass().getName());
    }
    public GraphicEditorTopComponent(Lookup lookup) {
        this.lookup = lookup;
        initComponents();
        setName(Bundle.CTL_GraphicEditorTopComponent());
        setToolTipText(Bundle.HINT_GraphicEditorTopComponent());
        //associateLookup(progNode.getLookup());
        setLayout(new BorderLayout());
        init();
    }
    public void init() {
        fxPanel = new JFXPanel();
        add(fxPanel, BorderLayout.NORTH);   
        Platform.setImplicitExit(false);
        Platform.runLater(() -> createScene());      
    }
    
    private void createScene() {        
        fxPanel.setScene(new Scene(dragNDrop));
 
    }
    public ArrayList<String> getCommandSequence() {
        return dragNDrop.getSequence();
    }
    public void setMultiPanel(TopComponent multiPanel) {
        this.multiPanel = multiPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        //callback.updateTitle(Bundle.CTL_GraphicEditorTopComponent());
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    
    @Override
    public JComponent getVisualRepresentation() {
        return this;
    }

    @Override
    public JComponent getToolbarRepresentation() {
        return toolbar;
    }

    @Override
    public Action[] getActions() {
        if(callback != null) {
            return callback.createDefaultActions();
        } else {
            return new Action[]{};
        }
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    @Override
    public void componentShowing() {
        
    }

    @Override
    public void componentHidden() {
        //
    }

    @Override
    public void componentActivated() {
        //callback.updateTitle(Bundle.CTL_GraphicEditorTopComponent());
    }

    @Override
    public void componentDeactivated() {
        //
    }

    @Override
    public UndoRedo getUndoRedo() {
        return UndoRedo.NONE;
    }

    @Override
    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback;
    }

    @Override
    public CloseOperationState canCloseElement() {
        return CloseOperationState.STATE_OK;
    }
    
}
