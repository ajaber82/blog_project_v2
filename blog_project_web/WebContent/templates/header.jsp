<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-default">
      <div class="container">

        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">

          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar_main" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>

          <a class="navbar-brand" href="index.html">Highland</a>

        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar_main">

          <ul class="nav navbar-nav navbar-left">
            <li><a href="index.html">Home</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Profile <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="profile.html">Profile</a></li>
                <li><a href="edit-profile.html">Edit Profile</a></li>
                <li><a href="inbox.html">Inbox</a></li>
                <li><a href="inbox-dialog.html">Inbox: Dialog</a></li>
                <li><a href="sign-in.html">Sign In</a></li>
                <li><a href="sign-up.html">Sign Up</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Blog <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="blog.html">Blog</a></li>
                <li><a href="blog-timeline.html">Blog: Timeline</a></li>
                <li><a href="blog-post.html">Blog Post</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Gallery <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="gallery-text.html">Gallery: Text blocks</a></li>
                <li><a href="gallery-bubbles.html">Gallery: Bubbles</a></li>
                <li><a href="gallery-item.html">Gallery Item</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">More Pages <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="about-us.html">About Us</a></li>
                <li><a href="contact.html">Contact Us</a></li>
                <li><a href="404.html">Error 404</a></li>
                <li><a href="faq.html">FAQ</a></li>
                <li><a href="team.html">Our Team</a></li>
                <li><a href="pricing.html">Pricing</a></li>
              </ul>
            </li>
            <li><a href="ui-elements.html">UI elements</a></li>
          </ul>

          <ul class="nav navbar-nav navbar-right">
            <li>
              <a href="${pageContext.request.contextPath}/users/login"><i class="fa fa-lock"></i> <span class="hidden-md">Sign In</span></a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/users/register"><i class="fa fa-user"></i> <span class="hidden-md">Sign Up</span></a>
            </li>
          </ul>

          <form class="navbar-form navbar-search navbar-right">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="&#xF002; &nbsp; Search">
            </div>
            <button type="submit" class="btn btn-default">
              <i class="fa fa-search"></i> Search
            </button>
          </form>

        </div><!-- /.navbar-collapse -->
      </div><!-- /.container -->
    </nav>