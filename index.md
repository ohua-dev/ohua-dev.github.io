---
redirect_from: "/ohua/"
layout: default
---

The goal of this project is to release the developer from the burden to deal
with constructs for parallelism such as threads, tasks and processes and their
respective synchronization mechanism such as locks, messages, futures etc. We
provide a programming model that is free of new abstractions but still allows
the compiler and runtime system to exploit the inherent parallelism in your
program.

## So, what is your programming model then?

The heart of our programming model is the concept of a **stateful function**,
that is a function that may use some additional state to compute a result, very
similar to methods in object oriented languages.

_-- What?! That's not new, I do that all the time when I write code!_

{% include tabs.html input=site.data.code.simple_example %}

You can now use it like a regular method.

{% include tabs.html input=site.data.code.simple_example_ohua %}

Just as you would in any object oriented language you first initialize the state
and then operate on it. Ohua makes sure that, even though we run your program
implicitly parallel, that state access is efficiently synchronized and safe. The
reason we call it "stateful function" and not method, is that you are not
restricted to methods. You can freely choose the pairing of function and state.
Of the function is a method, the state is its object, if its not, it simply
receives it as an additional first argument.

In our example the state allows us to gather all the friends in
`_alreadyGreeted`. For a list of `friends` containing `Java`, `Rust` and
`JavaScript`, the program would print the following to standard out:

```
--> Hello Java, I already greeted all these guys: []
--> Hello Rust, I already greeted all these guys: [Java]
--> Hello JavaScript, I already greeted all these guys: [Java, Rust]
```

_--  Ok, I got that but show me some parallelism already!_

Well, that's about it. You won't see any parallelism in the code. That's the
whole point of Ohua. You don't have to worry about it, the compiler and runtime
system will do that for you! In the above code, Ohua finds the pipeline
parallelism between `greetings` and `printToStdOut` and tells the runtime system
to compute the next greeting in parallel to printing the current one.


## Foundation

In the literature, our stateful functions are referred to as **state threads**.
In contrast to _pure_ functions, state threads may have _side-effects_ to their
own private state. The foundation for composing state threads is a call-by-need
lambda calculus.

## Publications

Here we list the papers we have published about Ohua and its applications.

Our [first paper](https://dl.acm.org/citation.cfm?id=2807431) on the subject
introduces the basics of the dataflow model underlying Ohua, the notion of
stateful functions and high level algorithms. It was published in 2015 at PPPJ.

The [Yauhau](https://dl.acm.org/citation.cfm?id=3179505) project is a compiler
plugin for Ohua that improves the performance of I/O heavy applications through
automatic batching and caching. The paper was published at CC in 2018 and the
project also has [it's own website](/yauhau/).

At PMAM in 2018 we [published a
paper](https://cfaed.tu-dresden.de/files/Images/people/chair-cc/publications/1802_Ertel_PMAM.pdf)
where we used Ohua to rewrite the core of Hadoop to increase the
read-decode-process-encode pipeline of both map and reduce workers.

For a more extensive and structured view see the [bibliography](/bib/).
