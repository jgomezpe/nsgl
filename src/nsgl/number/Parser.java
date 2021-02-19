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
package nsgl.number;

import nsgl.language.lexeme.Lexeme;
import nsgl.service.io.Source;
import nsgl.service.io.Token;

/**
 * <p>Title: RealRecover</p>
 *
 * <p>Description: Parse (Load from a String) a real number (Double)</p>
 *
 */
public class Parser implements Lexeme<Number>{
    public static final String TAG = "number";
	public String type() { return TAG; }
	
	public static boolean issign(char c){ return ('-'==c || c=='+'); }

	public boolean startsWith(char c){ return issign(c) || Character.isDigit(c); }

	public Token match(Source txt, int start, int end){
		if(!this.startsWith(txt.get(start)))
		    return error(txt, start, start);
		int n = end;
		end=start+1;
		while(end<n && Character.isDigit(txt.get(end))) end++;
		if(end==n) 
		    return token(txt, start, end, Integer.parseInt(txt.substring(start,end)));
		boolean integer = true;
		if(txt.get(end)=='.'){
		    integer = false;
			end++;
			int s=end;
			while(end<n && Character.isDigit(txt.get(end))) end++;
			if(end==n) 
			    return token(txt, start, end, Double.parseDouble(txt.substring(start,end)));
			if(end==s) return error(txt, start, end);
		}
		if(txt.get(end)=='E' || txt.get(end)=='e'){
		    integer = false;
			end++;
			if(end==n) return error(txt, start, end);
			if(issign(txt.get(end))) end++;
			if(end==n) return error(txt, start, end);
			int s = end;
			while(end<n && Character.isDigit(txt.get(end))) end++;
			if(end==s) return error(txt, start, end);
		}
		if( integer ) return token(txt, start, end, Integer.parseInt(txt.substring(start,end)));
		return token(txt, start, end, Double.parseDouble(txt.substring(start,end)));
	}	
}