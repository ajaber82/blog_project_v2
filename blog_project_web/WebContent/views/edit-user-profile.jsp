<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- jstl tag library -->

  <!-- Header -->
    <div class="page-topbar">
      <div class="container">
        <div class="row">
          <div class="col-sm-4">

            <h3> ${userProfile.firstName} ${userProfile.lastName} Edit profile</h3>
            
          </div>
          <div class="col-sm-8 hidden-xs">
            
            <ol class="breadcrumb">
              <li><a href="#">Home</a></li>
              <li class="active">${userProfile.firstName} ${userProfile.lastName} Edit profile</li>
            </ol>

          </div>
        </div> <!-- / .row -->
      </div> <!-- / .container -->
    </div>


    <!-- CONTENT
    ================================================== -->
    <div class="container">
      <div class="row">
        <div class="col-sm-3">
          <div class="profile__aside">
          
            <div class="profile__img">
              <img src="/medias/profile-picture/${profilePicture}" class="img-responsive" alt="...">
            </div>

            <h4 class="profile__name">${userProfile.firstName} ${userProfile.lastName} <small>Administrator</small></h4>

            <ul class="social-icons social-icons_sm">
              <li class="facebook">
                <a href="#"><i class="fa fa-facebook"></i></a>
              </li>
              <li class="twitter">
                <a href="#"><i class="fa fa-twitter"></i></a>
              </li>
              <li class="google-plus">
                <a href="#"><i class="fa fa-google-plus"></i></a>
              </li>
            </ul>

            <hr />

            <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#profile__message">
              <i class="fa fa-envelope-o"></i> Send message
            </a>

          </div> <!-- / .profile__aside -->
        </div>
        <div class="col-sm-9">
          
          <!-- Profile nav -->
          <nav class="clearfix">
            <ul class="profile__nav">
              <li>
                <a href="${pageContext.request.contextPath}/users/profile/${userProfile.firstName}-${userProfile.lastName}/${userProfile.id}/info">
                  <i class="fa fa-user"></i> Profile
                </a>
              </li>
                <li>
                <a href="${pageContext.request.contextPath}/users/profile/${userProfile.firstName}-${userProfile.lastName}/${userProfile.id}/blogs">
                  <i class="fa fa-file-text-o"></i> Blogs
                </a>
              </li>
          
              <li class="active">
                <a href="${pageContext.request.contextPath}/users/profile/edit-profile">
                  <i class="fa fa-edit"></i> Edit
                </a>
              </li>
            
              <li>
                <a href="${pageContext.request.contextPath}/users/logout">
                  <i class="fa fa-sign-out"></i> Logout
                </a>
              </li>
            </ul>
          </nav>
            
          <div class="profile__body">
          

            <h1 class="block-header alt">
              <span>Edit profile</span>
            </h1>
            
            <c:if test="${errorList != null}">
					<div class="alert alert-danger">
					  <strong>Errors!</strong><br>
					  <c:forEach var="msg" items="${errorList}">
					      ${msg}<br>
					  </c:forEach>
					</div>
			</c:if>

            
            <form action="${pageContext.request.contextPath}/users/profile/update-profile" enctype="multipart/form-data" method="POST">

              <div class="form-group">
                <label class="sr-only">First name</label>
                <input name="txtFirstName" type="text"  value="${userProfile.firstName}"  class="form-control input-lg" placeholder="First name">
              </div>
              
              <div class="form-group">
                <label class="sr-only">Last name</label>
                <input name="txtLastName" type="text"  value="${userProfile.lastName}"  class="form-control input-lg" placeholder="Last name">
              </div>
                         
                         
              <div class="form-group">
                <label class="sr-only">About me</label>
                <textarea name="txtAboutMe" class="input-lg form-control" rows="12" id="txtAboutMe" placeholder="About me" ><c:out value="${userProfile.aboutMe}"/></textarea>
                <span class="help-block"></span>
              </div>
              
               <div class="form-group">
                <label class="sr-only">Profile Picture</label>
                <input name="filePicture" type="file" class="form-control input-lg" placeholder="Last name">
              </div>
          
                      
              <button type="submit" class="btn btn-primary">
                Update
              </button>

            </form>

          </div> <!-- / .profile__body -->

        </div>
      </div> <!-- / .row -->
    </div> <!-- / .container -->


    <!-- MESSAGE
    ================================================== -->
    <div class="modal fade" id="profile__message" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">New message</h4>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <label class="sr-only">Subject</label>
                <input type="text" class="form-control" placeholder="Subject">
              </div>
              <div class="form-group">
                <label class="sr-only">Message</label>
                <textarea rows="5" class="form-control" placeholder="Message"></textarea>
              </div>
              <button type="button" class="btn btn-primary">Send message</button>
              <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </form>
          </div>
        </div> <!-- / .modal-content -->
      </div> <!-- / .moda-dialog -->
    </div> <!-- / .modal -->

