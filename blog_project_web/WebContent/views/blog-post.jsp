<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <div class="page-topbar">
      <div class="container">
        <div class="row">
          <div class="col-sm-4">

            <h3>Contact Us</h3>
            
          </div>
          <div class="col-sm-8 hidden-xs">
            
            <ol class="breadcrumb">
              <li><a href="#">Home</a></li>
              <li class="active">Blog post</li>
            </ol>

          </div>
        </div> <!-- / .row -->
      </div> <!-- / .container -->
    </div>


    <!-- CONTENT
    ================================================== -->
    <div class="container">
      <div class="row">
        <div class="col-sm-9">

          <h1 class="block-header alt">
            <span>Create new blog</span>
          </h1>

          
         <c:if test="${errorList != null}">
			<div class="alert alert-danger">
				<strong>Errors!</strong><br>
				<c:forEach var="msg" items="${errorList}">
					      ${msg}<br>
				</c:forEach>
			</div>
		  </c:if>
		  
		  <c:if test="${message != null }">
			<div class="alert alert-success">
				 Success! ${message}
			</div>
		  </c:if>

          <form action="${pageContext.request.contextPath}/blog/post" method="POST">
            <div class="form-group">
                <label class="sr-only" for="txtBlogTitle">Title</label>
                <input type="text" id="txtBlogTitle" name="txtBlogTitle" class="form-control" placeholder="Blog title">
            </div>
            <div class="form-group">
              <label class="sr-only" for="txtBlogBody">Blog body</label>
              <textarea name="txtBlogBody" class="input-lg form-control" rows="12" id="txtBlogBody" placeholder="Blog body"></textarea>
              <span class="help-block"></span>
            </div>
            
             <div class="form-group">
			      <label class="sr-only" for="lsCategory">Select list (select one):</label>
			      <select class="form-control" id="lsCategory" name="lsCategory">
			        <option value="-1" selected>--Please select category--</option>
			        <c:forEach var="cat" items="${categories }">
			              <option value="${cat.id}">${cat.categoryName}</option>
			        </c:forEach>
			        
			      </select>
		     </div>
           
            <button type="submit" class="btn btn-lg btn-primary">Post blog</button>
          </form>
          
        </div>
        <div class="col-sm-3">
          
          <h1 class="block-header alt">
            <span>Keep in touch</span>
          </h1>

          <ul class="social-icons">
            <li class="facebook">
              <a href="#"><i class="fa fa-facebook"></i></a>
            </li>
            <li class="twitter">
              <a href="#"><i class="fa fa-twitter"></i></a>
            </li>
            <li class="google-plus">
              <a href="#"><i class="fa fa-google-plus"></i></a>
            </li>
            <li class="linkedin">
              <a href="#"><i class="fa fa-linkedin"></i></a>
            </li>
            <li class="rss">
              <a href="#"><i class="fa fa-rss"></i></a>
            </li>
          </ul>

          <h1 class="block-header alt">
            <span>Contact info</span>
          </h1>

          <p class="text-muted">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi, corrupti debitis.
          </p>
          <p class="text-muted">
            US & CAN: 1-234-56789 <br />
            Worldwide: 1-234-56789
          </p>
          <p class="text-muted">
            E-mail: info@domain.com <br />
            Website: <a href="#">https://www.domain.com/</a>
          </p>

        </div>
      </div> <!-- / .row -->
    </div> <!-- / .container -->