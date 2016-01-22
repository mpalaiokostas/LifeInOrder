'use strict';

app.config(function ($routeProvider) {
    $routeProvider.
        when('/',{
            templateUrl: 'views/home.html',
            controller: 'myCalendar'
        }).when('/Home', {
            templateUrl: 'views/home.html',
            controller: 'myCalendar'
        }).when('/Transactions', {
            templateUrl: 'views/transactions.html',
            controller: 'transactions'
        }).when('/Statistics', {
            templateUrl: 'views/statistics.html',
            controller: 'statistics'
        }).when('/Microphone', {
            templateUrl: 'views/microphone.html',
            controller: 'VoiceCtrl'
        }).otherwise({
            templateUrl: 'views/home.html'
        });
});