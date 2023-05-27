package gg.jte.generated.ondemand;
public final class JteloginGenerated {
	public static final String JTE_NAME = "login.jte";
	public static final int[] JTE_LINE_INFO = {41,41,41,41,41,41};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		jteOutput.writeContent("<!DOCTYPE html>\r\n<html>\r\n\r\n<head>\r\n    <script src=\"js/pushservice.js\"></script>\r\n    <script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@9\"></script>\r\n</head>\r\n\r\n<body>\r\n\r\n<h2>HTML Forms</h2>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n<form class=\"default-form\" method=\"POST\">\r\n    <label for=\"username\">Username:</label><br>\r\n    <input type=\"text\" id=\"username\" name=\"username\"><br>\r\n    <label for=\"username\">Password:</label><br>\r\n    <input type=\"text\" id=\"password\" name=\"password\"><br><br>\r\n    <input class=\"button\" id=\"login\" type=\"submit\" name=\"login\" value=\"Anmelden\"/><br>\r\n</form>\r\n<button onclick=\"window.location.href='/register'\">Register</button>\r\n\r\n<script type = \"text/javascript\">\r\n    const btn = document.getElementById('login');\r\n\r\n    btn.addEventListener('click', (e) => {\r\n        e.preventDefault()\r\n        login();\r\n    });\r\n</script>\r\n\r\n</body>\r\n</html>\r\n\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
