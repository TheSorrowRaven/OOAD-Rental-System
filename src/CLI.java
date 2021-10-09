package src;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * 
 * This class is for debugging purposes by writing into the CLI interface
 * 
 */
public class CLI {
    
    private static final boolean usePrintWriter = true;

    private static PrintWriter pw = new PrintWriter(System.out, true);

    //Write
    private static void out(String str){
        if (usePrintWriter){
            pw.print(str);
            pw.flush();
            return;
        }
        System.out.print(str);
    }
    //Write line
    private static void outln(String str){
        if (usePrintWriter){
            pw.println(str);
            pw.flush();
            return;
        }
        pw.println(str);
    }

    //Print
    public static void print(Object obj){
        out(obj.toString());
    }
    //Print, skip line
    public static void println(Object obj){
        outln(obj.toString());
    }
    //Overload - skip line
    public static void println(){
        out("\n");
    }

    //Print array by each line
    public static void printArr(String[] text){
        for (int i = 0; i < text.length - 1; i++){
            println(text[i]);
        }
        print(text[text.length - 1]);
    }
    //Print array by each line, skip line
    public static void printArrln(String[] text){
        for (int i = 0; i < text.length; i++){
            println(text[i]);
        }
    }

    public static String debugPrepend = "DEBUG: ";
    public static boolean debugPrint = true;

    //get debugPrint
    private static boolean canDebugLog(){
        return debugPrint;
    }
    //Debug logging
    public static void log(Object obj){
        if (!canDebugLog()){
            return;
        }
        outln(debugPrepend + obj.toString());
    }

    //Clear CLI screen
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
        } catch (IOException e) {
        }
    }

}