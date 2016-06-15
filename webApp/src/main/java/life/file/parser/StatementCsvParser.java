package life.file.parser;

import life.database.model.BankTransaction;
import life.file.DateUtilsImpl;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class StatementCsvParser implements TransactionFileParser {

  private DateUtilsImpl dateUtils = new DateUtilsImpl();

  @Override
  public boolean canParse(MultipartFile multipartFile) throws IOException {
    return multipartFile.getOriginalFilename().toLowerCase().endsWith(".csv");
  }

  /**
   * Statement parser expect to have 3 headers
   * date : DD/MM/YYYY
   * description [a-zA-Z0-9]
   * amount ##.#
   *
   * @param statement read statement csv file
   * @return BankTransaction Object
   */
  @Override
  public List<BankTransaction> parse(MultipartFile statement) throws IOException, ParseException {
    final List<BankTransaction> bankTransactions = new ArrayList<>();

    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(statement.getInputStream()));
    try {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        bankTransactions.add(new BankTransaction(
            getDate(line),
            getDescription(line),
            getCost(line)
        ));
      }
    } finally {
      bufferedReader.close();
    }

    // todo read with stream
    // todo implement csv reader based on buffered reader
    // todo row mapper implementation
    // todo manage path of the buffer


    return bankTransactions;
  }

  public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
    File convFile = new File(multipart.getOriginalFilename());
    multipart.transferTo(convFile);
    return convFile;
  }

  private Double getCost(String csvLine) {
    return Double.parseDouble(csvLine.substring(csvLine.lastIndexOf(",") + 1, csvLine.length()));
  }

  private String getDescription(String csvLine) {
    return csvLine.substring(csvLine.indexOf(",") + 1, csvLine.lastIndexOf(","));
  }

  private LocalDate getDate(String csvLine) throws ParseException {
    String date = csvLine.substring(0, csvLine.indexOf(","));
    return dateUtils.convertTextToDate(date);
  }

}
