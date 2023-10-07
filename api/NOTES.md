


* An adaptable can be adapted to a client interface, and a list of possible default methods implementations. The default method implementations is what the proxy will be based on, the client calls have to come through as default method calls.  It's up to the user to guarantee this for now.
  * The initial object has a state object that is tagged with the original client type. Any adaptation of the object needs to address how to map from/to that original state.
  * 