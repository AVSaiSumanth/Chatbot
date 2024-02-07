<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Student Home</title>
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
<script type="text/javascript">
<%int size = (Integer) request.getAttribute("sz");

%>
$('#submitexam').submit(ajax);
function disableElement(){
	  $('#btnExamSubmit').prop('disabled', true);
	  ajax();
	 
	}
function ajax(){
    $.ajax({
        url : 'submitexam.htm',
        type : 'POST',
        data : $('form').serialize(),
        success: function(data){
            window.location="studentexamcomplete.htm";
        }
    });
    return true;
}
	setTimeout(disableElement, <%=size * 60000%>);
</script>


<script>



var i=<%=size%>;

var timer2 = i+":01";

var interval = setInterval(function() {


  var timer = timer2.split(':');
  //by parsing integer, I avoid all extra string processing
  var minutes = parseInt(timer[0], 10);
  var seconds = parseInt(timer[1], 10);
  --seconds;
  minutes = (seconds < 0) ? --minutes : minutes;
  if (minutes < 0) clearInterval(interval);
  seconds = (seconds < 0) ? 59 : seconds;
  seconds = (seconds < 10) ? '0' + seconds : seconds;
  //minutes = (minutes < 10) ?  minutes : minutes;
  $('#txt').html(minutes + 'minutes' + seconds+" seconds");
  timer2 = minutes + ':' + seconds;
}, 1000);

</script>
</head>

<body onload="startTime()">


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
			<div id="txt"></div>
			<c:if test='${subject.qpattern eq "multiple" }'>

				<div align="right">
					<%
						for (int i = 1; i <= size; i++) {
					%>
					<a onclick="showDiv(<%=i%>)"><%=i%></a>
					<%
						}
					%>
				</div>
			</c:if>
			<form action="submitexam.htm" method="post" id="submitexam">
				<%
					int count = 0;
				%>

				<c:forEach items="${requestScope.questions }" var="ques"
					varStatus="st">
					<div class="row" id="div<%=++count%>">
						<div
							class="col-lg-6 col-lg-offset-1 col-sm-6 col-sm-offset-3 col-xs-12 ">

								<div class="row">
								<div
									class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
									<strong> ${ques.sectionType }
									</strong> 

								</div>

							</div>
							<div class="row">
								<div
									class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
									<strong> <%=count%>)&nbsp;${ques.quationTitle }
									</strong> <input type="hidden" name="q<%=count%>" value="${ques.qid }" />
									<input type="hidden" name="qtype<%=count%>"
										value="${ques.quetionType }" />

								</div>

							</div>
						
								
									
							<c:choose>
								<c:when
									test='${ques.quetionType eq "single" or ques.quetionType eq "tf"  }'>

									<div class="row">
										<div
											class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
											&nbsp;A)&nbsp;<input type="radio" name="a<%=count%>"
												value="A">${ques.a }

										</div>

									</div>
									<div class="row">
										<div
											class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
											&nbsp;B)&nbsp;<input type="radio" name="a<%=count%>"
												value="B">${ques.b }

										</div>

									</div>
									<c:if test='${ques.quetionType eq "single"  }'>
										<div class="row">
											<div
												class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
												&nbsp;C)&nbsp;<input type="radio" name="a<%=count%>"
													value="C">${ques.c }

											</div>

										</div>
										<div class="row">
											<div
												class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
												&nbsp;D)&nbsp;<input type="radio" name="a<%=count%>"
													value="D">${ques.d }

											</div>

										</div>
									</c:if>
								</c:when>
								<c:when test='${ques.quetionType eq "multiple" }'>

									<div class="row">
										<div
											class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
											&nbsp;A)&nbsp;<input type="checkbox" name="a<%=count%>"
												value="A">${ques.a }

										</div>

									</div>
									<div class="row">
										<div
											class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
											&nbsp;B)&nbsp;<input type="checkbox" name="a<%=count%>"
												value="B">${ques.b }

										</div>

									</div>
									<div class="row">
										<div
											class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
											&nbsp;C)&nbsp;<input type="checkbox" name="a<%=count%>"
												value="C">${ques.c }

										</div>

									</div>
									<div class="row">
										<div
											class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
											&nbsp;D)&nbsp;<input type="checkbox" name="a<%=count%>"
												value="D">${ques.d }

										</div>

									</div>
								</c:when>

							</c:choose>
						</div>


					</div>
				</c:forEach>
				<div class="row">
					<div
						class="col-lg-6 col-lg-offset-4 col-sm-6 col-sm-offset-3 col-xs-12 ">
						<c:if test='${subject.qpattern eq "multiple" }'>
							<input type="button" class="btn btn-success "
								onclick="showDiv(currentDiv + 1)" id="nxt" style="width: 16%;"
								value="Next">
						</c:if>
						<input type="hidden" name="sid"
							value="${requestScope.subject.subjectId }">
						<c:if test='${subject.qpattern eq "multiple" }'>
							<input type="button" class="btn btn-success "
								onclick="showDiv(currentDiv - 1)" id="prv" style="width: 16%;"
								value="Previous" />
						</c:if>
						<input type="hidden" value="${requestScope.stype }" name="stype">
						<input type="submit" class="btn btn-success " value="Submit"
							id="btnExamSubmit" style="width: 15%;">


					</div>

				</div>

			</form>
		</div>

		<div></div>
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
<c:if test='${subject.qpattern eq "multiple" }'>
	<script type="text/javascript">
var sz=${sz+1};
var currentDiv = 1; 
$("#prv").hide();
for(i = 1; i < sz; i++) {
	  
	   $("#div"+i).hide();
	   
}
$("#div1").show();
function showDiv(which) {
	
	
	

	   for(i = 1; i < sz; i++) {
		  
		   $("#div"+i).hide();
	   }
	   //in the next 2 lines, you make sure which isn't lower than 1, and isn't greater than the number of images
	   if(which < 1) which = 1;
	   if(which > sz) which =sz-1;
	   $("#div"+which).show();
	   if(which==sz-1)
	   {
	   $("#nxt").hide();
	   }
	   else
		{
		   $("#nxt").show();
		 }
	   if(which==1)
	   {
	   $("#prv").hide();
	   }
	   else
		{
		   $("#prv").show();
		 }
	   currentDiv = which;
	}
</script>
</c:if>
</html>