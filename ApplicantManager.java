import java.util.ArrayList;

public class ApplicantManager {

    private ArrayList<Applicant> applicants;

    ApplicantManager() {

        applicants = new ArrayList<>();
    }

    public ArrayList<Applicant> getApplicants() {

        return applicants;
    }

    public void deleteApplicant(String deleteName) {
        boolean deleted = false;
        for(int i =0; i < applicants.size(); i++) {
            if(applicants.get(i).getName().equals(deleteName)); {
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
    public void showApplicants() {
        for(Applicant applicant : applicants) {
            applicant.printData();
            System.out.println("-------");
        }
    }
}