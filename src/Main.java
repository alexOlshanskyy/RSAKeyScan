import jdk.jshell.spi.ExecutionControl;
import utils.ArrayUtil;
import utils.FileUtil;
import utils.ThreadWrapper;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args){

        if (args.length != 1) {
            usage();
            return;
        }
        if (args[0].equals("1")){
            scan();
        } else if (args[0].equals("2")){
            breakKeys();
        } else {
            usage();
            return;
        }
    }


    private static void scan(){
        try{
            if (!FileUtil.createOutputFile("output.txt")){
                System.out.println("Failed to create output file: ");
            }
            List<String> usernames = FileUtil.readUsernames("usernames.txt");
            int start = 0;
            int end = 500;
            // send requests for only 500 users at the time otherwise github will start blocking requests
            for (int j = 0; j < (usernames.size()/500) + 1; j++){
                List<List<String>> partitionedUsernames = ArrayUtil.splitArray(usernames.subList(start, end), 10);
                List<ThreadWrapper> tws = new ArrayList<>();

                // start threads
                for (int i = 0; i < 10; i++) {
                    ThreadWrapper tw = new ThreadWrapper(partitionedUsernames.get(i));
                    tw.start();
                    tws.add(tw);
                }

                // wait for threads to finish
                for (int i = 0; i < tws.size(); i++)
                {
                    tws.get(i).join();
                }

                System.out.println("All threads finished");

                // write the result of each thread to the output file
                for (ThreadWrapper tw : tws) {
                    FileUtil.writeOutputFile("output.txt", tw.getOutput());
                }

                System.out.println("Finished writing to output file");
                System.out.println("Usernames processed: " + start + "-" + end);
                start = end;
                end += 500;
                if (end > usernames.size()){
                    end = usernames.size();
                }

                // wait for 80 seconds so that github does not block requests.
                System.out.println("Starting sleep...");
                System.out.println("80 seconds left...");
                Thread.sleep(20000);
                System.out.println("60 seconds left...");
                Thread.sleep(20000);
                System.out.println("40 seconds left...");
                Thread.sleep(20000);
                System.out.println("20 seconds left...");
                Thread.sleep(20000);
                System.out.println("0 seconds left...");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    private static void breakKeys()  {
        System.out.println("Not Implemented");
    }
    private static void usage() {
        System.out.println("Usage: give parameter 1 for scan and 2 for breaking keys");
        System.out.println("For example: java -jar RSAKeyScan.jar 1");
        System.out.println("If option 2 is chosen a file named output.txt with key data should be provided " +
                "in the same directory");
    }

}
