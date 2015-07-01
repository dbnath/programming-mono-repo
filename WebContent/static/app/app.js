'use strict';

var app = angular.module('legalrpf', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ui.router',
  'ui.bootstrap'
])
  .config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
    $urlRouterProvider.otherwise('/login');

   

    $stateProvider.state('login',{ 
      url : '/login',
      templateUrl : 'static/app/pages/login/login.html',
      controller : 'loginCtrl as login'
    }).state('setting',{
        url : '/setting',     
        controller : 'settingCtrl as sc',
        templateUrl : 'static/app/pages/setting/setting.html'
    }).state('home',{
        url : '/home',     
        controller : 'homeCtrl as uh',
        templateUrl : 'static/app/pages/home/home.html'
    })
  });
