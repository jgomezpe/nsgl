/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.real.variation;

import nsgl.random.Pick;
import nsgl.real.random.PowerLaw;
import nsgl.real.random.Symmetric;

/**
 *
 * @author jgomez
 */
public class PowerLawMutation extends IntensityMutation {
  /**
   * Creates a Gaussian Mutation with the given standard deviation per component
   * @param _sigma Standard deviation per component
   */
  public PowerLawMutation( double sigma ) {
      super(sigma, new Symmetric(new PowerLaw()));
  }

    public PowerLawMutation( double sigma, Pick components){
        super(sigma, new Symmetric(new PowerLaw()), components);
    }
}
