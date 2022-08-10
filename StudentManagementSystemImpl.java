package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class StudentManagementSystemImpl implements StudentManagementSystem {
	
	Scanner sc=new Scanner(System.in);
	LinkedHashMap<Integer, Student> db=new LinkedHashMap<Integer, Student>();
	
	@Override
	public void addStudent() {
		System.out.println("ENTER ID,NAME,AGE,MARKS");
		int id=sc.nextInt();
		String name=sc.next();
		int age=sc.nextInt();
		double marks=sc.nextDouble();
		
		//db.put(std,new Student(id,name,age,marks))

		Student std = new Student(id, name, age, marks);
		db.put(id,std);  //db.put(std.getId(),std)
        System.out.println("Student record inserted succefully");		
		
	}

	@Override
	public void removeStudent() {
		System.out.println("enter Student id");
		int id=sc.nextInt();
		
		if(db.containsKey(id)) {
			db.remove(id);
			System.out.println("Student record deleted");
		}else
		{
			try {
				throw new StudentNotFoundException("Student data not found");
			}catch(StudentNotFoundException e){
				System.out.println(e.getMessage());
			}
		}
		
		
	}

	@Override
	public void removeAllStudent() {
		db.clear();
		System.out.println("All the Student Records Deleted");
		

	}

	@Override
	public void displayStudent() {
		System.out.println("enter Student id");
		int id=sc.nextInt();
		if(db.containsKey(id)) {
			Student std=db.get(id); //Student std = new Student();
			System.out.println(std); //since toString is overriden
			System.out.println("ID:"+std.getId());
			System.out.println("NAME:"+std.getName());
			System.out.println("AGE:"+std.getAge());
			System.out.println("MARKS:"+std.getMarks());
		}else {
			try {
				throw new StudentNotFoundException("Student data not found");
			}catch(StudentNotFoundException e){
				System.out.println(e.getMessage());
			}
			
		}
		
		
	}

	@Override
	public void displayAllStudents() {
		Set<Integer> setOfKeys =db.keySet(); //creating set of students ID's
		
		/*for(int id : setOfKeys) {
			System.out.println(db.get(id));
		}*/
		
		for(int key :setOfKeys) {
			Student s = db.get(key);
			System.out.println("Id:"+s.getId()+"\tName:"+s.getName());
			System.out.println("\tAge:"+s.getAge()+"\tMarks:"+s.getMarks());
		}
		
		
	}

	@Override
	public void updateStudent() {
		System.out.println("Enter Student id");
		int id=sc.nextInt();
		
		if(db.containsKey(id)) {
			Student s=db.get(id);
			System.out.println("1:Update Age\n2:Update Name\n3 Update Marks");
			System.out.println("enter choice");
			int choice=sc.nextInt();
			
			switch(choice) {
			case 1: 
				System.out.println("Enter Age");
				int age=sc.nextInt();
				s.setAge(age);
				break;
			case 2:
				System.out.println("Enter Name");
				String name=sc.next();
				s.setName(name);
				break;
			case 3:
				System.out.println("ENter Marks");
				double marks=sc.nextDouble();
				s.setMarks(marks);
				break;
				
			default:
					System.out.println("Invalid ");
				
			}
			System.out.println("Student record Successefully Updated");
			
		}else {
			try {
				throw new StudentNotFoundException("Student data not found");
			}catch(StudentNotFoundException e){
				System.out.println(e.getMessage());
			}
			
		}
		
		
		
	}

	@Override
	public void countStudent() {
		System.out.println("Number Of Student Records Present: "+db.size());
	}

	@Override
	public void sortStudent() {
		ArrayList<Student> l = new ArrayList<Student>();
		Set<Integer> s = db.keySet();
		for(int id : s) {
			l.add(db.get(id));
		}
		
		System.out.println("1:Sort Based On Id\n2:Sort Based On Name\n3:Sort Based On Age\n4:Sort Based On Marks");
		System.out.println("Enter choice");
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			Collections.sort(l, new SortStudentById());
			display(l);
			break;
		case 2:
			Collections.sort(l, new SortStudentByName());
			display(l);
			break;
		case 3:
			Collections.sort(l, new SortStudentByAge());
			display(l);
			break;
		case 4:
			Collections.sort(l, new SortStudentByMarks());
			display(l);
			break;
		default:
			System.out.println("Invalid choice,Enter valid choice");
		}
		
	}
	 private static void display(List<Student> l) {
		 for(Student s1:l) {
				System.out.println(s1);
			}
	 }
	

}
