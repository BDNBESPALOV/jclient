package execution_JSON.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JProcess extends Remote {
    void getProgessData(POJO_Process process) throws RemoteException;
}
