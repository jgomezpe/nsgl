package nsgl.real.variation;

import nsgl.array.Array;
import nsgl.search.space.Space;

/**
 * <p>Title: OneDimensionSimpleXOver</p>
 * <p>Description:Exchanges one component of the first individual with
 * the same component of the second individual</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class OneDimensionSimpleXOver extends SimpleXOver {
  /**
   * Default constructor
   */
  public OneDimensionSimpleXOver() {}

  /**
   * Apply the 2-ary genetic operator over the individual genomes
   * @param c1 First Individuals genome to be modified by the genetic operator
   * @param c2 Second Individuals genome to be modified by the genetic operator
   * @return extra information of the genetic operator
   */
  public Array<double[]> generates(Space<double[]> space, double[] c1, double[] c2) {
      try {
          double[] x = c1.clone();
          double[] y = c2.clone();
          int pos = pos(x.length, y.length);
          double t = x[pos];
          x[pos] = y[pos];
          y[pos] = t;
          Array<double[]> v = new Array<double[]>();
          v.add(x);
          v.add(y);
          return v;
      } catch (Exception e) {
      }
      return null;
  }
}
