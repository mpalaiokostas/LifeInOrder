package life.file.processor;

import java.io.IOException;

public interface FileProcessor<I, O> {

  boolean canProcess(I i);

  O process(I i) throws IOException;

}
