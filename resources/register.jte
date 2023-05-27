<!DOCTYPE html>
<html>

<head>
    <script src="js/pushservice.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</head>

<body>

<h2>HTML Forms</h2>



<form class="default-form" method="POST">
    <label for="username">Username:</label><br>
    <input type="text" id="username" name="username"><br>
    <label for="username">Password:</label><br>
    <input type="text" id="password" name="password"><br><br>
    <input class="button" id="register" type="submit" name="login" value="Register"/><br>
</form>


<script type = "text/javascript">
    const btn = document.getElementById('register');

    btn.addEventListener('click', (e) => {
        e.preventDefault()
        register();
    });
</script>

</body>
</html>

