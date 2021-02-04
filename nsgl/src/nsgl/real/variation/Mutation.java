/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nsgl.real.variation;

import nsgl.random.Pick;
import nsgl.search.variation.Variation_1_1;

/**
 *
 * @author jgomezpe
 */
public abstract class Mutation implements Variation_1_1<double[]>{
    // Mutation definitions
    protected Pick components = null;
    protected int[] indices = new int[0];
    
    public Mutation(Pick components){ this.components = components; }
    
    public Mutation(){}            
}