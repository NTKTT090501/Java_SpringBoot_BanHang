let host = "http://localhost:8080/rest";


const app = angular.module("app", []);
app.controller("ctrl", function($scope, $http) {
    $scope.formProduct = {};
    $scope.Product = [];
    $scope.formCategory = {};
    $scope.Category = [];
    $scope.sl = 0;
    $scope.reset = function() {
        $scope.formCategory = {};
        $scope.formProduct = {};
    }
    $scope.load_all = function() {

        var urlProduct = `${host}/products`;
        $http.get(urlProduct).then(resp => {
            $scope.Product = resp.data;
            console.log("success", resp)
            $scope.arr = [];
            for (let i = 0; i < $scope.Product.length; i++) {
                $scope.arr.push($scope.Product[i].id)
            }
            $scope.sl = $scope.Product.length;
            $scope.formProduct.id = Math.max.apply(Math, $scope.arr) + 1;
        }).catch(error => {
            console.log("error", error);
        });
        var urlCategory = `${host}/categories`;
        $http.get(urlCategory).then(resp => {
            $scope.Category = resp.data;
            console.log("success", resp)
        }).catch(error => {
            console.log("error", error);
        });
    }
    $scope.edit = function(id) {

            var url = `${host}/products/${id}`;
            $http.get(url).then(resp => {

                $scope.form = resp.data;
                console.log("success", resp)
            }).catch(error => {
                console.log("error", error);
            });

        }
        // $scope.create = function() {
        //     var item = angular.copy($scope.form);
        //     var url = `${host}/products`;
        //     var date = new Date($scope.form.createDate);
        //     console.log(item);
        //     console.log(date);
        //     $http.post(url, item).then(resp => {

    //         $scope.items.push(item);
    //         $scope.load_all();
    //         $scope.reset();
    //         console.log("success", resp)
    //     }).catch(error => {
    //         console.log("error", error);
    //     });
    // }
    // $scope.update = function() {
    //     var item = angular.copy($scope.form);
    //     var url = `${host}/products/${$scope.form.id}`;
    //     $http.put(url, item).then(resp => {
    //         var index = $scope.items.findIndex(item => item.id == $scope.form.id);
    //         $scope.items[index] = resp.data;
    //         $scope.load_all();
    //         $scope.reset();
    //         console.log("success", resp)
    //     }).catch(error => {
    //         console.log("error", error);
    //     });
    // }
    // $scope.delete = function(id) {

    //     var url = `${host}/products/${id}`;
    //     $http.delete(url).then(resp => {
    //         var index = $scope.items.findIndex(item => item.id == id);
    //         $scope.items.splice(index, 1);
    //         $scope.load_all();
    //         $scope.reset();
    //         console.log("success", resp)
    //     }).catch(error => {
    //         console.log("error", error);
    //     });
    // }
    $scope.load_all();
    $scope.reset();

})
