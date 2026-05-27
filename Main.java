import java.util.Scanner;
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

        Applicant[] applicants = new Applicant[3];
        applicants[0] = applicant1;
        applicants[1] = applicant2;
        applicants[2] = applicant3;

        for(int i = 0; i < applicants.length; i++) {
            applicants[i].printData();
            System.out.println("-----------");
        }
        System.out.println("【面接関連の応募者】");
        for(int i = 0; i < applicants.length; i++) {
            if(applicants[i].getStatus().equals("面接中")
                || 
            applicants[i].getStatus().equals("面接予定")) {
                applicants[i].printData();
                System.out.println("--------");
            }
        }
    }

}