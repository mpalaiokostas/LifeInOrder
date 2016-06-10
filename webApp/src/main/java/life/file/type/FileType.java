package life.file.type;

public enum FileType {

  MIDATA("midata",true,"\\d+\\/\\d+\\/\\d+\\,.+\\,[a-zA-Z0-9)(.\\/ @*'&:-]*\\,[-+]£\\d+.\\d+\\,[-+]£\\d+\\.\\d+"),
  STATEMENT("statement", false,"\\d+\\-\\d+\\-\\d+\\,[a-zA-Z0-9)(.\\/ @*'&:\\,-]*\\,[-+]?\\d+\\.\\d+");

  private final String keyword;
  private final boolean ignoreFirstLine;
  private final String patternRecognitionRegex;

  FileType(String keyword, boolean ignoreFirstLine, String patternRecognitionRegex) {
    this.keyword = keyword;
    this.ignoreFirstLine = ignoreFirstLine;
    this.patternRecognitionRegex = patternRecognitionRegex;
  }

  public String getKeyword() {
    return keyword;
  }

  public boolean isIgnoreFirstLine() {
    return ignoreFirstLine;
  }

  public String getPatternRecognitionRegex() {
    return patternRecognitionRegex;
  }
}
