/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import domain.Box;
import domain.BoxController;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BoxOverViewController extends AnchorPane implements Observer {

    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private TableView<Box> tblBox;
    @FXML
    private TableColumn<Box, String> clmName;
    @FXML
    private TableColumn<Box, String> clmDescription;

    private BoxController dc;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnCopy;
    @FXML
    private JFXButton btnRemove;

    public BoxOverViewController(BoxController dc)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoxOverView.fxml"));
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
        JFXDepthManager.setDepth(tblBox, 1);
        
        tblBox.setItems(dc.getBoxes());
        clmName.setCellFactory(e -> 
                {
                    return new TableCell<Box, String>() {
                        @Override
                        protected void updateItem(String item, boolean empty)
                        {
                            super.updateItem(item, empty);

                            setText(empty ? "" : getItem());
                            setGraphic(null);
                            TableRow<Box> currentRow = getTableRow();

                            if (!isEmpty())
                            {
                              if(currentRow.getItem() != null) {
                                    if (!currentRow.getItem().isValidBox())
                                {
                                    currentRow.setStyle("-fx-background-color:lightcoral");
                                } else
                                {
                                    currentRow.setStyle("-fx-background-color:none");
                                }
                                }
                                
                            }
                        }
                    };
        });
        clmDescription.setCellValueFactory(e -> e.getValue().descriptionProperty());
        clmName.setCellValueFactory(e -> e.getValue().nameProperty());
        tblBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
                {
                    if (newSelection != null)
                    {
                        setBoxToDc();
                    }
        });
        
    }

    @Override
    public void update(Observable o, Object arg)
    {
       Box box = (Box) arg;
       
    }

    private void setBoxToDc()
    {
        dc.setSelectedBox(tblBox.getSelectionModel().getSelectedItem());
        dc.setManagerMode(PersistMode.UPDATE);
    }

    @FXML
    private void createNewBox(ActionEvent event)
    {
        dc.setManagerMode(PersistMode.NEW);
        dc.setSelectedBox(new Box());
    }

    @FXML
    private void copySelected(ActionEvent event)
    {
        dc.setManagerMode(PersistMode.NEW);
        Box box = new Box();
        box.copy(tblBox.getSelectionModel().getSelectedItem());
        dc.setSelectedBox(box);
    }

    @FXML
    private void removeSelected(ActionEvent event)
    {
        dc.removeBox();
        dc.setSelectedBox(new Box());
    }

}
