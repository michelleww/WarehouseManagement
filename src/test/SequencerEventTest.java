package test;

import event.SequencerEvent;

import org.junit.Before;
import org.junit.Test;

public class SequencerEventTest {

  /** Set up a new sequencerEvent. */
  public SequencerEvent sequence;
  /** Set up a new mock system. */
  public MockMySystem system;

  /**
   * Create a new SequencerEvent to test methods in SequencerEvent class.
   * 
   */
  @Before
  public void before() {
    sequence = new SequencerEvent();
    system = new MockMySystem();
  }

  /**
   * Test processEvent() method in SequencerEvent class.
   * 
   * @throws Exception throw the exceptions just in case
   */
  @Test
  public void testprocessEvent1() throws Exception {
    sequence.processEvent("Sequencer Sue visually inspected false", system);
    sequence.processEvent("Sequencer Sue visually inspected true", system);
  }

  /**
   * Test processEvent() method in SequencerEvent class.
   * 
   * @throws Exception throw the exceptions just in case
   */
  @Test
  public void testprocessEvent2() throws Exception {
    sequence.processEvent("Sequencer Sue scan 2", system);
    sequence.processEvent("Sequencer Sue rescan 3", system);
  }

}
