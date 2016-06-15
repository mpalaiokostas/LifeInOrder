package life.file.parser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvStreamReader {

  //TODO improve and use the code below into the app

  public static void main(String[] args) throws IOException {
    new CsvStreamReader().read(
      new FileInputStream("C:\\Users\\emmanuoil.fragkakis\\Desktop\\A\\Big_statement_file.csv"),
      new CsvRowMapper() {
        @Override
        public void map(int rowNumber, String[] cells) {
          System.out.println("[rowNumber: " + rowNumber + ", cells:" + Arrays.toString(cells) + ", cells: " + cells.length + "]");
        }
      });

    System.out.print("file processed");
  }

  public void read(InputStream inputStream, CsvRowMapper rowMapper) throws IOException {
    try (BufferedInputStream bis = new BufferedInputStream(inputStream)) {
      final byte[] bytes = new byte[1024];
      final List<String> cells = new ArrayList<>();
      final int[] rowIndex = {0};

      while(bis.available() > 0) {
        bis.read(bytes);

        StringBuffer cell = new StringBuffer();
        boolean isEscaped = false;

        for(byte aByte : bytes) {
          switch((char)aByte) {
            case '"':
              isEscaped = !isEscaped;
              break;

            case ',':
              if(!readEscaped(isEscaped, cell, aByte)) {
                cells.add(cell.toString());
                cell = new StringBuffer();
              }
              break;

            case '\r':
              if(!readEscaped(isEscaped, cell, aByte)) {
                // do nothing...
              }
              break;

            case '\n':
              if(!readEscaped(isEscaped, cell, aByte)) {
                cells.add(cell.toString());
                cell = new StringBuffer();
                rowMapper.map(rowIndex[0]++, cells.toArray(new String[0]));
                cells.clear();
              }
              break;

            default:
              cell.append((char)aByte);
          }
        }
      }
    }
  }

  public boolean readEscaped(boolean isEscaped, StringBuffer cell, byte aByte) {
    if(isEscaped) {
      cell.append((char)aByte);
    }

    return isEscaped;
  }

  public interface CsvRowMapper {
    void map(int rowNumber, String[] cells);
  }

}
