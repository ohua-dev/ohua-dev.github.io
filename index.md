## Ohua - Implicit Parallel Programming for Everyone

The goal of this project is to release the developer from the burden to deal with constructs for parallelism such as threads, tasks and processes and their respective synchronization mechanism such as locks, messages, futures etc. We provide a programming model that is free of new abstractions but still allows the compiler and runtime system to exploit the inherent parallelism in your program.

### So, what is your programming model then?

The heart of our programming model is the concept of a **stateful function**, that is a function that may use some additional state to compute a result. 

_-- What?! That's not new, I do that all the time when I write code!_

```java
class State {
  private List<String> _alreadyGreeted = new LinkedList<>();
  String greetings(String name){
    String greeting = "Hello " + name + "\nI already greeted all these guys: " + _alreadyGreeted;
    _alreadyGreeted.add(name);
    return greeting;
  }
}
```

```rust
struct State {
  _alreadyGreeted: LinkedList<String>
}

impl State {
  pub fn new() -> State {
    State {
      _alreadyGreeted = LinkedList::new()
    }
  }
  
  fn greetings(&self, name:String) -> String {
    let hello = String::from("Hello ");
    let ag:String = self._alreadyGreeted.into_iter().collect();
    let greeting = hello + &name + "\nI already greeted all these guys: " + &ag;
    self._alreadyGreeted.add(name);
    greeting
  }
}
```

That's right. In Ohua, the `greetings` function is a stateful function and you can use it now as any other function when writing your program.

```java
for(String friend : friends){
  val greeting = greetings(friend);
  printToStdOut(greeting);
}
```

Note that you do not have to instantiate the `State` for the `greetings` (and the `printToStdOut`) function. Ohua does that for you and makes sure that you `greetings` operates on one and the same `State` object for each greeted friend. This allows it to gather all the friends in `_alreadyGreeted`.

_--  Ok, I got that but show me some parallelism already!_

Well, that's about it. You won't see any parallelism in the code. That's the whole point of Ohua. You don't have to worry about it, the compiler and runtime system will do that for you! In the above code, Ohua finds the pipeline parallelism between `greetings` and `printToStdOut` and tell the runtime system to compute the next greeting in parallel to printing the current one.


### Foundation

In the literature, our stateful functions are referred to as **state threads**. In contrast to _pure_ functions, state threads may have _side-effects_ to their own private state. The foundation for composing state threads is a call-by-need lambda calculus.


### Logo


Official artwork for the Rust project. This artwork is distributed under the terms of the [Creative Commons Attribution license (CC-BY)](https://creativecommons.org/licenses/by/4.0/). This is the most permissive Creative Commons license, and allows reuse and modifications for any purpose. The restrictions are that distributors must “give appropriate credit, provide a link to the license, and indicate if changes were made”.
We are thankful to [Lucas Vogel](https://github.com/lucasvog) for this cool logo!
