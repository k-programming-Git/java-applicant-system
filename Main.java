import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Applicant> applicants = new ArrayList<>();
  
        loadFromFile(applicants);
        Applicant.printCount();
        
        boolean running = true;

        while(running) {
            showMenu();
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch(menu) {
                case 1:
                    for(Applicant applicant : applicants) {
                        applicant.printData();
                        System.out.println("----------");
                    }
                    break;
                    
                case 2:
                    addApplicant(scanner, applicants);
                    break;
                

                case 3:
                    searchApplicant(scanner, applicants);
                    break;

                    case 4:
                        deleteApplicant(scanner, applicants);
                        break;

                    case 5:
                        updateApplicant(scanner, applicants);
                        break;

                        case 6:

                        saveToFile(applicants);

                        break;

                    case 0:
                        running = false;

                        System.out.println("終了します");
                        break;

                        default:
                        System.out.println("正しい番号を入力してください");
            }
        }        
    }
    public static void saveToFile(ArrayList<Applicant> applicants) {

    try {

        FileWriter writer =
            new FileWriter("applicants.txt");

        for(Applicant applicant : applicants) {

            writer.write(
                applicant.getName() + ","
                + applicant.getAge() + ","
                + applicant.getStatus() + ","
                + applicant.getSkill()
                + "\n"
            );
        }

        writer.close();

        System.out.println("保存しました");

    } catch(IOException e) {

        System.out.println("ファイル保存エラー");
    }
}
public static void loadFromFile(ArrayList<Applicant> applicants) {

    File file = new File("applicants.txt");

    if(file.exists()) {

        try {

            BufferedReader reader =
                new BufferedReader(
                    new FileReader("applicants.txt")
                );

            String line;

            while((line = reader.readLine()) != null) {

                if(line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                String fileName = data[0];
                int fileAge = Integer.parseInt(data[1]);
                String fileStatus = data[2];
                String fileSkill = data[3];

                Applicant applicant =
                    new Applicant(
                        fileName,
                        fileAge,
                        fileStatus,
                        fileSkill
                    );

                applicants.add(applicant);
            }

            reader.close();

        } catch(IOException e) {

            System.out.println("ファイル読み込みエラー");
        }
    }
}

public static void showMenu() {

    System.out.println("6: ファイル保存");
    System.out.println("5: ステータス更新");
    System.out.println("4: 応募者削除");
    System.out.println("3: 名前検索");
    System.out.println("2: 応募者追加");
    System.out.println("1: 一覧表示");
    System.out.println("0: 終了");
    System.out.println("番号を入力してください");
}

public static void addApplicant(
    Scanner scanner,
    ArrayList<Applicant> applicants
) {

    System.out.println("名前：");
    String newName = scanner.nextLine();

    System.out.println("年齢：");
    int newAge = scanner.nextInt();
    scanner.nextLine();

    System.out.print("応募状況：");
    String newStatus = scanner.nextLine();

    System.out.print("スキル：");
    String newSkill = scanner.nextLine();

    Applicant newApplicant =
        new Applicant(
            newName,
            newAge,
            newStatus,
            newSkill
        );

    applicants.add(newApplicant);

    System.out.println("応募者を追加しました");
}

public static void searchApplicant(
    Scanner scanner,
    ArrayList<Applicant> applicants
) {

    System.out.print("検索名：");
    String searchName = scanner.nextLine();

    boolean found = false;

    for(Applicant applicant : applicants) {

        if(applicant.getName().equals(searchName)) {

            applicant.printData();
            System.out.println("-------");

            found = true;
        }
    }

    if(!found) {

        System.out.println("該当する応募者がいません");
    }
}
public static void deleteApplicant(
    Scanner scanner,
    ArrayList<Applicant> applicants
) {

    System.out.print("削除する名前：");
    String deleteName = scanner.nextLine();

    boolean deleted = false;

    for(int i = 0; i < applicants.size(); i++) {

        if(applicants.get(i).getName().equals(deleteName)) {

            applicants.remove(i);

            System.out.println("削除しました");

            deleted = true;

            break;
        }
    }

    if(!deleted) {

        System.out.println("該当する応募者がいません");
    }
}
public static void updateApplicant(
    Scanner scanner,
    ArrayList<Applicant> applicants
) {

    System.out.print("更新する名前：");
    String updateName = scanner.nextLine();

    boolean updated = false;

    for(Applicant applicant : applicants) {

        if(applicant.getName().equals(updateName)) {

            System.out.print("新しいステータス：");

            String updatedStatus =
                scanner.nextLine();

            applicant.changeStatus(updatedStatus);

            System.out.println(
                "ステータスを更新しました"
            );

            updated = true;

            break;
        }
    }

    if(!updated) {

        System.out.println(
            "該当する応募者がいません"
        );
    }
}

}