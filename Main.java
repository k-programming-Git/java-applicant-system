import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("名前を入力してください：");
        String name = scanner.next();
        System.out.print("年齢を入力してください：");
        int age = scanner.nextInt();
        System.out.print("応募状況を入力してください：");
        String status = scanner.next();
        System.out.print("スキルを入力してください：");
        String skill = scanner.next();
        Applicant applicant3 = new Applicant(name, age, status, skill);
        
        Applicant applicant1 = new Applicant("田中", 25, "応募中", "Java");
        applicant1.changeStatus("面接中");
        
        Applicant applicant2 = new Applicant("佐藤", 23, "書類通過", "Excel VBA");
        applicant2.changeStatus("面接予定");

        Applicant.printCount();

        ArrayList<Applicant> applicants = new ArrayList<>();

        applicants.add(applicant1);
        applicants.add(applicant2);
        applicants.add(applicant3);

        boolean running = true;

        while(running) {
            System.out.println("6: ファイル保存");
            System.out.println("5: ステータス更新");
            System.out.println("4: 応募者削除");
            System.out.println("3: 名前検索");
            System.out.println("2: 応募者追加");
            System.out.println("1: 一覧表示");
            System.out.println("0: 終了");
            System.out.println("番号を入力してください");

            int menu = scanner.nextInt();

            switch(menu) {
                case 1:
                    for(Applicant applicant : applicants) {
                        applicant.printData();
                        System.out.println("----------");
                    }
                    break;
                    
                case 2:
                    System.out.println("名前：");
                    String newName = scanner.next();

                    System.out.println("年齢：");
                    int newAge = scanner.nextInt();

                    System.out.print("応募状況：");
                    String newStatus = scanner.next();

                    System.out.print("スキル：");
                    String newSkill = scanner.next();

                    Applicant newApplicant = new Applicant(newName, newAge, newStatus, newSkill);

                    applicants.add(newApplicant);

                    System.out.println("応募者を追加しました");

                    break;

                case 3:
                    System.out.print("検索名：");
                    String searchName = scanner.next();

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

                    break;

                    case 4:

                    System.out.print("削除する名前：");
                    String deleteName = scanner.next();

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

                    break;

                    case 5:
                        System.out.print("更新する名前：");
                        String updateName = scanner.next();

                        boolean updated = false;

                        for(Applicant applicant : applicants) {
                            if(applicant.getName().equals(updateName)){

                                System.out.print("新しいステータス：");
                                String updatedStatus = scanner.next();

                                applicant.changeStatus(updatedStatus);

                                System.out.println("ステータスを更新しました");

                                updated = true;

                                break;
                            }
                        }

                        if(!updated) {
                            System.out.println("該当する応募者がいません");
                        }

                        break;

                        case 6:

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

}