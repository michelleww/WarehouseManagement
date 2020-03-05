package test;

import static org.junit.Assert.assertEquals;

import event.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class FileHelperTest {

  /** Set up a new file for testing. */
  private static File dir = new File("test.txt");

  /**
   * Create a new ReadFileHelper to test methods in FileHelper class.
   */
  @Before
  public void before() {
    new FileHelper();
  }

  /**
   * Test readFile method in FileHelper class.
   * 
   * @throws IOException throw the exceptions just in case
   */
  @Test
  public void testReadFile() throws IOException {
    List<String> list = FileHelper.readFile("myorders.txt");
    String expected = "Order S White";
    assertEquals(expected, list.get(0));
  }

  /**
   * Test readCsvFile method in FileHelper class.
   * 
   * @throws IOException throw the exceptions just in case
   */
  @Test
  public void testReadCsvFile() throws IOException {
    List<String> list = FileHelper.readCsvFile("translation.csv");
    String expected = "White,S,1,2";
    assertEquals(expected, list.get(0));
  }

  /**
   * Test writeFile method in FileHelper class.
   * 
   * @throws IOException throw the exceptions just in case
   */
  @Test
  public void testWriteFile() throws IOException {
    List<String> readList = FileHelper.readFile("myorders.txt");
    List<String> list = new ArrayList<>();
    list.add("Order S White");
    String fileName = dir.getName();
    FileHelper.writeFile(fileName, list);
    List<String> writeList = FileHelper.readFile("test.txt");
    assertEquals(writeList.get(0), readList.get(0));
  }

  /**
   * Delete the output test file once finished all the tests.
   */
  @AfterClass
  public static void clean() {
    dir.delete();
  }

}
