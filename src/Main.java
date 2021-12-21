import utils.ModulusExtractionUtil;
import utils.Scan;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        try{
            ModulusExtractionUtil.parseSSHPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDVO2WGM6rdHBGg26/HHFbgYgz7gcA3AIS+JkcgOMzvSUZIxb3IsldJEUl6AMClIDH/RrxwXpWzUmdBEZykHLYElAP3HEYh158wlldtkX1m2u1aLAR/OXCiixpM5H54yvpZVfja6Cdnkzn93QHnXdCln8L7zpprGI1xpa6eit1vMPD4QqDKqxlYryxDlfJAhzGakcKX1RU/4k8ORvLoYEbkeJQTm/UPNVr0G6NDtFI98c6xBIA3fRHSpyfB8Ky//KdAkLR9TQhj7pY1oDb5WPgd7IyTjT2lijYI889xcsi1InmAW52rEof2F+KOPR2ac/+RDQaQhybCnrhYoKg+QLqx1enabNzGE2i8mjXAtczPm3EDdSwhf/5Z2Zj3A6gv4rRu0JhezvmX3MKEgXVenuaXUH9fo20zKZjgsT0A+SKN7qc9017zxf10zN+lwu9fdhKPhFAiFdf7xAK15JBaYHyslnpiwsj6jEBSlduHDlHs2CAof0wioaurQB1wWUBlPdM=");
            List<String> userNames = new ArrayList<>();
            userNames.add("alexOlshanskyy");
            userNames.add("alex");
            Scan.start(userNames);
        } catch (Exception e) {
            System.out.println("Error parsing key" + e);
        }

    }
}
