package mobile.project.bzu.assignementgroup3.Model;

public class School {
    private String StudentName;
    private String StudentID;
    private String Gender;
    private String address;
    private String Branch;
    private String DOB;
    private Integer Phone;
    private Integer Gpa;

    public School(String studentName, String studentID, String gender,  String address, String branch,  String DOB, Integer phone, Integer gpa) {
        StudentName = studentName;
        StudentID = studentID;
        Gender = gender;

        this.address = address;
        Branch = branch;


        this.DOB = DOB;
        Phone = phone;
        Gpa = gpa;
    }
    public School(){

    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }





    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public Integer getPhone() {
        return Phone;
    }

    public void setPhone(Integer phone) {
        Phone = phone;
    }

    public Integer getGpa() {
        return Gpa;
    }

    public void setGpa(Integer gpa) {
        Gpa = gpa;
    }

    @Override
    public String toString() {
        return "school{" +
                "StudentName='" + StudentName + '\'' +
                ", StudentID=" + StudentID +
                ", Gender='" + Gender + '\'' +
                ", address='" + address + '\'' +
                ", Branch='" + Branch + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Phone=" + Phone +
                ", Gpa=" + Gpa +
                '}';
    }
}
