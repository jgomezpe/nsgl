package nsgl.bit.variation;

import nsgl.bit.random.Random;
import nsgl.bit.array.Array;
import nsgl.search.variation.ParameterizedObject;
import nsgl.search.variation.Variation_1_1;

/**
 * <p>Title: Mutation</p>
 * <p>Description: The simple bit mutation operator</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class BitMutation implements Variation_1_1<Array>, ParameterizedObject<Double> {
  /**
   * Probability of mutating one single bit
   */
  protected double bit_mutation_rate = 0.0;

  /**
   * Constructor: Creates a mutation with a mutation probability depending on the size of the genome
   */
  public BitMutation() {}

  /**
   * Constructor: Creates a mutation with the given mutation rate
   * @param bit_mutation_rate Probability of mutating each single bit
   */
  public BitMutation(double bit_mutation_rate) {
    this.bit_mutation_rate = bit_mutation_rate;
  }

  /**
   * Flips a bit in the given genome
   * @param gen Genome to be modified
   * @return Number of mutated bits
   */
  @Override
  public Array apply(Array gen) {
    try{
      Array genome = (Array)gen.copy();
      double rate = 1.0 - ((bit_mutation_rate == 0.0)?1.0/genome.size():bit_mutation_rate);
      Random g = new Random(rate);
      for (int i = 0; i < genome.size(); i++) if (g.next()) genome.not(i);
      return genome;
    }catch( Exception e ){ System.err.println("[Mutation]" + e.getMessage()); }
    return null;
  }

  @Override
  public void setParameters(Double parameters){ bit_mutation_rate = parameters; }

  @Override
  public Double getParameters(){ return bit_mutation_rate; }
}
