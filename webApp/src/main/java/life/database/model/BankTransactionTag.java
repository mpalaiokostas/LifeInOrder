package life.database.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * @author fragkakise on 12/04/2016.
 */
@Entity
@Table(name = "BANKTRANSACTIONTAG")
public class BankTransactionTag {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "TAGTITLE", nullable = false)
  private String title;

  @Column(name = "KEYWORDS", nullable = false)
  private List<String> keywords;

  public BankTransactionTag(String title) {
    this.title = title;
    keywords = new ArrayList<>();
  }

  public String getTitle() {
    return title;
  }

  public List<String> getKeywords() {
    return keywords;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    BankTransactionTag that = (BankTransactionTag)o;
    return id.equals(that.id) && title.equals(that.title);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + title.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "BankTransactionTag{" +
             "id=" + id +
             ", title='" + title + '\'' +
             '}';
  }

  public boolean hasKeywords() {
    return keywords.size() > 0;
  }

  public void setKeywords(String keyword) {
    this.keywords.add(keyword);
  }
}
