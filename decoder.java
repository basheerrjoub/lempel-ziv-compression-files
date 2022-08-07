import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
public class Decoder {
 public static void main(String[] args) {
 String data = "";
 try {
 File myObj = new File("file.txt");
 Scanner myReader = new Scanner(myObj);
 while (myReader.hasNextLine()) {
 data += myReader.nextLine();
 }
 myReader.close();
 } catch (FileNotFoundException e) {
 System.out.println("An error occurred.");
 e.printStackTrace();
 }
 System.out.println("BinaryRep: "+data);
 //Decoding Data
 LinkedList<String> numaricalData = new LinkedList<>();
 String current = "";
 String letter;
 int number;
 for(int i=0; i<data.length(); i+=8) {
 current = data.substring(i,i+10);
 number =Integer.parseInt(current,2);
 i+=10;
 letter = data.substring(i, i+8);
 letter = setStringtoASCII(letter);
 current = String.valueOf(number);
 current +=letter;
 numaricalData.add(current);
 }
//We got the numarical Representation now get the sequence
 System.out.print("NumaricalRap: ");
 for (int j=0; j<numaricalData.size(); j++)
 System.out.print(numaricalData.get(j) + " ");
 String originalData = "";
//Decode the numarical representation into the original Form
 for(int i=0;i<numaricalData.size();i++) {
 current = numaricalData.get(i);
 number =Integer.parseInt(current.substring(0,current.length()-1),10);
 if(number == 0) {
 originalData += current.charAt(current.length()-1);
 }
 else {
 String str = "";
 str += current.charAt(current.length()-1);
 int num;
 while((num = Integer.parseInt(current.substring(0, current.length() -
1))) != 0) {
 current = numaricalData.get(num-1);
 str +=current.charAt(current.length()-1);
 }
 //Reverse
 StringBuilder str1 = new StringBuilder();
 str1.append(str);
 str1.reverse();
 originalData +=str1;
 }
 }
 System.out.println();
 final String ANSI_COLOR = "\u001B[32m";
 final String ANSI_RESET = "\u001B[0m";
 System.out.println(ANSI_COLOR+"Original: "+originalData+ANSI_RESET);
 }
 static String setStringtoASCII(String str)
 {
 // To store size of s
 int N = (str.length());
 // If given String is not a
 // valid String
 if (N % 8 != 0) {
 return "Not Possible!";
 }
 // To store final answer
 String res = "";
 // Loop to iterate through String
 for (int i = 0; i < N; i += 8) {
 int decimal_value = binaryToDecimal((str.substring(i, 8+i)));
 // Apprend the ASCII character
 // equivalent to current value
 res += (char)(decimal_value);
 }
 // Return Answer
 return res;
 }
 static int binaryToDecimal(String n)
 {
 String num = n;
 // Stores the decimal value
 int dec_value = 0;
 // Initializing base value to 1
 int base = 1;
 int len = num.length();
 for (int i = len - 1; i >= 0; i--) {
 // If the current bit is 1
 if (num.charAt(i) == '1')
 dec_value += base;
 base = base * 2;
 }
 // Return answer
 return dec_value;
 }