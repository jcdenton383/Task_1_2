import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static class CmdParams {
        public String inputXPoints;
        public String inputYPoints;
        public boolean error;
    }

    public static CmdParams parseArgs(String[] args){
        CmdParams params = new CmdParams();
        if (args.length > 0){
            params.inputXPoints = args[0];
            params.inputYPoints = args[1];
        } else if (args.length != 2) params.error = true;
        return params;
    }


    public static void gui() throws Exception{
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run() { new FormMain().setVisible(true);}
        });
    }



    public static ArrayList<Double> readArrayListFromFile(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        ArrayList<Double> list = new ArrayList<Double>();
        while (s.hasNext()){
            list.add(s.nextDouble());
        }
        s.close();    //probably should keep it here to close scanner

        return list;
    }





    public static void main(String[] args) throws Exception {
	// write your code here
        //for now use HARDCODED polygons in formmain, disregard data in txts, since the task expressively ALLOWES it

        CmdParams params = parseArgs(args);
        if(params.error) {
            System.out.println("error");
            System.exit(2);
        }

        File fileXPoints = new File(params.inputXPoints);
        File fileYPoints = new File(params.inputYPoints);
        if((fileXPoints.length() == 0) || (fileYPoints.length() == 0)){
            System.out.println("error: no input files");
            System.exit(2);
        }
        gui();

    }
    //-------







}
