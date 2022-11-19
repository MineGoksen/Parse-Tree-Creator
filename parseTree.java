import java.io.*;
import java.util.Scanner;

public class parseTree {

    public static FileReader inputStream = null;
    public static PrintWriter outputStream = null;
    public static String nextToken = "";
    static String islem_line;
    static boolean correct = true, else_check = false, else_parse = true;
    public static void main(String[] args) {
        String input_file = "x.txt";
        //String output_file = "result1.txt";

        try {
            int tmp ;
            String s = "";
            inputStream = new FileReader(input_file);
            outputStream = new PrintWriter("result1.txt");
            while((tmp = inputStream.read()) != -1){
                s += analysis((char)tmp) + " ";
                String new_line = s.trim();
                islem_line = new_line.replaceAll("( )+", " ");
            }
            inputStream.close();

        } catch (IOException e) {
            outputStream.println("You try to read nonexistent file.");
            System.exit(0);
        }
        String[] temp = islem_line.split(" ");
        checker(temp);
        outputStream.close();

    }

    public static void checker(String[] temp){
        for(int i = 0; i< temp.length; i++){
            if(temp[i].equalsIgnoreCase("if")) {
                String tmp_var = "", tmp_var2 = "", tmp_var3 = "", tmp_var4 = "", tmp_var5 = "",tmp_var6 = "", tmp_var7 = "", tmp_var8 = "";
                String tmp_logic = "", tmp_expr = "",tmp_logic2 = "", tmp_expr2= "";
                i++;
                if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) {
                    tmp_var = temp[i];
                    i++;
                    if (temp[i].equals("<=") ||temp[i].equals(">=") ||temp[i].equals("<") ||temp[i].equals(">") ||temp[i].equals("=") ) {
                        tmp_logic = temp[i];
                        i++;
                        if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) {
                            tmp_var2 = temp[i];
                            i++;
                            if (temp[i].equalsIgnoreCase("{")) { // statement giris
                                i++;
                                if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) {
                                    tmp_var3 = temp[i];
                                    i++;
                                    if (temp[i].equalsIgnoreCase(":=")) {
                                        i++;
                                        if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) { // expr giris
                                            tmp_var4 = temp[i];
                                            i++;
                                            if (temp[i].equals("+") || temp[i].equals("-") || temp[i].equals("*") || temp[i].equals("/") || temp[i].equals("%")) {
                                                tmp_expr = temp[i];
                                                i++;
                                                if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) {
                                                    tmp_var5 = temp[i];
                                                    i++;
                                                    if (temp[i].equalsIgnoreCase("}")) {
                                                        System.out.print("");
                                                        if(i != temp.length-1)
                                                            i++;
                                                        if(temp[i].equalsIgnoreCase("else")){ // else var mi??
                                                            else_check = true;
                                                            i++;
                                                            if (temp[i].equalsIgnoreCase("{")) { // statement giris
                                                                i++;
                                                                if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) {
                                                                    tmp_var6 = temp[i];
                                                                    i++;
                                                                    if (temp[i].equalsIgnoreCase(":=")) {
                                                                        i++;
                                                                        if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) { // expr giris
                                                                            tmp_var7 = temp[i];
                                                                            i++;
                                                                            if (temp[i].equals("+") || temp[i].equals("-") || temp[i].equals("*") || temp[i].equals("/") || temp[i].equals("%")) {
                                                                                tmp_expr2 = temp[i];
                                                                                i++;
                                                                                if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) {
                                                                                    tmp_var8 = temp[i];
                                                                                    i++;
                                                                                    if (temp[i].equalsIgnoreCase("}")) {
                                                                                        System.out.print("");
                                                                                    }else else_parse = false;
                                                                                }else else_parse = false;
                                                                            }else else_parse = false;
                                                                        }else else_parse = false;
                                                                    }else else_parse = false;
                                                                }else else_parse = false;
                                                            }else else_parse = false;
                                                        } else else_check = false;
                                                    } else correct = false;
                                                } else correct = false;
                                            } else correct = false;
                                        } else correct = false;
                                    } else correct = false;
                                } else correct = false;
                            } else correct = false; // statement if bitis
                        } else correct = false;
                    } else correct = false;
                } else correct = false;
                if (correct == true && else_check == false) { // if var else yok
                    outputStream.println ("Parsed correctly\nParse Tree :");
                    outputStream.println("<start>");
                    outputStream.println("<if_statement>");
                    outputStream.println("if <logic_expr> <statement>");
                    outputStream.println("if <var> " + tmp_logic + " <var> <statement>");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " <var> <statement>");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " <statement>");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {<var> := <expr>}");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {" + tmp_var3 + " :=" + " <expr>}");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {" + tmp_var3 + " :=" + " <var> " + tmp_expr + " <var>}");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {" + tmp_var3 + " := " + tmp_var4 + " " + tmp_expr + " <var>}");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {" + tmp_var3 + " := " + tmp_var4 + " " + tmp_expr + " " + tmp_var5 + "}");
                }
                else if(correct == true && else_check == true && else_parse == true){
                    outputStream.println("Parsed correctly\nParse Tree :");
                    outputStream.println("<start>");
                    outputStream.println("<if_statement>");
                    outputStream.println("if <logic_expr> <statement> else <statement>");
                    outputStream.println("if <var> " + tmp_logic + " <var> <statement> else <statement>");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " <var> <statement> else <statement>");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " <statement> else <statement>");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {<var> := <expr> } else <statement>");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {" + tmp_var3 + " :=" + " <expr>} else <statement>");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {" + tmp_var3 + " :=" + " <var> " + tmp_expr + " <var>} else <statement>");
                    outputStream.println("if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {" + tmp_var3 + " := " + tmp_var4 + " " + tmp_expr + " <var>} else <statement>");
                    String flag = "if " + tmp_var + " " + tmp_logic + " " + tmp_var2 + " {" + tmp_var3 + " := " + tmp_var4 + " " + tmp_expr + " " + tmp_var5 + "}";
                    outputStream.println(flag + " else <statement>");
                    outputStream.println(flag+ " else {<var> := <expr>}");
                    outputStream.println(flag + " else {" + tmp_var6 + " :=" + " <expr>}");
                    outputStream.println(flag + " else {" + tmp_var6 + " :=" + " <var> " + tmp_expr + " <var> }");
                    outputStream.println(flag + " else {" + tmp_var6 + " := " + tmp_var7 + " " + tmp_expr2 + " <var>}");
                    outputStream.println(flag + " else {" + tmp_var6 + " := " + tmp_var7 + " " + tmp_expr2 + " " + tmp_var8 + "}");

                }
                else {
                    correct = false;
                }
            }
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            else if (temp[i].equalsIgnoreCase("{")) { // statement giris
                String tmp_var = "", tmp_var2 = "", tmp_var3 = "", tmp_var4 = "", tmp_var5 = "";
                String tmp_logic = "", tmp_expr = "";
                i++;
                if (Character.isLetter(temp[i].charAt(0))) {
                    tmp_var = temp[i];
                    i++;
                    if (temp[i].equalsIgnoreCase(":=")) {
                        i++;
                        if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) { // expr giris
                            tmp_var2 = temp[i];
                            i++;
                            if (temp[i].equals("+") || temp[i].equals("-") || temp[i].equals("*") || temp[i].equals("/") || temp[i].equals("%")) {
                                tmp_expr = temp[i];
                                i++;
                                if (temp[i].length()==1 && Character.isLetter(temp[i].charAt(0))) {
                                    tmp_var3 = temp[i];
                                    i++;
                                    if (temp[i].equalsIgnoreCase("}")) {
                                        System.out.print("");
                                    } else correct = false;
                                } else correct = false;
                            } else correct = false;
                        } else correct = false;
                    } else correct = false;
                } else correct = false;

                if (correct == true) {
                    outputStream.println("<start>");
                    outputStream.println("Parsed correctly\nParse Tree :");
                    outputStream.println("<statement>");
                    outputStream.println("{<var> := <expr>}");
                    outputStream.println("{" + tmp_var + " :=" + " <expr>}");
                    outputStream.println("{" + tmp_var + " :=" + " <var> " + tmp_expr + " <var>}");
                    outputStream.println("{" + tmp_var + " := " + tmp_var2 + " " + tmp_expr + " <var>}");
                    outputStream.println("{" + tmp_var + " := " + tmp_var2 + " " + tmp_expr + " " + tmp_var3 + "}");
                }
                else{
                    correct = false;
                }
            }
            else
                correct = false;
        }
        if(correct == false){
            outputStream.println("Parsed incorrectly");
        }
    }

    public static String analysis(char c){
        if(c == ':'){
            if(getChar() == '='){
                nextToken = ":=";
                return nextToken;
            }
        }
        else if((c == 'i') && (getChar()=='f' )) {
            nextToken = "if";
            return nextToken;
        }

        else if ((c == 'e') && (getChar()=='l') && (getChar()=='s')&& (getChar()=='e')) {
            nextToken = "else";
            return nextToken;

        }
        else if(Character.isLetter(c)){
            nextToken = Character.toString(c);
            return nextToken;
        }
        else if ((c=='<' && getChar()=='=')) { nextToken = "<="; return nextToken;}
        else if ((c=='>' && getChar()=='=')) { nextToken = ">="; return nextToken;}
        else if ((c=='<')) { nextToken = "<"; return nextToken;}
        else if ((c=='>')) { nextToken = ">"; return nextToken;}
        else if ((c=='=')) { nextToken = "="; return nextToken;}

        else if (c=='+') {  nextToken = "+";  return nextToken;}
        else if (c=='-') {  nextToken = "-";  return nextToken;}
        else if (c=='*') {  nextToken = "*";  return nextToken;}
        else if (c=='/') {  nextToken = "/";  return nextToken;}
        else if (c=='%') {  nextToken = "%";  return nextToken;}

        else if(c == '{'){
            nextToken = "{";
            return nextToken;
        }
        else if(c == '}'){
            nextToken = "}";
            return nextToken;
        }
        return "";
    }
   /////////////////////////////////////////////////////////////////////////////////////
    public static char getChar(){
        int x;
        try {
            if((x = inputStream.read()) != -1)
                return (char)x;
        } catch (IOException e) {
            System.out.println("File could not open.");
        }
        return 'm';
    }
    /////////////////////////////////////////////////////////////////////////////////////
}
