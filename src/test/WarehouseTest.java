package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import warehouse.Fascia;
import warehouse.Floor;
import warehouse.WareHouse;

public class WarehouseTest {

  /** Set up a new warehouse. */
  private WareHouse warehouse;

  /**
   * Create a new warehouse to test methods in WareHouse class.
   */
  @Before
  public void setUp() {
    warehouse = new WareHouse();
  }

  /**
   * Test getStorage() method in WareHouse class.
   */
  @Test
  public void testGetStorage() {
    Map<Fascia, String> storage = new HashMap<>();
    assertEquals(storage, warehouse.getStorage());
  }

  /**
   * Test getSkuToLocation() method in WareHouse class.
   */
  @Test
  public void testGetSkuToLocation() {
    Map<Fascia, String> map = new HashMap<>();
    assertEquals(map, warehouse.getSkuToLocation());
  }

  /**
   * Test getOrderList() method in WareHouse class.
   */
  @Test
  public void testWareHouseGetOrderList() {
    assertEquals(0, warehouse.getOrderList().size());
  }

  /**
   * Test getFloor() method in WareHouse class.
   */
  @Test
  public void testWareHouseGetFloor() {
    Floor floor = new Floor();
    Map<String, Integer> testPickFace = floor.getPickFace();
    assertEquals(testPickFace, warehouse.getFloor().getPickFace());
  }

  /**
   * Test getMap() method in WareHouse class.
   */
  @Test
  public void testGetMap() {
    Map<Fascia, String> testMap = new HashMap<>();
    assertEquals(testMap, warehouse.getMap());
  }

  /**
   * Test getLocationToFascia() method in WareHouse class.
   */
  @Test
  public void testGetLocationToFascia() {
    Map<Fascia, String> testLocationToFascia = new HashMap<>();
    assertEquals(testLocationToFascia, warehouse.getLocationToFascia());
  }
}
