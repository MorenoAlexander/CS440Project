angular
    .module('ngMyapp')
    .controller('myappController', function($scope, myappFactory){
        $scope.hello = 'Hello world!'; 
        $scope.arr = [
            {
                "key": "One",
                "value": 1
            },
            {
                "key": "Two",
                "value": 2
            },
            {
                "key": "Three",
                "value": 1
            },
            {
                "key": "Four",
                "value": 2
            }
        ];

        $scope.data = myappFactory.getMyapp();
        $scope.clickMe = function(){
            console.log('Hello!')
        }
    });