package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import warehouse.WareHousePicking;

public class WareHousePickingTest {

  /**
   * Create a new WareHousePicking to test methods in WareHousePicking class.
   * 
   * @throws Exception throw the exceptions just in case
   */

  @Before
  public void setUp() throws Exception {
    new WareHousePicking();
  }

  /**
   * Test optimize() method in WareHousePicking class.
   */
  @Test
  public void testWareHousePickingOptimize() throws IOException {
    Map<Integer, String> expected = new HashMap<>();
    expected.put(1, "A,0,0,3");
    expected.put(2, "A,0,1,2");
    expected.put(3, "A,0,2,0");
    expected.put(4, "A,1,2,1");
    expected.put(5, "B,0,1,2");
    expected.put(6, "B,0,1,3");
    expected.put(7, "B,1,1,3");
    expected.put(8, "B,1,2,1");
    List<String> sku = new ArrayList<String>();
    sku.add("4");
    sku.add("7");
    sku.add("9");
    sku.add("22");
    sku.add("31");
    sku.add("32");
    sku.add("44");
    sku.add("46");

    assertEquals(expected, WareHousePicking.optimize(sku));
  }

  /**
   * Test optimize() method in WareHousePicking class, the case which traversal_table.csv does not
   * contain some specific input sku.
   */
  @Test
  public void testWareHousePickingOptimize2() throws IOException {
    Map<Integer, String> expected = new HashMap<>();
    expected.put(1, "A,0,0,3");
    expected.put(2, "A,0,1,2");
    expected.put(3, "A,0,2,0");
    expected.put(4, "A,1,2,1");
    expected.put(5, "B,0,1,2");
    expected.put(6, "B,0,1,3");
    expected.put(7, "B,1,1,3");
    expected.put(8, "B,1,2,1");
    List<String> sku = new ArrayList<String>();
    sku.add("4");
    sku.add("7");
    sku.add("9");
    sku.add("22");
    sku.add("31");
    sku.add("32");
    sku.add("44");
    sku.add("49");

    assertFalse(expected.equals(WareHousePicking.optimize(sku)));
  }

  /**
   * Test optimize() method in WareHousePicking class, the case which list of skus' size is larger
   * than 8.
   */
  @Test
  public void testWareHousePickingOptimize3() throws IOException {
    Map<Integer, String> expected = new HashMap<>();
    expected.put(1, "A,0,0,3");
    expected.put(2, "A,0,1,2");
    expected.put(3, "A,0,2,0");
    expected.put(4, "A,1,2,1");
    expected.put(5, "B,0,1,2");
    expected.put(6, "B,0,1,3");
    expected.put(7, "B,1,1,3");
    expected.put(8, "B,1,2,1");
    expected.put(9, "A,0,0,0");
    List<String> sku = new ArrayList<String>();
    sku.add("4");
    sku.add("7");
    sku.add("9");
    sku.add("22");
    sku.add("31");
    sku.add("32");
    sku.add("44");
    sku.add("46");
    sku.add("1");

    assertFalse(expected.equals(WareHousePicking.optimize(sku)));
  }

}
