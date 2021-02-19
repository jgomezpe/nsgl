/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nsgl.real.variation;
import nsgl.random.Pick;
import nsgl.real.random.Random;
import nsgl.search.variation.ParameterizedObject;

/**
 *
 * @author jgomezpe
 */
public class IntensityMutation extends Mutation implements ParameterizedObject<Double>{
    // Mutation definitions
    protected double sigma;
    protected Random g;
    protected double[] delta = new double[0];
    
    public IntensityMutation( double sigma, Random g, 
                              Pick components){
        super(components);
        this.sigma = sigma;
        this.g = g;
    }
    
    public IntensityMutation(double sigma, Random g ){
        this( sigma, g, null);
    }
    
    protected double[] delta(int DIMENSION){
    	if( delta.length == DIMENSION ) for( int i=0; i<indices.length; i++) delta[indices[i]]=0.0;
    	else delta = new double[DIMENSION];
        if( components != null ){
            indices = components.get(DIMENSION);
            for( int i=0; i<indices.length; i++ ) delta[indices[i]] =  sigma*g.next();
        }else for( int i=0; i<delta.length; i++) delta[i] =  sigma*g.next();
        return delta;
    }
        
    @Override
    public double[] apply( double[] x ){
        double[] y = x.clone();
        double[] z = delta(x.length);
        for( int i=0; i<y.length; i++){
            y[i] += z[i];
        }
        return y;
    }

   /* @TODO: What about productivity 
    @Override
    public void adapt( double productivity ){
        if( adapt != null ){
            sigma = adapt.apply(sigma, productivity);
        }
    }
  */
    
    
  /**
   * Class of objects the operator is able to process
   * @return Class of objects the operator is able to process
   */
  public Object owner(){
      return double[].class;
  }

  @Override
  public void setParameters(Double parameters) {
	sigma = parameters;	
  }    
  
  @Override
  public Double getParameters(){ return sigma; } 
}