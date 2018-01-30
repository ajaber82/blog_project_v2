<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- jstl tag library -->


    <!-- Header -->
    <div class="page-topbar">
      <div class="container">
        <div class="row">
          <div class="col-sm-4">

            <h3>Blog</h3>
            
          </div>
          <div class="col-sm-8 hidden-xs">
            
            <ol class="breadcrumb">
              <li><a href="#">Home</a></li>
              <li class="active">${categoryName}</li>
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
           <c:forEach var="blog" items="${blogs}">
	          <div class="blog__item">
	
	            <div class="blog__header">
	              <h1 class="blog__title">
	                <a href="${pageContext.request.contextPath}/blog/${blog.id}/detail/${blog.blogTitle}"><!-- link to detail page -->
	                   ${blog.blogTitle} 
	                </a>
	              </h1>
	              <ul class="blog__info">
	                <li>
	                  <i class="fa fa-user"></i> <a href="#">${blog.firstName}, ${blog.lastName}</a>
	                </li>
	                <li>
	                  <i class="fa fa-calendar"></i> <time datetime="2016-03-01">${blog.createdTime}</time>
	                </li>
	                <li>
	                  <i class="fa fa-comments-o"></i> ${blog.commentsCount} comments
	                </li>
	              </ul>
	            </div> <!-- / .blog__header -->
	
	            <div class="blog__body">
	               ${blog.blogBody}
	            </div> <!-- / .blog__body -->
	
	            <div class="blog__footer">
	              <ul class="blog__tags">
                    <c:forEach var="tag" items="${blog.tags }">
                       <li><a href="#">${tag.tagName}</a></li>
                    </c:forEach>
                  </ul>
	            </div> <!-- / .blog__footer -->
	
	          </div> <!-- / .blog__item -->
		</c:forEach>
         

          <!-- Pagination -->
          <nav class="text-right">
            <ul class="pagination">
              <li class="disabled">
                <a href="#" aria-label="Previous">
                  <span aria-hidden="true">&laquo;</span>
                </a>
              </li>
              <li class="active"><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li>
                <a href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
              </li>
            </ul>
          </nav>

        </div>
        <div class="col-sm-3">
   

          <!-- Contact info -->
          <h1 class="block-header alt">
            <span>Blog Categories</span>
          </h1>
          <c:forEach var="cat" items="${categories}">
         <a href="${pageContext.request.contextPath}/blog/${cat.id}/categories/${cat.categoryName}">
         ${cat.categoryName}
         </a> 
         <br>
          
          </c:forEach>
          
        </div>
      </div> <!-- / .row -->
    </div> <!-- / .container -->
