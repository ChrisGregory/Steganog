import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Program
{
  public static void main(String[] args)
    throws IOException
  {
    PrimeSuspects ps = new PrimeSuspects();
    

    String message = "";
    File cleanImage = new File("C:/Users/cgregory/workspace/Steganog/CleanImage.png");
    System.out.println("File Pre-Encryption: " + ps.retrieveFromImage(cleanImage));
    File encryptedImage = ps.embedIntoImage(cleanImage, message);
    
    encryptedImage = new File("C:/Users/cgregory/workspace/Steganog/CleanImage.png");
    System.out.println("File Post-Encryption: " + ps.retrieveFromImage(encryptedImage));
  }
  
  public static void printPrimes()
  {
    PrimeIterator pi = new PrimeIterator();
    for (int i = 0; i < 500; i++) {
      System.out.println(pi.next());
    }
  }
  
  public static void chartest()
  {
    for (int i = 0; i < 200; i++) {
      System.out.println((char)i + " - " + i);
    }
  }
}
