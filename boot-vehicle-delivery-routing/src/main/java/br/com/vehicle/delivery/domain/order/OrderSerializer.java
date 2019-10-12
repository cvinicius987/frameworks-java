package br.com.vehicle.delivery.domain.order;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Customização da serialização para JSON da Entidade Order.
 * 
 * @author Caio
 * @since 08/07/2018
 * @version 1.0
 */
public class OrderSerializer extends StdSerializer<Order>{

	private static final long serialVersionUID = 1L;

	public OrderSerializer() {
		this(null);
	}
	   
    public OrderSerializer(Class<Order> t) {
        super(t);
    }
	    
	@Override
	public void serialize(Order order, JsonGenerator jgen, SerializerProvider provider) 
	throws IOException {
		
		jgen.writeStartObject();
        jgen.writeNumberField("id", order.getId());
        jgen.writeNumberField("restaurantId", order.getRestaurant().getId());
        jgen.writeNumberField("clientId", order.getClient().getId());
        jgen.writeObjectField("pickup", order.getPickup());
        jgen.writeObjectField("delivery", order.getDelivery());
        jgen.writeEndObject();
	}
}