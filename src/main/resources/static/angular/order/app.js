/**
 * Created by colorado on 5/03/17.
 */
angular
    .module('order', [])
    .controller('OrderController', ['$scope', '$http', function($scope, $http) {
        $scope.order = {
            orderDetails: []
        };

        $scope.grandTotal = 0;

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
            $scope.grandTotal += $scope.selectedProduct.unitCost * $scope.quantity;
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
                    console.log(response);
                }, function(response) {
                    console.log(response);
                })
        }

    }]);
