//BASSAM ALASSAAD 40120540


import java.io.*;
import java.util.*;

public class assignmentV1 {
    //The heap memory still has space, stack overflow hasn't been thrown yet, so we terminate the program prematurely
    // The program terminates prematurely as heap memory still has space, and stack overflow hasn't been thrown yet

    static long startTime;
    static long endTime;
    static double[] duration = new double[50];
    static String[] time = new String[50];
    static PrintWriter p;

    public static void main(String[] args) {

        String finalString = null;
        String binaryString = "";
        double milliseconds = 0;
        int counter = 0;

        /*In order to test the recursive method successfully, we can set the outer for loop upper bounds to 10 rather than
         *100 to confirm the recursive output is correct, and that the output files are created and composed correctly.
         * The duration.txt file is also generated to know the time taken for each string input
         *With an outer loop bound of 100, the program runs for a very big amount of time and eventually results in stack overflow
         * */

        for (int i = 2; i <= 10; i+=2) {
            startTime = System.nanoTime(); //timer starts
            counter++;
            Random r = new Random();
            int randomNum = r.nextInt(100)+i;

            char[] binary = new char[randomNum]; //storing the # of * char in the char array based on index of outer for loop
            //filling the char array with * char
            for (int j = i; j > 0; j--) {
                binary[j-1] = '*'; // we store the * char at start of the array for simplicity

            }
            for (int k = i; k < binary.length; k++) { //for the remaining length of the array we store 0 and 1 characters
                if(k%2==0) {
                    binary[k] = '0';
                }
                    binary[k] = '1';

            }
            for (int l = 0; l < binary.length; l++) { //creating the string by concatenating the characters at each index of the char array
                binaryString += binary[l];
            }
            finalString = binaryString;
            RevealStr(finalString, 0,counter); //calling our recursive method, passing through our final string
            endTime = System.nanoTime();
            milliseconds = (endTime - startTime)/1000000.0; // storing time in milliseconds in a double variable
            duration[(i/2)-1] = milliseconds; //storing the double variable in a double array
            time[(i/2)-1] = (duration[(i/2)-1] + ""); //converting the double value to a string and storing it in our string time array
            binaryString = "";

        }
        System.out.println(time[0]);
        System.out.println(time[1]);
        System.out.println(time[2]);

        OutputStream os2;
        PrintWriter p2 = null;
        try {
            os2 = new FileOutputStream(new File("duration.txt"), true);
            p2 = new PrintWriter(os2);
            String tempString = "";
            int numStar = 2;
            for (int j = 0; j < time.length; j++) {
                tempString = time[j];
                p2.append(tempString + "ms to compute a string masked with " + numStar + " * characters.\n");
                numStar+=2;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            p2.close();
        }
    }

    static void RevealStr(String binary, int index, int counter) {
        if(index == binary.length()) {
            writeToTextFile(binary, counter);
            return;

        }
        int star = 0;
        star = binary.indexOf('*'); //star is the index of the star character
        if(star != -1) {
            binary = binary.substring(0,star)+ '0' + binary.substring(star+1);
            RevealStr(binary, star+1,counter); //star+1 is the index we pass through
            // last in first out, we go through 2 now we are unstacking we are at 1
            binary = binary.substring(0,star)+ '1' + binary.substring(star+1);
            RevealStr(binary, star+1,counter);
            binary = binary.substring(0,star)+ '*' + binary.substring(star+1);
        }
        else {
            RevealStr(binary, index+1,counter);
        }
    }

    static void writeToTextFile(String s, int counter) {
        OutputStream os;
        try {
            os = new FileOutputStream(new File("out" + counter + ".txt"), true);
            p = new PrintWriter(os);
            p.append(s + "\n");
        } catch (FileNotFoundException E) {
            E.printStackTrace();
        }

        finally {

            try {
                p.close();

            }
            catch (Exception e) {

            }
        }

    }

}
