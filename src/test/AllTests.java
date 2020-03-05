package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({DefaultEventTest.class, FasciaTest.class, FileHelperTest.class, FloorTest.class,
    LoaderEventTest.class, MarshallingEventTest.class, MySystemTest.class, OrderEventTest.class,
    OrderTest.class, PalletTest.class, PickerEventTest.class, ReadyEventTest.class,
    ReplenisherEventTest.class, RequestTest.class, SequencerEventTest.class, TruckTest.class,
    WareHousePickingTest.class, WarehouseTest.class, WorkerTest.class})
public class AllTests {

}
