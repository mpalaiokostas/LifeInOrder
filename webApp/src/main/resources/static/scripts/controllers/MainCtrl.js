'use strict';

app.controller('MainCtrl', function ($scope) {

  $scope.myData = [{
    "date": "10/08/2015", "description": "Cost 1", "cost": "10"
  }, {
    "date": "11/08/2015", "description": "Cost 2", "cost": "20"
  }, {
    "date": "12/08/2015", "description": "Cost 3", "cost": "30"
  }, {
    "date": "13/08/2015", "description": "Cost 4", "cost": "40"
  }, {
    "date": "14/08/2015", "description": "Cost 5", "cost": "50"
  }, {
    "date": "15/08/2015", "description": "Cost 6", "cost": "60"
  }, {
    "date": "16/08/2015", "description": "Cost 7", "cost": "70"
  }];

  $scope.id = "1";
  $scope.content = "Manos";

});