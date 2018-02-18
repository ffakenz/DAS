package service;

import javax.jws.WebService;

@WebService(targetNamespace = "http://www.example.org/contract/DoubleItService"
        , portName="DoubleItServicePort"
        , serviceName="DoubleItServiceService"
)
public class DoubleItService {
    public int doubleIt(int numberToDouble) {
        return numberToDouble * 2;
    }
}
