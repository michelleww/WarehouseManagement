package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import warehouse.Fascia;
import warehouse.Order;
import warehouse.Request;

public class RequestTest {

  /** Set up a new request. */
  private Request request;
  /** Set up a new fascia. */
  Fascia f1 = new Fascia("S", "White", "2");
  /** Set up a new fascia. */
  Fascia f2 = new Fascia("SES", "Tan", "78");
  /** Set up a new fascia. */
  Fascia f3 = new Fascia("SEL", "Gold", "72");
  /** Set up a new fascia. */
  Fascia f4 = new Fascia("S", "Silver", "89");
  /** Set up a new order. */
  Order o1 = new Order(f1, f2);
  /** Set up a new order. */
  Order o2 = new Order(f1, f3);
  /** Set up a new order. */
  Order o3 = new Order(f3, f4);
  /** Set up a new order. */
  Order o4 = new Order(f3, f2);

  /**
   * Create a new request to test methods in Request class.
   */
  @Before
  public void before() {
    request = new Request();
  }

  /**
   * Test getOrders() method in Request class.
   */
  @Test
  public void testgetOrders() {
    List<Order> orders = new ArrayList<Order>();
    assertEquals(orders, request.getOrders());
  }

  /**
   * Test getPicker() method in Request class.
   */
  @Test
  public void getPicker() {
    String picker = null;
    assertEquals(picker, request.getPicker());
  }

  /**
   * Test setPicker() method in Request class.
   */
  @Test
  public void setPicker() {
    request.setPicker("Alice");
    assertEquals("Alice", request.getPicker());
  }

  /**
   * Test formRequest() method in Request class.
   */
  @Test
  public void formRequest() {
    request.formRequest(o1, o2, o3, o4);

    List<Order> list = new ArrayList<>();
    list.add(o1);
    list.add(o2);
    list.add(o3);
    list.add(o4);
    assertEquals(list, request.getOrders());
  }

  /**
   * Test requestConfirmed() method in Request class.
   */
  @Test
  public void requestConfirmed() {
    assertFalse(request.requestConfirmed());
  }

  /**
   * Test isPickready() method in Request class.
   */
  @Test
  public void testisPickready() {
    assertFalse(request.isPickready());
  }

  /**
   * Test setPickready() method in Request class.
   */
  @Test
  public void testsetPickready() {
    request.setPickready();
    assertTrue(request.isPickready());
  }

  /**
   * Test requestConfirmed() method in Request class.
   */
  @Test
  public void testRequestConfirmed() {
    request.formRequest(o1, o2, o3, o4);
    assertTrue(request.requestConfirmed());
  }
}
