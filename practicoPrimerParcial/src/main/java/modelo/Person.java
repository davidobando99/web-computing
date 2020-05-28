package modelo;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Person {
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;

	
	
}
