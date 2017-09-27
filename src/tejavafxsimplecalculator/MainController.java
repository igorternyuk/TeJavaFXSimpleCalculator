/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tejavafxsimplecalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 *
 * @author igor
 * Last edited 27.09.2017
 */
public class MainController implements Initializable {
    @FXML
    private Label input;
    private double number1 = 0, number2 = 0;
    private OperatorType operator = OperatorType.EMPTY;
    private boolean isFirstOperandObtained = false;
    //private boolean start = true;
    private final Model model = new Model();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void showErorMessage(){
        Alert alert = new Alert(Alert.AlertType.ERROR,  "Invalid input",
                ButtonType.OK);
        alert.setTitle("Invalid input");
        alert.setHeaderText("Could not parse number");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }
    
    @FXML
    public void processNumbers(ActionEvent event){
        /*if(start){
            input.setText("");
            start = false;
        }*/
        String value = ((Button)event.getSource()).getText();
        if(value.equalsIgnoreCase(".")){           
            if(!input.getText().contains(".")){
                input.setText(input.getText() + value);
            }            
        }
        else if(value.equalsIgnoreCase("±")){
            input.setText(String.valueOf(-1 * Double.valueOf(input
                    .getText())));
        }
        else if(value.equalsIgnoreCase("<")){
            String txt = input.getText();
            if(txt.length() > 1)
                input.setText(txt.substring(0, txt.length() - 2));
            else
                input.setText("");
        }
        else {
            input.setText(input.getText() + value);
        }
    }
    
    @FXML
    public void processOperators(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if(!value.equalsIgnoreCase("=")){
            switch (value) {
                case "+":
                    operator = OperatorType.PLUS;
                    break;
                case "-":
                    operator = OperatorType.MINUS;
                    break;
                case "*":
                    operator = OperatorType.MULTIPLY;
                    break;
                case "/":
                    operator = OperatorType.DIVIDE;
                    break;
                case "^":
                    operator = OperatorType.POWER;
                    break;
                case "mod":
                    operator = OperatorType.MOD;
                    break;
                default:
                    break;
            }
            try{
                number1 = Double.parseDouble(input.getText());
            }
            catch(NumberFormatException e){
                showErorMessage();
            }
            isFirstOperandObtained = true;
            input.setText("");
        }
        else {
            if (isFirstOperandObtained) {
                try{
                    number2 = Double.parseDouble(input.getText());
                }
                catch(NumberFormatException e){
                    showErorMessage();
                }
                double result = model.calculate(number1, number2, operator);
                input.setText(String.valueOf(result));
                isFirstOperandObtained = false;
            } 
        }
    }
}
