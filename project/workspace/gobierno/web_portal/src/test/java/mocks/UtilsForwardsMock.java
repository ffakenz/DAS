package mocks;

import ar.edu.ubp.das.mvc.config.ForwardConfig;

import java.util.HashMap;
import java.util.Map;

public class UtilsForwardsMock {
    public static Map<String, ForwardConfig> forwardsMock() {
        Map<String, ForwardConfig>   forwards = new HashMap<String, ForwardConfig>();
        ForwardConfig successForward = new ForwardConfig();
        successForward.setName("success");
        successForward.setPath("/util/success.jsp");
        forwards.put("success", successForward);

        ForwardConfig failureForward = new ForwardConfig();
        failureForward.setName("failure");
        failureForward.setPath("/util/failure.jsp");
        forwards.put("failure", failureForward);

        ForwardConfig warningForward = new ForwardConfig();
        failureForward.setName("warning");
        failureForward.setPath("/util/warning.jsp");
        forwards.put("warning", failureForward);

        return forwards;
    }
}
