/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import domain.Category;
import domain.ExerciseDomainController;
import domain.GroupOperation;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class ClassDetailController extends AnchorPane implements Observer{

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXTextField txtClassName;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnRemove;

    private ExerciseDomainController dc;
    
    private JFXDialog dialog;

    public ClassDetailController(ExerciseDomainController dc, JFXDialog dia)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClassDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        this.dialog = dia;
    }
    
    
    @FXML
    private void addNewClass(ActionEvent event)
    {
        dc.setManagerMode(Category.class, PersistMode.NEW);
        dc.setSelectedItem(new Category());
    }

    @FXML
    private void saveClass(ActionEvent event)
    {
        dc.saveCategory(txtClassName.getText());
        dc.setManagerMode(Category.class, PersistMode.UPDATE);
    }

    @FXML
    private void removeClass(ActionEvent event)
    { 
        try {
            dc.deleteCategory();
        } catch(IllegalArgumentException ex) {
            setErrorDialog(ex);
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Category cat = (Category) arg;
        txtClassName.setText(cat.getName());
    }
    
    private void setErrorDialog(Exception ex) {
         JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label(ex.getMessage()));
            JFXButton okButton = new JFXButton("OK");
            okButton.setOnMouseClicked(e -> dialog.close());
            layout.setActions(okButton);
            dialog.setContent(layout);
            dialog.show();
    }
    
}
