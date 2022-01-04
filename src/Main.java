import utils.ArrayUtil;
import utils.FileUtil;
import utils.ThreadWrapper;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args){
        try{
            if (!FileUtil.createOutputFile("output.txt")){
                System.out.println("Failed to create output file: ");
            }
            List<String> usernames = FileUtil.readUsernames("usernames.txt");
            int start = 0;
            int end = 500;
            for (int j = 0; j < (usernames.size()/500) + 1; j++){
                List<List<String>> partitionedUsernames = ArrayUtil.splitArray(usernames.subList(start, end), 10);
                List<ThreadWrapper> tws = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    ThreadWrapper tw = new ThreadWrapper(partitionedUsernames.get(i));
                    tw.start();
                    tws.add(tw);
                }
                for (int i = 0; i < tws.size(); i++)
                {
                    tws.get(i).join();
                }
                System.out.println("All threads finished");
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
}
