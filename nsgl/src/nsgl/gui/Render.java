package nsgl.gui;

import nsgl.app.Component;
import nsgl.object.Configurable;

public interface Render extends Component, Configurable{
	static final String TAG = "render";

	void render( Object obj );

	void init();	
}