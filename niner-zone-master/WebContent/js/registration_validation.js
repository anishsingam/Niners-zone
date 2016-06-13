	$(document).ready(function() {
		//AJAX requests for college and department
		var result;
		$('#college_name').change(function() {						
			fillOptions('college_name','select_department');

		});
		//AJAX for UserName
		$('#user_name').keydown(function() {	//Keydown removes the appending of the errors					
			validateUser('user_name');
			
		});
		
		//AJAX for UserName
		$('#user_name').blur(function() {	//Keydown removes the appending of the errors					
			validateUser('user_name');
			
		});
		
		 $('#user_name').keyup(function(e){
		        if (e.which === 32) {
		        	$('#vaildateUser').text("No spaces are allowed in username");//Remove spaces from username
		            //alert('No space are allowed in usernames');
		            var str = $(this).val();
		            str = str.replace(/\s/g,'');
		            $(this).val(str);            
		        }
		    }).blur(function() {
		        var str = $(this).val();
		        str = str.replace(/\s/g,'');
		        $(this).val(str);            
		    });
		
		
		//Date Picker
		$("#datepicker1").datepicker({
			changeMonth:true,
			changeYear:true,
			maxDate: "-10Y",
			minDate: "-100Y",
			yearRange: "-100:-10"
		});
		//Validation Of Fields
		$("#myform").validate(
						{
							rules : {
								userName : {
									required : true,
									minlength : 4
								},
								firstName : "required",
								lastName : "required",
								email : {
									required : true,
									email : true
								},
								userPassword : {
									required : true,
									minlength : 6
								},
								confirmPassword : {
									required : true,
									equalTo : "#password" //Used the selector here
								},
								dateOfBirth : {
									required : true
								},
							},
							messages : {
								userName : {
									required : "Provide an User Name",
									minlength : "Select an Username which is 4 characters long",
									validUser: "This username is already taken"
								},
								firstName : "Provide a First Name",
								lastName : "Provide a Last Name",

								emailAddress : {
									required : "We need your email address to contact you",
									email : "Your email address must be in the format of name@domain.com"
								},
								userPassword : {
									required : "Provide a password which is 6 character in length",
									minlength : "Password needs to be 6 characters long"
								},
								confirmPassword : {
									equalTo : "Both the Passwords must match"
								},
								dateOfBirth : {
									required : "Provide the Date Of Birth"
								}
							}
						});
		
		
	});
	
	//College and Department
	function fillOptions(parentDDId, childDDId) {
		var dd = $('#' + childDDId);
		var jsonURL = 'register?val='+ $('#' + parentDDId + ' :selected').val();
		$.getJSON(jsonURL, function(opts) {
			$('>option', dd).remove(); // Clean old options first.
			if (opts) {
				$.each(opts, function(keyObject, valueObject) {
					var deptId,deptName;
					$.each(valueObject, function(key, value) {						
						if(key=="departmentName"){
							deptName = value;
						}
						if(key=="departmentId"){
							deptId = value;
						}
					});
					dd.append($('<option/>').val(deptId).text(deptName));
				});
			} else {
				dd.append($('<option/>').text("Please Select College"));
			}
		});
	}
	//Username Fetch
	function validateUser(parentDDId) {
		var jsonURL = 'register?username='+ $('#' + parentDDId).val();
		 $.getJSON(jsonURL, function(opts) {
			if (opts) {
				$.each(opts, function(key1, value1) {					
					if(value1 == true){
						$('#vaildateUser').text("Username " +$('#' + parentDDId).val() + " already exists. Please provide another username");
						$('#user_name').val("");
					}
					else{
						$('#vaildateUser').text("");
					}
				});
			} 
		}); 
	}
	
	
	
	
