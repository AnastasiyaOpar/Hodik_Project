/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.intsys16.startwindow;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.intsys16.integrator.api.Integrator;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.LifecycleManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.intsys16.startwindow//Start//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "StartTopComponent",
        iconBase = "org/intsys16/startwindow/hodik24.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "org.intsys16.startwindow.StartTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_StartAction",
        preferredID = "StartTopComponent"
)
@Messages({
    "CTL_StartAction=Start Window",
    "CTL_StartTopComponent=Start Window",
    "HINT_StartTopComponent=This is a Start window",
    /* for controller class */
    "CTL_WelcomeText=Welcome to the Hodik IDE !",
    "CTL_NoSessionsMsg=No saved session yet",
    "CTL_OpenSessionMsg=Open saved session:",
    "CTL_CreationHint=After the creation you should choose a planet where to start.",
    "CTL_NoProgramsForMsg=No programs available for",
    "CTL_OpenProgramsForMsg=You can open programs available for",
    "CTL_NoRobotsMsg=No robots yet",
    "CTL_ChoosePlanetLabel=Choose a planet:",
    "CTL_ChooseRobotLabel=Choose a robot:",
    "CTL_CreateRobotLabel=Or create a new one:",
    "CTL_CreationInputPrompt=Type a name",
    "CTL_LoadButton=Load!",
    "CTL_CreateButton=Create!"
})
public final class StartTopComponent extends TopComponent {

    private static JFXPanel fxPanel;
    private StartWindowController controller;
    private static Integrator integrator = Integrator.getIntegrator();
    private static final Logger logger = Logger.getLogger(StartTopComponent.class.getName());
    
    public StartTopComponent() {
        initComponents();
        setName(Bundle.CTL_StartTopComponent());
        setToolTipText(Bundle.HINT_StartTopComponent());
        setLayout(new BorderLayout());
        init();  
    }
    
    public void init() {
        fxPanel = new JFXPanel();
        add(fxPanel, BorderLayout.CENTER);
                    
        /**
         * @notice
         * this method set to false keeps javafx application thread alive 
         * when all jfx windows are closed
         * (without it we'll see the blank page if reopen the window)
         */
        Platform.setImplicitExit(false); 
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        });
    }
    
    private void createScene() {
        try {
            controller = (StartWindowController) replaceSceneContent("StartWindow.fxml");
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL location = getClass().getResource(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(location);
        
        Parent root = (Parent) loader.load(location.openStream());
        Scene scene = new Scene(root);
        fxPanel.setScene(scene);

        return (Initializable) loader.getController();
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
        // TODO add custom code on component opening
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
}