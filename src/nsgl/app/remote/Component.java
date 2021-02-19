package nsgl.app.remote;

import nsgl.app.net.Channel;
import nsgl.object.Named;

public class Component extends Named implements nsgl.app.Component{
	protected Channel channel;

	public Component(String id) { super(id); }
	
	public Component(String id,Channel channel) {
		super(id);
		this.channel = channel;
	}
	
	public void setClient( Channel channel ) { this.channel = channel; }
	
	public Object run( String method, Object... args ) throws Exception{
	    return channel.send(Channel.command(id(), method, args)); 
	}
}