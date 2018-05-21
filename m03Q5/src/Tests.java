/**
 *  Copyright (c)  2017 Olivier Martin
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package src;

import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.text.MessageFormat;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import src.librairies.FunctionHelper;
import StudentCode.*;

public class Tests {
    
    private String[] test_function_names = {    "square",
                                                "rectangle",
                                                "circle" };
    private Class[][] test_params_rype = {      new Class[]{int.class},
                                                new Class[]{int.class, int.class},
                                                new Class[]{double.class} };
    private String[] feedbacks = {  "The surface of a square of side {0} is {2}.\nBut your code computes {3}.\n",
                                    "The surface of a rectangle of width {0} and height {1} is {2}.\nBut your code computes {3}.\n",
                                    "The surface of a circle of radius {0} is {2}.\nBut your code computes {3}.\n" };
    private int index = (int)(Runner.R * test_function_names.length);

    public void test(double a, double b) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int int_a = (int)a;
        int int_b = (int)b;
        
        double reponse_etudiant = -1;
        double expected = -1;
        if(index == 0){
            expected = int_a * int_a;
            reponse_etudiant = (double) FunctionHelper.run_student_function(test_function_names[index], int_a);
        }else if(index == 1){
            expected = int_a * int_b;
            reponse_etudiant = (double) FunctionHelper.run_student_function(test_function_names[index], int_a, int_b);
        }else if(index == 2){
            expected = Math.PI * a * a;
            reponse_etudiant = (double) FunctionHelper.run_student_function(test_function_names[index], a);
        }
        String feedback = MessageFormat.format(feedbacks[index], a, b, expected, reponse_etudiant);
        assertEquals(feedback, expected, reponse_etudiant, 0.001);
    }
    
    /**
     *	Lanceur de test
     **/
    @Test
    public void testLauncher() {
        try{
            FunctionHelper.check_etudiant_function("StudentCode.Etudiant", test_function_names[index], double.class, test_params_rype[index]);
            test(4.5, 6.6);
            test(1.5, 6.6);
            test(1.75, 6.6);
            test(2, 6.6);
            test(2.2, 6.6);
            test(12.2, 3.3);
            System.err.print("TAG_STARTcompute:trueTAG_END");
        }catch (InvocationTargetException e){
            Throwable t = e.getCause();
            if(t instanceof ArithmeticException){
                System.err.print("TAG_STARTexception_divide_by_0:trueTAG_END");
                fail("Attention, il est interdit de diviser par zéro.");
            }else if(t instanceof ClassCastException){
                fail("Attention, certaines variables ont été mal castées !");
            }else if(t instanceof StringIndexOutOfBoundsException){
                fail("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
            }else if(t instanceof ArrayIndexOutOfBoundsException){
                fail("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
            }else if(t instanceof NullPointerException){
                fail("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
            }else if(t instanceof StackOverflowError){
                fail("Il semble que votre code boucle. Ceci peut arriver si votre fonction s'appelle elle-même.");
            }else{
                fail("Une erreur inattendue est survenue dans votre tâche : " + t.toString());
            }
        }catch(Exception e){
            fail("Une erreur inattendue est survenue dans votre tâche : " + e.toString());
        }
    }
}
