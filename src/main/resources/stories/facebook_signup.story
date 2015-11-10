Narrative:
In order to sign up on face book web site
As a development team
I would like to use an sign up page by facebook

Scenario:  Sign up successfully
Meta:
@author eattgyo
@type ui
 
Given Browser Running with loaded site http://www.facebook.com having header Üdvözlünk a Facebookon – jelentkezz be, regisztrálj, vagy csak tudj meg többet róla!
When Email field is filed by someinvalidemail@email.com
And Login is pressed
Then Page Redirected to site with error header Hibás e-mail cím