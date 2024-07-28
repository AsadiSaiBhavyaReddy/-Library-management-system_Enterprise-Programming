<%@page import="com.klef.ep.beans.AdminBean"%>
<%@page import="com.klef.ep.models.Book"%>
<%@page import="com.klef.ep.services.AdminService"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="adminbean" class="com.klef.ep.beans.AdminBean" />

<!DOCTYPE html>
<html>
<head>
<title>Library Management System</title>
<link type="text/css" rel="stylesheet" href="style.css"/>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-size: cover;
        background-attachment: fixed;
        background-repeat: no-repeat;
        margin: 0;
        padding: 0;
        color: #333;
        background-color: #f9f9f9;
    }

.title-bar {
    background-color: #222;
    color: #ffffff;
    padding: 50px 0; /* Increased vertical padding */
    text-align: center;
    font-size: 24px;
    font-weight: bold;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    z-index: 1000;
}



    .main-navbar {
        background-color: #DDDDDD;
        width: 100%;
        overflow: hidden;
        padding: 10px 0;
        border-bottom: 2px solid #80461B;
        text-align: center;
        margin-top: 60px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        position: fixed;
        top: 60px;
        left: 0;
        z-index: 1000;
    }

    .main-navbar .button {
        color: white;
        background-color: #d5724d;
        border: none;
        padding: 14px 20px;
        text-decoration: none;
        font-size: 16px;
        margin: 0 10px;
        border-radius: 5px;
        text-align: center;
        display: inline-block;
        transition: background-color 0.3s;
    }

    .main-navbar .button:hover {
        background-color: #b45a3f;
    }

    .content {
        margin-top: 150px;
        padding: 20px;
    }

    .paper {
        max-width: 600px;
        background-color: #fff;
        margin: 40px auto;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        border: 1px solid #ddd;
    }

    .paper h2 {
        text-align: center;
        font-size: 22px;
        color: #444;
        margin-bottom: 20px;
        text-decoration: underline;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .form-group input[type="text"],
    .form-group input[type="number"],
    .form-group select,
    .form-group textarea,
    .form-group input[type="file"] {
        width: calc(100% - 20px);
        padding: 8px 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
        color: #333;
        box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
    }

    .form-group textarea {
        resize: vertical;
        height: 100px;
    }

    .form-group input[type="submit"],
    .form-group input[type="reset"] {
        width: 48%;
        padding: 10px 15px;
        font-size: 16px;
        font-weight: bold;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .form-group input[type="submit"] {
        background-color: #28a745;
        color: white;
        margin-right: 2%;
    }

    .form-group input[type="reset"] {
        background-color: #dc3545;
        color: white;
    }

    .form-group input[type="submit"]:hover {
        background-color: #218838;
    }

    .form-group input[type="reset"]:hover {
        background-color: #c82333;
    }
</style>
</head>
<body>

<div class="title-bar">
    Library Management System
</div>

<div class="main-navbar">
    <!-- Example navigation buttons, if needed -->
    <a href="viewallbooksad.jsf" class="button">Back</a>
   
</div>

<div class="content">
    <div class="paper">
        <h2>Update Book</h2>
        <form method="post" action="updatebook" enctype="multipart/form-data">
            <div class="form-group">
                <label for="bid">ID</label>
                <input type="text" id="bid" name="bid" value='<jsp:getProperty property="id" name="adminbean"/>' readonly required>
            </div>
            <div class="form-group">
                <label for="btitle">Title</label>
                <input type="text" id="btitle" name="btitle" required>
            </div>
            <div class="form-group">
                <label for="bcategory">Category</label>
                <select id="bcategory" name="bcategory" required>
                    <option value="">---Select---</option>
                    <option value="Historical">Historical</option>
                    <option value="Academic Text Books">Academic Text Books</option>
                    <option value="Research">Research</option>
                </select>
            </div>
            <div class="form-group">
                <label for="bauthor">Author</label>
                <input type="text" id="bauthor" name="bauthor" required>
            </div>
            <div class="form-group">
                <label for="byear">Year</label>
                <input type="number" id="byear" name="byear" required>
            </div>
            <div class="form-group">
                <label for="bdesc">Description</label>
                <textarea id="bdesc" name="bdesc"></textarea>
            </div>
            <div class="form-group">
                <label for="bimage">Image</label>
                <input type="file" id="bimage" name="bimage" accept="image/*" required>
            </div>
            <div class="form-group">
                <input type="submit" value="Update">
                <input type="reset" value="Clear">
            </div>
        </form>
    </div>
</div>

</body>
</html>
