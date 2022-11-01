var app = angular.module("myapp", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/product", {
            templateUrl: "/assets/admin/product/index.html",
            controller: "product-ctrl"
        })
        .when("/authorize", {
            templateUrl: "/assets/admin/authority/index.html",
            controller: "authority-ctrl"
        })
       
        .otherwise({
            template: "<h1> FPT Polytechnic Administration</h1>"
        });

})