'use strict';

var app = angular.module('legalrpf', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ui.router',
  'ui.bootstrap',
  'ui.grid','ui.grid.selection',
  'ngAnimate', 'ngUpload'
]).config(function ($stateProvider, $urlRouterProvider, $locationProvider) {
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
        url : '/home/:name',     
        controller : 'homeCtrl as hc',
        templateUrl : 'static/app/pages/home/home.html'
    }).state('reports',{
        url : '/reports/:name',     
        controller : 'reportsCtrl as rc',
        templateUrl : 'static/app/pages/reports/reports.html'
    })
  }).run(function($rootScope) {
    	$rootScope.selectedUserRole = {
			userId : null,
			userName : null,
			selectedRoleId : null,
			selectedRoleName : null
		};
  });
  