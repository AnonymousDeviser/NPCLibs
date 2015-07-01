package znox.npc.network;

import java.net.SocketAddress;

import net.minecraft.util.io.netty.channel.AbstractChannel;
import net.minecraft.util.io.netty.channel.Channel;
import net.minecraft.util.io.netty.channel.ChannelConfig;
import net.minecraft.util.io.netty.channel.ChannelMetadata;
import net.minecraft.util.io.netty.channel.ChannelOutboundBuffer;
import net.minecraft.util.io.netty.channel.DefaultChannelConfig;
import net.minecraft.util.io.netty.channel.EventLoop;

public class NullChannel extends AbstractChannel {
	
	protected final ChannelConfig config = new DefaultChannelConfig(this);
	  
	protected NullChannel(Channel parent){
		super(parent);
	}
	  
	protected AbstractUnsafe newUnsafe(){
	    return null;
	}
	  
	protected boolean isCompatible(EventLoop eventExecutors){
	    return true;
	}
	  
	protected SocketAddress localAddress0(){
	    return null;
	}
	  
	protected SocketAddress remoteAddress0(){
	    return null;
	}
	  
	protected void doBind(SocketAddress socketAddress) throws Exception{}
	  
	protected void doDisconnect() throws Exception{}
	  
	protected void doClose() throws Exception {}
	  
	protected void doBeginRead() throws Exception {}
	  
	protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {}
	  
	public ChannelConfig config(){
	    return this.config;
	}
	  
	public boolean isOpen(){
	    return true;
	}
	  
	public boolean isActive(){
	    return false;
	}
	  
	public ChannelMetadata metadata(){
		return null;
	}

}
