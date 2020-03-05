package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import warehouse.Fascia;
import warehouse.Pallet;

public class PalletTest {

  /** Set up a new pallet. */
  private Pallet pallet;

  /**
   * Create a new pallet to test methods in Pallet class.
   */
  @Before
  public void before() {
    pallet = new Pallet();
  }

  /**
   * Test getPallet() method in Pallet class.
   */
  @Test
  public void testGetPallet() {
    List<Fascia> testFascia = new ArrayList<>();
    assertEquals(testFascia, pallet.getPallet());
  }

  /**
   * Test palletConfirmed() method in Pallet class.
   */
  @Test
  public void testPalletConfirmed() {
    List<Fascia> list = new ArrayList<Fascia>();
    Fascia f1 = new Fascia("S", "White", "2");
    Fascia f2 = new Fascia("SES", "Tan", "78");
    Fascia f3 = new Fascia("SEL", "Gold", "72");
    Fascia f4 = new Fascia("S", "Silver", "89");
    list.add(f1);
    list.add(f2);
    list.add(f3);
    list.add(f4);
    pallet.palletConfirmed(f1, f2, f3, f4);
    assertEquals(list, pallet.getPallet());
  }
}
