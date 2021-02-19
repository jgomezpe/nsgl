package nsgl.real.variation;

import nsgl.random.Pick;
import nsgl.real.random.Gaussian;

/**
 * <p>Title: GaussianMutation</p>
 * <p>Description: Changes one component of the encoded double[] with a number
 * randomly generated following a Gaussian distribution with mean the old value of
 * the component and the given standard deviation</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class GaussianMutation extends IntensityMutation {
  /**
   * Creates a Gaussian Mutation with the given standard deviation per component
   * @param _sigma Standard deviation per component
   */
  public GaussianMutation( double sigma ) {
      super(sigma, new Gaussian());
  }

    public GaussianMutation(double sigma, Pick components){
        super(sigma, new Gaussian(), components);
    }
}