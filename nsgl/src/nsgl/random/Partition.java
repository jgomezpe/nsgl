/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package nsgl.random;

import java.util.Iterator;

/**
 * <p>Title: Partition</p>
 *
 * <p>Description: Random partition of a set.</p>
 *
 */
public class Partition {
    /**
     * Random distribution of index
     */
    protected int[] index = null;

    /**
     * Number of groups
     */
    protected int m; 
    
    /**
     * Creates a random partition of <i>m</i> groups equal size from a set of <i>n</i> elements 
     */
    public Partition(int n, int m) {
	this(nsgl.integer.array.Util.shuffle(n), m);
    }
    
    /**
     * Creates a partition of <i>m</i> groups equal size from a set of elements 
     */
    public Partition(int[] set, int m) {
	this.index = set;
	this.m = m;
    }
    
    public Iterator<Integer> group(int k){
	return new Iterator<Integer>() {
	    protected int pos=k;

	    @Override
	    public boolean hasNext() {
		return pos<index.length;
	    }

	    @Override
	    public Integer next() {
		int i=index[pos];
		pos+=m;
		return i;
	    }
	};
    }

    public Iterator<Integer> skip_group(int k){
	return new Iterator<Integer>() {
	    protected int pos=0;

	    @Override
	    public boolean hasNext() {
		if(pos%k==0) pos++;
		return pos<index.length;
	    }

	    @Override
	    public Integer next() {
		int i=index[pos];
		pos+=m;
		return i;
	    }
	};
    }
}