package dto;

import com.heystyles.common.types.ObjectResponse;
import domain.SessionToken;

public class SessionTokenResponse extends ObjectResponse<SessionToken> {

    public SessionTokenResponse() {
        super();
    }

    public SessionTokenResponse(SessionToken sessionToken) {
        super(sessionToken);
    }
}
