package domain;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Enumerated;
import domain.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Person {

	private String name;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable = false)
	private int age;

	@Column(nullable = false, unique = true)
	private String identificationNumber;
	
	private String typeId;
	private String address;
	private String phone;
	
	
}
