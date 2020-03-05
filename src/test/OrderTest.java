package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import warehouse.Fascia;
import warehouse.Order;

public class OrderTest {

  /** Set up a new fascia. */
  private Fascia f1;
  /** Set up a new fascia. */
  private Fascia f2;
  /** Set up a new fascia. */
  private Fascia f3;
  /** Set up a new fascia. */
  private Fascia f4;
  /** Set up a new fascia. */
  private Fascia f5;
  /** Set up a new order. */
  private Order order1;
  /** Set up a new order. */
  private Order order2;
  /** Set up a new order. */
  private Order order3;
  /** Set up a new order. */
  private Order order4;

  /**
   * Create new fascias and orders to test methods in Order class.
   * 
   */
  @Before
  public void setUp() {
    f1 = new Fascia("S", "White", "1");
    f2 = new Fascia("S", "White", "2");
    f3 = new Fascia("S", "Black", "6");
    f4 = new Fascia("SES", "Black", "7");
    f5 = new Fascia("SES", "Black", "7");
    order1 = new Order(f1, f2);
    order2 = new Order(f1, f3);
    order3 = new Order(f3, f4);
    order4 = new Order(f4, f5);
  }

  /**
   * Test getPicker() method in Order class and order1 does not have a picker.
   */
  @Test
  public void testGetPicker() {
    assertNull(order1.getPicker());
  }

  /**
   * Test setPicker() method in Order class and order1 has a picker.
   */
  @Test
  public void testSetPicker() {
    String picker = "Alice";
    order1.setPicker(picker);
    assertEquals("Alice", order1.getPicker());
  }

  /**
   * Test getFrontFascia() method in Order class with order1 and order2, which f1 and f2 in order1
   * is a pair,f1 and f3 in order2 is not a pair.
   */
  @Test
  public void testGetFrontFascia() {
    assertEquals(f1, order1.getFrontFascia());
    assertNull(order2.getFrontFascia());
  }

  /**
   * Test setFrontFascia() method in Order class with order2, which f1 and f3 in order2 is not a
   * pair.
   */
  @Test
  public void testSetFrontFascia() {
    order2.setFrontFascia(f3);
    assertEquals(f3, order2.getFrontFascia());
  }

  /**
   * Test getBackFascia() method in Order class with order1 and order2, which f1 and f2 in order1 is
   * a pair, f1 and f3 in order2 is not a pair.
   */
  @Test
  public void testGetBackFascia() {
    assertEquals(f2, order1.getBackFascia());
    assertNull(order2.getFrontFascia());
  }

  /**
   * Test setBackFascia() method in Order class with order2. f1 and f3 in order2 is not a pair.
   */
  @Test
  public void testSetBackFascia() {
    order2.setBackFascia(f1);
    assertEquals(f1, order2.getBackFascia());
  }

  /**
   * Test setPickReady() method in Order class.
   */
  @Test
  public void testSetPickready() {
    order3.setPickready();
    assertTrue(order3.isPickready());
  }

  /**
   * Test isReady() method in Order class.
   */
  @Test
  public void testIsReady() {
    assertFalse(order4.isPickready());
  }
}
