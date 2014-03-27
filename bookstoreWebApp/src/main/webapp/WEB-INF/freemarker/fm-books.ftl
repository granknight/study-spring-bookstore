<html>
<head>
  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
  <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>
<table class="table table-striped">
  <thead>
  <tr>
    <td>책제목</td>
    <td>Comment</td>
  </tr>
  <#list books as book>
      <tr>
        <td>${book.name}</td>
        <td>${book.comment}</td>
      </tr>
  </#list>
</body>
</html>