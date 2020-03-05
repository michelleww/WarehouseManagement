package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import warehouse.Fascia;

public class FasciaTest {

  /** Set up a new fascia. */
  private Fascia f1;

  /**
   * Create a new fascia to test methods in Fascia class.
   */
  @Before
  public void before() {
    f1 = new Fascia("S", "White", "2");
  }

  /**
   * Test getColour() method in Fascia class.
   */
  @Test
  public void testGetColour() {
    assertEquals("White", f1.getColour());
  }

  /**
   * Test getModel() method in Fascia class.
   */
  @Test
  public void testGetModel() {
    assertEquals("S", f1.getModel());
  }

  /**
   * Test getSku() method in Fascia class.
   */
  @Test
  public void testGetSku() {
    assertEquals("2", f1.getSku());
  }
}
