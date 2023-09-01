package BuildWeek.Epic_Energy_Service.Security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

}
