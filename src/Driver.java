

public class Driver {

    public static void main(String[] args) {
        Records record = new Records();
        EMail eMail = new EMail(args[0],args[1],args[2]);
        eMail.getEmail(record);
        System.out.println("Done!");
    }
}
