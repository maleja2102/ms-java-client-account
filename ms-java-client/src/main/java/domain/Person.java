package domain;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Enumerated;
import domain.enums.Gender;
import jakarta.persistence.EnumType;
import lombok.*;

@Getter
@Setter
@MappedSuperclass
public class Person {

	private String name;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private int age;
	private String identificationNumber;
	private String typeId;
	private String address;
	private String phone;
	
	
}
