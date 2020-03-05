package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import warehouse.Floor;

public class FloorTest {

  /** Set up a new Floor. */
  private Floor floor;

  /**
   * Create a new floor to test methods in Floor class.
   * 
   */
  @Before
  public void before() {
    floor = new Floor();
  }

  /**
   * Test getPickFace() method in Floor class.
   */
  @Test
  public void testgetPickFace() {
    Integer init = floor.getPickFace().get("A,1,0,3");
    assertEquals(null, init);
  }
}
