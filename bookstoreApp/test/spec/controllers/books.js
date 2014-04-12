'use strict';

describe('Controller: BooksCtrl', function () {

  // load the controller's module
  beforeEach(module('bookstoreApp'));

  var BooksCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BooksCtrl = $controller('BooksCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
  
  it('the size of books is 3', function() {
    expect(scope.books.length).toBe(3);
  })
});
