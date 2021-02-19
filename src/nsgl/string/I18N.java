package nsgl.string;

import java.io.IOException;

import nsgl.hash.HashMap;
import nsgl.json.JSON;
import nsgl.service.io.Read;

public class I18N {
    protected static HashMap<String, String> dictionary = new HashMap<String, String>();
    protected static final char c = '·';
    
    public static void clear() {
	dictionary = new HashMap<String, String>();
    }
    
    public static void set( String key, String value ) {
	dictionary.set(key,value); 
    }
    
    public static String process( String code ) { return Template.get(code, I18N.dictionary, c); }
    
    public static void set(String code) {
	clear();
	try {
	    JSON json =(JSON)Read.get(JSON.class, code);
	    for(String key:json.keys()) {
		String value = json.string(key);
		if(value!=null) dictionary.set(key, value);
	    }
	} catch (IOException e) {}
    }
}