package br.com.vehicle.delivery.domain.client;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Dados do Cliente que efetuou o pedido.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@ApiModel(description="Modelo de clientes dos restaurantes.")
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="tbl_client")
public class Client {

	@ApiModelProperty(notes="ID do cliente, sendo um Number", required=true)
	@Setter
	@Id
	private Long id;
	
	@ApiModelProperty(notes="Nome do cliente, podendo conter até 50 caracteres", required=true)
	@Size(max=50, message="Limite de caracteres foi ultrapassado")
	@NotNull(message="O Nome do cliente é obrigatorio")
	private String name;
	
	@ApiModelProperty(notes="Latitude do endereço do Cliente", required=true)
	@NotNull(message="Latitude é obrigatorio")
	private Double lat;
	
	@ApiModelProperty(notes="Longitude do endereço do Cliente", required=true)
	@NotNull(message="Longitude é obrigatorio")
	private Double lng;
	
	public Client(Long id) {
		this.id = id;
	}
}