package ir.ac.kntu;

public class RequestThread extends Thread {
    private Bank myBank;
    public RequestThread(Bank myBank){
        super();
        this.myBank = myBank;
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            handleRequest();
            try {
                Thread.sleep(1000 * 30);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void handleRequest(){
        for (final Requests request : myBank.getRequest()) {
            if (" ".equals(request.getSupportMassage())) {
                request.setSupportMassage("Our colleagues will contact you soon.");
            }
        }
    }

}
