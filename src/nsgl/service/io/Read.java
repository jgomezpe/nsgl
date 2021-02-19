package nsgl.service.io;

import java.io.IOException;

import nsgl.json.JSON;
import nsgl.service.Service;

public interface Read<T> {
	
    @SuppressWarnings("unchecked")
    default T get(Source input, int start, int end) throws IOException{
	Token t = match(input,start,end);
	if(t.isError()) throw new IOException(t.json().stringify());
	return (T)t.value();
	
    }
 
    default T get(String input, int start, int end) throws IOException{
	return get(new Source(input), start, end);
    }
	
    default T get(String input, int start) throws IOException {
	return get(input, start, input.length());
    }
	
    default T get(String input) throws IOException {
	return get(input, 0);
    }
	
    Token match(Source input, int start, int end);
	
    default Token match(String input, int start, int end) {
	return match(new Source(input), start, end);
    }
	
    default Token match(String input, int start) {
	return match(input, start, input.length());
    }
	
    default Token match(String input) {
	return match(input, 0);
    }
	
     /**
     * Gets the recovering method associated to the given class 
     * @param cl Class that will get its recovering method
     * @return The recovering method associated to the given class, <i>null</i> otherwise
     */
    static void init() {
	if( Service.get(String.class,Read.class) != null ) return;
	Service.set(String.class, Read.class, new nsgl.string.Parser() ); 
	set(Character.class, new nsgl.character.Parser()); 
	set(Integer.class, new nsgl.integer.Parser()); 
	set(Double.class, new nsgl.real.Parser());
	set(byte[].class, new nsgl.blob.Parser());
	set(JSON.class, new nsgl.json.Parser());
    }

    /**
     * Adds an Stringifying method for the given object 
     * @param caller Class<?> that will register its stringifying method
     * @param cast Casting method
     */
    static void set( Class<?> caller, Read<?> cast ){
	init();
	Service.set(caller, Read.class, cast); 
    }	

    /**
     * Cast an object to Recoverable, if possible
     * @param obj Class<?> to be casted to Recoverable
     * @return A Recoverable version of the given object, <i>null</i> otherwise
     */
    static Read<?> get( Class<?> obj ){
	init();
	if(obj==null) return null;
	return (Read<?>)Service.get(obj,Read.class);
    }
    
    static Object get( Class<?> target, String input, int start, int end ) throws IOException{
	return get(target).get(input, start, end);
    }
	
    static Object get( Class<?> target, String input, int start ) throws IOException{
	return get(target).get(input, start);
    }
	
    static Object get( Class<?> target, String input ) throws IOException{
	return get(target).get(input);
    }
	
    static Token match( Class<?> target, String input, int start, int end ){
	return get(target).match(input, start, end);
    }
	
    static Token match( Class<?> target, String input, int start ){
	return get(target).match(input, start);
    }
	
    static Token match( Class<?> target, String input ){
	return get(target).match(input);
    }
}