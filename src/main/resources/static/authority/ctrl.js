var app = angular.module("app", []);

app.controller("ctrl", function($scope, $http){
	
	$http.get("/rest/autho").then(resp => {
		$scope.db = resp.data;
		console.log($scope.db.authorities)
		console.log($scope.db.accounts[1].username)
	})
	$scope.index_of = function(username, role){
		return $scope.db.authorities
		.findIndex(a => a.account.username == username && a.role.id == role)
		
	}
	$scope.update = function(username, role){
		var index = $scope.index_of(username, role);
		console.log(index);
		console.log(username, role);
		if(index >= 0){
			var id = $scope.db.authorities[index].id;
			$http.delete(`/rest/autho/${id}`).then(resp => {
				$scope.db.authorities.splice(index, 1);
			}).catch(error => {
            console.log("Errors", error);
        });
		}
		else {
			var authorities ={
				account:{username: username},
				role:{id: role}
			};
			
			$http.post(`/rest/autho`, authorities).then(resp => {
				$scope.db.authorities.push(resp.data);
			})
		}
	}
});