package function

object Partial extends App {

  //in this type of function you can use isDefinedAt to see if your function is defined at a specific input
  // to avoid match error
  val fp: PartialFunction[Int, Int] = {
    case a if a > 10 => a
  }

  val res = if (fp.isDefinedAt(2)) fp(2) else fp(102) // <== no idea why 102 :D
  println(res)
}
