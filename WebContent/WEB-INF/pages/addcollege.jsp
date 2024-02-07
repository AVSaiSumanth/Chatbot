<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin Add College </title>
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
            <div class="navbar-collapse  collapse">
              <ul class="nav navbar-nav navbar-right">
               <li class="active"><a href="adminhome.htm">Home</a></li>
               <li><a href="admindept.htm">Departments</a></li>
               <li><a href="adminactivitys.htm">Activities</a></li>
               
               
			    <li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Faculty <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="adminfaculty.htm">Faculty</a></li>
					<li class="kopie"><a href="adminaddfaculty.htm">AddFaculty</a></li>
					</ul>
				</li>
			 <li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Courses <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="admincourses.htm">Courses</a></li>
					<li class="kopie"><a href="adminaddcourses.htm">Add Course</a></li>
					</ul>
				</li>
				 <li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Subjects <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="adminsubjects.htm">Subjects</a></li>
					<li class="kopie"><a href="adminaddsubject.htm">Add Subjects</a></li>
					</ul>
				</li>
				 <li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Student <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="adminstudents.htm">Students</a></li>
					<li class="kopie"><a href="adminaddstudent.htm">Add Students</a></li>
					</ul>
				</li>
				
				
				 <li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Placements <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="adminplacements.htm">Placements</a></li>
					<li class="kopie"><a href="adminaddplacement.htm">Add Placement</a></li>
					</ul>
				</li>
				
				
				
				 <li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Subject Allotment <b class="caret"></b></a> 
				  <ul class="dropdown-menu active">
					<li class="kopie"><a href="adminallotedsubjects.htm">Alloted Subjects</a></li>
					<li class="kopie"><a href="adminallotsubject.htm">Allot Subject</a></li>
					</ul>
				</li>
				   <li><a href="adminranks.htm">Ranks</a></li>
				
				
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
</div><!-- banner -->

<!-- banner -->



<div class="container">
<div class="spacer">
<div class="row register">
  <div class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
  <form method="post" action="addcollege.htm">
<div style = "font-size:11px; color:#cc0000; margin-top:10px"></div>
 
			
			 
			   <!-- row  -->
			   <div class="row">
			  
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>CollegeName</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					   <input type="text" class="form-control" name="collegeName" id="collegeName" required>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			<!-- row  -->
			   <div class="row">
			  
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>Address</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					    <textarea class="form-control"  class="form-control" name="address" id="address" required></textarea>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			   
			   <!-- row  -->
			   <div class="row">
			
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>Principal</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					  <input type="text" class="form-control" name="principal" id="principal" required/>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			   
			   
			    <!-- row  -->
			   <div class="row">
			
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>Vice-Principal</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					  <input type="text" class="form-control" name="vicePrincipal" id="vicePrincipal" required/>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			   
			       <!-- row  -->
			   <div class="row">
			
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>Vice-Chancellor</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					  <input type="text" class="form-control" name="vc" id="vc" required/>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			   
			     <!-- row  -->
			   <div class="row">
			
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>no.of Affliated colleges</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					  <input type="number" class="form-control" name="noofaff" id="noofaff" required/>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			  
			     <!-- row  -->
			   <div class="row">
			
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>Estsblished Year</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					  <input type="number" class="form-control" name="estaYear" id="estaYear" required/>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			   
			   
			        <!-- row  -->
			   <div class="row">
			
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>Hostel Manager</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					  <input type="text" class="form-control" name="hostelManager" id="hostelManager" required/>

					 </div>
									  
				</div>
								
			   </div>
			   <!-- end row -->
			   
			   
			        <!-- row  -->
			   <div class="row">
			
			   <div class="col-xs-3 col-md-3" align="right">
				<label for="concept" class="col-xs-4 col-md-1 control-label-left"><font color="red">*&nbsp;</font>controller of examinations</label>
			   </div>
			   <div class="col-xs-5 col-md-6">
					<div class="form-group">
					  <input type="text" class="form-control" name="ce" id="ce" required/>

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