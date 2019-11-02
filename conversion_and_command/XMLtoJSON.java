package conversion_and_command;

import conversion_and_command.Values;
import org.json.JSONObject;
import org.json.XML;
import java.io.*;
import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.apache.commons.lang3.StringUtils.substringAfter;
class XMLtoJSON
{
    private int current = 0;
    private File[] files;
    private String[] rcns = new String[Values.capacityOfDocuments];
    static JSONObject[] jsonObjects = new JSONObject[Values.capacityOfDocuments];
    //Constructor
    XMLtoJSON()
    {
        gatherDocs();
        readXML();
        convertToJSON();
    }
    //gather documents
    private void gatherDocs()
    {
        File dir = new File(Values.documentsLocation);
        if (dir.exists() && dir.isDirectory())
        {
            files = dir.listFiles((d, name) -> name.endsWith(".xml"));
        }

    }
    //read XML documents and hold them for conversion later
    private void readXML()
    {
        try
        {
            while (current<Values.capacityOfDocuments)
            {
                BufferedReader br = new BufferedReader(new FileReader(new File(files[current].toString())));
                String line;
                StringBuilder sb = new StringBuilder();
                //converts each xml document into a string "document"
                while ((line = br.readLine()) != null)
                {
                    sb.append(line.trim());
                }
                rcns[current] = sb.toString();
                current++;
            }
        }
        catch (Exception e){e.printStackTrace();}
        finally
        {
            fixTags(rcns);
        }
    }
    //fix Tags (title and objective -> text)
    private void fixTags(String[] rcNs)
    {

        String current,title,objective,text;
        String currentLeft,currentRight;
        for (int i =0; i<rcNs.length; i++)
        {
            current = rcNs[i];
            title = substringBetween(current, "<title>", "</title>");
            objective = substringBetween(current,"<objective>","</objective>");
            text = title + " | " + objective;
            currentLeft = substringBefore(current,"<objective>");
            currentRight = substringAfter(current,"</title>");
            current = currentLeft+"<text>"+text+"</text>"+currentRight;
            rcNs[i] = current;
        }
    }
    //XML to Json
    private void convertToJSON()
    {
        System.out.println("Converting to Json...");
        for (int j=0; j<Values.capacityOfDocuments; j++)
        {
            String xml_data = rcns[j];
            JSONObject obj = XML.toJSONObject(xml_data);
            jsonObjects[j] = obj;
        }
        System.out.println("Conversion was successful! \n");
        save(jsonObjects);
    }
    //Save converted files (json files)
    private void save(JSONObject[] arr)
    {
        System.out.println("\n\nSaving Files...");
        String location = Values.saveLocation;
        for(int i=0; i<Values.capacityOfDocuments; i++)
        {
            String fname = files[i].getName().substring(0,files[i].getName().indexOf("."));
            fname = location + fname;
            writeValues(arr, i, fname);
        }
        System.out.println("json files successfully saved! ");
    }
    //writes values in each document
    private void writeValues(JSONObject[] arr, int counter, String fname) {
        PrintWriter fw;
        try
        {
            fw = new PrintWriter(fname+Values.JsonFormat);
            JSONObject object = arr[counter];
            fw.write(object.toString());
            fw.flush();
            fw.close();
        }catch (IOException e){e.printStackTrace();}
    }
}