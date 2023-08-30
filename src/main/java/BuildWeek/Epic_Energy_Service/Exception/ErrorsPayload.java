package BuildWeek.Epic_Energy_Service.Exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorsPayload {
	private String message;
	private Date timestamp;

}
