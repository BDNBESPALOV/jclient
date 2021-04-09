package execution_JSON;

import org.slf4j.Logger;

import java.io.*;

public class CreateFileSH implements Exec{

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CreateFileSH.class);
    private String command;

    @Override
    public void doProcess() {

    }

    @Override
    public void doProcess(int i) {

    }

    @Override
    public void doProcess(String str) {

        if (str == null){
            log.info("Передано нулеве значение в doProcess(String str) ");
        } else this.command = str;

    }

    @Override
    public void doProcess(PrintWriter out) {

        try {
            Process process =   Runtime.getRuntime().exec(command);

            log.info("команда для исполнения: "+ command);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                log.info("ответ exec : "+ line);
                out.print(line);
            }
        } catch ( IllegalThreadStateException e){
            log.info("ERROR: " , e);
        }

        catch (IOException e) {
            log.info("ERROR: " , e);
        }

    }

    private String readTemplate(){
        try( BufferedReader br = new BufferedReader(new FileReader("templateExecFile"))){

            String str;
            while ((str = br.readLine()) != null){
                if(!str.contains("$")){
                    writer(str);
                }else {

                }
            }

        } catch (FileNotFoundException e) {
            log.info("ERROR: " , e);
        } catch (IOException e) {
            log.info("ERROR: " , e);
        }
        return "";
    }
    private void writer(String str){}
    private void createSHFile(){}
}
