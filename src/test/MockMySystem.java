package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import warehouse.Fascia;
import warehouse.MySystem;
import warehouse.Order;
import warehouse.Worker;

public class MockMySystem extends MySystem {

  /** The list of ready replenisher. */
  private List<Worker> replenisherList;

  /**
   * Construct a mock MySystem for testing.
   */
  public MockMySystem() {
    replenisherList = new ArrayList<>();
    replenisherList.add(new Worker("Paul", "Replenisher"));
    replenisherList.add(new Worker("Alice", "Replenisher"));
  }

  /**
   * Get the list of ready replenisher.
   * 
   * @return List<@Worker@> list of ready replenisher
   */
  @Override
  public List<Worker> getReplenisherList() {
    return replenisherList;
  }

  /**
   * Replenishing the fascia whose amount is five or less.
   * 
   * @param replenisher the name of replenisher
   * @param location the location of fascia which need to be replenished
   */
  @Override
  public void replenishing(String replenisher, String location) {}

  /**
   * Receive the order from the OrderEvent.
   * 
   * @param oneOrder the order received from the OrderEvent
   */
  @Override
  public void receiveOrder(Order oneOrder) {}

  /**
   * Create the order of specific model and colour specified by description.
   * 
   * @param description the input description
   */
  @Override
  public Order createOrder(String description) {
    Fascia f1 = new Fascia("S", "White", "1");
    Fascia f2 = new Fascia("S", "White", "2");
    Order order = new Order(f1, f2);
    return order;
  }

  /**
   * Pick this fascia specified by its sku.
   * 
   * @param picker the name of the picker
   * @param sku the sku of the fascia
   * @param optimized the optimized locations
   * @param updatelocations the locations for fascias
   * 
   */
  @Override
  public void pickFascia(String picker, String sku, Map<Integer, String> optimized,
      List<String> updatelocations) {}

  /**
   * Update the locations before start picking.
   * 
   * @return List<@String@> list of updated locations
   */
  @Override
  public List<String> updateLocations() {
    List<String> skus = new ArrayList<>();
    skus.add("A 0 0 0");
    return skus;
  }

  /**
   * Marshaling the order by the picker.
   * 
   * @param picker the name of the picker
   */
  @Override
  public void marshaling(String picker) {}

  /**
   * Distinguishing the type of ready workers and giving corresponding feedback.
   * 
   * @param worker the string represents the type of the worker
   * @param name the name of the worker
   */
  @Override
  public void readyDistinguishing(String worker, String name) {}

  /**
   * Loading the fascias that have been sequenced already.
   * 
   * @param loader the name of the loader
   * @param loadedNumber the sku of the fascia loaded by loader
   */
  @Override
  public void loading(String loader, String loadedNumber, String scanStatus) {

  }

  /**
   * Sequencing every fascia in the request list.
   * 
   * @return List<@Order@> list of orders that have been sequenced
   */
  @Override
  public List<Order> sequencing() {
    List<Order> savedlist = new ArrayList<Order>();
    Fascia f1 = new Fascia("S", "White", "2");
    Fascia f2 = new Fascia("S", "White", "3");
    Order o1 = new Order(f1, f2);
    savedlist.add(o1);
    return savedlist;
  }

  /**
   * Storing the scanned sku by sequencer and comparing to the correspond request if the
   * corresponding request does not match the scanned sku, tell sequencer to rescan.
   * 
   * @param scannedNum the sku of the fascia scanned by sequencer
   * @param sequencer the name of the sequencer
   * @param scanStatus the status for scanning (scan or rescan)
   */
  @Override
  public void sequencerScanning(String scannedNum, String sequencer, String scanStatus) {}

  /**
   * Sequencing the order if the inspectedResult is true otherwise tell the picker to repick the
   * order.
   * 
   * @param inspectedResult the result of visually inspecting
   */
  @Override
  public void visuallyInspecting(String inspectedResult) {
    if (inspectedResult.equals("true")) {
      List<Order> list = this.sequencing();
      this.getTruck().setListSequencedOrders(list);
    }
  }
}
