Narrative:
In order to get a quote via http call
As a development team
I would like to use a Rest interface which privides

Scenario:  Basic functionality of a REST API
Meta:
@author eattgyo
@type rest

When I call rest api with get http://echo.jsontest.com/key/value/one/two
Then the response should be 200
And the response body is Response{one='two', key='value'}