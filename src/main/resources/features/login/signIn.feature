Feature: SignIn 

Background: 
	Given I am on the vendians home page 
	
@Sanity @Positive @C104
Scenario: Sign into vendians
When Click 'sign in' button
And Enter login creditionals