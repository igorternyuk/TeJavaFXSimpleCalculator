/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tejavafxsimplecalculator;

/**
 *
 * @author igor
 */
public class Model {
    public double calculate(double number1, double number2,
            OperatorType operator){
        switch(operator){
            case PLUS:
                return number1 + number2;
            case MINUS:
                return number1 - number2;
            case MULTIPLY:
                return number1 * number2;
            case DIVIDE:
                return number2 != 0 ? number1 / number2 : 0;
            case POWER:
                return number1 >= 0 ? Math.pow(number1, number2) : 0;
            case MOD:
                return number2 == 0 ? number1 % number2 : 0;
        }
        return 0;
    }
}
