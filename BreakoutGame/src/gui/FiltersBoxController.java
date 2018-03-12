/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import domain.BoxController;
import domain.Category;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class FiltersBoxController extends AnchorPane {

    @FXML
    private VBox vboxClasses;

    private BoxController dc;
    @FXML
    private JFXListView<Category> lstClasses;

    public FiltersBoxController(BoxController dc)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FiltersBox.fxml"));
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
        lstClasses.setItems(dc.getClasses());
        lstClasses.setCellFactory(CheckBoxListCell.forListView((Category item)
                -> 
                {
                    BooleanProperty observable = new SimpleBooleanProperty();
                    observable.addListener((obs, wasSelected, isNowSelected) -> 
                            {
                                System.out.println();
                                System.out.println("Check box for " + item + " changed from " + wasSelected + " to " + isNowSelected);
                                if(isNowSelected)
                                   dc.addCategoryToFilter(item);
                                else
                                    dc.removeCategoryToFilter(item);
                    });
                    return observable;
        }));
        
    /*    lstClasses.setCellFactory(e -> {
           CheckBoxListCell test = new CheckBoxListCell();
            System.out.println(test.itemProperty().getBean().getCheckBox());
            test.getItem();
           test.setGraphic(new JFXListCell());
           test.setSelectedStateCallback(e2 -> {
                BooleanProperty observable = new SimpleBooleanProperty();
                    observable.addListener((obs, wasSelected, isNowSelected) -> 
                            {
                                System.out.println("Check box for " + e + " changed from " + wasSelected + " to " + isNowSelected);
                               
                    });
                    return observable;
           });
           return test;
       });*/

    }

}
