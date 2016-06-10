package life.web.controller;

import life.database.model.BankTransaction;
import life.file.parser.FileParser;
import life.file.processor.FileProcessor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/upload/")
public class UploadController {

  private List<FileProcessor<MultipartFile, String>> fileProcessors;

  private BankingFacade bankingFacade;
  private FileParser fileParser;

  @Inject
  public UploadController(FileParser fileParser) {
    this.fileParser = fileParser;
  }

  @Inject
  public void setFileProcessors(List<FileProcessor<MultipartFile, String>> fileProcessors) {
    this.fileProcessors = fileProcessors;
  }

  @RequestMapping(value = "transactions", method = RequestMethod.POST)
  @ResponseBody
  public boolean uploadTransactions(@RequestParam("file") MultipartFile multipartFile) throws IOException, ParseException {
    for (FileProcessor<MultipartFile, String> fileProcessor : fileProcessors) {
      if (fileProcessor.canProcess(multipartFile)) {
        String result = fileProcessor.process(multipartFile);
        List<BankTransaction> transactions = fileParser.parse(result);

        bankingFacade.saveToDB(result);
        return true;
      }
    }

    throw new UnsupportedOperationException("File type not support: " + multipartFile.getOriginalFilename());
  }
}
