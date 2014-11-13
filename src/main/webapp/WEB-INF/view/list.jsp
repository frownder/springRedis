<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	
	<title>All Players</title>
	
	<style>
		td { border:1px solid #AAA; text-align:center; }
	</style>
	
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
		$(document).ready( function(){
			$("tr:first").css("background","#CCC");
			$("tr:gt(0) > td:nth-child(5n+1)").css("background","#EEE");
		});
	</script>
</head>


<body>
	<h2>All Players : ${fn:length(playerList)} </h2>
	
	<table>
		<tr>   
			<td> Player ID</td>
			<td> Marine Count </td>
			<td> Medic Count </td>
			<td> Firebat Count </td>
			<td> Ghost Count </td>
		</tr>
		
		<c:forEach var="player" items="${playerList}">
		<tr>   
			<td> ${player.playerId}</td>
			<td> ${player.marineCount}</td>
			<td> ${player.medicCount}</td>
			<td> ${player.firebatCount}</td>
			<td> ${player.ghostCount}</td>
		</tr>
		</c:forEach>
	
	</table>
	
</body>
</html>