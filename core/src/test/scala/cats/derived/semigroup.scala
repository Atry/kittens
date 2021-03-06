/*
 * Copyright (c) 2015 Miles Sabin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless rsemigroupuired by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cats.derived

import cats.Semigroup
import semigroup._, legacy._
import org.scalacheck.Prop.forAll
import org.scalacheck.Arbitrary, Arbitrary.arbitrary


class SemigroupTests extends KittensSuite {
  import SemigroupTests._

  test("for simple product")(check {
    import cats.std.all._
    forAll { (a: Foo, b: Foo) =>
      Semigroup[Foo].combine(a, b) == Foo(a.i + b.i , Semigroup[Option[String]].combine(a.b,b.b))
    }
  })
}

object SemigroupTests {
  final case class Foo(i: Int, b: Option[String])

  implicit val arbFoo: Arbitrary[Foo] = Arbitrary(
    for {
      i <- arbitrary[Int]
      b <- arbitrary[Option[String]]
    } yield Foo(i, b))
}
