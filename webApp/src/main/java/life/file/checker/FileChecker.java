package life.file.checker;

import life.file.type.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileChecker {

  private static final Logger log = LoggerFactory.getLogger(FileChecker.class);

  public static FileType getTypeOfFile(Object o) throws IOException {
    FileType fileType = null;
    FileReader reader = new FileReader(String.valueOf(o));
    if (o instanceof String) {
      BufferedReader br = new BufferedReader(reader);
      br.readLine(); // ignore first line
      if (br.readLine().matches(FileType.STATEMENT.getPatternRecognitionRegex())) {
        fileType = FileType.STATEMENT;
      } else if (br.readLine().matches(FileType.MIDATA.getPatternRecognitionRegex())) {
        fileType = FileType.MIDATA;
      } else {
        log.error("Unrecognizable FileType");
      }
      br.close();
      reader.close();
    }

    return fileType;
  }

}
