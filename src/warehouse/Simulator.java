package warehouse;

import event.EventProcessor;
import event.FileHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;


public class Simulator {

  /** processor of this simulator. */
  private EventProcessor processor;
  /** mySystem of this simulator. */
  private MySystem mySystem;

  /**
   * Construct a new simulator for reading input files
   * 
   * @param orderFile the myorders.txt
   * @param setUpFile the translation.csv
   * @param traversalFile the traversal.csv
   * @param initialFile the initial.csv
   * @throws IOException throw the exceptions just in case
   */
  public Simulator(String orderFile, String setUpFile, String traversalFile, String initialFile)
      throws IOException {
    processor = new EventProcessor(orderFile);
    mySystem = new MySystem();
    this.initWareHouse(setUpFile, traversalFile);
    this.initFloor(traversalFile, initialFile);
  }

  /**
   * simulate the system with commands and event.process
   * 
   * @param orderFile the input orderfile for the system
   * @throws IOException throw the exceptions just in case
   * @throws Exception throw the general exceptions just in case
   */
  public void simulate(String orderFile) throws IOException, Exception {
    for (String line : FileHelper.readFile(orderFile)) {
      mySystem.getLogger().log(Level.INFO, "Line from event file: " + line);
      processor.process(line, mySystem);
    }
  }

  /**
   * Set up the warehouse of mySystem
   * 
   * @param setUpFile the translation.csv
   * @param traversalFile  the traversal_table.csv
   * @throws IOException throw the exceptions just in case
   */
  public void initWareHouse(String setUpFile, String traversalFile) throws IOException {
    List<String> linesFascia = FileHelper.readCsvFile(setUpFile);
    List<String> linesLocation = FileHelper.readFile(traversalFile);
    Map<String, String> temp = new HashMap<>();
    for (String locations : linesLocation) {
      String subLine = locations.substring(0, 7);
      String[] split = locations.split(",");
      temp.put(split[4], subLine);
      mySystem.getWareHouse().getSkuToLocation().put(split[4], subLine);
    }
    for (String fascia : linesFascia) {
      String[] split = fascia.split(",");

      String skuFront = split[2];
      String skuBack = split[3];

      Fascia fasciaFront = new Fascia(split[1], split[0], skuFront);
      Fascia fasciaBack = new Fascia(split[1], split[0], skuBack);

      mySystem.getWareHouse().getStorage().put(fasciaFront, temp.get(fasciaFront.getSku()));
      mySystem.getWareHouse().getStorage().put(fasciaBack, temp.get(fasciaBack.getSku()));

      mySystem.getWareHouse().getLocationToFascia().put(temp.get(fasciaFront.getSku()),
          fasciaFront);
      mySystem.getWareHouse().getLocationToFascia().put(temp.get(fasciaBack.getSku()), fasciaBack);

      Order newOrder = new Order(fasciaFront, fasciaBack);
      mySystem.getWareHouse().getOrderList().add(newOrder);

      mySystem.getWareHouse().getMap().put(split[1] + " " + split[0], split[2] + " " + split[3]);
    }
  }

  /**
   * Set up the floor of this warehouse
   * 
   * @param traversalFile the traversal_table.csv
   * @param initialFile the initial.csv
   * @throws IOException throw the exceptions just in case
   */
  public void initFloor(String traversalFile, String initialFile) throws IOException {
    List<String> trasversalLines = FileHelper.readFile(traversalFile);
    List<String> initialLines = FileHelper.readFile(initialFile);
    for (String line : trasversalLines) {
      String subline = line.substring(0, 7);
      mySystem.getWareHouse().getFloor().getPickFace().put(subline, 30);
    }
    for (String line : initialLines) {
      String subline = line.substring(0, 7);
      String[] value = line.split(",");
      int amount = Integer.valueOf(value[4]);
      mySystem.getWareHouse().getFloor().getPickFace().replace(subline, amount);
    }
  }

  /**
   * Show how many left for each fascia in this pick face of this warehouse
   * 
   * @param fileName the file name of target .csv file to show left fascias.
   * @throws IOException throw the exceptions just in case
   */
  public void showPickFace(String fileName) throws IOException {
    List<String> lines = new ArrayList<>();
    Set<Entry<String, Integer>> mySet = mySystem.getWareHouse().getFloor().getPickFace().entrySet();
    for (Entry<String, Integer> entry : mySet) {
      String store = entry.getKey() + "," + entry.getValue();
      lines.add(store);
    }
    FileHelper.writeFile(fileName, lines);
  }

  /**
   * The main method for Simulator.
   * 
   * @param args the parameter for a main method
   * @throws Exception throw the exceptions just in case
   */
  public static void main(String[] args) throws Exception {
    Simulator simulator =
        new Simulator("myorders.txt", "translation.csv", "traversal_table.csv", "initial.csv");
    simulator.simulate(args[0]);
    simulator.showPickFace("final.csv");
    simulator.mySystem.getTruck().showSequencedOrders("orders.csv",
        simulator.mySystem.getTruck().getListSequencedOrders());
  }

  /**
   * Get the system from the simulator for testing.
   * 
   * @return MySystem
   */
  public MySystem getSystem() {
    return mySystem;
  }
}
