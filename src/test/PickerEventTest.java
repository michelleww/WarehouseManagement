package test;

import event.PickerEvent;

import org.junit.Before;
import org.junit.Test;

import warehouse.MySystem;

public class PickerEventTest {

  /** Set up a new pickerEvent. */
  private PickerEvent pick;
  /** Set up a new mock system. */
  private MySystem system;

  /**
   * Create a new PickerEvent to test methods in PickerEvent class.
   * 
   */
  @Before
  public void before() {
    pick = new PickerEvent();
    system = new MockMySystem();
  }

  /**
   * Test processEvent() method in PickerEvent class.
   * 
   * @throws Exception throw the exceptions just in case
   */
  @Test
  public void testprocessEvent() throws Exception {
    pick.processEvent("Picker Alice scan 1", system);
  }
}


