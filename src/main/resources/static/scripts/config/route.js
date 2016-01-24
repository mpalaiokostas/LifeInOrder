'use strict';

app.config(function ($routeProvider) {
    $routeProvider.
        when('/',{
            templateUrl: 'views/calendar.html',
            controller: 'myCalendar'
        }).when('/Calendar', {
            templateUrl: 'views/calendar.html',
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
            templateUrl: 'views/calendar.html'
        });
});