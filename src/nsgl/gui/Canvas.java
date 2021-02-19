package nsgl.gui;

import nsgl.app.Component;
import nsgl.json.JSON;
import nsgl.object.Configurable;

public interface Canvas extends Component, Configurable{
    	static final String TAG = "canvas";
	void draw( JSON c );
	
	/**
	
	public abstract void drawArc(int x, int y, int width, int height, int startAngle, int endAngle); 

	public abstract void drawFillArc(int x, int y, int width, int height, int startAngle, int endAngle );

	public void drawOval( int x, int y, int width, int height ){ drawArc( x, y, width, height, 0, 360); }
	
	public void drawFillOval( int x, int y, int width, int height ){ drawFillArc( x, y, width, height, 0, 360); }
	*/	
}