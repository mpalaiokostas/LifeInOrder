package com.fragmanos;

import java.util.ArrayList;
import java.util.List;

import life.database.model.BankTransaction;
import life.database.model.BankTransactionTag;
import life.database.model.BankTransactionTagKeywords;
import org.joda.time.LocalDate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TransactionsTagTests {

  BankTransaction bankTransactionWithTags;
  BankTransaction bankTransactionWithoutTags;
  BankTransactionTag foodTag;

  @BeforeMethod
  public void setUp() throws Exception {
    foodTag = new BankTransactionTag("Rent");
    List<BankTransactionTag> bankTransactionTags = new ArrayList<>();
    bankTransactionTags.add(new BankTransactionTag("food"));
    bankTransactionTags.add(new BankTransactionTag("commute"));
    bankTransactionWithTags = new BankTransaction(new LocalDate(2016,4,12),"expense",100.0,bankTransactionTags);
    bankTransactionWithoutTags = new BankTransaction(new LocalDate(2016,4,12),"expense",100.0);
  }

  @Test
  public void testTransactionContainTags() throws Exception {
    assertTrue(bankTransactionWithTags.hasTags());
  }

  @Test
  public void testTransactionThatDoNotContainTags() throws Exception {
    assertFalse(bankTransactionWithoutTags.hasTags());
  }

  @Test
  public void testBankTransactionTagMatchedExpected() throws Exception {
    assertEquals("food",bankTransactionWithTags.getTags().get(0).getTitle());
  }

  @Test
  public void testBankTransactionWithoutTagsAfterTagInsertion() throws Exception {
    bankTransactionWithoutTags.setTag(new BankTransactionTag("testTag"));
    assertEquals("testTag",bankTransactionWithoutTags.getTags().get(0).getTitle());
  }

  @Test
  public void testTagHasKeywords() throws Exception {
    foodTag.setKeywords(new BankTransactionTagKeywords("Saintsburry"));
    foodTag.setKeywords(new BankTransactionTagKeywords("Tesco"));
    foodTag.setKeywords(new BankTransactionTagKeywords("CoOperative"));
    assertTrue(foodTag.hasKeywords());
    assertEquals("Saintsburry", foodTag.getKeywords().get(0).getKeyword());
    assertEquals(3,foodTag.getKeywords().size());
  }
}
