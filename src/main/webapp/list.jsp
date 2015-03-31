<!DOCTYPE html>
<html ng-app="people">
<head>
<%@ include file="imports/head.jsp"%>
<title>List - Have You Seen?</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.3/angular.min.js"></script>
<script src="js/controllers.js"></script>
</head>
<body ng-controller="peopleController">
	<%@ include file="imports/menu.jsp"%>
	<div class="intro-margin"></div>
	<div class="content-section-a">
		<div class="container">
			<form novalidate id="people-form">
				<fieldset>
				<legend>New Member</legend>
					<div class="row">
						<div class="form-group col-lg-3">
							<label for="name">Name</label>
							<input type="text" class="form-control" ng-model="personInForm.name" placeholder="Enter name">
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-3">
							<label for="surname">Surname</label>
							<input type="text" class="form-control" ng-model="personInForm.surname" placeholder="Enter surname">
						</div>
					</div>
					<button type="submit" class="btn btn-primary" ng-click="insert()" ng-hide="isEditing">Create</button>
					<button type="submit" class="btn btn-primary" ng-click="update()" ng-show="isEditing">Save</button> 
					<button type="reset" class="btn btn-primary" ng-click="resetForm()">Clean</button>
				</fieldset>
			</form>
		</div>
	</div>
	<div class="content-section-b">
		<div class="container">
			<table class="table table-hover text-center" id="people-list">
				<tr class="first-row">
					<td>Id</td>
					<td>Name</td>
					<td>Surname</td>
					<td></td>
					<td></td>
				</tr>
				<tr ng-repeat="person in people">
					<td>{{person.id}}</th>
					<td>{{person.name}}</th>
					<td>{{person.surname}}</th>
					<td><a href="#" ng-click="edit(person.id)">Edit</a></td>
					<td><a href="#" ng-click="delete(person.id)">Delete</a></td>
				</tr>
			</table>
		</div>
	</div>
	<%@ include file="imports/footer.jsp" %>
</body>
</html>