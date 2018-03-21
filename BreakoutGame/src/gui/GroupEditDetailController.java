/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domain.BoBGroup;
import domain.SessionController;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class GroupEditDetailController extends AnchorPane implements Observer{

    @FXML
    private TableView<?> tblStudents;
    @FXML
    private JFXTextField txfGroupName;
    @FXML
    private JFXButton btnAddGroup;

    private SessionController dc;
    
    public GroupEditDetailController(SessionController dc)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupEditDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        } catch (IOException ex)
        {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        tblStudents.setItems(dc.getStudents());
    }

    @Override
    public void update(Observable o, Object arg)
    {
        BoBGroup boBGroup = (BoBGroup) arg;
        
    }

    /**
     * Initializes the controller class.
     */
    
    
}
