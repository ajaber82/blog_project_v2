<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page-topbar">
	<div class="container">
		<div class="row">
			<div class="col-sm-4">

				<h3>Sign Up</h3>

			</div>
			<div class="col-sm-8 hidden-xs">

				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active">Sign Up</li>
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

				<form action="${pageContext.request.contextPath}/users/register" method="POST">
					<div class="form-group">
						<label class="sr-only">First name</label> <input name="txtFirstName" type="text" required
							class="form-control input-lg" placeholder="First name">
					</div>
					<div class="form-group">
						<label class="sr-only">Last name</label> <input name="txtLastName" type="text" required
							class="form-control input-lg" placeholder="Last name">
					</div>
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
						<div class="col-sm-6">
							<div class="form-group">
								<label class="sr-only">Repeat password</label> <input name="txtConfirmPassword" required
									type="password" class="form-control input-lg"
									placeholder="Repeat password">
							</div>
						</div>
					</div>
					<div class="checkbox">
						<input type="checkbox" id="profile-sign-in__remember" value="">
						<label for="profile-sign-in__remember"> I agree to the <a
							href="#">Terms of Service</a> and <a href="#">Privacy Policy</a>
						</label>
					</div>
					<br />
					<button type="submit" class="btn btn-lg btn-primary">Sign
						Up</button>
				</form>

				<hr>

				<p>
					Already registered? <a href="sign-in.html">Sign in to your
						account</a>.
				</p>

			</div>
			<!-- / .profile_sign-in -->

		</div>
	</div>
	<!-- / .row -->
</div>
<!-- / .container -->