package function

import java.util.stream.MatchOps

object HigherOrderFunction extends App {

  val add = (a: Int, b: Int) => a + b

  def sub(a: Int, b: Int): Int = a - b

  val mul: (Int, Int) => Int = (a, b) => a * b

  def div: (Int, Int) => Int = (a, b) => a / b

  def op(a: Int, b: Int, f: (Int, Int) => Int): Int = {
    f(a, b)
  }

  println(op(3, 2, add))
  println(op(3, 2, sub))
  println(op(3, 2, mul))
  println(op(4, 2, div))

  type MathOps = (Int, Int) => Int

  trait Operation

  case object Add extends Operation

  case object Sub extends Operation

  case object Mul extends Operation

  case object Div extends Operation

  def getOp(op: Operation): MathOps = op match {
    case Add => add
    case Sub => sub
    case Mul => mul
    case Div => div

  }

  println(getOp(Add)(4, 2))
  println(getOp(Sub)(4, 2))
  println(getOp(Div)(4, 2))
  println(getOp(Mul)(4, 2))

}
