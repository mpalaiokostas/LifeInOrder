'use strict';

app.controller('statistics', function ($scope, $http, $resource) {

  $scope.monthlyStatistics = function () {
    $http.get("/api/statistics/monthly").then(function successCallback(response) {
      $scope.gridOptions.data = response.data;
    }, function errorCallback(response) {
      $scope.gridOptions.data = null;
    });
  };
  $scope.monthlyStatistics();

  //$resource('/api/statistics/monthly').query(function(result) {
  //  $scope.monthlyStats = result;
  //});

  $scope.gridOptions = {
    showGridFooter: true,
    showColumnFooter: true,
    enableFiltering: true,
    enableGridMenu: true,
    enableSelectAll: true,
    exporterCsvFilename: 'monthlyBankStatistics.csv',
    exporterPdfDefaultStyle: {fontSize: 9},
    exporterPdfTableStyle: {margin: [30, 30, 30, 30]},
    exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'red'},
    exporterPdfHeader: { text: "Monthly Bank Statistics", style: 'headerStyle' },
    exporterPdfFooter: function ( currentPage, pageCount ) {
      return { text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle' };
    },
    exporterPdfCustomFormatter: function ( docDefinition ) {
      docDefinition.styles.headerStyle = { fontSize: 22, bold: true };
      docDefinition.styles.footerStyle = { fontSize: 10, bold: true };
      return docDefinition;
    },
    exporterPdfOrientation: 'portrait',
    exporterPdfPageSize: 'LETTER',
    exporterPdfMaxGridWidth: 500,
    exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),
    onRegisterApi: function(gridApi){
      $scope.gridApi = gridApi;
    },

    columnDefs: [
      //{ field: 'ID' },
      { field: 'yearMonth.year' },
      { field: 'yearMonth.monthOfYear' },
      { field: 'income' },
      { field: 'expense' },
      { field: 'profit' }
    ]
  };

});