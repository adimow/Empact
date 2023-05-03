<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <title>Stanciu Adrian</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Stanciu Adrian</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/">Books</a></li>
            <li><a href="add">Add Book</a></li>
        </ul>
    </div>
</nav>

<div class="container">

<c:choose>
    <c:when test ="${mode == 'BOOK_VIEW'}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Book Id</th>
                <th>Name</th>
                <th>Author</th>
                <th>Description</th>
                <th>Editor</th>
                <th>Price</th>
                <th>Stock</th>
                <th>Edit</th>
                <th>Delete</th>


            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.author}</td>
                    <td>${book.name}</td>
                    <td>${book.description}</td>
                    <td>${book.editor}</td>
                    <td>${book.price}</td>
                    <td>${book.stock}</td>
                    <td><a href="editBook?id=${book.id}"><div class="glyphicon glyphicon-pencil"></div> </a> </td>
                    <td><a href="deleteBook?id=${book.id}"><div class="glyphicon glyphicon-trash"></div> </a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:when>
    <c:when test="${mode == 'BOOK_EDIT' || mode == 'BOOK_NEW'}">

        <form action="save" method="POST">
 <input type="hidden" class="form-control" value="${book.id}" name="id" id="id"/>
            <div class="form-group">
                <label for="author">Author:</label>
                <input type="text" class="form-control" value="${book.author}" name="author" id="author">
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" value="${book.name}" name="name" id="name">
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <input type="text" class="form-control" value="${book.description}" name="description" id="description">
            </div>
              <div class="form-group">
                            <label for="editor">Editor:</label>
                            <input type="text" class="form-control" value="${book.editor}" name="editor" id="editor">
                        </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="text" class="form-control" value="${book.price}" name="price" id="price">
            </div>
            <div class="form-group">
                <label for="stock">Stock:</label>
                <input type="text" class="form-control" value="${book.stock}" name="stock" id="stock">
            </div>


            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </c:when>

</c:choose>

    </div>

</body>
</html>
