package typesys

object Variance extends App {

  class A

  class B extends A

  //invariant
  class InVariant[T]
  val i1: InVariant[A] = new InVariant[A]
  //  val i2 : InVariant[A] = new InVariant[B] won't compile


  //covariance
  class CoVariant[+T]
  val co1: CoVariant[A] = new CoVariant[B] // if B extends A then SomeClass[B] also extends SomeClass[A]
  val co2: CoVariant[A] = new CoVariant[A]
  //  val co3 : CoVariant[B] = new CoVariant[A] won't compile

  //contra variant
  class ContraVariant[-T]
  //  val ca1 : ContraVariant[A] = new ContraVariant[B] won't compile'
  val ca2: ContraVariant[A] = new ContraVariant[A]
  val ca3: ContraVariant[B] = new ContraVariant[A] // if B extends A SomeClass[A] also extends SomeClass[B]

// will add more here
}
