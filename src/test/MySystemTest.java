package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import warehouse.Fascia;
import warehouse.MySystem;
import warehouse.Order;
import warehouse.Request;
import warehouse.Simulator;
import warehouse.Truck;
import warehouse.Worker;

public class MySystemTest {

  /** Set up a new system. */
  public MySystem system;
  /** Set up a new simulator. */
  public Simulator simulator;

  /**
   * Create a new system to test methods in MySystem class.
   * 
   * @throws IOException throw the exceptions just in case
   */
  @Before
  public void before() throws IOException {
    simulator =
        new Simulator("myorders.txt", "translation.csv", "traversal_table.csv", "initial.csv");
    system = simulator.getSystem();
  }

  /**
   * Test getRequestList() method in MySystem class.
   */
  @Test
  public void testGetRequestList() {
    List<Request> request = new ArrayList<>();
    assertEquals(request, system.getRequestList());
  }

  /**
   * Test getReplenisherList() method in MySystem class.
   */
  @Test
  public void testGetReplenisherList() {
    List<Worker> replenisherList = new ArrayList<>();
    assertEquals(replenisherList, system.getReplenisherList());
  }

  /**
   * Test setReplenisherList() method in MySystem class.
   */
  @Test
  public void testSetReplenisherList() {
    Worker worker = new Worker("Alice", "Picker");
    List<Worker> replenisherList = new ArrayList<>();
    replenisherList.add(worker);
    system.setReplenisherList(replenisherList);
    assertEquals(replenisherList, system.getReplenisherList());
  }

  /**
   * Test getWareHouse() method in MySystem class.
   */
  @Test
  public void testGetWareHouse() {
    assertEquals("47 48", system.getWareHouse().getMap().get("SEL Black"));
  }

  /**
   * Test getTruck() method in MySystem class.
   */
  @Test
  public void testGetTruck() {
    Truck truck = new Truck();
    assertEquals(truck.getListSequencedOrders(), system.getTruck().getListSequencedOrders());
  }

  /**
   * Test getLogger() method in MySystem class.
   */
  @Test
  public void testGetLogger() {
    Logger log = Logger.getLogger(MySystem.class.getName());
    assertEquals(log.toString(), system.getLogger().toString());
  }

  /**
   * Test getLoadedList() method in MySystem class.
   */
  @Test
  public void testGetLoadedList() {
    List<String> sku = new ArrayList<>();
    assertEquals(sku, system.getRequestList());
  }


  /**
   * Test getScannedSkuList() method in MySystem class.
   */
  @Test
  public void testGetScannedSkuList() {
    List<String> empty = new ArrayList<String>();
    assertEquals(empty, system.getScannedSkuList());
  }

  /**
   * Test setSavedlist() method in MySystem class.
   */
  @Test
  public void testSetSavedlist() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    List<Order> orderlist = new ArrayList<Order>();
    orderlist.add(order);
    system.setSavedlist(orderlist);
    List<String> numberlist = new ArrayList<String>();
    numberlist.add("1");
    numberlist.add("2");
    assertTrue(system.getSavedList().equals(numberlist));
  }

  /**
   * Test createOrder() method in MySystem class.
   */
  @Test
  public void testCreateOrder() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    assertEquals(order.getBackFascia().getSku(),
        system.createOrder("S White").getBackFascia().getSku());
  }

  /**
   * Test receiveOrder() method in MySystem class.
   */
  @Test
  public void testReceiveOrder() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Fascia fascia3 = new Fascia("SE", "White", "3");
    Fascia fascia4 = new Fascia("SE", "White", "4");
    Fascia fascia5 = new Fascia("SSE", "White", "5");
    Fascia fascia6 = new Fascia("SSE", "White", "6");
    Order o1 = new Order(fascia1, fascia2);
    Order o2 = new Order(fascia3, fascia4);
    Order o3 = new Order(fascia5, fascia6);
    system.receiveOrder(o1);
    system.receiveOrder(o2);
    system.receiveOrder(o3);
    assertEquals(3, system.getBufferOrders().size());
    Fascia fascia7 = new Fascia("SSR", "White", "7");
    Fascia fascia8 = new Fascia("SSR", "White", "8");
    Order o4 = new Order(fascia7, fascia8);
    system.receiveOrder(o4);
    assertEquals(0, system.getBufferOrders().size());
    assertEquals(1, system.getRequestList().size());

  }

  /**
   * Test createOrder() method in MySystem class, the case which bufferOrder has orders.
   */
  @Test
  public void testSetOrderPicker1() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    system.receiveOrder(order);
    system.setOrderPicker("Alice");
    assertEquals("Alice", order.getPicker());
  }

  /**
   * Test createOrder() method in MySystem class, the case which bufferOrder has no orders.
   */
  @Test
  public void testSetOrderPicker2() {
    system.setOrderPicker("Alice");
    assertEquals(0, system.getBufferOrders().size());
  }

  /**
   * Test setRequestPicker() method in MySystem class, the case which requestList has requests.
   */
  @Test
  public void testSetRequestPicker1() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    Request request = new Request();
    request.formRequest(order, order, order, order);
    system.getRequestList().add(request);
    system.setRequestPicker("Alice");
    assertEquals("Alice", system.getRequestList().get(0).getOrders().get(0).getPicker());
  }

  /**
   * Test setRequestPicker() method in MySystem class, the case which requestList has no requests.
   */
  @Test
  public void testSetRequestPicker2() {
    system.setRequestPicker("Alice");
    assertEquals(0, system.getRequestList().size());
  }

  /**
   * Test readyDistinguishing() method in MySystem class.
   */
  @Test
  public void testReadyDistinguishing() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    system.receiveOrder(order);
    system.readyDistinguishing("Picker", "Alice");
    assertEquals("Alice", system.getBufferOrders().getFirst().getPicker());
    system.readyDistinguishing("Sequencer", "Alice");
    system.readyDistinguishing("Loader", "Alice");
    system.readyDistinguishing("Replenisher", "Alice");
    Worker replenisher = new Worker("Alice", "Replenisher");
    assertEquals(replenisher.getName(), system.getReplenisherList().get(0).getName());
    system.readyDistinguishing("Pick", "Alice");
  }

  /**
   * Test updateLocations() method in MySystem class, the case which requestList has requests.
   */
  @Test
  public void testUpdateLocations1() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    Request request = new Request();
    request.formRequest(order, order, order, order);
    system.getRequestList().add(request);
    List<String> skus = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      skus.add("1");
      skus.add("2");
    }
    for (int i = 0; i < 8; i++) {
      assertEquals(skus.get(i), system.updateLocations().get(i));
    }
  }

  /**
   * Test updateLocations() method in MySystem class, the case which requestList has no requests.
   */
  @Test
  public void testUpdateLocations2() {
    assertEquals(0, system.updateLocations().size());
  }

  /**
   * Test pickFascia() method in MySystem class, the case which the amount is larger than 5.
   */
  @Test
  public void testPickFascia1() {
    Map<Integer, String> optimized = new HashMap<>();
    optimized.put(1, "A,0,0,0");
    List<String> updatelocations = new ArrayList<>();
    updatelocations.add("A,0,0,0");
    system.getWareHouse().getFloor().getPickFace().put("A,0,0,0", 30);
    Fascia fascia = new Fascia("S", "White", "1");
    system.getWareHouse().getLocationToFascia().put("A,0,0,0", fascia);
    system.pickFascia("Alice", "1", optimized, updatelocations);
    int amount = system.getWareHouse().getFloor().getPickFace().get("A,0,0,0");
    assertEquals(29, amount);
  }

  /**
   * Test pickFascia() method in MySystem class, the case which the amount is smaller or equal to 5.
   */
  @Test
  public void testPickFascia2() {
    Map<Integer, String> optimized = new HashMap<>();
    optimized.put(1, "A,0,0,0");
    List<String> updatelocations = new ArrayList<>();
    updatelocations.add("A,0,0,0");
    system.getWareHouse().getFloor().getPickFace().put("A,0,0,0", 5);
    Fascia fascia = new Fascia("S", "White", "1");
    system.getWareHouse().getLocationToFascia().put("A,0,0,0", fascia);
    system.pickFascia("Alice", "1", optimized, updatelocations);
    assertTrue(system.getReplenishList().size() > 0);
  }

  /**
   * Test pickFascia() method in MySystem class, the case which the updateLocationList has no
   * locations.
   */
  @Test
  public void testPickFascia3() {
    Map<Integer, String> optimized = new HashMap<>();
    optimized.put(1, "A,0,0,0");
    List<String> updatelocations = new ArrayList<>();
    system.getWareHouse().getFloor().getPickFace().put("A,0,0,0", 5);
    Fascia fascia = new Fascia("S", "White", "1");
    system.getWareHouse().getLocationToFascia().put("A,0,0,0", fascia);
    system.pickFascia("Alice", "1", optimized, updatelocations);
    assertTrue(system.getReplenishList().size() > 0);
  }

  /**
   * Test pickFascia() method in MySystem class, the case which picked is 9.
   */
  @Test
  public void testPickFascia4() {
    Map<Integer, String> optimized = new HashMap<>();
    optimized.put(1, "A,0,0,0");
    List<String> updatelocations = new ArrayList<>();
    updatelocations.add("A,0,0,0");
    system.getWareHouse().getFloor().getPickFace().put("A,0,0,0", 30);
    Fascia fascia = new Fascia("S", "White", "1");
    system.getWareHouse().getLocationToFascia().put("A,0,0,0", fascia);
    system.setPicked(8);
    system.pickFascia("Alice", "1", optimized, updatelocations);
    assertEquals(1, system.getPicked());
  }

  /**
   * Test pickFascia() method in MySystem class, the case which picker picked wrong sku.
   */
  @Test
  public void testPickFascia5() {
    Map<Integer, String> optimized = new HashMap<>();
    optimized.put(1, "A,0,0,0");
    List<String> updatelocations = new ArrayList<>();
    updatelocations.add("A,0,0,0");
    system.getWareHouse().getFloor().getPickFace().put("A,0,0,0", 30);
    Fascia fascia = new Fascia("S", "White", "1");
    system.getWareHouse().getLocationToFascia().put("A,0,0,0", fascia);
    system.pickFascia("Alice", "2", optimized, updatelocations);
    int amount = system.getWareHouse().getFloor().getPickFace().get("A,0,0,0");
    assertEquals(30, amount);
  }

  /**
   * Test marshaling() method in MySystem class, the case which the first order in this request has
   * same picker as it is picked.
   */
  @Test
  public void testMarshaling1() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    Request request = new Request();
    request.formRequest(order, order, order, order);
    request.getOrders().get(0).setPicker("Alice");
    system.getRequestList().add(request);
    system.marshaling("Alice");
    assertTrue(request.isPickready());
  }

  /**
   * Test marshaling() method in MySystem class, the case which the first order in this request has
   * different picker as it is picked.
   */
  @Test
  public void testMarshaling2() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    Request request = new Request();
    request.formRequest(order, order, order, order);
    request.getOrders().get(0).setPicker("Alice");
    system.getRequestList().add(request);
    system.marshaling("Tom");
    assertFalse(request.isPickready());
  }

  /**
   * Test sequencing() method in MySystem class, the case which the first request in requestList is
   * picked.
   */
  @Test
  public void testSequencing1() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    Request request = new Request();
    request.formRequest(order, order, order, order);
    request.setPickready();
    system.getRequestList().add(request);
    for (Order ouder : system.sequencing()) {
      assertEquals(order.getBackFascia().getSku(), ouder.getBackFascia().getSku());
    }
  }

  /**
   * Test sequencing() method in MySystem class, the case which the first request in requestList is
   * not picked.
   */
  @Test
  public void testSequencing2() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    Request request = new Request();
    request.formRequest(order, order, order, order);
    system.getRequestList().add(request);

    assertEquals(0, system.sequencing().size());

  }

  /**
   * Test getSavedList() method in MySystem class.
   */
  @Test
  public void testGetSavedList() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    Request request = new Request();
    request.formRequest(order, order, order, order);
    request.setPickready();
    system.getRequestList().add(request);
    system.sequencing();
    List<String> savedlist = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      savedlist.add("1");
      savedlist.add("2");
    }
    for (int i = 0; i < 8; i++) {
      assertEquals(savedlist.get(i), system.getSavedList().get(i));
    }
  }

  /**
   * Test visuallyInspecting() method in MySystem class, the false case.
   * 
   * @throws IOException throw the exceptions just in case
   */
  @Test
  public void testVisuallyInspectingF() throws IOException {
    system.visuallyInspecting("false");
  }

  /**
   * Test visuallyInspecting() method in MySystem class, the true case.
   */
  @Test
  public void testVisuallyInspectingT() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    Request request = new Request();
    request.formRequest(order, order, order, order);
    request.setPickready();
    system.getRequestList().add(request);
    system.visuallyInspecting("true");
    assertEquals((Integer) 4, (Integer) system.getTruck().getListSequencedOrders().size());
  }

  /**
   * Test scanChecking method in MySystem class.
   */
  @Test
  public void testScanChecking() {
    List<String> l1 = new ArrayList<String>();
    l1.add("a");
    l1.add("b");
    l1.add("c");
    l1.add("d");
    l1.add("1");
    l1.add("2");
    List<String> l2 = new ArrayList<String>();
    l2.add("a");
    l2.add("b");
    l2.add("c");
    l2.add("d");
    List<String> l3 = new ArrayList<String>();
    l3.add("a");
    l3.add("b");
    l3.add("1");
    l3.add("2");
    assertEquals(true, system.scanChecking(l2, l1));
    assertEquals(false, system.scanChecking(l3, l1));
  }


  /**
   * Test sequencerScanning() method in MySystem class. tested case: scanned wrong number tested
   * case: scanned right number but not 8 fascia tested case: scanned right number and 8 fascia
   */
  @Test
  public void testSequencerScanning() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Fascia fascia3 = new Fascia("SE", "White", "3");
    Fascia fascia4 = new Fascia("SE", "White", "4");
    Fascia fascia5 = new Fascia("SSE", "White", "5");
    Fascia fascia6 = new Fascia("SSE", "White", "6");
    Fascia fascia7 = new Fascia("SSR", "White", "7");
    Fascia fascia8 = new Fascia("SSR", "White", "8");
    Order order1 = new Order(fascia1, fascia2);
    Order order2 = new Order(fascia3, fascia4);
    Order order3 = new Order(fascia5, fascia6);
    Order order4 = new Order(fascia7, fascia8);
    List<Order> orderlist = new ArrayList<Order>();
    orderlist.add(order1);
    orderlist.add(order2);
    orderlist.add(order3);
    orderlist.add(order4);
    system.setSavedlist(orderlist);
    system.sequencerScanning("2", "Alice", "scan");
    assertTrue(system.getSavedList().get(0).equals("1"));
    assertEquals(0, system.getScannedSkuList().size());
    system.sequencerScanning("1", "Alice", "scan");
    system.sequencerScanning("2", "Alice", "scan");
    system.sequencerScanning("3", "Alice", "scan");
    system.sequencerScanning("4", "Alice", "scan");
    assertEquals(4, system.getScannedSkuList().size());
    system.sequencerScanning("5", "Alice", "scan");
    system.sequencerScanning("6", "Alice", "scan");
    system.sequencerScanning("7", "Alice", "scan");
    system.sequencerScanning("8", "Alice", "scan");
    assertEquals(8, system.getScannedSkuList().size());
  }

  /**
   * Test loading() method in MySystem class.
   */
  @Test
  public void testLoading() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Fascia fascia3 = new Fascia("SE", "White", "3");
    Fascia fascia4 = new Fascia("SE", "White", "4");
    Fascia fascia5 = new Fascia("SSE", "White", "5");
    Fascia fascia6 = new Fascia("SSE", "White", "6");
    Fascia fascia7 = new Fascia("SSR", "White", "7");
    Fascia fascia8 = new Fascia("SSR", "White", "8");
    Order order1 = new Order(fascia1, fascia2);
    Order order2 = new Order(fascia3, fascia4);
    Order order3 = new Order(fascia5, fascia6);
    Order order4 = new Order(fascia7, fascia8);
    List<Order> orderlist = new ArrayList<Order>();
    orderlist.add(order1);
    orderlist.add(order2);
    orderlist.add(order3);
    orderlist.add(order4);
    Truck truck = system.getTruck();
    truck.getListRequestId().add(1);
    truck.getListRequestId().add(2);
    truck.getListRequestId().add(3);
    truck.getListRequestId().add(4);
    truck.getListRequestId().add(5);
    truck.getListRequestId().add(6);
    truck.getListRequestId().add(7);
    truck.getListRequestId().add(8);
    truck.getListRequestId().add(9);
    system.setSavedlist(orderlist);
    system.loading("2", "Alice", "scan");
    assertEquals(9, truck.getListRequestId().size());
    assertEquals("1", system.getSavedList().get(0));
    assertEquals(0, system.getLoadedList().size());
    system.loading("Alice", "1", "scan");
    system.loading("Alice", "2", "scan");
    system.loading("Alice", "3", "scan");
    system.loading("Alice", "4", "scan");
    assertEquals(4, system.getLoadedList().size());
    system.loading("Alice", "5", "scan");
    system.loading("Alice", "6", "scan");
    system.loading("Alice", "7", "scan");
    system.loading("Alice", "8", "scan");
    assertEquals(8, system.getLoadedList().size());
    assertEquals(0, truck.getListRequestId().size());
  }

  /**
   * Test setListRequestIdOfTruck() method in MySystem class.
   */
  @Test
  public void testSetListRequestIdOfTruck() {
    Fascia fascia1 = new Fascia("S", "White", "1");
    Fascia fascia2 = new Fascia("S", "White", "2");
    Order order = new Order(fascia1, fascia2);
    Request request = new Request();
    request.formRequest(order, order, order, order);
    system.getRequestList().add(request);
    system.setListRequestIdOfTruck();
    assertEquals((Integer) 1, system.getTruck().getListRequestId().get(0));
  }

  /**
   * Test replenishing() method in MySystem class, the case which replenishList has fascias.
   */
  @Test
  public void testReplenishing1() {
    Fascia fascia = new Fascia("S", "White", "1");
    system.getReplenishList().add(fascia);
    system.getWareHouse().getFloor().getPickFace().put("A 0 0 0", 5);
    system.replenishing("Mike", "A 0 0 0");
    int quantity = system.getWareHouse().getFloor().getPickFace().get("A 0 0 0");
    assertEquals(30, quantity);
  }

  /**
   * Test replenishing() method in MySystem class, the case which replenishList has no fascias.
   */
  @Test
  public void testReplenishing2() {
    system.getWareHouse().getFloor().getPickFace().put("A 0 0 0", 30);
    system.replenishing("Mike", "A 0 0 0");
    int quantity = system.getWareHouse().getFloor().getPickFace().get("A 0 0 0");
    assertEquals(30, quantity);
  }
}
