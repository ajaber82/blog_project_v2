<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- jstl tag library -->

    <!-- Header -->
    <div class="page-topbar">
      <div class="container">
        <div class="row">
          <div class="col-sm-8">

            <h3>${blogDetail.blogTitle}</h3>
            
          </div>
          <div class="col-sm-4 hidden-xs">
            
            <ol class="breadcrumb">
              <li><a href="#">Home</a></li>
           
              <li class="active">Blog Detail</li>
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
          
          <div class="blog__item">

            <div class="blog__header">
              <h1 class="blog__title">
                <a href="#">
                 ${blogDetail.blogTitle}
                </a>
              </h1>
              <ul class="blog__info">
                <li>
                  <i class="fa fa-user"></i> <a href="#">${blogDetail.firstName}, ${blogDetail.lastName}</a>
                </li>
                <li>
                  <i class="fa fa-calendar"></i> <time datetime="2016-03-01"> ${blogDetail.createdTime}</time>
                </li>
                <li>
                  <i class="fa fa-comments-o"></i> ${blogDetail.commentsCount} comments
                </li>
              </ul>
            </div> <!-- / .blog__header -->

            <div class="blog__body">
             ${blogDetail.blogBody}
             
            </div> <!-- / .blog__body -->

            <div class="blog__footer">
              <ul class="blog__tags">
                <c:forEach var="tag" items="${blogDetail.tags }">
                   <li><a href="#">${tag.tagName}</a></li>
                </c:forEach>
              </ul>
            </div> <!-- / .blog__footer -->

          </div> <!-- / .blog__item -->


          <!-- Comments section -->
          <div class="comments">

            <div class="comments__heading">4 comments</div>

            <!-- New comment form -->
            <form name="comments__new">

              <div class="form-group">
                <textarea class="form-control" rows="1" placeholder="Write your comment here"></textarea>
              </div>

              <button type="submit" class="btn btn-primary" disabled="disabled">Send</button>

            </form>

            <!-- List of comments -->
            <div class="comments__list">
              
              <!-- Comment item -->
              <div class="comments__item">
                
                <div class="comments-item__avatar">
                  <img src="assets/img/person_1.jpg" class="img-responsive" alt="..." />
                </div>

                <div class="comments-item__body">

                  <div class="comments-item__info">
                    <div class="comments-item-info__author">Jane Doe</div>
                    <div class="comments-item-info__divider">
                      <i class="fa fa-circle"></i>
                    </div>
                    <div class="comment-item-info__timestamp">3 hours ago</div>
                  </div>

                  <div class="comments-item__content">
                    Nunc sagittis in est malesuada finibus. Aliquam non metus nec nisi auctor vulputate eget at purus. Morbi mattis nisl elit, id egestas magna viverra ut. Nunc condimentum porttitor tortor a tempus.
                  </div>

                  <div class="comments-item__actions">
                    <a href="#" class="comment-item-actions__reply">
                      Reply
                    </a>
                  </div>
                </div>

              </div> <!-- / .comments__item -->

              <!-- Comment item -->
              <div class="comments__item">
                
                <div class="comments-item__avatar">
                  <img src="assets/img/person_2.jpg" class="img-responsive" alt="..." />
                </div>

                <div class="comments-item__body">

                  <div class="comments-item__info">
                    <div class="comments-item-info__author">John Doe</div>
                    <div class="comments-item-info__divider">
                      <i class="fa fa-circle"></i>
                    </div>
                    <div class="comment-item-info__timestamp">5 hours ago</div>
                  </div>

                  <div class="comments-item__content">
                    In quis tellus tristique, ullamcorper mauris quis, mollis urna. Maecenas cursus auctor vestibulum. Suspendisse eu mi convallis, porttitor leo sit amet, fermentum nulla. Fusce rhoncus ex maximus, condimentum elit tincidunt, venenatis augue.
                  </div>

                  <div class="comments-item__actions">
                    <a href="#" class="comment-item-actions__reply">
                      Reply
                    </a>
                  </div>
                </div>

              </div> <!-- / .comments__item -->

              <!-- Comment item -->
              <div class="comments__item">
                
                <div class="comments-item__avatar">
                  <img src="assets/img/person_3.jpg" class="img-responsive" alt="..." />
                </div>

                <div class="comments-item__body">

                  <div class="comments-item__info">
                    <div class="comments-item-info__author">Jane Doe</div>
                    <div class="comments-item-info__divider">
                      <i class="fa fa-circle"></i>
                    </div>
                    <div class="comment-item-info__timestamp">1 day ago</div>
                  </div>

                  <div class="comments-item__content">
                    Pellentesque leo urna, ornare non pharetra eu, suscipit sit amet magna. Vivamus vel quam vitae massa posuere pellentesque accumsan eu justo. Cras non porttitor leo
                  </div>

                  <div class="comments-item__actions">
                    <a href="#" class="comment-item-actions__reply">
                      Reply
                    </a>
                  </div>
                </div>

              </div> <!-- / .comments__item -->

              <!-- Comment item -->
              <div class="comments__item">
                
                <div class="comments-item__avatar">
                  <img src="assets/img/person_4.jpg" class="img-responsive" alt="..." />
                </div>

                <div class="comments-item__body">

                  <div class="comments-item__info">
                    <div class="comments-item-info__author">John Doe</div>
                    <div class="comments-item-info__divider">
                      <i class="fa fa-circle"></i>
                    </div>
                    <div class="comment-item-info__timestamp">2 days ago</div>
                  </div>

                  <div class="comments-item__content">
                    Sed id vestibulum mauris. Nunc venenatis mauris sit amet accumsan tincidunt. Praesent cursus nulla sed accumsan pulvinar. Donec ipsum justo, pretium at neque eget, imperdiet tristique quam.
                  </div>

                  <div class="comments-item__actions">
                    <a href="#" class="comment-item-actions__reply">
                      Reply
                    </a>
                  </div>
                </div>

              </div> <!-- / .comments__item -->

            </div> <!-- / .comments__list -->

          </div> <!-- / .comments -->

        </div>
        <div class="col-sm-3">
          
          <!-- Keep in touch -->
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

          <!-- Recent works -->
          <h1 class="block-header alt">
            <span>Recent works</span>
          </h1>
          <div class="thumbnails_small">
            <div class="row">
              <div class="col-sm-6">
                
                <a href="gallery-item.html" class="thumbnail">
                  <img src="assets/img/work_1.jpg" alt="...">
                  <div class="caption hidden">
                    <h5>Project Title</h5>
                    <ul class="rating">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                    </ul>
                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                    </p>
                  </div>
                </a>

              </div>
              <div class="col-sm-6">
                
                <a href="gallery-item.html" class="thumbnail">
                  <img src="assets/img/work_2.jpg" alt="...">
                  <div class="caption hidden">
                    <h5>Project Title</h5>
                    <ul class="rating">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                    </ul>
                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                    </p>
                  </div>
                </a>

              </div>
              <div class="col-sm-6">
                
                <a href="gallery-item.html" class="thumbnail">
                  <img src="assets/img/work_3.jpg" alt="...">
                  <div class="caption hidden">
                    <h5>Project Title</h5>
                    <ul class="rating">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                    </ul>
                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                    </p>
                  </div>
                </a>

              </div>
              <div class="col-sm-6">
                
                <a href="gallery-item.html" class="thumbnail">
                  <img src="assets/img/work_4.jpg" alt="...">
                  <div class="caption hidden">
                    <h5>Project Title</h5>
                    <ul class="rating">
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                      <li><i class="fa fa-star"></i></li>
                    </ul>
                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                    </p>
                  </div>
                </a>

              </div>
            </div> <!-- / .row -->
          </div> <!-- / .thumbnail_small -->

          <!-- Contact info -->
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