package javaTester;

public class Topic_05_Primitive_Casting {

	public String studentName;
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public void showStudentName() {
		System.out.println("Student Name = " + studentName);
	}
	
	public static void main(String[] args) {
		Topic_05_Primitive_Casting firstStudent = new Topic_05_Primitive_Casting();
		Topic_05_Primitive_Casting secondStudent = new Topic_05_Primitive_Casting();
		
		firstStudent.setStudentName("Nguyen Van A");
		secondStudent.setStudentName("Phan Thi B");
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
		
		//  Ép kiểu
		secondStudent = firstStudent;
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
		
		secondStudent.setStudentName("Le Hoang Long");
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();

	}

}
