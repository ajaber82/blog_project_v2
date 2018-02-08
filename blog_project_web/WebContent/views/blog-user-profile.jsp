<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- jstl tag library -->

  <!-- Header -->
    <div class="page-topbar">
      <div class="container">
        <div class="row">
          <div class="col-sm-4">

            <h3>${userProfile.firstName} ${userProfile.lastName} Profile</h3>
            
          </div>
          <div class="col-sm-8 hidden-xs">
            
            <ol class="breadcrumb">
              <li><a href="#">Home</a></li>
              <li class="active">${userProfile.firstName} ${userProfile.lastName} Profile</li>
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
              <img src="assets/img/person_1.jpg" class="img-responsive" alt="...">
            </div>

            <h4 class="profile__name">${userProfile.firstName} ${userProfile.lastName}</h4>

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
              <li class="active">
                <a href="${pageContext.request.contextPath}/users/profile/${userProfile.firstName}-${userProfile.lastName}/${userProfile.id}/info">
                  <i class="fa fa-user"></i> Profile
                </a>
              </li>
                <li>
                <a href="${pageContext.request.contextPath}/users/profile/${userProfile.firstName}-${userProfile.lastName}/${userProfile.id}/blogs">
                  <i class="fa fa-file-text-o"></i> Blogs
                </a>
              </li>
              <li>
                <a href="edit-profile.html">
                  <i class="fa fa-edit"></i> Edit
                </a>
              </li>
            
              <li>
                <a href="#">
                  <i class="fa fa-sign-out"></i> Sign out
                </a>
              </li>
            </ul>
          </nav>
            
          <div class="profile__body">

            <h1 class="block-header alt">
              <span>About ${userProfile.firstName} ${userProfile.lastName}</span>
            </h1>
            <p class="text-muted">
	             <c:choose>
				    <c:when test="${empty userProfile.aboutMe}">
				        There is no information about ${userProfile.firstName}  ${userProfile.lastName}.
				    </c:when>
				    <c:otherwise>
				        ${userProfile.aboutMe}
				    </c:otherwise>
				</c:choose>
            </p>
           

            <h1 class="block-header alt">
              <span>User Email</span>
            </h1>
            <p class="text-muted">
            	${userProfile.email}
            </p>


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
