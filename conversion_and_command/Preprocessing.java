package conversion_and_command;

public class Preprocessing
{
    //Main
    public static void main (String[] args)
    {

        if(Values.documentsLocation.toLowerCase().contains("path")||Values.saveLocation.toLowerCase().contains("path")||Values.kibanaCommands.toLowerCase().contains("path"))
            System.out.println("First set paths in Values Class\n");
        else
        {
            System.out.println(Values.Info);
            new XMLtoJSON();
            new KibanaCommands();
        }
    }
}
