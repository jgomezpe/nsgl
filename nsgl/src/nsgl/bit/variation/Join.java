package nsgl.bit.variation;
import nsgl.bit.array.Array;
import nsgl.search.variation.Variation_2_2;

/**
 * <p>Title: Join</p>
 * <p>Description: Joins two chromosomes</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Join implements Variation_2_2<Array>{
  /**
   * Apply the simple point crossover operation over the given genomes
   * @param c1 The first parent
   * @param c2 The second parent
   */
  public Array[] apply(Array c1, Array c2) {
      try {
          Array genome1 = (Array)c1.copy();
          genome1.add(c2);
          Array genome2 = (Array)c2.copy();
          genome2.add(c1);
          return new Array[]{genome1, genome2};
      } catch (Exception e) {
      }
      return null;
  }
}