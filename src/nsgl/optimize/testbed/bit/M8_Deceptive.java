package nsgl.optimize.testbed.bit;

import nsgl.bit.array.Array;

/**
 * <p>Title: M8_Deceptive</p>
 * <p>Description: Extended deceptive binary functions</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class M8_Deceptive extends M7_Deceptive {

  /**
   * Evaluate the max ones OptimizationFunction function over the binary array given
   * @param x Binary Array to be evaluated
   * @return the OptimizationFunction function over the binary array
   */
  public Double compute(Array x) {
    double f = super.compute(x);
    f = 5.0*Math.pow(f/5.0,15.0);
    return f;
  }
}
