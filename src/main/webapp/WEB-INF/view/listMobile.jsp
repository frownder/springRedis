<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<meta name="viewport" content="device-width">
	
	<title>All-Players</title>
	
	<style>
		td { border:1px solid #AAA; text-align:center; }
		
		@media screen and (max-width:480px) {
			.vga {display:none}
			.xga {display:none}  
		}
		@media screen and (min-width:481px) and (max-width:767px) {
			.vga {display:none}
			.xga {display:show}  
		}
		@media screen and (min-width:768px) {
			.vga {display:show}
			.xga {display:show}  
		}
	</style>
</head>


<body>
	<h2>All-Players : ${fn:length(playerList)} </h2>
	
	<table>
		<tr style="background:#CCC">   
			<td> Player ID</td>
			<td> Marine Count </td>
			<td> Medic Count </td>
			<td class="vga"> Firebat Count </td>
			<td class="xga"> Ghost Count </td>
		</tr>
		
		<c:forEach var="player" items="${playerList}">
		<tr>   
			<td style="background:#EEE"> 
				  ${player.playerId}</td>
			<td> ${player.marineCount}</td>
			<td> ${player.medicCount}</td>
			<td class="vga"> ${player.firebatCount}</td>
			<td class="xga"> ${player.ghostCount}</td>
		</tr>
		</c:forEach>
	
	</table>
	
</body>
</html>