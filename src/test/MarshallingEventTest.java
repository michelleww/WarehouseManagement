package test;

import event.MarshallingEvent;

import org.junit.Before;
import org.junit.Test;

import warehouse.MySystem;

public class MarshallingEventTest {

  /** Set up a new marshallingEvent. */
  private MarshallingEvent marshalling;
  /** Set up a new system. */
  private MySystem system;

  /**
   * Create a new MarshallingEvent to test methods in MarshallingEvent class.
   * 
   */
  @Before
  public void before() {
    marshalling = new MarshallingEvent();
    system = new MockMySystem();
  }

  /**
   * Test processEvent() method in MarshallingEvent class.
   * 
   * @throws Exception throw the exceptions just in case
   */
  @Test
  public void testprocessEvent() throws Exception {
    marshalling.processEvent("Marshalling Picker Alice to Marshalling", system);
  }
}


