package jdbc.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import jdbc.dto.StudyMember;

public class StudyMemberDao {

	private static String dburl = "jdbc:mysql://localhost/test?characterEncoding=UTF-8&serverTimezone=UTC";
	private static String dbUser= "";
	private static String dbpasswd= "!";
	
	StudyMember sm = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;	
	ArrayList<StudyMember> table =new ArrayList();
	
	@SuppressWarnings("finally")
	public ArrayList<StudyMember> gettable() { 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql= "Select * from java_study";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id1 = rs.getInt("id");
				String name = rs.getString("name");
				String hometown = rs.getString("hometown");
				sm = new StudyMember(id1, name, hometown);
				table.add(sm);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
			if(ps!=null) {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}	
			return table;
		}
	}	
	
	
	public StudyMember getId(int id) { 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql= "select id,name,hometown from java_study where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,id);  //sql�� �������ϼ��ִ� �տ����� �ε���
			rs=ps.executeQuery();
			
			if(rs.next()){
				int id1=rs.getInt("id");//index�ε�����
				String name =rs.getString("name");
				String hometown =rs.getString("hometown");
				sm=new StudyMember(id1,name,hometown);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}			
		}
			return sm;
	}
	
	
	@SuppressWarnings("finally")
	public int addMember(StudyMember sm) {
		int result =0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql= "Insert into java_study value(?,?,?)";
			ps= conn.prepareStatement(sql);
			ps.setInt(1,sm.getId());  //1,2,3�� ?�� �����Ǵ°�
			ps.setString(2,sm.getName());
			ps.setString(3,sm.getHometown());
			
			result=ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}	
			return result;
		}
	}

	@SuppressWarnings("finally")
	public int deleteMember(String input) {
		int result =0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql= "Delete from java_study where name=?";
			ps= conn.prepareStatement(sql);
			ps.setString(1,input);				
			result=ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}	
			return result;
		}
	}	
	
}
