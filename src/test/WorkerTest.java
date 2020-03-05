package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import warehouse.Worker;

public class WorkerTest {

  /** Set up a new worker. */
  private Worker worker;

  /**
   * Create a new worker to test methods in Worker class.
   */
  @Before
  public void before() {
    worker = new Worker("Jason", "Picker");
  }

  /**
   * Test scan() method in Worker class.
   */
  @Test
  public void testScan() {
    worker.scan("1A");
    assertEquals("1A", worker.getScannedSkuList().get(0));
  }

  /**
   * Test getName() method in Worker class.
   */
  @Test
  public void testGetName() {
    assertEquals("Jason", worker.getName());
  }

  /**
   * Test getPosition() method in Worker class.
   */
  @Test
  public void testGetPosition() {
    assertEquals("Picker", worker.getPosition());
  }

  /**
   * Test getScannedSkuList() method in Worker class.
   */
  @Test
  public void testGetScannedSkuList() {
    List<Integer> expected = new ArrayList<>();
    assertEquals(expected, worker.getScannedSkuList());
  }
}
