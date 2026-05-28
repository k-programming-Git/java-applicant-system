public class Applicant {
    private String name;
    private int age;
    private String status;
    private String skill;

    Applicant(String name, int age, String status, String skill){
    this.name = name;
    this.age = age;
    this.status = status;
    this.skill = skill;

    count++;
    }
    public void printData() {
        System.out.println("名前：" + this.name);
        System.out.println("年齢：" + this.age);
        System.out.println("応募状況：" + this.status);
        System.out.println("スキル：" + this.skill);
    }
    public void changeStatus(String newStatus) {
        this.status = newStatus;
    }
    public static int count = 0;
    public String getStatus() {
        return this.status;
    }
    public String getName() {
        return this.name;
    }

    public int getAge() {
    return this.age;
    }

    public String getSkill() {
    return this.skill;
    }

    public static void printCount() {
        System.out.println("応募者数" + count);
    }
    
    }
    