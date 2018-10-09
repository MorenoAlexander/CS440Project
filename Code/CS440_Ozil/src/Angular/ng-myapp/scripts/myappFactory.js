angular
    .module('ngMyapp')
    .factory('myappFactory', function(){
        hello = 'Hello world!'; 
        arr = [
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

        function getMyapp(){
            return arr;
        }

        return{
            getMyapp: getMyapp
        }
    });