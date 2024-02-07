<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" type="image/jpg" href="images/logo.jpg"/>
<title>Admin Add Students </title>
<meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

 	<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.css" />
  <link rel="stylesheet" href="assets/style.css"/>
  <script src="js/jquery-1.9.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.js"></script>
  <script src="assets/script.js"></script>



<!-- Owl stylesheet -->
<link rel="stylesheet" href="assets/owl-carousel/owl.carousel.css">
<link rel="stylesheet" href="assets/owl-carousel/owl.theme.css">
<script src="assets/owl-carousel/owl.carousel.js"></script>
<!-- Owl stylesheet -->


<!-- slitslider -->
    <link rel="stylesheet" type="text/css" href="assets/slitslider/css/style.css" />
    <link rel="stylesheet" type="text/css" href="assets/slitslider/css/custom.css" />
    <script type="text/javascript" src="assets/slitslider/js/modernizr.custom.79639.js"></script>
    <script type="text/javascript" src="assets/slitslider/js/jquery.ba-cond.min.js"></script>
    <script type="text/javascript" src="assets/slitslider/js/jquery.slitslider.js"></script>
<!-- slitslider -->

<script src='/google_analytics_auto.js'></script></head>

<body>


<!-- Header Starts -->
<div class="navbar-wrapper">

        <div class="navbar-inverse" role="navigation" style=" background-color: skyblue; ">
		<nav class="navbar navbar-default navbar-static-top" style=" background-color: skyblue; "> <div class="container"> <div class="row" style=" padding-top:5px;"> <div class="col-xs-12 col-sm-1 rit"><img src="images/jntua.png" alt="JNTUA" width="60px" height="70px" /> </div> <div class="col-xs-12 col-sm-5"> <span class="text-primary myh1">JNTUA</span><span class="text-primary myh2">, ANANTHAPURAMU</span><br /> <span class="text-primary myh1">ChatBot</span> </div> <div class="col-xs-12 col-sm-6 rit" >  </div> </div>  </div> </nav>  
          <div class="container">
            <div class="navbar-header">


              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>

            </div>

  <!-- Nav Starts -->
            <div class="navbar-collapse  collapse" >
              <ul class="nav navbar-nav navbar-right">
               <li class="active"><a href="login.htm">Home</a></li>
                <li><a href="http://jntua.ac.in/aboutus.php" target="blank">About JNTU</a></li>
               
              </ul>
            </div>
            <!-- #Nav Ends -->


          </div>
        </div>

    </div>
<!-- #Header Starts -->





<div class="container">


<!-- #Header Starts -->
</div><!-- banner -->

<!-- banner -->



<div class="container">
<div class="spacer">
<div class="row register">
  <div class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
  <form method="post" action="studentregs.htm">
<div style = "font-size:11px; color:#cc0000; margin-top:10px"></div>
 
			<!-- row  -->
			   <div class="row">
				
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-3 control-label-left"><font color="red">*&nbsp;</font>Course&nbsp;Name</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					   <select class="form-control" name="courseId" id="courseId" required>
							<option value="">Select Course</option>
							
							<c:forEach var="course" items="${courses }" varStatus="st">
<option value="${course.courseId }">${course.courseName }</option>
			
					</c:forEach>
							
					   </select>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			 
			   <!-- row  -->
			   <div class="row">
			  
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>Name</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					   <input type="text" class="form-control" name="name" id="name" required>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			    <!-- row  -->
			   <div class="row">
			  
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>RegNo</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					   <input type="text" class="form-control" name="regNo" id="regNo" required>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			<!-- row  -->
			   <div class="row">
			  
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>EmailId</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					   <input type="text" class="form-control" name="emailId" id="emailId" required>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			   
			  <!-- row  -->
			   <div class="row">
			  
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>mobileNo</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					   <input type="text" class="form-control" name="mobileNo" id="mobileNo" required>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			    <!-- row  -->
			   <div class="row">
			  
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>Sem</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					   <input type="text" class="form-control" name="semester" id="semester" required>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			   
			  
			 
			   
			   <!-- row  -->
		   <div class="row">
		  
		   <div class="col-xs-4 col-md-3"></div>
		   
		   <div class="col-xs-6 col-md-6">
				<div class="form-group">
				
		<input type="submit"  class="btn btn-success " value="Submit">
		
				   
				 </div>
								  
			</div>
							
		   </div>
		   <!-- end row -->
 </form>

                
        </div>
  
</div>
</div>
</div>





<div class="footer">

<div class="container">



<div class="row">
            <div class="col-lg-3 col-sm-3">
                   <h4>Information</h4>
                   <ul class="row">
                <li class="col-lg-12 col-sm-12 col-xs-3"><a href="about.html">About JNTU</a></li>
                <li class="col-lg-12 col-sm-12 col-xs-3"><a href="staff.html">Staff</a></li>         
                <li class="col-lg-12 col-sm-12 col-xs-3"><a href="blog.html">Student Blog</a></li>
                <li class="col-lg-12 col-sm-12 col-xs-3"><a href="contact.html">Contact</a></li>
              </ul>
            </div>
            
            <div class="col-lg-3 col-sm-3">
                    <h4>Newsletter</h4>
                    <p>Get notified about the latest news in jntu.</p>
                    <form class="form-inline" role="form">
                            <input type="text" placeholder="Enter Your email address" class="form-control">
                                <button class="btn btn-success" type="button">Notify Me!</button></form>
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
                 
<span class="glyphicon glyphicon-map-marker"></span> Jawaharlal Nehru Technological University Anantapur <br>
<span class="glyphicon glyphicon-envelope"></span> jntua@gmail.com<br>
<span class="glyphicon glyphicon-earphone"></span> 08554-242438</p>
            </div>
        </div>
<p class="copyright">Copyright 2019. All rights reserved.	</p>


</div></div>





</body>
</html>