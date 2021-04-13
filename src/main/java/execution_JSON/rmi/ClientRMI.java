package execution_JSON.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientRMI {
    public void getClientRMI(String pData){

            try{
//         String localhost    = "127.0.0.1";
//         String RMI_HOSTNAME = "java.rmi.server.hostname";
//         String SERVICE_PATH = "rmi://localhost/Serv";
//         System.setProperty(RMI_HOSTNAME, localhost);
//

        String objectName = "rmi://localhost/JProcess";
        JProcess bs = (JProcess) Naming.lookup(objectName);
        POJO_Process pojo =  new POJO_Process();
        pojo.data = pData;
        bs.getProgessData(pojo);

        } catch (MalformedURLException e) {
                e.printStackTrace();
        } catch (RemoteException e) {
                e.printStackTrace();
        } catch (NotBoundException e) {
                e.printStackTrace();
                System.err.println("NotBoundException : " + e.getMessage());
            }
    }
}
