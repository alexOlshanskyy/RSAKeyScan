import utils.FileUtil;
import utils.ModulusExtractionUtil;
import utils.ThreadWrapper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        try{
            ModulusExtractionUtil.parseSSHPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDVO2WGM6rdHBGg26/HHFbgYgz7gcA3AIS+JkcgOMzvSUZIxb3IsldJEUl6AMClIDH/RrxwXpWzUmdBEZykHLYElAP3HEYh158wlldtkX1m2u1aLAR/OXCiixpM5H54yvpZVfja6Cdnkzn93QHnXdCln8L7zpprGI1xpa6eit1vMPD4QqDKqxlYryxDlfJAhzGakcKX1RU/4k8ORvLoYEbkeJQTm/UPNVr0G6NDtFI98c6xBIA3fRHSpyfB8Ky//KdAkLR9TQhj7pY1oDb5WPgd7IyTjT2lijYI889xcsi1InmAW52rEof2F+KOPR2ac/+RDQaQhybCnrhYoKg+QLqx1enabNzGE2i8mjXAtczPm3EDdSwhf/5Z2Zj3A6gv4rRu0JhezvmX3MKEgXVenuaXUH9fo20zKZjgsT0A+SKN7qc9017zxf10zN+lwu9fdhKPhFAiFdf7xAK15JBaYHyslnpiwsj6jEBSlduHDlHs2CAof0wioaurQB1wWUBlPdM=");
            if (!FileUtil.createOutputFile("output.txt")){
                System.out.println("Failed to create output file: ");
            }
            List<String> usernames = FileUtil.readUsernames("test.txt");
            List<ThreadWrapper> tws = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ThreadWrapper tw = new ThreadWrapper(usernames);
                tw.start();
                tws.add(tw);
            }
            for (int i = 0; i < tws.size(); i++)
            {
                tws.get(i).join();
            }
            System.out.println("All threads finished");
            for (ThreadWrapper tw : tws) {
                System.out.println("Out:" + tw.getOutput());
                FileUtil.writeOutputFile("output.txt", tw.getOutput());
            }
            System.out.println("Finished writing to output file");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}
