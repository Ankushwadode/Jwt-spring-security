# Jwt-spring-security
Basic implementation of jwt token in a application to authenticate user and to implement the level of access in a application.

Its easy to run the code first downlad and extract the code in eclipse and sts import it as existing meaven project and in vs code just open the folder
update the meaven project build once befor running the project.
secondly, crate the database for the project for me it was "springboot" (database name) or you can create your own and for more changes you can do that
in application.properties.
 
 # First request 
(post) http://localhost:8080/register --> url request is used to register the user which takes email, user password,name and phone number.

json data for register
{
    "email": "user@gamil.com",  
    "password": "123",
    "name": "User",
    "phone": "0123456789" 
}

 # Second request
(post) http://localhost:8080/authenticaiton --> url request is  to genrate jwt token by providing register email and password.

json data for authentication
{
    "email":"user@gamil.com",
    "password":"123"
}

 # Third request
(get) http://localhost:8080/api/hello --> urla reqest is used to test the weather the genrated token is working or not for this in postman under authorization tab
in type select Bearer token and provide the genrated token form second request in token field and it will display message "Hello form JWT Authorization".
