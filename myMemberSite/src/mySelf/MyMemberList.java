package mySelf;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.tg.member.MemberDto;

@WebServlet(value="/my/member/list")
public class MyMemberList extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "jsp";
		String password = "jsp";
		
		String sql = "";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
						
			sql = "SELECT MNO, MNAME, EMAIL, PWD, CRE_DATE, MOD_DATE";
			sql += " FROM MEMBERS";
			sql += " ORDER BY MNO DESC";
			
			pstmt = conn.prepareStatement(sql);
					
			rs = pstmt.executeQuery();
			System.out.println("쿼리 수행 성공");
			
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			// request에 회원 목록 데이터 보관
			ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();
			
			int mno = 0;
			String mname = "";
			String email = "";
			String pwd = "";
			Date creDate = null;
			Date modDate = null;
			
			// 데이터베이스에서 회원 정보를 가져와 MemberDto에 담는다.
			// 그리고 MemberDto 객체를 ArrayList에 추가한다.
			while(rs.next()) {
				mno = rs.getInt("MNO");
				mname = rs.getString("MNAME");
				pwd = rs.getString("PWD");
				email = rs.getString("EMAIL");
				creDate = rs.getDate("CRE_DATE");
				modDate = rs.getDate("MOD_DATE");
				
				MemberDto memberDto = 
						new MemberDto(mno, mname, email, pwd, creDate, modDate);
				memberList.add(memberDto);
				
			} // while end
			
			// request에 회원 목록 데이터를 보관한다.
			req.setAttribute("memberList", memberList);
			
			// jsp로 출력을 위임한다.
			RequestDispatcher dispatcher =	//┌>(안에 있는 값)을 통해   경로 찾아감(?)
					req.getRequestDispatcher("/member/memberListView.jsp");
			
			dispatcher.include(req, res);	// 갖고있던 req와 res를 포함 시켜 디스패처 경로로 들고감.
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			//	예외처리 페이지로 위임
			req.setAttribute("error", e);
			RequestDispatcher dispatcher = 
					req.getRequestDispatcher("/error.jsp");
			
			dispatcher.forward(req, res);
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // if(rs != null) end
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} // finally end
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
	}
}
