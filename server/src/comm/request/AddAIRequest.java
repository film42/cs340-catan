package comm.request;

/**
 * Created by qzcx on 3/7/14.
 */
public class AddAIRequest {
    private String AIType;

    public AddAIRequest(String AIType) {
        this.AIType = AIType;
    }

    public String getAIType() {

        return AIType;
    }
}
