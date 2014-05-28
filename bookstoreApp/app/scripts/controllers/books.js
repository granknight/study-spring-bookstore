'use strict';

angular.module('bookstoreApp')
  .controller('BooksCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    
    $scope.books = [
      {
        name : 'book name01',
        description : 'book description01',
        author : '가나다'
      },
      {
        name : 'book name02',
        description : 'book description02',
        author : '저자이름'
      },
      {
        name : 'book name03',
        description : 'book description03',
        author : '하하하'
      }
    ];
    $scope.query = "";
  });
