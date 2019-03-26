package jdbc.practice;
import java.util.ArrayList;

import jdbc.dao.*;
import jdbc.dto.*;

public class jdbcPractice {
	public static void main(String[] args) {
		int number=(int)(Math.random()*4+1);
		StudyMemberDao smDao=new StudyMemberDao();
	//	StudyMember sm =smDao.getId(number);
	//	System.out.println(sm);
		
		//StudyMember sm2= new StudyMember(5,"kim","Anyang");
		//int a= smDao.addMember(sm2);
		//System.out.println(a);
		//smDao.deleteMember("sungjin");
		ArrayList<StudyMember> table=new ArrayList();
		table= smDao.gettable();
	
		System.out.println(table);
	}
}
