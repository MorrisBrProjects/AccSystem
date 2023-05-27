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
    <input class="button" id="login" type="submit" name="login" value="Anmelden"/><br>
</form>
<button onclick="window.location.href='/register'">Register</button>

<script type = "text/javascript">
    const btn = document.getElementById('login');

    btn.addEventListener('click', (e) => {
        e.preventDefault()
        login();
    });
</script>

</body>
</html>

