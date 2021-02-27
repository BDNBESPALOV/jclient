package execution_JSON;

import java.io.*;

public class UpdateSP implements Exec{
    @Override
    public void doProcess() {

    }

    @Override
    public void doProcess(int i) {

    }

    @Override
    public void doProcess(String str) {

    }

    @Override
    public void doProcess(PrintWriter out) {
        String inputLine;
            try {
                Process   p = Runtime.getRuntime().exec(new String[]{"cmd", "/c","type","order.log"});
                InputStream in = p.getInputStream();
                BufferedReader inb = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                /*в цикле считываем сообщения от SPAdmin */
                while ((inputLine = inb.readLine()) != null) {
                    /*отправлять все ответы от SPAdmin */
                    out.println(inputLine);
                }
                inb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
