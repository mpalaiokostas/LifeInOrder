package life.database.model;

import javax.persistence.*;

@Entity
@Table(name = "BANK_TRANSACTION_TAG_KEYWORDS")
public class BankTransactionTagKeywords {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "BANK_TRANSACTION_TAG_KEYWORD_ID")
  private Long bank_transaction_tag_keyword_id;

  @Column(name = "KEYWORD", nullable = false)
  private String keyword;

  @ManyToOne
  @JoinColumn(name = "bank_transaction_tag_id")
  private BankTransactionTag bankTransactionTag;

  public BankTransactionTagKeywords(String keyword) {
    this.keyword = keyword;
  }

  public String getKeyword() {
    return keyword;
  }
}
