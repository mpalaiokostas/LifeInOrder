package life.database.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BANK_TRANSACTION_TAG")
public class BankTransactionTag {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "BANK_TRANSACTION_TAG_ID", insertable = false, updatable = false)
  private Long bank_transaction_tag_id;

  @Column(name = "TAG_TITLE", nullable = false)
  private String title;

  @OneToMany(mappedBy = "bankTransactionTag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<BankTransactionTagKeywords> keywords;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bank_transaction_id")
  private BankTransaction bankTransaction;

  public BankTransactionTag(String title) {
    this.title = title;
    this.keywords = new ArrayList<>();
  }

  public String getTitle() {
    return title;
  }

  public List<BankTransactionTagKeywords> getKeywords() {
    return keywords;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    BankTransactionTag that = (BankTransactionTag)o;
    return bank_transaction_tag_id.equals(that.bank_transaction_tag_id) && title.equals(that.title);
  }

  @Override
  public int hashCode() {
    int result = bank_transaction_tag_id.hashCode();
    result = 31 * result + title.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "BankTransactionTag{" +
             "bank_transaction_tag_id=" + bank_transaction_tag_id +
             ", title='" + title + '\'' +
             '}';
  }

  public boolean hasKeywords() {
    return keywords.size() > 0;
  }

  public void setKeywords(BankTransactionTagKeywords keyword) {
    this.keywords.add(keyword);
  }
}
