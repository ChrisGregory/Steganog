import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;

public class PrimeSuspects
{
  public File embedIntoImage(File cleanImage, String message)
    throws IOException
  {
    BufferedImage bufferedImage = ImageIO.read(cleanImage);
    PrimeIterator primeIterator = new PrimeIterator();
    int pixelNumber = 0;
    int nextPrime = primeIterator.next().intValue();
    int pixelsAnalyzed = 0;
    for (int x = 0; x < bufferedImage.getWidth(); x++) {
      for (int y = 0; y < bufferedImage.getHeight(); y++)
      {
        pixelNumber++;
        if (pixelNumber == nextPrime)
        {
          if (message.length() > pixelsAnalyzed)
          {
            Color newColor = charToPixel(
              (char)(message.charAt(pixelsAnalyzed) - ' '), 
              new Color(bufferedImage.getRGB(x, y)));
            bufferedImage.setRGB(x, y, newColor.getRGB());
          }
          nextPrime = primeIterator.next().intValue();
          pixelsAnalyzed++;
        }
      }
    }
    System.out.println("Pixels in image: " + pixelNumber + 
      ". PixelsAnalyzed: " + pixelsAnalyzed + ". HighestPrime: " + 
      nextPrime + ".");
    File newImage = new File("EncryptedImage.png");
    ImageIO.write(bufferedImage, "PNG", newImage);
    return newImage;
  }
  
  public String retrieveFromImage(File imageWithEmbeddedMessage)
    throws IOException
  {
    BufferedImage bufferedImage = ImageIO.read(imageWithEmbeddedMessage);
    PrimeIterator primeIterator = new PrimeIterator();
    int pixelNumber = 0;
    int nextPrime = primeIterator.next().intValue();
    int pixelsAnalyzed = 0;
    String message = "";
    for (int x = 0; x < bufferedImage.getWidth(); x++) {
      for (int y = 0; y < bufferedImage.getHeight(); y++)
      {
        pixelNumber++;
        if (pixelNumber == nextPrime)
        {
          message = 
            message + (char)(pixelToChar(new Color(
            bufferedImage.getRGB(x, y))) + ' ');
          nextPrime = primeIterator.next().intValue();
          pixelsAnalyzed++;
        }
      }
    }
    System.out.println("Pixels in image: " + pixelNumber + 
      ". PixelsAnalyzed: " + pixelsAnalyzed + ". HighestPrime: " + 
      nextPrime + ".");
    return message;
  }
  boolean first = true;
  public char pixelToChar(Color color)
  {
    String hex = colorToHex(color);
    

    String binaryRed = hexToBinary(hex.substring(1, 3));
    String binaryGreen = hexToBinary(hex.substring(3, 5));
    String binaryBlue = hexToBinary(hex.substring(5, 7));
    
    String charCode = binaryRed.substring(6, 8) + 
      binaryGreen.substring(6, 8) + binaryBlue.substring(6, 8);
    if(first)
    {
    	System.out.println(charCode);
    	first = false;
    }
    int charCodeInt = binaryToInteger(charCode) + 32;
    char result = (char)charCodeInt;
    

    return result;
  }
  
  public Color charToPixel(char character, Color startingPoint)
  {
    int charCode = character - 32;
    String binaryCharCode = integerToBinary(charCode);
    


    String hex = colorToHex(startingPoint);
    String binaryRed = hexToBinary(hex.substring(1, 3));
    String binaryGreen = hexToBinary(hex.substring(3, 5));
    String binaryBlue = hexToBinary(hex.substring(5, 7));
    

    binaryRed = binaryRed.substring(0, 6) + binaryCharCode.substring(2, 4);
    binaryGreen = binaryGreen.substring(0, 6) + 
      binaryCharCode.substring(4, 6);
    binaryBlue = binaryBlue.substring(0, 6) + 
      binaryCharCode.substring(6, 8);
    
    hex = '#' + binaryToHex(binaryRed) + binaryToHex(binaryGreen) + 
      binaryToHex(binaryBlue);
    

    Color result = hexToColor(hex);
    
    return result;
  }
  
  public static int binaryToInteger(String binary)
  {
    int result = Integer.parseInt(binary, 2);
    return result;
  }
  
  public static String integerToBinary(int integer)
  {
    String result = Integer.toString(integer, 2);
    while (result.length() < 8) {
      result = "0" + result;
    }
    return result;
  }
  
  public static String hexToBinary(String hex)
  {
    String result = Integer.toBinaryString(Integer.parseInt(hex, 16));
    while (result.length() < 8) {
      result = "0" + result;
    }
    return result;
  }
  
  public static String binaryToHex(String binary)
  {
    String result = Integer.toHexString(Integer.parseInt(binary, 2));
    if (result.length() < 2) {
      result = "0" + result;
    }
    return result;
  }
  
  public static String colorToHex(Color color)
  {
    String result = Integer.toHexString(color.getRGB() & 0xFFFFFF);
    if (result.length() < 6) {
      result = "000000".substring(0, 6 - result.length()) + result;
    }
    return "#" + result;
  }
  
  public static Color hexToColor(String hex)
  {
    Color result = new Color(Integer.valueOf(hex.substring(1, 3), 16).intValue(), 
      Integer.valueOf(hex.substring(3, 5), 16).intValue(), Integer.valueOf(
      hex.substring(5, 7), 16).intValue());
    return result;
  }
}
