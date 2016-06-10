package life.file.parser;

import java.util.List;

public interface FileParser<I, O> {

  List<O> parse(I i);

}
