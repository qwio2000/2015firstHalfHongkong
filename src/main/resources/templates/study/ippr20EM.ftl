<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<!--
' ##############################################################
' 페이지 이름 (버전) : 개인별진단처방기록부
' 페이지 설명 : 입력 대기 Page
' 개발자 / 개발일시 : IT지원1팀 Tabris / 2007-12-25
' 수정자 / 수정일시 :
' 수정 내용 :
' ##############################################################

Dim Jisa,OmrDate, Hkey, Kwamok, Grp, Avg, Mujin, PreShow

Jisa	= NIF(Request("Jisa"), "08")
Omrdate = NIF(Request("PopOmrdate"), "")
Hkey	= NIF(Request("PopHkey"), "")
Kwamok = NIF(Request("PopKwamok"), "")
Grp = NIF(Request("PopGraph"), "")
Avg = NIF(Request("PopAverage"), "")
Mujin = Bif (Request("Mujin") = "", "0", NIF(Request("Mujin"), ""))
PreShow = Bif (Request("PreShow") = "", "0", NIF(Request("PreShow"), "")) ' 1: PreShow, 0: Print

grp = BIF ( grp = "", "B", grp)
avg = BIF ( avg = "", "N", avg)

'fwrite  Omrdate & "<BR>"
'fwrite  Hkey & "<BR>"
'fwrite  Kwamok & "<BR>"
'fwrite  NIF(Request("PopGraph"), "") & "<BR>"
'fwrite  Avg & "<BR>"
'fwrite  Mujin & "<BR>"

'오답번호
Function UFN_DisplayOdab(OdabNo)
	UFN_DisplayOdab = ""
	Select Case OdabNo
		Case "01" : UFN_DisplayOdab = "①"
		Case "02" : UFN_DisplayOdab = "②"
		Case "03" : UFN_DisplayOdab = "③"
		Case "04" : UFN_DisplayOdab = "④"
		Case "05" : UFN_DisplayOdab = "⑤"
		Case "06" : UFN_DisplayOdab = "⑥"
		Case "07" : UFN_DisplayOdab = "⑦"
		Case "08" : UFN_DisplayOdab = "⑧"
		Case "09" : UFN_DisplayOdab = "⑨"
		Case "10" : UFN_DisplayOdab = "⑩"
		Case "11" : UFN_DisplayOdab = "⑪"
		Case "12" : UFN_DisplayOdab = "⑫"
	End Select
End Function

'평가세트
Function UFN_DisplayJinSet(F_Kwamok, F_SET)
	If F_SET = "Z999" Then
		UFN_DisplayJinSet = "<font style='font-size:11px;'>COM</font>"
	ElseIf F_SET = "5000" Then
		If F_Kwamok="G" Then
			UFN_DisplayJinSet = "국어0"
		Else
			UFN_DisplayJinSet = "영어0"
		End If
	ElseIf F_SET = "5001" Then
		If F_Kwamok="G" Then
			UFN_DisplayJinSet = "국어1"
		Else
			UFN_DisplayJinSet = "영어1"
		End If
	ElseIf Right(F_SET, 3) = "000" Then
		UFN_DisplayJinSet = LEFT(F_SET, 1) & "-Dig."
	ElseIf Right(F_SET, 3) = "999" Then
		UFN_DisplayJinSet = LEFT(F_SET, 1) & "-Ach."
	ElseIf Right(F_SET, 3) = "992" Then
		UFN_DisplayJinSet = LEFT(F_SET, 1) & "-Rev."
	ElseIf Right(F_SET, 3) = "994" Then
		UFN_DisplayJinSet = LEFT(F_SET, 1) & "-Prep."
	Else
		UFN_DisplayJinSet = F_SET
	End If
End Function

'월변환
Function UFN_DisplayMonth(MM)
	UFN_DisplayMonth = ""
	Select Case MM
		Case "1" : UFN_DisplayMonth = "JAN"
		Case "2" : UFN_DisplayMonth = "FEB"
		Case "3" : UFN_DisplayMonth = "MAR"
		Case "4" : UFN_DisplayMonth = "APR"
		Case "5" : UFN_DisplayMonth = "MAY"
		Case "6" : UFN_DisplayMonth = "JUN"
		Case "7" : UFN_DisplayMonth = "JUL"
		Case "8" : UFN_DisplayMonth = "AUG"
		Case "9" : UFN_DisplayMonth = "SEP"
		Case "10" : UFN_DisplayMonth = "OCT"
		Case "11" : UFN_DisplayMonth = "NOV"
		Case "12" : UFN_DisplayMonth = "DEC"
	End Select
End Function

'날자형식변환
Function UFN_DisplayDate(YYYYMMDD)
	Dim UFN_DisplayDateYYYY , UFN_DisplayDateMM, UFN_DisplayDateDD

	UFN_DisplayDateYYYY = Left(YYYYMMDD,4)
	UFN_DisplayDateMM = Mid(YYYYMMDD,6,2)
	UFN_DisplayDateDD = Right(YYYYMMDD,2)
	UFN_DisplayDate = UFN_DisplayDateMM+"/"+UFN_DisplayDateDD+"/"+UFN_DisplayDateYYYY
End Function

Set adoCmd = Server.CreateObject("ADODB.Command")		'## 3. 반드시 연결 객체를 생성 합니다.

'If Mujin = "0" And PreShow = "0" Then ' 출력정보 기록
'	with adoCmd
'		.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
'		.CommandType = adCmdStoredProc
'		.CommandText = "GBJINDO.DBO.USP_JD_OmrPrt_Ins"
'		.Parameters.Append .CreateParameter("RETURN_VLAUE",adinteger,adParamReturnValue)
'		.Parameters.append .CreateParameter("@Omrdate", advarchar, adParamInput, 10, Omrdate )
'		.Parameters.append .CreateParameter("@HKEY", advarchar, adParamInput, 8, Hkey )
'		.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
'		.Parameters.append .CreateParameter("@WorkId", advarchar, adParamInput, 10, WorkId )
'		.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
'
'		.Execute , , adExecuteNoRecords
'
'		iErr = .Parameters("RETURN_VLAUE").value
'
'	End with
'End If

'처방기초정보
Dim range_su1,range_su2,range_su3,range_su4,range_su5,range_su6,range_su7,range_su8,range_su9, i
Dim Graph, Tot, Jung, Sung, tsung, MaxSung, MaxRange, MaxTot, RangeSung
Dim SpName, adoCmd, RS, iErr
Dim r_date, Ju
Dim KwamokNM, Hname, OmrBirth, Sname, OmrGrd, OmrHak, OmrHakNM, OmrKind, OmrKindNM, OmrYoil
Dim OmrYoilNM, DeptName, DepTel, BoSetSu, Pan, SP, SPNM, SilsiYMD
Dim ChongPyung
'fwrite "exec USP_JD_OmrPrintGicho '"& Jisa &"','" & Omrdate &"','" &Hkey &"','" &Kwamok &"','" &Mujin &"'"
'die
Call ClearCmd(AdoCmd)
with adoCmd
	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
	.CommandType = adCmdStoredProc
	.CommandText = "GBJINDO.DBO.USP_JD_OmrPrintGicho"
	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
	.Parameters.append .CreateParameter("@Omrdate", adchar, adParamInput, 10, Omrdate )
	.Parameters.append .CreateParameter("@Hkey", adchar, adParamInput, 8, Hkey )
	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
	If RS.Eof Then	 								'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
		iErr = 1
	Else
		iErr = 0

		Hname = rs("Hname")
		Sname = rs("Sname")
		OmrGrd = rs("Omr_grd")
		OmrHak = rs("Omr_Hak")
		OmrHakNM = rs("Omr_HakNM")
		OmrKind = rs("Omr_Kind")
		OmrKindNM = rs("Omr_KindNM")
		OmrYoil = rs("Omr_Day")
		OmrBirth = rs("Omr_birth")
		SilsiYMD = rs("SilsiYMD")
		OmrYoilNM = rs("OmrYoilNM")
		DeptName = rs("Deptname")
		DepTel = rs("Tel")
		BoSetSu = rs("bo_set_su")
		Pan = rs("Pan")
		SP = rs("SP")
		SPNM = rs("SPNM")

		If Kwamok = "P" Then
			If OmrGrd < "D" Then
				KwamokNM = "생각하는 리틀피자"
			Else
				KwamokNM = "생각하는 P!zzaa"
			End If
		Else
			KwamokNM = rs("kwamokNM")
		End If
	End If
	RS.Close : Set RS = Nothing

End with

'오답내용
Dim AryOdab1_1, AryOdab1_2, AryOdab2, AryOdab3, AryOdab4, iOdabCnt, iOdabCnt1, iOdabCnt2, iOdabCnt3, iOdabCnt4
Call ClearCmd(AdoCmd)
with adoCmd
	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
	.CommandType = adCmdStoredProc
	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintOdab1_1"
	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
	.Parameters.append .CreateParameter("@OmrDate", advarchar, adParamInput, 10, OmrDate )
	.Parameters.append .CreateParameter("@Hkey", advarchar, adParamInput, 8, Hkey )
	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
	.Parameters.append .CreateParameter("@Gubun", advarchar, adParamInput, 1, "" )
	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
	If RS.Eof Then									'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
		iOdabCnt = 0
	Else
		AryOdab1_1 = rs.getrows()
		iOdabCnt = Ubound(AryOdab1_1, 2) + 1
	End If

	RS.Close : Set RS = Nothing
End with

'학습내용별 분석
Call ClearCmd(AdoCmd)
'fwrite "GBJINDO.dbo.USP_JD_OmrPrintOdab1_2 '"&Jisa&"','"&OmrDate&"','"&Hkey&"','"&Kwamok&"','','"&Mujin&"'"
with adoCmd
	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
	.CommandType = adCmdStoredProc
	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintOdab1_2"
	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
	.Parameters.append .CreateParameter("@OmrDate", advarchar, adParamInput, 10, OmrDate )
	.Parameters.append .CreateParameter("@Hkey", advarchar, adParamInput, 8, Hkey )
	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
	.Parameters.append .CreateParameter("@Gubun", advarchar, adParamInput, 1, "" )
	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
	If RS.Eof Then								'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
		iOdabCnt1 = 0
	Else
		AryOdab1_2 = rs.getrows()
		iOdabCnt1 = Ubound(AryOdab1_2, 2) + 1
	End If

	RS.Close : Set RS = Nothing
End with

'
'Call ClearCmd(AdoCmd)
'with adoCmd
'	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
'	.CommandType = adCmdStoredProc
'	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintOdab2"
'	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
'	.Parameters.append .CreateParameter("@OmrDate", advarchar, adParamInput, 10, OmrDate )
'	.Parameters.append .CreateParameter("@Hkey", advarchar, adParamInput, 8, Hkey )
'	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
'	.Parameters.append .CreateParameter("@Gubun", advarchar, adParamInput, 1, "" )
'	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
'	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
'	If RS.Eof Then									'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
'		iOdabCnt2 = 0
'	Else
'		AryOdab2 = rs.getrows()
'		iOdabCnt2 = Ubound(AryOdab2, 2) + 1
'	End If
'
'	RS.Close : Set RS = Nothing
'End with

'Call ClearCmd(AdoCmd)
'with adoCmd
'	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
'	.CommandType = adCmdStoredProc
'	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintOdab3"
'	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
'	.Parameters.append .CreateParameter("@OmrDate", advarchar, adParamInput, 10, OmrDate )
'	.Parameters.append .CreateParameter("@Hkey", advarchar, adParamInput, 8, Hkey )
'	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
'	.Parameters.append .CreateParameter("@OmrHak", advarchar, adParamInput, 2, OmrHak )
'	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
'	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
'	If RS.Eof Then									'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
'		iOdabCnt3 = 0
'	Else
'		AryOdab3 = rs.getrows()
'		iOdabCnt3 = Ubound(AryOdab3, 2) + 1
'	End If
'
'	RS.Close : Set RS = Nothing
'End with

'오답사례별분석
Call ClearCmd(AdoCmd)
'fwrite "GBJINDO.dbo.USP_JD_OmrPrintOdab4 '"&Jisa&"','"&OmrDate&"','"&Hkey&"','"&Kwamok&"','','"&Mujin&"'"
with adoCmd
	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
	.CommandType = adCmdStoredProc
	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintOdab4"
	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
	.Parameters.append .CreateParameter("@OmrDate", advarchar, adParamInput, 10, OmrDate )
	.Parameters.append .CreateParameter("@Hkey", advarchar, adParamInput, 8, Hkey )
	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
	.Parameters.append .CreateParameter("@Gubun", advarchar, adParamInput, 1, "")
	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
	If RS.Eof Then									'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
		iOdabCnt4 = 0
	Else
		AryOdab4 = rs.getrows()
		iOdabCnt4 = Ubound(AryOdab4, 2) + 1
	End If

	RS.Close : Set RS = Nothing
End with

'영역분석(회원)
Dim AryOmrRange
Call ClearCmd(AdoCmd)
'fwrite "GBJINDO.dbo.USP_JD_OmrPrintRange '"&Jisa&"','"&OmrDate&"','"&Hkey&"','"&Kwamok&"','"&Mujin&"'"
with adoCmd
	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
	.CommandType = adCmdStoredProc
	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintRange"
	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
	.Parameters.append .CreateParameter("@OmrDate", advarchar, adParamInput, 10, OmrDate )
	.Parameters.append .CreateParameter("@Hkey", advarchar, adParamInput, 8, Hkey )
	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
		If RS.Eof Then									'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
			AryOmrRange = ""
		Else
			AryOmrRange = rs.getrows()
		End If

		RS.Close : Set RS = Nothing
End with

'영역별분석(전체)
Dim ArykwamokRange
Call ClearCmd(AdoCmd)

with adoCmd
	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
	.CommandType = adCmdStoredProc
	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintRangeAll_Get"
	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
	.Parameters.append .CreateParameter("@Dung",   advarchar, adParamInput, 1, OmrGrd )
	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
	If RS.Eof Then									'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
		AryOmrRange = ""
	Else
		ArykwamokRange = rs.getrows()
	End If

	RS.Close : Set RS = Nothing
End with

Dim Soojun1, Soojun2, Soojun3, Soojun4
'Dim ChongHak, ChongHakGi, ChongWol, ChongCnt

'학습수준분석
'fwrite OmrBirth
'Call ClearCmd(AdoCmd)
'with adoCmd
'	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
'	.CommandType = adCmdStoredProc
'	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintSooJun"
'	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
'	.Parameters.append .CreateParameter("@OmrDate", advarchar, adParamInput, 10, OmrDate )
'	.Parameters.append .CreateParameter("@Hkey", advarchar, adParamInput, 8, Hkey )
'	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
'	.Parameters.append .CreateParameter("@Omr_Kind", advarchar, adParamInput, 1, OmrKind )
'	.Parameters.append .CreateParameter("@OMR_GRD", advarchar, adParamInput, 1, OmrGrd )
'	.Parameters.append .CreateParameter("@OMR_Hak", advarchar, adParamInput, 2, OmrHak )
'	.Parameters.append .CreateParameter("@Pan", advarchar, adParamInput, 10, Pan )
'	.Parameters.append .CreateParameter("@BoSetSu", adInteger, adParamInput, , BoSetSu )
'	.Parameters.append .CreateParameter("@OMR_BIRTH", advarchar, adParamInput, 10, OmrBirth )
'	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
'	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
'	If RS.Eof Then									'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
'		Soojun1 = 0
'		Soojun2 = 0
'		Soojun3 = 0
'		Soojun4 = 0
'	Else
'		Soojun1 	= RS("SilSu1")
'		Soojun2 	= RS("SilSu2")
'		Soojun3 	= RS("SilSu3")
'		Soojun4 	= RS("SilSu4")
'		'ChongHak 	= RS("ChongHak")
'		'ChongHakGi 	= RS("ChongHakGi")
'		'ChongWol 	= RS("ChongWol")
'		'ChongPyung 	= RS("ChongPyung")
'		'ChongCnt 	= RS("ChongCnt")
'	End If
'
'	RS.Close : Set RS = Nothing
'End with

'총평
'if OmrHak <> "00"  and Soojun4 <= 2 then
'	if Soojun4 = 2 then
'		ChongPyung = ChongPyung & " " & Hname & "회원은 현재 " & OmrHakNM & "학년으로 학교진도의 학습정도가 매우 우수합니다. "
'		ChongPyung = ChongPyung & "앞으로 재능스스로수학으로 꾸준히 학습하게 되면 "
'		ChongPyung = ChongPyung & round(ChongCnt / 4.5, 1) & "개월 후에는 " & (ChongHak) & "학년 " & (ChongHakGi) & "학기 " & (ChongWol) & "월에 "
'		ChongPyung = ChongPyung & "학습하는 내용까지도 진행할 수 있습니다."
'	elseif Soojun4 = 1 then
'		ChongPyung = ChongPyung & " " & Hname & "회원은 현재 " & OmrHakNM & "학년으로 학교진도의 학습정도가 보통니다."
'		ChongPyung = ChongPyung & "앞으로 학교에서 배웠어야 할 내용 중 부족한 부분까지 재능스스로수학으로 꾸준히 학습하게 되면 "
'		ChongPyung = ChongPyung & round(ChongCnt / 4.5, 1) & "개월 후에는 " & (ChongHak) & "학년 " & (ChongHakGi) & "학기 " & (ChongWol) & "월에 "
'		ChongPyung = ChongPyung & "학습하는 내용까지도 진행할 수 있습니다."
'	else
'		ChongPyung = ChongPyung & " " & Hname & "회원은 현재 " & OmrHakNM & "학년으로 학교진도의 학습정도가 다소 부족합니다."
'		ChongPyung = ChongPyung & "앞으로 학교에서 배웠어야 할 내용 중 부족한 부분까지 재능스스로수학으로 꾸준히 학습하게 되면 "
'		ChongPyung = ChongPyung & round(ChongCnt / 4.5, 1) & "개월 후에는 " & (ChongHak) & "학년 " & (ChongHakGi) & "학기 " & (ChongWol) & "월에 "
'		ChongPyung = ChongPyung & "학습하는 내용까지도 진행할 수 있습니다."
'	end if
'end if

'처방프로그램(진도)
'fwrite "GBJINDO.dbo.USP_JD_OmrPrintWol '"&Jisa&"','"&Omrdate&"','"&Hkey&"','"&Kwamok&"','"&Mujin&"'"
Call ClearCmd(AdoCmd)
with adoCmd
	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
	.CommandType = adCmdStoredProc
	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintWol"
	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
	.Parameters.append .CreateParameter("@Omrdate", adchar, adParamInput, 10, Omrdate )
	.Parameters.append .CreateParameter("@Hkey", adchar, adParamInput, 8, Hkey )
	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
	If RS.Eof Then									'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
		iErr = 1
	Else
		iErr = 0
		r_date = RS("r_yy") & "-" & RS("r_mm") & "-01"
	End If

	'## 9. 필요한 모든 값을 가져 왔으므로 레코드셋 객체를 소멸시킵니다.
	RS.Close : Set RS = Nothing
End with

'예상진도
Dim AryNextJinSet, iNextJinSetCnt
Call ClearCmd(AdoCmd)
with adoCmd
	.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
	.CommandType = adCmdStoredProc
	.CommandText = "GBJINDO.dbo.USP_JD_OmrPrintNext"
	.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
	.Parameters.append .CreateParameter("@OmrDate", advarchar, adParamInput, 10, OmrDate )
	.Parameters.append .CreateParameter("@Hkey", advarchar, adParamInput, 8, Hkey )
	.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
	.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
	Set RS = .execute									'## 5. 실행 값을 레코드 셋으로 연결하여 실행 합니다.
		If RS.Eof Then									'## 6. 리턴된 레크드 셋의 값이 있을 경우에만 사용 가능합니다.
			AryNextJinSet = ""
			iNextJinSetCnt = 0
		Else
			AryNextJinSet = rs.getrows()
			iNextJinSetCnt = Ubound(AryNextJinSet, 2) + 1
		End If

		RS.Close : Set RS = Nothing
End with

Set adoCmd = Nothing

-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<title>INDIVIDUAL PROGRESS PRESCRIPTION REPORT</title>
<style type="text/css">
<!--
body
{
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
    font-family:Dotum;
    color:#000000;
    font-size:12px;
}
.style1 {
    color:#000000;
	font-size: 11px;
	font-weight: bold;
}
.style2 {
    color:#000000;
	font-size: 10px;
}
.style3 {
    color:#000000;
	font-size: 10px;
	font-weight: bold;
}
.style4 {
    color:#000000;
	font-size: 12px;
	font-weight: bold;
}
.style7 {
    color:#000000;
	font-size: 11px;
    line-height:120%;
}
.style9 {color:#000000;
	font-size: 12px;
	font-weight: bold;
}
.style11 {color:#000000;
	font-size: 12px;
    line-height:130%;
}
.style12 {
    color:#000000;
	font-size: 11;
    line-height:130%;
}
.style21 {
    color:#000000;
	font-size: 11px;
    line-height:120%;
	font-weight: bold;
}
.style22 {
    color:#000000;
	font-size: 11px;
}
.style23{
	font-size:13px;
	font-face:'';
	font-weight:bold
}

img {border:0;}
.styledm {width:648px;text-align:center; border-left:1px solid #000;font:10px Dotum; border-collapse:collapse;}
.styledm caption {display:none;}
.styledm tfoot {font-weight:bold; background-color:#F5F7F9;}
.styledm th {padding:6px 0 3px 0; background-color:#99c2ff; border-top:1px solid #000; border-left:1px solid #000; border-right:1px solid #000; font:11px dotum; font-weight:bold;}

.styledm td {padding:4px 0 2px 0; border-top:1px dashed #000;border-right:1px solid #000;}
.styledm th, td.line{border-bottom:solid 1px #000;}
.styledm td.dotted_line{border-bottom:dashed 1px #d0d0d0;border-right:dashed 1px #d0d0d0;}

.styledm td em{color:#ff0000;font-weight:bold;font-style:normal;}
.styledm td.ranking {font-weight:bold;}
.styledm td.graph_line{padding:0;margin:0;text-align:left;}
-->
</style>
</head>

<body>
<#include "/function.ftl">

<center>
<!-- Page1 -->
<table width="648" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="3" align="center" height="10"></td>
	</tr>
</table>
<table width="648" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="3" align="center"><font style="font-size:17px; font-face:'';font-weight:bold">INDIVIDUAL PROGRESS PRESCRIPTION REPORT(I)</font></td>
	</tr>
	<tr>
		<td height="20" width="30%" align="left" valign="bottom" class="style4"></td>
		<td width="30%" align="left" valign="bottom" class="style4"></td>
		<td width="40%" align="right" valign="bottom" class="style4">Center : ${rsGicho.depidNM}</td>
	</tr>
</table>

<!-- 회원정보 -->
<table width="648" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor='#000000'>
	<tr>
		<td width="93" height="20" align="center" bgcolor="#99c2ff" class="style1">Name</td>
		<td width="79" align="center" bgcolor="#99c2ff" class="style1">Date of Birth</td>
		<td width="79" align="center" bgcolor="#99c2ff" class="style1">Grade</td>
		<td width="79" align="center" bgcolor="#99c2ff" class="style1">Member ID</td>
		<td width="79" align="center" bgcolor="#99c2ff" class="style1">Subject</td>
		<td width="79" align="center" bgcolor="#99c2ff" class="style1">Level</td>
		<td width="79" align="center" bgcolor="#99c2ff" class="style1">Test Type</td>
		<td width="79" align="center" bgcolor="#99c2ff" class="style1">Input Date</td>
	</tr>
	<tr>
		<td height="20" align="center" bgcolor="#FFFFFF" class="style1">${rsGicho.hname}</td>
		<td align="center" bgcolor="#FFFFFF" class="style1">${rsGicho.omrBirth}</td>
		<td align="center" bgcolor="#FFFFFF" class="style1">${rsGicho.omrHakNM}</td>
		<td align="center" valign="middle" bgcolor="#FFFFFF" class="style1">${rsGicho.hkey}</td>
		<td align="center" bgcolor="#FFFFFF" class="style1">Math</td>
		<td align="center" bgcolor="#FFFFFF" class="style1">${rsGicho.omrGrd}</td>
		<td align="center" bgcolor="#FFFFFF" class="style1">${rsGicho.omrKindNM}</td>
		<td align="center" bgcolor="#FFFFFF" class="style1">${rsGicho.omrDate}</td>
	</tr>
	<tr>
		<td height="20" align="center" bgcolor="#FFFFFF" class="style1">S/P</td>
		<td colspan="7" bgcolor="#FFFFFF" class="style7" style="padding:0 0 0 3">${rsGicho.sp}${rsGicho.spNM}</td>
	</tr>
</table>

<!-- 오답내용 -->
<br>
<table width="648" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor='#ffffff'>
	<tr>
		<td colspan="3" height='20' class="style23" align="center" bgcolor="#ffffff">CONTENTS OF ERRORS</td>
	</tr>
	<tr>
		<td width="325" valign="top">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="border:1px; border-style:solid; border-color:#000000;">
			<tr>
				<td width="34" height="22" align="center" bgcolor="#99c2ff" class="style1">No.</td>
				<td width="1" bgcolor="#000000"></td>
				<td width="258" align="center" bgcolor="#99c2ff" class="style1">Objectives</td>
				<td width="1" bgcolor="#000000"></td>
				<td width="30" align="center" bgcolor="#99c2ff" class="style3">Domain</td>
			</tr>
			<tr>
				<td height="1" colspan="5" bgcolor="#000000"></td>
			</tr>
<!--
	Dim OdabStr, Odab2Str, OdabGubun, OdabHakObj, OdabHakJin, MaxOdabLine, OdabWidth
	Dim OdabGubunStyle, addBG
	MaxOdabLine = 28 : OdabWidth = 22	'미주 26라인, 대양주,홍콩 28라인
	'MaxOdabLine = 26 : OdabWidth = 22
	For i = 0 To MaxOdabLine - 1
		If iOdabCnt > 0 And i <= (iOdabCnt - 1) Then
			OdabStr 		= AryOdab1_1(0, i)
			Odab2Str 		= AryOdab1_1(1, i)
			OdabGubun 	    = Trim(AryOdab1_1(2, i))
			OdabHakObj 	    = "&nbsp;" & AryOdab1_1(3, i)
			OdabHakJin 	    = AryOdab1_1(4, i)

			If Kwamok = "EM" Then
				OdabStr = OdabStr & UFN_DisplayOdab(Odab2Str)
			End If
		Else
			OdabStr 	= "&nbsp;"
			OdabGubun 	= "&nbsp;"
			OdabHakObj 	= "&nbsp;"
			OdabHakJin 	= "&nbsp;"
		End If

		'2009-09-10 수정사항 박경우
		If (Right(OdabGubun,1)="A" Or Right(OdabGubun,1)="B") Then
			OdabGubunStyle = "style21"
		Else
			OdabGubunStyle = "style7"
		End If

		'배경색결정
		If CInt(i / 2) * 2 = i Then
			addBG="#FFFFFF"
		Else
			addBG="dadbdd"
		End If
-->

		<#assign OdabmaxLine = 28>
		<#assign odabHeight = 22>
		
		<!-- Contents of Errors - Left -->	
		<#assign odabIndex = 0>	
		<#if rsOdab11Left?has_content>
		<#list rsOdab11Left as odab11Left>
			<#assign odab1Str = odab11Left.odab1>
			<#assign odab2Str = odab11Left.odab2>
			<#assign odabGubun = odab11Left.hakGubun>
			<#assign odabHakObj = odab11Left.odabNM>
			<#assign odabHakJin = odab11Left.odabSchool>
			<#assign odabIndex = odab11Left_index+1>
			<#if odabGubun?substring(1,2) = "A" || odabGubun?substring(1,2) = "B">
				<#assign odabGubunStyle = "style21">
			<#else>
				<#assign odabGubunStyle = "style7">
			</#if> 
			<#if (odabIndex/2)?int*2 != odabIndex>
				<#assign addBG = "#FFFFFF">
			<#else>
				<#assign addBG = "dadbdd">
			</#if> 

			<tr>
				<td height="${odabHeight}" align="center" bgcolor="${addBG}" class="${odabGubunStyle}">${odab1Str}${displayOdab(odab2Str)}</td>
				<td bgcolor="#000000"></td>
				<td align="" bgcolor="${addBG}" class="${odabGubunStyle}">&nbsp;${odabHakObj}</td>
				<td bgcolor="#000000"></td>
				<td align="center" bgcolor="${addBG}" class="${odabGubunStyle}">${odabGubun}</td>
			</tr>	
							
		</#list>			
		</#if>

		<#if odabIndex+1 < OdabmaxLine>
		<#list odabIndex+1..OdabmaxLine as i>
			<#assign odabGubunStyle = "style7">
			<#if (i/2)?int*2 != i>
				<#assign addBG = "#FFFFFF">
			<#else>
				<#assign addBG = "dadbdd">
			</#if> 
							
			<tr>
				<td height="${odabHeight}" align="center" bgcolor="${addBG}" class="${odabGubunStyle}">&nbsp;${i}</td>
				<td width="1" bgcolor="#000000"></td>
				<td align="" bgcolor="${addBG}" class="${odabGubunStyle}">&nbsp;</td>
				<td width="1" bgcolor="#000000"></td>
				<td align="center" bgcolor="${addBG}" class="${odabGubunStyle}">&nbsp;</td>
			</tr>
			
		</#list>		
		</#if>			
<!-- Next -->
		</table>
		</td>
		<td width='1' bgcolor='#ffffff'></td>
		<td width='324' valign="top">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="border:1px; border-style:solid; border-color:#000000;">
			<tr>
				<td width="34" height="22" align="center" bgcolor="#99c2ff" class="style1">No.</td>
				<td width="1" bgcolor="#000000"></td>
				<td width="257" align="center" bgcolor="#99c2ff" class="style1">Objectives</td>
				<td width="1" bgcolor="#000000"></td>
				<td width="30" align="center" bgcolor="#99c2ff" class="style3">Domain</td>
			</tr>
			<tr>
				<td height="1" colspan="5" bgcolor="#000000"></td>
			</tr>

		<!-- Contents of Errors - Right -->
		<#assign odabIndex = 0>		
		<#if rsOdab11Right?has_content>
		<#list rsOdab11Right as odab11Right>
			<#assign odab1Str = odab11Left.odab1>
			<#assign odab2Str = odab11Left.odab2>
			<#assign odabGubun = odab11Left.hakGubun>
			<#assign odabHakObj = odab11Left.odabNM>
			<#assign odabHakJin = odab11Left.odabSchool>
			<#assign odabIndex = odab11Right_index+1>
			<#if odabGubun?substring(1,2) = "A" || odabGubun?substring(1,2) = "B">
				<#assign odabGubunStyle = "style21">
			<#else>
				<#assign odabGubunStyle = "style7">
			</#if> 
			<#if (odabIndex/2)?int*2 != odabIndex>
				<#assign addBG = "#FFFFFF">
			<#else>
				<#assign addBG = "dadbdd">
			</#if> 

			<tr>
				<td height="${odabHeight}" align="center" bgcolor="${addBG}" class="${odabGubunStyle}">${odab1Str}${displayOdab(odab2Str)}</td>
				<td bgcolor="#000000"></td>
				<td align="" bgcolor="${addBG}" class="${odabGubunStyle}">&nbsp;${odabHakObj}</td>
				<td bgcolor="#000000"></td>
				<td align="center" bgcolor="${addBG}" class="${odabGubunStyle}">${odabGubun}</td>
			</tr>					
		</#list>			
		</#if>

		<#if odabIndex+1 < OdabmaxLine>
		<#list odabIndex+1..OdabmaxLine as i>
			<#assign odabGubunStyle = "style7">
			<#if (i/2)?int*2 != i>
				<#assign addBG = "#FFFFFF">
			<#else>
				<#assign addBG = "dadbdd">
			</#if> 
							
			<tr>
				<td height="${odabHeight}" align="center" bgcolor="${addBG}" class="${odabGubunStyle}">&nbsp;${i}</td>
				<td width="1" bgcolor="#000000"></td>
				<td align="" bgcolor="${addBG}" class="${odabGubunStyle}">&nbsp;</td>
				<td width="1" bgcolor="#000000"></td>
				<td align="center" bgcolor="${addBG}" class="${odabGubunStyle}">&nbsp;</td>
			</tr>
			
		</#list>		
		</#if>	
		</table>
		</td>
	</tr>
</table>

<!-- 영역별 분석 --><!-- 학습수준분석 -->
<!--
	'2009-09-10  이재춘요청 박경우

	Dim RangeCnt : RangeCnt = ArykwamokRange(0, 0)

	'영역명가져오기
	Dim aryKwamokRangeNM()
	ReDim aryKwamokRangeNM(RangeCnt)
	For i = 1 To RangeCnt
		aryKwamokRangeNM(i-1) = "("&i&")"&ArykwamokRange(i, 0)
	Next

	'문항수
	Dim aryOmrRangeMunTot()
	ReDim aryOmrRangeMunTot(RangeCnt)
	For i = 0 To RangeCnt-1
		aryOmrRangeMunTot(i) = BIF(AryOmrRange(i * 2, 0)=0,  "", AryOmrRange(i * 2, 0))
	Next

	'맞은수
	Dim aryOmrRangeJungTot()
	ReDim aryOmrRangeJungTot(RangeCnt)
	For i = 0 To RangeCnt-1
		aryOmrRangeJungTot(i) = BIF(AryOmrRange(i * 2, 0)=0,  "", AryOmrRange(i * 2 + 1, 0))
	Next

	'성취율
	Dim aryOmrRangeCompletePrt()
	ReDim aryOmrRangeCompletePrt(RangeCnt)
	For i = 0 To RangeCnt-1
		'fwrite i& ":" &AryOmrRange(i * 2 + 1,0) &","& AryOmrRange(i * 2, 0) &"<br>"
		If AryOmrRange(i * 2, 0)=0 Then
			aryOmrRangeCompletePrt(i) = ""
		Else
			aryOmrRangeCompletePrt(i) = FormatNumber(AryOmrRange(i * 2 + 1, 0)/AryOmrRange(i * 2, 0)*100,0)
		End If
		'aryOmrRangeCompletePrt(i) = BIF(AryOmrRange(i * 2, 0)=0,  "&nbsp", FormatNumber(AryOmrRange(i * 2 + 1, 0)/AryOmrRange(i * 2, 0)*100,0))
	Next

	'영역별 그래프길이 (100점 180픽셀로 맞춘다)
	Dim aryOmrRangePrt
	ReDim aryOmrRangeGraphWidth(RangeCnt)
	For i = 0 To RangeCnt-1
		If aryOmrRangeCompletePrt(i) = "" Then
			aryOmrRangeGraphWidth(i) = ""
		Else
			'aryOmrRangeGraphWidth(i) = aryOmrRangeCompletePrt(i) * 1.8
			aryOmrRangeGraphWidth(i) = aryOmrRangeCompletePrt(i) * 0.9
		End If
	Next

	'총계
	Dim OmrRangeMunTot, OmrRangeJungTot, OmrRangePtgTot, OmrRangePtgTotGraphWidth
	If AryOmrRange(18, 0)=0 Then
		OmrRangeMunTot=""	'전체문항수
		OmrRangeJungTot=""	'전체맞은수
		OmrRangePtgTot=""	'종하성취율
	Else
		OmrRangeMunTot=AryOmrRange(18, 0)	'전체문항수
		OmrRangeJungTot=AryOmrRange(19, 0)	'전체맞은수
		OmrRangePtgTot=FormatNumber(OmrRangeJungTot/OmrRangeMunTot*100,0)	'종합성취율
		'OmrRangePtgTotGraphWidth= OmrRangePtgTot * 0.8
		OmrRangePtgTotGraphWidth= OmrRangePtgTot * 0.9
	End If
-->

<br>
<table width="648" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor='#ffffff'>
	<tr>
		<td height='20' class="style23" align="center" bgcolor="#ffffff">ANALYSIS BY DOMAINS</td>
	</tr>
	<tr>
		<td valign="top">
			<!-- 영역별 분석 -->

			<table width="100%" cellspacing="0" class="styledm" summary="ANALYSIS BY DOMAINS">
			<caption>ANALYSIS BY DOMAINS</caption>
			<colgroup>
			<col width="144px">
			<col width="60px">
			<col width="120px">
			<col width="144px">
			<col width="60px">
			<col width="120px">
			</colgroup>
			<thead>
			 <tr>
			  <th scope="col">Learning Domain</th>
			  <th scope="col">Correct/<br>Total</th>
			  <th scope="col">Achievement(%)</th>
			  <th scope="col">Learning Domain</th>
			  <th scope="col">Correct/<br>Total</th>
			  <th scope="col">Achievement(%)</th>
			 </tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><%=aryKwamokRangeNM(0)%></td>
					<td>
					<!-- If aryOmrRangeMunTot(0) <> "" Then -->
						<%=aryOmrRangeJungTot(0)%>/<%=aryOmrRangeMunTot(0)%>
					<!-- Else -->
						&nbsp;
					<!-- End If -->
					</td>
					<td class="graph_line" align="left">
						<p class="graph">
						<!-- If aryOmrRangeCompletePrt(0) <> "" And BIF(aryOmrRangeMunTot(0)="",0,aryOmrRangeMunTot(0)) > 4 Then -->
							<img src="../../images/bg_blue.gif" height="15" border="0" align="absmiddle" width="<%=aryOmrRangeGraphWidth(0)%>">&nbsp;<span class="style12"><%=aryOmrRangeCompletePrt(0)%></span>
						<!-- Else %>
							&nbsp;
						<!-- End If %>
						</p>
					</td>
					<td align="center"><%=aryKwamokRangeNM(3)%></td>
					<td>
						<!-- If aryOmrRangeMunTot(3) <> "" Then -->
							<%=aryOmrRangeJungTot(3)%>/<%=aryOmrRangeMunTot(3)%>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
					</td>
					<td class="graph_line" align="left">
						<p class="graph">
						<!-- If aryOmrRangeCompletePrt(3) <> "" And BIF(aryOmrRangeMunTot(3)="",0,aryOmrRangeMunTot(3)) > 4 Then -->
							<img src="../../images/bg_blue.gif" height="15" border="0" align="absmiddle" width="<%=aryOmrRangeGraphWidth(3)%>">&nbsp;<span class="style12"><%=aryOmrRangeCompletePrt(3)%></span>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
						</p>
					</td>
				</tr>
				<tr>
					<td align="center"><%=aryKwamokRangeNM(1)%></td>
					<td>
						<!-- If aryOmrRangeMunTot(1) <> "" Then -->
							<%=aryOmrRangeJungTot(1)%>/<%=aryOmrRangeMunTot(1)%>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
					</td>
					<td class="graph_line" align="left">
						<p class="graph">
						<!-- If aryOmrRangeCompletePrt(1) <> "" And BIF(aryOmrRangeMunTot(1)="",0,aryOmrRangeMunTot(1)) > 4 Then -->
							<img src="../../images/bg_blue.gif" height="15" border="0" align="absmiddle" width="<%=aryOmrRangeGraphWidth(1)%>">&nbsp;<span class="style12"><%=aryOmrRangeCompletePrt(1)%></span>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
						</p>
					</td>
					<td align="center"><%=aryKwamokRangeNM(4)%></td>
					<td>
						<!-- If aryOmrRangeMunTot(4) <> "" Then -->
							<%=aryOmrRangeJungTot(4)%>/<%=aryOmrRangeMunTot(4)%>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
					</td>
					<td class="graph_line" align="left">
						<p class="graph">
						<!-- If aryOmrRangeCompletePrt(4) <> "" And BIF(aryOmrRangeMunTot(4)="",0,aryOmrRangeMunTot(4)) > 4 Then -->
							<img src="../../images/bg_blue.gif" height="15" border="0" align="absmiddle" width="<%=aryOmrRangeGraphWidth(4)%>">&nbsp;<span class="style12"><%=aryOmrRangeCompletePrt(4)%></span>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
						</p>
					</td>
				</tr>
				<tr>
					<td align="center"><%=aryKwamokRangeNM(2)%></td>
					<td>
						<!-- If aryOmrRangeMunTot(2) <> "" Then -->
							<%=aryOmrRangeJungTot(2)%>/<%=aryOmrRangeMunTot(2)%>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
					</td>
					<td class="graph_line" align="left">
						<p class="graph">
						<!-- If aryOmrRangeCompletePrt(2) <> "" And BIF(aryOmrRangeMunTot(2)="",0,aryOmrRangeMunTot(2)) > 4 Then -->
							<img src="../../images/bg_blue.gif" height="15" border="0" align="absmiddle" width="<%=aryOmrRangeGraphWidth(2)%>">&nbsp;<span class="style12"><%=aryOmrRangeCompletePrt(2)%></span>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
						</p>
					</td>
					<td class="line" align="center"><%=aryKwamokRangeNM(5)%></td>
					<td class="line">
						<!-- If aryOmrRangeMunTot(5) <> "" Then -->
							<%=aryOmrRangeJungTot(5)%>/<%=aryOmrRangeMunTot(5)%>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
					</td>
					<td class="line" align="left">
						<p class="graph">
						<!-- If aryOmrRangeCompletePrt(5) <> "" And BIF(aryOmrRangeMunTot(5)="",0,aryOmrRangeMunTot(5)) > 4 Then -->
							<img src="../../images/bg_blue.gif" height="15" border="0" align="absmiddle" width="<%=aryOmrRangeGraphWidth(5)%>">&nbsp;<span class="style12"><%=aryOmrRangeCompletePrt(5)%></span>
						<!-- Else -->
							&nbsp;
						<!-- End If -->
						</p>
					</td>
				</tr>
				<tr>
					<td class="line" align="center">Total</td>
					<td class="line"><%=OmrRangeJungTot%>/<%=OmrRangeMunTot%></td>
					<td class="graph_line line">
						<p class="graph">
						<img src="../../images/bg_gray.gif" height="15" border="0" align="absmiddle" width="<%=OmrRangePtgTotGraphWidth%>">&nbsp;<span class="style12"><%=OmrRangePtgTot%></span>
						</p>
					</td>
				</tr>

			</tbody>
			</table>
		</td>
	</tr>
</table>

<!-- Page2 -->

<div style="page-break-before: always;">
   <!--[if IE 7]><br style="height:0; line-height:0"><![endif]-->
   <!--[if IE 8]><br style="height:0; line-height:0"><![endif]-->
</div>
<table width="648" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="3" align="center" height="10"></td>
	</tr>
</table>
<table width="648" border="0" align="center" cellpadding="0" cellspacing="1">
	<tr>
		<td colspan="3" align="center"><font style="font-size:17px; font-face:'';font-weight:bold">INDIVIDUAL PROGRESS PRESCRIPTION REPORT(II)</font></td>
	</tr>
	<tr>
		<td width="40%" height="20" valign="bottom"></td>
		<td width="30%" align="right" valign="bottom" class="style4">Name:${rsGicho.hname}</td>
		<td width="30%" align="right" valign="bottom" class="style4">Member ID:${rsGicho.hkey}</td>
	</tr>
</table>
<!--
	Dim SpaceLine
	SpaceLine = ""
-->

<table width="648" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="ffffff">
	<tr>
		<td colspan=9 height='20' class="style23" align="center" bgcolor="ffffff">ANALYSIS OF ERRORS</td>
	</tr>
</table>
<!-- 학습내용별 분석 -->
<table width="648" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="ffffff">
	<tr>
		<td bgcolor="#ffffff" colspan=7 style="color:#000000;"><span class="style4">[Analysis By Objective]</span> <span class="style12"> Identify the lacking skills and prescribe workbooks for improvement.</span></td>
	</tr>
</table>
<table width="648" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="000000">
	<tr>
		<td width="498" height="20"  align="center" bgcolor="#99c2ff" class="style1">Objectives</td>
		<td width="150" height="" align="center" bgcolor="#99c2ff" class="style1">Prescribed Workbooks</td>
	</tr>

<!--
	Dim OdabSet, OdabGrp, iDisplayCnt
	OdabStr = "" : OdabSet = "" : OdabGrp = "" : i = 0 : iDisplayCnt  = 0
	If iOdabCnt1 > 0 And Not((Pan = "상위" And BoSetSu="0") Or Pan = "하위") Then
		For i = 0 To (iOdabCnt1 - 1)
			If OdabGrp <> AryOdab1_2(4, i) And OdabGrp <> "" Then

			'배경색결정
			If CInt(iDisplayCnt / 2) * 2 = iDisplayCnt Then
				addBG="#FFFFFF"
			Else
				addBG="dadbdd"
			End If
-->

	<#assign odab12Size = 0>	
	<#assign odab12Index = 0>
	<#assign odabStr = "">
	<#assign odabSet = "">
	<#assign odabGrp = "">

	<#if rsOdab12?has_content>
	<#assign odab12Size = rsOdab12?size>
				
		<#if odab12Size gt 0 && !((rsGicho.pan == "상위" && rsGicho.boSetSu == "0") || rsGicho.pan == "하위")>
			<#list rsOdab12 as odab12>
				
				<#if odabGrp != "" && odabGrp != odab12.odabGrp>
					<#if (odab12Index/2)?int*2 != odab12Index>
						<#assign addBG = "dadbdd">
					<#else>
						<#assign addBG = "#FFFFFF">
					</#if>		
					
	<tr>
		<td align="" height="19" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabStr}</td>
		<td align="" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabSet}</td>
	</tr>

					<#assign odabStr = "">
					<#assign odabSet = "">
					<#assign odabGrp = "">
					<#assign odab12Index = odab12Index+1>	
					<#if odab12Index gte 12> 
						<#break> 
					</#if>						
				</#if>

				<#assign odabStr = odabStr + compare(odabStr?length, 0, '', ', ') + odab12.odabNM>
				<#assign odabStr = odabStr + "(" + odab12.odab1?number + displayOdab(odab12.odab2) + ")">
				<#assign odabSet = odab12.setList>
				<#assign odabGrp = odab12.odabGrp>
											
			</#list>	
		</#if>
	</#if>

<!--
				OdabStr = "" : OdabSet = "" : OdabGrp = "" : iDisplayCnt = iDisplayCnt + 1
				If iDisplayCnt>=12 Then Exit For
			End If
			OdabGrp = AryOdab1_2(4, i)
			OdabStr = OdabStr & BIF(LEN(OdabStr) = 0, "", ", ") & AryOdab1_2(0, i)
			OdabSet = AryOdab1_2(3, i)

			If Kwamok = "EM" Then
				OdabStr = OdabStr & "(" & CInt(AryOdab1_2(1, i)) & UFN_DisplayOdab(AryOdab1_2(2, i)) & ")"
			End If
		Next
	End If
-->
<!--
	If iOdabCnt1 > 0 And iDisplayCnt < 12 And Not((Pan = "상위" And BoSetSu=="0") Or Pan = "하위") Then

		'배경색결정
		If CInt(iDisplayCnt / 2) * 2 = iDisplayCnt Then
			addBG="#FFFFFF"
		Else
			addBG="dadbdd"
		End If
-->

	<#if odab12Size gt 0 && odab12Index lt 12 && !((rsGicho.pan == "상위" && rsGicho.boSetSu == "0") || rsGicho.pan == "하위")>
		<#if (odab12Index/2)?int*2 != odab12Index>
			<#assign addBG = "dadbdd">
		<#else>
			<#assign addBG = "#FFFFFF">
		</#if>
				
	<tr>
		<td align="" height="19" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabStr}</td>
		<td align="" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabSet}</td>
	</tr>
		<#assign odab12Index = odab12Index+1>
	</#if>
	
<!--
		iDisplayCnt = iDisplayCnt + 1
	End If

	If iDisplayCnt < 12 Then
		For i = iDisplayCnt To 11
			If i = 0 Then
				If (Pan = "상위" And BoSetSu="0") Or Pan = "하위" then
					'OdabStr = "한 단계 낮은 등급으로 평가하도록 합니다."
					OdabStr = "Retake the diagnostic test."
				Else
					'OdabStr = "해당 분석 내용이 없습니다."
					OdabStr = "There are no lacking skills based on the Analysis by Objective. Therefore no workbooks are prescribed."
				End If
			Else
				OdabStr = "&nbsp;"
			End If

			'배경색결정
			If CInt(i / 2) * 2 = i Then
				addBG="#FFFFFF"
			Else
				addBG="dadbdd"
			End If
-->

	<#if odab12Index lt 12>
		<#list odab12Index..12 as i>
			<#if i == 0>
				<#if (rsGicho.pan == '상위' && rsGicho.boSetSu == '0') || rsGicho.pan == '하위'>
					<#assign odbStr = 'Retake the diagnostic test.'>
				<#else>
					<#assign odbStr = 'There are no lacking skills based on the Analysis by Objective. Therefore no workbooks are prescribed.'>
				</#if>
			<#else>
				<#assign odabStr = '&nbsp;'>
			</#if>
			
			<#if (i/2)?int*2 != i>
				<#assign addBG = "dadbdd">
			<#else>
				<#assign addBG = "#FFFFFF">
			</#if>			

	<tr>
		<td align="center" height="19" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabStr}</td>
		<td align="" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">&nbsp;</td>
	</tr>
	
		</#list>	
	</#if>
	
<!--
		Next
	End If
-->
</table>

<!-- 오답 사례별 분석 -->
<table width="648" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="ffffff">
	<tr>
		<td bgcolor="#ffffff" colspan=7 style="color:#000000;"><span class="style4">[Analysis By Answer Selection]</span> <span class="style12">Identify the cause and rationale by analyzing the wrong answer selection.</span></td>
	</tr>
</table>
<table width="648" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="000000">
	<tr>
		<td height="19" width="453" align="center" bgcolor="#99c2ff" class="style1">Comments Of Errors</td>
		<td height="" width="45" align="center" bgcolor="#99c2ff" class="style1">Type</td>
		<td height="" width="150" align="center" bgcolor="#99c2ff" class="style1">Prescribed Workbooks</td>
	</tr>
<!--
	Dim OdabStr2
	OdabStr = "" : OdabSet = "" : OdabGrp = "" : OdabStr2 = "" : i = 0 : iDisplayCnt  = 0
	If iOdabCnt4 > 0 And Not((Pan = "상위" And BoSetSu="0") Or Pan = "하위") Then
		For i = 0 To (iOdabCnt4 - 1)
			If OdabGrp <> AryOdab4(0, i) and OdabGrp <> "" Then
				'배경색결정
				If CInt(iDisplayCnt / 2) * 2 = iDisplayCnt Then
					addBG="#FFFFFF"
				Else
					addBG="dadbdd"
				End If
-->

	<#assign odab4Size = 0>	
	<#assign odab4Index = 0>
	<#assign odabStr = "">
	<#assign odabSet = "">
	<#assign odabGrp = "">

	<#if rsOdab4?has_content>
	<#assign odab4Size = rsOdab4?size>
					
		<#if odab4Size gt 0 && !((rsGicho.pan == "상위" && rsGicho.boSetSu == "0") || rsGicho.pan == "하위")>
			<#list rsOdab4 as odab4>
				
				<#if odabGrp != "" && odabGrp != odab4.sCode>
					<#if (odab4Index/2)?int*2 != odab4Index>
						<#assign addBG = "dadbdd">
					<#else>
						<#assign addBG = "#FFFFFF">
					</#if>			
	<tr>
		<td align="" height="19" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabStr}</td>
		<td align="center" bgcolor="${addBG}" class="style7">${odabGrp}</td>
		<td align="" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabSet}</td>
	</tr>

					<#assign odabStr = "">
					<#assign odabSet = "">
					<#assign odabGrp = "">
					<#assign odab4Index = odab4Index+1>	
					<#if odab4Index gte 6> 
						<#break> 
					</#if>						
				</#if>

				<#assign odabStr = odabStr + compare(odabStr?length, 0, '', ', ') + odab4.odabNM>
				<#assign odabStr = odabStr + "(" + odab4.odab1?number + displayOdab(odab4.odab2) + ")">
				<#assign odabSet = odab4.setList>
				<#assign odabGrp = odab4.sCode>
											
			</#list>	
		</#if>
	</#if>
	
<!--
				OdabStr = "" : OdabSet = "" : OdabGrp = "" : OdabStr2 = "" : iDisplayCnt = iDisplayCnt + 1
				If iDisplayCnt>=6 Then Exit For
			End If
			OdabGrp = AryOdab4(0, i)
			OdabStr = AryOdab4(3, i)
			OdabSet = AryOdab4(4, i)

			If Kwamok = "EM" Then
				OdabStr2 = OdabStr2 & BIF(LEN(OdabStr2) = 0, "", ",") & CInt(AryOdab4(1, i)) & UFN_DisplayOdab(AryOdab4(2, i))
			End If
		Next
	End If
-->
<!--
	If iOdabCnt4 > 0 And iDisplayCnt<6 And Not((Pan = "상위" And BoSetSu="0") Or Pan = "하위") Then
		'배경색결정
		If CInt(iDisplayCnt / 2) * 2 = iDisplayCnt Then
			addBG="#FFFFFF"
		Else
			addBG="dadbdd"
		End If
-->

	<#if odab4Size gt 0 && odab4Index lt 6 && !((rsGicho.pan == "상위" && rsGicho.boSetSu == "0") || rsGicho.pan == "하위")>
		<#if (odab4Index/2)?int*2 != odab4Index>
			<#assign addBG = "dadbdd">
		<#else>
			<#assign addBG = "#FFFFFF">
		</#if>
	<tr>
		<td align="" height="19" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabStr}</td>
		<td align="center" bgcolor="${addBG}" class="style7">${odabGrp}</td>
		<td align="" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabSet}</td>
	</tr>
		<#assign odab4Index = odab4Index+1>
	</#if>
	
<!--
		iDisplayCnt = iDisplayCnt + 1
	End If

	If iDisplayCnt < 6 Then
		For i = iDisplayCnt To 5
			If i = 0 Then
				'OdabStr = "해당 오답 사례별 분석이 없습니다."
				If (Pan = "상위" And BoSetSu="0") Or Pan = "하위" Then
					OdabStr = "Retake the diagnostic test."
				Else
					'OdabStr = "해당 분석 내용이 없습니다."
					OdabStr = "There are no lacking skills based on the Analysis by Answer Selection. Therefore no workbooks are prescribed."
				End If
			Else
				OdabStr = "&nbsp;"
			End If

			'배경색결정
			If CInt(i / 2) * 2 = i Then
				addBG="#FFFFFF"
			Else
				addBG="dadbdd"
			End If
-->

	<#if odab4Index lt 6>
		<#list odab4Index..6 as i>
			<#if i == 0>
				<#if (rsGicho.pan == '상위' && rsGicho.boSetSu == '0') || rsGicho.pan == '하위'>
					<#assign odbStr = 'Retake the diagnostic test.'>
				<#else>
					<#assign odbStr = 'There are no lacking skills based on the Analysis by Answer Selection. Therefore no workbooks are prescribed.'>
				</#if>
			<#else>
				<#assign odabStr = '&nbsp;'>
			</#if>
			
			<#if (i/2)?int*2 != i>
				<#assign addBG = "dadbdd">
			<#else>
				<#assign addBG = "#FFFFFF">
			</#if>	
	<tr>
		<td align="center" height="19" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">${odabStr}</td>
		<td align="" bgcolor="${addBG}" class="style7">&nbsp;</td>
		<td align="" bgcolor="${addBG}" class="style7" style="padding:0 0 0 3">&nbsp;</td>
	</tr>
		</#list>
	</#if>
	
<!--
		Next
	End If
-->
</table>

<!-- 처방프로그램 -->

<table width="648" border="0" align="center" cellpadding="0" cellspacing="0" >
	<tr>
		<td align='center' class="style23" height="20">PRESCRIPTION PROGRAM</td>
	<tr>
</table>
<table width="648" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor='#000000'>
	<tr>
		<td width="46" height="19" align="center" bgcolor="#99c2ff" class="style1">Week</td>
		<td width="51" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  0, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  1, r_date))) %></td>
		<td width="51" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  2, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  3, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  4, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  5, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  6, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  7, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  8, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m",  9, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m", 10, r_date))) %></td>
		<td width="50" align="center" bgcolor="#99c2ff" class="style1"><% = UFN_DisplayMonth(month(dateadd("m", 11, r_date))) %></td>
	</tr>
<!--
			Dim R_set
			Set adoCmd = Server.CreateObject("ADODB.Command")		'## 3. 반드시 연결 객체를 생성 합니다.
			with adoCmd
				.ActiveConnection = GlbBilisUdlPATH				'## 4. 연결을 open 합니다.
				.CommandType = adCmdStoredProc
				.CommandText = "GBJindo.dbo.USP_JD_OmrPrintJindo"
				'fwrite "GBJINDO.dbo.USP_JD_OmrPrintJindo '"&Jisa&"','"&Omrdate&"','"&Hkey&"','"&Kwamok&"','"&CStr(Ju + 1)&"','"&Mujin&"'"
				For Ju = 0 To 4
					call ClearCmd(adoCmd)
					.Parameters.append .CreateParameter("@Jisa", adchar, adParamInput, 2, Jisa )
					.Parameters.append .CreateParameter("@Omrdate", adchar, adParamInput, 10, Omrdate )
					.Parameters.append .CreateParameter("@Hkey", adchar, adParamInput, 8, Hkey )
					.Parameters.append .CreateParameter("@Kwamok", advarchar, adParamInput, 2, Kwamok )
					.Parameters.append .CreateParameter("@Week", advarchar, adParamInput, 2, CStr(Ju + 1) )
					.Parameters.append .CreateParameter("@Mujin", advarchar, adParamInput, 2, Mujin )
					Set RS = .execute
-->
	<tr>
		<td width="" height="19" align="center" bgcolor="#99c2ff" class="style1"><%=Ju + 1%></td>
<!--
					For i = 1 To 12
						if RS.Eof Or rs.bof Then
							r_set = "&nbsp;"
						ElseIf trim(rs("r_set")) = "" Then
							r_set = "&nbsp;"
						Else
							r_set = UFN_DisplayJinSet(Kwamok, trim(rs("r_set")))
						End if
-->
		<td width="" align="center" bgcolor="<%=BIF(Ju=0 Or Ju=2 Or Ju=4, "#FFFFFF", "dadbdd")%>" class="style7"><%=r_set%><%If r_set=SP Then%> <font color="#FF0000">▼</font><%End If%></td>
<!--
						If Not (RS.Eof Or rs.bof) Then rs.movenext
					Next
-->
	</tr>
<!--
					RS.Close : SET RS = Nothing
				Next
			End with
			Set adoCmd = Nothing
-->
</table>

<!-- 예상진도 -->
<table width="648" border="0" align="center" cellpadding="0" cellspacing="0" >
	<tr>
		<td align='center' class="style23" height="20">PROGRESS SCHEDULE</td>
	<tr>
</table>
<table width="648" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor='#000000'>
<!--
	Dim JinMM, JinStr
	JinMM = "" : JinStr = "" : iDisplayCnt = 0

	If iNextJinSetCnt > 0 Then
		For i = 0 To (iNextJinSetCnt - 1)
			If JinMM <> AryNextJinSet(1, i) And JinMM <> "" Then
				iDisplayCnt = iDisplayCnt + 1
-->
	<#assign jinMM = "">
	<#assign jinStr = "">
	<#assign scheduleIndex = 0>

	<#if rsSchedule?has_content>
	
		<#list rsSchedule as schedule>	
			<#if jinMM != "" && jinMM != schedule.rMM>
				<#assign scheduleIndex = scheduleIndex+1>
			
	<tr height="60" valign="top">
		<td width="46" height="20" align="center" bgcolor="#99c2ff" class="style1" valign='middle'>${displayMonth(jinMM)}</td>
		<td width="" align="" bgcolor="#ffffff" class="style7">${jinStr}</td>
	</tr>
	
				<#if scheduleIndex gte 3>
					<#break>
				</#if>
				<#assign jinMM = "">
				<#assign jinStr = "">				
			</#if>	
						
			<#assign jinMM = schedule.rMM>
			
			<#if schedule.rSet == "Z999">
				<#assign jinStr = jinStr + compare(jinStr, "", "&nbsp;", "<br>&nbsp;") + displayJinSet(rsGicho.kwamok, schedule.rSet) + " : Congratulations on the completion of JEI Math">
			<#else>
				<#assign jinStr = jinStr + compare(jinStr, "", "&nbsp;", "<br>&nbsp;") + displayJinSet(rsGicho.kwamok, schedule.rSet) + " : " + schedule.yNM>
			</#if>
		</#list>
		
		<#if scheduleIndex lt 3>
		<tr height="60" valign="top">
			<td width="46" height="20" align="center" bgcolor="#99c2ff" valign="middle" class="style1">${scheduleIndex}</td>
			<td width="" align="" bgcolor="#ffffff" class="style7">${jinStr}</td>
		</tr>
		<#assign scheduleIndex = scheduleIndex+1>
		</#if>		
	</#if>
	
<!--
				If iDisplayCnt = 3 Then
					Exit For
				End If
				JinMM = "" : JinStr = ""
			End If
			JinMM = AryNextJinSet(1, i)

			If AryNextJinSet(3, i)="Z999" Then
				JinStr = JinStr & BIF(JinStr="", "&nbsp;", "<br>&nbsp;") & UFN_DisplayJinSet(Kwamok, AryNextJinSet(3, i)) & " : Congratulations on the completion of JEI Math"
			Else
				JinStr = JinStr & BIF(JinStr="", "&nbsp;", "<br>&nbsp;") & UFN_DisplayJinSet(Kwamok, AryNextJinSet(3, i)) & " : " & AryNextJinSet(4, i)
			End If
-->

<!--		Next  -->
<!--		If iDisplayCnt < 3 Then -->

	<#if scheduleIndex lt 3>
	
	<#if jinMM == "">
		<#assign jinMM = "">
	<#else>
		<#assign jinMM = "">
	</#if>
	<tr height="60" valign="top">
		<td width="46" height="20" align="center" bgcolor="#99c2ff" valign="middle" class="style1">${scheduleIndex}</td>
		<td width="" align="" bgcolor="#ffffff" class="style7">${jinStr}</td>
	</tr>
	<#assign scheduleIndex = scheduleIndex+1>
	</#if>
<!--
			iDisplayCnt = iDisplayCnt + 1
		End If
	End If

	If iDisplayCnt < 3 Then
		If JinMM = "" Then
			JinMM = mid(cstr(date), 6, 2)
		Else
			JinMM = bif(JinMM = 12, "1", cint(JinMM) + 1)
		End If

		For i = iDisplayCnt To 2
			If i = 0 Then
				JinStr = "Retake the diagnostic test."	'해당 분석 내용이 없습니다
			Else
				JinStr = "&nbsp;"
			End If
-->
	<#if scheduleIndex lt 3>
		<#if jinMM == "">
			<#assign jinMM = .now?date?iso_utc?substring(5,7)>
		<#else>
			<#assign jinMM = compare(jinMM, "12", "1", jinMM?number+1)>
		</#if>
		
		<#list scheduleIndex..2 as i>
			<#if i == 0>
				<#assign JinStr = "Retake the diagnostic test.">
			<#else>
				<#assign JinStr = "&nbsp;">
			</#if>
	<tr height="60" valign="top">
		<td width="46" height="20" align="center" bgcolor="#99c2ff" valign="middle" class="style1"><%=UFN_DisplayMonth(Cint(JinMM))%></td>
		<td width="" align="" bgcolor="#ffffff" class="style7">${jinStr}</td>
	</tr>
		</#list>
	</#if>
<!--
			JinMM = bif(JinMM = 12, "1", cint(JinMM) + 1)
		Next
	End If
-->
</table>
</center>
</body>
</html>