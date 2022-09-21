import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;

public class Records {
    private static File file;
    private static final String WEB_URL = "https://nitsri.ac.in/Pages/DisplayPages.aspx?page=cackm";
    public static final String NEW_UPDATES_FILEPATH = "Record_files/new_updates.txt";
    public static final String PREV_UPDATES_FILEPATH = "Record_files/prev_updates.txt";
    

    public Records(){
        file = new File(PREV_UPDATES_FILEPATH);
        Scrapper scrapper = new Scrapper();
        Elements notifications = scrapper.scrap(WEB_URL);
        checkPrevRecordFile(file,notifications);
        recordNewUpdates(fetchNewUpdates(notifications));
    }

    private ArrayList fetchNewUpdates(Elements message) {
        ArrayList<String> arrayList = new ArrayList<>();
        if(message == null){
            System.out.println("Connection timed-out!");
            arrayList.add("No new Updates");
            return arrayList;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            for (int i = 1 ; i < message.size(); i++){
                if (line.equals(message.get(i).text())) break;
                arrayList.add(message.get(i).text());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }

    private void recordNewUpdates(ArrayList<String> arrayList){
        File fNewUpdates = new File(NEW_UPDATES_FILEPATH);
        FileWriter fw = null;
        try {
            fw = new FileWriter(fNewUpdates);
            for (String s : arrayList) {
                fw.write(s.toString() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<String> getContentFromFile(File f) {
        BufferedReader reader = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = reader.readLine()) != null){
                list.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void updatePrevRecordFile(File fNewUpdates) {
        FileWriter fw = null;
        ArrayList<String> list1,list2,list;
        try {
            list1 = getContentFromFile(fNewUpdates);
            list2 = getContentFromFile(file);
            list = new ArrayList<>();
            list.addAll(list1);
            list.addAll(list2);
            fw = new FileWriter(file);
            for (String s : list) {
                fw.write(s + "\n");
            }
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkPrevRecordFile(File file, Elements notifications) {
        if (file.length() == 0) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file));
                for (int i = 1; i < notifications.size(); i++){
                    bw.write(notifications.get(i).text());
                    bw.write("\n");
                    i++;
                }
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
