package utilities;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandlingUtilities {
private final static Logger log = LoggerFactory.getLogger(FileHandlingUtilities.class);
Charset charSets = StandardCharsets.UTF_8;

public String readFile(String fileLocation){
    String expectedData = "";
    File file = new File(fileLocation);

    try{
        expectedData = FileUtils.readFileToString(file,charSets);
        return expectedData;
    }
    catch( IOException ioe){
        log.error(" Error caused while accessing file at [{}] -> [{}]", fileLocation , ioe.getStackTrace());
    }

    return expectedData;
}

public boolean writeFile(String fileLocation , String content) {
    try {
        Files.write(Paths.get(fileLocation), content.getBytes());
    } catch (IOException e) {
        log.error(" Error caused while accessing file at [{}] or writing contents to the file [{}]", fileLocation, e.getStackTrace());
    }
    return false;
}

}
