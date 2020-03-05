package test;

import event.DefaultEvent;
import event.EventNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import warehouse.MySystem;

public class DefaultEventTest {

  /** set up a new system. */
  public MySystem system;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * test processEvent() methods in DefaultEvent.java
   * 
   * @throws Exception throw the exceptions just in case
   */
  @Test
  public void testprocessEvent() throws Exception {
    thrown.expect(EventNotFoundException.class);
    DefaultEvent df = new DefaultEvent();
    system = new MySystem();
    df.processEvent("wahaha", system);
  }
}
