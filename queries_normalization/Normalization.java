package queries_normalization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Normalization
{
    private static String[] parts = new String[10];
    private void readQueriesTxt()
    {
        File file = new File(Values.locationOfQueries);
        Scanner sc = null;
        try {

            sc = new Scanner(file);
            StringBuilder line = new StringBuilder();
            String result;
            while (sc.hasNextLine())
            {
                line.append(sc.nextLine());
            }

            result = line.toString().replaceAll("\\s+"," ");
            result = result.replace("/", "-");

            parts[0] = result.substring(result.indexOf("Q01"),result.indexOf("Q02"));
            parts[1] = result.substring(result.indexOf("Q02"),result.indexOf("Q03"));
            parts[2] = result.substring(result.indexOf("Q03"),result.indexOf("Q04"));
            parts[3] = result.substring(result.indexOf("Q04"),result.indexOf("Q05"));
            parts[4] = result.substring(result.indexOf("Q05"),result.indexOf("Q06"));
            parts[5] = result.substring(result.indexOf("Q06"),result.indexOf("Q07"));
            parts[6] = result.substring(result.indexOf("Q07"),result.indexOf("Q08"));
            parts[7] = result.substring(result.indexOf("Q08"),result.indexOf("Q09"));
            parts[8] = result.substring(result.indexOf("Q09"),result.indexOf("Q10"));
            parts[9] = result.substring(result.indexOf("Q10"));

            saveNormalizedQueries();

        }
        catch (FileNotFoundException e) { e.printStackTrace(); }



    }

    private void saveNormalizedQueries() throws FileNotFoundException {
        PrintWriter printWriter;
        String location = Values.saveLocationOfQueries;
        String fname = "\\Q";
        for(int i=0; i<10; i++)
        {
            printWriter = new PrintWriter(location+fname+(i+1)+".txt");
            printWriter.write(parts[i]);
            printWriter.flush();
            printWriter.close();
        }
        System.out.println("Queries normalized at location "+ Values.saveLocationOfQueries);
    }

    public static void main(String[] args)
    {
        if(Values.locationOfQueries.toLowerCase().contains("path")||Values.saveLocationOfQueries.toLowerCase().contains("path"))
            System.out.println("First set paths in Values Class\n");
        else
            new Normalization().readQueriesTxt();
    }

}

