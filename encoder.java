import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
public class Encoder {
 public static void main(String[] args) {
 String data = "Garlic has long been touted as a health booster but it has never 
been clear why the herb might be good for you. Now new research is beginning to unlock 
the secrets of the odoriferous bulb. In a study published today in the proceedings of 
the national academy of sciences researchers show that eating garlic appears to boost 
our natural supply of hydrogen sulfide. Hydrogen sulfide is actually poisonous at high 
concentrations. It is the same noxious byproduct of oil refining that smells like 
rotten eggs. But the body makes its own supply of the stuff which acts as an 
antioxidant and transmits cellular signals that relax blood vessels and increase blood 
flow. In the latest study performed at the university of alabama at birmingham 
researchers extracted juice from supermarket garlic and added small amounts to human 
red blood cells. The cells immediately began emitting hydrogen sulfide the scientists 
found. The power to boost hydrogen sulfide production may help explain why a garlic 
rich diet appears to protect against various cancers including breast prostate and 
colon cancer say the study authors. Higher hydrogen sulfide might also protect the 
heart according to other experts. Although garlic has not consistently been shown to 
lower cholesterol levels researchers at albert einstein college of medicine earlier 
this year found that injecting hydrogen sulfide into mice almost completely prevented 
the damage to heart muscle caused by a heart attack. People have known garlic was 
important and has health benefits for centuries said doctor Kraus associate professor 
of environmental science and biology at the university of alabama. Even the greeks 
would feed garlic to their athletes before they competed in the olympic games. Now, 
the downside. The concentration of garlic extract used in the latest study was 
equivalent to an adult eating about two medium sized cloves per day. In such countries 
as italy korea and china where a garlic rich diet seems to be protective against 
disease per capita consumption is as high as eight to twelve cloves per day. While 
that may sound like a lot of garlic doctor kraus noted that increasing your 
consumption to five or more cloves a day is not hard if you use it every time you 
cook.\n";
 String prevData = "";
 char[] datasplit = data.toCharArray();
// for(int x=0; x<datasplit.length; x++)
// System.out.println(datasplit[x]);
 LinkedList<String> sequence = new LinkedList<String>();
 //Generating a sequence
 String current = "";
 for(int i=0; i< datasplit.length; i++){
 current += datasplit[i];
 if(!sequenceContain(sequence, current)) {
 sequence.add(current);
 current = "";
 }
 prevData += datasplit[i];
 }
 System.out.println("Sequence: ");
 for (int i=0; i<sequence.size(); i++) {
 System.out.print(sequence.get(i) + " ");
 }
 //Numarical Representation for values
 System.out.println();
 LinkedList<String> numaricalRep = new LinkedList<String>();
 current = "";
 int index;
 String indexstr;
 for(int i=0; i<sequence.size(); i++){
 current = sequence.get(i);
 if(sequence.contains(current.substring(0, current.length()-1))){
 index = sequence.indexOf(current.substring(0, current.length()-1));
 sequence.get(index);
 index++;
 indexstr = String.valueOf(index);
 indexstr += current.substring(current.length()-1, current.length());
 numaricalRep.add(indexstr);
 }
 else {
 numaricalRep.add("0"+current);
 }
 }
 ///////////////
 ///////////////
 System.out.println("Numarical: ");
 for (int i=0; i<numaricalRep.size(); i++) {
 System.out.print(numaricalRep.get(i) + " ");
 }
 ///////////////
 ///////////////
 //Coding Part
 String numaricalMessage = "";
 for(int i=0; i<numaricalRep.size(); i++) {
 numaricalMessage += numaricalRep.get(i);
 }
 String binaryMessage = AsciiToBinary(numaricalRep);
 System.out.println();
 System.out.println("Binary Message:"+binaryMessage);
 System.out.println("length of Message after Compression: " + 
binaryMessage.length());
 String messagebinarybefore = dataToBinary(data);
 System.out.println("length of Message Before Compression: " + 
messagebinarybefore.length());
 //Write on the communication channel
 try {
 FileWriter myWriter = new FileWriter("./file.txt");
 myWriter.write(binaryMessage);
 myWriter.close();
 System.out.println("Transmitted into Communication Channel");
 } catch (IOException e) {
 System.out.println("Channel Is Corrupted!");
 e.printStackTrace();
 }
 final String RED_COLOR = "\u001B[31m";
 final String ANSI_RESET = "\u001B[0m";
 System.out.println();
 System.out.println(RED_COLOR+"1)NumberOfBits(ASCII): 
"+messagebinarybefore.length()+ANSI_RESET);
 System.out.println(RED_COLOR+"2)NumberOfBits(LempelZiv): 
"+binaryMessage.length()+ANSI_RESET);
 //Average Number Of Bits/Symbol
 System.out.println(RED_COLOR+"3)AVG. #bits/Symbol: 
"+((float)messagebinarybefore.length()/(float)data.length()) +" 
bits/symbol"+ANSI_RESET);
 //Entropy of source
 System.out.println(RED_COLOR+"4)EntropyOfSouce: 
"+((float)binaryMessage.length()/(float)data.length())+" bits/Symbol"+ANSI_RESET);
 //Compression Level
 System.out.println(RED_COLOR+"5)Compression Percentage: 
"+(((float)binaryMessage.length()/(float)messagebinarybefore.length())*100)+" 
%"+ANSI_RESET);
 }
 static boolean sequenceContain(LinkedList<String> list, String current) {
 boolean ret = false;
 if(list.contains(current))
 ret = true;
 return ret;
 }
 public static String AsciiToBinary(LinkedList<String> list){
 String binary = "";
 for (int i=0; i<list.size(); i++)
 {
 String app = "";
 String str = list.get(i).substring(0,list.get(i).length()-1);
 int number = Integer.parseInt(str);
 String bin = Integer.toBinaryString(number);
 for(int j=bin.length(); j<10; j++)
 app+="0";
 app += bin;
 binary += app;
// binary += ' ';
 ////Letter:
 app = "";
 char strToChar = list.get(i).charAt(list.get(i).length()-1);
 number = (int)(strToChar);
 bin = Integer.toBinaryString(number);
 for(int j=bin.length(); j<8; j++)
 app+="0";
 app += bin;
 binary += app;
// binary += ' ';
 }
 return binary.toString();
 }
 public static String dataToBinary(String asciiString){
 byte[] bytes = asciiString.getBytes();
 StringBuilder binary = new StringBuilder();
 for (byte b : bytes)
 {
 int val = b;
 for (int i = 0; i < 8; i++)
 {
 binary.append((val & 128) == 0 ? 0 : 1);
 val <<= 1;
 }
 // binary.append(' ');
 }
 return binary.toString();
 }
}
