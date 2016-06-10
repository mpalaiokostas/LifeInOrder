package life.file.processor;

import life.file.parser.FileParser;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Named
public class CsvProcessor implements FileProcessor {

  FileParser fileParser;

  @Inject
  public CsvProcessor(FileParser fileParser) {
    this.fileParser = fileParser;
  }

  @Override
  public boolean canProcess(Object o) {
    return o != null;
  }

  @Override
  public Object process(Object o) throws IOException {
    String csvFile = null;
    if (o instanceof MultipartFile) {
      csvFile = IOUtils.toString(new ByteArrayInputStream(((MultipartFile) o).getBytes()));
    }
    return csvFile;
  }

}
