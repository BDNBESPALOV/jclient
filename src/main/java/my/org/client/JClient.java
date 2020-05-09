package my.org.client;

public class JClient {
    private boolean status;
    private String command;

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCommand(){
            return command;
        }
        public void setCommand(String command){
            this.command = command;
        }
}
