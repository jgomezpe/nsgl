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
package nsgl.keymap;

/**
 * <p>Title: IndexedStringifier</p>
 *
 * <p>Description: Stringify method for Indexed collections.</p>
 *
 */
public class Stringifier implements nsgl.stringify.Stringifier{
	protected char OPEN = '{';
	protected char CLOSE = '}';
	protected char SEPARATOR = ',';
	protected char ASSIGN = ':';
	
	public Stringifier() {}
	
	public Stringifier( char open, char close, char separator, char assign ) {
	    this.CLOSE = close;
	    this.OPEN = open;
	    this.SEPARATOR = separator;
	    this.ASSIGN = assign;
	}
	
	public String stringify(KeyMap<?,?> map) {
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		if( OPEN != '\0' ) sb.append(OPEN);
		for( Object k : map.keys() ) {
			if( flag ) sb.append(SEPARATOR);
			Object v = map.obtain(k);
			sb.append(nsgl.stringify.Stringifier.apply(k));
			sb.append(ASSIGN);
			sb.append(nsgl.stringify.Stringifier.apply(v));
			flag = true;
		}
		if( CLOSE != '\0' ) sb.append(CLOSE);
		return sb.toString();
	}

	@Override
	public String stringify(Object obj) { return stringify((KeyMap<?,?>)obj); }
}