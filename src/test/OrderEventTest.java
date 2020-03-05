package test;

import event.OrderEvent;

import org.junit.Before;
import org.junit.Test;

import warehouse.MySystem;

public class OrderEventTest {

  /** Set up a new orderEvent. */
  private OrderEvent order;
  /** Set up a new mock system. */
  private MySystem system;

  /**
   * Create a new orderEvent to test methods in OrderEvent class.
   */
  @Before
  public void before() {
    order = new OrderEvent();
    system = new MockMySystem();
  }

  /**
   * Test processEvent() method in OrderEvent class.
   * 
   * @throws Exception throw the exceptions just in case
   */
  @Test
  public void testprocessEvent() throws Exception {
    order.processEvent("Order S White", system);
  }
}
