 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <div class="showcase">
      
      <div id="showcase-carousel" class="carousel slide" data-ride="carousel">
      
        <!-- Indicators -->
        <ol class="carousel-indicators">
          <li data-target="#showcase-carousel" data-slide-to="0" class="active"></li>
          <li data-target="#showcase-carousel" data-slide-to="1"></li>
          <li data-target="#showcase-carousel" data-slide-to="2"></li>
        </ol>
      
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">

          <!-- Item 1 -->
          <div class="item item_1 active">
            <div class="container">
              <div class="row">
                <div class="col-lg-6 col-md-8 col-sm-12">
                  <div class="table_centered">
                    <div class="table_centered__cell">
                      
                      <!-- Add animation classes to the active slide only -->
                      <h1 class="animateDown" data-animation="animateDown">
                        The Highland template
                      </h1>
                      <p class="lead delay_1 animateUp" data-animation="animateUp">
                        Professional multi-purpose HTML5 template <br /> built with Bootstrap.
                      </p>
                      <a href="#" class="btn btn-lg btn-primary delay_2 animateUp" data-animation="animateUp">
                        Purchase now!
                      </a>
                      <a href="#" class="btn btn-lg btn-glass delay_2 animateUp" data-animation="animateUp">
                        Find out more
                      </a>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Item 2 -->
          <div class="item item_2">
            <div class="container">
              <div class="row">
                <div class="col-lg-6 col-md-8 col-sm-12">
                  <div class="table_centered">
                    <div class="table_centered__cell">
                      
                      <h1 data-animation="animateDown">
                        Responsive grid system
                      </h1>
                      <p class="lead delay_1" data-animation="animateUp">
                        Looks great on all major browsers, tablets and phones.
                      </p>
                      <a href="#" class="btn btn-lg btn-primary delay_2" data-animation="animateUp">
                        Purchase now!
                      </a>
                      <a href="#" class="btn btn-lg btn-glass delay_2" data-animation="animateUp">
                        Find out more
                      </a>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Item 3 -->
          <div class="item item_3">
            <div class="container">
              <div class="row">
                <div class="col-sm-12">
                  <div class="table_centered">
                    <div class="table_centered__cell">
                      
                      <h1 data-animation="animateDown">
                        Ready-to-go solution
                      </h1>
                      <p class="lead delay_1" data-animation="animateUp">
                        Beautiful template that works out of the box.
                      </p>
                      <a href="#" class="btn btn-lg btn-primary delay_2" data-animation="animateUp">
                        Purchase now!
                      </a>
                      <a href="#" class="btn btn-lg btn-glass delay_2" data-animation="animateUp">
                        Find out more
                      </a>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div> <!-- / .carousel-inner -->
      
        <!-- Controls -->
        <a class="carousel__control carousel__control_left" href="#showcase-carousel" role="button" data-slide="prev">
          <img src="assets/img/arrow_left.svg" alt="...">  
        </a>
        <a class="carousel__control carousel__control_right" href="#showcase-carousel" role="button" data-slide="next">
          <img src="assets/img/arrow_right.svg" alt="...">  
        </a>
      
      </div> <!-- / .carousel -->

    </div> <!-- / .showcase -->


    <!-- SERVICES
    ================================================== -->
    <div class="container">
      <div class="row">
        <div class="col-md-12">

          <div class="services">
            <div class="services__item">
              <div class="services-item__body">
                <div class="services-item__icon">
                  <i class="fa fa-briefcase fa-3x"></i>
                </div>
                <div class="services-item__content">
                  <p class="services-item__caption">Lorem ipsum dolor sit amet</p>
                  <a class="services-item__link" href="#">Learn more <i class="fa fa-angle-right"></i></a>
                </div>
              </div>
            </div>
            <div class="services__item">
              <div class="services-item__body">
                <div class="services-item__icon">
                  <i class="fa fa-cloud-upload fa-3x"></i>
                </div>
                <div class="services-item__content">
                  <p class="services-item__caption">Lorem ipsum dolor sit amet</p>
                  <a class="services-item__link" href="#">Learn more <i class="fa fa-angle-right"></i></a>
                </div>
              </div>
            </div>
            <div class="services__item">
              <div class="services-item__body">
                <div class="services-item__icon">
                  <i class="fa fa-laptop fa-3x"></i>
                </div>
                <div class="services-item__content">
                  <p class="services-item__caption">Lorem ipsum dolor sit amet</p>
                  <a class="services-item__link" href="#">Learn more <i class="fa fa-angle-right"></i></a>
                </div>
              </div>
            </div>
            <div class="services__item">
              <div class="services-item__body">
                <div class="services-item__icon">
                  <i class="fa fa-gears fa-3x"></i>
                </div>
                <div class="services-item__content">
                  <p class="services-item__caption">Lorem ipsum dolor sit amet</p>
                  <a class="services-item__link" href="#">Learn more <i class="fa fa-angle-right"></i></a>
                </div>
              </div>
            </div>
            <div class="services__item">
              <div class="services-item__body">
                <div class="services-item__icon">
                  <i class="fa fa-compass fa-3x"></i>
                </div>
                <div class="services-item__content">
                  <p class="services-item__caption">Lorem ipsum dolor sit amet</p>
                  <a class="services-item__link" href="#">Learn more <i class="fa fa-angle-right"></i></a>
                </div>
              </div>
            </div>
            <div class="clearfix"></div>
          </div> <!-- / .services -->

        </div>
      </div> <!-- / row -->
    </div> <!-- / .container -->


    <!-- WELCOME MESSAGE 
    ================================================== -->
    <div class="container">
      <div class="row">
        <div class="col-sm-7 col-md-7 col-lg-8">
          
          <h1 class="block-header">
            <span>Hi ${MSG}</span>
          </h1>
          
          <div class="row">
            <div class="col-xs-12">
              <img src="assets/img/welcome.jpg" class="img-responsive img-article pull-left" alt="...">
              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aspernatur sunt expedita magni quas, soluta nisi aliquid maiores dolorem minima tempora, provident dolor similique commodi temporibus quibusdam consectetur molestias nihil quo.
              </p>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Suscipit itaque, alias, inventore obcaecati ex impedit quasi debitis aut velit odit modi voluptate dignissimos, iure iste. Veniam cum sunt doloremque totam. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolorum perferendis, in non odio at.
              </p>
            </div>
          </div> <!-- / .row -->

          <div class="info-board info-board-primary">
            <h4>Important info</h4>
            <p>
              Nunc in neque nec arcu vulputate ullamcorper. Ut id orci ac arcu consectetur fringilla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.
            </p>
          </div>

        </div>
        <div class="col-sm-5 col-md-5 col-lg-4">
          
          <h1 class="block-header">
            <span>Last Updates</span>
          </h1>

          <!-- Tabs -->
          <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active">
              <a href="#lorem" aria-controls="lorem" role="tab" data-toggle="tab">
                Blog
              </a>
            </li>
            <li role="presentation">
              <a href="#ipsum" aria-controls="ipsum" role="tab" data-toggle="tab">
                Comments
              </a>
            </li>
            <li role="presentation">
              <a href="#dolor" aria-controls="dolor" role="tab" data-toggle="tab">
                Events
              </a>
            </li>
          </ul>
          
          <!-- Tab panes -->
          <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="lorem">

              <div class="media">
                <div class="media-left">
                  <a href="#">
                    <img class="media-object" src="assets/img/blog_1.jpg" alt="...">
                  </a>
                </div>
                <div class="media-body">
                  <h4 class="media-heading">Story Title</h4>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor sed reprehenderit, ex. Quisquam unde dolorum.
                  </p>
                </div>
              </div>
              <div class="media">
                <div class="media-left">
                  <a href="#">
                    <img class="media-object" src="assets/img/blog_2.jpg" alt="...">
                  </a>
                </div>
                <div class="media-body">
                  <h4 class="media-heading">Story Title</h4>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor sed reprehenderit, ex. Quisquam unde dolorum.
                  </p>
                </div>
              </div>
              <div class="media">
                <div class="media-left">
                  <a href="#">
                    <img class="media-object" src="assets/img/blog_3.jpg" alt="...">
                  </a>
                </div>
                <div class="media-body">
                  <h4 class="media-heading">Story Title</h4>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor sed reprehenderit, ex. Quisquam unde dolorum.
                  </p>
                </div>
              </div>

            </div>
            <div role="tabpanel" class="tab-pane" id="ipsum">

              <div class="media">
                <div class="media-left">
                  <a href="#">
                    <img class="media-object" src="assets/img/blog_3.jpg" alt="...">
                  </a>
                </div>
                <div class="media-body">
                  <h4 class="media-heading">Comment Title</h4>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor sed reprehenderit, ex. Quisquam unde dolorum.
                  </p>
                </div>
              </div>
              <div class="media">
                <div class="media-left">
                  <a href="#">
                    <img class="media-object" src="assets/img/blog_1.jpg" alt="...">
                  </a>
                </div>
                <div class="media-body">
                  <h4 class="media-heading">Comment Title</h4>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor sed reprehenderit, ex. Quisquam unde dolorum.
                  </p>
                </div>
              </div>
              <div class="media">
                <div class="media-left">
                  <a href="#">
                    <img class="media-object" src="assets/img/blog_2.jpg" alt="...">
                  </a>
                </div>
                <div class="media-body">
                  <h4 class="media-heading">Comment Title</h4>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor sed reprehenderit, ex. Quisquam unde dolorum.
                  </p>
                </div>
              </div>

            </div>
            <div role="tabpanel" class="tab-pane" id="dolor">

              <div class="media">
                <div class="media-left">
                  <a href="#">
                    <img class="media-object" src="assets/img/blog_2.jpg" alt="...">
                  </a>
                </div>
                <div class="media-body">
                  <h4 class="media-heading">Event Title</h4>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor sed reprehenderit, ex. Quisquam unde dolorum.
                  </p>
                </div>
              </div>
              <div class="media">
                <div class="media-left">
                  <a href="#">
                    <img class="media-object" src="assets/img/blog_3.jpg" alt="...">
                  </a>
                </div>
                <div class="media-body">
                  <h4 class="media-heading">Event Title</h4>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor sed reprehenderit, ex. Quisquam unde dolorum.
                  </p>
                </div>
              </div>
              <div class="media">
                <div class="media-left">
                  <a href="#">
                    <img class="media-object" src="assets/img/blog_1.jpg" alt="...">
                  </a>
                </div>
                <div class="media-body">
                  <h4 class="media-heading">Event Title</h4>
                  <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolor sed reprehenderit, ex. Quisquam unde dolorum.
                  </p>
                </div>
              </div>

            </div>
          </div>

        </div>
      </div> <!-- / .row -->
    </div> <!-- / .container -->


    <!-- RECENT WORKS
    ================================================== -->
    <div class="container">
      <div class="row">
        <div class="col-sm-12">
          
          <h1 class="block-header">
            <span>Recent Works</span>
          </h1>

        </div>
      </div> <!-- / .row -->
      <div class="row">
        <div class="col-sm-3">
          
          <a href="gallery-item.html" class="thumbnail">
            <img src="assets/img/work_1.jpg" alt="...">
            <div class="caption">
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
        <div class="col-sm-3">
          
          <a href="gallery-item.html" class="thumbnail">
            <img src="assets/img/work_2.jpg" alt="...">
            <div class="caption">
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
        <div class="col-sm-3">
          
          <a href="gallery-item.html" class="thumbnail">
            <img src="assets/img/work_3.jpg" alt="...">
            <div class="caption">
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
        <div class="col-sm-3">
          
          <a href="gallery-item.html" class="thumbnail">
            <img src="assets/img/work_4.jpg" alt="...">
            <div class="caption">
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
      </div>
    </div> <!-- / .container -->