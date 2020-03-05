package test;

import event.ReadyEvent;

import org.junit.Before;
import org.junit.Test;

import warehouse.MySystem;

public class ReadyEventTest {

  /** Set up a new readyEvent. */
  private ReadyEvent ready;
  /** Set up a new mock system. */
  private MySystem system;

  /**
   * Create a new ReadyEvent to test methods in ReadyEvent class.
   */
  @Before
  public void before() {
    ready = new ReadyEvent();
    system = new MockMySystem();
  }

  /**
   * Test processEvent() method in ReadyEvent class.
   * 
   * @throws Exception throw the exceptions just in case
   */
  @Test
  public void testprocessEvent() throws Exception {
    ready.processEvent("Ready Picker Bob ready", system);
  }
}


