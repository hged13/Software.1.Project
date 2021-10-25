package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.Optional;

import static controller.AddPartController.ValidPart;

/**logical error. An inhouse part would populate the screen but not an outsourced part.
 *

solved because i hadn't closed a parenthesis in the if section of the setpart function.*/

/** This creates the Modify Part Controller class. */
public class ModifyPartController {

    @FXML
    private RadioButton InHouseButton;

    @FXML
    private RadioButton OutsourcedButton;

    @FXML
    private Text TextField;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField StockTextField;

    @FXML
    private TextField PriceTextField;

    @FXML
    private TextField MaxTextField;

    @FXML
    private TextField SwitchTextField;

    @FXML
    private TextField MinTextField;

    private boolean isOutsourced;
    private Part part;
    private int PartID;
    private String errorMessage = new String();

    @FXML
    /** This cancels modifying a part. Brings you back to the main screen. */
    void CancelButtonPushed(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Parent addPartCancel = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
            Scene scene = new Scene(addPartCancel);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        else {
            System.out.println("you canceled");
        }
    }

    /** This Changes the part form to in-house. */
    @FXML
    void ShowInHouseForm(ActionEvent event) {
        isOutsourced = false;
        TextField.setText("MachineID");
        OutsourcedButton.setSelected(false);
        InHouseButton.setSelected(true);

    }
    /** This changes the part form to outsourced. */
    @FXML
    void ShowOutsourcedForm(ActionEvent event) {
        isOutsourced = true;
        TextField.setText("Company Name");
        InHouseButton.setSelected(false);
        OutsourcedButton.setSelected(true);


    }



    @FXML
    /** This saves the modified part and replaces the old part in the inventory. */
    void SavePart(ActionEvent event) {

        int ID = Integer.parseInt(IDTextField.getText());

        String Name = NameTextField.getText();
        String Stock = StockTextField.getText();
        String Price = PriceTextField.getText();
        String Max = MaxTextField.getText();
        String Both = SwitchTextField.getText();
        String Min = MinTextField.getText();


        try{
            errorMessage = ValidPart( Name,Integer.parseInt(Min),Integer.parseInt(Max), Integer.parseInt(Stock), Double.parseDouble(Price), errorMessage);
            if(errorMessage.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(errorMessage);
                alert.showAndWait();
                errorMessage = "";
            }
            else{
                if(part instanceof InHouse){

                    System.out.println("Part name: " + Name);
                    InHouse newPart = new InHouse(ID,Name,Double.parseDouble(Price), Integer.parseInt(Stock), Integer.parseInt(Min), Integer.parseInt(Max), Integer.parseInt(Both));
                    newPart.setId(ID);
                    newPart.setName(Name);
                    newPart.setPrice(Double.parseDouble(Price));
                    newPart.setStock(Integer.parseInt(Stock));
                    newPart.setMin(Integer.parseInt(Min));
                    newPart.setMax(Integer.parseInt(Max));
                    newPart.setMachineID(Integer.parseInt(Both));
                    Inventory.updatePart(newPart);
                }
                else if( part instanceof Outsourced) {
                    System.out.println("Part name: " + Name);
                    Outsourced newPart = new Outsourced(ID, Name, Double.parseDouble(Price), Integer.parseInt(Stock), Integer.parseInt(Min), Integer.parseInt(Max), Both);
                    newPart.setId(ID);
                    newPart.setName(Name);
                    newPart.setPrice(Double.parseDouble(Price));
                    newPart.setStock(Integer.parseInt(Stock));
                    newPart.setMin(Integer.parseInt(Min));
                    newPart.setMax(Integer.parseInt(Max));
                    newPart.setCompanyName(Both);
                    Inventory.updatePart(newPart);
                }

                Parent addPartSave = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
                Scene scene = new Scene(addPartSave);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();


            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }




/** This populates the tableviews with the part that was selected on the main screen. */
    public void setPart(Part part) {
        this.part = part;


        if (part instanceof InHouse) {
            InHouseButton.setSelected(true);
            TextField.setText("Machine ID");
            InHouse part1 = (InHouse) part;

            IDTextField.setText(String.valueOf(part1.getId()));
        NameTextField.setText(part1.getName());
        StockTextField.setText(String.valueOf(part1.getStock()));
        PriceTextField.setText(String.valueOf( part1.getPrice()));
        MaxTextField.setText(String.valueOf(part1.getMax()));
        MinTextField.setText(String.valueOf(part1.getMin()));
        SwitchTextField.setText(String.valueOf(part1.getMachineID()));}

                if (part instanceof Outsourced){
                    OutsourcedButton.setSelected(true);
                    TextField.setText("Company Name");
                    Outsourced part2 = (Outsourced) part;
                IDTextField.setText(String.valueOf(part2.getId()));
                NameTextField.setText(part2.getName());
                StockTextField.setText(String.valueOf(part2.getStock()));
                PriceTextField.setText(String.valueOf( part2.getPrice()));
                MaxTextField.setText(String.valueOf(part2.getMax()));
                MinTextField.setText(String.valueOf(part2.getMin()));
                SwitchTextField.setText(String.valueOf(part2.getCompanyName()));


            }



    }

}


