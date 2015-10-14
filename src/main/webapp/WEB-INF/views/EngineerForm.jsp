<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Engineer Enrollment Form</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/custom.css' />" rel="stylesheet"></link>
</head>

<body>

	<div class="form-container">

		<h1>Engineer Details Form</h1>

		<form:form method="POST" modelAttribute="engineer"
			enctype="multipart/form-data" onsubmit="return validateForm()"
			class="form-horizontal">
		
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="firstName">First
						Name</label>
					<div class="col-md-7">
						<form:input type="text" path="first_name" id="firstName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="first_name" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="lastName">Last
						Name</label>
					<div class="col-md-7">
						<form:input type="text" path="last_name" id="lastName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="last_name" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="gender">gender</label>
					<div class="col-md-7" class="form-control input-sm">
						<form:radiobutton path="gender" value="M" />
						Male
						<form:radiobutton path="gender" value="F" />
						Female
						<div class="has-error">
							<form:errors path="gender" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="dob">Date of
						birth</label>
					<div class="col-md-7">
						<form:input type="date" path="dob" id="dob"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="dob" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable">Highest
						Qualification:</label>
					<div class="col-md-7">
						<form:select path="qualification">
							<c:forEach items="${qualificationListItems}" var="oper">
								<form:option label="${oper.description}"
									value="${oper.description}" />
							</c:forEach>
						</form:select>
						<div class="has-error">
							<form:errors path="qualification" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="email">Email:</label>
					<div class="col-md-7">
						<form:input type="email" path="email_addr" id="email"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="email_addr" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="mobile_number">Mobile
						Number:</label>
					<div class="col-md-7">
						<form:input type="text" path="mobile_number" id="mobile_number"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="mobile_number" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable">Website:</label>
					<div class="col-md-7">

						<form:input type="url" path="website"
							class="form-control input-sm" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">

					<label class="col-md-3 control-lable">Address:</label>
					<div class="col-md-7">
						<form:textarea path="address" rows="6"
							class="form-control input-sm" />
					</div>
				</div>
			</div>
			<div class="row">

				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable">Technical Skills:</label>
					<div class="col-md-7">
						<form:textarea type="textarea" path="technical_skills"
							class="form-control input-sm" rows="6" />
						<div class="has-error">

							<form:errors path="technical_skills" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">

				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable">Experience</label>
					<div class="col-md-7">

						<form:select path="experience">
							<form:options items="${experienceListItems}"
								itemLabel="description" path="experience" />

						</form:select>
						<div class="has-error">
							<form:errors path="experience" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">

				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable">Remarks:</label>
					<div class="col-md-7">

						<form:textarea rows="6" path="remarks" placeholder="Remarks"
							class="form-control input-sm" />
						<div class="col-md-7"></div>
					</div>
				</div>
			</div>

			<div class="row">
				<label class="control-label col-xs-2">Image:</label> <input
					type="file" name="image" onchange="previewFile()" accept="image/*" />
				<img src="${engineer.imageSource}" height="200"
					alt="Image preview...">
			</div>

			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Submit"
						onsubmit="return validateForm()" class="btn btn-primary btn-sm">
				</div>
			</div>

		</form:form>
		<script>
			function validateForm() {
				return confirm('Confirm submission?');
			}
			function previewFile() {
				var preview = document.querySelector('img'); //selects the query named img
				var file = document.querySelector('input[type=file]').files[0]; //sames as here
				var reader = new FileReader();

				reader.onloadend = function() {
					preview.src = reader.result;
				}

				if (file) {
					reader.readAsDataURL(file); //reads the data as a URL
				} else {
					preview.src = "";
				}
			}

			previewFile();
		</script>
	</div>
</body>
</html>