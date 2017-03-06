/**
 * Created by colorado on 5/03/17.
 */
angular
    .module('order', [])
    .controller('OrderController', ['$scope', '$http', function($scope, $http) {
        $scope.order = {
            id: $('#id').val(),
            version: $('#version').val(),
            total: 0,
            orderDetails: []
        };

        if($scope.order.id != 0) {
            $http.get('/find-order/' + $scope.order.id)
                .then(function(response) {
                    console.log(response.data);
                    $scope.order = response.data;
                }, function(response) {
                    console.log(response);
                })
        }


        $scope.resetForm = function() {
            $scope.newForm = false;
            $scope.selectedProduct = $scope.catalog[0];
            $scope.quantity = 1;
        }

        //Loading Catalog
        $http.get('/catalog')
            .then(function successCallback(response) {
                $scope.catalog = response.data;
                $scope.resetForm();
            }, function errorCallback(response) {
                console.log(response);
            });

        $scope.newProduct = function() {
            $scope.newForm = !$scope.newForm;
        }

        $scope.addProduct = function() {
            $scope.order.total += $scope.selectedProduct.unitCost * $scope.quantity;
            $scope.order.orderDetails.push({
                product: $scope.selectedProduct,
                quantity: $scope.quantity,
                total: $scope.selectedProduct.unitCost * $scope.quantity
            });

            $scope.resetForm();
        }

        $scope.submit = function() {
            $http.post('/create-order', $scope.order)
                .then(function(response) {
                    if(response.data.id != null)
                        window.location.pathname='/order';
                    //console.log(response);
                }, function(response) {
                    console.log(response);
                })
        }

        $scope.removeProduct = function(id) {
            $scope.order.orderDetails =
                $scope.order.orderDetails.filter(function(detail) {
                    if(detail.product.id != id) {
                        return true;
                    } else {
                        $scope.order.total -= detail.product.unitCost * detail.quantity;
                    }
                });
        }
        
        $scope.recalculate = function () {
            $scope.order.total = 0;
            for(i in $scope.order.orderDetails) {
                var detail = $scope.order.orderDetails[i];
                $scope.order.total += detail.quantity * detail.product.unitCost;
            }
        }

        $scope.editProduct = function(detail) {
            detail.edit = true;
        }

        $scope.saveProduct = function(detail) {
            detail.edit = false;
        }

    }]);
