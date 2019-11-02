package conversion_and_command;

import conversion_and_command.Values;
import conversion_and_command.XMLtoJSON;

import java.io.*;
class KibanaCommands
{
    private static final String POST_command = "POST /"+Values.projectName+"/"+Values.projectType+"/_bulk?pretty"+"\n";
    //Constructor
    KibanaCommands()
    {
        try {
            writePOSTCommands();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //creates index id for the batch processing
    private static String createIndexId(int id)
    {
        return (Values.prefix+id+Values.suffix);

    }
    //writes item above index id
    private static String item(String jsonObject, int id)
    {

        return createIndexId(id) +"\n"+ jsonObject;
    }
    //produces POST command for all documents
    private void writePOSTCommands() throws FileNotFoundException
    {
        System.out.println("\n\nNow producing POST commands for Kibana Console");
        PrintWriter printWriter;
        String location = Values.kibanaCommands;
        String tag = "kibanaPOST";
        String fname = tag+".txt";
        String write_loc = location+fname;
        printWriter = new PrintWriter(write_loc);
        printWriter.write(POST_command);
        for(int i=0; i<Values.capacityOfDocuments; i++)
        {
            String value = item(XMLtoJSON.jsonObjects[i].toString(),i);
            printWriter.write(value+"\n");
        }
        printWriter.flush();
        printWriter.close();

        System.out.println("POST Commands for Kibana Console are ready\n");
    }

}
