package nsgl.function;

import nsgl.array.Array;

public class CodeDecodeMap<G,P>{
	/**
	   * Generates a thing from the given genome
	   * @param genome Genome of the thing to be expressed
	   * @return A thing expressed from the genome
	   */
	  @SuppressWarnings("unchecked")
	public P decode(G genome) { return (P)genome; }

	  /**
	   * Generates a genome from the given thing
	   * @param thing A thing expressed from the genome
	   * @return Genome of the thing
	   */
	  @SuppressWarnings("unchecked")
	public G code(P thing) { return (G)thing; }

	  /**
	   * Generates a population of individuals from the given collection of genomes
	   * @param genomes Genomes used for building the population of individuals
	   * @return Population of individuals built upon the given genomes
	   */
	  public Array<P> decodeArray( Array<G> genomes ){
	      G genome;
	      Array<P> v = new Array<P>();
	      try{
	    	  int n = genomes.size();
	    	  for( int i=0; i<n; i++ ){
	    		  genome = genomes.get(i);
	    		  v.add( decode(genome) );
	    	  }
	      }catch(Exception e){}
	      return v;
	  }

	  /**
	   * Obtains the first <i>n</i> candidate solutions genomes of the population
	   * @param n Number of candidate solutions genomes to obtain
	   * @param population Population used for obtaining the first <i>n</i> genomes
	   * @return The first <i>n</i> candidate solutions genomes of the population
	   */
	  public Array<G> codeArray( int n, Array<P> population ){
	      Array<G> genomes = new Array<G>();
	      try{ for( int i=0; i<n; i++ ) genomes.add( code(population.get(i)) ); }catch(Exception e){}
	      return genomes;
	  }

	  /**
	   * Obtains the first <i>n</i> candidate solutions genomes of the population
	   * @param n Number of candidate solutions genomes to obtain
	   * @param population Population used for obtaining the first <i>n</i> genomes
	   * @return The first <i>n</i> candidate solutions genomes of the population
	   */
	  public Array<G> codeArray( Array<P> population ){
	      return codeArray( population.size(), population );
	  }
}
