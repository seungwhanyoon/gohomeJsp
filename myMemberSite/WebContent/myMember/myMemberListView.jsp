<%@page import="com.tg.member.MemberDto"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>회원목록</h1>
	<div>
		<a href="../../myMember/add">신규 회원</a>
<!-- 		<a href="./add">신규 회원</a> -->
	</div>
	<br/>
	
	<%
		ArrayList<MemberDto> myMemberList = 
			(ArrayList<MemberDto>)request.getAttribute("memberList");
			//┌>
		for(MemberDto memberDto : myMemberList){
	
	%>
	
	<%=memberDto.getNo()%>,
	<a href='./update?no=<%=memberDto.getNo()%>'><%=memberDto.getName()%></a>,
	<%=memberDto.getEmail()%>,
	<%=memberDto.getPassword()%>,
	<%=memberDto.getCreateDate()%>,
	<%=memberDto.getModifiedDate()%>
	<a href='./delete?no=<%=memberDto.getNo()%>'>[삭제]</a>
	<br>
	
	<%
		}
	%>
	

</body>
</html>