<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SingUp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <h2>Sing Up</h2>
            <div><c:out value="${name}"></c:out></div>
            <c:if test="${errors != null}">
                <c:forEach var="item" items="${errors}">
                    <div><c:out value="${item.getField()}"></c:out> - <c:out value="${item.getDefaultMessage()}"></c:out></div>
                </c:forEach>
            </c:if>
            <form method="post" action="singup">
                <div class="mb-3">
                    <input required name="name" placeholder="Name" class="form-control" />
                </div>
                <div class="mb-3">
                    <input required name="surname" placeholder="Surname" class="form-control" />
                </div>
                <div class="mb-3">
                    <input required name="email" type="email" placeholder="E-mail" class="form-control" />
                </div>
                <div class="mb-3">
                    <select name="city" class="form-select">
                        <option value="istanbul">İstanbul</option>
                        <option value="ankara">Ankara</option>
                        <option value="bursa">Bursa</option>
                    </select>
                </div>
                <div class="mb-3">
                    <input required name="password" type="password" placeholder="Password" class="form-control" />
                </div>
                <button type="submit" class="btn btn-success">Send</button>
            </form>
        </div>
        <div class="col-sm-4"></div>
    </div>
</body>
</html>