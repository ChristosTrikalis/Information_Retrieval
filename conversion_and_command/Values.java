package conversion_and_command;

class Values
{
    static final int capacityOfDocuments = 1;//number of documents. First get your collection of docs
    static final String JsonFormat = ".json";
    static final String saveLocation = "C:\\path\\To\\files\\json_results\\"; //path where json docs will be saved
    static final String kibanaCommands = "C:\\path\\to\\files\\KibanaCommands\\"; //path where POST command will be saved
    static final String documentsLocation = "C:\\path\\to\\files\\Parsed files";    //path where parsed files are located
    static final String projectName = "index_yourElasticIndex";
    static final String projectType = "_doc";
    static final String Info = "";
    static final String prefix = "{\"index\":{\"_id\":\"";
    static final String suffix = "\"}}";

}
