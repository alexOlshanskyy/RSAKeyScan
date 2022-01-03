package utils;

import model.DataEntry;

import java.util.List;

public class ThreadWrapper extends Thread {

    private List<String> usernames;
    private String output;


    public ThreadWrapper(List<String> usernames) {
        this.usernames = usernames;
    }

    public void run()
    {
        try {
            System.out.println("Starting thread " + Thread.currentThread().getId());
            List<DataEntry> dataEntries = Scan.start(usernames);
            StringBuilder sb = new StringBuilder();
            for (DataEntry dataEntry : dataEntries) {
                sb.append(dataEntry.toString());
                sb.append('\n');
            }
            this.output = sb.toString();  // set the output
            System.out.println("Thread finished " + Thread.currentThread().getId());
        }
        catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }

    public String getOutput() {
        return output;
    }
}
