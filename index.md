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

You can now use it like a regular method in an **algorithm**.

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

## Show me Code!

- Parallelize your Rust code with the [ohua-rust-runtime](https://github.com/ohua-dev/ohua-rust-runtime)
- The standalone compiler for algorithms: [ohuac](https://github.com/ohua-dev/ohuac) (usually used in conjunction with an Ohua backend/runtime)
- Make your enterprise application both fast and secure with the [ohua-jvm-integration](https://github.com/ohua-dev/ohua-jvm-integration) with support for both Java and Clojure (also on [clojars](https://clojars.org/ohua)
- Batch you I/O with little to no effort using the [Yauhau project](https://github.com/ohua-dev/yauhau)
- Hack on/with the [ohua-core](https://github.com/ohua-dev/ohua-core) library to make your own Ohua or help us make it better

You can also visit the [Ohua Development Organization](https://github.com/ohua-dev). The Ohua core library, compiler and backends are developed under this umbrella.

## Publications

Here we list the papers we have published about Ohua and its applications.

* The idea of stateful functions and algorithms that a compiler can translate into a dataflow-based runtime system can be found [here](https://dl.acm.org/citation.cfm?id=2807431).
* Stateful loops (`smap`) and their translation into dataflow have been applied to bottlenecks in big data engines have been published [here](https://cfaed.tu-dresden.de/files/Images/people/chair-cc/publications/1802_Ertel_PMAM.pdf).
* Some internals of our compiler and transformations to optimize I/O in the context of micro-service deployments can be found [here](/yauhau/) and were published also [here](https://dl.acm.org/citation.cfm?id=3179505).

For a more extensive and structured view see the [bibliography](/bib/).
