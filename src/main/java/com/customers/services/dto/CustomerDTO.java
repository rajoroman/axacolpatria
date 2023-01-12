package com.customers.services.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CustomerDTO {
	
	private Long id;
	
	@NotNull(message = "Debe indicar el identificador del cliente")
	@Size(min = 5, max = 10, message = "El Id del Cliente debe tener entre 8 y 10 carácteres")
	private String customerId;
	
	@NotNull(message = "Debe indicar el nombre")
	@Size(min = 2, max = 100, message = "El nombre debe tener entre 10 y 100 carácteres")
	private String name;
	
	@NotNull(message = "Debe indicar la edad")
	@Min(value = 1, message = "La edad debe ser mayor a 0")
	@Max(value = 100, message = "La edad debe ser menor a 100")
	private Integer age;
	
	@NotNull
	@Pattern(regexp = "(\\w{1,}\s)(\\d{2,}\\s)([#]{1}\s)(\\d{2})(\\w{0,1}\s)([-]{1}\\s)(\\d{2})(\\w{0,10})", message = "La dirección debe cumplir el formato XXXX 99 # 99XXX - 99XXX")
	private String address;
	
	@NotNull
	@Size(min = 8, max = 15, message = "el número de teléfono debe tener entre 8 y 15 digitos")
	@Pattern(regexp = "(\\d{1,15})", message = "El número de teléfono debe contener solo dígito entre 0 y 9")
	private String phoneNumber;
}
