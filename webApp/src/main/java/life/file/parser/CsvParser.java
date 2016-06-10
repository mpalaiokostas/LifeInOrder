package life.file.parser;


import life.database.model.BankTransaction;
import life.file.checker.FileChecker;
import life.file.type.FileType;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Named
public class CsvParser implements FileParser {

  private static final Logger log = LoggerFactory.getLogger(CsvParser.class);

  @Override
  public List<BankTransaction> parse(Object o) {
    List<BankTransaction> bankTransactions;
    try {
      FileType typeOfFile = FileChecker.getTypeOfFile(o);
      bankTransactions = getStatements(o, typeOfFile);
      return bankTransactions;
    } catch (IOException e) {
      log.error("File was not able to read.");
      return null;
    }
  }

  private List<BankTransaction> getStatements(Object o, FileType fileType) throws IOException {
    List<BankTransaction> bankTransactions = new ArrayList<>();
    String csvLine;
    BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(o)));
    while ((csvLine = bufferedReader.readLine()) != null) {
      if (csvLine.matches(fileType.getPatternRecognitionRegex())) {
        bankTransactions.add(new BankTransaction(
            getDate(csvLine, fileType),
            getDescription(csvLine, fileType),
            getCost(csvLine, fileType)
        ));
      }

    }
    return bankTransactions;
  }

  private Double getCost(String csvLine, FileType fileType) {
    return null;
  }

  private String getDescription(String csvLine, FileType fileType) {
    return null;
  }

  private LocalDate getDate(String csvLine, FileType fileType) {
    return null;
  }


}
