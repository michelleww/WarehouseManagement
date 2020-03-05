package test;

import event.LoaderEvent;

import org.junit.Before;
import org.junit.Test;

import warehouse.MySystem;

public class LoaderEventTest {

  /** Set up a new loaderEvent. */
  private LoaderEvent load;
  /** Set up a new system. */
  private MySystem system;

  /**
   * Create a new loaderEvent to test methods in LoaderEvent class.
   * 
   */
  @Before
  public void before() {
    load = new LoaderEvent();
    system = new MockMySystem();
  }

  /**
   * Test processEvent() method in LoadEvent class.
   * 
   * @throws Exception throw the exceptions just in case
   */
  @Test
  public void testprocessEvent() throws Exception {
    load.processEvent("Loader Bill scan 11", system);
  }
}
