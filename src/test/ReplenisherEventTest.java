package test;

import event.ReplenisherEvent;

import org.junit.Before;
import org.junit.Test;

import warehouse.MySystem;

public class ReplenisherEventTest {

  /** Set up a new replenisherEvent. */
  private ReplenisherEvent replenish;
  /** Set up a new mock system. */
  private MySystem system;

  /**
   * Create a new ReplenishEvent to test methods in ReplenishEvent class.
   * 
   */
  @Before
  public void before() {
    replenish = new ReplenisherEvent();
    system = new MockMySystem();
  }

  /**
   * Test processEvent() method in ReplenishEvent class.
   * 
   * @throws Exception throw the exceptions just in case
   */
  @Test
  public void testprocessEvent() throws Exception {
    replenish.processEvent("Replenisher Ruby replenish A 0 0 0", system);
    system.getReplenisherList().clear();
    replenish.processEvent("Replenisher Alice replenish A 0 0 1", system);
  }
}

