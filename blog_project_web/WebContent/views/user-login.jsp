<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page-topbar">
	<div class="container">
		<div class="row">
			<div class="col-sm-4">

				<h3>Sign In</h3>

			</div>
			<div class="col-sm-8 hidden-xs">

				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Sign In</li>
				</ol>

			</div>
		</div>
		<!-- / .row -->
	</div>
	<!-- / .container -->
</div>

<div class="container">
	<div class="row">
		<div
			class="col-sm-12 col-md-8 col-lg-6 col-md-offset-2 col-lg-offset-3">

			<!-- Sign In form -->
			<div class="profile__sign-in">

				<h1 class="block-header alt">
					<span>Create a new account</span>
				</h1>
				<!-- JSTL -->
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

				<form action="${pageContext.request.contextPath}/users/login" method="POST">
					<div class="form-group">
						<label class="sr-only">E-mail</label> <input name="txtEmail" type="email" required
							class="form-control input-lg" placeholder="E-mail">
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="sr-only">Password</label> <input name="txtPassword" type="password" required
									class="form-control input-lg" placeholder="Password">
							</div>
						</div>
					</div>
					<br />
					<button type="submit" class="btn btn-lg btn-primary">Login</button>
				</form>

				<hr>

				<p>
	              Not registered? <a href="${pageContext.request.contextPath}/users/register">Create an account</a>.
	            </p>

			</div>
			<!-- / .profile_sign-in -->

		</div>
	</div>
	<!-- / .row -->
</div>
<!-- / .container -->