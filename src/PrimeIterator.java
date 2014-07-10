import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class PrimeIterator
  implements Iterator<Integer>
{
  private static LinkedList<Integer> temporaryPrimes = new LinkedList();
  private ListIterator<Integer> primesIterator;
  
  public PrimeIterator()
  {
    this.primesIterator = temporaryPrimes.listIterator();
    if (!this.primesIterator.hasNext())
    {
      this.primesIterator.add(Integer.valueOf(2));
      this.primesIterator.add(Integer.valueOf(3));
      this.primesIterator.previous();
      this.primesIterator.previous();
    }
  }
  
  public static boolean isPrime(int number)
  {
    boolean prime = true;
    if (number % 2 == 0) {
      prime = false;
    }
    for (int i = 3; i * i <= number; i += 2) {
      if (number % i == 0) {
        prime = false;
      }
    }
    return prime;
  }
  
  public boolean hasNext()
  {
    return true;
  }
  
  public void calculateNext()
  {
    int number = ((Integer)this.primesIterator.previous()).intValue() + 2;
    this.primesIterator.next();
    while (!isPrime(number)) {
      number++;
    }
    this.primesIterator.add(Integer.valueOf(number));
    this.primesIterator.previous();
  }
  
  public Integer next()
  {
    if (!this.primesIterator.hasNext()) {
      calculateNext();
    }
    return (Integer)this.primesIterator.next();
  }
  
  public void remove() {}
}
