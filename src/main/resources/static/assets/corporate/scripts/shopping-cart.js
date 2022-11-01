const app = angular.module("myapp", []);
app.controller("myctrl", function($scope, $http) {
    $scope.cart = {
        items: [],
        //thêm sản phẩm
        add(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }

        },
        //xóa sản phẩm
        remove(id) {
            var item = this.items.find(item => item.id == id);
            this.items.splice(item, 1);
            this.saveToLocalStorage();
        },
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        //tính thành tiền từng sp
        sum_of_sl(item) {},
        //tính toỏng sl các mặt hàng 
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        //Tổng tiền
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },
        //Lưu giỏ hàng
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFromLocalStorage();
    $scope.order = {
        createDate: new Date(),
        address: "",
        account: { username: $("#username").text() },
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            $http.post("/rest/orders", order).then(resp => {
                alert("đặt hàng thành công");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;

            }).catch(error => {
                alert("Lỗi");
                console.log(error);
            })

        }
    }
})