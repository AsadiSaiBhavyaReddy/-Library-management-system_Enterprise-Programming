<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
    <link type="text/css" rel="stylesheet" href="style.css"/>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            position: relative;
            margin: 0;
            padding: 0;
            color: #333;
            background-image: url('https://lh5.googleusercontent.com/proxy/YVNYLiB47tctN9kUQYoNnQCXr2dvkEnIX0dVPaW-J7gXeIrn-m--OO0t6A1hEN99L2sWgtxDeWPj4OLsKFlYcsX4H5RAdZ4YVqMPd7WrVH1iBJc-HcMM4DxVcguKSccxQ0WCiavRDhoOh_ul');
            background-size: cover;
            background-attachment: fixed;
            background-repeat: no-repeat;
            overflow: hidden;
        }

        body::before {
            content: "";
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.3);
            z-index: -1;
        }

        .title-bar {
            background-color: #222;
            color: #ffffff;
            padding: 10px 0;
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

        .form-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin: 190px auto 0;
            width: 60%;
            max-width: 400px;
            transition: background-color 0.3s;
        }

        .form-container:hover {
            background-color: #ffffff;
        }

        .form-container input[type="submit"],
        .form-container input[type="reset"] {
            background-color: #d5724d; /* Mustard yellow background color */
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 10px 5px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .form-container input[type="submit"]:hover,
        .form-container input[type="reset"]:hover {
            background-color: #b45a3f; /* Darker shade of mustard yellow on hover */
        }

    </style>
</head>
<body>

    <div class="title-bar">
        <h1>Library Management System</h1>
    </div>

    <div class="main-navbar">
        <a href="viewalllibrarians.jsf" class="button">View Librarians</a>&nbsp;&nbsp;&nbsp;
        <a href="pendingregs.jsf" class="button">Pending Librarians</a>&nbsp;&nbsp;&nbsp;
        <a href="pendingusers.jsf" class="button">Pending Users</a>&nbsp;&nbsp;&nbsp;
        <a href="viewallusers.jsf" class="button">View Users</a>&nbsp;&nbsp;&nbsp;
        <a href="addbook.jsp" class="button">Add Book</a>&nbsp;&nbsp;&nbsp;
        <a href="viewallbooksad.jsf" class="button">View All Books</a>&nbsp;&nbsp;&nbsp;
        <a href="logout.jsf" class="button">Logout</a>&nbsp;&nbsp;&nbsp;
    </div>

    <div class="form-container">
        <h2 align="center">Add Book</h2>
        <form method="post" action="insertbook" enctype="multipart/form-data">
            <table align="center">
                <tr><td></td></tr>
                <tr>
                    <td><b>Title</b></td>
                    <td><input type="text" name="btitle" required></td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td><b>Category</b></td>
                    <td>
                        <select name="bcategory" required>
                            <option value="">---Select---</option>
                            <option value="Historical">Historical</option>
                            <option value="Academic Text Books">Academic Text Books</option>
                            <option value="Research">Research</option>
                        </select>
                    </td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td><b>Author</b></td>
                    <td><input type="text" name="bauthor" required></td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td><b>Year</b></td>
                    <td><input type="number" name="byear" required></td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td><b>Description</b></td>
                    <td><textarea name="bdesc"></textarea></td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td><b>Image</b></td>
                    <td><input type="file" name="bimage" accept="image/*" required></td>
                </tr>
                <tr><td></td></tr>
                <tr align="center">
                    <td><input type="submit" value="Add" required></td>
                    <td><input type="reset" value="Clear" required></td>
                </tr>
            </table>
        </form>
    </div>

</body>
</html>
