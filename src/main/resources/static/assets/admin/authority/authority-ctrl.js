app.controller("authority-ctrl", function($scope, $http, $location) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authoriries = [];

    $scope.initialize = function() {
        $http.get(`/rest/roles`).then(resp => {
            $scope.roles = resp.data;
            console.log($scope.roles)
        })
        
        $http.get(`/rest/accounts?admin=true`).then(resp => {
            $scope.admins = resp.data;
            console.log($scope.admins)
        })
        $http.get(`/rest/authorities?admin=true`).then(resp => {
            $scope.authorities = resp.data;
            console.log($scope.authoriries)
        }).catch(error =>{
        	console.log(error)
        })
        
        
        

    }
    $scope.authority_of = function(acc, role) {
        if ($scope.authoriries) {
        console.log($scope.authoriries.find(ur => ur.account.username == "ANATR" && ur.role.id == "CUST"))
            return $scope.authoriries.find(ur => ur.account.username == acc && ur.role.id == role);

        }

    }
    $scope.authority_changed = function(acc, role) {
        var authority = $scope.authority_of(acc, role);;
        if (authority) {
            $scope.revoke_authority(authority);
        } else {
            authority = { account: acc, role: role };
            $scope.grant_authority(authority);
        }
    }
    $scope.grant_authority = function(authority) {
        $http.post(`/rest/authorities`, authority).then(resp => {
            $scope.authoriries.push(resp.data);
            alert("Cap quyen thanh cong");

        }).catch(error => {
            alert("Cap quyen thhat bai");
            console.log("ERROR", error);
        })
    }
    $scope.revoke_authority = function(authority) {
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
            var index = $scope.authoriries.findIndex(a => a.id == authority.id);
            $scope.authoriries.splice(index, 1);
            alert("thanh cong");
        }).catch(error => {
            alert("that bai");
        })
    }
    $scope.initialize();
})