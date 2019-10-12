package br.com.vehicle.delivery.domain.order;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.vehicle.delivery.domain.client.Client;
import br.com.vehicle.delivery.domain.restaurant.Restaurant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Dados do Pedido
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@ApiModel(description="Modelo de pedidos dos restaurantes.")
@JsonSerialize(using = OrderSerializer.class)
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="tbl_order")
public class Order {

	@ApiModelProperty(notes="ID do cliente, sendo um Number", required=true)
	@Id
	private Long id;
	
	@ApiModelProperty(notes="ID do restaurante", required=true)
	@JsonProperty(value="restaurantId")
	@JsonIdentityReference(alwaysAsId=true)
	@NotNull(message="Restaurante é obrigatorio")
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;
	
	@ApiModelProperty(notes="ID do cliente", required=true)
	@JsonProperty(value="clientId")
	@JsonIdentityReference(alwaysAsId=true)
	@NotNull(message="Cliente é obrigatorio")
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@ApiModelProperty(notes="Data/Hora que o pedido esta pronto para ser entregue [yyyy-MM-dd'T'HH:mm:ss]", required=true)
	@NotNull(message="Data/Hora da retirada é obrigatoria")
	private LocalDateTime pickup;
	
	@ApiModelProperty(notes="Data/Hora limite para entrega, tem que ser uma data no futuro.", required=true)
	@Future(message="Data/Hora da entrega possui um valor inválido.")
	@NotNull(message="Data/Hora da entrega é obrigatoria")
	private LocalDateTime delivery;
	
	@JsonIgnore
	@Column(insertable=true, updatable=false)
	private Boolean finished = false;
}