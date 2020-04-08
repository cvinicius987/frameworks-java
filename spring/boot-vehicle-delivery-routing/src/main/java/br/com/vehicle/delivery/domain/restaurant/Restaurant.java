package br.com.vehicle.delivery.domain.restaurant;

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
 * Dados do Restaurante.
 * 
 * @author cvinicius
 * @since 05/07/2018
 * @version 1.0
 */
@ApiModel(description="Modelo de Restaurantes.")
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="tbl_restaurant")
public class Restaurant {

	@ApiModelProperty(notes="ID do Restaurante, sendo um Number", required=true)
	@Setter
	@Id
	private Long id;
	
	@ApiModelProperty(notes="Titulo do Restaurante, não pode ser maior que 50 caracteres", required=true)
	@Size(max=50, message="Limite de caracteres foi ultrapassado")
	@NotNull(message="O Titulo do restaurante é obrigatorio")
	private String title;
	
	@ApiModelProperty(notes="Latitude do endereço do Restaurante", required=true)
	@NotNull(message="Latitude é obrigatorio")
	private Double lat;
	
	@ApiModelProperty(notes="Longiude do endereço do Restaurante", required=true)
	@NotNull(message="Longiude é obrigatorio")
	private Double lng;
	
	public Restaurant(Long id) {
		this.id = id;
	}
}