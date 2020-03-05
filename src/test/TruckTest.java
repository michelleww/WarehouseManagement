package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import event.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import warehouse.Fascia;
import warehouse.Order;
import warehouse.Pallet;
import warehouse.Truck;

public class TruckTest {

  /** Set up a new truck. */
  private Truck truck;
  /** Set up a new fascia. */
  Fascia f1 = new Fascia("S", "White", "2");
  /** Set up a new fascia. */
  Fascia f2 = new Fascia("S", "White", "78");
  /** Set up a new fascia. */
  Fascia f3 = new Fascia("SEL", "Gold", "72");
  /** Set up a new fascia. */
  Fascia f4 = new Fascia("S", "Silver", "89");
  /** Set up a new order. */
  Order o1 = new Order(f1, f2);
  /** Set up a new pallet. */
  Pallet pallet = new Pallet();
  /** Set up a new file for testing. */
  private static File dir = new File("testOrder.csv");

  /**
   * Create a new truck to test methods in Truck class.
   * 
   */
  @Before
  public void before() {
    truck = new Truck();
    pallet.palletConfirmed(f1, f2, f3, f4);
  }

  /**
   * Test the getListFrontPallet() method in Truck class.
   */
  @Test
  public void testGetListFrontPallet() {
    List<Pallet> front = new ArrayList<>();
    assertEquals(front, truck.getListFrontPallet());
  }

  /**
   * Test the getListBackPallet() method in Truck class.
   */
  @Test
  public void testGetListBackPallet() {
    List<Pallet> back = new ArrayList<>();
    assertEquals(back, truck.getListBackPallet());
  }

  /**
   * Test the addFrontPallet() method in Truck class.
   */
  @Test
  public void testAddFrontPallet() {
    truck.addFrontPallet(pallet);
    assertEquals(pallet, truck.getListFrontPallet().get(0));
  }

  /**
   * Test the addBackPallet() method in Truck class.
   */
  @Test
  public void testAddBackPallet() {
    truck.addBackPallet(pallet);
    assertEquals(pallet, truck.getListBackPallet().get(0));
  }

  /**
   * Test getListSequencedOrders() method in Truck class, the list request has size 0.
   */
  @Test
  public void testGetListSequencedOrders() {
    assertEquals(0, truck.getListSequencedOrders().size());
  }

  /**
   * Test setListSequencedOrders() method in Truck class, the list request has size 1.
   */
  @Test
  public void testSetListSequencedOrders() {
    List<Order> list = new ArrayList<>();
    list.add(o1);
    truck.setListSequencedOrders(list);
    assertFalse(truck.getListSequencedOrders().get(0).isPickready());
  }

  /**
   * Test getListRequestID() method in Truck class, the list request has size 4.
   */
  @Test
  public void testGetListRequestId() {
    truck.getListRequestId().add(1);
    truck.getListRequestId().add(2);
    truck.getListRequestId().add(3);
    truck.getListRequestId().add(4);
    ArrayList<Integer> expected = new ArrayList<Integer>();
    expected.add(1);
    expected.add(2);
    expected.add(3);
    expected.add(4);
    assertEquals(expected, truck.getListRequestId());
  }

  /**
   * Test showSequencedOrders() method in Truck class.
   * 
   * @throws IOException throw the exceptions just in case
   */
  @Test
  public void testShowSequencedOrders() throws IOException {
    List<Order> order = new ArrayList<>();
    order.add(o1);
    truck.showSequencedOrders("testOrder.csv", order);
    List<String> list = FileHelper.readFile("testOrder.csv");
    String newlist = "Order,S,White";
    assertEquals(newlist, list.get(0));

  }

  /**
   * Delete the output test file once finished all the tests.
   */
  @AfterClass
  public static void clean() {
    dir.delete();
  }


}
