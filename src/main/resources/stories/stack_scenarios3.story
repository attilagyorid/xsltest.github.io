Narrative:
In order to develop an application that requires a stack efficiently
As a development team
I would like to use an interface and implementation in Java directly
 
 
Scenario:  Basic3 functionality of a Stack
Meta:
@author eattgyo
@type simple
 
Given an empty stack
When the string Javascript is added
And the string C++ is added
And the last element is removed again
Then the resulting element should be Javascript
 
Scenario:  Stack2 search
Meta:
@author egyopaj
@type simple
 
Given an empty stack
When the string Erlang is added
And the string C++ is added
And the string PHP is added
And the element Erlang is searched for
Then the position returned should be 3
And we use and additional method