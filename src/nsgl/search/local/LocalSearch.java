/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsgl.search.local;

import nsgl.tagged.Tagged;
import nsgl.search.Search;
import nsgl.search.space.Space;

/**
 *
 * @author Jonatan
 */
public abstract class LocalSearch<T,R> extends Search<T,R> {
    
    public abstract Tagged<T> apply( Tagged<T> x, Space<T> space );
    
    @Override
    public Tagged<T> solve(Space<T> space){ return apply(new Tagged<T>(space.pick()), space); }
}