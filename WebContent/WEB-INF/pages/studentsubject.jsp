<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Staff Home</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="assets/style.css" />
<script src="js/jquery-1.9.1.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.js"></script>
<script src="assets/script.js"></script>



<!-- Owl stylesheet -->
<link rel="stylesheet" href="assets/owl-carousel/owl.carousel.css">
<link rel="stylesheet" href="assets/owl-carousel/owl.theme.css">
<script src="assets/owl-carousel/owl.carousel.js"></script>
<!-- Owl stylesheet -->


<!-- slitslider -->
<link rel="stylesheet" type="text/css"
	href="assets/slitslider/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="assets/slitslider/css/custom.css" />
<script type="text/javascript"
	src="assets/slitslider/js/modernizr.custom.79639.js"></script>
<script type="text/javascript"
	src="assets/slitslider/js/jquery.ba-cond.min.js"></script>
<script type="text/javascript"
	src="assets/slitslider/js/jquery.slitslider.js"></script>
<!-- slitslider -->

<script src='/google_analytics_auto.js'></script>
</head>

<body>


	<!-- Header Starts -->
	<div class="navbar-wrapper">

		<div class="navbar-inverse" role="navigation"
			style="background-color: skyblue;">
			<nav class="navbar navbar-default navbar-static-top"
				style="background-color: skyblue;">
				<div class="container">
					<div class="row" style="padding-top: 5px;">
						<div class="col-xs-12 col-sm-1 rit">
							<img src="images/jntua.png" alt="JNTUA" width="60px"
								height="70px" />
						</div>
						<div class="col-xs-12 col-sm-5">
							<span class="text-primary myh1">JNTUA</span><span
								class="text-primary myh2">, ANANTHAPURAMU</span><br /> <span
								class="text-primary myh1">ChatBot</span>
						</div>
						<div class="col-xs-12 col-sm-6 rit"></div>
					</div>
				</div>
			</nav>
			<div class="container">
				<div class="navbar-header">


					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

				</div>



				<!-- Nav Starts -->
				<div class="navbar-collapse  collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="studenthome.htm">Home</a></li>


						<li><a href="logout.htm">Logout</a></li>

					</ul>
				</div>
				<!-- #Nav Ends -->

			</div>
		</div>

	</div>
	<!-- #Header Starts -->





	<div class="container">


		<!-- #Header Starts -->
	</div>
	<!-- banner -->

	<!-- banner -->


	<div class="container">
		<div class="spacer">
			<!-- row  -->
			<div class="row">

				<div class="col-xs-2 col-md-2" align="right">
					<label for="concept" class="col-xs-2 col-md-2 control-label-left">SubjectName</label>
				</div>
				<div class="col-xs-2 col-md-2">
					<div class="form-group">${requestScope.subject.name }</div>

				</div>

			</div>
			<!-- end row -->
			<!-- row  -->
			<div class="row">

				<div class="col-xs-2 col-md-2" align="right">
					<label for="concept" class="col-xs-2 col-md-2 control-label-left">Title</label>
				</div>
				<div class="col-xs-2 col-md-2">
					<div class="form-group">${requestScope.subject.title }</div>

				</div>

			</div>
			<!-- end row -->
			<!-- row  -->
			<div class="row">

				<div class="col-xs-2 col-md-2" align="right">
					<label for="concept" class="col-xs-2 col-md-2 control-label-left"><a
						href="studentmockexam.htm?sid=${requestScope.subject.subjectId }">Mock&nbsp;Exam</a></label>
				</div>

			</div>
			<!-- end row -->
			<%
				if (request.getAttribute("already") == null) {
			%>

			<!-- row  -->
			<div class="row">

				<div class="col-xs-2 col-md-2" align="right">
					<label for="concept" class="col-xs-2 col-md-2 control-label-left"><a
						href="studentexam.htm?sid=${requestScope.subject.subjectId }">Take&nbsp;Exam</a></label>
				</div>

			</div>
			<!-- end row -->
			<%
				} else {
					double grandTotal=0;
					
			%>
				<!-- row  -->
			<div class="row">

				<div class="col-xs-12 col-md-12" align="left">
				<strong>Section A</strong>
			</div>
			</div>

			<!-- row  -->
			<div class="row">

				<div class="col-xs-12 col-md-12" align="right">

					<%
						List<Map<String, Object>> lst = (List<Map<String, Object>>) request.getAttribute("alreadya");
							if (lst != null && lst.size() > 0) {
					%>
					<table class="table">
						<tr>
							<th>SNO</th>
							<th>Question&nbsp;Title</th>
							<th>Section</th>
							<th>Option A</th>
							<th>Option B</th>
							<th>Option C</th>
							<th>Option D</th>
							<th>Negative&nbsp;Marks</th>
							<th>Your&nbsp;Answers</th>
							<th>Correct&nbsp;Answer</th>
							
							
						</tr>
						<%
							int total = 0;
									int neg = 0;
									for (int index = 0; index < lst.size(); index++) {
										Map<String, Object> m = (Map<String, Object>) lst.get(index);
										String studentAns = (String) m.get("studentAns");
										String ans = (String) m.get("ans");
										String qtype = (String) m.get("quetiontype");
										String mans = "";
										String smans = "";
										ArrayList<String> al = new ArrayList();
										ArrayList<String> sal = new ArrayList();

										if (ans != null) {
											if (qtype.equalsIgnoreCase("multiple")) {
												System.out.print("Multi");
												if (ans != null) {
													String[] answrs = ans.split(",");

													for (String an : answrs) {
														al.add(an);
														mans += m.get(an.toLowerCase());

													}
													if (studentAns != null) {
														String[] sans = studentAns.split(",");

														if (answrs.length == sans.length) {

															boolean isC = true;
															for (String a2 : sans) {
																sal.add(a2);
																smans += m.get(a2.toLowerCase());

																if (!al.contains(a2)) {
																	isC = false;

																}

															}
															if (isC) {
																total++;
															} else {
																neg++;
															}
														} else {
															neg++;
															for (String a2 : sans) {
																smans += m.get(a2.toLowerCase());

															}
														}

													}
												}
											} else {
												if (studentAns != null) {
													String an1 = (String) m.get(studentAns.toLowerCase());
													String an2 = (String) m.get(ans.toLowerCase());
													if (an1.equalsIgnoreCase(an2)) {
														total++;
													} else {
														neg++;
													}
												}
											}

										}
						%>
						<tr>
							<td><%=index + 1%></td>
							<td><%=m.get("quationtitle")%></td>
							<td><%=m.get("sectionType")%></td>
							<td><%=m.get("a")%></td>
							<td><%=m.get("b")%></td>
							<td><%=m.get("c")%></td>
							<td><%=m.get("d")%></td>
							<td><%=m.get("nmarks")%></td>
							<td>
								<%
									if (qtype.equalsIgnoreCase("multiple")) {
								%> <%=smans == null || smans.trim().equals("") || smans.trim().equals("null")
									? "NotAnswer" : sal%> <%
 	} else {
 %> <%=studentAns == null ? "NotAnswer"
									: m.get(studentAns.toLowerCase()) == null ? "NotAnswer"
											: m.get(studentAns.toLowerCase())%> <%
 	}
 %>



							</td>
							<td>
								<%
									if (qtype.equalsIgnoreCase("multiple")) {
								%> <%=al%> <%
 	} else {
 %> <%=m.get(ans.toLowerCase())%> <%
 	}
 %>
							</td>

						</tr>
						<%
							}
						%>
						<tr>
							<td colspan="4">
								<%
									double totalQ = lst.size();
											System.out.println(totalQ + " ");
											double totalScore = total;
											System.out.println(totalQ + " " + totalScore + " " + neg);
											double finalScore = totalScore - (neg * 0.25);
											grandTotal=grandTotal+finalScore;
								%> The Section A total Marks is <%=finalScore%>
							</td>
						</tr>
					</table>
					<%
						}
					%>

				</div>

			</div>
			<!-- end row -->
			
			
			
				<!-- row  -->
			<div class="row">

				<div class="col-xs-12 col-md-12" align="left">
				<strong>Section B</strong>
			</div>
			</div>

			<!-- row  -->
			<div class="row">

				<div class="col-xs-12 col-md-12" align="right">

					<%
						 lst = (List<Map<String, Object>>) request.getAttribute("alreadyb");
							if (lst != null && lst.size() > 0) {
					%>
					<table class="table">
						<tr>
							<th>SNO</th>
							<th>Question&nbsp;Title</th>
							<th>Section</th>
							<th>Option A</th>
							<th>Option B</th>
							<th>Option C</th>
							<th>Option D</th>
							<th>Negative&nbsp;Marks</th>
							<th>Your&nbsp;Answers</th>
							<th>Correct&nbsp;Answer</th>
						</tr>
						<%
							int total = 0;
									double neg = 0;
									for (int index = 0; index < lst.size(); index++) {
										Map<String, Object> m = (Map<String, Object>) lst.get(index);
										String studentAns = (String) m.get("studentAns");
										String ans = (String) m.get("ans");
										String qtype = (String) m.get("quetiontype");
										String mans = "";
										String smans = "";
										ArrayList<String> al = new ArrayList();
										ArrayList<String> sal = new ArrayList();

										if (ans != null) {
											if (qtype.equalsIgnoreCase("multiple")) {
												System.out.print("Multi");
												if (ans != null) {
													String[] answrs = ans.split(",");

													for (String an : answrs) {
														al.add(an);
														mans += m.get(an.toLowerCase());

													}
													if (studentAns != null) {
														String[] sans = studentAns.split(",");

														if (answrs.length == sans.length) {

															boolean isC = true;
															for (String a2 : sans) {
																sal.add(a2);
																smans += m.get(a2.toLowerCase());

																if (!al.contains(a2)) {
																	isC = false;

																}

															}
															if (isC) {
																total++;
															} else {
																neg=neg+(Double)m.get("nmarks");
															}
														} else {
															neg++;
															for (String a2 : sans) {
																smans += m.get(a2.toLowerCase());

															}
														}

													}
												}
											} else {
												if (studentAns != null) {
													String an1 = (String) m.get(studentAns.toLowerCase());
													String an2 = (String) m.get(ans.toLowerCase());
													if (an1.equalsIgnoreCase(an2)) {
														total++;
													} else {
														neg++;
													}
												}
											}

										}
						%>
						<tr>
							<td><%=index + 1%></td>
							<td><%=m.get("quationtitle")%></td>
							<td><%=m.get("sectionType")%></td>
							<td><%=m.get("a")%></td>
							<td><%=m.get("b")%></td>
							<td><%=m.get("c")%></td>
							<td><%=m.get("d")%></td>
							<td><%=m.get("nmarks")%></td>
							<td>
								<%
									if (qtype.equalsIgnoreCase("multiple")) {
								%> <%=smans == null || smans.trim().equals("") || smans.trim().equals("null")
									? "NotAnswer" : sal%> <%
 	} else {
 %> <%=studentAns == null ? "NotAnswer"
									: m.get(studentAns.toLowerCase()) == null ? "NotAnswer"
											: m.get(studentAns.toLowerCase())%> <%
 	}
 %>



							</td>
							<td>
								<%
									if (qtype.equalsIgnoreCase("multiple")) {
								%> <%=al%> <%
 	} else {
 %> <%=m.get(ans.toLowerCase())%> <%
 	}
 %>
							</td>

						</tr>
						<%
							}
						%>
						<tr>
							<td colspan="4">
								<%
									double totalQ = lst.size();
											System.out.println(totalQ + " ");
											double totalScore = total;
											System.out.println(totalQ + " " + totalScore + " " + neg);
											double finalScore = totalScore - (neg );
											grandTotal=grandTotal+finalScore;
								%> The Section B total Marks is <%=finalScore%>
							</td>
						</tr>
					</table>
					<%
						}
					%>

				</div>

			</div>
			<!-- end row -->
				<!-- row  -->
			<div class="row">

				<div class="col-xs-12 col-md-12" align="left">
				<strong>Section C</strong>
			</div>
			</div>

			<!-- row  -->
			<div class="row">

				<div class="col-xs-12 col-md-12" align="right">

					<%
						 lst = (List<Map<String, Object>>) request.getAttribute("alreadyc");
							if (lst != null && lst.size() > 0) {
					%>
					<table class="table">
						<tr>
							<th>SNO</th>
							<th>Question&nbsp;Title</th>
							<th>Section</th>
							<th>Option A</th>
							<th>Option B</th>
							<th>Option C</th>
							<th>Option D</th>
							<th>Negative&nbsp;Marks</th>
							<th>Your&nbsp;Answers</th>
							<th>Correct&nbsp;Answer</th>
						</tr>
						<%
							int total = 0;
									int neg = 0;
									for (int index = 0; index < lst.size(); index++) {
										Map<String, Object> m = (Map<String, Object>) lst.get(index);
										String studentAns = (String) m.get("studentAns");
										String ans = (String) m.get("ans");
										String qtype = (String) m.get("quetiontype");
										String mans = "";
										String smans = "";
										ArrayList<String> al = new ArrayList();
										ArrayList<String> sal = new ArrayList();

										if (ans != null) {
											if (qtype.equalsIgnoreCase("multiple")) {
												System.out.print("Multi");
												if (ans != null) {
													String[] answrs = ans.split(",");

													for (String an : answrs) {
														al.add(an);
														mans += m.get(an.toLowerCase());

													}
													if (studentAns != null) {
														String[] sans = studentAns.split(",");

														if (answrs.length == sans.length) {

															boolean isC = true;
															for (String a2 : sans) {
																sal.add(a2);
																smans += m.get(a2.toLowerCase());

																if (!al.contains(a2)) {
																	isC = false;

																}

															}
															if (isC) {
																total++;
															} else {
																neg++;
															}
														} else {
															neg++;
															for (String a2 : sans) {
																smans += m.get(a2.toLowerCase());

															}
														}

													}
												}
											} else {
												if (studentAns != null) {
													String an1 = (String) m.get(studentAns.toLowerCase());
													String an2 = (String) m.get(ans.toLowerCase());
													if (an1.equalsIgnoreCase(an2)) {
														total++;
													} else {
														neg++;
													}
												}
											}

										}
						%>
						<tr>
							<td><%=index + 1%></td>
							<td><%=m.get("quationtitle")%></td>
							<td><%=m.get("sectionType")%></td>
							<td><%=m.get("a")%></td>
							<td><%=m.get("b")%></td>
							<td><%=m.get("c")%></td>
							<td><%=m.get("d")%></td>
							<td><%=m.get("nmarks")%></td>
							<td>
								<%
									if (qtype.equalsIgnoreCase("multiple")) {
								%> <%=smans == null || smans.trim().equals("") || smans.trim().equals("null")
									? "NotAnswer" : sal%> <%
 	} else {
 %> <%=studentAns == null ? "NotAnswer"
									: m.get(studentAns.toLowerCase()) == null ? "NotAnswer"
											: m.get(studentAns.toLowerCase())%> <%
 	}
 %>



							</td>
							<td>
								<%
									if (qtype.equalsIgnoreCase("multiple")) {
								%> <%=al%> <%
 	} else {
 %> <%=m.get(ans.toLowerCase())%> <%
 	}
 %>
							</td>

						</tr>
						<%
							}
						%>
						<tr>
							<td colspan="4">
								<%
									double totalQ = lst.size();
											System.out.println(totalQ + " ");
											double totalScore = total;
											System.out.println(totalQ + " " + totalScore + " " + neg);
											double finalScore = totalScore - (neg * 0.25);
											grandTotal=grandTotal+finalScore;
								%> The total Marks is <%=finalScore%>
							</td>
						</tr>
					</table>
					<%
						}
					%>

				</div>

			</div>
			<!-- end row -->
			<p>
				<strong>Grand Total Marks is <%=grandTotal %></strong>
				<c:if test="${rank ne null}">
				<strong>Rank is ${rank }</strong>
				</c:if>
			</p>
			<%
				}
			%>
		</div>
	</div>




	<div class="footer">

		<div class="container">



			<div class="row">
				<div class="col-lg-3 col-sm-3">
					<h4>Information</h4>
					<ul class="row">
						<li class="col-lg-12 col-sm-12 col-xs-3"><a href="about.html">About
								JNTU</a></li>
						<li class="col-lg-12 col-sm-12 col-xs-3"><a href="staff.html">Staff</a></li>
						<li class="col-lg-12 col-sm-12 col-xs-3"><a href="blog.html">Student
								Blog</a></li>
						<li class="col-lg-12 col-sm-12 col-xs-3"><a
							href="contact.html">Contact</a></li>
					</ul>
				</div>

				<div class="col-lg-3 col-sm-3">
					<h4>Newsletter</h4>
					<p>Get notified about the latest news in jntu.</p>
					<form class="form-inline" role="form">
						<input type="text" placeholder="Enter Your email address"
							class="form-control">
						<button class="btn btn-success" type="button">Notify Me!</button>
					</form>
				</div>

				<div class="col-lg-3 col-sm-3">
					<h4>Follow us</h4>
					<a href="#"><img src="images/facebook.png" alt="facebook"></a>
					<a href="#"><img src="images/twitter.png" alt="twitter"></a>
					<a href="#"><img src="images/linkedin.png" alt="linkedin"></a>
					<a href="#"><img src="images/instagram.png" alt="instagram"></a>
				</div>

				<div class="col-lg-3 col-sm-3">
					<h4>Contact us</h4>

					<span class="glyphicon glyphicon-map-marker"></span> Jawaharlal
					Nehru Technological University Anantapur <br> <span
						class="glyphicon glyphicon-envelope"></span> jntua@gmail.com<br>
					<span class="glyphicon glyphicon-earphone"></span> 08554-242438
					</p>
				</div>
			</div>
			<p class="copyright">Copyright 2019. All rights reserved.</p>


		</div>
	</div>





</body>
</html>