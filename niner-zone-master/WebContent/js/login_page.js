/**
 * 
 */
$('document').ready(function() {

	value = $("#error_message").val(); 
	if (value == "Invalid Username or Password"){
		alert(value);
		window.location = "login.jsp";
	}
	$("#login").validate(
			{
				rules: {
					userName: {
						required: true
					},
					userPassword: {
						required:true
					},
				},
				messages: {
					userName: {
						required : "Provide and User Name"
					},
					userPassword: {
						required: "Enter a valid password"
					}
				}
			});
});
