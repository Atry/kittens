# kittens: automatic type class derivation for Cats and generic utility functions

**kittens** is a Scala library which provides instances of type classes from the [Cats][cats] library for arbitrary
algebraic data types using [shapeless][shapeless]-based automatic type class derivation. It also provides some utility functions related to cats.Applicative such as lift, traverse and sequence to HList, Record and arbitrary parameter list.

![kittens image](http://plastic-idolatry.com/erik/kittens2x.png)

kittens is part of the [Typelevel][typelevel] family of projects. It is an Open Source project under the Apache
License v2, hosted on [github][source]. Binary artefacts will be published to the [Sonatype OSS Repository Hosting
service][sonatype] and synced to Maven Central.

It is current in a pre-alpha state, but should be ready for an initial release in the very near future.

[![Build Status](https://api.travis-ci.org/milessabin/kittens.png?branch=master)](https://travis-ci.org/milessabin/kittens)
[![Stories in Ready](https://badge.waffle.io/milessabin/kittens.png?label=Ready)](https://waffle.io/milessabin/kittens)
[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/milessabin/kittens)

### Auto derived Examples

```scala

scala> import cats.derived._, functor._, legacy._
scala> import cats.Functor

scala> case class Cat[Food](food: Option[Food], foods: List[Food])
defined class Cat

scala> val f = Functor[Cat]
f: cats.Functor[Cat] = cats.derived.MkFunctor2$$anon$4@782b2ad1

scala> val cat = Cat(Some(1), List(2, 3))
cat: Cat[Int] = Cat(Some(1),List(2, 3))

scala> f.map(cat)(_ + 1)
res3: Cat[Int] = Cat(Some(2),List(3, 4))
```

### Sequence examples

```scala
scala> import cats.implicits._, cats.sequence._
import cats.implicits._
import cats.sequence._

scala> val f1 = (_: String).length
f1: String => Int = <function1>

scala> val f2 = (_: String).reverse
f2: String => String = <function1>

scala> val f3 = (_: String).toFloat
f3: String => Double = <function1>

scala> val f = sequence(f1, f2, f3)
f: String => shapeless.::[Int,shapeless.::[String,shapeless.::[Float,shapeless.HNil]]] = <function1>

scala> f("42.0")
res0: shapeless.::[Int,shapeless.::[String,shapeless.::[Float,shapeless.HNil]]] = 4 :: 0.24 :: 42.0 :: HNil

//or generic over ADTs
scala>  case class MyCase(a: Int, b: String, c: Float)
defined class MyCase

scala>  val myGen = sequenceGeneric[MyCase]
myGen: cats.sequence.sequenceGen[MyCase] = cats.sequence.SequenceOps$sequenceGen@63ae3243

scala> val f = myGen(a = f1, b = f2, c = f3)
f: String => MyCase = <function1>

scala> f("42.0")
res1: MyCase = MyCase(4,0.24,42.0)

```

Traverse works similarly but you need a Poly.

### Lift examples

```scala
scala> import cats._, implicits._, lift._
import cats._
import implicits._
import lift._

scala> def foo(x: Int, y: String, z: Float) = s"$x - $y - $z"

scala> val lifted = Applicative[Option].liftA(foo _)
lifted: (Option[Int], Option[String], Option[Float]) => Option[String] = <function3>

scala> lifted(Some(1), Some("a"), Some(3.2f))
res0: Option[String] = Some(1 - a - 3.2)

```


[cats]: https://github.com/non/cats
[shapeless]: https://github.com/milessabin/shapeless
[typelevel]: http://typelevel.org/
[source]: https://github.com/milessabin/kittens
[sonatype]: https://oss.sonatype.org/

## Participation

The Kittens project supports the [Typelevel][typelevel] [code of conduct][codeofconduct] and wants all of its
channels (mailing list, Gitter, github, etc.) to be welcoming environments for everyone.

[codeofconduct]: http://typelevel.org/conduct.html

## Building kittens

kittens is built with SBT 0.13.9 or later, and its master branch is built with Scala 2.11.7 by default.

## Contributors

+ Cody Allen <ceedubs@gmail.com> [@fourierstrick](https://twitter.com/fourierstrick)
+ Kailuo Wang <kailuo.wang@gmail.com> [@kailuowang](https://twitter.com/kailuowang)
+ Miles Sabin <miles@milessabin.com> [@milessabin](https://twitter.com/milessabin)
+ Your name here :-)
