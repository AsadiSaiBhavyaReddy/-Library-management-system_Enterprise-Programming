<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EP</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .card {
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
        max-width: 400px;
        width: 100%;
        text-align: center;
        transition: box-shadow 0.3s ease-in-out;
        margin-top: -140px;
    }

    .card:hover {
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.5);
    }

    .card h3 {
        color: green;
        margin-bottom: 20px;
    }

    .card a {
        color: #d5724d;
        text-decoration: none;
        font-weight: bold;
        display: inline-block;
        margin-top: 20px;
    }

    .card a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <div class="card">
        <h3>Book Added Successfully</h3>
        <a href="addbook.jsp">Go Back</a>
    </div>
</body>
</html>
